package com.blankj.utilcode.util;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.os.StatFs;
import android.text.TextUtils;
import com.baidubce.http.Headers;
import com.blankj.utilcode.constant.RegexConstants;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import kotlin.jvm.internal.ByteCompanionObject;

public final class FileUtils {
    private static final String LINE_SEP = System.getProperty("line.separator");

    public interface OnReplaceListener {
        boolean onReplace(File file, File file2);
    }

    private FileUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static File getFileByPath(String str) {
        if (UtilsBridge.isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    public static boolean isFileExists(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return true;
        }
        return isFileExists(file.getAbsolutePath());
    }

    public static boolean isFileExists(String str) {
        File fileByPath = getFileByPath(str);
        if (fileByPath == null) {
            return false;
        }
        if (fileByPath.exists()) {
            return true;
        }
        return isFileExistsApi29(str);
    }

    private static boolean isFileExistsApi29(String str) {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                AssetFileDescriptor openAssetFileDescriptor = Utils.getApp().getContentResolver().openAssetFileDescriptor(Uri.parse(str), "r");
                if (openAssetFileDescriptor == null) {
                    return false;
                }
                try {
                    openAssetFileDescriptor.close();
                    return true;
                } catch (IOException unused) {
                    return true;
                }
            } catch (FileNotFoundException unused2) {
            }
        }
        return false;
    }

    public static boolean rename(String str, String str2) {
        return rename(getFileByPath(str), str2);
    }

    public static boolean rename(File file, String str) {
        if (file == null || !file.exists() || UtilsBridge.isSpace(str)) {
            return false;
        }
        if (str.equals(file.getName())) {
            return true;
        }
        File file2 = new File(file.getParent() + File.separator + str);
        if (file2.exists() || !file.renameTo(file2)) {
            return false;
        }
        return true;
    }

    public static boolean isDir(String str) {
        return isDir(getFileByPath(str));
    }

    public static boolean isDir(File file) {
        return file != null && file.exists() && file.isDirectory();
    }

    public static boolean isFile(String str) {
        return isFile(getFileByPath(str));
    }

    public static boolean isFile(File file) {
        return file != null && file.exists() && file.isFile();
    }

    public static boolean createOrExistsDir(String str) {
        return createOrExistsDir(getFileByPath(str));
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? file.mkdirs() : file.isDirectory());
    }

    public static boolean createOrExistsFile(String str) {
        return createOrExistsFile(getFileByPath(str));
    }

    public static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createFileByDeleteOldFile(String str) {
        return createFileByDeleteOldFile(getFileByPath(str));
    }

    public static boolean createFileByDeleteOldFile(File file) {
        if (file == null) {
            return false;
        }
        if ((file.exists() && !file.delete()) || !createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copy(String str, String str2) {
        return copy(getFileByPath(str), getFileByPath(str2), (OnReplaceListener) null);
    }

    public static boolean copy(String str, String str2, OnReplaceListener onReplaceListener) {
        return copy(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    public static boolean copy(File file, File file2) {
        return copy(file, file2, (OnReplaceListener) null);
    }

    public static boolean copy(File file, File file2, OnReplaceListener onReplaceListener) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            return copyDir(file, file2, onReplaceListener);
        }
        return copyFile(file, file2, onReplaceListener);
    }

    private static boolean copyDir(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveDir(file, file2, onReplaceListener, false);
    }

    private static boolean copyFile(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveFile(file, file2, onReplaceListener, false);
    }

    public static boolean move(String str, String str2) {
        return move(getFileByPath(str), getFileByPath(str2), (OnReplaceListener) null);
    }

    public static boolean move(String str, String str2, OnReplaceListener onReplaceListener) {
        return move(getFileByPath(str), getFileByPath(str2), onReplaceListener);
    }

    public static boolean move(File file, File file2) {
        return move(file, file2, (OnReplaceListener) null);
    }

    public static boolean move(File file, File file2, OnReplaceListener onReplaceListener) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            return moveDir(file, file2, onReplaceListener);
        }
        return moveFile(file, file2, onReplaceListener);
    }

    public static boolean moveDir(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveDir(file, file2, onReplaceListener, true);
    }

    public static boolean moveFile(File file, File file2, OnReplaceListener onReplaceListener) {
        return copyOrMoveFile(file, file2, onReplaceListener, true);
    }

    private static boolean copyOrMoveDir(File file, File file2, OnReplaceListener onReplaceListener, boolean z) {
        if (file == null || file2 == null) {
            return false;
        }
        String str = file2.getPath() + File.separator;
        if (str.contains(file.getPath() + File.separator) || !file.exists() || !file.isDirectory() || !createOrExistsDir(file2)) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file3 : listFiles) {
                File file4 = new File(str + file3.getName());
                if (file3.isFile()) {
                    if (!copyOrMoveFile(file3, file4, onReplaceListener, z)) {
                        return false;
                    }
                } else if (file3.isDirectory() && !copyOrMoveDir(file3, file4, onReplaceListener, z)) {
                    return false;
                }
            }
        }
        if (!z || deleteDir(file)) {
            return true;
        }
        return false;
    }

    private static boolean copyOrMoveFile(File file, File file2, OnReplaceListener onReplaceListener, boolean z) {
        if (file != null && file2 != null && !file.equals(file2) && file.exists() && file.isFile()) {
            if (file2.exists()) {
                if (onReplaceListener != null && !onReplaceListener.onReplace(file, file2)) {
                    return true;
                }
                if (!file2.delete()) {
                    return false;
                }
            }
            if (!createOrExistsDir(file2.getParentFile())) {
                return false;
            }
            try {
                if (!UtilsBridge.writeFileFromIS(file2.getAbsolutePath(), new FileInputStream(file))) {
                    return false;
                }
                if (!z || deleteFile(file)) {
                    return true;
                }
                return false;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean delete(String str) {
        return delete(getFileByPath(str));
    }

    public static boolean delete(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            return deleteDir(file);
        }
        return deleteFile(file);
    }

    private static boolean deleteDir(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    if (!file2.delete()) {
                        return false;
                    }
                } else if (file2.isDirectory() && !deleteDir(file2)) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    private static boolean deleteFile(File file) {
        return file != null && (!file.exists() || (file.isFile() && file.delete()));
    }

    public static boolean deleteAllInDir(String str) {
        return deleteAllInDir(getFileByPath(str));
    }

    public static boolean deleteAllInDir(File file) {
        return deleteFilesInDirWithFilter(file, (FileFilter) new FileFilter() {
            public boolean accept(File file) {
                return true;
            }
        });
    }

    public static boolean deleteFilesInDir(String str) {
        return deleteFilesInDir(getFileByPath(str));
    }

    public static boolean deleteFilesInDir(File file) {
        return deleteFilesInDirWithFilter(file, (FileFilter) new FileFilter() {
            public boolean accept(File file) {
                return file.isFile();
            }
        });
    }

    public static boolean deleteFilesInDirWithFilter(String str, FileFilter fileFilter) {
        return deleteFilesInDirWithFilter(getFileByPath(str), fileFilter);
    }

    public static boolean deleteFilesInDirWithFilter(File file, FileFilter fileFilter) {
        if (file == null || fileFilter == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (!file.isDirectory()) {
            return false;
        }
        File[] listFiles = file.listFiles();
        if (!(listFiles == null || listFiles.length == 0)) {
            for (File file2 : listFiles) {
                if (fileFilter.accept(file2)) {
                    if (file2.isFile()) {
                        if (!file2.delete()) {
                            return false;
                        }
                    } else if (file2.isDirectory() && !deleteDir(file2)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static List<File> listFilesInDir(String str) {
        return listFilesInDir(str, (Comparator<File>) null);
    }

    public static List<File> listFilesInDir(File file) {
        return listFilesInDir(file, (Comparator<File>) null);
    }

    public static List<File> listFilesInDir(String str, Comparator<File> comparator) {
        return listFilesInDir(getFileByPath(str), false, comparator);
    }

    public static List<File> listFilesInDir(File file, Comparator<File> comparator) {
        return listFilesInDir(file, false, comparator);
    }

    public static List<File> listFilesInDir(String str, boolean z) {
        return listFilesInDir(getFileByPath(str), z);
    }

    public static List<File> listFilesInDir(File file, boolean z) {
        return listFilesInDir(file, z, (Comparator<File>) null);
    }

    public static List<File> listFilesInDir(String str, boolean z, Comparator<File> comparator) {
        return listFilesInDir(getFileByPath(str), z, comparator);
    }

    public static List<File> listFilesInDir(File file, boolean z, Comparator<File> comparator) {
        return listFilesInDirWithFilter(file, (FileFilter) new FileFilter() {
            public boolean accept(File file) {
                return true;
            }
        }, z, comparator);
    }

    public static List<File> listFilesInDirWithFilter(String str, FileFilter fileFilter) {
        return listFilesInDirWithFilter(getFileByPath(str), fileFilter);
    }

    public static List<File> listFilesInDirWithFilter(File file, FileFilter fileFilter) {
        return listFilesInDirWithFilter(file, fileFilter, false, (Comparator<File>) null);
    }

    public static List<File> listFilesInDirWithFilter(String str, FileFilter fileFilter, Comparator<File> comparator) {
        return listFilesInDirWithFilter(getFileByPath(str), fileFilter, comparator);
    }

    public static List<File> listFilesInDirWithFilter(File file, FileFilter fileFilter, Comparator<File> comparator) {
        return listFilesInDirWithFilter(file, fileFilter, false, comparator);
    }

    public static List<File> listFilesInDirWithFilter(String str, FileFilter fileFilter, boolean z) {
        return listFilesInDirWithFilter(getFileByPath(str), fileFilter, z);
    }

    public static List<File> listFilesInDirWithFilter(File file, FileFilter fileFilter, boolean z) {
        return listFilesInDirWithFilter(file, fileFilter, z, (Comparator<File>) null);
    }

    public static List<File> listFilesInDirWithFilter(String str, FileFilter fileFilter, boolean z, Comparator<File> comparator) {
        return listFilesInDirWithFilter(getFileByPath(str), fileFilter, z, comparator);
    }

    public static List<File> listFilesInDirWithFilter(File file, FileFilter fileFilter, boolean z, Comparator<File> comparator) {
        List<File> listFilesInDirWithFilterInner = listFilesInDirWithFilterInner(file, fileFilter, z);
        if (comparator != null) {
            Collections.sort(listFilesInDirWithFilterInner, comparator);
        }
        return listFilesInDirWithFilterInner;
    }

    private static List<File> listFilesInDirWithFilterInner(File file, FileFilter fileFilter, boolean z) {
        File[] listFiles;
        ArrayList arrayList = new ArrayList();
        if (isDir(file) && (listFiles = file.listFiles()) != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (fileFilter.accept(file2)) {
                    arrayList.add(file2);
                }
                if (z && file2.isDirectory()) {
                    arrayList.addAll(listFilesInDirWithFilterInner(file2, fileFilter, true));
                }
            }
        }
        return arrayList;
    }

    public static long getFileLastModified(String str) {
        return getFileLastModified(getFileByPath(str));
    }

    public static long getFileLastModified(File file) {
        if (file == null) {
            return -1;
        }
        return file.lastModified();
    }

    public static String getFileCharsetSimple(String str) {
        return getFileCharsetSimple(getFileByPath(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x003c A[SYNTHETIC, Splitter:B:24:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x004f A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0054 A[SYNTHETIC, Splitter:B:34:0x0054] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getFileCharsetSimple(java.io.File r4) {
        /*
            if (r4 != 0) goto L_0x0005
            java.lang.String r4 = ""
            return r4
        L_0x0005:
            boolean r0 = isUtf8((java.io.File) r4)
            if (r0 == 0) goto L_0x000e
            java.lang.String r4 = "UTF-8"
            return r4
        L_0x000e:
            r0 = 0
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0036 }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0036 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0036 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0036 }
            int r4 = r2.read()     // Catch:{ IOException -> 0x0031, all -> 0x002e }
            int r4 = r4 << 8
            int r0 = r2.read()     // Catch:{ IOException -> 0x0031, all -> 0x002e }
            int r0 = r0 + r4
            r2.close()     // Catch:{ IOException -> 0x0029 }
            goto L_0x003f
        L_0x0029:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x003f
        L_0x002e:
            r4 = move-exception
            r1 = r2
            goto L_0x0052
        L_0x0031:
            r4 = move-exception
            r1 = r2
            goto L_0x0037
        L_0x0034:
            r4 = move-exception
            goto L_0x0052
        L_0x0036:
            r4 = move-exception
        L_0x0037:
            r4.printStackTrace()     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x003f
            r1.close()     // Catch:{ IOException -> 0x0029 }
        L_0x003f:
            r4 = 65279(0xfeff, float:9.1475E-41)
            if (r0 == r4) goto L_0x004f
            r4 = 65534(0xfffe, float:9.1833E-41)
            if (r0 == r4) goto L_0x004c
            java.lang.String r4 = "GBK"
            return r4
        L_0x004c:
            java.lang.String r4 = "Unicode"
            return r4
        L_0x004f:
            java.lang.String r4 = "UTF-16BE"
            return r4
        L_0x0052:
            if (r1 == 0) goto L_0x005c
            r1.close()     // Catch:{ IOException -> 0x0058 }
            goto L_0x005c
        L_0x0058:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005c:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileUtils.getFileCharsetSimple(java.io.File):java.lang.String");
    }

    public static boolean isUtf8(String str) {
        return isUtf8(getFileByPath(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x0048 A[SYNTHETIC, Splitter:B:32:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0053 A[SYNTHETIC, Splitter:B:38:0x0053] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isUtf8(java.io.File r5) {
        /*
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            r2 = 24
            byte[] r2 = new byte[r2]     // Catch:{ IOException -> 0x0042 }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x0042 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0042 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0042 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x0042 }
            int r5 = r3.read(r2)     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            r1 = -1
            if (r5 == r1) goto L_0x0031
            byte[] r1 = new byte[r5]     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            java.lang.System.arraycopy(r2, r0, r1, r0, r5)     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            int r5 = isUtf8((byte[]) r1)     // Catch:{ IOException -> 0x003d, all -> 0x003a }
            r1 = 100
            if (r5 != r1) goto L_0x0028
            r0 = 1
        L_0x0028:
            r3.close()     // Catch:{ IOException -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0030:
            return r0
        L_0x0031:
            r3.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x0039
        L_0x0035:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0039:
            return r0
        L_0x003a:
            r5 = move-exception
            r1 = r3
            goto L_0x0051
        L_0x003d:
            r5 = move-exception
            r1 = r3
            goto L_0x0043
        L_0x0040:
            r5 = move-exception
            goto L_0x0051
        L_0x0042:
            r5 = move-exception
        L_0x0043:
            r5.printStackTrace()     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x0050
            r1.close()     // Catch:{ IOException -> 0x004c }
            goto L_0x0050
        L_0x004c:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0050:
            return r0
        L_0x0051:
            if (r1 == 0) goto L_0x005b
            r1.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x005b
        L_0x0057:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005b:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileUtils.isUtf8(java.io.File):boolean");
    }

    private static int isUtf8(byte[] bArr) {
        if (bArr.length > 3 && bArr[0] == -17 && bArr[1] == -69 && bArr[2] == -65) {
            return 100;
        }
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        loop0:
        while (true) {
            int i4 = 0;
            while (i < length) {
                if ((bArr[i] & -1) == -1 || (bArr[i] & -2) == -2) {
                    return 0;
                }
                if (i4 == 0) {
                    if ((bArr[i] & ByteCompanionObject.MAX_VALUE) == bArr[i] && bArr[i] != 0) {
                        i2++;
                    } else if ((bArr[i] & -64) == -64) {
                        int i5 = i4;
                        for (int i6 = 0; i6 < 8; i6++) {
                            byte b = (byte) (128 >> i6);
                            if ((bArr[i] & b) != b) {
                                break;
                            }
                            i5 = i6;
                        }
                        i3++;
                        i4 = i5;
                    }
                    i++;
                } else {
                    if (bArr.length - i <= i4) {
                        i4 = bArr.length - i;
                    }
                    int i7 = i2;
                    boolean z = false;
                    for (int i8 = 0; i8 < i4; i8++) {
                        int i9 = i + i8;
                        if ((bArr[i9] & ByteCompanionObject.MIN_VALUE) != Byte.MIN_VALUE) {
                            if ((bArr[i9] & ByteCompanionObject.MAX_VALUE) == bArr[i9] && bArr[i] != 0) {
                                i7++;
                            }
                            z = true;
                        }
                    }
                    if (z) {
                        i3--;
                        i++;
                    } else {
                        i3 += i4;
                        i += i4;
                    }
                    i2 = i7;
                }
            }
            if (i2 == length) {
                return 100;
            }
            return (int) ((((float) (i3 + i2)) / ((float) length)) * 100.0f);
        }
        return 0;
    }

    public static int getFileLines(String str) {
        return getFileLines(getFileByPath(str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0031, code lost:
        r9 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0032, code lost:
        r1 = r2;
        r0 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0057, code lost:
        r9 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x006f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0070, code lost:
        r0.printStackTrace();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0057 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:4:0x000e] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0065 A[SYNTHETIC, Splitter:B:45:0x0065] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x006b A[SYNTHETIC, Splitter:B:49:0x006b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int getFileLines(java.io.File r9) {
        /*
            r0 = 1
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x005f }
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x005f }
            r3.<init>(r9)     // Catch:{ IOException -> 0x005f }
            r2.<init>(r3)     // Catch:{ IOException -> 0x005f }
            r9 = 1024(0x400, float:1.435E-42)
            byte[] r1 = new byte[r9]     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            java.lang.String r3 = LINE_SEP     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            java.lang.String r4 = "\n"
            boolean r3 = r3.endsWith(r4)     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            r4 = -1
            r5 = 0
            if (r3 == 0) goto L_0x0037
        L_0x001c:
            int r3 = r2.read(r1, r5, r9)     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            if (r3 == r4) goto L_0x004e
            r6 = r0
            r0 = 0
        L_0x0024:
            if (r0 >= r3) goto L_0x0035
            byte r7 = r1[r0]     // Catch:{ IOException -> 0x0031, all -> 0x0057 }
            r8 = 10
            if (r7 != r8) goto L_0x002e
            int r6 = r6 + 1
        L_0x002e:
            int r0 = r0 + 1
            goto L_0x0024
        L_0x0031:
            r9 = move-exception
            r1 = r2
            r0 = r6
            goto L_0x0060
        L_0x0035:
            r0 = r6
            goto L_0x001c
        L_0x0037:
            int r3 = r2.read(r1, r5, r9)     // Catch:{ IOException -> 0x0059, all -> 0x0057 }
            if (r3 == r4) goto L_0x004e
            r6 = r0
            r0 = 0
        L_0x003f:
            if (r0 >= r3) goto L_0x004c
            byte r7 = r1[r0]     // Catch:{ IOException -> 0x0031, all -> 0x0057 }
            r8 = 13
            if (r7 != r8) goto L_0x0049
            int r6 = r6 + 1
        L_0x0049:
            int r0 = r0 + 1
            goto L_0x003f
        L_0x004c:
            r0 = r6
            goto L_0x0037
        L_0x004e:
            r2.close()     // Catch:{ IOException -> 0x0052 }
            goto L_0x0068
        L_0x0052:
            r9 = move-exception
            r9.printStackTrace()
            goto L_0x0068
        L_0x0057:
            r9 = move-exception
            goto L_0x0069
        L_0x0059:
            r9 = move-exception
            r1 = r2
            goto L_0x0060
        L_0x005c:
            r9 = move-exception
            r2 = r1
            goto L_0x0069
        L_0x005f:
            r9 = move-exception
        L_0x0060:
            r9.printStackTrace()     // Catch:{ all -> 0x005c }
            if (r1 == 0) goto L_0x0068
            r1.close()     // Catch:{ IOException -> 0x0052 }
        L_0x0068:
            return r0
        L_0x0069:
            if (r2 == 0) goto L_0x0073
            r2.close()     // Catch:{ IOException -> 0x006f }
            goto L_0x0073
        L_0x006f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0073:
            goto L_0x0075
        L_0x0074:
            throw r9
        L_0x0075:
            goto L_0x0074
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileUtils.getFileLines(java.io.File):int");
    }

    public static String getSize(String str) {
        return getSize(getFileByPath(str));
    }

    public static String getSize(File file) {
        if (file == null) {
            return "";
        }
        if (file.isDirectory()) {
            return getDirSize(file);
        }
        return getFileSize(file);
    }

    private static String getDirSize(File file) {
        long dirLength = getDirLength(file);
        if (dirLength == -1) {
            return "";
        }
        return UtilsBridge.byte2FitMemorySize(dirLength);
    }

    private static String getFileSize(File file) {
        long fileLength = getFileLength(file);
        if (fileLength == -1) {
            return "";
        }
        return UtilsBridge.byte2FitMemorySize(fileLength);
    }

    public static long getLength(String str) {
        return getLength(getFileByPath(str));
    }

    public static long getLength(File file) {
        if (file == null) {
            return 0;
        }
        if (file.isDirectory()) {
            return getDirLength(file);
        }
        return getFileLength(file);
    }

    private static long getDirLength(File file) {
        long j;
        long j2 = 0;
        if (!isDir(file)) {
            return 0;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    j = getDirLength(file2);
                } else {
                    j = file2.length();
                }
                j2 += j;
            }
        }
        return j2;
    }

    public static long getFileLength(String str) {
        if (str.matches(RegexConstants.REGEX_URL)) {
            try {
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL(str).openConnection();
                httpsURLConnection.setRequestProperty(Headers.ACCEPT_ENCODING, "identity");
                httpsURLConnection.connect();
                if (httpsURLConnection.getResponseCode() == 200) {
                    return (long) httpsURLConnection.getContentLength();
                }
                return -1;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getFileLength(getFileByPath(str));
    }

    private static long getFileLength(File file) {
        if (!isFile(file)) {
            return -1;
        }
        return file.length();
    }

    public static String getFileMD5ToString(String str) {
        return getFileMD5ToString(UtilsBridge.isSpace(str) ? null : new File(str));
    }

    public static String getFileMD5ToString(File file) {
        return UtilsBridge.bytes2HexString(getFileMD5(file));
    }

    public static byte[] getFileMD5(String str) {
        return getFileMD5(getFileByPath(str));
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x003e A[SYNTHETIC, Splitter:B:23:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x004b A[SYNTHETIC, Splitter:B:31:0x004b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] getFileMD5(java.io.File r3) {
        /*
            r0 = 0
            if (r3 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ NoSuchAlgorithmException -> 0x0037, IOException -> 0x0035, all -> 0x0033 }
            r1.<init>(r3)     // Catch:{ NoSuchAlgorithmException -> 0x0037, IOException -> 0x0035, all -> 0x0033 }
            java.lang.String r3 = "MD5"
            java.security.MessageDigest r3 = java.security.MessageDigest.getInstance(r3)     // Catch:{ NoSuchAlgorithmException -> 0x0037, IOException -> 0x0035, all -> 0x0033 }
            java.security.DigestInputStream r2 = new java.security.DigestInputStream     // Catch:{ NoSuchAlgorithmException -> 0x0037, IOException -> 0x0035, all -> 0x0033 }
            r2.<init>(r1, r3)     // Catch:{ NoSuchAlgorithmException -> 0x0037, IOException -> 0x0035, all -> 0x0033 }
            r3 = 262144(0x40000, float:3.67342E-40)
            byte[] r3 = new byte[r3]     // Catch:{ NoSuchAlgorithmException -> 0x0031, IOException -> 0x002f }
        L_0x0018:
            int r1 = r2.read(r3)     // Catch:{ NoSuchAlgorithmException -> 0x0031, IOException -> 0x002f }
            if (r1 > 0) goto L_0x0018
            java.security.MessageDigest r3 = r2.getMessageDigest()     // Catch:{ NoSuchAlgorithmException -> 0x0031, IOException -> 0x002f }
            byte[] r3 = r3.digest()     // Catch:{ NoSuchAlgorithmException -> 0x0031, IOException -> 0x002f }
            r2.close()     // Catch:{ IOException -> 0x002a }
            goto L_0x002e
        L_0x002a:
            r0 = move-exception
            r0.printStackTrace()
        L_0x002e:
            return r3
        L_0x002f:
            r3 = move-exception
            goto L_0x0039
        L_0x0031:
            r3 = move-exception
            goto L_0x0039
        L_0x0033:
            r3 = move-exception
            goto L_0x0049
        L_0x0035:
            r3 = move-exception
            goto L_0x0038
        L_0x0037:
            r3 = move-exception
        L_0x0038:
            r2 = r0
        L_0x0039:
            r3.printStackTrace()     // Catch:{ all -> 0x0047 }
            if (r2 == 0) goto L_0x0046
            r2.close()     // Catch:{ IOException -> 0x0042 }
            goto L_0x0046
        L_0x0042:
            r3 = move-exception
            r3.printStackTrace()
        L_0x0046:
            return r0
        L_0x0047:
            r3 = move-exception
            r0 = r2
        L_0x0049:
            if (r0 == 0) goto L_0x0053
            r0.close()     // Catch:{ IOException -> 0x004f }
            goto L_0x0053
        L_0x004f:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0053:
            goto L_0x0055
        L_0x0054:
            throw r3
        L_0x0055:
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.FileUtils.getFileMD5(java.io.File):byte[]");
    }

    public static String getDirName(File file) {
        return file == null ? "" : getDirName(file.getAbsolutePath());
    }

    public static String getDirName(String str) {
        int lastIndexOf;
        if (!UtilsBridge.isSpace(str) && (lastIndexOf = str.lastIndexOf(File.separator)) != -1) {
            return str.substring(0, lastIndexOf + 1);
        }
        return "";
    }

    public static String getFileName(File file) {
        return file == null ? "" : getFileName(file.getAbsolutePath());
    }

    public static String getFileName(String str) {
        if (UtilsBridge.isSpace(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(File.separator);
        return lastIndexOf == -1 ? str : str.substring(lastIndexOf + 1);
    }

    public static String getFileNameNoExtension(File file) {
        return file == null ? "" : getFileNameNoExtension(file.getPath());
    }

    public static String getFileNameNoExtension(String str) {
        if (UtilsBridge.isSpace(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf2 == -1) {
            return lastIndexOf == -1 ? str : str.substring(0, lastIndexOf);
        }
        if (lastIndexOf == -1 || lastIndexOf2 > lastIndexOf) {
            return str.substring(lastIndexOf2 + 1);
        }
        return str.substring(lastIndexOf2 + 1, lastIndexOf);
    }

    public static String getFileExtension(File file) {
        return file == null ? "" : getFileExtension(file.getPath());
    }

    public static String getFileExtension(String str) {
        if (UtilsBridge.isSpace(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        int lastIndexOf2 = str.lastIndexOf(File.separator);
        if (lastIndexOf == -1 || lastIndexOf2 >= lastIndexOf) {
            return "";
        }
        return str.substring(lastIndexOf + 1);
    }

    public static void notifySystemToScan(String str) {
        notifySystemToScan(getFileByPath(str));
    }

    public static void notifySystemToScan(File file) {
        if (file != null && file.exists()) {
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.parse("file://" + file.getAbsolutePath()));
            Utils.getApp().sendBroadcast(intent);
        }
    }

    public static long getFsTotalSize(String str) {
        long j;
        long j2;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        StatFs statFs = new StatFs(str);
        if (Build.VERSION.SDK_INT >= 18) {
            j2 = statFs.getBlockSizeLong();
            j = statFs.getBlockCountLong();
        } else {
            j2 = (long) statFs.getBlockSize();
            j = (long) statFs.getBlockCount();
        }
        return j2 * j;
    }

    public static long getFsAvailableSize(String str) {
        long j;
        long j2;
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        StatFs statFs = new StatFs(str);
        if (Build.VERSION.SDK_INT >= 18) {
            j2 = statFs.getBlockSizeLong();
            j = statFs.getAvailableBlocksLong();
        } else {
            j2 = (long) statFs.getBlockSize();
            j = (long) statFs.getAvailableBlocks();
        }
        return j2 * j;
    }
}
