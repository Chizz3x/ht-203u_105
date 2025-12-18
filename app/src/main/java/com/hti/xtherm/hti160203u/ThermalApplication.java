package com.hti.xtherm.hti160203u;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.hti.xtherm.hti160203u.helper.Alog;
import com.kongzue.dialogx.DialogX;
import com.kongzue.dialogx.style.MaterialStyle;

import org.opencv.android.InstallCallbackInterface;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;

/* loaded from: classes.dex */
public class ThermalApplication extends Application {
    private static Context global_context;
    private LoaderCallbackInterface opencv_init_callback = new LoaderCallbackInterface() { // from class: com.hti.xtherm.hti160203u.ThermalApplication.1
        @Override // org.opencv.android.LoaderCallbackInterface
        public void onManagerConnected(int i) {
            Alog.e("opencv初始化成功", new Object[0]);
        }

        @Override // org.opencv.android.LoaderCallbackInterface
        public void onPackageInstall(int i, InstallCallbackInterface installCallbackInterface) {
            Alog.e("opencv初始化成功", new Object[0]);
        }
    };

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();

//        System.setProperty("jna.nosys", "true");
//        System.setProperty("jna.prefix", "arm64-v8a");
//        try {
//            // Load the native dispatch library manually first
//            System.loadLibrary("jnidispatch");
//        } catch (UnsatisfiedLinkError e) {
//            Log.e("ThermalApplication", "Failed to load jnidispatch.so", e);
//        }
//        System.setProperty("jna.boot.library.path", getApplicationInfo().nativeLibraryDir);

        Log.d("ThermalApplication", "run");

        if (global_context == null) {
            global_context = getApplicationContext();
        }
        initKZDialog();
        initOpenCV();
        Alog.e("xxxxxxxxxxxxxxxxxxxxxxxxxxx启动", new Object[0]);
    }

    private void initKZDialog() {
        DialogX.init(this);
        DialogX.globalTheme = DialogX.THEME.LIGHT;
        DialogX.globalStyle = MaterialStyle.style();
        DialogX.autoRunOnUIThread = true;
    }

    private void initOpenCV() {
        if (!OpenCVLoader.initDebug()) {
            Alog.e("OpenCV initialized successfully", new Object[0]);
//            OpenCVLoader.initAsync("3.0.0", global_context, this.opencv_init_callback);
        } else {
            Alog.e("OpenCV initialization failed", new Object[0]);
//            this.opencv_init_callback.onManagerConnected(0);
        }
    }

    public static Context getAppContext() {
        return global_context;
    }
}
