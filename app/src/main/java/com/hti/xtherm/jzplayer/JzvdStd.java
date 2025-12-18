package com.hti.xtherm.jzplayer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.view.GravityCompat;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: classes.dex */
public class JzvdStd extends Jzvd {
    public static final String BROADCAST_VIDEOBACK_ACTION = "com.hti.xtherm.action_VIDEOBACK";
    protected static Timer DISMISS_CONTROL_VIEW_TIMER = null;
    public static int LAST_GET_BATTERYLEVEL_PERCENT = 70;
    public static long LAST_GET_BATTERYLEVEL_TIME;
    public ImageView backButton;
    public BroadcastReceiver battertReceiver;
    public ImageView batteryLevel;
    public LinearLayout batteryTimeLayout;
    public ProgressBar bottomProgressBar;
    public TextView clarity;
    public PopupWindow clarityPopWindow;
    protected ArrayDeque<Runnable> delayTask;
    protected GestureDetector gestureDetector;
    public ProgressBar loadingProgressBar;
    protected Dialog mBrightnessDialog;
    protected ProgressBar mDialogBrightnessProgressBar;
    protected TextView mDialogBrightnessTextView;
    protected ImageView mDialogIcon;
    protected ProgressBar mDialogProgressBar;
    protected TextView mDialogSeekTime;
    protected TextView mDialogTotalTime;
    protected ImageView mDialogVolumeImageView;
    protected ProgressBar mDialogVolumeProgressBar;
    protected TextView mDialogVolumeTextView;
    protected DismissControlViewTimerTask mDismissControlViewTimerTask;
    protected boolean mIsWifi;
    protected Dialog mProgressDialog;
    public TextView mRetryBtn;
    public LinearLayout mRetryLayout;
    protected Dialog mVolumeDialog;
    public ImageView posterImageView;
    public TextView replayTextView;
    public ImageView tinyBackImageView;
    public TextView titleTextView;
    public TextView videoCurrentTime;
    public BroadcastReceiver wifiReceiver;

