package com.hcusbsdk.jna;

import com.hti.xtherm.hti160203u.helper.Config;
import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

import ai.onnxruntime.OrtException;

/* loaded from: classes.dex */
public interface HCUSBSDKByJNA extends Library {
    public static final int FINGER_PRINT_LEN = 1024;
    public static final int MAX_DEVICE_NAME_LEN = 32;
    public static final int MAX_EXPERT_REGIONS = 21;
    public static final int MAX_MANUFACTURE_LEN = 32;
    public static final int MAX_PASSWORD_LEN = 16;
    public static final int MAX_REGION_POINT_NUM = 10;
    public static final int MAX_ROI_REGIONS = 10;
    public static final int MAX_SERIAL_NUM_LEN = 48;
    public static final int MAX_TEMPERATURE_NUM = 4;
    public static final int MAX_THERMAL_REGIONS = 10;
    public static final int MAX_USERNAME_LEN = 32;
    public static final int PIC_LEN = 1024;
    public static final int WORD_LEN = 256;

    public interface FStreamCallBack extends Callback {
        void invoke(int i, Pointer pointer, Pointer pointer2) throws OrtException;
    }

    boolean USB_Cleanup();

    boolean USB_CloseUpgradeHandle(int i);

    boolean USB_Control(int i, int i2, Pointer pointer);

    boolean USB_EnumDevices(int i, USB_DEVICE_INFO[] usb_device_infoArr);

    int USB_FileTransfer(int i, int i2, String str);

    boolean USB_GetCommandState(int i, Pointer pointer);

    boolean USB_GetDeviceConfig(int i, int i2, Pointer pointer, Pointer pointer2);

    int USB_GetDeviceCount();

    int USB_GetLastError();

    int USB_GetSDKVersion();

    boolean USB_GetUpgradeState(int i, Pointer pointer);

    boolean USB_Init();

    int USB_Login(Pointer pointer, Pointer pointer2);

    boolean USB_Logout(int i);

    boolean USB_SetDeviceConfig(int i, int i2, Pointer pointer, Pointer pointer2);

    boolean USB_SetLogToFile(int i, String str, int i2);

    int USB_StartPreview(int i, Pointer pointer);

    int USB_StartStreamCallback(int i, Pointer pointer);

    boolean USB_StopChannel(int i, int i2);

    int USB_Upgrade(int i, int i2, String str, Pointer pointer, int i3);

