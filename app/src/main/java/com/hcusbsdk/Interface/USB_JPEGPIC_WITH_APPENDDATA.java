package com.hcusbsdk.Interface;

/* loaded from: classes.dex */
public class USB_JPEGPIC_WITH_APPENDDATA {
    public byte byChannelID;
    public byte byIsFreezedata;
    public byte[] byJpegPic = new byte[JavaInterface.MAX_JEPG_DATA_SIZE];
    public byte[] byP2pData = new byte[JavaInterface.MAX_JEPG_DATA_SIZE];
    public byte byTemperatureDataLength;
    public int dwJpegPicHeight;
    public int dwJpegPicLen;
    public int dwJpegPicWidth;
    public int dwP2pDataLen;
    public int dwScale;
    public int iOffset;
}
