package com.huayu.tzc.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.WindowManager;
import com.baidubce.BceConfig;
import com.facebook.appevents.codeless.internal.Constants;
import com.huayu.tzc.C2128R;
import java.io.File;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.regex.Pattern;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import okhttp3.FormBody;

public class Utils {
    public static int getImgRes(int i) {
        return i == 1 ? C2128R.C2130drawable.ic_woman_b : C2128R.C2130drawable.ic_man_b;
    }

    public static void logFormBody(FormBody formBody) {
        for (int i = 0; i < formBody.size(); i++) {
            Log.e("getFormBody: ", formBody.name(i) + "  : " + formBody.value(i));
        }
    }

    public static boolean isLocServiceEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        return locationManager.isProviderEnabled("gps") || locationManager.isProviderEnabled("network");
    }

    public static boolean isPackageExisted(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 128);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public static String getRealFilePath(Context context, Uri uri) {
        Cursor query;
        int columnIndex;
        String str = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null) {
            str = uri.getPath();
        } else if ("file".equals(scheme)) {
            str = uri.getPath();
        } else if ("content".equals(scheme) && (query = context.getContentResolver().query(uri, new String[]{"_data"}, (String) null, (String[]) null, (String) null)) != null) {
            if (query.moveToFirst() && (columnIndex = query.getColumnIndex("_data")) > -1) {
                str = query.getString(columnIndex);
            }
            query.close();
        }
        if (!TextUtils.isEmpty(str) || uri == null) {
            return str;
        }
        String uri2 = uri.toString();
        String substring = uri2.substring(uri2.lastIndexOf(BceConfig.BOS_DELIMITER));
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), substring);
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        return new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), substring).getAbsolutePath();
    }

    public static boolean checkPhone(String str) {
        return Pattern.compile("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$", 2).matcher(str).matches();
    }

    public static int getStatusBarHeight(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", Constants.PLATFORM);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return (int) TypedValue.applyDimension(1, (float) 24, Resources.getSystem().getDisplayMetrics());
    }

    public static int convertDpToPixel(Context context, int i) {
        return (int) (((float) i) * context.getResources().getDisplayMetrics().density);
    }

    public static int getStatusBarHeight2(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", Constants.PLATFORM);
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(Context context, int i) {
        return (int) ((((float) i) / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        float f = displayMetrics.density;
        int i3 = displayMetrics.densityDpi;
        return i;
    }

    public static String getVersionName(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            return "";
        }
        return packageInfo.versionName;
    }

    public static int getVersionCode(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo == null) {
            return 0;
        }
        return packageInfo.versionCode;
    }

    public static void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                public boolean verify(String str, SSLSession sSLSession) {
                    return true;
                }
            });
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, new X509TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(instance.getSocketFactory());
        } catch (Exception unused) {
        }
    }

    public static int checkNetState(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return 3;
        }
        int type = activeNetworkInfo.getType();
        if (type == 0) {
            return 2;
        }
        if (type != 1) {
            return 3;
        }
        return 1;
    }
}
