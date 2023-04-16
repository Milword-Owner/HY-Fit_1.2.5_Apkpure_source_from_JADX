package com.blankj.utilcode.util;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.baidu.mobstat.Config;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public final class RomUtils {
    private static final String[] ROM_360 = {"360", "qiku"};
    private static final String[] ROM_COOLPAD = {"coolpad", "yulong"};
    private static final String[] ROM_GIONEE = {"gionee", "amigo"};
    private static final String[] ROM_GOOGLE = {"google"};
    private static final String[] ROM_HTC = {"htc"};
    private static final String[] ROM_HUAWEI = {"huawei"};
    private static final String[] ROM_LEECO = {"leeco", "letv"};
    private static final String[] ROM_LENOVO = {"lenovo"};
    private static final String[] ROM_LG = {"lg", "lge"};
    private static final String[] ROM_MEIZU = {"meizu"};
    private static final String[] ROM_MOTOROLA = {"motorola"};
    private static final String[] ROM_NUBIA = {"nubia"};
    private static final String[] ROM_ONEPLUS = {"oneplus"};
    private static final String[] ROM_OPPO = {"oppo"};
    private static final String[] ROM_SAMSUNG = {"samsung"};
    private static final String[] ROM_SMARTISAN = {"smartisan"};
    private static final String[] ROM_SONY = {"sony"};
    private static final String[] ROM_VIVO = {"vivo"};
    private static final String[] ROM_XIAOMI = {"xiaomi"};
    private static final String[] ROM_ZTE = {"zte"};
    private static final String UNKNOWN = "unknown";
    private static final String VERSION_PROPERTY_360 = "ro.build.uiversion";
    private static final String VERSION_PROPERTY_HUAWEI = "ro.build.version.emui";
    private static final String VERSION_PROPERTY_LEECO = "ro.letv.release.version";
    private static final String VERSION_PROPERTY_NUBIA = "ro.build.rom.id";
    private static final String VERSION_PROPERTY_ONEPLUS = "ro.rom.version";
    private static final String VERSION_PROPERTY_OPPO = "ro.build.version.opporom";
    private static final String VERSION_PROPERTY_VIVO = "ro.vivo.os.build.display.id";
    private static final String VERSION_PROPERTY_XIAOMI = "ro.build.version.incremental";
    private static final String VERSION_PROPERTY_ZTE = "ro.build.MiFavor_version";
    private static RomInfo bean = null;

    private RomUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isHuawei() {
        return ROM_HUAWEI[0].equals(getRomInfo().name);
    }

    public static boolean isVivo() {
        return ROM_VIVO[0].equals(getRomInfo().name);
    }

    public static boolean isXiaomi() {
        return ROM_XIAOMI[0].equals(getRomInfo().name);
    }

    public static boolean isOppo() {
        return ROM_OPPO[0].equals(getRomInfo().name);
    }

    public static boolean isLeeco() {
        return ROM_LEECO[0].equals(getRomInfo().name);
    }

    public static boolean is360() {
        return ROM_360[0].equals(getRomInfo().name);
    }

    public static boolean isZte() {
        return ROM_ZTE[0].equals(getRomInfo().name);
    }

    public static boolean isOneplus() {
        return ROM_ONEPLUS[0].equals(getRomInfo().name);
    }

    public static boolean isNubia() {
        return ROM_NUBIA[0].equals(getRomInfo().name);
    }

    public static boolean isCoolpad() {
        return ROM_COOLPAD[0].equals(getRomInfo().name);
    }

    public static boolean isLg() {
        return ROM_LG[0].equals(getRomInfo().name);
    }

    public static boolean isGoogle() {
        return ROM_GOOGLE[0].equals(getRomInfo().name);
    }

    public static boolean isSamsung() {
        return ROM_SAMSUNG[0].equals(getRomInfo().name);
    }

    public static boolean isMeizu() {
        return ROM_MEIZU[0].equals(getRomInfo().name);
    }

    public static boolean isLenovo() {
        return ROM_LENOVO[0].equals(getRomInfo().name);
    }

    public static boolean isSmartisan() {
        return ROM_SMARTISAN[0].equals(getRomInfo().name);
    }

    public static boolean isHtc() {
        return ROM_HTC[0].equals(getRomInfo().name);
    }

    public static boolean isSony() {
        return ROM_SONY[0].equals(getRomInfo().name);
    }

    public static boolean isGionee() {
        return ROM_GIONEE[0].equals(getRomInfo().name);
    }

    public static boolean isMotorola() {
        return ROM_MOTOROLA[0].equals(getRomInfo().name);
    }

    public static RomInfo getRomInfo() {
        RomInfo romInfo = bean;
        if (romInfo != null) {
            return romInfo;
        }
        bean = new RomInfo();
        String brand = getBrand();
        String manufacturer = getManufacturer();
        if (isRightRom(brand, manufacturer, ROM_HUAWEI)) {
            String unused = bean.name = ROM_HUAWEI[0];
            String romVersion = getRomVersion(VERSION_PROPERTY_HUAWEI);
            String[] split = romVersion.split(Config.replace);
            if (split.length > 1) {
                String unused2 = bean.version = split[1];
            } else {
                String unused3 = bean.version = romVersion;
            }
            return bean;
        } else if (isRightRom(brand, manufacturer, ROM_VIVO)) {
            String unused4 = bean.name = ROM_VIVO[0];
            String unused5 = bean.version = getRomVersion(VERSION_PROPERTY_VIVO);
            return bean;
        } else if (isRightRom(brand, manufacturer, ROM_XIAOMI)) {
            String unused6 = bean.name = ROM_XIAOMI[0];
            String unused7 = bean.version = getRomVersion(VERSION_PROPERTY_XIAOMI);
            return bean;
        } else if (isRightRom(brand, manufacturer, ROM_OPPO)) {
            String unused8 = bean.name = ROM_OPPO[0];
            String unused9 = bean.version = getRomVersion(VERSION_PROPERTY_OPPO);
            return bean;
        } else if (isRightRom(brand, manufacturer, ROM_LEECO)) {
            String unused10 = bean.name = ROM_LEECO[0];
            String unused11 = bean.version = getRomVersion(VERSION_PROPERTY_LEECO);
            return bean;
        } else if (isRightRom(brand, manufacturer, ROM_360)) {
            String unused12 = bean.name = ROM_360[0];
            String unused13 = bean.version = getRomVersion(VERSION_PROPERTY_360);
            return bean;
        } else if (isRightRom(brand, manufacturer, ROM_ZTE)) {
            String unused14 = bean.name = ROM_ZTE[0];
            String unused15 = bean.version = getRomVersion(VERSION_PROPERTY_ZTE);
            return bean;
        } else if (isRightRom(brand, manufacturer, ROM_ONEPLUS)) {
            String unused16 = bean.name = ROM_ONEPLUS[0];
            String unused17 = bean.version = getRomVersion(VERSION_PROPERTY_ONEPLUS);
            return bean;
        } else if (isRightRom(brand, manufacturer, ROM_NUBIA)) {
            String unused18 = bean.name = ROM_NUBIA[0];
            String unused19 = bean.version = getRomVersion(VERSION_PROPERTY_NUBIA);
            return bean;
        } else {
            if (isRightRom(brand, manufacturer, ROM_COOLPAD)) {
                String unused20 = bean.name = ROM_COOLPAD[0];
            } else if (isRightRom(brand, manufacturer, ROM_LG)) {
                String unused21 = bean.name = ROM_LG[0];
            } else if (isRightRom(brand, manufacturer, ROM_GOOGLE)) {
                String unused22 = bean.name = ROM_GOOGLE[0];
            } else if (isRightRom(brand, manufacturer, ROM_SAMSUNG)) {
                String unused23 = bean.name = ROM_SAMSUNG[0];
            } else if (isRightRom(brand, manufacturer, ROM_MEIZU)) {
                String unused24 = bean.name = ROM_MEIZU[0];
            } else if (isRightRom(brand, manufacturer, ROM_LENOVO)) {
                String unused25 = bean.name = ROM_LENOVO[0];
            } else if (isRightRom(brand, manufacturer, ROM_SMARTISAN)) {
                String unused26 = bean.name = ROM_SMARTISAN[0];
            } else if (isRightRom(brand, manufacturer, ROM_HTC)) {
                String unused27 = bean.name = ROM_HTC[0];
            } else if (isRightRom(brand, manufacturer, ROM_SONY)) {
                String unused28 = bean.name = ROM_SONY[0];
            } else if (isRightRom(brand, manufacturer, ROM_GIONEE)) {
                String unused29 = bean.name = ROM_GIONEE[0];
            } else if (isRightRom(brand, manufacturer, ROM_MOTOROLA)) {
                String unused30 = bean.name = ROM_MOTOROLA[0];
            } else {
                String unused31 = bean.name = manufacturer;
            }
            String unused32 = bean.version = getRomVersion("");
            return bean;
        }
    }

    private static boolean isRightRom(String str, String str2, String... strArr) {
        for (String str3 : strArr) {
            if (str.contains(str3) || str2.contains(str3)) {
                return true;
            }
        }
        return false;
    }

    private static String getManufacturer() {
        try {
            String str = Build.MANUFACTURER;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    private static String getBrand() {
        try {
            String str = Build.BRAND;
            return !TextUtils.isEmpty(str) ? str.toLowerCase() : "unknown";
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    private static String getRomVersion(String str) {
        String systemProperty = !TextUtils.isEmpty(str) ? getSystemProperty(str) : "";
        if (TextUtils.isEmpty(systemProperty) || systemProperty.equals("unknown")) {
            try {
                String str2 = Build.DISPLAY;
                if (!TextUtils.isEmpty(str2)) {
                    systemProperty = str2.toLowerCase();
                }
            } catch (Throwable unused) {
            }
        }
        if (TextUtils.isEmpty(systemProperty)) {
            return "unknown";
        }
        return systemProperty;
    }

    private static String getSystemProperty(String str) {
        String systemPropertyByShell = getSystemPropertyByShell(str);
        if (!TextUtils.isEmpty(systemPropertyByShell)) {
            return systemPropertyByShell;
        }
        String systemPropertyByStream = getSystemPropertyByStream(str);
        return (TextUtils.isEmpty(systemPropertyByStream) && Build.VERSION.SDK_INT < 28) ? getSystemPropertyByReflect(str) : systemPropertyByStream;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0040 A[SYNTHETIC, Splitter:B:18:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0047 A[SYNTHETIC, Splitter:B:24:0x0047] */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getSystemPropertyByShell(java.lang.String r4) {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch:{ IOException -> 0x0044, all -> 0x003d }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0044, all -> 0x003d }
            r2.<init>()     // Catch:{ IOException -> 0x0044, all -> 0x003d }
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch:{ IOException -> 0x0044, all -> 0x003d }
            r2.append(r4)     // Catch:{ IOException -> 0x0044, all -> 0x003d }
            java.lang.String r4 = r2.toString()     // Catch:{ IOException -> 0x0044, all -> 0x003d }
            java.lang.Process r4 = r1.exec(r4)     // Catch:{ IOException -> 0x0044, all -> 0x003d }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0044, all -> 0x003d }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0044, all -> 0x003d }
            java.io.InputStream r4 = r4.getInputStream()     // Catch:{ IOException -> 0x0044, all -> 0x003d }
            r2.<init>(r4)     // Catch:{ IOException -> 0x0044, all -> 0x003d }
            r4 = 1024(0x400, float:1.435E-42)
            r1.<init>(r2, r4)     // Catch:{ IOException -> 0x0044, all -> 0x003d }
            java.lang.String r4 = r1.readLine()     // Catch:{ IOException -> 0x003b, all -> 0x0038 }
            if (r4 == 0) goto L_0x0034
            r1.close()     // Catch:{ IOException -> 0x0033 }
        L_0x0033:
            return r4
        L_0x0034:
            r1.close()     // Catch:{ IOException -> 0x004a }
            goto L_0x004a
        L_0x0038:
            r4 = move-exception
            r0 = r1
            goto L_0x003e
        L_0x003b:
            r0 = r1
            goto L_0x0045
        L_0x003d:
            r4 = move-exception
        L_0x003e:
            if (r0 == 0) goto L_0x0043
            r0.close()     // Catch:{ IOException -> 0x0043 }
        L_0x0043:
            throw r4
        L_0x0044:
        L_0x0045:
            if (r0 == 0) goto L_0x004a
            r0.close()     // Catch:{ IOException -> 0x004a }
        L_0x004a:
            java.lang.String r4 = ""
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.RomUtils.getSystemPropertyByShell(java.lang.String):java.lang.String");
    }

    private static String getSystemPropertyByStream(String str) {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            return properties.getProperty(str, "");
        } catch (Exception unused) {
            return "";
        }
    }

    private static String getSystemPropertyByReflect(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", new Class[]{String.class, String.class}).invoke(cls, new Object[]{str, ""});
        } catch (Exception unused) {
            return "";
        }
    }

    public static class RomInfo {
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public String version;

        public String getName() {
            return this.name;
        }

        public String getVersion() {
            return this.version;
        }

        public String toString() {
            return "RomInfo{name=" + this.name + ", version=" + this.version + "}";
        }
    }
}
