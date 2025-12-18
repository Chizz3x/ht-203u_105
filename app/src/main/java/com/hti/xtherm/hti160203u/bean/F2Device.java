package com.hti.xtherm.hti160203u.bean;

import com.hti.xtherm.hti160203u.helper.Config;

/* loaded from: classes.dex */
public class F2Device {
    public int pid;
    public int preview_height;
    public int preview_width;
    public String sn;
    public int thermal_height;
    public int thermal_width;
    public int vid;
    public int yuv_height;
    public int yuv_width;

    public F2Device(Config.F2Type f2Type) {
        if (f2Type == Config.F2Type.F2_256) {
            this.preview_width = 256;
            this.preview_height = 392;
        } else if (f2Type == Config.F2Type.F2_160) {
            this.preview_width = Config.F2_PREVIEW_WIDTH;
            this.preview_height = Config.F2_PREVIEW_HEIGHT;
        }
        int i = this.preview_width;
        this.thermal_width = i;
        int i2 = this.preview_height;
        this.thermal_height = (i2 / 2) - 4;
        this.yuv_width = i;
        this.yuv_height = (i2 / 2) - 4;
    }

    public int getYuvBufferSize() {
        return this.yuv_width * this.yuv_height * 2;
    }

    public int getTempBufferSize() {
        return this.thermal_width * this.thermal_height * 2;
    }

    public int getTempSize() {
        return this.thermal_width * this.thermal_height;
    }

    public int getArgb32BufferSize() {
        return this.yuv_width * this.yuv_height * 4;
    }
}