    public JzvdStd(Context context) {
        super(context);
        this.battertReceiver = new BroadcastReceiver() { // from class: com.hti.xtherm.jzplayer.JzvdStd.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                    JzvdStd.LAST_GET_BATTERYLEVEL_PERCENT = (intent.getIntExtra("level", 0) * 100) / intent.getIntExtra("scale", 100);
                    JzvdStd.this.setBatteryLevel();
                    try {
                        JzvdStd.this.jzvdContext.unregisterReceiver(JzvdStd.this.battertReceiver);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.wifiReceiver = new BroadcastReceiver() { // from class: com.hti.xtherm.jzplayer.JzvdStd.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                boolean zIsWifiConnected;
                if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) || JzvdStd.this.mIsWifi == (zIsWifiConnected = JZUtils.isWifiConnected(context2))) {
                    return;
                }
                JzvdStd.this.mIsWifi = zIsWifiConnected;
                if (JzvdStd.this.mIsWifi || Jzvd.WIFI_TIP_DIALOG_SHOWED) {
                    return;
                }
                int i = JzvdStd.this.state;
            }
        };
        this.delayTask = new ArrayDeque<>();
        this.gestureDetector = new GestureDetector(getContext().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.hti.xtherm.jzplayer.JzvdStd.3
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (JzvdStd.this.state == 5 || JzvdStd.this.state == 6) {
                    Log.d("JZVD", "doublClick [" + hashCode() + "] ");
                    JzvdStd.this.startButton.performClick();
                }
                return super.onDoubleTap(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (!JzvdStd.this.mChangePosition && !JzvdStd.this.mChangeVolume) {
                    JzvdStd.this.onClickUiToggle();
                }
                return super.onSingleTapConfirmed(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                super.onLongPress(motionEvent);
            }
        });
    }

    public JzvdStd(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.battertReceiver = new BroadcastReceiver() { // from class: com.hti.xtherm.jzplayer.JzvdStd.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                    JzvdStd.LAST_GET_BATTERYLEVEL_PERCENT = (intent.getIntExtra("level", 0) * 100) / intent.getIntExtra("scale", 100);
                    JzvdStd.this.setBatteryLevel();
                    try {
                        JzvdStd.this.jzvdContext.unregisterReceiver(JzvdStd.this.battertReceiver);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.wifiReceiver = new BroadcastReceiver() { // from class: com.hti.xtherm.jzplayer.JzvdStd.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                boolean zIsWifiConnected;
                if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) || JzvdStd.this.mIsWifi == (zIsWifiConnected = JZUtils.isWifiConnected(context2))) {
                    return;
                }
                JzvdStd.this.mIsWifi = zIsWifiConnected;
                if (JzvdStd.this.mIsWifi || Jzvd.WIFI_TIP_DIALOG_SHOWED) {
                    return;
                }
                int i = JzvdStd.this.state;
            }
        };
        this.delayTask = new ArrayDeque<>();
        this.gestureDetector = new GestureDetector(getContext().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() { // from class: com.hti.xtherm.jzplayer.JzvdStd.3
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (JzvdStd.this.state == 5 || JzvdStd.this.state == 6) {
                    Log.d("JZVD", "doublClick [" + hashCode() + "] ");
                    JzvdStd.this.startButton.performClick();
                }
                return super.onDoubleTap(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (!JzvdStd.this.mChangePosition && !JzvdStd.this.mChangeVolume) {
                    JzvdStd.this.onClickUiToggle();
                }
                return super.onSingleTapConfirmed(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
                super.onLongPress(motionEvent);
            }
        });
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void init(Context context) {
        super.init(context);
        this.batteryTimeLayout = (LinearLayout) findViewById(R.id.battery_time_layout);
        this.bottomProgressBar = (ProgressBar) findViewById(R.id.bottom_progress);
        this.titleTextView = (TextView) findViewById(R.id.title);
        this.backButton = (ImageView) findViewById(R.id.back);
        this.posterImageView = (ImageView) findViewById(R.id.poster);
        this.loadingProgressBar = (ProgressBar) findViewById(R.id.loading);
        this.tinyBackImageView = (ImageView) findViewById(R.id.back_tiny);
        this.batteryLevel = (ImageView) findViewById(R.id.battery_level);
        this.videoCurrentTime = (TextView) findViewById(R.id.video_current_time);
        this.replayTextView = (TextView) findViewById(R.id.replay_text);
        this.clarity = (TextView) findViewById(R.id.clarity);
        this.mRetryBtn = (TextView) findViewById(R.id.retry_btn);
        this.mRetryLayout = (LinearLayout) findViewById(R.id.retry_layout);
        if (this.batteryTimeLayout == null) {
            this.batteryTimeLayout = new LinearLayout(context);
        }
        if (this.bottomProgressBar == null) {
            this.bottomProgressBar = new ProgressBar(context);
        }
        if (this.titleTextView == null) {
            this.titleTextView = new TextView(context);
        }
        if (this.backButton == null) {
            this.backButton = new ImageView(context);
        }
        if (this.posterImageView == null) {
            this.posterImageView = new ImageView(context);
        }
        if (this.loadingProgressBar == null) {
            this.loadingProgressBar = new ProgressBar(context);
        }
        if (this.tinyBackImageView == null) {
            this.tinyBackImageView = new ImageView(context);
        }
        if (this.batteryLevel == null) {
            this.batteryLevel = new ImageView(context);
        }
        if (this.videoCurrentTime == null) {
            this.videoCurrentTime = new TextView(context);
        }
        if (this.replayTextView == null) {
            this.replayTextView = new TextView(context);
        }
        if (this.clarity == null) {
            this.clarity = new TextView(context);
        }
        if (this.mRetryBtn == null) {
            this.mRetryBtn = new TextView(context);
        }
        if (this.mRetryLayout == null) {
            this.mRetryLayout = new LinearLayout(context);
        }
        this.posterImageView.setOnClickListener(this);
        this.backButton.setOnClickListener(this);
        this.tinyBackImageView.setOnClickListener(this);
        this.clarity.setOnClickListener(this);
        this.mRetryBtn.setOnClickListener(this);
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void setUp(JZDataSource jZDataSource, int i, Class cls) {
        if (System.currentTimeMillis() - this.gobakFullscreenTime >= 200 && System.currentTimeMillis() - this.gotoFullscreenTime >= 200) {
            super.setUp(jZDataSource, i, cls);
            this.titleTextView.setText(jZDataSource.title);
            setScreen(i);
        }
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void changeUrl(JZDataSource jZDataSource, long j) throws IllegalAccessException, NoSuchMethodException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.changeUrl(jZDataSource, j);
        this.titleTextView.setText(jZDataSource.title);
    }

    public void changeStartButtonSize(int i) {
        ViewGroup.LayoutParams layoutParams = this.startButton.getLayoutParams();
        layoutParams.height = i;
        layoutParams.width = i;
        ViewGroup.LayoutParams layoutParams2 = this.loadingProgressBar.getLayoutParams();
        layoutParams2.height = i;
        layoutParams2.width = i;
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public int getLayoutId() {
        return R.layout.jz_layout_std;
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void onStateNormal() {
        super.onStateNormal();
        changeUiToNormal();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void onStatePreparing() {
        super.onStatePreparing();
        changeUiToPreparing();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void onStatePreparingPlaying() {
        super.onStatePreparingPlaying();
        changeUIToPreparingPlaying();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void onStatePreparingChangeUrl() throws IllegalAccessException, NoSuchMethodException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.onStatePreparingChangeUrl();
        changeUIToPreparingChangeUrl();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void onStatePlaying() {
        super.onStatePlaying();
        changeUiToPlayingClear();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void onStatePause() {
        super.onStatePause();
        changeUiToPauseShow();
        cancelDismissControlViewTimer();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void onStateError() {
        super.onStateError();
        changeUiToError();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
        changeUiToComplete();
        cancelDismissControlViewTimer();
        this.bottomProgressBar.setProgress(100);
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void startVideo() throws IllegalAccessException, NoSuchMethodException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        super.startVideo();
        registerWifiListener(getApplicationContext());
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd, android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int id = view.getId();
        if (id == R.id.surface_container) {
            if (motionEvent.getAction() == 1) {
                startDismissControlViewTimer();
                if (this.mChangePosition) {
                    long duration = getDuration();
                    long j = this.mSeekTimePosition * 100;
                    if (duration == 0) {
                        duration = 1;
                    }
                    this.bottomProgressBar.setProgress((int) (j / duration));
                }
            }
            this.gestureDetector.onTouchEvent(motionEvent);
        } else if (id == R.id.bottom_seek_progress) {
            int action = motionEvent.getAction();
            if (action == 0) {
                cancelDismissControlViewTimer();
            } else if (action == 1) {
                startDismissControlViewTimer();
            }
        }
        return super.onTouch(view, motionEvent);
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd, android.view.View.OnClickListener
    public void onClick(View view) throws SecurityException, IllegalArgumentException {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.poster) {
            try {
                clickPoster();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
            return;
        }
        if (id == R.id.surface_container) {
            clickSurfaceContainer();
            PopupWindow popupWindow = this.clarityPopWindow;
            if (popupWindow != null) {
                popupWindow.dismiss();
                return;
            }
            return;
        }
        if (id == R.id.back) {
            clickBack();
            return;
        }
        if (id == R.id.back_tiny) {
            clickBackTiny();
        } else if (id == R.id.clarity) {
            clickClarity();
        } else if (id == R.id.retry_btn) {
            try {
                clickRetryBtn();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void clickRetryBtn() throws IllegalAccessException, NoSuchMethodException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (this.jzDataSource.urlsMap.isEmpty() || this.jzDataSource.getCurrentUrl() == null) {
            Toast.makeText(this.jzvdContext, getResources().getString(R.string.no_url), Toast.LENGTH_SHORT).show();
            return;
        }
        if (!this.jzDataSource.getCurrentUrl().toString().startsWith("file") && !this.jzDataSource.getCurrentUrl().toString().startsWith("/") && !JZUtils.isWifiConnected(this.jzvdContext)) {
            boolean z = WIFI_TIP_DIALOG_SHOWED;
        }
        this.seekToInAdvance = this.mCurrentPosition;
        startVideo();
    }

    protected void clickClarity() {
        onCLickUiToggleToClear();
        final LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) this.jzvdContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.jz_layout_clarity, (ViewGroup) null);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.hti.xtherm.jzplayer.JzvdStd$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws SecurityException, IllegalArgumentException {
                try {
                    m128lambda$clickClarity$0$comhtixthermjzplayerJzvdStd(linearLayout, view);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        for (int i = 0; i < this.jzDataSource.urlsMap.size(); i++) {
            String keyFromDataSource = this.jzDataSource.getKeyFromDataSource(i);
            TextView textView = (TextView) View.inflate(this.jzvdContext, R.layout.jz_layout_clarity_item, null);
            textView.setText(keyFromDataSource);
            textView.setTag(Integer.valueOf(i));
            linearLayout.addView(textView, i);
            textView.setOnClickListener(onClickListener);
            if (i == this.jzDataSource.currentUrlIndex) {
                textView.setTextColor(Color.parseColor("#fff85959"));
            }
        }
        PopupWindow popupWindow = new PopupWindow((View) linearLayout, JZUtils.dip2px(this.jzvdContext, 240.0f), -1, true);
        this.clarityPopWindow = popupWindow;
        popupWindow.setContentView(linearLayout);
        this.clarityPopWindow.setAnimationStyle(R.style.pop_animation);
        this.clarityPopWindow.showAtLocation(this.textureViewContainer, GravityCompat.END, 0, 0);
    }

    /* renamed from: lambda$clickClarity$0$com-hti-xtherm-jzplayer-JzvdStd, reason: not valid java name */
    /* synthetic */ void m128lambda$clickClarity$0$comhtixthermjzplayerJzvdStd(LinearLayout linearLayout, View view) throws IllegalAccessException, NoSuchMethodException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        this.jzDataSource.currentUrlIndex = ((Integer) view.getTag()).intValue();
        changeUrl(this.jzDataSource, getCurrentPositionWhenPlaying());
        this.clarity.setText(this.jzDataSource.getCurrentKey().toString());
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            if (i == this.jzDataSource.currentUrlIndex) {
                ((TextView) linearLayout.getChildAt(i)).setTextColor(Color.parseColor("#fff85959"));
            } else {
                ((TextView) linearLayout.getChildAt(i)).setTextColor(Color.parseColor("#ffffff"));
            }
        }
        PopupWindow popupWindow = this.clarityPopWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    protected void clickBackTiny() {
        clearFloatScreen();
    }

    protected void clickBack() {
        if (backPress()) {
            return;
        }
        getContext().sendBroadcast(new Intent(BROADCAST_VIDEOBACK_ACTION));
    }

    protected void clickSurfaceContainer() {
        startDismissControlViewTimer();
    }

    protected void clickPoster() throws IllegalAccessException, NoSuchMethodException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        if (this.jzDataSource == null || this.jzDataSource.urlsMap.isEmpty() || this.jzDataSource.getCurrentUrl() == null) {
            Toast.makeText(this.jzvdContext, getResources().getString(R.string.no_url), Toast.LENGTH_SHORT).show();
            return;
        }
        if (this.state == 0) {
            if (!this.jzDataSource.getCurrentUrl().toString().startsWith("file") && !this.jzDataSource.getCurrentUrl().toString().startsWith("/") && !JZUtils.isWifiConnected(this.jzvdContext)) {
                boolean z = WIFI_TIP_DIALOG_SHOWED;
            }
            startVideo();
            return;
        }
        if (this.state == 7) {
            onClickUiToggle();
        }
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void setScreenNormal() {
        super.setScreenNormal();
        this.fullscreenButton.setImageResource(R.drawable.jz_enlarge);
        this.tinyBackImageView.setVisibility(View.INVISIBLE);
        changeStartButtonSize((int) getResources().getDimension(R.dimen.jz_start_button_w_h_normal));
        this.batteryTimeLayout.setVisibility(View.GONE);
        this.clarity.setVisibility(View.GONE);
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void setScreenFullscreen() {
        super.setScreenFullscreen();
        this.fullscreenButton.setImageResource(R.drawable.jz_shrink);
        this.backButton.setVisibility(View.VISIBLE);
        this.tinyBackImageView.setVisibility(View.INVISIBLE);
        this.batteryTimeLayout.setVisibility(View.VISIBLE);
        if (this.jzDataSource.urlsMap.size() == 1) {
            this.clarity.setVisibility(View.GONE);
        } else {
            this.clarity.setText(this.jzDataSource.getCurrentKey().toString());
            this.clarity.setVisibility(View.VISIBLE);
        }
        changeStartButtonSize((int) getResources().getDimension(R.dimen.jz_start_button_w_h_fullscreen));
        setSystemTimeAndBattery();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void setScreenTiny() {
        super.setScreenTiny();
        this.tinyBackImageView.setVisibility(View.VISIBLE);
        setAllControlsVisiblity(4, 4, 4, 4, 4, 4, 4);
        this.batteryTimeLayout.setVisibility(View.GONE);
        this.clarity.setVisibility(View.GONE);
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void showWifiDialog() {
        super.showWifiDialog();
        AlertDialog.Builder builder = new AlertDialog.Builder(this.jzvdContext);
        builder.setMessage(getResources().getString(R.string.tips_not_wifi));
        builder.setPositiveButton(getResources().getString(R.string.tips_not_wifi_confirm), new DialogInterface.OnClickListener() { // from class: com.hti.xtherm.jzplayer.JzvdStd$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) throws SecurityException, IllegalArgumentException {
                try {
                    m130lambda$showWifiDialog$1$comhtixthermjzplayerJzvdStd(dialogInterface, i);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.tips_not_wifi_cancel), new DialogInterface.OnClickListener() { // from class: com.hti.xtherm.jzplayer.JzvdStd$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                m131lambda$showWifiDialog$2$comhtixthermjzplayerJzvdStd(dialogInterface, i);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.hti.xtherm.jzplayer.JzvdStd.4
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
                Jzvd.releaseAllVideos();
                JzvdStd.this.clearFloatScreen();
            }
        });
        builder.create().show();
    }

    /* renamed from: lambda$showWifiDialog$1$com-hti-xtherm-jzplayer-JzvdStd, reason: not valid java name */
    /* synthetic */ void m130lambda$showWifiDialog$1$comhtixthermjzplayerJzvdStd(DialogInterface dialogInterface, int i) throws IllegalAccessException, NoSuchMethodException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        dialogInterface.dismiss();
        WIFI_TIP_DIALOG_SHOWED = true;
        if (this.state == 6) {
            this.startButton.performClick();
        } else {
            startVideo();
        }
    }

    /* renamed from: lambda$showWifiDialog$2$com-hti-xtherm-jzplayer-JzvdStd, reason: not valid java name */
    /* synthetic */ void m131lambda$showWifiDialog$2$comhtixthermjzplayerJzvdStd(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        releaseAllVideos();
        clearFloatScreen();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd, android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        super.onStartTrackingTouch(seekBar);
        cancelDismissControlViewTimer();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd, android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        super.onStopTrackingTouch(seekBar);
        startDismissControlViewTimer();
    }

    public void onClickUiToggle() {
        if (this.bottomContainer.getVisibility() != View.VISIBLE) {
            setSystemTimeAndBattery();
            this.clarity.setText(this.jzDataSource.getCurrentKey().toString());
        }
        if (this.state == 1) {
            changeUiToPreparing();
            if (this.bottomContainer.getVisibility() == View.VISIBLE) {
                return;
            }
            setSystemTimeAndBattery();
            return;
        }
        if (this.state == 5) {
            if (this.bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPlayingClear();
                return;
            } else {
                changeUiToPlayingShow();
                return;
            }
        }
        if (this.state == 6) {
            if (this.bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPauseClear();
            } else {
                changeUiToPauseShow();
            }
        }
    }

    public void setSystemTimeAndBattery() {
        this.videoCurrentTime.setText(new SimpleDateFormat("HH:mm").format(new Date()));
        if (System.currentTimeMillis() - LAST_GET_BATTERYLEVEL_TIME > 30000) {
            LAST_GET_BATTERYLEVEL_TIME = System.currentTimeMillis();
            if (Build.VERSION.SDK_INT >= 34) {
                this.jzvdContext.registerReceiver(this.battertReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"), Context.RECEIVER_EXPORTED);
                return;
            } else {
                this.jzvdContext.registerReceiver(this.battertReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                return;
            }
        }
        setBatteryLevel();
    }

    public void setBatteryLevel() {
        int i = LAST_GET_BATTERYLEVEL_PERCENT;
        if (i < 15) {
            this.batteryLevel.setBackgroundResource(R.drawable.jz_battery_level_10);
            return;
        }
        if (i >= 15 && i < 40) {
            this.batteryLevel.setBackgroundResource(R.drawable.jz_battery_level_30);
            return;
        }
        if (i >= 40 && i < 60) {
            this.batteryLevel.setBackgroundResource(R.drawable.jz_battery_level_50);
            return;
        }
        if (i >= 60 && i < 80) {
            this.batteryLevel.setBackgroundResource(R.drawable.jz_battery_level_70);
            return;
        }
        if (i >= 80 && i < 95) {
            this.batteryLevel.setBackgroundResource(R.drawable.jz_battery_level_90);
        } else {
            if (i < 95 || i > 100) {
                return;
            }
            this.batteryLevel.setBackgroundResource(R.drawable.jz_battery_level_100);
        }
    }

    public void onCLickUiToggleToClear() {
        if (this.state == 1) {
            if (this.bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPreparing();
            }
        } else if (this.state == 5) {
            if (this.bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPlayingClear();
            }
        } else if (this.state == 6) {
            if (this.bottomContainer.getVisibility() == View.VISIBLE) {
                changeUiToPauseClear();
            }
        } else if (this.state == 7 && this.bottomContainer.getVisibility() == View.VISIBLE) {
            changeUiToComplete();
        }
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void onProgress(int i, long j, long j2) {
        super.onProgress(i, j, j2);
        this.bottomProgressBar.setProgress(i);
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void setBufferProgress(int i) {
        super.setBufferProgress(i);
        this.bottomProgressBar.setSecondaryProgress(i);
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void resetProgressAndTime() {
        super.resetProgressAndTime();
        this.bottomProgressBar.setProgress(0);
        this.bottomProgressBar.setSecondaryProgress(0);
    }

    public void changeUiToNormal() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(0, 4, 0, 4, 0, 4, 4);
            updateStartImage();
        }
    }

    public void changeUiToPreparing() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(4, 4, 4, 0, 0, 4, 4);
            updateStartImage();
        }
    }

    public void changeUIToPreparingPlaying() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(0, 0, 4, 0, 4, 4, 4);
            updateStartImage();
        }
    }

    public void changeUIToPreparingChangeUrl() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(4, 4, 4, 0, 0, 4, 4);
            updateStartImage();
        }
    }

    public void changeUiToPlayingShow() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(0, 0, 0, 4, 4, 4, 4);
            updateStartImage();
        }
    }

    public void changeUiToPlayingClear() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(4, 4, 4, 4, 4, 0, 4);
        }
    }

    public void changeUiToPauseShow() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(0, 0, 0, 4, 4, 4, 4);
            updateStartImage();
        }
    }

    public void changeUiToPauseClear() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(4, 4, 4, 4, 4, 0, 4);
        }
    }

    public void changeUiToComplete() {
        int i = this.screen;
        if (i == 0 || i == 1) {
            setAllControlsVisiblity(0, 4, 0, 4, 0, 4, 4);
            updateStartImage();
        }
    }

    public void changeUiToError() {
        int i = this.screen;
        if (i == 0) {
            setAllControlsVisiblity(4, 4, 0, 4, 4, 4, 0);
            updateStartImage();
        } else {
            if (i != 1) {
                return;
            }
            setAllControlsVisiblity(0, 4, 0, 4, 4, 4, 0);
            updateStartImage();
        }
    }

    public void setAllControlsVisiblity(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.topContainer.setVisibility(i);
        this.bottomContainer.setVisibility(i2);
        this.startButton.setVisibility(i3);
        this.loadingProgressBar.setVisibility(i4);
        this.posterImageView.setVisibility(i5);
        this.bottomProgressBar.setVisibility(i6);
        this.mRetryLayout.setVisibility(i7);
    }

    public void updateStartImage() {
        if (this.state == 5) {
            this.startButton.setVisibility(View.VISIBLE);
            this.startButton.setImageResource(R.drawable.jz_click_pause_selector);
            this.replayTextView.setVisibility(View.GONE);
        } else if (this.state == 8) {
            this.startButton.setVisibility(View.INVISIBLE);
            this.replayTextView.setVisibility(View.GONE);
        } else if (this.state == 7) {
            this.startButton.setVisibility(View.VISIBLE);
            this.startButton.setImageResource(R.drawable.jz_click_replay_selector);
            this.replayTextView.setVisibility(View.VISIBLE);
        } else {
            this.startButton.setImageResource(R.drawable.jz_click_play_selector);
            this.replayTextView.setVisibility(View.GONE);
        }
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void showProgressDialog(float f, String str, long j, String str2, long j2) {
        super.showProgressDialog(f, str, j, str2, j2);
        if (this.mProgressDialog == null) {
            View viewInflate = LayoutInflater.from(this.jzvdContext).inflate(R.layout.jz_dialog_progress, (ViewGroup) null);
            this.mDialogProgressBar = (ProgressBar) viewInflate.findViewById(R.id.duration_progressbar);
            this.mDialogSeekTime = (TextView) viewInflate.findViewById(R.id.tv_current);
            this.mDialogTotalTime = (TextView) viewInflate.findViewById(R.id.tv_duration);
            this.mDialogIcon = (ImageView) viewInflate.findViewById(R.id.duration_image_tip);
            this.mProgressDialog = createDialogWithView(viewInflate);
        }
        if (!this.mProgressDialog.isShowing()) {
            this.mProgressDialog.show();
        }
        this.mDialogSeekTime.setText(str);
        this.mDialogTotalTime.setText(" / " + str2);
        this.mDialogProgressBar.setProgress(j2 <= 0 ? 0 : (int) ((j * 100) / j2));
        if (f > 0.0f) {
            this.mDialogIcon.setBackgroundResource(R.drawable.jz_forward_icon);
        } else {
            this.mDialogIcon.setBackgroundResource(R.drawable.jz_backward_icon);
        }
        onCLickUiToggleToClear();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void dismissProgressDialog() {
        super.dismissProgressDialog();
        Dialog dialog = this.mProgressDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void showVolumeDialog(float f, int i) {
        super.showVolumeDialog(f, i);
        if (this.mVolumeDialog == null) {
            View viewInflate = LayoutInflater.from(this.jzvdContext).inflate(R.layout.jz_dialog_volume, (ViewGroup) null);
            this.mDialogVolumeImageView = (ImageView) viewInflate.findViewById(R.id.volume_image_tip);
            this.mDialogVolumeTextView = (TextView) viewInflate.findViewById(R.id.tv_volume);
            this.mDialogVolumeProgressBar = (ProgressBar) viewInflate.findViewById(R.id.volume_progressbar);
            this.mVolumeDialog = createDialogWithView(viewInflate);
        }
        if (!this.mVolumeDialog.isShowing()) {
            this.mVolumeDialog.show();
        }
        if (i <= 0) {
            this.mDialogVolumeImageView.setBackgroundResource(R.drawable.jz_close_volume);
        } else {
            this.mDialogVolumeImageView.setBackgroundResource(R.drawable.jz_add_volume);
        }
        if (i > 100) {
            i = 100;
        } else if (i < 0) {
            i = 0;
        }
        this.mDialogVolumeTextView.setText(i + "%");
        this.mDialogVolumeProgressBar.setProgress(i);
        onCLickUiToggleToClear();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void dismissVolumeDialog() {
        super.dismissVolumeDialog();
        Dialog dialog = this.mVolumeDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void showBrightnessDialog(int i) {
        super.showBrightnessDialog(i);
        if (this.mBrightnessDialog == null) {
            View viewInflate = LayoutInflater.from(this.jzvdContext).inflate(R.layout.jz_dialog_brightness, (ViewGroup) null);
            this.mDialogBrightnessTextView = (TextView) viewInflate.findViewById(R.id.tv_brightness);
            this.mDialogBrightnessProgressBar = (ProgressBar) viewInflate.findViewById(R.id.brightness_progressbar);
            this.mBrightnessDialog = createDialogWithView(viewInflate);
        }
        if (!this.mBrightnessDialog.isShowing()) {
            this.mBrightnessDialog.show();
        }
        if (i > 100) {
            i = 100;
        } else if (i < 0) {
            i = 0;
        }
        this.mDialogBrightnessTextView.setText(i + "%");
        this.mDialogBrightnessProgressBar.setProgress(i);
        onCLickUiToggleToClear();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void dismissBrightnessDialog() {
        super.dismissBrightnessDialog();
        Dialog dialog = this.mBrightnessDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    public Dialog createDialogWithView(View view) {
        Dialog dialog = new Dialog(this.jzvdContext, R.style.jz_style_dialog_progress);
        dialog.setContentView(view);
        Window window = dialog.getWindow();
        window.addFlags(8);
        window.addFlags(32);
        window.addFlags(16);
        window.setLayout(-2, -2);
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = Gravity.CENTER;
        window.setAttributes(attributes);
        return dialog;
    }

    public void startDismissControlViewTimer() {
        cancelDismissControlViewTimer();
        DISMISS_CONTROL_VIEW_TIMER = new Timer();
        DismissControlViewTimerTask dismissControlViewTimerTask = new DismissControlViewTimerTask();
        this.mDismissControlViewTimerTask = dismissControlViewTimerTask;
        DISMISS_CONTROL_VIEW_TIMER.schedule(dismissControlViewTimerTask, 2500L);
    }

    public void cancelDismissControlViewTimer() {
        Timer timer = DISMISS_CONTROL_VIEW_TIMER;
        if (timer != null) {
            timer.cancel();
        }
        DismissControlViewTimerTask dismissControlViewTimerTask = this.mDismissControlViewTimerTask;
        if (dismissControlViewTimerTask != null) {
            dismissControlViewTimerTask.cancel();
        }
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void onCompletion() {
        super.onCompletion();
        cancelDismissControlViewTimer();
    }

    @Override // com.hti.xtherm.jzplayer.Jzvd
    public void reset() {
        super.reset();
        cancelDismissControlViewTimer();
        unregisterWifiListener(getApplicationContext());
    }

    public void dissmissControlView() {
        if (this.state == 0 || this.state == 8 || this.state == 7) {
            return;
        }
        post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JzvdStd$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                m129lambda$dissmissControlView$3$comhtixthermjzplayerJzvdStd();
            }
        });
    }

    /* renamed from: lambda$dissmissControlView$3$com-hti-xtherm-jzplayer-JzvdStd, reason: not valid java name */
    /* synthetic */ void m129lambda$dissmissControlView$3$comhtixthermjzplayerJzvdStd() {
        this.bottomContainer.setVisibility(View.INVISIBLE);
        this.topContainer.setVisibility(View.INVISIBLE);
        this.startButton.setVisibility(View.INVISIBLE);
        if (this.screen != 2) {
            this.bottomProgressBar.setVisibility(View.VISIBLE);
        }
    }

    public void registerWifiListener(Context context) {
        if (context == null) {
            return;
        }
        this.mIsWifi = JZUtils.isWifiConnected(context);
        IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        if (Build.VERSION.SDK_INT >= 34) {
            context.registerReceiver(this.wifiReceiver, intentFilter, Context.RECEIVER_EXPORTED);
        } else {
            context.registerReceiver(this.wifiReceiver, intentFilter);
        }
    }

    public void unregisterWifiListener(Context context) {
        if (context == null) {
            return;
        }
        try {
            context.unregisterReceiver(this.wifiReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public class DismissControlViewTimerTask extends TimerTask {
        public DismissControlViewTimerTask() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            JzvdStd.this.dissmissControlView();
        }
    }
}
