package p005cn.sharesdk.framework.loopshare.watermark;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Pair;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.mob.tools.log.NLog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p005cn.sharesdk.onekeyshare.OnekeyShare;

/* renamed from: cn.sharesdk.framework.loopshare.watermark.FirstPic */
public class FirstPic {

    /* renamed from: cn.sharesdk.framework.loopshare.watermark.FirstPic$AnalyzeCallback */
    public interface AnalyzeCallback {
        void onAnalyzeFailed();

        void onAnalyzeSuccess(Bitmap bitmap, String str);
    }

    /* renamed from: a */
    public static void m473a(final Context context, final ReadQrImageListener readQrImageListener) {
        if (Build.VERSION.SDK_INT > 24) {
            if (context == null) {
                if (readQrImageListener != null) {
                    readQrImageListener.onFailed(new Throwable("Please set Contenxt"), -4);
                } else {
                    return;
                }
            }
            new Thread() {
                /* JADX WARNING: Removed duplicated region for block: B:11:0x0021 A[Catch:{ Throwable -> 0x008d }] */
                /* JADX WARNING: Removed duplicated region for block: B:18:0x007c A[Catch:{ Throwable -> 0x008d }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void run() {
                    /*
                        r9 = this;
                        java.lang.String r0 = "ShareSDK"
                        r1 = 0
                        r2 = 1
                        boolean r3 = p005cn.sharesdk.framework.loopshare.watermark.C0770e.m495b()     // Catch:{ Throwable -> 0x008d }
                        if (r3 != 0) goto L_0x0018
                        boolean r3 = p005cn.sharesdk.framework.loopshare.watermark.C0770e.m492a()     // Catch:{ Throwable -> 0x008d }
                        if (r3 == 0) goto L_0x0011
                        goto L_0x0018
                    L_0x0011:
                        android.content.Context r3 = r2     // Catch:{ Throwable -> 0x008d }
                        android.util.Pair r3 = p005cn.sharesdk.framework.loopshare.watermark.FirstPic.m476b(r3)     // Catch:{ Throwable -> 0x008d }
                        goto L_0x001e
                    L_0x0018:
                        android.content.Context r3 = r2     // Catch:{ Throwable -> 0x008d }
                        android.util.Pair r3 = p005cn.sharesdk.framework.loopshare.watermark.FirstPic.m470a((android.content.Context) r3)     // Catch:{ Throwable -> 0x008d }
                    L_0x001e:
                        r4 = -1
                        if (r3 == 0) goto L_0x007c
                        java.lang.Object r3 = r3.second     // Catch:{ Throwable -> 0x008d }
                        java.lang.String r3 = (java.lang.String) r3     // Catch:{ Throwable -> 0x008d }
                        com.mob.tools.log.NLog r5 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x008d }
                        java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x008d }
                        java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x008d }
                        r7.<init>()     // Catch:{ Throwable -> 0x008d }
                        java.lang.String r8 = " 相册第一张图片的路径为: "
                        r7.append(r8)     // Catch:{ Throwable -> 0x008d }
                        r7.append(r3)     // Catch:{ Throwable -> 0x008d }
                        java.lang.String r7 = r7.toString()     // Catch:{ Throwable -> 0x008d }
                        r6[r1] = r7     // Catch:{ Throwable -> 0x008d }
                        r5.mo29768d(r0, r6)     // Catch:{ Throwable -> 0x008d }
                        boolean r5 = android.text.TextUtils.isEmpty(r3)     // Catch:{ Throwable -> 0x008d }
                        if (r5 != 0) goto L_0x004f
                        android.content.Context r4 = r2     // Catch:{ Throwable -> 0x008d }
                        cn.sharesdk.framework.loopshare.watermark.ReadQrImageListener r5 = r3     // Catch:{ Throwable -> 0x008d }
                        p005cn.sharesdk.framework.loopshare.watermark.FirstPic.m474a((android.content.Context) r4, (java.lang.String) r3, (p005cn.sharesdk.framework.loopshare.watermark.ReadQrImageListener) r5)     // Catch:{ Throwable -> 0x008d }
                        goto L_0x005f
                    L_0x004f:
                        cn.sharesdk.framework.loopshare.watermark.ReadQrImageListener r5 = r3     // Catch:{ Throwable -> 0x008d }
                        if (r5 == 0) goto L_0x005f
                        cn.sharesdk.framework.loopshare.watermark.ReadQrImageListener r5 = r3     // Catch:{ Throwable -> 0x008d }
                        java.lang.Throwable r6 = new java.lang.Throwable     // Catch:{ Throwable -> 0x008d }
                        java.lang.String r7 = "读取相册图片失败"
                        r6.<init>(r7)     // Catch:{ Throwable -> 0x008d }
                        r5.onFailed(r6, r4)     // Catch:{ Throwable -> 0x008d }
                    L_0x005f:
                        com.mob.tools.log.NLog r4 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()     // Catch:{ Throwable -> 0x008d }
                        java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x008d }
                        java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x008d }
                        r6.<init>()     // Catch:{ Throwable -> 0x008d }
                        java.lang.String r7 = "pairPath: "
                        r6.append(r7)     // Catch:{ Throwable -> 0x008d }
                        r6.append(r3)     // Catch:{ Throwable -> 0x008d }
                        java.lang.String r3 = r6.toString()     // Catch:{ Throwable -> 0x008d }
                        r5[r1] = r3     // Catch:{ Throwable -> 0x008d }
                        r4.mo29768d(r0, r5)     // Catch:{ Throwable -> 0x008d }
                        goto L_0x00b2
                    L_0x007c:
                        cn.sharesdk.framework.loopshare.watermark.ReadQrImageListener r3 = r3     // Catch:{ Throwable -> 0x008d }
                        if (r3 == 0) goto L_0x00b2
                        cn.sharesdk.framework.loopshare.watermark.ReadQrImageListener r3 = r3     // Catch:{ Throwable -> 0x008d }
                        java.lang.Throwable r5 = new java.lang.Throwable     // Catch:{ Throwable -> 0x008d }
                        java.lang.String r6 = "读取截屏相册图片失败，请检查是否有图片"
                        r5.<init>(r6)     // Catch:{ Throwable -> 0x008d }
                        r3.onFailed(r5, r4)     // Catch:{ Throwable -> 0x008d }
                        goto L_0x00b2
                    L_0x008d:
                        r3 = move-exception
                        com.mob.tools.log.NLog r4 = p005cn.sharesdk.framework.utils.SSDKLog.m645b()
                        java.lang.Object[] r2 = new java.lang.Object[r2]
                        java.lang.StringBuilder r5 = new java.lang.StringBuilder
                        r5.<init>()
                        java.lang.String r6 = "解析二维码异常 "
                        r5.append(r6)
                        r5.append(r3)
                        java.lang.String r5 = r5.toString()
                        r2[r1] = r5
                        r4.mo29771e(r0, r2)
                        cn.sharesdk.framework.loopshare.watermark.ReadQrImageListener r0 = r3
                        if (r0 == 0) goto L_0x00b2
                        r1 = -3
                        r0.onFailed(r3, r1)
                    L_0x00b2:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.loopshare.watermark.FirstPic.C07621.run():void");
                }
            }.start();
        } else if (readQrImageListener != null) {
            readQrImageListener.onFailed(new Throwable("The minimum version supported for this feature is 7.0"), -2);
        }
    }

    /* renamed from: a */
    public static Pair<Long, String> m470a(Context context) {
        String str;
        try {
            if (C0770e.m495b()) {
                str = m477b();
            } else {
                str = C0770e.m492a() ? m471a() : null;
            }
            String[] strArr = {"_data", "date_modified"};
            String[] strArr2 = {m472a(str)};
            Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, "bucket_id = ?", strArr2, "date_modified DESC");
            if (query.moveToFirst()) {
                new Pair(Long.valueOf(query.getLong(query.getColumnIndex("date_modified"))), query.getString(query.getColumnIndex("_data")));
            }
            Cursor query2 = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, "bucket_id = ?", strArr2, "date_modified DESC");
            Pair<Long, String> pair = query2.moveToFirst() ? new Pair<>(Long.valueOf(query2.getLong(query2.getColumnIndex("date_modified"))), query2.getString(query2.getColumnIndex("_data"))) : null;
            if (!query2.isClosed()) {
                query2.close();
            }
            if (pair != null) {
                return pair;
            }
            return null;
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29786w("getOVLatestPhoto catch: " + th);
            return null;
        }
    }

    /* renamed from: b */
    public static Pair<Long, String> m476b(Context context) {
        try {
            String c = m478c();
            String[] strArr = {"_data", "date_modified"};
            String[] strArr2 = {m472a(Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera")};
            String[] strArr3 = {m472a(c)};
            Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, "bucket_id = ?", strArr2, "date_modified DESC");
            Pair<Long, String> pair = query.moveToFirst() ? new Pair<>(Long.valueOf(query.getLong(query.getColumnIndex("date_modified"))), query.getString(query.getColumnIndex("_data"))) : null;
            Cursor query2 = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, strArr, "bucket_id = ?", strArr3, "date_modified DESC");
            Pair<Long, String> pair2 = query2.moveToFirst() ? new Pair<>(Long.valueOf(query2.getLong(query2.getColumnIndex("date_modified"))), query2.getString(query2.getColumnIndex("_data"))) : null;
            if (!query2.isClosed()) {
                query2.close();
            }
            if (pair != null && pair2 != null) {
                return ((Long) pair.first).longValue() > ((Long) pair2.first).longValue() ? pair : pair2;
            }
            if (pair != null && pair2 == null) {
                return pair;
            }
            if (pair != null || pair2 == null) {
                return null;
            }
            return pair2;
        } catch (Throwable th) {
            SSDKLog.m645b().mo29786w("getLatestPhoto catch: " + th);
            return null;
        }
    }

    /* renamed from: a */
    private static String m472a(String str) {
        return String.valueOf(str.toLowerCase().hashCode());
    }

    /* renamed from: a */
    private static String m471a() {
        return Environment.getExternalStorageDirectory().toString() + "/Pictures/Screenshots";
    }

    /* renamed from: b */
    private static String m477b() {
        return Environment.getExternalStorageDirectory().toString() + "/Screenshot";
    }

    /* renamed from: c */
    private static String m478c() {
        String str = Environment.getExternalStorageDirectory().toString() + "/DCIM/Screenshots";
        if (new File(str).exists()) {
            return str;
        }
        return Environment.getExternalStorageDirectory().toString() + "/Pictures/Screenshots";
    }

    /* renamed from: a */
    private static Bitmap m467a(Context context, Uri uri, ReadQrImageListener readQrImageListener) {
        try {
            ParcelFileDescriptor openFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r");
            Bitmap decodeFileDescriptor = BitmapFactory.decodeFileDescriptor(openFileDescriptor.getFileDescriptor());
            openFileDescriptor.close();
            return decodeFileDescriptor;
        } catch (IOException e) {
            if (readQrImageListener == null) {
                return null;
            }
            SSDKLog.m645b().mo29771e(OnekeyShare.SHARESDK_TAG, " getBitmapFromPath catch ");
            readQrImageListener.onFailed(e, -1);
            return null;
        }
    }

    /* renamed from: a */
    public static Uri m469a(Context context, String str) {
        Cursor query = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, (String) null);
        if (query != null && query.moveToFirst()) {
            int i = query.getInt(query.getColumnIndex("_id"));
            Uri parse = Uri.parse("content://media/external/images/media");
            return Uri.withAppendedPath(parse, "" + i);
        } else if (!new File(str).exists()) {
            return null;
        } else {
            ContentValues contentValues = new ContentValues();
            contentValues.put("_data", str);
            return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        }
    }

