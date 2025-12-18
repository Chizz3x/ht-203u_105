package com.hcusbsdk.Interface;

/* loaded from: classes.dex */
public class USB_THERMOMETRY_EXPERT_REGIONS {
    public byte byRegionNum;
    public THERMAL_EXPERT_REGIONS[] struExpertRegions = new THERMAL_EXPERT_REGIONS[21];

    public USB_THERMOMETRY_EXPERT_REGIONS() {
        for (int i = 0; i < 21; i++) {
            this.struExpertRegions[i] = new THERMAL_EXPERT_REGIONS();
            this.struExpertRegions[i].szName = new String(" ");
        }
    }
}
