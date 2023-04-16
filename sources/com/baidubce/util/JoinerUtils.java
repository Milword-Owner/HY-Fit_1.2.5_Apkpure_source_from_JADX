package com.baidubce.util;

import java.util.List;

public class JoinerUtils {
    /* renamed from: on */
    public static String m1833on(String str, List<String> list) {
        String str2 = "";
        for (String str3 : list) {
            str2 = str2 + str3 + str;
        }
        return str2.substring(0, str2.length() - 1);
    }

    /* renamed from: on */
    public static String m1834on(String str, Object... objArr) {
        String str2 = "";
        for (Object obj : objArr) {
            str2 = str2 + obj + str;
        }
        return str2.substring(0, str2.length() - 1);
    }

    public static String cut(String str, String str2) {
        return str2.replace(str, "");
    }
}
