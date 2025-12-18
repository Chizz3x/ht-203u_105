package com.hcusbsdk.Interface;

/* loaded from: classes.dex */
public class USB_ROI_MAX_TEMPERATURE_SEARCH_RESULT {
    public byte byChannelID;
    public byte byROIRegionNum;
    public int dwJpegPicLen;
    public int dwMaxP2PTemperature;
    public int dwThermalP2PMaxTemperaturePointX;
    public int dwThermalP2PMaxTemperaturePointY;
    public int dwVisibleP2PMaxTemperaturePointX;
    public int dwVisibleP2PMaxTemperaturePointY;
    public THERMAL_ROI_REGION_INFO[] struThermalROIRegionInfo = new THERMAL_ROI_REGION_INFO[10];
    public byte[] byJpegPic = new byte[JavaInterface.MAX_JEPG_DATA_SIZE];

    public USB_ROI_MAX_TEMPERATURE_SEARCH_RESULT() {
        for (int i = 0; i < 10; i++) {
            this.struThermalROIRegionInfo[i] = new THERMAL_ROI_REGION_INFO();
        }
    }
}
