package com.hti.xtherm.hti160203u.thread;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.Rect;

import com.hti.xtherm.hti160203u.bean.UVCPoint;
import com.hti.xtherm.hti160203u.bean.UVCRange;
import com.hti.xtherm.hti160203u.helper.Alog;
import com.hti.xtherm.hti160203u.helper.Config;
import com.hti.xtherm.hti160203u.helper.Helper;
import com.hti.xtherm.hti160203u.helper.ShareHelper;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class FeatureSearchThread extends Thread {
    private byte[] frame_temp_buffer;
    private int height;
    private OnFeatureSearchCallback mCallback;
    private boolean search = true;
    private int width;

    public interface OnFeatureSearchCallback {
        int onScreenRotation();

        void onSearchFeature(List<UVCPoint> list);

        List<UVCRange> onSearchRanges();

        Config.TempRange onTempRange();

        void onTemperatureAlarm(Config.AlarmType alarmType, boolean z);
    }

    private FeatureSearchThread() {
    }

    public static FeatureSearchThread load() {
        return new FeatureSearchThread();
    }

    public void search(OnFeatureSearchCallback onFeatureSearchCallback) {
        this.mCallback = onFeatureSearchCallback;
        start();
    }

    public void setOnFeatureSearchCallback(OnFeatureSearchCallback onFeatureSearchCallback) {
        this.mCallback = onFeatureSearchCallback;
    }

    public void putTemperatureBuffer(byte[] bArr, int i, int i2) {
        if (this.frame_temp_buffer == null && bArr != null && bArr.length >= i * i2 * 2) {
            this.frame_temp_buffer = (byte[]) bArr.clone();
            this.width = i;
            this.height = i2;
        }
    }

    public void stopSearch() {
        this.search = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:111:0x0419  */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        Iterator it;
        int i = Math.max(this.width, 1);
        int i2 = Math.max(this.height, 1);
        int i3 = 0;
        Alog.e("FeatureSearchThread启动", new Object[0]);
        while (this.search) {
            if (this.frame_temp_buffer == null) {
                try {
                    sleepms(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                ArrayList<UVCPoint> arrayList = new ArrayList<>();
                int i4 = 180;
                int i5 = this.mCallback.onScreenRotation() == 1 ? i3 : 180;
                int i6 = this.width * this.height;
                float[] fArr = new float[i6];
                UVCPoint uVCPoint = new UVCPoint(0.0f, 0.0f);
                uVCPoint.temp = (ByteBuffer.wrap(Helper.getIntBytes(Arrays.copyOfRange(this.frame_temp_buffer, i3, 2))).order(ByteOrder.LITTLE_ENDIAN).getInt() / 64.0f) - 50.0f;
                uVCPoint.type = Config.FeatureType.MAX;
                UVCPoint uVCPoint2 = new UVCPoint(0.0f, 0.0f);
                uVCPoint2.temp = uVCPoint.temp;
                uVCPoint2.type = Config.FeatureType.MIN;
                UVCPoint uVCPoint3 = new UVCPoint(0.5f, 0.5f);
                uVCPoint3.type = Config.FeatureType.CENTER;
                for (int i7 = i3; i7 < i6; i7++) {
                    int i8 = i7 * 2;
                    float f = (ByteBuffer.wrap(Helper.getIntBytes(Arrays.copyOfRange(this.frame_temp_buffer, i8, i8 + 2))).order(ByteOrder.LITTLE_ENDIAN).getInt() / 64.0f) - 50.0f;
                    fArr[i7] = f;
                    if (f > uVCPoint.temp) {
                        uVCPoint.x = (i7 % i2) / (this.width * 1.0f);
                        uVCPoint.y = (i7 / this.width) / (this.height * 1.0f);
                        uVCPoint.temp = fArr[i7];
                    }
                    if (fArr[i7] < uVCPoint2.temp) {
                        uVCPoint2.x = (i7 % i) / (this.width * 1.0f);
                        uVCPoint2.y = (i7 / this.width) / (this.height * 1.0f);
                        uVCPoint2.temp = fArr[i7];
                    }
                }
                int i9 = this.width;
                uVCPoint3.temp = fArr[(int) ((this.height * 0.5f * i9) + (i9 * 0.5f))];
                arrayList.add(uVCPoint);
                arrayList.add(uVCPoint2);
                arrayList.add(uVCPoint3);
                OnFeatureSearchCallback onFeatureSearchCallback = this.mCallback;
                if (onFeatureSearchCallback != null) {
                    List onSearchRanges = onFeatureSearchCallback.onSearchRanges();
                    if (onSearchRanges != null && onSearchRanges.size() > 0) {
                        Iterator it2 = onSearchRanges.iterator();
                        while (it2.hasNext()) {
                            UVCRange uVCRange = (UVCRange) it2.next();
                            if (uVCRange.patternType == Config.PatternType.POINT) {
                                if (i5 == i4) {
                                    uVCRange.touch_up.x = 1.0f - uVCRange.touch_up.x;
                                    uVCRange.touch_up.y = 1.0f - uVCRange.touch_up.y;
                                }
                                UVCPoint uVCPoint4 = new UVCPoint(uVCRange.touch_up.x, uVCRange.touch_up.y);
                                uVCPoint4.type = Config.FeatureType.PICK_POI;
                                Point convertPointSize = uVCRange.touch_down.convertPointSize(this.width, this.height);
                                uVCPoint4.temp = fArr[(convertPointSize.y * this.width) + convertPointSize.x];
                                arrayList.add(uVCPoint4);
                                it = it2;
                            } else if (uVCRange.patternType == Config.PatternType.LINE) {
                                if (i5 == i4) {
                                    uVCRange.touch_down.x = 1.0f - uVCRange.touch_down.x;
                                    uVCRange.touch_down.y = 1.0f - uVCRange.touch_down.y;
                                    uVCRange.touch_up.x = 1.0f - uVCRange.touch_up.x;
                                    uVCRange.touch_up.y = 1.0f - uVCRange.touch_up.y;
                                }
                                Point convertPointSize2 = uVCRange.touch_down.convertPointSize(this.width, this.height);
                                Point convertPointSize3 = uVCRange.touch_up.convertPointSize(this.width, this.height);
                                Path path = new Path();
                                path.moveTo(convertPointSize2.x, convertPointSize2.y);
                                path.lineTo(convertPointSize3.x, convertPointSize3.y);
                                PathMeasure pathMeasure = new PathMeasure(path, false);
                                float length = pathMeasure.getLength();
                                float[] fArr2 = new float[2];
                                float[] fArr3 = new float[2];
                                pathMeasure.getPosTan(0.0f, fArr2, fArr3);
                                int i10 = (int) ((fArr2[1] * this.width) + fArr2[0]);
                                UVCPoint uVCPoint5 = new UVCPoint(fArr2[0] / this.width, fArr2[1] / this.height);
                                uVCPoint5.temp = fArr[i10];
                                uVCPoint5.type = Config.FeatureType.PICK_MAX;
                                UVCPoint uVCPoint6 = new UVCPoint(fArr2[0] / this.width, fArr2[1] / this.height);
                                uVCPoint6.temp = fArr[i10];
                                uVCPoint6.type = Config.FeatureType.PICK_MIN;
                                int i11 = 0;
                                while (true) {
                                    float f2 = i11;
                                    if (f2 > length) {
                                        break;
                                    }
                                    pathMeasure.getPosTan(f2, fArr2, fArr3);
                                    int i12 = (int) ((fArr2[1] * this.width) + fArr2[0]);
                                    if (fArr[i12] > uVCPoint5.temp) {
                                        uVCPoint5.x = fArr2[0] / this.width;
                                        uVCPoint5.y = fArr2[1] / this.height;
                                        uVCPoint5.temp = fArr[i12];
                                    }
                                    Iterator it3 = it2;
                                    if (fArr[i12] < uVCPoint6.temp) {
                                        uVCPoint6.x = fArr2[0] / this.width;
                                        uVCPoint6.y = fArr2[1] / this.height;
                                        uVCPoint6.temp = fArr[i12];
                                    }
                                    i11++;
                                    it2 = it3;
                                }
                                it = it2;
                                arrayList.add(uVCPoint5);
                                arrayList.add(uVCPoint6);
                            } else {
                                it = it2;
                                if (uVCRange.patternType == Config.PatternType.RECT) {
                                    if (i5 == 180) {
                                        uVCRange.touch_down.x = 1.0f - uVCRange.touch_down.x;
                                        uVCRange.touch_down.y = 1.0f - uVCRange.touch_down.y;
                                        uVCRange.touch_up.x = 1.0f - uVCRange.touch_up.x;
                                        uVCRange.touch_up.y = 1.0f - uVCRange.touch_up.y;
                                    }
                                    Rect rect = Helper.getRect(uVCRange.touch_down.convertPointSize(this.width, this.height), uVCRange.touch_up.convertPointSize(this.width, this.height));
                                    UVCPoint uVCPoint7 = new UVCPoint((rect.left * 1.0f) / this.width, (rect.top * 1.0f) / this.height);
                                    int i13 = (rect.top * this.width) + rect.left;
                                    uVCPoint7.temp = fArr[i13];
                                    uVCPoint7.type = Config.FeatureType.PICK_MAX;
                                    UVCPoint uVCPoint8 = new UVCPoint((rect.left * 1.0f) / this.width, (rect.top * 1.0f) / this.height);
                                    uVCPoint8.temp = fArr[i13];
                                    uVCPoint8.type = Config.FeatureType.PICK_MIN;
                                    for (int i14 = rect.left; i14 <= rect.right; i14++) {
                                        for (int i15 = rect.top; i15 <= rect.bottom; i15++) {
                                            int i16 = (this.width * i15) + i14;
                                            if (fArr[i16] > uVCPoint7.temp) {
                                                uVCPoint7.x = i14 / (this.width * 1.0f);
                                                uVCPoint7.y = i15 / (this.height * 1.0f);
                                                uVCPoint7.temp = fArr[i16];
                                            }
                                            if (fArr[i16] < uVCPoint8.temp) {
                                                uVCPoint8.x = i14 / (this.width * 1.0f);
                                                uVCPoint8.y = i15 / (this.height * 1.0f);
                                                uVCPoint8.temp = fArr[i16];
                                            }
                                        }
                                    }
                                    arrayList.add(uVCPoint7);
                                    arrayList.add(uVCPoint8);
                                }
                            }
                            it2 = it;
                            i4 = 180;
                        }
                    }
                    boolean z = ShareHelper.getAlarmMaxEnable() && ShareHelper.getAlarmMaxValue() != Float.MIN_VALUE && uVCPoint.temp >= ShareHelper.getAlarmMaxValue();
                    OnFeatureSearchCallback onFeatureSearchCallback2 = this.mCallback;
                    if (onFeatureSearchCallback2 != null) {
                        onFeatureSearchCallback2.onTemperatureAlarm(Config.AlarmType.MAX, z);
                    }
                    boolean z2 = ShareHelper.getAlarmMinEnable() && ShareHelper.getAlarmMinValue() != Float.MIN_VALUE && uVCPoint2.temp <= ShareHelper.getAlarmMinValue();
                    OnFeatureSearchCallback onFeatureSearchCallback3 = this.mCallback;
                    if (onFeatureSearchCallback3 != null) {
                        onFeatureSearchCallback3.onTemperatureAlarm(Config.AlarmType.MIN, z2);
                    }
                    OnFeatureSearchCallback onFeatureSearchCallback4 = this.mCallback;
                    if (onFeatureSearchCallback4 != null) {
                        Config.TempRange onTempRange = onFeatureSearchCallback4.onTempRange();
                        for (UVCPoint uVCPoint9 : arrayList) {
                            if (onTempRange == Config.TempRange.SMALL) {
                                uVCPoint9.temp += 1.0f;
                                if (uVCPoint9.temp < -22.0f || uVCPoint9.temp > 122.4d) {
                                    uVCPoint9.illegal = true;
                                    if (i5 != 180) {
                                        uVCPoint9.x = 1.0f - uVCPoint9.x;
                                        uVCPoint9.y = 1.0f - uVCPoint9.y;
                                    }
                                }
                                if (i5 != 180) {
                                }
                            } else {
                                uVCPoint9.temp += 1.5f;
                                if (uVCPoint9.temp < 117.6d || uVCPoint9.temp > 561.0d) {
                                    uVCPoint9.illegal = true;
                                    if (i5 != 180) {
                                    }
                                }
                                if (i5 != 180) {
                                }
                            }
                        }
                    }
                    Config.Tempunit tempunit = ShareHelper.getTempunit();
                    if (tempunit != Config.Tempunit.Celsius) {
                        for (UVCPoint uVCPoint10 : arrayList) {
                            if (!uVCPoint10.illegal) {
                                uVCPoint10.temp = Helper.getTemperatureValue(uVCPoint10.temp, tempunit);
                                uVCPoint10.tempunit = tempunit;
                            }
                        }
                    }
                    OnFeatureSearchCallback onFeatureSearchCallback5 = this.mCallback;
                    if (onFeatureSearchCallback5 != null) {
                        onFeatureSearchCallback5.onSearchFeature(arrayList);
                    }
                }
                try {
                    sleepms(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                this.frame_temp_buffer = null;
                i3 = 0;
            }
        }
        Alog.e("FeatureSearchThread结束", new Object[0]);
    }

    private void sleepms(int i) throws InterruptedException {
        try {
            Thread.sleep(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
