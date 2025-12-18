package com.hcusbsdk.Interface;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.util.Log;
import com.bumptech.glide.load.Key;
import com.hcusbsdk.jna.HCUSBSDK;
import com.hcusbsdk.jna.HCUSBSDKByJNA;
import com.hcusbsdk.jni.HCUSBSDKByJNI;
import com.hcusbsdk.jni.StreamCallBack_JNI;
import com.hcusbsdk.jni.USB_COMMON_COND;
import com.sun.jna.Pointer;
import java.io.UnsupportedEncodingException;

import ai.onnxruntime.OrtException;
import kotlin.UByte;

/* loaded from: classes.dex */
public class JavaInterface {
    public static final int CLOSE_LEVEL = 0;
    public static final int DEBUG_LEVEL = 2;
    public static final int ERROR_LEVEL = 1;
    public static final int FINGER_PRINT_LEN = 1024;
    public static final int INFO_LEVEL = 3;
    public static final int MAX_DEVICE_NAME_LEN = 32;
    public static final int MAX_DEVICE_NUM = 128;
    public static final int MAX_DIAGNOSED_DATA_SIZE = 1048576;
    public static final int MAX_EXPERT_REGIONS = 21;
    public static final int MAX_JEPG_DATA_SIZE = 204800;
    public static final int MAX_MANUFACTURE_LEN = 32;
    public static final int MAX_PASSWORD_LEN = 16;
    public static final int MAX_REGION_POINT_NUM = 10;
    public static final int MAX_ROI_REGIONS = 10;
    public static final int MAX_SERIAL_NUM_LEN = 48;
    public static final int MAX_TEMPERATURE_NUM = 4;
    public static final int MAX_THERMAL_REGIONS = 10;
    public static final int MAX_USERNAME_LEN = 32;
    public static final int PIC_LEN = 1024;
    public static final int USB_CAPTURE_FINGER_PRINT = 1028;
    public static final int USB_CPU_CARD_ENCRYPT_CFG = 1030;
    public static final int USB_CTRL_M1_BLOCK_TO_REG = 1012;
    public static final int USB_CTRL_M1_REG_TO_BLOCK = 1013;
    public static final int USB_CTRL_RESET_RFC = 1002;
    public static final int USB_CTRL_STOP_CARD_OPER = 1005;
    public static final int USB_DETECT_CARD = 1024;
    public static final int USB_GET_ACTIVATE_CARD = 1004;
    public static final int USB_GET_AUDIO_BT_MAC = 4024;
    public static final int USB_GET_AUDIO_BT_STATUS = 4025;
    public static final int USB_GET_AUDIO_CAP = 3002;
    public static final int USB_GET_AUDIO_DETECT = 4026;
    public static final int USB_GET_AUDIO_DEVICE_DELAY = 4021;
    public static final int USB_GET_AUDIO_DUMP_DATA = 4036;
    public static final int USB_GET_AUDIO_EFFICT_DETECT = 4028;
    public static final int USB_GET_AUDIO_FAC_TEST = 4030;
    public static final int USB_GET_AUDIO_INPUT_OUTPUT_CHANNELINFO = 4047;
    public static final int USB_GET_AUDIO_IN_STATUS = 4014;
    public static final int USB_GET_AUDIO_IN_VL = 4016;
    public static final int USB_GET_AUDIO_OUT_STATUS = 4015;
    public static final int USB_GET_AUDIO_OUT_VL = 4017;
    public static final int USB_GET_AUDIO_POE_LINK_STATUS = 4050;
    public static final int USB_GET_AUDIO_PROCESS_LINE_CFG = 4048;
    public static final int USB_GET_AUDIO_RECOG_RSLT = 4034;
    public static final int USB_GET_BLACK_BODY = 2042;
    public static final int USB_GET_BODYTEMP_COMPENSATION = 2044;
    public static final int USB_GET_CARD_ISSUE_VERSION = 1001;
    public static final int USB_GET_CERTIFICATE_ADD_ADDR_INFO = 1021;
    public static final int USB_GET_CERTIFICATE_INFO = 1020;
    public static final int USB_GET_CERTIFICATE_MAC = 1022;
    public static final int USB_GET_CPU_CARD_PACK = 1019;
    public static final int USB_GET_CPU_CARD_RESET = 1017;
    public static final int USB_GET_DOUBLE_LIGHTS_CORRECT_POINTS_CTRL = 2052;
    public static final int USB_GET_ENVIROTEMPERATURE_CORRECT = 2063;
    public static final int USB_GET_EXPERT_CORRECTION_PARAM = 2058;
    public static final int USB_GET_EXTERNAL_DEV_INFO = 1026;
    public static final int USB_GET_FINGER_PRINT_CONTRAST_RESULT = 1029;
    public static final int USB_GET_IC_CARD_NO = 1023;
    public static final int USB_GET_IMAGE_BRIGHTNESS = 2018;
    public static final int USB_GET_IMAGE_CONTRAST = 2020;
    public static final int USB_GET_IMAGE_ENHANCEMENT = 2026;
    public static final int USB_GET_IMAGE_VIDEO_ADJUST = 2028;
    public static final int USB_GET_IMAGE_VIDEO_SVC_MULTIPLE_STREAM = 4052;
    public static final int USB_GET_JPEGPIC_WITH_APPENDDATA = 2046;
    public static final int USB_GET_M1_MIFARE_PACK = 1015;
    public static final int USB_GET_M1_READ_BLOCK = 1007;
    public static final int USB_GET_P2P_PARAM = 2048;
    public static final int USB_GET_PRIVACY = 4042;
    public static final int USB_GET_ROI_MAX_TEMPERATURE_SEARCH = 2047;
    public static final int USB_GET_SYSTEM_DEVICE_CAPABILITIES = 4051;
    public static final int USB_GET_SYSTEM_DEVICE_INFO = 2011;
    public static final int USB_GET_SYSTEM_DEVICE_STATUS_DATA = 4006;
    public static final int USB_GET_SYSTEM_DIAGNOSED_DATA = 2024;
    public static final int USB_GET_SYSTEM_DIAGNOSED_DATA_EX = 2050;
    public static final int USB_GET_SYSTEM_ENCRYPT_STATUS = 4002;
    public static final int USB_GET_SYSTEM_HARDWARE_SERVER = 2014;
    public static final int USB_GET_SYSTEM_INDICATORLIGHT = 4003;
    public static final int USB_GET_SYSTEM_LOCALTIME = 2016;
    public static final int USB_GET_SYSTEM_LOG_DATA = 4005;
    public static final int USB_GET_TEMPERATURE_CORRECT = 2040;
    public static final int USB_GET_THERMAL_ALG_VERSION = 2036;
    public static final int USB_GET_THERMAL_STREAM_PARAM = 2038;
    public static final int USB_GET_THERMOMETRY_BASIC_PARAM = 2030;
    public static final int USB_GET_THERMOMETRY_CALIBRATION_FILE = 2054;
    public static final int USB_GET_THERMOMETRY_EXPERT_REGIONS = 2056;
    public static final int USB_GET_THERMOMETRY_MODE = 2032;
    public static final int USB_GET_THERMOMETRY_REGIONS = 2034;
    public static final int USB_GET_THERMOMETRY_RISE_SETTINGS = 2061;
    public static final int USB_GET_VIDEO_BACKLIGHTCOMPENSATION = 3031;
    public static final int USB_GET_VIDEO_BRIGHTNESS = 3015;
    public static final int USB_GET_VIDEO_CAP = 3001;
    public static final int USB_GET_VIDEO_CONTRAST = 3017;
    public static final int USB_GET_VIDEO_GAIN = 3033;
    public static final int USB_GET_VIDEO_PAN = 3037;
    public static final int USB_GET_VIDEO_PARAM = 3003;
    public static final int USB_GET_VIDEO_SATURATION = 3021;
    public static final int USB_GET_VIDEO_SHARPNESS = 3023;
    public static final int USB_GET_VIDEO_TILT = 3039;
    public static final int USB_GET_VIDEO_ZOOM = 3043;
    public static final int USB_INVALID_CHANNEL = -1;
    public static final int USB_INVALID_USER_ID = -1;
    public static final int USB_SET_AUDIO_AECSP = 4020;
    public static final int USB_SET_AUDIO_AGC_CONFIG = 4032;
    public static final int USB_SET_AUDIO_AMER = 4018;
    public static final int USB_SET_AUDIO_AO_VA = 4019;
    public static final int USB_SET_AUDIO_BT = 4023;
    public static final int USB_SET_AUDIO_DETECT = 4027;
    public static final int USB_SET_AUDIO_DEVICE_DELAY = 4022;
    public static final int USB_SET_AUDIO_ECHO = 4044;
    public static final int USB_SET_AUDIO_EFFICT_DETECT = 4029;
    public static final int USB_SET_AUDIO_FAC_TEST = 4031;
    public static final int USB_SET_AUDIO_PROCESS_LINE_CFG = 4049;
    public static final int USB_SET_AUDIO_RECOG_RSLT = 4035;
    public static final int USB_SET_AUDIO_REDUCE_NOISE = 4033;
    public static final int USB_SET_AUDIO_SIGNAL_TRANS = 4046;
    public static final int USB_SET_BEEP_AND_FLICKER = 1000;
    public static final int USB_SET_BLACK_BODY = 2043;
    public static final int USB_SET_BODYTEMP_COMPENSATION = 2045;
    public static final int USB_SET_CARD_PARAM = 1016;
    public static final int USB_SET_CARD_PROTO = 1003;
    public static final int USB_SET_CPU_CARD_PACK = 1018;
    public static final int USB_SET_DOUBLE_LIGHTS_CORRECT_POINTS_CTRL = 2053;
    public static final int USB_SET_ENVIROTEMPERATURE_CORRECT = 2064;
    public static final int USB_SET_EXPERT_CORRECTION_PARAM = 2059;
    public static final int USB_SET_FINGER_PRINT_OPER_PARAM = 1027;
    public static final int USB_SET_IDENTITY_INFO = 1025;
    public static final int USB_SET_IMAGE_BACKGROUND_CORRECT = 2023;
    public static final int USB_SET_IMAGE_BRIGHTNESS = 2019;
    public static final int USB_SET_IMAGE_CONTRAST = 2021;
    public static final int USB_SET_IMAGE_ENHANCEMENT = 2027;
    public static final int USB_SET_IMAGE_MANUAL_CORRECT = 2025;
    public static final int USB_SET_IMAGE_VIDEO_ADJUST = 2029;
    public static final int USB_SET_IMAGE_VIDEO_LOGO_CFG = 4009;
    public static final int USB_SET_IMAGE_VIDEO_LOGO_SWITCH = 4008;
    public static final int USB_SET_IMAGE_VIDEO_MULTIPLE_IFRAME = 4013;
    public static final int USB_SET_IMAGE_VIDEO_MULTIPLE_STREAM = 4012;
    public static final int USB_SET_IMAGE_VIDEO_OSD_CFG = 4011;
    public static final int USB_SET_IMAGE_VIDEO_OSD_SWITCH = 4010;
    public static final int USB_SET_IMAGE_WDR = 4007;
    public static final int USB_SET_M1_BLOCK_ADD_VALUE = 1010;
    public static final int USB_SET_M1_BLOCK_MINUS_VALUE = 1011;
    public static final int USB_SET_M1_MIFARE_PACK = 1014;
    public static final int USB_SET_M1_MODIFY_SCB = 1009;
    public static final int USB_SET_M1_PWD_VERIFY = 1006;
    public static final int USB_SET_M1_SECTION_ENCRYPT = 1031;
    public static final int USB_SET_M1_WRITE_BLOCK = 1008;
    public static final int USB_SET_P2P_PARAM = 2049;
    public static final int USB_SET_PRIVACY = 4043;
    public static final int USB_SET_PTZ_AUTO_TRACK_SENSITIVITY = 4041;
    public static final int USB_SET_PTZ_PRESET_CALL = 4039;
    public static final int USB_SET_PTZ_PRESET_CFG = 4038;
    public static final int USB_SET_PTZ_PRESET_DELETE = 4040;
    public static final int USB_SET_PTZ_TRACK_MODE = 4037;
    public static final int USB_SET_SYSTEM_ENCRYPT_DATA = 4001;
    public static final int USB_SET_SYSTEM_HARDWARE_SERVER = 2015;
    public static final int USB_SET_SYSTEM_INDICATORLIGHT = 4004;
    public static final int USB_SET_SYSTEM_LOCALTIME = 2017;
    public static final int USB_SET_SYSTEM_REBOOT = 2012;
    public static final int USB_SET_SYSTEM_RESET = 2013;
    public static final int USB_SET_TEMPERATURE_CORRECT = 2041;
    public static final int USB_SET_THERMAL_STREAM_PARAM = 2039;
    public static final int USB_SET_THERMOMETRY_BASIC_PARAM = 2031;
    public static final int USB_SET_THERMOMETRY_CALIBRATION_FILE = 2055;
    public static final int USB_SET_THERMOMETRY_EXPERT_REGIONS = 2057;
    public static final int USB_SET_THERMOMETRY_MODE = 2033;
    public static final int USB_SET_THERMOMETRY_REGIONS = 2035;
    public static final int USB_SET_THERMOMETRY_RISE_SETTINGS = 2062;
    public static final int USB_SET_VIDEO_BACKLIGHTCOMPENSATION = 3032;
    public static final int USB_SET_VIDEO_BRIGHTNESS = 3016;
    public static final int USB_SET_VIDEO_CONTRAST = 3018;
    public static final int USB_SET_VIDEO_GAIN = 3034;
    public static final int USB_SET_VIDEO_PAN = 3038;
    public static final int USB_SET_VIDEO_PARAM = 3004;
    public static final int USB_SET_VIDEO_SATURATION = 3022;
    public static final int USB_SET_VIDEO_SHARPNESS = 3024;
    public static final int USB_SET_VIDEO_TILT = 3040;
    public static final int USB_SET_VIDEO_ZOOM = 3044;
    public static final int USB_START_EXPERT_CORRECTION = 2060;
    public static final int USB_STREAM_H264 = 102;
    public static final int USB_STREAM_MJPEG = 101;
    public static final int USB_STREAM_PCM = 1;
    public static final int USB_STREAM_PS_H264 = 201;
    public static final int USB_STREAM_PS_MJPEG = 202;
    public static final int USB_STREAM_PS_YUY2 = 203;
    public static final int USB_STREAM_UNKNOW = 0;
    public static final int USB_STREAM_YUY2 = 103;
    public static final int WORD_LEN = 256;
    private static JavaInterface m_pJavaInterface;
    public FStreamCallBack[] m_fnStreamCallBack = new FStreamCallBack[1000];
    private int m_iEnumType = 1;
    private boolean m_bInit = false;
    public HCUSBSDKByJNA.FStreamCallBack m_fnStreamCallBack_jna = new FnStreamCallBack();
    public StreamCallBack_JNI m_fnStreamCallBack_jni = new StreamCallBack_JNI() { // from class: com.hcusbsdk.Interface.JavaInterface.1
        @Override // com.hcusbsdk.jni.StreamCallBack_JNI
        public void fStreamCallback_JNI(int i, com.hcusbsdk.jni.USB_FRAME_INFO usb_frame_info) throws OrtException {
            USB_FRAME_INFO usb_frame_info2 = new USB_FRAME_INFO();
            usb_frame_info2.nStamp = usb_frame_info.nStamp;
            usb_frame_info2.dwStreamType = usb_frame_info.dwStreamType;
            usb_frame_info2.dwWidth = usb_frame_info.dwWidth;
            usb_frame_info2.dwHeight = usb_frame_info.dwHeight;
            usb_frame_info2.dwFrameRate = usb_frame_info.dwFrameRate;
            usb_frame_info2.dwFrameType = usb_frame_info.dwFrameType;
            usb_frame_info2.dwDataType = usb_frame_info.dwDataType;
            usb_frame_info2.nFrameNum = usb_frame_info.nFrameNum;
            usb_frame_info2.dwBufSize = usb_frame_info.dwBufSize;
            System.arraycopy(usb_frame_info.pBuf, 0, usb_frame_info2.pBuf, 0, usb_frame_info.dwBufSize);
//            Log.i("[JavaInterface]", "fStreamCallback_JNI Success! nStamp:" + usb_frame_info2.nStamp + " dwStreamType:" + usb_frame_info2.dwStreamType + " dwWidth:" + usb_frame_info2.dwWidth + " dwHeight:" + usb_frame_info2.dwHeight + " dwFrameRate:" + usb_frame_info2.dwFrameRate + " dwFrameType:" + usb_frame_info2.dwFrameType + " dwDataType:" + usb_frame_info2.dwDataType + " nFrameNum:" + usb_frame_info2.nFrameNum + " dwBufSize:" + usb_frame_info2.dwBufSize);
            JavaInterface.this.m_fnStreamCallBack[i].invoke(i, usb_frame_info2);
//            Log.i("[JavaInterface]", "fStreamCallback_JNI OUT!");
        }
    };

    public static JavaInterface getInstance() {
        if (m_pJavaInterface == null) {
            synchronized (JavaInterface.class) {
                m_pJavaInterface = new JavaInterface();
            }
        }
        return m_pJavaInterface;
    }

    public boolean USB_Init() {
//        HCUSBSDKByJNI.getInstance();
//        if (HCUSBSDK.getInstance().USB_Init()) {
//            Log.i("[JavaInterface]", "USB_Init Success!");
//            this.m_bInit = true;
//            return true;
//        }
//        Log.e("[JavaInterface]", "USB_Init Failed!");
//        return false;

        try {
            HCUSBSDKByJNA sdk = HCUSBSDK.getInstance();
            if (sdk.USB_Init()) {
                Log.i("JavaInterface", "USB_Init Success!");
                m_bInit = true;
                return true;
            } else {
                Log.e("JavaInterface", "USB_Init Failed!");
                return false;
            }
        } catch (UnsatisfiedLinkError e) {
            Log.e("JavaInterface", "HCUSBSDK native library load error", e);
            return false;
        }
    }

    public boolean USB_Cleanup() {
        if (HCUSBSDK.getInstance().USB_Cleanup()) {
            Log.i("[JavaInterface]", "USB_Cleanup Success!");
            this.m_bInit = false;
            return true;
        }
        Log.e("[JavaInterface]", "USB_Cleanup Failed!");
        return false;
    }

    public int USB_GetSDKVersion() {
        return HCUSBSDK.getInstance().USB_GetSDKVersion();
    }

    public int USB_GetLastError() {
        return HCUSBSDK.getInstance().USB_GetLastError();
    }

