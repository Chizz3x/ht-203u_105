package com.hti.xtherm.hti160203u.helper;

import java.util.Collections;
import java.util.List;

import ai.onnxruntime.OrtException;

public class CustomPalettes {
//    private static void tempToArgb(byte[] tempBuffer, byte[] argbBuffer, int width, int height, Config.PaletteType pType) throws OrtException {
//        final int size = width * height;
//        float[] temps = new float[size];
//        float frameMin = Float.MAX_VALUE;
//        float frameMax = -Float.MAX_VALUE;
//
//        // --- Step 1: convert raw temps to Celsius ---
//        for (int i = 0; i < size; i++) {
//            int raw = (tempBuffer[i * 2] & 0xFF) | ((tempBuffer[i * 2 + 1] & 0xFF) << 8);
//            float t = raw / 64.0f - 50.0f;
//            temps[i] = t;
//            frameMin = Math.min(frameMin, t);
//            frameMax = Math.max(frameMax, t);
//        }
//
//        float configMin = getTempRange() == Config.TempRange.SMALL ? -20f : 120f;
//        float configMax = getTempRange() == Config.TempRange.SMALL ? 120f : 550f;
//        float effectiveMin = Math.max(frameMin, configMin);
//        float effectiveMax = Math.min(frameMax, configMax);
//        if (effectiveMax - effectiveMin < 1e-3f) effectiveMax = effectiveMin + 1f;
//
//        // --- Step 2: allocate scratch buffers once ---
//        float[] scratchWin = new float[9];
//        float[] blurBuf = new float[size];
//        float[] tmpBuf = new float[size];
//
//        // --- Step 3: noise reduction ---
//        if (ShareHelper.getNoiseEnable()) {
//            int passes = Math.max(1, ShareHelper.getNoise() / 30);
//            float[] src = temps;
//            float[] dst = blurBuf;
//            for (int p = 0; p < passes; p++) {
//                medianFilter3x3_inplace(src, dst, width, height, scratchWin);
//                float[] t = src; src = dst; dst = t;
//            }
//            temps = src;
//        }
//
//        // --- Step 4: detail enhancement ---
//        if (ShareHelper.getDetailEnable()) {
//            float amount = ShareHelper.getDetail() / 100f * 1.2f;
//            enhanceDetails_inplace(temps, tmpBuf, blurBuf, width, height, amount, scratchWin);
//            temps = tmpBuf;
//        }
//
//        // --- Step 6: precompute brightness/contrast ---
//        float brightness = (ShareHelper.getBrightness() - 50) / 100f;
//        float contrast   = 1f + ((ShareHelper.getContrast() - 50) / 50f);
//        if (contrast < 0f) contrast = 0f;
//
//        List<float[]> detectedBoxes = pType == Config.PaletteType.HUMAN_DETECT
//                ? getInterpolatedBoxes(temps, width, height, effectiveMin, effectiveMax)
//                : Collections.emptyList();
//
//        for (int i = 0; i < size; i++) {
//            float normalized = (temps[i] - effectiveMin) / (effectiveMax - effectiveMin);
//            normalized = Math.max(0f, Math.min(1f, normalized));
//            normalized = applyBrightnessContrastFloat(normalized, brightness, contrast);
//
//            boolean insideBox = false;
//            if (pType == Config.PaletteType.HUMAN_DETECT) {
//                int x = i % width;
//                int y = i / width;
//                for (float[] box : detectedBoxes) {
//                    if (box[0] <= x && x <= box[2] && box[1] <= y && y <= box[3] && box[4] >= 0.5f) {
//                        insideBox = true;
//                        break;
//                    }
//                }
//            }
//
//            int color;
//            switch (pType) {
//                case RGB:  color = paletteRGB(normalized); break;
//                case WHBC: color = paletteWhiteHotBlueCold(normalized); break;
//                case HUMAN_ORANGE: color = paletteHumanOrange(temps[i]); break;
//                case HUMAN_DETECT: color = paletteDetect(temps[i], insideBox); break;
//                case TEST: color = paletteHumanOrange(temps[i]); break;
//                default:   color = paletteRGB(normalized); break;
//            }
//
//            int idx = i * 4;
//            argbBuffer[idx]     = (byte) ((color >> 16) & 0xFF);
//            argbBuffer[idx + 1] = (byte) ((color >> 8) & 0xFF);
//            argbBuffer[idx + 2] = (byte) (color & 0xFF);
//            argbBuffer[idx + 3] = (byte) ((color >> 24) & 0xFF);
//        }
//    }
}
