package com.mob.tools.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.opengl.GLES10;
import android.text.TextUtils;
import android.view.View;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.baidubce.http.Headers;
import com.mob.tools.MobLog;
import com.mob.tools.network.HttpConnection;
import com.mob.tools.network.HttpResponseCallback;
import com.mob.tools.network.NetworkHelper;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BitmapHelper {
    private static final int DEFAULT_MAX_BITMAP_DIMENSION = 2048;
    private static int maxBitmapHeight;
    private static int maxBitmapWidth;

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int max = Math.max(iArr[0], 2048);
        maxBitmapWidth = max;
        maxBitmapHeight = max;
    }

    public static Bitmap getBitmapByCompressSize(String str, int i, int i2) throws Throwable {
        int i3;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i4 = options.outWidth;
        int i5 = options.outHeight;
        if (i <= 1 || i2 <= 1) {
            i3 = 1;
        } else {
            float f = 1.0f;
            float min = (((float) Math.min(i4, i5)) * 1.0f) / ((float) Math.min(i, i2));
            float max = (((float) Math.max(i4, i5)) * 1.0f) / ((float) Math.max(i, i2));
            float f2 = (float) (i4 / i5);
            if (f2 <= 2.0f && ((double) f2) >= 0.5d) {
                float min2 = Math.min(min, max);
                while (true) {
                    float f3 = f * 2.0f;
                    if (f3 > min2) {
                        break;
                    }
                    f = f3;
                }
            } else {
                while (true) {
                    float f4 = f * 2.0f;
                    if (f4 > min) {
                        break;
                    }
                    f = f4;
                }
            }
            i3 = (int) f;
        }
        if (i3 < 1) {
            i3 = 1;
        }
        while (true) {
            if (i4 / i3 > maxBitmapWidth || i5 / i3 > maxBitmapHeight) {
                i3++;
            } else {
                BitmapFactory.Options options2 = new BitmapFactory.Options();
                options2.inPreferredConfig = Bitmap.Config.RGB_565;
                options2.inSampleSize = i3;
                return BitmapFactory.decodeFile(str, options2);
            }
        }
    }

    public static Bitmap getBitmapByCompressQuality(String str, int i, int i2, int i3, long j) throws Throwable {
        Bitmap bitmapByCompressSize = getBitmapByCompressSize(str, i, i2);
        if (i3 < 10 || i3 > 100) {
            i3 = 100;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bitmap.CompressFormat bmpFormat = getBmpFormat(str);
        bitmapByCompressSize.compress(bmpFormat, i3, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (j < 10240) {
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            try {
                byteArrayOutputStream.close();
            } catch (Throwable unused) {
            }
            return decodeByteArray;
        }
        while (((long) byteArray.length) > j && i3 >= 11) {
            byteArrayOutputStream.reset();
            i3 -= 6;
            bitmapByCompressSize.compress(bmpFormat, i3, byteArrayOutputStream);
            byteArray = byteArrayOutputStream.toByteArray();
        }
        if (i3 != 100) {
            bitmapByCompressSize = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        }
        try {
            byteArrayOutputStream.close();
        } catch (Throwable unused2) {
        }
        return bitmapByCompressSize;
    }

    public static String saveBitmapByCompress(String str, int i, int i2, int i3) throws Throwable {
        Bitmap bitmapByCompressSize = getBitmapByCompressSize(str, i, i2);
        int i4 = 10;
        if (i3 > 100) {
            i4 = 100;
        } else if (i3 >= 10) {
            i4 = i3;
        }
        Bitmap.CompressFormat bmpFormat = getBmpFormat(str);
        String str2 = bmpFormat == Bitmap.CompressFormat.PNG ? ".png" : ".jpg";
        String parent = new File(str).getParent();
        File file = new File(parent, String.valueOf(System.currentTimeMillis()) + str2);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        bitmapByCompressSize.compress(bmpFormat, i4, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return file.getAbsolutePath();
    }

    public static Bitmap getBitmap(String str, int i) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return getBitmap(new File(str), i);
    }

    public static Bitmap getBitmap(File file, int i) throws Throwable {
        if (file == null || !file.exists()) {
            return null;
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        Bitmap bitmap = getBitmap((InputStream) fileInputStream, i);
        fileInputStream.close();
        return bitmap;
    }

    public static Bitmap getBitmap(InputStream inputStream, int i) {
        if (inputStream == null) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = i;
        return BitmapFactory.decodeStream(inputStream, (Rect) null, options);
    }

    public static Bitmap getBitmap(String str) throws Throwable {
        return getBitmap(str, 1);
    }

    public static Bitmap getBitmap(Context context, String str) throws Throwable {
        return getBitmap(downloadBitmap(context, str));
    }

    public static String downloadBitmap(Context context, final String str) throws Throwable {
        final String cachePath = ResHelper.getCachePath(context, "images");
        File file = new File(cachePath, Data.MD5(str));
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        final HashMap hashMap = new HashMap();
        new NetworkHelper().rawGet(str, (HttpResponseCallback) new HttpResponseCallback() {
            public void onResponse(HttpConnection httpConnection) throws Throwable {
                int responseCode = httpConnection.getResponseCode();
                if (responseCode == 200) {
                    String access$000 = BitmapHelper.getFileName(httpConnection, str);
                    File file = new File(cachePath, access$000);
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    if (file.exists()) {
                        file.delete();
                    }
                    try {
                        Bitmap bitmap = BitmapHelper.getBitmap((InputStream) new FilterInputStream(httpConnection.getInputStream()) {
                            public long skip(long j) throws IOException {
                                long j2 = 0;
                                while (j2 < j) {
                                    long skip = this.in.skip(j - j2);
                                    if (skip == 0) {
                                        break;
                                    }
                                    j2 += skip;
                                }
                                return j2;
                            }
                        }, 1);
                        if (bitmap != null && !bitmap.isRecycled()) {
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            if (access$000.toLowerCase().endsWith(".gif") || access$000.toLowerCase().endsWith(".png")) {
                                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                            } else {
                                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                            }
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            hashMap.put("bitmap", file.getAbsolutePath());
                        }
                    } catch (Throwable th) {
                        if (file.exists()) {
                            file.delete();
                        }
                        throw th;
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream(), Charset.forName("utf-8")));
                    for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (sb.length() > 0) {
                            sb.append(10);
                        }
                        sb.append(readLine);
                    }
                    bufferedReader.close();
                    HashMap hashMap = new HashMap();
                    hashMap.put("error", sb.toString());
                    hashMap.put("status", Integer.valueOf(responseCode));
                    throw new Throwable(new Hashon().fromHashMap(hashMap));
                }
            }
        }, (NetworkHelper.NetworkTimeOut) null);
        return (String) hashMap.get("bitmap");
    }

    /* access modifiers changed from: private */
    public static String getFileName(HttpConnection httpConnection, String str) throws Throwable {
        String str2;
        List list;
        String str3;
        int lastIndexOf;
        List list2;
        Map<String, List<String>> headerFields = httpConnection.getHeaderFields();
        String str4 = null;
        if (headerFields == null || (list2 = headerFields.get(Headers.CONTENT_DISPOSITION)) == null || list2.size() <= 0) {
            str2 = null;
        } else {
            str2 = null;
            for (String str5 : ((String) list2.get(0)).split(";")) {
                if (str5.trim().startsWith("filename")) {
                    str2 = str5.split("=")[1];
                    if (str2.startsWith("\"") && str2.endsWith("\"")) {
                        str2 = str2.substring(1, str2.length() - 1);
                    }
                }
            }
        }
        if (str2 != null) {
            return str2;
        }
        String MD5 = Data.MD5(str);
        if (headerFields == null || (list = headerFields.get(Headers.CONTENT_TYPE)) == null || list.size() <= 0) {
            return MD5;
        }
        String str6 = (String) list.get(0);
        if (str6 == null) {
            str3 = "";
        } else {
            str3 = str6.trim();
        }
        if (str3.startsWith("image/")) {
            String substring = str3.substring(6);
            StringBuilder sb = new StringBuilder();
            sb.append(MD5);
            sb.append(".");
            if ("jpeg".equals(substring)) {
                substring = "jpg";
            }
            sb.append(substring);
            return sb.toString();
        }
        int lastIndexOf2 = str.lastIndexOf(47);
        if (lastIndexOf2 > 0) {
            str4 = str.substring(lastIndexOf2 + 1);
        }
        if (str4 == null || str4.length() <= 0 || (lastIndexOf = str4.lastIndexOf(46)) <= 0 || str4.length() - lastIndexOf >= 10) {
            return MD5;
        }
        return MD5 + str4.substring(lastIndexOf);
    }

    public static String saveViewToImage(View view) throws Throwable {
        if (view == null) {
            return null;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        return saveViewToImage(view, width, height);
    }

    public static String saveViewToImage(View view, int i, int i2) throws Throwable {
        Bitmap captureView = captureView(view, i, i2);
        if (captureView == null || captureView.isRecycled()) {
            return null;
        }
        String cachePath = ResHelper.getCachePath(view.getContext(), "screenshot");
        File file = new File(cachePath, String.valueOf(System.currentTimeMillis()) + ".jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        captureView.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return file.getAbsolutePath();
    }

    public static Bitmap captureView(View view, int i, int i2) throws Throwable {
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    public static Bitmap blur(Bitmap bitmap, int i, int i2) {
        float f = (float) i2;
        Bitmap createBitmap = Bitmap.createBitmap((int) ((((float) bitmap.getWidth()) / f) + 0.5f), (int) ((((float) bitmap.getHeight()) / f) + 0.5f), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        float f2 = 1.0f / f;
        canvas.scale(f2, f2);
        Paint paint = new Paint();
        paint.setFlags(2);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        blur(createBitmap, (int) ((((float) i) / f) + 0.5f), true);
        return createBitmap;
    }

    private static Bitmap blur(Bitmap bitmap, int i, boolean z) {
        Bitmap bitmap2;
        int[] iArr;
        int i2 = i;
        if (z) {
            bitmap2 = bitmap;
        } else {
            bitmap2 = bitmap.copy(bitmap.getConfig(), true);
        }
        if (i2 < 1) {
            return null;
        }
        int width = bitmap2.getWidth();
        int height = bitmap2.getHeight();
        int i3 = width * height;
        int[] iArr2 = new int[i3];
        bitmap2.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i4 = width - 1;
        int i5 = height - 1;
        int i6 = i2 + i2 + 1;
        int[] iArr3 = new int[i3];
        int[] iArr4 = new int[i3];
        int[] iArr5 = new int[i3];
        int[] iArr6 = new int[Math.max(width, height)];
        int i7 = (i6 + 1) >> 1;
        int i8 = i7 * i7;
        int i9 = i8 * 256;
        int[] iArr7 = new int[i9];
        for (int i10 = 0; i10 < i9; i10++) {
            iArr7[i10] = i10 / i8;
        }
        int[][] iArr8 = (int[][]) Array.newInstance(int.class, new int[]{i6, 3});
        int i11 = i2 + 1;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        while (i12 < height) {
            Bitmap bitmap3 = bitmap2;
            int i15 = -i2;
            int i16 = 0;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            while (i15 <= i2) {
                int i25 = i5;
                int i26 = height;
                int i27 = iArr2[i13 + Math.min(i4, Math.max(i15, 0))];
                int[] iArr9 = iArr8[i15 + i2];
                iArr9[0] = (i27 & 16711680) >> 16;
                iArr9[1] = (i27 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr9[2] = i27 & 255;
                int abs = i11 - Math.abs(i15);
                i16 += iArr9[0] * abs;
                i17 += iArr9[1] * abs;
                i18 += iArr9[2] * abs;
                if (i15 > 0) {
                    i22 += iArr9[0];
                    i23 += iArr9[1];
                    i24 += iArr9[2];
                } else {
                    i19 += iArr9[0];
                    i20 += iArr9[1];
                    i21 += iArr9[2];
                }
                i15++;
                height = i26;
                i5 = i25;
            }
            int i28 = i5;
            int i29 = height;
            int i30 = i2;
            int i31 = 0;
            while (i31 < width) {
                iArr3[i13] = iArr7[i16];
                iArr4[i13] = iArr7[i17];
                iArr5[i13] = iArr7[i18];
                int i32 = i16 - i19;
                int i33 = i17 - i20;
                int i34 = i18 - i21;
                int[] iArr10 = iArr8[((i30 - i2) + i6) % i6];
                int i35 = i19 - iArr10[0];
                int i36 = i20 - iArr10[1];
                int i37 = i21 - iArr10[2];
                if (i12 == 0) {
                    iArr = iArr7;
                    iArr6[i31] = Math.min(i31 + i2 + 1, i4);
                } else {
                    iArr = iArr7;
                }
                int i38 = iArr2[i14 + iArr6[i31]];
                iArr10[0] = (i38 & 16711680) >> 16;
                iArr10[1] = (i38 & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8;
                iArr10[2] = i38 & 255;
                int i39 = i22 + iArr10[0];
                int i40 = i23 + iArr10[1];
                int i41 = i24 + iArr10[2];
                i16 = i32 + i39;
                i17 = i33 + i40;
                i18 = i34 + i41;
                i30 = (i30 + 1) % i6;
                int[] iArr11 = iArr8[i30 % i6];
                i19 = i35 + iArr11[0];
                i20 = i36 + iArr11[1];
                i21 = i37 + iArr11[2];
                i22 = i39 - iArr11[0];
                i23 = i40 - iArr11[1];
                i24 = i41 - iArr11[2];
                i13++;
                i31++;
                iArr7 = iArr;
            }
            int[] iArr12 = iArr7;
            i14 += width;
            i12++;
            bitmap2 = bitmap3;
            height = i29;
            i5 = i28;
        }
        Bitmap bitmap4 = bitmap2;
        int i42 = i5;
        int i43 = height;
        int[] iArr13 = iArr7;
        int i44 = 0;
        while (i44 < width) {
            int i45 = -i2;
            int i46 = i45 * width;
            int i47 = 0;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            int i53 = 0;
            int i54 = 0;
            int i55 = 0;
            while (i45 <= i2) {
                int[] iArr14 = iArr6;
                int max = Math.max(0, i46) + i44;
                int[] iArr15 = iArr8[i45 + i2];
                iArr15[0] = iArr3[max];
                iArr15[1] = iArr4[max];
                iArr15[2] = iArr5[max];
                int abs2 = i11 - Math.abs(i45);
                i47 += iArr3[max] * abs2;
                i48 += iArr4[max] * abs2;
                i49 += iArr5[max] * abs2;
                if (i45 > 0) {
                    i53 += iArr15[0];
                    i54 += iArr15[1];
                    i55 += iArr15[2];
                } else {
                    i50 += iArr15[0];
                    i51 += iArr15[1];
                    i52 += iArr15[2];
                }
                int i56 = i42;
                if (i45 < i56) {
                    i46 += width;
                }
                i45++;
                i42 = i56;
                iArr6 = iArr14;
            }
            int[] iArr16 = iArr6;
            int i57 = i42;
            int i58 = i44;
            int i59 = i54;
            int i60 = i55;
            int i61 = 0;
            int i62 = i2;
            int i63 = i53;
            int i64 = i52;
            int i65 = i51;
            int i66 = i50;
            int i67 = i49;
            int i68 = i48;
            int i69 = i47;
            int i70 = i43;
            while (i61 < i70) {
                iArr2[i58] = (iArr2[i58] & ViewCompat.MEASURED_STATE_MASK) | (iArr13[i69] << 16) | (iArr13[i68] << 8) | iArr13[i67];
                int i71 = i69 - i66;
                int i72 = i68 - i65;
                int i73 = i67 - i64;
                int[] iArr17 = iArr8[((i62 - i2) + i6) % i6];
                int i74 = i66 - iArr17[0];
                int i75 = i65 - iArr17[1];
                int i76 = i64 - iArr17[2];
                if (i44 == 0) {
                    iArr16[i61] = Math.min(i61 + i11, i57) * width;
                }
                int i77 = iArr16[i61] + i44;
                iArr17[0] = iArr3[i77];
                iArr17[1] = iArr4[i77];
                iArr17[2] = iArr5[i77];
                int i78 = i63 + iArr17[0];
                int i79 = i59 + iArr17[1];
                int i80 = i60 + iArr17[2];
                i69 = i71 + i78;
                i68 = i72 + i79;
                i67 = i73 + i80;
                i62 = (i62 + 1) % i6;
                int[] iArr18 = iArr8[i62];
                i66 = i74 + iArr18[0];
                i65 = i75 + iArr18[1];
                i64 = i76 + iArr18[2];
                i63 = i78 - iArr18[0];
                i59 = i79 - iArr18[1];
                i60 = i80 - iArr18[2];
                i58 += width;
                i61++;
                i2 = i;
            }
            i44++;
            i2 = i;
            i42 = i57;
            i43 = i70;
            iArr6 = iArr16;
        }
        bitmap4.setPixels(iArr2, 0, width, 0, 0, width, i43);
        return bitmap4;
    }

    public static Bitmap roundBitmap(Bitmap bitmap, int i, int i2, float f, float f2, float f3, float f4) throws Throwable {
        Bitmap bitmap2;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Rect rect = new Rect(0, 0, width, height);
        if (width == i && height == i2) {
            bitmap2 = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        } else {
            bitmap2 = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap2);
        Paint paint = new Paint();
        Rect rect2 = new Rect(0, 0, i, i2);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        float[] fArr = {f, f, f2, f2, f3, f3, f4, f4};
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(fArr, new RectF(0.0f, 0.0f, 0.0f, 0.0f), fArr));
        shapeDrawable.setBounds(rect2);
        shapeDrawable.draw(canvas);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return bitmap2;
    }

    public static int[] fixRect(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[2];
        if (((float) iArr[0]) / ((float) iArr[1]) > ((float) iArr2[0]) / ((float) iArr2[1])) {
            iArr3[0] = iArr2[0];
            iArr3[1] = (int) (((((float) iArr[1]) * ((float) iArr2[0])) / ((float) iArr[0])) + 0.5f);
        } else {
            iArr3[1] = iArr2[1];
            iArr3[0] = (int) (((((float) iArr[0]) * ((float) iArr2[1])) / ((float) iArr[1])) + 0.5f);
        }
        return iArr3;
    }

    public static int[] fixRect_2(int[] iArr, int[] iArr2) {
        int[] iArr3 = new int[2];
        if (((float) iArr[0]) / ((float) iArr[1]) > ((float) iArr2[0]) / ((float) iArr2[1])) {
            iArr3[1] = iArr2[1];
            iArr3[0] = (int) (((((float) iArr[0]) * ((float) iArr2[1])) / ((float) iArr[1])) + 0.5f);
        } else {
            iArr3[0] = iArr2[0];
            iArr3[1] = (int) (((((float) iArr[1]) * ((float) iArr2[0])) / ((float) iArr[0])) + 0.5f);
        }
        return iArr3;
    }

    public static String saveBitmap(Context context, Bitmap bitmap, Bitmap.CompressFormat compressFormat, int i) throws Throwable {
        String cachePath = ResHelper.getCachePath(context, "images");
        String str = compressFormat == Bitmap.CompressFormat.PNG ? ".png" : ".jpg";
        File file = new File(cachePath, String.valueOf(System.currentTimeMillis()) + str);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        bitmap.compress(compressFormat, i, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        return file.getAbsolutePath();
    }

    public static String saveBitmap(Context context, Bitmap bitmap) throws Throwable {
        return saveBitmap(context, bitmap, Bitmap.CompressFormat.JPEG, 80);
    }

    public static Bitmap.CompressFormat getBmpFormat(byte[] bArr) {
        String mime = getMime(bArr);
        Bitmap.CompressFormat compressFormat = Bitmap.CompressFormat.JPEG;
        if (mime != null) {
            return (mime.endsWith("png") || mime.endsWith("gif")) ? Bitmap.CompressFormat.PNG : compressFormat;
        }
        return compressFormat;
    }

    public static Bitmap.CompressFormat getBmpFormat(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.endsWith("png") || lowerCase.endsWith("gif")) {
            return Bitmap.CompressFormat.PNG;
        }
        if (lowerCase.endsWith("jpg") || lowerCase.endsWith("jpeg") || lowerCase.endsWith("bmp") || lowerCase.endsWith("tif")) {
            return Bitmap.CompressFormat.JPEG;
        }
        String mime = getMime(str);
        if (mime.endsWith("png") || mime.endsWith("gif")) {
            return Bitmap.CompressFormat.PNG;
        }
        return Bitmap.CompressFormat.JPEG;
    }

    public static String getMime(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[8];
            fileInputStream.read(bArr);
            fileInputStream.close();
            return getMime(bArr);
        } catch (Exception e) {
            MobLog.getInstance().mo29787w((Throwable) e);
            return "";
        }
    }

    private static String getMime(byte[] bArr) {
        byte[] bArr2 = {-1, -40, -1, -31};
        if (bytesStartWith(bArr, new byte[]{-1, -40, -1, -32}) || bytesStartWith(bArr, bArr2)) {
            return "jpg";
        }
        if (bytesStartWith(bArr, new byte[]{-119, 80, 78, 71})) {
            return "png";
        }
        if (bytesStartWith(bArr, "GIF".getBytes())) {
            return "gif";
        }
        if (bytesStartWith(bArr, "BM".getBytes())) {
            return "bmp";
        }
        return (bytesStartWith(bArr, new byte[]{73, 73, 42}) || bytesStartWith(bArr, new byte[]{77, 77, 42})) ? "tif" : "";
    }

    private static boolean bytesStartWith(byte[] bArr, byte[] bArr2) {
        if (bArr == bArr2) {
            return true;
        }
        if (bArr == null || bArr2 == null || bArr.length < bArr2.length) {
            return false;
        }
        for (int i = 0; i < bArr2.length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static Bitmap cropBitmap(Bitmap bitmap, int i, int i2, int i3, int i4) throws Throwable {
        int width = (bitmap.getWidth() - i) - i3;
        int height = (bitmap.getHeight() - i2) - i4;
        if (width == bitmap.getWidth() && height == bitmap.getHeight()) {
            return bitmap;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmap, (float) (-i), (float) (-i2), new Paint());
        return createBitmap;
    }

    public static boolean isBlackBitmap(Bitmap bitmap) throws Throwable {
        if (bitmap == null || bitmap.isRecycled()) {
            return true;
        }
        int[] iArr = new int[(bitmap.getWidth() * bitmap.getHeight())];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= iArr.length) {
                break;
            } else if ((iArr[i] & ViewCompat.MEASURED_SIZE_MASK) != 0) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        return !z;
    }

    public static int mixAlpha(int i, int i2) {
        int i3 = i >>> 24;
        int i4 = 255 - i3;
        return ((((((i & 16711680) >>> 16) * i3) + (((16711680 & i2) >>> 16) * i4)) / 255) << 16) | ViewCompat.MEASURED_STATE_MASK | ((((((i & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >>> 8) * i3) + (((65280 & i2) >>> 8) * i4)) / 255) << 8) | (((i3 * (i & 255)) + (i4 * (i2 & 255))) / 255);
    }

    public static Bitmap scaleBitmapByHeight(Context context, int i, int i2) throws Throwable {
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
        boolean z = i2 != decodeResource.getHeight();
        Bitmap scaleBitmapByHeight = scaleBitmapByHeight(decodeResource, i2);
        if (z) {
            decodeResource.recycle();
        }
        return scaleBitmapByHeight;
    }

    public static Bitmap scaleBitmapByHeight(Bitmap bitmap, int i) throws Throwable {
        return Bitmap.createScaledBitmap(bitmap, (bitmap.getWidth() * i) / bitmap.getHeight(), i, true);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, int i) {
        return compressByQuality(bitmap, i, false);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, int i, boolean z) {
        if (isEmptyBitmap(bitmap)) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, long j) {
        return compressByQuality(bitmap, j, false);
    }

    public static Bitmap compressByQuality(Bitmap bitmap, long j, boolean z) {
        byte[] bArr;
        if (isEmptyBitmap(bitmap) || j <= 0) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        if (((long) byteArrayOutputStream.size()) <= j) {
            bArr = byteArrayOutputStream.toByteArray();
        } else {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 0, byteArrayOutputStream);
            if (((long) byteArrayOutputStream.size()) >= j) {
                bArr = byteArrayOutputStream.toByteArray();
            } else {
                int i = 0;
                int i2 = 100;
                int i3 = 0;
                while (i < i2) {
                    i3 = (i + i2) / 2;
                    byteArrayOutputStream.reset();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, i3, byteArrayOutputStream);
                    long size = (long) byteArrayOutputStream.size();
                    if (size == j) {
                        break;
                    } else if (size > j) {
                        i2 = i3 - 1;
                    } else {
                        i = i3 + 1;
                    }
                }
                if (i2 == i3 - 1) {
                    byteArrayOutputStream.reset();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
                }
                bArr = byteArrayOutputStream.toByteArray();
            }
        }
        if (z && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    private static boolean isEmptyBitmap(Bitmap bitmap) {
        return bitmap == null || bitmap.getWidth() == 0 || bitmap.getHeight() == 0;
    }

    public static boolean save(Bitmap bitmap, String str, Bitmap.CompressFormat compressFormat) {
        return save(bitmap, FileUtils.getFileByPath(str), compressFormat, false);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0037, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0038, code lost:
        r0 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003a, code lost:
        r5 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x003b, code lost:
        r0 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0037 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x001c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean save(android.graphics.Bitmap r5, java.io.File r6, android.graphics.Bitmap.CompressFormat r7, boolean r8) {
        /*
            boolean r0 = isEmptyBitmap(r5)
            r1 = 0
            if (r0 != 0) goto L_0x0058
            boolean r0 = com.mob.tools.utils.FileUtils.createFileByDeleteOldFile(r6)
            if (r0 != 0) goto L_0x000e
            goto L_0x0058
        L_0x000e:
            r0 = 0
            r2 = 1
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ Throwable -> 0x003f }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ Throwable -> 0x003f }
            r4.<init>(r6)     // Catch:{ Throwable -> 0x003f }
            r3.<init>(r4)     // Catch:{ Throwable -> 0x003f }
            r6 = 100
            boolean r6 = r5.compress(r7, r6, r3)     // Catch:{ Throwable -> 0x003a, all -> 0x0037 }
            if (r8 == 0) goto L_0x002f
            boolean r7 = r5.isRecycled()     // Catch:{ Throwable -> 0x002c, all -> 0x0037 }
            if (r7 != 0) goto L_0x002f
            r5.recycle()     // Catch:{ Throwable -> 0x002c, all -> 0x0037 }
            goto L_0x002f
        L_0x002c:
            r5 = move-exception
            r0 = r3
            goto L_0x0041
        L_0x002f:
            java.io.Closeable[] r5 = new java.io.Closeable[r2]
            r5[r1] = r3
            com.mob.tools.utils.FileUtils.closeIO(r5)
            goto L_0x004f
        L_0x0037:
            r5 = move-exception
            r0 = r3
            goto L_0x0050
        L_0x003a:
            r5 = move-exception
            r0 = r3
            goto L_0x0040
        L_0x003d:
            r5 = move-exception
            goto L_0x0050
        L_0x003f:
            r5 = move-exception
        L_0x0040:
            r6 = 0
        L_0x0041:
            com.mob.tools.log.NLog r7 = com.mob.tools.MobLog.getInstance()     // Catch:{ all -> 0x003d }
            r7.mo29769d(r5)     // Catch:{ all -> 0x003d }
            java.io.Closeable[] r5 = new java.io.Closeable[r2]
            r5[r1] = r0
            com.mob.tools.utils.FileUtils.closeIO(r5)
        L_0x004f:
            return r6
        L_0x0050:
            java.io.Closeable[] r6 = new java.io.Closeable[r2]
            r6[r1] = r0
            com.mob.tools.utils.FileUtils.closeIO(r6)
            throw r5
        L_0x0058:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.BitmapHelper.save(android.graphics.Bitmap, java.io.File, android.graphics.Bitmap$CompressFormat, boolean):boolean");
    }
}
