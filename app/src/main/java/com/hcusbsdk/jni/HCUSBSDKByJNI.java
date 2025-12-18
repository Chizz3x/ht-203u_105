package com.hcusbsdk.jni;

import android.util.Log;

/* loaded from: classes.dex */
public class HCUSBSDKByJNI {
    public static final int MAX_CONFIG_COND_BUFFER_SIZE = 1024;
    public static final int MAX_CONFIG_INPUT_BUFFER_SIZE = 1048576;
    public static final int MAX_CONFIG_OUTPUT_BUFFER_SIZE = 1048576;
    public static final int MAX_FRAME_SIZE = 8294400;
    public static final int MAX_ROI_REGIONS = 10;
    static HCUSBSDKByJNI UsbSdk;

    public native boolean USB_GetDeviceConfig(int i, int i2, USB_CONFIG usb_config, USB_CONFIG usb_config2, USB_CONFIG usb_config3);

    public native int USB_StartStreamCallback(int i, USB_STREAM_CALLBACK_PARAM usb_stream_callback_param, StreamCallBack_JNI streamCallBack_JNI);

    public native boolean USB_StopChannel(int i, int i2);

    public static synchronized HCUSBSDKByJNI getInstance() {
        if (UsbSdk == null) {
            UsbSdk = new HCUSBSDKByJNI();
        }
        return UsbSdk;
    }

    private HCUSBSDKByJNI() {
        try {
            System.loadLibrary("HCUSBSDK");
        } catch (UnsatisfiedLinkError e) {
            Log.e("[JavaInterface]", "load hcusbsdk failed, err info:  " + e);
        }
    }
}
