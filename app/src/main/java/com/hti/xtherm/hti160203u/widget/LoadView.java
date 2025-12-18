package com.hti.xtherm.hti160203u.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.view.ViewCompat;

/* loaded from: classes.dex */
public class LoadView extends View implements ValueAnimator.AnimatorUpdateListener {
    private long duration;
    private ValueAnimator load;
    private int load_index;
    private int load_max;
    private float load_size_scale;
    private Paint mPaint;

    public LoadView(Context context) {
        this(context, null);
    }

    public LoadView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.duration = 2000L;
        this.load_max = 16;
        this.load_index = 1;
        this.load_size_scale = 0.16666667f;
        setVisibility(View.GONE);
        initAnimator();
        initPaint();
    }

    private void initPaint() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(-1);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setStrokeWidth(20.0f);
    }

    private void initAnimator() {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, this.load_max);
        this.load = valueAnimatorOfInt;
        valueAnimatorOfInt.setDuration(this.duration);
        this.load.setRepeatCount(-1);
        this.load.addUpdateListener(this);
    }

    public void load() {
        setVisibility(View.VISIBLE); // 0
        this.load.start();
    }

    public void cancel() {
        setVisibility(View.GONE); // 8
        this.load.cancel();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (getVisibility() == View.GONE) {
            return;
        }
        canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
        canvasLoad(canvas);
    }

    private void canvasLoad(Canvas canvas) {
        int measuredWidth;
        Point point;
        if (canvas == null || getVisibility() == View.GONE || getMeasuredWidth() <= 0 || (measuredWidth = (int) (getMeasuredWidth() * this.load_size_scale)) <= 0) {
            return;
        }
        Point point2 = new Point();
        point2.x = (int) (getMeasuredWidth() / 2.0f);
        point2.y = (int) (getMeasuredHeight() / 2.0f);
        int i = (int) (measuredWidth / 2.0f);
        float f = 360.0f / this.load_max;
        for (int i2 = 0; i2 < this.load_max; i2++) {
            float f2 = 270.0f + (i2 * f);
            if (i2 <= this.load_index) {
                this.mPaint.setAlpha(255);
            } else {
                this.mPaint.setAlpha(150);
            }
            float f3 = i;
            float f4 = f2 % 360.0f;
            Point point3 = getPoint(point2, (int) (0.6666667f * f3), f4);
            if (i2 == this.load_index) {
                point = getPoint(point2, (int) (f3 + ((i * 1) / 6.0f)), f4);
            } else {
                point = getPoint(point2, i, f4);
            }
            canvas.drawLine(point3.x, point3.y, point.x, point.y, this.mPaint);
        }
    }

    private Point getPoint(Point point, int i, float f) {
        double d = i;
        double d2 = (f * 3.141592653589793d) / 180.0d;
        return new Point((int) (point.x + (Math.cos(d2) * d)), (int) (point.y + (d * Math.sin(d2))));
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.load_index = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        invalidate();
    }
}
