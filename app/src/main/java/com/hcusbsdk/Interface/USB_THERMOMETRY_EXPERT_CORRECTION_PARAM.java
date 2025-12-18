package com.hcusbsdk.Interface;

/* loaded from: classes.dex */
public class USB_THERMOMETRY_EXPERT_CORRECTION_PARAM {
    public byte byPointNum;
    public int dwDistance;
    public int dwEmissivity;
    public int dwEnviroTemperature;
    public THERMAL_EXPERT_TEMPERATURE[] struExpertTemperature = new THERMAL_EXPERT_TEMPERATURE[4];

    public USB_THERMOMETRY_EXPERT_CORRECTION_PARAM() {
        for (int i = 0; i < 4; i++) {
            this.struExpertTemperature[i] = new THERMAL_EXPERT_TEMPERATURE();
        }
    }
}
