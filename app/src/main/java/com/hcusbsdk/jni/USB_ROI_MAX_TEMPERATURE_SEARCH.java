package com.hcusbsdk.jni;

/* loaded from: classes.dex */
public class USB_ROI_MAX_TEMPERATURE_SEARCH extends USB_CONFIG {
    public byte byDay;
    public byte byHour;
    public byte byJpegPicEnabled;
    public byte byMaxTemperatureOverlay;
    public byte byMinute;
    public byte byMonth;
    public byte byROIRegionNum;
    public byte byRegionsOverlay;
    public byte byRes2;
    public byte bySecond;
    public int dwSize;
    public short wMillisecond;
    public short wYear;
    public byte[] byRes1 = new byte[2];
    public THERMAL_ROI_REGION[] struThermalROIRegion = new THERMAL_ROI_REGION[10];
    public byte[] byRes = new byte[176];

    public USB_ROI_MAX_TEMPERATURE_SEARCH() {
        for (int i = 0; i < 10; i++) {
            this.struThermalROIRegion[i] = new THERMAL_ROI_REGION();
        }
    }
}
