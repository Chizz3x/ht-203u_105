package com.hti.xtherm.hti160203u.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.usb.UsbDevice;
import android.text.TextUtils;
import android.util.Log;

import com.hcusbsdk.Interface.FStreamCallBack;
import com.hcusbsdk.Interface.JavaInterface;
import com.hcusbsdk.Interface.USB_DEVICE_INFO;
import com.hcusbsdk.Interface.USB_FRAME_INFO;
import com.hcusbsdk.Interface.USB_IMAGE_BRIGHTNESS;
import com.hcusbsdk.Interface.USB_IMAGE_CONTRAST;
import com.hcusbsdk.Interface.USB_IMAGE_ENHANCEMENT;
import com.hcusbsdk.Interface.USB_IMAGE_VIDEO_ADJUST;
import com.hcusbsdk.Interface.USB_STREAM_CALLBACK_PARAM;
import com.hcusbsdk.Interface.USB_SYSTEM_DEVICE_INFO;
import com.hcusbsdk.Interface.USB_THERMAL_STREAM_PARAM;
import com.hcusbsdk.Interface.USB_THERMOMETRY_BASIC_PARAM;
import com.hcusbsdk.Interface.USB_THERMOMETRY_EXPERT_REGIONS;
import com.hcusbsdk.Interface.USB_THERMOMETRY_MODE;
import com.hcusbsdk.Interface.USB_VIDEO_PARAM;
import com.hti.xtherm.hti160203u.bean.F2Device;
import com.hti.xtherm.hti160203u.listener.OnLoginCallback;
import com.hti.xtherm.hti160203u.thread.LoginThread;
import com.hti.xtherm.hti160203u.widget.ThermalImageView;
import com.xiao.yuvtools.LibYuv;

import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ai.onnxruntime.OrtException;

/* loaded from: classes.dex */
public class F2Helper {
    private File cache_yuv_file;
    private OnF2DeviceCallback callback;
    private Context context;
    private USB_DEVICE_INFO[] f2_devices;
    private byte[] frame_argb_buffer;
    private byte[] frame_temp_buffer;
    private byte[] frame_yuy2_buffer;
    private Bitmap render_bitmap;
    private Bitmap scale_bitmap;
    private ThermalImageView thermal_view;
    private boolean f2_init = false;
    private int select_index = 0;
    private boolean isPreview = false;
    private int login_userid = -1;
    private F2Device previewDevice = null;
    private int preview_handler = -1;
    private HumanDetector humanDetector = null;
    private USB_IMAGE_ENHANCEMENT mImageEnhancement = new USB_IMAGE_ENHANCEMENT();
    private USB_THERMOMETRY_BASIC_PARAM mThermometryBasicParam = new USB_THERMOMETRY_BASIC_PARAM();
    private USB_THERMOMETRY_EXPERT_REGIONS mThermometryExpertRegions = new USB_THERMOMETRY_EXPERT_REGIONS();
    // ---------- helper: 3x3 median that writes into dst (no allocations per pixel) ----------
    private void medianFilter3x3_inplace(float[] src, float[] dst, int w, int h, float[] winScratch) {
        for (int y = 0; y < h; y++) {
            int row = y * w;
            for (int x = 0; x < w; x++) {
                int k = 0;
                // gather 3x3, clamped
                for (int dy = -1; dy <= 1; dy++) {
                    int yy = y + dy;
                    if (yy < 0) yy = 0;
                    else if (yy >= h) yy = h - 1;
                    int brow = yy * w;
                    for (int dx = -1; dx <= 1; dx++) {
                        int xx = x + dx;
                        if (xx < 0) xx = 0;
                        else if (xx >= w) xx = w - 1;
                        winScratch[k++] = src[brow + xx];
                    }
                }

                // insertion sort 9 elements (small, fast)
                // simple insertion sort in-place on winScratch (only 9 items)
                for (int i = 1; i < 9; i++) {
                    float v = winScratch[i];
                    int j = i - 1;
                    while (j >= 0 && winScratch[j] > v) {
                        winScratch[j + 1] = winScratch[j];
                        j--;
                    }
                    winScratch[j + 1] = v;
                }

                dst[row + x] = winScratch[4]; // median
            }
        }
    }

    // ---------- helper: detail enhancement using preallocated blur ----------
    private void enhanceDetails_inplace(float[] src, float[] dst, float[] blurBuf, int w, int h, float amount, float[] winScratch) {
        medianFilter3x3_inplace(src, blurBuf, w, h, winScratch);

        int n = w * h;
        for (int i = 0; i < n; i++) {
            float edge = src[i] - blurBuf[i];
            dst[i] = src[i] + edge * amount;
        }
    }

    // ---------- brightness/contrast helper (same as you had) ----------
    private float applyBrightnessContrastFloat(float v, float brightness, float contrast) {
        v = (v - 0.5f) * contrast + 0.5f;
        v += brightness;
        if (v < 0f) v = 0f;
        else if (v > 1f) v = 1f;
        return v;
    }

    private boolean isOnAnyBoxEdge(
            int x, int y,
            List<float[]> boxes,
            int t
    ) {
        for (float[] b : boxes) {
            int x1 = Math.round(b[0]);
            int y1 = Math.round(b[1]);
            int x2 = Math.round(b[2]);
            int y2 = Math.round(b[3]);

            // Quick reject (very important for performance)
            if (x < x1 - t || x > x2 + t ||
                    y < y1 - t || y > y2 + t) {
                continue;
            }

            boolean insideX = x >= x1 && x <= x2;
            boolean insideY = y >= y1 && y <= y2;

            boolean left   = Math.abs(x - x1) < t && insideY;
            boolean right  = Math.abs(x - x2) < t && insideY;
            boolean top    = Math.abs(y - y1) < t && insideX;
            boolean bottom = Math.abs(y - y2) < t && insideX;

            if (left || right || top || bottom) {
                return true;
            }
        }
        return false;
    }

