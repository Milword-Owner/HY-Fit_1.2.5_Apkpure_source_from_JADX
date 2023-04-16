package com.baidu.mobstat;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.baidu.mobstat.C0933at;
import com.baidu.mobstat.C0985bs;
import com.baidubce.BceConfig;
import com.facebook.appevents.codeless.internal.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.bi */
public class C0968bi {
    /* renamed from: a */
    public static View m1241a(Activity activity) {
        Window window;
        if (activity == null || (window = activity.getWindow()) == null) {
            return null;
        }
        return window.getDecorView();
    }

    /* renamed from: b */
    public static View m1261b(Activity activity) {
        View a = m1241a(activity);
        if (a != null) {
            return a.getRootView();
        }
        return null;
    }

    /* renamed from: c */
    public static int m1270c(Activity activity) {
        if (activity == null) {
            return 0;
        }
        WindowManager windowManager = activity.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /* renamed from: d */
    public static int m1278d(Activity activity) {
        if (activity == null) {
            return 0;
        }
        WindowManager windowManager = activity.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static String m1245a(View view) {
        String str;
        String str2;
        CharSequence charSequence;
        CharSequence text;
        if (view == null) {
            return "";
        }
        if (view instanceof TextView) {
            if ((view instanceof EditText) || (text = ((TextView) view).getText()) == null) {
                str2 = "";
            } else {
                str2 = text.toString();
            }
            if (Build.VERSION.SDK_INT >= 14 && (view instanceof Switch)) {
                Switch switchR = (Switch) view;
                if (switchR.isChecked()) {
                    charSequence = switchR.getTextOn();
                } else {
                    charSequence = switchR.getTextOff();
                }
                if (charSequence != null) {
                    str = charSequence.toString();
                }
            }
            str = str2;
        } else if (view instanceof Spinner) {
            Spinner spinner = (Spinner) view;
            Object selectedItem = spinner.getSelectedItem();
            if (selectedItem == null || !(selectedItem instanceof String)) {
                return m1245a(spinner.getSelectedView());
            }
            str = (String) selectedItem;
        } else {
            str = "";
        }
        byte[] bytes = str.getBytes();
        if (bytes.length <= 4096) {
            return str;
        }
        if (Build.VERSION.SDK_INT >= 9) {
            return new String(Arrays.copyOf(bytes, 4096));
        }
        return "";
    }

    /* renamed from: b */
    public static String m1263b(View view) {
        String str;
        if (view instanceof ListView) {
            str = ListView.class.getSimpleName();
        } else {
            str = view instanceof WebView ? WebView.class.getSimpleName() : "";
        }
        if (TextUtils.isEmpty(str)) {
            String a = m1249a(view.getClass());
            if (!"android.widget".equals(a) && !"android.view".equals(a)) {
                Class<?> cls = null;
                try {
                    cls = Class.forName("androidx.recyclerview.widget.RecyclerView");
                } catch (Exception unused) {
                }
                if (cls != null && cls.isAssignableFrom(view.getClass())) {
                    str = "RecyclerView";
                }
            }
        }
        if (TextUtils.isEmpty(str)) {
            str = m1273c(view.getClass());
        }
        return TextUtils.isEmpty(str) ? "Object" : str;
    }

    /* renamed from: c */
    private static String m1273c(Class<?> cls) {
        if (cls == null) {
            return "";
        }
        String a = m1249a(cls);
        if ("android.widget".equals(a) || "android.view".equals(a)) {
            return m1279d(cls);
        }
        return m1273c((Class<?>) cls.getSuperclass());
    }

    /* renamed from: a */
    public static String m1249a(Class<?> cls) {
        String str;
        if (cls == null) {
            return "";
        }
        Package packageR = cls.getPackage();
        if (packageR != null) {
            str = packageR.getName();
        } else {
            str = "";
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    /* renamed from: a */
    public static String m1246a(View view, View view2) {
        if (view == null) {
            return String.valueOf(0);
        }
        if (view == view2) {
            return String.valueOf(0);
        }
        ViewParent parent = view.getParent();
        if (parent == null || !(parent instanceof ViewGroup)) {
            return String.valueOf(0);
        }
        Class<?> cls = view.getClass();
        if (cls == null) {
            return String.valueOf(0);
        }
        String b = m1264b(cls);
        if (TextUtils.isEmpty(b)) {
            return String.valueOf(0);
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        int i = 0;
        for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if (childAt != null) {
                if (childAt == view) {
                    break;
                } else if (childAt.getClass() != null && b.equals(m1264b(childAt.getClass()))) {
                    i++;
                }
            }
        }
        return String.valueOf(i);
    }

    /* renamed from: a */
    public static String m1247a(View view, String str) {
        ViewParent parent;
        if (TextUtils.isEmpty(str) || view == null || (parent = view.getParent()) == null || !(parent instanceof View)) {
            return "";
        }
        View view2 = (View) parent;
        if (ListView.class.getSimpleName().equals(str)) {
            try {
                if (!(view2 instanceof ListView) || view.getParent() == null) {
                    return "";
                }
                return String.valueOf(((ListView) view2).getPositionForView(view));
            } catch (Throwable unused) {
                return "";
            }
        } else if (GridView.class.getSimpleName().equals(str)) {
            if (!(view2 instanceof GridView) || view.getParent() == null) {
                return "";
            }
            return String.valueOf(((GridView) view2).getPositionForView(view));
        } else if ("RecyclerView".equals(str)) {
            return String.valueOf(((RecyclerView) view2).getChildLayoutPosition(view));
        } else {
            return "";
        }
    }

    /* renamed from: c */
    public static String m1271c(View view) {
        ViewParent parent;
        String str;
        if (view == null || (parent = view.getParent()) == null || !(parent instanceof ViewGroup)) {
            return "";
        }
        String a = m1249a(parent.getClass());
        if ("android.widget".equals(a) || "android.view".equals(a)) {
            return "";
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        Class<?> cls = null;
        try {
            cls = Class.forName("androidx.viewpager.widget.ViewPager");
        } catch (ClassNotFoundException unused) {
        }
        if (cls == null || !cls.isAssignableFrom(viewGroup.getClass())) {
            return "";
        }
        try {
            ViewPager viewPager = (ViewPager) viewGroup;
            ArrayList arrayList = new ArrayList();
            int childCount = viewPager.getChildCount();
            int i = 0;
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = viewPager.getChildAt(i2);
                arrayList.add(childAt);
                if (m1282e(childAt) != null) {
                    i++;
                }
            }
            if (arrayList.size() < 2 || i < 2) {
                str = String.valueOf(viewPager.getCurrentItem());
            } else {
                try {
                    Collections.sort(arrayList, new Comparator<View>() {
                        /* renamed from: a */
                        public int compare(View view, View view2) {
                            return view.getLeft() - view2.getLeft();
                        }
                    });
                } catch (Exception unused2) {
                }
                int left = view.getLeft() / Math.abs(((View) arrayList.get(1)).getLeft() - ((View) arrayList.get(0)).getLeft());
                int count = viewPager.getAdapter().getCount();
                if (count != 0) {
                    left %= count;
                }
                str = String.valueOf(left);
            }
            return str;
        } catch (Throwable unused3) {
            return "";
        }
    }

    /* renamed from: a */
    public static String m1244a(Bitmap bitmap) {
        byte[] c = m1277c(bitmap);
        if (c == null) {
            return "";
        }
        try {
            return C0981bo.m1356b(c);
        } catch (Exception unused) {
            return "";
        }
    }

    /* renamed from: b */
    public static String m1262b(Bitmap bitmap) {
        byte[] c = m1277c(bitmap);
        return c != null ? C0985bs.C0986a.m1411a(c) : "";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0025, code lost:
        if (r1 != null) goto L_0x0014;
     */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0020 A[SYNTHETIC, Splitter:B:14:0x0020] */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] m1277c(android.graphics.Bitmap r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x0024, all -> 0x001c }
            r1.<init>()     // Catch:{ Exception -> 0x0024, all -> 0x001c }
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.PNG     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            r3 = 100
            r4.compress(r2, r3, r1)     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
            byte[] r0 = r1.toByteArray()     // Catch:{ Exception -> 0x001a, all -> 0x0018 }
        L_0x0014:
            r1.close()     // Catch:{ Exception -> 0x0028 }
            goto L_0x0028
        L_0x0018:
            r4 = move-exception
            goto L_0x001e
        L_0x001a:
            goto L_0x0025
        L_0x001c:
            r4 = move-exception
            r1 = r0
        L_0x001e:
            if (r1 == 0) goto L_0x0023
            r1.close()     // Catch:{ Exception -> 0x0023 }
        L_0x0023:
            throw r4
        L_0x0024:
            r1 = r0
        L_0x0025:
            if (r1 == 0) goto L_0x0028
            goto L_0x0014
        L_0x0028:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0968bi.m1277c(android.graphics.Bitmap):byte[]");
    }

    /* renamed from: d */
    public static boolean m1281d(View view) {
        if (view.getVisibility() != 0) {
            return false;
        }
        return m1257a(view, new Rect());
    }

    /* renamed from: e */
    public static Rect m1282e(View view) {
        if (view.getVisibility() != 0) {
            return null;
        }
        Rect rect = new Rect();
        if (m1257a(view, rect) && rect.right > rect.left && rect.bottom > rect.top) {
            return rect;
        }
        return null;
    }

    /* renamed from: a */
    private static boolean m1257a(View view, Rect rect) {
        if (view == null || rect == null) {
            return false;
        }
        try {
            return view.getGlobalVisibleRect(rect);
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: f */
    public static String m1285f(View view) {
        int lastIndexOf;
        int i;
        String str = null;
        try {
            if (view.getId() != 0) {
                str = view.getResources().getResourceName(view.getId());
            }
        } catch (Exception unused) {
        }
        if (!TextUtils.isEmpty(str) && str.contains(":id/") && (lastIndexOf = str.lastIndexOf(":id/")) != -1 && (i = lastIndexOf + 4) < str.length()) {
            str = str.substring(i);
        }
        return str == null ? "" : str;
    }

    /* renamed from: a */
    public static JSONArray m1253a(Activity activity, View view) {
        JSONArray jSONArray = new JSONArray();
        if (activity == null || view == null) {
            return jSONArray;
        }
        View view2 = null;
        try {
            view2 = m1241a(activity);
        } catch (Exception unused) {
        }
        if (view2 == null) {
            return jSONArray;
        }
        while (true) {
            if (view == null) {
                break;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("p", m1293k(view));
                String c = m1271c(view);
                if (TextUtils.isEmpty(c)) {
                    String str = "";
                    ViewParent parent = view.getParent();
                    if (parent != null && (parent instanceof View)) {
                        str = m1263b((View) parent);
                    }
                    c = m1247a(view, str);
                    if (TextUtils.isEmpty(c)) {
                        c = m1246a(view, view2);
                    }
                }
                jSONObject.put("i", c);
                jSONObject.put("t", m1263b(view));
                jSONArray.put(jSONObject);
                ViewParent parent2 = view.getParent();
                if (parent2 == null) {
                    break;
                } else if (view == view2) {
                    break;
                } else if (!(parent2 instanceof View)) {
                    break;
                } else if (m1304v(view)) {
                    break;
                } else if (jSONArray.length() > 1000) {
                    break;
                } else {
                    view = (View) parent2;
                }
            } catch (Exception unused2) {
                jSONArray = new JSONArray();
            }
        }
        JSONArray jSONArray2 = new JSONArray();
        try {
            for (int length = jSONArray.length() - 1; length >= 0; length--) {
                jSONArray2.put(jSONArray.get(length));
            }
        } catch (Exception unused3) {
        }
        return jSONArray2;
    }

    /* renamed from: g */
    public static Map<String, String> m1287g(View view) {
        Map<String, String> map;
        Object tag = view.getTag(-96000);
        if (tag != null && (tag instanceof Map)) {
            try {
                map = (Map) tag;
            } catch (Exception unused) {
                map = null;
            }
            if (map == null || map.size() == 0) {
                return null;
            }
            return map;
        }
        return null;
    }

    /* renamed from: b */
    public static boolean m1269b(View view, String str) {
        if ("ListView".equals(str) || "RecyclerView".equals(str) || "GridView".equals(str) || view.isClickable()) {
            return true;
        }
        return false;
    }

    /* renamed from: h */
    public static String m1289h(View view) {
        String str = null;
        if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (text != null) {
                str = text.toString();
            }
        } else if (view instanceof ViewGroup) {
            StringBuilder sb = new StringBuilder();
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            boolean z = false;
            for (int i = 0; i < childCount && sb.length() < 128; i++) {
                String h = m1289h(viewGroup.getChildAt(i));
                if (h != null && h.length() > 0) {
                    if (z) {
                        sb.append(", ");
                    }
                    sb.append(h);
                    z = true;
                }
            }
            if (sb.length() > 128) {
                str = sb.substring(0, 128);
            } else if (z) {
                str = sb.toString();
            }
        }
        return TextUtils.isEmpty(str) ? "" : str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000a, code lost:
        r3 = r3.getBackground();
     */
    @android.annotation.SuppressLint({"NewApi"})
    /* renamed from: i */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int m1290i(android.view.View r3) {
        /*
            float r0 = r3.getAlpha()
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 14
            if (r1 < r2) goto L_0x0015
            android.graphics.drawable.Drawable r3 = r3.getBackground()
            if (r3 == 0) goto L_0x0015
            int r3 = r3.getAlpha()
            goto L_0x0016
        L_0x0015:
            r3 = 0
        L_0x0016:
            float r3 = (float) r3
            float r0 = r0 * r3
            int r3 = (int) r0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0968bi.m1290i(android.view.View):int");
    }

    @SuppressLint({"NewApi"})
    /* renamed from: j */
    public static float m1292j(View view) {
        if (Build.VERSION.SDK_INT >= 21) {
            return view.getZ();
        }
        return 0.0f;
    }

    /* renamed from: a */
    public static String m1252a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                String string = jSONObject.getString("p");
                String string2 = jSONObject.getString("i");
                sb.append(BceConfig.BOS_DELIMITER + string + "[" + string2 + "]");
                i++;
            } catch (Exception unused) {
                return "";
            }
        }
        return sb.toString();
    }

    /* renamed from: b */
    public static String m1266b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                String string = jSONObject.getString("p");
                String string2 = jSONObject.getString("i");
                sb.append(BceConfig.BOS_DELIMITER + string + "[" + string2 + "]");
                String optString = jSONObject.optString("d");
                if (!TextUtils.isEmpty(optString)) {
                    sb.append("#" + optString);
                }
                i++;
            } catch (Exception unused) {
                return "";
            }
        }
        return sb.toString();
    }

