package com.baidu.mobstat;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.ScrollView;
import com.baidu.mobstat.MtjConfig;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;

/* renamed from: com.baidu.mobstat.as */
public class C0915as {

    /* renamed from: u */
    private static final C0915as f1057u = new C0915as();
    /* access modifiers changed from: private */

    /* renamed from: A */
    public Object f1058A = new Object();

    /* renamed from: a */
    private Context f1059a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public WeakReference<Activity> f1060b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public Handler f1061c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public boolean f1062d = true;
    /* access modifiers changed from: private */

    /* renamed from: e */
    public List<WeakReference<View>> f1063e = Collections.synchronizedList(new ArrayList());

    /* renamed from: f */
    private volatile MtjConfig.FeedTrackStrategy f1064f = MtjConfig.FeedTrackStrategy.TRACK_ALL;

    /* renamed from: g */
    private long f1065g;

    /* renamed from: h */
    private long f1066h;

    /* renamed from: i */
    private long f1067i;

    /* renamed from: j */
    private String f1068j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public String f1069k;

    /* renamed from: l */
    private String f1070l;

    /* renamed from: m */
    private String f1071m;

    /* renamed from: n */
    private boolean f1072n;

    /* renamed from: o */
    private String f1073o;

    /* renamed from: p */
    private boolean f1074p;

    /* renamed from: q */
    private boolean f1075q;

    /* renamed from: r */
    private List<WeakReference<View>> f1076r = new ArrayList();
    /* access modifiers changed from: private */

    /* renamed from: s */
    public HashMap<WeakReference<View>, ArrayList<C0911ao>> f1077s = new HashMap<>();

    /* renamed from: t */
    private HashMap<WeakReference<View>, HashMap<String, ArrayList<C0912ap>>> f1078t = new HashMap<>();

    /* renamed from: v */
    private C0932a f1079v;

    /* renamed from: w */
    private ViewTreeObserver.OnScrollChangedListener f1080w;

    /* renamed from: x */
    private Runnable f1081x = null;
    /* access modifiers changed from: private */

    /* renamed from: y */
    public float f1082y = 0.0f;
    /* access modifiers changed from: private */

    /* renamed from: z */
    public float f1083z = 0.0f;

    /* renamed from: com.baidu.mobstat.as$a */
    public interface C0932a {
        /* renamed from: a */
        void mo11579a(C0910an anVar);

        /* renamed from: a */
        void mo11580a(ArrayList<C0911ao> arrayList);

        /* renamed from: b */
        void mo11581b(ArrayList<C0912ap> arrayList);
    }

    /* renamed from: a */
    private boolean m1025a(long j, long j2) {
        long j3 = j2 - j;
        return j3 > 0 && j3 > 50;
    }

    /* renamed from: a */
    public static C0915as m997a() {
        return f1057u;
    }

    private C0915as() {
        HandlerThread handlerThread = new HandlerThread("feedViewCrawlerThread");
        handlerThread.start();
        this.f1061c = new Handler(handlerThread.getLooper());
    }

    /* renamed from: a */
    public void mo11553a(MtjConfig.FeedTrackStrategy feedTrackStrategy) {
        this.f1064f = feedTrackStrategy;
    }

    /* renamed from: b */
    public boolean mo11557b() {
        return this.f1064f == MtjConfig.FeedTrackStrategy.TRACK_NONE;
    }

