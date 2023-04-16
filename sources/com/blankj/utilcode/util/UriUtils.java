package com.blankj.utilcode.util;

import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import com.baidubce.BceConfig;
import java.io.File;

public final class UriUtils {
    private UriUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Uri res2Uri(String str) {
        return Uri.parse("android.resource://" + Utils.getApp().getPackageName() + BceConfig.BOS_DELIMITER + str);
    }

    public static Uri file2Uri(File file) {
        if (!UtilsBridge.isFileExists(file)) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 24) {
            return Uri.fromFile(file);
        }
        return FileProvider.getUriForFile(Utils.getApp(), Utils.getApp().getPackageName() + ".utilcode.provider", file);
    }

    public static File uri2File(Uri uri) {
        if (uri == null) {
            return null;
        }
        File uri2FileReal = uri2FileReal(uri);
        if (uri2FileReal != null) {
            return uri2FileReal;
        }
        return copyUri2Cache(uri);
    }

    /* JADX WARNING: Removed duplicated region for block: B:60:0x0245 A[Catch:{ Exception -> 0x029a }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0246 A[Catch:{ Exception -> 0x029a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.io.File uri2FileReal(android.net.Uri r17) {
        /*
            r1 = r17
            java.lang.String r0 = r17.toString()
            java.lang.String r2 = "UriUtils"
            android.util.Log.d(r2, r0)
            java.lang.String r0 = r17.getAuthority()
            java.lang.String r3 = r17.getScheme()
            java.lang.String r4 = r17.getPath()
            int r5 = android.os.Build.VERSION.SDK_INT
            java.lang.String r6 = "/"
            r7 = 0
            r8 = 0
            r9 = 24
            if (r5 < r9) goto L_0x0154
            if (r4 == 0) goto L_0x0154
            java.lang.String r5 = "/external/"
            java.lang.String r9 = "/external_path/"
            java.lang.String[] r5 = new java.lang.String[]{r5, r9}
            int r9 = r5.length
            r10 = 0
        L_0x002d:
            java.lang.String r11 = " -> "
            if (r10 >= r9) goto L_0x007c
            r12 = r5[r10]
            boolean r13 = r4.startsWith(r12)
            if (r13 == 0) goto L_0x0079
            java.io.File r13 = new java.io.File
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.io.File r15 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String r15 = r15.getAbsolutePath()
            r14.append(r15)
            java.lang.String r15 = r4.replace(r12, r6)
            r14.append(r15)
            java.lang.String r14 = r14.toString()
            r13.<init>(r14)
            boolean r14 = r13.exists()
            if (r14 == 0) goto L_0x0079
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r17.toString()
            r0.append(r1)
            r0.append(r11)
            r0.append(r12)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r2, r0)
            return r13
        L_0x0079:
            int r10 = r10 + 1
            goto L_0x002d
        L_0x007c:
            java.lang.String r5 = "/files_path/"
            boolean r9 = r4.startsWith(r5)
            if (r9 == 0) goto L_0x00aa
            java.io.File r9 = new java.io.File
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            android.app.Application r12 = com.blankj.utilcode.util.Utils.getApp()
            java.io.File r12 = r12.getFilesDir()
            java.lang.String r12 = r12.getAbsolutePath()
            r10.append(r12)
            java.lang.String r5 = r4.replace(r5, r6)
            r10.append(r5)
            java.lang.String r5 = r10.toString()
            r9.<init>(r5)
            goto L_0x0132
        L_0x00aa:
            java.lang.String r5 = "/cache_path/"
            boolean r9 = r4.startsWith(r5)
            if (r9 == 0) goto L_0x00d7
            java.io.File r9 = new java.io.File
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            android.app.Application r12 = com.blankj.utilcode.util.Utils.getApp()
            java.io.File r12 = r12.getCacheDir()
            java.lang.String r12 = r12.getAbsolutePath()
            r10.append(r12)
            java.lang.String r5 = r4.replace(r5, r6)
            r10.append(r5)
            java.lang.String r5 = r10.toString()
            r9.<init>(r5)
            goto L_0x0132
        L_0x00d7:
            java.lang.String r5 = "/external_files_path/"
            boolean r9 = r4.startsWith(r5)
            if (r9 == 0) goto L_0x0104
            java.io.File r9 = new java.io.File
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            android.app.Application r12 = com.blankj.utilcode.util.Utils.getApp()
            java.io.File r12 = r12.getExternalFilesDir(r7)
            java.lang.String r12 = r12.getAbsolutePath()
            r10.append(r12)
            java.lang.String r5 = r4.replace(r5, r6)
            r10.append(r5)
            java.lang.String r5 = r10.toString()
            r9.<init>(r5)
            goto L_0x0132
        L_0x0104:
            java.lang.String r5 = "/external_cache_path/"
            boolean r9 = r4.startsWith(r5)
            if (r9 == 0) goto L_0x0131
            java.io.File r9 = new java.io.File
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            android.app.Application r12 = com.blankj.utilcode.util.Utils.getApp()
            java.io.File r12 = r12.getExternalCacheDir()
            java.lang.String r12 = r12.getAbsolutePath()
            r10.append(r12)
            java.lang.String r5 = r4.replace(r5, r6)
            r10.append(r5)
            java.lang.String r5 = r10.toString()
            r9.<init>(r5)
            goto L_0x0132
        L_0x0131:
            r9 = r7
        L_0x0132:
            if (r9 == 0) goto L_0x0154
            boolean r5 = r9.exists()
            if (r5 == 0) goto L_0x0154
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r17.toString()
            r0.append(r1)
            r0.append(r11)
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r2, r0)
            return r9
        L_0x0154:
            java.lang.String r5 = "file"
            boolean r5 = r5.equals(r3)
            if (r5 == 0) goto L_0x017d
            if (r4 == 0) goto L_0x0164
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            return r0
        L_0x0164:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r17.toString()
            r0.append(r1)
            java.lang.String r1 = " parse failed. -> 0"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r2, r0)
            return r7
        L_0x017d:
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 19
            java.lang.String r9 = "content"
            if (r4 < r5) goto L_0x03ee
            android.app.Application r4 = com.blankj.utilcode.util.Utils.getApp()
            boolean r4 = android.provider.DocumentsContract.isDocumentUri(r4, r1)
            if (r4 == 0) goto L_0x03ee
            java.lang.String r4 = "com.android.externalstorage.documents"
            boolean r4 = r4.equals(r0)
            java.lang.String r5 = ":"
            r10 = 1
            if (r4 == 0) goto L_0x02d9
            java.lang.String r0 = android.provider.DocumentsContract.getDocumentId(r17)
            java.lang.String[] r0 = r0.split(r5)
            r3 = r0[r8]
            java.lang.String r4 = "primary"
            boolean r4 = r4.equalsIgnoreCase(r3)
            if (r4 == 0) goto L_0x01ca
            java.io.File r1 = new java.io.File
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.io.File r3 = android.os.Environment.getExternalStorageDirectory()
            r2.append(r3)
            r2.append(r6)
            r0 = r0[r10]
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            return r1
        L_0x01ca:
            android.app.Application r4 = com.blankj.utilcode.util.Utils.getApp()
            java.lang.String r5 = "storage"
            java.lang.Object r4 = r4.getSystemService(r5)
            android.os.storage.StorageManager r4 = (android.os.storage.StorageManager) r4
            java.lang.String r5 = "android.os.storage.StorageVolume"
            java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch:{ Exception -> 0x029a }
            java.lang.Class r9 = r4.getClass()     // Catch:{ Exception -> 0x029a }
            java.lang.String r11 = "getVolumeList"
            java.lang.Class[] r12 = new java.lang.Class[r8]     // Catch:{ Exception -> 0x029a }
            java.lang.reflect.Method r9 = r9.getMethod(r11, r12)     // Catch:{ Exception -> 0x029a }
            java.lang.String r11 = "getUuid"
            java.lang.Class[] r12 = new java.lang.Class[r8]     // Catch:{ Exception -> 0x029a }
            java.lang.reflect.Method r11 = r5.getMethod(r11, r12)     // Catch:{ Exception -> 0x029a }
            java.lang.String r12 = "getState"
            java.lang.Class[] r13 = new java.lang.Class[r8]     // Catch:{ Exception -> 0x029a }
            java.lang.reflect.Method r12 = r5.getMethod(r12, r13)     // Catch:{ Exception -> 0x029a }
            java.lang.String r13 = "getPath"
            java.lang.Class[] r14 = new java.lang.Class[r8]     // Catch:{ Exception -> 0x029a }
            java.lang.reflect.Method r13 = r5.getMethod(r13, r14)     // Catch:{ Exception -> 0x029a }
            java.lang.String r14 = "isPrimary"
            java.lang.Class[] r15 = new java.lang.Class[r8]     // Catch:{ Exception -> 0x029a }
            java.lang.reflect.Method r14 = r5.getMethod(r14, r15)     // Catch:{ Exception -> 0x029a }
            java.lang.String r15 = "isEmulated"
            java.lang.Class[] r7 = new java.lang.Class[r8]     // Catch:{ Exception -> 0x029a }
            java.lang.reflect.Method r5 = r5.getMethod(r15, r7)     // Catch:{ Exception -> 0x029a }
            java.lang.Object[] r7 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x029a }
            java.lang.Object r4 = r9.invoke(r4, r7)     // Catch:{ Exception -> 0x029a }
            int r7 = java.lang.reflect.Array.getLength(r4)     // Catch:{ Exception -> 0x029a }
            r9 = 0
        L_0x021b:
            if (r9 >= r7) goto L_0x02bf
            java.lang.Object r15 = java.lang.reflect.Array.get(r4, r9)     // Catch:{ Exception -> 0x029a }
            java.lang.String r10 = "mounted"
            r16 = r4
            java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x029a }
            java.lang.Object r4 = r12.invoke(r15, r4)     // Catch:{ Exception -> 0x029a }
            boolean r4 = r10.equals(r4)     // Catch:{ Exception -> 0x029a }
            if (r4 != 0) goto L_0x0242
            java.lang.String r4 = "mounted_ro"
            java.lang.Object[] r10 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x029a }
            java.lang.Object r10 = r12.invoke(r15, r10)     // Catch:{ Exception -> 0x029a }
            boolean r4 = r4.equals(r10)     // Catch:{ Exception -> 0x029a }
            if (r4 == 0) goto L_0x0240
            goto L_0x0242
        L_0x0240:
            r4 = 0
            goto L_0x0243
        L_0x0242:
            r4 = 1
        L_0x0243:
            if (r4 != 0) goto L_0x0246
            goto L_0x0294
        L_0x0246:
            java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x029a }
            java.lang.Object r4 = r14.invoke(r15, r4)     // Catch:{ Exception -> 0x029a }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ Exception -> 0x029a }
            boolean r4 = r4.booleanValue()     // Catch:{ Exception -> 0x029a }
            if (r4 == 0) goto L_0x0263
            java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x029a }
            java.lang.Object r4 = r5.invoke(r15, r4)     // Catch:{ Exception -> 0x029a }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ Exception -> 0x029a }
            boolean r4 = r4.booleanValue()     // Catch:{ Exception -> 0x029a }
            if (r4 == 0) goto L_0x0263
            goto L_0x0294
        L_0x0263:
            java.lang.Object[] r4 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x029a }
            java.lang.Object r4 = r11.invoke(r15, r4)     // Catch:{ Exception -> 0x029a }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x029a }
            if (r4 == 0) goto L_0x0294
            boolean r4 = r4.equals(r3)     // Catch:{ Exception -> 0x029a }
            if (r4 == 0) goto L_0x0294
            java.io.File r3 = new java.io.File     // Catch:{ Exception -> 0x029a }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x029a }
            r4.<init>()     // Catch:{ Exception -> 0x029a }
            java.lang.Object[] r5 = new java.lang.Object[r8]     // Catch:{ Exception -> 0x029a }
            java.lang.Object r5 = r13.invoke(r15, r5)     // Catch:{ Exception -> 0x029a }
            r4.append(r5)     // Catch:{ Exception -> 0x029a }
            r4.append(r6)     // Catch:{ Exception -> 0x029a }
            r5 = 1
            r0 = r0[r5]     // Catch:{ Exception -> 0x029a }
            r4.append(r0)     // Catch:{ Exception -> 0x029a }
            java.lang.String r0 = r4.toString()     // Catch:{ Exception -> 0x029a }
            r3.<init>(r0)     // Catch:{ Exception -> 0x029a }
            return r3
        L_0x0294:
            int r9 = r9 + 1
            r4 = r16
            r10 = 1
            goto L_0x021b
        L_0x029a:
            r0 = move-exception
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r17.toString()
            r3.append(r4)
            java.lang.String r4 = " parse failed. "
            r3.append(r4)
            java.lang.String r0 = r0.toString()
            r3.append(r0)
            java.lang.String r0 = " -> 1_0"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            android.util.Log.d(r2, r0)
        L_0x02bf:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r17.toString()
            r0.append(r1)
            java.lang.String r1 = " parse failed. -> 1_0"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r2, r0)
            r1 = 0
            return r1
        L_0x02d9:
            java.lang.String r4 = "com.android.providers.downloads.documents"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x036a
            java.lang.String r0 = android.provider.DocumentsContract.getDocumentId(r17)
            boolean r3 = android.text.TextUtils.isEmpty(r0)
            if (r3 == 0) goto L_0x0305
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r17.toString()
            r0.append(r1)
            java.lang.String r1 = " parse failed(id is null). -> 1_1"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r2, r0)
            r1 = 0
            return r1
        L_0x0305:
            java.lang.String r3 = "raw:"
            boolean r3 = r0.startsWith(r3)
            if (r3 == 0) goto L_0x0318
            java.io.File r1 = new java.io.File
            r2 = 4
            java.lang.String r0 = r0.substring(r2)
            r1.<init>(r0)
            return r1
        L_0x0318:
            java.lang.String r3 = "msf:"
            boolean r3 = r0.startsWith(r3)
            if (r3 == 0) goto L_0x0327
            java.lang.String[] r0 = r0.split(r5)
            r3 = 1
            r0 = r0[r3]
        L_0x0327:
            long r3 = java.lang.Long.parseLong(r0)     // Catch:{ Exception -> 0x0368 }
            java.lang.String r0 = "content://downloads/public_downloads"
            java.lang.String r5 = "content://downloads/all_downloads"
            java.lang.String r6 = "content://downloads/my_downloads"
            java.lang.String[] r0 = new java.lang.String[]{r0, r5, r6}
            int r5 = r0.length
        L_0x0336:
            if (r8 >= r5) goto L_0x034e
            r6 = r0[r8]
            android.net.Uri r6 = android.net.Uri.parse(r6)
            android.net.Uri r6 = android.content.ContentUris.withAppendedId(r6, r3)
            java.lang.String r7 = "1_1"
            java.io.File r6 = getFileFromUri(r6, r7)     // Catch:{ Exception -> 0x034b }
            if (r6 == 0) goto L_0x034b
            return r6
        L_0x034b:
            int r8 = r8 + 1
            goto L_0x0336
        L_0x034e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r17.toString()
            r0.append(r1)
            java.lang.String r1 = " parse failed. -> 1_1"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r2, r0)
            r1 = 0
            return r1
        L_0x0368:
            r1 = 0
            return r1
        L_0x036a:
            java.lang.String r4 = "com.android.providers.media.documents"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x03c7
            java.lang.String r0 = android.provider.DocumentsContract.getDocumentId(r17)
            java.lang.String[] r0 = r0.split(r5)
            r3 = r0[r8]
            java.lang.String r4 = "image"
            boolean r4 = r4.equals(r3)
            if (r4 == 0) goto L_0x0388
            android.net.Uri r1 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        L_0x0386:
            r2 = 1
            goto L_0x039e
        L_0x0388:
            java.lang.String r4 = "video"
            boolean r4 = r4.equals(r3)
            if (r4 == 0) goto L_0x0393
            android.net.Uri r1 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            goto L_0x0386
        L_0x0393:
            java.lang.String r4 = "audio"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L_0x03ad
            android.net.Uri r1 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
            goto L_0x0386
        L_0x039e:
            java.lang.String[] r3 = new java.lang.String[r2]
            r0 = r0[r2]
            r3[r8] = r0
            java.lang.String r0 = "_id=?"
            java.lang.String r2 = "1_2"
            java.io.File r0 = getFileFromUri(r1, r0, r3, r2)
            return r0
        L_0x03ad:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r17.toString()
            r0.append(r1)
            java.lang.String r1 = " parse failed. -> 1_2"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r2, r0)
            r1 = 0
            return r1
        L_0x03c7:
            boolean r0 = r9.equals(r3)
            if (r0 == 0) goto L_0x03d4
            java.lang.String r0 = "1_3"
            java.io.File r0 = getFileFromUri(r1, r0)
            return r0
        L_0x03d4:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r17.toString()
            r0.append(r1)
            java.lang.String r1 = " parse failed. -> 1_4"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r2, r0)
            r1 = 0
            return r1
        L_0x03ee:
            boolean r0 = r9.equals(r3)
            if (r0 == 0) goto L_0x03fb
            java.lang.String r0 = "2"
            java.io.File r0 = getFileFromUri(r1, r0)
            return r0
        L_0x03fb:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r17.toString()
            r0.append(r1)
            java.lang.String r1 = " parse failed. -> 3"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r2, r0)
            r1 = 0
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.UriUtils.uri2FileReal(android.net.Uri):java.io.File");
    }

    private static File getFileFromUri(Uri uri, String str) {
        return getFileFromUri(uri, (String) null, (String[]) null, str);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:22|23|(2:25|(3:27|28|29)(4:30|31|32|33))(4:34|35|36|37)|39|40|41|42) */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0105, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0126, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0129, code lost:
        throw r7;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x0107 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.io.File getFileFromUri(android.net.Uri r7, java.lang.String r8, java.lang.String[] r9, java.lang.String r10) {
        /*
            java.lang.String r0 = r7.getAuthority()
            java.lang.String r1 = "com.google.android.apps.photos.content"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x0020
            java.lang.String r0 = r7.getLastPathSegment()
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 != 0) goto L_0x006e
            java.io.File r8 = new java.io.File
            java.lang.String r7 = r7.getLastPathSegment()
            r8.<init>(r7)
            return r8
        L_0x0020:
            java.lang.String r0 = r7.getAuthority()
            java.lang.String r1 = "com.tencent.mtt.fileprovider"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x004a
            java.lang.String r0 = r7.getPath()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x006e
            java.io.File r7 = android.os.Environment.getExternalStorageDirectory()
            java.io.File r8 = new java.io.File
            r9 = 10
            int r10 = r0.length()
            java.lang.String r9 = r0.substring(r9, r10)
            r8.<init>(r7, r9)
            return r8
        L_0x004a:
            java.lang.String r0 = r7.getAuthority()
            java.lang.String r1 = "com.huawei.hidisk.fileprovider"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x006e
            java.lang.String r0 = r7.getPath()
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x006e
            java.io.File r7 = new java.io.File
            java.lang.String r8 = "/root"
            java.lang.String r9 = ""
            java.lang.String r8 = r0.replace(r8, r9)
            r7.<init>(r8)
            return r7
        L_0x006e:
            android.app.Application r0 = com.blankj.utilcode.util.Utils.getApp()
            android.content.ContentResolver r1 = r0.getContentResolver()
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r6 = 0
            r2 = r7
            r4 = r8
            r5 = r9
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)
            r9 = 0
            java.lang.String r1 = "UriUtils"
            if (r8 != 0) goto L_0x00a5
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r7 = r7.toString()
            r8.append(r7)
            java.lang.String r7 = " parse failed(cursor is null). -> "
            r8.append(r7)
            r8.append(r10)
            java.lang.String r7 = r8.toString()
            android.util.Log.d(r1, r7)
            return r9
        L_0x00a5:
            boolean r2 = r8.moveToFirst()     // Catch:{ Exception -> 0x0107 }
            if (r2 == 0) goto L_0x00e6
            int r0 = r8.getColumnIndex(r0)     // Catch:{ Exception -> 0x0107 }
            r2 = -1
            if (r0 <= r2) goto L_0x00bf
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x0107 }
            java.lang.String r0 = r8.getString(r0)     // Catch:{ Exception -> 0x0107 }
            r2.<init>(r0)     // Catch:{ Exception -> 0x0107 }
            r8.close()
            return r2
        L_0x00bf:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0107 }
            r2.<init>()     // Catch:{ Exception -> 0x0107 }
            java.lang.String r3 = r7.toString()     // Catch:{ Exception -> 0x0107 }
            r2.append(r3)     // Catch:{ Exception -> 0x0107 }
            java.lang.String r3 = " parse failed(columnIndex: "
            r2.append(r3)     // Catch:{ Exception -> 0x0107 }
            r2.append(r0)     // Catch:{ Exception -> 0x0107 }
            java.lang.String r0 = " is wrong). -> "
            r2.append(r0)     // Catch:{ Exception -> 0x0107 }
            r2.append(r10)     // Catch:{ Exception -> 0x0107 }
            java.lang.String r0 = r2.toString()     // Catch:{ Exception -> 0x0107 }
            android.util.Log.d(r1, r0)     // Catch:{ Exception -> 0x0107 }
            r8.close()
            return r9
        L_0x00e6:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0107 }
            r0.<init>()     // Catch:{ Exception -> 0x0107 }
            java.lang.String r2 = r7.toString()     // Catch:{ Exception -> 0x0107 }
            r0.append(r2)     // Catch:{ Exception -> 0x0107 }
            java.lang.String r2 = " parse failed(moveToFirst return false). -> "
            r0.append(r2)     // Catch:{ Exception -> 0x0107 }
            r0.append(r10)     // Catch:{ Exception -> 0x0107 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0107 }
            android.util.Log.d(r1, r0)     // Catch:{ Exception -> 0x0107 }
            r8.close()
            return r9
        L_0x0105:
            r7 = move-exception
            goto L_0x0126
        L_0x0107:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0105 }
            r0.<init>()     // Catch:{ all -> 0x0105 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0105 }
            r0.append(r7)     // Catch:{ all -> 0x0105 }
            java.lang.String r7 = " parse failed. -> "
            r0.append(r7)     // Catch:{ all -> 0x0105 }
            r0.append(r10)     // Catch:{ all -> 0x0105 }
            java.lang.String r7 = r0.toString()     // Catch:{ all -> 0x0105 }
            android.util.Log.d(r1, r7)     // Catch:{ all -> 0x0105 }
            r8.close()
            return r9
        L_0x0126:
            r8.close()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.UriUtils.getFileFromUri(android.net.Uri, java.lang.String, java.lang.String[], java.lang.String):java.io.File");
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0056 A[SYNTHETIC, Splitter:B:19:0x0056] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0062 A[SYNTHETIC, Splitter:B:26:0x0062] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.io.File copyUri2Cache(android.net.Uri r7) {
        /*
            java.lang.String r0 = "UriUtils"
            java.lang.String r1 = "copyUri2Cache() called"
            android.util.Log.d(r0, r1)
            r0 = 0
            android.app.Application r1 = com.blankj.utilcode.util.Utils.getApp()     // Catch:{ FileNotFoundException -> 0x004f, all -> 0x004a }
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ FileNotFoundException -> 0x004f, all -> 0x004a }
            java.io.InputStream r7 = r1.openInputStream(r7)     // Catch:{ FileNotFoundException -> 0x004f, all -> 0x004a }
            java.io.File r1 = new java.io.File     // Catch:{ FileNotFoundException -> 0x0048 }
            android.app.Application r2 = com.blankj.utilcode.util.Utils.getApp()     // Catch:{ FileNotFoundException -> 0x0048 }
            java.io.File r2 = r2.getCacheDir()     // Catch:{ FileNotFoundException -> 0x0048 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ FileNotFoundException -> 0x0048 }
            r3.<init>()     // Catch:{ FileNotFoundException -> 0x0048 }
            java.lang.String r4 = ""
            r3.append(r4)     // Catch:{ FileNotFoundException -> 0x0048 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ FileNotFoundException -> 0x0048 }
            r3.append(r4)     // Catch:{ FileNotFoundException -> 0x0048 }
            java.lang.String r3 = r3.toString()     // Catch:{ FileNotFoundException -> 0x0048 }
            r1.<init>(r2, r3)     // Catch:{ FileNotFoundException -> 0x0048 }
            java.lang.String r2 = r1.getAbsolutePath()     // Catch:{ FileNotFoundException -> 0x0048 }
            com.blankj.utilcode.util.UtilsBridge.writeFileFromIS(r2, r7)     // Catch:{ FileNotFoundException -> 0x0048 }
            if (r7 == 0) goto L_0x0047
            r7.close()     // Catch:{ IOException -> 0x0043 }
            goto L_0x0047
        L_0x0043:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0047:
            return r1
        L_0x0048:
            r1 = move-exception
            goto L_0x0051
        L_0x004a:
            r7 = move-exception
            r6 = r0
            r0 = r7
            r7 = r6
            goto L_0x0060
        L_0x004f:
            r1 = move-exception
            r7 = r0
        L_0x0051:
            r1.printStackTrace()     // Catch:{ all -> 0x005f }
            if (r7 == 0) goto L_0x005e
            r7.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r7 = move-exception
            r7.printStackTrace()
        L_0x005e:
            return r0
        L_0x005f:
            r0 = move-exception
        L_0x0060:
            if (r7 == 0) goto L_0x006a
            r7.close()     // Catch:{ IOException -> 0x0066 }
            goto L_0x006a
        L_0x0066:
            r7 = move-exception
            r7.printStackTrace()
        L_0x006a:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.UriUtils.copyUri2Cache(android.net.Uri):java.io.File");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0034 A[SYNTHETIC, Splitter:B:21:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0040 A[SYNTHETIC, Splitter:B:28:0x0040] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] uri2Bytes(android.net.Uri r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            android.app.Application r1 = com.blankj.utilcode.util.Utils.getApp()     // Catch:{ FileNotFoundException -> 0x0026, all -> 0x0021 }
            android.content.ContentResolver r1 = r1.getContentResolver()     // Catch:{ FileNotFoundException -> 0x0026, all -> 0x0021 }
            java.io.InputStream r4 = r1.openInputStream(r4)     // Catch:{ FileNotFoundException -> 0x0026, all -> 0x0021 }
            byte[] r0 = com.blankj.utilcode.util.UtilsBridge.inputStream2Bytes(r4)     // Catch:{ FileNotFoundException -> 0x001f }
            if (r4 == 0) goto L_0x001e
            r4.close()     // Catch:{ IOException -> 0x001a }
            goto L_0x001e
        L_0x001a:
            r4 = move-exception
            r4.printStackTrace()
        L_0x001e:
            return r0
        L_0x001f:
            r1 = move-exception
            goto L_0x0028
        L_0x0021:
            r4 = move-exception
            r3 = r0
            r0 = r4
            r4 = r3
            goto L_0x003e
        L_0x0026:
            r1 = move-exception
            r4 = r0
        L_0x0028:
            r1.printStackTrace()     // Catch:{ all -> 0x003d }
            java.lang.String r1 = "UriUtils"
            java.lang.String r2 = "uri to bytes failed."
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x003d }
            if (r4 == 0) goto L_0x003c
            r4.close()     // Catch:{ IOException -> 0x0038 }
            goto L_0x003c
        L_0x0038:
            r4 = move-exception
            r4.printStackTrace()
        L_0x003c:
            return r0
        L_0x003d:
            r0 = move-exception
        L_0x003e:
            if (r4 == 0) goto L_0x0048
            r4.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0048:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.UriUtils.uri2Bytes(android.net.Uri):byte[]");
    }
}
