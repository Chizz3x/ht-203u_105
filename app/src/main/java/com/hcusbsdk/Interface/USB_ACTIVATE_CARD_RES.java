package com.hcusbsdk.Interface;

/* loaded from: classes.dex */
public class USB_ACTIVATE_CARD_RES {
    public byte byCardType;
    public byte bySelectVerifyLen;
    public byte bySerialLen;
    public int[] dwSerial = new int[10];
    public byte[] bySelectVerify = new byte[3];
}
