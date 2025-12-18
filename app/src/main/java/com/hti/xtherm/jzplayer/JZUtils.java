package com.hti.xtherm.jzplayer;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.ViewConfiguration;
import android.view.Window;
import java.util.Formatter;
import java.util.Locale;

/* loaded from: classes.dex */
public class JZUtils {
    public static int SYSTEM_UI = 0;
    public static final String TAG = "JZVD";

    public static String stringForTime(long j) {
        if (j <= 0 || j >= 86400000) {
            return "00:00";
        }
        long j2 = j / 1000;
        int i = (int) (j2 % 60);
        int i2 = (int) ((j2 / 60) % 60);
        int i3 = (int) (j2 / 3600);
        Formatter formatter = new Formatter(new StringBuilder(), Locale.getDefault());
        if (i3 > 0) {
            return formatter.format("%d:%02d:%02d", Integer.valueOf(i3), Integer.valueOf(i2), Integer.valueOf(i)).toString();
        }
        return formatter.format("%02d:%02d", Integer.valueOf(i2), Integer.valueOf(i)).toString();
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    public static Activity scanForActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public static void setRequestedOrientation(Context context, int i) {
        if (scanForActivity(context) != null) {
            scanForActivity(context).setRequestedOrientation(i);
        } else {
            scanForActivity(context).setRequestedOrientation(i);
        }
    }

    public static Window getWindow(Context context) {
        if (scanForActivity(context) != null) {
            return scanForActivity(context).getWindow();
        }
        return scanForActivity(context).getWindow();
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static void saveProgress(Context context, Object obj, long j) {
        if (Jzvd.SAVE_PROGRESS) {
            Log.i("JZVD", "saveProgress: " + j);
            if (j < 5000) {
                j = 0;
            }
            context.getSharedPreferences("JZVD_PROGRESS", 0).edit().putLong("newVersion:" + obj.toString(), j).apply();
        }
    }

    public static long getSavedProgress(Context context, Object obj) {
        if (Jzvd.SAVE_PROGRESS) {
            return context.getSharedPreferences("JZVD_PROGRESS", 0).getLong("newVersion:" + obj.toString(), 0L);
        }
        return 0L;
    }

    public static void clearSavedProgress(Context context, Object obj) {
        if (obj == null) {
            context.getSharedPreferences("JZVD_PROGRESS", 0).edit().clear().apply();
        } else {
            context.getSharedPreferences("JZVD_PROGRESS", 0).edit().putLong("newVersion:" + obj.toString(), 0L).apply();
        }
    }

    public static void showStatusBar(Context context) {
        if (Jzvd.TOOL_BAR_EXIST) {
            getWindow(context).clearFlags(1024);
        }
    }

    public static void hideStatusBar(Context context) {
        if (Jzvd.TOOL_BAR_EXIST) {
            getWindow(context).setFlags(1024, 1024);
        }
    }

    public static void hideSystemUI(Context context) {
        SYSTEM_UI = getWindow(context).getDecorView().getSystemUiVisibility();
        getWindow(context).getDecorView().setSystemUiVisibility(5638);
    }

    public static void showSystemUI(Context context) {
        getWindow(context).getDecorView().setSystemUiVisibility(SYSTEM_UI);
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }

    public static int getNavigationBarHeight(Context context) {
        boolean zHasPermanentMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey();
        int identifier = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier <= 0 || zHasPermanentMenuKey) {
            return 0;
        }
        return context.getResources().getDimensionPixelSize(identifier);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }
}