    /* renamed from: c */
    public static String m1274c(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                String b = m1265b(jSONObject.getString("p"));
                String string = jSONObject.getString("i");
                sb.append(BceConfig.BOS_DELIMITER + b + "[" + string + "]");
                i++;
            } catch (Exception unused) {
                return "";
            }
        }
        return sb.toString();
    }

    /* renamed from: d */
    public static String m1280d(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                String b = m1265b(jSONObject.getString("p"));
                String string = jSONObject.getString("i");
                sb.append(BceConfig.BOS_DELIMITER + b + "[" + string + "]");
                String optString = jSONObject.optString("d");
                if (!TextUtils.isEmpty(optString)) {
                    sb.append("#" + optString);
                }
                i++;
            } catch (Exception unused) {
                return "";
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static String m1248a(View view, boolean z) {
        String str = null;
        if (view instanceof TextView) {
            CharSequence text = ((TextView) view).getText();
            if (text != null) {
                str = text.toString();
            }
        } else if (view instanceof ViewGroup) {
            StringBuilder sb = new StringBuilder();
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            boolean z2 = false;
            for (int i = 0; i < childCount && sb.length() < 128; i++) {
                String a = m1248a(viewGroup.getChildAt(i), false);
                if (a != null && a.length() > 0) {
                    if (z2) {
                        sb.append("| ");
                    }
                    sb.append(a);
                    z2 = true;
                }
            }
            if (sb.length() > 4096) {
                str = sb.substring(0, 4096);
            } else if (z2) {
                str = sb.toString();
            }
        }
        return TextUtils.isEmpty(str) ? "" : str;
    }

    /* renamed from: b */
    private static String m1265b(String str) {
        String a = C0948ax.m1145a().mo11614a(str);
        if (TextUtils.isEmpty(a)) {
            a = C0933at.m1079a().mo11582a(str, C0933at.C0935a.f1126a);
        }
        return a == null ? "" : a;
    }

    /* renamed from: a */
    public static String m1251a(String str) {
        String a = C0933at.m1079a().mo11582a(str, C0933at.C0935a.f1127b);
        return a == null ? "" : a;
    }

    /* renamed from: e */
    public static String m1283e(Activity activity) {
        if (activity == null || activity.getClass() == null) {
            return "";
        }
        String name = activity.getClass().getName();
        if (!TextUtils.isEmpty(name)) {
            return name;
        }
        return "";
    }

    /* renamed from: b */
    public static String m1264b(Class<?> cls) {
        String str;
        if (cls == null) {
            return "";
        }
        String a = m1250a(cls, false);
        if (TextUtils.isEmpty(a) || !cls.isAnonymousClass()) {
            str = a;
        } else {
            str = a + "$";
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    /* renamed from: k */
    public static String m1293k(View view) {
        Class<?> cls;
        String str;
        if (view == null || (cls = view.getClass()) == null) {
            return "";
        }
        String d = m1279d(cls);
        if (TextUtils.isEmpty(d) || !cls.isAnonymousClass()) {
            str = d;
        } else {
            str = d + "$";
        }
        if (str == null) {
            return "";
        }
        return str;
    }

    /* renamed from: a */
    private static String m1250a(Class<?> cls, boolean z) {
        if (!cls.isAnonymousClass()) {
            return z ? cls.getSimpleName() : cls.getName();
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null) {
            return z ? superclass.getSimpleName() : superclass.getName();
        }
        return "";
    }

    /* renamed from: d */
    private static String m1279d(Class<?> cls) {
        return m1250a(cls, true);
    }

    /* renamed from: l */
    public static boolean m1294l(View view) {
        if (view == null) {
            return false;
        }
        if ((view instanceof ListView) || (view instanceof GridView)) {
            return true;
        }
        String a = m1249a(view.getClass());
        if ("android.widget".equals(a) || "android.view".equals(a)) {
            return false;
        }
        Class<?> cls = null;
        try {
            cls = Class.forName("androidx.recyclerview.widget.RecyclerView");
        } catch (Exception unused) {
        }
        if (cls == null || !cls.isAssignableFrom(view.getClass())) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m1258a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return !str.equals(str2);
    }

    /* renamed from: m */
    public static View m1295m(View view) {
        if (view == null) {
            return null;
        }
        ViewParent parent = view.getParent();
        if (!(parent instanceof View)) {
            return null;
        }
        View view2 = (View) parent;
        if (view2 == null || !m1294l(view2)) {
            return null;
        }
        return view2;
    }

    /* renamed from: a */
    public static View m1242a(View view, Activity activity) {
        View view2;
        if (view == null || activity == null) {
            return null;
        }
        try {
            view2 = m1241a(activity);
        } catch (Exception unused) {
            view2 = null;
        }
        if (view2 == null) {
            return null;
        }
        while (view != null && view != view2 && view.getParent() != null && (view.getParent() instanceof View)) {
            View view3 = (View) view.getParent();
            if (m1294l(view3)) {
                return view;
            }
            view = view3;
        }
        return null;
    }

    /* renamed from: f */
    public static String m1284f(Activity activity) {
        String str;
        CharSequence title;
        if (activity == null || (title = activity.getTitle()) == null) {
            str = "";
        } else {
            str = title.toString();
        }
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        return str.length() > 256 ? str.substring(0, 256) : str;
    }

    /* renamed from: n */
    public static int m1296n(View view) {
        if (view != null) {
            return view.getWidth();
        }
        return 0;
    }

    /* renamed from: o */
    public static int m1297o(View view) {
        if (view != null) {
            return view.getHeight();
        }
        return 0;
    }

    /* renamed from: p */
    public static boolean m1298p(View view) {
        if (view == null) {
            return false;
        }
        String a = m1249a(view.getClass());
        if ("android.widget".equals(a) || "android.view".equals(a)) {
            return false;
        }
        Class<?> cls = null;
        try {
            cls = Class.forName("androidx.recyclerview.widget.RecyclerView");
        } catch (Exception unused) {
        }
        if (cls == null || !cls.isAssignableFrom(view.getClass())) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static int m1240a(ListView listView) {
        int height = listView.getHeight();
        if (listView.getChildCount() <= 0) {
            return height;
        }
        int height2 = listView.getChildAt(0).getHeight();
        int i = 1;
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            i = adapter.getCount();
        }
        int i2 = height2 * i;
        return i2 >= height ? i2 : height;
    }

    @TargetApi(11)
    /* renamed from: a */
    public static int m1239a(GridView gridView) {
        int height = gridView.getHeight();
        if (gridView.getChildCount() <= 0) {
            return height;
        }
        int height2 = gridView.getChildAt(0).getHeight();
        int i = 1;
        if (Build.VERSION.SDK_INT >= 11) {
            ListAdapter adapter = gridView.getAdapter();
            int numColumns = gridView.getNumColumns();
            if (!(adapter == null || numColumns == 0)) {
                double count = (double) adapter.getCount();
                double d = (double) numColumns;
                Double.isNaN(count);
                Double.isNaN(d);
                i = (int) Math.ceil(count / d);
            }
        }
        int i2 = height2 * i;
        return i2 >= height ? i2 : height;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0073  */
    /* renamed from: b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<java.lang.Integer> m1267b(android.app.Activity r4, android.view.View r5) {
        /*
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r0 = 0
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            if (r5 != 0) goto L_0x0013
            r4.add(r1)
            r4.add(r1)
            return r4
        L_0x0013:
            int r1 = r5.getWidth()
            int r2 = r5.getHeight()
            boolean r3 = r5 instanceof android.webkit.WebView
            if (r3 == 0) goto L_0x0028
            int r3 = r5.getScrollX()
            int r5 = r5.getScrollY()
            goto L_0x006a
        L_0x0028:
            boolean r3 = r5 instanceof android.widget.ScrollView
            if (r3 == 0) goto L_0x003d
            android.widget.ScrollView r5 = (android.widget.ScrollView) r5
            int r3 = r5.getChildCount()
            if (r3 <= 0) goto L_0x0068
            int r3 = r5.getScrollX()
            int r5 = r5.getScrollY()
            goto L_0x006a
        L_0x003d:
            boolean r3 = r5 instanceof android.widget.ListView
            if (r3 == 0) goto L_0x0049
            android.widget.ListView r5 = (android.widget.ListView) r5
            int r5 = m1260b((android.widget.ListView) r5)
        L_0x0047:
            r3 = 0
            goto L_0x006a
        L_0x0049:
            boolean r3 = r5 instanceof android.widget.GridView
            if (r3 == 0) goto L_0x0054
            android.widget.GridView r5 = (android.widget.GridView) r5
            int r5 = m1259b((android.widget.GridView) r5)
            goto L_0x0047
        L_0x0054:
            boolean r3 = m1298p(r5)
            if (r3 == 0) goto L_0x0068
            androidx.recyclerview.widget.RecyclerView r5 = (androidx.recyclerview.widget.RecyclerView) r5     // Catch:{ Exception -> 0x0065 }
            int r3 = r5.computeHorizontalScrollOffset()     // Catch:{ Exception -> 0x0065 }
            int r5 = r5.computeVerticalScrollOffset()     // Catch:{ Exception -> 0x0066 }
            goto L_0x006a
        L_0x0065:
            r3 = 0
        L_0x0066:
            r5 = 0
            goto L_0x006a
        L_0x0068:
            r5 = 0
            goto L_0x0047
        L_0x006a:
            int r1 = r1 + r3
            int r5 = r5 + r2
            if (r1 <= 0) goto L_0x006f
            goto L_0x0070
        L_0x006f:
            r1 = 0
        L_0x0070:
            if (r5 <= 0) goto L_0x0073
            goto L_0x0074
        L_0x0073:
            r5 = 0
        L_0x0074:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
            r4.add(r0)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            r4.add(r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0968bi.m1267b(android.app.Activity, android.view.View):java.util.ArrayList");
    }

    /* renamed from: b */
    public static int m1260b(ListView listView) {
        if (listView == null || listView.getChildCount() <= 0) {
            return 0;
        }
        View childAt = listView.getChildAt(0);
        return (-childAt.getTop()) + (listView.getFirstVisiblePosition() * childAt.getHeight());
    }

    @TargetApi(11)
    /* renamed from: b */
    public static int m1259b(GridView gridView) {
        int numColumns;
        if (gridView == null || gridView.getChildCount() <= 0) {
            return 0;
        }
        View childAt = gridView.getChildAt(0);
        int i = 1;
        if (Build.VERSION.SDK_INT >= 11 && (numColumns = gridView.getNumColumns()) != 0) {
            i = gridView.getFirstVisiblePosition() / numColumns;
        }
        return (-childAt.getTop()) + (i * childAt.getHeight());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r1 = r1.getTag(-97001);
     */
    /* renamed from: c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String m1272c(android.view.View r1, java.lang.String r2) {
        /*
            if (r1 == 0) goto L_0x0012
            r0 = -97001(0xfffffffffffe8517, float:NaN)
            java.lang.Object r1 = r1.getTag(r0)
            if (r1 == 0) goto L_0x0012
            boolean r0 = r1 instanceof java.lang.String
            if (r0 == 0) goto L_0x0012
            java.lang.String r1 = (java.lang.String) r1
            goto L_0x0014
        L_0x0012:
            java.lang.String r1 = ""
        L_0x0014:
            if (r2 == 0) goto L_0x001d
            boolean r0 = android.text.TextUtils.isEmpty(r1)
            if (r0 == 0) goto L_0x001d
            r1 = r2
        L_0x001d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0968bi.m1272c(android.view.View, java.lang.String):java.lang.String");
    }

    /* renamed from: q */
    public static boolean m1299q(View view) {
        Object tag;
        if (view == null || (tag = view.getTag(-97001)) == null || !(tag instanceof String)) {
            return false;
        }
        return true;
    }

    /* renamed from: r */
    public static Map<String, String> m1300r(View view) {
        return m1268b(view, true);
    }

    /* renamed from: b */
    public static Map<String, String> m1268b(View view, boolean z) {
        HashMap hashMap = new HashMap();
        if (view == null) {
            return hashMap;
        }
        Object tag = view.getTag(-97003);
        String str = (tag == null || !(tag instanceof String)) ? "" : (String) tag;
        Map<String, String> c = m1275c(view, z);
        if (!TextUtils.isEmpty(str)) {
            hashMap.put("title", str);
        } else {
            hashMap.put("title", c.get("title"));
        }
        hashMap.put("content", c.get("content"));
        return hashMap;
    }

    /* renamed from: s */
    public static String m1301s(View view) {
        Object tag;
        if (view == null || (tag = view.getTag(-97004)) == null || !(tag instanceof String)) {
            return "";
        }
        return (String) tag;
    }

    /* renamed from: c */
    public static Map<String, String> m1275c(View view, boolean z) {
        String str;
        View view2;
        HashMap hashMap = new HashMap();
        if (view == null) {
            return hashMap;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        m1254a(view, (LinkedHashMap<View, Integer>) linkedHashMap);
        StringBuilder sb = new StringBuilder();
        if (linkedHashMap.size() == 0) {
            return hashMap;
        }
        ArrayList<Map.Entry> arrayList = new ArrayList<>(linkedHashMap.entrySet());
        Iterator it = arrayList.iterator();
        int i = 0;
        boolean z2 = false;
        while (true) {
            str = "";
            if (!it.hasNext()) {
                break;
            }
            Map.Entry entry = (Map.Entry) it.next();
            int intValue = ((Integer) entry.getValue()).intValue();
            if (intValue > i) {
                i = intValue;
            }
            View view3 = (View) entry.getKey();
            if (view3 != null && (view3 instanceof TextView)) {
                CharSequence text = ((TextView) view3).getText();
                if (text != null) {
                    str = text.toString();
                }
                if (!TextUtils.isEmpty(str)) {
                    if (!TextUtils.isEmpty(sb.toString())) {
                        sb.append(Config.replace);
                    }
                    sb.append(str);
                    if (!z || str.contains("广告")) {
                        z2 = true;
                    }
                }
            }
        }
        String sb2 = sb.toString();
        if (!TextUtils.isEmpty(sb2) && z2) {
            if (sb2.length() > 256) {
                sb2 = sb2.substring(0, 256);
            }
            hashMap.put("content", sb2);
        }
        StringBuilder sb3 = new StringBuilder();
        for (Map.Entry entry2 : arrayList) {
            if (((Integer) entry2.getValue()).intValue() >= i && (view2 = (View) entry2.getKey()) != null && (view2 instanceof TextView)) {
                CharSequence text2 = ((TextView) view2).getText();
                String charSequence = text2 != null ? text2.toString() : str;
                if (!TextUtils.isEmpty(charSequence)) {
                    if (!TextUtils.isEmpty(sb3.toString())) {
                        sb3.append(Config.replace);
                    }
                    sb3.append(charSequence);
                }
            }
        }
        String sb4 = sb3.toString();
        if (!TextUtils.isEmpty(sb4)) {
            if (sb4.length() > 256) {
                sb4 = sb4.substring(0, 256);
            }
            hashMap.put("title", sb4);
        }
        return hashMap;
    }

    /* renamed from: a */
    private static void m1254a(View view, LinkedHashMap<View, Integer> linkedHashMap) {
        if (view != null) {
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                if (textView.getVisibility() == 0) {
                    linkedHashMap.put(view, Integer.valueOf((int) (textView.getTextSize() * 10.0f)));
                }
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    m1254a(viewGroup.getChildAt(i), linkedHashMap);
                }
            }
        }
    }

    /* renamed from: u */
    private static Rect m1303u(View view) {
        if (view == null || view.getVisibility() != 0) {
            return null;
        }
        Rect rect = new Rect();
        m1257a(view, rect);
        return rect;
    }

    /* renamed from: a */
    public static boolean m1256a(View view, float f) {
        Rect u;
        if (view == null) {
            return false;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        if (width * height > 0 && (u = m1303u(view)) != null && ((float) (u.width() * u.height())) >= f * ((float) width) * ((float) height)) {
            return true;
        }
        return false;
    }

    /* renamed from: t */
    public static boolean m1302t(View view) {
        Object tag;
        if (view == null || (tag = view.getTag(-97002)) == null || !(tag instanceof Boolean)) {
            return false;
        }
        return true;
    }

    /* renamed from: v */
    private static boolean m1304v(View view) {
        if (view != null && "com.android.internal.policy".equals(m1249a(view.getClass())) && "DecorView".equals(m1293k(view))) {
            return true;
        }
        return false;
    }

    /* renamed from: c */
    public static boolean m1276c(Activity activity, View view) {
        View a;
        if (activity == null || view == null || (a = m1241a(activity)) == null || !m1304v(view) || a == view) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static String m1243a(Context context) {
        ActivityInfo activityInfo;
        if (context == null) {
            return "";
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return "";
        }
        ResolveInfo resolveInfo = null;
        try {
            resolveInfo = packageManager.resolveActivity(intent, 0);
        } catch (Exception unused) {
        }
        if (resolveInfo == null || (activityInfo = resolveInfo.activityInfo) == null) {
            return "";
        }
        String str = activityInfo.packageName;
        if (Constants.PLATFORM.equals(str)) {
            return "";
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        return "";
    }

    /* renamed from: a */
    public static boolean m1255a(Context context, String str) {
        PackageManager packageManager;
        if (context == null || TextUtils.isEmpty(str) || (packageManager = context.getPackageManager()) == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        List<ResolveInfo> list = null;
        try {
            list = packageManager.queryIntentActivities(intent, 65536);
        } catch (Exception unused) {
        }
        if (list == null) {
            return false;
        }
        for (ResolveInfo resolveInfo : list) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null && str.equals(activityInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: g */
    public static String m1286g(Activity activity) {
        if (activity == null) {
            return "";
        }
        String h = m1288h(activity);
        if (!TextUtils.isEmpty(h)) {
            return h;
        }
        Uri i = m1291i(activity);
        if (i != null) {
            String host = i.getHost();
            if (!TextUtils.isEmpty(host)) {
                return host;
            }
        }
        return "";
    }

    /* renamed from: h */
    private static String m1288h(Activity activity) {
        return activity.getCallingPackage();
    }

    @TargetApi(22)
    /* renamed from: i */
    private static Uri m1291i(Activity activity) {
        Uri uri;
        Intent intent = activity.getIntent();
        if (intent == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 17 && (uri = (Uri) intent.getParcelableExtra("android.intent.extra.REFERRER")) != null) {
            return uri;
        }
        if (Build.VERSION.SDK_INT >= 22) {
            String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
            if (!TextUtils.isEmpty(stringExtra)) {
                return Uri.parse(stringExtra);
            }
        }
        if (Build.VERSION.SDK_INT >= 22) {
            return activity.getReferrer();
        }
        return null;
    }
}
