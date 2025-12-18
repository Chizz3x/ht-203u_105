package com.hti.xtherm.hti160203u.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.FragmentActivity;

import com.bumptech.glide.Glide;
import com.hti.xtherm.hti160203u.R;
import com.hti.xtherm.hti160203u.adapter.VideosAdapter;
import com.hti.xtherm.hti160203u.base.BaseActivity;
import com.hti.xtherm.hti160203u.bean.VideoDetails;
import com.hti.xtherm.hti160203u.bean.VideoUri;
import com.hti.xtherm.hti160203u.helper.FileHelper;
import com.hti.xtherm.hti160203u.helper.GalleryHelper;
import com.hti.xtherm.hti160203u.helper.Helper;
import com.kongzue.dialogx.dialogs.BottomMenu;
import com.kongzue.dialogx.dialogs.MessageDialog;
import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.kongzue.dialogx.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialogx.interfaces.OnMenuItemClickListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class VideoActivity extends BaseActivity implements View.OnClickListener {
    private List<VideoUri> mVideos;
    private VideosAdapter mVideosAdapter;
    private int show_video_index = -1;
    private View titlebar_back;
    private RelativeLayout titlebar_layout;
    private View titlebar_menu;
    private TextView titlebar_title;
    private View video_detailed;
    private TextView video_detailed_duration;
    private TextView video_detailed_filename;
    private TextView video_detailed_filesize;
    private ImageView video_detailed_image;
    private View video_detailed_info;
    private View video_detailed_play;
    private TextView video_detailed_resolution;
    private TextView video_detailed_storage;
    private TextView video_detailed_time;
    private GridView video_gridview;
    private View video_nothing;
    private View video_nothing_search;

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected boolean onFullScreen() {
        return false;
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected boolean onKeepScreen() {
        return false;
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected int onLayout() {
        return R.layout.activity_videos;
    }

    static /* synthetic */ int access$508(VideoActivity videoActivity) {
        int i = videoActivity.show_video_index;
        videoActivity.show_video_index = i + 1;
        return i;
    }

    static /* synthetic */ int access$510(VideoActivity videoActivity) {
        int i = videoActivity.show_video_index;
        videoActivity.show_video_index = i - 1;
        return i;
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected void onInitView() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.titlebar_layout);
        this.titlebar_layout = relativeLayout;
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.VideoActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() throws Resources.NotFoundException {
                if (VideoActivity.this.titlebar_layout.getLayoutParams() == null) {
                    return;
                }
                VideoActivity.this.titlebar_layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams = VideoActivity.this.titlebar_layout.getLayoutParams();
                layoutParams.height = (int) (Helper.getScreenSize().getHeight() / Float.valueOf(VideoActivity.this.getResources().getInteger(R.integer.title_height_proportion)).floatValue());
                VideoActivity.this.titlebar_layout.setLayoutParams(layoutParams);
            }
        });
        View viewFindViewById = findViewById(R.id.titlebar_back);
        this.titlebar_back = viewFindViewById;
        viewFindViewById.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.VideoActivity.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() throws Resources.NotFoundException {
                if (VideoActivity.this.titlebar_back.getLayoutParams() == null) {
                    return;
                }
                VideoActivity.this.titlebar_back.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams = VideoActivity.this.titlebar_back.getLayoutParams();
                layoutParams.width = (int) (Helper.getScreenSize().getHeight() / Float.valueOf(VideoActivity.this.getResources().getInteger(R.integer.title_height_proportion)).floatValue());
                VideoActivity.this.titlebar_back.setLayoutParams(layoutParams);
            }
        });
        this.titlebar_back.setOnClickListener(this);
        this.titlebar_title = (TextView) findViewById(R.id.titlebar_title);
        View viewFindViewById2 = findViewById(R.id.titlebar_menu);
        this.titlebar_menu = viewFindViewById2;
        viewFindViewById2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.VideoActivity.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() throws Resources.NotFoundException {
                if (VideoActivity.this.titlebar_menu.getLayoutParams() == null) {
                    return;
                }
                VideoActivity.this.titlebar_menu.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams = VideoActivity.this.titlebar_menu.getLayoutParams();
                layoutParams.width = (int) (Helper.getScreenSize().getHeight() / Float.valueOf(VideoActivity.this.getResources().getInteger(R.integer.title_height_proportion)).floatValue());
                VideoActivity.this.titlebar_menu.setLayoutParams(layoutParams);
            }
        });
        this.titlebar_menu.setOnClickListener(this);
        this.video_gridview = (GridView) findViewById(R.id.video_gridview);
        this.video_nothing = findViewById(R.id.video_nothing);
        View viewFindViewById3 = findViewById(R.id.video_nothing_search);
        this.video_nothing_search = viewFindViewById3;
        viewFindViewById3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.VideoActivity.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (VideoActivity.this.video_nothing_search.getLayoutParams() == null) {
                    return;
                }
                VideoActivity.this.video_nothing_search.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams = VideoActivity.this.video_nothing_search.getLayoutParams();
                layoutParams.width = (int) (Helper.getScreenSize().getWidth() * 0.25f);
                layoutParams.height = layoutParams.width;
                VideoActivity.this.video_nothing_search.setLayoutParams(layoutParams);
            }
        });
        this.video_nothing_search.setOnClickListener(this);
        this.video_detailed = findViewById(R.id.video_detailed);
        ImageView imageView = (ImageView) findViewById(R.id.video_detailed_image);
        this.video_detailed_image = imageView;
        imageView.setOnTouchListener(new View.OnTouchListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.VideoActivity.5
            private float downX;
            private float upX;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) throws SecurityException, IllegalArgumentException {
                if (motionEvent.getAction() == 0) {
                    this.downX = motionEvent.getX();
                } else if (motionEvent.getAction() == 1) {
                    float x = motionEvent.getX();
                    this.upX = x;
                    if (Math.abs(this.downX - x) > 25.0f) {
                        if (VideoActivity.this.mVideos == null || VideoActivity.this.mVideos.size() <= 1) {
                            return true;
                        }
                        if (this.downX > this.upX) {
                            VideoActivity.access$508(VideoActivity.this);
                        } else {
                            VideoActivity.access$510(VideoActivity.this);
                        }
                        VideoActivity videoActivity = VideoActivity.this;
                        videoActivity.show_video_index = videoActivity.show_video_index < 0 ? VideoActivity.this.mVideos.size() - 1 : VideoActivity.this.show_video_index;
                        VideoActivity videoActivity2 = VideoActivity.this;
                        videoActivity2.show_video_index = videoActivity2.show_video_index < VideoActivity.this.mVideos.size() ? VideoActivity.this.show_video_index : 0;
                        try {
                            VideoActivity.this.showVideo();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        VideoActivity.this.video_detailed_info.setVisibility(VideoActivity.this.video_detailed_info.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
                    }
                }
                return true;
            }
        });
        View viewFindViewById4 = findViewById(R.id.video_detailed_play);
        this.video_detailed_play = viewFindViewById4;
        viewFindViewById4.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.VideoActivity.6
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (VideoActivity.this.video_detailed_play.getLayoutParams() == null) {
                    return;
                }
                VideoActivity.this.video_detailed_play.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams = VideoActivity.this.video_detailed_play.getLayoutParams();
                layoutParams.width = (int) (Helper.getScreenSize().getWidth() * 0.16666667f);
                layoutParams.height = layoutParams.width;
                VideoActivity.this.video_detailed_play.setLayoutParams(layoutParams);
            }
        });
        this.video_detailed_play.setOnClickListener(this);
        this.video_detailed_info = findViewById(R.id.video_detailed_info);
        this.video_detailed_filename = (TextView) findViewById(R.id.video_detailed_filename);
        this.video_detailed_duration = (TextView) findViewById(R.id.video_detailed_duration);
        this.video_detailed_time = (TextView) findViewById(R.id.video_detailed_time);
        this.video_detailed_resolution = (TextView) findViewById(R.id.video_detailed_resolution);
        this.video_detailed_filesize = (TextView) findViewById(R.id.video_detailed_filesize);
        this.video_detailed_storage = (TextView) findViewById(R.id.video_detailed_storage);
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected void onInitData() {
        showVideos();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.titlebar_back) {
            if (!onInterceptBack()) {
                finish();
            }
        } else if (id == R.id.titlebar_menu) {
            showMenu();
        } else if (id == R.id.video_detailed_play) {
            playDetailsVideo();
        } else if (id == R.id.video_nothing_search) {
            showVideos();
            PopTip.show(getStringValue(R.string.video_search_done));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Other initialization...

        // Handle back press using OnBackPressedDispatcher
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (!onInterceptBack()) {
                    // Default back behavior
                    finish(); // or call super if inside a fragment
                }
            }
        });
    }

