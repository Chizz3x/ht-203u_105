package com.hti.xtherm.hti160203u.widget.thermalview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.internal.view.SupportMenu;
import androidx.core.view.InputDeviceCompat;

import com.hti.xtherm.hti160203u.bean.UVCPoint;
import com.hti.xtherm.hti160203u.bean.UVCRange;
import com.hti.xtherm.hti160203u.helper.BitmapHelper;
import com.hti.xtherm.hti160203u.helper.Config;
import com.hti.xtherm.hti160203u.helper.Helper;
import com.hti.xtherm.hti160203u.helper.ShareHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class ThermalFreatureView extends View {
    private float cursor_weight;
    private Paint mPaint;
    private Config.PatternType mPattern;
    private boolean mPickEnable;
    private List<UVCPoint> mPickFeatures;
    private List<UVCRange> mPickRanges;
    private Bitmap mWatermarkBitmap;
    private boolean mWatermarkEnable;
    private UVCPoint touch_down;
    private UVCPoint touch_up;

    public ThermalFreatureView(Context context) {
        this(context, null);
    }

    public ThermalFreatureView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ThermalFreatureView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.cursor_weight = 0.0625f;
        this.mPattern = Config.PatternType.NONE;
        this.mPickRanges = new ArrayList();
        this.mPickFeatures = new ArrayList();
        this.mPickEnable = false;
        this.mWatermarkEnable = ShareHelper.getWarkmark();
        initPaint();
    }

    public List<UVCRange> getPickRanges() {
        List<UVCRange> list = this.mPickRanges;
        if (list == null || list.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<UVCRange> it = this.mPickRanges.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().copy());
        }
        return arrayList;
    }

    public void setPickFeatures(List<UVCPoint> list) {
        this.mPickFeatures = list;
        redraw();
    }

    public void setPatternType(Config.PatternType patternType) {
        this.mPattern = patternType;
        this.mPickRanges.clear();
        this.mPickFeatures.clear();
        redraw();
    }

    public void setPickEnable(boolean z) {
        this.mPickEnable = z;
    }

    public void setWatermarkEnable(boolean z) {
        this.mWatermarkEnable = z;
        redraw();
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        try {
            drawWatermark(canvas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (this.mPattern != Config.PatternType.NONE) {
            drawPickingRange(canvas);
            drawPickedRanges(canvas);
            drawPickedFeatures(canvas);
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mPattern == Config.PatternType.NONE || !this.mPickEnable) {
            return false;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            touchDownAction(motionEvent.getX(), motionEvent.getY());
        } else if (action == 1) {
            touchUpAction(motionEvent.getX(), motionEvent.getY());
        } else if (action == 2) {
            touchMoveAction(motionEvent.getX(), motionEvent.getY());
        }
        return true;
    }

    private void drawWatermark(Canvas canvas) throws IOException {
        if (this.mWatermarkEnable) {
            if (this.mWatermarkBitmap == null) {
                initWatermarkCursor();
            }
            canvas.drawBitmap(this.mWatermarkBitmap, (getMeasuredWidth() - this.mWatermarkBitmap.getWidth()) - 10, 10, this.mPaint);
        }
    }

    private void initWatermarkCursor() throws IOException {
        this.mWatermarkBitmap = BitmapHelper.getAssetsBitmap("ic_cursor_watermark.png");
        double measuredWidth = getMeasuredWidth() * 0.1d;
        this.mWatermarkBitmap = BitmapHelper.scale(this.mWatermarkBitmap, (int) measuredWidth, (int) (measuredWidth / ((this.mWatermarkBitmap.getWidth() * 1.0d) / this.mWatermarkBitmap.getHeight())));
    }

    private void drawPickingRange(Canvas canvas) {
        if (this.touch_down == null || this.touch_up == null) {
            return;
        }
        if (this.mPattern == Config.PatternType.LINE) {
            drawLine(canvas, this.touch_down, this.touch_up);
        } else if (this.mPattern == Config.PatternType.RECT) {
            drawRect(canvas, this.touch_down, this.touch_up);
        }
    }

    private void drawPickedRanges(Canvas canvas) {
        List<UVCRange> list;
        if (canvas == null || (list = this.mPickRanges) == null || list.size() <= 0) {
            return;
        }
        for (UVCRange uVCRange : this.mPickRanges) {
            if (uVCRange.patternType == Config.PatternType.LINE) {
                drawLine(canvas, uVCRange.touch_down, uVCRange.touch_up);
            } else if (uVCRange.patternType == Config.PatternType.RECT) {
                drawRect(canvas, uVCRange.touch_down, uVCRange.touch_up);
            } else {
                Config.PatternType patternType = uVCRange.patternType;
                Config.PatternType patternType2 = Config.PatternType.POINT;
            }
        }
    }

    @SuppressLint("RestrictedApi")
    private void drawPickedFeatures(Canvas canvas) {
        List<UVCPoint> list;
        String str;
        if (canvas == null || (list = this.mPickFeatures) == null || list.size() <= 0) {
            return;
        }
        for (UVCPoint uVCPoint : this.mPickFeatures) {
            Point pointConvertPointSize = uVCPoint.convertPointSize(getMeasuredWidth(), getMeasuredHeight());
            if (uVCPoint.type != Config.FeatureType.NONE) {
                if (uVCPoint.type == Config.FeatureType.MAX) {
                    this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
                    drawCursor(canvas, pointConvertPointSize);
                } else if (uVCPoint.type == Config.FeatureType.CENTER) {
                    this.mPaint.setColor(-1);
                    drawCursor(canvas, pointConvertPointSize);
                } else if (uVCPoint.type == Config.FeatureType.MIN) {
                    this.mPaint.setColor(-16711936);
                    drawCursor(canvas, pointConvertPointSize);
                } else if (uVCPoint.type == Config.FeatureType.PICK_MAX) {
                    this.mPaint.setColor(SupportMenu.CATEGORY_MASK);
                    drawFeatureCursor(canvas, pointConvertPointSize);
                } else if (uVCPoint.type == Config.FeatureType.PICK_MIN) {
                    this.mPaint.setColor(-16711936);
                    drawFeatureCursor(canvas, pointConvertPointSize);
                } else if (uVCPoint.type == Config.FeatureType.PICK_POI) {
                    this.mPaint.setColor(InputDeviceCompat.SOURCE_ANY);
                    drawFeatureCursor(canvas, pointConvertPointSize);
                }
                float measuredWidth = this.cursor_weight * getMeasuredWidth();
                if (uVCPoint.illegal) {
                    str = "OVER";
                } else if (uVCPoint.tempunit == Config.Tempunit.Celsius) {
                    str = String.format("%.1f℃", Float.valueOf(uVCPoint.temp));
                } else {
                    str = uVCPoint.tempunit == Config.Tempunit.Fahrenheit ? String.format("%.1f℉", Float.valueOf(uVCPoint.temp)) : "";
                }
                Paint.FontMetrics fontMetrics = this.mPaint.getFontMetrics();
                float f = fontMetrics.bottom - fontMetrics.top;
                float fMeasureText = this.mPaint.measureText(str);
                float measuredWidth2 = pointConvertPointSize.x - (fMeasureText / 2.0f);
                if (measuredWidth2 < 0.0f) {
                    measuredWidth2 = 0.0f;
                }
                if (measuredWidth2 + fMeasureText > getMeasuredWidth()) {
                    measuredWidth2 = getMeasuredWidth() - fMeasureText;
                }
                float f2 = measuredWidth / 2.0f;
                float fAbs = pointConvertPointSize.y + f2 + Math.abs(fontMetrics.top);
                if (f + fAbs > getMeasuredHeight()) {
                    fAbs = (pointConvertPointSize.y - f2) - fontMetrics.bottom;
                }
                canvas.drawText(str, measuredWidth2, fAbs, this.mPaint);
            }
        }
    }

    private void drawLine(Canvas canvas, UVCPoint uVCPoint, UVCPoint uVCPoint2) {
        if (canvas == null || uVCPoint == null || uVCPoint2 == null) {
            return;
        }
        this.mPaint.setColor(-16711936);
        Point pointConvertPointSize = uVCPoint.convertPointSize(getMeasuredWidth(), getMeasuredHeight());
        Point pointConvertPointSize2 = uVCPoint2.convertPointSize(getMeasuredWidth(), getMeasuredHeight());
        canvas.drawLine(pointConvertPointSize.x, pointConvertPointSize.y, pointConvertPointSize2.x, pointConvertPointSize2.y, this.mPaint);
    }

    private void drawRect(Canvas canvas, UVCPoint uVCPoint, UVCPoint uVCPoint2) {
        if (canvas == null || uVCPoint == null || uVCPoint2 == null) {
            return;
        }
        canvas.drawRect(Helper.getRect(uVCPoint.convertPointSize(getMeasuredWidth(), getMeasuredHeight()), uVCPoint2.convertPointSize(getMeasuredWidth(), getMeasuredHeight())), this.mPaint);
    }

    private void drawCursor(Canvas canvas, Point point) {
        float measuredWidth = this.cursor_weight * getMeasuredWidth();
        float f = measuredWidth / 3.0f;
        float f2 = f / 2.0f;
        canvas.drawLine(point.x - f2, point.y, point.x + f2, point.y, this.mPaint);
        canvas.drawLine(point.x, point.y - f2, point.x, point.y + f2, this.mPaint);
        float f3 = measuredWidth / 2.0f;
        canvas.drawLine(point.x - f3, point.y - f3, (point.x - f3) + f, point.y - f3, this.mPaint);
        canvas.drawLine(point.x - f3, point.y - f3, point.x - f3, (point.y - f3) + f, this.mPaint);
        canvas.drawLine(point.x - f3, point.y + f3, (point.x - f3) + f, point.y + f3, this.mPaint);
        canvas.drawLine(point.x - f3, point.y + f3, point.x - f3, (point.y + f3) - f, this.mPaint);
        canvas.drawLine(point.x + f3, point.y - f3, (point.x + f3) - f, point.y - f3, this.mPaint);
        canvas.drawLine(point.x + f3, point.y - f3, point.x + f3, (point.y - f3) + f, this.mPaint);
        canvas.drawLine(point.x + f3, point.y + f3, (point.x + f3) - f, point.y + f3, this.mPaint);
        canvas.drawLine(point.x + f3, point.y + f3, point.x + f3, (point.y + f3) - f, this.mPaint);
    }

    private void drawFeatureCursor(Canvas canvas, Point point) {
        float measuredWidth = this.cursor_weight * getMeasuredWidth();
        float f = measuredWidth / 2.0f;
        canvas.drawLine(point.x - f, point.y, point.x + f, point.y, this.mPaint);
        canvas.drawLine(point.x, point.y - f, point.x, point.y + f, this.mPaint);
        float f2 = (measuredWidth / 3.0f) / 2.0f;
        canvas.drawRect(new Rect((int) (point.x - f2), (int) (point.y - f2), (int) (point.x + f2), (int) (point.y + f2)), this.mPaint);
    }

    private void touchDownAction(float f, float f2) {
        this.touch_down = getUVCPoint(f, f2);
    }

    private void touchMoveAction(float f, float f2) {
        if (this.mPattern == Config.PatternType.LINE || this.mPattern == Config.PatternType.RECT) {
            this.touch_up = getUVCPoint(f, f2);
            invalidate();
        }
    }

    private void touchUpAction(float f, float f2) {
        UVCRange uVCRange = new UVCRange(this.mPattern);
        uVCRange.touch_up = getUVCPoint(f, f2);
        uVCRange.touch_down = this.touch_down;
        if (this.mPickRanges.size() >= 5) {
            this.mPickRanges.remove(0);
        }
        this.mPickRanges.add(uVCRange);
        this.touch_down = null;
        this.touch_up = null;
        invalidate();
    }

    private UVCPoint getUVCPoint(float f, float f2) {
        if (f < 0.0f) {
            f = 0.0f;
        }
        if (f > getMeasuredWidth() - 1) {
            f = getMeasuredWidth() - 1;
        }
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > getMeasuredHeight() - 1) {
            f2 = getMeasuredHeight() - 1;
        }
        return new UVCPoint(f / getMeasuredWidth(), f2 / getMeasuredHeight());
    }

    private void redraw() {
        post(new Runnable() { // from class: com.hti.xtherm.hti160203u.widget.thermalview.ThermalFreatureView$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                m114x4f2e2284();
            }
        });
    }

    /* renamed from: lambda$redraw$0$com-hti-xtherm-hti160203u-widget-thermalview-ThermalFreatureView, reason: not valid java name */
    /* synthetic */ void m114x4f2e2284() {
        invalidate();
    }

    private void initPaint() {
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setDither(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setTextSize(Helper.sp2px(14.0f));
        this.mPaint.setStrokeWidth(Helper.dip2px(1.0f));
    }
}