    // ---------- main pipeline (optimized) ----------
    private void tempToArgb(byte[] tempBuffer, byte[] argbBuffer, int width, int height, Config.PaletteType pType) throws OrtException {
        final int size = width * height;
        float[] temps = new float[size];
        float frameMin = Float.MAX_VALUE;
        float frameMax = -Float.MAX_VALUE;

        // --- Step 1: convert raw temps to Celsius ---
        for (int i = 0; i < size; i++) {
            int raw = (tempBuffer[i * 2] & 0xFF) | ((tempBuffer[i * 2 + 1] & 0xFF) << 8);
            float t = raw / 64.0f - 50.0f;
            temps[i] = t;
            frameMin = Math.min(frameMin, t);
            frameMax = Math.max(frameMax, t);
        }

        float configMin = getTempRange() == Config.TempRange.SMALL ? -20f : 120f;
        float configMax = getTempRange() == Config.TempRange.SMALL ? 120f : 550f;
        float effectiveMin = Math.max(frameMin, configMin);
        float effectiveMax = Math.min(frameMax, configMax);
        if (effectiveMax - effectiveMin < 1e-3f) effectiveMax = effectiveMin + 1f;

        // --- Step 2: allocate scratch buffers once ---
        float[] scratchWin = new float[9];
        float[] blurBuf = new float[size];
        float[] tmpBuf = new float[size];

        // --- Step 3: noise reduction ---
        if (ShareHelper.getNoiseEnable()) {
            int passes = Math.max(1, ShareHelper.getNoise() / 30);
            float[] src = temps;
            float[] dst = blurBuf;
            for (int p = 0; p < passes; p++) {
                medianFilter3x3_inplace(src, dst, width, height, scratchWin);
                float[] t = src; src = dst; dst = t;
            }
            temps = src;
        }

        // --- Step 4: detail enhancement ---
        if (ShareHelper.getDetailEnable()) {
            float amount = ShareHelper.getDetail() / 100f * 1.2f;
            enhanceDetails_inplace(temps, tmpBuf, blurBuf, width, height, amount, scratchWin);
            temps = tmpBuf;
        }

        // --- Step 6: precompute brightness/contrast ---
        float brightness = (ShareHelper.getBrightness() - 50) / 100f;
        float contrast   = 1f + ((ShareHelper.getContrast() - 50) / 50f);
        if (contrast < 0f) contrast = 0f;

        List<float[]> detectedBoxes = pType == Config.PaletteType.HUMAN_DETECT || pType == Config.PaletteType.HUMAN_DETECT_BOX
                ? getInterpolatedBoxes(temps, width, height, effectiveMin, effectiveMax)
                : Collections.emptyList();

        for (int i = 0; i < size; i++) {
            if (pType == Config.PaletteType.HUMAN_DETECT_BOX) {
                if(isOnAnyBoxEdge(i % width, i / width, detectedBoxes, 1)) {
                    int idx = i * 4;
                    int color = 0xFF00FF00;
                    argbBuffer[idx]     = (byte) ((color >> 16) & 0xFF);
                    argbBuffer[idx + 1] = (byte) ((color >> 8) & 0xFF);
                    argbBuffer[idx + 2] = (byte) (color & 0xFF);
                    argbBuffer[idx + 3] = (byte) ((color >> 24) & 0xFF);
                    continue;
                }
            }

            float normalized = (temps[i] - effectiveMin) / (effectiveMax - effectiveMin);
            normalized = Math.max(0f, Math.min(1f, normalized));
            normalized = applyBrightnessContrastFloat(normalized, brightness, contrast);

            boolean insideBox = false;
            if (pType == Config.PaletteType.HUMAN_DETECT) {
                int x = i % width;
                int y = i / width;
                for (float[] box : detectedBoxes) {
                    if (box[0] <= x && x <= box[2] && box[1] <= y && y <= box[3] && box[4] >= 0.5f) {
                        insideBox = true;
                        break;
                    }
                }
            }

            int color;
            switch (pType) {
                case RGB:  color = paletteRGB(normalized); break;
                case WHBC: color = paletteWhiteHotBlueCold(normalized); break;
                case HUMAN_ORANGE: color = paletteHumanOrange(temps[i]); break;
                case HUMAN_DETECT: color = paletteDetect(temps[i], insideBox); break;
                case HUMAN_DETECT_BOX: color = paletteHumanOrange(temps[i]); break;
                case TEST: color = paletteHumanOrange(temps[i]); break;
                default:   color = paletteRGB(normalized); break;
            }

            int idx = i * 4;
            argbBuffer[idx]     = (byte) ((color >> 16) & 0xFF);
            argbBuffer[idx + 1] = (byte) ((color >> 8) & 0xFF);
            argbBuffer[idx + 2] = (byte) (color & 0xFF);
            argbBuffer[idx + 3] = (byte) ((color >> 24) & 0xFF);
        }
    }

    private List<float[]> lastHumanBoxes = new ArrayList<>();
    private List<float[]> nextHumanBoxes = new ArrayList<>();
    private int frameCounter = 0;
    private int detectEveryNFrames = 10; // run detection every N frames

    public List<float[]> getInterpolatedBoxes(float[] temps, int width, int height, float effectiveMin, float effectiveMax) throws OrtException {
        if (frameCounter % detectEveryNFrames == 0) {
            // Detection frame: store current as nextHumanBoxes
            int size = width * height;
            float[] rgbBuffer = new float[size * 3];
            for (int i = 0; i < size; i++) {
                float normalized = (temps[i] - effectiveMin) / (effectiveMax - effectiveMin);
                normalized = Math.max(0f, Math.min(1f, normalized));

                // Use simple pseudo-RGB for model detection
                rgbBuffer[i * 3] = normalized;       // R
                rgbBuffer[i * 3 + 1] = normalized;   // G
                rgbBuffer[i * 3 + 2] = normalized;   // B
            }
            nextHumanBoxes = this.humanDetector.detectHumans(rgbBuffer, width, height);
            frameCounter = 0;
            lastHumanBoxes = nextHumanBoxes; // save for interpolation
            frameCounter++;
            return nextHumanBoxes;
        } else {
            // Interpolation frame: interpolate between last and next
            List<float[]> interpolated = new ArrayList<>();
            float alpha = frameCounter / (float) detectEveryNFrames;

            for (int i = 0; i < Math.min(lastHumanBoxes.size(), nextHumanBoxes.size()); i++) {
                float[] boxLast = lastHumanBoxes.get(i);
                float[] boxNext = nextHumanBoxes.get(i);
                float[] interp = new float[5];
                for (int j = 0; j < 5; j++) {
                    interp[j] = boxLast[j] * (1f - alpha) + boxNext[j] * alpha;
                }
                interpolated.add(interp);
            }

            frameCounter++;
            return interpolated;
        }
    }

