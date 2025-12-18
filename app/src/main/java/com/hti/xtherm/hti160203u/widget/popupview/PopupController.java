package com.hti.xtherm.hti160203u.widget.popupview;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
class PopupController {
    private Context context;
    private int layoutResId;
    View mPopupView;
    private View mView;
    private Window mWindow;
    private PopupWindow popupWindow;

    void setBackGroundLevel(float f) {
    }

    PopupController(Context context, PopupWindow popupWindow) {
        this.context = context;
        this.popupWindow = popupWindow;
    }

    public void setView(int i) {
        this.mView = null;
        this.layoutResId = i;
        installContent();
    }

    public void setView(View view) {
        this.mView = view;
        this.layoutResId = 0;
        installContent();
    }

    private void installContent() {
        if (this.layoutResId != 0) {
            this.mPopupView = LayoutInflater.from(this.context).inflate(this.layoutResId, (ViewGroup) null);
        } else {
            View view = this.mView;
            if (view != null) {
                this.mPopupView = view;
            }
        }
        this.popupWindow.setContentView(this.mPopupView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setWidthAndHeight(int i, int i2) {
        if (i == 0 || i2 == 0) {
            this.popupWindow.setWidth(-2);
            this.popupWindow.setHeight(-2);
        } else {
            this.popupWindow.setWidth(i);
            this.popupWindow.setHeight(i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAnimationStyle(int i) {
        this.popupWindow.setAnimationStyle(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOutsideTouchable(boolean z) {
        this.popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        this.popupWindow.setOutsideTouchable(z);
        this.popupWindow.setFocusable(z);
    }

    public static void measureWidthAndHeight(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
    }

    static class PopupParams {
        public int animationStyle;
        public float bg_level;
        public boolean isShowAnim;
        public boolean isShowBg;
        public boolean isTouchable = true;
        public int layoutResId;
        public Context mContext;
        public int mHeight;
        public View mView;
        public int mWidth;

        public PopupParams(Context context) {
            this.mContext = context;
        }

        public void apply(PopupController popupController) {
            View view = this.mView;
            if (view != null) {
                popupController.setView(view);
            } else {
                int i = this.layoutResId;
                if (i != 0) {
                    popupController.setView(i);
                } else {
                    throw new IllegalArgumentException("PopupView's contentView is null");
                }
            }
            popupController.setWidthAndHeight(this.mWidth, this.mHeight);
            popupController.setOutsideTouchable(this.isTouchable);
            if (this.isShowBg) {
                popupController.setBackGroundLevel(this.bg_level);
            }
            if (this.isShowAnim) {
                popupController.setAnimationStyle(this.animationStyle);
            }
        }
    }
}
