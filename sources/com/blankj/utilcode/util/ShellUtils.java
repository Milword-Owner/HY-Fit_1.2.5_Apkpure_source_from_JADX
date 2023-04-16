package com.blankj.utilcode.util;

import androidx.annotation.NonNull;
import com.blankj.utilcode.util.Utils;
import java.util.List;

public final class ShellUtils {
    private static final String LINE_SEP = System.getProperty("line.separator");

    private ShellUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static Utils.Task<CommandResult> execCmdAsync(String str, boolean z, Utils.Consumer<CommandResult> consumer) {
        return execCmdAsync(new String[]{str}, z, true, consumer);
    }

    public static Utils.Task<CommandResult> execCmdAsync(List<String> list, boolean z, Utils.Consumer<CommandResult> consumer) {
        return execCmdAsync(list == null ? null : (String[]) list.toArray(new String[0]), z, true, consumer);
    }

    public static Utils.Task<CommandResult> execCmdAsync(String[] strArr, boolean z, Utils.Consumer<CommandResult> consumer) {
        return execCmdAsync(strArr, z, true, consumer);
    }

    public static Utils.Task<CommandResult> execCmdAsync(String str, boolean z, boolean z2, Utils.Consumer<CommandResult> consumer) {
        return execCmdAsync(new String[]{str}, z, z2, consumer);
    }

    public static Utils.Task<CommandResult> execCmdAsync(List<String> list, boolean z, boolean z2, Utils.Consumer<CommandResult> consumer) {
        return execCmdAsync(list == null ? null : (String[]) list.toArray(new String[0]), z, z2, consumer);
    }

