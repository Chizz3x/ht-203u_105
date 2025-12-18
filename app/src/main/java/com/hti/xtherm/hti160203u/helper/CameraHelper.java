package com.hti.xtherm.hti160203u.helper;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.TextureView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class CameraHelper {
    private Activity mActivity;
    private Camera mCamera;
    private int mCameraFacing = 0;
    private int mDisplayOrientation = 0;
    private Camera.Parameters mParameters;
    private TextureView mTextureView;

    public CameraHelper(Activity activity, TextureView textureView) {
        this.mActivity = activity;
        this.mTextureView = textureView;
    }

    public boolean previewCamera() throws IOException {
        if (!this.mTextureView.isAvailable() || !openCamera(this.mCameraFacing)) {
            return false;
        }
        startPreview(this.mTextureView.getSurfaceTexture());
        return true;
    }

    public void stopPreviewCamera() {
        releaseCamera();
    }

    private boolean openCamera(int i) {
        try {
            Camera cameraOpen = Camera.open(i);
            this.mCamera = cameraOpen;
            initParameters(cameraOpen);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            toast("打开相机失败!");
            return false;
        }
    }

    private void initParameters(Camera camera) {
        this.mParameters = camera.getParameters();
        setPreviewFormat();
        setPreviewSizes();
        if (isSupportFocus("continuous-picture")) {
            this.mParameters.setFocusMode("continuous-picture");
        } else {
            log("不支持自动连续对焦");
        }
        camera.setParameters(this.mParameters);
    }

    private void setPreviewSizes() {
        try {
            Camera.Parameters parameters = this.mParameters;
            if (parameters != null) {
                Camera.Size bestSize = getBestSize(640, 480, parameters.getSupportedPreviewSizes());
                this.mParameters.setPreviewSize(bestSize.width, bestSize.height);
                log("设置预览宽高完成");
            }
        } catch (Exception e) {
            log("设置预览宽高失败");
            e.printStackTrace();
        }
    }

    private void setPreviewFormat() {
        try {
            Camera.Parameters parameters = this.mParameters;
            if (parameters != null) {
                parameters.setPreviewFormat(17);
                this.mCamera.setParameters(this.mParameters);
                log("设置NV21完成");
            }
        } catch (Exception e) {
            log("设置NV21失败");
            e.printStackTrace();
        }
    }

    private void startPreview(SurfaceTexture surfaceTexture) throws IOException {
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                camera.setPreviewTexture(surfaceTexture);
                setCameraDisplayOrientation(this.mActivity);
                this.mCamera.startPreview();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isSupportFocus(String str) {
        boolean z = false;
        for (String str2 : this.mParameters.getSupportedFocusModes()) {
            log("对焦模式：" + str2);
            if (str2.equals(str)) {
                z = true;
            }
        }
        return z;
    }

    private void releaseCamera() {
        Camera camera = this.mCamera;
        if (camera != null) {
            camera.stopPreview();
            this.mCamera.setPreviewCallback(null);
            this.mCamera.release();
            this.mCamera = null;
        }
    }

    private Camera.Size getBestSize(int i, int i2, List<Camera.Size> list) {
        double d = 1.0d;
        double d2 = ((i2 * 1.0d) / i) * 1.0d;
        for (Camera.Size size : list) {
            log("系统支持的尺寸 : " + size.width + " * " + size.height + "比例" + ((size.width * 1.0d) / size.height));
        }
        Iterator<Camera.Size> it = list.iterator();
        Camera.Size size2 = null;
        double dAbs = d2;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Camera.Size next = it.next();
            if (next.width == i && next.height == i2) {
                size2 = next;
                break;
            }
            double d3 = ((next.width * d) / next.height) - d2;
            if (Math.abs(d3) < dAbs) {
                dAbs = Math.abs(d3);
                size2 = next;
            }
            d = 1.0d;
        }
        log("目标尺寸 ：" + i + " * " + i2 + "   比例:" + d2);
        log("最优尺寸 ：" + size2.width + " * " + size2.height);
        return size2;
    }

    private void setCameraDisplayOrientation(Activity activity) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(this.mCameraFacing, cameraInfo);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int i = 0;
        if (rotation != 0) {
            if (rotation == 1) {
                i = 90;
            } else if (rotation == 2) {
                i = 180;
            } else if (rotation == 3) {
                i = 270;
            }
        }
        if (cameraInfo.facing == 1) {
            int i2 = (cameraInfo.orientation + i) % 360;
            this.mDisplayOrientation = i2;
            this.mDisplayOrientation = (360 - i2) % 360;
        } else {
            this.mDisplayOrientation = ((cameraInfo.orientation - i) + 360) % 360;
        }
        this.mCamera.setDisplayOrientation(this.mDisplayOrientation);
    }

    private boolean supportCameraFacing(int i) {
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == i) {
                return true;
            }
        }
        return false;
    }

    private void toast(String str) {
        Toast.makeText(this.mActivity, str, 0).show();
    }

    private void log(String str) {
        Alog.e(str, new Object[0]);
    }

    public Camera getCamera() {
        return this.mCamera;
    }
}
