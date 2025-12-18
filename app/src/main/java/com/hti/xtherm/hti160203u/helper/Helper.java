package com.hti.xtherm.hti160203u.helper;

import android.graphics.Point;
import android.graphics.Rect;
import android.os.LocaleList;
import android.util.DisplayMetrics;
import android.util.Size;

import com.hcusbsdk.Interface.USB_THERMOMETRY_BASIC_PARAM;
import com.hti.xtherm.hti160203u.ThermalApplication;

import java.util.Locale;

import kotlin.UByte;

/* loaded from: classes.dex */
public class Helper {
    public static Config.Language getSystemLanguage() {
        Locale locale = LocaleList.getDefault().get(0);
        if (locale != null) {
            if (locale.getLanguage().equalsIgnoreCase("zh")) {
                return Config.Language.CHINESE;
            }
            if (locale.getLanguage().equalsIgnoreCase("ru")) {
                return Config.Language.RUSSIAN;
            }
            if (locale.getLanguage().equalsIgnoreCase("en")) {
                return Config.Language.ENGLISH;
            }
        }
        return Config.Language.ENGLISH;
    }

    public static int bytes2Int(byte[] bArr) {
        int i = bArr[0] & UByte.MAX_VALUE;
        int i2 = (bArr[1] & UByte.MAX_VALUE) << 8;
        return ((bArr[3] & UByte.MAX_VALUE) << 24) | i | i2 | ((bArr[2] & UByte.MAX_VALUE) << 16);
    }

