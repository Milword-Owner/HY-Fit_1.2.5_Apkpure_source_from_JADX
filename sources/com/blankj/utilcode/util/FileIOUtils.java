package com.blankj.utilcode.util;

import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

public final class FileIOUtils {
    private static int sBufferSize = 524288;

    public interface OnProgressUpdateListener {
        void onProgressUpdate(double d);
    }

    private FileIOUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream) {
        return writeFileFromIS(UtilsBridge.getFileByPath(str), inputStream, false, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream, boolean z) {
        return writeFileFromIS(UtilsBridge.getFileByPath(str), inputStream, z, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream) {
        return writeFileFromIS(file, inputStream, false, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream, boolean z) {
        return writeFileFromIS(file, inputStream, z, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromIS(UtilsBridge.getFileByPath(str), inputStream, false, onProgressUpdateListener);
    }

    public static boolean writeFileFromIS(String str, InputStream inputStream, boolean z, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromIS(UtilsBridge.getFileByPath(str), inputStream, z, onProgressUpdateListener);
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromIS(file, inputStream, false, onProgressUpdateListener);
    }

    /* JADX WARNING: Removed duplicated region for block: B:54:0x0089 A[SYNTHETIC, Splitter:B:54:0x0089] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean writeFileFromIS(java.io.File r7, java.io.InputStream r8, boolean r9, com.blankj.utilcode.util.FileIOUtils.OnProgressUpdateListener r10) {
        /*
            r0 = 0
            if (r8 == 0) goto L_0x0092
            boolean r1 = com.blankj.utilcode.util.UtilsBridge.createOrExistsFile(r7)
            if (r1 != 0) goto L_0x000b
            goto L_0x0092
        L_0x000b:
            r1 = 0
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch:{ IOException -> 0x0068 }
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0068 }
            r3.<init>(r7, r9)     // Catch:{ IOException -> 0x0068 }
            int r7 = sBufferSize     // Catch:{ IOException -> 0x0068 }
            r2.<init>(r3, r7)     // Catch:{ IOException -> 0x0068 }
            r7 = -1
            if (r10 != 0) goto L_0x0029
            int r9 = sBufferSize     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            byte[] r9 = new byte[r9]     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
        L_0x001f:
            int r10 = r8.read(r9)     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            if (r10 == r7) goto L_0x004e
            r2.write(r9, r0, r10)     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            goto L_0x001f
        L_0x0029:
            int r9 = r8.available()     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            double r3 = (double) r9     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            r5 = 0
            r10.onProgressUpdate(r5)     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            int r9 = sBufferSize     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            byte[] r9 = new byte[r9]     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            r1 = 0
        L_0x0038:
            int r5 = r8.read(r9)     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            if (r5 == r7) goto L_0x004e
            r2.write(r9, r0, r5)     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            int r1 = r1 + r5
            double r5 = (double) r1
            java.lang.Double.isNaN(r5)
            java.lang.Double.isNaN(r3)
            double r5 = r5 / r3
            r10.onProgressUpdate(r5)     // Catch:{ IOException -> 0x0062, all -> 0x0060 }
            goto L_0x0038
        L_0x004e:
            r7 = 1
            r8.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0057
        L_0x0053:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0057:
            r2.close()     // Catch:{ IOException -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r8 = move-exception
            r8.printStackTrace()
        L_0x005f:
            return r7
        L_0x0060:
            r7 = move-exception
            goto L_0x007f
        L_0x0062:
            r7 = move-exception
            r1 = r2
            goto L_0x0069
        L_0x0065:
            r7 = move-exception
            r2 = r1
            goto L_0x007f
        L_0x0068:
            r7 = move-exception
        L_0x0069:
            r7.printStackTrace()     // Catch:{ all -> 0x0065 }
            r8.close()     // Catch:{ IOException -> 0x0070 }
            goto L_0x0074
        L_0x0070:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0074:
            if (r1 == 0) goto L_0x007e
            r1.close()     // Catch:{ IOException -> 0x007a }
            goto L_0x007e
        L_0x007a:
            r7 = move-exception
            r7.printStackTrace()
        L_0x007e:
            return r0
        L_0x007f:
            r8.close()     // Catch:{ IOException -> 0x0083 }
            goto L_0x0087
        L_0x0083:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0087:
            if (r2 == 0) goto L_0x0091
            r2.close()     // Catch:{ IOException -> 0x008d }
            goto L_0x0091
        L_0x008d:
            r8 = move-exception
            r8.printStackTrace()
        L_0x0091:
            throw r7
        L_0x0092:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "create file <"
            r8.append(r9)
            r8.append(r7)
            java.lang.String r7 = "> failed."
            r8.append(r7)
            java.lang.String r7 = r8.toString()
            java.lang.String r8 = "FileIOUtils"
            android.util.Log.e(r8, r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileIOUtils.writeFileFromIS(java.io.File, java.io.InputStream, boolean, com.blankj.utilcode.util.FileIOUtils$OnProgressUpdateListener):boolean");
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr) {
        return writeFileFromBytesByStream(UtilsBridge.getFileByPath(str), bArr, false, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByStream(UtilsBridge.getFileByPath(str), bArr, z, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr) {
        return writeFileFromBytesByStream(file, bArr, false, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr, boolean z) {
        return writeFileFromBytesByStream(file, bArr, z, (OnProgressUpdateListener) null);
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromBytesByStream(UtilsBridge.getFileByPath(str), bArr, false, onProgressUpdateListener);
    }

    public static boolean writeFileFromBytesByStream(String str, byte[] bArr, boolean z, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromBytesByStream(UtilsBridge.getFileByPath(str), bArr, z, onProgressUpdateListener);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr, OnProgressUpdateListener onProgressUpdateListener) {
        return writeFileFromBytesByStream(file, bArr, false, onProgressUpdateListener);
    }

    public static boolean writeFileFromBytesByStream(File file, byte[] bArr, boolean z, OnProgressUpdateListener onProgressUpdateListener) {
        if (bArr == null) {
            return false;
        }
        return writeFileFromIS(file, (InputStream) new ByteArrayInputStream(bArr), z, onProgressUpdateListener);
    }

    public static boolean writeFileFromBytesByChannel(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByChannel(UtilsBridge.getFileByPath(str), bArr, false, z);
    }

    public static boolean writeFileFromBytesByChannel(String str, byte[] bArr, boolean z, boolean z2) {
        return writeFileFromBytesByChannel(UtilsBridge.getFileByPath(str), bArr, z, z2);
    }

    public static boolean writeFileFromBytesByChannel(File file, byte[] bArr, boolean z) {
        return writeFileFromBytesByChannel(file, bArr, false, z);
    }

    public static boolean writeFileFromBytesByChannel(File file, byte[] bArr, boolean z, boolean z2) {
        if (bArr == null) {
            Log.e("FileIOUtils", "bytes is null.");
            return false;
        } else if (!UtilsBridge.createOrExistsFile(file)) {
            Log.e("FileIOUtils", "create file <" + file + "> failed.");
            return false;
        } else {
            FileChannel fileChannel = null;
            try {
                fileChannel = new FileOutputStream(file, z).getChannel();
                if (fileChannel == null) {
                    Log.e("FileIOUtils", "fc is null.");
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return false;
                }
                fileChannel.position(fileChannel.size());
                fileChannel.write(ByteBuffer.wrap(bArr));
                if (z2) {
                    fileChannel.force(true);
                }
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return true;
            } catch (IOException e3) {
                e3.printStackTrace();
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return false;
            } catch (Throwable th) {
                if (fileChannel != null) {
                    try {
                        fileChannel.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        }
    }

    public static boolean writeFileFromBytesByMap(String str, byte[] bArr, boolean z) {
        return writeFileFromBytesByMap(str, bArr, false, z);
    }

    public static boolean writeFileFromBytesByMap(String str, byte[] bArr, boolean z, boolean z2) {
        return writeFileFromBytesByMap(UtilsBridge.getFileByPath(str), bArr, z, z2);
    }

    public static boolean writeFileFromBytesByMap(File file, byte[] bArr, boolean z) {
        return writeFileFromBytesByMap(file, bArr, false, z);
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x0057 A[SYNTHETIC, Splitter:B:37:0x0057] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0062 A[SYNTHETIC, Splitter:B:43:0x0062] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean writeFileFromBytesByMap(java.io.File r10, byte[] r11, boolean r12, boolean r13) {
        /*
            java.lang.String r0 = "FileIOUtils"
            r1 = 0
            if (r11 == 0) goto L_0x006b
            boolean r2 = com.blankj.utilcode.util.UtilsBridge.createOrExistsFile(r10)
            if (r2 != 0) goto L_0x000c
            goto L_0x006b
        L_0x000c:
            r2 = 0
            java.io.FileOutputStream r3 = new java.io.FileOutputStream     // Catch:{ IOException -> 0x0051 }
            r3.<init>(r10, r12)     // Catch:{ IOException -> 0x0051 }
            java.nio.channels.FileChannel r10 = r3.getChannel()     // Catch:{ IOException -> 0x0051 }
            if (r10 != 0) goto L_0x0028
            java.lang.String r11 = "fc is null."
            android.util.Log.e(r0, r11)     // Catch:{ IOException -> 0x004b, all -> 0x0049 }
            if (r10 == 0) goto L_0x0027
            r10.close()     // Catch:{ IOException -> 0x0023 }
            goto L_0x0027
        L_0x0023:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0027:
            return r1
        L_0x0028:
            java.nio.channels.FileChannel$MapMode r5 = java.nio.channels.FileChannel.MapMode.READ_WRITE     // Catch:{ IOException -> 0x004b, all -> 0x0049 }
            long r6 = r10.size()     // Catch:{ IOException -> 0x004b, all -> 0x0049 }
            int r12 = r11.length     // Catch:{ IOException -> 0x004b, all -> 0x0049 }
            long r8 = (long) r12     // Catch:{ IOException -> 0x004b, all -> 0x0049 }
            r4 = r10
            java.nio.MappedByteBuffer r12 = r4.map(r5, r6, r8)     // Catch:{ IOException -> 0x004b, all -> 0x0049 }
            r12.put(r11)     // Catch:{ IOException -> 0x004b, all -> 0x0049 }
            if (r13 == 0) goto L_0x003d
            r12.force()     // Catch:{ IOException -> 0x004b, all -> 0x0049 }
        L_0x003d:
            r11 = 1
            if (r10 == 0) goto L_0x0048
            r10.close()     // Catch:{ IOException -> 0x0044 }
            goto L_0x0048
        L_0x0044:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0048:
            return r11
        L_0x0049:
            r11 = move-exception
            goto L_0x0060
        L_0x004b:
            r11 = move-exception
            r2 = r10
            goto L_0x0052
        L_0x004e:
            r11 = move-exception
            r10 = r2
            goto L_0x0060
        L_0x0051:
            r11 = move-exception
        L_0x0052:
            r11.printStackTrace()     // Catch:{ all -> 0x004e }
            if (r2 == 0) goto L_0x005f
            r2.close()     // Catch:{ IOException -> 0x005b }
            goto L_0x005f
        L_0x005b:
            r10 = move-exception
            r10.printStackTrace()
        L_0x005f:
            return r1
        L_0x0060:
            if (r10 == 0) goto L_0x006a
            r10.close()     // Catch:{ IOException -> 0x0066 }
            goto L_0x006a
        L_0x0066:
            r10 = move-exception
            r10.printStackTrace()
        L_0x006a:
            throw r11
        L_0x006b:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r12 = "create file <"
            r11.append(r12)
            r11.append(r10)
            java.lang.String r10 = "> failed."
            r11.append(r10)
            java.lang.String r10 = r11.toString()
            android.util.Log.e(r0, r10)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileIOUtils.writeFileFromBytesByMap(java.io.File, byte[], boolean, boolean):boolean");
    }

    public static boolean writeFileFromString(String str, String str2) {
        return writeFileFromString(UtilsBridge.getFileByPath(str), str2, false);
    }

    public static boolean writeFileFromString(String str, String str2, boolean z) {
        return writeFileFromString(UtilsBridge.getFileByPath(str), str2, z);
    }

    public static boolean writeFileFromString(File file, String str) {
        return writeFileFromString(file, str, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x004e A[SYNTHETIC, Splitter:B:27:0x004e] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0059 A[SYNTHETIC, Splitter:B:33:0x0059] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean writeFileFromString(java.io.File r4, java.lang.String r5, boolean r6) {
        /*
            r0 = 0
            if (r4 == 0) goto L_0x0062
            if (r5 != 0) goto L_0x0006
            goto L_0x0062
        L_0x0006:
            boolean r1 = com.blankj.utilcode.util.UtilsBridge.createOrExistsFile(r4)
            if (r1 != 0) goto L_0x0028
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "create file <"
            r5.append(r6)
            r5.append(r4)
            java.lang.String r4 = "> failed."
            r5.append(r4)
            java.lang.String r4 = r5.toString()
            java.lang.String r5 = "FileIOUtils"
            android.util.Log.e(r5, r4)
            return r0
        L_0x0028:
            r1 = 0
            java.io.BufferedWriter r2 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x0048 }
            java.io.FileWriter r3 = new java.io.FileWriter     // Catch:{ IOException -> 0x0048 }
            r3.<init>(r4, r6)     // Catch:{ IOException -> 0x0048 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0048 }
            r2.write(r5)     // Catch:{ IOException -> 0x0043, all -> 0x0040 }
            r4 = 1
            r2.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x003f
        L_0x003b:
            r5 = move-exception
            r5.printStackTrace()
        L_0x003f:
            return r4
        L_0x0040:
            r4 = move-exception
            r1 = r2
            goto L_0x0057
        L_0x0043:
            r4 = move-exception
            r1 = r2
            goto L_0x0049
        L_0x0046:
            r4 = move-exception
            goto L_0x0057
        L_0x0048:
            r4 = move-exception
        L_0x0049:
            r4.printStackTrace()     // Catch:{ all -> 0x0046 }
            if (r1 == 0) goto L_0x0056
            r1.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x0056
        L_0x0052:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0056:
            return r0
        L_0x0057:
            if (r1 == 0) goto L_0x0061
            r1.close()     // Catch:{ IOException -> 0x005d }
            goto L_0x0061
        L_0x005d:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0061:
            throw r4
        L_0x0062:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileIOUtils.writeFileFromString(java.io.File, java.lang.String, boolean):boolean");
    }

    public static List<String> readFile2List(String str) {
        return readFile2List(UtilsBridge.getFileByPath(str), (String) null);
    }

    public static List<String> readFile2List(String str, String str2) {
        return readFile2List(UtilsBridge.getFileByPath(str), str2);
    }

    public static List<String> readFile2List(File file) {
        return readFile2List(file, 0, Integer.MAX_VALUE, (String) null);
    }

    public static List<String> readFile2List(File file, String str) {
        return readFile2List(file, 0, Integer.MAX_VALUE, str);
    }

    public static List<String> readFile2List(String str, int i, int i2) {
        return readFile2List(UtilsBridge.getFileByPath(str), i, i2, (String) null);
    }

    public static List<String> readFile2List(String str, int i, int i2, String str2) {
        return readFile2List(UtilsBridge.getFileByPath(str), i, i2, str2);
    }

    public static List<String> readFile2List(File file, int i, int i2) {
        return readFile2List(file, i, i2, (String) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x005e A[SYNTHETIC, Splitter:B:32:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x006b A[SYNTHETIC, Splitter:B:40:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<java.lang.String> readFile2List(java.io.File r6, int r7, int r8, java.lang.String r9) {
        /*
            boolean r0 = com.blankj.utilcode.util.UtilsBridge.isFileExists(r6)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            if (r7 <= r8) goto L_0x000b
            return r1
        L_0x000b:
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            r0.<init>()     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            boolean r2 = com.blankj.utilcode.util.UtilsBridge.isSpace(r9)     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            r3 = 1
            if (r2 == 0) goto L_0x0027
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            r4.<init>(r6)     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            r9.<init>(r2)     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            goto L_0x0037
        L_0x0027:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            java.io.FileInputStream r5 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            r5.<init>(r6)     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            r4.<init>(r5, r9)     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0057, all -> 0x0055 }
            r9 = r2
        L_0x0037:
            java.lang.String r6 = r9.readLine()     // Catch:{ IOException -> 0x0053 }
            if (r6 == 0) goto L_0x004a
            if (r3 <= r8) goto L_0x0040
            goto L_0x004a
        L_0x0040:
            if (r7 > r3) goto L_0x0047
            if (r3 > r8) goto L_0x0047
            r0.add(r6)     // Catch:{ IOException -> 0x0053 }
        L_0x0047:
            int r3 = r3 + 1
            goto L_0x0037
        L_0x004a:
            r9.close()     // Catch:{ IOException -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0052:
            return r0
        L_0x0053:
            r6 = move-exception
            goto L_0x0059
        L_0x0055:
            r6 = move-exception
            goto L_0x0069
        L_0x0057:
            r6 = move-exception
            r9 = r1
        L_0x0059:
            r6.printStackTrace()     // Catch:{ all -> 0x0067 }
            if (r9 == 0) goto L_0x0066
            r9.close()     // Catch:{ IOException -> 0x0062 }
            goto L_0x0066
        L_0x0062:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0066:
            return r1
        L_0x0067:
            r6 = move-exception
            r1 = r9
        L_0x0069:
            if (r1 == 0) goto L_0x0073
            r1.close()     // Catch:{ IOException -> 0x006f }
            goto L_0x0073
        L_0x006f:
            r7 = move-exception
            r7.printStackTrace()
        L_0x0073:
            goto L_0x0075
        L_0x0074:
            throw r6
        L_0x0075:
            goto L_0x0074
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileIOUtils.readFile2List(java.io.File, int, int, java.lang.String):java.util.List");
    }

    public static String readFile2String(String str) {
        return readFile2String(UtilsBridge.getFileByPath(str), (String) null);
    }

    public static String readFile2String(String str, String str2) {
        return readFile2String(UtilsBridge.getFileByPath(str), str2);
    }

    public static String readFile2String(File file) {
        return readFile2String(file, (String) null);
    }

    public static String readFile2String(File file, String str) {
        byte[] readFile2BytesByStream = readFile2BytesByStream(file);
        if (readFile2BytesByStream == null) {
            return null;
        }
        if (UtilsBridge.isSpace(str)) {
            return new String(readFile2BytesByStream);
        }
        try {
            return new String(readFile2BytesByStream, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] readFile2BytesByStream(String str) {
        return readFile2BytesByStream(UtilsBridge.getFileByPath(str), (OnProgressUpdateListener) null);
    }

    public static byte[] readFile2BytesByStream(File file) {
        return readFile2BytesByStream(file, (OnProgressUpdateListener) null);
    }

    public static byte[] readFile2BytesByStream(String str, OnProgressUpdateListener onProgressUpdateListener) {
        return readFile2BytesByStream(UtilsBridge.getFileByPath(str), onProgressUpdateListener);
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0079 A[SYNTHETIC, Splitter:B:45:0x0079] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x008d A[SYNTHETIC, Splitter:B:58:0x008d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] readFile2BytesByStream(java.io.File r10, com.blankj.utilcode.util.FileIOUtils.OnProgressUpdateListener r11) {
        /*
            boolean r0 = com.blankj.utilcode.util.UtilsBridge.isFileExists(r10)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x0096 }
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0096 }
            r2.<init>(r10)     // Catch:{ FileNotFoundException -> 0x0096 }
            int r10 = sBufferSize     // Catch:{ FileNotFoundException -> 0x0096 }
            r0.<init>(r2, r10)     // Catch:{ FileNotFoundException -> 0x0096 }
            java.io.ByteArrayOutputStream r10 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            r10.<init>()     // Catch:{ IOException -> 0x006a, all -> 0x0067 }
            int r2 = sBufferSize     // Catch:{ IOException -> 0x0065 }
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x0065 }
            r3 = -1
            r4 = 0
            if (r11 != 0) goto L_0x002d
        L_0x0021:
            int r11 = sBufferSize     // Catch:{ IOException -> 0x0065 }
            int r11 = r0.read(r2, r4, r11)     // Catch:{ IOException -> 0x0065 }
            if (r11 == r3) goto L_0x0050
            r10.write(r2, r4, r11)     // Catch:{ IOException -> 0x0065 }
            goto L_0x0021
        L_0x002d:
            int r5 = r0.available()     // Catch:{ IOException -> 0x0065 }
            double r5 = (double) r5     // Catch:{ IOException -> 0x0065 }
            r7 = 0
            r11.onProgressUpdate(r7)     // Catch:{ IOException -> 0x0065 }
            r7 = 0
        L_0x0038:
            int r8 = sBufferSize     // Catch:{ IOException -> 0x0065 }
            int r8 = r0.read(r2, r4, r8)     // Catch:{ IOException -> 0x0065 }
            if (r8 == r3) goto L_0x0050
            r10.write(r2, r4, r8)     // Catch:{ IOException -> 0x0065 }
            int r7 = r7 + r8
            double r8 = (double) r7
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r5)
            double r8 = r8 / r5
            r11.onProgressUpdate(r8)     // Catch:{ IOException -> 0x0065 }
            goto L_0x0038
        L_0x0050:
            byte[] r11 = r10.toByteArray()     // Catch:{ IOException -> 0x0065 }
            r0.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x005c
        L_0x0058:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ FileNotFoundException -> 0x0096 }
        L_0x005c:
            r10.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0064
        L_0x0060:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ FileNotFoundException -> 0x0096 }
        L_0x0064:
            return r11
        L_0x0065:
            r11 = move-exception
            goto L_0x006c
        L_0x0067:
            r11 = move-exception
            r10 = r1
            goto L_0x0083
        L_0x006a:
            r11 = move-exception
            r10 = r1
        L_0x006c:
            r11.printStackTrace()     // Catch:{ all -> 0x0082 }
            r0.close()     // Catch:{ IOException -> 0x0073 }
            goto L_0x0077
        L_0x0073:
            r11 = move-exception
            r11.printStackTrace()     // Catch:{ FileNotFoundException -> 0x0096 }
        L_0x0077:
            if (r10 == 0) goto L_0x0081
            r10.close()     // Catch:{ IOException -> 0x007d }
            goto L_0x0081
        L_0x007d:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ FileNotFoundException -> 0x0096 }
        L_0x0081:
            return r1
        L_0x0082:
            r11 = move-exception
        L_0x0083:
            r0.close()     // Catch:{ IOException -> 0x0087 }
            goto L_0x008b
        L_0x0087:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ FileNotFoundException -> 0x0096 }
        L_0x008b:
            if (r10 == 0) goto L_0x0095
            r10.close()     // Catch:{ IOException -> 0x0091 }
            goto L_0x0095
        L_0x0091:
            r10 = move-exception
            r10.printStackTrace()     // Catch:{ FileNotFoundException -> 0x0096 }
        L_0x0095:
            throw r11     // Catch:{ FileNotFoundException -> 0x0096 }
        L_0x0096:
            r10 = move-exception
            r10.printStackTrace()
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileIOUtils.readFile2BytesByStream(java.io.File, com.blankj.utilcode.util.FileIOUtils$OnProgressUpdateListener):byte[]");
    }

    public static byte[] readFile2BytesByChannel(String str) {
        return readFile2BytesByChannel(UtilsBridge.getFileByPath(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x0054 A[SYNTHETIC, Splitter:B:34:0x0054] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0060 A[SYNTHETIC, Splitter:B:41:0x0060] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] readFile2BytesByChannel(java.io.File r4) {
        /*
            boolean r0 = com.blankj.utilcode.util.UtilsBridge.isFileExists(r4)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x004d, all -> 0x004a }
            java.lang.String r2 = "r"
            r0.<init>(r4, r2)     // Catch:{ IOException -> 0x004d, all -> 0x004a }
            java.nio.channels.FileChannel r4 = r0.getChannel()     // Catch:{ IOException -> 0x004d, all -> 0x004a }
            if (r4 != 0) goto L_0x002a
            java.lang.String r0 = "FileIOUtils"
            java.lang.String r2 = "fc is null."
            android.util.Log.e(r0, r2)     // Catch:{ IOException -> 0x0048 }
            r0 = 0
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x0048 }
            if (r4 == 0) goto L_0x0029
            r4.close()     // Catch:{ IOException -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0029:
            return r0
        L_0x002a:
            long r2 = r4.size()     // Catch:{ IOException -> 0x0048 }
            int r0 = (int) r2     // Catch:{ IOException -> 0x0048 }
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.allocate(r0)     // Catch:{ IOException -> 0x0048 }
        L_0x0033:
            int r2 = r4.read(r0)     // Catch:{ IOException -> 0x0048 }
            if (r2 > 0) goto L_0x0033
            byte[] r0 = r0.array()     // Catch:{ IOException -> 0x0048 }
            if (r4 == 0) goto L_0x0047
            r4.close()     // Catch:{ IOException -> 0x0043 }
            goto L_0x0047
        L_0x0043:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0047:
            return r0
        L_0x0048:
            r0 = move-exception
            goto L_0x004f
        L_0x004a:
            r0 = move-exception
            r4 = r1
            goto L_0x005e
        L_0x004d:
            r0 = move-exception
            r4 = r1
        L_0x004f:
            r0.printStackTrace()     // Catch:{ all -> 0x005d }
            if (r4 == 0) goto L_0x005c
            r4.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x005c
        L_0x0058:
            r4 = move-exception
            r4.printStackTrace()
        L_0x005c:
            return r1
        L_0x005d:
            r0 = move-exception
        L_0x005e:
            if (r4 == 0) goto L_0x0068
            r4.close()     // Catch:{ IOException -> 0x0064 }
            goto L_0x0068
        L_0x0064:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0068:
            goto L_0x006a
        L_0x0069:
            throw r0
        L_0x006a:
            goto L_0x0069
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileIOUtils.readFile2BytesByChannel(java.io.File):byte[]");
    }

    public static byte[] readFile2BytesByMap(String str) {
        return readFile2BytesByMap(UtilsBridge.getFileByPath(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0059 A[SYNTHETIC, Splitter:B:32:0x0059] */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0065 A[SYNTHETIC, Splitter:B:39:0x0065] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] readFile2BytesByMap(java.io.File r10) {
        /*
            boolean r0 = com.blankj.utilcode.util.UtilsBridge.isFileExists(r10)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.io.RandomAccessFile r0 = new java.io.RandomAccessFile     // Catch:{ IOException -> 0x0052, all -> 0x004f }
            java.lang.String r2 = "r"
            r0.<init>(r10, r2)     // Catch:{ IOException -> 0x0052, all -> 0x004f }
            java.nio.channels.FileChannel r10 = r0.getChannel()     // Catch:{ IOException -> 0x0052, all -> 0x004f }
            r0 = 0
            if (r10 != 0) goto L_0x002a
            java.lang.String r2 = "FileIOUtils"
            java.lang.String r3 = "fc is null."
            android.util.Log.e(r2, r3)     // Catch:{ IOException -> 0x004d }
            byte[] r0 = new byte[r0]     // Catch:{ IOException -> 0x004d }
            if (r10 == 0) goto L_0x0029
            r10.close()     // Catch:{ IOException -> 0x0025 }
            goto L_0x0029
        L_0x0025:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0029:
            return r0
        L_0x002a:
            long r2 = r10.size()     // Catch:{ IOException -> 0x004d }
            int r9 = (int) r2     // Catch:{ IOException -> 0x004d }
            java.nio.channels.FileChannel$MapMode r4 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ IOException -> 0x004d }
            r5 = 0
            long r7 = (long) r9     // Catch:{ IOException -> 0x004d }
            r3 = r10
            java.nio.MappedByteBuffer r2 = r3.map(r4, r5, r7)     // Catch:{ IOException -> 0x004d }
            java.nio.MappedByteBuffer r2 = r2.load()     // Catch:{ IOException -> 0x004d }
            byte[] r3 = new byte[r9]     // Catch:{ IOException -> 0x004d }
            r2.get(r3, r0, r9)     // Catch:{ IOException -> 0x004d }
            if (r10 == 0) goto L_0x004c
            r10.close()     // Catch:{ IOException -> 0x0048 }
            goto L_0x004c
        L_0x0048:
            r10 = move-exception
            r10.printStackTrace()
        L_0x004c:
            return r3
        L_0x004d:
            r0 = move-exception
            goto L_0x0054
        L_0x004f:
            r0 = move-exception
            r10 = r1
            goto L_0x0063
        L_0x0052:
            r0 = move-exception
            r10 = r1
        L_0x0054:
            r0.printStackTrace()     // Catch:{ all -> 0x0062 }
            if (r10 == 0) goto L_0x0061
            r10.close()     // Catch:{ IOException -> 0x005d }
            goto L_0x0061
        L_0x005d:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0061:
            return r1
        L_0x0062:
            r0 = move-exception
        L_0x0063:
            if (r10 == 0) goto L_0x006d
            r10.close()     // Catch:{ IOException -> 0x0069 }
            goto L_0x006d
        L_0x0069:
            r10 = move-exception
            r10.printStackTrace()
        L_0x006d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileIOUtils.readFile2BytesByMap(java.io.File):byte[]");
    }

    public static void setBufferSize(int i) {
        sBufferSize = i;
    }
}
