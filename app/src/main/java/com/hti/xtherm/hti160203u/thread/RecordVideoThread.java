package com.hti.xtherm.hti160203u.thread;

import android.graphics.Bitmap;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.util.Range;
import android.view.Surface;

import com.hti.xtherm.hti160203u.helper.Alog;
import com.hti.xtherm.hti160203u.helper.BitmapHelper;
import com.hti.xtherm.hti160203u.helper.Config;
import com.hti.xtherm.hti160203u.helper.FileHelper;
import com.hti.xtherm.hti160203u.helper.GalleryHelper;
import com.xiao.yuvtools.LibYuv;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public class RecordVideoThread extends Thread {
    private boolean isHWEncodec;
    private LibYuv libYuv;
    private int mColorFormat;
    private MediaCodecInfo mEncoderInfo;
    public int mRecordFps;
    private int mRecordHeight;
    private String mRecordVideoFile;
    private int mRecordWidth;
    private MediaCodec mediaCodec;
    private MediaMuxer mediaMuxer;
    private boolean needRecord;
    private boolean needStartMediaMuxer;
    private OnVideoFrameListener videoFrameListener;
    private int video_track;

    public interface OnVideoFrameListener {
        void onRecordFileShort();

        Bitmap onRecordFrame();
    }

    private RecordVideoThread() {
        super("RecordVideoThread");
        this.mEncoderInfo = null;
        this.mColorFormat = -1;
        this.isHWEncodec = false;
        this.mRecordFps = 15;
        this.video_track = -1;
        this.needStartMediaMuxer = true;
        this.needRecord = false;
        this.mRecordWidth = Config.MEDIA_RESOLUTION.getWidth();
        this.mRecordHeight = Config.MEDIA_RESOLUTION.getHeight();
    }

    public static RecordVideoThread load() throws IOException {
        RecordVideoThread recordVideoThread = new RecordVideoThread();
        if (recordVideoThread.init()) {
            return recordVideoThread;
        }
        return null;
    }

    private boolean init() throws IOException {
        if (!chooseDecoderColorFormat() || this.mColorFormat == -1 || this.mEncoderInfo == null || !initVideoSize()) {
            return false;
        }
        String absolutePath = FileHelper.getVideoFilePath().getAbsolutePath();
        this.mRecordVideoFile = absolutePath;
        if (absolutePath == null || absolutePath.isEmpty() || !createMediaCode()) {
            return false;
        }
        this.libYuv = new LibYuv();
        return true;
    }

    public boolean record(OnVideoFrameListener onVideoFrameListener) {
        if (onVideoFrameListener == null) {
            return false;
        }
        this.videoFrameListener = onVideoFrameListener;
        this.needRecord = true;
        this.needStartMediaMuxer = true;
        start();
        return true;
    }

    public void stopRecord() {
        this.needRecord = false;
    }

    public boolean isRecording() {
        return this.needRecord;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws MediaCodec.CryptoException {
        MediaCodec mediaCodec;
        Bitmap bitmap;
        byte[] bArr;
        try {
            this.mediaCodec.start();
            long jCurrentTimeMillis = System.currentTimeMillis();
            long j = 1000 / this.mRecordFps;
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int i = this.mRecordWidth;
            int i2 = this.mRecordHeight;
            int i3 = ((i * i2) * 3) / 2;
            byte[] bArr2 = new byte[i3];
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i * i2 * 4);
            Bitmap bitmapScale = null;
            long j2 = 0;
            while (this.needRecord) {
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                Bitmap bitmapOnRecordFrame = this.videoFrameListener.onRecordFrame();
                if (bitmapOnRecordFrame != null && bitmapOnRecordFrame.getByteCount() > 0 && !bitmapOnRecordFrame.isRecycled()) {
                    bitmapScale = bitmapOnRecordFrame.copy(Bitmap.Config.ARGB_8888, true);
                } else if (bitmapScale == null) {
                }
                if (bitmapScale.getWidth() != this.mRecordWidth || bitmapScale.getHeight() != this.mRecordHeight) {
                    bitmapScale = BitmapHelper.scale(bitmapScale, this.mRecordWidth, this.mRecordHeight);
                }
                Bitmap bitmap2 = bitmapScale;
                int iDequeueInputBuffer = this.mediaCodec.dequeueInputBuffer(10000L);
                if (iDequeueInputBuffer == -1) {
                    bitmap = bitmap2;
                    bArr = bArr2;
                } else {
                    byteBufferAllocate.rewind();
                    bitmap2.copyPixelsToBuffer(byteBufferAllocate);
                    produceYUV(byteBufferAllocate.array(), bArr2);
                    ByteBuffer inputBuffer = this.mediaCodec.getInputBuffer(iDequeueInputBuffer);
                    inputBuffer.clear();
                    inputBuffer.put(bArr2);
                    bitmap = bitmap2;
                    bArr = bArr2;
                    this.mediaCodec.queueInputBuffer(iDequeueInputBuffer, 0, i3, j2 == 0 ? 0L : (System.currentTimeMillis() - jCurrentTimeMillis) * 1000, 0);
                    int iDequeueOutputBuffer = this.mediaCodec.dequeueOutputBuffer(bufferInfo, 10000L);
                    if (iDequeueOutputBuffer != -1) {
                        if (iDequeueOutputBuffer == -2) {
                            if (this.needStartMediaMuxer) {
                                this.video_track = this.mediaMuxer.addTrack(this.mediaCodec.getOutputFormat());
                                this.mediaMuxer.start();
                                this.needStartMediaMuxer = false;
                            }
                        } else {
                            if (iDequeueOutputBuffer >= 0) {
                                ByteBuffer outputBuffer = this.mediaCodec.getOutputBuffer(iDequeueOutputBuffer);
                                outputBuffer.position(bufferInfo.offset);
                                outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                                try {
                                    this.mediaMuxer.writeSampleData(this.video_track, outputBuffer, bufferInfo);
                                    j2++;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                this.mediaCodec.releaseOutputBuffer(iDequeueOutputBuffer, false);
                            }
                            try {
                                long jCurrentTimeMillis3 = System.currentTimeMillis() - jCurrentTimeMillis2;
                                if (jCurrentTimeMillis3 < j) {
                                    sleep(j - jCurrentTimeMillis3);
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
                bArr2 = bArr;
                bitmapScale = bitmap;
            }
            try {
                MediaMuxer mediaMuxer = this.mediaMuxer;
                if (mediaMuxer == null || this.needStartMediaMuxer) {
                    mediaCodec = null;
                } else {
                    mediaMuxer.stop();
                    this.mediaMuxer.release();
                    mediaCodec = null;
                    this.mediaMuxer = null;
                }
                MediaCodec mediaCodec2 = this.mediaCodec;
                if (mediaCodec2 != null) {
                    mediaCodec2.stop();
                    this.mediaCodec.release();
                    this.mediaCodec = mediaCodec;
                }
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            if (!checkMinTimeVideo(jCurrentTimeMillis)) {
                OnVideoFrameListener onVideoFrameListener = this.videoFrameListener;
                if (onVideoFrameListener != null) {
                    onVideoFrameListener.onRecordFileShort();
                }
            } else {
                show("插入图库：insert = " + GalleryHelper.insertVideoToGallery(new File(this.mRecordVideoFile)));
            }
            show("视频录制结束！");
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    private boolean checkMinTimeVideo(long j) {
        if (System.currentTimeMillis() - j >= 2000) {
            return true;
        }
        File file = new File(this.mRecordVideoFile);
        if (!file.exists()) {
            return false;
        }
        file.delete();
        return false;
    }

    private void produceYUV(byte[] bArr, byte[] bArr2) {
        int i = this.mColorFormat;
        if (i != 39 && i != 2130706688) {
            switch (i) {
                case 19:
                case 20:
                    this.libYuv.ARGB_TO_I420(bArr, bArr2, this.mRecordWidth, this.mRecordHeight);
                    break;
            }
        }
        this.libYuv.ARGB_TO_NV21(bArr, bArr2, this.mRecordWidth, this.mRecordHeight);
    }

    private boolean createMediaCode() throws IOException {
        if (this.mEncoderInfo != null && this.mColorFormat != -1) {
            MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat("video/avc", this.mRecordWidth, this.mRecordHeight);
            mediaFormatCreateVideoFormat.setInteger("color-format", this.mColorFormat);
            mediaFormatCreateVideoFormat.setInteger("bitrate", this.mRecordWidth * this.mRecordHeight);
            mediaFormatCreateVideoFormat.setInteger("frame-rate", this.mRecordFps);
            mediaFormatCreateVideoFormat.setInteger("i-frame-interval", 1);
            try {
                MediaCodec mediaCodecCreateByCodecName = MediaCodec.createByCodecName(this.mEncoderInfo.getName());
                this.mediaCodec = mediaCodecCreateByCodecName;
                mediaCodecCreateByCodecName.configure(mediaFormatCreateVideoFormat, (Surface) null, (MediaCrypto) null, MediaCodec.CONFIGURE_FLAG_ENCODE);
                this.mediaMuxer = new MediaMuxer(this.mRecordVideoFile, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean initVideoSize() {
        MediaCodecInfo mediaCodecInfo;
        MediaCodecInfo.VideoCapabilities videoCapabilities;
        if (this.mRecordWidth * this.mRecordHeight <= 0 || (mediaCodecInfo = this.mEncoderInfo) == null || (videoCapabilities = mediaCodecInfo.getCapabilitiesForType("video/avc").getVideoCapabilities()) == null) {
            return false;
        }
        Range<Integer> supportedWidths = videoCapabilities.getSupportedWidths();
        show("编码器：" + this.mEncoderInfo.getName() + ",支持宽度范围：" + supportedWidths);
        Range<Integer> supportedHeights = videoCapabilities.getSupportedHeights();
        show("编码器：" + this.mEncoderInfo.getName() + ",支持高度范围：" + supportedHeights);
        if (this.mRecordWidth > ((Integer) supportedWidths.getUpper()).intValue() && this.mRecordHeight > ((Integer) supportedHeights.getUpper()).intValue()) {
            float fIntValue = ((Integer) supportedWidths.getUpper()).intValue() / (this.mRecordWidth * 1.0f);
            float fIntValue2 = ((Integer) supportedHeights.getUpper()).intValue() / (this.mRecordHeight * 1.0f);
            if (fIntValue < fIntValue2) {
                this.mRecordWidth = ((Integer) supportedWidths.getUpper()).intValue();
                this.mRecordHeight = (int) (this.mRecordHeight * fIntValue);
            } else {
                this.mRecordWidth = (int) (this.mRecordWidth * fIntValue2);
                this.mRecordHeight = ((Integer) supportedHeights.getUpper()).intValue();
            }
        } else if (this.mRecordWidth > ((Integer) supportedWidths.getUpper()).intValue()) {
            this.mRecordWidth = ((Integer) supportedWidths.getUpper()).intValue();
            this.mRecordHeight = (int) (this.mRecordHeight * (((Integer) supportedWidths.getUpper()).intValue() / (this.mRecordWidth * 1.0f)));
        } else if (this.mRecordHeight > ((Integer) supportedHeights.getUpper()).intValue()) {
            this.mRecordWidth = (int) (this.mRecordWidth * (((Integer) supportedHeights.getUpper()).intValue() / (this.mRecordHeight * 1.0f)));
            this.mRecordHeight = ((Integer) supportedHeights.getUpper()).intValue();
        }
        if (this.mRecordWidth < ((Integer) supportedWidths.getLower()).intValue() && this.mRecordHeight < ((Integer) supportedHeights.getLower()).intValue()) {
            float fIntValue3 = (this.mRecordWidth * 1.0f) / ((Integer) supportedWidths.getLower()).intValue();
            float fIntValue4 = (this.mRecordHeight * 1.0f) / ((Integer) supportedHeights.getLower()).intValue();
            if (fIntValue3 < fIntValue4) {
                this.mRecordWidth = ((Integer) supportedWidths.getLower()).intValue();
                this.mRecordHeight = (int) (this.mRecordHeight / fIntValue3);
            } else {
                this.mRecordWidth = (int) (this.mRecordWidth / fIntValue4);
                this.mRecordHeight = ((Integer) supportedHeights.getLower()).intValue();
            }
        } else if (this.mRecordWidth < ((Integer) supportedWidths.getLower()).intValue()) {
            this.mRecordWidth = ((Integer) supportedWidths.getLower()).intValue();
            this.mRecordHeight = (int) (this.mRecordHeight / ((this.mRecordWidth * 1.0f) / ((Integer) supportedWidths.getLower()).intValue()));
        } else if (this.mRecordHeight < ((Integer) supportedHeights.getLower()).intValue()) {
            this.mRecordWidth = (int) (this.mRecordWidth / ((this.mRecordHeight * 1.0f) / ((Integer) supportedHeights.getLower()).intValue()));
            this.mRecordHeight = ((Integer) supportedHeights.getLower()).intValue();
        }
        while (true) {
            int i = this.mRecordWidth;
            if (i % 2 == 0) {
                break;
            }
            this.mRecordWidth = i - 1;
        }
        while (true) {
            int i2 = this.mRecordHeight;
            if (i2 % 2 == 0) {
                break;
            }
            this.mRecordHeight = i2 - 1;
        }
        show("确认最终视频大小：w = " + this.mRecordWidth + ",h = " + this.mRecordHeight);
        return this.mRecordWidth * this.mRecordHeight > 0;
    }

    private boolean chooseDecoderColorFormat() {
        MediaCodecInfo[] codecInfos = new MediaCodecList(0).getCodecInfos();
        if (codecInfos == null || codecInfos.length <= 0) {
            show("系统不支持视频编码");
            return false;
        }
        ArrayList<MediaCodecInfo> arrayList = new ArrayList();
        for (MediaCodecInfo mediaCodecInfo : codecInfos) {
            String string = Arrays.toString(mediaCodecInfo.getSupportedTypes());
            if (mediaCodecInfo.isEncoder() && string.indexOf("video/avc") != -1) {
                arrayList.add(mediaCodecInfo);
            }
        }
        if (arrayList.size() <= 0) {
            show("系统不支持H264编码器");
            return false;
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (MediaCodecInfo mediaCodecInfo2 : arrayList) {
            int[] iArr = mediaCodecInfo2.getCapabilitiesForType("video/avc").colorFormats;
            show("name = " + mediaCodecInfo2.getName() + ",支持采样：" + Arrays.toString(iArr));
            for (int i : iArr) {
                if (i == 21 || i == 19 || i == 39 || i == 20 || i == 2130706688) {
                    if (mediaCodecInfo2.getName().toUpperCase().startsWith("OMX.GOOGLE")) {
                        arrayList2.add(mediaCodecInfo2);
                    } else {
                        arrayList3.add(mediaCodecInfo2);
                    }
                }
            }
        }
        if (this.isHWEncodec && arrayList3.size() > 0) {
            MediaCodecInfo mediaCodecInfo3 = (MediaCodecInfo) arrayList3.get(0);
            this.mEncoderInfo = mediaCodecInfo3;
            int colorcFormat = getColorcFormat(mediaCodecInfo3);
            this.mColorFormat = colorcFormat;
            if (colorcFormat != -1) {
                show("选择硬编：" + this.mEncoderInfo.getName() + ",yuv采样：" + this.mColorFormat);
                return true;
            }
        }
        if (arrayList2.size() > 0) {
            MediaCodecInfo mediaCodecInfo4 = (MediaCodecInfo) arrayList2.get(0);
            this.mEncoderInfo = mediaCodecInfo4;
            int colorcFormat2 = getColorcFormat(mediaCodecInfo4);
            this.mColorFormat = colorcFormat2;
            if (colorcFormat2 != -1) {
                show("选择软编：" + this.mEncoderInfo.getName() + ",yuv采样：" + this.mColorFormat);
                return true;
            }
        }
        if (arrayList3.size() > 0) {
            MediaCodecInfo mediaCodecInfo5 = (MediaCodecInfo) arrayList3.get(0);
            this.mEncoderInfo = mediaCodecInfo5;
            int colorcFormat3 = getColorcFormat(mediaCodecInfo5);
            this.mColorFormat = colorcFormat3;
            if (colorcFormat3 != -1) {
                show("选择硬编：" + this.mEncoderInfo.getName() + ",yuv采样：" + this.mColorFormat);
                return true;
            }
        }
        show("无法选择硬编或软编");
        return false;
    }

    private int getColorcFormat(MediaCodecInfo mediaCodecInfo) {
        if (mediaCodecInfo == null) {
            return -1;
        }
        int[] iArr = mediaCodecInfo.getCapabilitiesForType("video/avc").colorFormats;
        show("支持采样：" + Arrays.toString(iArr));
        if (iArr != null && iArr.length > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i : iArr) {
                arrayList.add(Integer.valueOf(i));
            }
            if (arrayList.contains(19)) {
                return 19;
            }
            if (arrayList.contains(21)) {
                return 21;
            }
            if (arrayList.contains(20)) {
                return 20;
            }
            if (arrayList.contains(39)) {
                return 39;
            }
            if (arrayList.contains(2130706688)) {
                return 2130706688;
            }
        }
        return -1;
    }

    private void show(String str) {
        Alog.e(str, new Object[0]);
    }
}
