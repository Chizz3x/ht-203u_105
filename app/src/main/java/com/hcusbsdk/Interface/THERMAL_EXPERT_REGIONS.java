package com.hcusbsdk.Interface;

/* loaded from: classes.dex */
public class THERMAL_EXPERT_REGIONS {
    public byte byEnabled;
    public byte byPointNum;
    public byte byReflectiveEnable;
    public byte byRegionID;
    public byte byRule;
    public byte byShowAlarmColorEnabled;
    public byte byType;
    public int dwAlarm;
    public int dwAlert;
    public int dwDistance;
    public int dwEmissivity;
    public int dwReflectiveTemperature;
    public REGION_VERTEX_COORDINATES[] struRegionCoordinate = new REGION_VERTEX_COORDINATES[10];
    public String szName;

    public THERMAL_EXPERT_REGIONS() {
        for (int i = 0; i < 10; i++) {
            this.struRegionCoordinate[i] = new REGION_VERTEX_COORDINATES();
        }
    }
}
