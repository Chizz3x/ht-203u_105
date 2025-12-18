package com.hti.xtherm.hti160203u.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.hti.xtherm.hti160203u.R;
import com.hti.xtherm.hti160203u.base.BaseActivity;
import com.hti.xtherm.hti160203u.helper.Helper;

/* loaded from: classes.dex */
public class MediaActivity extends BaseActivity implements View.OnClickListener {
    private View gallery_picture_layout;
    private View gallery_video_layout;
    private View titlebar_back;
    private RelativeLayout titlebar_layout;

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected boolean onFullScreen() {
        return false;
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected void onInitData() {
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected boolean onKeepScreen() {
        return false;
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected int onLayout() {
        return R.layout.activity_media;
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected void onInitView() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.titlebar_layout);
        this.titlebar_layout = relativeLayout;
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.MediaActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() throws Resources.NotFoundException {
                if (MediaActivity.this.titlebar_layout.getLayoutParams() == null) {
                    return;
                }
                MediaActivity.this.titlebar_layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams = MediaActivity.this.titlebar_layout.getLayoutParams();
                layoutParams.height = (int) (Helper.getScreenSize().getHeight() / Float.valueOf(MediaActivity.this.getResources().getInteger(R.integer.title_height_proportion)).floatValue());
                MediaActivity.this.titlebar_layout.setLayoutParams(layoutParams);
            }
        });
        View viewFindViewById = findViewById(R.id.titlebar_back);
        this.titlebar_back = viewFindViewById;
        viewFindViewById.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.MediaActivity.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() throws Resources.NotFoundException {
                if (MediaActivity.this.titlebar_back.getLayoutParams() == null) {
                    return;
                }
                MediaActivity.this.titlebar_back.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams = MediaActivity.this.titlebar_back.getLayoutParams();
                layoutParams.width = (int) (Helper.getScreenSize().getHeight() / Float.valueOf(MediaActivity.this.getResources().getInteger(R.integer.title_height_proportion)).floatValue());
                MediaActivity.this.titlebar_back.setLayoutParams(layoutParams);
            }
        });
        this.titlebar_back.setOnClickListener(this);
        View viewFindViewById2 = findViewById(R.id.gallery_picture_layout);
        this.gallery_picture_layout = viewFindViewById2;
        viewFindViewById2.setOnClickListener(this);
        View viewFindViewById3 = findViewById(R.id.gallery_video_layout);
        this.gallery_video_layout = viewFindViewById3;
        viewFindViewById3.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.gallery_picture_layout) {
            startActivity(new Intent(this, PictureActivity.class));

        } else if (id == R.id.gallery_video_layout) {
            startActivity(new Intent(this, VideoActivity.class));

        } else if (id == R.id.titlebar_back) {
            finish();
        }
    }
}
