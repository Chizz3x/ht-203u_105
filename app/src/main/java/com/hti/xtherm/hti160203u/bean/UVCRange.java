package com.hti.xtherm.hti160203u.bean;

import com.hti.xtherm.hti160203u.helper.Config;

/* loaded from: classes.dex */
public class UVCRange {
    public Config.PatternType patternType;
    public UVCPoint touch_down;
    public UVCPoint touch_up;

    public UVCRange(Config.PatternType patternType) {
        this.patternType = patternType;
    }

    public UVCRange copy() {
        UVCRange uVCRange = new UVCRange(this.patternType);
        uVCRange.touch_down = this.touch_down.copy();
        uVCRange.touch_up = this.touch_up.copy();
        return uVCRange;
    }
}
