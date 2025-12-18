package com.hti.xtherm.hti160203u.helper;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Rect;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ai.onnxruntime.*;

public class HumanDetector {
    private OrtEnvironment env;
    private OrtSession session;
    private boolean loaded = false;
    private boolean loading = false;
    private String modelPath;

    public HumanDetector() {
        env = OrtEnvironment.getEnvironment();
    }

    public void setModelPath(String path) {
        this.modelPath = path;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public boolean isLoading() {
        return loading;
    }

    public boolean canLoad() {
        return !isLoaded() && !isLoading();
    }

    public static String loadModelFromAssets(Context context, String assetName) throws IOException {
        AssetManager am = context.getAssets();
        InputStream is = am.open(assetName);

        File tempFile = new File(context.getCacheDir(), assetName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[4096];
            int read;
            while ((read = is.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
            }
        }
        is.close();
        return tempFile.getAbsolutePath();
    }

    /** Load the ONNX model if not loaded yet */
    public void loadModel() throws OrtException {
        if (!loaded && !loading) {
            loading = true;
            OrtSession.SessionOptions options = new OrtSession.SessionOptions();
            session = env.createSession(this.modelPath, options);
            session.getInputInfo().forEach((name, info) -> Log.d("ONNX", info.toString()));
            loaded = true;
            loading = false;
        }
    }

    /** Unload the model and release memory */
    public void unloadModel() {
        if (loaded) {
            try { session.close(); } catch (Exception e) {}
            session = null;
            loaded = false;
        }
    }

    private float[] preprocess(float[] rgb, int width, int height) {
        int size = width * height;
        float[] tensor = new float[3 * size];

        for (int i = 0; i < size; i++) {
            int idx = i * 3;
            tensor[i] = rgb[idx];          // R
            tensor[i + size] = rgb[idx + 1]; // G
            tensor[i + 2 * size] = rgb[idx + 2]; // B
        }

        return tensor;
    }

    private int clamp(int v, int min, int max) {
        return Math.max(min, Math.min(max, v));
    }

    /** Run model and return bounding boxes scaled to original frame */
    public List<float[]> detectHumans(float[] rgb, int width, int height) throws OrtException {
        if (!loaded) return new ArrayList<>();

        float[] tensorData = preprocess(rgb, width, height);
        long[] shape = new long[]{1, 3, height, width}; // [N,C,H,W]
        OnnxTensor inputTensor = OnnxTensor.createTensor(env, FloatBuffer.wrap(tensorData), shape);

        OrtSession.Result output = session.run(Collections.singletonMap(
                session.getInputNames().iterator().next(), inputTensor));

        float[][] raw = ((float[][][]) output.get(0).getValue())[0]; // shape [5,N]
        int N = raw[0].length;
        List<float[]> boxes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            float conf = raw[4][i];
            if (conf < 0.25f) continue;

            float cx = raw[0][i];
            float cy = raw[1][i];
            float w = raw[2][i];
            float h = raw[3][i];

            float x1 = cx - w / 2;
            float y1 = cy - h / 2;
            float x2 = cx + w / 2;
            float y2 = cy + h / 2;

            boxes.add(new float[]{x1, y1, x2, y2, conf});
        }

        return nonMaxSuppression(boxes, 0.45f);
    }

    /** Simple NMS for boxes [x1,y1,x2,y2,conf] */
    private List<float[]> nonMaxSuppression(List<float[]> boxes, float iouThreshold) {
        if (boxes.isEmpty()) return boxes;

        boxes.sort((a,b) -> Float.compare(b[4], a[4]));
        List<float[]> keep = new ArrayList<>();

        boolean[] suppressed = new boolean[boxes.size()];

        for (int i = 0; i < boxes.size(); i++) {
            if (suppressed[i]) continue;
            float[] a = boxes.get(i);
            keep.add(a);

            for (int j = i + 1; j < boxes.size(); j++) {
                if (suppressed[j]) continue;
                float[] b = boxes.get(j);

                float xx1 = Math.max(a[0], b[0]);
                float yy1 = Math.max(a[1], b[1]);
                float xx2 = Math.min(a[2], b[2]);
                float yy2 = Math.min(a[3], b[3]);

                float w = Math.max(0, xx2 - xx1);
                float h = Math.max(0, yy2 - yy1);
                float inter = w * h;
                float iou = inter / ((a[2]-a[0])*(a[3]-a[1]) + (b[2]-b[0])*(b[3]-b[1]) - inter);

                if (iou > iouThreshold) suppressed[j] = true;
            }
        }

        return keep;
    }

    /** Convert bounding boxes to boolean mask for thermal coloring */
    public boolean[] boxesToMask(List<Rect> boxes, int width, int height) {
        boolean[] mask = new boolean[width * height];
        for (Rect r : boxes) {
            for (int y = r.top; y < r.bottom; y++) {
                for (int x = r.left; x < r.right; x++) {
                    mask[y * width + x] = true;
                }
            }
        }
        return mask;
    }
}
