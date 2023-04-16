package p005cn.sharesdk.framework.loopshare.watermark;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.baidubce.BceConfig;
import com.mob.MobSDK;
import com.mob.tools.log.NLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p005cn.sharesdk.onekeyshare.OnekeyShare;

/* renamed from: cn.sharesdk.framework.loopshare.watermark.d */
/* compiled from: ImageCopyAlbum */
public class C0769d {

    /* renamed from: a */
    private static final String f393a = (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/watermark");

    /* renamed from: a */
    public static void m489a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            SSDKLog.m645b().mo29771e(OnekeyShare.SHARESDK_TAG, " 拷贝文件的目标路径为空 ");
            return;
        }
        File file = new File(str);
        if (!file.exists()) {
            SSDKLog.m645b().mo29771e(OnekeyShare.SHARESDK_TAG, " 拷贝文件不存在 ");
            return;
        }
        m488a();
        File file2 = new File(f393a + BceConfig.BOS_DELIMITER + file.getName());
        m491a(file, file2);
        MediaScannerConnection.scanFile(context, new String[]{file2.getPath()}, (String[]) null, (MediaScannerConnection.OnScanCompletedListener) null);
    }

    /* renamed from: a */
    private static void m488a() {
        File file = new File(f393a);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    private static void m491a(File file, File file2) {
        if (!file.getAbsolutePath().equals(file2.getAbsolutePath())) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read >= 0) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileInputStream.close();
                        fileOutputStream.close();
                        NLog b = SSDKLog.m645b();
                        b.mo29768d(OnekeyShare.SHARESDK_TAG, "copyFile 执行完毕, src " + file.length() + " dest: " + file2.length());
                        return;
                    }
                }
            } catch (IOException e) {
                NLog b2 = SSDKLog.m645b();
                b2.mo29771e(OnekeyShare.SHARESDK_TAG, "拷贝文件到相册失败: " + e);
            }
        }
    }

    /* renamed from: a */
    public static void m490a(File file) {
        ContentResolver contentResolver = MobSDK.getContext().getContentResolver();
        try {
            Uri insert = contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, m487a(file, System.currentTimeMillis()));
            if (insert != null) {
                byte[] bArr = new byte[1024];
                FileOutputStream fileOutputStream = new FileOutputStream(contentResolver.openFileDescriptor(insert, Config.DEVICE_WIDTH).getFileDescriptor());
                FileInputStream fileInputStream = new FileInputStream(file);
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        fileInputStream.close();
                        fileOutputStream.flush();
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                        intent.setData(insert);
                        MobSDK.getContext().sendBroadcast(intent);
                        SSDKLog.m645b().mo29768d(OnekeyShare.SHARESDK_TAG, "视频拷贝到相册完成");
                        return;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
            } else {
                SSDKLog.m645b().mo29771e(OnekeyShare.SHARESDK_TAG, "此款机型拷贝到相册失败，localUri is null, insert MediaStore failed， 将会再次尝试一下别的路径");
                m489a(MobSDK.getContext(), file.getAbsolutePath());
                SSDKLog.m645b().mo29768d(OnekeyShare.SHARESDK_TAG, "别的路径尝试拷贝完成");
                return;
            }
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29771e(OnekeyShare.SHARESDK_TAG, "异常处理下再次执行拷贝依然失败" + th);
        }
        NLog b2 = SSDKLog.m645b();
        b2.mo29771e(OnekeyShare.SHARESDK_TAG, "Failed to insert media file " + th);
    }

    /* renamed from: a */
    public static ContentValues m487a(File file, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", file.getName());
        contentValues.put("description", "water mark video");
        contentValues.put("title", file.getName());
        contentValues.put("_display_name", file.getName());
        contentValues.put("mime_type", "video/*");
        contentValues.put("datetaken", Long.valueOf(j));
        contentValues.put("date_modified", Long.valueOf(j));
        contentValues.put("date_added", Long.valueOf(j));
        contentValues.put("_data", file.getAbsolutePath());
        contentValues.put("_size", Long.valueOf(file.length()));
        return contentValues;
    }
}