    /* renamed from: a */
    private boolean m1026a(View view) {
        if (this.f1064f == MtjConfig.FeedTrackStrategy.TRACK_ALL) {
            return false;
        }
        if (this.f1064f != MtjConfig.FeedTrackStrategy.TRACK_SINGLE || !C0968bi.m1302t(view)) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public void mo11550a(Activity activity) {
        if (activity != null) {
            final WeakReference weakReference = new WeakReference(activity);
            final long currentTimeMillis = System.currentTimeMillis();
            this.f1061c.post(new Runnable() {
                public void run() {
                    boolean unused = C0915as.this.f1062d = true;
                    Activity activity = (Activity) weakReference.get();
                    if (activity != null) {
                        C0915as.this.m1002a(activity, currentTimeMillis);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1002a(Activity activity, long j) {
        this.f1059a = activity.getApplicationContext();
        this.f1060b = new WeakReference<>(activity);
        this.f1065g = j;
        String e = C0968bi.m1283e(activity);
        if (C0968bi.m1258a(e, this.f1068j)) {
            this.f1074p = false;
            if (m1030a(e, this.f1069k, this.f1068j, this.f1067i, this.f1065g, activity)) {
                this.f1074p = true;
            }
        }
    }

    /* renamed from: b */
    public void mo11556b(Activity activity) {
        if (activity != null) {
            final WeakReference weakReference = new WeakReference(activity);
            final long currentTimeMillis = System.currentTimeMillis();
            this.f1061c.post(new Runnable() {
                public void run() {
                    boolean unused = C0915as.this.f1062d = false;
                    Activity activity = (Activity) weakReference.get();
                    if (activity != null) {
                        C0915as.this.m1036b(activity, currentTimeMillis);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m1036b(Activity activity, long j) {
        this.f1066h = j;
        String e = C0968bi.m1283e(activity);
        this.f1068j = e;
        if (!TextUtils.isEmpty(this.f1069k) && !this.f1069k.equals(e)) {
            this.f1067i = 0;
        }
        if (this.f1074p) {
            m1003a(activity, this.f1065g, j, this.f1076r);
            this.f1076r.clear();
            this.f1075q = false;
        }
        m1022a(this.f1077s, this.f1078t, j);
        m1040b(this.f1077s);
        m1046c(this.f1077s);
        m1053e(this.f1078t);
        m1054f(this.f1078t);
        if (activity != null) {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    C0915as asVar = C0915as.this;
                    asVar.m1024a((List<WeakReference<View>>) asVar.f1063e, C0915as.this.m1047d());
                }
            });
        }
    }

    /* renamed from: c */
    public void mo11558c(Activity activity) {
        if (activity != null) {
            final WeakReference weakReference = new WeakReference(activity);
            final long currentTimeMillis = System.currentTimeMillis();
            this.f1061c.post(new Runnable() {
                public void run() {
                    Activity activity = (Activity) weakReference.get();
                    if (activity != null) {
                        C0915as.this.m1044c(activity, currentTimeMillis);
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public void mo11552a(View view, Activity activity) {
        if (view != null && activity != null) {
            final WeakReference weakReference = new WeakReference(activity);
            final WeakReference weakReference2 = new WeakReference(view);
            final long currentTimeMillis = System.currentTimeMillis();
            final View view2 = view;
            this.f1061c.post(new Runnable() {
                public void run() {
                    Activity activity = (Activity) weakReference.get();
                    if (activity != null && ((View) weakReference2.get()) != null) {
                        C0915as.this.m1007a(view2, activity, currentTimeMillis);
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public void mo11551a(KeyEvent keyEvent) {
        if (keyEvent != null && keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1) {
            this.f1061c.post(new Runnable() {
                public void run() {
                    Activity activity;
                    if (C0915as.this.f1060b != null && (activity = (Activity) C0915as.this.f1060b.get()) != null) {
                        String e = C0968bi.m1283e(activity);
                        if (!TextUtils.isEmpty(C0915as.this.f1069k) && C0915as.this.f1069k.equals(e)) {
                            C0915as.this.m1043c();
                        }
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1007a(View view, Activity activity, long j) {
        View a = C0968bi.m1242a(view, activity);
        View m = C0968bi.m1295m(a);
        if (m == null) {
            if (!this.f1074p) {
                m1043c();
            }
        } else if (!m1026a(m)) {
            this.f1067i = j;
            this.f1069k = C0968bi.m1283e(activity);
            this.f1070l = "";
            Map<String, String> r = C0968bi.m1300r(a);
            if (r != null && r.size() > 0 && !TextUtils.isEmpty(r.get("title"))) {
                this.f1070l = r.get("title");
            }
            this.f1071m = C0968bi.m1272c(m, this.f1069k);
            this.f1072n = C0968bi.m1299q(m);
            this.f1073o = mo11548a(activity, a, m);
        } else if (!this.f1074p) {
            m1043c();
        }
    }

    /* renamed from: a */
    public String mo11548a(Activity activity, View view, View view2) {
        Map<String, String> r = C0968bi.m1300r(view);
        String str = (r == null || r.size() <= 0 || TextUtils.isEmpty(r.get("title"))) ? "" : r.get("title");
        return C0912ap.m964a(C0968bi.m1301s(view), C0968bi.m1283e(activity), C0968bi.m1253a(activity, view), str, C0968bi.m1247a(view, C0968bi.m1263b(view2)), C0968bi.m1272c(view2, C0968bi.m1283e(activity)), C0968bi.m1299q(view2));
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m1043c() {
        this.f1067i = 0;
        this.f1069k = "";
        this.f1070l = "";
        this.f1071m = "";
        this.f1072n = false;
        this.f1073o = "";
    }

    /* renamed from: a */
    public void mo11554a(final String str) {
        this.f1061c.post(new Runnable() {
            public void run() {
                C0914ar.m990a().mo11544a(str);
            }
        });
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public ViewTreeObserver.OnScrollChangedListener m1047d() {
        if (this.f1080w == null) {
            this.f1080w = new ViewTreeObserver.OnScrollChangedListener() {
                public void onScrollChanged() {
                    C0915as asVar = C0915as.this;
                    asVar.mo11555a((WeakReference<Activity>) asVar.f1060b);
                }
            };
        }
        return this.f1080w;
    }

    /* renamed from: a */
    public void mo11555a(final WeakReference<Activity> weakReference) {
        if (weakReference != null) {
            final long currentTimeMillis = System.currentTimeMillis();
            C092215 r2 = new Runnable() {
                public void run() {
                    Activity activity = (Activity) weakReference.get();
                    if (activity != null) {
                        C0915as.this.m1049d(activity, currentTimeMillis);
                    }
                }
            };
            Runnable runnable = this.f1081x;
            if (runnable != null) {
                this.f1061c.removeCallbacks(runnable);
            }
            this.f1081x = r2;
            this.f1061c.postDelayed(r2, 350);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1024a(List<WeakReference<View>> list, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        if (list != null && list.size() > 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                WeakReference weakReference = null;
                try {
                    weakReference = list.get(i);
                } catch (Exception unused) {
                }
                if (weakReference != null) {
                    m1009a((View) weakReference.get(), onScrollChangedListener);
                }
            }
            list.clear();
        }
    }

    /* renamed from: a */
    private void m1009a(View view, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener) {
        ViewTreeObserver viewTreeObserver;
        if (view != null && (viewTreeObserver = view.getViewTreeObserver()) != null && viewTreeObserver.isAlive() && onScrollChangedListener != null) {
            viewTreeObserver.removeOnScrollChangedListener(onScrollChangedListener);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1023a(List<WeakReference<View>> list) {
        if (list != null && list.size() != 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                WeakReference weakReference = null;
                try {
                    weakReference = list.get(i);
                } catch (Exception unused) {
                }
                if (weakReference == null) {
                    arrayList.add(weakReference);
                } else {
                    View view = (View) weakReference.get();
                    if (view == null) {
                        arrayList.add(weakReference);
                    } else {
                        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                        if (viewTreeObserver == null || !viewTreeObserver.isAlive()) {
                            arrayList.add(weakReference);
                        }
                    }
                }
            }
            list.removeAll(arrayList);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1010a(View view, ViewTreeObserver.OnScrollChangedListener onScrollChangedListener, List<WeakReference<View>> list) {
        ViewTreeObserver viewTreeObserver;
        if (view != null && !m1031a(list, view) && !m1026a(view) && (viewTreeObserver = view.getViewTreeObserver()) != null && viewTreeObserver.isAlive() && onScrollChangedListener != null && list != null) {
            try {
                viewTreeObserver.addOnScrollChangedListener(onScrollChangedListener);
                list.add(new WeakReference(view));
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    private boolean m1031a(List<WeakReference<View>> list, View view) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            WeakReference weakReference = null;
            try {
                weakReference = list.get(i);
            } catch (Exception unused) {
            }
            if (weakReference != null && view == weakReference.get()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private boolean m1030a(String str, String str2, String str3, long j, long j2, Activity activity) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || str.equals(str2) || !str2.equals(str3) || (activity instanceof IIgnoreAutoTrace)) {
            return false;
        }
        long j3 = j2 - j;
        if (j3 <= 0 || j3 >= Config.BPLUS_DELAY_TIME) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m1044c(Activity activity, long j) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        View a = C0968bi.m1241a(activity);
        m1004a(activity, a, hashMap, arrayList, arrayList2);
        if (this.f1074p && !this.f1075q && m1025a(this.f1065g, j)) {
            ArrayList<WeakReference<View>> a2 = m999a((HashMap<View, Integer>) hashMap);
            a2.add(new WeakReference(a));
            this.f1076r = a2;
            this.f1075q = true;
        }
        if (activity != null) {
            final ArrayList arrayList3 = arrayList2;
            final long j2 = j;
            final ArrayList arrayList4 = arrayList;
            final Activity activity2 = activity;
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    Iterator it = arrayList3.iterator();
                    while (it.hasNext()) {
                        C0915as asVar = C0915as.this;
                        asVar.m1010a((View) it.next(), asVar.m1047d(), (List<WeakReference<View>>) C0915as.this.f1063e);
                    }
                    C0915as.this.f1061c.post(new Runnable() {
                        public void run() {
                            C0915as.this.m1023a((List<WeakReference<View>>) C0915as.this.f1063e);
                            if (C0915as.this.f1062d) {
                                C0915as.this.m1018a((WeakReference<Activity>) C0915as.this.f1060b, j2);
                                boolean unused = C0915as.this.f1062d = false;
                            }
                            C0915as.this.m1019a((HashMap<WeakReference<View>, ArrayList<C0911ao>>) C0915as.this.f1077s, j2);
                            Iterator it = arrayList4.iterator();
                            while (it.hasNext()) {
                                C0915as.this.m1037b((View) it.next(), activity2, j2);
                            }
                        }
                    });
                }
            });
        }
    }

    /* renamed from: a */
    private void m1004a(Activity activity, View view, HashMap<View, Integer> hashMap, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        int width;
        if (view != null && !C0968bi.m1276c(activity, view)) {
            boolean l = C0968bi.m1294l(view);
            if (l && !m1026a(view) && C0968bi.m1281d(view)) {
                arrayList.add(view);
            }
            if (l) {
                arrayList2.add(view);
            }
            if ((l || (view instanceof WebView) || (view instanceof ScrollView)) && (width = view.getWidth() * view.getHeight()) != 0) {
                hashMap.put(view, Integer.valueOf(width));
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    m1004a(activity, viewGroup.getChildAt(i), hashMap, arrayList, arrayList2);
                }
            }
        }
    }

    /* renamed from: a */
    private ArrayList<WeakReference<View>> m999a(HashMap<View, Integer> hashMap) {
        ArrayList<Map.Entry> arrayList = new ArrayList<>(hashMap.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<View, Integer>>() {
            /* renamed from: a */
            public int compare(Map.Entry<View, Integer> entry, Map.Entry<View, Integer> entry2) {
                return entry2.getValue().compareTo(entry.getValue());
            }
        });
        ArrayList<WeakReference<View>> arrayList2 = new ArrayList<>(arrayList.size());
        for (Map.Entry key : arrayList) {
            arrayList2.add(new WeakReference(key.getKey()));
        }
        return arrayList2;
    }

    /* renamed from: a */
    private void m1003a(Activity activity, long j, long j2, List<WeakReference<View>> list) {
        View view;
        Activity activity2 = activity;
        List<WeakReference<View>> list2 = list;
        if (list2 != null) {
            if (list.size() == 0) {
                list2.add(new WeakReference(C0968bi.m1241a(activity)));
            }
            View view2 = null;
            Iterator<WeakReference<View>> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                WeakReference next = it.next();
                if (next != null && (view = (View) next.get()) != null) {
                    view2 = view;
                    break;
                }
            }
            if (!TextUtils.isEmpty(this.f1070l)) {
                String str = this.f1071m;
                if (!TextUtils.isEmpty(str)) {
                    String e = C0968bi.m1283e(activity);
                    String f = C0968bi.m1284f(activity);
                    ArrayList<Integer> a = mo11549a(activity2, view2);
                    int a2 = C0884ag.m838a(this.f1059a, (float) a.get(0).intValue());
                    int a3 = C0884ag.m838a(this.f1059a, (float) a.get(1).intValue());
                    ArrayList<Integer> b = C0968bi.m1267b(activity2, view2);
                    int a4 = C0884ag.m838a(this.f1059a, (float) b.get(0).intValue());
                    int a5 = C0884ag.m838a(this.f1059a, (float) b.get(1).intValue());
                    if (a4 > a2) {
                        a2 = a4;
                    }
                    if (a5 > a3) {
                        a3 = a5;
                    }
                    if (a2 != 0 && a3 != 0) {
                        String str2 = this.f1070l;
                        float f2 = (float) a2;
                        String str3 = str2;
                        long j3 = j;
                        C0910an anVar = r4;
                        C0910an anVar2 = new C0910an(e, f, str3, j2 - j, j3, (float) a4, (float) a5, f2, (float) a3, str, this.f1072n, this.f1073o);
                        m1006a(this.f1059a, anVar);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m1006a(Context context, C0910an anVar) {
        if (anVar != null) {
            C0932a aVar = this.f1079v;
            if (aVar != null) {
                aVar.mo11579a(anVar);
            }
            C0939av.m1093a().mo11589a(context, anVar);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m1037b(View view, Activity activity, long j) {
        if (view != null && C0968bi.m1281d(view)) {
            m1020a(this.f1077s, view, new C0911ao(C0968bi.m1272c(view, C0968bi.m1283e(activity)), j, j, j, C0968bi.m1299q(view)));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1019a(HashMap<WeakReference<View>, ArrayList<C0911ao>> hashMap, long j) {
        if (hashMap != null && hashMap.size() != 0) {
            for (Map.Entry<WeakReference<View>, ArrayList<C0911ao>> value : hashMap.entrySet()) {
                ArrayList arrayList = (ArrayList) value.getValue();
                if (!(arrayList == null || arrayList.size() == 0)) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        C0911ao aoVar = (C0911ao) it.next();
                        if (aoVar.mo11525e() == aoVar.mo11523c()) {
                            aoVar.mo11520a(j);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private LinkedHashMap<WeakReference<View>, ArrayList<C0911ao>> m1000a(HashMap<WeakReference<View>, ArrayList<C0911ao>> hashMap, View view) {
        View view2;
        for (Map.Entry next : hashMap.entrySet()) {
            WeakReference weakReference = (WeakReference) next.getKey();
            if (weakReference != null && (view2 = (View) weakReference.get()) != null && view2 == view) {
                LinkedHashMap<WeakReference<View>, ArrayList<C0911ao>> linkedHashMap = new LinkedHashMap<>(1);
                linkedHashMap.put(weakReference, (ArrayList) next.getValue());
                return linkedHashMap;
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0099  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1020a(java.util.HashMap<java.lang.ref.WeakReference<android.view.View>, java.util.ArrayList<com.baidu.mobstat.C0911ao>> r17, android.view.View r18, com.baidu.mobstat.C0911ao r19) {
        /*
            r16 = this;
            java.lang.String r0 = r19.mo11518a()
            long r1 = r19.mo11523c()
            long r3 = r19.mo11525e()
            boolean r5 = r19.mo11524d()
            boolean r6 = android.text.TextUtils.isEmpty(r0)
            if (r6 == 0) goto L_0x0017
            return
        L_0x0017:
            java.util.LinkedHashMap r6 = r16.m1000a((java.util.HashMap<java.lang.ref.WeakReference<android.view.View>, java.util.ArrayList<com.baidu.mobstat.C0911ao>>) r17, (android.view.View) r18)
            if (r6 == 0) goto L_0x0044
            int r8 = r6.size()
            if (r8 <= 0) goto L_0x0044
            java.util.Set r6 = r6.entrySet()
            java.util.Iterator r6 = r6.iterator()
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x0044
            java.lang.Object r6 = r6.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r8 = r6.getKey()
            java.lang.ref.WeakReference r8 = (java.lang.ref.WeakReference) r8
            java.lang.Object r6 = r6.getValue()
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            goto L_0x0046
        L_0x0044:
            r6 = 0
            r8 = 0
        L_0x0046:
            if (r6 == 0) goto L_0x007b
            java.util.Iterator r9 = r6.iterator()
        L_0x004c:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x007b
            java.lang.Object r10 = r9.next()
            com.baidu.mobstat.ao r10 = (com.baidu.mobstat.C0911ao) r10
            java.lang.String r11 = r10.mo11518a()
            long r12 = r10.mo11523c()
            long r14 = r10.mo11525e()
            boolean r7 = r10.mo11524d()
            boolean r11 = r0.equals(r11)
            if (r11 != 0) goto L_0x006f
            goto L_0x004c
        L_0x006f:
            if (r5 == r7) goto L_0x0072
            goto L_0x004c
        L_0x0072:
            int r7 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r7 >= 0) goto L_0x007c
            int r7 = (r12 > r1 ? 1 : (r12 == r1 ? 0 : -1))
            if (r7 == 0) goto L_0x007c
            goto L_0x004c
        L_0x007b:
            r10 = 0
        L_0x007c:
            if (r10 != 0) goto L_0x0099
            if (r6 != 0) goto L_0x0085
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
        L_0x0085:
            r0 = r19
            r6.add(r0)
            if (r8 != 0) goto L_0x0093
            java.lang.ref.WeakReference r8 = new java.lang.ref.WeakReference
            r0 = r18
            r8.<init>(r0)
        L_0x0093:
            r0 = r17
            r0.put(r8, r6)
            goto L_0x009f
        L_0x0099:
            r10.mo11520a((long) r1)
            r10.mo11522b(r3)
        L_0x009f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0915as.m1020a(java.util.HashMap, android.view.View, com.baidu.mobstat.ao):void");
    }

    /* renamed from: b */
    private void m1040b(HashMap<WeakReference<View>, ArrayList<C0911ao>> hashMap) {
        m1051d(hashMap);
    }

    /* renamed from: c */
    private void m1046c(HashMap<WeakReference<View>, ArrayList<C0911ao>> hashMap) {
        for (Map.Entry<WeakReference<View>, ArrayList<C0911ao>> value : hashMap.entrySet()) {
            ArrayList arrayList = (ArrayList) value.getValue();
            if (arrayList != null) {
                arrayList.clear();
            }
        }
        hashMap.clear();
    }

    /* renamed from: d */
    private void m1051d(HashMap<WeakReference<View>, ArrayList<C0911ao>> hashMap) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<WeakReference<View>, ArrayList<C0911ao>> value : this.f1077s.entrySet()) {
            ArrayList arrayList2 = (ArrayList) value.getValue();
            if (!(arrayList2 == null || arrayList2.size() == 0)) {
                arrayList.addAll(arrayList2);
            }
        }
        Collections.sort(arrayList, new Comparator<C0911ao>() {
            /* renamed from: a */
            public int compare(C0911ao aoVar, C0911ao aoVar2) {
                long b = aoVar.mo11521b() - aoVar2.mo11521b();
                if (b > 0) {
                    return 1;
                }
                return b < 0 ? -1 : 0;
            }
        });
        C0932a aVar = this.f1079v;
        if (aVar != null) {
            aVar.mo11580a((ArrayList<C0911ao>) arrayList);
        }
        C0939av.m1093a().mo11593a(this.f1059a, (ArrayList<C0911ao>) arrayList);
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public void m1049d(Activity activity, long j) {
        View view;
        m1041b(this.f1078t, j);
        List<WeakReference<View>> list = this.f1063e;
        if (list != null && list.size() > 0) {
            for (int i = 0; i < this.f1063e.size(); i++) {
                WeakReference weakReference = null;
                try {
                    weakReference = this.f1063e.get(i);
                } catch (Exception unused) {
                }
                if (weakReference != null && (view = (View) weakReference.get()) != null && C0968bi.m1281d(view) && !m1026a(view) && (view instanceof ViewGroup)) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                        View childAt = viewGroup.getChildAt(i2);
                        if (childAt != null && C0968bi.m1281d(childAt)) {
                            m1008a(view, childAt, activity, j);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: b */
    private void m1041b(HashMap<WeakReference<View>, HashMap<String, ArrayList<C0912ap>>> hashMap, long j) {
        if (hashMap != null && hashMap.size() != 0) {
            for (Map.Entry<WeakReference<View>, HashMap<String, ArrayList<C0912ap>>> value : hashMap.entrySet()) {
                HashMap hashMap2 = (HashMap) value.getValue();
                if (hashMap2 != null) {
                    for (Map.Entry value2 : hashMap2.entrySet()) {
                        ArrayList arrayList = (ArrayList) value2.getValue();
                        if (!(arrayList == null || arrayList.size() == 0)) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                C0912ap apVar = (C0912ap) it.next();
                                if (apVar.mo11542k() == apVar.mo11541j()) {
                                    apVar.mo11529a(j);
                                    apVar.mo11530a(String.valueOf(apVar.mo11541j() - apVar.mo11540i()));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m1008a(View view, View view2, Activity activity, long j) {
        String str;
        View view3 = view;
        View view4 = view2;
        if (view3 != null && view4 != null && C0968bi.m1256a(view4, C0914ar.m990a().mo11546c())) {
            Map<String, String> r = C0968bi.m1300r(view2);
            String str2 = "";
            if (r == null || r.size() <= 0) {
                str = str2;
            } else {
                String str3 = !TextUtils.isEmpty(r.get("title")) ? r.get("title") : str2;
                if (!TextUtils.isEmpty(r.get("content"))) {
                    str2 = r.get("content");
                }
                str = str3;
            }
            String s = C0968bi.m1301s(view2);
            String e = C0968bi.m1283e(activity);
            boolean q = C0968bi.m1299q(view);
            String c = C0968bi.m1272c(view3, C0968bi.m1283e(activity));
            JSONArray a = C0968bi.m1253a(activity, view4);
            String a2 = C0968bi.m1247a(view4, C0968bi.m1263b(view));
            String valueOf = String.valueOf(j - j);
            JSONArray jSONArray = r2;
            JSONArray jSONArray2 = new JSONArray();
            C0912ap apVar = new C0912ap(s, e, a, str, str2, a2, c, q, 1, j, j, j, valueOf, "", jSONArray);
            C0939av.m1093a().mo11601b(str2);
            m1021a(this.f1078t, view3, apVar);
        }
    }

    /* renamed from: b */
    private LinkedHashMap<WeakReference<View>, HashMap<String, ArrayList<C0912ap>>> m1035b(HashMap<WeakReference<View>, HashMap<String, ArrayList<C0912ap>>> hashMap, View view) {
        View view2;
        for (Map.Entry next : hashMap.entrySet()) {
            WeakReference weakReference = (WeakReference) next.getKey();
            if (weakReference != null && (view2 = (View) weakReference.get()) != null && view2 == view) {
                LinkedHashMap<WeakReference<View>, HashMap<String, ArrayList<C0912ap>>> linkedHashMap = new LinkedHashMap<>();
                linkedHashMap.put(weakReference, (HashMap) next.getValue());
                return linkedHashMap;
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ac  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00ee  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m1021a(java.util.HashMap<java.lang.ref.WeakReference<android.view.View>, java.util.HashMap<java.lang.String, java.util.ArrayList<com.baidu.mobstat.C0912ap>>> r19, android.view.View r20, com.baidu.mobstat.C0912ap r21) {
        /*
            r18 = this;
            r0 = r18
            r1 = r20
            r2 = r21
            if (r1 != 0) goto L_0x0009
            return
        L_0x0009:
            long r3 = r21.mo11542k()
            long r5 = r21.mo11541j()
            java.lang.String r7 = r21.mo11535d()
            java.lang.String r8 = r21.mo11537f()
            boolean r7 = android.text.TextUtils.isEmpty(r7)
            if (r7 != 0) goto L_0x0104
            boolean r7 = android.text.TextUtils.isEmpty(r8)
            if (r7 == 0) goto L_0x0027
            goto L_0x0104
        L_0x0027:
            java.util.LinkedHashMap r7 = r18.m1035b((java.util.HashMap<java.lang.ref.WeakReference<android.view.View>, java.util.HashMap<java.lang.String, java.util.ArrayList<com.baidu.mobstat.C0912ap>>>) r19, (android.view.View) r20)
            r8 = 0
            if (r7 == 0) goto L_0x0055
            int r9 = r7.size()
            if (r9 <= 0) goto L_0x0055
            java.util.Set r7 = r7.entrySet()
            java.util.Iterator r7 = r7.iterator()
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x0055
            java.lang.Object r7 = r7.next()
            java.util.Map$Entry r7 = (java.util.Map.Entry) r7
            java.lang.Object r9 = r7.getKey()
            java.lang.ref.WeakReference r9 = (java.lang.ref.WeakReference) r9
            java.lang.Object r7 = r7.getValue()
            java.util.HashMap r7 = (java.util.HashMap) r7
            goto L_0x0057
        L_0x0055:
            r7 = r8
            r9 = r7
        L_0x0057:
            if (r7 == 0) goto L_0x00aa
            java.util.Set r10 = r7.entrySet()
            java.util.Iterator r10 = r10.iterator()
        L_0x0061:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x00aa
            java.lang.Object r11 = r10.next()
            java.util.Map$Entry r11 = (java.util.Map.Entry) r11
            java.lang.Object r11 = r11.getValue()
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            if (r11 == 0) goto L_0x0061
            int r12 = r11.size()
            if (r12 != 0) goto L_0x007c
            goto L_0x0061
        L_0x007c:
            java.util.Iterator r11 = r11.iterator()
        L_0x0080:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x0061
            java.lang.Object r12 = r11.next()
            com.baidu.mobstat.ap r12 = (com.baidu.mobstat.C0912ap) r12
            long r13 = r12.mo11542k()
            long r15 = r12.mo11541j()
            boolean r17 = r0.m1027a((com.baidu.mobstat.C0912ap) r12, (com.baidu.mobstat.C0912ap) r2)
            if (r17 != 0) goto L_0x009b
            goto L_0x0080
        L_0x009b:
            int r17 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r17 >= 0) goto L_0x00a8
            long r13 = r21.mo11541j()
            int r17 = (r15 > r13 ? 1 : (r15 == r13 ? 0 : -1))
            if (r17 == 0) goto L_0x00a8
            goto L_0x0080
        L_0x00a8:
            r8 = r12
            goto L_0x0061
        L_0x00aa:
            if (r8 != 0) goto L_0x00ee
            java.lang.String r3 = r0.m998a((com.baidu.mobstat.C0912ap) r2)
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 == 0) goto L_0x00b7
            return
        L_0x00b7:
            if (r7 != 0) goto L_0x00d6
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            r5.add(r2)
            r4.put(r3, r5)
            if (r9 != 0) goto L_0x00d0
            java.lang.ref.WeakReference r9 = new java.lang.ref.WeakReference
            r9.<init>(r1)
        L_0x00d0:
            r1 = r19
            r1.put(r9, r4)
            goto L_0x0104
        L_0x00d6:
            java.lang.Object r1 = r7.get(r3)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            if (r1 != 0) goto L_0x00ea
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r1.add(r2)
            r7.put(r3, r1)
            goto L_0x0104
        L_0x00ea:
            r1.add(r2)
            goto L_0x0104
        L_0x00ee:
            r8.mo11529a((long) r5)
            r8.mo11532b((long) r3)
            long r1 = r8.mo11541j()
            long r3 = r8.mo11540i()
            long r1 = r1 - r3
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r8.mo11530a((java.lang.String) r1)
        L_0x0104:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0915as.m1021a(java.util.HashMap, android.view.View, com.baidu.mobstat.ap):void");
    }

    /* renamed from: a */
    private boolean m1027a(C0912ap apVar, C0912ap apVar2) {
        String a = apVar2.mo11526a();
        String b = apVar2.mo11531b();
        JSONArray c = apVar2.mo11534c();
        String d = apVar2.mo11535d();
        String e = apVar2.mo11536e();
        String f = apVar2.mo11537f();
        boolean g = apVar2.mo11538g();
        String a2 = apVar.mo11526a();
        String b2 = apVar.mo11531b();
        JSONArray c2 = apVar.mo11534c();
        String d2 = apVar.mo11535d();
        String e2 = apVar.mo11536e();
        String f2 = apVar.mo11537f();
        boolean g2 = apVar.mo11538g();
        if (m1029a(a2, a) && m1029a(b2, b) && m1032a(c2, c) && m1029a(d2, d) && m1029a(e2, e) && m1029a(f2, f) && g2 == g) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private boolean m1029a(String str, String str2) {
        if (str == str2) {
            return true;
        }
        return !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && str.equals(str2);
    }

    /* renamed from: a */
    private boolean m1032a(JSONArray jSONArray, JSONArray jSONArray2) {
        if (jSONArray == null && jSONArray2 == null) {
            return true;
        }
        if (jSONArray == null || jSONArray2 == null) {
            return false;
        }
        return m1029a(jSONArray.toString(), jSONArray2.toString());
    }

    /* renamed from: e */
    private void m1053e(HashMap<WeakReference<View>, HashMap<String, ArrayList<C0912ap>>> hashMap) {
        m1057g(hashMap);
    }

    /* renamed from: f */
    private void m1054f(HashMap<WeakReference<View>, HashMap<String, ArrayList<C0912ap>>> hashMap) {
        for (Map.Entry<WeakReference<View>, HashMap<String, ArrayList<C0912ap>>> value : hashMap.entrySet()) {
            HashMap hashMap2 = (HashMap) value.getValue();
            if (hashMap2 != null) {
                for (Map.Entry value2 : hashMap2.entrySet()) {
                    ((ArrayList) value2.getValue()).clear();
                }
                hashMap2.clear();
            }
        }
        hashMap.clear();
    }

    /* renamed from: a */
    private String m998a(C0912ap apVar) {
        return C0912ap.m964a(apVar.mo11526a(), apVar.mo11531b(), apVar.mo11534c(), apVar.mo11535d(), apVar.mo11536e(), apVar.mo11537f(), apVar.mo11538g());
    }

    /* renamed from: g */
    private void m1057g(HashMap<WeakReference<View>, HashMap<String, ArrayList<C0912ap>>> hashMap) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<WeakReference<View>, HashMap<String, ArrayList<C0912ap>>> value : hashMap.entrySet()) {
            arrayList.addAll(m1059h((HashMap<String, ArrayList<C0912ap>>) (HashMap) value.getValue()));
        }
        Collections.sort(arrayList, new Comparator<C0912ap>() {
            /* renamed from: a */
            public int compare(C0912ap apVar, C0912ap apVar2) {
                long i = apVar.mo11540i() - apVar2.mo11540i();
                if (i > 0) {
                    return 1;
                }
                return i < 0 ? -1 : 0;
            }
        });
        C0932a aVar = this.f1079v;
        if (aVar != null) {
            aVar.mo11581b(arrayList);
        }
        C0939av.m1093a().mo11599b(this.f1059a, (ArrayList<C0912ap>) arrayList);
    }

    /* renamed from: h */
    private ArrayList<C0912ap> m1059h(HashMap<String, ArrayList<C0912ap>> hashMap) {
        ArrayList<C0912ap> arrayList = new ArrayList<>();
        for (Map.Entry<String, ArrayList<C0912ap>> value : hashMap.entrySet()) {
            C0912ap a = m996a((ArrayList<C0912ap>) (ArrayList) value.getValue());
            if (a != null) {
                arrayList.add(a);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private C0912ap m996a(ArrayList<C0912ap> arrayList) {
        ArrayList<C0912ap> arrayList2 = arrayList;
        C0912ap apVar = null;
        if (arrayList2 == null || arrayList.size() == 0) {
            return null;
        }
        Collections.sort(arrayList2, new Comparator<C0912ap>() {
            /* renamed from: a */
            public int compare(C0912ap apVar, C0912ap apVar2) {
                long i = apVar.mo11540i() - apVar2.mo11540i();
                if (i > 0) {
                    return 1;
                }
                return i < 0 ? -1 : 0;
            }
        });
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        int i = 0;
        Iterator<C0912ap> it = arrayList.iterator();
        long j = 0;
        while (it.hasNext()) {
            C0912ap next = it.next();
            long i2 = next.mo11540i();
            String l = next.mo11543l();
            try {
                if (Long.valueOf(l).longValue() < C0914ar.m990a().mo11547d()) {
                }
            } catch (Exception unused) {
            }
            if (j == 0) {
                apVar = next;
                j = i2;
            }
            long j2 = i2 - j;
            if (j2 < 0) {
                j2 = 0;
            }
            if (TextUtils.isEmpty(sb2.toString())) {
                sb2.append(l);
            } else {
                sb2.append("|" + l);
            }
            if (TextUtils.isEmpty(sb.toString())) {
                sb.append("" + j2);
            } else {
                sb.append("|" + j2);
            }
            i += next.mo11539h();
        }
        if (apVar != null) {
            apVar.mo11530a(sb2.toString());
            apVar.mo11533b(sb.toString());
            apVar.mo11528a(i);
        }
        return apVar;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1018a(WeakReference<Activity> weakReference, long j) {
        Activity activity;
        if (weakReference != null && (activity = (Activity) weakReference.get()) != null) {
            m1049d(activity, j);
        }
    }

    /* renamed from: a */
    private void m1022a(HashMap<WeakReference<View>, ArrayList<C0911ao>> hashMap, HashMap<WeakReference<View>, HashMap<String, ArrayList<C0912ap>>> hashMap2, long j) {
        for (Map.Entry<WeakReference<View>, ArrayList<C0911ao>> value : hashMap.entrySet()) {
            ArrayList arrayList = (ArrayList) value.getValue();
            if (!(arrayList == null || arrayList.size() == 0)) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    C0911ao aoVar = (C0911ao) it.next();
                    if (aoVar.mo11525e() == aoVar.mo11523c()) {
                        aoVar.mo11520a(j);
                    }
                }
            }
        }
        for (Map.Entry<WeakReference<View>, HashMap<String, ArrayList<C0912ap>>> value2 : hashMap2.entrySet()) {
            HashMap hashMap3 = (HashMap) value2.getValue();
            if (!(hashMap3 == null || hashMap3.size() == 0)) {
                for (Map.Entry value3 : hashMap3.entrySet()) {
                    ArrayList arrayList2 = (ArrayList) value3.getValue();
                    if (!(arrayList2 == null || arrayList2.size() == 0)) {
                        Iterator it2 = arrayList2.iterator();
                        while (it2.hasNext()) {
                            C0912ap apVar = (C0912ap) it2.next();
                            if (apVar.mo11542k() == apVar.mo11541j()) {
                                apVar.mo11529a(j);
                                apVar.mo11530a(String.valueOf(apVar.mo11541j() - apVar.mo11540i()));
                            }
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    private void m1005a(Activity activity, final WebView webView) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                synchronized (C0915as.this.f1058A) {
                    float unused = C0915as.this.f1082y = (float) webView.getContentHeight();
                    float unused2 = C0915as.this.f1083z = webView.getScale();
                    C0915as.this.f1058A.notifyAll();
                }
            }
        });
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:7|8|9|10|11|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x002b */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x008c  */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<java.lang.Integer> mo11549a(android.app.Activity r8, android.view.View r9) {
        /*
            r7 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.lang.Integer r2 = java.lang.Integer.valueOf(r1)
            if (r9 == 0) goto L_0x009c
            if (r8 != 0) goto L_0x0010
            goto L_0x009c
        L_0x0010:
            int r2 = r9.getWidth()
            int r3 = r9.getHeight()
            boolean r4 = r9 instanceof android.webkit.WebView
            if (r4 == 0) goto L_0x0037
            java.lang.Object r4 = r7.f1058A
            monitor-enter(r4)
            android.webkit.WebView r9 = (android.webkit.WebView) r9     // Catch:{ all -> 0x0034 }
            r7.m1005a((android.app.Activity) r8, (android.webkit.WebView) r9)     // Catch:{ all -> 0x0034 }
            java.lang.Object r8 = r7.f1058A     // Catch:{ Exception -> 0x002b }
            r5 = 5000(0x1388, double:2.4703E-320)
            r8.wait(r5)     // Catch:{ Exception -> 0x002b }
        L_0x002b:
            float r8 = r7.f1082y     // Catch:{ all -> 0x0034 }
            float r9 = r7.f1083z     // Catch:{ all -> 0x0034 }
            float r8 = r8 * r9
            int r8 = (int) r8     // Catch:{ all -> 0x0034 }
            monitor-exit(r4)     // Catch:{ all -> 0x0034 }
            goto L_0x005e
        L_0x0034:
            r8 = move-exception
            monitor-exit(r4)     // Catch:{ all -> 0x0034 }
            throw r8
        L_0x0037:
            boolean r8 = r9 instanceof android.widget.ScrollView
            if (r8 == 0) goto L_0x0054
            android.widget.ScrollView r9 = (android.widget.ScrollView) r9
            int r8 = r9.getChildCount()
            if (r8 <= 0) goto L_0x007d
            android.view.View r8 = r9.getChildAt(r1)
            int r8 = r8.getWidth()
            android.view.View r9 = r9.getChildAt(r1)
            int r9 = r9.getHeight()
            goto L_0x007f
        L_0x0054:
            boolean r8 = r9 instanceof android.widget.ListView
            if (r8 == 0) goto L_0x0061
            android.widget.ListView r9 = (android.widget.ListView) r9
            int r8 = com.baidu.mobstat.C0968bi.m1240a((android.widget.ListView) r9)
        L_0x005e:
            r9 = r8
            r8 = 0
            goto L_0x007f
        L_0x0061:
            boolean r8 = r9 instanceof android.widget.GridView
            if (r8 == 0) goto L_0x006c
            android.widget.GridView r9 = (android.widget.GridView) r9
            int r8 = com.baidu.mobstat.C0968bi.m1239a((android.widget.GridView) r9)
            goto L_0x005e
        L_0x006c:
            boolean r8 = com.baidu.mobstat.C0968bi.m1298p(r9)
            if (r8 == 0) goto L_0x007d
            androidx.recyclerview.widget.RecyclerView r9 = (androidx.recyclerview.widget.RecyclerView) r9     // Catch:{ Exception -> 0x007d }
            int r8 = r9.computeHorizontalScrollRange()     // Catch:{ Exception -> 0x007d }
            int r9 = r9.computeVerticalScrollRange()     // Catch:{ Exception -> 0x007e }
            goto L_0x007f
        L_0x007d:
            r8 = 0
        L_0x007e:
            r9 = 0
        L_0x007f:
            if (r8 != 0) goto L_0x0082
            r8 = r2
        L_0x0082:
            if (r9 != 0) goto L_0x0085
            r9 = r3
        L_0x0085:
            if (r8 <= 0) goto L_0x0088
            goto L_0x0089
        L_0x0088:
            r8 = 0
        L_0x0089:
            if (r9 <= 0) goto L_0x008c
            goto L_0x008d
        L_0x008c:
            r9 = 0
        L_0x008d:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            r0.add(r8)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r9)
            r0.add(r8)
            return r0
        L_0x009c:
            r0.add(r2)
            r0.add(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobstat.C0915as.mo11549a(android.app.Activity, android.view.View):java.util.ArrayList");
    }
}
