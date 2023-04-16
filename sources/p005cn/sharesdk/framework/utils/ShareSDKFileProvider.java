package p005cn.sharesdk.framework.utils;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import com.baidubce.BceConfig;
import com.mob.MobSDK;
import com.mob.tools.log.NLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: cn.sharesdk.framework.utils.ShareSDKFileProvider */
public class ShareSDKFileProvider extends ContentProvider {

    /* renamed from: a */
    private static final String[] f590a = {"_display_name", "_size"};

    /* renamed from: b */
    private static final File f591b = new File(BceConfig.BOS_DELIMITER);

    /* renamed from: c */
    private static HashMap<String, PathStrategy> f592c = new HashMap<>();

    /* renamed from: d */
    private PathStrategy f593d;

    /* renamed from: cn.sharesdk.framework.utils.ShareSDKFileProvider$PathStrategy */
    interface PathStrategy {
        File getFileForUri(Uri uri);

        Uri getUriForFile(File file);
    }

    public boolean onCreate() {
        SSDKLog.m645b().mo29768d("ShareSDKFileProvider onCreate ", new Object[0]);
        return true;
    }

    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            SSDKLog.m645b().mo29768d("ShareSDKFileProvider attachInfo ===> Provider must not be exported", new Object[0]);
            throw new SecurityException("Provider must not be exported");
        } else if (providerInfo.grantUriPermissions) {
            SSDKLog.m645b().mo29768d("ShareSDKFileProvider attachInfo ", new Object[0]);
            this.f593d = m648a(context, providerInfo.authority);
            NLog b = SSDKLog.m645b();
            b.mo29768d("ShareSDKFileProvider attachInfo mStrategy===> " + this.f593d, new Object[0]);
        } else {
            SSDKLog.m645b().mo29768d("ShareSDKFileProvider attachInfo ===> Provider must grant uri permissions", new Object[0]);
            throw new SecurityException("Provider must grant uri permissions");
        }
    }

    /* renamed from: a */
    public static Uri m647a(Context context, String str, File file) {
        return m648a(context, str).getUriForFile(file);
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i;
        File fileForUri = this.f593d.getFileForUri(uri);
        if (strArr == null) {
            strArr = f590a;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i2 = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i2] = "_display_name";
                i = i2 + 1;
                objArr[i2] = fileForUri.getName();
            } else if ("_size".equals(str3)) {
                strArr3[i2] = "_size";
                i = i2 + 1;
                objArr[i2] = Long.valueOf(fileForUri.length());
            }
            i2 = i;
        }
        String[] a = m652a(strArr3, i2);
        Object[] a2 = m651a(objArr, i2);
        MatrixCursor matrixCursor = new MatrixCursor(a, 1);
        matrixCursor.addRow(a2);
        return matrixCursor;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0012, code lost:
        r3 = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(r3.getName().substring(r0 + 1));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getType(android.net.Uri r3) {
        /*
            r2 = this;
            cn.sharesdk.framework.utils.ShareSDKFileProvider$PathStrategy r0 = r2.f593d
            java.io.File r3 = r0.getFileForUri(r3)
            java.lang.String r0 = r3.getName()
            r1 = 46
            int r0 = r0.lastIndexOf(r1)
            if (r0 < 0) goto L_0x0027
            java.lang.String r3 = r3.getName()
            int r0 = r0 + 1
            java.lang.String r3 = r3.substring(r0)
            android.webkit.MimeTypeMap r0 = android.webkit.MimeTypeMap.getSingleton()
            java.lang.String r3 = r0.getMimeTypeFromExtension(r3)
            if (r3 == 0) goto L_0x0027
            return r3
        L_0x0027:
            java.lang.String r3 = "application/octet-stream"
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: p005cn.sharesdk.framework.utils.ShareSDKFileProvider.getType(android.net.Uri):java.lang.String");
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return this.f593d.getFileForUri(uri).delete() ? 1 : 0;
    }

    public ParcelFileDescriptor openFile(Uri uri, String str) throws FileNotFoundException {
        return ParcelFileDescriptor.open(this.f593d.getFileForUri(uri), m646a(str));
    }

    /* renamed from: a */
    private static PathStrategy m648a(Context context, String str) {
        PathStrategy pathStrategy;
        synchronized (f592c) {
            pathStrategy = f592c.get(str);
            if (pathStrategy == null) {
                pathStrategy = m653b(context, str);
                f592c.put(str, pathStrategy);
            }
        }
        NLog b = SSDKLog.m645b();
        b.mo29768d("ShareSDKFileProvider getPathStrategy strat " + pathStrategy, new Object[0]);
        return pathStrategy;
    }

    /* renamed from: b */
    private static PathStrategy m653b(Context context, String str) {
        C0799a aVar = new C0799a(str);
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            aVar.mo10916a("imageNameFilesDir", m649a(filesDir, "Mob/cache/images"));
            aVar.mo10916a("videoNameFilesDir", m649a(filesDir, "Mob/cache/videos"));
            SSDKLog.m645b().mo29768d("ShareSDKFileProvider target " + filesDir, new Object[0]);
        }
        String str2 = "Mob/" + MobSDK.getContext().getPackageName() + "/cache/images";
        File cacheDir = MobSDK.getContext().getCacheDir();
        if (cacheDir != null) {
            aVar.mo10916a("cachename", m649a(filesDir, "."));
            aVar.mo10916a("imageNameExternal", m649a(filesDir, str2));
            aVar.mo10916a("imageNameExternal", m649a(filesDir, "Mob/cache/images"));
            SSDKLog.m645b().mo29768d("ShareSDKFileProvider target " + cacheDir, new Object[0]);
        }
        String str3 = "Mob/" + MobSDK.getContext().getPackageName() + "/cache/images";
        String str4 = "Mob/" + MobSDK.getContext().getPackageName() + "/cache/videos";
        File[] a = m650a(MobSDK.getContext());
        File file = a.length > 0 ? a[0] : null;
        if (file != null) {
            aVar.mo10916a("imageNameExternal", m649a(file, str3));
            aVar.mo10916a("videoNameExternal", m649a(file, str4));
            aVar.mo10916a("mihayou", m649a(file, "."));
            aVar.mo10916a("more", m649a(file, "./."));
            SSDKLog.m645b().mo29768d("ShareSDKFileProvider target " + file, new Object[0]);
        }
        String str5 = "Mob/" + MobSDK.getContext().getPackageName() + "/cache/images";
        String str6 = "Mob/" + MobSDK.getContext().getPackageName() + "/cache/videos";
        File[] b = m654b(MobSDK.getContext());
        File file2 = b.length > 0 ? b[0] : null;
        if (file2 != null) {
            aVar.mo10916a("imageNameEtc", m649a(file2, str5));
            aVar.mo10916a("videoNameEtc", m649a(file2, str6));
            SSDKLog.m645b().mo29768d("ShareSDKFileProvider target " + file2, new Object[0]);
        }
        File file3 = f591b;
        if (file3 != null) {
            aVar.mo10916a("imageNameRoot", m649a((File) null, "Mob/cache/images"));
            aVar.mo10916a("videoNameRoot", m649a((File) null, "Mob/cache/videos"));
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            aVar.mo10916a("externalStDir", m649a(externalStorageDirectory, "."));
            SSDKLog.m645b().mo29768d("ShareSDKFileProvider target " + externalStorageDirectory, new Object[0]);
        }
        SSDKLog.m645b().mo29768d("ShareSDKFileProvider !!! target===> " + file3, new Object[0]);
        return aVar;
    }

    /* renamed from: a */
    public static File[] m650a(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            SSDKLog.m645b().mo29768d("ShareSDKFileProvider api >= 19", new Object[0]);
            return context.getExternalFilesDirs((String) null);
        }
        SSDKLog.m645b().mo29768d("ShareSDKFileProvider api <= 19", new Object[0]);
        return new File[]{context.getExternalFilesDir((String) null)};
    }

    /* renamed from: b */
    public static File[] m654b(Context context) {
        if (Build.VERSION.SDK_INT >= 19) {
            SSDKLog.m645b().mo29768d("ShareSDKFileProvider api >= 19", new Object[0]);
            return context.getExternalCacheDirs();
        }
        SSDKLog.m645b().mo29768d("ShareSDKFileProvider api <= 19", new Object[0]);
        return new File[]{context.getExternalCacheDir()};
    }

    /* renamed from: cn.sharesdk.framework.utils.ShareSDKFileProvider$a */
    static class C0799a implements PathStrategy {

        /* renamed from: a */
        private final String f594a;

        /* renamed from: b */
        private final HashMap<String, File> f595b = new HashMap<>();

        public C0799a(String str) {
            this.f594a = str;
        }

        /* renamed from: a */
        public void mo10916a(String str, File file) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    File canonicalFile = file.getCanonicalFile();
                    NLog b = SSDKLog.m645b();
                    b.mo29768d("ShareSDKFileProvider addRoot name ===> " + str + " root===> " + canonicalFile, new Object[0]);
                    this.f595b.put(str, canonicalFile);
                } catch (IOException e) {
                    throw new IllegalArgumentException("Failed to resolve canonical path for " + file, e);
                }
            } else {
                throw new IllegalArgumentException("Name must not be empty");
            }
        }

        public Uri getUriForFile(File file) {
            String str;
            try {
                NLog b = SSDKLog.m645b();
                b.mo29768d("ShareSDKFileProvider !!! getUriForFile !!! file " + file, new Object[0]);
                String canonicalPath = file.getCanonicalPath();
                NLog b2 = SSDKLog.m645b();
                b2.mo29768d("ShareSDKFileProvider getUriForFile path " + canonicalPath, new Object[0]);
                Map.Entry entry = null;
                for (Map.Entry next : this.f595b.entrySet()) {
                    String path = ((File) next.getValue()).getPath();
                    if (canonicalPath.startsWith(path) && (entry == null || path.length() > ((File) entry.getValue()).getPath().length())) {
                        NLog b3 = SSDKLog.m645b();
                        b3.mo29768d("ShareSDKFileProvider getUriForFile mostSpecific " + next, new Object[0]);
                        entry = next;
                    }
                }
                if (entry != null) {
                    String path2 = ((File) entry.getValue()).getPath();
                    if (path2.endsWith(BceConfig.BOS_DELIMITER)) {
                        str = canonicalPath.substring(path2.length());
                    } else {
                        str = canonicalPath.substring(path2.length() + 1);
                    }
                    return new Uri.Builder().scheme("content").authority(this.f594a).encodedPath(Uri.encode((String) entry.getKey()) + '/' + Uri.encode(str, BceConfig.BOS_DELIMITER)).build();
                }
                NLog b4 = SSDKLog.m645b();
                b4.mo29768d("ShareSDKFileProvider Failed to find configured root that contains " + canonicalPath, new Object[0]);
                throw new IllegalArgumentException("Failed to find configured root that contains " + canonicalPath);
            } catch (IOException unused) {
                throw new IllegalArgumentException("Failed to resolve canonical path for " + file);
            }
        }

        public File getFileForUri(Uri uri) {
            String encodedPath = uri.getEncodedPath();
            int indexOf = encodedPath.indexOf(47, 1);
            String decode = Uri.decode(encodedPath.substring(1, indexOf));
            String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
            File file = this.f595b.get(decode);
            if (file != null) {
                File file2 = new File(file, decode2);
                try {
                    File canonicalFile = file2.getCanonicalFile();
                    if (canonicalFile.getPath().startsWith(file.getPath())) {
                        return canonicalFile;
                    }
                    throw new SecurityException("Resolved path jumped beyond configured root");
                } catch (IOException unused) {
                    throw new IllegalArgumentException("Failed to resolve canonical path for " + file2);
                }
            } else {
                throw new IllegalArgumentException("Unable to find configured root for " + uri);
            }
        }
    }

    /* renamed from: a */
    private static int m646a(String str) {
        if ("r".equals(str)) {
            return 268435456;
        }
        if (Config.DEVICE_WIDTH.equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException("Invalid mode: " + str);
    }

    /* renamed from: a */
    private static File m649a(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    /* renamed from: a */
    private static String[] m652a(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        return strArr2;
    }

    /* renamed from: a */
    private static Object[] m651a(Object[] objArr, int i) {
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        return objArr2;
    }
}
