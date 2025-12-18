package com.hti.xtherm.hti160203u.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.hti.xtherm.hti160203u.ThermalApplication;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes.dex */
public class BitmapHelper {
    public static Bitmap scale(Bitmap bitmap, int i, int i2) {
        if (bitmap == null || bitmap.isRecycled() || i * i2 <= 0) {
            return null;
        }
        Mat mat = new Mat();
        Utils.bitmapToMat(bitmap, mat);
        Mat mat2 = new Mat();
        Imgproc.resize(mat, mat2, new Size(i, i2));
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        Utils.matToBitmap(mat2, bitmapCreateBitmap);
        mat.release();
        mat2.release();
        bitmap.recycle();
        return bitmapCreateBitmap.copy(Bitmap.Config.ARGB_8888, true);
    }

    public static Bitmap getAssetsBitmap(String str) throws IOException {
        try {
            InputStream inputStreamOpen = ThermalApplication.getAppContext().getAssets().open(str);
            Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen);
            inputStreamOpen.close();
            return bitmapDecodeStream;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap rotate(Bitmap bitmap, int i) {
        Bitmap bitmapCreateBitmap;
        if (bitmap == null || bitmap.isRecycled() || i % 360 == 0) {
            return bitmap;
        }
        Mat mat = new Mat();
        Mat mat2 = new Mat();
        Utils.bitmapToMat(bitmap, mat);
        int i2 = ((i / 90) * 90) % 360;
        if (i2 == 90) {
            bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
            Core.rotate(mat, mat2, 0);
        } else if (i2 == 180) {
            bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Core.rotate(mat, mat2, 1);
        } else {
            bitmapCreateBitmap = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
            Core.rotate(mat, mat2, 2);
        }
        Utils.matToBitmap(mat2, bitmapCreateBitmap);
        mat.release();
        mat2.release();
        bitmap.recycle();
        return bitmapCreateBitmap;
    }
}
