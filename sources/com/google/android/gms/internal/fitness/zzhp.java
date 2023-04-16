package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public class zzhp {
    private static final zzgp zztx = zzgp.zzbf();
    private zzfx zzze;
    private volatile zzik zzzf;
    private volatile zzfx zzzg;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzhp)) {
            return false;
        }
        zzhp zzhp = (zzhp) obj;
        zzik zzik = this.zzzf;
        zzik zzik2 = zzhp.zzzf;
        if (zzik == null && zzik2 == null) {
            return zzam().equals(zzhp.zzam());
        }
        if (zzik != null && zzik2 != null) {
            return zzik.equals(zzik2);
        }
        if (zzik != null) {
            return zzik.equals(zzhp.zzg(zzik.zzbu()));
        }
        return zzg(zzik2.zzbu()).equals(zzik2);
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:7|8|9|10|11|12) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0012 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.fitness.zzik zzg(com.google.android.gms.internal.fitness.zzik r2) {
        /*
            r1 = this;
            com.google.android.gms.internal.fitness.zzik r0 = r1.zzzf
            if (r0 != 0) goto L_0x001d
            monitor-enter(r1)
            com.google.android.gms.internal.fitness.zzik r0 = r1.zzzf     // Catch:{ all -> 0x001a }
            if (r0 == 0) goto L_0x000b
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x000b:
            r1.zzzf = r2     // Catch:{ zzhk -> 0x0012 }
            com.google.android.gms.internal.fitness.zzfx r0 = com.google.android.gms.internal.fitness.zzfx.zzub     // Catch:{ zzhk -> 0x0012 }
            r1.zzzg = r0     // Catch:{ zzhk -> 0x0012 }
            goto L_0x0018
        L_0x0012:
            r1.zzzf = r2     // Catch:{ all -> 0x001a }
            com.google.android.gms.internal.fitness.zzfx r2 = com.google.android.gms.internal.fitness.zzfx.zzub     // Catch:{ all -> 0x001a }
            r1.zzzg = r2     // Catch:{ all -> 0x001a }
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            goto L_0x001d
        L_0x001a:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001a }
            throw r2
        L_0x001d:
            com.google.android.gms.internal.fitness.zzik r2 = r1.zzzf
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.zzhp.zzg(com.google.android.gms.internal.fitness.zzik):com.google.android.gms.internal.fitness.zzik");
    }

    public final zzik zzh(zzik zzik) {
        zzik zzik2 = this.zzzf;
        this.zzze = null;
        this.zzzg = null;
        this.zzzf = zzik;
        return zzik2;
    }

    public final int zzbp() {
        if (this.zzzg != null) {
            return this.zzzg.size();
        }
        if (this.zzzf != null) {
            return this.zzzf.zzbp();
        }
        return 0;
    }

    public final zzfx zzam() {
        if (this.zzzg != null) {
            return this.zzzg;
        }
        synchronized (this) {
            if (this.zzzg != null) {
                zzfx zzfx = this.zzzg;
                return zzfx;
            }
            if (this.zzzf == null) {
                this.zzzg = zzfx.zzub;
            } else {
                this.zzzg = this.zzzf.zzam();
            }
            zzfx zzfx2 = this.zzzg;
            return zzfx2;
        }
    }
}
