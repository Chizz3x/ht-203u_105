package com.hcusbsdk.Interface;

/* loaded from: classes.dex */
public class USB_CERTIFICATE_INFO {
    public byte byCertificateType;
    public short wFingerPrintInfoSize;
    public short wPicInfoSize;
    public short wWordInfoSize;
    public byte[] byWordInfo = new byte[256];
    public byte[] byPicInfo = new byte[1024];
    public byte[] byFingerPrintInfo = new byte[1024];
}