    private int paletteDetect(float tempC, boolean insideHumanBox) {
        // ---- 1. Convert tempC to normalized 0..1 range (global full palette range)
//        final float tMin = 0f;     // global low bound (adjustable)
//        final float tMax = 60f;    // global high bound (adjustable)
//        float tNorm = (tempC - tMin) / (tMax - tMin);

        // clamp
//        if (tNorm < 0f) tNorm = 0f;
//        if (tNorm > 1f) tNorm = 1f;
        float tNorm = tempC;
//        Log.i("tempC", tempC + "");
        // ---- 2. Modify tNorm based on human/background
        if (!insideHumanBox) {
            // Background: cap to lower end of palette
            // Smooth, soft, full gradient but muted
//            tNorm *= 0.35f;
            tNorm = Math.min(tNorm, 25f);
        } else {
//            tNorm = tNorm * 1.2f;
        }
        // else: inside, keep full range (0..1)

        // ---- 3. Apply ONE palette interpolation (your human palette)
        return paletteHumanOrange(tNorm);
    }

    // Example palette mapping function
    private int paletteRGB(float normalized) {
//        normalized = Math.max(0f, Math.min(1f, normalized));

        int r, g, b;
        if (normalized < 0.5f) {
            float t = normalized / 0.5f;
            r = 0;
            g = (int) (255 * t);
            b = (int) (255 * (1 - t));
        } else {
            float t = (normalized - 0.5f) / 0.5f;
            r = (int) (255 * t);
            g = (int) (255 * (1 - t));
            b = 0;
        }

        int a = 255;
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
    private int paletteWhiteHotBlueCold(float normalized) {
//        normalized = Math.max(0f, Math.min(1f, normalized));

        // Interpolate between: blue (0,0,255) → white (255,255,255)
        int r = (int) (255 * normalized);
        int g = (int) (255 * normalized);
        int b = 255;

        int a = 255;
        return (a << 24) | (r << 16) | (g << 8) | b;
    }
    private int paletteHumanOrange(float tempC) {
        final float[] tempStops = {20f, 36f, 40f, 50f};
        final int[] colorStops = {
                0xFF0000FF, // cold background: blue
                0xFFFFA500, // human: orange
                0xFFFF4500, // hotter than human: dark orange/red
                0xFFFF0000  // extremely hot: red
        };

        // Clamp temp
        if (tempC <= tempStops[0]) return colorStops[0];
        if (tempC >= tempStops[tempStops.length - 1]) return colorStops[colorStops.length - 1];

        // Find segment
        int i;
        for (i = 0; i < tempStops.length - 1; i++) {
            if (tempC <= tempStops[i + 1]) break;
        }

        float t = (tempC - tempStops[i]) / (tempStops[i + 1] - tempStops[i]);
        t = Math.max(0f, Math.min(1f, t));

        // Interpolate RGB
        int c0 = colorStops[i];
        int c1 = colorStops[i + 1];

        int a0 = (c0 >> 24) & 0xFF;
        int r0 = (c0 >> 16) & 0xFF;
        int g0 = (c0 >> 8) & 0xFF;
        int b0 = c0 & 0xFF;

        int a1 = (c1 >> 24) & 0xFF;
        int r1 = (c1 >> 16) & 0xFF;
        int g1 = (c1 >> 8) & 0xFF;
        int b1 = c1 & 0xFF;

        int a = (int)(a0 + (a1 - a0) * t);
        int r = (int)(r0 + (r1 - r0) * t);
        int g = (int)(g0 + (g1 - g0) * t);
        int b = (int)(b0 + (b1 - b0) * t);

        return (a << 24) | (r << 16) | (g << 8) | b;
    }
    private FStreamCallBack frame_callback = new FStreamCallBack() { // from class: com.hti.xtherm.hti160203u.helper.F2Helper.1
        @Override // com.hcusbsdk.Interface.FStreamCallBack
        public void invoke(int i, USB_FRAME_INFO usb_frame_info) throws OrtException {
            byte[] bArrCopyOf = Arrays.copyOf(usb_frame_info.pBuf, usb_frame_info.dwBufSize);
            int i2 = ByteBuffer.wrap(Arrays.copyOfRange(bArrCopyOf, 4, 8)).order(ByteOrder.LITTLE_ENDIAN).getInt();
            if (i2 >= 0) {
                int i3 = i2 + 4;
                if (F2Helper.this.previewDevice.getTempBufferSize() + i3 + F2Helper.this.previewDevice.getYuvBufferSize() > 0 && bArrCopyOf.length >= F2Helper.this.previewDevice.getTempBufferSize() + i3 + F2Helper.this.previewDevice.getYuvBufferSize()) {
                    if (F2Helper.this.frame_temp_buffer == null) {
                        F2Helper f2Helper = F2Helper.this;
                        f2Helper.frame_temp_buffer = new byte[f2Helper.previewDevice.getTempBufferSize()];
                    }
                    System.arraycopy(bArrCopyOf, i3, F2Helper.this.frame_temp_buffer, 0, F2Helper.this.frame_temp_buffer.length);
                    F2Helper f2Helper2 = F2Helper.this;
                    f2Helper2.on_f2_temperature(f2Helper2.frame_temp_buffer, F2Helper.this.previewDevice.thermal_width, F2Helper.this.previewDevice.thermal_height, true);
                    if (F2Helper.this.frame_yuy2_buffer == null) {
                        F2Helper f2Helper3 = F2Helper.this;
                        f2Helper3.frame_yuy2_buffer = new byte[f2Helper3.previewDevice.getYuvBufferSize()];
                    }
                    System.arraycopy(bArrCopyOf, i3 + F2Helper.this.frame_temp_buffer.length, F2Helper.this.frame_yuy2_buffer, 0, F2Helper.this.frame_yuy2_buffer.length);
                    if (F2Helper.this.frame_argb_buffer == null) {
                        F2Helper f2Helper4 = F2Helper.this;
                        f2Helper4.frame_argb_buffer = new byte[f2Helper4.previewDevice.getArgb32BufferSize()];
                    }
                    if (F2Helper.this.thermal_view == null) {
                        return;
                    }
                    Config.PaletteType paletteMode = ShareHelper.getPaletteType();

                    // Human detection model loading
                    if (
                        paletteMode == Config.PaletteType.HUMAN_DETECT
                        || paletteMode == Config.PaletteType.HUMAN_DETECT_BOX
                    ) {
                        if(humanDetector.canLoad()) {
                            Log.i("HumanDetector", "Load");
                            humanDetector.loadModel();
                        }
                    } else {
                        if(humanDetector.isLoaded()) {
                            Log.i("HumanDetector", "UnLoad");
                            humanDetector.unloadModel();
                        }
                    }

                    // CUSTOM
                    if(Config.PaletteType.isCustom(paletteMode)) {
                        tempToArgb(
                            F2Helper.this.frame_temp_buffer,
                            F2Helper.this.frame_argb_buffer,
                            F2Helper.this.previewDevice.thermal_width,
                            F2Helper.this.previewDevice.thermal_height,
                            paletteMode
                        );
                    } else {
                        if (!F2Helper.this.libYuv.YUV2_TO_ARGB(F2Helper.this.frame_yuy2_buffer, F2Helper.this.frame_argb_buffer, F2Helper.this.previewDevice.yuv_width, F2Helper.this.previewDevice.yuv_height)) {
                            return;
                        }
                    }
                    if (F2Helper.this.render_bitmap == null) {
                        F2Helper f2Helper5 = F2Helper.this;
                        f2Helper5.render_bitmap = Bitmap.createBitmap(f2Helper5.previewDevice.yuv_width, F2Helper.this.previewDevice.yuv_height, Bitmap.Config.ARGB_8888);
                    }
                    Canvas canvasLockCanvas = F2Helper.this.thermal_view.getThermalRenderView().lockCanvas();
                    if (canvasLockCanvas != null) {
                        F2Helper.this.render_bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(F2Helper.this.frame_argb_buffer));
                        Mat mat = new Mat();
                        Utils.bitmapToMat(F2Helper.this.render_bitmap, mat);
                        Mat mat2 = new Mat();
                        int measuredWidth = F2Helper.this.thermal_view.getMeasuredWidth();
                        int measuredHeight = F2Helper.this.thermal_view.getMeasuredHeight();
                        Imgproc.resize(mat, mat2, new Size(measuredWidth, measuredHeight));
                        if (F2Helper.this.scale_bitmap == null || F2Helper.this.scale_bitmap.getWidth() != measuredWidth || F2Helper.this.scale_bitmap.getHeight() != measuredHeight) {
                            F2Helper.this.scale_bitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
                        }
                        Utils.matToBitmap(mat2, F2Helper.this.scale_bitmap);
                        canvasLockCanvas.drawBitmap(F2Helper.this.scale_bitmap, (Rect) null, new Rect(0, 0, F2Helper.this.thermal_view.getThermalRenderView().getMeasuredWidth(), F2Helper.this.thermal_view.getThermalRenderView().getMeasuredHeight()), (Paint) null);
                        F2Helper.this.thermal_view.getThermalRenderView().unlockCanvasAndPost(canvasLockCanvas);
                        mat.release();
                        mat2.release();
                        return;
                    }
                    return;
                }
            }
            Alog.e("数据长度不正确,错误帧", new Object[0]);
        }
    };
    private JavaInterface javaInterface = JavaInterface.getInstance();
    private LibYuv libYuv = new LibYuv();

