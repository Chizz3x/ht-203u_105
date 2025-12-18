package com.hti.xtherm.hti160203u.helper;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;

import java.util.HashMap;

/* loaded from: classes.dex */
public class UsbHelper {
    private static final String ACTION_USB_PERMISSION = "com.hti.xtherm.usb_PERMISSION";
    private Context context;
    private OnUsbCallback on_usb_callback;
    private UsbManager usbManager;
    private boolean register_permission_receiver = false;
    private BroadcastReceiver usb_plug_receiver = new BroadcastReceiver() { // from class: com.hti.xtherm.hti160203u.helper.UsbHelper.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.hardware.usb.action.USB_DEVICE_DETACHED")) {
                Alog.e("设备移除", new Object[0]);
                UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                if (usbDevice != null && UsbHelper.this.check_f2_device(usbDevice) && UsbHelper.this.on_usb_callback.on_usb_using()) {
                    UsbHelper.this.on_usb_callback.on_usb_detached(usbDevice);
                }
            }
        }
    };
    private BroadcastReceiver usb_permission_receiver = new BroadcastReceiver() { // from class: com.hti.xtherm.hti160203u.helper.UsbHelper.2
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Alog.e("申请设备权限", new Object[0]);
            if (action.equals(UsbHelper.ACTION_USB_PERMISSION)) {
                synchronized (this) {
                    UsbHelper.this.context.unregisterReceiver(UsbHelper.this.usb_permission_receiver);
                    UsbHelper.this.register_permission_receiver = false;
                    UsbDevice usbDevice = (UsbDevice) intent.getParcelableExtra("device");
                    if (usbDevice != null && UsbHelper.this.check_f2_device(usbDevice)) {
                        if (intent.getBooleanExtra("permission", false)) {
                            UsbHelper.this.on_usb_callback.on_usb_attached(usbDevice);
                            Alog.i("用户允许了权限", new Object[0]);
                        } else {
                            Alog.e("用户拒绝了权限", new Object[0]);
                            if (UsbHelper.this.has_target_device(usbDevice)) {
                                UsbHelper.this.request_device_permission(usbDevice);
                            } else {
                                Alog.e("设备已经移除", new Object[0]);
                            }
                        }
                    } else {
                        Alog.e("usb设备为空", new Object[0]);
                    }
                }
            }
        }
    };

    public interface OnUsbCallback {
        void on_usb_attached(UsbDevice usbDevice);

        void on_usb_detached(UsbDevice usbDevice);

        boolean on_usb_using();
    }

    public UsbHelper(Context context) {
        this.context = context;
        this.usbManager = (UsbManager) context.getSystemService("usb");
    }

    public void register_plug_event(OnUsbCallback onUsbCallback) {
        this.on_usb_callback = onUsbCallback;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        if (Build.VERSION.SDK_INT >= 34) {
            this.context.registerReceiver(this.usb_plug_receiver, intentFilter, 2);
        } else {
            this.context.registerReceiver(this.usb_plug_receiver, intentFilter);
        }
        search_hcusb_devices();
    }

    public void unregister_plug_event() {
        this.on_usb_callback = null;
        this.context.unregisterReceiver(this.usb_plug_receiver);
        if (this.register_permission_receiver) {
            this.context.unregisterReceiver(this.usb_permission_receiver);
        }
    }

    public void search_hcusb_devices() {
        for (UsbDevice usbDevice : this.usbManager.getDeviceList().values()) {
            if (check_f2_device(usbDevice)) {
                request_device_permission(usbDevice);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean has_target_device(UsbDevice usbDevice) {
        HashMap<String, UsbDevice> deviceList = this.usbManager.getDeviceList();
        if (deviceList != null && deviceList.size() > 0) {
            for (UsbDevice usbDevice2 : deviceList.values()) {
                if (usbDevice2.getProductId() == usbDevice.getProductId() && usbDevice2.getVendorId() == usbDevice.getVendorId()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void request_device_permission(UsbDevice usbDevice) {
        if (usbDevice == null) {
            return;
        }
        if (this.usbManager.hasPermission(usbDevice)) {
            this.on_usb_callback.on_usb_attached(usbDevice);
            Alog.i("已获取了设备的权限", new Object[0]);
            return;
        }
        if (Build.VERSION.SDK_INT >= 34) {
            this.context.registerReceiver(this.usb_permission_receiver, new IntentFilter(ACTION_USB_PERMISSION), 2);
        } else {
            this.context.registerReceiver(this.usb_permission_receiver, new IntentFilter(ACTION_USB_PERMISSION));
        }
        this.register_permission_receiver = true;
        Alog.e("注册权限广播", new Object[0]);
        this.usbManager.requestPermission(usbDevice, PendingIntent.getBroadcast(this.context, 0, new Intent(ACTION_USB_PERMISSION), 301989888));
        Alog.e("申请USB权限", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean check_f2_device(UsbDevice usbDevice) {
        int vendorId = usbDevice.getVendorId();
        int productId = usbDevice.getProductId();
        if (vendorId == 11231 && productId == 258) {
            return true;
        }
        return vendorId == 11231 && productId == 257;
    }
}
