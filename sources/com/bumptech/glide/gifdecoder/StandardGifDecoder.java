package com.bumptech.glide.gifdecoder;

import android.graphics.Bitmap;
import android.util.Log;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bumptech.glide.gifdecoder.GifDecoder;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.UByte;

public class StandardGifDecoder implements GifDecoder {
    private static final int BYTES_PER_INTEGER = 4;
    @ColorInt
    private static final int COLOR_TRANSPARENT_BLACK = 0;
    private static final int INITIAL_FRAME_POINTER = -1;
    private static final int MASK_INT_LOWEST_BYTE = 255;
    private static final int MAX_STACK_SIZE = 4096;
    private static final int NULL_CODE = -1;
    private static final String TAG = "StandardGifDecoder";
    @ColorInt
    private int[] act;
    @NonNull
    private Bitmap.Config bitmapConfig;
    private final GifDecoder.BitmapProvider bitmapProvider;
    private byte[] block;
    private int downsampledHeight;
    private int downsampledWidth;
    private int framePointer;
    private GifHeader header;
    @Nullable
    private Boolean isFirstFrameTransparent;
    private byte[] mainPixels;
    @ColorInt
    private int[] mainScratch;
    private GifHeaderParser parser;
    @ColorInt
    private final int[] pct;
    private byte[] pixelStack;
    private short[] prefix;
    private Bitmap previousImage;
    private ByteBuffer rawData;
    private int sampleSize;
    private boolean savePrevious;
    private int status;
    private byte[] suffix;

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider2, GifHeader gifHeader, ByteBuffer byteBuffer) {
        this(bitmapProvider2, gifHeader, byteBuffer, 1);
    }

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider2, GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
        this(bitmapProvider2);
        setData(gifHeader, byteBuffer, i);
    }

    public StandardGifDecoder(@NonNull GifDecoder.BitmapProvider bitmapProvider2) {
        this.pct = new int[256];
        this.bitmapConfig = Bitmap.Config.ARGB_8888;
        this.bitmapProvider = bitmapProvider2;
        this.header = new GifHeader();
    }

    public int getWidth() {
        return this.header.width;
    }

    public int getHeight() {
        return this.header.height;
    }

    @NonNull
    public ByteBuffer getData() {
        return this.rawData;
    }

    public int getStatus() {
        return this.status;
    }

    public void advance() {
        this.framePointer = (this.framePointer + 1) % this.header.frameCount;
    }

    public int getDelay(int i) {
        if (i < 0 || i >= this.header.frameCount) {
            return -1;
        }
        return this.header.frames.get(i).delay;
    }

    public int getNextDelay() {
        int i;
        if (this.header.frameCount <= 0 || (i = this.framePointer) < 0) {
            return 0;
        }
        return getDelay(i);
    }

    public int getFrameCount() {
        return this.header.frameCount;
    }

    public int getCurrentFrameIndex() {
        return this.framePointer;
    }

    public void resetFrameIndex() {
        this.framePointer = -1;
    }

    @Deprecated
    public int getLoopCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        return this.header.loopCount;
    }

    public int getNetscapeLoopCount() {
        return this.header.loopCount;
    }

    public int getTotalIterationCount() {
        if (this.header.loopCount == -1) {
            return 1;
        }
        if (this.header.loopCount == 0) {
            return 0;
        }
        return this.header.loopCount + 1;
    }

    public int getByteSize() {
        return this.rawData.limit() + this.mainPixels.length + (this.mainScratch.length * 4);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f7, code lost:
        return null;
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized android.graphics.Bitmap getNextFrame() {
        /*
            r8 = this;
            monitor-enter(r8)
            com.bumptech.glide.gifdecoder.GifHeader r0 = r8.header     // Catch:{ all -> 0x00f8 }
            int r0 = r0.frameCount     // Catch:{ all -> 0x00f8 }
            r1 = 3
            r2 = 1
            if (r0 <= 0) goto L_0x000d
            int r0 = r8.framePointer     // Catch:{ all -> 0x00f8 }
            if (r0 >= 0) goto L_0x003b
        L_0x000d:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00f8 }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00f8 }
            if (r0 == 0) goto L_0x0039
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00f8 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f8 }
            r3.<init>()     // Catch:{ all -> 0x00f8 }
            java.lang.String r4 = "Unable to decode frame, frameCount="
            r3.append(r4)     // Catch:{ all -> 0x00f8 }
            com.bumptech.glide.gifdecoder.GifHeader r4 = r8.header     // Catch:{ all -> 0x00f8 }
            int r4 = r4.frameCount     // Catch:{ all -> 0x00f8 }
            r3.append(r4)     // Catch:{ all -> 0x00f8 }
            java.lang.String r4 = ", framePointer="
            r3.append(r4)     // Catch:{ all -> 0x00f8 }
            int r4 = r8.framePointer     // Catch:{ all -> 0x00f8 }
            r3.append(r4)     // Catch:{ all -> 0x00f8 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x00f8 }
            android.util.Log.d(r0, r3)     // Catch:{ all -> 0x00f8 }
        L_0x0039:
            r8.status = r2     // Catch:{ all -> 0x00f8 }
        L_0x003b:
            int r0 = r8.status     // Catch:{ all -> 0x00f8 }
            r3 = 0
            if (r0 == r2) goto L_0x00d6
            int r0 = r8.status     // Catch:{ all -> 0x00f8 }
            r4 = 2
            if (r0 != r4) goto L_0x0047
            goto L_0x00d6
        L_0x0047:
            r0 = 0
            r8.status = r0     // Catch:{ all -> 0x00f8 }
            byte[] r5 = r8.block     // Catch:{ all -> 0x00f8 }
            if (r5 != 0) goto L_0x0058
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r5 = r8.bitmapProvider     // Catch:{ all -> 0x00f8 }
            r6 = 255(0xff, float:3.57E-43)
            byte[] r5 = r5.obtainByteArray(r6)     // Catch:{ all -> 0x00f8 }
            r8.block = r5     // Catch:{ all -> 0x00f8 }
        L_0x0058:
            com.bumptech.glide.gifdecoder.GifHeader r5 = r8.header     // Catch:{ all -> 0x00f8 }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r5 = r5.frames     // Catch:{ all -> 0x00f8 }
            int r6 = r8.framePointer     // Catch:{ all -> 0x00f8 }
            java.lang.Object r5 = r5.get(r6)     // Catch:{ all -> 0x00f8 }
            com.bumptech.glide.gifdecoder.GifFrame r5 = (com.bumptech.glide.gifdecoder.GifFrame) r5     // Catch:{ all -> 0x00f8 }
            int r6 = r8.framePointer     // Catch:{ all -> 0x00f8 }
            int r6 = r6 - r2
            if (r6 < 0) goto L_0x0074
            com.bumptech.glide.gifdecoder.GifHeader r7 = r8.header     // Catch:{ all -> 0x00f8 }
            java.util.List<com.bumptech.glide.gifdecoder.GifFrame> r7 = r7.frames     // Catch:{ all -> 0x00f8 }
            java.lang.Object r6 = r7.get(r6)     // Catch:{ all -> 0x00f8 }
            com.bumptech.glide.gifdecoder.GifFrame r6 = (com.bumptech.glide.gifdecoder.GifFrame) r6     // Catch:{ all -> 0x00f8 }
            goto L_0x0075
        L_0x0074:
            r6 = r3
        L_0x0075:
            int[] r7 = r5.lct     // Catch:{ all -> 0x00f8 }
            if (r7 == 0) goto L_0x007c
            int[] r7 = r5.lct     // Catch:{ all -> 0x00f8 }
            goto L_0x0080
        L_0x007c:
            com.bumptech.glide.gifdecoder.GifHeader r7 = r8.header     // Catch:{ all -> 0x00f8 }
            int[] r7 = r7.gct     // Catch:{ all -> 0x00f8 }
        L_0x0080:
            r8.act = r7     // Catch:{ all -> 0x00f8 }
            int[] r7 = r8.act     // Catch:{ all -> 0x00f8 }
            if (r7 != 0) goto L_0x00aa
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00f8 }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00f8 }
            if (r0 == 0) goto L_0x00a6
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00f8 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f8 }
            r1.<init>()     // Catch:{ all -> 0x00f8 }
            java.lang.String r4 = "No valid color table found for frame #"
            r1.append(r4)     // Catch:{ all -> 0x00f8 }
            int r4 = r8.framePointer     // Catch:{ all -> 0x00f8 }
            r1.append(r4)     // Catch:{ all -> 0x00f8 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00f8 }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00f8 }
        L_0x00a6:
            r8.status = r2     // Catch:{ all -> 0x00f8 }
            monitor-exit(r8)
            return r3
        L_0x00aa:
            boolean r1 = r5.transparency     // Catch:{ all -> 0x00f8 }
            if (r1 == 0) goto L_0x00d0
            int[] r1 = r8.act     // Catch:{ all -> 0x00f8 }
            int[] r3 = r8.pct     // Catch:{ all -> 0x00f8 }
            int[] r7 = r8.act     // Catch:{ all -> 0x00f8 }
            int r7 = r7.length     // Catch:{ all -> 0x00f8 }
            java.lang.System.arraycopy(r1, r0, r3, r0, r7)     // Catch:{ all -> 0x00f8 }
            int[] r1 = r8.pct     // Catch:{ all -> 0x00f8 }
            r8.act = r1     // Catch:{ all -> 0x00f8 }
            int[] r1 = r8.act     // Catch:{ all -> 0x00f8 }
            int r3 = r5.transIndex     // Catch:{ all -> 0x00f8 }
            r1[r3] = r0     // Catch:{ all -> 0x00f8 }
            int r0 = r5.dispose     // Catch:{ all -> 0x00f8 }
            if (r0 != r4) goto L_0x00d0
            int r0 = r8.framePointer     // Catch:{ all -> 0x00f8 }
            if (r0 != 0) goto L_0x00d0
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)     // Catch:{ all -> 0x00f8 }
            r8.isFirstFrameTransparent = r0     // Catch:{ all -> 0x00f8 }
        L_0x00d0:
            android.graphics.Bitmap r0 = r8.setPixels(r5, r6)     // Catch:{ all -> 0x00f8 }
            monitor-exit(r8)
            return r0
        L_0x00d6:
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00f8 }
            boolean r0 = android.util.Log.isLoggable(r0, r1)     // Catch:{ all -> 0x00f8 }
            if (r0 == 0) goto L_0x00f6
            java.lang.String r0 = TAG     // Catch:{ all -> 0x00f8 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f8 }
            r1.<init>()     // Catch:{ all -> 0x00f8 }
            java.lang.String r2 = "Unable to decode frame, status="
            r1.append(r2)     // Catch:{ all -> 0x00f8 }
            int r2 = r8.status     // Catch:{ all -> 0x00f8 }
            r1.append(r2)     // Catch:{ all -> 0x00f8 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00f8 }
            android.util.Log.d(r0, r1)     // Catch:{ all -> 0x00f8 }
        L_0x00f6:
            monitor-exit(r8)
            return r3
        L_0x00f8:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.getNextFrame():android.graphics.Bitmap");
    }

    public int read(@Nullable InputStream inputStream, int i) {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i > 0 ? i + 4096 : 16384);
                byte[] bArr = new byte[16384];
                while (true) {
                    int read = inputStream.read(bArr, 0, bArr.length);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                Log.w(TAG, "Error reading data from stream", e);
            }
        } else {
            this.status = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e2) {
                Log.w(TAG, "Error closing stream", e2);
            }
        }
        return this.status;
    }

    public void clear() {
        this.header = null;
        byte[] bArr = this.mainPixels;
        if (bArr != null) {
            this.bitmapProvider.release(bArr);
        }
        int[] iArr = this.mainScratch;
        if (iArr != null) {
            this.bitmapProvider.release(iArr);
        }
        Bitmap bitmap = this.previousImage;
        if (bitmap != null) {
            this.bitmapProvider.release(bitmap);
        }
        this.previousImage = null;
        this.rawData = null;
        this.isFirstFrameTransparent = null;
        byte[] bArr2 = this.block;
        if (bArr2 != null) {
            this.bitmapProvider.release(bArr2);
        }
    }

    public synchronized void setData(@NonNull GifHeader gifHeader, @NonNull byte[] bArr) {
        setData(gifHeader, ByteBuffer.wrap(bArr));
    }

    public synchronized void setData(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer) {
        setData(gifHeader, byteBuffer, 1);
    }

    public synchronized void setData(@NonNull GifHeader gifHeader, @NonNull ByteBuffer byteBuffer, int i) {
        if (i > 0) {
            int highestOneBit = Integer.highestOneBit(i);
            this.status = 0;
            this.header = gifHeader;
            this.framePointer = -1;
            this.rawData = byteBuffer.asReadOnlyBuffer();
            this.rawData.position(0);
            this.rawData.order(ByteOrder.LITTLE_ENDIAN);
            this.savePrevious = false;
            Iterator<GifFrame> it = gifHeader.frames.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().dispose == 3) {
                        this.savePrevious = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            this.sampleSize = highestOneBit;
            this.downsampledWidth = gifHeader.width / highestOneBit;
            this.downsampledHeight = gifHeader.height / highestOneBit;
            this.mainPixels = this.bitmapProvider.obtainByteArray(gifHeader.width * gifHeader.height);
            this.mainScratch = this.bitmapProvider.obtainIntArray(this.downsampledWidth * this.downsampledHeight);
        } else {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + i);
        }
    }

    @NonNull
    private GifHeaderParser getHeaderParser() {
        if (this.parser == null) {
            this.parser = new GifHeaderParser();
        }
        return this.parser;
    }

    public synchronized int read(@Nullable byte[] bArr) {
        this.header = getHeaderParser().setData(bArr).parseHeader();
        if (bArr != null) {
            setData(this.header, bArr);
        }
        return this.status;
    }

    public void setDefaultBitmapConfig(@NonNull Bitmap.Config config) {
        if (config == Bitmap.Config.ARGB_8888 || config == Bitmap.Config.RGB_565) {
            this.bitmapConfig = config;
            return;
        }
        throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
    }

    private Bitmap setPixels(GifFrame gifFrame, GifFrame gifFrame2) {
        Bitmap bitmap;
        int[] iArr = this.mainScratch;
        int i = 0;
        if (gifFrame2 == null) {
            Bitmap bitmap2 = this.previousImage;
            if (bitmap2 != null) {
                this.bitmapProvider.release(bitmap2);
            }
            this.previousImage = null;
            Arrays.fill(iArr, 0);
        }
        if (gifFrame2 != null && gifFrame2.dispose == 3 && this.previousImage == null) {
            Arrays.fill(iArr, 0);
        }
        if (gifFrame2 != null && gifFrame2.dispose > 0) {
            if (gifFrame2.dispose == 2) {
                if (!gifFrame.transparency) {
                    int i2 = this.header.bgColor;
                    if (gifFrame.lct == null || this.header.bgIndex != gifFrame.transIndex) {
                        i = i2;
                    }
                }
                int i3 = gifFrame2.f1505ih / this.sampleSize;
                int i4 = gifFrame2.f1508iy / this.sampleSize;
                int i5 = gifFrame2.f1506iw / this.sampleSize;
                int i6 = gifFrame2.f1507ix / this.sampleSize;
                int i7 = this.downsampledWidth;
                int i8 = (i4 * i7) + i6;
                int i9 = (i3 * i7) + i8;
                while (i8 < i9) {
                    int i10 = i8 + i5;
                    for (int i11 = i8; i11 < i10; i11++) {
                        iArr[i11] = i;
                    }
                    i8 += this.downsampledWidth;
                }
            } else if (gifFrame2.dispose == 3 && (bitmap = this.previousImage) != null) {
                int i12 = this.downsampledWidth;
                bitmap.getPixels(iArr, 0, i12, 0, 0, i12, this.downsampledHeight);
            }
        }
        decodeBitmapData(gifFrame);
        if (gifFrame.interlace || this.sampleSize != 1) {
            copyCopyIntoScratchRobust(gifFrame);
        } else {
            copyIntoScratchFast(gifFrame);
        }
        if (this.savePrevious && (gifFrame.dispose == 0 || gifFrame.dispose == 1)) {
            if (this.previousImage == null) {
                this.previousImage = getNextBitmap();
            }
            Bitmap bitmap3 = this.previousImage;
            int i13 = this.downsampledWidth;
            bitmap3.setPixels(iArr, 0, i13, 0, 0, i13, this.downsampledHeight);
        }
        Bitmap nextBitmap = getNextBitmap();
        int i14 = this.downsampledWidth;
        nextBitmap.setPixels(iArr, 0, i14, 0, 0, i14, this.downsampledHeight);
        return nextBitmap;
    }

    private void copyIntoScratchFast(GifFrame gifFrame) {
        GifFrame gifFrame2 = gifFrame;
        int[] iArr = this.mainScratch;
        int i = gifFrame2.f1505ih;
        int i2 = gifFrame2.f1508iy;
        int i3 = gifFrame2.f1506iw;
        int i4 = gifFrame2.f1507ix;
        boolean z = this.framePointer == 0;
        int i5 = this.downsampledWidth;
        byte[] bArr = this.mainPixels;
        int[] iArr2 = this.act;
        int i6 = 0;
        byte b = -1;
        while (i6 < i) {
            int i7 = (i6 + i2) * i5;
            int i8 = i7 + i4;
            int i9 = i8 + i3;
            int i10 = i7 + i5;
            if (i10 < i9) {
                i9 = i10;
            }
            int i11 = gifFrame2.f1506iw * i6;
            int i12 = i8;
            while (i12 < i9) {
                byte b2 = bArr[i11];
                byte b3 = b2 & UByte.MAX_VALUE;
                if (b3 != b) {
                    int i13 = iArr2[b3];
                    if (i13 != 0) {
                        iArr[i12] = i13;
                    } else {
                        b = b2;
                    }
                }
                i11++;
                i12++;
                GifFrame gifFrame3 = gifFrame;
            }
            i6++;
            gifFrame2 = gifFrame;
        }
        Boolean bool = this.isFirstFrameTransparent;
        this.isFirstFrameTransparent = Boolean.valueOf((bool != null && bool.booleanValue()) || (this.isFirstFrameTransparent == null && z && b != -1));
    }

    private void copyCopyIntoScratchRobust(GifFrame gifFrame) {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        GifFrame gifFrame2 = gifFrame;
        int[] iArr = this.mainScratch;
        int i6 = gifFrame2.f1505ih / this.sampleSize;
        int i7 = gifFrame2.f1508iy / this.sampleSize;
        int i8 = gifFrame2.f1506iw / this.sampleSize;
        int i9 = gifFrame2.f1507ix / this.sampleSize;
        boolean z2 = true;
        boolean z3 = this.framePointer == 0;
        int i10 = this.sampleSize;
        int i11 = this.downsampledWidth;
        int i12 = this.downsampledHeight;
        byte[] bArr = this.mainPixels;
        int[] iArr2 = this.act;
        Boolean bool = this.isFirstFrameTransparent;
        int i13 = 0;
        int i14 = 0;
        int i15 = 1;
        int i16 = 8;
        while (i13 < i6) {
            Boolean bool2 = z2;
            if (gifFrame2.interlace) {
                if (i14 >= i6) {
                    i = i6;
                    i5 = i15 + 1;
                    if (i5 == 2) {
                        i14 = 4;
                    } else if (i5 == 3) {
                        i14 = 2;
                        i16 = 4;
                    } else if (i5 == 4) {
                        i14 = 1;
                        i16 = 2;
                    }
                } else {
                    i = i6;
                    i5 = i15;
                }
                i2 = i14 + i16;
                i15 = i5;
            } else {
                i = i6;
                i2 = i14;
                i14 = i13;
            }
            int i17 = i14 + i7;
            boolean z4 = i10 == 1;
            if (i17 < i12) {
                int i18 = i17 * i11;
                int i19 = i18 + i9;
                int i20 = i19 + i8;
                int i21 = i18 + i11;
                if (i21 < i20) {
                    i20 = i21;
                }
                i3 = i7;
                int i22 = i13 * i10 * gifFrame2.f1506iw;
                if (z4) {
                    int i23 = i19;
                    while (i23 < i20) {
                        int i24 = i8;
                        int i25 = iArr2[bArr[i22] & UByte.MAX_VALUE];
                        if (i25 != 0) {
                            iArr[i23] = i25;
                        } else if (z3 && bool == null) {
                            bool = bool2;
                        }
                        i22 += i10;
                        i23++;
                        i8 = i24;
                    }
                } else {
                    i4 = i8;
                    int i26 = ((i20 - i19) * i10) + i22;
                    int i27 = i19;
                    while (i27 < i20) {
                        int i28 = i20;
                        int averageColorsNear = averageColorsNear(i22, i26, gifFrame2.f1506iw);
                        if (averageColorsNear != 0) {
                            iArr[i27] = averageColorsNear;
                        } else if (z3 && bool == null) {
                            bool = bool2;
                        }
                        i22 += i10;
                        i27++;
                        i20 = i28;
                    }
                    i13++;
                    i14 = i2;
                    i8 = i4;
                    z2 = bool2;
                    i6 = i;
                    i7 = i3;
                }
            } else {
                i3 = i7;
            }
            i4 = i8;
            i13++;
            i14 = i2;
            i8 = i4;
            z2 = bool2;
            i6 = i;
            i7 = i3;
        }
        if (this.isFirstFrameTransparent == null) {
            if (bool == null) {
                z = false;
            } else {
                z = bool.booleanValue();
            }
            this.isFirstFrameTransparent = Boolean.valueOf(z);
        }
    }

    @ColorInt
    private int averageColorsNear(int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = i; i9 < this.sampleSize + i; i9++) {
            byte[] bArr = this.mainPixels;
            if (i9 >= bArr.length || i9 >= i2) {
                break;
            }
            int i10 = this.act[bArr[i9] & UByte.MAX_VALUE];
            if (i10 != 0) {
                i4 += (i10 >> 24) & 255;
                i5 += (i10 >> 16) & 255;
                i6 += (i10 >> 8) & 255;
                i7 += i10 & 255;
                i8++;
            }
        }
        int i11 = i + i3;
        for (int i12 = i11; i12 < this.sampleSize + i11; i12++) {
            byte[] bArr2 = this.mainPixels;
            if (i12 >= bArr2.length || i12 >= i2) {
                break;
            }
            int i13 = this.act[bArr2[i12] & UByte.MAX_VALUE];
            if (i13 != 0) {
                i4 += (i13 >> 24) & 255;
                i5 += (i13 >> 16) & 255;
                i6 += (i13 >> 8) & 255;
                i7 += i13 & 255;
                i8++;
            }
        }
        if (i8 == 0) {
            return 0;
        }
        return ((i4 / i8) << 24) | ((i5 / i8) << 16) | ((i6 / i8) << 8) | (i7 / i8);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v0, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v1, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v5, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r24v2, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v3, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v4, resolved type: byte} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: byte} */
    /* JADX WARNING: Incorrect type for immutable var: ssa=short, code=int, for r4v16, types: [short] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame r30) {
        /*
            r29 = this;
            r0 = r29
            r1 = r30
            if (r1 == 0) goto L_0x000d
            java.nio.ByteBuffer r2 = r0.rawData
            int r3 = r1.bufferFrameStart
            r2.position(r3)
        L_0x000d:
            if (r1 != 0) goto L_0x001a
            com.bumptech.glide.gifdecoder.GifHeader r1 = r0.header
            int r1 = r1.width
            com.bumptech.glide.gifdecoder.GifHeader r2 = r0.header
            int r2 = r2.height
            int r1 = r1 * r2
            goto L_0x0020
        L_0x001a:
            int r2 = r1.f1506iw
            int r1 = r1.f1505ih
            int r1 = r1 * r2
        L_0x0020:
            byte[] r2 = r0.mainPixels
            if (r2 == 0) goto L_0x0027
            int r2 = r2.length
            if (r2 >= r1) goto L_0x002f
        L_0x0027:
            com.bumptech.glide.gifdecoder.GifDecoder$BitmapProvider r2 = r0.bitmapProvider
            byte[] r2 = r2.obtainByteArray(r1)
            r0.mainPixels = r2
        L_0x002f:
            byte[] r2 = r0.mainPixels
            short[] r3 = r0.prefix
            r4 = 4096(0x1000, float:5.74E-42)
            if (r3 != 0) goto L_0x003b
            short[] r3 = new short[r4]
            r0.prefix = r3
        L_0x003b:
            short[] r3 = r0.prefix
            byte[] r5 = r0.suffix
            if (r5 != 0) goto L_0x0045
            byte[] r5 = new byte[r4]
            r0.suffix = r5
        L_0x0045:
            byte[] r5 = r0.suffix
            byte[] r6 = r0.pixelStack
            if (r6 != 0) goto L_0x0051
            r6 = 4097(0x1001, float:5.741E-42)
            byte[] r6 = new byte[r6]
            r0.pixelStack = r6
        L_0x0051:
            byte[] r6 = r0.pixelStack
            int r7 = r29.readByte()
            r8 = 1
            int r9 = r8 << r7
            int r10 = r9 + 1
            int r11 = r9 + 2
            int r7 = r7 + r8
            int r12 = r8 << r7
            int r12 = r12 - r8
            r13 = 0
            r14 = 0
        L_0x0064:
            if (r14 >= r9) goto L_0x006e
            r3[r14] = r13
            byte r15 = (byte) r14
            r5[r14] = r15
            int r14 = r14 + 1
            goto L_0x0064
        L_0x006e:
            byte[] r14 = r0.block
            r15 = -1
            r26 = r7
            r24 = r11
            r25 = r12
            r16 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = -1
            r22 = 0
            r23 = 0
        L_0x0087:
            if (r13 >= r1) goto L_0x015f
            if (r16 != 0) goto L_0x0098
            int r16 = r29.readBlock()
            if (r16 > 0) goto L_0x0096
            r3 = 3
            r0.status = r3
            goto L_0x015f
        L_0x0096:
            r19 = 0
        L_0x0098:
            byte r4 = r14[r19]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r4 = r4 << r17
            int r18 = r18 + r4
            int r17 = r17 + 8
            int r19 = r19 + 1
            int r16 = r16 + -1
            r4 = r17
            r8 = r21
            r28 = r22
            r27 = r24
            r21 = r20
            r20 = r13
            r13 = r26
        L_0x00b4:
            if (r4 < r13) goto L_0x0143
            r15 = r18 & r25
            int r18 = r18 >> r13
            int r4 = r4 - r13
            if (r15 != r9) goto L_0x00c5
            r13 = r7
            r27 = r11
            r25 = r12
            r8 = -1
        L_0x00c3:
            r15 = -1
            goto L_0x00b4
        L_0x00c5:
            if (r15 != r10) goto L_0x00da
            r17 = r4
            r26 = r13
            r13 = r20
            r20 = r21
            r24 = r27
            r22 = r28
            r4 = 4096(0x1000, float:5.74E-42)
            r15 = -1
            r21 = r8
            r8 = 1
            goto L_0x0087
        L_0x00da:
            r0 = -1
            if (r8 != r0) goto L_0x00eb
            byte r8 = r5[r15]
            r2[r21] = r8
            int r21 = r21 + 1
            int r20 = r20 + 1
            r0 = r29
            r8 = r15
            r28 = r8
            goto L_0x00c3
        L_0x00eb:
            r0 = r27
            if (r15 < r0) goto L_0x00fa
            r24 = r4
            r4 = r28
            byte r4 = (byte) r4
            r6[r23] = r4
            int r23 = r23 + 1
            r4 = r8
            goto L_0x00fd
        L_0x00fa:
            r24 = r4
            r4 = r15
        L_0x00fd:
            if (r4 < r9) goto L_0x0108
            byte r26 = r5[r4]
            r6[r23] = r26
            int r23 = r23 + 1
            short r4 = r3[r4]
            goto L_0x00fd
        L_0x0108:
            byte r4 = r5[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            r26 = r7
            byte r7 = (byte) r4
            r2[r21] = r7
        L_0x0111:
            int r21 = r21 + 1
            int r20 = r20 + 1
            if (r23 <= 0) goto L_0x011e
            int r23 = r23 + -1
            byte r27 = r6[r23]
            r2[r21] = r27
            goto L_0x0111
        L_0x011e:
            r27 = r4
            r4 = 4096(0x1000, float:5.74E-42)
            if (r0 >= r4) goto L_0x0135
            short r8 = (short) r8
            r3[r0] = r8
            r5[r0] = r7
            int r0 = r0 + 1
            r7 = r0 & r25
            if (r7 != 0) goto L_0x0135
            if (r0 >= r4) goto L_0x0135
            int r13 = r13 + 1
            int r25 = r25 + r0
        L_0x0135:
            r8 = r15
            r4 = r24
            r7 = r26
            r28 = r27
            r15 = -1
            r27 = r0
            r0 = r29
            goto L_0x00b4
        L_0x0143:
            r24 = r4
            r0 = r27
            r15 = r28
            r26 = r13
            r22 = r15
            r13 = r20
            r20 = r21
            r17 = r24
            r4 = 4096(0x1000, float:5.74E-42)
            r15 = -1
            r24 = r0
            r21 = r8
            r8 = 1
            r0 = r29
            goto L_0x0087
        L_0x015f:
            r13 = r20
            r0 = 0
            java.util.Arrays.fill(r2, r13, r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.gifdecoder.StandardGifDecoder.decodeBitmapData(com.bumptech.glide.gifdecoder.GifFrame):void");
    }

    private int readByte() {
        return this.rawData.get() & UByte.MAX_VALUE;
    }

    private int readBlock() {
        int readByte = readByte();
        if (readByte <= 0) {
            return readByte;
        }
        ByteBuffer byteBuffer = this.rawData;
        byteBuffer.get(this.block, 0, Math.min(readByte, byteBuffer.remaining()));
        return readByte;
    }

    private Bitmap getNextBitmap() {
        Boolean bool = this.isFirstFrameTransparent;
        Bitmap obtain = this.bitmapProvider.obtain(this.downsampledWidth, this.downsampledHeight, (bool == null || bool.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.bitmapConfig);
        obtain.setHasAlpha(true);
        return obtain;
    }
}
