package com.hcusbsdk.jni;

import com.hti.xtherm.hti160203u.helper.Config;

/* loaded from: classes.dex */
public class USB_ROI_MAX_TEMPERATURE_SEARCH_RESULT extends USB_CONFIG {
    public byte byROIRegionNum;
    public int dwJpegPicLen;
    public int dwMaxP2PTemperature;
    public int dwSize;
    public int dwThermalP2PMaxTemperaturePointX;
    public int dwThermalP2PMaxTemperaturePointY;
    public int dwVisibleP2PMaxTemperaturePointX;
    public int dwVisibleP2PMaxTemperaturePointY;
    public byte[] byRes2 = new byte[3];
    public THERMAL_ROI_REGION_INFO[] struThermalROIRegionInfo = new THERMAL_ROI_REGION_INFO[10];
    public byte[] pJpegPic = new byte[HCUSBSDKByJNI.MAX_FRAME_SIZE];
    public byte[] byRes = new byte[Config.F2_PREVIEW_WIDTH];

    public USB_ROI_MAX_TEMPERATURE_SEARCH_RESULT() {
        for (int i = 0; i < 10; i++) {
            this.struThermalROIRegionInfo[i] = new THERMAL_ROI_REGION_INFO();
        }
    }
}
