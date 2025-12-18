package com.hcusbsdk.Interface;

/* loaded from: classes.dex */
public class USB_M1_SECTION_ENCRYPT {
    public byte byKeyType;
    public byte byNewKeyType;
    public byte bySectionID;
    public byte[] byKeyAContent = new byte[6];
    public byte[] byNewKeyAContent = new byte[6];
    public byte[] byCtrlBits = new byte[4];
    public byte[] byNewKeyBContent = new byte[6];
}
