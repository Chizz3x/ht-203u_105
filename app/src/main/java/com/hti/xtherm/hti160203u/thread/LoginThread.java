package com.hti.xtherm.hti160203u.thread;

import com.hcusbsdk.Interface.JavaInterface;
import com.hcusbsdk.Interface.USB_DEVICE_INFO;
import com.hcusbsdk.Interface.USB_DEVICE_REG_RES;
import com.hcusbsdk.Interface.USB_USER_LOGIN_INFO;
import com.hti.xtherm.hti160203u.listener.OnLoginCallback;

/* loaded from: classes.dex */
public class LoginThread extends Thread {
    private OnLoginCallback callback;
    private USB_DEVICE_INFO info;

    public static void login(USB_DEVICE_INFO usb_device_info, OnLoginCallback onLoginCallback) {
        LoginThread loginThread = new LoginThread();
        loginThread.info = usb_device_info;
        loginThread.callback = onLoginCallback;
        loginThread.start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        USB_USER_LOGIN_INFO usb_user_login_info = new USB_USER_LOGIN_INFO();
        usb_user_login_info.dwTimeout = 5000;
        usb_user_login_info.dwDevIndex = this.info.dwIndex;
        usb_user_login_info.dwVID = this.info.dwVID;
        usb_user_login_info.dwPID = this.info.dwPID;
        usb_user_login_info.dwFd = this.info.dwFd;
        int iUSB_Login = JavaInterface.getInstance().USB_Login(usb_user_login_info, new USB_DEVICE_REG_RES());
        if (iUSB_Login == -1) {
            onLoginFailed();
        } else {
            try {
                onLoginSuccess(iUSB_Login);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void onLoginFailed() {
        OnLoginCallback onLoginCallback = this.callback;
        if (onLoginCallback != null) {
            onLoginCallback.onLoginFailed();
        }
    }

    private void onLoginSuccess(int i) throws IllegalAccessException {
        OnLoginCallback onLoginCallback = this.callback;
        if (onLoginCallback != null) {
            onLoginCallback.onLoginSuccess(i);
        }
    }
}
