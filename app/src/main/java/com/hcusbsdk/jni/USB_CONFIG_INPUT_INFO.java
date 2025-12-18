package com.hcusbsdk.jni;

/* loaded from: classes.dex */
public class USB_CONFIG_INPUT_INFO extends USB_CONFIG {
    public int dwCondBufferSize;
    public int dwInBufferSize;
    public byte[] lpCondBuffer = new byte[1024];
    public byte[] lpInBuffer = new byte[1048576];
    public byte[] byRes = new byte[48];
}
