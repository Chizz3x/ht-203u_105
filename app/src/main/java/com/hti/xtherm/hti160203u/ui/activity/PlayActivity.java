package com.hti.xtherm.hti160203u.ui.activity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.OnBackPressedCallback;

import com.hti.xtherm.hti160203u.R;
import com.hti.xtherm.hti160203u.base.BaseActivity;
import com.hti.xtherm.hti160203u.helper.GalleryHelper;
import com.hti.xtherm.jzplayer.JZDataSource;
import com.hti.xtherm.jzplayer.Jzvd;
import com.hti.xtherm.jzplayer.JzvdStd;

import java.lang.reflect.InvocationTargetException;

/* loaded from: classes.dex */
public class PlayActivity extends BaseActivity {
    public static String VIDEO_ID = "com.video.play.VIDEO_ID";
    private long mVideoID;
    private JzvdStd play_view;
    private BroadcastReceiver videoback_receiver = new BroadcastReceiver() { // from class: com.hti.xtherm.hti160203u.ui.activity.PlayActivity.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(JzvdStd.BROADCAST_VIDEOBACK_ACTION)) {
                PlayActivity.this.finish();
            }
        }
    };

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
        return R.layout.activity_play;
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected void onInitView() {
        this.play_view = (JzvdStd) findViewById(R.id.play_view);
    }

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected void onInitData() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(JzvdStd.BROADCAST_VIDEOBACK_ACTION);
        if (Build.VERSION.SDK_INT >= 34) {
            registerReceiver(this.videoback_receiver, intentFilter, Context.RECEIVER_EXPORTED);
        } else {
            registerReceiver(this.videoback_receiver, intentFilter);
        }
        this.mVideoID = getIntent().getLongExtra(VIDEO_ID, -1L);
        this.mHandler.postDelayed(new Runnable() { // from class: com.hti.xtherm.hti160203u.ui.activity.PlayActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws SecurityException, IllegalArgumentException {
                try {
                    m109x4783144c();
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
        }, 500L);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(0);
            if (decorView != null) {
                decorView.setSystemUiVisibility(5126);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Register a back-press callback
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (!Jzvd.backPress()) {
                    // Default behavior if Jzvd did not consume the back press
                    setEnabled(false); // temporarily disable callback
                    onBackPressed();
                    setEnabled(true);  // re-enable callback
                }
            }
        });
    }

//    @Override // androidx.activity.ComponentActivity, android.app.Activity
//    public void onBackPressed() {
//        if (Jzvd.backPress()) {
//            return;
//        }
//        super.onBackPressed();
//    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.videoback_receiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: playVideo, reason: merged with bridge method [inline-methods] */
    public void m109x4783144c() throws IllegalAccessException, NoSuchMethodException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        JZDataSource jZDataSource = new JZDataSource(ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, this.mVideoID));
        jZDataSource.title = GalleryHelper.getVideoName(this.mVideoID);
        this.play_view.setUp(jZDataSource, 0);
        this.play_view.startVideo();
    }
}
