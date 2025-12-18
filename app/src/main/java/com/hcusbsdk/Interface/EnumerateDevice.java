package com.hcusbsdk.Interface;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.util.Log;
import com.sun.jna.Function;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class EnumerateDevice {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static int[] m_fdList;
    public static HashMap<String, UsbDevice> m_deviceList = new HashMap<>();
    private static final int[][] ProductTable_Thermal = {new int[]{11231, 257}, new int[]{11231, 383}};
    private static final int[][] ProductTable_Thermal_VID = {new int[]{11231, 11231}};
    private static final int[][] ProductTable_Thermal_PID = {new int[]{257, 319}, new int[]{320, 351}, new int[]{352, 383}, new int[]{Function.USE_VARARGS, 512}};
    private static final int[][] ProductTable_Camera = {new int[]{JavaInterface.USB_SET_VIDEO_GAIN, 22594}, new int[]{JavaInterface.USB_SET_VIDEO_GAIN, 22646}, new int[]{3141, JavaInterface.USB_SET_ENVIROTEMPERATURE_CORRECT}, new int[]{3141, 24576}, new int[]{3141, 25432}, new int[]{3141, 25446}, new int[]{3141, 25451}, new int[]{4429, 33847}, new int[]{4429, 34185}, new int[]{7119, 8833}, new int[]{11231, 640}, new int[]{11231, 642}, new int[]{11231, 645}, new int[]{11231, 670}, new int[]{11231, 671}};
    private static final int[][] ProductTable_Camera_VID = {new int[]{11231, 11231}};
    private static final int[][] ProductTable_Camera_PID = {new int[]{513, 768}};
    private static final int[][] ProductTable_Acs = {new int[]{11231, 769}, new int[]{1155, 22352}, new int[]{1155, 22315}};
    private static final int[][] ProductTable_Acs_VID = {new int[]{11231, 11231}};
    private static final int[][] ProductTable_Acs_PID = {new int[]{769, 1024}};
    private static final int[][] ProductTable_Transmission_VID = {new int[]{11231, 11231}};
    private static final int[][] ProductTable_Transmission_PID = {new int[]{1281, 1536}};

    public int EnumDevice(Context context) {
        PendingIntent broadcast;
        UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
        m_deviceList.clear();
        FilterAcsWithID(deviceList);
        FilterCameraWithID(deviceList);
        FilterThermalWithID(deviceList);
        FilterTransmissionWithID(deviceList);
        m_fdList = new int[m_deviceList.size()];
        int i = 0;
        if (Build.VERSION.SDK_INT > 30) {
            broadcast = PendingIntent.getBroadcast(context, 0, new Intent("com.android.example.USB_PERMISSION"), PendingIntent.FLAG_IMMUTABLE);
        } else {
            broadcast = PendingIntent.getBroadcast(context, 0, new Intent("com.android.example.USB_PERMISSION"), PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        }
        for (UsbDevice usbDevice : m_deviceList.values()) {
            if (usbManager.hasPermission(usbDevice)) {
                Log.i("[JavaInterface]", "EnumDevice [1]Device  VendorId: " + usbDevice.getVendorId() + " Device ProductId: " + usbDevice.getProductId());
            } else {
                Log.e("[JavaInterface]", "EnumDevice [2]Device  VendorId: " + usbDevice.getVendorId() + " Device ProductId: " + usbDevice.getProductId());
                usbManager.requestPermission(usbDevice, broadcast);
            }
            UsbDeviceConnection usbDeviceConnectionOpenDevice = usbManager.openDevice(usbDevice);
            if (usbDeviceConnectionOpenDevice != null) {
                m_fdList[i] = usbDeviceConnectionOpenDevice.getFileDescriptor();
                Log.i("[JavaInterface]", "EnumDevice [3]openDevice succ, fd: " + m_fdList[i]);
            } else {
                Log.e("[JavaInterface]", "EnumDevice [4]openDevice failed.");
            }
            i++;
        }
        return m_deviceList.size();
    }

    private void FilterAcsWithID(HashMap<String, UsbDevice> map) {
        if (map.size() == 0) {
            Log.d("[JavaInterface]", "FilterAcsWithID, deviceList.size() == 0");
            return;
        }
        int i = 0;
        for (Map.Entry<String, UsbDevice> entry : map.entrySet()) {
            int vendorId = entry.getValue().getVendorId();
            int productId = entry.getValue().getProductId();
            int[][] iArr = ProductTable_Acs;
            if (!CheckProductID(vendorId, productId, iArr, iArr.length)) {
                int vendorId2 = entry.getValue().getVendorId();
                int productId2 = entry.getValue().getProductId();
                int[][] iArr2 = ProductTable_Acs_VID;
                int[][] iArr3 = ProductTable_Acs_PID;
                if (CheckProductIDEx(vendorId2, productId2, iArr2, iArr3, iArr2.length, iArr3.length)) {
                }
            }
            Log.d("[JavaInterface]", "FilterAcsWithID, dwVid[" + entry.getValue().getVendorId() + "] dwPid [" + entry.getValue().getProductId() + "]");
            m_deviceList.put(entry.getKey(), entry.getValue());
            i++;
        }
        Log.d("[JavaInterface]", "FilterAcsWithID, iResultCount[" + i + "]");
    }

    private void FilterCameraWithID(HashMap<String, UsbDevice> map) {
        if (map.size() == 0) {
            Log.d("[JavaInterface]", "FilterCameraWithID, deviceList.size() == 0");
            return;
        }
        int i = 0;
        for (Map.Entry<String, UsbDevice> entry : map.entrySet()) {
            int vendorId = entry.getValue().getVendorId();
            int productId = entry.getValue().getProductId();
            int[][] iArr = ProductTable_Camera;
            if (!CheckProductID(vendorId, productId, iArr, iArr.length)) {
                int vendorId2 = entry.getValue().getVendorId();
                int productId2 = entry.getValue().getProductId();
                int[][] iArr2 = ProductTable_Camera_VID;
                int[][] iArr3 = ProductTable_Camera_PID;
                if (CheckProductIDEx(vendorId2, productId2, iArr2, iArr3, iArr2.length, iArr3.length)) {
                }
            }
            Log.d("[JavaInterface]", "FilterCameraWithID, dwVid[" + entry.getValue().getVendorId() + "] dwPid [" + entry.getValue().getProductId() + "]");
            m_deviceList.put(entry.getKey(), entry.getValue());
            i++;
        }
        Log.d("[JavaInterface]", "FilterCameraWithID, iResultCount[" + i + "]");
    }

    private void FilterThermalWithID(HashMap<String, UsbDevice> map) {
        if (map.size() == 0) {
            Log.d("[JavaInterface]", "FilterThermalWithID, deviceList.size() == 0");
            return;
        }
        int i = 0;
        for (Map.Entry<String, UsbDevice> entry : map.entrySet()) {
            int vendorId = entry.getValue().getVendorId();
            int productId = entry.getValue().getProductId();
            int[][] iArr = ProductTable_Thermal;
            if (!CheckProductID(vendorId, productId, iArr, iArr.length)) {
                int vendorId2 = entry.getValue().getVendorId();
                int productId2 = entry.getValue().getProductId();
                int[][] iArr2 = ProductTable_Thermal_VID;
                int[][] iArr3 = ProductTable_Thermal_PID;
                if (CheckProductIDEx(vendorId2, productId2, iArr2, iArr3, iArr2.length, iArr3.length)) {
                }
            }
            Log.d("[JavaInterface]", "FilterThermalWithID, dwVid[" + entry.getValue().getVendorId() + "] dwPid [" + entry.getValue().getProductId() + "]");
            m_deviceList.put(entry.getKey(), entry.getValue());
            i++;
        }
        Log.d("[JavaInterface]", "FilterThermalWithID, iResultCount[" + i + "]");
    }

    private void FilterTransmissionWithID(HashMap<String, UsbDevice> map) {
        if (map.size() == 0) {
            Log.d("[JavaInterface]", "FilterTransmissionWithID, deviceList.size() == 0");
            return;
        }
        int i = 0;
        for (Map.Entry<String, UsbDevice> entry : map.entrySet()) {
            int vendorId = entry.getValue().getVendorId();
            int productId = entry.getValue().getProductId();
            int[][] iArr = ProductTable_Transmission_VID;
            int[][] iArr2 = ProductTable_Transmission_PID;
            if (CheckProductIDEx(vendorId, productId, iArr, iArr2, iArr.length, iArr2.length)) {
                Log.d("FilterTransmission", "FilterTransmissionWithID, dwVid[" + entry.getValue().getVendorId() + "] dwPid[" + entry.getValue().getProductId() + "]");
                m_deviceList.put(entry.getKey(), entry.getValue());
                i++;
            }
        }
        Log.d("[JavaInterface]", "FilterTransmissionWithID, iResultCount[" + i + "]");
    }

    private boolean CheckProductID(int i, int i2, int[][] iArr, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            int[] iArr2 = iArr[i4];
            if (i == iArr2[0] && i2 == iArr2[1]) {
                return true;
            }
        }
        return false;
    }

    private boolean CheckRange(int i, int[] iArr, int i2) {
        return i >= iArr[0] && i <= iArr[1];
    }

    private boolean CheckProductIDEx(int i, int i2, int[][] iArr, int[][] iArr2, int i3, int i4) {
        boolean z;
        int i5 = 0;
        while (true) {
            if (i5 >= i3) {
                z = false;
                break;
            }
            if (CheckRange(i, iArr[i5], 2)) {
                z = true;
                break;
            }
            i5++;
        }
        if (z) {
            for (int i6 = 0; i6 < i4; i6++) {
                if (CheckRange(i2, iArr2[i6], 2)) {
                    return true;
                }
            }
        }
        return false;
    }
}
