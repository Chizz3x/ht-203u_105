package com.hti.xtherm.hti160203u.helper;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.hti.xtherm.hti160203u.types.TypedValues;

import com.hti.xtherm.hti160203u.ThermalApplication;
import com.hti.xtherm.hti160203u.bean.PictureDetails;
import com.hti.xtherm.hti160203u.bean.PictureUri;
import com.hti.xtherm.hti160203u.bean.VideoDetails;
import com.hti.xtherm.hti160203u.bean.VideoUri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes.dex */
public class GalleryHelper {
    public static boolean insertVideoToGallery(File file) throws IOException {
        Uri uriInsert;
        if (file == null || !file.exists() || !file.isFile() || !file.canRead()) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 29) {
            String str = System.currentTimeMillis() + Config.MOVIES_SUFFIX;
            ContentValues contentValues = new ContentValues();
            contentValues.put("relative_path", Environment.DIRECTORY_MOVIES + File.separator + Config.MEDIA_VIDEO_FOLDER);
            contentValues.put("_display_name", str);
            contentValues.put("is_pending", (Boolean) true);
            ContentResolver contentResolver = ThermalApplication.getAppContext().getContentResolver();
            try {
                uriInsert = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
                if (uriInsert != null) {
                    try {
                        OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uriInsert);
                        FileInputStream fileInputStream = new FileInputStream(file);
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int i = fileInputStream.read(bArr);
                            if (-1 != i) {
                                outputStreamOpenOutputStream.write(bArr, 0, i);
                            } else {
                                fileInputStream.close();
                                outputStreamOpenOutputStream.flush();
                                outputStreamOpenOutputStream.close();
                                contentValues.put("is_pending", (Boolean) false);
                                contentResolver.update(uriInsert, contentValues, null, null);
                                file.delete();
                                return true;
                            }
                        }
                    } catch (Exception e) {
//                        e = e;
                        e.printStackTrace();
                        if (uriInsert != null) {
                            contentResolver.delete(uriInsert, null, null);
                        }
                        return false;
                    }
                }
            } catch (Exception e2) {
//                e = e2;
                uriInsert = null;
            }
            return false;
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("_data", file.getAbsolutePath());
        ThermalApplication.getAppContext().getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues2);
        return true;
    }

    public static List<VideoUri> getVideos() {
        Cursor cursorQuery;
        ContentResolver contentResolver = ThermalApplication.getAppContext().getContentResolver();
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 29) {
            cursorQuery = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "relative_path", "_display_name"}, "relative_path like ?", new String[]{"%105-203U Videos%"}, null);
        } else {
            cursorQuery = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "_display_name"}, "_data like ?", new String[]{"%" + ThermalApplication.getAppContext().getPackageName() + "%"}, null);
        }
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                int columnIndex = cursorQuery.getColumnIndex("_id");
                if (columnIndex != -1) {
                    VideoUri videoUri = new VideoUri();
                    videoUri.video_id = cursorQuery.getLong(columnIndex);
                    videoUri.video_uri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, videoUri.video_id);
                    arrayList.add(videoUri);
                }
            }
            cursorQuery.close();
        }
        return arrayList;
    }

    public static VideoDetails getVideoDetails(VideoUri videoUri) throws SecurityException, IOException, IllegalArgumentException {
        Cursor cursorQuery;
        ContentResolver contentResolver = ThermalApplication.getAppContext().getContentResolver();
        if (Build.VERSION.SDK_INT >= 29) {
            cursorQuery = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "_size", "date_added", TypedValues.TransitionType.S_DURATION, "_display_name"}, "_id=" + videoUri.video_id, null, null);
        } else {
            cursorQuery = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "_size", "date_added", TypedValues.TransitionType.S_DURATION, "_display_name"}, "_id=" + videoUri.video_id, null, null);
        }
        if (cursorQuery == null) {
            return null;
        }
        cursorQuery.moveToFirst();
        VideoDetails videoDetails = new VideoDetails();
        int columnIndex = cursorQuery.getColumnIndex("_data");
        if (columnIndex != -1) {
            videoDetails.video_storage = cursorQuery.getString(columnIndex);
        }
        int columnIndex2 = cursorQuery.getColumnIndex("_size");
        if (columnIndex2 != -1) {
            videoDetails.video_size = cursorQuery.getLong(columnIndex2);
        }
        int columnIndex3 = cursorQuery.getColumnIndex("resolution");
        if (columnIndex3 != -1) {
            videoDetails.video_resolution = cursorQuery.getString(columnIndex3);
        }
        if (TextUtils.isEmpty(videoDetails.video_resolution)) {
            try {
                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                mediaMetadataRetriever.setDataSource(ThermalApplication.getAppContext(), videoUri.video_uri);
                videoDetails.video_resolution = mediaMetadataRetriever.extractMetadata(18) + "x" + mediaMetadataRetriever.extractMetadata(19);
                mediaMetadataRetriever.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int columnIndex4 = cursorQuery.getColumnIndex(TypedValues.TransitionType.S_DURATION);
        if (columnIndex4 != -1) {
            videoDetails.video_duration = Helper.getDurationText(cursorQuery.getLong(columnIndex4));
        }
        int columnIndex5 = cursorQuery.getColumnIndex("date_added");
        if (columnIndex5 != -1) {
            videoDetails.video_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(cursorQuery.getLong(columnIndex5) * 1000));
        }
        int columnIndex6 = cursorQuery.getColumnIndex("_display_name");
        if (columnIndex6 != -1) {
            videoDetails.video_name = cursorQuery.getString(columnIndex6);
        }
        cursorQuery.close();
        return videoDetails;
    }

    public static String getVideoName(long j) {
        int columnIndex;
        String string = "";
        if (j < 0) {
            return "";
        }
        Cursor cursorQuery = ThermalApplication.getAppContext().getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_display_name"}, "_id=" + j, null, null);
        if (cursorQuery != null) {
            if (cursorQuery.moveToFirst() && (columnIndex = cursorQuery.getColumnIndex("_display_name")) >= 0) {
                string = cursorQuery.getString(columnIndex);
            }
            cursorQuery.close();
        }
        return string;
    }

    public static boolean deleteVideo(VideoUri videoUri) {
        return (videoUri == null || videoUri.video_uri == null || ThermalApplication.getAppContext().getContentResolver().delete(videoUri.video_uri, null, null) == 0) ? false : true;
    }

    public static boolean insertBitmapToGallery(Bitmap bitmap) throws IOException {
        Uri uriInsert;
        if (bitmap != null && bitmap.getWidth() * bitmap.getHeight() > 0) {
            if (Build.VERSION.SDK_INT >= 29) {
                String str = System.currentTimeMillis() + Config.PICTURS_SUFFIX;
                ContentValues contentValues = new ContentValues();
                contentValues.put("relative_path", Environment.DIRECTORY_PICTURES + File.separator + Config.MEDIA_IMAGE_FOLDER);
                contentValues.put("_display_name", str);
                contentValues.put("is_pending", (Boolean) true);
                ContentResolver contentResolver = ThermalApplication.getAppContext().getContentResolver();
                try {
                    uriInsert = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    if (uriInsert != null) {
                        try {
                            OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(uriInsert);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStreamOpenOutputStream);
                            outputStreamOpenOutputStream.flush();
                            outputStreamOpenOutputStream.close();
                            contentValues.put("is_pending", (Boolean) false);
                            contentResolver.update(uriInsert, contentValues, null, null);
                            return true;
                        } catch (Exception e) {
//                            e = e;
                            e.printStackTrace();
                            if (uriInsert != null) {
                                contentResolver.delete(uriInsert, null, null);
                            }
                            return false;
                        }
                    }
                } catch (Exception e2) {
//                    e = e2;
                    uriInsert = null;
                }
                return false;
            }
            File pictureFilePath = FileHelper.getPictureFilePath();
            ContentResolver contentResolver2 = ThermalApplication.getAppContext().getContentResolver();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(pictureFilePath);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("_data", pictureFilePath.getAbsolutePath());
                contentResolver2.update(contentResolver2.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues2), contentValues2, null, null);
                return true;
            } catch (Exception e3) {
                e3.printStackTrace();
                if (pictureFilePath != null && pictureFilePath.exists()) {
                    pictureFilePath.delete();
                }
            }
        }
        return false;
    }

    public static List<PictureUri> getPictures() {
        Cursor cursorQuery;
        ContentResolver contentResolver = ThermalApplication.getAppContext().getContentResolver();
        ArrayList arrayList = new ArrayList();
        if (Build.VERSION.SDK_INT >= 29) {
            cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "relative_path", "_display_name"}, "relative_path like ?", new String[]{"%105-203U Pictures%"}, null);
        } else {
            cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "_display_name"}, "_data like ?", new String[]{"%" + ThermalApplication.getAppContext().getPackageName() + "%"}, null);
        }
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                int columnIndex = cursorQuery.getColumnIndex("_id");
                if (columnIndex != -1) {
                    PictureUri pictureUri = new PictureUri();
                    pictureUri.picture_id = cursorQuery.getLong(columnIndex);
                    pictureUri.picture_uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, pictureUri.picture_id);
                    arrayList.add(pictureUri);
                }
            }
            cursorQuery.close();
        }
        return arrayList;
    }

    public static PictureDetails getPictureDetails(PictureUri pictureUri) throws IOException {
        Cursor cursorQuery;
        ContentResolver contentResolver = ThermalApplication.getAppContext().getContentResolver();
        if (Build.VERSION.SDK_INT >= 29) {
            cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "_size", "date_added", "_display_name"}, "_id=" + pictureUri.picture_id, null, null);
        } else {
            cursorQuery = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "_size", "date_added", "_display_name"}, "_id=" + pictureUri.picture_id, null, null);
        }
        if (cursorQuery == null) {
            return null;
        }
        cursorQuery.moveToFirst();
        PictureDetails pictureDetails = new PictureDetails();
        int columnIndex = cursorQuery.getColumnIndex("_data");
        if (columnIndex != -1) {
            pictureDetails.picture_storage = cursorQuery.getString(columnIndex);
        }
        int columnIndex2 = cursorQuery.getColumnIndex("_size");
        if (columnIndex2 != -1) {
            pictureDetails.picture_filesize = cursorQuery.getLong(columnIndex2);
        }
        int columnIndex3 = cursorQuery.getColumnIndex("resolution");
        if (columnIndex3 != -1) {
            pictureDetails.picture_resolution = cursorQuery.getString(columnIndex3);
        }
        if (TextUtils.isEmpty(pictureDetails.picture_resolution)) {
            try {
                InputStream inputStreamOpenInputStream = contentResolver.openInputStream(pictureUri.picture_uri);
                if (inputStreamOpenInputStream != null) {
                    Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenInputStream);
                    if (bitmapDecodeStream != null && !bitmapDecodeStream.isRecycled()) {
                        pictureDetails.picture_resolution = bitmapDecodeStream.getWidth() + "x" + bitmapDecodeStream.getHeight();
                        bitmapDecodeStream.recycle();
                    }
                    inputStreamOpenInputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int columnIndex4 = cursorQuery.getColumnIndex("date_added");
        if (columnIndex4 != -1) {
            pictureDetails.picture_capturetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(cursorQuery.getLong(columnIndex4) * 1000));
        }
        int columnIndex5 = cursorQuery.getColumnIndex("_display_name");
        if (columnIndex5 != -1) {
            pictureDetails.picture_name = cursorQuery.getString(columnIndex5);
        }
        cursorQuery.close();
        return pictureDetails;
    }

    public static boolean deletePicture(PictureUri pictureUri) {
        return (pictureUri == null || pictureUri.picture_uri == null || ThermalApplication.getAppContext().getContentResolver().delete(pictureUri.picture_uri, null, null) == 0) ? false : true;
    }
}
