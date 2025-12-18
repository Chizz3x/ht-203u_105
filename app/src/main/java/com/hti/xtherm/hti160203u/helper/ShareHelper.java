package com.hti.xtherm.hti160203u.helper;

import android.content.SharedPreferences;

import com.hti.xtherm.hti160203u.ThermalApplication;

/* loaded from: classes.dex */
public class ShareHelper {
    private static final String SHARE_KEY_ALARMMAX_ENABLE;
    private static final String SHARE_KEY_ALARMMAX_VALUE;
    private static final String SHARE_KEY_ALARMMIN_ENABLE;
    private static final String SHARE_KEY_ALARMMIN_VALUE;
    private static final String SHARE_KEY_BRIGHTNESS;
    private static final String SHARE_KEY_CONTRAST;
    private static final String SHARE_KEY_DETAIL;
    private static final String SHARE_KEY_DETAIL_ENABLE;
    private static final String SHARE_KEY_DISTANCE;
    private static final String SHARE_KEY_EMISSIVITY;
    private static final String SHARE_KEY_LANGUAGE;
    private static final String SHARE_KEY_NOISE;
    private static final String SHARE_KEY_NOISE_ENABLE;
    private static final String SHARE_KEY_OPTICAL;
    private static final String SHARE_KEY_PALETTE;
    private static final String SHARE_KEY_REFLEATION;
    private static final String SHARE_KEY_REFLEATION_ENABLE;
    private static final String SHARE_KEY_TEMPUNIT;
    private static final String SHARE_KEY_WATERMARK;
    private static final String packageName;
    private static final SharedPreferences share;

    static {
        String packageName2 = ThermalApplication.getAppContext().getPackageName();
        packageName = packageName2;
        share = ThermalApplication.getAppContext().getSharedPreferences(packageName2 + ".config", 0);
        SHARE_KEY_LANGUAGE = packageName2 + "_LANGUAGE";
        SHARE_KEY_PALETTE = packageName2 + "_PALETTE";
        SHARE_KEY_TEMPUNIT = packageName2 + "_TEMPUNIT";
        SHARE_KEY_EMISSIVITY = packageName2 + "_EMISSIVITY";
        SHARE_KEY_OPTICAL = packageName2 + "_OPTICAL";
        SHARE_KEY_REFLEATION_ENABLE = packageName2 + "_REFLEATION_ENABLE";
        SHARE_KEY_REFLEATION = packageName2 + "_REFLEATION";
        SHARE_KEY_DISTANCE = packageName2 + "_DISTANCE";
        SHARE_KEY_BRIGHTNESS = packageName2 + "_BRIGHTNESS";
        SHARE_KEY_CONTRAST = packageName2 + "_CONTRAST";
        SHARE_KEY_NOISE_ENABLE = packageName2 + "_NOISE_ENABLE";
        SHARE_KEY_NOISE = packageName2 + "_NOISE";
        SHARE_KEY_DETAIL_ENABLE = packageName2 + "_DETAILENABLE";
        SHARE_KEY_DETAIL = packageName2 + "_DETAIL";
        SHARE_KEY_WATERMARK = packageName2 + "_WATERMARK";
        SHARE_KEY_ALARMMAX_ENABLE = packageName2 + "_ALARMMAX_ENABLE";
        SHARE_KEY_ALARMMAX_VALUE = packageName2 + "_ALARMMAX_VALUE";
        SHARE_KEY_ALARMMIN_ENABLE = packageName2 + "_ALARMMIN_ENABLE";
        SHARE_KEY_ALARMMIN_VALUE = packageName2 + "_ALARMMIN_VALUE";
    }

    public static void setLanguage(Config.Language language) {
        share.edit().putInt(SHARE_KEY_LANGUAGE, language.ordinal()).commit();
    }

    public static Config.Language getLanguage() {
        int i = share.getInt(SHARE_KEY_LANGUAGE, -1);
        if (i == -1) {
            Config.Language systemLanguage = Helper.getSystemLanguage();
            setLanguage(systemLanguage);
            return systemLanguage;
        }
        return Config.Language.values()[i];
    }

    public static void setPaletteType(Config.PaletteType paletteType) {
        share.edit().putInt(SHARE_KEY_PALETTE, paletteType.ordinal()).commit();
    }

    public static Config.PaletteType getPaletteType() {
        return Config.PaletteType.values()[share.getInt(SHARE_KEY_PALETTE, Config.DEF_PALETTE.ordinal())];
    }

    public static void setTempunit(Config.Tempunit tempunit) {
        share.edit().putInt(SHARE_KEY_TEMPUNIT, tempunit.ordinal()).commit();
    }

    public static Config.Tempunit getTempunit() {
        return Config.Tempunit.values()[share.getInt(SHARE_KEY_TEMPUNIT, Config.DEF_TEMPUNIT.ordinal())];
    }

    public static void setEmissivity(int i) {
        if (i < 1 || i > 100) {
            return;
        }
        share.edit().putInt(SHARE_KEY_EMISSIVITY, i).commit();
    }

