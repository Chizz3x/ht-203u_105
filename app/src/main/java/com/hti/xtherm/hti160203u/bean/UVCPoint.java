package com.hti.xtherm.hti160203u.bean;

import android.graphics.Point;

import com.hti.xtherm.hti160203u.helper.Config;

/* loaded from: classes.dex */
public class UVCPoint {
    public float temp;
    public float x;
    public float y;
    public Config.Tempunit tempunit = Config.Tempunit.Celsius;
    public Config.FeatureType type = Config.FeatureType.NONE;
    public boolean illegal = false;

    public UVCPoint(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public UVCPoint copy() {
        UVCPoint uVCPoint = new UVCPoint(this.x, this.y);
        uVCPoint.x = this.x;
        uVCPoint.y = this.y;
        uVCPoint.temp = this.temp;
        uVCPoint.tempunit = this.tempunit;
        uVCPoint.type = this.type;
        uVCPoint.illegal = this.illegal;
        return uVCPoint;
    }

    public Point convertPointSize(int i, int i2) {
        int i3 = (int) (this.x * i);
        if (i3 < 0) {
            i3 = 0;
        }
        if (i3 >= i) {
            i3 = i - 1;
        }
        int i4 = (int) (this.y * i2);
        int i5 = i4 >= 0 ? i4 : 0;
        if (i5 >= i2) {
            i5 = i2 - 1;
        }
        return new Point(i3, i5);
    }
}
