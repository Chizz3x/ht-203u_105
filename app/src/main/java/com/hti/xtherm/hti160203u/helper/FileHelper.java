package com.hti.xtherm.hti160203u.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.hti.xtherm.hti160203u.ThermalApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public class FileHelper {
    public static File getPicturesPath() {
        try {
            File externalFilesDir = ThermalApplication.getAppContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            if (!externalFilesDir.exists()) {
                externalFilesDir.mkdirs();
            }
            return externalFilesDir;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File getPictureFilePath() {
        try {
            return new File(getPicturesPath().getAbsolutePath() + File.separator + System.currentTimeMillis() + Config.PICTURS_SUFFIX);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File getVideosPath() {
        try {
            File externalFilesDir = ThermalApplication.getAppContext().getExternalFilesDir(Environment.DIRECTORY_MOVIES);
            if (!externalFilesDir.exists()) {
                externalFilesDir.mkdirs();
            }
            return externalFilesDir;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File getVideoFilePath() {
        try {
            return new File(getVideosPath().getAbsolutePath() + File.separator + System.currentTimeMillis() + Config.MOVIES_SUFFIX);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getRootDir() {
        return ThermalApplication.getAppContext().getExternalFilesDir(null).getAbsolutePath();
    }

    public static String getCacheDir() {
        String str = getRootDir() + File.separator + Config.CACHE_DIR;
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static File getCacheYUVFile() {
        return new File(getCacheDir() + File.separator + System.currentTimeMillis() + ".frame");
    }

    public static File getCacheLogFile() {
        return new File(getCacheDir() + File.separator + System.currentTimeMillis() + ".log");
    }

    public static boolean writeFile(File file, byte[] bArr) throws IOException {
        if (file != null && bArr != null && bArr.length > 0) {
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                fileOutputStream.write(bArr);
                fileOutputStream.flush();
                fileOutputStream.close();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String getlastModifiedTime(File file) {
        return (file == null || !file.exists()) ? "" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(file.lastModified()));
    }

    public static String getPictureFileRZ(File file) {
        if (file == null || !file.exists()) {
            return "N.A";
        }
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(file.getPath());
        return bitmapDecodeFile.getWidth() + " x " + bitmapDecodeFile.getHeight();
    }

    public static String getFileSize(long j) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        if (j == 0) {
            return "0B";
        }
        if (j < 1024) {
            return decimalFormat.format(j) + "B";
        }
        if (j < 1048576) {
            return decimalFormat.format(j / 1024.0d) + "KB";
        }
        if (j < 1073741824) {
            return decimalFormat.format(j / 1048576.0d) + "MB";
        }
        return decimalFormat.format(j / 1.073741824E9d) + "GB";
    }
}
