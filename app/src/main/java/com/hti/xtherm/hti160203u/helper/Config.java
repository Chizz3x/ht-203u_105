package com.hti.xtherm.hti160203u.helper;

import android.util.Size;

/* loaded from: classes.dex */
public class Config {
    public static final String CACHE_DIR = "Cache Files";
    public static final int DEF_DETAIL = 25;
    public static final boolean DEF_DETAILSTATE = false;
    public static final int DEF_DISTANCE = 30;
    public static final int DEF_EMISSIVITY = 95;
    public static final int DEF_IMAGE_BRIGHTNESS = 40;
    public static final int DEF_IMAGE_CONTRAST = 35;
    public static final int DEF_NOISE = 50;
    public static final boolean DEF_NOISESTATE = true;
    public static final int DEF_OPTICAL = 30;
    public static final int DEF_REFLEATION = 50;
    public static final boolean DEF_REFLEATIONSTATE = false;
    public static final int F2_160_PID = 257;
    public static final int F2_160_VID = 11231;
    public static final int F2_256_PID = 258;
    public static final int F2_256_VID = 11231;
    public static final int F2_PREVIEW_HEIGHT = 248;
    public static final int F2_PREVIEW_WIDTH = 160;
    public static final String MEDIA_IMAGE_FOLDER = "105-203U Pictures";
    public static final String MEDIA_VIDEO_FOLDER = "105-203U Videos";
    public static final String MOVIES_SUFFIX = ".MP4";
    public static int PERMISSIONS_CODE = 1001;
    public static final String PICTURE_DIR = "Picture Files";
    public static final String PICTURS_SUFFIX = ".JPG";
    public static final int RECORD_VIDEO_MIN_TIME = 2000;
    public static final String VIDEO_DIR = "Video Files";
    public static String[] permissions_28 = {"android.permission.CAMERA", "android.permission.ACCESS_NETWORK_STATE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    public static String[] permissions_29 = {"android.permission.CAMERA", "android.permission.ACCESS_NETWORK_STATE"};
    public static final PaletteType DEF_PALETTE = PaletteType.WHITE_HOT;
    public static final Tempunit DEF_TEMPUNIT = Tempunit.Celsius;
    public static final Size MEDIA_RESOLUTION = new Size(1280, 960);

    public enum AlarmType {
        MAX,
        MIN
    }

    public enum Angle {
        ANGLE_0,
        ANGLE_90,
        ANGLE_180,
        ANGLE_270
    }

    public enum F2Type {
        F2_256,
        F2_160
    }

    public enum FeatureType {
        NONE,
        MAX,
        CENTER,
        MIN,
        PICK_MAX,
        PICK_MIN,
        PICK_POI
    }

    public enum Language {
        CHINESE,
        ENGLISH,
        RUSSIAN
    }

    public enum Tempunit {
        Celsius,
        Fahrenheit
    }

    public enum PaletteType {
        WHITE_HOT(1),
        BLACK_HOT(2),
        BLEND_1(10),
        BLEND_2(12),
        RAINBOW(11),
        IRON_1(13),
        IRON_2(14),
        BROWN(15),
        COLOR_1(16),
        COLOR_2(17),
        ICE_FIRE(18),
        RAIN(19),
        RED_HOT(20),
        GREEN_HOT(21),
        BLUE(22),
        // CUSTOM
        RGB(23),
        WHBC(24),
        HUMAN_ORANGE(25),
        HUMAN_DETECT(26),
        HUMAN_DETECT_BOX(27),
        TEST(100);

        private int palette;

        PaletteType(int i) {
            this.palette = i;
        }

        public int getPalette() {
            return this.palette;
        }
        public static boolean isCustom(PaletteType pType) {
            return pType == RGB
                || pType == WHBC
                || pType == HUMAN_ORANGE
                || pType == HUMAN_DETECT
                || pType == HUMAN_DETECT_BOX
                || pType == TEST;
        }
    }

    public enum TempRange {
        SMALL((byte) 2),
        LARGE((byte) 3);

        private byte range;

        TempRange(byte b) {
            this.range = b;
        }

        public byte getTempRange() {
            return this.range;
        }

        public String getTempRangeString() {
            float f;
            float f2 = 120.0f;
            if (this.range == 2) {
                f2 = -20.0f;
                f = 120.0f;
            } else {
                f = 550.0f;
            }
            return "(" + Helper.getTemperatureCharacters(f2, ShareHelper.getTempunit()) + "~" + Helper.getTemperatureCharacters(f, ShareHelper.getTempunit()) + ")";
        }
    }

    public enum PatternType {
        NONE(0, 0),
        POINT(1, 1),
        LINE(2, 2),
        RECT(3, 4);

        private int pattern;
        private int pointNumber;

        PatternType(int i, int i2) {
            this.pattern = i;
            this.pointNumber = i2;
        }

        public int getPattern() {
            return this.pattern;
        }

        public int getPointNumber() {
            return this.pointNumber;
        }
    }
}
