package com.hti.xtherm.hti160203u.ui.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.hti.xtherm.hti160203u.adapter.PicturesAdapter;
import com.hti.xtherm.hti160203u.base.BaseActivity;
import com.hti.xtherm.hti160203u.bean.PictureDetails;
import com.hti.xtherm.hti160203u.bean.PictureUri;
import com.hti.xtherm.hti160203u.helper.Config;
import com.hti.xtherm.hti160203u.helper.FileHelper;
import com.hti.xtherm.hti160203u.helper.GalleryHelper;
import com.hti.xtherm.hti160203u.helper.Helper;
import com.kongzue.dialogx.dialogs.BottomMenu;
import com.kongzue.dialogx.dialogs.MessageDialog;
import com.kongzue.dialogx.dialogs.PopTip;
import com.kongzue.dialogx.dialogs.WaitDialog;
import com.kongzue.dialogx.interfaces.OnDialogButtonClickListener;
import com.kongzue.dialogx.interfaces.OnMenuItemClickListener;

import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.Mat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class PictureActivity extends BaseActivity implements View.OnClickListener {
    private List<PictureUri> mPictures;
    private PicturesAdapter mPicturesAdapter;
    private View picture_detailed;
    private TextView picture_detailed_capturetime;
    private TextView picture_detailed_filename;
    private TextView picture_detailed_filesize;
    private ImageView picture_detailed_image;
    private View picture_detailed_info;
    private TextView picture_detailed_resolution;
    private View picture_detailed_rotate;
    private TextView picture_detailed_storage;
    private GridView picture_gridview;
    private View picture_nothing;
    private View picture_nothing_search;
    private View titlebar_back;
    private RelativeLayout titlebar_layout;
    private View titlebar_menu;
    private TextView titlebar_title;
    private int show_picture_index = -1;
    private Config.Angle mAngle = Config.Angle.ANGLE_0;

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
        return R.layout.activity_pictures;
    }

    static /* synthetic */ int access$508(PictureActivity pictureActivity) {
        int i = pictureActivity.show_picture_index;
        pictureActivity.show_picture_index = i + 1;
        return i;
    }

    static /* synthetic */ int access$510(PictureActivity pictureActivity) {
        int i = pictureActivity.show_picture_index;
        pictureActivity.show_picture_index = i - 1;
        return i;
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected void onInitView() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.titlebar_layout);
        this.titlebar_layout = relativeLayout;
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.PictureActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() throws Resources.NotFoundException {
                if (PictureActivity.this.titlebar_layout.getLayoutParams() == null) {
                    return;
                }
                PictureActivity.this.titlebar_layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams = PictureActivity.this.titlebar_layout.getLayoutParams();
                layoutParams.height = (int) (Helper.getScreenSize().getHeight() / Float.valueOf(PictureActivity.this.getResources().getInteger(R.integer.title_height_proportion)).floatValue());
                PictureActivity.this.titlebar_layout.setLayoutParams(layoutParams);
            }
        });
        View viewFindViewById = findViewById(R.id.titlebar_back);
        this.titlebar_back = viewFindViewById;
        viewFindViewById.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.PictureActivity.2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() throws Resources.NotFoundException {
                if (PictureActivity.this.titlebar_back.getLayoutParams() == null) {
                    return;
                }
                PictureActivity.this.titlebar_back.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams = PictureActivity.this.titlebar_back.getLayoutParams();
                layoutParams.width = (int) (Helper.getScreenSize().getHeight() / Float.valueOf(PictureActivity.this.getResources().getInteger(R.integer.title_height_proportion)).floatValue());
                PictureActivity.this.titlebar_back.setLayoutParams(layoutParams);
            }
        });
        this.titlebar_back.setOnClickListener(this);
        this.titlebar_title = (TextView) findViewById(R.id.titlebar_title);
        View viewFindViewById2 = findViewById(R.id.titlebar_menu);
        this.titlebar_menu = viewFindViewById2;
        viewFindViewById2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.PictureActivity.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() throws Resources.NotFoundException {
                if (PictureActivity.this.titlebar_menu.getLayoutParams() == null) {
                    return;
                }
                PictureActivity.this.titlebar_menu.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams = PictureActivity.this.titlebar_menu.getLayoutParams();
                layoutParams.width = (int) (Helper.getScreenSize().getHeight() / Float.valueOf(PictureActivity.this.getResources().getInteger(R.integer.title_height_proportion)).floatValue());
                PictureActivity.this.titlebar_menu.setLayoutParams(layoutParams);
            }
        });
        this.titlebar_menu.setOnClickListener(this);
        this.picture_gridview = (GridView) findViewById(R.id.picture_gridview);
        this.picture_nothing = findViewById(R.id.picture_nothing);
        View viewFindViewById3 = findViewById(R.id.picture_nothing_search);
        this.picture_nothing_search = viewFindViewById3;
        viewFindViewById3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.PictureActivity.4
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (PictureActivity.this.picture_nothing_search.getLayoutParams() == null) {
                    return;
                }
                PictureActivity.this.picture_nothing_search.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams = PictureActivity.this.picture_nothing_search.getLayoutParams();
                layoutParams.width = (int) (Helper.getScreenSize().getWidth() * 0.25f);
                layoutParams.height = layoutParams.width;
                PictureActivity.this.picture_nothing_search.setLayoutParams(layoutParams);
            }
        });
        this.picture_nothing_search.setOnClickListener(this);
        this.picture_detailed = findViewById(R.id.picture_detailed);
        ImageView imageView = (ImageView) findViewById(R.id.picture_detailed_image);
        this.picture_detailed_image = imageView;
        imageView.setOnTouchListener(new View.OnTouchListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.PictureActivity.5
            private float downX;
            private float upX;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.downX = motionEvent.getX();
                } else if (motionEvent.getAction() == 1) {
                    float x = motionEvent.getX();
                    this.upX = x;
                    if (Math.abs(this.downX - x) > 25.0f) {
                        if (PictureActivity.this.mPictures == null || PictureActivity.this.mPictures.size() <= 1) {
                            return true;
                        }
                        if (this.downX > this.upX) {
                            PictureActivity.access$508(PictureActivity.this);
                        } else {
                            PictureActivity.access$510(PictureActivity.this);
                        }
                        PictureActivity pictureActivity = PictureActivity.this;
                        pictureActivity.show_picture_index = pictureActivity.show_picture_index < 0 ? PictureActivity.this.mPictures.size() - 1 : PictureActivity.this.show_picture_index;
                        PictureActivity pictureActivity2 = PictureActivity.this;
                        pictureActivity2.show_picture_index = pictureActivity2.show_picture_index < PictureActivity.this.mPictures.size() ? PictureActivity.this.show_picture_index : 0;
                        try {
                            PictureActivity.this.showPicture();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        int i = PictureActivity.this.picture_detailed_info.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;
                        PictureActivity.this.picture_detailed_info.setVisibility(i);
                        PictureActivity.this.picture_detailed_rotate.setVisibility(i);
                    }
                }
                return true;
            }
        });
        View viewFindViewById4 = findViewById(R.id.picture_detailed_rotate);
        this.picture_detailed_rotate = viewFindViewById4;
        viewFindViewById4.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.PictureActivity.6
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (PictureActivity.this.picture_detailed_rotate.getLayoutParams() == null) {
                    return;
                }
                PictureActivity.this.picture_detailed_rotate.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                ViewGroup.LayoutParams layoutParams = PictureActivity.this.picture_detailed_rotate.getLayoutParams();
                layoutParams.width = (int) (Helper.getScreenSize().getWidth() * 0.1f);
                layoutParams.height = layoutParams.width;
                PictureActivity.this.picture_detailed_rotate.setLayoutParams(layoutParams);
            }
        });
        this.picture_detailed_rotate.setOnClickListener(this);
        this.picture_detailed_info = findViewById(R.id.picture_detailed_info);
        this.picture_detailed_filename = (TextView) findViewById(R.id.picture_detailed_filename);
        this.picture_detailed_capturetime = (TextView) findViewById(R.id.picture_detailed_capturetime);
        this.picture_detailed_resolution = (TextView) findViewById(R.id.picture_detailed_resolution);
        this.picture_detailed_filesize = (TextView) findViewById(R.id.picture_detailed_filesize);
        this.picture_detailed_storage = (TextView) findViewById(R.id.picture_detailed_storage);
    }

    @Override // com.hti.xtherm.hti160203u.base.BaseActivity
    protected void onInitData() {
        showPictures();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.picture_detailed_rotate) {
            pictureRotate();

        } else if (id == R.id.picture_nothing_search) {
            showPictures();
            PopTip.show(getStringValue(R.string.picture_search_done));

        } else if (id == R.id.titlebar_back) {
            if (!onInterceptBack()) {
                finish();
            }

        } else if (id == R.id.titlebar_menu) {
            showMenu();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Register a callback for back presses
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (onInterceptBack()) {
                    // Do nothing, we intercepted
                } else {
                    // Let default back behavior happen
                    setEnabled(false);  // temporarily disable this callback
                    onBackPressed();    // call the old behavior once
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

    private void showMenu() {
        ArrayList arrayList = new ArrayList();
        List<PictureUri> list = this.mPictures;
        if (list == null || list.size() <= 0) {
            arrayList.add(getStringValue(R.string.gallery_menu_searchagain_title));
            BottomMenu.show((String[]) arrayList.toArray(new String[arrayList.size()])).setMessage((CharSequence) (getStringValue(R.string.picture_title) + "\n")).setOnMenuItemClickListener(new OnMenuItemClickListener<BottomMenu>() { // from class: com.hti.xtherm.hti160203u.ui.activity.PictureActivity.7
                @Override // com.kongzue.dialogx.interfaces.OnMenuItemClickListener
                public boolean onClick(BottomMenu bottomMenu, CharSequence charSequence, int i) {
                    PictureActivity.this.showPictures();
                    PopTip.show(PictureActivity.this.getStringValue(R.string.picture_search_done));
                    return false;
                }
            });
            return;
        }
        arrayList.add(getStringValue(R.string.gallery_menu_selectall_title));
        arrayList.add(getStringValue(R.string.gallery_menu_deselect_title));
        arrayList.add(getStringValue(R.string.gallery_menu_delete_title));
        arrayList.add(getStringValue(R.string.gallery_menu_share_title));
        BottomMenu.show((String[]) arrayList.toArray(new String[arrayList.size()])).setMessage((CharSequence) (getStringValue(R.string.picture_title) + "\n")).setOnMenuItemClickListener(new OnMenuItemClickListener<BottomMenu>() { // from class: com.hti.xtherm.hti160203u.ui.activity.PictureActivity.8
            @Override // com.kongzue.dialogx.interfaces.OnMenuItemClickListener
            public boolean onClick(BottomMenu bottomMenu, CharSequence charSequence, int i) {
                if (i == 0) {
                    if (PictureActivity.this.mPicturesAdapter == null) {
                        return false;
                    }
                    PictureActivity.this.mPicturesAdapter.selectAllPictures();
                    return false;
                }
                if (i == 1) {
                    if (PictureActivity.this.mPicturesAdapter == null) {
                        return false;
                    }
                    PictureActivity.this.mPicturesAdapter.clearSelectedPictures();
                    return false;
                }
                if (i == 2) {
                    PictureActivity.this.onDeleteSelectedAction();
                    return false;
                }
                if (i != 3) {
                    return false;
                }
                PictureActivity.this.onShareSelectedAction();
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onShareSelectedAction() {
        PicturesAdapter picturesAdapter = this.mPicturesAdapter;
        if (picturesAdapter == null || picturesAdapter.getSelectedPictures().size() <= 0) {
            tipFailed(R.string.gallery_menu_failed_noselected);
            return;
        }
        List<PictureUri> selectedPictures = this.mPicturesAdapter.getSelectedPictures();
        if (selectedPictures.size() > 9) {
            tipFailed(R.string.gallery_menu_failed_sharemax);
            return;
        }
        Intent intent = new Intent();
        if (selectedPictures.size() == 1) {
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.STREAM", selectedPictures.get(0).picture_uri);
            intent.setType("image/jpeg");
        } else {
            ArrayList<Uri> arrayList = new ArrayList<>();
            Iterator<PictureUri> it = selectedPictures.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().picture_uri);
            }
            intent.setAction("android.intent.action.SEND_MULTIPLE");
            intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList);
            intent.setType("image/*");
        }
        startActivity(Intent.createChooser(intent, getStringValue(R.string.gallery_share_picture_title)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDeleteSelectedAction() {
        PicturesAdapter picturesAdapter = this.mPicturesAdapter;
        if (picturesAdapter == null || picturesAdapter.getSelectedPictures().size() <= 0) {
            tipFailed(R.string.gallery_menu_failed_noselected);
        } else {
            show_message_dialog(R.string.gallery_delete_title, R.string.gallery_delete_message, R.string.gallery_delete_ok, new OnDialogButtonClickListener<MessageDialog>() { // from class: com.hti.xtherm.hti160203u.ui.activity.PictureActivity.9
                @Override // com.kongzue.dialogx.interfaces.OnDialogButtonClickListener
                public boolean onClick(MessageDialog messageDialog, View view) {
                    if (PictureActivity.this.mPicturesAdapter == null || PictureActivity.this.mPicturesAdapter.getSelectedPictures().size() <= 0) {
                        return false;
                    }
                    WaitDialog.show(PictureActivity.this.getStringValue(R.string.gallery_deleta_deleting));
                    PictureActivity.this.mPicturesAdapter.deleteSelectedPictures();
                    WaitDialog.dismiss();
                    PictureActivity.this.showPictures();
                    return false;
                }
            }, R.string.gallery_delete_cancle, null, true);
        }
    }

    private boolean onInterceptBack() {
        if (this.picture_detailed.getVisibility() != View.VISIBLE) {
            return false;
        }
        this.picture_detailed.setVisibility(View.GONE);
        this.titlebar_title.setText(getStringValue(R.string.picture_title));
        this.titlebar_menu.setVisibility(View.VISIBLE);
        this.picture_gridview.setVisibility(View.VISIBLE);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPictures() {
        List<PictureUri> pictures = GalleryHelper.getPictures();
        this.mPictures = pictures;
        if (pictures == null || pictures.size() <= 0) {
            this.picture_gridview.setVisibility(View.GONE);
            this.picture_nothing.setVisibility(View.VISIBLE);
            this.picture_detailed.setVisibility(View.GONE);
        } else {
            this.picture_gridview.setVisibility(View.VISIBLE);
            this.picture_nothing.setVisibility(View.GONE);
            this.picture_detailed.setVisibility(View.GONE);
        }
        if (this.mPicturesAdapter == null) {
            PicturesAdapter picturesAdapter = new PicturesAdapter(this, this.mPictures);
            this.mPicturesAdapter = picturesAdapter;
            this.picture_gridview.setAdapter((ListAdapter) picturesAdapter);
            this.picture_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.hti.xtherm.hti160203u.ui.activity.PictureActivity.10
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    PictureActivity.this.show_picture_index = i;
                    try {
                        PictureActivity.this.showPicture();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        this.mPicturesAdapter.setPictures(this.mPictures);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPicture() throws IOException {
        int i;
        List<PictureUri> list = this.mPictures;
        if (list == null || list.size() <= 0 || (i = this.show_picture_index) < 0 || i >= this.mPictures.size()) {
            return;
        }
        this.titlebar_menu.setVisibility(View.GONE);
        this.picture_gridview.setVisibility(View.GONE);
        this.picture_detailed.setVisibility(View.VISIBLE);
        this.picture_detailed_rotate.setVisibility(View.VISIBLE);
        this.picture_detailed_info.setVisibility(View.VISIBLE);
        this.titlebar_title.setText((this.show_picture_index + 1) + " / " + this.mPictures.size());
        Glide.with((FragmentActivity) this).load(this.mPictures.get(this.show_picture_index).picture_uri).error(R.mipmap.ic_picture_error).into(this.picture_detailed_image);
        PictureDetails pictureDetails = GalleryHelper.getPictureDetails(this.mPictures.get(this.show_picture_index));
        this.picture_detailed_filename.setText(pictureDetails.picture_name);
        this.picture_detailed_capturetime.setText(pictureDetails.picture_capturetime);
        this.picture_detailed_resolution.setText(pictureDetails.picture_resolution);
        this.picture_detailed_filesize.setText(FileHelper.getFileSize(pictureDetails.picture_filesize));
        this.picture_detailed_storage.setText(pictureDetails.picture_storage);
    }

    private void pictureRotate() {
        Bitmap bitmapCreateBitmap;
        List<PictureUri> list = this.mPictures;
        if (list == null || list.size() <= 0 || this.show_picture_index < 0) {
            return;
        }
        try {
            bitmapCreateBitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(this.mPictures.get(this.show_picture_index).picture_uri));
        } catch (Exception e) {
            e.printStackTrace();
            bitmapCreateBitmap = null;
        }
        if (bitmapCreateBitmap == null) {
            return;
        }
        Mat mat = new Mat();
        Utils.bitmapToMat(bitmapCreateBitmap, mat);
        Mat mat2 = new Mat();
        int i = AnonymousClass11.$SwitchMap$com$hti$xtherm$hti160203u$helper$Config$Angle[this.mAngle.ordinal()];
        if (i == 1) {
            Core.rotate(mat, mat2, 0);
            this.mAngle = Config.Angle.ANGLE_90;
            bitmapCreateBitmap = Bitmap.createBitmap(bitmapCreateBitmap.getHeight(), bitmapCreateBitmap.getWidth(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(mat2, bitmapCreateBitmap);
        } else if (i == 2) {
            Core.rotate(mat, mat2, 1);
            this.mAngle = Config.Angle.ANGLE_180;
            Utils.matToBitmap(mat2, bitmapCreateBitmap);
        } else if (i == 3) {
            Core.rotate(mat, mat2, 2);
            this.mAngle = Config.Angle.ANGLE_270;
            bitmapCreateBitmap = Bitmap.createBitmap(bitmapCreateBitmap.getHeight(), bitmapCreateBitmap.getWidth(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(mat2, bitmapCreateBitmap);
        } else if (i == 4) {
            this.mAngle = Config.Angle.ANGLE_0;
        }
        Glide.with((FragmentActivity) this).load(bitmapCreateBitmap).error(R.mipmap.ic_picture_error).into(this.picture_detailed_image);
        mat.release();
        mat2.release();
    }

    /* renamed from: com.hti.xtherm.hti160203u.ui.activity.PictureActivity$11, reason: invalid class name */
    static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] $SwitchMap$com$hti$xtherm$hti160203u$helper$Config$Angle;

        static {
            int[] iArr = new int[Config.Angle.values().length];
            $SwitchMap$com$hti$xtherm$hti160203u$helper$Config$Angle = iArr;
            try {
                iArr[Config.Angle.ANGLE_0.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$hti$xtherm$hti160203u$helper$Config$Angle[Config.Angle.ANGLE_90.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$hti$xtherm$hti160203u$helper$Config$Angle[Config.Angle.ANGLE_180.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$hti$xtherm$hti160203u$helper$Config$Angle[Config.Angle.ANGLE_270.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }
}