//    @Override // androidx.activity.ComponentActivity, android.app.Activity
//    public void onBackPressed() {
//        if (onInterceptBack()) {
//            return;
//        }
//        super.onBackPressed();
//    }

    private boolean onInterceptBack() {
        if (this.video_detailed.getVisibility() != View.VISIBLE) {
            return false;
        }
        this.video_detailed.setVisibility(View.GONE);
        this.titlebar_title.setText(getStringValue(R.string.video_title));
        this.titlebar_menu.setVisibility(View.VISIBLE);
        this.video_gridview.setVisibility(View.VISIBLE);
        return true;
    }

    private void playDetailsVideo() {
        int i;
        List<VideoUri> list = this.mVideos;
        if (list == null || list.size() <= 0 || (i = this.show_video_index) < 0 || i >= this.mVideos.size()) {
            return;
        }
        Intent intent = new Intent(this, (Class<?>) PlayActivity.class);
        intent.putExtra(PlayActivity.VIDEO_ID, this.mVideos.get(this.show_video_index).video_id);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showVideos() {
        List<VideoUri> videos = GalleryHelper.getVideos();
        this.mVideos = videos;
        if (videos == null || videos.size() <= 0) {
            this.video_gridview.setVisibility(View.GONE);
            this.video_nothing.setVisibility(View.VISIBLE);
            this.video_detailed.setVisibility(View.GONE);
        } else {
            this.video_gridview.setVisibility(View.VISIBLE);
            this.video_nothing.setVisibility(View.GONE);
            this.video_detailed.setVisibility(View.GONE);
        }
        if (this.mVideosAdapter == null) {
            VideosAdapter videosAdapter = new VideosAdapter(this, this.mVideos);
            this.mVideosAdapter = videosAdapter;
            this.video_gridview.setAdapter((ListAdapter) videosAdapter);
            this.video_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.VideoActivity.7
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) throws SecurityException, IllegalArgumentException {
                    VideoActivity.this.show_video_index = i;
                    try {
                        VideoActivity.this.showVideo();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        this.mVideosAdapter.setVideos(this.mVideos);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showVideo() throws SecurityException, IOException, IllegalArgumentException {
        int i;
        List<VideoUri> list = this.mVideos;
        if (list == null || list.size() <= 0 || (i = this.show_video_index) < 0 || i >= this.mVideos.size()) {
            return;
        }
        this.titlebar_menu.setVisibility(View.GONE);
        this.video_gridview.setVisibility(View.GONE);
        this.video_detailed.setVisibility(View.VISIBLE);
        this.video_detailed_info.setVisibility(View.VISIBLE);
        this.titlebar_title.setText((this.show_video_index + 1) + " / " + this.mVideos.size());
        Glide.with((FragmentActivity) this).load(this.mVideos.get(this.show_video_index).video_uri).error(R.mipmap.ic_video_error).into(this.video_detailed_image);
        VideoDetails videoDetails = GalleryHelper.getVideoDetails(this.mVideos.get(this.show_video_index));
        this.video_detailed_filename.setText(videoDetails.video_name);
        this.video_detailed_duration.setText(videoDetails.video_duration);
        this.video_detailed_time.setText(videoDetails.video_time);
        this.video_detailed_resolution.setText(videoDetails.video_resolution);
        this.video_detailed_filesize.setText(FileHelper.getFileSize(videoDetails.video_size));
        this.video_detailed_storage.setText(videoDetails.video_storage);
    }

    private void showMenu() {
        ArrayList arrayList = new ArrayList();
        List<VideoUri> list = this.mVideos;
        if (list == null || list.size() <= 0) {
            arrayList.add(getStringValue(R.string.gallery_menu_searchagain_title));
            BottomMenu.show((String[]) arrayList.toArray(new String[arrayList.size()])).setMessage((CharSequence) (getStringValue(R.string.video_title) + "\n")).setOnMenuItemClickListener(new OnMenuItemClickListener<BottomMenu>() { // from class: com.hti.xtherm.hti160203u.ui.activity.VideoActivity.8
                @Override // com.kongzue.dialogx.interfaces.OnMenuItemClickListener
                public boolean onClick(BottomMenu bottomMenu, CharSequence charSequence, int i) {
                    VideoActivity.this.showVideos();
                    PopTip.show(VideoActivity.this.getStringValue(R.string.video_search_done));
                    return false;
                }
            });
            return;
        }
        arrayList.add(getStringValue(R.string.gallery_menu_selectall_title));
        arrayList.add(getStringValue(R.string.gallery_menu_deselect_title));
        arrayList.add(getStringValue(R.string.gallery_menu_delete_title));
        arrayList.add(getStringValue(R.string.gallery_menu_share_title));
        BottomMenu.show((String[]) arrayList.toArray(new String[arrayList.size()])).setMessage((CharSequence) (getStringValue(R.string.video_title) + "\n")).setOnMenuItemClickListener(new OnMenuItemClickListener<BottomMenu>() { // from class: com.hti.xtherm.hti160203u.ui.activity.VideoActivity.9
            @Override // com.kongzue.dialogx.interfaces.OnMenuItemClickListener
            public boolean onClick(BottomMenu bottomMenu, CharSequence charSequence, int i) {
                if (i == 0) {
                    if (VideoActivity.this.mVideosAdapter == null) {
                        return false;
                    }
                    VideoActivity.this.mVideosAdapter.selectAllVideos();
                    return false;
                }
                if (i == 1) {
                    if (VideoActivity.this.mVideosAdapter == null) {
                        return false;
                    }
                    VideoActivity.this.mVideosAdapter.clearSelectedVideos();
                    return false;
                }
                if (i == 2) {
                    VideoActivity.this.onDeleteSelectedAction();
                    return false;
                }
                if (i != 3) {
                    return false;
                }
                VideoActivity.this.onShareSelectedAction();
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onShareSelectedAction() {
        VideosAdapter videosAdapter = this.mVideosAdapter;
        if (videosAdapter == null || videosAdapter.getSelectedVideos().size() <= 0) {
            tipFailed(R.string.gallery_menu_failed_noselected);
            return;
        }
        List<VideoUri> selectedVideos = this.mVideosAdapter.getSelectedVideos();
        if (selectedVideos.size() > 9) {
            tipFailed(R.string.gallery_menu_failed_sharemax);
            return;
        }
        Intent intent = new Intent();
        if (selectedVideos.size() == 1) {
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.STREAM", selectedVideos.get(0).video_uri);
            intent.setType("video/mp4");
        } else {
            ArrayList<Uri> arrayList = new ArrayList<>();
            Iterator<VideoUri> it = selectedVideos.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().video_uri);
            }
            intent.setAction("android.intent.action.SEND_MULTIPLE");
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
            intent.setType("video/*");
        }
        startActivity(Intent.createChooser(intent, getStringValue(R.string.gallery_share_video_title)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeleteSelectedAction() {
        VideosAdapter videosAdapter = this.mVideosAdapter;
        if (videosAdapter == null || videosAdapter.getSelectedVideos().size() <= 0) {
            tipFailed(R.string.gallery_menu_failed_noselected);
        } else {
            show_message_dialog(R.string.gallery_delete_title, R.string.gallery_delete_message, R.string.gallery_delete_ok, new OnDialogButtonClickListener<MessageDialog>() { // from class: com.hti.xtherm.hti160203u.ui.activity.VideoActivity.10
                @Override // com.kongzue.dialogx.interfaces.OnDialogButtonClickListener
                public boolean onClick(MessageDialog messageDialog, View view) {
                    if (VideoActivity.this.mVideosAdapter == null || VideoActivity.this.mVideosAdapter.getSelectedVideos().size() <= 0) {
                        return false;
                    }
                    WaitDialog.show(VideoActivity.this.getStringValue(R.string.gallery_deleta_deleting));
                    VideoActivity.this.mVideosAdapter.deleteSelectedVideos();
                    WaitDialog.dismiss();
                    VideoActivity.this.showVideos();
                    return false;
                }
            }, R.string.gallery_delete_cancle, null, true);
        }
    }
}
