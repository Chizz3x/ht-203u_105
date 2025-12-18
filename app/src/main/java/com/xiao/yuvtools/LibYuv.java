package com.xiao.yuvtools;

/* loaded from: classes.dex */
public class LibYuv {
    public native boolean ARGB_TO_I420(byte[] bArr, byte[] bArr2, int i, int i2);

    public native boolean ARGB_TO_NV21(byte[] bArr, byte[] bArr2, int i, int i2);

    public native boolean UYVY_TO_ARGB(byte[] bArr, byte[] bArr2, int i, int i2);

    public native boolean YUV2_TO_ARGB(byte[] bArr, byte[] bArr2, int i, int i2);

    public native String stringFromJNI();

    static {
        System.loadLibrary("yuvtools");
    }
}