    /* renamed from: a */
    public static void m474a(Context context, String str, ReadQrImageListener readQrImageListener) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap a = m467a(context, m469a(context, str), readQrImageListener);
        options.inJustDecodeBounds = false;
        int i = (int) (((float) options.outHeight) / 400.0f);
        if (i <= 0) {
            i = 1;
        }
        options.inSampleSize = i;
        m475a(a, readQrImageListener);
    }

    /* renamed from: a */
    public static void m475a(Bitmap bitmap, ReadQrImageListener readQrImageListener) {
        if (bitmap != null || readQrImageListener == null) {
            Bitmap a = m468a(bitmap);
            int width = a.getWidth();
            int height = a.getHeight();
            int[] iArr = new int[(width * height)];
            a.getPixels(iArr, 0, width, 0, 0, width, height);
            QRCodeReader qRCodeReader = new QRCodeReader();
            EnumMap enumMap = new EnumMap(DecodeHintType.class);
            enumMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            enumMap.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
            enumMap.put(DecodeHintType.CHARACTER_SET, "utf-8");
            try {
                Result decode = qRCodeReader.decode(new BinaryBitmap(new HybridBinarizer(new RGBLuminanceSource(width, height, iArr))));
                if (readQrImageListener != null) {
                    readQrImageListener.onSucessed(decode.getText());
                }
            } catch (NotFoundException e) {
                NLog b = SSDKLog.m645b();
                b.mo29771e(OnekeyShare.SHARESDK_TAG, "解析二维码异常 " + e);
                if (readQrImageListener != null) {
                    readQrImageListener.onFailed(e, -3);
                }
            } catch (ChecksumException e2) {
                NLog b2 = SSDKLog.m645b();
                b2.mo29771e(OnekeyShare.SHARESDK_TAG, "解析二维码异常 " + e2);
                if (readQrImageListener != null) {
                    readQrImageListener.onFailed(e2, -3);
                }
            } catch (FormatException e3) {
                NLog b3 = SSDKLog.m645b();
                b3.mo29771e(OnekeyShare.SHARESDK_TAG, "解析二维码异常 " + e3);
                if (readQrImageListener != null) {
                    readQrImageListener.onFailed(e3, -3);
                }
            } catch (Throwable th) {
                NLog b4 = SSDKLog.m645b();
                b4.mo29771e(OnekeyShare.SHARESDK_TAG, "解析二维码异常 " + th);
                if (readQrImageListener != null) {
                    readQrImageListener.onFailed(th, -3);
                }
            }
        } else {
            readQrImageListener.onFailed(new Throwable("读取相册图片失败"), -1);
        }
    }

    /* renamed from: a */
    private static Bitmap m468a(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        int i = 90;
        while (byteArrayOutputStream.toByteArray().length / 1024 > 50) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            i -= 10;
        }
        return BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), (Rect) null, (BitmapFactory.Options) null);
    }
}
