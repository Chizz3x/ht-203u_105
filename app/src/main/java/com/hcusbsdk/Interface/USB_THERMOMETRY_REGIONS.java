package com.hcusbsdk.Interface;

/* loaded from: classes.dex */
public class USB_THERMOMETRY_REGIONS {
    public byte byChannelID;
    public byte byRegionNum;
    public byte bySID;
    public THERMAL_REGION[] struRegion = new THERMAL_REGION[10];

    public USB_THERMOMETRY_REGIONS() {
        for (int i = 0; i < 10; i++) {
            this.struRegion[i] = new THERMAL_REGION();
        }
    }
}
