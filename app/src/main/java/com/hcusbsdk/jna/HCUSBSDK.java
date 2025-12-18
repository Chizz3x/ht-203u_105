package com.hcusbsdk.jna;

import com.sun.jna.Native;

/* loaded from: classes.dex */
public enum HCUSBSDK {
    CLASS;

    private static HCUSBSDKByJNA usbSdk = null;

    public static HCUSBSDKByJNA getInstance() {
        if (usbSdk == null) {
            synchronized (HCUSBSDKByJNA.class) {
                try {

                    usbSdk = (HCUSBSDKByJNA) Native.loadLibrary("HCUSBSDK", HCUSBSDKByJNA.class);
                } finally {
                }
            }
        }
        return usbSdk;
    }
}
