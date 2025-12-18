package com.hti.xtherm.jzplayer;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/* loaded from: classes.dex */
public class JZMediaSystem extends JZMediaInterface implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnVideoSizeChangedListener {
    public MediaPlayer mediaPlayer;

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public JZMediaSystem(Jzvd jzvd) {
        super(jzvd);
    }

    @Override // com.hti.xtherm.jzplayer.JZMediaInterface
    public void prepare() {
        release();
        this.mMediaHandlerThread = new HandlerThread("JZVD");
        this.mMediaHandlerThread.start();
        this.mMediaHandler = new Handler(this.mMediaHandlerThread.getLooper());
        this.handler = new Handler();
        this.mMediaHandler.post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JZMediaSystem$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() throws IllegalStateException, SecurityException, IllegalArgumentException {
                try {
                    m123lambda$prepare$0$comhtixthermjzplayerJZMediaSystem();
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /* renamed from: lambda$prepare$0$com-hti-xtherm-jzplayer-JZMediaSystem, reason: not valid java name */
    /* synthetic */ void m123lambda$prepare$0$comhtixthermjzplayerJZMediaSystem() throws IllegalStateException, IllegalAccessException, IOException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mediaPlayer = mediaPlayer;
            mediaPlayer.setAudioStreamType(3);
            this.mediaPlayer.setLooping(this.jzvd.jzDataSource.looping);
            this.mediaPlayer.setOnPreparedListener(this);
            this.mediaPlayer.setOnCompletionListener(this);
            this.mediaPlayer.setOnBufferingUpdateListener(this);
            this.mediaPlayer.setScreenOnWhilePlaying(true);
            this.mediaPlayer.setOnSeekCompleteListener(this);
            this.mediaPlayer.setOnErrorListener(this);
            this.mediaPlayer.setOnInfoListener(this);
            this.mediaPlayer.setOnVideoSizeChangedListener(this);
            Object currentUrl = this.jzvd.jzDataSource.getCurrentUrl();
            if (currentUrl instanceof Uri) {
                this.mediaPlayer.setDataSource(this.jzvd.getContext(), (Uri) currentUrl);
            } else {
                MediaPlayer.class.getDeclaredMethod("setDataSource", String.class, Map.class).invoke(this.mediaPlayer, currentUrl.toString(), this.jzvd.jzDataSource.headerMap);
            }
            this.mediaPlayer.prepareAsync();
            this.mediaPlayer.setSurface(new Surface(SAVED_SURFACE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: lambda$start$1$com-hti-xtherm-jzplayer-JZMediaSystem, reason: not valid java name */
    /* synthetic */ void m126lambda$start$1$comhtixthermjzplayerJZMediaSystem() throws IllegalStateException {
        this.mediaPlayer.start();
    }

    @Override // com.hti.xtherm.jzplayer.JZMediaInterface
    public void start() {
        this.mMediaHandler.post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JZMediaSystem$$ExternalSyntheticLambda6
            @Override // java.lang.Runnable
            public final void run() throws IllegalStateException {
                m126lambda$start$1$comhtixthermjzplayerJZMediaSystem();
            }
        });
    }

    /* renamed from: lambda$pause$2$com-hti-xtherm-jzplayer-JZMediaSystem, reason: not valid java name */
    /* synthetic */ void m122lambda$pause$2$comhtixthermjzplayerJZMediaSystem() throws IllegalStateException {
        this.mediaPlayer.pause();
    }

    @Override // com.hti.xtherm.jzplayer.JZMediaInterface
    public void pause() {
        this.mMediaHandler.post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JZMediaSystem$$ExternalSyntheticLambda7
            @Override // java.lang.Runnable
            public final void run() throws IllegalStateException {
                m122lambda$pause$2$comhtixthermjzplayerJZMediaSystem();
            }
        });
    }

    @Override // com.hti.xtherm.jzplayer.JZMediaInterface
    public boolean isPlaying() {
        return this.mediaPlayer.isPlaying();
    }

    @Override // com.hti.xtherm.jzplayer.JZMediaInterface
    public void seekTo(final long j) {
        this.mMediaHandler.post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JZMediaSystem$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() throws IllegalStateException {
                m124lambda$seekTo$3$comhtixthermjzplayerJZMediaSystem(j);
            }
        });
    }

    /* renamed from: lambda$seekTo$3$com-hti-xtherm-jzplayer-JZMediaSystem, reason: not valid java name */
    /* synthetic */ void m124lambda$seekTo$3$comhtixthermjzplayerJZMediaSystem(long j) throws IllegalStateException {
        try {
            this.mediaPlayer.seekTo((int) j);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override // com.hti.xtherm.jzplayer.JZMediaInterface
    public void release() {
        if (this.mMediaHandler == null || this.mMediaHandlerThread == null || this.mediaPlayer == null) {
            return;
        }
        final HandlerThread handlerThread = this.mMediaHandlerThread;
        final MediaPlayer mediaPlayer = this.mediaPlayer;
        JZMediaInterface.SAVED_SURFACE = null;
        this.mMediaHandler.post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JZMediaSystem$$ExternalSyntheticLambda8
            @Override // java.lang.Runnable
            public final void run() {
                JZMediaSystem.lambda$release$4(mediaPlayer, handlerThread);
            }
        });
        this.mediaPlayer = null;
    }

    static /* synthetic */ void lambda$release$4(MediaPlayer mediaPlayer, HandlerThread handlerThread) {
        mediaPlayer.setSurface(null);
        mediaPlayer.release();
        handlerThread.quit();
    }

    @Override // com.hti.xtherm.jzplayer.JZMediaInterface
    public long getCurrentPosition() {
        if (this.mediaPlayer != null) {
            return this.mediaPlayer.getCurrentPosition();
        }
        return 0L;
    }

    @Override // com.hti.xtherm.jzplayer.JZMediaInterface
    public long getDuration() {
        if (this.mediaPlayer != null) {
            return this.mediaPlayer.getDuration();
        }
        return 0L;
    }

    @Override // com.hti.xtherm.jzplayer.JZMediaInterface
    public void setVolume(final float f, final float f2) {
        if (this.mMediaHandler == null) {
            return;
        }
        this.mMediaHandler.post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JZMediaSystem$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                m125lambda$setVolume$5$comhtixthermjzplayerJZMediaSystem(f, f2);
            }
        });
    }

    /* renamed from: lambda$setVolume$5$com-hti-xtherm-jzplayer-JZMediaSystem, reason: not valid java name */
    /* synthetic */ void m125lambda$setVolume$5$comhtixthermjzplayerJZMediaSystem(float f, float f2) {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(f, f2);
        }
    }

    @Override // com.hti.xtherm.jzplayer.JZMediaInterface
    public void setSpeed(float f) {
        PlaybackParams playbackParams = this.mediaPlayer.getPlaybackParams();
        playbackParams.setSpeed(f);
        this.mediaPlayer.setPlaybackParams(playbackParams);
    }

    /* renamed from: lambda$onPrepared$6$com-hti-xtherm-jzplayer-JZMediaSystem, reason: not valid java name */
    /* synthetic */ void m119lambda$onPrepared$6$comhtixthermjzplayerJZMediaSystem() {
        this.jzvd.onPrepared();
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.handler.post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JZMediaSystem$$ExternalSyntheticLambda9
            @Override // java.lang.Runnable
            public final void run() {
                m119lambda$onPrepared$6$comhtixthermjzplayerJZMediaSystem();
            }
        });
    }

    /* renamed from: lambda$onCompletion$7$com-hti-xtherm-jzplayer-JZMediaSystem, reason: not valid java name */
    /* synthetic */ void m116lambda$onCompletion$7$comhtixthermjzplayerJZMediaSystem() {
        this.jzvd.onCompletion();
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        this.handler.post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JZMediaSystem$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                m116lambda$onCompletion$7$comhtixthermjzplayerJZMediaSystem();
            }
        });
    }

    /* renamed from: lambda$onBufferingUpdate$8$com-hti-xtherm-jzplayer-JZMediaSystem, reason: not valid java name */
    /* synthetic */ void m115lambda$onBufferingUpdate$8$comhtixthermjzplayerJZMediaSystem(int i) {
        this.jzvd.setBufferProgress(i);
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, final int i) {
        this.handler.post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JZMediaSystem$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                m115lambda$onBufferingUpdate$8$comhtixthermjzplayerJZMediaSystem(i);
            }
        });
    }

    /* renamed from: lambda$onSeekComplete$9$com-hti-xtherm-jzplayer-JZMediaSystem, reason: not valid java name */
    /* synthetic */ void m120lambda$onSeekComplete$9$comhtixthermjzplayerJZMediaSystem() {
        this.jzvd.onSeekComplete();
    }

    @Override // android.media.MediaPlayer.OnSeekCompleteListener
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        this.handler.post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JZMediaSystem$$ExternalSyntheticLambda10
            @Override // java.lang.Runnable
            public final void run() {
                m120lambda$onSeekComplete$9$comhtixthermjzplayerJZMediaSystem();
            }
        });
    }

    /* renamed from: lambda$onError$10$com-hti-xtherm-jzplayer-JZMediaSystem, reason: not valid java name */
    /* synthetic */ void m117lambda$onError$10$comhtixthermjzplayerJZMediaSystem(int i, int i2) {
        this.jzvd.onError(i, i2);
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, final int i, final int i2) {
        this.handler.post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JZMediaSystem$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                m117lambda$onError$10$comhtixthermjzplayerJZMediaSystem(i, i2);
            }
        });
        return true;
    }

    /* renamed from: lambda$onInfo$11$com-hti-xtherm-jzplayer-JZMediaSystem, reason: not valid java name */
    /* synthetic */ void m118lambda$onInfo$11$comhtixthermjzplayerJZMediaSystem(int i, int i2) throws IllegalAccessException, NoSuchMethodException, InstantiationException, SecurityException, IllegalArgumentException, InvocationTargetException {
        this.jzvd.onInfo(i, i2);
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public boolean onInfo(MediaPlayer mediaPlayer, final int i, final int i2) {
        this.handler.post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JZMediaSystem$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() throws SecurityException, IllegalArgumentException {
                try {
                    m118lambda$onInfo$11$comhtixthermjzplayerJZMediaSystem(i, i2);
                } catch (IllegalAccessException | NoSuchMethodException | InstantiationException |
                         InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return false;
    }

    /* renamed from: lambda$onVideoSizeChanged$12$com-hti-xtherm-jzplayer-JZMediaSystem, reason: not valid java name */
    /* synthetic */ void m121x73351051(int i, int i2) {
        this.jzvd.onVideoSizeChanged(i, i2);
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, final int i, final int i2) {
        this.handler.post(new Runnable() { // from class: com.hti.xtherm.jzplayer.JZMediaSystem$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                m121x73351051(i, i2);
            }
        });
    }

    @Override // com.hti.xtherm.jzplayer.JZMediaInterface
    public void setSurface(Surface surface) {
        this.mediaPlayer.setSurface(surface);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (SAVED_SURFACE == null) {
            SAVED_SURFACE = surfaceTexture;
            prepare();
        } else {
            this.jzvd.textureView.setSurfaceTexture(SAVED_SURFACE);
        }
    }
}
