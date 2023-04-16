package com.mob.commons.p024b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import com.mob.commons.C2312k;
import java.util.ArrayList;

/* renamed from: com.mob.commons.b.g */
/* compiled from: Meizu */
public class C2282g extends C2277f {
    /* access modifiers changed from: private */

    /* renamed from: c */
    public C2284a f2086c = new C2284a(C2312k.m2575a(71));
    /* access modifiers changed from: private */

    /* renamed from: d */
    public C2284a f2087d = new C2284a(C2312k.m2575a(69));
    /* access modifiers changed from: private */

    /* renamed from: e */
    public C2284a f2088e = new C2284a(C2312k.m2575a(70));
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C2284a f2089f = new C2284a(C2312k.m2575a(75));

    /* renamed from: g */
    private C2284a f2090g = new C2284a(C2312k.m2575a(125));

    /* renamed from: h */
    private BroadcastReceiver f2091h;

    public C2282g(Context context) {
        super(context);
    }

    /* renamed from: b */
    public synchronized String mo29045b() {
        if (this.f2069a == null) {
            return null;
        }
        return m2385a(this.f2069a.getApplicationContext(), this.f2089f, false);
    }

    /* renamed from: e */
    public synchronized String mo29053e() {
        if (this.f2069a == null) {
            return null;
        }
        return m2385a(this.f2069a.getApplicationContext(), this.f2087d, false);
    }

    /* renamed from: f */
    public synchronized String mo29054f() {
        if (this.f2069a == null) {
            return null;
        }
        return m2385a(this.f2069a.getApplicationContext(), this.f2086c, false);
    }

    /* renamed from: g */
    public synchronized String mo29055g() {
        if (this.f2069a == null) {
            return null;
        }
        return m2385a(this.f2069a.getApplicationContext(), this.f2088e, false);
    }

    /* renamed from: a */
    private String m2385a(Context context, C2284a aVar, boolean z) {
        String str;
        if (aVar == null) {
            return null;
        }
        if (!z && aVar.mo29065b()) {
            return aVar.f2095c;
        }
        try {
            Cursor query = context.getContentResolver().query(Uri.parse(C2312k.m2575a(124)), (String[]) null, (String) null, new String[]{aVar.f2093a}, (String) null);
            if (query != null) {
                query.moveToFirst();
                int columnIndex = query.getColumnIndex(C2312k.m2575a(126));
                if (columnIndex >= 0) {
                    str = query.getString(columnIndex);
                    aVar.mo29064a(str);
                } else {
                    str = null;
                }
                if (!z) {
                    int columnIndex2 = query.getColumnIndex(C2312k.m2575a(130));
                    if (columnIndex2 >= 0) {
                        aVar.mo29063a(query.getLong(columnIndex2));
                    }
                    int columnIndex3 = query.getColumnIndex(C2312k.m2575a(119));
                    if (columnIndex3 >= 0 && query.getInt(columnIndex3) != 1000) {
                        m2390j();
                        if (!m2386a(false)) {
                            m2386a(true);
                        }
                    }
                }
                query.close();
                return str;
            }
            if (z) {
                aVar.mo29064a("1");
            }
            if (m2386a(false)) {
                m2386a(true);
            }
            return null;
        } catch (Throwable th) {
            C2272c.m2344a().mo29044a(th);
        }
    }

    /* renamed from: j */
    private void m2390j() {
        try {
            if (this.f2091h == null) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(C2312k.m2575a(131));
                this.f2091h = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        String stringExtra;
                        ArrayList<String> stringArrayListExtra;
                        if (context != null && intent != null) {
                            try {
                                boolean z = false;
                                if (intent.getIntExtra(C2312k.m2575a(127), 0) == 2 && (stringArrayListExtra = intent.getStringArrayListExtra(C2312k.m2575a(128))) != null) {
                                    z = stringArrayListExtra.contains(context.getPackageName());
                                }
                                if (z && (stringExtra = intent.getStringExtra(C2312k.m2575a(129))) != null) {
                                    if (stringExtra.equals(C2312k.m2575a(71))) {
                                        C2282g.this.f2086c.mo29063a(0);
                                    } else if (stringExtra.equals(C2312k.m2575a(69))) {
                                        C2282g.this.f2087d.mo29063a(0);
                                    } else if (stringExtra.equals(C2312k.m2575a(70))) {
                                        C2282g.this.f2088e.mo29063a(0);
                                    } else if (stringExtra.equals(C2312k.m2575a(75))) {
                                        C2282g.this.f2089f.mo29063a(0);
                                    }
                                }
                            } catch (Throwable unused) {
                            }
                        }
                    }
                };
                this.f2069a.registerReceiver(this.f2091h, intentFilter, C2312k.m2575a(132), (Handler) null);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private boolean m2386a(boolean z) {
        C2284a aVar;
        if (!z && (aVar = this.f2090g) != null && aVar.mo29062a() != null) {
            return this.f2090g.mo29062a().equals("0");
        }
        String a = m2385a(this.f2069a, this.f2090g, true);
        if (a == null || !"0".equals(a)) {
            return false;
        }
        return true;
    }

    /* renamed from: com.mob.commons.b.g$a */
    /* compiled from: Meizu */
    public static class C2284a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public String f2093a;

        /* renamed from: b */
        private long f2094b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public String f2095c;

        public C2284a(String str) {
            this.f2093a = str;
        }

        /* renamed from: a */
        public void mo29063a(long j) {
            this.f2094b = j;
        }

        /* renamed from: a */
        public String mo29062a() {
            return this.f2095c;
        }

        /* renamed from: a */
        public void mo29064a(String str) {
            this.f2095c = str;
        }

        /* renamed from: b */
        public boolean mo29065b() {
            return this.f2094b > System.currentTimeMillis();
        }
    }
}