    public static Utils.Task<CommandResult> execCmdAsync(final String[] strArr, final boolean z, final boolean z2, @NonNull Utils.Consumer<CommandResult> consumer) {
        if (consumer != null) {
            return UtilsBridge.doAsync(new Utils.Task<CommandResult>(consumer) {
                public CommandResult doInBackground() {
                    return ShellUtils.execCmd(strArr, z, z2);
                }
            });
        }
        throw new NullPointerException("Argument 'consumer' of type Utils.Consumer<CommandResult> (#3 out of 4, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static CommandResult execCmd(String str, boolean z) {
        return execCmd(new String[]{str}, z, true);
    }

    public static CommandResult execCmd(List<String> list, boolean z) {
        return execCmd(list == null ? null : (String[]) list.toArray(new String[0]), z, true);
    }

    public static CommandResult execCmd(String[] strArr, boolean z) {
        return execCmd(strArr, z, true);
    }

    public static CommandResult execCmd(String str, boolean z, boolean z2) {
        return execCmd(new String[]{str}, z, z2);
    }

    public static CommandResult execCmd(List<String> list, boolean z, boolean z2) {
        return execCmd(list == null ? null : (String[]) list.toArray(new String[0]), z, z2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v4, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v5, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v10, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v6, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v7, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v8, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v4, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v9, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v6, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v10, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v11, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v24, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v25, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v12, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v26, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v7, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v14, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v15, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v16, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: java.io.BufferedReader} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v9, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v10, resolved type: java.io.DataOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v31, resolved type: java.lang.StringBuilder} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v17, resolved type: java.lang.StringBuilder} */
    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r3v2, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r3v5 */
    /* JADX WARNING: type inference failed for: r3v6, types: [java.io.BufferedReader] */
    /* JADX WARNING: type inference failed for: r3v7 */
    /* JADX WARNING: type inference failed for: r3v8 */
    /* JADX WARNING: type inference failed for: r3v9 */
    /* JADX WARNING: Code restructure failed: missing block: B:100:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0134, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0135, code lost:
        r10.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x013a, code lost:
        r11.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x015d, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x015e, code lost:
        r12.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0167, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0168, code lost:
        r12.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0171, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0172, code lost:
        r12.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0177, code lost:
        r11.destroy();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c3, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00c4, code lost:
        r5 = null;
        r6 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00cd, code lost:
        r12 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ce, code lost:
        r0 = null;
        r5 = null;
        r6 = null;
        r3 = r4;
        r9 = r12;
        r12 = r10;
        r10 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x00fb, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x00fc, code lost:
        r6 = null;
        r4 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0120, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0121, code lost:
        r10.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x012a, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x012b, code lost:
        r10.printStackTrace();
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:104:0x013a  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x0159 A[SYNTHETIC, Splitter:B:118:0x0159] */
    /* JADX WARNING: Removed duplicated region for block: B:123:0x0163 A[SYNTHETIC, Splitter:B:123:0x0163] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x016d A[SYNTHETIC, Splitter:B:128:0x016d] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0177  */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x00fb A[ExcHandler: all (th java.lang.Throwable), Splitter:B:13:0x0025] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x011c A[SYNTHETIC, Splitter:B:89:0x011c] */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0126 A[SYNTHETIC, Splitter:B:94:0x0126] */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0130 A[SYNTHETIC, Splitter:B:99:0x0130] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.blankj.utilcode.util.ShellUtils.CommandResult execCmd(java.lang.String[] r10, boolean r11, boolean r12) {
        /*
            java.lang.String r0 = "UTF-8"
            java.lang.String r1 = ""
            r2 = -1
            if (r10 == 0) goto L_0x017b
            int r3 = r10.length
            if (r3 != 0) goto L_0x000c
            goto L_0x017b
        L_0x000c:
            r3 = 0
            java.lang.Runtime r4 = java.lang.Runtime.getRuntime()     // Catch:{ Exception -> 0x0111, all -> 0x010c }
            if (r11 == 0) goto L_0x0016
            java.lang.String r11 = "su"
            goto L_0x0018
        L_0x0016:
            java.lang.String r11 = "sh"
        L_0x0018:
            java.lang.Process r11 = r4.exec(r11)     // Catch:{ Exception -> 0x0111, all -> 0x010c }
            java.io.DataOutputStream r4 = new java.io.DataOutputStream     // Catch:{ Exception -> 0x0109, all -> 0x0106 }
            java.io.OutputStream r5 = r11.getOutputStream()     // Catch:{ Exception -> 0x0109, all -> 0x0106 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0109, all -> 0x0106 }
            int r5 = r10.length     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            r6 = 0
        L_0x0027:
            if (r6 >= r5) goto L_0x0040
            r7 = r10[r6]     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            if (r7 != 0) goto L_0x002e
            goto L_0x003d
        L_0x002e:
            byte[] r7 = r7.getBytes()     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            r4.write(r7)     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            java.lang.String r7 = LINE_SEP     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            r4.writeBytes(r7)     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            r4.flush()     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
        L_0x003d:
            int r6 = r6 + 1
            goto L_0x0027
        L_0x0040:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            r10.<init>()     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            java.lang.String r5 = "exit"
            r10.append(r5)     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            java.lang.String r5 = LINE_SEP     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            r10.append(r5)     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            java.lang.String r10 = r10.toString()     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            r4.writeBytes(r10)     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            r4.flush()     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            int r2 = r11.waitFor()     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            if (r12 == 0) goto L_0x00d6
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            r10.<init>()     // Catch:{ Exception -> 0x00ff, all -> 0x00fb }
            java.lang.StringBuilder r12 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00cd, all -> 0x00fb }
            r12.<init>()     // Catch:{ Exception -> 0x00cd, all -> 0x00fb }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00c3, all -> 0x00fb }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00c3, all -> 0x00fb }
            java.io.InputStream r7 = r11.getInputStream()     // Catch:{ Exception -> 0x00c3, all -> 0x00fb }
            r6.<init>(r7, r0)     // Catch:{ Exception -> 0x00c3, all -> 0x00fb }
            r5.<init>(r6)     // Catch:{ Exception -> 0x00c3, all -> 0x00fb }
            java.io.BufferedReader r6 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00c0, all -> 0x00bc }
            java.io.InputStreamReader r7 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00c0, all -> 0x00bc }
            java.io.InputStream r8 = r11.getErrorStream()     // Catch:{ Exception -> 0x00c0, all -> 0x00bc }
            r7.<init>(r8, r0)     // Catch:{ Exception -> 0x00c0, all -> 0x00bc }
            r6.<init>(r7)     // Catch:{ Exception -> 0x00c0, all -> 0x00bc }
            java.lang.String r0 = r5.readLine()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            if (r0 == 0) goto L_0x009d
            r10.append(r0)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
        L_0x008e:
            java.lang.String r0 = r5.readLine()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            if (r0 == 0) goto L_0x009d
            java.lang.String r3 = LINE_SEP     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r10.append(r3)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r10.append(r0)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            goto L_0x008e
        L_0x009d:
            java.lang.String r0 = r6.readLine()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            if (r0 == 0) goto L_0x00b5
            r12.append(r0)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
        L_0x00a6:
            java.lang.String r0 = r6.readLine()     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            if (r0 == 0) goto L_0x00b5
            java.lang.String r3 = LINE_SEP     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r12.append(r3)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            r12.append(r0)     // Catch:{ Exception -> 0x00ba, all -> 0x00b7 }
            goto L_0x00a6
        L_0x00b5:
            r3 = r5
            goto L_0x00d9
        L_0x00b7:
            r10 = move-exception
            goto L_0x0156
        L_0x00ba:
            r0 = move-exception
            goto L_0x00c6
        L_0x00bc:
            r10 = move-exception
            r6 = r3
            goto L_0x0156
        L_0x00c0:
            r0 = move-exception
            r6 = r3
            goto L_0x00c6
        L_0x00c3:
            r0 = move-exception
            r5 = r3
            r6 = r5
        L_0x00c6:
            r3 = r4
            r9 = r12
            r12 = r10
            r10 = r0
            r0 = r9
            goto L_0x0117
        L_0x00cd:
            r12 = move-exception
            r0 = r3
            r5 = r0
            r6 = r5
            r3 = r4
            r9 = r12
            r12 = r10
            r10 = r9
            goto L_0x0117
        L_0x00d6:
            r10 = r3
            r12 = r10
            r6 = r12
        L_0x00d9:
            r4.close()     // Catch:{ IOException -> 0x00dd }
            goto L_0x00e1
        L_0x00dd:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00e1:
            if (r3 == 0) goto L_0x00eb
            r3.close()     // Catch:{ IOException -> 0x00e7 }
            goto L_0x00eb
        L_0x00e7:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00eb:
            if (r6 == 0) goto L_0x00f5
            r6.close()     // Catch:{ IOException -> 0x00f1 }
            goto L_0x00f5
        L_0x00f1:
            r0 = move-exception
            r0.printStackTrace()
        L_0x00f5:
            if (r11 == 0) goto L_0x013f
            r11.destroy()
            goto L_0x013f
        L_0x00fb:
            r10 = move-exception
            r6 = r3
            goto L_0x0157
        L_0x00ff:
            r10 = move-exception
            r12 = r3
            r0 = r12
            r5 = r0
            r6 = r5
            r3 = r4
            goto L_0x0117
        L_0x0106:
            r10 = move-exception
            r4 = r3
            goto L_0x010f
        L_0x0109:
            r10 = move-exception
            r12 = r3
            goto L_0x0114
        L_0x010c:
            r10 = move-exception
            r11 = r3
            r4 = r11
        L_0x010f:
            r6 = r4
            goto L_0x0157
        L_0x0111:
            r10 = move-exception
            r11 = r3
            r12 = r11
        L_0x0114:
            r0 = r12
            r5 = r0
            r6 = r5
        L_0x0117:
            r10.printStackTrace()     // Catch:{ all -> 0x0154 }
            if (r3 == 0) goto L_0x0124
            r3.close()     // Catch:{ IOException -> 0x0120 }
            goto L_0x0124
        L_0x0120:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0124:
            if (r5 == 0) goto L_0x012e
            r5.close()     // Catch:{ IOException -> 0x012a }
            goto L_0x012e
        L_0x012a:
            r10 = move-exception
            r10.printStackTrace()
        L_0x012e:
            if (r6 == 0) goto L_0x0138
            r6.close()     // Catch:{ IOException -> 0x0134 }
            goto L_0x0138
        L_0x0134:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0138:
            if (r11 == 0) goto L_0x013d
            r11.destroy()
        L_0x013d:
            r10 = r12
            r12 = r0
        L_0x013f:
            com.blankj.utilcode.util.ShellUtils$CommandResult r11 = new com.blankj.utilcode.util.ShellUtils$CommandResult
            if (r10 != 0) goto L_0x0145
            r10 = r1
            goto L_0x0149
        L_0x0145:
            java.lang.String r10 = r10.toString()
        L_0x0149:
            if (r12 != 0) goto L_0x014c
            goto L_0x0150
        L_0x014c:
            java.lang.String r1 = r12.toString()
        L_0x0150:
            r11.<init>(r2, r10, r1)
            return r11
        L_0x0154:
            r10 = move-exception
            r4 = r3
        L_0x0156:
            r3 = r5
        L_0x0157:
            if (r4 == 0) goto L_0x0161
            r4.close()     // Catch:{ IOException -> 0x015d }
            goto L_0x0161
        L_0x015d:
            r12 = move-exception
            r12.printStackTrace()
        L_0x0161:
            if (r3 == 0) goto L_0x016b
            r3.close()     // Catch:{ IOException -> 0x0167 }
            goto L_0x016b
        L_0x0167:
            r12 = move-exception
            r12.printStackTrace()
        L_0x016b:
            if (r6 == 0) goto L_0x0175
            r6.close()     // Catch:{ IOException -> 0x0171 }
            goto L_0x0175
        L_0x0171:
            r12 = move-exception
            r12.printStackTrace()
        L_0x0175:
            if (r11 == 0) goto L_0x017a
            r11.destroy()
        L_0x017a:
            throw r10
        L_0x017b:
            com.blankj.utilcode.util.ShellUtils$CommandResult r10 = new com.blankj.utilcode.util.ShellUtils$CommandResult
            r10.<init>(r2, r1, r1)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.ShellUtils.execCmd(java.lang.String[], boolean, boolean):com.blankj.utilcode.util.ShellUtils$CommandResult");
    }

    public static class CommandResult {
        public String errorMsg;
        public int result;
        public String successMsg;

        public CommandResult(int i, String str, String str2) {
            this.result = i;
            this.successMsg = str;
            this.errorMsg = str2;
        }

        public String toString() {
            return "result: " + this.result + "\nsuccessMsg: " + this.successMsg + "\nerrorMsg: " + this.errorMsg;
        }
    }
}
