package com.blankj.utilcode.util;

import android.util.Log;
import com.baidubce.BceConfig;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class ZipUtils {
    private static final int BUFFER_LEN = 8192;

    private ZipUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean zipFiles(Collection<String> collection, String str) throws IOException {
        return zipFiles(collection, str, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zipFiles(java.util.Collection<java.lang.String> r4, java.lang.String r5, java.lang.String r6) throws java.io.IOException {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x0049
            if (r5 != 0) goto L_0x0006
            goto L_0x0049
        L_0x0006:
            r1 = 0
            java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch:{ all -> 0x003e }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x003e }
            r3.<init>(r5)     // Catch:{ all -> 0x003e }
            r2.<init>(r3)     // Catch:{ all -> 0x003e }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x003c }
        L_0x0015:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x003c }
            if (r5 == 0) goto L_0x0034
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x003c }
            java.lang.String r5 = (java.lang.String) r5     // Catch:{ all -> 0x003c }
            java.io.File r5 = com.blankj.utilcode.util.UtilsBridge.getFileByPath(r5)     // Catch:{ all -> 0x003c }
            java.lang.String r1 = ""
            boolean r5 = zipFile(r5, r1, r2, r6)     // Catch:{ all -> 0x003c }
            if (r5 != 0) goto L_0x0015
            r2.finish()
            r2.close()
            return r0
        L_0x0034:
            r4 = 1
            r2.finish()
            r2.close()
            return r4
        L_0x003c:
            r4 = move-exception
            goto L_0x0040
        L_0x003e:
            r4 = move-exception
            r2 = r1
        L_0x0040:
            if (r2 == 0) goto L_0x0048
            r2.finish()
            r2.close()
        L_0x0048:
            throw r4
        L_0x0049:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ZipUtils.zipFiles(java.util.Collection, java.lang.String, java.lang.String):boolean");
    }

    public static boolean zipFiles(Collection<File> collection, File file) throws IOException {
        return zipFiles(collection, file, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zipFiles(java.util.Collection<java.io.File> r4, java.io.File r5, java.lang.String r6) throws java.io.IOException {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x0045
            if (r5 != 0) goto L_0x0006
            goto L_0x0045
        L_0x0006:
            r1 = 0
            java.util.zip.ZipOutputStream r2 = new java.util.zip.ZipOutputStream     // Catch:{ all -> 0x003a }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ all -> 0x003a }
            r3.<init>(r5)     // Catch:{ all -> 0x003a }
            r2.<init>(r3)     // Catch:{ all -> 0x003a }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0038 }
        L_0x0015:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x0038 }
            if (r5 == 0) goto L_0x0030
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x0038 }
            java.io.File r5 = (java.io.File) r5     // Catch:{ all -> 0x0038 }
            java.lang.String r1 = ""
            boolean r5 = zipFile(r5, r1, r2, r6)     // Catch:{ all -> 0x0038 }
            if (r5 != 0) goto L_0x0015
            r2.finish()
            r2.close()
            return r0
        L_0x0030:
            r4 = 1
            r2.finish()
            r2.close()
            return r4
        L_0x0038:
            r4 = move-exception
            goto L_0x003c
        L_0x003a:
            r4 = move-exception
            r2 = r1
        L_0x003c:
            if (r2 == 0) goto L_0x0044
            r2.finish()
            r2.close()
        L_0x0044:
            throw r4
        L_0x0045:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ZipUtils.zipFiles(java.util.Collection, java.io.File, java.lang.String):boolean");
    }

    public static boolean zipFile(String str, String str2) throws IOException {
        return zipFile(UtilsBridge.getFileByPath(str), UtilsBridge.getFileByPath(str2), (String) null);
    }

    public static boolean zipFile(String str, String str2, String str3) throws IOException {
        return zipFile(UtilsBridge.getFileByPath(str), UtilsBridge.getFileByPath(str2), str3);
    }

    public static boolean zipFile(File file, File file2) throws IOException {
        return zipFile(file, file2, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0020  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean zipFile(java.io.File r3, java.io.File r4, java.lang.String r5) throws java.io.IOException {
        /*
            if (r3 == 0) goto L_0x0024
            if (r4 != 0) goto L_0x0005
            goto L_0x0024
        L_0x0005:
            r0 = 0
            java.util.zip.ZipOutputStream r1 = new java.util.zip.ZipOutputStream     // Catch:{ all -> 0x001d }
            java.io.FileOutputStream r2 = new java.io.FileOutputStream     // Catch:{ all -> 0x001d }
            r2.<init>(r4)     // Catch:{ all -> 0x001d }
            r1.<init>(r2)     // Catch:{ all -> 0x001d }
            java.lang.String r4 = ""
            boolean r3 = zipFile(r3, r4, r1, r5)     // Catch:{ all -> 0x001a }
            r1.close()
            return r3
        L_0x001a:
            r3 = move-exception
            r0 = r1
            goto L_0x001e
        L_0x001d:
            r3 = move-exception
        L_0x001e:
            if (r0 == 0) goto L_0x0023
            r0.close()
        L_0x0023:
            throw r3
        L_0x0024:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ZipUtils.zipFile(java.io.File, java.io.File, java.lang.String):boolean");
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0095  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean zipFile(java.io.File r4, java.lang.String r5, java.util.zip.ZipOutputStream r6, java.lang.String r7) throws java.io.IOException {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            boolean r5 = com.blankj.utilcode.util.UtilsBridge.isSpace(r5)
            if (r5 == 0) goto L_0x0011
            java.lang.String r5 = ""
            goto L_0x0013
        L_0x0011:
            java.lang.String r5 = java.io.File.separator
        L_0x0013:
            r0.append(r5)
            java.lang.String r5 = r4.getName()
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            boolean r0 = r4.isDirectory()
            r1 = 0
            if (r0 == 0) goto L_0x0062
            java.io.File[] r4 = r4.listFiles()
            if (r4 == 0) goto L_0x0042
            int r0 = r4.length
            if (r0 > 0) goto L_0x0032
            goto L_0x0042
        L_0x0032:
            int r0 = r4.length
            r2 = 0
        L_0x0034:
            if (r2 >= r0) goto L_0x008d
            r3 = r4[r2]
            boolean r3 = zipFile(r3, r5, r6, r7)
            if (r3 != 0) goto L_0x003f
            return r1
        L_0x003f:
            int r2 = r2 + 1
            goto L_0x0034
        L_0x0042:
            java.util.zip.ZipEntry r4 = new java.util.zip.ZipEntry
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r5)
            r5 = 47
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            r4.<init>(r5)
            r4.setComment(r7)
            r6.putNextEntry(r4)
            r6.closeEntry()
            goto L_0x008d
        L_0x0062:
            r0 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ all -> 0x0091 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x0091 }
            r3.<init>(r4)     // Catch:{ all -> 0x0091 }
            r2.<init>(r3)     // Catch:{ all -> 0x0091 }
            java.util.zip.ZipEntry r4 = new java.util.zip.ZipEntry     // Catch:{ all -> 0x008f }
            r4.<init>(r5)     // Catch:{ all -> 0x008f }
            r4.setComment(r7)     // Catch:{ all -> 0x008f }
            r6.putNextEntry(r4)     // Catch:{ all -> 0x008f }
            r4 = 8192(0x2000, float:1.14794E-41)
            byte[] r5 = new byte[r4]     // Catch:{ all -> 0x008f }
        L_0x007c:
            int r7 = r2.read(r5, r1, r4)     // Catch:{ all -> 0x008f }
            r0 = -1
            if (r7 == r0) goto L_0x0087
            r6.write(r5, r1, r7)     // Catch:{ all -> 0x008f }
            goto L_0x007c
        L_0x0087:
            r6.closeEntry()     // Catch:{ all -> 0x008f }
            r2.close()
        L_0x008d:
            r4 = 1
            return r4
        L_0x008f:
            r4 = move-exception
            goto L_0x0093
        L_0x0091:
            r4 = move-exception
            r2 = r0
        L_0x0093:
            if (r2 == 0) goto L_0x0098
            r2.close()
        L_0x0098:
            goto L_0x009a
        L_0x0099:
            throw r4
        L_0x009a:
            goto L_0x0099
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ZipUtils.zipFile(java.io.File, java.lang.String, java.util.zip.ZipOutputStream, java.lang.String):boolean");
    }

    public static List<File> unzipFile(String str, String str2) throws IOException {
        return unzipFileByKeyword(str, str2, (String) null);
    }

    public static List<File> unzipFile(File file, File file2) throws IOException {
        return unzipFileByKeyword(file, file2, (String) null);
    }

    public static List<File> unzipFileByKeyword(String str, String str2, String str3) throws IOException {
        return unzipFileByKeyword(UtilsBridge.getFileByPath(str), UtilsBridge.getFileByPath(str2), str3);
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:17:0x0060=Splitter:B:17:0x0060, B:7:0x0026=Splitter:B:7:0x0026} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.io.File> unzipFileByKeyword(java.io.File r11, java.io.File r12, java.lang.String r13) throws java.io.IOException {
        /*
            if (r11 == 0) goto L_0x00a9
            if (r12 != 0) goto L_0x0006
            goto L_0x00a9
        L_0x0006:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.zip.ZipFile r1 = new java.util.zip.ZipFile
            r1.<init>(r11)
            java.util.Enumeration r11 = r1.entries()
            boolean r2 = com.blankj.utilcode.util.UtilsBridge.isSpace(r13)     // Catch:{ all -> 0x00a4 }
            java.lang.String r3 = " is dangerous!"
            java.lang.String r4 = "entryName: "
            java.lang.String r5 = "ZipUtils"
            java.lang.String r6 = "../"
            java.lang.String r7 = "/"
            java.lang.String r8 = "\\"
            if (r2 == 0) goto L_0x0060
        L_0x0026:
            boolean r13 = r11.hasMoreElements()     // Catch:{ all -> 0x00a4 }
            if (r13 == 0) goto L_0x00a0
            java.lang.Object r13 = r11.nextElement()     // Catch:{ all -> 0x00a4 }
            java.util.zip.ZipEntry r13 = (java.util.zip.ZipEntry) r13     // Catch:{ all -> 0x00a4 }
            java.lang.String r2 = r13.getName()     // Catch:{ all -> 0x00a4 }
            java.lang.String r2 = r2.replace(r8, r7)     // Catch:{ all -> 0x00a4 }
            boolean r9 = r2.contains(r6)     // Catch:{ all -> 0x00a4 }
            if (r9 == 0) goto L_0x0056
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a4 }
            r13.<init>()     // Catch:{ all -> 0x00a4 }
            r13.append(r4)     // Catch:{ all -> 0x00a4 }
            r13.append(r2)     // Catch:{ all -> 0x00a4 }
            r13.append(r3)     // Catch:{ all -> 0x00a4 }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x00a4 }
            android.util.Log.e(r5, r13)     // Catch:{ all -> 0x00a4 }
            goto L_0x0026
        L_0x0056:
            boolean r13 = unzipChildFile(r12, r0, r1, r13, r2)     // Catch:{ all -> 0x00a4 }
            if (r13 != 0) goto L_0x0026
            r1.close()
            return r0
        L_0x0060:
            boolean r2 = r11.hasMoreElements()     // Catch:{ all -> 0x00a4 }
            if (r2 == 0) goto L_0x00a0
            java.lang.Object r2 = r11.nextElement()     // Catch:{ all -> 0x00a4 }
            java.util.zip.ZipEntry r2 = (java.util.zip.ZipEntry) r2     // Catch:{ all -> 0x00a4 }
            java.lang.String r9 = r2.getName()     // Catch:{ all -> 0x00a4 }
            java.lang.String r9 = r9.replace(r8, r7)     // Catch:{ all -> 0x00a4 }
            boolean r10 = r9.contains(r6)     // Catch:{ all -> 0x00a4 }
            if (r10 == 0) goto L_0x0090
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a4 }
            r2.<init>()     // Catch:{ all -> 0x00a4 }
            r2.append(r4)     // Catch:{ all -> 0x00a4 }
            r2.append(r9)     // Catch:{ all -> 0x00a4 }
            r2.append(r3)     // Catch:{ all -> 0x00a4 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00a4 }
            android.util.Log.e(r5, r2)     // Catch:{ all -> 0x00a4 }
            goto L_0x0060
        L_0x0090:
            boolean r10 = r9.contains(r13)     // Catch:{ all -> 0x00a4 }
            if (r10 == 0) goto L_0x0060
            boolean r2 = unzipChildFile(r12, r0, r1, r2, r9)     // Catch:{ all -> 0x00a4 }
            if (r2 != 0) goto L_0x0060
            r1.close()
            return r0
        L_0x00a0:
            r1.close()
            return r0
        L_0x00a4:
            r11 = move-exception
            r1.close()
            throw r11
        L_0x00a9:
            r11 = 0
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ZipUtils.unzipFileByKeyword(java.io.File, java.io.File, java.lang.String):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0055  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean unzipChildFile(java.io.File r1, java.util.List<java.io.File> r2, java.util.zip.ZipFile r3, java.util.zip.ZipEntry r4, java.lang.String r5) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r1, r5)
            r2.add(r0)
            boolean r1 = r4.isDirectory()
            if (r1 == 0) goto L_0x0013
            boolean r1 = com.blankj.utilcode.util.UtilsBridge.createOrExistsDir(r0)
            return r1
        L_0x0013:
            boolean r1 = com.blankj.utilcode.util.UtilsBridge.createOrExistsFile(r0)
            r2 = 0
            if (r1 != 0) goto L_0x001b
            return r2
        L_0x001b:
            r1 = 0
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ all -> 0x004c }
            java.io.InputStream r3 = r3.getInputStream(r4)     // Catch:{ all -> 0x004c }
            r5.<init>(r3)     // Catch:{ all -> 0x004c }
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ all -> 0x004a }
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch:{ all -> 0x004a }
            r4.<init>(r0)     // Catch:{ all -> 0x004a }
            r3.<init>(r4)     // Catch:{ all -> 0x004a }
            r1 = 8192(0x2000, float:1.14794E-41)
            byte[] r1 = new byte[r1]     // Catch:{ all -> 0x0046 }
        L_0x0033:
            int r4 = r5.read(r1)     // Catch:{ all -> 0x0046 }
            r0 = -1
            if (r4 == r0) goto L_0x003e
            r3.write(r1, r2, r4)     // Catch:{ all -> 0x0046 }
            goto L_0x0033
        L_0x003e:
            r5.close()
            r3.close()
            r1 = 1
            return r1
        L_0x0046:
            r1 = move-exception
            r2 = r1
            r1 = r3
            goto L_0x004e
        L_0x004a:
            r2 = move-exception
            goto L_0x004e
        L_0x004c:
            r2 = move-exception
            r5 = r1
        L_0x004e:
            if (r5 == 0) goto L_0x0053
            r5.close()
        L_0x0053:
            if (r1 == 0) goto L_0x0058
            r1.close()
        L_0x0058:
            goto L_0x005a
        L_0x0059:
            throw r2
        L_0x005a:
            goto L_0x0059
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ZipUtils.unzipChildFile(java.io.File, java.util.List, java.util.zip.ZipFile, java.util.zip.ZipEntry, java.lang.String):boolean");
    }

    public static List<String> getFilesPath(String str) throws IOException {
        return getFilesPath(UtilsBridge.getFileByPath(str));
    }

    public static List<String> getFilesPath(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            String replace = ((ZipEntry) entries.nextElement()).getName().replace("\\", BceConfig.BOS_DELIMITER);
            if (replace.contains("../")) {
                Log.e("ZipUtils", "entryName: " + replace + " is dangerous!");
                arrayList.add(replace);
            } else {
                arrayList.add(replace);
            }
        }
        zipFile.close();
        return arrayList;
    }

    public static List<String> getComments(String str) throws IOException {
        return getComments(UtilsBridge.getFileByPath(str));
    }

    public static List<String> getComments(File file) throws IOException {
        if (file == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            arrayList.add(((ZipEntry) entries.nextElement()).getComment());
        }
        zipFile.close();
        return arrayList;
    }
}
