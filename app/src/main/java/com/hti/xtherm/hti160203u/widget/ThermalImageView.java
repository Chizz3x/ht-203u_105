package com.hti.xtherm.hti160203u.widget;

import android.animation.TimeAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.hti.xtherm.hti160203u.R;
import com.hti.xtherm.hti160203u.bean.UVCPoint;
import com.hti.xtherm.hti160203u.bean.UVCRange;
import com.hti.xtherm.hti160203u.helper.Alog;
import com.hti.xtherm.hti160203u.helper.BitmapHelper;
import com.hti.xtherm.hti160203u.helper.Config;
import com.hti.xtherm.hti160203u.widget.thermalview.ThermalFreatureView;
import com.hti.xtherm.hti160203u.widget.thermalview.ThermalRenderView;

import java.util.List;

/* loaded from: classes.dex */
public class ThermalImageView extends FrameLayout implements TimeAnimator.TimeListener {
    private boolean alarm_max;
    private boolean alarm_min;
    private Bitmap capture_bitmap;
    private TimeAnimator mAlarmAnimator;
    private long mAnimatorCounter;
    private int mAnimatorDuration;
    private View thermal_alarm_image;
    private View thermal_alarm_maxtext;
    private View thermal_alarm_mintext;
    private View thermal_alarm_view;
    private TextureView thermal_camera_view;
    private ThermalFreatureView thermal_feature_view;
    private ThermalRenderView thermal_render_view;

    public ThermalImageView(Context context) {
        this(context, null);
    }