    public boolean USB_SetLogToFile(int i, String str, int i2) {
        if (HCUSBSDK.getInstance().USB_SetLogToFile(i, str, i2)) {
            Log.i("[JavaInterface]", "USB_SetLogToFile Success!");
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetLogToFile Failed!");
        return false;
    }

    private int USB_GetDeviceCount() {
        this.m_iEnumType = 0;
        int iUSB_GetDeviceCount = HCUSBSDK.getInstance().USB_GetDeviceCount();
        if (iUSB_GetDeviceCount >= 0) {
            Log.i("[JavaInterface]", "USB_GetDeviceCount Device count is :" + iUSB_GetDeviceCount);
        } else {
            Log.e("[JavaInterface]", "USB_GetDeviceCount failed! error:" + USB_GetLastError());
        }
        return iUSB_GetDeviceCount;
    }

    public int USB_GetDeviceCount(Context context) {
        this.m_iEnumType = 1;
        int iEnumDevice = new EnumerateDevice().EnumDevice(context);
        if (iEnumDevice >= 0) {
            Log.i("[JavaInterface]", "USB_GetDeviceCount Device count is :" + iEnumDevice);
        } else {
            Log.e("[JavaInterface]", "USB_GetDeviceCount failed! error:" + USB_GetLastError());
        }
        return iEnumDevice;
    }

    private boolean USB_EnumDevices_C(int i, USB_DEVICE_INFO[] usb_device_infoArr) {
        if (i <= 0) {
            Log.e("[JavaInterface]", "USB_EnumDevices failed! Device count is :" + i);
            return false;
        }
        if (usb_device_infoArr == null || usb_device_infoArr.length == 0) {
            Log.e("[JavaInterface]", "USB_EnumDevices failed! struUsbDeviceInfo == null");
            return false;
        }
        HCUSBSDKByJNA.USB_DEVICE_INFO[] usb_device_infoArr2 = (HCUSBSDKByJNA.USB_DEVICE_INFO[]) new HCUSBSDKByJNA.USB_DEVICE_INFO().toArray(i);
        if (!HCUSBSDK.getInstance().USB_EnumDevices(i, usb_device_infoArr2)) {
            Log.e("[JavaInterface]", "USB_EnumDevices failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            usb_device_infoArr2[i2].read();
            usb_device_infoArr[i2].dwIndex = usb_device_infoArr2[i2].dwIndex;
            usb_device_infoArr[i2].dwVID = usb_device_infoArr2[i2].dwVID;
            usb_device_infoArr[i2].dwPID = usb_device_infoArr2[i2].dwPID;
            try {
                usb_device_infoArr[i2].szManufacturer = new String(usb_device_infoArr2[i2].szManufacturer, Key.STRING_CHARSET_NAME);
                usb_device_infoArr[i2].szDeviceName = new String(usb_device_infoArr2[i2].szDeviceName, Key.STRING_CHARSET_NAME);
                usb_device_infoArr[i2].szSerialNumber = new String(usb_device_infoArr2[i2].szSerialNumber, Key.STRING_CHARSET_NAME);
            } catch (UnsupportedEncodingException unused) {
                usb_device_infoArr[i2].szManufacturer = new String();
                usb_device_infoArr[i2].szDeviceName = new String();
                usb_device_infoArr[i2].szSerialNumber = new String();
            }
            usb_device_infoArr[i2].byHaveAudio = usb_device_infoArr2[i2].byHaveAudio;
            Log.i("[JavaInterface]", "USB_EnumDevices Device info is dwIndex:" + usb_device_infoArr2[i2].dwIndex + " dwVID:" + usb_device_infoArr2[i2].dwVID + " dwPID:" + usb_device_infoArr2[i2].dwPID + " szManufacturer:" + new String(usb_device_infoArr2[i2].szManufacturer) + " szDeviceName:" + new String(usb_device_infoArr2[i2].szDeviceName) + " szSerialNumber:" + new String(usb_device_infoArr2[i2].szSerialNumber) + " byHaveAudio:" + ((int) usb_device_infoArr2[i2].byHaveAudio));
        }
        return true;
    }

    public boolean USB_EnumDevices_Java(int i, USB_DEVICE_INFO[] usb_device_infoArr) {
        if (i <= 0) {
            Log.e("[JavaInterface]", "USB_EnumDevices failed! Device count is :" + i);
            return false;
        }
        if (usb_device_infoArr == null || usb_device_infoArr.length == 0) {
            Log.e("[JavaInterface]", "USB_EnumDevices failed! struUsbDeviceInfo == null");
            return false;
        }
        int i2 = 0;
        for (UsbDevice usbDevice : EnumerateDevice.m_deviceList.values()) {
            if (i <= i2 || i2 >= 128) {
                return true;
            }
            int i3 = i2 + 1;
            usb_device_infoArr[i2].dwIndex = i3;
            usb_device_infoArr[i2].dwVID = usbDevice.getVendorId();
            usb_device_infoArr[i2].dwPID = usbDevice.getProductId();
            usb_device_infoArr[i2].szManufacturer = usbDevice.getManufacturerName();
            usb_device_infoArr[i2].szDeviceName = usbDevice.getDeviceName();
            usb_device_infoArr[i2].szSerialNumber = usbDevice.getSerialNumber();
            usb_device_infoArr[i2].byHaveAudio = (byte) 0;
            usb_device_infoArr[i2].dwFd = EnumerateDevice.m_fdList[i2];
            Log.i("[JavaInterface]", "USB_EnumDevices Device info is dwIndex:" + usb_device_infoArr[i2].dwIndex + " dwVID:" + usb_device_infoArr[i2].dwVID + " dwPID:" + usb_device_infoArr[i2].dwPID + " szManufacturer:" + usb_device_infoArr[i2].szManufacturer + " szDeviceName:" + usb_device_infoArr[i2].szDeviceName + " szSerialNumber:" + usb_device_infoArr[i2].szSerialNumber + " byHaveAudio:" + ((int) usb_device_infoArr[i2].byHaveAudio) + " dwFd:" + usb_device_infoArr[i2].dwFd);
            i2 = i3;
        }
        return true;
    }

    public boolean USB_EnumDevices(int i, USB_DEVICE_INFO[] usb_device_infoArr) {
        if (!this.m_bInit) {
            Log.e("[JavaInterface]", "USB_EnumDevices failed! not init");
            return false;
        }
        if (this.m_iEnumType == 0) {
            return USB_EnumDevices_C(i, usb_device_infoArr);
        }
        return USB_EnumDevices_Java(i, usb_device_infoArr);
    }

    public int USB_Login(USB_USER_LOGIN_INFO usb_user_login_info, USB_DEVICE_REG_RES usb_device_reg_res) {
        if (usb_user_login_info == null || usb_device_reg_res == null) {
            Log.e("[JavaInterface]", "USB_Login failed! struUsbDeviceInfo == null || struDeviceRegRes == null");
            return -1;
        }
        HCUSBSDKByJNA.USB_USER_LOGIN_INFO usb_user_login_info2 = new HCUSBSDKByJNA.USB_USER_LOGIN_INFO();
        usb_user_login_info2.dwSize = usb_user_login_info2.size();
        usb_user_login_info2.dwTimeout = usb_user_login_info.dwTimeout;
        usb_user_login_info2.dwDevIndex = usb_user_login_info.dwDevIndex;
        usb_user_login_info2.dwVID = usb_user_login_info.dwVID;
        usb_user_login_info2.dwPID = usb_user_login_info.dwPID;
        usb_user_login_info2.byLoginMode = usb_user_login_info.byLoginMode;
        usb_user_login_info2.dwFd = usb_user_login_info.dwFd;
        if (usb_user_login_info.szUserName != null) {
            try {
                System.arraycopy(usb_user_login_info.szUserName.getBytes(Key.STRING_CHARSET_NAME), 0, usb_user_login_info2.szUserName, 0, usb_user_login_info.szUserName.length());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return -1;
            }
        }
        if (usb_user_login_info.szPassword != null) {
            try {
                System.arraycopy(usb_user_login_info.szPassword.getBytes(Key.STRING_CHARSET_NAME), 0, usb_user_login_info2.szPassword, 0, usb_user_login_info.szPassword.length());
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
                return -1;
            }
        }
        if (usb_user_login_info.szSerialNumber != null) {
            try {
                System.arraycopy(usb_user_login_info.szSerialNumber.getBytes(Key.STRING_CHARSET_NAME), 0, usb_user_login_info2.szSerialNumber, 0, usb_user_login_info.szSerialNumber.length());
            } catch (UnsupportedEncodingException e3) {
                e3.printStackTrace();
                return -1;
            }
        }
        usb_user_login_info2.write();
        HCUSBSDKByJNA.USB_DEVICE_REG_RES usb_device_reg_res2 = new HCUSBSDKByJNA.USB_DEVICE_REG_RES();
        usb_device_reg_res2.dwSize = usb_device_reg_res2.size();
        usb_device_reg_res2.write();
        int iUSB_Login = HCUSBSDK.getInstance().USB_Login(usb_user_login_info2.getPointer(), usb_device_reg_res2.getPointer());
        if (iUSB_Login != -1) {
            Log.i("[JavaInterface]", "USB_Login Success! iUserID:" + iUSB_Login + " dwDevIndex:" + usb_user_login_info.dwDevIndex + " dwVID:" + usb_user_login_info.dwVID + " dwPID:" + usb_user_login_info.dwPID + " dwFd:" + usb_user_login_info.dwFd);
        } else {
            Log.e("[JavaInterface]", "USB_Login failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwDevIndex:" + usb_user_login_info.dwDevIndex + " dwVID:" + usb_user_login_info.dwVID + " dwPID:" + usb_user_login_info.dwPID + " dwFd:" + usb_user_login_info.dwFd);
        }
        usb_device_reg_res2.read();
        try {
            usb_device_reg_res.szDeviceName = new String(usb_device_reg_res2.szDeviceName, Key.STRING_CHARSET_NAME);
            usb_device_reg_res.szSerialNumber = new String(usb_device_reg_res2.szSerialNumber, Key.STRING_CHARSET_NAME);
        } catch (UnsupportedEncodingException unused) {
            usb_device_reg_res.szDeviceName = new String();
            usb_device_reg_res.szSerialNumber = new String();
        }
        usb_device_reg_res.dwSoftwareVersion = usb_device_reg_res2.dwSoftwareVersion;
        usb_device_reg_res.wYear = usb_device_reg_res2.wYear;
        usb_device_reg_res.byMonth = usb_device_reg_res2.byMonth;
        usb_device_reg_res.byDay = usb_device_reg_res2.byDay;
        usb_device_reg_res.byRetryLoginTimes = usb_device_reg_res2.byRetryLoginTimes;
        usb_device_reg_res.dwSurplusLockTime = usb_device_reg_res2.dwSurplusLockTime;
        return iUSB_Login;
    }

    public boolean USB_Logout(int i) {
        if (HCUSBSDK.getInstance().USB_Logout(i)) {
            Log.i("[JavaInterface]", "USB_Logout Success! lUserID:" + i);
            return true;
        }
        Log.e("[JavaInterface]", "USB_Logout failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
        return false;
    }

    class FnStreamCallBack implements HCUSBSDKByJNA.FStreamCallBack {
        FnStreamCallBack() {
        }

        @Override // com.hcusbsdk.jna.HCUSBSDKByJNA.FStreamCallBack
        public void invoke(int i, Pointer pointer, Pointer pointer2) throws OrtException {
            HCUSBSDKByJNA.USB_FRAME_INFO usb_frame_info = new HCUSBSDKByJNA.USB_FRAME_INFO(pointer);
            usb_frame_info.read();
            USB_FRAME_INFO usb_frame_info2 = new USB_FRAME_INFO();
            usb_frame_info2.nStamp = usb_frame_info.nStamp;
            usb_frame_info2.dwStreamType = usb_frame_info.dwStreamType;
            usb_frame_info2.dwWidth = usb_frame_info.dwWidth;
            usb_frame_info2.dwHeight = usb_frame_info.dwHeight;
            usb_frame_info2.dwFrameRate = usb_frame_info.dwFrameRate;
            usb_frame_info2.dwFrameType = usb_frame_info.dwFrameType;
            usb_frame_info2.dwDataType = usb_frame_info.dwDataType;
            usb_frame_info2.nFrameNum = usb_frame_info.nFrameNum;
            usb_frame_info2.dwBufSize = usb_frame_info.dwBufSize;
            usb_frame_info2.pBuf = usb_frame_info.pBuf.getByteArray(0L, usb_frame_info.dwBufSize);
            Log.i("[JavaInterface]", "FStreamCallBack Success! nFrameNum:" + usb_frame_info.nFrameNum + " dwBufSize:" + usb_frame_info.dwBufSize);
            JavaInterface.this.m_fnStreamCallBack[i].invoke(i, usb_frame_info2);
            Log.i("[JavaInterface]", "FStreamCallBack OUT!");
        }
    }

    private int USB_StartStreamCallback_jna(int i, USB_STREAM_CALLBACK_PARAM usb_stream_callback_param) {
        if (i == -1) {
            Log.e("[JavaInterface]", "USB_StartStreamCallback_jna failed! lUserID: -1");
            return -1;
        }
        this.m_fnStreamCallBack[i] = usb_stream_callback_param.fnStreamCallBack;
        HCUSBSDKByJNA.USB_STREAM_CALLBACK_PARAM usb_stream_callback_param2 = new HCUSBSDKByJNA.USB_STREAM_CALLBACK_PARAM();
        usb_stream_callback_param2.dwSize = usb_stream_callback_param2.size();
        usb_stream_callback_param2.dwStreamType = usb_stream_callback_param.dwStreamType;
        usb_stream_callback_param2.pUser = Pointer.NULL;
        usb_stream_callback_param2.fnStreamCallBack = this.m_fnStreamCallBack_jna;
        usb_stream_callback_param2.write();
        int iUSB_StartStreamCallback = HCUSBSDK.getInstance().USB_StartStreamCallback(i, usb_stream_callback_param2.getPointer());
        if (iUSB_StartStreamCallback != -1) {
            Log.i("[JavaInterface]", "USB_StartStreamCallback_jna Success! lUserID:" + i + " iHandle:" + iUSB_StartStreamCallback);
        } else {
            Log.e("[JavaInterface]", "USB_StartStreamCallback_jna failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
        }
        return iUSB_StartStreamCallback;
    }

    private int USB_StartStreamCallback_jni(int i, USB_STREAM_CALLBACK_PARAM usb_stream_callback_param) {
        if (i == -1) {
            Log.e("[JavaInterface]", "USB_StartStreamCallback_jni failed! lUserID: -1");
            return -1;
        }
        this.m_fnStreamCallBack[i] = usb_stream_callback_param.fnStreamCallBack;
        com.hcusbsdk.jni.USB_STREAM_CALLBACK_PARAM usb_stream_callback_param2 = new com.hcusbsdk.jni.USB_STREAM_CALLBACK_PARAM();
        usb_stream_callback_param2.dwSize = 0;
        usb_stream_callback_param2.dwStreamType = usb_stream_callback_param.dwStreamType;
        int iUSB_StartStreamCallback = HCUSBSDKByJNI.getInstance().USB_StartStreamCallback(i, usb_stream_callback_param2, this.m_fnStreamCallBack_jni);
        if (iUSB_StartStreamCallback != -1) {
            Log.i("[JavaInterface]", "USB_StartStreamCallback_jni Success! lUserID:" + i + " iHandle:" + iUSB_StartStreamCallback);
        } else {
            Log.e("[JavaInterface]", "USB_StartStreamCallback_jni failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
        }
        return iUSB_StartStreamCallback;
    }

    public int USB_StartStreamCallback(int i, USB_STREAM_CALLBACK_PARAM usb_stream_callback_param) {
        if (usb_stream_callback_param == null) {
            Log.e("[JavaInterface]", "USB_StartStreamCallback failed! struStreamCBParam == null");
            return -1;
        }
        if (usb_stream_callback_param.fnStreamCallBack == null) {
            Log.e("[JavaInterface]", "USB_StartStreamCallback failed! struStreamCBParam.fnStreamCallBack == null");
            return -1;
        }
        return USB_StartStreamCallback_jni(i, usb_stream_callback_param);
    }

    public boolean USB_StopChannel(int i, int i2) {
        if (HCUSBSDKByJNI.getInstance().USB_StopChannel(i, i2)) {
            Log.i("[JavaInterface]", "USB_StopChannel Success! lUserID:" + i + " iHandle:" + i2);
            return true;
        }
        Log.e("[JavaInterface]", "USB_StopChannel failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
        return false;
    }

    public int USB_Upgrade(int i, USB_UPGRADE_INFO usb_upgrade_info) {
        if (usb_upgrade_info == null) {
            Log.e("[JavaInterface]", "USB_Upgrade failed! struUpgradeInfo == null");
            return -1;
        }
        HCUSBSDKByJNA.USB_UPGRADE_COND usb_upgrade_cond = new HCUSBSDKByJNA.USB_UPGRADE_COND();
        usb_upgrade_cond.dwSize = usb_upgrade_cond.size();
        usb_upgrade_cond.byTimeout = (byte) 60;
        usb_upgrade_cond.write();
        int iUSB_Upgrade = HCUSBSDK.getInstance().USB_Upgrade(i, 0, usb_upgrade_info.szFileName, usb_upgrade_cond.getPointer(), usb_upgrade_cond.size());
        if (iUSB_Upgrade > 0) {
            Log.i("[JavaInterface]", "USB_Upgrade Success! lUpgradeHandle:" + iUSB_Upgrade + " szFileName:" + usb_upgrade_info.szFileName);
        } else {
            Log.e("[JavaInterface]", "USB_GetUpgradeState failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " lUpgradeHandle:" + iUSB_Upgrade + " szFileName:" + usb_upgrade_info.szFileName);
        }
        return iUSB_Upgrade;
    }

    public boolean USB_GetUpgradeState(int i, USB_UPGRADE_STATE_INFO usb_upgrade_state_info) {
        if (usb_upgrade_state_info == null) {
            Log.e("[JavaInterface]", "USB_GetUpgradeState failed! struUpgradeState == null");
            return false;
        }
        HCUSBSDKByJNA.USB_UPGRADE_STATE_INFO usb_upgrade_state_info2 = new HCUSBSDKByJNA.USB_UPGRADE_STATE_INFO();
        if (HCUSBSDK.getInstance().USB_GetUpgradeState(i, usb_upgrade_state_info2.getPointer())) {
            usb_upgrade_state_info2.read();
            usb_upgrade_state_info.byState = usb_upgrade_state_info2.byState;
            usb_upgrade_state_info.byProgress = usb_upgrade_state_info2.byProgress;
            Log.i("[JavaInterface]", "USB_GetUpgradeState Success! lUpgradeHandle:" + i + " byState:" + ((int) usb_upgrade_state_info.byState) + " byProgress:" + ((int) usb_upgrade_state_info.byProgress));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetUpgradeState failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " lUpgradeHandle:" + i + " byState:" + ((int) usb_upgrade_state_info.byState) + " byProgress:" + ((int) usb_upgrade_state_info.byProgress));
        return false;
    }

    public boolean USB_CloseUpgradeHandle(int i) {
        if (HCUSBSDK.getInstance().USB_CloseUpgradeHandle(i)) {
            Log.i("[JavaInterface]", "USB_CloseUpgradeHandle Success! lUpgradeHandle:" + i);
            return true;
        }
        Log.e("[JavaInterface]", "USB_CloseUpgradeHandle failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " lUpgradeHandle:" + i);
        return false;
    }

    public boolean USB_GetVideoCap(int i, USB_VIDEO_CAPACITY[] usb_video_capacityArr) {
        if (usb_video_capacityArr == null) {
            Log.e("[JavaInterface]", "USB_GetVideoCap failed! struVideoCap == null");
            return false;
        }
        HCUSBSDKByJNA.USB_VIDEO_CAPACITY[] usb_video_capacityArr2 = (HCUSBSDKByJNA.USB_VIDEO_CAPACITY[]) new HCUSBSDKByJNA.USB_VIDEO_CAPACITY().toArray(10);
        for (HCUSBSDKByJNA.USB_VIDEO_CAPACITY usb_video_capacity : usb_video_capacityArr2) {
            usb_video_capacity.write();
        }
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_video_capacityArr2[0].getPointer();
        usb_config_output_info.dwOutBufferSize = usb_video_capacityArr2.length * usb_video_capacityArr2[0].size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, 3001, Pointer.NULL, usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            for (int i2 = 0; i2 < usb_video_capacityArr2.length && i2 < usb_video_capacityArr.length; i2++) {
                usb_video_capacityArr2[i2].read();
                usb_video_capacityArr[i2].nIndex = usb_video_capacityArr2[i2].nIndex;
                usb_video_capacityArr[i2].nType = usb_video_capacityArr2[i2].nType;
                usb_video_capacityArr[i2].dwWidth = usb_video_capacityArr2[i2].dwWidth;
                usb_video_capacityArr[i2].dwHeight = usb_video_capacityArr2[i2].dwHeight;
                usb_video_capacityArr[i2].lListSize = usb_video_capacityArr2[i2].lListSize;
                usb_video_capacityArr[i2].lFrameRates = usb_video_capacityArr2[i2].lFrameRates;
            }
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_VIDEO_CAP Success!");
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_VIDEO_CAP failed! error:");
        return false;
    }

    public boolean USB_SetVideoParam(int i, USB_VIDEO_PARAM usb_video_param) {
        if (usb_video_param == null) {
            Log.e("[JavaInterface]", "USB_SetVideoParam failed! struVideoParam == null");
            return false;
        }
        HCUSBSDKByJNA.USB_VIDEO_PARAM usb_video_param2 = new HCUSBSDKByJNA.USB_VIDEO_PARAM();
        usb_video_param2.dwVideoFormat = usb_video_param.dwVideoFormat;
        usb_video_param2.dwWidth = usb_video_param.dwWidth;
        usb_video_param2.dwHeight = usb_video_param.dwHeight;
        usb_video_param2.dwFramerate = usb_video_param.dwFramerate;
        usb_video_param2.dwBitrate = usb_video_param.dwBitrate;
        usb_video_param2.dwParamType = usb_video_param.dwParamType;
        usb_video_param2.dwValue = usb_video_param.dwValue;
        usb_video_param2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpInBuffer = usb_video_param2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_video_param2.size();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, 3004, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_VIDEO_PARAM Success! dwVideoFormat:" + usb_video_param.dwVideoFormat + " dwWidth:" + usb_video_param.dwWidth + " dwHeight:" + usb_video_param.dwHeight + " dwFramerate:" + usb_video_param.dwFramerate + " dwBitrate:" + usb_video_param.dwBitrate + " dwParamType:" + usb_video_param.dwParamType + " dwValue:" + usb_video_param.dwValue);
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_VIDEO_PARAM failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwVideoFormat:" + usb_video_param.dwVideoFormat + " dwWidth:" + usb_video_param.dwWidth + " dwHeight:" + usb_video_param.dwHeight + " dwFramerate:" + usb_video_param.dwFramerate + " dwBitrate:" + usb_video_param.dwBitrate + " dwParamType:" + usb_video_param.dwParamType + " dwValue:" + usb_video_param.dwValue);
        return false;
    }

    public boolean USB_GetSysTemDeviceInfo(int i, USB_SYSTEM_DEVICE_INFO usb_system_device_info) {
        if (usb_system_device_info == null) {
            Log.e("[JavaInterface]", "USB_GetSysTemDeviceInfo failed! struSysDevInfo == null");
            return false;
        }
        HCUSBSDKByJNA.USB_SYSTEM_DEVICE_INFO usb_system_device_info2 = new HCUSBSDKByJNA.USB_SYSTEM_DEVICE_INFO();
        usb_system_device_info2.write();
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_system_device_info2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_system_device_info2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, 2011, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_system_device_info2.read();
            try {
                usb_system_device_info.byFirmwareVersion = new String(usb_system_device_info2.byFirmwareVersion, Key.STRING_CHARSET_NAME);
                usb_system_device_info.byEncoderVersion = new String(usb_system_device_info2.byEncoderVersion, Key.STRING_CHARSET_NAME);
                usb_system_device_info.byHardwareVersion = new String(usb_system_device_info2.byHardwareVersion, Key.STRING_CHARSET_NAME);
                usb_system_device_info.byDeviceType = new String(usb_system_device_info2.byDeviceType, Key.STRING_CHARSET_NAME);
                usb_system_device_info.byProtocolVersion = new String(usb_system_device_info2.byProtocolVersion, Key.STRING_CHARSET_NAME);
                usb_system_device_info.bySerialNumber = new String(usb_system_device_info2.bySerialNumber, Key.STRING_CHARSET_NAME);
                usb_system_device_info.bySecondHardwareVersion = new String(usb_system_device_info2.bySecondHardwareVersion, Key.STRING_CHARSET_NAME);
                usb_system_device_info.byModuleID = new String(usb_system_device_info2.byModuleID, Key.STRING_CHARSET_NAME);
                usb_system_device_info.byDeviceID = new String(usb_system_device_info2.byDeviceID, Key.STRING_CHARSET_NAME);
            } catch (UnsupportedEncodingException unused) {
                usb_system_device_info.byFirmwareVersion = new String();
                usb_system_device_info.byEncoderVersion = new String();
                usb_system_device_info.byHardwareVersion = new String();
                usb_system_device_info.byDeviceType = new String();
                usb_system_device_info.byProtocolVersion = new String();
                usb_system_device_info.bySerialNumber = new String();
                usb_system_device_info.bySecondHardwareVersion = new String();
                usb_system_device_info.byModuleID = new String();
                usb_system_device_info.byDeviceID = new String();
            }
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_DEVICE_INFO Success! byFirmwareVersion:" + usb_system_device_info.byFirmwareVersion + " byEncoderVersion:" + usb_system_device_info.byEncoderVersion + " byHardwareVersion:" + usb_system_device_info.byHardwareVersion + " byDeviceType:" + usb_system_device_info.byDeviceType + " byProtocolVersion:" + usb_system_device_info.byProtocolVersion + " bySerialNumber:" + usb_system_device_info.bySerialNumber + " bySecondHardwareVersion:" + usb_system_device_info.bySecondHardwareVersion + " byModuleID:" + usb_system_device_info.byModuleID + " byDeviceID:" + usb_system_device_info.byDeviceID);
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_DEVICE_INFO failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byFirmwareVersion:" + usb_system_device_info.byFirmwareVersion + " byEncoderVersion:" + usb_system_device_info.byEncoderVersion + " byHardwareVersion:" + usb_system_device_info.byHardwareVersion + " byDeviceType:" + usb_system_device_info.byDeviceType + " byProtocolVersion:" + usb_system_device_info.byProtocolVersion + " bySerialNumber:" + usb_system_device_info.bySerialNumber + " bySecondHardwareVersion:" + usb_system_device_info.bySecondHardwareVersion + " byModuleID:" + usb_system_device_info.byModuleID + " byDeviceID:" + usb_system_device_info.byDeviceID);
        return false;
    }

    public boolean USB_SetSystemReboot(int i) {
        if (HCUSBSDK.getInstance().USB_Control(i, 2012, Pointer.NULL)) {
            Log.i("[JavaInterface]", "USB_Control USB_SET_SYSTEM_REBOOT Success!");
            return true;
        }
        Log.e("[JavaInterface]", "USB_Control USB_SET_SYSTEM_REBOOT failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
        return false;
    }

    public boolean USB_SetSystemReset(int i) {
        if (HCUSBSDK.getInstance().USB_Control(i, USB_SET_SYSTEM_RESET, Pointer.NULL)) {
            Log.i("[JavaInterface]", "USB_Control USB_SET_SYSTEM_RESET Success!");
            return true;
        }
        Log.e("[JavaInterface]", "USB_Control USB_SET_SYSTEM_RESET failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
        return false;
    }

    public boolean USB_GetSystemHardwareServer(int i, USB_SYSTEM_HARDWARE_SERVER usb_system_hardware_server) throws IllegalAccessException {
        if (usb_system_hardware_server == null) {
            Log.e("[JavaInterface]", "USB_GetSystemHardwareServer failed! struHardwareServer == null");
            return false;
        }
        HCUSBSDKByJNA.USB_SYSTEM_HARDWARE_SERVER usb_system_hardware_server2 = new HCUSBSDKByJNA.USB_SYSTEM_HARDWARE_SERVER();
        usb_system_hardware_server2.write();
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_system_hardware_server2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_system_hardware_server2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_SYSTEM_HARDWARE_SERVER, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_system_hardware_server2.read();
            usb_system_hardware_server.byUsbMode = usb_system_hardware_server2.byUsbMode;
            usb_system_hardware_server.byDeviceInitialStatus = usb_system_hardware_server2.byDeviceInitialStatus;
            usb_system_hardware_server.byDeviceWorkingStatus = usb_system_hardware_server2.byDeviceWorkingStatus;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_HARDWARE_SERVER Success! byUsbMode:" + ((int) usb_system_hardware_server.byUsbMode) + " byDeviceInitialStatus:" + ((int) usb_system_hardware_server.byDeviceInitialStatus) + " byDeviceWorkingStatus:" + ((int) usb_system_hardware_server.byDeviceWorkingStatus));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_DEVICE_INFO failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byUsbMode:" + ((int) usb_system_hardware_server.byUsbMode) + " byDeviceInitialStatus:" + ((int) usb_system_hardware_server.byDeviceInitialStatus) + " byDeviceWorkingStatus:" + ((int) usb_system_hardware_server.byDeviceWorkingStatus));
        return false;
    }

    public boolean USB_SetSystemHardwareServer(int i, USB_SYSTEM_HARDWARE_SERVER usb_system_hardware_server) throws IllegalAccessException {
        if (usb_system_hardware_server == null) {
            Log.e("[JavaInterface]", "USB_SetSystemHardwareServer failed! struHardwareServer == null");
            return false;
        }
        HCUSBSDKByJNA.USB_SYSTEM_HARDWARE_SERVER usb_system_hardware_server2 = new HCUSBSDKByJNA.USB_SYSTEM_HARDWARE_SERVER();
        usb_system_hardware_server2.byUsbMode = usb_system_hardware_server.byUsbMode;
        usb_system_hardware_server2.byDeviceInitialStatus = usb_system_hardware_server.byDeviceInitialStatus;
        usb_system_hardware_server2.byDeviceWorkingStatus = usb_system_hardware_server.byDeviceWorkingStatus;
        usb_system_hardware_server2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_system_hardware_server2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_system_hardware_server2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_SYSTEM_HARDWARE_SERVER, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_system_hardware_server2.read();
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_HARDWARE_SERVER Success! byUsbMode:" + ((int) usb_system_hardware_server2.byUsbMode) + " byDeviceInitialStatus:" + ((int) usb_system_hardware_server2.byDeviceInitialStatus) + " byDeviceWorkingStatus:" + ((int) usb_system_hardware_server2.byDeviceWorkingStatus));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_DEVICE_INFO failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byUsbMode:" + ((int) usb_system_hardware_server2.byUsbMode) + " byDeviceInitialStatus:" + ((int) usb_system_hardware_server2.byDeviceInitialStatus) + " byDeviceWorkingStatus:" + ((int) usb_system_hardware_server2.byDeviceWorkingStatus));
        return false;
    }

    public boolean USB_GetSystemLocalTime(int i, USB_SYSTEM_LOCALTIME usb_system_localtime) throws IllegalAccessException {
        if (usb_system_localtime == null) {
            Log.e("[JavaInterface]", "USB_GetSystemLocalTime failed! struLocalTime == null");
            return false;
        }
        HCUSBSDKByJNA.USB_SYSTEM_LOCALTIME usb_system_localtime2 = new HCUSBSDKByJNA.USB_SYSTEM_LOCALTIME();
        usb_system_localtime2.write();
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_system_localtime2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_system_localtime2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_SYSTEM_LOCALTIME, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_system_localtime2.read();
            usb_system_localtime.wMillisecond = usb_system_localtime2.wMillisecond;
            usb_system_localtime.bySecond = usb_system_localtime2.bySecond;
            usb_system_localtime.byMinute = usb_system_localtime2.byMinute;
            usb_system_localtime.byHour = usb_system_localtime2.byHour;
            usb_system_localtime.byDay = usb_system_localtime2.byDay;
            usb_system_localtime.byMonth = usb_system_localtime2.byMonth;
            usb_system_localtime.wYear = usb_system_localtime2.wYear;
            usb_system_localtime.byExternalTimeSourceEnabled = usb_system_localtime2.byExternalTimeSourceEnabled;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_LOCALTIME Success! wYear:" + ((int) usb_system_localtime.wYear) + " byMonth:" + ((int) usb_system_localtime.byMonth) + " byDay:" + ((int) usb_system_localtime.byDay) + " byHour:" + ((int) usb_system_localtime.byHour) + " byMinute:" + ((int) usb_system_localtime.byMinute) + " bySecond:" + ((int) usb_system_localtime.bySecond) + " wMillisecond:" + ((int) usb_system_localtime.wMillisecond) + " byExternalTimeSourceEnabled:" + ((int) usb_system_localtime.byExternalTimeSourceEnabled));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_LOCALTIME failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " wYear:" + ((int) usb_system_localtime.wYear) + " byMonth:" + ((int) usb_system_localtime.byMonth) + " byDay:" + ((int) usb_system_localtime.byDay) + " byHour:" + ((int) usb_system_localtime.byHour) + " byMinute:" + ((int) usb_system_localtime.byMinute) + " bySecond:" + ((int) usb_system_localtime.bySecond) + " wMillisecond:" + ((int) usb_system_localtime.wMillisecond) + " byExternalTimeSourceEnabled:" + ((int) usb_system_localtime.byExternalTimeSourceEnabled));
        return false;
    }

    public boolean USB_SetSystemLocalTime(int i, USB_SYSTEM_LOCALTIME usb_system_localtime) throws IllegalAccessException {
        if (usb_system_localtime == null) {
            Log.e("[JavaInterface]", "USB_SetSystemLocalTime failed! struLocalTime == null");
            return false;
        }
        HCUSBSDKByJNA.USB_SYSTEM_LOCALTIME usb_system_localtime2 = new HCUSBSDKByJNA.USB_SYSTEM_LOCALTIME();
        usb_system_localtime2.wMillisecond = usb_system_localtime.wMillisecond;
        usb_system_localtime2.bySecond = usb_system_localtime.bySecond;
        usb_system_localtime2.byMinute = usb_system_localtime.byMinute;
        usb_system_localtime2.byHour = usb_system_localtime.byHour;
        usb_system_localtime2.byDay = usb_system_localtime.byDay;
        usb_system_localtime2.byMonth = usb_system_localtime.byMonth;
        usb_system_localtime2.wYear = usb_system_localtime.wYear;
        usb_system_localtime2.byExternalTimeSourceEnabled = usb_system_localtime.byExternalTimeSourceEnabled;
        usb_system_localtime2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_system_localtime2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_system_localtime2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_SYSTEM_LOCALTIME, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_system_localtime2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_SYSTEM_LOCALTIME Success! wYear:" + ((int) usb_system_localtime.wYear) + " byMonth:" + ((int) usb_system_localtime.byMonth) + " byDay:" + ((int) usb_system_localtime.byDay) + " byHour:" + ((int) usb_system_localtime.byHour) + " byMinute:" + ((int) usb_system_localtime.byMinute) + " bySecond:" + ((int) usb_system_localtime.bySecond) + " wMillisecond:" + ((int) usb_system_localtime.wMillisecond) + " byExternalTimeSourceEnabled:" + ((int) usb_system_localtime.byExternalTimeSourceEnabled));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_SYSTEM_LOCALTIME failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " wYear:" + ((int) usb_system_localtime.wYear) + " byMonth:" + ((int) usb_system_localtime.byMonth) + " byDay:" + ((int) usb_system_localtime.byDay) + " byHour:" + ((int) usb_system_localtime.byHour) + " byMinute:" + ((int) usb_system_localtime.byMinute) + " bySecond:" + ((int) usb_system_localtime.bySecond) + " wMillisecond:" + ((int) usb_system_localtime.wMillisecond) + " byExternalTimeSourceEnabled:" + ((int) usb_system_localtime.byExternalTimeSourceEnabled));
        return false;
    }

    public boolean USB_GetImageBrightNess(int i, USB_IMAGE_BRIGHTNESS usb_image_brightness) throws IllegalAccessException {
        if (usb_image_brightness == null) {
            Log.e("[JavaInterface]", "USB_GetImageBrightNess failed! struImageBrightNess == null");
            return false;
        }
        HCUSBSDKByJNA.USB_IMAGE_BRIGHTNESS usb_image_brightness2 = new HCUSBSDKByJNA.USB_IMAGE_BRIGHTNESS();
        usb_image_brightness2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_image_brightness2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_image_brightness2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_IMAGE_BRIGHTNESS, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_image_brightness2.read();
            usb_image_brightness.dwBrightness = usb_image_brightness2.dwBrightness;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_IMAGE_BRIGHTNESS Success! dwBrightness:" + usb_image_brightness.dwBrightness);
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_IMAGE_BRIGHTNESS failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwBrightness:" + usb_image_brightness.dwBrightness);
        return false;
    }

    public boolean USB_SetImageBrightNess(int i, USB_IMAGE_BRIGHTNESS usb_image_brightness) throws IllegalAccessException {
        if (usb_image_brightness == null) {
            Log.e("[JavaInterface]", "USB_SetImageBrightNess failed! struImageBrightNess == null");
            return false;
        }
        HCUSBSDKByJNA.USB_IMAGE_BRIGHTNESS usb_image_brightness2 = new HCUSBSDKByJNA.USB_IMAGE_BRIGHTNESS();
        usb_image_brightness2.dwBrightness = usb_image_brightness.dwBrightness;
        usb_image_brightness2.dwSize = usb_image_brightness2.size();
        usb_image_brightness2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_image_brightness2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_image_brightness2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_IMAGE_BRIGHTNESS, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_image_brightness2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_IMAGE_BRIGHTNESS Success! dwBrightness:" + usb_image_brightness.dwBrightness);
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_IMAGE_BRIGHTNESS failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwBrightness:" + usb_image_brightness.dwBrightness);
        return false;
    }

    public boolean USB_GetImageContrast(int i, USB_IMAGE_CONTRAST usb_image_contrast) throws IllegalAccessException {
        if (usb_image_contrast == null) {
            Log.e("[JavaInterface]", "USB_GetImageContrast failed! struImageContrast == null");
            return false;
        }
        HCUSBSDKByJNA.USB_IMAGE_CONTRAST usb_image_contrast2 = new HCUSBSDKByJNA.USB_IMAGE_CONTRAST();
        usb_image_contrast2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_image_contrast2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_image_contrast2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_IMAGE_CONTRAST, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_image_contrast2.read();
            usb_image_contrast.dwContrast = usb_image_contrast2.dwContrast;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_IMAGE_CONTRAST Success! dwContrast:" + usb_image_contrast.dwContrast);
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_IMAGE_CONTRAST failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwContrast:" + usb_image_contrast.dwContrast);
        return false;
    }

    public boolean USB_SetImageContrast(int i, USB_IMAGE_CONTRAST usb_image_contrast) throws IllegalAccessException {
        if (usb_image_contrast == null) {
            Log.e("[JavaInterface]", "USB_SetImageContrast failed! struImageContrast == null");
            return false;
        }
        HCUSBSDKByJNA.USB_IMAGE_CONTRAST usb_image_contrast2 = new HCUSBSDKByJNA.USB_IMAGE_CONTRAST();
        usb_image_contrast2.dwContrast = usb_image_contrast.dwContrast;
        usb_image_contrast2.dwSize = usb_image_contrast2.size();
        usb_image_contrast2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_image_contrast2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_image_contrast2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_IMAGE_CONTRAST, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_image_contrast2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_IMAGE_CONTRAST Success! dwContrast:" + usb_image_contrast2.dwContrast);
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_IMAGE_CONTRAST failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwContrast:" + usb_image_contrast2.dwContrast);
        return false;
    }

    public boolean USB_SetImageBackGroundCorrect(int i) throws IllegalAccessException {
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONTROL_INPUT_INFO usb_control_input_info = new HCUSBSDKByJNA.USB_CONTROL_INPUT_INFO();
        usb_control_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_control_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_control_input_info.write();
        if (HCUSBSDK.getInstance().USB_Control(i, USB_SET_IMAGE_BACKGROUND_CORRECT, usb_control_input_info.getPointer())) {
            Log.i("[JavaInterface]", "USB_Control USB_SET_IMAGE_BACKGROUND_CORRECT Success!");
            return true;
        }
        Log.e("[JavaInterface]", "USB_Control USB_SET_IMAGE_BACKGROUND_CORRECT failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
        return false;
    }

    public boolean USB_GetSystemDiagnosedData(int i, USB_SYSTEM_DIAGNOSED_DATA usb_system_diagnosed_data) throws IllegalAccessException {
        if (usb_system_diagnosed_data == null) {
            Log.e("[JavaInterface]", "USB_GetSystemDiagnosedData failed! struSysDiagnosedData == null");
            return false;
        }
        HCUSBSDKByJNA.USB_SYSTEM_DIAGNOSED_DATA usb_system_diagnosed_data2 = new HCUSBSDKByJNA.USB_SYSTEM_DIAGNOSED_DATA();
        HCUSBSDKByJNA.BYTE_ARRAY byte_array = new HCUSBSDKByJNA.BYTE_ARRAY(1048576);
        usb_system_diagnosed_data2.pDiagnosedData = byte_array.getPointer();
        usb_system_diagnosed_data2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_system_diagnosed_data2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_system_diagnosed_data2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_SYSTEM_DIAGNOSED_DATA, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_system_diagnosed_data2.read();
            byte_array.read();
            if (usb_system_diagnosed_data2.dwDataLenth > 1048576) {
                Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_DIAGNOSED_DATA failed!  dwDataLenth > MAX_DIAGNOSED_DATA_SIZE");
                return false;
            }
            usb_system_diagnosed_data.dwDataLenth = usb_system_diagnosed_data2.dwDataLenth;
            System.arraycopy(byte_array.byValue, 0, usb_system_diagnosed_data.pDiagnosedData, 0, usb_system_diagnosed_data2.dwDataLenth);
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_DIAGNOSED_DATA Success! dwDataLenth:" + usb_system_diagnosed_data.dwDataLenth);
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_DIAGNOSED_DATA failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwDataLenth:" + usb_system_diagnosed_data.dwDataLenth);
        return false;
    }

    public boolean USB_GetSystemDiagnosedDataEx(int i, USB_SYSTEM_DIAGNOSED_DATA_COND usb_system_diagnosed_data_cond, USB_SYSTEM_DIAGNOSED_DATA usb_system_diagnosed_data) throws IllegalAccessException {
        if (usb_system_diagnosed_data_cond == null || usb_system_diagnosed_data == null) {
            Log.e("[JavaInterface]", "USB_GetSystemDiagnosedData failed! struSysDiagnosedDataCond == null || struSysDiagnosedData == null");
            return false;
        }
        HCUSBSDKByJNA.USB_SYSTEM_DIAGNOSED_DATA_COND usb_system_diagnosed_data_cond2 = new HCUSBSDKByJNA.USB_SYSTEM_DIAGNOSED_DATA_COND();
        usb_system_diagnosed_data_cond2.byDataType = usb_system_diagnosed_data_cond.byDataType;
        usb_system_diagnosed_data_cond2.dwAddress = usb_system_diagnosed_data_cond.dwAddress;
        usb_system_diagnosed_data_cond2.dwLength = usb_system_diagnosed_data_cond.dwLength;
        usb_system_diagnosed_data_cond2.dwSize = usb_system_diagnosed_data_cond2.size();
        usb_system_diagnosed_data_cond2.write();
        HCUSBSDKByJNA.USB_SYSTEM_DIAGNOSED_DATA usb_system_diagnosed_data2 = new HCUSBSDKByJNA.USB_SYSTEM_DIAGNOSED_DATA();
        HCUSBSDKByJNA.BYTE_ARRAY byte_array = new HCUSBSDKByJNA.BYTE_ARRAY(1048576);
        usb_system_diagnosed_data2.pDiagnosedData = byte_array.getPointer();
        usb_system_diagnosed_data2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_system_diagnosed_data_cond2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_system_diagnosed_data_cond2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_system_diagnosed_data2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_system_diagnosed_data2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_SYSTEM_DIAGNOSED_DATA_EX, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_system_diagnosed_data2.read();
            byte_array.read();
            if (usb_system_diagnosed_data2.dwDataLenth > 1048576) {
                Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_DIAGNOSED_DATA failed!  dwDataLenth > MAX_DIAGNOSED_DATA_SIZE");
                return false;
            }
            usb_system_diagnosed_data.dwDataLenth = usb_system_diagnosed_data2.dwDataLenth;
            System.arraycopy(byte_array.byValue, 0, usb_system_diagnosed_data.pDiagnosedData, 0, usb_system_diagnosed_data2.dwDataLenth);
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_DIAGNOSED_DATA Success! dwDataLenth:" + usb_system_diagnosed_data.dwDataLenth);
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_SYSTEM_DIAGNOSED_DATA failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwDataLenth:" + usb_system_diagnosed_data.dwDataLenth);
        return false;
    }

    public boolean USB_SetImageManualCorrect(int i) throws IllegalAccessException {
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONTROL_INPUT_INFO usb_control_input_info = new HCUSBSDKByJNA.USB_CONTROL_INPUT_INFO();
        usb_control_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_control_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_control_input_info.write();
        if (HCUSBSDK.getInstance().USB_Control(i, USB_SET_IMAGE_MANUAL_CORRECT, usb_control_input_info.getPointer())) {
            Log.i("[JavaInterface]", "USB_Control USB_SET_IMAGE_MANUAL_CORRECT Success!");
            return true;
        }
        Log.e("[JavaInterface]", "USB_Control USB_SET_IMAGE_MANUAL_CORRECT failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
        return false;
    }

    public boolean USB_GetImageEnhancement(int i, USB_IMAGE_ENHANCEMENT usb_image_enhancement) throws IllegalAccessException {
        if (usb_image_enhancement == null) {
            Log.e("[JavaInterface]", "USB_GetImageEnhancement failed! struImageEnhancement == null");
            return false;
        }
        HCUSBSDKByJNA.USB_IMAGE_ENHANCEMENT usb_image_enhancement2 = new HCUSBSDKByJNA.USB_IMAGE_ENHANCEMENT();
        usb_image_enhancement2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_image_enhancement2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_image_enhancement2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_IMAGE_ENHANCEMENT, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_image_enhancement2.read();
            usb_image_enhancement.byNoiseReduceMode = usb_image_enhancement2.byNoiseReduceMode;
            usb_image_enhancement.dwGeneralLevel = usb_image_enhancement2.dwGeneralLevel;
            usb_image_enhancement.dwFrameNoiseReduceLevel = usb_image_enhancement2.dwFrameNoiseReduceLevel;
            usb_image_enhancement.dwInterFrameNoiseReduceLevel = usb_image_enhancement2.dwInterFrameNoiseReduceLevel;
            usb_image_enhancement.byPaletteMode = usb_image_enhancement2.byPaletteMode;
            usb_image_enhancement.byLSEDetailEnabled = usb_image_enhancement2.byLSEDetailEnabled;
            usb_image_enhancement.dwLSEDetailLevel = usb_image_enhancement2.dwLSEDetailLevel;
            usb_image_enhancement.byBirdWatchingMode = usb_image_enhancement2.byBirdWatchingMode;
            usb_image_enhancement.byHighLightMode = usb_image_enhancement2.byHighLightMode;
            usb_image_enhancement.byHighLightLevel = usb_image_enhancement2.byHighLightLevel;
            usb_image_enhancement.byHookEdgeMode = usb_image_enhancement2.byHookEdgeMode;
            usb_image_enhancement.byHookEdgeLevel = usb_image_enhancement2.byHookEdgeLevel;
            usb_image_enhancement.byWideTemperatureMode = usb_image_enhancement2.byWideTemperatureMode;
            usb_image_enhancement.dwWideTemperatureUpThreshold = usb_image_enhancement2.dwWideTemperatureUpThreshold;
            usb_image_enhancement.dwWideTemperatureDownThreshold = usb_image_enhancement2.dwWideTemperatureDownThreshold;
            usb_image_enhancement.byWideTemperatureWork = usb_image_enhancement2.byWideTemperatureWork;
            usb_image_enhancement.byIspAgcMode = usb_image_enhancement2.byIspAgcMode;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_IMAGE_ENHANCEMENT Success! byNoiseReduceMode:" + ((int) usb_image_enhancement.byNoiseReduceMode) + " dwGeneralLevel:" + usb_image_enhancement.dwGeneralLevel + " dwFrameNoiseReduceLevel:" + usb_image_enhancement.dwFrameNoiseReduceLevel + " dwInterFrameNoiseReduceLevel:" + usb_image_enhancement.dwInterFrameNoiseReduceLevel + " byPaletteMode:" + ((int) usb_image_enhancement.byPaletteMode) + " byLSEDetailEnabled:" + ((int) usb_image_enhancement.byLSEDetailEnabled) + " dwLSEDetailLevel:" + usb_image_enhancement.dwLSEDetailLevel + " byBirdWatchingMode:" + ((int) usb_image_enhancement.byBirdWatchingMode) + " byHighLightMode:" + ((int) usb_image_enhancement.byHighLightMode) + " byHighLightLevel:" + ((int) usb_image_enhancement.byHighLightLevel) + " byHookEdgeMode:" + ((int) usb_image_enhancement.byHookEdgeMode) + " byHookEdgeLevel:" + ((int) usb_image_enhancement.byHookEdgeLevel) + " byWideTemperatureMode:" + ((int) usb_image_enhancement.byWideTemperatureMode) + " dwWideTemperatureUpThreshold:" + usb_image_enhancement.dwWideTemperatureUpThreshold + " dwWideTemperatureDownThreshold:" + usb_image_enhancement.dwWideTemperatureDownThreshold + " byWideTemperatureWork:" + ((int) usb_image_enhancement.byWideTemperatureWork) + " byIspAgcMode:" + ((int) usb_image_enhancement.byIspAgcMode));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_IMAGE_ENHANCEMENT failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byNoiseReduceMode:" + ((int) usb_image_enhancement.byNoiseReduceMode) + " dwGeneralLevel:" + usb_image_enhancement.dwGeneralLevel + " dwFrameNoiseReduceLevel:" + usb_image_enhancement.dwFrameNoiseReduceLevel + " dwInterFrameNoiseReduceLevel:" + usb_image_enhancement.dwInterFrameNoiseReduceLevel + " byPaletteMode:" + ((int) usb_image_enhancement.byPaletteMode) + " byLSEDetailEnabled:" + ((int) usb_image_enhancement.byLSEDetailEnabled) + " dwLSEDetailLevel:" + usb_image_enhancement.dwLSEDetailLevel + " byBirdWatchingMode:" + ((int) usb_image_enhancement.byBirdWatchingMode) + " byHighLightMode:" + ((int) usb_image_enhancement.byHighLightMode) + " byHighLightLevel:" + ((int) usb_image_enhancement.byHighLightLevel) + " byHookEdgeMode:" + ((int) usb_image_enhancement.byHookEdgeMode) + " byHookEdgeLevel:" + ((int) usb_image_enhancement.byHookEdgeLevel) + " byWideTemperatureMode:" + ((int) usb_image_enhancement.byWideTemperatureMode) + " dwWideTemperatureUpThreshold:" + usb_image_enhancement.dwWideTemperatureUpThreshold + " dwWideTemperatureDownThreshold:" + usb_image_enhancement.dwWideTemperatureDownThreshold + " byWideTemperatureWork:" + ((int) usb_image_enhancement.byWideTemperatureWork) + " byIspAgcMode:" + ((int) usb_image_enhancement.byIspAgcMode));
        return false;
    }

    public boolean USB_SetImageEnhancement(int i, USB_IMAGE_ENHANCEMENT usb_image_enhancement) throws IllegalAccessException {
        if (usb_image_enhancement == null) {
            Log.e("[JavaInterface]", "USB_SetImageEnhancement failed! struImageEnhancement == null");
            return false;
        }
        HCUSBSDKByJNA.USB_IMAGE_ENHANCEMENT usb_image_enhancement2 = new HCUSBSDKByJNA.USB_IMAGE_ENHANCEMENT();
        usb_image_enhancement2.byNoiseReduceMode = usb_image_enhancement.byNoiseReduceMode;
        usb_image_enhancement2.dwGeneralLevel = usb_image_enhancement.dwGeneralLevel;
        usb_image_enhancement2.dwFrameNoiseReduceLevel = usb_image_enhancement.dwFrameNoiseReduceLevel;
        usb_image_enhancement2.dwInterFrameNoiseReduceLevel = usb_image_enhancement.dwInterFrameNoiseReduceLevel;
        usb_image_enhancement2.byPaletteMode = usb_image_enhancement.byPaletteMode;
        usb_image_enhancement2.byLSEDetailEnabled = usb_image_enhancement.byLSEDetailEnabled;
        usb_image_enhancement2.dwLSEDetailLevel = usb_image_enhancement.dwLSEDetailLevel;
        usb_image_enhancement2.byBirdWatchingMode = usb_image_enhancement.byBirdWatchingMode;
        usb_image_enhancement2.byHighLightMode = usb_image_enhancement.byHighLightMode;
        usb_image_enhancement2.byHighLightLevel = usb_image_enhancement.byHighLightLevel;
        usb_image_enhancement2.byHookEdgeMode = usb_image_enhancement.byHookEdgeMode;
        usb_image_enhancement2.byHookEdgeLevel = usb_image_enhancement.byHookEdgeLevel;
        usb_image_enhancement2.byWideTemperatureMode = usb_image_enhancement.byWideTemperatureMode;
        usb_image_enhancement2.dwWideTemperatureUpThreshold = usb_image_enhancement.dwWideTemperatureUpThreshold;
        usb_image_enhancement2.dwWideTemperatureDownThreshold = usb_image_enhancement.dwWideTemperatureDownThreshold;
        usb_image_enhancement2.byWideTemperatureWork = usb_image_enhancement.byWideTemperatureWork;
        usb_image_enhancement2.byIspAgcMode = usb_image_enhancement.byIspAgcMode;
        usb_image_enhancement2.dwSize = usb_image_enhancement2.size();
        usb_image_enhancement2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_image_enhancement2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_image_enhancement2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_IMAGE_ENHANCEMENT, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_image_enhancement2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_IMAGE_ENHANCEMENT Success! byNoiseReduceMode:" + ((int) usb_image_enhancement.byNoiseReduceMode) + " dwGeneralLevel:" + usb_image_enhancement.dwGeneralLevel + " dwFrameNoiseReduceLevel:" + usb_image_enhancement.dwFrameNoiseReduceLevel + " dwInterFrameNoiseReduceLevel:" + usb_image_enhancement.dwInterFrameNoiseReduceLevel + " byPaletteMode:" + ((int) usb_image_enhancement.byPaletteMode) + " byLSEDetailEnabled:" + ((int) usb_image_enhancement.byLSEDetailEnabled) + " dwLSEDetailLevel:" + usb_image_enhancement.dwLSEDetailLevel + " byBirdWatchingMode:" + ((int) usb_image_enhancement.byBirdWatchingMode) + " byHighLightMode:" + ((int) usb_image_enhancement.byHighLightMode) + " byHighLightLevel:" + ((int) usb_image_enhancement.byHighLightLevel) + " byHookEdgeMode:" + ((int) usb_image_enhancement.byHookEdgeMode) + " byHookEdgeLevel:" + ((int) usb_image_enhancement.byHookEdgeLevel) + " byWideTemperatureMode:" + ((int) usb_image_enhancement.byWideTemperatureMode) + " dwWideTemperatureUpThreshold:" + usb_image_enhancement.dwWideTemperatureUpThreshold + " dwWideTemperatureDownThreshold:" + usb_image_enhancement.dwWideTemperatureDownThreshold + " byWideTemperatureWork:" + ((int) usb_image_enhancement.byWideTemperatureWork) + " byIspAgcMode:" + ((int) usb_image_enhancement.byIspAgcMode));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_IMAGE_ENHANCEMENT failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byNoiseReduceMode:" + ((int) usb_image_enhancement.byNoiseReduceMode) + " dwGeneralLevel:" + usb_image_enhancement.dwGeneralLevel + " dwFrameNoiseReduceLevel:" + usb_image_enhancement.dwFrameNoiseReduceLevel + " dwInterFrameNoiseReduceLevel:" + usb_image_enhancement.dwInterFrameNoiseReduceLevel + " byPaletteMode:" + ((int) usb_image_enhancement.byPaletteMode) + " byLSEDetailEnabled:" + ((int) usb_image_enhancement.byLSEDetailEnabled) + " dwLSEDetailLevel:" + usb_image_enhancement.dwLSEDetailLevel + " byBirdWatchingMode:" + ((int) usb_image_enhancement.byBirdWatchingMode) + " byHighLightMode:" + ((int) usb_image_enhancement.byHighLightMode) + " byHighLightLevel:" + ((int) usb_image_enhancement.byHighLightLevel) + " byHookEdgeMode:" + ((int) usb_image_enhancement.byHookEdgeMode) + " byHookEdgeLevel:" + ((int) usb_image_enhancement.byHookEdgeLevel) + " byWideTemperatureMode:" + ((int) usb_image_enhancement.byWideTemperatureMode) + " dwWideTemperatureUpThreshold:" + usb_image_enhancement.dwWideTemperatureUpThreshold + " dwWideTemperatureDownThreshold:" + usb_image_enhancement.dwWideTemperatureDownThreshold + " byWideTemperatureWork:" + ((int) usb_image_enhancement.byWideTemperatureWork) + " byIspAgcMode:" + ((int) usb_image_enhancement.byIspAgcMode));
        return false;
    }

    public boolean USB_GetImageVideoAdjust(int i, USB_IMAGE_VIDEO_ADJUST usb_image_video_adjust) throws IllegalAccessException {
        if (usb_image_video_adjust == null) {
            Log.e("[JavaInterface]", "USB_GetImageVideoAdjust failed! struImageVideoAdjust == null");
            return false;
        }
        HCUSBSDKByJNA.USB_IMAGE_VIDEO_ADJUST usb_image_video_adjust2 = new HCUSBSDKByJNA.USB_IMAGE_VIDEO_ADJUST();
        usb_image_video_adjust2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_image_video_adjust2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_image_video_adjust2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_IMAGE_VIDEO_ADJUST, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_image_video_adjust2.read();
            usb_image_video_adjust.byImageFlipStyle = usb_image_video_adjust2.byImageFlipStyle;
            usb_image_video_adjust.byPowerLineFrequencyMode = usb_image_video_adjust2.byPowerLineFrequencyMode;
            usb_image_video_adjust.byCorridor = usb_image_video_adjust2.byCorridor;
            usb_image_video_adjust.byDigitalZoom = usb_image_video_adjust2.byDigitalZoom;
            usb_image_video_adjust.byCursor = usb_image_video_adjust2.byCursor;
            usb_image_video_adjust.byBadPointCursor = usb_image_video_adjust2.byBadPointCursor;
            usb_image_video_adjust.byBadPointCursorShiftMode = usb_image_video_adjust2.byBadPointCursorShiftMode;
            usb_image_video_adjust.dwCursorPointX = usb_image_video_adjust2.dwCursorPointX;
            usb_image_video_adjust.dwCursorPointY = usb_image_video_adjust2.dwCursorPointY;
            usb_image_video_adjust.dwBadCursorPointX = usb_image_video_adjust2.dwBadCursorPointX;
            usb_image_video_adjust.dwBadCursorPointY = usb_image_video_adjust2.dwBadCursorPointY;
            usb_image_video_adjust.byPointXShiftLeft = usb_image_video_adjust2.byPointXShiftLeft;
            usb_image_video_adjust.byPointXShiftRight = usb_image_video_adjust2.byPointXShiftRight;
            usb_image_video_adjust.byPointYShiftUp = usb_image_video_adjust2.byPointYShiftUp;
            usb_image_video_adjust.byPointYShiftDown = usb_image_video_adjust2.byPointYShiftDown;
            usb_image_video_adjust.byDeleteBadPoint = usb_image_video_adjust2.byDeleteBadPoint;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_IMAGE_VIDEO_ADJUST Success! byImageFlipStyle:" + ((int) usb_image_video_adjust.byImageFlipStyle) + " byPowerLineFrequencyMode:" + ((int) usb_image_video_adjust.byPowerLineFrequencyMode) + " byCorridor:" + ((int) usb_image_video_adjust.byCorridor) + " byDigitalZoom:" + ((int) usb_image_video_adjust.byDigitalZoom) + " byCursor:" + ((int) usb_image_video_adjust.byCursor) + " byBadPointCursor:" + ((int) usb_image_video_adjust.byBadPointCursor) + " byBadPointCursorShiftMode:" + ((int) usb_image_video_adjust.byBadPointCursorShiftMode) + " dwCursorPointX:" + usb_image_video_adjust.dwCursorPointX + " dwCursorPointY:" + usb_image_video_adjust.dwCursorPointY + " dwBadCursorPointX:" + usb_image_video_adjust.dwBadCursorPointX + " dwBadCursorPointY:" + usb_image_video_adjust.dwBadCursorPointY + " byPointXShiftLeft:" + ((int) usb_image_video_adjust.byPointXShiftLeft) + " byPointXShiftRight:" + ((int) usb_image_video_adjust.byPointXShiftRight) + " byPointYShiftUp:" + ((int) usb_image_video_adjust.byPointYShiftUp) + " byPointYShiftDown:" + ((int) usb_image_video_adjust.byPointYShiftDown) + " byDeleteBadPoint:" + ((int) usb_image_video_adjust.byDeleteBadPoint));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_IMAGE_VIDEO_ADJUST failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byImageFlipStyle:" + ((int) usb_image_video_adjust.byImageFlipStyle) + " byPowerLineFrequencyMode:" + ((int) usb_image_video_adjust.byPowerLineFrequencyMode) + " byCorridor:" + ((int) usb_image_video_adjust.byCorridor) + " byDigitalZoom:" + ((int) usb_image_video_adjust.byDigitalZoom) + " byCursor:" + ((int) usb_image_video_adjust.byCursor) + " byBadPointCursor:" + ((int) usb_image_video_adjust.byBadPointCursor) + " byBadPointCursorShiftMode:" + ((int) usb_image_video_adjust.byBadPointCursorShiftMode) + " dwCursorPointX:" + usb_image_video_adjust.dwCursorPointX + " dwCursorPointY:" + usb_image_video_adjust.dwCursorPointY + " dwBadCursorPointX:" + usb_image_video_adjust.dwBadCursorPointX + " dwBadCursorPointY:" + usb_image_video_adjust.dwBadCursorPointY + " byPointXShiftLeft:" + ((int) usb_image_video_adjust.byPointXShiftLeft) + " byPointXShiftRight:" + ((int) usb_image_video_adjust.byPointXShiftRight) + " byPointYShiftUp:" + ((int) usb_image_video_adjust.byPointYShiftUp) + " byPointYShiftDown:" + ((int) usb_image_video_adjust.byPointYShiftDown) + " byDeleteBadPoint:" + ((int) usb_image_video_adjust.byDeleteBadPoint));
        return false;
    }

    public boolean USB_SetImageVideoAdjust(int i, USB_IMAGE_VIDEO_ADJUST usb_image_video_adjust) throws IllegalAccessException {
        if (usb_image_video_adjust == null) {
            Log.e("[JavaInterface]", "USB_SetImageVideoAdjust failed! struImageVideoAdjust == null");
            return false;
        }
        HCUSBSDKByJNA.USB_IMAGE_VIDEO_ADJUST usb_image_video_adjust2 = new HCUSBSDKByJNA.USB_IMAGE_VIDEO_ADJUST();
        usb_image_video_adjust2.byImageFlipStyle = usb_image_video_adjust.byImageFlipStyle;
        usb_image_video_adjust2.byPowerLineFrequencyMode = usb_image_video_adjust.byPowerLineFrequencyMode;
        usb_image_video_adjust2.byCorridor = usb_image_video_adjust.byCorridor;
        usb_image_video_adjust2.byDigitalZoom = usb_image_video_adjust.byDigitalZoom;
        usb_image_video_adjust2.byCursor = usb_image_video_adjust.byCursor;
        usb_image_video_adjust2.byBadPointCursor = usb_image_video_adjust.byBadPointCursor;
        usb_image_video_adjust2.byBadPointCursorShiftMode = usb_image_video_adjust.byBadPointCursorShiftMode;
        usb_image_video_adjust2.dwCursorPointX = usb_image_video_adjust.dwCursorPointX;
        usb_image_video_adjust2.dwCursorPointY = usb_image_video_adjust.dwCursorPointY;
        usb_image_video_adjust2.dwBadCursorPointX = usb_image_video_adjust.dwBadCursorPointX;
        usb_image_video_adjust2.dwBadCursorPointY = usb_image_video_adjust.dwBadCursorPointY;
        usb_image_video_adjust2.byPointXShiftLeft = usb_image_video_adjust.byPointXShiftLeft;
        usb_image_video_adjust2.byPointXShiftRight = usb_image_video_adjust.byPointXShiftRight;
        usb_image_video_adjust2.byPointYShiftUp = usb_image_video_adjust.byPointYShiftUp;
        usb_image_video_adjust2.byPointYShiftDown = usb_image_video_adjust.byPointYShiftDown;
        usb_image_video_adjust2.byDeleteBadPoint = usb_image_video_adjust.byDeleteBadPoint;
        usb_image_video_adjust2.dwSize = usb_image_video_adjust2.size();
        usb_image_video_adjust2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_image_video_adjust2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_image_video_adjust2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_IMAGE_VIDEO_ADJUST, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_image_video_adjust2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_IMAGE_VIDEO_ADJUST Success! byImageFlipStyle:" + ((int) usb_image_video_adjust.byImageFlipStyle) + " byPowerLineFrequencyMode:" + ((int) usb_image_video_adjust.byPowerLineFrequencyMode) + " byCorridor:" + ((int) usb_image_video_adjust.byCorridor) + " byDigitalZoom:" + ((int) usb_image_video_adjust.byDigitalZoom) + " byCursor:" + ((int) usb_image_video_adjust.byCursor) + " byBadPointCursor:" + ((int) usb_image_video_adjust.byBadPointCursor) + " byBadPointCursorShiftMode:" + ((int) usb_image_video_adjust.byBadPointCursorShiftMode) + " dwCursorPointX:" + usb_image_video_adjust.dwCursorPointX + " dwCursorPointY:" + usb_image_video_adjust.dwCursorPointY + " dwBadCursorPointX:" + usb_image_video_adjust.dwBadCursorPointX + " dwBadCursorPointY:" + usb_image_video_adjust.dwBadCursorPointY + " byPointXShiftLeft:" + ((int) usb_image_video_adjust.byPointXShiftLeft) + " byPointXShiftRight:" + ((int) usb_image_video_adjust.byPointXShiftRight) + " byPointYShiftUp:" + ((int) usb_image_video_adjust.byPointYShiftUp) + " byPointYShiftDown:" + ((int) usb_image_video_adjust.byPointYShiftDown) + " byDeleteBadPoint:" + ((int) usb_image_video_adjust.byDeleteBadPoint));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_IMAGE_VIDEO_ADJUST failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byImageFlipStyle:" + ((int) usb_image_video_adjust.byImageFlipStyle) + " byPowerLineFrequencyMode:" + ((int) usb_image_video_adjust.byPowerLineFrequencyMode) + " byCorridor:" + ((int) usb_image_video_adjust.byCorridor) + " byDigitalZoom:" + ((int) usb_image_video_adjust.byDigitalZoom) + " byCursor:" + ((int) usb_image_video_adjust.byCursor) + " byBadPointCursor:" + ((int) usb_image_video_adjust.byBadPointCursor) + " byBadPointCursorShiftMode:" + ((int) usb_image_video_adjust.byBadPointCursorShiftMode) + " dwCursorPointX:" + usb_image_video_adjust.dwCursorPointX + " dwCursorPointY:" + usb_image_video_adjust.dwCursorPointY + " dwBadCursorPointX:" + usb_image_video_adjust.dwBadCursorPointX + " dwBadCursorPointY:" + usb_image_video_adjust.dwBadCursorPointY + " byPointXShiftLeft:" + ((int) usb_image_video_adjust.byPointXShiftLeft) + " byPointXShiftRight:" + ((int) usb_image_video_adjust.byPointXShiftRight) + " byPointYShiftUp:" + ((int) usb_image_video_adjust.byPointYShiftUp) + " byPointYShiftDown:" + ((int) usb_image_video_adjust.byPointYShiftDown) + " byDeleteBadPoint:" + ((int) usb_image_video_adjust.byDeleteBadPoint));
        return false;
    }

    public boolean USB_GetThermometryBasicParam(int i, USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param) throws IllegalAccessException {
        if (usb_thermometry_basic_param == null) {
            Log.e("[JavaInterface]", "USB_GetThermometryBasicParam failed! struThermometryBasic == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param2 = new HCUSBSDKByJNA.USB_THERMOMETRY_BASIC_PARAM();
        usb_thermometry_basic_param2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_thermometry_basic_param2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_thermometry_basic_param2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_THERMOMETRY_BASIC_PARAM, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermometry_basic_param2.read();
            usb_thermometry_basic_param.byEnabled = usb_thermometry_basic_param2.byEnabled;
            usb_thermometry_basic_param.byDisplayMaxTemperatureEnabled = usb_thermometry_basic_param2.byDisplayMaxTemperatureEnabled;
            usb_thermometry_basic_param.byDisplayMinTemperatureEnabled = usb_thermometry_basic_param2.byDisplayMinTemperatureEnabled;
            usb_thermometry_basic_param.byDisplayAverageTemperatureEnabled = usb_thermometry_basic_param2.byDisplayAverageTemperatureEnabled;
            usb_thermometry_basic_param.byTemperatureUnit = usb_thermometry_basic_param2.byTemperatureUnit;
            usb_thermometry_basic_param.byTemperatureRange = usb_thermometry_basic_param2.byTemperatureRange;
            usb_thermometry_basic_param.byCalibrationCoefficientEnabled = usb_thermometry_basic_param2.byCalibrationCoefficientEnabled;
            usb_thermometry_basic_param.dwCalibrationCoefficient = usb_thermometry_basic_param2.dwCalibrationCoefficient;
            usb_thermometry_basic_param.dwExternalOpticsWindowCorrection = usb_thermometry_basic_param2.dwExternalOpticsWindowCorrection;
            usb_thermometry_basic_param.dwEmissivity = usb_thermometry_basic_param2.dwEmissivity;
            usb_thermometry_basic_param.byDistanceUnit = usb_thermometry_basic_param2.byDistanceUnit;
            usb_thermometry_basic_param.dwDistance = usb_thermometry_basic_param2.dwDistance;
            usb_thermometry_basic_param.byReflectiveEnable = usb_thermometry_basic_param2.byReflectiveEnable;
            usb_thermometry_basic_param.dwReflectiveTemperature = usb_thermometry_basic_param2.dwReflectiveTemperature;
            usb_thermometry_basic_param.byThermomrtryInfoDisplayPosition = usb_thermometry_basic_param2.byThermomrtryInfoDisplayPosition;
            usb_thermometry_basic_param.byThermometryStreamOverlay = usb_thermometry_basic_param2.byThermometryStreamOverlay;
            usb_thermometry_basic_param.dwAlert = usb_thermometry_basic_param2.dwAlert;
            usb_thermometry_basic_param.dwAlarm = usb_thermometry_basic_param2.dwAlarm;
            usb_thermometry_basic_param.dwExternalOpticsTransmit = usb_thermometry_basic_param2.dwExternalOpticsTransmit;
            usb_thermometry_basic_param.byDisplayCenTempEnabled = usb_thermometry_basic_param2.byDisplayCenTempEnabled;
            usb_thermometry_basic_param.byBackcolorEnabled = usb_thermometry_basic_param2.byBackcolorEnabled;
            usb_thermometry_basic_param.byShowAlarmColorEnabled = usb_thermometry_basic_param2.byShowAlarmColorEnabled;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMOMETRY_BASIC_PARAM Success! byEnabled:" + ((int) usb_thermometry_basic_param.byEnabled) + " byDisplayMaxTemperatureEnabled:" + ((int) usb_thermometry_basic_param.byDisplayMaxTemperatureEnabled) + " byDisplayMinTemperatureEnabled:" + ((int) usb_thermometry_basic_param.byDisplayMinTemperatureEnabled) + " byDisplayAverageTemperatureEnabled:" + ((int) usb_thermometry_basic_param.byDisplayAverageTemperatureEnabled) + " byTemperatureUnit:" + ((int) usb_thermometry_basic_param.byTemperatureUnit) + " byTemperatureRange:" + ((int) usb_thermometry_basic_param.byTemperatureRange) + " byCalibrationCoefficientEnabled:" + ((int) usb_thermometry_basic_param.byCalibrationCoefficientEnabled) + " dwCalibrationCoefficient:" + usb_thermometry_basic_param.dwCalibrationCoefficient + " dwExternalOpticsWindowCorrection:" + usb_thermometry_basic_param.dwExternalOpticsWindowCorrection + " dwEmissivity:" + usb_thermometry_basic_param.dwEmissivity + " byDistanceUnit:" + ((int) usb_thermometry_basic_param.byDistanceUnit) + " dwDistance:" + usb_thermometry_basic_param.dwDistance + " byReflectiveEnable:" + ((int) usb_thermometry_basic_param.byReflectiveEnable) + " dwReflectiveTemperature:" + usb_thermometry_basic_param.dwReflectiveTemperature + " byThermomrtryInfoDisplayPosition:" + ((int) usb_thermometry_basic_param.byThermomrtryInfoDisplayPosition) + " byThermometryStreamOverlay:" + ((int) usb_thermometry_basic_param.byThermometryStreamOverlay) + " dwAlert:" + usb_thermometry_basic_param.dwAlert + " dwAlarm:" + usb_thermometry_basic_param.dwAlarm + " dwExternalOpticsTransmit:" + usb_thermometry_basic_param.dwExternalOpticsTransmit + " byDisplayCenTempEnabled:" + ((int) usb_thermometry_basic_param.byDisplayCenTempEnabled) + " byBackcolorEnabled:" + ((int) usb_thermometry_basic_param.byBackcolorEnabled) + " byShowAlarmColorEnabled:" + ((int) usb_thermometry_basic_param.byShowAlarmColorEnabled));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMOMETRY_BASIC_PARAM failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byEnabled:" + ((int) usb_thermometry_basic_param.byEnabled) + " byDisplayMaxTemperatureEnabled:" + ((int) usb_thermometry_basic_param.byDisplayMaxTemperatureEnabled) + " byDisplayMinTemperatureEnabled:" + ((int) usb_thermometry_basic_param.byDisplayMinTemperatureEnabled) + " byDisplayAverageTemperatureEnabled:" + ((int) usb_thermometry_basic_param.byDisplayAverageTemperatureEnabled) + " byTemperatureUnit:" + ((int) usb_thermometry_basic_param.byTemperatureUnit) + " byTemperatureRange:" + ((int) usb_thermometry_basic_param.byTemperatureRange) + " byCalibrationCoefficientEnabled:" + ((int) usb_thermometry_basic_param.byCalibrationCoefficientEnabled) + " dwCalibrationCoefficient:" + usb_thermometry_basic_param.dwCalibrationCoefficient + " dwExternalOpticsWindowCorrection:" + usb_thermometry_basic_param.dwExternalOpticsWindowCorrection + " dwEmissivity:" + usb_thermometry_basic_param.dwEmissivity + " byDistanceUnit:" + ((int) usb_thermometry_basic_param.byDistanceUnit) + " dwDistance:" + usb_thermometry_basic_param.dwDistance + " byReflectiveEnable:" + ((int) usb_thermometry_basic_param.byReflectiveEnable) + " dwReflectiveTemperature:" + usb_thermometry_basic_param.dwReflectiveTemperature + " byThermomrtryInfoDisplayPosition:" + ((int) usb_thermometry_basic_param.byThermomrtryInfoDisplayPosition) + " byThermometryStreamOverlay:" + ((int) usb_thermometry_basic_param.byThermometryStreamOverlay) + " dwAlert:" + usb_thermometry_basic_param.dwAlert + " dwAlarm:" + usb_thermometry_basic_param.dwAlarm + " dwExternalOpticsTransmit:" + usb_thermometry_basic_param.dwExternalOpticsTransmit + " byDisplayCenTempEnabled:" + ((int) usb_thermometry_basic_param.byDisplayCenTempEnabled) + " byBackcolorEnabled:" + ((int) usb_thermometry_basic_param.byBackcolorEnabled) + " byShowAlarmColorEnabled:" + ((int) usb_thermometry_basic_param.byShowAlarmColorEnabled));
        return false;
    }

    public boolean USB_SetThermometryBasicParam(int i, USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param) {
        if (usb_thermometry_basic_param == null) {
            Log.e("[JavaInterface]", "USB_SetThermometryBasicParam failed! struThermometryBasic == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMOMETRY_BASIC_PARAM usb_thermometry_basic_param2 = new HCUSBSDKByJNA.USB_THERMOMETRY_BASIC_PARAM();
        usb_thermometry_basic_param2.byEnabled = usb_thermometry_basic_param.byEnabled;
        usb_thermometry_basic_param2.byDisplayMaxTemperatureEnabled = usb_thermometry_basic_param.byDisplayMaxTemperatureEnabled;
        usb_thermometry_basic_param2.byDisplayMinTemperatureEnabled = usb_thermometry_basic_param.byDisplayMinTemperatureEnabled;
        usb_thermometry_basic_param2.byDisplayAverageTemperatureEnabled = usb_thermometry_basic_param.byDisplayAverageTemperatureEnabled;
        usb_thermometry_basic_param2.byTemperatureUnit = usb_thermometry_basic_param.byTemperatureUnit;
        usb_thermometry_basic_param2.byTemperatureRange = usb_thermometry_basic_param.byTemperatureRange;
        usb_thermometry_basic_param2.byCalibrationCoefficientEnabled = usb_thermometry_basic_param.byCalibrationCoefficientEnabled;
        usb_thermometry_basic_param2.dwCalibrationCoefficient = usb_thermometry_basic_param.dwCalibrationCoefficient;
        usb_thermometry_basic_param2.dwExternalOpticsWindowCorrection = usb_thermometry_basic_param.dwExternalOpticsWindowCorrection;
        usb_thermometry_basic_param2.dwEmissivity = usb_thermometry_basic_param.dwEmissivity;
        usb_thermometry_basic_param2.byDistanceUnit = usb_thermometry_basic_param.byDistanceUnit;
        usb_thermometry_basic_param2.dwDistance = usb_thermometry_basic_param.dwDistance;
        usb_thermometry_basic_param2.byReflectiveEnable = usb_thermometry_basic_param.byReflectiveEnable;
        usb_thermometry_basic_param2.dwReflectiveTemperature = usb_thermometry_basic_param.dwReflectiveTemperature;
        usb_thermometry_basic_param2.byThermomrtryInfoDisplayPosition = usb_thermometry_basic_param.byThermomrtryInfoDisplayPosition;
        usb_thermometry_basic_param2.byThermometryStreamOverlay = usb_thermometry_basic_param.byThermometryStreamOverlay;
        usb_thermometry_basic_param2.dwAlert = usb_thermometry_basic_param.dwAlert;
        usb_thermometry_basic_param2.dwAlarm = usb_thermometry_basic_param.dwAlarm;
        usb_thermometry_basic_param2.dwExternalOpticsTransmit = usb_thermometry_basic_param.dwExternalOpticsTransmit;
        usb_thermometry_basic_param2.byDisplayCenTempEnabled = usb_thermometry_basic_param.byDisplayCenTempEnabled;
        usb_thermometry_basic_param2.byBackcolorEnabled = usb_thermometry_basic_param.byBackcolorEnabled;
        usb_thermometry_basic_param2.byShowAlarmColorEnabled = usb_thermometry_basic_param.byShowAlarmColorEnabled;
        usb_thermometry_basic_param2.dwSize = usb_thermometry_basic_param2.size();
        usb_thermometry_basic_param2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_thermometry_basic_param2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_thermometry_basic_param2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_THERMOMETRY_BASIC_PARAM, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermometry_basic_param2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_THERMOMETRY_BASIC_PARAM Success! byEnabled:" + ((int) usb_thermometry_basic_param.byEnabled) + " byDisplayMaxTemperatureEnabled:" + ((int) usb_thermometry_basic_param.byDisplayMaxTemperatureEnabled) + " byDisplayMinTemperatureEnabled:" + ((int) usb_thermometry_basic_param.byDisplayMinTemperatureEnabled) + " byDisplayAverageTemperatureEnabled:" + ((int) usb_thermometry_basic_param.byDisplayAverageTemperatureEnabled) + " byTemperatureUnit:" + ((int) usb_thermometry_basic_param.byTemperatureUnit) + " byTemperatureRange:" + ((int) usb_thermometry_basic_param.byTemperatureRange) + " byCalibrationCoefficientEnabled:" + ((int) usb_thermometry_basic_param.byCalibrationCoefficientEnabled) + " dwCalibrationCoefficient:" + usb_thermometry_basic_param.dwCalibrationCoefficient + " dwExternalOpticsWindowCorrection:" + usb_thermometry_basic_param.dwExternalOpticsWindowCorrection + " dwEmissivity:" + usb_thermometry_basic_param.dwEmissivity + " byDistanceUnit:" + ((int) usb_thermometry_basic_param.byDistanceUnit) + " dwDistance:" + usb_thermometry_basic_param.dwDistance + " byReflectiveEnable:" + ((int) usb_thermometry_basic_param.byReflectiveEnable) + " dwReflectiveTemperature:" + usb_thermometry_basic_param.dwReflectiveTemperature + " byThermomrtryInfoDisplayPosition:" + ((int) usb_thermometry_basic_param.byThermomrtryInfoDisplayPosition) + " byThermometryStreamOverlay:" + ((int) usb_thermometry_basic_param.byThermometryStreamOverlay) + " dwAlert:" + usb_thermometry_basic_param.dwAlert + " dwAlarm:" + usb_thermometry_basic_param.dwAlarm + " dwExternalOpticsTransmit:" + usb_thermometry_basic_param.dwExternalOpticsTransmit + " byDisplayCenTempEnabled:" + ((int) usb_thermometry_basic_param.byDisplayCenTempEnabled) + " byBackcolorEnabled:" + ((int) usb_thermometry_basic_param.byBackcolorEnabled) + " byShowAlarmColorEnabled:" + ((int) usb_thermometry_basic_param.byShowAlarmColorEnabled));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_THERMOMETRY_BASIC_PARAM failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byEnabled:" + ((int) usb_thermometry_basic_param.byEnabled) + " byDisplayMaxTemperatureEnabled:" + ((int) usb_thermometry_basic_param.byDisplayMaxTemperatureEnabled) + " byDisplayMinTemperatureEnabled:" + ((int) usb_thermometry_basic_param.byDisplayMinTemperatureEnabled) + " byDisplayAverageTemperatureEnabled:" + ((int) usb_thermometry_basic_param.byDisplayAverageTemperatureEnabled) + " byTemperatureUnit:" + ((int) usb_thermometry_basic_param.byTemperatureUnit) + " byTemperatureRange:" + ((int) usb_thermometry_basic_param.byTemperatureRange) + " byCalibrationCoefficientEnabled:" + ((int) usb_thermometry_basic_param.byCalibrationCoefficientEnabled) + " dwCalibrationCoefficient:" + usb_thermometry_basic_param.dwCalibrationCoefficient + " dwExternalOpticsWindowCorrection:" + usb_thermometry_basic_param.dwExternalOpticsWindowCorrection + " dwEmissivity:" + usb_thermometry_basic_param.dwEmissivity + " byDistanceUnit:" + ((int) usb_thermometry_basic_param.byDistanceUnit) + " dwDistance:" + usb_thermometry_basic_param.dwDistance + " byReflectiveEnable:" + ((int) usb_thermometry_basic_param.byReflectiveEnable) + " dwReflectiveTemperature:" + usb_thermometry_basic_param.dwReflectiveTemperature + " byThermomrtryInfoDisplayPosition:" + ((int) usb_thermometry_basic_param.byThermomrtryInfoDisplayPosition) + " byThermometryStreamOverlay:" + ((int) usb_thermometry_basic_param.byThermometryStreamOverlay) + " dwAlert:" + usb_thermometry_basic_param.dwAlert + " dwAlarm:" + usb_thermometry_basic_param.dwAlarm + " dwExternalOpticsTransmit:" + usb_thermometry_basic_param.dwExternalOpticsTransmit + " byDisplayCenTempEnabled:" + ((int) usb_thermometry_basic_param.byDisplayCenTempEnabled) + " byBackcolorEnabled:" + ((int) usb_thermometry_basic_param.byBackcolorEnabled) + " byShowAlarmColorEnabled:" + ((int) usb_thermometry_basic_param.byShowAlarmColorEnabled));
        return false;
    }

    public boolean USB_GetThermometryMode(int i, USB_THERMOMETRY_MODE usb_thermometry_mode) throws IllegalAccessException {
        if (usb_thermometry_mode == null) {
            Log.e("[JavaInterface]", "USB_GetThermometryMode failed! struThermometryMode == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMOMETRY_MODE usb_thermometry_mode2 = new HCUSBSDKByJNA.USB_THERMOMETRY_MODE();
        usb_thermometry_mode2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_thermometry_mode2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_thermometry_mode2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_THERMOMETRY_MODE, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermometry_mode2.read();
            usb_thermometry_mode.byThermometryMode = usb_thermometry_mode2.byThermometryMode;
            usb_thermometry_mode.byThermometryROIEnabled = usb_thermometry_mode2.byThermometryROIEnabled;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMOMETRY_MODE Success! byThermometryMode:" + ((int) usb_thermometry_mode.byThermometryMode) + " byThermometryROIEnabled:" + ((int) usb_thermometry_mode.byThermometryROIEnabled));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMOMETRY_MODE failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byThermometryMode:" + ((int) usb_thermometry_mode.byThermometryMode) + " byThermometryROIEnabled:" + ((int) usb_thermometry_mode.byThermometryROIEnabled));
        return false;
    }

    public boolean USB_SetThermometryMode(int i, USB_THERMOMETRY_MODE usb_thermometry_mode) throws IllegalAccessException {
        if (usb_thermometry_mode == null) {
            Log.e("[JavaInterface]", "USB_SetThermometryMode failed! struThermometryMode == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMOMETRY_MODE usb_thermometry_mode2 = new HCUSBSDKByJNA.USB_THERMOMETRY_MODE();
        usb_thermometry_mode2.byThermometryMode = usb_thermometry_mode.byThermometryMode;
        usb_thermometry_mode2.byThermometryROIEnabled = usb_thermometry_mode.byThermometryROIEnabled;
        usb_thermometry_mode2.dwSize = usb_thermometry_mode2.size();
        usb_thermometry_mode2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_thermometry_mode2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_thermometry_mode2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_THERMOMETRY_MODE, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermometry_mode2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_THERMOMETRY_MODE Success! byThermometryMode:" + ((int) usb_thermometry_mode.byThermometryMode) + " byThermometryROIEnabled:" + ((int) usb_thermometry_mode.byThermometryROIEnabled));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_THERMOMETRY_MODE failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byThermometryMode:" + ((int) usb_thermometry_mode.byThermometryMode) + " byThermometryROIEnabled:" + ((int) usb_thermometry_mode.byThermometryROIEnabled));
        return false;
    }

    public boolean USB_GetThermometryRegions(int i, USB_THERMOMETRY_REGIONS usb_thermometry_regions) throws IllegalAccessException {
        if (usb_thermometry_regions == null) {
            Log.e("[JavaInterface]", "USB_GetThermometryRegions failed! struThermometryRegions == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMOMETRY_REGIONS usb_thermometry_regions2 = new HCUSBSDKByJNA.USB_THERMOMETRY_REGIONS();
        for (int i2 = 0; i2 < 10; i2++) {
            usb_thermometry_regions2.struRegion[i2] = new HCUSBSDKByJNA.THERMAL_REGION();
        }
        usb_thermometry_regions2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.bySID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_thermometry_regions2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_thermometry_regions2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_THERMOMETRY_REGIONS, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermometry_regions2.read();
            usb_thermometry_regions.byRegionNum = usb_thermometry_regions2.byRegionNum;
            for (int i3 = 0; i3 < usb_thermometry_regions2.byRegionNum; i3++) {
                usb_thermometry_regions.struRegion[i3].byRegionID = usb_thermometry_regions2.struRegion[i3].byRegionID;
                usb_thermometry_regions.struRegion[i3].byRegionEnabled = usb_thermometry_regions2.struRegion[i3].byRegionEnabled;
                usb_thermometry_regions.struRegion[i3].dwRegionX = usb_thermometry_regions2.struRegion[i3].dwRegionX;
                usb_thermometry_regions.struRegion[i3].dwRegionY = usb_thermometry_regions2.struRegion[i3].dwRegionY;
                usb_thermometry_regions.struRegion[i3].dwRegionWidth = usb_thermometry_regions2.struRegion[i3].dwRegionWidth;
                usb_thermometry_regions.struRegion[i3].dwRegionHeight = usb_thermometry_regions2.struRegion[i3].dwRegionHeight;
            }
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMOMETRY_REGIONS Success! byRegionNum:" + ((int) usb_thermometry_regions.byRegionNum));
            for (int i4 = 0; i4 < usb_thermometry_regions2.byRegionNum; i4++) {
                Log.i("[JavaInterface]", "" + i4 + " byRegionID:" + ((int) usb_thermometry_regions.struRegion[i4].byRegionID) + " byRegionEnabled:" + ((int) usb_thermometry_regions.struRegion[i4].byRegionEnabled) + " dwRegionX:" + usb_thermometry_regions.struRegion[i4].dwRegionX + " dwRegionY:" + usb_thermometry_regions.struRegion[i4].dwRegionY + " dwRegionWidth:" + usb_thermometry_regions.struRegion[i4].dwRegionWidth + " dwRegionHeight:" + usb_thermometry_regions.struRegion[i4].dwRegionHeight);
            }
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMOMETRY_REGIONS failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byRegionNum:" + ((int) usb_thermometry_regions.byRegionNum));
        for (int i5 = 0; i5 < usb_thermometry_regions2.byRegionNum; i5++) {
            Log.e("[JavaInterface]", "" + i5 + " byRegionID:" + ((int) usb_thermometry_regions.struRegion[i5].byRegionID) + " byRegionEnabled:" + ((int) usb_thermometry_regions.struRegion[i5].byRegionEnabled) + " dwRegionX:" + usb_thermometry_regions.struRegion[i5].dwRegionX + " dwRegionY:" + usb_thermometry_regions.struRegion[i5].dwRegionY + " dwRegionWidth:" + usb_thermometry_regions.struRegion[i5].dwRegionWidth + " dwRegionHeight:" + usb_thermometry_regions.struRegion[i5].dwRegionHeight);
        }
        return false;
    }

    public boolean USB_SetThermometryRegions(int i, USB_THERMOMETRY_REGIONS usb_thermometry_regions) throws IllegalAccessException {
        if (usb_thermometry_regions == null) {
            Log.e("[JavaInterface]", "USB_SetThermometryRegions failed! struThermometryRegions == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMOMETRY_REGIONS usb_thermometry_regions2 = new HCUSBSDKByJNA.USB_THERMOMETRY_REGIONS();
        for (int i2 = 0; i2 < 10; i2++) {
            usb_thermometry_regions2.struRegion[i2] = new HCUSBSDKByJNA.THERMAL_REGION();
        }
        usb_thermometry_regions2.byRegionNum = usb_thermometry_regions.byRegionNum;
        for (int i3 = 0; i3 < usb_thermometry_regions.byRegionNum; i3++) {
            usb_thermometry_regions2.struRegion[i3].byRegionID = usb_thermometry_regions.struRegion[i3].byRegionID;
            usb_thermometry_regions2.struRegion[i3].byRegionEnabled = usb_thermometry_regions.struRegion[i3].byRegionEnabled;
            usb_thermometry_regions2.struRegion[i3].dwRegionX = usb_thermometry_regions.struRegion[i3].dwRegionX;
            usb_thermometry_regions2.struRegion[i3].dwRegionY = usb_thermometry_regions.struRegion[i3].dwRegionY;
            usb_thermometry_regions2.struRegion[i3].dwRegionWidth = usb_thermometry_regions.struRegion[i3].dwRegionWidth;
            usb_thermometry_regions2.struRegion[i3].dwRegionHeight = usb_thermometry_regions.struRegion[i3].dwRegionHeight;
        }
        usb_thermometry_regions2.dwSize = usb_thermometry_regions2.size();
        usb_thermometry_regions2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.bySID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_thermometry_regions2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_thermometry_regions2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_THERMOMETRY_REGIONS, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermometry_regions2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_THERMOMETRY_REGIONS Success! byRegionNum:" + ((int) usb_thermometry_regions.byRegionNum));
            for (int i4 = 0; i4 < usb_thermometry_regions2.byRegionNum; i4++) {
                Log.i("[JavaInterface]", "" + i4 + " byRegionID:" + ((int) usb_thermometry_regions.struRegion[i4].byRegionID) + " byRegionEnabled:" + ((int) usb_thermometry_regions.struRegion[i4].byRegionEnabled) + " dwRegionX:" + usb_thermometry_regions.struRegion[i4].dwRegionX + " dwRegionY:" + usb_thermometry_regions.struRegion[i4].dwRegionY + " dwRegionWidth:" + usb_thermometry_regions.struRegion[i4].dwRegionWidth + " dwRegionHeight:" + usb_thermometry_regions.struRegion[i4].dwRegionHeight);
            }
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_THERMOMETRY_REGIONS failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byRegionNum:" + ((int) usb_thermometry_regions.byRegionNum));
        for (int i5 = 0; i5 < usb_thermometry_regions2.byRegionNum; i5++) {
            Log.e("[JavaInterface]", "" + i5 + " byRegionID:" + ((int) usb_thermometry_regions.struRegion[i5].byRegionID) + " byRegionEnabled:" + ((int) usb_thermometry_regions.struRegion[i5].byRegionEnabled) + " dwRegionX:" + usb_thermometry_regions.struRegion[i5].dwRegionX + " dwRegionY:" + usb_thermometry_regions.struRegion[i5].dwRegionY + " dwRegionWidth:" + usb_thermometry_regions.struRegion[i5].dwRegionWidth + " dwRegionHeight:" + usb_thermometry_regions.struRegion[i5].dwRegionHeight);
        }
        return false;
    }

    public boolean USB_GetThermalAlgVersion(int i, USB_THERMAL_ALG_VERSION usb_thermal_alg_version) throws IllegalAccessException {
        if (usb_thermal_alg_version == null) {
            Log.e("[JavaInterface]", "USB_GetThermalAlgVersion failed! struvThermalAlgVersion == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMAL_ALG_VERSION usb_thermal_alg_version2 = new HCUSBSDKByJNA.USB_THERMAL_ALG_VERSION();
        usb_thermal_alg_version2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_thermal_alg_version2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_thermal_alg_version2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_THERMAL_ALG_VERSION, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermal_alg_version2.read();
            try {
                usb_thermal_alg_version.szThermometryAlgName = new String(usb_thermal_alg_version2.byThermometryAlgName, Key.STRING_CHARSET_NAME);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMAL_ALG_VERSION Success! szThermometryAlgName:" + usb_thermal_alg_version.szThermometryAlgName);
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMAL_ALG_VERSION failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " szThermometryAlgName:" + usb_thermal_alg_version.szThermometryAlgName);
        return false;
    }

    public boolean USB_GetThermalStreamParam(int i, USB_THERMAL_STREAM_PARAM usb_thermal_stream_param) throws IllegalAccessException {
        if (usb_thermal_stream_param == null) {
            Log.e("[JavaInterface]", "USB_GetThermalStreamParam failed! struThermalStreamParam == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMAL_STREAM_PARAM usb_thermal_stream_param2 = new HCUSBSDKByJNA.USB_THERMAL_STREAM_PARAM();
        usb_thermal_stream_param2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_thermal_stream_param2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_thermal_stream_param2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_THERMAL_STREAM_PARAM, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermal_stream_param2.read();
            usb_thermal_stream_param.byVideoCodingType = usb_thermal_stream_param2.byVideoCodingType;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMAL_STREAM_PARAM Success! byVideoCodingType:" + ((int) usb_thermal_stream_param.byVideoCodingType));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMAL_STREAM_PARAM failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byVideoCodingType:" + ((int) usb_thermal_stream_param.byVideoCodingType));
        return false;
    }

    public boolean USB_SetThermalStreamParam(int i, USB_THERMAL_STREAM_PARAM usb_thermal_stream_param) {
        if (usb_thermal_stream_param == null) {
            Log.e("[JavaInterface]", "USB_SetThermalStreamParam failed! struThermalStreamParam == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMAL_STREAM_PARAM usb_thermal_stream_param2 = new HCUSBSDKByJNA.USB_THERMAL_STREAM_PARAM();
        usb_thermal_stream_param2.byVideoCodingType = usb_thermal_stream_param.byVideoCodingType;
        usb_thermal_stream_param2.dwSize = usb_thermal_stream_param2.size();
        usb_thermal_stream_param2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_thermal_stream_param2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_thermal_stream_param2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_THERMAL_STREAM_PARAM, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermal_stream_param2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_THERMAL_STREAM_PARAM Success! byVideoCodingType:" + ((int) usb_thermal_stream_param.byVideoCodingType));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_THERMAL_STREAM_PARAM failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byVideoCodingType:" + ((int) usb_thermal_stream_param.byVideoCodingType));
        return false;
    }

    public boolean USB_GetTemperatureCorrect(int i, USB_TEMPERATURE_CORRECT usb_temperature_correct) {
        if (usb_temperature_correct == null) {
            Log.e("[JavaInterface]", "USB_GetTemperatureCorrect failed! struTemperatureCorrect == null");
            return false;
        }
        HCUSBSDKByJNA.USB_TEMPERATURE_CORRECT usb_temperature_correct2 = new HCUSBSDKByJNA.USB_TEMPERATURE_CORRECT();
        usb_temperature_correct2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_temperature_correct2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_temperature_correct2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_TEMPERATURE_CORRECT, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_temperature_correct2.read();
            usb_temperature_correct.byEnabled = usb_temperature_correct2.byEnabled;
            usb_temperature_correct.byStreamOverlay = usb_temperature_correct2.byStreamOverlay;
            usb_temperature_correct.byCorrectEnabled = usb_temperature_correct2.byCorrectEnabled;
            usb_temperature_correct.dwEmissivity = usb_temperature_correct2.dwEmissivity;
            usb_temperature_correct.dwDistance = usb_temperature_correct2.dwDistance;
            usb_temperature_correct.dwTemperature = usb_temperature_correct2.dwTemperature;
            usb_temperature_correct.dwCentrePointX = usb_temperature_correct2.dwCentrePointX;
            usb_temperature_correct.dwCentrePointY = usb_temperature_correct2.dwCentrePointY;
            usb_temperature_correct.dwCorrectTemperature = usb_temperature_correct2.dwCorrectTemperature;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_TEMPERATURE_CORRECT Success! byEnabled:" + ((int) usb_temperature_correct.byEnabled) + " byStreamOverlay:" + ((int) usb_temperature_correct.byStreamOverlay) + " byCorrectEnabled:" + ((int) usb_temperature_correct.byCorrectEnabled) + " dwEmissivity:" + usb_temperature_correct.dwEmissivity + " dwDistance:" + usb_temperature_correct.dwDistance + " dwTemperature:" + usb_temperature_correct.dwTemperature + " dwCentrePointX:" + usb_temperature_correct.dwCentrePointX + " dwCentrePointY:" + usb_temperature_correct.dwCentrePointY + " dwCorrectTemperature:" + usb_temperature_correct.dwCorrectTemperature);
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_TEMPERATURE_CORRECT failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byEnabled:" + ((int) usb_temperature_correct.byEnabled) + " byStreamOverlay:" + ((int) usb_temperature_correct.byStreamOverlay) + " byCorrectEnabled:" + ((int) usb_temperature_correct.byCorrectEnabled) + " dwEmissivity:" + usb_temperature_correct.dwEmissivity + " dwDistance:" + usb_temperature_correct.dwDistance + " dwTemperature:" + usb_temperature_correct.dwTemperature + " dwCentrePointX:" + usb_temperature_correct.dwCentrePointX + " dwCentrePointY:" + usb_temperature_correct.dwCentrePointY + " dwCorrectTemperature:" + usb_temperature_correct.dwCorrectTemperature);
        return false;
    }

    public boolean USB_SetTemperatureCorrect(int i, USB_TEMPERATURE_CORRECT usb_temperature_correct) {
        if (usb_temperature_correct == null) {
            Log.e("[JavaInterface]", "USB_SetTemperatureCorrect failed! struTemperatureCorrect == null");
            return false;
        }
        HCUSBSDKByJNA.USB_TEMPERATURE_CORRECT usb_temperature_correct2 = new HCUSBSDKByJNA.USB_TEMPERATURE_CORRECT();
        usb_temperature_correct2.byEnabled = usb_temperature_correct.byEnabled;
        usb_temperature_correct2.byStreamOverlay = usb_temperature_correct.byStreamOverlay;
        usb_temperature_correct2.byCorrectEnabled = usb_temperature_correct.byCorrectEnabled;
        usb_temperature_correct2.dwEmissivity = usb_temperature_correct.dwEmissivity;
        usb_temperature_correct2.dwDistance = usb_temperature_correct.dwDistance;
        usb_temperature_correct2.dwTemperature = usb_temperature_correct.dwTemperature;
        usb_temperature_correct2.dwCentrePointX = usb_temperature_correct.dwCentrePointX;
        usb_temperature_correct2.dwCentrePointY = usb_temperature_correct.dwCentrePointY;
        usb_temperature_correct2.dwCorrectTemperature = usb_temperature_correct.dwCorrectTemperature;
        usb_temperature_correct2.dwSize = usb_temperature_correct2.size();
        usb_temperature_correct2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_temperature_correct2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_temperature_correct2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_TEMPERATURE_CORRECT, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_temperature_correct2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_TEMPERATURE_CORRECT Success! byEnabled:" + ((int) usb_temperature_correct.byEnabled) + " byStreamOverlay:" + ((int) usb_temperature_correct.byStreamOverlay) + " byCorrectEnabled:" + ((int) usb_temperature_correct.byCorrectEnabled) + " dwEmissivity:" + usb_temperature_correct.dwEmissivity + " dwDistance:" + usb_temperature_correct.dwDistance + " dwTemperature:" + usb_temperature_correct.dwTemperature + " dwCentrePointX:" + usb_temperature_correct.dwCentrePointX + " dwCentrePointY:" + usb_temperature_correct.dwCentrePointY + " dwCorrectTemperature:" + usb_temperature_correct.dwCorrectTemperature);
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_TEMPERATURE_CORRECT failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byEnabled:" + ((int) usb_temperature_correct.byEnabled) + " byStreamOverlay:" + ((int) usb_temperature_correct.byStreamOverlay) + " byCorrectEnabled:" + ((int) usb_temperature_correct.byCorrectEnabled) + " dwEmissivity:" + usb_temperature_correct.dwEmissivity + " dwDistance:" + usb_temperature_correct.dwDistance + " dwTemperature:" + usb_temperature_correct.dwTemperature + " dwCentrePointX:" + usb_temperature_correct.dwCentrePointX + " dwCentrePointY:" + usb_temperature_correct.dwCentrePointY + " dwCorrectTemperature:" + usb_temperature_correct.dwCorrectTemperature);
        return false;
    }

    public boolean USB_GetBlackBody(int i, USB_BLACK_BODY usb_black_body) {
        if (usb_black_body == null) {
            Log.e("[JavaInterface]", "USB_GetBlackBody failed! struBlackBody == null");
            return false;
        }
        HCUSBSDKByJNA.USB_BLACK_BODY usb_black_body2 = new HCUSBSDKByJNA.USB_BLACK_BODY();
        usb_black_body2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_black_body2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_black_body2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_BLACK_BODY, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_black_body2.read();
            usb_black_body.byEnabled = usb_black_body2.byEnabled;
            usb_black_body.dwEmissivity = usb_black_body2.dwEmissivity;
            usb_black_body.dwDistance = usb_black_body2.dwDistance;
            usb_black_body.dwTemperature = usb_black_body2.dwTemperature;
            usb_black_body.dwCentrePointX = usb_black_body2.dwCentrePointX;
            usb_black_body.dwCentrePointY = usb_black_body2.dwCentrePointY;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_BLACK_BODY Success! byEnabled:" + ((int) usb_black_body.byEnabled) + " dwEmissivity:" + usb_black_body.dwEmissivity + " dwDistance:" + usb_black_body.dwDistance + " dwTemperature:" + usb_black_body.dwTemperature + " dwCentrePointX:" + usb_black_body.dwCentrePointX + " dwCentrePointY:" + usb_black_body.dwCentrePointY);
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_BLACK_BODY failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byEnabled:" + ((int) usb_black_body.byEnabled) + " dwEmissivity:" + usb_black_body.dwEmissivity + " dwDistance:" + usb_black_body.dwDistance + " dwTemperature:" + usb_black_body.dwTemperature + " dwCentrePointX:" + usb_black_body.dwCentrePointX + " dwCentrePointY:" + usb_black_body.dwCentrePointY);
        return false;
    }

    public boolean USB_SetBlackBody(int i, USB_BLACK_BODY usb_black_body) {
        if (usb_black_body == null) {
            Log.e("[JavaInterface]", "USB_SetBlackBody failed! struBlackBody == null");
            return false;
        }
        HCUSBSDKByJNA.USB_BLACK_BODY usb_black_body2 = new HCUSBSDKByJNA.USB_BLACK_BODY();
        usb_black_body2.byEnabled = usb_black_body.byEnabled;
        usb_black_body2.dwEmissivity = usb_black_body.dwEmissivity;
        usb_black_body2.dwDistance = usb_black_body.dwDistance;
        usb_black_body2.dwTemperature = usb_black_body.dwTemperature;
        usb_black_body2.dwCentrePointX = usb_black_body.dwCentrePointX;
        usb_black_body2.dwCentrePointY = usb_black_body.dwCentrePointY;
        usb_black_body2.dwSize = usb_black_body2.size();
        usb_black_body2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_black_body2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_black_body2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_BLACK_BODY, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_black_body2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_BLACK_BODY Success! byEnabled:" + ((int) usb_black_body.byEnabled) + " dwEmissivity:" + usb_black_body.dwEmissivity + " dwDistance:" + usb_black_body.dwDistance + " dwTemperature:" + usb_black_body.dwTemperature + " dwCentrePointX:" + usb_black_body.dwCentrePointX + " dwCentrePointY:" + usb_black_body.dwCentrePointY);
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_BLACK_BODY failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byEnabled:" + ((int) usb_black_body.byEnabled) + " dwEmissivity:" + usb_black_body.dwEmissivity + " dwDistance:" + usb_black_body.dwDistance + " dwTemperature:" + usb_black_body.dwTemperature + " dwCentrePointX:" + usb_black_body.dwCentrePointX + " dwCentrePointY:" + usb_black_body.dwCentrePointY);
        return false;
    }

    public boolean USB_GetBodyTemperatureCompensation(int i, USB_BODYTEMP_COMPENSATION usb_bodytemp_compensation) {
        if (usb_bodytemp_compensation == null) {
            Log.e("[JavaInterface]", "USB_GetBodyTemperatureCompensation failed! struBodyTemperatureCompensation == null");
            return false;
        }
        HCUSBSDKByJNA.USB_BODYTEMP_COMPENSATION usb_bodytemp_compensation2 = new HCUSBSDKByJNA.USB_BODYTEMP_COMPENSATION();
        usb_bodytemp_compensation2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_bodytemp_compensation2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_bodytemp_compensation2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_BODYTEMP_COMPENSATION, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_bodytemp_compensation2.read();
            usb_bodytemp_compensation.byEnabled = usb_bodytemp_compensation2.byEnabled;
            usb_bodytemp_compensation.byType = usb_bodytemp_compensation2.byType;
            usb_bodytemp_compensation.iCompensationValue = usb_bodytemp_compensation2.iCompensationValue;
            usb_bodytemp_compensation.dwSmartCorrection = usb_bodytemp_compensation2.dwSmartCorrection;
            usb_bodytemp_compensation.dwEnvironmentalTemperature = usb_bodytemp_compensation2.dwEnvironmentalTemperature;
            usb_bodytemp_compensation.byEnvironmentalTemperatureMode = usb_bodytemp_compensation2.byEnvironmentalTemperatureMode;
            usb_bodytemp_compensation.byTemperatureCurveSensitivityLevel = usb_bodytemp_compensation2.byTemperatureCurveSensitivityLevel;
            usb_bodytemp_compensation.byEnvironmentCompensationenabled = usb_bodytemp_compensation2.byEnvironmentCompensationenabled;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_BODYTEMP_COMPENSATION Success! byEnabled:" + ((int) usb_bodytemp_compensation.byEnabled) + " byType:" + ((int) usb_bodytemp_compensation.byType) + " iCompensationValue:" + usb_bodytemp_compensation.iCompensationValue + " dwSmartCorrection:" + usb_bodytemp_compensation.dwSmartCorrection + " dwEnvironmentalTemperature:" + usb_bodytemp_compensation.dwEnvironmentalTemperature + " byEnvironmentalTemperatureMode:" + ((int) usb_bodytemp_compensation.byEnvironmentalTemperatureMode) + " byTemperatureCurveSensitivityLevel:" + ((int) usb_bodytemp_compensation.byTemperatureCurveSensitivityLevel) + " byEnvironmentCompensationenabled:" + ((int) usb_bodytemp_compensation.byEnvironmentCompensationenabled));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_BODYTEMP_COMPENSATION failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byEnabled:" + ((int) usb_bodytemp_compensation.byEnabled) + " byType:" + ((int) usb_bodytemp_compensation.byType) + " iCompensationValue:" + usb_bodytemp_compensation.iCompensationValue + " dwSmartCorrection:" + usb_bodytemp_compensation.dwSmartCorrection + " dwEnvironmentalTemperature:" + usb_bodytemp_compensation.dwEnvironmentalTemperature + " byEnvironmentalTemperatureMode:" + ((int) usb_bodytemp_compensation.byEnvironmentalTemperatureMode) + " byTemperatureCurveSensitivityLevel:" + ((int) usb_bodytemp_compensation.byTemperatureCurveSensitivityLevel) + " byEnvironmentCompensationenabled:" + ((int) usb_bodytemp_compensation.byEnvironmentCompensationenabled));
        return false;
    }

    public boolean USB_SetBodyTemperatureCompensation(int i, USB_BODYTEMP_COMPENSATION usb_bodytemp_compensation) {
        if (usb_bodytemp_compensation == null) {
            Log.e("[JavaInterface]", "USB_SetBodyTemperatureCompensation failed! struBodyTemperatureCompensation == null");
            return false;
        }
        HCUSBSDKByJNA.USB_BODYTEMP_COMPENSATION usb_bodytemp_compensation2 = new HCUSBSDKByJNA.USB_BODYTEMP_COMPENSATION();
        usb_bodytemp_compensation2.byEnabled = usb_bodytemp_compensation.byEnabled;
        usb_bodytemp_compensation2.byType = usb_bodytemp_compensation.byType;
        usb_bodytemp_compensation2.iCompensationValue = usb_bodytemp_compensation.iCompensationValue;
        usb_bodytemp_compensation2.dwSmartCorrection = usb_bodytemp_compensation.dwSmartCorrection;
        usb_bodytemp_compensation2.dwEnvironmentalTemperature = usb_bodytemp_compensation.dwEnvironmentalTemperature;
        usb_bodytemp_compensation2.byEnvironmentalTemperatureMode = usb_bodytemp_compensation.byEnvironmentalTemperatureMode;
        usb_bodytemp_compensation2.byTemperatureCurveSensitivityLevel = usb_bodytemp_compensation.byTemperatureCurveSensitivityLevel;
        usb_bodytemp_compensation2.byEnvironmentCompensationenabled = usb_bodytemp_compensation.byEnvironmentCompensationenabled;
        usb_bodytemp_compensation2.dwSize = usb_bodytemp_compensation2.size();
        usb_bodytemp_compensation2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_bodytemp_compensation2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_bodytemp_compensation2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_BODYTEMP_COMPENSATION, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_bodytemp_compensation2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_BODYTEMP_COMPENSATION Success! byEnabled:" + ((int) usb_bodytemp_compensation.byEnabled) + " byType:" + ((int) usb_bodytemp_compensation.byType) + " iCompensationValue:" + usb_bodytemp_compensation.iCompensationValue + " dwSmartCorrection:" + usb_bodytemp_compensation.dwSmartCorrection + " dwEnvironmentalTemperature:" + usb_bodytemp_compensation.dwEnvironmentalTemperature + " byEnvironmentalTemperatureMode:" + ((int) usb_bodytemp_compensation.byEnvironmentalTemperatureMode) + " byTemperatureCurveSensitivityLevel:" + ((int) usb_bodytemp_compensation.byTemperatureCurveSensitivityLevel) + " byEnvironmentCompensationenabled:" + ((int) usb_bodytemp_compensation.byEnvironmentCompensationenabled));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_BODYTEMP_COMPENSATION failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byEnabled:" + ((int) usb_bodytemp_compensation.byEnabled) + " byType:" + ((int) usb_bodytemp_compensation.byType) + " iCompensationValue:" + usb_bodytemp_compensation.iCompensationValue + " dwSmartCorrection:" + usb_bodytemp_compensation.dwSmartCorrection + " dwEnvironmentalTemperature:" + usb_bodytemp_compensation.dwEnvironmentalTemperature + " byEnvironmentalTemperatureMode:" + ((int) usb_bodytemp_compensation.byEnvironmentalTemperatureMode) + " byTemperatureCurveSensitivityLevel:" + ((int) usb_bodytemp_compensation.byTemperatureCurveSensitivityLevel) + " byEnvironmentCompensationenabled:" + ((int) usb_bodytemp_compensation.byEnvironmentCompensationenabled));
        return false;
    }

    private boolean USB_GetJpegpicWithAppendData_jna(int i, USB_JPEGPIC_WITH_APPENDDATA usb_jpegpic_with_appenddata) {
        HCUSBSDKByJNA.USB_JPEGPIC_WITH_APPENDDATA usb_jpegpic_with_appenddata2 = new HCUSBSDKByJNA.USB_JPEGPIC_WITH_APPENDDATA();
        HCUSBSDKByJNA.BYTE_ARRAY byte_array = new HCUSBSDKByJNA.BYTE_ARRAY(MAX_JEPG_DATA_SIZE);
        usb_jpegpic_with_appenddata2.dwJpegPicLen = MAX_JEPG_DATA_SIZE;
        usb_jpegpic_with_appenddata2.pJpegPic = byte_array.getPointer();
        HCUSBSDKByJNA.BYTE_ARRAY byte_array2 = new HCUSBSDKByJNA.BYTE_ARRAY(MAX_JEPG_DATA_SIZE);
        usb_jpegpic_with_appenddata2.dwP2pDataLen = MAX_JEPG_DATA_SIZE;
        usb_jpegpic_with_appenddata2.pP2pData = byte_array2.getPointer();
        usb_jpegpic_with_appenddata2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_jpegpic_with_appenddata2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_jpegpic_with_appenddata2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_JPEGPIC_WITH_APPENDDATA, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_jpegpic_with_appenddata2.read();
            byte_array.read();
            byte_array2.read();
            usb_jpegpic_with_appenddata.dwJpegPicLen = usb_jpegpic_with_appenddata2.dwJpegPicLen;
            usb_jpegpic_with_appenddata.dwJpegPicWidth = usb_jpegpic_with_appenddata2.dwJpegPicWidth;
            usb_jpegpic_with_appenddata.dwJpegPicHeight = usb_jpegpic_with_appenddata2.dwJpegPicHeight;
            usb_jpegpic_with_appenddata.dwP2pDataLen = usb_jpegpic_with_appenddata2.dwP2pDataLen;
            usb_jpegpic_with_appenddata.byIsFreezedata = usb_jpegpic_with_appenddata2.byIsFreezedata;
            usb_jpegpic_with_appenddata.byTemperatureDataLength = usb_jpegpic_with_appenddata2.byTemperatureDataLength;
            usb_jpegpic_with_appenddata.dwScale = usb_jpegpic_with_appenddata2.dwScale;
            usb_jpegpic_with_appenddata.iOffset = usb_jpegpic_with_appenddata2.iOffset;
            if (usb_jpegpic_with_appenddata2.dwJpegPicWidth > 204800 || usb_jpegpic_with_appenddata2.dwP2pDataLen > 204800) {
                Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_JPEGPIC_WITH_APPENDDATA failed!  dwDataLenth > MAX_DIAGNOSED_DATA_SIZE");
                return false;
            }
            System.arraycopy(byte_array.byValue, 0, usb_jpegpic_with_appenddata.byJpegPic, 0, usb_jpegpic_with_appenddata2.dwJpegPicLen);
            System.arraycopy(byte_array2.byValue, 0, usb_jpegpic_with_appenddata.byP2pData, 0, usb_jpegpic_with_appenddata2.dwP2pDataLen);
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_JPEGPIC_WITH_APPENDDATA Success! dwJpegPicLen:" + usb_jpegpic_with_appenddata.dwJpegPicLen + " dwJpegPicWidth:" + usb_jpegpic_with_appenddata.dwJpegPicWidth + " dwJpegPicHeight:" + usb_jpegpic_with_appenddata.dwJpegPicHeight + " dwP2pDataLen:" + usb_jpegpic_with_appenddata.dwP2pDataLen + " byIsFreezedata:" + ((int) usb_jpegpic_with_appenddata.byIsFreezedata) + " byTemperatureDataLength:" + ((int) usb_jpegpic_with_appenddata.byTemperatureDataLength) + " dwScale:" + usb_jpegpic_with_appenddata.dwScale + " iOffset:" + usb_jpegpic_with_appenddata.iOffset);
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_JPEGPIC_WITH_APPENDDATA failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwJpegPicLen:" + usb_jpegpic_with_appenddata.dwJpegPicLen + " dwJpegPicWidth:" + usb_jpegpic_with_appenddata.dwJpegPicWidth + " dwJpegPicHeight:" + usb_jpegpic_with_appenddata.dwJpegPicHeight + " dwP2pDataLen:" + usb_jpegpic_with_appenddata.dwP2pDataLen + " byIsFreezedata:" + ((int) usb_jpegpic_with_appenddata.byIsFreezedata) + " byTemperatureDataLength:" + ((int) usb_jpegpic_with_appenddata.byTemperatureDataLength) + " dwScale:" + usb_jpegpic_with_appenddata.dwScale + " iOffset:" + usb_jpegpic_with_appenddata.iOffset);
        return false;
    }

    private boolean USB_GetJpegpicWithAppendData_jni(int i, USB_JPEGPIC_WITH_APPENDDATA usb_jpegpic_with_appenddata) {
        com.hcusbsdk.jni.USB_JPEGPIC_WITH_APPENDDATA usb_jpegpic_with_appenddata2 = new com.hcusbsdk.jni.USB_JPEGPIC_WITH_APPENDDATA();
        usb_jpegpic_with_appenddata2.dwJpegPicLen = HCUSBSDKByJNI.MAX_FRAME_SIZE;
        usb_jpegpic_with_appenddata2.dwP2pDataLen = HCUSBSDKByJNI.MAX_FRAME_SIZE;
        USB_COMMON_COND usb_common_cond = new USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        if (HCUSBSDKByJNI.getInstance().USB_GetDeviceConfig(i, USB_GET_JPEGPIC_WITH_APPENDDATA, usb_common_cond, null, usb_jpegpic_with_appenddata2)) {
            usb_jpegpic_with_appenddata.dwJpegPicLen = usb_jpegpic_with_appenddata2.dwJpegPicLen;
            usb_jpegpic_with_appenddata.dwJpegPicWidth = usb_jpegpic_with_appenddata2.dwJpegPicWidth;
            usb_jpegpic_with_appenddata.dwJpegPicHeight = usb_jpegpic_with_appenddata2.dwJpegPicHeight;
            usb_jpegpic_with_appenddata.dwP2pDataLen = usb_jpegpic_with_appenddata2.dwP2pDataLen;
            usb_jpegpic_with_appenddata.byIsFreezedata = usb_jpegpic_with_appenddata2.byIsFreezedata;
            usb_jpegpic_with_appenddata.byTemperatureDataLength = usb_jpegpic_with_appenddata2.byTemperatureDataLength;
            usb_jpegpic_with_appenddata.dwScale = usb_jpegpic_with_appenddata2.dwScale;
            usb_jpegpic_with_appenddata.iOffset = usb_jpegpic_with_appenddata2.iOffset;
            if (usb_jpegpic_with_appenddata2.dwJpegPicWidth > 204800 || usb_jpegpic_with_appenddata2.dwP2pDataLen > 204800) {
                Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_JPEGPIC_WITH_APPENDDATA failed!  dwDataLenth > MAX_DIAGNOSED_DATA_SIZE");
                return false;
            }
            System.arraycopy(usb_jpegpic_with_appenddata2.pJpegPic, 0, usb_jpegpic_with_appenddata.byJpegPic, 0, usb_jpegpic_with_appenddata2.dwJpegPicLen);
            System.arraycopy(usb_jpegpic_with_appenddata2.pP2pData, 0, usb_jpegpic_with_appenddata.byP2pData, 0, usb_jpegpic_with_appenddata2.dwP2pDataLen);
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_JPEGPIC_WITH_APPENDDATA Success! dwJpegPicLen:" + usb_jpegpic_with_appenddata.dwJpegPicLen + " dwJpegPicWidth:" + usb_jpegpic_with_appenddata.dwJpegPicWidth + " dwJpegPicHeight:" + usb_jpegpic_with_appenddata.dwJpegPicHeight + " dwP2pDataLen:" + usb_jpegpic_with_appenddata.dwP2pDataLen + " byIsFreezedata:" + ((int) usb_jpegpic_with_appenddata.byIsFreezedata) + " byTemperatureDataLength:" + ((int) usb_jpegpic_with_appenddata.byTemperatureDataLength) + " dwScale:" + usb_jpegpic_with_appenddata.dwScale + " iOffset:" + usb_jpegpic_with_appenddata.iOffset);
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_JPEGPIC_WITH_APPENDDATA failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwJpegPicLen:" + usb_jpegpic_with_appenddata.dwJpegPicLen + " dwJpegPicWidth:" + usb_jpegpic_with_appenddata.dwJpegPicWidth + " dwJpegPicHeight:" + usb_jpegpic_with_appenddata.dwJpegPicHeight + " dwP2pDataLen:" + usb_jpegpic_with_appenddata.dwP2pDataLen + " byIsFreezedata:" + ((int) usb_jpegpic_with_appenddata.byIsFreezedata) + " byTemperatureDataLength:" + ((int) usb_jpegpic_with_appenddata.byTemperatureDataLength) + " dwScale:" + usb_jpegpic_with_appenddata.dwScale + " iOffset:" + usb_jpegpic_with_appenddata.iOffset);
        return false;
    }

    public boolean USB_GetJpegpicWithAppendData(int i, USB_JPEGPIC_WITH_APPENDDATA usb_jpegpic_with_appenddata) {
        if (usb_jpegpic_with_appenddata == null) {
            Log.e("[JavaInterface]", "USB_GetJpegpicWithAppendData failed! struBodyTemperatureCompensation == null");
            return false;
        }
        return USB_GetJpegpicWithAppendData_jni(i, usb_jpegpic_with_appenddata);
    }

    private boolean USB_GetROITemperatureSearch_jna(int i, USB_ROI_MAX_TEMPERATURE_SEARCH usb_roi_max_temperature_search, USB_ROI_MAX_TEMPERATURE_SEARCH_RESULT usb_roi_max_temperature_search_result) {
        if (usb_roi_max_temperature_search == null || usb_roi_max_temperature_search_result == null) {
            Log.e("[JavaInterface]", "USB_GetROITemperatureSearch_jna failed! struROITemperatureSearch == null");
            return false;
        }
        HCUSBSDKByJNA.USB_ROI_MAX_TEMPERATURE_SEARCH usb_roi_max_temperature_search2 = new HCUSBSDKByJNA.USB_ROI_MAX_TEMPERATURE_SEARCH();
        for (int i2 = 0; i2 < 10; i2++) {
            usb_roi_max_temperature_search2.struThermalROIRegion[i2] = new HCUSBSDKByJNA.THERMAL_ROI_REGION();
        }
        usb_roi_max_temperature_search2.wMillisecond = usb_roi_max_temperature_search.wMillisecond;
        usb_roi_max_temperature_search2.bySecond = usb_roi_max_temperature_search.bySecond;
        usb_roi_max_temperature_search2.byMinute = usb_roi_max_temperature_search.byMinute;
        usb_roi_max_temperature_search2.byHour = usb_roi_max_temperature_search.byHour;
        usb_roi_max_temperature_search2.byDay = usb_roi_max_temperature_search.byDay;
        usb_roi_max_temperature_search2.byMonth = usb_roi_max_temperature_search.byMonth;
        usb_roi_max_temperature_search2.wYear = usb_roi_max_temperature_search.wYear;
        usb_roi_max_temperature_search2.byJpegPicEnabled = usb_roi_max_temperature_search.byJpegPicEnabled;
        usb_roi_max_temperature_search2.byMaxTemperatureOverlay = usb_roi_max_temperature_search.byMaxTemperatureOverlay;
        usb_roi_max_temperature_search2.byRegionsOverlay = usb_roi_max_temperature_search.byRegionsOverlay;
        usb_roi_max_temperature_search2.byROIRegionNum = usb_roi_max_temperature_search.byROIRegionNum;
        usb_roi_max_temperature_search2.dwSize = usb_roi_max_temperature_search2.size();
        for (int i3 = 0; i3 < 10; i3++) {
            usb_roi_max_temperature_search2.struThermalROIRegion[i3].byROIRegionID = usb_roi_max_temperature_search.struThermalROIRegion[i3].byROIRegionID;
            usb_roi_max_temperature_search2.struThermalROIRegion[i3].byROIRegionEnabled = usb_roi_max_temperature_search.struThermalROIRegion[i3].byROIRegionEnabled;
            usb_roi_max_temperature_search2.struThermalROIRegion[i3].dwROIRegionX = usb_roi_max_temperature_search.struThermalROIRegion[i3].dwROIRegionX;
            usb_roi_max_temperature_search2.struThermalROIRegion[i3].dwROIRegionY = usb_roi_max_temperature_search.struThermalROIRegion[i3].dwROIRegionY;
            usb_roi_max_temperature_search2.struThermalROIRegion[i3].dwROIRegionWidth = usb_roi_max_temperature_search.struThermalROIRegion[i3].dwROIRegionWidth;
            usb_roi_max_temperature_search2.struThermalROIRegion[i3].dwROIRegionHeight = usb_roi_max_temperature_search.struThermalROIRegion[i3].dwROIRegionHeight;
            usb_roi_max_temperature_search2.struThermalROIRegion[i3].dwDistance = usb_roi_max_temperature_search.struThermalROIRegion[i3].dwDistance;
        }
        usb_roi_max_temperature_search2.write();
        HCUSBSDKByJNA.USB_ROI_MAX_TEMPERATURE_SEARCH_RESULT usb_roi_max_temperature_search_result2 = new HCUSBSDKByJNA.USB_ROI_MAX_TEMPERATURE_SEARCH_RESULT();
        for (int i4 = 0; i4 < 10; i4++) {
            usb_roi_max_temperature_search_result2.struThermalROIRegionInfo[i4] = new HCUSBSDKByJNA.THERMAL_ROI_REGION_INFO();
        }
        HCUSBSDKByJNA.BYTE_ARRAY byte_array = new HCUSBSDKByJNA.BYTE_ARRAY(MAX_JEPG_DATA_SIZE);
        usb_roi_max_temperature_search_result2.dwJpegPicLen = usb_roi_max_temperature_search_result.dwJpegPicLen;
        usb_roi_max_temperature_search_result2.pJpegPic = byte_array.getPointer();
        usb_roi_max_temperature_search_result2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_roi_max_temperature_search2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_roi_max_temperature_search2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_roi_max_temperature_search_result2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_roi_max_temperature_search_result2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_ROI_MAX_TEMPERATURE_SEARCH, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_roi_max_temperature_search_result2.read();
            byte_array.read();
            usb_roi_max_temperature_search_result.dwMaxP2PTemperature = usb_roi_max_temperature_search_result2.dwMaxP2PTemperature;
            usb_roi_max_temperature_search_result.dwVisibleP2PMaxTemperaturePointX = usb_roi_max_temperature_search_result2.dwVisibleP2PMaxTemperaturePointX;
            usb_roi_max_temperature_search_result.dwVisibleP2PMaxTemperaturePointY = usb_roi_max_temperature_search_result2.dwVisibleP2PMaxTemperaturePointY;
            usb_roi_max_temperature_search_result.dwThermalP2PMaxTemperaturePointX = usb_roi_max_temperature_search_result2.dwThermalP2PMaxTemperaturePointX;
            usb_roi_max_temperature_search_result.dwThermalP2PMaxTemperaturePointY = usb_roi_max_temperature_search_result2.dwThermalP2PMaxTemperaturePointY;
            usb_roi_max_temperature_search_result.byROIRegionNum = usb_roi_max_temperature_search_result2.byROIRegionNum;
            usb_roi_max_temperature_search_result.dwJpegPicLen = usb_roi_max_temperature_search_result2.dwJpegPicLen;
            if (usb_roi_max_temperature_search_result2.dwJpegPicLen > 204800) {
                Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_ROI_MAX_TEMPERATURE_SEARCH failed!  dwJpegPicLen > MAX_JEPG_DATA_SIZE");
                return false;
            }
            System.arraycopy(byte_array.byValue, 0, usb_roi_max_temperature_search_result.byJpegPic, 0, usb_roi_max_temperature_search_result2.dwJpegPicLen);
            for (int i5 = 0; i5 < 10; i5++) {
                usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i5].byROIRegionID = usb_roi_max_temperature_search_result2.struThermalROIRegionInfo[i5].byROIRegionID;
                usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i5].dwMaxROIRegionTemperature = usb_roi_max_temperature_search_result2.struThermalROIRegionInfo[i5].dwMaxROIRegionTemperature;
                usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i5].dwVisibleROIRegionMaxTemperaturePointX = usb_roi_max_temperature_search_result2.struThermalROIRegionInfo[i5].dwVisibleROIRegionMaxTemperaturePointX;
                usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i5].dwVisibleROIRegionMaxTemperaturePointY = usb_roi_max_temperature_search_result2.struThermalROIRegionInfo[i5].dwVisibleROIRegionMaxTemperaturePointY;
                usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i5].dwThermalROIRegionMaxTemperaturePointX = usb_roi_max_temperature_search_result2.struThermalROIRegionInfo[i5].dwThermalROIRegionMaxTemperaturePointX;
                usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i5].dwThermalROIRegionMaxTemperaturePointY = usb_roi_max_temperature_search_result2.struThermalROIRegionInfo[i5].dwThermalROIRegionMaxTemperaturePointY;
            }
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_ROI_MAX_TEMPERATURE_SEARCH Success! dwMaxP2PTemperature:" + usb_roi_max_temperature_search_result.dwMaxP2PTemperature + " dwVisibleP2PMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.dwVisibleP2PMaxTemperaturePointX + " dwVisibleP2PMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.dwVisibleP2PMaxTemperaturePointY + " dwThermalP2PMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.dwThermalP2PMaxTemperaturePointX + " dwThermalP2PMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.dwThermalP2PMaxTemperaturePointY + " byROIRegionNum:" + ((int) usb_roi_max_temperature_search_result.byROIRegionNum) + " dwScale:" + usb_roi_max_temperature_search_result.dwJpegPicLen);
            for (int i6 = 0; i6 < usb_roi_max_temperature_search_result.byROIRegionNum; i6++) {
                Log.i("[JavaInterface]", "" + i6 + " byROIRegionID:" + ((int) usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i6].byROIRegionID) + " dwMaxROIRegionTemperature:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i6].dwMaxROIRegionTemperature + " dwVisibleROIRegionMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i6].dwVisibleROIRegionMaxTemperaturePointX + " dwVisibleROIRegionMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i6].dwVisibleROIRegionMaxTemperaturePointY + " dwThermalROIRegionMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i6].dwThermalROIRegionMaxTemperaturePointX + " dwThermalROIRegionMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i6].dwThermalROIRegionMaxTemperaturePointY);
            }
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_ROI_MAX_TEMPERATURE_SEARCH failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwMaxP2PTemperature:" + usb_roi_max_temperature_search_result.dwMaxP2PTemperature + " dwVisibleP2PMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.dwVisibleP2PMaxTemperaturePointX + " dwVisibleP2PMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.dwVisibleP2PMaxTemperaturePointY + " dwThermalP2PMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.dwThermalP2PMaxTemperaturePointX + " dwThermalP2PMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.dwThermalP2PMaxTemperaturePointY + " byROIRegionNum:" + ((int) usb_roi_max_temperature_search_result.byROIRegionNum) + " dwScale:" + usb_roi_max_temperature_search_result.dwJpegPicLen);
        for (int i7 = 0; i7 < usb_roi_max_temperature_search_result.byROIRegionNum; i7++) {
            Log.e("[JavaInterface]", "" + i7 + " byROIRegionID:" + ((int) usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i7].byROIRegionID) + " dwMaxROIRegionTemperature:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i7].dwMaxROIRegionTemperature + " dwVisibleROIRegionMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i7].dwVisibleROIRegionMaxTemperaturePointX + " dwVisibleROIRegionMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i7].dwVisibleROIRegionMaxTemperaturePointY + " dwThermalROIRegionMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i7].dwThermalROIRegionMaxTemperaturePointX + " dwThermalROIRegionMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i7].dwThermalROIRegionMaxTemperaturePointY);
        }
        return false;
    }

    private boolean USB_GetROITemperatureSearch_jni(int i, USB_ROI_MAX_TEMPERATURE_SEARCH usb_roi_max_temperature_search, USB_ROI_MAX_TEMPERATURE_SEARCH_RESULT usb_roi_max_temperature_search_result) {
        if (usb_roi_max_temperature_search == null || usb_roi_max_temperature_search_result == null) {
            Log.e("[JavaInterface]", "USB_GetROITemperatureSearch_jni failed! struROITemperatureSearch == null");
            return false;
        }
        com.hcusbsdk.jni.USB_ROI_MAX_TEMPERATURE_SEARCH usb_roi_max_temperature_search2 = new com.hcusbsdk.jni.USB_ROI_MAX_TEMPERATURE_SEARCH();
        usb_roi_max_temperature_search2.wMillisecond = usb_roi_max_temperature_search.wMillisecond;
        usb_roi_max_temperature_search2.bySecond = usb_roi_max_temperature_search.bySecond;
        usb_roi_max_temperature_search2.byMinute = usb_roi_max_temperature_search.byMinute;
        usb_roi_max_temperature_search2.byHour = usb_roi_max_temperature_search.byHour;
        usb_roi_max_temperature_search2.byDay = usb_roi_max_temperature_search.byDay;
        usb_roi_max_temperature_search2.byMonth = usb_roi_max_temperature_search.byMonth;
        usb_roi_max_temperature_search2.wYear = usb_roi_max_temperature_search.wYear;
        usb_roi_max_temperature_search2.byJpegPicEnabled = usb_roi_max_temperature_search.byJpegPicEnabled;
        usb_roi_max_temperature_search2.byMaxTemperatureOverlay = usb_roi_max_temperature_search.byMaxTemperatureOverlay;
        usb_roi_max_temperature_search2.byRegionsOverlay = usb_roi_max_temperature_search.byRegionsOverlay;
        usb_roi_max_temperature_search2.byROIRegionNum = usb_roi_max_temperature_search.byROIRegionNum;
        for (int i2 = 0; i2 < 10; i2++) {
            usb_roi_max_temperature_search2.struThermalROIRegion[i2].byROIRegionID = usb_roi_max_temperature_search.struThermalROIRegion[i2].byROIRegionID;
            usb_roi_max_temperature_search2.struThermalROIRegion[i2].byROIRegionEnabled = usb_roi_max_temperature_search.struThermalROIRegion[i2].byROIRegionEnabled;
            usb_roi_max_temperature_search2.struThermalROIRegion[i2].dwROIRegionX = usb_roi_max_temperature_search.struThermalROIRegion[i2].dwROIRegionX;
            usb_roi_max_temperature_search2.struThermalROIRegion[i2].dwROIRegionY = usb_roi_max_temperature_search.struThermalROIRegion[i2].dwROIRegionY;
            usb_roi_max_temperature_search2.struThermalROIRegion[i2].dwROIRegionWidth = usb_roi_max_temperature_search.struThermalROIRegion[i2].dwROIRegionWidth;
            usb_roi_max_temperature_search2.struThermalROIRegion[i2].dwROIRegionHeight = usb_roi_max_temperature_search.struThermalROIRegion[i2].dwROIRegionHeight;
            usb_roi_max_temperature_search2.struThermalROIRegion[i2].dwDistance = usb_roi_max_temperature_search.struThermalROIRegion[i2].dwDistance;
        }
        com.hcusbsdk.jni.USB_ROI_MAX_TEMPERATURE_SEARCH_RESULT usb_roi_max_temperature_search_result2 = new com.hcusbsdk.jni.USB_ROI_MAX_TEMPERATURE_SEARCH_RESULT();
        usb_roi_max_temperature_search_result2.dwJpegPicLen = usb_roi_max_temperature_search_result.dwJpegPicLen;
        USB_COMMON_COND usb_common_cond = new USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        if (HCUSBSDKByJNI.getInstance().USB_GetDeviceConfig(i, USB_GET_ROI_MAX_TEMPERATURE_SEARCH, usb_common_cond, usb_roi_max_temperature_search2, usb_roi_max_temperature_search_result2)) {
            usb_roi_max_temperature_search_result.dwMaxP2PTemperature = usb_roi_max_temperature_search_result2.dwMaxP2PTemperature;
            usb_roi_max_temperature_search_result.dwVisibleP2PMaxTemperaturePointX = usb_roi_max_temperature_search_result2.dwVisibleP2PMaxTemperaturePointX;
            usb_roi_max_temperature_search_result.dwVisibleP2PMaxTemperaturePointY = usb_roi_max_temperature_search_result2.dwVisibleP2PMaxTemperaturePointY;
            usb_roi_max_temperature_search_result.dwThermalP2PMaxTemperaturePointX = usb_roi_max_temperature_search_result2.dwThermalP2PMaxTemperaturePointX;
            usb_roi_max_temperature_search_result.dwThermalP2PMaxTemperaturePointY = usb_roi_max_temperature_search_result2.dwThermalP2PMaxTemperaturePointY;
            usb_roi_max_temperature_search_result.byROIRegionNum = usb_roi_max_temperature_search_result2.byROIRegionNum;
            usb_roi_max_temperature_search_result.dwJpegPicLen = usb_roi_max_temperature_search_result2.dwJpegPicLen;
            if (usb_roi_max_temperature_search_result2.dwJpegPicLen > 204800) {
                Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_ROI_MAX_TEMPERATURE_SEARCH failed!  dwJpegPicLen > MAX_JEPG_DATA_SIZE");
                return false;
            }
            System.arraycopy(usb_roi_max_temperature_search_result2.pJpegPic, 0, usb_roi_max_temperature_search_result.byJpegPic, 0, usb_roi_max_temperature_search_result2.dwJpegPicLen);
            for (int i3 = 0; i3 < 10; i3++) {
                usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i3].byROIRegionID = usb_roi_max_temperature_search_result2.struThermalROIRegionInfo[i3].byROIRegionID;
                usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i3].dwMaxROIRegionTemperature = usb_roi_max_temperature_search_result2.struThermalROIRegionInfo[i3].dwMaxROIRegionTemperature;
                usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i3].dwVisibleROIRegionMaxTemperaturePointX = usb_roi_max_temperature_search_result2.struThermalROIRegionInfo[i3].dwVisibleROIRegionMaxTemperaturePointX;
                usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i3].dwVisibleROIRegionMaxTemperaturePointY = usb_roi_max_temperature_search_result2.struThermalROIRegionInfo[i3].dwVisibleROIRegionMaxTemperaturePointY;
                usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i3].dwThermalROIRegionMaxTemperaturePointX = usb_roi_max_temperature_search_result2.struThermalROIRegionInfo[i3].dwThermalROIRegionMaxTemperaturePointX;
                usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i3].dwThermalROIRegionMaxTemperaturePointY = usb_roi_max_temperature_search_result2.struThermalROIRegionInfo[i3].dwThermalROIRegionMaxTemperaturePointY;
            }
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_ROI_MAX_TEMPERATURE_SEARCH Success! dwMaxP2PTemperature:" + usb_roi_max_temperature_search_result.dwMaxP2PTemperature + " dwVisibleP2PMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.dwVisibleP2PMaxTemperaturePointX + " dwVisibleP2PMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.dwVisibleP2PMaxTemperaturePointY + " dwThermalP2PMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.dwThermalP2PMaxTemperaturePointX + " dwThermalP2PMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.dwThermalP2PMaxTemperaturePointY + " byROIRegionNum:" + ((int) usb_roi_max_temperature_search_result.byROIRegionNum) + " dwJpegPicLen:" + usb_roi_max_temperature_search_result.dwJpegPicLen);
            for (int i4 = 0; i4 < usb_roi_max_temperature_search_result.byROIRegionNum; i4++) {
                Log.i("[JavaInterface]", "" + i4 + " byROIRegionID:" + ((int) usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i4].byROIRegionID) + " dwMaxROIRegionTemperature:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i4].dwMaxROIRegionTemperature + " dwVisibleROIRegionMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i4].dwVisibleROIRegionMaxTemperaturePointX + " dwVisibleROIRegionMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i4].dwVisibleROIRegionMaxTemperaturePointY + " dwThermalROIRegionMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i4].dwThermalROIRegionMaxTemperaturePointX + " dwThermalROIRegionMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i4].dwThermalROIRegionMaxTemperaturePointY);
            }
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_ROI_MAX_TEMPERATURE_SEARCH failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwMaxP2PTemperature:" + usb_roi_max_temperature_search_result.dwMaxP2PTemperature + " dwVisibleP2PMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.dwVisibleP2PMaxTemperaturePointX + " dwVisibleP2PMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.dwVisibleP2PMaxTemperaturePointY + " dwThermalP2PMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.dwThermalP2PMaxTemperaturePointX + " dwThermalP2PMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.dwThermalP2PMaxTemperaturePointY + " byROIRegionNum:" + ((int) usb_roi_max_temperature_search_result.byROIRegionNum) + " dwJpegPicLen:" + usb_roi_max_temperature_search_result.dwJpegPicLen);
        for (int i5 = 0; i5 < usb_roi_max_temperature_search_result.byROIRegionNum; i5++) {
            Log.e("[JavaInterface]", "" + i5 + " byROIRegionID:" + ((int) usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i5].byROIRegionID) + " dwMaxROIRegionTemperature:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i5].dwMaxROIRegionTemperature + " dwVisibleROIRegionMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i5].dwVisibleROIRegionMaxTemperaturePointX + " dwVisibleROIRegionMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i5].dwVisibleROIRegionMaxTemperaturePointY + " dwThermalROIRegionMaxTemperaturePointX:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i5].dwThermalROIRegionMaxTemperaturePointX + " dwThermalROIRegionMaxTemperaturePointY:" + usb_roi_max_temperature_search_result.struThermalROIRegionInfo[i5].dwThermalROIRegionMaxTemperaturePointY);
        }
        return false;
    }

    public boolean USB_GetROITemperatureSearch(int i, USB_ROI_MAX_TEMPERATURE_SEARCH usb_roi_max_temperature_search, USB_ROI_MAX_TEMPERATURE_SEARCH_RESULT usb_roi_max_temperature_search_result) {
        if (usb_roi_max_temperature_search == null || usb_roi_max_temperature_search_result == null) {
            Log.e("[JavaInterface]", "USB_GetROITemperatureSearch failed! struROITemperatureSearch == null");
            return false;
        }
        return USB_GetROITemperatureSearch_jni(i, usb_roi_max_temperature_search, usb_roi_max_temperature_search_result);
    }

    public boolean USB_GetP2pParam(int i, USB_P2P_PARAM usb_p2p_param) {
        if (usb_p2p_param == null) {
            Log.e("[JavaInterface]", "USB_GetP2pParam failed! struP2pParam == null");
            return false;
        }
        HCUSBSDKByJNA.USB_P2P_PARAM usb_p2p_param2 = new HCUSBSDKByJNA.USB_P2P_PARAM();
        usb_p2p_param2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_p2p_param2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_p2p_param2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, 2048, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_p2p_param2.read();
            usb_p2p_param.byJpegPicEnabled = usb_p2p_param2.byJpegPicEnabled;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_P2P_PARAM Success! byJpegPicEnabled:" + ((int) usb_p2p_param.byJpegPicEnabled));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_P2P_PARAM failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byJpegPicEnabled:" + ((int) usb_p2p_param.byJpegPicEnabled));
        return false;
    }

    public boolean USB_SetP2pParam(int i, USB_P2P_PARAM usb_p2p_param) {
        if (usb_p2p_param == null) {
            Log.e("[JavaInterface]", "USB_SetP2pParam failed! struP2pParam == null");
            return false;
        }
        HCUSBSDKByJNA.USB_P2P_PARAM usb_p2p_param2 = new HCUSBSDKByJNA.USB_P2P_PARAM();
        usb_p2p_param2.byJpegPicEnabled = usb_p2p_param.byJpegPicEnabled;
        usb_p2p_param2.dwSize = usb_p2p_param2.size();
        usb_p2p_param2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_p2p_param2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_p2p_param2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_P2P_PARAM, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_p2p_param2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_P2P_PARAM Success! byJpegPicEnabled:" + ((int) usb_p2p_param.byJpegPicEnabled));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_P2P_PARAM failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byJpegPicEnabled:" + ((int) usb_p2p_param.byJpegPicEnabled));
        return false;
    }

    public boolean USB_GetThermometryCalibrationFile(int i, USB_THERMOMETRY_CALIBRATION_FILE usb_thermometry_calibration_file) {
        if (usb_thermometry_calibration_file == null) {
            Log.e("[JavaInterface]", "USB_GetThermometryCalibrationFile failed! struThermometryCalibrationFile == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMOMETRY_CALIBRATION_FILE usb_thermometry_calibration_file2 = new HCUSBSDKByJNA.USB_THERMOMETRY_CALIBRATION_FILE();
        HCUSBSDKByJNA.BYTE_ARRAY byte_array = new HCUSBSDKByJNA.BYTE_ARRAY(1048576);
        usb_thermometry_calibration_file2.pCalibrationFile = byte_array.getPointer();
        usb_thermometry_calibration_file2.dwFileLenth = 1048576;
        usb_thermometry_calibration_file2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_thermometry_calibration_file2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_thermometry_calibration_file2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_THERMOMETRY_CALIBRATION_FILE, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermometry_calibration_file2.read();
            byte_array.read();
            usb_thermometry_calibration_file.dwFileLenth = usb_thermometry_calibration_file2.dwFileLenth;
            if (usb_thermometry_calibration_file2.dwFileLenth > 1048576) {
                Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_ROI_MAX_TEMPERATURE_SEARCH failed!  dwFileLenth > MAX_DIAGNOSED_DATA_SIZE");
                return false;
            }
            System.arraycopy(byte_array.byValue, 0, usb_thermometry_calibration_file.pCalibrationFile, 0, usb_thermometry_calibration_file2.dwFileLenth);
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMOMETRY_CALIBRATION_FILE Success! dwFileLenth:" + usb_thermometry_calibration_file.dwFileLenth);
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMOMETRY_CALIBRATION_FILE failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwFileLenth:" + usb_thermometry_calibration_file.dwFileLenth);
        return false;
    }

    public boolean USB_SetThermometryCalibrationFile(int i, USB_THERMOMETRY_CALIBRATION_FILE usb_thermometry_calibration_file) {
        if (usb_thermometry_calibration_file == null) {
            Log.e("[JavaInterface]", "USB_SetThermometryCalibrationFile failed! struThermometryCalibrationFile == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMOMETRY_CALIBRATION_FILE usb_thermometry_calibration_file2 = new HCUSBSDKByJNA.USB_THERMOMETRY_CALIBRATION_FILE();
        HCUSBSDKByJNA.BYTE_ARRAY byte_array = new HCUSBSDKByJNA.BYTE_ARRAY(1048576);
        System.arraycopy(usb_thermometry_calibration_file.pCalibrationFile, 0, byte_array.byValue, 0, usb_thermometry_calibration_file.dwFileLenth);
        byte_array.write();
        usb_thermometry_calibration_file2.pCalibrationFile = byte_array.getPointer();
        usb_thermometry_calibration_file2.dwFileLenth = usb_thermometry_calibration_file.dwFileLenth;
        System.arraycopy(usb_thermometry_calibration_file.szFileName.getBytes(), 0, usb_thermometry_calibration_file2.byFileName, 0, usb_thermometry_calibration_file.szFileName.length());
        usb_thermometry_calibration_file2.dwSize = usb_thermometry_calibration_file2.size();
        usb_thermometry_calibration_file2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_thermometry_calibration_file2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_thermometry_calibration_file2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_THERMOMETRY_CALIBRATION_FILE, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermometry_calibration_file2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_THERMOMETRY_CALIBRATION_FILE Success! dwFileLenth:" + usb_thermometry_calibration_file.dwFileLenth + " byFileName:" + new String(usb_thermometry_calibration_file2.byFileName) + " dwSize:" + usb_thermometry_calibration_file2.byFileName.length);
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_THERMOMETRY_CALIBRATION_FILE failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwFileLenth:" + usb_thermometry_calibration_file.dwFileLenth + " byFileName:" + new String(usb_thermometry_calibration_file2.byFileName) + " dwSize:" + usb_thermometry_calibration_file2.byFileName.length);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0182 A[LOOP:3: B:36:0x0180->B:37:0x0182, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean USB_GetThermometryExpertRegions(int r24, com.hcusbsdk.Interface.USB_THERMOMETRY_EXPERT_REGIONS r25) {
        /*
            Method dump skipped, instructions count: 958
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hcusbsdk.Interface.JavaInterface.USB_GetThermometryExpertRegions(int, com.hcusbsdk.Interface.USB_THERMOMETRY_EXPERT_REGIONS):boolean");
    }

    public boolean USB_SetThermometryExpertRegions(int i, USB_THERMOMETRY_EXPERT_REGIONS usb_thermometry_expert_regions) {
        if (usb_thermometry_expert_regions == null) {
            Log.e("[JavaInterface]", "USB_SetThermometryExpertRegions failed! struThermometryExpertRegions == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMOMETRY_EXPERT_REGIONS usb_thermometry_expert_regions2 = new HCUSBSDKByJNA.USB_THERMOMETRY_EXPERT_REGIONS();
        usb_thermometry_expert_regions2.byRegionNum = usb_thermometry_expert_regions.byRegionNum;
        for (int i2 = 0; i2 < usb_thermometry_expert_regions2.byRegionNum; i2++) {
            usb_thermometry_expert_regions2.struExpertRegions[i2] = new HCUSBSDKByJNA.THERMAL_EXPERT_REGIONS();
            usb_thermometry_expert_regions2.struExpertRegions[i2].byRegionID = usb_thermometry_expert_regions.struExpertRegions[i2].byRegionID;
            usb_thermometry_expert_regions2.struExpertRegions[i2].byEnabled = usb_thermometry_expert_regions.struExpertRegions[i2].byEnabled;
            System.arraycopy(usb_thermometry_expert_regions.struExpertRegions[i2].szName.getBytes(), 0, usb_thermometry_expert_regions2.struExpertRegions[i2].byName, 0, usb_thermometry_expert_regions.struExpertRegions[i2].szName.length());
            usb_thermometry_expert_regions2.struExpertRegions[i2].dwEmissivity = usb_thermometry_expert_regions.struExpertRegions[i2].dwEmissivity;
            usb_thermometry_expert_regions2.struExpertRegions[i2].dwDistance = usb_thermometry_expert_regions.struExpertRegions[i2].dwDistance;
            usb_thermometry_expert_regions2.struExpertRegions[i2].byReflectiveEnable = usb_thermometry_expert_regions.struExpertRegions[i2].byReflectiveEnable;
            usb_thermometry_expert_regions2.struExpertRegions[i2].dwReflectiveTemperature = usb_thermometry_expert_regions.struExpertRegions[i2].dwReflectiveTemperature;
            usb_thermometry_expert_regions2.struExpertRegions[i2].byType = usb_thermometry_expert_regions.struExpertRegions[i2].byType;
            usb_thermometry_expert_regions2.struExpertRegions[i2].byShowAlarmColorEnabled = usb_thermometry_expert_regions.struExpertRegions[i2].byShowAlarmColorEnabled;
            usb_thermometry_expert_regions2.struExpertRegions[i2].byRule = usb_thermometry_expert_regions.struExpertRegions[i2].byRule;
            usb_thermometry_expert_regions2.struExpertRegions[i2].dwAlert = usb_thermometry_expert_regions.struExpertRegions[i2].dwAlert;
            usb_thermometry_expert_regions2.struExpertRegions[i2].dwAlarm = usb_thermometry_expert_regions.struExpertRegions[i2].dwAlarm;
            usb_thermometry_expert_regions2.struExpertRegions[i2].byPointNum = usb_thermometry_expert_regions.struExpertRegions[i2].byPointNum;
            usb_thermometry_expert_regions2.struExpertRegions[i2].byRegionID = usb_thermometry_expert_regions.struExpertRegions[i2].byRegionID;
            for (int i3 = 0; i3 < 10; i3++) {
                usb_thermometry_expert_regions2.struExpertRegions[i2].struRegionCoordinate[i3] = new HCUSBSDKByJNA.REGION_VERTEX_COORDINATES();
                usb_thermometry_expert_regions2.struExpertRegions[i2].struRegionCoordinate[i3].dwPointX = usb_thermometry_expert_regions.struExpertRegions[i2].struRegionCoordinate[i3].dwPointX;
                usb_thermometry_expert_regions2.struExpertRegions[i2].struRegionCoordinate[i3].dwPointY = usb_thermometry_expert_regions.struExpertRegions[i2].struRegionCoordinate[i3].dwPointY;
            }
        }
        usb_thermometry_expert_regions2.dwSize = usb_thermometry_expert_regions2.size();
        usb_thermometry_expert_regions2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.bySID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_thermometry_expert_regions2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_thermometry_expert_regions2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        String str = " dwEmissivity:";
        String str2 = " byRegionID:";
        String str3 = " dwAlert:";
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_THERMOMETRY_EXPERT_REGIONS, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermometry_expert_regions2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_THERMOMETRY_EXPERT_REGIONS Success! byRegionNum:" + ((int) usb_thermometry_expert_regions.byRegionNum));
            int i4 = 0;
            while (i4 < usb_thermometry_expert_regions2.byRegionNum) {
                Log.i("[JavaInterface]", "" + i4 + str2 + ((int) usb_thermometry_expert_regions.struExpertRegions[i4].byRegionID) + " byEnabled:" + ((int) usb_thermometry_expert_regions.struExpertRegions[i4].byEnabled) + " byName:" + usb_thermometry_expert_regions.struExpertRegions[i4].szName + " dwEmissivity:" + usb_thermometry_expert_regions.struExpertRegions[i4].dwEmissivity + " dwDistance:" + usb_thermometry_expert_regions.struExpertRegions[i4].dwDistance + " byReflectiveEnable:" + ((int) usb_thermometry_expert_regions.struExpertRegions[i4].byReflectiveEnable) + " dwReflectiveTemperature:" + usb_thermometry_expert_regions.struExpertRegions[i4].dwReflectiveTemperature + " byType:" + ((int) usb_thermometry_expert_regions.struExpertRegions[i4].byType) + " byShowAlarmColorEnabled:" + ((int) usb_thermometry_expert_regions.struExpertRegions[i4].byShowAlarmColorEnabled) + " byRule:" + ((int) usb_thermometry_expert_regions.struExpertRegions[i4].byRule) + str3 + usb_thermometry_expert_regions.struExpertRegions[i4].dwAlert + " dwAlarm:" + usb_thermometry_expert_regions.struExpertRegions[i4].dwAlarm + " byPointNum:" + ((int) usb_thermometry_expert_regions.struExpertRegions[i4].byPointNum));
                i4++;
                str2 = str2;
            }
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_THERMOMETRY_EXPERT_REGIONS failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byRegionNum:" + ((int) usb_thermometry_expert_regions.byRegionNum));
        int i5 = 0;
        while (i5 < usb_thermometry_expert_regions2.byRegionNum) {
            String str4 = str3;
            Log.e("[JavaInterface]", "" + i5 + " byRegionID:" + ((int) usb_thermometry_expert_regions.struExpertRegions[i5].byRegionID) + " byEnabled:" + ((int) usb_thermometry_expert_regions.struExpertRegions[i5].byEnabled) + " byName:" + usb_thermometry_expert_regions.struExpertRegions[i5].szName + str + usb_thermometry_expert_regions.struExpertRegions[i5].dwEmissivity + " dwDistance:" + usb_thermometry_expert_regions.struExpertRegions[i5].dwDistance + " byReflectiveEnable:" + ((int) usb_thermometry_expert_regions.struExpertRegions[i5].byReflectiveEnable) + " dwReflectiveTemperature:" + usb_thermometry_expert_regions.struExpertRegions[i5].dwReflectiveTemperature + " byType:" + ((int) usb_thermometry_expert_regions.struExpertRegions[i5].byType) + " byShowAlarmColorEnabled:" + ((int) usb_thermometry_expert_regions.struExpertRegions[i5].byShowAlarmColorEnabled) + " byRule:" + ((int) usb_thermometry_expert_regions.struExpertRegions[i5].byRule) + str4 + usb_thermometry_expert_regions.struExpertRegions[i5].dwAlert + " dwAlarm:" + usb_thermometry_expert_regions.struExpertRegions[i5].dwAlarm + " byPointNum:" + ((int) usb_thermometry_expert_regions.struExpertRegions[i5].byPointNum));
            i5++;
            usb_thermometry_expert_regions2 = usb_thermometry_expert_regions2;
            str = str;
            str3 = str4;
        }
        return false;
    }

    public boolean USB_GetExpertCorrectionParam(int i, USB_THERMOMETRY_EXPERT_CORRECTION_PARAM usb_thermometry_expert_correction_param) {
        if (usb_thermometry_expert_correction_param == null) {
            Log.e("[JavaInterface]", "USB_GetExpertCorrectionParam failed! struExpertCorrectionParam == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMOMETRY_EXPERT_CORRECTION_PARAM usb_thermometry_expert_correction_param2 = new HCUSBSDKByJNA.USB_THERMOMETRY_EXPERT_CORRECTION_PARAM();
        for (int i2 = 0; i2 < 4; i2++) {
            usb_thermometry_expert_correction_param2.struExpertTemperature[i2] = new HCUSBSDKByJNA.THERMAL_EXPERT_TEMPERATURE();
        }
        usb_thermometry_expert_correction_param2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_thermometry_expert_correction_param2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_thermometry_expert_correction_param2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_EXPERT_CORRECTION_PARAM, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermometry_expert_correction_param2.read();
            usb_thermometry_expert_correction_param.dwDistance = usb_thermometry_expert_correction_param2.dwDistance;
            usb_thermometry_expert_correction_param.dwEnviroTemperature = usb_thermometry_expert_correction_param2.dwEnviroTemperature;
            usb_thermometry_expert_correction_param.dwEmissivity = usb_thermometry_expert_correction_param2.dwEmissivity;
            usb_thermometry_expert_correction_param.byPointNum = usb_thermometry_expert_correction_param2.byPointNum;
            for (int i3 = 0; i3 < 4; i3++) {
                usb_thermometry_expert_correction_param.struExpertTemperature[i3].byID = usb_thermometry_expert_correction_param2.struExpertTemperature[i3].byID;
                usb_thermometry_expert_correction_param.struExpertTemperature[i3].dwPresetTemperature = usb_thermometry_expert_correction_param2.struExpertTemperature[i3].dwPresetTemperature;
                usb_thermometry_expert_correction_param.struExpertTemperature[i3].dwPointX = usb_thermometry_expert_correction_param2.struExpertTemperature[i3].dwPointX;
                usb_thermometry_expert_correction_param.struExpertTemperature[i3].dwPointY = usb_thermometry_expert_correction_param2.struExpertTemperature[i3].dwPointY;
            }
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_EXPERT_CORRECTION_PARAM Success! dwDistance:" + usb_thermometry_expert_correction_param.dwDistance + " dwEnviroTemperature:" + usb_thermometry_expert_correction_param.dwEnviroTemperature + " dwEmissivity:" + usb_thermometry_expert_correction_param.dwEmissivity + " byPointNum:" + ((int) usb_thermometry_expert_correction_param.byPointNum));
            for (int i4 = 0; i4 < 4; i4++) {
                Log.i("[JavaInterface]", "" + i4 + " byID:" + ((int) usb_thermometry_expert_correction_param.struExpertTemperature[i4].byID) + " dwPresetTemperature:" + usb_thermometry_expert_correction_param.struExpertTemperature[i4].dwPresetTemperature + " dwPointX:" + usb_thermometry_expert_correction_param.struExpertTemperature[i4].dwPointX + " dwPointY:" + usb_thermometry_expert_correction_param.struExpertTemperature[i4].dwPointY);
            }
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_EXPERT_CORRECTION_PARAM failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwDistance:" + usb_thermometry_expert_correction_param.dwDistance + " dwEnviroTemperature:" + usb_thermometry_expert_correction_param.dwEnviroTemperature + " dwEmissivity:" + usb_thermometry_expert_correction_param.dwEmissivity + " byPointNum:" + ((int) usb_thermometry_expert_correction_param.byPointNum));
        for (int i5 = 0; i5 < 4; i5++) {
            Log.e("[JavaInterface]", "" + i5 + " byID:" + ((int) usb_thermometry_expert_correction_param.struExpertTemperature[i5].byID) + " dwPresetTemperature:" + usb_thermometry_expert_correction_param.struExpertTemperature[i5].dwPresetTemperature + " dwPointX:" + usb_thermometry_expert_correction_param.struExpertTemperature[i5].dwPointX + " dwPointY:" + usb_thermometry_expert_correction_param.struExpertTemperature[i5].dwPointY);
        }
        return false;
    }

    public boolean USB_SetExpertCorrectionParam(int i, USB_THERMOMETRY_EXPERT_CORRECTION_PARAM usb_thermometry_expert_correction_param) {
        if (usb_thermometry_expert_correction_param == null) {
            Log.e("[JavaInterface]", "USB_SetExpertCorrectionParam failed! struExpertCorrectionParam == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMOMETRY_EXPERT_CORRECTION_PARAM usb_thermometry_expert_correction_param2 = new HCUSBSDKByJNA.USB_THERMOMETRY_EXPERT_CORRECTION_PARAM();
        usb_thermometry_expert_correction_param2.dwDistance = usb_thermometry_expert_correction_param.dwDistance;
        usb_thermometry_expert_correction_param2.dwEnviroTemperature = usb_thermometry_expert_correction_param.dwEnviroTemperature;
        usb_thermometry_expert_correction_param2.dwEmissivity = usb_thermometry_expert_correction_param.dwEmissivity;
        usb_thermometry_expert_correction_param2.byPointNum = usb_thermometry_expert_correction_param.byPointNum;
        for (int i2 = 0; i2 < 4; i2++) {
            usb_thermometry_expert_correction_param2.struExpertTemperature[i2] = new HCUSBSDKByJNA.THERMAL_EXPERT_TEMPERATURE();
            usb_thermometry_expert_correction_param2.struExpertTemperature[i2].byID = usb_thermometry_expert_correction_param.struExpertTemperature[i2].byID;
            usb_thermometry_expert_correction_param2.struExpertTemperature[i2].dwPresetTemperature = usb_thermometry_expert_correction_param.struExpertTemperature[i2].dwPresetTemperature;
            usb_thermometry_expert_correction_param2.struExpertTemperature[i2].dwPointX = usb_thermometry_expert_correction_param.struExpertTemperature[i2].dwPointX;
            usb_thermometry_expert_correction_param2.struExpertTemperature[i2].dwPointY = usb_thermometry_expert_correction_param.struExpertTemperature[i2].dwPointY;
        }
        usb_thermometry_expert_correction_param2.dwSize = usb_thermometry_expert_correction_param2.size();
        usb_thermometry_expert_correction_param2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_thermometry_expert_correction_param2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_thermometry_expert_correction_param2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_EXPERT_CORRECTION_PARAM, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermometry_expert_correction_param2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_EXPERT_CORRECTION_PARAM Success! dwDistance:" + usb_thermometry_expert_correction_param.dwDistance + " dwEnviroTemperature:" + usb_thermometry_expert_correction_param.dwEnviroTemperature + " dwEmissivity:" + usb_thermometry_expert_correction_param.dwEmissivity + " byPointNum:" + ((int) usb_thermometry_expert_correction_param.byPointNum));
            for (int i3 = 0; i3 < 4; i3++) {
                Log.i("[JavaInterface]", "" + i3 + " byID:" + ((int) usb_thermometry_expert_correction_param.struExpertTemperature[i3].byID) + " dwPresetTemperature:" + usb_thermometry_expert_correction_param.struExpertTemperature[i3].dwPresetTemperature + " dwPointX:" + usb_thermometry_expert_correction_param.struExpertTemperature[i3].dwPointX + " dwPointY:" + usb_thermometry_expert_correction_param.struExpertTemperature[i3].dwPointY);
            }
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_EXPERT_CORRECTION_PARAM failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " dwDistance:" + usb_thermometry_expert_correction_param.dwDistance + " dwEnviroTemperature:" + usb_thermometry_expert_correction_param.dwEnviroTemperature + " dwEmissivity:" + usb_thermometry_expert_correction_param.dwEmissivity + " byPointNum:" + ((int) usb_thermometry_expert_correction_param.byPointNum));
        for (int i4 = 0; i4 < 4; i4++) {
            Log.e("[JavaInterface]", "" + i4 + " byID:" + ((int) usb_thermometry_expert_correction_param.struExpertTemperature[i4].byID) + " dwPresetTemperature:" + usb_thermometry_expert_correction_param.struExpertTemperature[i4].dwPresetTemperature + " dwPointX:" + usb_thermometry_expert_correction_param.struExpertTemperature[i4].dwPointX + " dwPointY:" + usb_thermometry_expert_correction_param.struExpertTemperature[i4].dwPointY);
        }
        return false;
    }

    public boolean USB_StartExpertCorrection(int i) {
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONTROL_INPUT_INFO usb_control_input_info = new HCUSBSDKByJNA.USB_CONTROL_INPUT_INFO();
        usb_control_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_control_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_control_input_info.write();
        if (HCUSBSDK.getInstance().USB_Control(i, USB_START_EXPERT_CORRECTION, usb_control_input_info.getPointer())) {
            Log.i("[JavaInterface]", "USB_Control USB_START_EXPERT_CORRECTION Success!");
            return true;
        }
        Log.e("[JavaInterface]", "USB_Control USB_START_EXPERT_CORRECTION failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
        return false;
    }

    public boolean USB_SetImageWDR(int i, USB_IMAGE_WDR usb_image_wdr) {
        if (usb_image_wdr == null) {
            Log.e("[JavaInterface]", "USB_SetImageWDR failed! struImageWDR == null");
            return false;
        }
        HCUSBSDKByJNA.USB_IMAGE_WDR usb_image_wdr2 = new HCUSBSDKByJNA.USB_IMAGE_WDR();
        usb_image_wdr2.dwSize = usb_image_wdr2.size();
        usb_image_wdr2.byEnabled = usb_image_wdr.byEnabled;
        usb_image_wdr2.byMode = usb_image_wdr.byMode;
        usb_image_wdr2.byLevel = usb_image_wdr.byLevel;
        usb_image_wdr2.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpInBuffer = usb_image_wdr2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_image_wdr2.size();
        usb_config_input_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_IMAGE_WDR, usb_config_input_info.getPointer(), null)) {
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_IMAGE_WDR Success!");
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_IMAGE_WDR failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
        return false;
    }

    public boolean USB_GetAudioInStatus(int i, USB_AUDIO_STATUS usb_audio_status) {
        if (usb_audio_status == null) {
            Log.e("[JavaInterface]", "USB_GetAudioInStatus failed! struAudioStatus == null");
            return false;
        }
        HCUSBSDKByJNA.USB_AUDIO_STATUS usb_audio_status2 = new HCUSBSDKByJNA.USB_AUDIO_STATUS();
        usb_audio_status2.dwSize = usb_audio_status2.size();
        usb_audio_status2.byChannelID = usb_audio_status.byChannelID;
        usb_audio_status2.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpInBuffer = usb_audio_status2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_audio_status2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_audio_status2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_audio_status2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_AUDIO_IN_STATUS, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_audio_status2.read();
            usb_audio_status.byChannelID = usb_audio_status2.byConnectStatus;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_AUDIO_IN_STATUS Success!");
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_AUDIO_IN_STATUS failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
        return false;
    }

    public int USB_DownloadLogData(int i, String str) {
        int iUSB_FileTransfer = HCUSBSDK.getInstance().USB_FileTransfer(i, USB_GET_SYSTEM_LOG_DATA, str);
        if (iUSB_FileTransfer > 0) {
            Log.i("[JavaInterface]", "USB_DownloadLogData Success! Handle:" + iUSB_FileTransfer + " FileName:" + str);
        } else {
            Log.e("[JavaInterface]", "USB_DownloadLogData failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " Handle:" + iUSB_FileTransfer + " FileName:" + str);
        }
        return iUSB_FileTransfer;
    }

    public int USB_DownloadAudioData(int i, String str) {
        int iUSB_FileTransfer = HCUSBSDK.getInstance().USB_FileTransfer(i, USB_GET_AUDIO_DUMP_DATA, str);
        if (iUSB_FileTransfer > 0) {
            Log.i("[JavaInterface]", "USB_DownloadAudioData Success! Handle:" + iUSB_FileTransfer + " FileName:" + str);
        } else {
            Log.e("[JavaInterface]", "USB_DownloadAudioData failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " Handle:" + iUSB_FileTransfer + " FileName:" + str);
        }
        return iUSB_FileTransfer;
    }

    public int USB_UploadEncryptData(int i, String str) {
        int iUSB_FileTransfer = HCUSBSDK.getInstance().USB_FileTransfer(i, USB_SET_SYSTEM_ENCRYPT_DATA, str);
        if (iUSB_FileTransfer > 0) {
            Log.i("[JavaInterface]", "USB_UploadEncryptData Success! Handle:" + iUSB_FileTransfer + " FileName:" + str);
        } else {
            Log.e("[JavaInterface]", "USB_UploadEncryptData failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " Handle:" + iUSB_FileTransfer + " FileName:" + str);
        }
        return iUSB_FileTransfer;
    }

    public boolean USB_GetRiseSettings(int i, USB_THERMOMETRY_RISE_SETTINGS usb_thermometry_rise_settings) {
        if (usb_thermometry_rise_settings == null) {
            Log.e("[JavaInterface]", "USB_GetRiseSettings failed! struRiseSettings == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMOMETRY_RISE_SETTINGS usb_thermometry_rise_settings2 = new HCUSBSDKByJNA.USB_THERMOMETRY_RISE_SETTINGS();
        usb_thermometry_rise_settings2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_thermometry_rise_settings2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_thermometry_rise_settings2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_THERMOMETRY_RISE_SETTINGS, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermometry_rise_settings2.read();
            usb_thermometry_rise_settings.byEnabled = usb_thermometry_rise_settings2.byEnabled;
            usb_thermometry_rise_settings.byType = usb_thermometry_rise_settings2.byType;
            usb_thermometry_rise_settings.byResult = usb_thermometry_rise_settings2.byResult;
            usb_thermometry_rise_settings.dwEnvTemperature = usb_thermometry_rise_settings2.dwEnvTemperature;
            usb_thermometry_rise_settings.dwCoefficient = usb_thermometry_rise_settings2.dwCoefficient;
            usb_thermometry_rise_settings.dwMaxTemperatureRise = usb_thermometry_rise_settings2.dwMaxTemperatureRise;
            usb_thermometry_rise_settings.dwColdStartRate = usb_thermometry_rise_settings2.dwColdStartRate;
            usb_thermometry_rise_settings.dwColdStartRise = usb_thermometry_rise_settings2.dwColdStartRise;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMOMETRY_RISE_SETTINGS Success! byEnabled:" + ((int) usb_thermometry_rise_settings.byEnabled) + " byType:" + ((int) usb_thermometry_rise_settings.byType) + " byResult:" + ((int) usb_thermometry_rise_settings.byResult) + " dwEnvTemperature:" + usb_thermometry_rise_settings.dwEnvTemperature + " dwCoefficient:" + usb_thermometry_rise_settings.dwCoefficient + " dwMaxTemperatureRise:" + usb_thermometry_rise_settings.dwMaxTemperatureRise + " dwColdStartRate:" + usb_thermometry_rise_settings.dwColdStartRate + " dwColdStartRise:" + usb_thermometry_rise_settings.dwColdStartRise);
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_THERMOMETRY_RISE_SETTINGS failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byEnabled:" + ((int) usb_thermometry_rise_settings.byEnabled) + " byType:" + ((int) usb_thermometry_rise_settings.byType) + " byResult:" + ((int) usb_thermometry_rise_settings.byResult) + " dwEnvTemperature:" + usb_thermometry_rise_settings.dwEnvTemperature + " dwCoefficient:" + usb_thermometry_rise_settings.dwCoefficient + " dwMaxTemperatureRise:" + usb_thermometry_rise_settings.dwMaxTemperatureRise + " dwColdStartRate:" + usb_thermometry_rise_settings.dwColdStartRate + " dwColdStartRise:" + usb_thermometry_rise_settings.dwColdStartRise);
        return false;
    }

    public boolean USB_SetRiseSettings(int i, USB_THERMOMETRY_RISE_SETTINGS usb_thermometry_rise_settings) {
        if (usb_thermometry_rise_settings == null) {
            Log.e("[JavaInterface]", "USB_SetRiseSettings failed! struRiseSettings == null");
            return false;
        }
        HCUSBSDKByJNA.USB_THERMOMETRY_RISE_SETTINGS usb_thermometry_rise_settings2 = new HCUSBSDKByJNA.USB_THERMOMETRY_RISE_SETTINGS();
        usb_thermometry_rise_settings2.byEnabled = usb_thermometry_rise_settings.byEnabled;
        usb_thermometry_rise_settings2.byType = usb_thermometry_rise_settings.byType;
        usb_thermometry_rise_settings2.byResult = usb_thermometry_rise_settings.byResult;
        usb_thermometry_rise_settings2.dwEnvTemperature = usb_thermometry_rise_settings.dwEnvTemperature;
        usb_thermometry_rise_settings2.dwCoefficient = usb_thermometry_rise_settings.dwCoefficient;
        usb_thermometry_rise_settings2.dwMaxTemperatureRise = usb_thermometry_rise_settings.dwMaxTemperatureRise;
        usb_thermometry_rise_settings2.dwColdStartRate = usb_thermometry_rise_settings.dwColdStartRate;
        usb_thermometry_rise_settings2.dwColdStartRise = usb_thermometry_rise_settings.dwColdStartRise;
        usb_thermometry_rise_settings2.dwSize = usb_thermometry_rise_settings2.size();
        usb_thermometry_rise_settings2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_thermometry_rise_settings2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_thermometry_rise_settings2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_THERMOMETRY_RISE_SETTINGS, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_thermometry_rise_settings2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_GET_THERMOMETRY_RISE_SETTINGS Success! byEnabled:" + ((int) usb_thermometry_rise_settings.byEnabled) + " byType:" + ((int) usb_thermometry_rise_settings.byType) + " byResult:" + ((int) usb_thermometry_rise_settings.byResult) + " dwEnvTemperature:" + usb_thermometry_rise_settings.dwEnvTemperature + " dwCoefficient:" + usb_thermometry_rise_settings.dwCoefficient + " dwMaxTemperatureRise:" + usb_thermometry_rise_settings.dwMaxTemperatureRise + " dwColdStartRate:" + usb_thermometry_rise_settings.dwColdStartRate + " dwColdStartRise:" + usb_thermometry_rise_settings.dwColdStartRise);
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_GET_THERMOMETRY_RISE_SETTINGS failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byEnabled:" + ((int) usb_thermometry_rise_settings.byEnabled) + " byType:" + ((int) usb_thermometry_rise_settings.byType) + " byResult:" + ((int) usb_thermometry_rise_settings.byResult) + " dwEnvTemperature:" + usb_thermometry_rise_settings.dwEnvTemperature + " dwCoefficient:" + usb_thermometry_rise_settings.dwCoefficient + " dwMaxTemperatureRise:" + usb_thermometry_rise_settings.dwMaxTemperatureRise + " dwColdStartRate:" + usb_thermometry_rise_settings.dwColdStartRate + " dwColdStartRise:" + usb_thermometry_rise_settings.dwColdStartRise);
        return false;
    }

    public boolean USB_GetEnvirotemperatureCorrect(int i, USB_ENVIROTEMPERATURE_CORRECT usb_envirotemperature_correct) {
        if (usb_envirotemperature_correct == null) {
            Log.e("[JavaInterface]", "USB_GetEnvirotemperatureCorrect failed! struEnvirotemperatureCorrect == null");
            return false;
        }
        HCUSBSDKByJNA.USB_ENVIROTEMPERATURE_CORRECT usb_envirotemperature_correct2 = new HCUSBSDKByJNA.USB_ENVIROTEMPERATURE_CORRECT();
        usb_envirotemperature_correct2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_envirotemperature_correct2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_envirotemperature_correct2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, USB_GET_ENVIROTEMPERATURE_CORRECT, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_envirotemperature_correct2.read();
            usb_envirotemperature_correct.byEnabled = usb_envirotemperature_correct2.byEnabled;
            usb_envirotemperature_correct.byCorrectEnabled = usb_envirotemperature_correct2.byCorrectEnabled;
            usb_envirotemperature_correct.dwEnviroTemperature = usb_envirotemperature_correct2.dwEnviroTemperature;
            usb_envirotemperature_correct.dwCalibrationTemperature = usb_envirotemperature_correct2.dwCalibrationTemperature;
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_ENVIROTEMPERATURE_CORRECT Success! byEnabled:" + ((int) usb_envirotemperature_correct.byEnabled) + " byCorrectEnabled:" + ((int) usb_envirotemperature_correct.byCorrectEnabled) + " dwEnviroTemperature:" + usb_envirotemperature_correct.dwEnviroTemperature + " dwCalibrationTemperature:" + usb_envirotemperature_correct.dwCalibrationTemperature);
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_ENVIROTEMPERATURE_CORRECT failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byEnabled:" + ((int) usb_envirotemperature_correct.byEnabled) + " byCorrectEnabled:" + ((int) usb_envirotemperature_correct.byCorrectEnabled) + " dwEnviroTemperature:" + usb_envirotemperature_correct.dwEnviroTemperature + " dwCalibrationTemperature:" + usb_envirotemperature_correct.dwCalibrationTemperature);
        return false;
    }

    public boolean USB_SetEnvirotemperatureCorrect(int i, USB_ENVIROTEMPERATURE_CORRECT usb_envirotemperature_correct) {
        if (usb_envirotemperature_correct == null) {
            Log.e("[JavaInterface]", "USB_SetEnvirotemperatureCorrect failed! struEnvirotemperatureCorrect == null");
            return false;
        }
        HCUSBSDKByJNA.USB_ENVIROTEMPERATURE_CORRECT usb_envirotemperature_correct2 = new HCUSBSDKByJNA.USB_ENVIROTEMPERATURE_CORRECT();
        usb_envirotemperature_correct2.byEnabled = usb_envirotemperature_correct.byEnabled;
        usb_envirotemperature_correct2.byCorrectEnabled = usb_envirotemperature_correct.byCorrectEnabled;
        usb_envirotemperature_correct2.dwEnviroTemperature = usb_envirotemperature_correct.dwEnviroTemperature;
        usb_envirotemperature_correct2.dwCalibrationTemperature = usb_envirotemperature_correct.dwCalibrationTemperature;
        usb_envirotemperature_correct2.dwSize = usb_envirotemperature_correct2.size();
        usb_envirotemperature_correct2.write();
        HCUSBSDKByJNA.USB_COMMON_COND usb_common_cond = new HCUSBSDKByJNA.USB_COMMON_COND();
        usb_common_cond.byChannelID = (byte) 1;
        usb_common_cond.write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpCondBuffer = usb_common_cond.getPointer();
        usb_config_input_info.dwCondBufferSize = usb_common_cond.size();
        usb_config_input_info.lpInBuffer = usb_envirotemperature_correct2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_envirotemperature_correct2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_ENVIROTEMPERATURE_CORRECT, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_envirotemperature_correct2.read();
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_ENVIROTEMPERATURE_CORRECT Success! byEnabled:" + ((int) usb_envirotemperature_correct.byEnabled) + " byCorrectEnabled:" + ((int) usb_envirotemperature_correct.byCorrectEnabled) + " dwEnviroTemperature:" + usb_envirotemperature_correct.dwEnviroTemperature + " dwCalibrationTemperature:" + usb_envirotemperature_correct.dwCalibrationTemperature);
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_ENVIROTEMPERATURE_CORRECT failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byEnabled:" + ((int) usb_envirotemperature_correct.byEnabled) + " byCorrectEnabled:" + ((int) usb_envirotemperature_correct.byCorrectEnabled) + " dwEnviroTemperature:" + usb_envirotemperature_correct.dwEnviroTemperature + " dwCalibrationTemperature:" + usb_envirotemperature_correct.dwCalibrationTemperature);
        return false;
    }

    public boolean USB_GetCommandState(int i, USB_COMMAND_STATE usb_command_state) {
        if (usb_command_state == null) {
            Log.e("[JavaInterface]", "USB_GetCommandState failed! struCommandState == null");
            return false;
        }
        HCUSBSDKByJNA.USB_COMMAND_STATE usb_command_state2 = new HCUSBSDKByJNA.USB_COMMAND_STATE();
        usb_command_state2.dwSize = usb_command_state2.size();
        usb_command_state2.write();
        if (HCUSBSDK.getInstance().USB_GetCommandState(i, usb_command_state2.getPointer())) {
            usb_command_state2.read();
            usb_command_state.dwSize = usb_command_state2.dwSize;
            usb_command_state.byState = usb_command_state2.byState;
            Log.i("[JavaInterface]", "USB_GetCommandState Success! byState:" + ((int) usb_command_state.byState));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetCommandState failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
        return false;
    }

    public boolean USB_SetBeepAndFlicker(int i, USB_BEEP_AND_FLICKER usb_beep_and_flicker) {
        if (usb_beep_and_flicker == null) {
            Log.e("[JavaInterface]", "USB_SetBeepAndFlicker failed! struBeepAndFlicker == null");
            return false;
        }
        HCUSBSDKByJNA.USB_BEEP_AND_FLICKER usb_beep_and_flicker2 = new HCUSBSDKByJNA.USB_BEEP_AND_FLICKER();
        usb_beep_and_flicker2.dwSize = usb_beep_and_flicker2.size();
        usb_beep_and_flicker2.byBeepType = usb_beep_and_flicker.byBeepType;
        usb_beep_and_flicker2.byBeepCount = usb_beep_and_flicker.byBeepCount;
        usb_beep_and_flicker2.byFlickerType = usb_beep_and_flicker.byFlickerType;
        usb_beep_and_flicker2.byFlickerCount = usb_beep_and_flicker.byFlickerCount;
        usb_beep_and_flicker2.write();
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpInBuffer = usb_beep_and_flicker2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_beep_and_flicker2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, 1000, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_BEEP_AND_FLICKER Success!byBeepType:" + ((int) usb_beep_and_flicker.byBeepType) + "byBeepCount:" + ((int) usb_beep_and_flicker.byBeepCount) + "byFlickerType:" + ((int) usb_beep_and_flicker.byFlickerType) + "byFlickerCount:" + ((int) usb_beep_and_flicker.byFlickerCount));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_BEEP_AND_FLICKER failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + "byBeepType:" + ((int) usb_beep_and_flicker.byBeepType) + "byBeepCount:" + ((int) usb_beep_and_flicker.byBeepCount) + "byFlickerType:" + ((int) usb_beep_and_flicker.byFlickerType) + "byFlickerCount:" + ((int) usb_beep_and_flicker.byFlickerCount));
        return false;
    }

    public boolean USB_GetCardIssueVersion(int i, USB_CARD_ISSUE_VERSION usb_card_issue_version) {
        if (usb_card_issue_version == null) {
            Log.e("[JavaInterface]", "USB_GetCardIssueVersion failed! struCardIssueVersion == null");
            return false;
        }
        HCUSBSDKByJNA.USB_CARD_ISSUE_VERSION usb_card_issue_version2 = new HCUSBSDKByJNA.USB_CARD_ISSUE_VERSION();
        usb_card_issue_version2.write();
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_card_issue_version2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_card_issue_version2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, 1001, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_card_issue_version2.read();
            usb_card_issue_version.dwSoftwareVersion = usb_card_issue_version2.dwSoftwareVersion;
            usb_card_issue_version.wYear = usb_card_issue_version2.wYear;
            usb_card_issue_version.byMonth = usb_card_issue_version2.byMonth;
            usb_card_issue_version.byDay = usb_card_issue_version2.byDay;
            usb_card_issue_version.byLanguage = usb_card_issue_version2.byLanguage;
            try {
                usb_card_issue_version.szDeviceName = new String(usb_card_issue_version2.szDeviceName, Key.STRING_CHARSET_NAME);
                usb_card_issue_version.szSerialNumber = new String(usb_card_issue_version2.szSerialNumber, Key.STRING_CHARSET_NAME);
            } catch (UnsupportedEncodingException unused) {
                usb_card_issue_version.szDeviceName = new String();
                usb_card_issue_version.szSerialNumber = new String();
            }
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_CARD_ISSUE_VERSION Success! szDeviceName:" + usb_card_issue_version.szDeviceName + " szSerialNumber:" + usb_card_issue_version.szSerialNumber + " dwSoftwareVersion:" + usb_card_issue_version.dwSoftwareVersion + " wYear:" + ((int) usb_card_issue_version.wYear) + " byMonth:" + ((int) usb_card_issue_version.byMonth) + " byDay:" + ((int) usb_card_issue_version.byDay) + " byLanguage:" + ((int) usb_card_issue_version.byLanguage));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_CARD_ISSUE_VERSION failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " szDeviceName:" + usb_card_issue_version.szDeviceName + " szSerialNumber:" + usb_card_issue_version.szSerialNumber + " dwSoftwareVersion:" + usb_card_issue_version.dwSoftwareVersion + " wYear:" + ((int) usb_card_issue_version.wYear) + " byMonth:" + ((int) usb_card_issue_version.byMonth) + " byDay:" + ((int) usb_card_issue_version.byDay) + " byLanguage:" + ((int) usb_card_issue_version.byLanguage));
        return false;
    }

    public boolean USB_SetCardProto(int i, USB_CARD_PROTO usb_card_proto) {
        if (usb_card_proto == null) {
            Log.e("[JavaInterface]", "USB_SetCardProto failed! struCardProto == null");
            return false;
        }
        HCUSBSDKByJNA.USB_CARD_PROTO usb_card_proto2 = new HCUSBSDKByJNA.USB_CARD_PROTO();
        usb_card_proto2.byProto = (byte) usb_card_proto.wProto;
        usb_card_proto2.write();
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpInBuffer = usb_card_proto2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_card_proto2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, 1003, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_CARD_PROTO Success! byProto:" + ((int) usb_card_proto.wProto));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_CARD_PROTO failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byProto:" + ((int) usb_card_proto.wProto));
        return false;
    }

    public boolean USB_GetActivateCard(int i, USB_WAIT_SECOND usb_wait_second, USB_ACTIVATE_CARD_RES usb_activate_card_res) {
        if (usb_wait_second == null || usb_activate_card_res == null) {
            Log.e("[JavaInterface]", "USB_GetCardIssueVersion failed! struWaitSecond == null || struActivateCardRes == null");
            return false;
        }
        HCUSBSDKByJNA.USB_WAIT_SECOND usb_wait_second2 = new HCUSBSDKByJNA.USB_WAIT_SECOND();
        usb_wait_second2.dwSize = usb_wait_second2.size();
        usb_wait_second2.byWait = usb_wait_second.byWait;
        usb_wait_second2.write();
        HCUSBSDKByJNA.USB_ACTIVATE_CARD_RES usb_activate_card_res2 = new HCUSBSDKByJNA.USB_ACTIVATE_CARD_RES();
        usb_activate_card_res2.write();
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpInBuffer = usb_wait_second2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_wait_second2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_activate_card_res2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_activate_card_res2.size();
        usb_config_output_info.write();
        if (!HCUSBSDK.getInstance().USB_GetDeviceConfig(i, 1004, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            return false;
        }
        usb_config_output_info.read();
        usb_activate_card_res2.read();
        usb_activate_card_res.byCardType = usb_activate_card_res2.byCardType;
        usb_activate_card_res.bySerialLen = usb_activate_card_res2.bySerialLen;
        usb_activate_card_res.bySelectVerifyLen = usb_activate_card_res2.bySelectVerifyLen;
        System.arraycopy(usb_activate_card_res2.bySelectVerify, 0, usb_activate_card_res.bySelectVerify, 0, 3);
        for (int i2 = 0; i2 < 10; i2++) {
            usb_activate_card_res.dwSerial[i2] = usb_activate_card_res2.bySerial[i2] & UByte.MAX_VALUE;
        }
        Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_ACTIVATE_CARD Success! byWait:" + ((int) usb_wait_second.byWait) + " byCardType:" + ((int) usb_activate_card_res.byCardType) + " bySerialLen:" + ((int) usb_activate_card_res.bySerialLen) + " bySerial1:" + Integer.toHexString(usb_activate_card_res.dwSerial[0]) + " bySerial2:" + Integer.toHexString(usb_activate_card_res.dwSerial[1]) + " bySerial3:" + Integer.toHexString(usb_activate_card_res.dwSerial[2]) + " bySerial4:" + Integer.toHexString(usb_activate_card_res.dwSerial[3]) + " bySerial5:" + Integer.toHexString(usb_activate_card_res.dwSerial[4]) + " bySerial6:" + Integer.toHexString(usb_activate_card_res.dwSerial[5]) + " bySerial7:" + Integer.toHexString(usb_activate_card_res.dwSerial[6]) + " bySerial8:" + Integer.toHexString(usb_activate_card_res.dwSerial[7]) + " bySerial9:" + Integer.toHexString(usb_activate_card_res.dwSerial[8]) + " bySerial10:" + Integer.toHexString(usb_activate_card_res.dwSerial[9]) + " bySelectVerifyLen:" + ((int) usb_activate_card_res.bySelectVerifyLen) + " bySelectVerify[0]:" + ((int) usb_activate_card_res.bySelectVerify[0]) + " bySelectVerify[1]:" + ((int) usb_activate_card_res.bySelectVerify[1]) + " bySelectVerify[2]:" + ((int) usb_activate_card_res.bySelectVerify[2]));
        return true;
    }

    public boolean USB_StopCardOperation(int i) {
        if (HCUSBSDK.getInstance().USB_Control(i, 1005, Pointer.NULL)) {
            Log.i("[JavaInterface]", "USB_Control USB_SET_SYSTEM_REBOOT Success!");
            return true;
        }
        Log.e("[JavaInterface]", "USB_Control USB_CTRL_STOP_CARD_OPER failed! error:" + HCUSBSDK.getInstance().USB_GetLastError());
        return false;
    }

    public boolean USB_GetCertificateInfo(int i, USB_CERTIFICATE_INFO usb_certificate_info) {
        if (usb_certificate_info == null) {
            Log.e("[JavaInterface]", "USB_GetCertificateInfo failed! struCertificateInfo == null");
            return false;
        }
        HCUSBSDKByJNA.USB_CERTIFICATE_INFO usb_certificate_info2 = new HCUSBSDKByJNA.USB_CERTIFICATE_INFO();
        usb_certificate_info2.write();
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_certificate_info2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_certificate_info2.size();
        usb_config_output_info.write();
        if (!HCUSBSDK.getInstance().USB_GetDeviceConfig(i, 1020, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            return false;
        }
        usb_config_output_info.read();
        usb_certificate_info2.read();
        usb_certificate_info.wWordInfoSize = usb_certificate_info2.wWordInfoSize;
        usb_certificate_info.wPicInfoSize = usb_certificate_info2.wPicInfoSize;
        usb_certificate_info.wFingerPrintInfoSize = usb_certificate_info2.wFingerPrintInfoSize;
        usb_certificate_info.byCertificateType = usb_certificate_info2.byCertificateType;
        System.arraycopy(usb_certificate_info2.byWordInfo, 0, usb_certificate_info.byWordInfo, 0, 256);
        System.arraycopy(usb_certificate_info2.byPicInfo, 0, usb_certificate_info.byPicInfo, 0, 1024);
        System.arraycopy(usb_certificate_info2.byFingerPrintInfo, 0, usb_certificate_info.byFingerPrintInfo, 0, 1024);
        Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_CERTIFICATE_INFO Success! wWordInfoSize:" + ((int) usb_certificate_info.wWordInfoSize) + " wPicInfoSize:" + ((int) usb_certificate_info.wPicInfoSize) + " wFingerPrintInfoSize:" + ((int) usb_certificate_info.wFingerPrintInfoSize) + " byCertificateType:" + ((int) usb_certificate_info.byCertificateType) + " byWordInfo:" + new String(usb_certificate_info.byWordInfo));
        return true;
    }

    public boolean USB_SetM1PwdVerify(int i, USB_M1_PWD_VERIFY_INFO usb_m1_pwd_verify_info) {
        if (usb_m1_pwd_verify_info == null) {
            Log.e("[JavaInterface]", "USB_SetM1PwdVerify failed! struPwdVerifyInfo == null");
            return false;
        }
        HCUSBSDKByJNA.USB_M1_PWD_VERIFY_INFO usb_m1_pwd_verify_info2 = new HCUSBSDKByJNA.USB_M1_PWD_VERIFY_INFO();
        usb_m1_pwd_verify_info2.byPasswordType = usb_m1_pwd_verify_info.byPasswordType;
        usb_m1_pwd_verify_info2.bySectionNum = usb_m1_pwd_verify_info.bySectionNum;
        System.arraycopy(usb_m1_pwd_verify_info.byPassword, 0, usb_m1_pwd_verify_info2.byPassword, 0, 6);
        usb_m1_pwd_verify_info2.write();
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpInBuffer = usb_m1_pwd_verify_info2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_m1_pwd_verify_info2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, 1006, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_M1_PWD_VERIFY Success!byPasswordType:" + ((int) usb_m1_pwd_verify_info.byPasswordType) + "bySectionNum:" + ((int) usb_m1_pwd_verify_info.bySectionNum) + "byPassword:" + new String(usb_m1_pwd_verify_info.byPassword));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_M1_PWD_VERIFY failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + "byPasswordType:" + ((int) usb_m1_pwd_verify_info.byPasswordType) + "bySectionNum" + ((int) usb_m1_pwd_verify_info.bySectionNum) + "byPassword:" + new String(usb_m1_pwd_verify_info.byPassword));
        return false;
    }

    public boolean USB_GetReadBlock(int i, USB_M1_BLOCK_ADDR usb_m1_block_addr, USB_M1_BLOCK_DATA usb_m1_block_data) {
        if (usb_m1_block_addr == null) {
            Log.e("[JavaInterface]", "USB_GetReadBlock failed! struBlockAddr == null");
            return false;
        }
        if (usb_m1_block_data == null) {
            Log.e("[JavaInterface]", "USB_GetReadBlock failed! struBlockData == null");
            return false;
        }
        HCUSBSDKByJNA.USB_M1_BLOCK_ADDR usb_m1_block_addr2 = new HCUSBSDKByJNA.USB_M1_BLOCK_ADDR();
        usb_m1_block_addr2.dwSize = usb_m1_block_addr2.size();
        usb_m1_block_addr2.wAddr = usb_m1_block_addr.wAddr;
        usb_m1_block_addr2.write();
        HCUSBSDKByJNA.USB_M1_BLOCK_DATA usb_m1_block_data2 = new HCUSBSDKByJNA.USB_M1_BLOCK_DATA();
        usb_m1_block_data2.write();
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpInBuffer = usb_m1_block_addr2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_m1_block_addr2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_m1_block_data2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_m1_block_data2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_GetDeviceConfig(i, 1007, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_m1_block_data2.read();
            System.arraycopy(usb_m1_block_data2.byData, 0, usb_m1_block_data.byData, 0, 16);
            Log.i("[JavaInterface]", "USB_GetDeviceConfig USB_GET_M1_READ_BLOCK Success! byData:".concat(new String(usb_m1_block_data.byData)));
            return true;
        }
        Log.e("[JavaInterface]", "USB_GetDeviceConfig USB_GET_M1_READ_BLOCK failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byData:" + new String(usb_m1_block_data.byData));
        return false;
    }

    public boolean USB_SetWriteBlock(int i, USB_M1_BLOCK_WRITE_DATA usb_m1_block_write_data) {
        if (usb_m1_block_write_data == null) {
            Log.e("[JavaInterface]", "USB_SetWriteBlock failed! struBlockWriteData == null");
            return false;
        }
        HCUSBSDKByJNA.USB_M1_BLOCK_WRITE_DATA usb_m1_block_write_data2 = new HCUSBSDKByJNA.USB_M1_BLOCK_WRITE_DATA();
        usb_m1_block_write_data2.dwSize = usb_m1_block_write_data2.size();
        usb_m1_block_write_data2.wAddr = usb_m1_block_write_data.wAddr;
        usb_m1_block_write_data2.byDataLen = usb_m1_block_write_data.byDataLen;
        System.arraycopy(usb_m1_block_write_data.byData, 0, usb_m1_block_write_data2.byData, 0, 16);
        usb_m1_block_write_data2.write();
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpInBuffer = usb_m1_block_write_data2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_m1_block_write_data2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, 1008, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_M1_WRITE_BLOCK Success!wAddr:" + ((int) usb_m1_block_write_data.wAddr) + "byDataLen:" + ((int) usb_m1_block_write_data.byDataLen) + "byData:" + new String(usb_m1_block_write_data2.byData));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_M1_WRITE_BLOCK failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + "wAddr:" + ((int) usb_m1_block_write_data.wAddr) + "byDataLen:" + ((int) usb_m1_block_write_data.byDataLen) + "byData:" + new String(usb_m1_block_write_data2.byData));
        return false;
    }

    public boolean USB_SetModifyScb(int i, USB_M1_MODIFY_SCB usb_m1_modify_scb) {
        if (usb_m1_modify_scb == null) {
            Log.e("[JavaInterface]", "USB_SetModifyScb failed! struModifyScb == null");
            return false;
        }
        HCUSBSDKByJNA.USB_M1_MODIFY_SCB usb_m1_modify_scb2 = new HCUSBSDKByJNA.USB_M1_MODIFY_SCB();
        usb_m1_modify_scb2.dwSize = usb_m1_modify_scb2.size();
        usb_m1_modify_scb2.bySectionNum = usb_m1_modify_scb.bySectionNum;
        System.arraycopy(usb_m1_modify_scb.byPasswordA, 0, usb_m1_modify_scb2.byPasswordA, 0, 6);
        System.arraycopy(usb_m1_modify_scb.byCtrlBits, 0, usb_m1_modify_scb2.byCtrlBits, 0, 4);
        System.arraycopy(usb_m1_modify_scb.byPasswordB, 0, usb_m1_modify_scb2.byPasswordB, 0, 6);
        usb_m1_modify_scb2.write();
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpInBuffer = usb_m1_modify_scb2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_m1_modify_scb2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, 1009, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_M1_MODIFY_SCB Success!bySectionNum:" + ((int) usb_m1_modify_scb.bySectionNum) + "byPasswordA:" + new String(usb_m1_modify_scb.byPasswordA) + "byCtrlBits:" + new String(usb_m1_modify_scb.byCtrlBits) + "byPasswordB:" + new String(usb_m1_modify_scb.byPasswordB));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_M1_MODIFY_SCB failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + "bySectionNum:" + ((int) usb_m1_modify_scb.bySectionNum) + "byPasswordA:" + new String(usb_m1_modify_scb.byPasswordA) + "byCtrlBits:" + new String(usb_m1_modify_scb.byCtrlBits) + "byPasswordB:" + new String(usb_m1_modify_scb.byPasswordB));
        return false;
    }

    public boolean USB_SetSectionEncrypt(int i, USB_M1_SECTION_ENCRYPT usb_m1_section_encrypt, USB_M1_SECTION_ENCRYPT_RES usb_m1_section_encrypt_res) {
        if (usb_m1_section_encrypt == null) {
            Log.e("[JavaInterface]", "USB_SetSectionEncrypt failed! struSectionEncrypt == null");
            return false;
        }
        if (usb_m1_section_encrypt_res == null) {
            Log.e("[JavaInterface]", "USB_SetSectionEncrypt failed! struSectionEncryptRes == null");
            return false;
        }
        HCUSBSDKByJNA.USB_M1_SECTION_ENCRYPT usb_m1_section_encrypt2 = new HCUSBSDKByJNA.USB_M1_SECTION_ENCRYPT();
        usb_m1_section_encrypt2.dwSize = usb_m1_section_encrypt2.size();
        usb_m1_section_encrypt2.bySectionID = usb_m1_section_encrypt.bySectionID;
        usb_m1_section_encrypt2.byKeyType = usb_m1_section_encrypt.byKeyType;
        System.arraycopy(usb_m1_section_encrypt.byKeyAContent, 0, usb_m1_section_encrypt2.byKeyAContent, 0, 6);
        usb_m1_section_encrypt2.byNewKeyType = usb_m1_section_encrypt.byNewKeyType;
        System.arraycopy(usb_m1_section_encrypt.byNewKeyAContent, 0, usb_m1_section_encrypt2.byNewKeyAContent, 0, 6);
        System.arraycopy(usb_m1_section_encrypt.byCtrlBits, 0, usb_m1_section_encrypt2.byCtrlBits, 0, 4);
        System.arraycopy(usb_m1_section_encrypt.byNewKeyBContent, 0, usb_m1_section_encrypt2.byNewKeyBContent, 0, 6);
        usb_m1_section_encrypt2.write();
        HCUSBSDKByJNA.USB_M1_SECTION_ENCRYPT_RES usb_m1_section_encrypt_res2 = new HCUSBSDKByJNA.USB_M1_SECTION_ENCRYPT_RES();
        usb_m1_section_encrypt_res2.write();
        new HCUSBSDKByJNA.USB_COMMON_COND().write();
        HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO usb_config_input_info = new HCUSBSDKByJNA.USB_CONFIG_INPUT_INFO();
        usb_config_input_info.lpInBuffer = usb_m1_section_encrypt2.getPointer();
        usb_config_input_info.dwInBufferSize = usb_m1_section_encrypt2.size();
        usb_config_input_info.write();
        HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO usb_config_output_info = new HCUSBSDKByJNA.USB_CONFIG_OUTPUT_INFO();
        usb_config_output_info.lpOutBuffer = usb_m1_section_encrypt_res2.getPointer();
        usb_config_output_info.dwOutBufferSize = usb_m1_section_encrypt_res2.size();
        usb_config_output_info.write();
        if (HCUSBSDK.getInstance().USB_SetDeviceConfig(i, USB_SET_M1_SECTION_ENCRYPT, usb_config_input_info.getPointer(), usb_config_output_info.getPointer())) {
            usb_config_output_info.read();
            usb_m1_section_encrypt_res2.read();
            usb_m1_section_encrypt_res.byStatus = usb_m1_section_encrypt_res2.byStatus;
            Log.i("[JavaInterface]", "USB_SetDeviceConfig USB_SET_M1_SECTION_ENCRYPT Success! byStatus:" + ((int) usb_m1_section_encrypt_res.byStatus));
            return true;
        }
        Log.e("[JavaInterface]", "USB_SetDeviceConfig USB_SET_M1_SECTION_ENCRYPT failed! error:" + HCUSBSDK.getInstance().USB_GetLastError() + " byStatus:" + ((int) usb_m1_section_encrypt_res.byStatus));
        return false;
    }
}
