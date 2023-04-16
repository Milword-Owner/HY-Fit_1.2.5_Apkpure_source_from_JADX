package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.system.Os;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class CarUUID {

    /* renamed from: a */
    private static final Pattern f778a = Pattern.compile("(\\w{32})");

    public static String optUUID(Context context) {
        String b = m724b(context);
        if (b != null) {
            return b;
        }
        String c = m725c(context);
        if (c != null) {
            m721a(context, c);
            return c;
        }
        String a = m719a(context);
        if (a == null) {
            return "";
        }
        m721a(context, a);
        return a;
    }

    /* renamed from: a */
    private static String m719a(Context context) {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /* renamed from: b */
    private static String m724b(Context context) {
        return m720a(context.getFileStreamPath("libdueros_uuid.so"));
    }

    /* renamed from: c */
    private static String m725c(Context context) {
        String a;
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(0);
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        for (ApplicationInfo next : installedApplications) {
            if (!applicationInfo.packageName.equals(next.packageName) && (a = m720a(new File(new File(next.dataDir, "files"), "libdueros_uuid.so"))) != null) {
                return a;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static boolean m721a(Context context, String str) {
        boolean z = false;
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput("libdueros_uuid.so", Build.VERSION.SDK_INT >= 21 ? 0 : 1);
            if (m723a(fileOutputStream, str)) {
                if (Build.VERSION.SDK_INT >= 21) {
                    ApplicationInfo applicationInfo = context.getApplicationInfo();
                    File fileStreamPath = context.getFileStreamPath("libdueros_uuid.so");
                    if (m722a(new File(applicationInfo.dataDir), 457) && m722a(fileStreamPath, 484)) {
                        z = true;
                    }
                    C0988bt.m1414a(fileOutputStream);
                    return z;
                }
                C0988bt.m1414a(fileOutputStream);
                return true;
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            C0988bt.m1414a((Closeable) null);
            throw th;
        }
        C0988bt.m1414a(fileOutputStream);
        return false;
    }

    /* renamed from: a */
    private static String m720a(File file) {
        FileInputStream fileInputStream;
        String str = null;
        if (file != null && file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Exception unused) {
                fileInputStream = null;
                C0988bt.m1414a(fileInputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
                C0988bt.m1414a(fileInputStream);
                throw th;
            }
            try {
                byte[] bArr = new byte[1024];
                String str2 = new String(bArr, 0, fileInputStream.read(bArr));
                if (f778a.matcher(str2).matches()) {
                    str = str2;
                }
                C0988bt.m1414a(fileInputStream);
                return str;
            } catch (Exception unused2) {
                C0988bt.m1414a(fileInputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                C0988bt.m1414a(fileInputStream);
                throw th;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static boolean m723a(FileOutputStream fileOutputStream, String str) {
        try {
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private static boolean m722a(File file, int i) {
        if (Build.VERSION.SDK_INT < 21) {
            return true;
        }
        try {
            Os.chmod(file.getAbsolutePath(), i);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