    public ThermalImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ThermalImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.alarm_max = false;
        this.alarm_min = false;
        this.mAnimatorDuration = 500;
        this.mAnimatorCounter = 0L;
        LayoutInflater.from(context).inflate(R.layout.widget_thermalimage_layout, this);
        this.thermal_render_view = (ThermalRenderView) findViewById(R.id.thermal_render_view);
        this.thermal_camera_view = (TextureView) findViewById(R.id.thermal_camera_view);
        this.thermal_feature_view = (ThermalFreatureView) findViewById(R.id.thermal_feature_view);
        this.thermal_alarm_view = findViewById(R.id.thermal_alarm_view);
        this.thermal_alarm_image = findViewById(R.id.thermal_alarm_image);
        this.thermal_alarm_maxtext = findViewById(R.id.thermal_alarm_maxtext);
        this.thermal_alarm_mintext = findViewById(R.id.thermal_alarm_mintext);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int measuredHeight = (int) (getMeasuredHeight() / 8.0f);
        ViewGroup.LayoutParams layoutParams = this.thermal_alarm_view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = measuredHeight;
            this.thermal_alarm_view.setLayoutParams(layoutParams);
        }
        ViewGroup.LayoutParams layoutParams2 = this.thermal_alarm_image.getLayoutParams();
        if (layoutParams2 != null) {
            layoutParams2.width = measuredHeight;
            layoutParams2.height = measuredHeight;
            this.thermal_alarm_image.setLayoutParams(layoutParams2);
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    public void setScreenThermalRotation(final int i) {
        post(new Runnable() { // from class: com.hti.xtherm.hti160203u.widget.ThermalImageView$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                m112x205a8fdc(i);
            }
        });
    }

    /* renamed from: lambda$setScreenThermalRotation$0$com-hti-xtherm-hti160203u-widget-ThermalImageView, reason: not valid java name */
    /* synthetic */ void m112x205a8fdc(int i) {
        this.thermal_render_view.setRotation(i);
    }

    public void setScreenCameraRotation(final int i) {
        post(new Runnable() { // from class: com.hti.xtherm.hti160203u.widget.ThermalImageView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                m111x2b071f6d(i);
            }
        });
    }

    /* renamed from: lambda$setScreenCameraRotation$1$com-hti-xtherm-hti160203u-widget-ThermalImageView, reason: not valid java name */
    /* synthetic */ void m111x2b071f6d(int i) {
        this.thermal_camera_view.setRotation(i);
    }

    public void setAlarmEnable(Config.AlarmType alarmType, boolean z) {
        if (alarmType == Config.AlarmType.MAX) {
            this.alarm_max = z;
        } else if (alarmType == Config.AlarmType.MIN) {
            this.alarm_min = z;
        }
        updateAlarmAnimator();
    }

    private void updateAlarmAnimator() {
        post(new Runnable() { // from class: com.hti.xtherm.hti160203u.widget.ThermalImageView$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                m113x15e564ec();
            }
        });
    }

    /* renamed from: lambda$updateAlarmAnimator$2$com-hti-xtherm-hti160203u-widget-ThermalImageView, reason: not valid java name */
    /* synthetic */ void m113x15e564ec() {
        if (this.mAlarmAnimator == null) {
            TimeAnimator timeAnimator = new TimeAnimator();
            this.mAlarmAnimator = timeAnimator;
            timeAnimator.setTimeListener(this);
        }
        boolean z = this.alarm_max;
        if (z || this.alarm_min) {
            this.thermal_alarm_maxtext.setVisibility(z ? View.VISIBLE : View.GONE);
            this.thermal_alarm_mintext.setVisibility(this.alarm_min ? View.VISIBLE : View.GONE);
            if (this.mAlarmAnimator.isRunning()) {
                return;
            }
            this.mAlarmAnimator.start();
            this.mAnimatorCounter = 0L;
            return;
        }
        TimeAnimator timeAnimator2 = this.mAlarmAnimator;
        if (timeAnimator2 != null && timeAnimator2.isRunning()) {
            this.mAlarmAnimator.cancel();
        }
        this.thermal_alarm_view.setVisibility(View.GONE);
    }

    @Override // android.animation.TimeAnimator.TimeListener
    public void onTimeUpdate(TimeAnimator timeAnimator, long j, long j2) {
        int i = this.mAnimatorDuration;
        if (j / i > this.mAnimatorCounter) {
            this.mAnimatorCounter = j / i;
            if (this.thermal_alarm_view.getVisibility() == View.GONE) {
                this.thermal_alarm_view.setVisibility(View.VISIBLE);
            } else {
                this.thermal_alarm_view.setVisibility(View.GONE);
            }
        }
    }

    public TextureView getThermalRenderView() {
        return this.thermal_render_view;
    }

    public TextureView getCameraRenderView() {
        return this.thermal_camera_view;
    }

    public void setPickEnable(boolean z) {
        this.thermal_feature_view.setPickEnable(z);
    }

    public void setPatternType(Config.PatternType patternType) {
        this.thermal_feature_view.setPatternType(patternType);
    }

    public void showWatermark(boolean z) {
        this.thermal_feature_view.setWatermarkEnable(z);
    }

    public List<UVCRange> getPickRanges() {
        return this.thermal_feature_view.getPickRanges();
    }

    public void setPickFeatures(List<UVCPoint> list) {
        this.thermal_feature_view.setPickFeatures(list);
    }

    public void reLayout(final int i, final int i2) {
        post(new Runnable() { // from class: com.hti.xtherm.hti160203u.widget.ThermalImageView$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                m110xdab14c75(i, i2);
            }
        });
    }

    /* renamed from: lambda$reLayout$3$com-hti-xtherm-hti160203u-widget-ThermalImageView, reason: not valid java name */
    /* synthetic */ void m110xdab14c75(int i, int i2) {
        Object parent = getParent();
        if (parent instanceof ViewGroup) {
            View view = (View) parent;
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new RelativeLayout.LayoutParams(0, 0);
            }
            float f = i;
            float f2 = i2;
            float fMin = Math.min(view.getMeasuredWidth() / f, view.getMeasuredHeight() / f2);
            layoutParams.width = (int) (f * fMin);
            layoutParams.height = (int) (f2 * fMin);
            Alog.e("窗口大小：w = " + layoutParams.width + ",高度：" + layoutParams.height, new Object[0]);
            setLayoutParams(layoutParams);
            ViewGroup.LayoutParams layoutParams2 = this.thermal_camera_view.getLayoutParams();
            if (layoutParams2 == null) {
                layoutParams2 = new LayoutParams(0, 0);
            }
            layoutParams2.width = (int) (layoutParams.width * 0.3d);
            layoutParams2.height = (int) (layoutParams2.width / 1.3333333333333333d);
            this.thermal_camera_view.setLayoutParams(layoutParams2);
        }
    }

    public Bitmap getThermalBitmap() {
        Bitmap bitmap;
        Bitmap bitmap2 = this.capture_bitmap;
        if (bitmap2 == null || bitmap2.getWidth() != getMeasuredWidth() || this.capture_bitmap.getHeight() != getMeasuredHeight() || this.capture_bitmap.isRecycled()) {
            this.capture_bitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(this.capture_bitmap);
        Bitmap bitmap3 = this.thermal_render_view.getBitmap();
        if (bitmap3 != null && !bitmap3.isRecycled()) {
            if (this.thermal_render_view.getRotation() != 0.0f) {
                bitmap3 = BitmapHelper.rotate(bitmap3, (int) this.thermal_render_view.getRotation());
            }
            canvas.drawBitmap(bitmap3, 0.0f, 0.0f, (Paint) null);
        }
        if (this.thermal_camera_view.getVisibility() == View.VISIBLE && (bitmap = this.thermal_camera_view.getBitmap()) != null && !bitmap.isRecycled()) {
            if (this.thermal_camera_view.getRotation() != 0.0f) {
                bitmap = BitmapHelper.rotate(bitmap, (int) this.thermal_camera_view.getRotation());
            }
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        }
        if (this.thermal_alarm_view.getVisibility() == View.VISIBLE) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.thermal_alarm_view.getWidth(), this.thermal_alarm_view.getHeight(), Bitmap.Config.ARGB_8888);
            this.thermal_alarm_view.draw(new Canvas(bitmapCreateBitmap));
            canvas.drawBitmap(bitmapCreateBitmap, new Rect(0, 0, this.thermal_alarm_view.getWidth(), this.thermal_alarm_view.getHeight()), new Rect(this.thermal_alarm_view.getLeft(), this.thermal_alarm_view.getTop(), this.thermal_alarm_view.getRight(), this.thermal_alarm_view.getBottom()), (Paint) null);
        }
        this.thermal_feature_view.draw(canvas);
        return this.capture_bitmap;
    }
}
