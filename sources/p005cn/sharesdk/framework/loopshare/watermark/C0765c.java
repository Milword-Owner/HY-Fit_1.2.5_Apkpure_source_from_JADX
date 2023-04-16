package p005cn.sharesdk.framework.loopshare.watermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import com.arthenica.mobileffmpeg.Config;
import com.arthenica.mobileffmpeg.FFmpeg;
import com.baidubce.BceConfig;
import com.mob.MobSDK;
import com.mob.tools.log.NLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import p005cn.sharesdk.framework.utils.QRCodeUtil.QRCodeListener;
import p005cn.sharesdk.framework.utils.QRCodeUtil.QRCodeService;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p005cn.sharesdk.onekeyshare.OnekeyShare;

/* renamed from: cn.sharesdk.framework.loopshare.watermark.c */
/* compiled from: FfmpegCommandCentre */
public class C0765c {

    /* renamed from: a */
    public static String f379a = null;

    /* renamed from: b */
    private static String f380b = "WMarkVideo";
    /* access modifiers changed from: private */

    /* renamed from: c */
    public static String f381c = "WMarkImage.jpg";

    /* renamed from: d */
    private static String f382d = "outputVideo";

    /* renamed from: e */
    private static String f383e = "textMark";

    /* renamed from: a */
    private static boolean m484a(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return true;
            }
            return file.mkdirs();
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29771e(OnekeyShare.SHARESDK_TAG, "fileIsExist Throwable: " + th);
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static String m486b(String str, Bitmap bitmap, Context context) {
        String str2 = context.getCacheDir() + "/images/";
        if (!m484a(str2)) {
            SSDKLog.m645b().mo29771e(OnekeyShare.SHARESDK_TAG, "TargetPath isn't exist");
            return null;
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str2, str));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            if (!TextUtils.isEmpty(str2)) {
                return str2;
            }
            return null;
        } catch (IOException e) {
            SSDKLog.m645b().mo29771e(OnekeyShare.SHARESDK_TAG, "saveBitmap IOException: " + e);
            return null;
        }
    }

    /* renamed from: a */
    public static String m481a(Context context, boolean z) {
        if (context == null || f379a != null) {
            return f379a;
        }
        String str = context.getCacheDir() + BceConfig.BOS_DELIMITER + f380b;
        File file = new File(str);
        if (!file.exists()) {
            if (!file.mkdirs()) {
                SSDKLog.m645b().mo29771e(OnekeyShare.SHARESDK_TAG, "getPathInPackage 在pakage目录创建CGE临时目录失败!");
                return null;
            } else if (z) {
                if (Build.VERSION.SDK_INT >= 9 && file.setExecutable(true, false)) {
                    SSDKLog.m645b().mo29775i(OnekeyShare.SHARESDK_TAG, "getPathInPackage Package folder is executable");
                }
                if (Build.VERSION.SDK_INT >= 9 && file.setReadable(true, false)) {
                    SSDKLog.m645b().mo29775i(OnekeyShare.SHARESDK_TAG, "getPathInPackage Package folder is readable");
                }
                if (Build.VERSION.SDK_INT >= 9 && file.setWritable(true, false)) {
                    SSDKLog.m645b().mo29775i(OnekeyShare.SHARESDK_TAG, "getPathInPackage Package folder is writable");
                }
            }
        }
        f379a = str;
        return f379a;
    }

    /* renamed from: a */
    public static void m483a(String str, String str2, String str3, String str4, WaterMarkListener waterMarkListener) {
        final String str5;
        if (Build.VERSION.SDK_INT > 24) {
            try {
                Class.forName("com.arthenica.mobileffmpeg.FFmpeg");
                final String str6 = m481a(MobSDK.getContext(), true) + BceConfig.BOS_DELIMITER + f382d + ".mp4";
                File file = new File(str6);
                if (file.exists()) {
                    file.delete();
                }
                final boolean[] zArr = {false};
                final String[] strArr = {str2, str3};
                if (!TextUtils.isEmpty(str2)) {
                    str5 = m481a(MobSDK.getContext(), true) + BceConfig.BOS_DELIMITER + f383e + ".jpg";
                    C0771f.m496a(new Runnable() {
                        public void run() {
                            zArr[0] = C0763a.m479a(str5, strArr);
                        }
                    });
                } else {
                    str5 = "";
                }
                final String str7 = str5;
                QRCodeService qRCodeService = new QRCodeService();
                qRCodeService.setContent(str);
                final WaterMarkListener waterMarkListener2 = waterMarkListener;
                final String str8 = str4;
                qRCodeService.generateAsync(new QRCodeListener() {
                    public void onSuccess(Bitmap bitmap) {
                        String[] strArr;
                        WaterMarkListener waterMarkListener;
                        SSDKLog.m645b().mo29768d(OnekeyShare.SHARESDK_TAG, "onSuccess 二维码生成成功 ");
                        String str = C0765c.m486b(C0765c.f381c, bitmap, MobSDK.getContext()) + C0765c.f381c;
                        if (!TextUtils.isEmpty(str) || (waterMarkListener = waterMarkListener2) == null) {
                            SSDKLog.m645b().mo29768d(OnekeyShare.SHARESDK_TAG, "图片保存的路径为: " + str);
                            if (zArr[0]) {
                                SSDKLog.m645b().mo29771e(OnekeyShare.SHARESDK_TAG, "textMarkImage length: " + str7.length());
                                SSDKLog.m645b().mo29771e(OnekeyShare.SHARESDK_TAG, "imagePath length: " + str.length());
                                strArr = C0765c.m485a(str7, str, "", str8, str6, 5);
                            } else {
                                strArr = C0765c.m485a("", str, "", str8, str6, 5);
                            }
                            int execute = FFmpeg.execute(strArr);
                            if (execute == 0) {
                                SSDKLog.m645b().mo29775i("mobile-ffmpeg", "Async command execution completed successfully.");
                                SSDKLog.m645b().mo29768d(OnekeyShare.SHARESDK_TAG, " FFmpeg 命令行执行完成...onEnd ");
                                try {
                                    if (!TextUtils.isEmpty(str6)) {
                                        File file = new File(str6);
                                        if (file.length() >= 0) {
                                            C0769d.m490a(file);
                                            if (waterMarkListener2 != null) {
                                                waterMarkListener2.onEnd(execute);
                                            }
                                        } else if (waterMarkListener2 != null) {
                                            waterMarkListener2.onFailed("合成视频存储到本地异常", -8);
                                        }
                                    }
                                } catch (Throwable th) {
                                    SSDKLog.m645b().mo29771e(OnekeyShare.SHARESDK_TAG, "onEnd catch: " + th);
                                    WaterMarkListener waterMarkListener2 = waterMarkListener2;
                                    if (waterMarkListener2 != null) {
                                        waterMarkListener2.onFailed(th.getMessage(), -7);
                                    }
                                }
                            } else {
                                SSDKLog.m645b().mo29775i("mobile-ffmpeg", String.format("Command execution failed with rc=%d and the output below.", new Object[]{Integer.valueOf(execute)}));
                                Config.printLastCommandOutput(4);
                                WaterMarkListener waterMarkListener3 = waterMarkListener2;
                                if (waterMarkListener3 != null) {
                                    waterMarkListener3.onFailed(String.format("Async command execution failed with rc=%d.", new Object[]{Integer.valueOf(execute)}), -6);
                                }
                            }
                            C0771f.m496a(new Runnable() {
                                public void run() {
                                }
                            });
                            return;
                        }
                        waterMarkListener.onFailed("QR code image storage failed", -9);
                    }

                    public void onError(Throwable th) {
                        NLog b = SSDKLog.m645b();
                        b.mo29771e(OnekeyShare.SHARESDK_TAG, "onError 二维码生成失败 " + th);
                        WaterMarkListener waterMarkListener = waterMarkListener2;
                        if (waterMarkListener != null) {
                            waterMarkListener.onFailed("二维码生成失败", -5);
                        }
                    }
                });
            } catch (ClassNotFoundException unused) {
                if (waterMarkListener != null) {
                    waterMarkListener.onFailed("The specified package dependency does not exist, please make sure whether the related dependency package is pulled normally", -10);
                }
            }
        } else if (waterMarkListener != null) {
            waterMarkListener.onFailed("The minimum version supported for this feature is 7.0", -2);
        }
    }

    /* renamed from: a */
    public static String[] m485a(String str, String str2, String str3, String str4, String str5, int i) {
        SSDKLog.m645b().mo29768d("LOGCAT", "ffmpeg params makeVideo: textIimageUrl: " + str + " imageUrl: " + str2 + " musicUrl: " + str3 + " videoUrl: " + str4 + " outputUrl: " + str5);
        ArrayList arrayList = new ArrayList();
        arrayList.add("-i");
        arrayList.add(str4);
        String str6 = "";
        if (!str.equals(str6) || !str2.equals(str6)) {
            if (!str2.equals(str6)) {
                arrayList.add("-loop");
                arrayList.add("0");
                arrayList.add("-i");
                arrayList.add(str2);
            }
            if (!str.equals(str6)) {
                arrayList.add("-i");
                arrayList.add(str);
            }
            arrayList.add("-filter_complex");
            if (str.equals(str6)) {
                arrayList.add("[1:v]scale=" + C0764b.f371a + com.baidu.mobstat.Config.TRACE_TODAY_VISIT_SPLIT + C0764b.f372b + "[s];[0:v][s]overlay=0:0");
            } else if (str2.equals(str6)) {
                arrayList.add("overlay=x='if(lte(t," + C0773g.f405c + "),(main_w-overlay_w)/2,NAN )':(main_h-overlay_h)/2");
            } else {
                arrayList.add("[1:v]scale=" + C0764b.f377g + com.baidu.mobstat.Config.TRACE_TODAY_VISIT_SPLIT + C0764b.f378h + "[img1];[2:v]scale=" + C0773g.f403a + com.baidu.mobstat.Config.TRACE_TODAY_VISIT_SPLIT + C0773g.f404b + "[img2];[0:v][img1]overlay=main_w-overlay_w-10:main_h-overlay_h-10[bkg];[bkg][img2]overlay=x='if(lte(t," + C0773g.f405c + "),(main_w-overlay_w)/2,NAN )':(main_h-overlay_h)/2");
            }
        }
        if (!str3.equals(str6)) {
            arrayList.add("-i");
            arrayList.add(str3);
        }
        arrayList.add(str5);
        String[] strArr = new String[arrayList.size()];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            strArr[i2] = (String) arrayList.get(i2);
            str6 = str6 + strArr[i2];
        }
        SSDKLog.m645b().mo29768d("LOGCAT", "ffmpeg command:" + str6 + strArr.length);
        return strArr;
    }
}
