package com.hcusbsdk.jni;

/* loaded from: classes.dex */
public class THERMAL_ROI_REGION_INFO extends USB_CONFIG {
    public byte byROIRegionID;
    public int dwMaxROIRegionTemperature;
    public int dwThermalROIRegionMaxTemperaturePointX;
    public int dwThermalROIRegionMaxTemperaturePointY;
    public int dwVisibleROIRegionMaxTemperaturePointX;
    public int dwVisibleROIRegionMaxTemperaturePointY;
    public byte[] byRes1 = new byte[3];
    public byte[] byRes = new byte[8];
}
