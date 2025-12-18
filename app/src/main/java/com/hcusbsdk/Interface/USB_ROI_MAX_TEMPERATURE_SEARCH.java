package com.hcusbsdk.Interface;

/* loaded from: classes.dex */
public class USB_ROI_MAX_TEMPERATURE_SEARCH {
    public byte byChannelID;
    public byte byDay;
    public byte byHour;
    public byte byJpegPicEnabled;
    public byte byMaxTemperatureOverlay;
    public byte byMinute;
    public byte byMonth;
    public byte byROIRegionNum;
    public byte byRegionsOverlay;
    public byte bySecond;
    public THERMAL_ROI_REGION[] struThermalROIRegion = new THERMAL_ROI_REGION[10];
    public short wMillisecond;
    public short wYear;

    public USB_ROI_MAX_TEMPERATURE_SEARCH() {
        for (int i = 0; i < 10; i++) {
            this.struThermalROIRegion[i] = new THERMAL_ROI_REGION();
        }
    }
}
