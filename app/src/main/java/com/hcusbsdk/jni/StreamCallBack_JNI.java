package com.hcusbsdk.jni;

import ai.onnxruntime.OrtException;

/* loaded from: classes.dex */
public interface StreamCallBack_JNI {
    void fStreamCallback_JNI(int i, USB_FRAME_INFO usb_frame_info) throws OrtException;
}
