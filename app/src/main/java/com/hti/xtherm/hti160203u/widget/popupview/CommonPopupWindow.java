package com.hti.xtherm.hti160203u.widget.popupview;

import android.content.Context;
import android.view.View;
import android.widget.PopupWindow;

/* loaded from: classes.dex */
public class CommonPopupWindow extends PopupWindow {
    final PopupController controller;

    public interface ViewInterface {
        void getChildView(View view, int i);
    }

    @Override // android.widget.PopupWindow
    public int getWidth() {
        return this.controller.mPopupView.getMeasuredWidth();
    }

    @Override // android.widget.PopupWindow
    public int getHeight() {
        return this.controller.mPopupView.getMeasuredHeight();
    }

    private CommonPopupWindow(Context context) {
        this.controller = new PopupController(context, this);
    }

    @Override // android.widget.PopupWindow
    public void dismiss() {
        super.dismiss();
        this.controller.setBackGroundLevel(1.0f);
    }

    public static class Builder {
        private ViewInterface listener;
        private final PopupController.PopupParams params;

        public Builder(Context context) {
            this.params = new PopupController.PopupParams(context);
        }

        public Builder setView(int i) {
            this.params.mView = null;
            this.params.layoutResId = i;
            return this;
        }

        public Builder setView(View view) {
            this.params.mView = view;
            this.params.layoutResId = 0;
            return this;
        }

        public Builder setViewOnclickListener(ViewInterface viewInterface) {
            this.listener = viewInterface;
            return this;
        }

        public Builder setWidthAndHeight(int i, int i2) {
            this.params.mWidth = i;
            this.params.mHeight = i2;
            return this;
        }

        public Builder setBackGroundLevel(float f) {
            this.params.isShowBg = true;
            this.params.bg_level = f;
            return this;
        }

        public Builder setOutsideTouchable(boolean z) {
            this.params.isTouchable = z;
            return this;
        }

        public Builder setAnimationStyle(int i) {
            this.params.isShowAnim = true;
            this.params.animationStyle = i;
            return this;
        }

        public CommonPopupWindow create() {
            CommonPopupWindow commonPopupWindow = new CommonPopupWindow(this.params.mContext);
            this.params.apply(commonPopupWindow.controller);
            if (this.listener != null && this.params.layoutResId != 0) {
                this.listener.getChildView(commonPopupWindow.controller.mPopupView, this.params.layoutResId);
            }
            PopupController.measureWidthAndHeight(commonPopupWindow.controller.mPopupView);
            return commonPopupWindow;
        }
    }
}
