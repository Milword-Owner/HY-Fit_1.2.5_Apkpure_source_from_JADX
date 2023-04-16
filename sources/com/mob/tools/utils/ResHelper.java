package com.mob.tools.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.baidubce.BceConfig;
import com.facebook.internal.AnalyticsEvents;
import com.github.mikephil.charting.utils.Utils;
import com.hjq.permissions.Permission;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.network.KVPair;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.zip.GZIPOutputStream;
import kotlin.text.Typography;

public class ResHelper {
    private static float density;
    private static int deviceWidth;
    /* access modifiers changed from: private */
    public static Uri mediaUri;

    /* renamed from: rp */
    private static Object f2352rp;

    public static int dipToPx(Context context, int i) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return (int) ((((float) i) * density) + 0.5f);
    }

    public static int pxToDip(Context context, int i) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return (int) ((((float) i) / density) + 0.5f);
    }

    public static int designToDevice(Context context, int i, int i2) {
        if (deviceWidth == 0) {
            int[] screenSize = getScreenSize(context);
            deviceWidth = screenSize[0] < screenSize[1] ? screenSize[0] : screenSize[1];
        }
        return (int) (((((float) i2) * ((float) deviceWidth)) / ((float) i)) + 0.5f);
    }

    public static int designToDevice(Context context, float f, int i) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return (int) (((((float) i) * density) / f) + 0.5f);
    }

    public static int getDensityDpi(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    public static float[] getDensityXYDpi(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return new float[]{displayMetrics.xdpi, displayMetrics.ydpi};
    }

    public static float getScaledDensity(Context context) {
        return context.getResources().getDisplayMetrics().scaledDensity;
    }

    public static float getDensity(Context context) {
        if (density <= 0.0f) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return density;
    }

    public static int[] getScreenSize(Context context) {
        WindowManager windowManager;
        Display display = null;
        try {
            windowManager = (WindowManager) DeviceHelper.getInstance(context).getSystemServiceSafe("window");
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            windowManager = null;
        }
        if (windowManager == null) {
            return new int[]{0, 0};
        }
        try {
            display = windowManager.getDefaultDisplay();
        } catch (Throwable th2) {
            MobLog.getInstance().mo29787w(th2);
        }
        if (display == null) {
            try {
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                return new int[]{displayMetrics.widthPixels, displayMetrics.heightPixels};
            } catch (Throwable th3) {
                MobLog.getInstance().mo29787w(th3);
                return new int[]{0, 0};
            }
        } else if (Build.VERSION.SDK_INT < 13) {
            try {
                DisplayMetrics displayMetrics2 = new DisplayMetrics();
                display.getMetrics(displayMetrics2);
                return new int[]{displayMetrics2.widthPixels, displayMetrics2.heightPixels};
            } catch (Throwable th4) {
                MobLog.getInstance().mo29787w(th4);
                return new int[]{0, 0};
            }
        } else {
            try {
                Point point = new Point();
                Method method = display.getClass().getMethod("getRealSize", new Class[]{Point.class});
                method.setAccessible(true);
                method.invoke(display, new Object[]{point});
                return new int[]{point.x, point.y};
            } catch (Throwable th5) {
                MobLog.getInstance().mo29787w(th5);
                return new int[]{0, 0};
            }
        }
    }

    public static int getScreenWidth(Context context) {
        return getScreenSize(context)[0];
    }

    public static int getScreenHeight(Context context) {
        return getScreenSize(context)[1];
    }

    public static void setResourceProvider(Object obj) {
        try {
            if (obj.getClass().getMethod("getResId", new Class[]{Context.class, String.class, String.class}) != null) {
                f2352rp = obj;
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    public static int getResId(Context context, String str, String str2) {
        int i = 0;
        if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            Object obj = f2352rp;
            if (obj != null) {
                try {
                    Method method = obj.getClass().getMethod("getResId", new Class[]{Context.class, String.class, String.class});
                    method.setAccessible(true);
                    i = ((Integer) method.invoke(f2352rp, new Object[]{context, str, str2})).intValue();
                } catch (Throwable th) {
                    MobLog.getInstance().mo29769d(th);
                }
            }
            if (i <= 0) {
                String packageName = context.getPackageName();
                if (TextUtils.isEmpty(packageName)) {
                    return i;
                }
                if (i <= 0 && (i = context.getResources().getIdentifier(str2, str, packageName)) <= 0) {
                    i = context.getResources().getIdentifier(str2.toLowerCase(), str, packageName);
                }
                if (i <= 0) {
                    NLog instance = MobLog.getInstance();
                    instance.mo29786w("failed to parse " + str + " resource \"" + str2 + "\"");
                }
            }
        }
        return i;
    }

    public static int getBitmapRes(Context context, String str) {
        int resId = getResId(context, "drawable", str);
        return resId <= 0 ? getResId(context, "mipmap", str) : resId;
    }

    public static int getStringRes(Context context, String str) {
        return getResId(context, "string", str);
    }

    public static int getStringArrayRes(Context context, String str) {
        return getResId(context, "array", str);
    }

    public static int getLayoutRes(Context context, String str) {
        return getResId(context, "layout", str);
    }

    public static int getStyleRes(Context context, String str) {
        return getResId(context, AnalyticsEvents.PARAMETER_LIKE_VIEW_STYLE, str);
    }

    public static int getIdRes(Context context, String str) {
        return getResId(context, "id", str);
    }

    public static int getColorRes(Context context, String str) {
        return getResId(context, "color", str);
    }

    public static int getRawRes(Context context, String str) {
        return getResId(context, "raw", str);
    }

    public static int getPluralsRes(Context context, String str) {
        return getResId(context, "plurals", str);
    }

    public static int getAnimRes(Context context, String str) {
        return getResId(context, "anim", str);
    }

    public static int[] getStyleableRes(Context context, String str) {
        try {
            Object staticField = ReflectHelper.getStaticField(ReflectHelper.importClass(context.getPackageName() + ".R$styleable"), str);
            if (staticField == null) {
                return new int[0];
            }
            if (staticField.getClass().isArray()) {
                return (int[]) staticField;
            }
            return new int[]{((Integer) staticField).intValue()};
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return new int[0];
        }
    }

    public static File getCacheRootFile(Context context, String str) {
        try {
            String cacheRoot = getCacheRoot(context);
            if (cacheRoot == null) {
                return null;
            }
            File file = new File(cacheRoot, str);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            return file;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    public static String getCacheRoot(Context context) {
        String sdcardPath;
        try {
            String str = context.getFilesDir().getAbsolutePath() + "/Mob/";
            DeviceHelper instance = DeviceHelper.getInstance(context);
            if (instance.getSdcardState() && (sdcardPath = instance.getSdcardPath()) != null) {
                str = sdcardPath + "/Mob/";
            }
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            return str;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    public static String getDataCache(Context context) {
        String str = context.getFilesDir().getAbsolutePath() + "/Mob/";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    public static File getDataCacheFile(Context context, String str) {
        return new File(getDataCache(context), str);
    }

    public static String getCachePath(Context context, String str) {
        String sdcardPath;
        String str2 = context.getFilesDir().getAbsolutePath() + "/Mob/cache/";
        DeviceHelper instance = DeviceHelper.getInstance(context);
        try {
            if (instance.getSdcardState() && (sdcardPath = instance.getSdcardPath()) != null) {
                str2 = sdcardPath + "/Mob/" + instance.getPackageName() + "/cache/";
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
        if (!TextUtils.isEmpty(str)) {
            str2 = str2 + str + BceConfig.BOS_DELIMITER;
        }
        File file = new File(str2);
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
        return str2;
    }

    public static String getImageCachePath(Context context) {
        return getCachePath(context, "images");
    }

    public static void clearCache(Context context) throws Throwable {
        deleteFileAndFolder(new File(getCachePath(context, (String) null)));
    }

    public static void deleteFilesInFolder(File file) throws Throwable {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list != null && list.length > 0) {
                for (String file2 : list) {
                    File file3 = new File(file, file2);
                    if (file3.isDirectory()) {
                        deleteFilesInFolder(file3);
                    } else {
                        file3.delete();
                    }
                }
            }
        }
    }

    public static void deleteFileAndFolder(File file) throws Throwable {
        if (file != null && file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list == null || list.length <= 0) {
                file.delete();
                return;
            }
            for (String file2 : list) {
                File file3 = new File(file, file2);
                if (file3.isDirectory()) {
                    deleteFileAndFolder(file3);
                } else {
                    file3.delete();
                }
            }
            file.delete();
        }
    }

    public static String toWordText(String str, int i) {
        char[] charArray = str.toCharArray();
        int i2 = i * 2;
        StringBuilder sb = new StringBuilder();
        int length = charArray.length;
        for (int i3 = 0; i3 < length; i3++) {
            char c = charArray[i3];
            i2 -= c < 256 ? 1 : 2;
            if (i2 < 0) {
                return sb.toString();
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public static int getTextLengthInWord(String str) {
        char[] charArray = str == null ? new char[0] : str.toCharArray();
        int i = 0;
        for (int i2 = 0; i2 < charArray.length; i2++) {
            i += charArray[i2] < 256 ? 1 : 2;
        }
        return i;
    }

    public static long strToDate(String str) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str, new ParsePosition(0)).getTime();
    }

    public static long dateStrToLong(String str) {
        return new SimpleDateFormat("yyyy-MM-dd").parse(str, new ParsePosition(0)).getTime();
    }

    public static Date longToDate(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.getTime();
    }

    public static String longToTime(long j, int i) {
        return new SimpleDateFormat(i != 1 ? i != 2 ? i != 5 ? i != 10 ? i != 12 ? "yyyy-MM-dd kk:mm:ss" : "yyyy-MM-dd kk:mm" : "yyyy-MM-dd kk" : "yyyy-MM-dd" : "yyyy-MM" : "yyyy").format(Long.valueOf(j));
    }

    public static long dateToLong(String str) {
        try {
            Date date = new Date(str);
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            return instance.getTimeInMillis();
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return 0;
        }
    }

    public static int[] covertTimeInYears(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis <= 0) {
            return new int[]{0, 0};
        }
        long j2 = currentTimeMillis / 1000;
        if (j2 < 60) {
            return new int[]{(int) j2, 0};
        }
        long j3 = j2 / 60;
        if (j3 < 60) {
            return new int[]{(int) j3, 1};
        }
        long j4 = j3 / 60;
        if (j4 < 24) {
            return new int[]{(int) j4, 2};
        }
        long j5 = j4 / 24;
        if (j5 < 30) {
            return new int[]{(int) j5, 3};
        }
        long j6 = j5 / 30;
        if (j6 < 12) {
            return new int[]{(int) j6, 4};
        }
        return new int[]{(int) (j6 / 12), 5};
    }

    public static Uri pathToContentUri(Context context, String str) {
        try {
            if (!DeviceHelper.getInstance(context).checkPermission(Permission.READ_EXTERNAL_STORAGE)) {
                return null;
            }
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
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x008a A[Catch:{ Throwable -> 0x00af }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x009a A[Catch:{ Throwable -> 0x00af }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String contentUriToPath(android.content.Context r15, android.net.Uri r16) {
        /*
            r7 = 0
            if (r16 != 0) goto L_0x0004
            return r7
        L_0x0004:
            java.io.File r0 = new java.io.File
            java.lang.String r1 = r16.getPath()
            r0.<init>(r1)
            boolean r0 = r0.exists()
            if (r0 == 0) goto L_0x0018
            java.lang.String r0 = r16.getPath()
            return r0
        L_0x0018:
            int r0 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00af }
            r1 = 19
            java.lang.String r8 = "_data"
            if (r0 < r1) goto L_0x0087
            java.lang.String r0 = "android.provider.DocumentsContract"
            java.lang.Class r0 = java.lang.Class.forName(r0)     // Catch:{ Throwable -> 0x00af }
            java.lang.String r1 = "isDocumentUri"
            r2 = 2
            java.lang.Class[] r3 = new java.lang.Class[r2]     // Catch:{ Throwable -> 0x00af }
            java.lang.Class<android.content.Context> r4 = android.content.Context.class
            r5 = 0
            r3[r5] = r4     // Catch:{ Throwable -> 0x00af }
            java.lang.Class<android.net.Uri> r4 = android.net.Uri.class
            r6 = 1
            r3[r6] = r4     // Catch:{ Throwable -> 0x00af }
            java.lang.reflect.Method r1 = r0.getMethod(r1, r3)     // Catch:{ Throwable -> 0x00af }
            r1.setAccessible(r6)     // Catch:{ Throwable -> 0x00af }
            java.lang.Boolean r3 = java.lang.Boolean.TRUE     // Catch:{ Throwable -> 0x00af }
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x00af }
            r2[r5] = r15     // Catch:{ Throwable -> 0x00af }
            r2[r6] = r16     // Catch:{ Throwable -> 0x00af }
            java.lang.Object r1 = r1.invoke(r7, r2)     // Catch:{ Throwable -> 0x00af }
            boolean r1 = r3.equals(r1)     // Catch:{ Throwable -> 0x00af }
            if (r1 == 0) goto L_0x0087
            java.lang.String r1 = "getDocumentId"
            java.lang.Class[] r2 = new java.lang.Class[r6]     // Catch:{ Throwable -> 0x00af }
            java.lang.Class<android.net.Uri> r3 = android.net.Uri.class
            r2[r5] = r3     // Catch:{ Throwable -> 0x00af }
            java.lang.reflect.Method r0 = r0.getMethod(r1, r2)     // Catch:{ Throwable -> 0x00af }
            r0.setAccessible(r6)     // Catch:{ Throwable -> 0x00af }
            java.lang.Object[] r1 = new java.lang.Object[r6]     // Catch:{ Throwable -> 0x00af }
            r1[r5] = r16     // Catch:{ Throwable -> 0x00af }
            java.lang.Object r0 = r0.invoke(r7, r1)     // Catch:{ Throwable -> 0x00af }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x00af }
            java.lang.String r1 = ":"
            java.lang.String[] r0 = r0.split(r1)     // Catch:{ Throwable -> 0x00af }
            r0 = r0[r6]     // Catch:{ Throwable -> 0x00af }
            java.lang.String[] r11 = new java.lang.String[]{r8}     // Catch:{ Throwable -> 0x00af }
            java.lang.String r12 = "_id=?"
            java.lang.String[] r13 = new java.lang.String[r6]     // Catch:{ Throwable -> 0x00af }
            r13[r5] = r0     // Catch:{ Throwable -> 0x00af }
            android.content.ContentResolver r9 = r15.getContentResolver()     // Catch:{ Throwable -> 0x00af }
            android.net.Uri r10 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI     // Catch:{ Throwable -> 0x00af }
            r14 = 0
            android.database.Cursor r0 = r9.query(r10, r11, r12, r13, r14)     // Catch:{ Throwable -> 0x00af }
            goto L_0x0088
        L_0x0087:
            r0 = r7
        L_0x0088:
            if (r0 != 0) goto L_0x0098
            android.content.ContentResolver r1 = r15.getContentResolver()     // Catch:{ Throwable -> 0x00af }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r2 = r16
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)     // Catch:{ Throwable -> 0x00af }
        L_0x0098:
            if (r0 == 0) goto L_0x00b7
            boolean r1 = r0.moveToFirst()     // Catch:{ Throwable -> 0x00af }
            if (r1 == 0) goto L_0x00a9
            int r1 = r0.getColumnIndex(r8)     // Catch:{ Throwable -> 0x00af }
            java.lang.String r1 = r0.getString(r1)     // Catch:{ Throwable -> 0x00af }
            goto L_0x00aa
        L_0x00a9:
            r1 = r7
        L_0x00aa:
            r0.close()     // Catch:{ Throwable -> 0x00af }
            r7 = r1
            goto L_0x00b7
        L_0x00af:
            r0 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.mo29787w((java.lang.Throwable) r0)
        L_0x00b7:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ResHelper.contentUriToPath(android.content.Context, android.net.Uri):java.lang.String");
    }

    public static Uri videoPathToContentUri(Context context, String str) {
        try {
            if (!DeviceHelper.getInstance(context).checkPermission(Permission.READ_EXTERNAL_STORAGE)) {
                return null;
            }
            Cursor query = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=? ", new String[]{str}, (String) null);
            if (query != null && query.moveToFirst()) {
                int i = query.getInt(query.getColumnIndex("_id"));
                Uri parse = Uri.parse("content://media/external/video/media");
                return Uri.withAppendedPath(parse, "" + i);
            } else if (!new File(str).exists()) {
                return null;
            } else {
                ContentValues contentValues = new ContentValues();
                contentValues.put("_data", str);
                return context.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, contentValues);
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
        	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
        	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
        	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
        	at java.base/java.util.Objects.checkIndex(Objects.java:372)
        	at java.base/java.util.ArrayList.get(ArrayList.java:458)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:693)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:598)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    public static synchronized android.net.Uri getMediaUri(android.content.Context r6, java.lang.String r7, java.lang.String r8) {
        /*
            java.lang.Class<com.mob.tools.utils.ResHelper> r0 = com.mob.tools.utils.ResHelper.class
            monitor-enter(r0)
            java.lang.Object r1 = new java.lang.Object     // Catch:{ all -> 0x0032 }
            r1.<init>()     // Catch:{ all -> 0x0032 }
            r2 = 0
            mediaUri = r2     // Catch:{ all -> 0x0032 }
            r3 = 1
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ all -> 0x0032 }
            r5 = 0
            r4[r5] = r7     // Catch:{ all -> 0x0032 }
            java.lang.String[] r7 = new java.lang.String[r3]     // Catch:{ all -> 0x0032 }
            r7[r5] = r8     // Catch:{ all -> 0x0032 }
            com.mob.tools.utils.ResHelper$1 r8 = new com.mob.tools.utils.ResHelper$1     // Catch:{ all -> 0x0032 }
            r8.<init>(r1)     // Catch:{ all -> 0x0032 }
            android.media.MediaScannerConnection.scanFile(r6, r4, r7, r8)     // Catch:{ all -> 0x0032 }
            android.net.Uri r6 = mediaUri     // Catch:{ Throwable -> 0x002c }
            if (r6 != 0) goto L_0x002c
            monitor-enter(r1)     // Catch:{ Throwable -> 0x002c }
            r6 = 10000(0x2710, double:4.9407E-320)
            r1.wait(r6)     // Catch:{ all -> 0x0029 }
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            goto L_0x002c
        L_0x0029:
            r6 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0029 }
            throw r6     // Catch:{ Throwable -> 0x002c }
        L_0x002c:
            android.net.Uri r6 = mediaUri     // Catch:{ all -> 0x0032 }
            mediaUri = r2     // Catch:{ all -> 0x0032 }
            monitor-exit(r0)
            return r6
        L_0x0032:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ResHelper.getMediaUri(android.content.Context, java.lang.String, java.lang.String):android.net.Uri");
    }

    public static String encodeUrl(Bundle bundle) {
        if (bundle == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            if (obj == null) {
                obj = "";
            }
            if (z) {
                z = false;
            } else {
                sb.append("&");
            }
            sb.append(Data.urlEncode(str) + "=" + Data.urlEncode(String.valueOf(obj)));
        }
        return sb.toString();
    }

    public static String encodeUrl(ArrayList<KVPair<String>> arrayList) {
        if (arrayList == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Iterator<KVPair<String>> it = arrayList.iterator();
        while (it.hasNext()) {
            KVPair next = it.next();
            if (i > 0) {
                sb.append(Typography.amp);
            }
            String str = next.name;
            String str2 = (String) next.value;
            if (str != null) {
                if (str2 == null) {
                    str2 = "";
                }
                sb.append(Data.urlEncode(str) + "=" + Data.urlEncode(str2));
                i++;
            }
        }
        return sb.toString();
    }

    public static Bundle urlToBundle(String str) {
        String str2;
        int indexOf = str.indexOf("://");
        if (indexOf >= 0) {
            str2 = "http://" + str.substring(indexOf + 1);
        } else {
            str2 = "http://" + str;
        }
        try {
            URL url = new URL(str2);
            Bundle decodeUrl = decodeUrl(url.getQuery());
            decodeUrl.putAll(decodeUrl(url.getRef()));
            return decodeUrl;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return new Bundle();
        }
    }

    public static Bundle decodeUrl(String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            for (String split : str.split("&")) {
                String[] split2 = split.split("=");
                if (split2.length < 2 || split2[1] == null) {
                    bundle.putString(URLDecoder.decode(split2[0]), "");
                } else {
                    bundle.putString(URLDecoder.decode(split2[0]), URLDecoder.decode(split2[1]));
                }
            }
        }
        return bundle;
    }

    public static int parseInt(String str) throws Throwable {
        return parseInt(str, 10);
    }

    public static int parseInt(String str, int i) throws Throwable {
        return Integer.parseInt(str, i);
    }

    public static long parseLong(String str) throws Throwable {
        return parseLong(str, 10);
    }

    public static long parseLong(String str, int i) throws Throwable {
        return Long.parseLong(str, i);
    }

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static <T> T forceCast(Object obj) {
        return forceCast(obj, (Object) null);
    }

    public static <T> T forceCast(Object obj, T t) {
        if (obj == null) {
            return t;
        }
        boolean z = true;
        if (obj instanceof Byte) {
            byte byteValue = ((Byte) obj).byteValue();
            if (t instanceof Boolean) {
                if (byteValue == 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Short) {
                return Short.valueOf((short) byteValue);
            } else {
                if (t instanceof Character) {
                    return Character.valueOf((char) byteValue);
                }
                if (t instanceof Integer) {
                    return Integer.valueOf(byteValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) byteValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) byteValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) byteValue);
                }
                return obj;
            }
        } else if (obj instanceof Character) {
            char charValue = ((Character) obj).charValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) charValue);
            }
            if (t instanceof Boolean) {
                if (charValue == 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Short) {
                return Short.valueOf((short) charValue);
            } else {
                if (t instanceof Integer) {
                    return Integer.valueOf(charValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) charValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) charValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) charValue);
                }
                return obj;
            }
        } else if (obj instanceof Short) {
            short shortValue = ((Short) obj).shortValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) shortValue);
            }
            if (t instanceof Boolean) {
                if (shortValue == 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) shortValue);
            } else {
                if (t instanceof Integer) {
                    return Integer.valueOf(shortValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) shortValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) shortValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) shortValue);
                }
                return obj;
            }
        } else if (obj instanceof Integer) {
            int intValue = ((Integer) obj).intValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) intValue);
            }
            if (t instanceof Boolean) {
                if (intValue == 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) intValue);
            } else {
                if (t instanceof Short) {
                    return Short.valueOf((short) intValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) intValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) intValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) intValue);
                }
                return obj;
            }
        } else if (obj instanceof Float) {
            float floatValue = ((Float) obj).floatValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) ((int) floatValue));
            }
            if (t instanceof Boolean) {
                if (floatValue == 0.0f) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) ((int) floatValue));
            } else {
                if (t instanceof Short) {
                    return Short.valueOf((short) ((int) floatValue));
                }
                if (t instanceof Integer) {
                    return Integer.valueOf((int) floatValue);
                }
                if (t instanceof Long) {
                    return Long.valueOf((long) floatValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) floatValue);
                }
                return obj;
            }
        } else if (obj instanceof Long) {
            long longValue = ((Long) obj).longValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) ((int) longValue));
            }
            if (t instanceof Boolean) {
                if (longValue == 0) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) ((int) longValue));
            } else {
                if (t instanceof Short) {
                    return Short.valueOf((short) ((int) longValue));
                }
                if (t instanceof Integer) {
                    return Integer.valueOf((int) longValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) longValue);
                }
                if (t instanceof Double) {
                    return Double.valueOf((double) longValue);
                }
                return obj;
            }
        } else if (!(obj instanceof Double)) {
            return obj;
        } else {
            double doubleValue = ((Double) obj).doubleValue();
            if (t instanceof Byte) {
                return Byte.valueOf((byte) ((int) doubleValue));
            }
            if (t instanceof Boolean) {
                if (doubleValue == Utils.DOUBLE_EPSILON) {
                    z = false;
                }
                return Boolean.valueOf(z);
            } else if (t instanceof Character) {
                return Character.valueOf((char) ((int) doubleValue));
            } else {
                if (t instanceof Short) {
                    return Short.valueOf((short) ((int) doubleValue));
                }
                if (t instanceof Integer) {
                    return Integer.valueOf((int) doubleValue);
                }
                if (t instanceof Float) {
                    return Float.valueOf((float) doubleValue);
                }
                return t instanceof Long ? Long.valueOf((long) doubleValue) : obj;
            }
        }
    }

    public static boolean isLeapYear(int i) {
        return i % 400 == 0 || (i % 4 == 0 && i % 100 != 0);
    }

    public static <T> boolean isEqual(T t, T t2) {
        return !((t == null && t2 != null) || (t != null && !t.equals(t2)));
    }

    public static boolean copyFile(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !new File(str).exists()) {
            return false;
        }
        try {
            copyFile(new FileInputStream(str), new FileOutputStream(str2));
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void copyFile(FileInputStream fileInputStream, FileOutputStream fileOutputStream) throws Throwable {
        byte[] bArr = new byte[65536];
        int read = fileInputStream.read(bArr);
        while (read > 0) {
            fileOutputStream.write(bArr, 0, read);
            read = fileInputStream.read(bArr);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static long getFileSize(String str) throws Throwable {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return getFileSize(new File(str));
    }

    public static long getFileSize(File file) throws Throwable {
        if (!file.exists()) {
            return 0;
        }
        if (!file.isDirectory()) {
            return file.length();
        }
        String[] list = file.list();
        int i = 0;
        for (String file2 : list) {
            i = (int) (((long) i) + getFileSize(new File(file, file2)));
        }
        return (long) i;
    }

    public static boolean saveObjectToFile(String str, Object obj) {
        File file;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            file.createNewFile();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            file = null;
        }
        if (file == null) {
            return false;
        }
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream(file)));
            objectOutputStream.writeObject(obj);
            objectOutputStream.flush();
            objectOutputStream.close();
            return true;
        } catch (Throwable th2) {
            MobLog.getInstance().mo29769d(th2);
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (r0.exists() == false) goto L_0x001b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.Object readObjectFromFile(java.lang.String r2) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r2)
            r1 = 0
            if (r0 != 0) goto L_0x003d
            java.io.File r0 = new java.io.File     // Catch:{ Throwable -> 0x0013 }
            r0.<init>(r2)     // Catch:{ Throwable -> 0x0013 }
            boolean r2 = r0.exists()     // Catch:{ Throwable -> 0x0013 }
            if (r2 != 0) goto L_0x001c
            goto L_0x001b
        L_0x0013:
            r2 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            r0.mo29769d(r2)
        L_0x001b:
            r0 = r1
        L_0x001c:
            if (r0 == 0) goto L_0x003d
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Throwable -> 0x0035 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0035 }
            java.util.zip.GZIPInputStream r0 = new java.util.zip.GZIPInputStream     // Catch:{ Throwable -> 0x0035 }
            r0.<init>(r2)     // Catch:{ Throwable -> 0x0035 }
            java.io.ObjectInputStream r2 = new java.io.ObjectInputStream     // Catch:{ Throwable -> 0x0035 }
            r2.<init>(r0)     // Catch:{ Throwable -> 0x0035 }
            java.lang.Object r0 = r2.readObject()     // Catch:{ Throwable -> 0x0035 }
            r2.close()     // Catch:{ Throwable -> 0x0035 }
            return r0
        L_0x0035:
            r2 = move-exception
            com.mob.tools.log.NLog r0 = com.mob.tools.MobLog.getInstance()
            r0.mo29769d(r2)
        L_0x003d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ResHelper.readObjectFromFile(java.lang.String):java.lang.Object");
    }

    public static <T> T getStringValue(Context context, String str, T t) {
        try {
            String trim = context.getString(getStringRes(context, str)).trim();
            if (t == null) {
                return String.valueOf(trim);
            }
            Class<?> cls = t.getClass();
            if (cls.equals(String.class)) {
                return String.valueOf(trim);
            }
            if (!cls.equals(Integer.TYPE)) {
                if (!cls.equals(Integer.class)) {
                    if (!cls.equals(Long.TYPE)) {
                        if (!cls.equals(Long.class)) {
                            if (!cls.equals(Boolean.TYPE)) {
                                if (!cls.equals(Boolean.class)) {
                                    if (!cls.equals(Float.TYPE)) {
                                        if (!cls.equals(Float.class)) {
                                            if (!cls.equals(Double.TYPE)) {
                                                if (!cls.equals(Double.class)) {
                                                    if (!cls.equals(Character.TYPE)) {
                                                        if (!cls.equals(Character.class)) {
                                                            if (!cls.equals(Byte.TYPE)) {
                                                                if (!cls.equals(Byte.class)) {
                                                                    if (!cls.equals(Short.TYPE)) {
                                                                        if (!cls.equals(Short.class)) {
                                                                            return new Hashon().fromJson(trim, cls);
                                                                        }
                                                                    }
                                                                    return Short.valueOf(String.valueOf(trim));
                                                                }
                                                            }
                                                            return Byte.valueOf(String.valueOf(trim));
                                                        }
                                                    }
                                                    return Character.valueOf(String.valueOf(trim).charAt(0));
                                                }
                                            }
                                            return Double.valueOf(String.valueOf(trim));
                                        }
                                    }
                                    return Float.valueOf(String.valueOf(trim));
                                }
                            }
                            return Boolean.valueOf(trim);
                        }
                    }
                    return Long.valueOf(String.valueOf(trim));
                }
            }
            return Integer.valueOf(String.valueOf(trim));
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return t;
        }
    }
}
