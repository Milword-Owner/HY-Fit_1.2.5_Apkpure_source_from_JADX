package com.google.android.gms.internal.fitness;

import com.google.android.gms.internal.fitness.zzgy;
import kotlin.text.Typography;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public final class zzkq {

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static final class zza extends zzgy<zza, C2781zza> implements zzim {
        /* access modifiers changed from: private */
        public static final zza zzaji;
        private static volatile zziu<zza> zzg;
        private int zzajc;
        private String zzajd = "";
        private String zzaje = "";
        private String zzajf = "";
        private zzhi zzajg = zzbr();
        private String zzajh = "";

        private zza() {
        }

        /* renamed from: com.google.android.gms.internal.fitness.zzkq$zza$zza  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
        public static final class C2781zza extends zzgy.zzb<zza, C2781zza> implements zzim {
            private C2781zza() {
                super(zza.zzaji);
            }

            /* synthetic */ C2781zza(zzkp zzkp) {
                this();
            }
        }

        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.fitness.zziu<com.google.android.gms.internal.fitness.zzkq$zza>, com.google.android.gms.internal.fitness.zzgy$zza] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zziu<zza> zziu;
            switch (zzkp.zze[i - 1]) {
                case 1:
                    return new zza();
                case 2:
                    return new C2781zza((zzkp) null);
                case 3:
                    return zza((zzik) zzaji, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004\u0014\u0005ဈ\u0003", new Object[]{"zzajc", "zzajd", "zzaje", "zzajf", "zzajg", "zzajh"});
                case 4:
                    return zzaji;
                case 5:
                    zziu<zza> zziu2 = zzg;
                    zziu<zza> zziu3 = zziu2;
                    if (zziu2 == null) {
                        synchronized (zza.class) {
                            zziu<zza> zziu4 = zzg;
                            zziu = zziu4;
                            if (zziu4 == null) {
                                ? zza = new zzgy.zza(zzaji);
                                zzg = zza;
                                zziu = zza;
                            }
                        }
                        zziu3 = zziu;
                    }
                    return zziu3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zza zza = new zza();
            zzaji = zza;
            zzgy.zza(zza.class, zza);
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static final class zzb extends zzgy<zzb, C2782zzb> implements zzim {
        private static final zzhf<Integer, zza> zzajq = new zzkr();
        /* access modifiers changed from: private */
        public static final zzb zzajr;
        private static volatile zziu<zzb> zzg;
        private int zzajc;
        private String zzajh = "";
        private String zzajj = "";
        private String zzajk = "";
        private int zzajl;
        private zzc zzajm;
        private zze zzajn;
        private zza zzajo;
        private zzhg zzajp = zzbq();

        /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
        public enum zza implements zzhb {
            DATA_QUALITY_UNKNOWN(0),
            DATA_QUALITY_BLOOD_PRESSURE_ESH2002(1),
            DATA_QUALITY_BLOOD_PRESSURE_ESH2010(2),
            DATA_QUALITY_BLOOD_PRESSURE_AAMI(3),
            DATA_QUALITY_BLOOD_PRESSURE_BHS_A_A(4),
            DATA_QUALITY_BLOOD_PRESSURE_BHS_A_B(5),
            DATA_QUALITY_BLOOD_PRESSURE_BHS_B_A(6),
            DATA_QUALITY_BLOOD_PRESSURE_BHS_B_B(7),
            DATA_QUALITY_BLOOD_GLUCOSE_ISO151972003(8),
            DATA_QUALITY_BLOOD_GLUCOSE_ISO151972013(9);
            
            private static final zzhe<zza> zzjx = null;
            private final int value;

            public final int zzc() {
                return this.value;
            }

            public static zzhd zzec() {
                return zzks.zzakd;
            }

            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            private zza(int i) {
                this.value = i;
            }

            static {
                zzjx = new zzkt();
            }
        }

        /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
        public enum zzc implements zzhb {
            RAW(0),
            DERIVED(1),
            CLEANED(2),
            CONVERTED(3);
            
            private static final zzhe<zzc> zzjx = null;
            private final int value;

            public final int zzc() {
                return this.value;
            }

            public static zzhd zzec() {
                return zzkv.zzakd;
            }

            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            private zzc(int i) {
                this.value = i;
            }

            static {
                zzjx = new zzku();
            }
        }

        private zzb() {
        }

        /* renamed from: com.google.android.gms.internal.fitness.zzkq$zzb$zzb  reason: collision with other inner class name */
        /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
        public static final class C2782zzb extends zzgy.zzb<zzb, C2782zzb> implements zzim {
            private C2782zzb() {
                super(zzb.zzajr);
            }

            /* synthetic */ C2782zzb(zzkp zzkp) {
                this();
            }
        }

        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.fitness.zziu<com.google.android.gms.internal.fitness.zzkq$zzb>, com.google.android.gms.internal.fitness.zzgy$zza] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zziu<zzb> zziu;
            switch (zzkp.zze[i - 1]) {
                case 1:
                    return new zzb();
                case 2:
                    return new C2782zzb((zzkp) null);
                case 3:
                    return zza((zzik) zzajr, "\u0001\b\u0000\u0001\u0001\n\b\u0000\u0001\u0000\u0001ဈ\u0000\u0004ဈ\u0001\u0005ဈ\u0002\u0006ဌ\u0003\u0007ဉ\u0004\bဉ\u0005\tဉ\u0006\n\u001e", new Object[]{"zzajc", "zzajj", "zzajh", "zzajk", "zzajl", zzc.zzec(), "zzajm", "zzajn", "zzajo", "zzajp", zza.zzec()});
                case 4:
                    return zzajr;
                case 5:
                    zziu<zzb> zziu2 = zzg;
                    zziu<zzb> zziu3 = zziu2;
                    if (zziu2 == null) {
                        synchronized (zzb.class) {
                            zziu<zzb> zziu4 = zzg;
                            zziu = zziu4;
                            if (zziu4 == null) {
                                ? zza2 = new zzgy.zza(zzajr);
                                zzg = zza2;
                                zziu = zza2;
                            }
                        }
                        zziu3 = zziu;
                    }
                    return zziu3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.fitness.zzkr, com.google.android.gms.internal.fitness.zzhf<java.lang.Integer, com.google.android.gms.internal.fitness.zzkq$zzb$zza>] */
        static {
            zzb zzb = new zzb();
            zzajr = zzb;
            zzgy.zza(zzb.class, zzb);
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static final class zzc extends zzgy<zzc, zza> implements zzim {
        /* access modifiers changed from: private */
        public static final zzc zzakk;
        private static volatile zziu<zzc> zzg;
        private int zzajc;
        private String zzajh = "";
        private zzhh<zzd> zzakj = zzbs();

        private zzc() {
        }

        /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
        public static final class zza extends zzgy.zzb<zzc, zza> implements zzim {
            private zza() {
                super(zzc.zzakk);
            }

            /* synthetic */ zza(zzkp zzkp) {
                this();
            }
        }

        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.fitness.zziu<com.google.android.gms.internal.fitness.zzkq$zzc>, com.google.android.gms.internal.fitness.zzgy$zza] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zziu<zzc> zziu;
            switch (zzkp.zze[i - 1]) {
                case 1:
                    return new zzc();
                case 2:
                    return new zza((zzkp) null);
                case 3:
                    return zza((zzik) zzakk, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဈ\u0000\u0002\u001b", new Object[]{"zzajc", "zzajh", "zzakj", zzd.class});
                case 4:
                    return zzakk;
                case 5:
                    zziu<zzc> zziu2 = zzg;
                    zziu<zzc> zziu3 = zziu2;
                    if (zziu2 == null) {
                        synchronized (zzc.class) {
                            zziu<zzc> zziu4 = zzg;
                            zziu = zziu4;
                            if (zziu4 == null) {
                                ? zza2 = new zzgy.zza(zzakk);
                                zzg = zza2;
                                zziu = zza2;
                            }
                        }
                        zziu3 = zziu;
                    }
                    return zziu3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzc zzc = new zzc();
            zzakk = zzc;
            zzgy.zza(zzc.class, zzc);
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static final class zzd extends zzgy<zzd, zzb> implements zzim {
        /* access modifiers changed from: private */
        public static final zzd zzakn;
        private static volatile zziu<zzd> zzg;
        private int zzajc;
        private String zzajh = "";
        private int zzakl = 1;
        private boolean zzakm;

        /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
        public enum zza implements zzhb {
            INTEGER(1),
            FLOAT_POINT(2),
            STRING(3),
            MAP(4),
            INTEGER_LIST(5),
            FLOAT_LIST(6),
            BLOB(7);
            
            private static final zzhe<zza> zzjx = null;
            private final int value;

            public final int zzc() {
                return this.value;
            }

            public static zzhd zzec() {
                return zzkw.zzakd;
            }

            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            private zza(int i) {
                this.value = i;
            }

            static {
                zzjx = new zzkx();
            }
        }

        private zzd() {
        }

        /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
        public static final class zzb extends zzgy.zzb<zzd, zzb> implements zzim {
            private zzb() {
                super(zzd.zzakn);
            }

            /* synthetic */ zzb(zzkp zzkp) {
                this();
            }
        }

        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.fitness.zzgy$zza, com.google.android.gms.internal.fitness.zziu<com.google.android.gms.internal.fitness.zzkq$zzd>] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zziu<zzd> zziu;
            switch (zzkp.zze[i - 1]) {
                case 1:
                    return new zzd();
                case 2:
                    return new zzb((zzkp) null);
                case 3:
                    return zza((zzik) zzakn, "\u0001\u0003\u0000\u0001\u0001\u0004\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0003ဌ\u0001\u0004ဇ\u0002", new Object[]{"zzajc", "zzajh", "zzakl", zza.zzec(), "zzakm"});
                case 4:
                    return zzakn;
                case 5:
                    zziu<zzd> zziu2 = zzg;
                    zziu<zzd> zziu3 = zziu2;
                    if (zziu2 == null) {
                        synchronized (zzd.class) {
                            zziu<zzd> zziu4 = zzg;
                            zziu = zziu4;
                            if (zziu4 == null) {
                                ? zza2 = new zzgy.zza(zzakn);
                                zzg = zza2;
                                zziu = zza2;
                            }
                        }
                        zziu3 = zziu;
                    }
                    return zziu3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zzd zzd = new zzd();
            zzakn = zzd;
            zzgy.zza(zzd.class, zzd);
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static final class zze extends zzgy<zze, zza> implements zzim {
        /* access modifiers changed from: private */
        public static final zze zzala;
        private static volatile zziu<zze> zzg;
        private int zzajc;
        private String zzaje = "";
        private int zzajl;
        private String zzakw = "";
        private String zzakx = "";
        private String zzaky = "";
        private int zzakz;

        /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
        public enum zzb implements zzhb {
            PLATFORM_TYPE_UNKNOWN(0),
            PLATFORM_TYPE_BLE(1),
            PLATFORM_TYPE_ANDROID(2);
            
            private static final zzhe<zzb> zzjx = null;
            private final int value;

            public final int zzc() {
                return this.value;
            }

            public static zzhd zzec() {
                return zzkz.zzakd;
            }

            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            private zzb(int i) {
                this.value = i;
            }

            static {
                zzjx = new zzky();
            }
        }

        /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
        public enum zzc implements zzhb {
            UNKNOWN(0),
            zzalg(1),
            TABLET(2),
            WATCH(3),
            CHEST_STRAP(4),
            SCALE(5),
            HEAD_MOUNTED(6),
            SMART_DISPLAY(7);
            
            private static final zzhe<zzc> zzjx = null;
            private final int value;

            public final int zzc() {
                return this.value;
            }

            public static zzhd zzec() {
                return zzla.zzakd;
            }

            public final String toString() {
                return "<" + getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(this)) + " number=" + this.value + " name=" + name() + Typography.greater;
            }

            private zzc(int i) {
                this.value = i;
            }

            static {
                zzjx = new zzlb();
            }
        }

        private zze() {
        }

        /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
        public static final class zza extends zzgy.zzb<zze, zza> implements zzim {
            private zza() {
                super(zze.zzala);
            }

            /* synthetic */ zza(zzkp zzkp) {
                this();
            }
        }

        /* JADX WARNING: type inference failed for: r2v14, types: [com.google.android.gms.internal.fitness.zzgy$zza, com.google.android.gms.internal.fitness.zziu<com.google.android.gms.internal.fitness.zzkq$zze>] */
        /* access modifiers changed from: protected */
        public final Object zza(int i, Object obj, Object obj2) {
            zziu<zze> zziu;
            switch (zzkp.zze[i - 1]) {
                case 1:
                    return new zze();
                case 2:
                    return new zza((zzkp) null);
                case 3:
                    return zza((zzik) zzala, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဌ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဌ\u0005", new Object[]{"zzajc", "zzakw", "zzajl", zzc.zzec(), "zzaje", "zzakx", "zzaky", "zzakz", zzb.zzec()});
                case 4:
                    return zzala;
                case 5:
                    zziu<zze> zziu2 = zzg;
                    zziu<zze> zziu3 = zziu2;
                    if (zziu2 == null) {
                        synchronized (zze.class) {
                            zziu<zze> zziu4 = zzg;
                            zziu = zziu4;
                            if (zziu4 == null) {
                                ? zza2 = new zzgy.zza(zzala);
                                zzg = zza2;
                                zziu = zza2;
                            }
                        }
                        zziu3 = zziu;
                    }
                    return zziu3;
                case 6:
                    return (byte) 1;
                case 7:
                    return null;
                default:
                    throw new UnsupportedOperationException();
            }
        }

        static {
            zze zze = new zze();
            zzala = zze;
            zzgy.zza(zze.class, zze);
        }
    }
}
