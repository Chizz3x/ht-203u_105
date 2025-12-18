package com.hti.xtherm.hti160203u.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.usb.UsbDevice;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hti.xtherm.hti160203u.R;
import com.hti.xtherm.hti160203u.ThermalApplication;
import com.hti.xtherm.hti160203u.base.BaseActivity;
import com.hti.xtherm.hti160203u.bean.F2Device;
import com.hti.xtherm.hti160203u.bean.UVCPoint;
import com.hti.xtherm.hti160203u.bean.UVCRange;
import com.hti.xtherm.hti160203u.helper.Alog;
import com.hti.xtherm.hti160203u.helper.BitmapHelper;
import com.hti.xtherm.hti160203u.helper.CameraHelper;
import com.hti.xtherm.hti160203u.helper.Config;
import com.hti.xtherm.hti160203u.helper.F2Helper;
import com.hti.xtherm.hti160203u.helper.GalleryHelper;
import com.hti.xtherm.hti160203u.helper.Helper;
import com.hti.xtherm.hti160203u.helper.ShareHelper;
import com.hti.xtherm.hti160203u.helper.UsbHelper;
import com.hti.xtherm.hti160203u.thread.FeatureSearchThread;
import com.hti.xtherm.hti160203u.thread.RecordVideoThread;
import com.hti.xtherm.hti160203u.widget.LoadView;
import com.hti.xtherm.hti160203u.widget.ThermalImageView;
import com.hti.xtherm.hti160203u.widget.popupview.CommonPopupWindow;
import com.hti.xtherm.seekbar.OnRangeChangedListener;
import com.hti.xtherm.seekbar.RangeSeekBar;
import com.kongzue.dialogx.dialogs.InputDialog;
import com.kongzue.dialogx.dialogs.MessageDialog;
import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.kongzue.dialogx.interfaces.OnBindView;
import com.kongzue.dialogx.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialogx.interfaces.OnInputDialogButtonClickListener;
import com.kongzue.dialogx.util.InputInfo;
import com.kongzue.dialogx.util.TextInfo;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/* loaded from: classes.dex */
public class HomeActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, UsbHelper.OnUsbCallback, F2Helper.OnF2DeviceCallback, View.OnClickListener, FeatureSearchThread.OnFeatureSearchCallback, RecordVideoThread.OnVideoFrameListener {
    private static final int WHAT_RECORD_UPDATE_TIME = 1;
    private RangeSeekBar brightness_seekbar;
    private LoadView center_loadview;
    private AnimationDrawable center_plugin_Animation;
    private ImageView center_pluginanimation;
    private TextView center_recordtime;
    private ThermalImageView center_thermalview;
    private View config_brightness;
    private TextView config_brightness_value;
    private View config_celsius;
    private ImageView config_celsius_select;
    private View config_contrast;
    private TextView config_contrast_value;
    private View config_detail;
    private ImageView config_detail_select;
    private TextView config_detail_value;
    private View config_distance;
    private TextView config_distance_value;
    private View config_emissivity;
    private TextView config_emissivity_value;
    private View config_fahrenheit;
    private ImageView config_fahrenheit_select;
    private View config_large;
    private TextView config_large_rangetext;
    private ImageView config_large_select;
    private View config_noise;
    private ImageView config_noise_select;
    private TextView config_noise_value;
    private View config_optical;
    private TextView config_optical_value;
    private View config_reflection;
    private ImageView config_reflection_select;
    private TextView config_reflection_value;
    private Button config_reset;
    private View config_small;
    private TextView config_small_rangetext;
    private ImageView config_small_select;
    private RangeSeekBar contrast_seekbar;
    private RangeSeekBar detail_seekbar;
    private RangeSeekBar distance_seekbar;
    private RangeSeekBar emissivity_seekbar;
    private View home_capture_menu;
    private View home_center_layout;
    private View home_config_menu;
    private View home_feature_menu;
    private View home_gallery_menu;
    private View home_left_layout;
    private View home_palette_menu;
    private ImageView home_record_menu;
    private View home_right_layout;
    private View home_setting_menu;
    private View home_shutter_menu;
    private CameraHelper mCameraHelper;
    private CommonPopupWindow mConfigPopupView;
    private F2Helper mF2Helper;
    private FeatureSearchThread mFeatureSearchThread;
    private CommonPopupWindow mPalettePopupView;
    private SoundPool mPlaySoundPool;
    private RecordVideoThread mRecordVideoThread;
    private CommonPopupWindow mSettingsPopupView;
    private CommonPopupWindow mTempFeaturePopupView;
    private RangeSeekBar noise_seekbar;
    private RangeSeekBar optical_seekbar;
    private View palette_blackhot;
    private ImageView palette_blackhot_select;
    private View palette_blend1;
    private ImageView palette_blend1_select;
    private View palette_blend2;
    private ImageView palette_blend2_select;
    private View palette_blue;
    private ImageView palette_blue_select;
    private View palette_brown;
    private ImageView palette_brown_select;
    private View palette_color1;
    private ImageView palette_color1_select;
    private View palette_color2;
    private ImageView palette_color2_select;
    private View palette_greenhot;
    private ImageView palette_greenhot_select;
    private View palette_icefire;
    private ImageView palette_icefire_select;
    private View palette_iron1;
    private ImageView palette_iron1_select;
    private View palette_iron2;
    private ImageView palette_iron2_select;
    private View palette_rain;
    private ImageView palette_rain_select;
    private View palette_rainbow;
    private ImageView palette_rainbow_select;
    private View palette_redhot;
    private ImageView palette_redhot_select;
    private View palette_whitehot;
    private ImageView palette_whitehot_select;
    // CUSTOM
    private View palette_rgb;
    private ImageView palette_rgb_select;
    private View palette_whbc;
    private ImageView palette_whbc_select;
    private View palette_humanorange;
    private ImageView palette_humanorange_select;
    private View palette_humandetect;
    private ImageView palette_humandetect_select;
    private View palette_humandetectbox;
    private ImageView palette_humandetectbox_select;
    private View palette_test;
    private ImageView palette_test_select;
    private LinearLayout popup_config_layout;
    private View popup_feature_layout;
    private ImageView popup_feature_line;
    private ImageView popup_feature_point;
    private ImageView popup_feature_rect;
    private LinearLayout popup_palette_layout;
    private LinearLayout popup_settings_layout;
    private RangeSeekBar reflection_seekbar;
    private View settings_alarmmax;
    private ImageView settings_alarmmax_select;
    private TextView settings_alarmmax_value;
    private View settings_alarmmin;
    private ImageView settings_alarmmin_select;
    private TextView settings_alarmmin_value;
    private View settings_camera;
    private ImageView settings_camera_select;
    private View settings_chinese;
    private ImageView settings_chinese_select;
    private View settings_english;
    private ImageView settings_english_select;
    private View settings_russian;
    private ImageView settings_russian_select;
    private View settings_watermark;
    private ImageView settings_watermark_select;
    private UsbHelper usb_helper;
    private int mPopupBaseWidth = -1;
    private int mPopupBaseHeight = -1;
    private int mPopupBaseItemHeight = -1;
    private float mPopupBaseItemNumber = 8.0f;
    private String[] permissions = Config.permissions_29;
    private long mRecordStartTime = 0;
    private int sound_id_alarm = -1;
    private int sound_playid_alarm = 0;
    private boolean mAlarmisPlay = false;
    private Runnable stopAlarmPlayRunnable = new Runnable() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.1
        @Override // java.lang.Runnable
        public void run() {
            HomeActivity.this.stopAlarm();
            Alog.e("停止播放警告", new Object[0]);
        }
    };
    private BroadcastReceiver screen_rotation_receiver = new AnonymousClass2();
    private Config.PatternType mPatternType = Config.PatternType.NONE;
    private View.OnClickListener mTempFeaturePopupViewClick = new View.OnClickListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.6
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();

            if (id == R.id.popup_feature_line) {
                HomeActivity homeActivity = HomeActivity.this;
                homeActivity.mPatternType = homeActivity.mPatternType == Config.PatternType.LINE
                        ? Config.PatternType.NONE
                        : Config.PatternType.LINE;

            } else if (id == R.id.popup_feature_point) {
                HomeActivity homeActivity2 = HomeActivity.this;
                homeActivity2.mPatternType = homeActivity2.mPatternType == Config.PatternType.POINT
                        ? Config.PatternType.NONE
                        : Config.PatternType.POINT;

            } else if (id == R.id.popup_feature_rect) {
                HomeActivity homeActivity3 = HomeActivity.this;
                homeActivity3.mPatternType = homeActivity3.mPatternType == Config.PatternType.RECT
                        ? Config.PatternType.NONE
                        : Config.PatternType.RECT;
            }
            HomeActivity.this.center_thermalview.setPatternType(HomeActivity.this.mPatternType);
            HomeActivity.this.updateTempFeaturePopupView();
            HomeActivity.this.mTempFeaturePopupView.dismiss();
        }
    };
    private Config.PaletteType mPaletteType = ShareHelper.getPaletteType();
    private View.OnClickListener mPalettePopupViewClick = new View.OnClickListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.7
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();

            if (id == R.id.palette_blackhot) {
                HomeActivity.this.mPaletteType = Config.PaletteType.BLACK_HOT;

            } else if (id == R.id.palette_blend1) {
                HomeActivity.this.mPaletteType = Config.PaletteType.BLEND_1;

            } else if (id == R.id.palette_blend2) {
                HomeActivity.this.mPaletteType = Config.PaletteType.BLEND_2;

            } else if (id == R.id.palette_blue) {
                HomeActivity.this.mPaletteType = Config.PaletteType.BLUE;

            } else if (id == R.id.palette_brown) {
                HomeActivity.this.mPaletteType = Config.PaletteType.BROWN;

            } else if (id == R.id.palette_color1) {
                HomeActivity.this.mPaletteType = Config.PaletteType.COLOR_1;

            } else if (id == R.id.palette_color2) {
                HomeActivity.this.mPaletteType = Config.PaletteType.COLOR_2;

            } else if (id == R.id.palette_greenhot) {
                HomeActivity.this.mPaletteType = Config.PaletteType.GREEN_HOT;

            } else if (id == R.id.palette_icefire) {
                HomeActivity.this.mPaletteType = Config.PaletteType.ICE_FIRE;

            } else if (id == R.id.palette_iron1) {
                HomeActivity.this.mPaletteType = Config.PaletteType.IRON_1;

            } else if (id == R.id.palette_iron2) {
                HomeActivity.this.mPaletteType = Config.PaletteType.IRON_2;

            } else if (id == R.id.palette_rain) {
                HomeActivity.this.mPaletteType = Config.PaletteType.RAIN;

            } else if (id == R.id.palette_rainbow) {
                HomeActivity.this.mPaletteType = Config.PaletteType.RAINBOW;

            } else if (id == R.id.palette_redhot) {
                HomeActivity.this.mPaletteType = Config.PaletteType.RED_HOT;

            } else if (id == R.id.palette_whitehot) {
                HomeActivity.this.mPaletteType = Config.PaletteType.WHITE_HOT;
            }
            // CUSTOM
             else if (id == R.id.palette_rgb) {
                HomeActivity.this.mPaletteType = Config.PaletteType.RGB;
            } else if (id == R.id.palette_whbc) {
                HomeActivity.this.mPaletteType = Config.PaletteType.WHBC;
            } else if (id == R.id.palette_humanorange) {
                HomeActivity.this.mPaletteType = Config.PaletteType.HUMAN_ORANGE;
            } else if (id == R.id.palette_humandetect) {
                HomeActivity.this.mPaletteType = Config.PaletteType.HUMAN_DETECT;
            } else if (id == R.id.palette_humandetectbox) {
                HomeActivity.this.mPaletteType = Config.PaletteType.HUMAN_DETECT_BOX;
            } else if (id == R.id.palette_test) {
                HomeActivity.this.mPaletteType = Config.PaletteType.TEST;
            }
            ShareHelper.setPaletteType(HomeActivity.this.mPaletteType);
            try {
                HomeActivity.this.mF2Helper.setPalette(HomeActivity.this.mPaletteType);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            HomeActivity.this.updatePalettePopupView();
            HomeActivity.this.mPalettePopupView.dismiss();
        }
    };
    private Config.Tempunit mTempunit = ShareHelper.getTempunit();
    private Config.TempRange mTemprange = Config.TempRange.SMALL;
    private int mEmissivity = ShareHelper.getEmissivity();
    private int mOpticalTemperature = ShareHelper.getOptical();
    private int mReflectionTemperature = ShareHelper.getRefleation();
    private int mDistance = ShareHelper.getDistance();
    private int mImageBrightness = ShareHelper.getBrightness();
    private int mImageContrast = ShareHelper.getContrast();
    private int mImageNoise = ShareHelper.getNoise();
    private int mImageDetail = ShareHelper.getDetail();
    private View.OnClickListener mConfigPopupViewClick = new AnonymousClass9();
    private boolean mCameraPreview = false;
    private boolean mWatermark = ShareHelper.getWarkmark();
    private Config.Language mAppLanguage = ShareHelper.getLanguage();
    private boolean alarmmax_enable = ShareHelper.getAlarmMaxEnable();
    private boolean alarmmin_enable = ShareHelper.getAlarmMinEnable();
    private int camera_rotation_angle = 0;
    private View.OnClickListener mSettingsPopupViewClick = new AnonymousClass19();

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected boolean onFullScreen() {
        return true;
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected boolean onKeepScreen() {
        return true;
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected int onLayout() {
        return R.layout.activity_home;
    }

    static /* synthetic */ int access$212(HomeActivity homeActivity, int i) {
        int i2 = homeActivity.camera_rotation_angle + i;
        homeActivity.camera_rotation_angle = i2;
        return i2;
    }

    /* renamed from: com.hti.xtherm.hti160203u.ui.activity.HomeActivity$2, reason: invalid class name */
    class AnonymousClass2 extends BroadcastReceiver {
        AnonymousClass2() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            HomeActivity.this.mHandler.post(new Runnable() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity$2$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    m106x5429f414();
                }
            });
        }

        /* renamed from: lambda$onReceive$0$com-hti-xtherm-hti160203u-ui-activity-HomeActivity$2, reason: not valid java name */
        /* synthetic */ void m106x5429f414() {
            Alog.e("旋转广播：", new Object[0]);
            int rotation = HomeActivity.this.getWindowManager().getDefaultDisplay().getRotation();
            HomeActivity.access$212(HomeActivity.this, 180);
            HomeActivity.this.camera_rotation_angle %= 360;
            if (rotation == 1) {
                HomeActivity.this.center_thermalview.setScreenThermalRotation(0);
            } else {
                HomeActivity.this.center_thermalview.setScreenThermalRotation(180);
            }
            HomeActivity.this.center_thermalview.setScreenCameraRotation(HomeActivity.this.camera_rotation_angle);
        }
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected void onInitView() {
        this.home_left_layout = findViewById(R.id.home_left_layout);
        this.home_gallery_menu = findViewById(R.id.home_gallery_menu);
        this.home_capture_menu = findViewById(R.id.home_capture_menu);
        this.home_record_menu = (ImageView) findViewById(R.id.home_record_menu);
        this.home_shutter_menu = findViewById(R.id.home_shutter_menu);
        this.home_gallery_menu.setOnClickListener(this);
        this.home_capture_menu.setOnClickListener(this);
        this.home_record_menu.setOnClickListener(this);
        this.home_shutter_menu.setOnClickListener(this);
        this.home_center_layout = findViewById(R.id.home_center_layout);
        this.center_thermalview = (ThermalImageView) findViewById(R.id.center_thermalview);
        this.center_recordtime = (TextView) findViewById(R.id.center_recordtime);
        this.center_loadview = (LoadView) findViewById(R.id.center_loadview);
        ImageView imageView = (ImageView) findViewById(R.id.center_pluginanimation);
        this.center_pluginanimation = imageView;
        this.center_plugin_Animation = (AnimationDrawable) imageView.getBackground();
        this.home_right_layout = findViewById(R.id.home_right_layout);
        this.home_feature_menu = findViewById(R.id.home_feature_menu);
        this.home_palette_menu = findViewById(R.id.home_palette_menu);
        this.home_config_menu = findViewById(R.id.home_config_menu);
        this.home_setting_menu = findViewById(R.id.home_setting_menu);
        this.home_feature_menu.setOnClickListener(this);
        this.home_palette_menu.setOnClickListener(this);
        this.home_config_menu.setOnClickListener(this);
        this.home_setting_menu.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.home_capture_menu) {
            try {
                capturePicture();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (id == R.id.home_config_menu) {
            showConfigPopupView();

        } else if (id == R.id.home_feature_menu) {
            showTempFeaturePopupView();

        } else if (id == R.id.home_gallery_menu) {
            if (this.mRecordVideoThread != null) {
                showVideoRecordingTip();
            } else {
                startActivity(new Intent(this, (Class<?>) MediaActivity.class));
            }

        } else if (id == R.id.home_palette_menu) {
            showPalettePopupView();

        } else if (id == R.id.home_record_menu) {
            try {
                recordVideo();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else if (id == R.id.home_setting_menu) {
            showSettingsPopupView();

        } else if (id == R.id.home_shutter_menu) {
            if (!this.mF2Helper.isPreview()) {
                tipFailed(R.string.public_device_failed_message_1);
            } else {
                tipSuccess(R.string.shutter_refresh);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            m98xd2f6f281();
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            }
        }
    }

    /* renamed from: lambda$onClick$0$com-hti-xtherm-hti160203u-ui-activity-HomeActivity, reason: not valid java name */
    /* synthetic */ void m98xd2f6f281() throws IllegalAccessException {
        this.mF2Helper.shutter();
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected void onHandlerMessage(Message message) {
        if (message.what != 1) {
            return;
        }
        this.center_recordtime.setText(Helper.getDurationText(System.currentTimeMillis() - this.mRecordStartTime));
        this.mHandler.sendEmptyMessageDelayed(1, 500L);
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected void onInitData() throws IOException {
        this.mCameraHelper = new CameraHelper(this, this.center_thermalview.getCameraRenderView());
        F2Helper f2Helper = new F2Helper(this, this);
        this.mF2Helper = f2Helper;
        f2Helper.setThermalImageView(this.center_thermalview);
        this.usb_helper = new UsbHelper(this);
        regsiter_rotation_broadcast();
        initSoundPool();
        request_permissions();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Alog.e("onNewIntent启动", new Object[0]);
        UsbHelper usbHelper = this.usb_helper;
        if (usbHelper != null) {
            usbHelper.search_hcusb_devices();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        stopRecordVideo();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        hidesPluginAnimation();
        stopRecordVideo();
        this.usb_helper.unregister_plug_event();
        this.mF2Helper.clean();
        this.mPlaySoundPool.release();
        unregisterReceiver(this.screen_rotation_receiver);
        System.exit(0);
    }

    @Override // com.hti.xtherm.hti160203u.helper.UsbHelper.OnUsbCallback
    public void on_usb_attached(UsbDevice usbDevice) {
        this.mF2Helper.deviceAttachedF2(usbDevice);
        hidesPluginAnimation();
        showLoad();
    }

    @Override // com.hti.xtherm.hti160203u.helper.UsbHelper.OnUsbCallback
    public void on_usb_detached(UsbDevice usbDevice) {
        this.mF2Helper.deviceDetachedF2(usbDevice);
        cancelLoad();
        showPluginAnimation();
    }

    @Override // com.hti.xtherm.hti160203u.helper.F2Helper.OnF2DeviceCallback
    public void on_open_f2_failed() {
        cancelLoad();
        showPluginAnimation();
        Alog.e("设备打开失败", new Object[0]);
        tipFailed(R.string.device_open_failed);
    }

    @Override // com.hti.xtherm.hti160203u.helper.F2Helper.OnF2DeviceCallback
    public void on_open_f2_success() {
        cancelLoad();
        Alog.e("设备打开成功", new Object[0]);
        this.mHandler.post(new Runnable() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                m100xf88027aa();
            }
        });
        F2Device previewDevice = this.mF2Helper.getPreviewDevice();
        if (previewDevice != null) {
            this.center_thermalview.reLayout(previewDevice.yuv_width, previewDevice.yuv_height);
        }
        this.center_thermalview.setPickEnable(true);
    }

    /* renamed from: lambda$on_open_f2_success$1$com-hti-xtherm-hti160203u-ui-activity-HomeActivity, reason: not valid java name */
    /* synthetic */ void m100xf88027aa() {
        this.mTemprange = this.mF2Helper.getTempRange();
        updateConfigPopupView();
    }

    @Override // com.hti.xtherm.hti160203u.helper.F2Helper.OnF2DeviceCallback
    public void on_f2_disconnect() {
        tipFailed(R.string.device_open_disconnect);
        this.mHandler.removeCallbacks(this.stopAlarmPlayRunnable);
        stopAlarm();
        stopRecordVideo();
        this.center_thermalview.setPickEnable(false);
        FeatureSearchThread featureSearchThread = this.mFeatureSearchThread;
        if (featureSearchThread != null) {
            featureSearchThread.setOnFeatureSearchCallback(null);
            this.mFeatureSearchThread.stopSearch();
            this.mFeatureSearchThread = null;
        }
        this.mHandler.post(new Runnable() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                m99x41b8e33f();
            }
        });
        showPluginAnimation();
    }

    /* renamed from: lambda$on_f2_disconnect$2$com-hti-xtherm-hti160203u-ui-activity-HomeActivity, reason: not valid java name */
    /* synthetic */ void m99x41b8e33f() {
        if (this.mCameraPreview) {
            this.center_thermalview.getCameraRenderView().setVisibility(View.GONE);
            this.mCameraHelper.stopPreviewCamera();
            this.mCameraPreview = false;
        }
    }

    @Override // com.hti.xtherm.hti160203u.helper.F2Helper.OnF2DeviceCallback
    public void on_f2_temperature(byte[] bArr, int i, int i2, boolean z) {
        if (z) {
            cancelLoad();
        }
        if (this.mFeatureSearchThread == null) {
            FeatureSearchThread featureSearchThreadLoad = FeatureSearchThread.load();
            this.mFeatureSearchThread = featureSearchThreadLoad;
            featureSearchThreadLoad.search(this);
        }
        this.mFeatureSearchThread.putTemperatureBuffer(bArr, i, i2);
    }

    @Override // com.hti.xtherm.hti160203u.thread.FeatureSearchThread.OnFeatureSearchCallback
    public List<UVCRange> onSearchRanges() {
        return this.center_thermalview.getPickRanges();
    }

    @Override // com.hti.xtherm.hti160203u.thread.FeatureSearchThread.OnFeatureSearchCallback
    public Config.TempRange onTempRange() {
        return this.mTemprange;
    }

    @Override // com.hti.xtherm.hti160203u.thread.FeatureSearchThread.OnFeatureSearchCallback
    public void onSearchFeature(List<UVCPoint> list) {
        this.center_thermalview.setPickFeatures(list);
    }

    @Override // com.hti.xtherm.hti160203u.thread.FeatureSearchThread.OnFeatureSearchCallback
    public void onTemperatureAlarm(Config.AlarmType alarmType, boolean z) {
        if (z) {
            playAlarm();
        }
        this.center_thermalview.setAlarmEnable(alarmType, z);
    }

    @Override // com.hti.xtherm.hti160203u.thread.FeatureSearchThread.OnFeatureSearchCallback
    public int onScreenRotation() {
        return getWindowManager().getDefaultDisplay().getRotation();
    }

    @Override // com.hti.xtherm.hti160203u.thread.RecordVideoThread.OnVideoFrameListener
    public Bitmap onRecordFrame() {
        return this.center_thermalview.getThermalBitmap();
    }

    @Override // com.hti.xtherm.hti160203u.thread.RecordVideoThread.OnVideoFrameListener
    public void onRecordFileShort() {
        tipWarning(R.string.record_video_failed_message_4);
    }

    @Override // com.hti.xtherm.hti160203u.helper.UsbHelper.OnUsbCallback
    public boolean on_usb_using() {
        return this.mF2Helper.isPreview();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) throws SecurityException, IllegalArgumentException {
        super.onRequestPermissionsResult(i, strArr, iArr);
        EasyPermissions.onRequestPermissionsResult(i, strArr, iArr, this);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == Config.PERMISSIONS_CODE) {
            request_permissions();
        }
    }

    @Override // pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
    public void onPermissionsGranted(int i, List<String> list) {
        if (EasyPermissions.hasPermissions(this, this.permissions)) {
            request_permissions();
        }
    }

    @Override // pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks
    public void onPermissionsDenied(int i, List<String> list) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, list)) {
            if (list != null) {
                StringBuffer stringBuffer = new StringBuffer();
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    stringBuffer.append(it.next() + "++++");
                }
                Alog.e("失败的权限：" + ((Object) stringBuffer), new Object[0]);
            }
            show_permission_denied_dialog();
            return;
        }
        request_permissions();
    }

    private void showVideoRecordingTip() {
        show_message_dialog(R.string.recording_tip_title, R.string.recording_tip_message, R.string.recording_tip_ok, new OnDialogButtonClickListener<MessageDialog>() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.3
            @Override // com.kongzue.dialogx.interfaces.OnDialogButtonClickListener
            public boolean onClick(MessageDialog messageDialog, View view) {
                HomeActivity.this.startActivity(new Intent(HomeActivity.this, (Class<?>) MediaActivity.class));
                return false;
            }
        }, R.string.recording_tip_cancel, null, true);
    }

    private void regsiter_rotation_broadcast() {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.CONFIGURATION_CHANGED");
        if (Build.VERSION.SDK_INT >= 34) {
            registerReceiver(this.screen_rotation_receiver, intentFilter, Context.RECEIVER_EXPORTED);
        } else {
            registerReceiver(this.screen_rotation_receiver, intentFilter);
        }
    }

    private void showPluginAnimation() {
        ImageView imageView = this.center_pluginanimation;
        if (imageView != null) {
            imageView.post(new Runnable() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    m103xc602ed13();
                }
            });
        }
    }

    /* renamed from: lambda$showPluginAnimation$3$com-hti-xtherm-hti160203u-ui-activity-HomeActivity, reason: not valid java name */
    /* synthetic */ void m103xc602ed13() {
        this.center_pluginanimation.setVisibility(View.VISIBLE);
        this.center_plugin_Animation.start();
    }

    private void hidesPluginAnimation() {
        ImageView imageView = this.center_pluginanimation;
        if (imageView != null) {
            imageView.post(new Runnable() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity$$ExternalSyntheticLambda6
                @Override // java.lang.Runnable
                public final void run() {
                    m97x44b7250c();
                }
            });
        }
    }

    /* renamed from: lambda$hidesPluginAnimation$4$com-hti-xtherm-hti160203u-ui-activity-HomeActivity, reason: not valid java name */
    /* synthetic */ void m97x44b7250c() {
        this.center_pluginanimation.setVisibility(View.GONE);
        this.center_plugin_Animation.stop();
    }

    private void showLoad() {
        LoadView loadView = this.center_loadview;
        if (loadView != null) {
            loadView.post(new Runnable() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    m102x13e76028();
                }
            });
        }
    }

    /* renamed from: lambda$showLoad$5$com-hti-xtherm-hti160203u-ui-activity-HomeActivity, reason: not valid java name */
    /* synthetic */ void m102x13e76028() {
        this.center_loadview.load();
    }

    private void cancelLoad() {
        LoadView loadView = this.center_loadview;
        if (loadView != null) {
            loadView.post(new Runnable() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    m96x4929e4ea();
                }
            });
        }
    }

    /* renamed from: lambda$cancelLoad$6$com-hti-xtherm-hti160203u-ui-activity-HomeActivity, reason: not valid java name */
    /* synthetic */ void m96x4929e4ea() {
        this.center_loadview.cancel();
    }

    private void playAlarm() {
        this.mHandler.removeCallbacks(this.stopAlarmPlayRunnable);
        if (!this.mAlarmisPlay) {
            int iPlay = this.mPlaySoundPool.play(this.sound_id_alarm, 1.0f, 1.0f, 1, -1, 1.0f);
            this.sound_playid_alarm = iPlay;
            if (iPlay != 0) {
                this.mAlarmisPlay = true;
            }
        }
        this.mHandler.postDelayed(this.stopAlarmPlayRunnable, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void stopAlarm() {
        SoundPool soundPool = this.mPlaySoundPool;
        if (soundPool != null) {
            soundPool.stop(this.sound_playid_alarm);
            Alog.e("停止播放", new Object[0]);
        }
        this.center_thermalview.setAlarmEnable(Config.AlarmType.MAX, false);
        this.center_thermalview.setAlarmEnable(Config.AlarmType.MIN, false);
        this.mAlarmisPlay = false;
    }

    private void initSoundPool() {
        SoundPool soundPoolBuild = new SoundPool.Builder().setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(5).build()).build();
        this.mPlaySoundPool = soundPoolBuild;
        this.sound_id_alarm = soundPoolBuild.load(this, R.raw.raw_alarm, 1);
        Alog.e("音频加载ID = " + this.sound_id_alarm, new Object[0]);
    }

    private void capturePicture() throws IOException {
        if (!this.mF2Helper.isPreview()) {
            tipFailed(R.string.capture_failed_1);
            return;
        }
        Bitmap thermalBitmap = this.center_thermalview.getThermalBitmap();
        int i = R.string.capture_failed_2;
        if (thermalBitmap == null || thermalBitmap.isRecycled()) {
            tipFailed(R.string.capture_failed_2);
            return;
        }
        if (GalleryHelper.insertBitmapToGallery(BitmapHelper.scale(thermalBitmap, Config.MEDIA_RESOLUTION.getWidth(), Config.MEDIA_RESOLUTION.getHeight()))) {
            i = R.string.capture_success;
        }
        PopTip.show(getStringByid(i));
    }

    private void recordVideo() throws IOException {
        if (this.mRecordVideoThread == null) {
            startRecordVideo();
        } else {
            stopRecordVideo();
        }
    }

    private void startRecordVideo() throws IOException {
        if (!this.mF2Helper.isPreview()) {
            tipFailed(R.string.record_video_failed_message_1);
            return;
        }
        ThermalImageView thermalImageView = this.center_thermalview;
        if (thermalImageView == null || thermalImageView.getMeasuredWidth() * this.center_thermalview.getMeasuredHeight() <= 0) {
            tipFailed(R.string.record_video_failed_message_2);
            return;
        }
        RecordVideoThread recordVideoThreadLoad = RecordVideoThread.load();
        this.mRecordVideoThread = recordVideoThreadLoad;
        if (recordVideoThreadLoad == null) {
            tipFailed(R.string.record_video_failed_message_3);
            return;
        }
        recordVideoThreadLoad.record(this);
        this.mRecordStartTime = System.currentTimeMillis();
        updateRecordViews();
        this.mHandler.sendEmptyMessage(1);
    }

    private void stopRecordVideo() {
        this.mHandler.post(new Runnable() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                m104x96f8491b();
            }
        });
    }

    /* renamed from: lambda$stopRecordVideo$7$com-hti-xtherm-hti160203u-ui-activity-HomeActivity, reason: not valid java name */
    /* synthetic */ void m104x96f8491b() {
        RecordVideoThread recordVideoThread = this.mRecordVideoThread;
        if (recordVideoThread != null) {
            recordVideoThread.stopRecord();
            this.mRecordVideoThread = null;
        }
        this.mHandler.removeMessages(1);
        updateRecordViews();
    }

    private void updateRecordViews() {
        boolean z = this.mRecordVideoThread != null;
        this.center_recordtime.setVisibility(z ? View.VISIBLE : View.GONE);
        this.home_record_menu.setImageResource(z ? R.mipmap.ic_menu_record_focus : R.mipmap.ic_menu_record_normal);
    }

    private int getPopupBaseWidth() {
        if (this.mPopupBaseWidth == -1) {
            this.mPopupBaseWidth = ((int) (Helper.getScreenSize().getWidth() / ((getResources().getInteger(R.integer.home_left_layout_weight) + getResources().getInteger(R.integer.home_center_layout_weight)) + getResources().getInteger(R.integer.home_right_layout_weight)))) * ThermalApplication.getAppContext().getResources().getInteger(R.integer.home_right_layout_weight);
        }
        return this.mPopupBaseWidth;
    }

    private int getPopupBaseHeight() {
        if (this.mPopupBaseHeight == -1) {
            this.mPopupBaseHeight = Helper.getScreenSize().getHeight() - 10;
        }
        return this.mPopupBaseHeight;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getPopupBaseItemHeight() {
        if (this.mPopupBaseItemHeight == -1) {
            this.mPopupBaseItemHeight = (int) (getPopupBaseHeight() / this.mPopupBaseItemNumber);
        }
        return this.mPopupBaseItemHeight;
    }

    private void request_permissions() {
        if (Build.VERSION.SDK_INT <= 28) {
            this.permissions = Config.permissions_28;
            Alog.e("需要申请SD卡读写权限", new Object[0]);
        } else {
            this.permissions = Config.permissions_29;
            Alog.e("不需要申请SD卡读写权限", new Object[0]);
        }
        if (EasyPermissions.hasPermissions(this, this.permissions)) {
            showPluginAnimation();
            this.usb_helper.register_plug_event(this);
        } else {
            requestPermissions(this.permissions, Config.PERMISSIONS_CODE);
        }
    }

    private void show_permission_denied_dialog() {
        show_message_dialog(R.string.permission_denied_title, R.string.permission_denied_message, R.string.permission_denied_ok, new OnDialogButtonClickListener<MessageDialog>() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.4
            @Override // com.kongzue.dialogx.interfaces.OnDialogButtonClickListener
            public boolean onClick(MessageDialog messageDialog, View view) {
                Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
                intent.setData(Uri.parse("package:" + ThermalApplication.getAppContext().getPackageName()));
                HomeActivity.this.startActivityForResult(intent, Config.PERMISSIONS_CODE);
                return false;
            }
        }, R.string.permission_denied_cancel, new OnDialogButtonClickListener<MessageDialog>() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.5
            @Override // com.kongzue.dialogx.interfaces.OnDialogButtonClickListener
            public boolean onClick(MessageDialog messageDialog, View view) {
                HomeActivity.this.finish();
                return false;
            }
        }, false);
    }

    private void showTempFeaturePopupView() {
        makeTempFeaturePopupView();
        CommonPopupWindow commonPopupWindow = this.mTempFeaturePopupView;
        if (commonPopupWindow != null) {
            if (commonPopupWindow.isShowing()) {
                this.mTempFeaturePopupView.dismiss();
            }
            this.mTempFeaturePopupView.showAsDropDown(this.home_right_layout, (0 - getPopupBaseWidth()) - 10, (0 - getPopupBaseHeight()) + 10, 3);
        }
    }

    private void makeTempFeaturePopupView() {
        if (this.mTempFeaturePopupView == null) {
            CommonPopupWindow commonPopupWindowCreate = new CommonPopupWindow.Builder(getLanguageContext(this, ShareHelper.getLanguage())).setView(R.layout.popup_feature_layout).setBackGroundLevel(1.0f).setWidthAndHeight(getPopupBaseWidth(), getPopupBaseHeight()).setOutsideTouchable(true).create();
            this.mTempFeaturePopupView = commonPopupWindowCreate;
            commonPopupWindowCreate.setFocusable(true);
            View contentView = this.mTempFeaturePopupView.getContentView();
            this.popup_feature_layout = contentView;
            this.popup_feature_point = (ImageView) contentView.findViewById(R.id.popup_feature_point);
            this.popup_feature_line = (ImageView) this.popup_feature_layout.findViewById(R.id.popup_feature_line);
            this.popup_feature_rect = (ImageView) this.popup_feature_layout.findViewById(R.id.popup_feature_rect);
            this.popup_feature_point.setOnClickListener(this.mTempFeaturePopupViewClick);
            this.popup_feature_line.setOnClickListener(this.mTempFeaturePopupViewClick);
            this.popup_feature_rect.setOnClickListener(this.mTempFeaturePopupViewClick);
        }
        updateTempFeaturePopupView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTempFeaturePopupView() {
        if (this.mTempFeaturePopupView == null) {
            return;
        }
        this.popup_feature_point.setImageResource(this.mPatternType == Config.PatternType.POINT ? R.mipmap.ic_feature_point_focus : R.mipmap.ic_feature_point_normal);
        this.popup_feature_line.setImageResource(this.mPatternType == Config.PatternType.LINE ? R.mipmap.ic_feature_line_focus : R.mipmap.ic_feature_line_normal);
        this.popup_feature_rect.setImageResource(this.mPatternType == Config.PatternType.RECT ? R.mipmap.ic_feature_rect_focus : R.mipmap.ic_feature_rect_normal);
    }

    private void showPalettePopupView() {
        makePalettePopupView();
        CommonPopupWindow commonPopupWindow = this.mPalettePopupView;
        if (commonPopupWindow != null) {
            if (commonPopupWindow.isShowing()) {
                this.mPalettePopupView.dismiss();
            }
            this.mPalettePopupView.showAsDropDown(this.home_right_layout, (0 - (getPopupBaseWidth() * 3)) - 10, (0 - getPopupBaseHeight()) + 10, 3);
        }
    }

    private void makePalettePopupView() {
        if (this.mPalettePopupView == null) {
            CommonPopupWindow commonPopupWindowCreate = new CommonPopupWindow.Builder(getLanguageContext(this, ShareHelper.getLanguage())).setView(R.layout.popup_palette_layout).setBackGroundLevel(1.0f).setWidthAndHeight(getPopupBaseWidth() * 3, getPopupBaseHeight()).setOutsideTouchable(true).create();
            this.mPalettePopupView = commonPopupWindowCreate;
            commonPopupWindowCreate.setFocusable(true);
            View contentView = this.mPalettePopupView.getContentView();
            LinearLayout linearLayout = (LinearLayout) contentView.findViewById(R.id.popup_palette_layout);
            this.popup_palette_layout = linearLayout;
            linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.8
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (HomeActivity.this.popup_palette_layout.getLayoutParams() == null) {
                        return;
                    }
                    HomeActivity.this.popup_palette_layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int popupBaseItemHeight = HomeActivity.this.getPopupBaseItemHeight();
                    for (int i = 0; i < HomeActivity.this.popup_palette_layout.getChildCount(); i++) {
                        View childAt = HomeActivity.this.popup_palette_layout.getChildAt(i);
                        if (childAt instanceof ViewGroup) {
                            ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                            if (layoutParams == null) {
                                layoutParams = new ViewGroup.LayoutParams(-1, 0);
                            }
                            layoutParams.height = popupBaseItemHeight;
                            childAt.setLayoutParams(layoutParams);
                        }
                    }
                }
            });
            this.palette_whitehot = contentView.findViewById(R.id.palette_whitehot);
            this.palette_whitehot_select = (ImageView) contentView.findViewById(R.id.palette_whitehot_select);
            this.palette_whitehot.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_blackhot = contentView.findViewById(R.id.palette_blackhot);
            this.palette_blackhot_select = (ImageView) contentView.findViewById(R.id.palette_blackhot_select);
            this.palette_blackhot.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_blend1 = contentView.findViewById(R.id.palette_blend1);
            this.palette_blend1_select = (ImageView) contentView.findViewById(R.id.palette_blend1_select);
            this.palette_blend1.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_rainbow = contentView.findViewById(R.id.palette_rainbow);
            this.palette_rainbow_select = (ImageView) contentView.findViewById(R.id.palette_rainbow_select);
            this.palette_rainbow.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_blend2 = contentView.findViewById(R.id.palette_blend2);
            this.palette_blend2_select = (ImageView) contentView.findViewById(R.id.palette_blend2_select);
            this.palette_blend2.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_iron1 = contentView.findViewById(R.id.palette_iron1);
            this.palette_iron1_select = (ImageView) contentView.findViewById(R.id.palette_iron1_select);
            this.palette_iron1.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_iron2 = contentView.findViewById(R.id.palette_iron2);
            this.palette_iron2_select = (ImageView) contentView.findViewById(R.id.palette_iron2_select);
            this.palette_iron2.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_brown = contentView.findViewById(R.id.palette_brown);
            this.palette_brown_select = (ImageView) contentView.findViewById(R.id.palette_brown_select);
            this.palette_brown.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_color1 = contentView.findViewById(R.id.palette_color1);
            this.palette_color1_select = (ImageView) contentView.findViewById(R.id.palette_color1_select);
            this.palette_color1.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_color2 = contentView.findViewById(R.id.palette_color2);
            this.palette_color2_select = (ImageView) contentView.findViewById(R.id.palette_color2_select);
            this.palette_color2.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_icefire = contentView.findViewById(R.id.palette_icefire);
            this.palette_icefire_select = (ImageView) contentView.findViewById(R.id.palette_icefire_select);
            this.palette_icefire.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_rain = contentView.findViewById(R.id.palette_rain);
            this.palette_rain_select = (ImageView) contentView.findViewById(R.id.palette_rain_select);
            this.palette_rain.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_redhot = contentView.findViewById(R.id.palette_redhot);
            this.palette_redhot_select = (ImageView) contentView.findViewById(R.id.palette_redhot_select);
            this.palette_redhot.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_greenhot = contentView.findViewById(R.id.palette_greenhot);
            this.palette_greenhot_select = (ImageView) contentView.findViewById(R.id.palette_greenhot_select);
            this.palette_greenhot.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_blue = contentView.findViewById(R.id.palette_blue);
            this.palette_blue_select = (ImageView) contentView.findViewById(R.id.palette_blue_select);
            this.palette_blue.setOnClickListener(this.mPalettePopupViewClick);
            // CUSTOM
            this.palette_rgb = contentView.findViewById(R.id.palette_rgb);
            this.palette_rgb_select = (ImageView) contentView.findViewById(R.id.palette_rgb_select);
            this.palette_rgb.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_whbc = contentView.findViewById(R.id.palette_whbc);
            this.palette_whbc_select = (ImageView) contentView.findViewById(R.id.palette_whbc_select);
            this.palette_whbc.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_humanorange = contentView.findViewById(R.id.palette_humanorange);
            this.palette_humanorange_select = (ImageView) contentView.findViewById(R.id.palette_humanorange_select);
            this.palette_humanorange.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_humandetect = contentView.findViewById(R.id.palette_humandetect);
            this.palette_humandetect_select = (ImageView) contentView.findViewById(R.id.palette_humandetect_select);
            this.palette_humandetect.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_humandetectbox = contentView.findViewById(R.id.palette_humandetectbox);
            this.palette_humandetectbox_select = (ImageView) contentView.findViewById(R.id.palette_humandetectbox_select);
            this.palette_humandetectbox.setOnClickListener(this.mPalettePopupViewClick);
            this.palette_test = contentView.findViewById(R.id.palette_test);
            this.palette_test_select = (ImageView) contentView.findViewById(R.id.palette_test_select);
            this.palette_test.setOnClickListener(this.mPalettePopupViewClick);
        }
        updatePalettePopupView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePalettePopupView() {
        if (this.mPalettePopupView == null) {
            return;
        }
        ImageView imageView = this.palette_whitehot_select;
        Config.PaletteType paletteType = this.mPaletteType;
        Config.PaletteType paletteType2 = Config.PaletteType.WHITE_HOT;
        int i = R.mipmap.ic_public_select_focus;
        imageView.setImageResource(paletteType == paletteType2 ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_blackhot_select.setImageResource(this.mPaletteType == Config.PaletteType.BLACK_HOT ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_blend1_select.setImageResource(this.mPaletteType == Config.PaletteType.BLEND_1 ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_rainbow_select.setImageResource(this.mPaletteType == Config.PaletteType.RAINBOW ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_blend2_select.setImageResource(this.mPaletteType == Config.PaletteType.BLEND_2 ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_iron1_select.setImageResource(this.mPaletteType == Config.PaletteType.IRON_1 ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_iron2_select.setImageResource(this.mPaletteType == Config.PaletteType.IRON_2 ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_brown_select.setImageResource(this.mPaletteType == Config.PaletteType.BROWN ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_color1_select.setImageResource(this.mPaletteType == Config.PaletteType.COLOR_1 ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_color2_select.setImageResource(this.mPaletteType == Config.PaletteType.COLOR_2 ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_icefire_select.setImageResource(this.mPaletteType == Config.PaletteType.ICE_FIRE ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_rain_select.setImageResource(this.mPaletteType == Config.PaletteType.RAIN ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_redhot_select.setImageResource(this.mPaletteType == Config.PaletteType.RED_HOT ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_greenhot_select.setImageResource(this.mPaletteType == Config.PaletteType.GREEN_HOT ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        // CUSTOM
        this.palette_rgb_select.setImageResource(this.mPaletteType == Config.PaletteType.RGB ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_whbc_select.setImageResource(this.mPaletteType == Config.PaletteType.WHBC ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_humanorange_select.setImageResource(this.mPaletteType == Config.PaletteType.HUMAN_ORANGE ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_humandetect_select.setImageResource(this.mPaletteType == Config.PaletteType.HUMAN_DETECT ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_humandetectbox_select.setImageResource(this.mPaletteType == Config.PaletteType.HUMAN_DETECT_BOX ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.palette_test_select.setImageResource(this.mPaletteType == Config.PaletteType.TEST ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        ImageView imageView2 = this.palette_blue_select;
        if (this.mPaletteType != Config.PaletteType.BLUE) {
            i = R.mipmap.ic_public_select_normal;
        }
        imageView2.setImageResource(i);
    }

    /* renamed from: com.hti.xtherm.hti160203u.ui.activity.HomeActivity$9, reason: invalid class name */
    class AnonymousClass9 implements View.OnClickListener {
        AnonymousClass9() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();

            if (id == R.id.config_brightness) {
                HomeActivity.this.inputBrightness();

            } else if (id == R.id.config_celsius) {
                HomeActivity.this.mTempunit = Config.Tempunit.Celsius;
                HomeActivity.this.updateConfigPopupView();
                HomeActivity.this.mConfigPopupView.dismiss();
                ShareHelper.setTempunit(HomeActivity.this.mTempunit);

            } else if (id == R.id.config_contrast) {
                HomeActivity.this.inputContrast();

            } else if (id == R.id.config_detail) {
                HomeActivity.this.inputDetail();

            } else if (id == R.id.config_detail_select) {
                boolean z = !ShareHelper.getDetailEnable();
                ShareHelper.setDetailEnable(z);
                try {
                    HomeActivity.this.mF2Helper.setDetailEnhancement(ShareHelper.getDetail(), z);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                HomeActivity.this.updateConfigPopupView();

            } else if (id == R.id.config_distance) {
                HomeActivity.this.inputDistance();

            } else if (id == R.id.config_emissivity) {
                HomeActivity.this.inputEmissivity();

            } else if (id == R.id.config_fahrenheit) {
                HomeActivity.this.mTempunit = Config.Tempunit.Fahrenheit;
                HomeActivity.this.updateConfigPopupView();
                HomeActivity.this.mConfigPopupView.dismiss();
                ShareHelper.setTempunit(HomeActivity.this.mTempunit);

            } else if (id == R.id.config_large) {
                if (HomeActivity.this.mTemprange != Config.TempRange.LARGE) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            m107xf1045ad6();
                        }
                    }).start();
                }
                HomeActivity.this.mConfigPopupView.dismiss();

            } else if (id == R.id.config_noise) {
                HomeActivity.this.inputNoise();

            } else if (id == R.id.config_noise_select) {
                boolean z2 = !ShareHelper.getNoiseEnable();
                ShareHelper.setNoiseEnable(z2);
                try {
                    HomeActivity.this.mF2Helper.setNoiseReducation(ShareHelper.getNoise(), z2);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                HomeActivity.this.updateConfigPopupView();

            } else if (id == R.id.config_optical) {
                HomeActivity.this.inputOptical();

            } else if (id == R.id.config_reflection) {
                HomeActivity.this.inputReflection();

            } else if (id == R.id.config_reflection_select) {
                boolean z3 = !ShareHelper.getRefleationEnable();
                ShareHelper.setRefleationEnable(z3);
                HomeActivity.this.mF2Helper.setReflectionTemperature(ShareHelper.getRefleation(), z3);
                HomeActivity.this.updateConfigPopupView();

            } else if (id == R.id.config_reset) {
                HomeActivity.this.mConfigPopupView.dismiss();
                HomeActivity.this.show_message_dialog(
                        R.string.config_reset_dialog_title,
                        R.string.config_reset_dialog_message,
                        R.string.public_dialog_ok,
                        new OnDialogButtonClickListener<MessageDialog>() {
                            @Override
                            public boolean onClick(MessageDialog messageDialog, View view2) {
                                HomeActivity.this.resetConfigParameter();
                                return false;
                            }
                        },
                        R.string.public_dialog_cancel,
                        null,
                        true
                );

            } else if (id == R.id.config_small) {
                if (HomeActivity.this.mTemprange != Config.TempRange.SMALL) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            m108xb1fd975();
                        }
                    }).start();
                }
                HomeActivity.this.mConfigPopupView.dismiss();
            }
        }

        /* renamed from: lambda$onClick$0$com-hti-xtherm-hti160203u-ui-activity-HomeActivity$9, reason: not valid java name */
        /* synthetic */ void m107xf1045ad6() {
            try {
                WaitDialog.show(HomeActivity.this.getStringByid(R.string.gain_switch_wait_message));
                boolean tempRange = HomeActivity.this.mF2Helper.setTempRange(Config.TempRange.LARGE);
                WaitDialog.dismiss();
                if (!tempRange) {
                    HomeActivity.this.tipFailed(R.string.gain_switch_failed_message);
                    return;
                }
                HomeActivity.this.mTemprange = Config.TempRange.LARGE;
                HomeActivity.this.tipSuccess(R.string.gain_switch_success_message);
            } catch (InterruptedException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        /* renamed from: lambda$onClick$1$com-hti-xtherm-hti160203u-ui-activity-HomeActivity$9, reason: not valid java name */
        /* synthetic */ void m108xb1fd975() {
            try {
                WaitDialog.show(HomeActivity.this.getStringByid(R.string.gain_switch_wait_message));
                boolean tempRange = HomeActivity.this.mF2Helper.setTempRange(Config.TempRange.SMALL);
                WaitDialog.dismiss();
                if (!tempRange) {
                    HomeActivity.this.tipFailed(R.string.gain_switch_failed_message);
                    return;
                }
                HomeActivity.this.mTemprange = Config.TempRange.SMALL;
                HomeActivity.this.tipSuccess(R.string.gain_switch_success_message);
            } catch (InterruptedException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inputEmissivity() {
        this.mConfigPopupView.dismiss();
        TextInfo textInfo = new TextInfo();
        textInfo.setFontColor(Color.parseColor("#007aff"));
        textInfo.setBold(true);
        MessageDialog.build(new OnBindView<MessageDialog>(R.layout.dialog_input_emissivity) { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.10
            @Override // com.kongzue.dialogx.interfaces.OnBindView
            public void onBind(MessageDialog messageDialog, View view) {
                HomeActivity.this.emissivity_seekbar = (RangeSeekBar) view.findViewById(R.id.emissivity_seekbar);
                HomeActivity.this.emissivity_seekbar.setProgress(ShareHelper.getEmissivity());
                HomeActivity.this.emissivity_seekbar.setIndicatorText(String.format("E=%.2f", Float.valueOf(ShareHelper.getEmissivity() / 100.0f)));
                HomeActivity.this.emissivity_seekbar.setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.10.1
                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onRangeChanged(RangeSeekBar rangeSeekBar, float f, float f2, boolean z) {
                        HomeActivity.this.emissivity_seekbar.setIndicatorText(String.format("E=%.2f", Float.valueOf(f / 100.0f)));
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) throws IllegalAccessException {
                        int iRound = Math.round(rangeSeekBar.getRangeSeekBarState()[0].value);
                        ShareHelper.setEmissivity(iRound);
                        HomeActivity.this.mF2Helper.setEmissivity(iRound);
                        HomeActivity.this.updateConfigPopupView();
                    }
                });
            }
        }).setTitle(getStringByid(R.string.config_input_emissivity_title)).setMessage(getStringByid(R.string.config_input_emissivity_message)).setOkButton(getStringByid(R.string.public_dialog_ok)).setOkTextInfo(textInfo).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inputOptical() {
        this.mConfigPopupView.dismiss();
        TextInfo textInfo = new TextInfo();
        textInfo.setFontColor(Color.parseColor("#007aff"));
        textInfo.setBold(true);
        MessageDialog.build(new OnBindView<MessageDialog>(R.layout.dialog_input_optical) { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.11
            @Override // com.kongzue.dialogx.interfaces.OnBindView
            public void onBind(MessageDialog messageDialog, View view) {
                HomeActivity.this.optical_seekbar = (RangeSeekBar) view.findViewById(R.id.optical_seekbar);
                HomeActivity.this.optical_seekbar.setProgress(ShareHelper.getOptical());
                HomeActivity.this.optical_seekbar.setIndicatorText(Helper.getTemperatureCharacters(ShareHelper.getOptical() / 10.0f, HomeActivity.this.mTempunit));
                HomeActivity.this.optical_seekbar.setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.11.1
                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onRangeChanged(RangeSeekBar rangeSeekBar, float f, float f2, boolean z) {
                        HomeActivity.this.optical_seekbar.setIndicatorText(Helper.getTemperatureCharacters(f / 10.0f, HomeActivity.this.mTempunit));
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
                        int iRound = Math.round(rangeSeekBar.getRangeSeekBarState()[0].value);
                        ShareHelper.setOptical(iRound);
                        HomeActivity.this.mF2Helper.setOpticalTemperature(iRound);
                        HomeActivity.this.updateConfigPopupView();
                    }
                });
            }
        }).setTitle(getStringByid(R.string.config_input_optical_title)).setMessage(getStringByid(R.string.config_input_optical_message)).setOkButton(getStringByid(R.string.public_dialog_ok)).setOkTextInfo(textInfo).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inputReflection() {
        this.mConfigPopupView.dismiss();
        TextInfo textInfo = new TextInfo();
        textInfo.setFontColor(Color.parseColor("#007aff"));
        textInfo.setBold(true);
        MessageDialog.build(new OnBindView<MessageDialog>(R.layout.dialog_input_reflection) { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.12
            @Override // com.kongzue.dialogx.interfaces.OnBindView
            public void onBind(MessageDialog messageDialog, View view) {
                HomeActivity.this.reflection_seekbar = (RangeSeekBar) view.findViewById(R.id.reflection_seekbar);
                HomeActivity.this.reflection_seekbar.setProgress(ShareHelper.getRefleation());
                HomeActivity.this.reflection_seekbar.setIndicatorText(Helper.getTemperatureCharacters(ShareHelper.getRefleation() / 10.0f, HomeActivity.this.mTempunit));
                HomeActivity.this.reflection_seekbar.setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.12.1
                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onRangeChanged(RangeSeekBar rangeSeekBar, float f, float f2, boolean z) {
                        HomeActivity.this.reflection_seekbar.setIndicatorText(Helper.getTemperatureCharacters(f / 10.0f, HomeActivity.this.mTempunit));
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
                        int iRound = Math.round(rangeSeekBar.getRangeSeekBarState()[0].value);
                        ShareHelper.setRefleation(iRound);
                        HomeActivity.this.mF2Helper.setReflectionTemperature(iRound, ShareHelper.getRefleationEnable());
                        HomeActivity.this.updateConfigPopupView();
                    }
                });
            }
        }).setTitle(getStringByid(R.string.config_input_reflection_title)).setMessage(getStringByid(R.string.config_input_reflection_message)).setOkButton(getStringByid(R.string.public_dialog_ok)).setOkTextInfo(textInfo).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inputDistance() {
        this.mConfigPopupView.dismiss();
        TextInfo textInfo = new TextInfo();
        textInfo.setFontColor(Color.parseColor("#007aff"));
        textInfo.setBold(true);
        MessageDialog.build(new OnBindView<MessageDialog>(R.layout.dialog_input_distance) { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.13
            @Override // com.kongzue.dialogx.interfaces.OnBindView
            public void onBind(MessageDialog messageDialog, View view) {
                HomeActivity.this.distance_seekbar = (RangeSeekBar) view.findViewById(R.id.distance_seekbar);
                HomeActivity.this.distance_seekbar.setProgress(ShareHelper.getDistance());
                HomeActivity.this.distance_seekbar.setIndicatorText(String.format("D=%.1fm", Float.valueOf(ShareHelper.getDistance() / 100.0f)));
                HomeActivity.this.distance_seekbar.setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.13.1
                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onRangeChanged(RangeSeekBar rangeSeekBar, float f, float f2, boolean z) {
                        HomeActivity.this.distance_seekbar.setIndicatorText(String.format("D=%.1fm", Float.valueOf(f / 100.0f)));
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
                        int iRound = Math.round(rangeSeekBar.getRangeSeekBarState()[0].value);
                        ShareHelper.setDistance(iRound);
                        HomeActivity.this.mF2Helper.setDistance(iRound);
                        HomeActivity.this.updateConfigPopupView();
                    }
                });
            }
        }).setTitle(getStringByid(R.string.config_input_distance_title)).setMessage(getStringByid(R.string.config_input_distance_message)).setOkButton(getStringByid(R.string.public_dialog_ok)).setOkTextInfo(textInfo).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inputBrightness() {
        this.mConfigPopupView.dismiss();
        TextInfo textInfo = new TextInfo();
        textInfo.setFontColor(Color.parseColor("#007aff"));
        textInfo.setBold(true);
        MessageDialog.build(new OnBindView<MessageDialog>(R.layout.dialog_input_brightness) { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.14
            @Override // com.kongzue.dialogx.interfaces.OnBindView
            public void onBind(MessageDialog messageDialog, View view) {
                HomeActivity.this.brightness_seekbar = (RangeSeekBar) view.findViewById(R.id.brightness_seekbar);
                HomeActivity.this.brightness_seekbar.setProgress(ShareHelper.getBrightness());
                HomeActivity.this.brightness_seekbar.setIndicatorTextDecimalFormat("0");
                HomeActivity.this.brightness_seekbar.setIndicatorTextStringFormat("B=%s");
                HomeActivity.this.brightness_seekbar.setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.14.1
                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onRangeChanged(RangeSeekBar rangeSeekBar, float f, float f2, boolean z) {
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) throws IllegalAccessException {
                        int iRound = Math.round(rangeSeekBar.getRangeSeekBarState()[0].value);
                        ShareHelper.setBrightness(iRound);
                        HomeActivity.this.mF2Helper.setImageBrightness(iRound);
                        HomeActivity.this.updateConfigPopupView();
                    }
                });
            }
        }).setTitle(getStringByid(R.string.config_input_brightness_title)).setMessage(getStringByid(R.string.config_input_brightness_message)).setOkButton(getStringByid(R.string.public_dialog_ok)).setOkTextInfo(textInfo).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inputContrast() {
        this.mConfigPopupView.dismiss();
        TextInfo textInfo = new TextInfo();
        textInfo.setFontColor(Color.parseColor("#007aff"));
        textInfo.setBold(true);
        MessageDialog.build(new OnBindView<MessageDialog>(R.layout.dialog_input_contrast) { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.15
            @Override // com.kongzue.dialogx.interfaces.OnBindView
            public void onBind(MessageDialog messageDialog, View view) {
                HomeActivity.this.contrast_seekbar = (RangeSeekBar) view.findViewById(R.id.contrast_seekbar);
                HomeActivity.this.contrast_seekbar.setProgress(ShareHelper.getContrast());
                HomeActivity.this.contrast_seekbar.setIndicatorTextDecimalFormat("0");
                HomeActivity.this.contrast_seekbar.setIndicatorTextStringFormat("C=%s");
                HomeActivity.this.contrast_seekbar.setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.15.1
                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onRangeChanged(RangeSeekBar rangeSeekBar, float f, float f2, boolean z) {
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) throws IllegalAccessException {
                        int iRound = Math.round(rangeSeekBar.getRangeSeekBarState()[0].value);
                        ShareHelper.setContrast(iRound);
                        HomeActivity.this.mF2Helper.setImageContrast(iRound);
                        HomeActivity.this.updateConfigPopupView();
                    }
                });
            }
        }).setTitle(getStringByid(R.string.config_input_contrast_title)).setMessage(getStringByid(R.string.config_input_contrast_message)).setOkButton(getStringByid(R.string.public_dialog_ok)).setOkTextInfo(textInfo).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inputNoise() {
        this.mConfigPopupView.dismiss();
        TextInfo textInfo = new TextInfo();
        textInfo.setFontColor(Color.parseColor("#007aff"));
        textInfo.setBold(true);
        MessageDialog.build(new OnBindView<MessageDialog>(R.layout.dialog_input_noise) { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.16
            @Override // com.kongzue.dialogx.interfaces.OnBindView
            public void onBind(MessageDialog messageDialog, View view) {
                HomeActivity.this.noise_seekbar = (RangeSeekBar) view.findViewById(R.id.noise_seekbar);
                HomeActivity.this.noise_seekbar.setProgress(ShareHelper.getNoise());
                HomeActivity.this.noise_seekbar.setIndicatorTextDecimalFormat("0");
                HomeActivity.this.noise_seekbar.setIndicatorTextStringFormat("NR=%s%%");
                HomeActivity.this.noise_seekbar.setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.16.1
                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onRangeChanged(RangeSeekBar rangeSeekBar, float f, float f2, boolean z) {
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) throws IllegalAccessException {
                        int iRound = Math.round(rangeSeekBar.getRangeSeekBarState()[0].value);
                        ShareHelper.setNoise(iRound);
                        HomeActivity.this.mF2Helper.setNoiseReducation(iRound, ShareHelper.getNoiseEnable());
                        HomeActivity.this.updateConfigPopupView();
                    }
                });
            }
        }).setTitle(getStringByid(R.string.config_input_noise_title)).setMessage(getStringByid(R.string.config_input_noise_message)).setOkButton(getStringByid(R.string.public_dialog_ok)).setOkTextInfo(textInfo).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inputDetail() {
        this.mConfigPopupView.dismiss();
        TextInfo textInfo = new TextInfo();
        textInfo.setFontColor(Color.parseColor("#007aff"));
        textInfo.setBold(true);
        MessageDialog.build(new OnBindView<MessageDialog>(R.layout.dialog_input_detail) { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.17
            @Override // com.kongzue.dialogx.interfaces.OnBindView
            public void onBind(MessageDialog messageDialog, View view) {
                HomeActivity.this.detail_seekbar = (RangeSeekBar) view.findViewById(R.id.detail_seekbar);
                HomeActivity.this.detail_seekbar.setProgress(ShareHelper.getDetail());
                HomeActivity.this.detail_seekbar.setIndicatorTextDecimalFormat("0");
                HomeActivity.this.detail_seekbar.setIndicatorTextStringFormat("DE=%s%%");
                HomeActivity.this.detail_seekbar.setOnRangeChangedListener(new OnRangeChangedListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.17.1
                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onRangeChanged(RangeSeekBar rangeSeekBar, float f, float f2, boolean z) {
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStartTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) {
                    }

                    @Override // com.hti.xtherm.seekbar.OnRangeChangedListener
                    public void onStopTrackingTouch(RangeSeekBar rangeSeekBar, boolean z) throws IllegalAccessException {
                        int iRound = Math.round(rangeSeekBar.getRangeSeekBarState()[0].value);
                        ShareHelper.setDetail(iRound);
                        HomeActivity.this.mF2Helper.setDetailEnhancement(iRound, ShareHelper.getDetailEnable());
                        HomeActivity.this.updateConfigPopupView();
                    }
                });
            }
        }).setTitle(getStringByid(R.string.config_input_detail_title)).setMessage(getStringByid(R.string.config_input_detail_message)).setOkButton(getStringByid(R.string.public_dialog_ok)).setOkTextInfo(textInfo).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetConfigParameter() {
        new Thread(new Runnable() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    m101xa3315a30();
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    /* renamed from: lambda$resetConfigParameter$8$com-hti-xtherm-hti160203u-ui-activity-HomeActivity, reason: not valid java name */
    /* synthetic */ void m101xa3315a30() throws IllegalAccessException {
        WaitDialog.show(getStringByid(R.string.config_reset_wait_message));
        ShareHelper.setBrightness(40);
        this.mF2Helper.setImageBrightness(ShareHelper.getBrightness());
        ShareHelper.setContrast(35);
        this.mF2Helper.setImageContrast(35);
        this.mF2Helper.resetThermometryParameter();
        this.mF2Helper.resetImageParameter();
        ShareHelper.resetConfig();
        this.mTempunit = Config.Tempunit.Celsius;
        this.mTemprange = Config.TempRange.SMALL;
        WaitDialog.dismiss();
    }

    private void showConfigPopupView() {
        makeConfigPopupView();
        CommonPopupWindow commonPopupWindow = this.mConfigPopupView;
        if (commonPopupWindow != null) {
            if (commonPopupWindow.isShowing()) {
                this.mConfigPopupView.dismiss();
            }
            this.mConfigPopupView.showAsDropDown(this.home_right_layout, (0 - (getPopupBaseWidth() * 3)) - 10, (0 - getPopupBaseHeight()) + 10, 3);
        }
    }

    private void makeConfigPopupView() {
        if (this.mConfigPopupView == null) {
            CommonPopupWindow commonPopupWindowCreate = new CommonPopupWindow.Builder(getLanguageContext(this, ShareHelper.getLanguage())).setView(R.layout.popup_config_layout).setBackGroundLevel(0.0f).setWidthAndHeight(getPopupBaseWidth() * 3, getPopupBaseHeight()).setOutsideTouchable(true).create();
            this.mConfigPopupView = commonPopupWindowCreate;
            commonPopupWindowCreate.setFocusable(true);
            View contentView = this.mConfigPopupView.getContentView();
            LinearLayout linearLayout = (LinearLayout) contentView.findViewById(R.id.popup_config_layout);
            this.popup_config_layout = linearLayout;
            linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.18
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (HomeActivity.this.popup_config_layout.getLayoutParams() == null) {
                        return;
                    }
                    HomeActivity.this.popup_config_layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int popupBaseItemHeight = HomeActivity.this.getPopupBaseItemHeight();
                    for (int i = 0; i < HomeActivity.this.popup_config_layout.getChildCount(); i++) {
                        View childAt = HomeActivity.this.popup_config_layout.getChildAt(i);
                        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                        if (layoutParams == null) {
                            layoutParams = new ViewGroup.LayoutParams(-1, 0);
                        }
                        Object tag = childAt.getTag();
                        if (tag != null) {
                            layoutParams.height = (int) (popupBaseItemHeight * Float.valueOf((String) tag).floatValue());
                        } else {
                            layoutParams.height = popupBaseItemHeight;
                        }
                        childAt.setLayoutParams(layoutParams);
                    }
                }
            });
            View viewFindViewById = contentView.findViewById(R.id.config_celsius);
            this.config_celsius = viewFindViewById;
            viewFindViewById.setOnClickListener(this.mConfigPopupViewClick);
            this.config_celsius_select = (ImageView) contentView.findViewById(R.id.config_celsius_select);
            View viewFindViewById2 = contentView.findViewById(R.id.config_fahrenheit);
            this.config_fahrenheit = viewFindViewById2;
            viewFindViewById2.setOnClickListener(this.mConfigPopupViewClick);
            this.config_fahrenheit_select = (ImageView) contentView.findViewById(R.id.config_fahrenheit_select);
            View viewFindViewById3 = contentView.findViewById(R.id.config_large);
            this.config_large = viewFindViewById3;
            viewFindViewById3.setOnClickListener(this.mConfigPopupViewClick);
            this.config_large_select = (ImageView) contentView.findViewById(R.id.config_large_select);
            TextView textView = (TextView) contentView.findViewById(R.id.config_large_rangetext);
            this.config_large_rangetext = textView;
            textView.setText(Config.TempRange.LARGE.getTempRangeString());
            View viewFindViewById4 = contentView.findViewById(R.id.config_small);
            this.config_small = viewFindViewById4;
            viewFindViewById4.setOnClickListener(this.mConfigPopupViewClick);
            this.config_small_select = (ImageView) contentView.findViewById(R.id.config_small_select);
            TextView textView2 = (TextView) contentView.findViewById(R.id.config_small_rangetext);
            this.config_small_rangetext = textView2;
            textView2.setText(Config.TempRange.SMALL.getTempRangeString());
            View viewFindViewById5 = contentView.findViewById(R.id.config_emissivity);
            this.config_emissivity = viewFindViewById5;
            viewFindViewById5.setOnClickListener(this.mConfigPopupViewClick);
            this.config_emissivity_value = (TextView) contentView.findViewById(R.id.config_emissivity_value);
            View viewFindViewById6 = contentView.findViewById(R.id.config_optical);
            this.config_optical = viewFindViewById6;
            viewFindViewById6.setOnClickListener(this.mConfigPopupViewClick);
            this.config_optical_value = (TextView) contentView.findViewById(R.id.config_optical_value);
            View viewFindViewById7 = contentView.findViewById(R.id.config_distance);
            this.config_distance = viewFindViewById7;
            viewFindViewById7.setOnClickListener(this.mConfigPopupViewClick);
            this.config_distance_value = (TextView) contentView.findViewById(R.id.config_distance_value);
            View viewFindViewById8 = contentView.findViewById(R.id.config_brightness);
            this.config_brightness = viewFindViewById8;
            viewFindViewById8.setOnClickListener(this.mConfigPopupViewClick);
            this.config_brightness_value = (TextView) contentView.findViewById(R.id.config_brightness_value);
            View viewFindViewById9 = contentView.findViewById(R.id.config_contrast);
            this.config_contrast = viewFindViewById9;
            viewFindViewById9.setOnClickListener(this.mConfigPopupViewClick);
            this.config_contrast_value = (TextView) contentView.findViewById(R.id.config_contrast_value);
            View viewFindViewById10 = contentView.findViewById(R.id.config_reflection);
            this.config_reflection = viewFindViewById10;
            viewFindViewById10.setOnClickListener(this.mConfigPopupViewClick);
            this.config_reflection_value = (TextView) contentView.findViewById(R.id.config_reflection_value);
            ImageView imageView = (ImageView) contentView.findViewById(R.id.config_reflection_select);
            this.config_reflection_select = imageView;
            imageView.setOnClickListener(this.mConfigPopupViewClick);
            View viewFindViewById11 = contentView.findViewById(R.id.config_noise);
            this.config_noise = viewFindViewById11;
            viewFindViewById11.setOnClickListener(this.mConfigPopupViewClick);
            this.config_noise_value = (TextView) contentView.findViewById(R.id.config_noise_value);
            ImageView imageView2 = (ImageView) contentView.findViewById(R.id.config_noise_select);
            this.config_noise_select = imageView2;
            imageView2.setOnClickListener(this.mConfigPopupViewClick);
            View viewFindViewById12 = contentView.findViewById(R.id.config_detail);
            this.config_detail = viewFindViewById12;
            viewFindViewById12.setOnClickListener(this.mConfigPopupViewClick);
            this.config_detail_value = (TextView) contentView.findViewById(R.id.config_detail_value);
            ImageView imageView3 = (ImageView) contentView.findViewById(R.id.config_detail_select);
            this.config_detail_select = imageView3;
            imageView3.setOnClickListener(this.mConfigPopupViewClick);
            Button button = (Button) contentView.findViewById(R.id.config_reset);
            this.config_reset = button;
            button.setOnClickListener(this.mConfigPopupViewClick);
        }
        updateConfigPopupView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateConfigPopupView() {
        if (this.mConfigPopupView == null) {
            return;
        }
        ImageView imageView = this.config_celsius_select;
        Config.Tempunit tempunit = this.mTempunit;
        Config.Tempunit tempunit2 = Config.Tempunit.Celsius;
        int i = R.mipmap.ic_public_select_focus;
        imageView.setImageResource(tempunit == tempunit2 ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.config_fahrenheit_select.setImageResource(this.mTempunit == Config.Tempunit.Fahrenheit ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.config_large_select.setImageResource(this.mTemprange == Config.TempRange.LARGE ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        ImageView imageView2 = this.config_small_select;
        if (this.mTemprange != Config.TempRange.SMALL) {
            i = R.mipmap.ic_public_select_normal;
        }
        imageView2.setImageResource(i);
        this.config_emissivity_value.setText("E=" + (ShareHelper.getEmissivity() / 100.0f));
        this.config_optical_value.setText("OT=" + Helper.getTemperatureCharacters(ShareHelper.getOptical() / 10.0f, this.mTempunit));
        this.config_reflection_value.setText("RT=" + Helper.getTemperatureCharacters(ShareHelper.getRefleation() / 10.0f, this.mTempunit));
        ImageView imageView3 = this.config_reflection_select;
        boolean refleationEnable = ShareHelper.getRefleationEnable();
        int i2 = R.mipmap.ic_public_switch_focus;
        imageView3.setImageResource(refleationEnable ? R.mipmap.ic_public_switch_focus : R.mipmap.ic_public_switch_normal);
        this.config_distance_value.setText(String.format("D=%.1fm", Float.valueOf(ShareHelper.getDistance() / 100.0f)));
        this.config_brightness_value.setText("B=" + ShareHelper.getBrightness());
        this.config_contrast_value.setText("C=" + ShareHelper.getContrast());
        this.config_noise_value.setText("NR=" + ShareHelper.getNoise() + "%");
        this.config_noise_select.setImageResource(ShareHelper.getNoiseEnable() ? R.mipmap.ic_public_switch_focus : R.mipmap.ic_public_switch_normal);
        this.config_detail_value.setText("DE=" + ShareHelper.getDetail() + "%");
        ImageView imageView4 = this.config_detail_select;
        if (!ShareHelper.getDetailEnable()) {
            i2 = R.mipmap.ic_public_switch_normal;
        }
        imageView4.setImageResource(i2);
    }

    /* renamed from: com.hti.xtherm.hti160203u.ui.activity.HomeActivity$19, reason: invalid class name */
    class AnonymousClass19 implements View.OnClickListener {
        AnonymousClass19() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = view.getId();

            if (id == R.id.settings_alarmmax) {
                HomeActivity.this.showAlarmMaxInput();
                HomeActivity.this.mSettingsPopupView.dismiss();

            } else if (id == R.id.settings_alarmmax_select) {
                HomeActivity homeActivity = HomeActivity.this;
                homeActivity.alarmmax_enable = !homeActivity.alarmmax_enable;
                ShareHelper.setAlarmMaxEnable(HomeActivity.this.alarmmax_enable);
                HomeActivity.this.updateSettingsPopupView();

            } else if (id == R.id.settings_alarmmin) {
                HomeActivity.this.showAlarmMinInput();
                HomeActivity.this.mSettingsPopupView.dismiss();

            } else if (id == R.id.settings_alarmmin_select) {
                HomeActivity homeActivity2 = HomeActivity.this;
                homeActivity2.alarmmin_enable = !homeActivity2.alarmmin_enable;
                ShareHelper.setAlarmMinEnable(HomeActivity.this.alarmmin_enable);
                HomeActivity.this.updateSettingsPopupView();

            } else if (id == R.id.settings_camera) {
                if (!HomeActivity.this.mF2Helper.isPreview()) {
                    HomeActivity.this.mSettingsPopupView.dismiss();
                    HomeActivity.this.tipFailed(R.string.public_device_failed_message_1);
                } else {
                    if (HomeActivity.this.mCameraPreview) {
                        HomeActivity.this.center_thermalview.getCameraRenderView().setVisibility(View.GONE);
                        HomeActivity.this.mCameraHelper.stopPreviewCamera();
                        HomeActivity.this.mCameraPreview = false;
                    } else {
                        HomeActivity.this.center_thermalview.getCameraRenderView().setVisibility(View.VISIBLE);
                        HomeActivity.this.camera_rotation_angle = 0;
                        HomeActivity.this.center_thermalview.setScreenCameraRotation(HomeActivity.this.camera_rotation_angle);
                        HomeActivity.this.mHandler.postDelayed(() -> {
                            try {
                                m105x2f86ff2b();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }, 500L);
                        HomeActivity.this.mCameraPreview = true;
                    }
                    HomeActivity.this.mSettingsPopupView.dismiss();
                }

            } else if (id == R.id.settings_chinese) {
                if (HomeActivity.this.mAppLanguage != Config.Language.CHINESE) {
                    HomeActivity.this.mAppLanguage = Config.Language.CHINESE;
                    ShareHelper.setLanguage(HomeActivity.this.mAppLanguage);
                    HomeActivity.this.updateViewLanguage();
                } else {
                    HomeActivity.this.mSettingsPopupView.dismiss();
                }

            } else if (id == R.id.settings_english) {
                if (HomeActivity.this.mAppLanguage != Config.Language.ENGLISH) {
                    HomeActivity.this.mAppLanguage = Config.Language.ENGLISH;
                    ShareHelper.setLanguage(HomeActivity.this.mAppLanguage);
                    HomeActivity.this.updateViewLanguage();
                } else {
                    HomeActivity.this.mSettingsPopupView.dismiss();
                }

            } else if (id == R.id.settings_russian) {
                if (HomeActivity.this.mAppLanguage != Config.Language.RUSSIAN) {
                    HomeActivity.this.mAppLanguage = Config.Language.RUSSIAN;
                    ShareHelper.setLanguage(HomeActivity.this.mAppLanguage);
                    HomeActivity.this.updateViewLanguage();
                } else {
                    HomeActivity.this.mSettingsPopupView.dismiss();
                }

            } else if (id == R.id.settings_watermark) {
                HomeActivity homeActivity3 = HomeActivity.this;
                homeActivity3.mWatermark = !homeActivity3.mWatermark;
                ShareHelper.setWatermark(HomeActivity.this.mWatermark);
                HomeActivity.this.center_thermalview.showWatermark(HomeActivity.this.mWatermark);
                HomeActivity.this.mSettingsPopupView.dismiss();
            }
        }

        /* renamed from: lambda$onClick$0$com-hti-xtherm-hti160203u-ui-activity-HomeActivity$19, reason: not valid java name */
        /* synthetic */ void m105x2f86ff2b() throws IOException {
            HomeActivity.this.mCameraHelper.previewCamera();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAlarmMaxInput() {
        InputInfo inputInfo = new InputInfo();
        inputInfo.setInputType(2);
        TextInfo textInfo = new TextInfo();
        textInfo.setFontColor(Color.parseColor("#007aff"));
        textInfo.setBold(true);
        InputDialog.build().setTitle((CharSequence) getStringByid(R.string.alarmmax_input_title)).setMessage((CharSequence) getStringByid(R.string.alarmmax_input_message)).setInputHintText(getStringByid(R.string.alarmmax_input_hint)).setAutoShowInputKeyboard(false).setInputInfo(inputInfo).setInputText(ShareHelper.getAlarmMaxValue() != Float.MIN_VALUE ? Helper.getTemperatureValue(ShareHelper.getAlarmMaxValue(), this.mTempunit) + "" : "").setOkButton((CharSequence) getStringByid(R.string.public_dialog_ok)).setOkTextInfo(textInfo).setCancelable(true).setOkButtonClickListener(new OnInputDialogButtonClickListener<InputDialog>() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.20
            @Override // com.kongzue.dialogx.interfaces.OnInputDialogButtonClickListener
            public boolean onClick(InputDialog inputDialog, View view, String str) {
                if (!TextUtils.isEmpty(str)) {
                    if (HomeActivity.this.checkInputAlarmValue(str)) {
                        ShareHelper.setAlarmMaxValue(Helper.getTemperatureValue(Float.valueOf(str).floatValue(), HomeActivity.this.mTempunit, Config.Tempunit.Celsius));
                        inputDialog.dismiss();
                    } else {
                        HomeActivity homeActivity = HomeActivity.this;
                        PopTip.show(homeActivity.getStringByid(R.string.settings_alarmvalue_input_unavailable, Helper.getTemperatureCharacters(-20.0f, homeActivity.mTempunit), Helper.getTemperatureCharacters(400.0f, HomeActivity.this.mTempunit)));
                    }
                    return true;
                }
                inputDialog.dismiss();
                return true;
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAlarmMinInput() {
        InputInfo inputInfo = new InputInfo();
        inputInfo.setInputType(2);
        TextInfo textInfo = new TextInfo();
        textInfo.setFontColor(Color.parseColor("#007aff"));
        textInfo.setBold(true);
        InputDialog.build().setTitle((CharSequence) getStringByid(R.string.alarmmin_input_title)).setMessage((CharSequence) getStringByid(R.string.alarmmin_input_message)).setInputHintText(getStringByid(R.string.alarmmin_input_hint)).setAutoShowInputKeyboard(false).setInputInfo(inputInfo).setInputText(ShareHelper.getAlarmMinValue() != Float.MIN_VALUE ? Helper.getTemperatureValue(ShareHelper.getAlarmMinValue(), this.mTempunit) + "" : "").setOkButton((CharSequence) getStringByid(R.string.public_dialog_ok)).setOkTextInfo(textInfo).setCancelable(true).setOkButtonClickListener(new OnInputDialogButtonClickListener<InputDialog>() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.21
            @Override // com.kongzue.dialogx.interfaces.OnInputDialogButtonClickListener
            public boolean onClick(InputDialog inputDialog, View view, String str) {
                if (!TextUtils.isEmpty(str)) {
                    if (HomeActivity.this.checkInputAlarmValue(str)) {
                        ShareHelper.setAlarmMinValue(Helper.getTemperatureValue(Float.valueOf(str).floatValue(), HomeActivity.this.mTempunit, Config.Tempunit.Celsius));
                        inputDialog.dismiss();
                    } else {
                        HomeActivity homeActivity = HomeActivity.this;
                        PopTip.show(homeActivity.getStringByid(R.string.settings_alarmvalue_input_unavailable, Helper.getTemperatureCharacters(-20.0f, homeActivity.mTempunit), Helper.getTemperatureCharacters(400.0f, HomeActivity.this.mTempunit)));
                    }
                    return true;
                }
                inputDialog.dismiss();
                return true;
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean checkInputAlarmValue(String str) {
        try {
            float temperatureValue = Helper.getTemperatureValue(Float.valueOf(str).floatValue(), this.mTempunit, Config.Tempunit.Celsius);
            return temperatureValue >= -20.0f && temperatureValue <= 400.0f;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showSettingsPopupView() {
        makeSettingsPopupView();
        CommonPopupWindow commonPopupWindow = this.mSettingsPopupView;
        if (commonPopupWindow != null) {
            if (commonPopupWindow.isShowing()) {
                this.mSettingsPopupView.dismiss();
            }
            this.mSettingsPopupView.showAsDropDown(this.home_right_layout, (0 - (getPopupBaseWidth() * 3)) - 10, (0 - getPopupBaseHeight()) + 10, 3);
        }
    }

    private void makeSettingsPopupView() {
        if (this.mSettingsPopupView == null) {
            CommonPopupWindow commonPopupWindowCreate = new CommonPopupWindow.Builder(getLanguageContext(this, ShareHelper.getLanguage())).setView(R.layout.popup_settings_layout).setBackGroundLevel(0.0f).setWidthAndHeight(getPopupBaseWidth() * 3, getPopupBaseHeight()).setOutsideTouchable(true).create();
            this.mSettingsPopupView = commonPopupWindowCreate;
            commonPopupWindowCreate.setFocusable(true);
            View contentView = this.mSettingsPopupView.getContentView();
            this.popup_settings_layout = (LinearLayout) contentView.findViewById(R.id.popup_settings_layout);
            LinearLayout linearLayout = (LinearLayout) contentView.findViewById(R.id.popup_settings_layout);
            this.popup_settings_layout = linearLayout;
            linearLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.HomeActivity.22
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (HomeActivity.this.popup_settings_layout.getLayoutParams() == null) {
                        return;
                    }
                    HomeActivity.this.popup_settings_layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int popupBaseItemHeight = HomeActivity.this.getPopupBaseItemHeight();
                    for (int i = 0; i < HomeActivity.this.popup_settings_layout.getChildCount(); i++) {
                        View childAt = HomeActivity.this.popup_settings_layout.getChildAt(i);
                        ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                        if (layoutParams == null) {
                            layoutParams = new ViewGroup.LayoutParams(-1, 0);
                        }
                        layoutParams.height = popupBaseItemHeight;
                        childAt.setLayoutParams(layoutParams);
                    }
                }
            });
            View viewFindViewById = contentView.findViewById(R.id.settings_camera);
            this.settings_camera = viewFindViewById;
            viewFindViewById.setOnClickListener(this.mSettingsPopupViewClick);
            this.settings_camera_select = (ImageView) contentView.findViewById(R.id.settings_camera_select);
            View viewFindViewById2 = contentView.findViewById(R.id.settings_watermark);
            this.settings_watermark = viewFindViewById2;
            viewFindViewById2.setOnClickListener(this.mSettingsPopupViewClick);
            this.settings_watermark_select = (ImageView) contentView.findViewById(R.id.settings_watermark_select);
            View viewFindViewById3 = contentView.findViewById(R.id.settings_alarmmax);
            this.settings_alarmmax = viewFindViewById3;
            viewFindViewById3.setOnClickListener(this.mSettingsPopupViewClick);
            this.settings_alarmmax_value = (TextView) contentView.findViewById(R.id.settings_alarmmax_value);
            ImageView imageView = (ImageView) contentView.findViewById(R.id.settings_alarmmax_select);
            this.settings_alarmmax_select = imageView;
            imageView.setOnClickListener(this.mSettingsPopupViewClick);
            View viewFindViewById4 = contentView.findViewById(R.id.settings_alarmmin);
            this.settings_alarmmin = viewFindViewById4;
            viewFindViewById4.setOnClickListener(this.mSettingsPopupViewClick);
            this.settings_alarmmin_value = (TextView) contentView.findViewById(R.id.settings_alarmmin_value);
            ImageView imageView2 = (ImageView) contentView.findViewById(R.id.settings_alarmmin_select);
            this.settings_alarmmin_select = imageView2;
            imageView2.setOnClickListener(this.mSettingsPopupViewClick);
            View viewFindViewById5 = contentView.findViewById(R.id.settings_chinese);
            this.settings_chinese = viewFindViewById5;
            viewFindViewById5.setOnClickListener(this.mSettingsPopupViewClick);
            this.settings_chinese_select = (ImageView) contentView.findViewById(R.id.settings_chinese_select);
            View viewFindViewById6 = contentView.findViewById(R.id.settings_english);
            this.settings_english = viewFindViewById6;
            viewFindViewById6.setOnClickListener(this.mSettingsPopupViewClick);
            this.settings_english_select = (ImageView) contentView.findViewById(R.id.settings_english_select);
            View viewFindViewById7 = contentView.findViewById(R.id.settings_russian);
            this.settings_russian = viewFindViewById7;
            viewFindViewById7.setOnClickListener(this.mSettingsPopupViewClick);
            this.settings_russian_select = (ImageView) contentView.findViewById(R.id.settings_russian_select);
        }
        updateSettingsPopupView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSettingsPopupView() {
        if (this.mSettingsPopupView == null) {
            return;
        }
        ImageView imageView = this.settings_camera_select;
        boolean z = this.mCameraPreview;
        int i = R.mipmap.ic_public_switch_focus;
        imageView.setImageResource(z ? R.mipmap.ic_public_switch_focus : R.mipmap.ic_public_switch_normal);
        this.settings_watermark_select.setImageResource(this.mWatermark ? R.mipmap.ic_public_switch_focus : R.mipmap.ic_public_switch_normal);
        this.settings_alarmmax_select.setImageResource(this.alarmmax_enable ? R.mipmap.ic_public_switch_focus : R.mipmap.ic_public_switch_normal);
        ImageView imageView2 = this.settings_alarmmin_select;
        if (!this.alarmmin_enable) {
            i = R.mipmap.ic_public_switch_normal;
        }
        imageView2.setImageResource(i);
        ImageView imageView3 = this.settings_chinese_select;
        Config.Language language = this.mAppLanguage;
        Config.Language language2 = Config.Language.CHINESE;
        int i2 = R.mipmap.ic_public_select_focus;
        imageView3.setImageResource(language == language2 ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        this.settings_english_select.setImageResource(this.mAppLanguage == Config.Language.ENGLISH ? R.mipmap.ic_public_select_focus : R.mipmap.ic_public_select_normal);
        ImageView imageView4 = this.settings_russian_select;
        if (this.mAppLanguage != Config.Language.RUSSIAN) {
            i2 = R.mipmap.ic_public_select_normal;
        }
        imageView4.setImageResource(i2);
        if (ShareHelper.getAlarmMaxValue() != Float.MIN_VALUE) {
            this.settings_alarmmax_value.setText(Helper.getTemperatureCharacters(ShareHelper.getAlarmMaxValue(), ShareHelper.getTempunit()));
        } else {
            this.settings_alarmmax_value.setText("");
        }
        if (ShareHelper.getAlarmMinValue() != Float.MIN_VALUE) {
            this.settings_alarmmin_value.setText(Helper.getTemperatureCharacters(ShareHelper.getAlarmMinValue(), ShareHelper.getTempunit()));
        } else {
            this.settings_alarmmin_value.setText("");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateViewLanguage() {
        CommonPopupWindow commonPopupWindow = this.mTempFeaturePopupView;
        if (commonPopupWindow != null) {
            commonPopupWindow.dismiss();
            this.mTempFeaturePopupView = null;
        }
        CommonPopupWindow commonPopupWindow2 = this.mPalettePopupView;
        if (commonPopupWindow2 != null) {
            commonPopupWindow2.dismiss();
            this.mPalettePopupView = null;
        }
        CommonPopupWindow commonPopupWindow3 = this.mConfigPopupView;
        if (commonPopupWindow3 != null) {
            commonPopupWindow3.dismiss();
            this.mConfigPopupView = null;
        }
        CommonPopupWindow commonPopupWindow4 = this.mSettingsPopupView;
        if (commonPopupWindow4 != null) {
            commonPopupWindow4.dismiss();
            this.mSettingsPopupView = null;
        }
    }
}