    public static String getBytesHex(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(byteToHex(b) + " ");
        }
        return stringBuffer.toString();
    }

    public static String byteToHex(byte b) {
        String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
        return hexString.length() < 2 ? "0" + hexString : hexString;
    }

    public static Size getScreenSize() {
        DisplayMetrics displayMetrics = ThermalApplication.getAppContext().getResources().getDisplayMetrics();
        return new Size(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    public static float getTemperatureValue(float f, Config.Tempunit tempunit) {
        return (tempunit != Config.Tempunit.Celsius && tempunit == Config.Tempunit.Fahrenheit) ? (f * 1.8f) + 32.0f : f;
    }

    public static float getTemperatureValue(float f, Config.Tempunit tempunit, Config.Tempunit tempunit2) {
        return tempunit == tempunit2 ? f : tempunit == Config.Tempunit.Celsius ? (f * 1.8f) + 32.0f : (f - 32.0f) / 1.8f;
    }

    public static String getTemperatureCharacters(float f, Config.Tempunit tempunit) {
        return String.format("%.1f".concat(tempunit == Config.Tempunit.Celsius ? "℃" : "℉"), Float.valueOf(getTemperatureValue(f, tempunit)));
    }

    public static int sp2px(float f) {
        return (int) ((f * ThermalApplication.getAppContext().getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int dip2px(float f) {
        return (int) ((f * ThermalApplication.getAppContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static Rect getRect(Point point, Point point2) {
        int i;
        int i2;
        int i3;
        int i4;
        if (point2.x > point.x) {
            i = point.x;
            i2 = point2.x;
        } else {
            i = point2.x;
            i2 = point.x;
        }
        if (point2.y > point.y) {
            i4 = point.y;
            i3 = point2.y;
        } else {
            int i5 = point2.y;
            i3 = point.y;
            i4 = i5;
        }
        return new Rect(i, i4, i2, i3);
    }

    public static float bytes2Float(byte[] bArr) {
        return Float.intBitsToFloat(((bArr[3] & UByte.MAX_VALUE) << 24) | 0 | ((bArr[0] & UByte.MAX_VALUE) << 0) | ((bArr[1] & UByte.MAX_VALUE) << 8) | ((bArr[2] & UByte.MAX_VALUE) << 16));
    }

    public static byte[] getIntBytes(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            return null;
        }
        return new byte[]{bArr[0], bArr[1], 0, 0};
    }

    public static String getDurationText(long j) {
        long j2 = (j % 86400000) / 3600000;
        long j3 = (j % 3600000) / 60000;
        long j4 = (j % 60000) / 1000;
        return (j2 < 10 ? "0" + j2 : Long.valueOf(j2)) + ": " + (j3 < 10 ? "0" + j3 : Long.valueOf(j3)) + ": " + (j4 < 10 ? "0" + j4 : Long.valueOf(j4));
    }

    public static void showThermometryBasicParam(USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param) {
        if (usb_thermometry_basic_param == null) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("byChannelID = " + ((int) usb_thermometry_basic_param.byChannelID) + "\r\n");
        stringBuffer.append("byEnabled = " + ((int) usb_thermometry_basic_param.byEnabled) + "\r\n");
        stringBuffer.append("byDisplayMaxTemperatureEnabled = " + ((int) usb_thermometry_basic_param.byDisplayMaxTemperatureEnabled) + "\r\n");
        stringBuffer.append("byDisplayMinTemperatureEnabled = " + ((int) usb_thermometry_basic_param.byDisplayMinTemperatureEnabled) + "\r\n");
        stringBuffer.append("byDisplayAverageTemperatureEnabled = " + ((int) usb_thermometry_basic_param.byDisplayAverageTemperatureEnabled) + "\r\n");
        stringBuffer.append("byTemperatureUnit = " + ((int) usb_thermometry_basic_param.byTemperatureUnit) + "\r\n");
        stringBuffer.append("byTemperatureRange = " + ((int) usb_thermometry_basic_param.byTemperatureRange) + "\r\n");
        stringBuffer.append("byCalibrationCoefficientEnabled = " + ((int) usb_thermometry_basic_param.byCalibrationCoefficientEnabled) + "\r\n");
        stringBuffer.append("dwCalibrationCoefficient = " + usb_thermometry_basic_param.dwCalibrationCoefficient + "\r\n");
        stringBuffer.append("dwExternalOpticsWindowCorrection = " + usb_thermometry_basic_param.dwExternalOpticsWindowCorrection + "\r\n");
        stringBuffer.append("dwEmissivity = " + usb_thermometry_basic_param.dwEmissivity + "\r\n");
        stringBuffer.append("byDistanceUnit = " + ((int) usb_thermometry_basic_param.byDistanceUnit) + "\r\n");
        stringBuffer.append("dwDistance = " + usb_thermometry_basic_param.dwDistance + "\r\n");
        stringBuffer.append("byReflectiveEnable = " + ((int) usb_thermometry_basic_param.byReflectiveEnable) + "\r\n");
        stringBuffer.append("dwReflectiveTemperature = " + usb_thermometry_basic_param.dwReflectiveTemperature + "\r\n");
        stringBuffer.append("byThermomrtryInfoDisplayPosition = " + ((int) usb_thermometry_basic_param.byThermomrtryInfoDisplayPosition) + "\r\n");
        stringBuffer.append("byThermometryStreamOverlay = " + ((int) usb_thermometry_basic_param.byThermometryStreamOverlay) + "\r\n");
        stringBuffer.append("dwAlert = " + usb_thermometry_basic_param.dwAlert + "\r\n");
        stringBuffer.append("dwAlarm = " + usb_thermometry_basic_param.dwAlarm + "\r\n");
        stringBuffer.append("dwExternalOpticsTransmit = " + usb_thermometry_basic_param.dwExternalOpticsTransmit + "\r\n");
        stringBuffer.append("byDisplayCenTempEnabled = " + ((int) usb_thermometry_basic_param.byDisplayCenTempEnabled) + "\r\n");
        stringBuffer.append("byBackcolorEnabled = " + ((int) usb_thermometry_basic_param.byBackcolorEnabled) + "\r\n");
        stringBuffer.append("byShowAlarmColorEnabled = " + ((int) usb_thermometry_basic_param.byShowAlarmColorEnabled) + "\r\n");
        Alog.e("USB_THERMOMETRY_BASIC_PARAM = " + stringBuffer.toString(), new Object[0]);
    }
}
