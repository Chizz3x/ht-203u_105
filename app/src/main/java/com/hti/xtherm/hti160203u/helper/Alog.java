package com.hti.xtherm.hti160203u.helper;

import java.util.Locale;

/* loaded from: classes.dex */
public class Alog {
    public static boolean IsDebug = true;
    private static final String TAG = "xiao_";

    public static void d(String str, Object... objArr) {
    }

    public static void d(Throwable th, String str, Object... objArr) {
    }

    public static void e(String str, Object... objArr) {
    }

    public static void e(Throwable th, String str, Object... objArr) {
    }

    public static void i(String str, Object... objArr) {
    }

    public static void i(Throwable th, String str, Object... objArr) {
    }

    public static void v(String str, Object... objArr) {
    }

    public static void v(Throwable th, String str, Object... objArr) {
    }

    public static void w(String str, Object... objArr) {
    }

    public static void w(Throwable th, String str, Object... objArr) {
    }

    public static void printCaller() {
        if (IsDebug) {
            try {
                StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
                StringBuilder sb = new StringBuilder();
                sb.append("print caller info\n==========BEGIN OF CALLER INFO============\n");
                for (int i = 2; i < stackTrace.length; i++) {
                    String className = stackTrace[i].getClassName();
                    sb.append(String.format(Locale.US, "[%03d] %s.%s(%s:%d)", Long.valueOf(Thread.currentThread().getId()), className.substring(className.lastIndexOf(46) + 1), stackTrace[i].getMethodName(), stackTrace[i].getFileName(), Integer.valueOf(stackTrace[i].getLineNumber())));
                    sb.append("\n");
                }
                sb.append("==========END OF CALLER INFO============");
                i(TAG, sb.toString());
            } catch (Exception e) {
                e(e, TAG, e.getMessage());
            }
        }
    }

    private static String buildMessage(String str, Object[] objArr) {
        String strSubstring;
        String methodName;
        String fileName;
        int lineNumber;
        if (objArr != null) {
            try {
                if (objArr.length != 0) {
                    str = String.format(Locale.US, str, objArr);
                }
            } catch (Exception e) {
                e(e, TAG, e.getMessage());
                return "----->ERROR LOG STRING<------";
            }
        }
        if (!IsDebug) {
            return str;
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                strSubstring = "";
                methodName = "";
                fileName = methodName;
                lineNumber = 0;
                break;
            }
            if (!stackTrace[i].getClass().equals(Alog.class)) {
                String className = stackTrace[i].getClassName();
                strSubstring = className.substring(className.lastIndexOf(46) + 1);
                methodName = stackTrace[i].getMethodName();
                fileName = stackTrace[i].getFileName();
                lineNumber = stackTrace[i].getLineNumber();
                break;
            }
            i++;
        }
        return String.format(Locale.US, "%s ==========> %s", str, String.format(Locale.US, "[%03d] %s.%s(%s:%d)", Long.valueOf(Thread.currentThread().getId()), strSubstring, methodName, fileName, Integer.valueOf(lineNumber)));
    }
}
