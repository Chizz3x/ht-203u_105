package com.hti.xtherm.hti160203u.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.hti.xtherm.hti160203u.R;
import com.hti.xtherm.hti160203u.bean.PictureUri;
import com.hti.xtherm.hti160203u.helper.GalleryHelper;
import com.hti.xtherm.hti160203u.helper.Helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class PicturesAdapter extends BaseAdapter {
    private Context context;
    private int mItemViewWidth;
    private List<PictureUri> mPictures;
    private List<PictureUri> mSelectPictures = new ArrayList();

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public PicturesAdapter(Context context, List<PictureUri> list) throws Resources.NotFoundException {
        this.context = context;
        this.mPictures = list;
        calcItemViewWidth();
    }

    public void setPictures(List<PictureUri> list) {
        this.mPictures = list;
        notifyDataSetChanged();
    }

    public List<PictureUri> getSelectedPictures() {
        return this.mSelectPictures;
    }

    public void selectAllPictures() {
        List<PictureUri> list = this.mPictures;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mSelectPictures.clear();
        Iterator<PictureUri> it = this.mPictures.iterator();
        while (it.hasNext()) {
            this.mSelectPictures.add(it.next());
        }
        notifyDataSetChanged();
    }

    public void clearSelectedPictures() {
        List<PictureUri> list = this.mSelectPictures;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mSelectPictures.clear();
        notifyDataSetChanged();
    }

    public void deleteSelectedPictures() {
        List<PictureUri> list = this.mSelectPictures;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<PictureUri> it = this.mSelectPictures.iterator();
        while (it.hasNext()) {
            GalleryHelper.deletePicture(it.next());
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<PictureUri> list = this.mPictures;
        if (list == null || list.size() <= 0) {
            return 0;
        }
        return this.mPictures.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mPictures.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(this.context, R.layout.activity_pictures_item, null);
            viewHolder = new ViewHolder();
            viewHolder.picture_thumbnail = (ImageView) view.findViewById(R.id.picture_thumbnail);
            viewHolder.picture_selected = (ImageView) view.findViewById(R.id.picture_selected);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
            layoutParams.width = this.mItemViewWidth;
            layoutParams.height = (int) (layoutParams.width / 1.3333334f);
            view.setLayoutParams(layoutParams);
            viewHolder.picture_selected.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.adapter.PicturesAdapter.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (viewHolder.picture_selected.getLayoutParams() == null) {
                        return;
                    }
                    viewHolder.picture_selected.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    ViewGroup.LayoutParams layoutParams2 = viewHolder.picture_selected.getLayoutParams();
                    layoutParams2.width = (int) (PicturesAdapter.this.mItemViewWidth / 6.0d);
                    layoutParams2.height = (int) (PicturesAdapter.this.mItemViewWidth / 6.0d);
                    viewHolder.picture_selected.setLayoutParams(layoutParams2);
                }
            });
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(view).load(this.mPictures.get(i).picture_uri).placeholder(R.mipmap.ic_picture_placeholder).error(R.mipmap.ic_picture_error).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new RoundedCorners(20))).into(viewHolder.picture_thumbnail);
        viewHolder.picture_selected.setOnClickListener(new View.OnClickListener() { // from class: com.hti.xtherm.hti160203u.adapter.PicturesAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ImageView imageView = (ImageView) view2;
                PictureUri pictureUri = (PictureUri) PicturesAdapter.this.mPictures.get(i);
                if (PicturesAdapter.this.mSelectPictures.contains(pictureUri)) {
                    PicturesAdapter.this.mSelectPictures.remove(pictureUri);
                    imageView.setImageResource(R.mipmap.ic_gallery_selected_normal);
                } else {
                    PicturesAdapter.this.mSelectPictures.add(pictureUri);
                    imageView.setImageResource(R.mipmap.ic_gallery_selected_focus);
                }
            }
        });
        viewHolder.picture_selected.setImageResource(this.mSelectPictures.contains(this.mPictures.get(i)) ? R.mipmap.ic_gallery_selected_focus : R.mipmap.ic_gallery_selected_normal);
        return view;
    }

    private void calcItemViewWidth() throws Resources.NotFoundException {
        this.mItemViewWidth = (int) (((Helper.getScreenSize().getWidth() * 1.0f) - ((this.context.getResources().getInteger(R.integer.gallery_gridview_numcolumns) + 1) * this.context.getResources().getDimensionPixelSize(R.dimen.gallery_gridview_space))) / this.context.getResources().getInteger(R.integer.gallery_gridview_numcolumns));
    }

    private static class ViewHolder {
        public ImageView picture_selected;
        public ImageView picture_thumbnail;

        private ViewHolder() {
        }
    }
}
