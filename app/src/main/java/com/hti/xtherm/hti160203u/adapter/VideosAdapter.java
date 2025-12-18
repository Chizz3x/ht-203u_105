package com.hti.xtherm.hti160203u.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.hti.xtherm.hti160203u.bean.VideoUri;
import com.hti.xtherm.hti160203u.helper.GalleryHelper;
import com.hti.xtherm.hti160203u.helper.Helper;
import com.hti.xtherm.hti160203u.ui.activity.PlayActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class VideosAdapter extends BaseAdapter {
    private Context context;
    private int mItemViewWidth;
    private List<VideoUri> mSelectedVideos = new ArrayList();
    private List<VideoUri> mVideos;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public VideosAdapter(Context context, List<VideoUri> list) throws Resources.NotFoundException {
        this.context = context;
        this.mVideos = list;
        calcItemViewWidth();
    }

    public void setVideos(List<VideoUri> list) {
        this.mVideos = list;
        notifyDataSetChanged();
    }

    public List<VideoUri> getSelectedVideos() {
        return this.mSelectedVideos;
    }

    public void selectAllVideos() {
        List<VideoUri> list = this.mVideos;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mSelectedVideos.clear();
        Iterator<VideoUri> it = this.mVideos.iterator();
        while (it.hasNext()) {
            this.mSelectedVideos.add(it.next());
        }
        notifyDataSetChanged();
    }

    public void clearSelectedVideos() {
        List<VideoUri> list = this.mSelectedVideos;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mSelectedVideos.clear();
        notifyDataSetChanged();
    }

    public void deleteSelectedVideos() {
        List<VideoUri> list = this.mSelectedVideos;
        if (list == null || list.size() <= 0) {
            return;
        }
        Iterator<VideoUri> it = this.mSelectedVideos.iterator();
        while (it.hasNext()) {
            GalleryHelper.deleteVideo(it.next());
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<VideoUri> list = this.mVideos;
        if (list == null || list.size() <= 0) {
            return 0;
        }
        return this.mVideos.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.mVideos.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (view == null) {
            view = View.inflate(this.context, R.layout.activity_videos_item, null);
            viewHolder = new ViewHolder();
            viewHolder.video_thumbnail = (ImageView) view.findViewById(R.id.video_thumbnail);
            viewHolder.video_selected = (ImageView) view.findViewById(R.id.video_selected);
            viewHolder.video_play = (ImageView) view.findViewById(R.id.video_play);
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
            layoutParams.width = this.mItemViewWidth;
            layoutParams.height = (int) (layoutParams.width / 1.3333334f);
            view.setLayoutParams(layoutParams);
            viewHolder.video_selected.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.adapter.VideosAdapter.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (viewHolder.video_selected.getLayoutParams() == null) {
                        return;
                    }
                    viewHolder.video_selected.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    ViewGroup.LayoutParams layoutParams2 = viewHolder.video_selected.getLayoutParams();
                    layoutParams2.width = (int) (VideosAdapter.this.mItemViewWidth / 6.0d);
                    layoutParams2.height = (int) (VideosAdapter.this.mItemViewWidth / 6.0d);
                    viewHolder.video_selected.setLayoutParams(layoutParams2);
                }
            });
            viewHolder.video_play.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.hti.xtherm.hti160203u.adapter.VideosAdapter.2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    if (viewHolder.video_play.getLayoutParams() == null) {
                        return;
                    }
                    viewHolder.video_play.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    ViewGroup.LayoutParams layoutParams2 = viewHolder.video_play.getLayoutParams();
                    layoutParams2.width = (int) (VideosAdapter.this.mItemViewWidth / 4.0f);
                    layoutParams2.height = (int) (VideosAdapter.this.mItemViewWidth / 4.0f);
                    viewHolder.video_play.setLayoutParams(layoutParams2);
                }
            });
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Glide.with(view).load(this.mVideos.get(i).video_uri).placeholder(R.mipmap.ic_video_placeholder).error(R.mipmap.ic_video_error).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new RoundedCorners(20))).into(viewHolder.video_thumbnail);
        viewHolder.video_selected.setOnClickListener(new View.OnClickListener() { // from class: com.hti.xtherm.hti160203u.adapter.VideosAdapter.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ImageView imageView = (ImageView) view2;
                VideoUri videoUri = (VideoUri) VideosAdapter.this.mVideos.get(i);
                if (VideosAdapter.this.mSelectedVideos.contains(videoUri)) {
                    VideosAdapter.this.mSelectedVideos.remove(videoUri);
                    imageView.setImageResource(R.mipmap.ic_gallery_selected_normal);
                } else {
                    VideosAdapter.this.mSelectedVideos.add(videoUri);
                    imageView.setImageResource(R.mipmap.ic_gallery_selected_focus);
                }
            }
        });
        viewHolder.video_play.setOnClickListener(new View.OnClickListener() { // from class: com.hti.xtherm.hti160203u.adapter.VideosAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Intent intent = new Intent(VideosAdapter.this.context, (Class<?>) PlayActivity.class);
                intent.putExtra(PlayActivity.VIDEO_ID, ((VideoUri) VideosAdapter.this.mVideos.get(i)).video_id);
                VideosAdapter.this.context.startActivity(intent);
            }
        });
        viewHolder.video_selected.setImageResource(this.mSelectedVideos.contains(this.mVideos.get(i)) ? R.mipmap.ic_gallery_selected_focus : R.mipmap.ic_gallery_selected_normal);
        return view;
    }

    private void calcItemViewWidth() throws Resources.NotFoundException {
        int numColumns = this.context.getResources().getInteger(R.integer.gallery_gridview_numcolumns);
        int spacing = this.context.getResources().getDimensionPixelSize(R.dimen.gallery_gridview_space);
        this.mItemViewWidth = (int) (((Helper.getScreenSize().getWidth() * 1.0f) - ((numColumns + 1) * spacing)) / numColumns);
    }

    private static class ViewHolder {
        public ImageView video_play;
        public ImageView video_selected;
        public ImageView video_thumbnail;

        private ViewHolder() {
        }
    }
}
