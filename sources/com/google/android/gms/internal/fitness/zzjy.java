package com.google.android.gms.internal.fitness;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzjy {
    private static final Logger logger = Logger.getLogger(zzjy.class.getName());
    private static final Unsafe zzaad = zzdt();
    private static final boolean zzabz = zzj(Long.TYPE);
    private static final boolean zzaca = zzj(Integer.TYPE);
    private static final zzc zzacb;
    private static final boolean zzacc = zzdv();
    private static final long zzacd = ((long) zzh(byte[].class));
    private static final long zzace = ((long) zzh(boolean[].class));
    private static final long zzacf = ((long) zzi(boolean[].class));
    private static final long zzacg = ((long) zzh(int[].class));
    private static final long zzach = ((long) zzi(int[].class));
    private static final long zzaci = ((long) zzh(long[].class));
    private static final long zzacj = ((long) zzi(long[].class));
    private static final long zzack = ((long) zzh(float[].class));
    private static final long zzacl = ((long) zzi(float[].class));
    private static final long zzacm = ((long) zzh(double[].class));
    private static final long zzacn = ((long) zzi(double[].class));
    private static final long zzaco = ((long) zzh(Object[].class));
    private static final long zzacp = ((long) zzi(Object[].class));
    private static final long zzacq;
    private static final int zzacr = ((int) (zzacd & 7));
    static final boolean zzacs = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);
    private static final Class<?> zzty = zzfv.zzau();
    private static final boolean zzum = zzdu();

    private zzjy() {
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    private static final class zza extends zzc {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzx(Object obj, long j) {
            if (zzjy.zzacs) {
                return zzjy.zzp(obj, j);
            }
            return zzjy.zzq(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzjy.zzacs) {
                zzjy.zza(obj, j, b);
            } else {
                zzjy.zzb(obj, j, b);
            }
        }

        public final boolean zzl(Object obj, long j) {
            if (zzjy.zzacs) {
                return zzjy.zzr(obj, j);
            }
            return zzjy.zzs(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzjy.zzacs) {
                zzjy.zzb(obj, j, z);
            } else {
                zzjy.zzc(obj, j, z);
            }
        }

        public final float zzm(Object obj, long j) {
            return Float.intBitsToFloat(zzj(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final double zzn(Object obj, long j) {
            return Double.longBitsToDouble(zzk(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    private static final class zzb extends zzc {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzx(Object obj, long j) {
            if (zzjy.zzacs) {
                return zzjy.zzp(obj, j);
            }
            return zzjy.zzq(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzjy.zzacs) {
                zzjy.zza(obj, j, b);
            } else {
                zzjy.zzb(obj, j, b);
            }
        }

        public final boolean zzl(Object obj, long j) {
            if (zzjy.zzacs) {
                return zzjy.zzr(obj, j);
            }
            return zzjy.zzs(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            if (zzjy.zzacs) {
                zzjy.zzb(obj, j, z);
            } else {
                zzjy.zzc(obj, j, z);
            }
        }

        public final float zzm(Object obj, long j) {
            return Float.intBitsToFloat(zzj(obj, j));
        }

        public final void zza(Object obj, long j, float f) {
            zza(obj, j, Float.floatToIntBits(f));
        }

        public final double zzn(Object obj, long j) {
            return Double.longBitsToDouble(zzk(obj, j));
        }

        public final void zza(Object obj, long j, double d) {
            zza(obj, j, Double.doubleToLongBits(d));
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    private static final class zzd extends zzc {
        zzd(Unsafe unsafe) {
            super(unsafe);
        }

        public final byte zzx(Object obj, long j) {
            return this.zzact.getByte(obj, j);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzact.putByte(obj, j, b);
        }

        public final boolean zzl(Object obj, long j) {
            return this.zzact.getBoolean(obj, j);
        }

        public final void zza(Object obj, long j, boolean z) {
            this.zzact.putBoolean(obj, j, z);
        }

        public final float zzm(Object obj, long j) {
            return this.zzact.getFloat(obj, j);
        }

        public final void zza(Object obj, long j, float f) {
            this.zzact.putFloat(obj, j, f);
        }

        public final double zzn(Object obj, long j) {
            return this.zzact.getDouble(obj, j);
        }

        public final void zza(Object obj, long j, double d) {
            this.zzact.putDouble(obj, j, d);
        }
    }

    static boolean zzdr() {
        return zzum;
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    private static abstract class zzc {
        Unsafe zzact;

        zzc(Unsafe unsafe) {
            this.zzact = unsafe;
        }

        public abstract void zza(Object obj, long j, double d);

        public abstract void zza(Object obj, long j, float f);

        public abstract void zza(Object obj, long j, boolean z);

        public abstract void zze(Object obj, long j, byte b);

        public abstract boolean zzl(Object obj, long j);

        public abstract float zzm(Object obj, long j);

        public abstract double zzn(Object obj, long j);

        public abstract byte zzx(Object obj, long j);

        public final int zzj(Object obj, long j) {
            return this.zzact.getInt(obj, j);
        }

        public final void zza(Object obj, long j, int i) {
            this.zzact.putInt(obj, j, i);
        }

        public final long zzk(Object obj, long j) {
            return this.zzact.getLong(obj, j);
        }

        public final void zza(Object obj, long j, long j2) {
            this.zzact.putLong(obj, j, j2);
        }
    }

    static boolean zzds() {
        return zzacc;
    }

    static <T> T zzg(Class<T> cls) {
        try {
            return zzaad.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzh(Class<?> cls) {
        if (zzum) {
            return zzacb.zzact.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzi(Class<?> cls) {
        if (zzum) {
            return zzacb.zzact.arrayIndexScale(cls);
        }
        return -1;
    }

    static int zzj(Object obj, long j) {
        return zzacb.zzj(obj, j);
    }

    static void zza(Object obj, long j, int i) {
        zzacb.zza(obj, j, i);
    }

    static long zzk(Object obj, long j) {
        return zzacb.zzk(obj, j);
    }

    static void zza(Object obj, long j, long j2) {
        zzacb.zza(obj, j, j2);
    }

    static boolean zzl(Object obj, long j) {
        return zzacb.zzl(obj, j);
    }

    static void zza(Object obj, long j, boolean z) {
        zzacb.zza(obj, j, z);
    }

    static float zzm(Object obj, long j) {
        return zzacb.zzm(obj, j);
    }

    static void zza(Object obj, long j, float f) {
        zzacb.zza(obj, j, f);
    }

    static double zzn(Object obj, long j) {
        return zzacb.zzn(obj, j);
    }

    static void zza(Object obj, long j, double d) {
        zzacb.zza(obj, j, d);
    }

    static Object zzo(Object obj, long j) {
        return zzacb.zzact.getObject(obj, j);
    }

    static void zza(Object obj, long j, Object obj2) {
        zzacb.zzact.putObject(obj, j, obj2);
    }

    static byte zza(byte[] bArr, long j) {
        return zzacb.zzx(bArr, zzacd + j);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzacb.zze(bArr, zzacd + j, b);
    }

    static Unsafe zzdt() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzjx());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzdu() {
        Unsafe unsafe = zzaad;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzfv.zzat()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzdv() {
        Unsafe unsafe = zzaad;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzdw() == null) {
                return false;
            }
            if (zzfv.zzat()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzj(Class<?> cls) {
        if (!zzfv.zzat()) {
            return false;
        }
        try {
            Class<?> cls2 = zzty;
            cls2.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls2.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls2.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls2.getMethod("peekByte", new Class[]{cls});
            cls2.getMethod("pokeByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            cls2.getMethod("peekByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static Field zzdw() {
        Field zzb2;
        if (zzfv.zzat() && (zzb2 = zzb(Buffer.class, "effectiveDirectAddress")) != null) {
            return zzb2;
        }
        Field zzb3 = zzb(Buffer.class, "address");
        if (zzb3 == null || zzb3.getType() != Long.TYPE) {
            return null;
        }
        return zzb3;
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static byte zzp(Object obj, long j) {
        return (byte) (zzj(obj, -4 & j) >>> ((int) (((j ^ -1) & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (zzj(obj, -4 & j) >>> ((int) ((j & 3) << 3)));
    }

    /* access modifiers changed from: private */
    public static void zza(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = ((((int) j) ^ -1) & 3) << 3;
        zza(obj, j2, ((255 & b) << i) | (zzj(obj, j2) & ((255 << i) ^ -1)));
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = -4 & j;
        int i = (((int) j) & 3) << 3;
        zza(obj, j2, ((255 & b) << i) | (zzj(obj, j2) & ((255 << i) ^ -1)));
    }

    /* access modifiers changed from: private */
    public static boolean zzr(Object obj, long j) {
        return zzp(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    /* access modifiers changed from: private */
    public static void zzb(Object obj, long j, boolean z) {
        zza(obj, j, z ? (byte) 1 : 0);
    }

    /* access modifiers changed from: private */
    public static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, z ? (byte) 1 : 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00dc, code lost:
        r1 = zzacb;
     */
    static {
        /*
            java.lang.Class<com.google.android.gms.internal.fitness.zzjy> r0 = com.google.android.gms.internal.fitness.zzjy.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            logger = r0
            sun.misc.Unsafe r0 = zzdt()
            zzaad = r0
            java.lang.Class r0 = com.google.android.gms.internal.fitness.zzfv.zzau()
            zzty = r0
            java.lang.Class r0 = java.lang.Long.TYPE
            boolean r0 = zzj(r0)
            zzabz = r0
            java.lang.Class r0 = java.lang.Integer.TYPE
            boolean r0 = zzj(r0)
            zzaca = r0
            sun.misc.Unsafe r0 = zzaad
            r1 = 0
            if (r0 != 0) goto L_0x002e
            goto L_0x0053
        L_0x002e:
            boolean r0 = com.google.android.gms.internal.fitness.zzfv.zzat()
            if (r0 == 0) goto L_0x004c
            boolean r0 = zzabz
            if (r0 == 0) goto L_0x0040
            com.google.android.gms.internal.fitness.zzjy$zza r1 = new com.google.android.gms.internal.fitness.zzjy$zza
            sun.misc.Unsafe r0 = zzaad
            r1.<init>(r0)
            goto L_0x0053
        L_0x0040:
            boolean r0 = zzaca
            if (r0 == 0) goto L_0x0053
            com.google.android.gms.internal.fitness.zzjy$zzb r1 = new com.google.android.gms.internal.fitness.zzjy$zzb
            sun.misc.Unsafe r0 = zzaad
            r1.<init>(r0)
            goto L_0x0053
        L_0x004c:
            com.google.android.gms.internal.fitness.zzjy$zzd r1 = new com.google.android.gms.internal.fitness.zzjy$zzd
            sun.misc.Unsafe r0 = zzaad
            r1.<init>(r0)
        L_0x0053:
            zzacb = r1
            boolean r0 = zzdv()
            zzacc = r0
            boolean r0 = zzdu()
            zzum = r0
            java.lang.Class<byte[]> r0 = byte[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzacd = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzace = r0
            java.lang.Class<boolean[]> r0 = boolean[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzacf = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzacg = r0
            java.lang.Class<int[]> r0 = int[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzach = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzaci = r0
            java.lang.Class<long[]> r0 = long[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzacj = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzack = r0
            java.lang.Class<float[]> r0 = float[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzacl = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzacm = r0
            java.lang.Class<double[]> r0 = double[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzacn = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzh(r0)
            long r0 = (long) r0
            zzaco = r0
            java.lang.Class<java.lang.Object[]> r0 = java.lang.Object[].class
            int r0 = zzi(r0)
            long r0 = (long) r0
            zzacp = r0
            java.lang.reflect.Field r0 = zzdw()
            if (r0 == 0) goto L_0x00e8
            com.google.android.gms.internal.fitness.zzjy$zzc r1 = zzacb
            if (r1 != 0) goto L_0x00e1
            goto L_0x00e8
        L_0x00e1:
            sun.misc.Unsafe r1 = r1.zzact
            long r0 = r1.objectFieldOffset(r0)
            goto L_0x00ea
        L_0x00e8:
            r0 = -1
        L_0x00ea:
            zzacq = r0
            long r0 = zzacd
            r2 = 7
            long r0 = r0 & r2
            int r1 = (int) r0
            zzacr = r1
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r1 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r1) goto L_0x00fe
            r0 = 1
            goto L_0x00ff
        L_0x00fe:
            r0 = 0
        L_0x00ff:
            zzacs = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.fitness.zzjy.<clinit>():void");
    }
}