    public interface OnF2DeviceCallback {
        void on_f2_disconnect();

        void on_f2_temperature(byte[] bArr, int i, int i2, boolean z);

        void on_open_f2_failed();

        void on_open_f2_success();
    }

    public F2Helper(Context context, OnF2DeviceCallback onF2DeviceCallback) throws IOException {
        Log.d("F2Helper", "run start");
        this.context = context;
        this.callback = onF2DeviceCallback;
        this.humanDetector = new HumanDetector();
        this.humanDetector.setModelPath(HumanDetector.loadModelFromAssets(context, "model.onnx"));
        init();
        Log.d("F2Helper", "run finish");
    }

    public void setThermalImageView(ThermalImageView thermalImageView) {
        this.thermal_view = thermalImageView;
    }

    public void setPalette(Config.PaletteType paletteType) throws IllegalAccessException {
        USB_IMAGE_ENHANCEMENT usb_image_enhancement;
        if (!this.isPreview || (usb_image_enhancement = this.mImageEnhancement) == null || this.login_userid == -1) {
            return;
        }
        usb_image_enhancement.byPaletteMode = (byte) paletteType.getPalette();
        Log.i("palette change", paletteType.toString());
        Alog.e("设置调色板：" + paletteType + ", rst = " + this.javaInterface.USB_SetImageEnhancement(this.login_userid, this.mImageEnhancement), new Object[0]);
    }

