package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzix implements zzii {
    private final int flags;
    private final String info;
    private final Object[] zzaaf;
    private final zzik zzzy;

    zzix(zzik zzik, String str, Object[] objArr) {
        this.zzzy = zzik;
        this.info = str;
        this.zzaaf = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        char c = charAt & 8191;
        int i = 13;
        int i2 = 1;
        while (true) {
            int i3 = i2 + 1;
            char charAt2 = str.charAt(i2);
            if (charAt2 >= 55296) {
                c |= (charAt2 & 8191) << i;
                i += 13;
                i2 = i3;
            } else {
                this.flags = c | (charAt2 << i);
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final String zzcz() {
        return this.info;
    }

    /* access modifiers changed from: package-private */
    public final Object[] zzda() {
        return this.zzaaf;
    }

    public final zzik zzcu() {
        return this.zzzy;
    }

    public final int zzcs() {
        return (this.flags & 1) == 1 ? zziw.zzaaw : zziw.zzaax;
    }

    public final boolean zzct() {
        return (this.flags & 2) == 2;
    }
}
