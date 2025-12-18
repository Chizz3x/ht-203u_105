package com.hcusbsdk.jni;

/* loaded from: classes.dex */
public class USB_JPEGPIC_WITH_APPENDDATA extends USB_CONFIG {
    public byte byIsFreezedata;
    public byte byTemperatureDataLength;
    public int dwJpegPicHeight;
    public int dwJpegPicLen;
    public int dwJpegPicWidth;
    public int dwP2pDataLen;
    public int dwScale;
    public int dwSize;
    public int iOffset;
    public byte[] byRes2 = new byte[2];
    public byte[] pJpegPic = new byte[HCUSBSDKByJNI.MAX_FRAME_SIZE];
    public byte[] pP2pData = new byte[HCUSBSDKByJNI.MAX_FRAME_SIZE];
    public byte[] byRes = new byte[28];
}