    public boolean setTempRange(Config.TempRange tempRange) throws InterruptedException, IllegalAccessException {
        USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param;
        if (this.isPreview && (usb_thermometry_basic_param = this.mThermometryBasicParam) != null && this.login_userid != -1) {
            usb_thermometry_basic_param.byTemperatureRange = tempRange.getTempRange();
            if (this.javaInterface.USB_SetThermometryBasicParam(this.login_userid, this.mThermometryBasicParam)) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param2 = new USB_THERMOMETRY_BASIC_PARAM();
                while (System.currentTimeMillis() - jCurrentTimeMillis <= 15000) {
                    this.javaInterface.USB_GetThermometryBasicParam(this.login_userid, usb_thermometry_basic_param2);
                    if (usb_thermometry_basic_param2.byTemperatureRange == tempRange.getTempRange()) {
                        Alog.e("增益设置成功！", new Object[0]);
                        return true;
                    }
                    Thread.sleep(100L);
                }
            }
        }
        return false;
    }

    public Config.TempRange getTempRange() {
        USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param;
        if (!this.isPreview || (usb_thermometry_basic_param = this.mThermometryBasicParam) == null || this.login_userid == -1) {
            return Config.TempRange.SMALL;
        }
        if (usb_thermometry_basic_param.byTemperatureRange == 3) {
            return Config.TempRange.LARGE;
        }
        return Config.TempRange.SMALL;
    }

    public void setEmissivity(int i) throws IllegalAccessException {
        USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param;
        if (i < 1 || i > 100 || !this.isPreview || (usb_thermometry_basic_param = this.mThermometryBasicParam) == null || this.login_userid == -1) {
            return;
        }
        usb_thermometry_basic_param.dwEmissivity = i;
        boolean zUSB_SetThermometryBasicParam = this.javaInterface.USB_SetThermometryBasicParam(this.login_userid, this.mThermometryBasicParam);
        USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param2 = new USB_THERMOMETRY_BASIC_PARAM();
        this.javaInterface.USB_GetThermometryBasicParam(this.login_userid, usb_thermometry_basic_param2);
        Alog.e("设置后发射率：" + usb_thermometry_basic_param2.dwEmissivity, new Object[0]);
        Alog.e("设置发射率：" + i + ", rst = " + zUSB_SetThermometryBasicParam + ",ERROR = " + this.javaInterface.USB_GetLastError(), new Object[0]);
    }

    public void setOpticalTemperature(int i) {
        USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param;
        if (i < -400 || i > 800 || !this.isPreview || (usb_thermometry_basic_param = this.mThermometryBasicParam) == null || this.login_userid == -1) {
            return;
        }
        usb_thermometry_basic_param.dwExternalOpticsWindowCorrection = (int) (((i / 10.0f) + 100.0f) * 10.0f);
        Alog.e("设置光学温度：" + i + ", rst = " + this.javaInterface.USB_SetThermometryBasicParam(this.login_userid, this.mThermometryBasicParam), new Object[0]);
    }

    public void setReflectionTemperature(int i, boolean z) {
        USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param;
        if (!this.isPreview || (usb_thermometry_basic_param = this.mThermometryBasicParam) == null || this.login_userid == -1) {
            return;
        }
        if (!z || (i >= -1000 && i <= 10000)) {
            usb_thermometry_basic_param.byReflectiveEnable = z ? (byte) 1 : (byte) 0;
            this.mThermometryBasicParam.dwReflectiveTemperature = (int) (((i / 10.0f) + 100.0f) * 10.0f);
            Alog.e("设置反射温度：" + i + ",enable = " + z + ", rst = " + this.javaInterface.USB_SetThermometryBasicParam(this.login_userid, this.mThermometryBasicParam), new Object[0]);
        }
    }

    public void setDistance(int i) {
        USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param;
        if (i < 30 || i > 300 || !this.isPreview || (usb_thermometry_basic_param = this.mThermometryBasicParam) == null || this.login_userid == -1) {
            return;
        }
        usb_thermometry_basic_param.byDistanceUnit = (byte) 2;
        this.mThermometryBasicParam.dwDistance = i;
        Alog.e("设置距离：" + i + ", rst = " + this.javaInterface.USB_SetThermometryBasicParam(this.login_userid, this.mThermometryBasicParam), new Object[0]);
    }

    public void setImageBrightness(int i) throws IllegalAccessException {
        if (i < 0 || i > 100 || !this.isPreview || this.login_userid == -1) {
            return;
        }
        USB_IMAGE_BRIGHTNESS usb_image_brightness = new USB_IMAGE_BRIGHTNESS();
        usb_image_brightness.dwBrightness = i;
        Alog.e("设置图片亮度：" + i + ", rst = " + this.javaInterface.USB_SetImageBrightNess(this.login_userid, usb_image_brightness), new Object[0]);
    }

    public void setImageContrast(int i) throws IllegalAccessException {
        if (i < 0 || i > 100 || !this.isPreview || this.login_userid == -1) {
            return;
        }
        USB_IMAGE_CONTRAST usb_image_contrast = new USB_IMAGE_CONTRAST();
        usb_image_contrast.dwContrast = i;
        Alog.e("设置图片对比度：" + i + ", rst = " + this.javaInterface.USB_SetImageContrast(this.login_userid, usb_image_contrast), new Object[0]);
    }

    public void setNoiseReducation(int i, boolean z) throws IllegalAccessException {
        USB_IMAGE_ENHANCEMENT usb_image_enhancement;
        if (!this.isPreview || (usb_image_enhancement = this.mImageEnhancement) == null || this.login_userid == -1) {
            return;
        }
        if (!z || (i >= 0 && i <= 100)) {
            usb_image_enhancement.byNoiseReduceMode = (byte) (z ? 2 : 0);
            this.mImageEnhancement.dwFrameNoiseReduceLevel = i;
            this.mImageEnhancement.dwInterFrameNoiseReduceLevel = i;
            Alog.e("设置图像降噪：" + i + ",enable = " + z + ", rst = " + this.javaInterface.USB_SetImageEnhancement(this.login_userid, this.mImageEnhancement), new Object[0]);
        }
    }

    public void setDetailEnhancement(int i, boolean z) throws IllegalAccessException {
        USB_IMAGE_ENHANCEMENT usb_image_enhancement;
        if (!this.isPreview || (usb_image_enhancement = this.mImageEnhancement) == null || this.login_userid == -1) {
            return;
        }
        if (!z || (i >= 0 && i <= 100)) {
            usb_image_enhancement.byLSEDetailEnabled = z ? (byte) 1 : (byte) 0;
            this.mImageEnhancement.dwLSEDetailLevel = i;
            Alog.e("设置图像细节增强：" + i + ",enable = " + z + ", rst = " + this.javaInterface.USB_SetImageEnhancement(this.login_userid, this.mImageEnhancement), new Object[0]);
        }
    }

    public F2Device getPreviewDevice() {
        return this.previewDevice;
    }

    private boolean init() {
        if (this.f2_init) {
            return true;
        }
        if (this.javaInterface.USB_Init()) {
            this.f2_devices = new USB_DEVICE_INFO[128];
            for (int i = 0; i < 128; i++) {
                this.f2_devices[i] = new USB_DEVICE_INFO();
            }
            this.f2_init = true;
        } else {
            Alog.e("初始化失败", new Object[0]);
            this.f2_init = false;
        }
        return this.f2_init;
    }

    public boolean resetThermometryParameter() {
        USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param;
        if (!this.isPreview || (usb_thermometry_basic_param = this.mThermometryBasicParam) == null || this.login_userid == -1) {
            return false;
        }
        usb_thermometry_basic_param.byTemperatureRange = Config.TempRange.SMALL.getTempRange();
        this.mThermometryBasicParam.dwEmissivity = 95;
        this.mThermometryBasicParam.dwExternalOpticsWindowCorrection = 1300;
        this.mThermometryBasicParam.byReflectiveEnable = (byte) 0;
        this.mThermometryBasicParam.dwReflectiveTemperature = 1500;
        this.mThermometryBasicParam.byDistanceUnit = (byte) 2;
        this.mThermometryBasicParam.dwDistance = 30;
        boolean zUSB_SetThermometryBasicParam = this.javaInterface.USB_SetThermometryBasicParam(this.login_userid, this.mThermometryBasicParam);
        Alog.e("重置红外参数：rst = " + zUSB_SetThermometryBasicParam, new Object[0]);
        return zUSB_SetThermometryBasicParam;
    }

    public boolean resetImageParameter() throws IllegalAccessException {
        if (!this.isPreview || this.mImageEnhancement == null || this.login_userid == -1) {
            return false;
        }
        ShareHelper.setNoiseEnable(true);
        this.mImageEnhancement.byNoiseReduceMode = (byte) (ShareHelper.getNoiseEnable() ? 2 : 0);
        ShareHelper.setNoise(50);
        this.mImageEnhancement.dwFrameNoiseReduceLevel = ShareHelper.getNoise();
        this.mImageEnhancement.dwInterFrameNoiseReduceLevel = ShareHelper.getNoise();
        ShareHelper.setPaletteType(Config.DEF_PALETTE);
        this.mImageEnhancement.byPaletteMode = (byte) ShareHelper.getPaletteType().getPalette();
        ShareHelper.setDetailEnable(false);
        this.mImageEnhancement.byLSEDetailEnabled = ShareHelper.getDetailEnable() ? (byte) 1 : (byte) 0;
        ShareHelper.setDetail(25);
        this.mImageEnhancement.dwLSEDetailLevel = ShareHelper.getDetail();
        boolean zUSB_SetImageEnhancement = this.javaInterface.USB_SetImageEnhancement(this.login_userid, this.mImageEnhancement);
        Alog.e("重置图像参数：rst = " + zUSB_SetImageEnhancement, new Object[0]);
        return zUSB_SetImageEnhancement;
    }

    public void shutter() throws IllegalAccessException {
        int i;
        if (!this.isPreview || (i = this.login_userid) == -1) {
            return;
        }
        this.javaInterface.USB_SetImageManualCorrect(i);
    }

    public boolean isPreview() {
        return this.isPreview;
    }

    public void clean() {
        this.javaInterface.USB_Cleanup();
    }

    public void deviceAttachedF2(UsbDevice usbDevice) {
        int i = 0;
        Log.d("info", "TEST");
        if (this.isPreview) {
            Alog.e("正在预览，不处理设备接入事件", new Object[0]);
            return;
        }
        int iUSB_GetDeviceCount = this.javaInterface.USB_GetDeviceCount(this.context);
        if (iUSB_GetDeviceCount <= 0) {
            Alog.e("没有发现设备", new Object[0]);
            return;
        }
        if (!this.javaInterface.USB_EnumDevices(iUSB_GetDeviceCount, this.f2_devices)) {
            Alog.e("枚举设备失败", new Object[0]);
            return;
        }
        this.select_index = 0;
        if (usbDevice != null) {
            int vendorId = usbDevice.getVendorId();
            int productId = usbDevice.getProductId();
            String strTrim = usbDevice.getSerialNumber().trim();
            while (true) {
                if (i < iUSB_GetDeviceCount) {
                    if (this.f2_devices[i].dwVID == vendorId && this.f2_devices[i].dwPID == productId && !TextUtils.isEmpty(this.f2_devices[i].szSerialNumber) && this.f2_devices[i].szSerialNumber.trim().equals(strTrim)) {
                        this.select_index = i;
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
        }
        LoginThread.login(this.f2_devices[this.select_index], new OnLoginCallback() { // from class: com.hti.xtherm.hti160203u.helper.F2Helper.2
            @Override // com.hti.xtherm.hti160203u.listener.OnLoginCallback
            public void onLoginFailed() {
                F2Helper.this.on_open_f2_failed();
                Alog.e("设备登录失败", new Object[0]);
                F2Helper.this.release();
            }

            @Override // com.hti.xtherm.hti160203u.listener.OnLoginCallback
            public void onLoginSuccess(int i2) throws IllegalAccessException {
                F2Helper.this.login_userid = i2;
                if (!F2Helper.this.initPreviewDevice() || F2Helper.this.previewDevice == null) {
                    F2Helper.this.on_open_f2_failed();
                    F2Helper.this.release();
                    return;
                }
                F2Helper.this.initDeviceParam();
                Alog.e("等待2s开始：" + System.currentTimeMillis(), new Object[0]);
                try {
                    Thread.sleep(2000L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Alog.e("等待2s结束：" + System.currentTimeMillis(), new Object[0]);
                if (!F2Helper.this.startPreview()) {
                    F2Helper.this.isPreview = false;
                    F2Helper.this.on_open_f2_failed();
                } else {
                    Alog.e("开始预览", new Object[0]);
                    F2Helper.this.isPreview = true;
                    F2Helper.this.on_open_f2_success();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean initPreviewDevice() {
        String str;
        if (this.javaInterface == null || this.login_userid == -1) {
            return false;
        }
        USB_SYSTEM_DEVICE_INFO usb_system_device_info = new USB_SYSTEM_DEVICE_INFO();
        if (!this.javaInterface.USB_GetSysTemDeviceInfo(this.login_userid, usb_system_device_info)) {
            return false;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\r\nbyFirmwareVersion = " + usb_system_device_info.byFirmwareVersion + "\r\n");
        stringBuffer.append("byEncoderVersion = " + usb_system_device_info.byEncoderVersion + "\r\n");
        stringBuffer.append("byHardwareVersion = " + usb_system_device_info.byHardwareVersion + "\r\n");
        stringBuffer.append("byDeviceType = " + usb_system_device_info.byDeviceType + "\r\n");
        stringBuffer.append("byProtocolVersion = " + usb_system_device_info.byProtocolVersion + "\r\n");
        stringBuffer.append("bySerialNumber = " + usb_system_device_info.bySerialNumber + "\r\n");
        stringBuffer.append("bySecondHardwareVersion = " + usb_system_device_info.bySecondHardwareVersion + "\r\n");
        stringBuffer.append("byModuleID = " + usb_system_device_info.byModuleID + "\r\n");
        stringBuffer.append("byDeviceID = " + usb_system_device_info.byDeviceID + "\r\n");
        Alog.e("INFO = " + stringBuffer.toString(), new Object[0]);
        if (usb_system_device_info.byDeviceID.getBytes()[0] != 0) {
            str = usb_system_device_info.byDeviceID;
        } else if (usb_system_device_info.bySecondHardwareVersion.getBytes()[0] != 0) {
            Alog.e("16进制：" + Helper.getBytesHex(usb_system_device_info.bySecondHardwareVersion.getBytes()), new Object[0]);
            str = usb_system_device_info.bySecondHardwareVersion;
        } else {
            str = "";
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        int i = 0;
        while (true) {
            if (i >= bytes.length) {
                break;
            }
            if (bytes[i] == 0) {
                length = i;
                break;
            }
            i++;
        }
        String[] strArrSplit = new String(Arrays.copyOfRange(bytes, 0, length)).split("-");
        if (strArrSplit.length < 2) {
            return false;
        }
        this.previewDevice = null;
        if (strArrSplit[1].equals("TM32")) {
            this.previewDevice = new F2Device(Config.F2Type.F2_256);
            Alog.e("256分辨率设备", new Object[0]);
        } else if (strArrSplit[1].equals("TM31")) {
            this.previewDevice = new F2Device(Config.F2Type.F2_160);
            Alog.e("160分辨率设备", new Object[0]);
        }
        F2Device f2Device = this.previewDevice;
        if (f2Device == null) {
            return false;
        }
        f2Device.vid = this.f2_devices[this.select_index].dwVID;
        this.previewDevice.pid = this.f2_devices[this.select_index].dwPID;
        this.previewDevice.sn = this.f2_devices[this.select_index].szSerialNumber;
        return true;
    }

    public void deviceDetachedF2(UsbDevice usbDevice) {
        Alog.e("开始时间：" + System.currentTimeMillis(), new Object[0]);
        if (this.isPreview) {
            if (this.javaInterface.USB_GetDeviceCount(this.context) <= 0) {
                on_f2_disconnect();
                release();
            }
            if (usbDevice == null || this.previewDevice == null) {
                return;
            }
            int vendorId = usbDevice.getVendorId();
            int productId = usbDevice.getProductId();
            String strTrim = usbDevice.getSerialNumber().trim();
            if (this.previewDevice.vid == vendorId && this.previewDevice.pid == productId && !TextUtils.isEmpty(this.previewDevice.sn) && this.previewDevice.sn.trim().equals(strTrim)) {
                on_f2_disconnect();
                release();
            }
        }
    }

    private void logout() {
        Alog.e("退出登录", new Object[0]);
        int i = this.login_userid;
        if (i != -1) {
            this.javaInterface.USB_Logout(i);
            this.login_userid = -1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initDeviceParam() throws IllegalAccessException {
        if (this.login_userid == -1 || this.previewDevice == null) {
            return;
        }
        USB_THERMOMETRY_MODE usb_thermometry_mode = new USB_THERMOMETRY_MODE();
        usb_thermometry_mode.byThermometryMode = (byte) 2;
        if (this.javaInterface.USB_SetThermometryMode(this.login_userid, usb_thermometry_mode)) {
            Alog.e("测温模式设置成功", new Object[0]);
        } else {
            Alog.e("测温模式设置失败", new Object[0]);
        }
        USB_IMAGE_BRIGHTNESS usb_image_brightness = new USB_IMAGE_BRIGHTNESS();
        usb_image_brightness.dwBrightness = ShareHelper.getBrightness();
        if (this.javaInterface.USB_SetImageBrightNess(this.login_userid, usb_image_brightness)) {
            Alog.e("设置图片亮度成功", new Object[0]);
        } else {
            Alog.e("设置图片亮度失败", new Object[0]);
        }
        USB_IMAGE_CONTRAST usb_image_contrast = new USB_IMAGE_CONTRAST();
        usb_image_contrast.dwContrast = ShareHelper.getContrast();
        if (this.javaInterface.USB_SetImageContrast(this.login_userid, usb_image_contrast)) {
            Alog.e("设置图像对比度成功", new Object[0]);
        } else {
            Alog.e("设置图像对比度失败", new Object[0]);
        }
        this.mImageEnhancement.byNoiseReduceMode = (byte) (ShareHelper.getNoiseEnable() ? 2 : 0);
        this.mImageEnhancement.dwFrameNoiseReduceLevel = ShareHelper.getNoise();
        this.mImageEnhancement.dwInterFrameNoiseReduceLevel = ShareHelper.getNoise();
        this.mImageEnhancement.byPaletteMode = (byte) ShareHelper.getPaletteType().getPalette();
        this.mImageEnhancement.byLSEDetailEnabled = ShareHelper.getDetailEnable() ? (byte) 1 : (byte) 0;
        this.mImageEnhancement.dwLSEDetailLevel = ShareHelper.getDetail();
        this.mImageEnhancement.byIspAgcMode = (byte) 2;
        if (this.javaInterface.USB_SetImageEnhancement(this.login_userid, this.mImageEnhancement)) {
            Alog.e("设置图像增强成功", new Object[0]);
        } else {
            Alog.e("设置图像增强失败", new Object[0]);
        }
        this.javaInterface.USB_GetThermometryBasicParam(this.login_userid, this.mThermometryBasicParam);
        this.mThermometryBasicParam.byEnabled = (byte) 0;
        this.mThermometryBasicParam.byDisplayMaxTemperatureEnabled = (byte) 0;
        this.mThermometryBasicParam.byDisplayMinTemperatureEnabled = (byte) 0;
        this.mThermometryBasicParam.byDisplayAverageTemperatureEnabled = (byte) 0;
        this.mThermometryBasicParam.byTemperatureUnit = (byte) 1;
        this.mThermometryBasicParam.byCalibrationCoefficientEnabled = (byte) 0;
        this.mThermometryBasicParam.dwCalibrationCoefficient = 0;
        this.mThermometryBasicParam.dwExternalOpticsWindowCorrection = (int) (((ShareHelper.getOptical() / 10.0f) + 100.0f) * 10.0f);
        this.mThermometryBasicParam.dwEmissivity = ShareHelper.getEmissivity();
        this.mThermometryBasicParam.byDistanceUnit = (byte) 2;
        this.mThermometryBasicParam.dwDistance = ShareHelper.getDistance();
        this.mThermometryBasicParam.byReflectiveEnable = ShareHelper.getRefleationEnable() ? (byte) 1 : (byte) 0;
        this.mThermometryBasicParam.dwReflectiveTemperature = (int) (((ShareHelper.getRefleation() / 10.0f) + 100.0f) * 10.0f);
        this.mThermometryBasicParam.byThermometryStreamOverlay = (byte) 1;
        Alog.e("设备初始化量程：" + ((int) this.mThermometryBasicParam.byTemperatureRange), new Object[0]);
        if (this.javaInterface.USB_SetThermometryBasicParam(this.login_userid, this.mThermometryBasicParam)) {
            Alog.e("设置测温规则成功", new Object[0]);
        } else {
            Alog.e("设置测温规则失败", new Object[0]);
        }
        this.mThermometryExpertRegions.byRegionNum = (byte) 0;
        if (this.javaInterface.USB_SetThermometryExpertRegions(this.login_userid, this.mThermometryExpertRegions)) {
            Alog.e("设置专家测温规则配置成功", new Object[0]);
        } else {
            Alog.e("设置专家测温规则配置失败", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean startPreview() throws IllegalAccessException {
        if (this.login_userid == -1 || this.previewDevice == null) {
            return false;
        }
        USB_VIDEO_PARAM usb_video_param = new USB_VIDEO_PARAM();
        usb_video_param.dwVideoFormat = 103;
        usb_video_param.dwWidth = this.previewDevice.preview_width;
        usb_video_param.dwHeight = this.previewDevice.preview_height;
        usb_video_param.dwFramerate = 25;
        usb_video_param.dwBitrate = 0;
        usb_video_param.dwParamType = 0;
        usb_video_param.dwValue = 0;
        if (!JavaInterface.getInstance().USB_SetVideoParam(this.login_userid, usb_video_param)) {
            Alog.e("设置视频参数错误，预览失败", new Object[0]);
            return false;
        }
        USB_IMAGE_VIDEO_ADJUST usb_image_video_adjust = new USB_IMAGE_VIDEO_ADJUST();
        this.javaInterface.USB_GetImageVideoAdjust(this.login_userid, usb_image_video_adjust);
        Alog.e("镜像模式：" + ((int) usb_image_video_adjust.byImageFlipStyle), new Object[0]);
        Alog.e("旋转模式：" + ((int) usb_image_video_adjust.byImageFlipStyle), new Object[0]);
        usb_image_video_adjust.byImageFlipStyle = (byte) 0;
        usb_image_video_adjust.byCorridor = (byte) 0;
        this.javaInterface.USB_SetImageVideoAdjust(this.login_userid, usb_image_video_adjust);
        USB_STREAM_CALLBACK_PARAM usb_stream_callback_param = new USB_STREAM_CALLBACK_PARAM();
        usb_stream_callback_param.dwStreamType = 103;
        usb_stream_callback_param.fnStreamCallBack = this.frame_callback;
        int iUSB_StartStreamCallback = JavaInterface.getInstance().USB_StartStreamCallback(this.login_userid, usb_stream_callback_param);
        this.preview_handler = iUSB_StartStreamCallback;
        if (iUSB_StartStreamCallback == -1) {
            Alog.e("设置码流回调错误，预览失败" + this.javaInterface.USB_GetLastError(), new Object[0]);
            return false;
        }
        USB_THERMAL_STREAM_PARAM usb_thermal_stream_param = new USB_THERMAL_STREAM_PARAM();
        usb_thermal_stream_param.byVideoCodingType = (byte) 8;
        if (JavaInterface.getInstance().USB_SetThermalStreamParam(this.login_userid, usb_thermal_stream_param)) {
            return true;
        }
        Alog.e("设置红外预览格式错误，预览失败", new Object[0]);
        return false;
    }

    private void stopPreview() {
        int i;
        Alog.e("关闭预览", new Object[0]);
        int i2 = this.login_userid;
        if (i2 != -1 && (i = this.preview_handler) != -1) {
            this.javaInterface.USB_StopChannel(i2, i);
            this.preview_handler = -1;
        }
        this.isPreview = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release() {
        new Thread(new Runnable() { // from class: com.hti.xtherm.hti160203u.helper.F2Helper$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                m95lambda$release$0$comhtixthermhti160203uhelperF2Helper();
            }
        }).start();
        this.previewDevice = null;
        this.frame_yuy2_buffer = null;
        this.frame_temp_buffer = null;
        this.frame_argb_buffer = null;
        this.render_bitmap = null;
    }

    /* renamed from: lambda$release$0$com-hti-xtherm-hti160203u-helper-F2Helper, reason: not valid java name */
    /* synthetic */ void m95lambda$release$0$comhtixthermhti160203uhelperF2Helper() {
        if (this.preview_handler != -1) {
            stopPreview();
        }
        if (this.login_userid != -1) {
            logout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void on_open_f2_failed() {
        OnF2DeviceCallback onF2DeviceCallback = this.callback;
        if (onF2DeviceCallback != null) {
            onF2DeviceCallback.on_open_f2_failed();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void on_open_f2_success() {
        OnF2DeviceCallback onF2DeviceCallback = this.callback;
        if (onF2DeviceCallback != null) {
            onF2DeviceCallback.on_open_f2_success();
        }
    }

    private void on_f2_disconnect() {
        OnF2DeviceCallback onF2DeviceCallback = this.callback;
        if (onF2DeviceCallback != null) {
            onF2DeviceCallback.on_f2_disconnect();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void on_f2_temperature(byte[] bArr, int i, int i2, boolean z) {
        OnF2DeviceCallback onF2DeviceCallback = this.callback;
        if (onF2DeviceCallback != null) {
            onF2DeviceCallback.on_f2_temperature(bArr, i, i2, z);
        }
    }
}