    public static int getEmissivity() {
        return share.getInt(SHARE_KEY_EMISSIVITY, 95);
    }

    public static void setOptical(int i) {
        if (i < -400 || i > 800) {
            return;
        }
        share.edit().putInt(SHARE_KEY_OPTICAL, i).commit();
    }

    public static int getOptical() {
        return share.getInt(SHARE_KEY_OPTICAL, 30);
    }

    public static void setRefleationEnable(boolean z) {
        share.edit().putBoolean(SHARE_KEY_REFLEATION_ENABLE, z).commit();
    }

    public static boolean getRefleationEnable() {
        return share.getBoolean(SHARE_KEY_REFLEATION_ENABLE, false);
    }

    public static void setRefleation(int i) {
        if (i < -1000 || i > 10000) {
            return;
        }
        share.edit().putInt(SHARE_KEY_REFLEATION, i).commit();
    }

    public static int getRefleation() {
        return share.getInt(SHARE_KEY_REFLEATION, 50);
    }

    public static void setDistance(int i) {
        if (i < 30 || i > 300) {
            return;
        }
        share.edit().putInt(SHARE_KEY_DISTANCE, i).commit();
    }

    public static int getDistance() {
        return share.getInt(SHARE_KEY_DISTANCE, 30);
    }

    public static void setBrightness(int i) {
        if (i < 0 || i > 100) {
            return;
        }
        share.edit().putInt(SHARE_KEY_BRIGHTNESS, i).commit();
    }

    public static int getBrightness() {
        return share.getInt(SHARE_KEY_BRIGHTNESS, 40);
    }

    public static void setContrast(int i) {
        if (i < 0 || i > 100) {
            return;
        }
        share.edit().putInt(SHARE_KEY_CONTRAST, i).commit();
    }

    public static int getContrast() {
        return share.getInt(SHARE_KEY_CONTRAST, 35);
    }

    public static void setNoiseEnable(boolean z) {
        share.edit().putBoolean(SHARE_KEY_NOISE_ENABLE, z).commit();
    }

    public static boolean getNoiseEnable() {
        return share.getBoolean(SHARE_KEY_NOISE_ENABLE, true);
    }

    public static void setNoise(int i) {
        if (i < 0 || i > 100) {
            return;
        }
        share.edit().putInt(SHARE_KEY_NOISE, i).commit();
    }

    public static int getNoise() {
        return share.getInt(SHARE_KEY_NOISE, 50);
    }

    public static void setDetailEnable(boolean z) {
        share.edit().putBoolean(SHARE_KEY_DETAIL_ENABLE, z).commit();
    }

    public static boolean getDetailEnable() {
        return share.getBoolean(SHARE_KEY_DETAIL_ENABLE, false);
    }

    public static void setDetail(int i) {
        if (i < 0 || i > 100) {
            return;
        }
        share.edit().putInt(SHARE_KEY_DETAIL, i).commit();
    }

    public static int getDetail() {
        return share.getInt(SHARE_KEY_DETAIL, 25);
    }

    public static void resetConfig() {
        setTempunit(Config.Tempunit.Celsius);
        setEmissivity(95);
        setOptical(30);
        setRefleationEnable(false);
        setRefleation(50);
        setDistance(30);
        setBrightness(40);
        setContrast(35);
        setNoiseEnable(true);
        setNoise(50);
        setDetailEnable(false);
        setDetail(25);
    }

    public static void setWatermark(boolean z) {
        share.edit().putBoolean(SHARE_KEY_WATERMARK, z).commit();
    }

    public static boolean getWarkmark() {
        return share.getBoolean(SHARE_KEY_WATERMARK, false);
    }

    public static void setAlarmMaxEnable(boolean z) {
        share.edit().putBoolean(SHARE_KEY_ALARMMAX_ENABLE, z).commit();
    }

    public static boolean getAlarmMaxEnable() {
        return share.getBoolean(SHARE_KEY_ALARMMAX_ENABLE, false);
    }

    public static void setAlarmMaxValue(float f) {
        share.edit().putFloat(SHARE_KEY_ALARMMAX_VALUE, f).commit();
    }

    public static float getAlarmMaxValue() {
        return share.getFloat(SHARE_KEY_ALARMMAX_VALUE, Float.MIN_VALUE);
    }

    public static void setAlarmMinEnable(boolean z) {
        share.edit().putBoolean(SHARE_KEY_ALARMMIN_ENABLE, z).commit();
    }

    public static boolean getAlarmMinEnable() {
        return share.getBoolean(SHARE_KEY_ALARMMIN_ENABLE, false);
    }

    public static void setAlarmMinValue(float f) {
        share.edit().putFloat(SHARE_KEY_ALARMMIN_VALUE, f).commit();
    }

    public static float getAlarmMinValue() {
        return share.getFloat(SHARE_KEY_ALARMMIN_VALUE, Float.MIN_VALUE);
    }
}
