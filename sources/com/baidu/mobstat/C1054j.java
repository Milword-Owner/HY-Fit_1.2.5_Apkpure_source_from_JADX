package com.baidu.mobstat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import java.io.Closeable;
import java.util.ArrayList;

/* renamed from: com.baidu.mobstat.j */
abstract class C1054j implements Closeable {

    /* renamed from: a */
    private C1061l f1422a;

    /* renamed from: a */
    public abstract long mo11838a(String str, String str2);

    /* renamed from: a */
    public abstract ArrayList<C1053i> mo11841a(int i, int i2);

    /* renamed from: b */
    public abstract boolean mo11845b(long j);

    public C1054j(Context context, String str, String str2) {
        try {
            this.f1422a = new C1061l(context, str);
            if (context.getDatabasePath(C1087y.f1473e) != null) {
                m1715a(str2);
            }
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    private void m1715a(String str) {
        this.f1422a.mo11858a(str);
    }

    /* renamed from: a */
    public synchronized boolean mo11842a() {
        try {
        } catch (Exception e) {
            C0954ba.m1191c().mo11629b((Throwable) e);
            return false;
        }
        return this.f1422a.mo11859a();
    }

    public synchronized void close() {
        try {
            this.f1422a.close();
        } catch (Exception e) {
            C0954ba.m1191c().mo11629b((Throwable) e);
        }
        return;
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public int mo11844b() {
        return this.f1422a.mo11860b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Cursor mo11839a(String str, int i, int i2) {
        return this.f1422a.mo11857a((String[]) null, (String) null, (String[]) null, (String) null, (String) null, str + " desc", i2 + ", " + i);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Cursor mo11840a(String str, String str2, String str3, int i) {
        String str4 = str + "=? ";
        String[] strArr = {str2};
        return this.f1422a.mo11857a((String[]) null, str4, strArr, (String) null, (String) null, str3 + " desc", i + "");
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public long mo11837a(ContentValues contentValues) {
        return this.f1422a.mo11856a((String) null, contentValues);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo11843a(long j) {
        if (this.f1422a.mo11855a("_id=? ", new String[]{j + ""}) > 0) {
            return true;
        }
        return false;
    }
}
