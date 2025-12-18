package com.hcusbsdk.jni;

/* loaded from: classes.dex */
public class USB_FRAME_INFO extends USB_CONFIG {
    public int dwBufSize;
    public int dwDataType;
    public int dwFrameRate;
    public int dwFrameType;
    public int dwHeight;
    public int dwStreamType;
    public int dwWidth;
    public int nFrameNum;
    public int nStamp;
    public byte[] pBuf = new byte[HCUSBSDKByJNI.MAX_FRAME_SIZE];
    public byte[] byRes = new byte[128];
}