    public static class USB_DEVICE_INFO extends Structure {
        public byte byHaveAudio;
        public int dwIndex;
        public int dwPID;
        public int dwSize;
        public int dwVID;
        public byte[] szManufacturer = new byte[32];
        public byte[] szDeviceName = new byte[32];
        public byte[] szSerialNumber = new byte[48];
        public byte[] byRes = new byte[255];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "dwIndex", "dwVID", "dwPID", "szManufacturer", "szDeviceName", "szSerialNumber", "byHaveAudio", "byRes");
        }
    }

    public static class USB_USER_LOGIN_INFO extends Structure {
        public byte byLoginMode;
        public int dwDevIndex;
        public int dwFd;
        public int dwPID;
        public int dwSize;
        public int dwTimeout;
        public int dwVID;
        public byte[] szUserName = new byte[32];
        public byte[] szPassword = new byte[16];
        public byte[] szSerialNumber = new byte[48];
        public byte[] byRes2 = new byte[3];
        public byte[] byRes = new byte[Config.F2_PREVIEW_HEIGHT];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "dwTimeout", "dwDevIndex", "dwVID", "dwPID", "szUserName", "szPassword", "szSerialNumber", "byLoginMode", "byRes2", "dwFd", "byRes");
        }
    }

    public static class USB_DEVICE_REG_RES extends Structure {
        public byte byDay;
        public byte byMonth;
        public byte byRetryLoginTimes;
        public int dwSize;
        public int dwSoftwareVersion;
        public int dwSurplusLockTime;
        public short wYear;
        public byte[] szDeviceName = new byte[32];
        public byte[] szSerialNumber = new byte[48];
        public byte[] byRes1 = new byte[3];
        public byte[] byRes = new byte[256];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "szDeviceName", "szSerialNumber", "dwSoftwareVersion", "wYear", "byMonth", "byDay", "byRetryLoginTimes", "byRes1", "dwSurplusLockTime", "byRes");
        }
    }

    public static class USB_STREAM_CALLBACK_PARAM extends Structure {
        public byte[] byRes = new byte[128];
        public int dwSize;
        public int dwStreamType;
        public FStreamCallBack fnStreamCallBack;
        public Pointer pUser;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "dwStreamType", "fnStreamCallBack", "pUser", "byRes");
        }
    }

    public static class USB_FRAME_INFO extends Structure {
        public byte[] byRes;
        public int dwBufSize;
        public int dwDataType;
        public int dwFrameRate;
        public int dwFrameType;
        public int dwHeight;
        public int dwStreamType;
        public int dwWidth;
        public int nFrameNum;
        public int nStamp;
        public Pointer pBuf;

        public USB_FRAME_INFO(Pointer pointer) {
            super(pointer);
            this.byRes = new byte[128];
        }

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("nStamp", "dwStreamType", "dwWidth", "dwHeight", "dwFrameRate", "dwFrameType", "dwDataType", "nFrameNum", "pBuf", "dwBufSize", "byRes");
        }
    }

    public static class USB_CONFIG_INPUT_INFO extends Structure {
        public byte[] byRes = new byte[48];
        public int dwCondBufferSize;
        public int dwInBufferSize;
        public Pointer lpCondBuffer;
        public Pointer lpInBuffer;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("lpCondBuffer", "dwCondBufferSize", "lpInBuffer", "dwInBufferSize", "byRes");
        }
    }

    public static class USB_CONFIG_OUTPUT_INFO extends Structure {
        public byte[] byRes = new byte[56];
        public int dwOutBufferSize;
        public Pointer lpOutBuffer;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("lpOutBuffer", "dwOutBufferSize", "byRes");
        }
    }

    public static class USB_COMMON_COND extends Structure {
        public byte byChannelID;
        public byte[] byRes = new byte[6];
        public byte bySID;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byChannelID", "bySID", "byRes");
        }
    }

    public static class USB_CONTROL_INPUT_INFO extends Structure {
        public byte[] byRes = new byte[48];
        public int dwCondBufferSize;
        public int dwInBufferSize;
        public Pointer lpCondBuffer;
        public Pointer lpInBuffer;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("lpCondBuffer", "dwCondBufferSize", "lpInBuffer", "dwInBufferSize", "byRes");
        }
    }

    public static class BYTE_ARRAY extends Structure {
        public byte[] byValue;

        public BYTE_ARRAY(int i) {
            this.byValue = new byte[i];
        }

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byValue");
        }
    }

    public static class USB_VIDEO_CAPACITY extends Structure {
        public int dwHeight;
        public int dwWidth;
        public long[] lFrameRates = new long[50];
        public int lListSize;
        public byte nIndex;
        public byte nType;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("nIndex", "nType", "dwWidth", "dwHeight", "lListSize", "lFrameRates");
        }
    }

    public static class USB_VIDEO_PARAM extends Structure {
        public byte[] byRes = new byte[128];
        public int dwBitrate;
        public int dwFramerate;
        public int dwHeight;
        public int dwParamType;
        public int dwValue;
        public int dwVideoFormat;
        public int dwWidth;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwVideoFormat", "dwWidth", "dwHeight", "dwFramerate", "dwBitrate", "dwParamType", "dwValue", "byRes");
        }
    }

    public static class USB_SYSTEM_DEVICE_INFO extends Structure {
        public int dwSize;
        public byte[] byFirmwareVersion = new byte[64];
        public byte[] byEncoderVersion = new byte[64];
        public byte[] byHardwareVersion = new byte[64];
        public byte[] byDeviceType = new byte[64];
        public byte[] byProtocolVersion = new byte[4];
        public byte[] bySerialNumber = new byte[64];
        public byte[] bySecondHardwareVersion = new byte[64];
        public byte[] byModuleID = new byte[32];
        public byte[] byDeviceID = new byte[64];
        public byte[] byRes = new byte[28];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byFirmwareVersion", "byEncoderVersion", "byHardwareVersion", "byDeviceType", "byProtocolVersion", "bySerialNumber", "bySecondHardwareVersion", "byModuleID", "byDeviceID", "byRes");
        }
    }

    public static class USB_SYSTEM_HARDWARE_SERVER extends Structure {
        public byte byDeviceInitialStatus;
        public byte byDeviceWorkingStatus;
        public byte[] byRes = new byte[29];
        public byte byUsbMode;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byUsbMode", "byDeviceInitialStatus", "byDeviceWorkingStatus", "byRes");
        }
    }

    public static class USB_SYSTEM_LOCALTIME extends Structure {
        public byte byDay;
        public byte byExternalTimeSourceEnabled;
        public byte byHour;
        public byte byMinute;
        public byte byMonth;
        public byte bySecond;
        public int dwSize;
        public short wMillisecond;
        public short wYear;
        public byte[] byRes1 = new byte[1];
        public byte[] byRes = new byte[5];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "wMillisecond", "bySecond", "byMinute", "byHour", "byDay", "byMonth", "byRes1", "wYear", "byExternalTimeSourceEnabled", "byRes");
        }
    }

    public static class USB_IMAGE_BRIGHTNESS extends Structure {
        public byte[] byRes = new byte[28];
        public int dwBrightness;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "dwBrightness", "byRes");
        }
    }

    public static class USB_IMAGE_CONTRAST extends Structure {
        public byte[] byRes = new byte[28];
        public int dwContrast;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "dwContrast", "byRes");
        }
    }

    public static class USB_IMAGE_ENHANCEMENT extends Structure {
        public byte byBirdWatchingMode;
        public byte byHighLightLevel;
        public byte byHighLightMode;
        public byte byHookEdgeLevel;
        public byte byHookEdgeMode;
        public byte byIspAgcMode;
        public byte byLSEDetailEnabled;
        public byte byNoiseReduceMode;
        public byte byPaletteMode;
        public byte[] byRes = new byte[28];
        public byte byRes1;
        public byte byWideTemperatureMode;
        public byte byWideTemperatureWork;
        public int dwFrameNoiseReduceLevel;
        public int dwGeneralLevel;
        public int dwInterFrameNoiseReduceLevel;
        public int dwLSEDetailLevel;
        public int dwSize;
        public int dwWideTemperatureDownThreshold;
        public int dwWideTemperatureUpThreshold;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byNoiseReduceMode", "byBirdWatchingMode", "byHighLightMode", "byHighLightLevel", "dwGeneralLevel", "dwFrameNoiseReduceLevel", "dwInterFrameNoiseReduceLevel", "byPaletteMode", "byLSEDetailEnabled", "byHookEdgeMode", "byHookEdgeLevel", "dwLSEDetailLevel", "byWideTemperatureMode", "byWideTemperatureWork", "byIspAgcMode", "byRes1", "dwWideTemperatureUpThreshold", "dwWideTemperatureDownThreshold", "byRes");
        }
    }

    public static class USB_IMAGE_VIDEO_ADJUST extends Structure {
        public byte byBadPointCursor;
        public byte byBadPointCursorShiftMode;
        public byte byCorridor;
        public byte byCursor;
        public byte byDeleteBadPoint;
        public byte byDigitalZoom;
        public byte byImageFlipStyle;
        public byte byPointXShiftLeft;
        public byte byPointXShiftRight;
        public byte byPointYShiftDown;
        public byte byPointYShiftUp;
        public byte byPowerLineFrequencyMode;
        public byte[] byRes = new byte[3];
        public byte byRes1;
        public int dwBadCursorPointX;
        public int dwBadCursorPointY;
        public int dwCursorPointX;
        public int dwCursorPointY;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byImageFlipStyle", "byPowerLineFrequencyMode", "byCorridor", "byDigitalZoom", "byCursor", "byBadPointCursor", "byBadPointCursorShiftMode", "byRes1", "dwCursorPointX", "dwCursorPointY", "dwBadCursorPointX", "dwBadCursorPointY", "byPointXShiftLeft", "byPointXShiftRight", "byPointYShiftUp", "byPointYShiftDown", "byDeleteBadPoint", "byRes");
        }
    }

    public static class USB_SYSTEM_ENCRYPT_STATUS extends Structure {
        public byte byEncryptStatus;
        public byte byErrMsg;
        public byte[] byRes = new byte[30];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byEncryptStatus", "byErrMsg", "byRes");
        }
    }

    public static class USB_SYSTEM_INDICATORLIGHT extends Structure {
        public byte byColour;
        public byte[] byRes = new byte[30];
        public byte byStatus;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byStatus", "byColour", "byRes");
        }
    }

    public static class USB_IMAGE_WDR extends Structure {
        public byte byEnabled;
        public byte byLevel;
        public byte byMode;
        public byte[] byRes = new byte[29];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byEnabled", "byMode", "byLevel", "byRes");
        }
    }

    public static class USB_IMAGE_VIDEO_LOGO_SWITCH extends Structure {
        public byte byChannelID;
        public byte byEnabled;
        public byte byLogoID;
        public byte byPictureID;
        public byte byPortID;
        public byte[] byRes = new byte[30];
        public byte byType;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byEnabled", "byChannelID", "byType", "byPortID", "byPictureID", "byLogoID", "byRes");
        }
    }

    public static class USB_IMAGE_VIDEO_LOGO_CFG extends Structure {
        public byte byChannelID;
        public byte byFlickerControl;
        public byte byLogoID;
        public byte byLogoPicNums;
        public byte byLogoType;
        public byte[] byRes = new byte[32];
        public byte byShieldColorU;
        public byte byShieldColorV;
        public byte byShieldColorY;
        public int dwRegionHeight;
        public int dwRegionWidth;
        public int dwRegionX;
        public int dwRegionY;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byChannelID", "byLogoID", "byLogoType", "byLogoPicNums", "dwRegionX", "dwRegionY", "dwRegionWidth", "dwRegionHeight", "byFlickerControl", "byShieldColorY", "byShieldColorU", "byShieldColorV", "byRes");
        }
    }

    public static class USB_IMAGE_VIDEO_OSD_SWITCH extends Structure {
        public byte byChannelID;
        public byte byEnabled;
        public byte byPortID;
        public byte[] byRes = new byte[32];
        public byte byType;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byEnabled", "byChannelID", "byType", "byPortID", "byRes");
        }
    }

    public static class USB_OSD_PROPERTY extends Structure {
        public int byBackgroundColor;
        public int byFontColor;
        public byte byHorizontalScalingRatio;
        public byte byRes2;
        public byte byRowCharNums;
        public byte byRowID;
        public byte byVerticalScalingRatio;
        public int dwRegionX;
        public int dwRegionY;
        public byte[] byRes1 = new byte[3];
        public short[] wCharacterCode = new short[32];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byRowID", "byRes1", "dwRegionX", "dwRegionY", "byBackgroundColor", "byFontColor", "byVerticalScalingRatio", "byHorizontalScalingRatio", "byRowCharNums", "byRes2", "wCharacterCode");
        }
    }

    public static class USB_IMAGE_VIDEO_OSD_CFG extends Structure {
        public byte byAutoBrightness;
        public byte byChannelID;
        public byte byPortID;
        public byte byTranslucent;
        public byte byType;
        public int dwFlickerControl;
        public int dwSize;
        public byte[] byRes1 = new byte[3];
        public USB_OSD_PROPERTY[] struOSDProperty = new USB_OSD_PROPERTY[4];
        public byte[] byRes = new byte[32];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byChannelID", "byType", "byPortID", "byAutoBrightness", "byTranslucent", "byRes1", "dwFlickerControl", "struOSDProperty", "byRes");
        }
    }

    public static class USB_IMAGE_VIDEO_MULTIPLE_STREAM extends Structure {
        public byte byBpsType;
        public byte byChannelID;
        public byte byFps;
        public byte byProfile;
        public byte byVideoType;
        public int dwBitrate;
        public int dwEncHeight;
        public int dwEncWidth;
        public int dwIFrameInterval;
        public int dwSize;
        public byte[] byRes1 = new byte[3];
        public byte[] byRes2 = new byte[3];
        public byte[] byRes = new byte[29];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byChannelID", "byRes1", "dwEncWidth", "dwEncHeight", "byVideoType", "byRes2", "dwIFrameInterval", "dwBitrate", "byBpsType", "byProfile", "byFps", "byRes");
        }
    }

    public static class USB_IMAGE_VIDEO_MULTIPLE_IFRAME extends Structure {
        public byte byChannelID;
        public byte byIFrameFlag;
        public byte[] byRes = new byte[30];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byChannelID", "byIFrameFlag", "byRes");
        }
    }

    public static class USB_AUDIO_STATUS extends Structure {
        public byte byChannelID;
        public byte byConnectStatus;
        public byte[] byRes = new byte[30];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byChannelID", "byConnectStatus", "byRes");
        }
    }

    public static class USB_AUDIO_VOLUME extends Structure {
        public byte byChannelID;
        public byte[] byRes = new byte[30];
        public byte byVolume;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byChannelID", "byVolume", "byRes");
        }
    }

    public static class USB_AUDIO_AMER extends Structure {
        public byte byChannelID;
        public byte byEnabled;
        public byte[] byRes = new byte[30];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byChannelID", "byEnabled", "byRes");
        }
    }

    public static class USB_AUDIO_AECSP extends Structure {
        public byte byEnabled;
        public byte byInChannelID;
        public byte byOutChannelID;
        public byte[] byRes = new byte[31];
        public int dwSize;
        public short wAecValue;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byInChannelID", "byEnabled", "wAecValue", "byOutChannelID", "byRes");
        }
    }

    public static class USB_AUDIO_DEVICE_DELAY extends Structure {
        public byte byInChannelID;
        public byte byOutChannelID;
        public byte byProcessMode;
        public byte[] byRes = new byte[30];
        public byte byRes1;
        public int dwSize;
        public short wAecValue;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byInChannelID", "byOutChannelID", "byProcessMode", "byRes1", "wAecValue", "byRes");
        }
    }

    public static class USB_AUDIO_BT extends Structure {
        public byte byEnabled;
        public byte[] byRes = new byte[31];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byEnabled", "byRes");
        }
    }

    public static class USB_AUDIO_BT_MAC extends Structure {
        public byte[] byMacAddress = new byte[40];
        public byte[] byRes = new byte[32];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byMacAddress", "byRes");
        }
    }

    public static class USB_AUDIO_BT_STATUS extends Structure {
        public byte byConnectStatus;
        public byte[] byRes = new byte[31];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byConnectStatus", "byRes");
        }
    }

    public static class USB_AUDIO_DETECT extends Structure {
        public byte byInChannelID;
        public byte byOutChannelID;
        public byte byProcessMode;
        public byte[] byRes = new byte[32];
        public byte byTestResult;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byInChannelID", "byOutChannelID", "byProcessMode", "byTestResult", "byRes");
        }
    }

    public static class USB_AUDIO_EFFICT_DETECT extends Structure {
        public byte byInChannelID;
        public byte byOutChannelID;
        public byte byProcessMode;
        public byte[] byRes = new byte[31];
        public byte byTestMode;
        public byte byTestResult;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byInChannelID", "byOutChannelID", "byTestMode", "byProcessMode", "byTestResult", "byRes");
        }
    }

    public static class USB_AUDIO_FAC_TEST extends Structure {
        public byte byProcessMode;
        public byte[] byRes = new byte[29];
        public byte byTestMode;
        public byte byTestResult;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byTestMode", "byProcessMode", "byTestResult", "byRes");
        }
    }

    public static class USB_AUDIO_AGC_CONFIG extends Structure {
        public byte byChannelID;
        public byte byEnabled;
        public byte byGainLevel;
        public byte[] byRes = new byte[29];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byChannelID", "byEnabled", "byGainLevel", "byRes");
        }
    }

    public static class USB_AUDIO_REDUCE_NOISE extends Structure {
        public byte byChannelID;
        public byte byEnabled;
        public byte byLevel;
        public byte[] byRes = new byte[29];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byChannelID", "byEnabled", "byLevel", "byRes");
        }
    }

    public static class USB_AUDIO_RECOG_RSLT extends Structure {
        public byte byAudAiCmd;
        public byte byMatchAudAi;
        public int dwResRet;
        public int dwResSet;
        public int dwSize;
        public byte[] byRes1 = new byte[2];
        public byte[] byRes = new byte[32];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byAudAiCmd", "byMatchAudAi", "byRes1", "dwResSet", "dwResRet", "byRes");
        }
    }

    public static class USB_AUDIO_ECHO_SET extends Structure {
        public byte byEnabled;
        public byte byInChannelID;
        public byte byOutChannelID;
        public byte[] byRes = new byte[29];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byInChannelID", "byOutChannelID", "byEnabled", "byRes");
        }
    }

    public static class USB_AUDIO_SIGNAL_TRANS extends Structure {
        public byte[] byRes = new byte[31];
        public byte bySignalTransType;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("bySignalTransType", "byRes");
        }
    }

    public static class USB_AUDIO_INPUT_OUTPUT_CHANNELINFO extends Structure {
        public byte byInChannelID;
        public byte byOutChannelID;
        public byte[] byRes = new byte[30];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byInChannelID", "byOutChannelID", "byRes");
        }
    }

    public static class USB_AUDIO_PROCESS_LINE_CFG extends Structure {
        public byte byEnabled;
        public byte[] byRes = new byte[31];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byEnabled", "byRes");
        }
    }

    public static class USB_AUDIO_POE_LINK_STATUS extends Structure {
        public byte byPOELinkStatus;
        public byte[] byRes = new byte[31];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byPOELinkStatus", "byRes");
        }
    }

    public static class USB_SYSTEM_DEVICE_CAPABILITIES extends Structure {
        public byte byIsSupportSVC;
        public byte[] byRes = new byte[31];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byIsSupportSVC", "byRes");
        }
    }

    public static class USB_IMAGE_VIDEO_SVC_MULTIPLE_STREAM extends Structure {
        public byte byMultipleStreamNum;
        public byte[] byRes = new byte[31];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byMultipleStreamNum", "byRes");
        }
    }

    public static class USB_PTZ_TRACK_MODE extends Structure {
        public byte[] byRes = new byte[31];
        public byte byTrackingMode;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byTrackingMode", "byRes");
        }
    }

    public static class USB_PTZ_PRESET_CFG extends Structure {
        public byte byChannelID;
        public byte byPresetID;
        public byte[] byRes = new byte[30];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byChannelID", "byPresetID", "byRes");
        }
    }

    public static class USB_PTZ_AUTO_TRACK_SENSITIVITY extends Structure {
        public byte[] byRes = new byte[31];
        public byte bySensitivity;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "bySensitivity", "byRes");
        }
    }

    public static class USB_BIG_DATA_FILE extends Structure {
        public byte byDataType;
        public int dwId;
        public int dwLen;
        public Pointer pData;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byDataType", "dwId", "dwLen", "pData");
        }
    }

    public static class USB_THERMOMETRY_BASIC_PARAM extends Structure {
        public byte byBackcolorEnabled;
        public byte byCalibrationCoefficientEnabled;
        public byte byDisplayAverageTemperatureEnabled;
        public byte byDisplayCenTempEnabled;
        public byte byDisplayMaxTemperatureEnabled;
        public byte byDisplayMinTemperatureEnabled;
        public byte byDistanceUnit;
        public byte byEnabled;
        public byte byReflectiveEnable;
        public byte byRes3;
        public byte byShowAlarmColorEnabled;
        public byte byTemperatureRange;
        public byte byTemperatureUnit;
        public byte byThermometryStreamOverlay;
        public byte byThermomrtryInfoDisplayPosition;
        public int dwAlarm;
        public int dwAlert;
        public int dwCalibrationCoefficient;
        public int dwDistance;
        public int dwEmissivity;
        public int dwExternalOpticsTransmit;
        public int dwExternalOpticsWindowCorrection;
        public int dwReflectiveTemperature;
        public int dwSize;
        public byte[] byRes1 = new byte[2];
        public byte[] byRes2 = new byte[3];
        public byte[] byRes = new byte[204];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byRes3", "byEnabled", "byDisplayMaxTemperatureEnabled", "byDisplayMinTemperatureEnabled", "byDisplayAverageTemperatureEnabled", "byTemperatureUnit", "byTemperatureRange", "byCalibrationCoefficientEnabled", "dwCalibrationCoefficient", "dwExternalOpticsWindowCorrection", "dwEmissivity", "byDistanceUnit", "byShowAlarmColorEnabled", "byRes1", "dwDistance", "byReflectiveEnable", "byRes2", "dwReflectiveTemperature", "byThermomrtryInfoDisplayPosition", "byThermometryStreamOverlay", "byDisplayCenTempEnabled", "byBackcolorEnabled", "dwAlert", "dwAlarm", "dwExternalOpticsTransmit", "byRes");
        }
    }

    public static class USB_THERMOMETRY_MODE extends Structure {
        public byte[] byRes = new byte[62];
        public byte byThermometryMode;
        public byte byThermometryROIEnabled;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byThermometryMode", "byThermometryROIEnabled", "byRes");
        }
    }

    public static class THERMAL_REGION extends Structure {
        public byte byRegionEnabled;
        public byte byRegionID;
        public int dwRegionHeight;
        public int dwRegionWidth;
        public int dwRegionX;
        public int dwRegionY;
        public int dwSize;
        public byte[] byRes1 = new byte[2];
        public byte[] byRes = new byte[12];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byRegionID", "byRegionEnabled", "byRes1", "dwRegionX", "dwRegionY", "dwRegionWidth", "dwRegionHeight", "byRes");
        }
    }

    public static class USB_THERMOMETRY_REGIONS extends Structure {
        public byte byRegionNum;
        public int dwSize;
        public byte[] byRes1 = new byte[3];
        public THERMAL_REGION[] struRegion = new THERMAL_REGION[10];
        public byte[] byRes = new byte[188];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byRegionNum", "byRes1", "struRegion", "byRes");
        }
    }

    public static class USB_THERMAL_STREAM_PARAM extends Structure {
        public byte[] byRes = new byte[15];
        public byte byVideoCodingType;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byVideoCodingType", "byRes");
        }
    }

    public static class USB_TEMPERATURE_CORRECT extends Structure {
        public byte byCorrectEnabled;
        public byte byEnabled;
        public byte[] byRes = new byte[36];
        public byte byRes1;
        public byte byStreamOverlay;
        public int dwCentrePointX;
        public int dwCentrePointY;
        public int dwCorrectTemperature;
        public int dwDistance;
        public int dwEmissivity;
        public int dwSize;
        public int dwTemperature;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byRes1", "byEnabled", "byStreamOverlay", "byCorrectEnabled", "dwEmissivity", "dwDistance", "dwTemperature", "dwCentrePointX", "dwCentrePointY", "dwCorrectTemperature", "byRes");
        }
    }

    public static class USB_BLACK_BODY extends Structure {
        public byte byEnabled;
        public int dwCentrePointX;
        public int dwCentrePointY;
        public int dwDistance;
        public int dwEmissivity;
        public int dwSize;
        public int dwTemperature;
        public byte[] byRes1 = new byte[3];
        public byte[] byRes = new byte[40];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byEnabled", "byRes1", "dwEmissivity", "dwDistance", "dwTemperature", "dwCentrePointX", "dwCentrePointY", "byRes");
        }
    }

    public static class USB_BODYTEMP_COMPENSATION extends Structure {
        public byte byEnabled;
        public byte byEnvironmentCompensationenabled;
        public byte byEnvironmentalTemperatureMode;
        public byte byTemperatureCurveSensitivityLevel;
        public byte byType;
        public int dwEnvironmentalTemperature;
        public int dwSize;
        public int dwSmartCorrection;
        public int iCompensationValue;
        public byte[] byRes1 = new byte[2];
        public byte[] byRes = new byte[45];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byEnabled", "byType", "byRes1", "iCompensationValue", "dwSmartCorrection", "dwEnvironmentalTemperature", "byEnvironmentalTemperatureMode", "byTemperatureCurveSensitivityLevel", "byEnvironmentCompensationenabled", "byRes");
        }
    }

    public static class USB_P2P_PARAM extends Structure {
        public byte byJpegPicEnabled;
        public byte[] byRes = new byte[31];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byJpegPicEnabled", "byRes");
        }
    }

    public static class USB_DOUBLE_LIGHTS_CORRECT_POINTS_CTRL extends Structure {
        public byte byDoubleLightsCorrectPointsEnabled;
        public byte[] byRes = new byte[31];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byDoubleLightsCorrectPointsEnabled", "byRes");
        }
    }

    public static class USB_THERMAL_ALG_VERSION extends Structure {
        public int dwSize;
        public byte[] byThermometryAlgName = new byte[64];
        public byte[] byRes = new byte[64];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byThermometryAlgName", "byRes");
        }
    }

    public static class USB_JPEGPIC_WITH_APPENDDATA extends Structure {
        public byte byIsFreezedata;
        public byte byTemperatureDataLength;
        public int dwJpegPicHeight;
        public int dwJpegPicLen;
        public int dwJpegPicWidth;
        public int dwP2pDataLen;
        public int dwScale;
        public int dwSize;
        public int iOffset;
        public Pointer pJpegPic;
        public Pointer pP2pData;
        public byte[] byRes2 = new byte[2];
        public byte[] byRes = new byte[28];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "dwJpegPicLen", "dwJpegPicWidth", "dwJpegPicHeight", "dwP2pDataLen", "byIsFreezedata", "byTemperatureDataLength", "byRes2", "dwScale", "iOffset", "pJpegPic", "pP2pData", "byRes");
        }
    }

    public static class ROI_REGION extends Structure {
        public byte byROIRegionEnabled;
        public byte byROIRegionID;
        public int dwDistance;
        public int dwROIRegionHeight;
        public int dwROIRegionWidth;
        public int dwROIRegionX;
        public int dwROIRegionY;
        public byte[] byRes1 = new byte[2];
        public byte[] byRes = new byte[8];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byROIRegionID", "byROIRegionEnabled", "byRes1", "dwROIRegionX", "dwROIRegionY", "dwROIRegionWidth", "dwROIRegionHeight", "dwDistance", "byRes");
        }
    }

    public static class USB_ROI_MAX_TEMPERATURE_SEARCH extends Structure {
        public byte byDay;
        public byte byHour;
        public byte byJpegPicEnabled;
        public byte byMaxTemperatureOverlay;
        public byte byMinute;
        public byte byMonth;
        public byte byROIRegionNum;
        public byte byRegionsOverlay;
        public byte byRes2;
        public byte bySecond;
        public int dwSize;
        public short wMillisecond;
        public short wYear;
        public byte[] byRes1 = new byte[2];
        public THERMAL_ROI_REGION[] struThermalROIRegion = (THERMAL_ROI_REGION[]) new THERMAL_ROI_REGION().toArray(10);
        public byte[] byRes = new byte[176];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "wMillisecond", "byRes2", "bySecond", "byMinute", "byHour", "byDay", "byMonth", "wYear", "byJpegPicEnabled", "byMaxTemperatureOverlay", "byRegionsOverlay", "byROIRegionNum", "byRes1", "struThermalROIRegion", "byRes");
        }
    }

    public static class THERMAL_ROI_REGION extends Structure {
        public byte byROIRegionEnabled;
        public byte byROIRegionID;
        public int dwDistance;
        public int dwROIRegionHeight;
        public int dwROIRegionWidth;
        public int dwROIRegionX;
        public int dwROIRegionY;
        public byte[] byRes1 = new byte[2];
        public byte[] byRes = new byte[8];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byROIRegionID", "byROIRegionEnabled", "byRes1", "dwROIRegionX", "dwROIRegionY", "dwROIRegionWidth", "dwROIRegionHeight", "dwDistance", "byRes");
        }
    }

    public static class THERMAL_ROI_REGION_INFO extends Structure {
        public byte byROIRegionID;
        public int dwMaxROIRegionTemperature;
        public int dwThermalROIRegionMaxTemperaturePointX;
        public int dwThermalROIRegionMaxTemperaturePointY;
        public int dwVisibleROIRegionMaxTemperaturePointX;
        public int dwVisibleROIRegionMaxTemperaturePointY;
        public byte[] byRes1 = new byte[3];
        public byte[] byRes = new byte[8];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byROIRegionID", "byRes1", "dwMaxROIRegionTemperature", "dwVisibleROIRegionMaxTemperaturePointX", "dwVisibleROIRegionMaxTemperaturePointY", "dwThermalROIRegionMaxTemperaturePointX", "dwThermalROIRegionMaxTemperaturePointY", "byRes");
        }
    }

    public static class USB_ROI_MAX_TEMPERATURE_SEARCH_RESULT extends Structure {
        public byte byROIRegionNum;
        public int dwJpegPicLen;
        public int dwMaxP2PTemperature;
        public int dwSize;
        public int dwThermalP2PMaxTemperaturePointX;
        public int dwThermalP2PMaxTemperaturePointY;
        public int dwVisibleP2PMaxTemperaturePointX;
        public int dwVisibleP2PMaxTemperaturePointY;
        public Pointer pJpegPic;
        public byte[] byRes2 = new byte[3];
        public THERMAL_ROI_REGION_INFO[] struThermalROIRegionInfo = (THERMAL_ROI_REGION_INFO[]) new THERMAL_ROI_REGION_INFO().toArray(10);
        public byte[] byRes = new byte[Config.F2_PREVIEW_WIDTH];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "dwMaxP2PTemperature", "dwVisibleP2PMaxTemperaturePointX", "dwVisibleP2PMaxTemperaturePointY", "dwThermalP2PMaxTemperaturePointX", "dwThermalP2PMaxTemperaturePointY", "byROIRegionNum", "byRes2", "dwJpegPicLen", "struThermalROIRegionInfo", "pJpegPic", "byRes");
        }
    }

    public static class USB_SYSTEM_DIAGNOSED_DATA_COND extends Structure {
        public byte byDataType;
        public int dwAddress;
        public int dwLength;
        public int dwSize;
        public byte[] byRes1 = new byte[3];
        public byte[] byRes = new byte[112];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byDataType", "byRes1", "dwAddress", "dwLength", "byRes");
        }
    }

    public static class USB_SYSTEM_DIAGNOSED_DATA extends Structure {
        public byte[] byRes = new byte[56];
        public int dwDataLenth;
        public int dwSize;
        public Pointer pDiagnosedData;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "dwDataLenth", "pDiagnosedData", "byRes");
        }
    }

    public static class USB_THERMOMETRY_CALIBRATION_FILE extends Structure {
        public byte[] byFileName = new byte[64];
        public byte[] byRes = new byte[56];
        public int dwFileLenth;
        public int dwSize;
        public Pointer pCalibrationFile;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byFileName", "dwFileLenth", "pCalibrationFile", "byRes");
        }
    }

    public static class USB_UPGRADE_STATE_INFO extends Structure {
        public byte byProgress;
        public byte[] byRes = new byte[6];
        public byte byState;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byState", "byProgress", "byRes");
        }
    }

    public static class USB_UPGRADE_COND extends Structure {
        public byte[] byRes = new byte[11];
        public byte byTimeout;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byTimeout", "byRes");
        }
    }

    public static class REGION_VERTEX_COORDINATES extends Structure {
        public byte[] byRes = new byte[24];
        public int dwPointX;
        public int dwPointY;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwPointX", "dwPointY", "byRes");
        }
    }

    public static class THERMAL_EXPERT_REGIONS extends Structure {
        public byte byEnabled;
        public byte byPointNum;
        public byte byReflectiveEnable;
        public byte byRegionID;
        public byte byRes3;
        public byte byRule;
        public byte byShowAlarmColorEnabled;
        public byte byType;
        public int dwAlarm;
        public int dwAlert;
        public int dwDistance;
        public int dwEmissivity;
        public int dwReflectiveTemperature;
        public byte[] byRes1 = new byte[2];
        public byte[] byName = new byte[32];
        public byte[] byRes2 = new byte[3];
        public byte[] byRes4 = new byte[3];
        public REGION_VERTEX_COORDINATES[] struRegionCoordinate = new REGION_VERTEX_COORDINATES[10];
        public byte[] byRes = new byte[200];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byRegionID", "byEnabled", "byRes1", "byName", "dwEmissivity", "dwDistance", "byReflectiveEnable", "byRes2", "dwReflectiveTemperature", "byType", "byShowAlarmColorEnabled", "byRule", "byRes3", "dwAlert", "dwAlarm", "byPointNum", "byRes4", "struRegionCoordinate", "byRes");
        }
    }

    public static class USB_THERMOMETRY_EXPERT_REGIONS extends Structure {
        public byte byRegionNum;
        public int dwSize;
        public byte[] byRes1 = new byte[3];
        public THERMAL_EXPERT_REGIONS[] struExpertRegions = new THERMAL_EXPERT_REGIONS[21];
        public byte[] byRes = new byte[220];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byRegionNum", "byRes1", "struExpertRegions", "byRes");
        }
    }

    public static class THERMAL_EXPERT_TEMPERATURE extends Structure {
        public byte byID;
        public int dwPointX;
        public int dwPointY;
        public int dwPresetTemperature;
        public byte[] byRes1 = new byte[3];
        public byte[] byRes = new byte[24];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("byID", "byRes1", "dwPresetTemperature", "dwPointX", "dwPointY", "byRes");
        }
    }

    public static class USB_THERMOMETRY_EXPERT_CORRECTION_PARAM extends Structure {
        public byte byPointNum;
        public int dwDistance;
        public int dwEmissivity;
        public int dwEnviroTemperature;
        public int dwSize;
        public byte[] byRes2 = new byte[3];
        public THERMAL_EXPERT_TEMPERATURE[] struExpertTemperature = new THERMAL_EXPERT_TEMPERATURE[4];
        public byte[] byRes = new byte[64];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "dwDistance", "dwEnviroTemperature", "dwEmissivity", "byPointNum", "byRes2", "struExpertTemperature", "byRes");
        }
    }

    public static class USB_THERMOMETRY_RISE_SETTINGS extends Structure {
        public byte byEnabled;
        public byte[] byRes = new byte[128];
        public byte byRes1;
        public byte byResult;
        public byte byType;
        public int dwCoefficient;
        public int dwColdStartRate;
        public int dwColdStartRise;
        public int dwEnvTemperature;
        public int dwMaxTemperatureRise;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byEnabled", "byType", "byResult", "byRes1", "dwEnvTemperature", "dwCoefficient", "dwMaxTemperatureRise", "dwColdStartRate", "dwColdStartRise", "byRes");
        }
    }

    public static class USB_ENVIROTEMPERATURE_CORRECT extends Structure {
        public byte byCorrectEnabled;
        public byte byEnabled;
        public int dwCalibrationTemperature;
        public int dwEnviroTemperature;
        public int dwSize;
        public byte[] byRes1 = new byte[2];
        public byte[] byRes = new byte[112];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byEnabled", "byCorrectEnabled", "byRes1", "dwEnviroTemperature", "dwCalibrationTemperature", "byRes");
        }
    }

    public static class USB_VIDEO_PROPERTY extends Structure {
        public byte byFlag;
        public byte[] byRes = new byte[31];
        public int dwValue;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwValue", "byFlag", "byRes");
        }
    }

    public static class USB_COMMAND_STATE extends Structure {
        public byte[] byRes = new byte[3];
        public byte byState;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byState", "byRes");
        }
    }

    public static class USB_BEEP_AND_FLICKER extends Structure {
        public byte byBeepCount;
        public byte byBeepType;
        public byte byFlickerCount;
        public byte byFlickerType;
        public byte[] byRes = new byte[24];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byBeepType", "byBeepCount", "byFlickerType", "byFlickerCount", "byRes");
        }
    }

    public static class USB_CARD_ISSUE_VERSION extends Structure {
        public byte byDay;
        public byte byLanguage;
        public byte byMonth;
        public int dwSize;
        public int dwSoftwareVersion;
        public short wYear;
        public byte[] szDeviceName = new byte[32];
        public byte[] szSerialNumber = new byte[48];
        public byte[] byRes = new byte[35];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "szDeviceName", "szSerialNumber", "dwSoftwareVersion", "wYear", "byMonth", "byDay", "byLanguage", "byRes");
        }
    }

    public static class USB_CARD_PROTO extends Structure {
        public byte byProto;
        public byte[] byRes = new byte[27];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byProto", "byRes");
        }
    }

    public static class USB_WAIT_SECOND extends Structure {
        public byte[] byRes = new byte[27];
        public byte byWait;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byWait", "byRes");
        }
    }

    public static class USB_ACTIVATE_CARD_RES extends Structure {
        public byte byCardType;
        public byte bySelectVerifyLen;
        public byte bySerialLen;
        public int dwSize;
        public byte[] bySerial = new byte[10];
        public byte[] bySelectVerify = new byte[3];
        public byte[] byRes = new byte[12];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byCardType", "bySerialLen", "bySerial", "bySelectVerifyLen", "bySelectVerify", "byRes");
        }
    }

    public static class USB_CERTIFICATE_INFO extends Structure {
        public byte byCertificateType;
        public byte byRes2;
        public int dwSize;
        public short wFingerPrintInfoSize;
        public short wPicInfoSize;
        public short wWordInfoSize;
        public byte[] byWordInfo = new byte[256];
        public byte[] byPicInfo = new byte[1024];
        public byte[] byFingerPrintInfo = new byte[1024];
        public byte[] byRes = new byte[40];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "wWordInfoSize", "wPicInfoSize", "wFingerPrintInfoSize", "byCertificateType", "byRes2", "byWordInfo", "byPicInfo", "byFingerPrintInfo", "byRes");
        }
    }

    public static class USB_M1_PWD_VERIFY_INFO extends Structure {
        public byte byPasswordType;
        public byte bySectionNum;
        public int dwSize;
        public byte[] byRes1 = new byte[2];
        public byte[] byPassword = new byte[6];
        public byte[] byRes = new byte[18];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byPasswordType", "bySectionNum", "byRes1", "byPassword", "byRes");
        }
    }

    public static class USB_M1_BLOCK_ADDR extends Structure {
        public byte[] byRes = new byte[26];
        public int dwSize;
        public short wAddr;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "wAddr", "byRes");
        }
    }

    public static class USB_M1_BLOCK_DATA extends Structure {
        public byte[] byData = new byte[16];
        public byte[] byRes = new byte[12];
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byData", "byRes");
        }
    }

    public static class USB_M1_BLOCK_WRITE_DATA extends Structure {
        public byte byDataLen;
        public byte byRes1;
        public int dwSize;
        public short wAddr;
        public byte[] byData = new byte[16];
        public byte[] byRes = new byte[8];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "wAddr", "byDataLen", "byRes1", "byData", "byRes");
        }
    }

    public static class USB_M1_MODIFY_SCB extends Structure {
        public byte byRes1;
        public byte bySectionNum;
        public int dwSize;
        public byte[] byPasswordA = new byte[6];
        public byte[] byCtrlBits = new byte[4];
        public byte[] byPasswordB = new byte[6];
        public byte[] byRes = new byte[10];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "bySectionNum", "byPasswordA", "byRes1", "byCtrlBits", "byPasswordB", "byRes");
        }
    }

    public static class USB_M1_SECTION_ENCRYPT extends Structure {
        public byte byKeyType;
        public byte byNewKeyType;
        public byte byRes1;
        public byte bySectionID;
        public int dwSize;
        public byte[] byKeyAContent = new byte[6];
        public byte[] byNewKeyAContent = new byte[6];
        public byte[] byCtrlBits = new byte[4];
        public byte[] byNewKeyBContent = new byte[6];
        public byte[] byRes = new byte[34];

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "bySectionID", "byKeyType", "byKeyAContent", "byNewKeyType", "byRes1", "byNewKeyAContent", "byCtrlBits", "byNewKeyBContent", "byRes");
        }
    }

    public static class USB_M1_SECTION_ENCRYPT_RES extends Structure {
        public byte[] byRes = new byte[27];
        public byte byStatus;
        public int dwSize;

        @Override // com.sun.jna.Structure
        protected List<String> getFieldOrder() {
            return Arrays.asList("dwSize", "byStatus", "byRes");
        }
    }
}
