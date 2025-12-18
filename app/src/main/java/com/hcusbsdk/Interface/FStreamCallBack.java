package com.hcusbsdk.Interface;

import com.sun.jna.Callback;

import ai.onnxruntime.OrtException;

/* loaded from: classes.dex */
public interface FStreamCallBack extends Callback {
    void invoke(int i, USB_FRAME_INFO usb_frame_info) throws OrtException;
}
