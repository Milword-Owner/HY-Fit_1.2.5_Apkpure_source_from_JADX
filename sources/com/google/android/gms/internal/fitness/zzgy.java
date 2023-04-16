package com.google.android.gms.internal.fitness;

import com.google.android.gms.internal.fitness.zzgy;
import com.google.android.gms.internal.fitness.zzgy.zzb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzgy<MessageType extends zzgy<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzfo<MessageType, BuilderType> {
    private static Map<Object, zzgy<?, ?>> zzxv = new ConcurrentHashMap();
    protected zzjr zzxt = zzjr.zzdp();
    private int zzxu = -1;

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    protected static class zza<T extends zzgy<T, ?>> extends zzft<T> {
        private final T zzxx;

        public zza(T t) {
            this.zzxx = t;
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    static final class zzc implements zzgv<zzc> {
        public final int zzc() {
            throw new NoSuchMethodError();
        }

        public final zzkg zzbl() {
            throw new NoSuchMethodError();
        }

        public final zzkj zzbm() {
            throw new NoSuchMethodError();
        }

        public final boolean zzbn() {
            throw new NoSuchMethodError();
        }

        public final boolean zzbo() {
            throw new NoSuchMethodError();
        }

        public final zzij zza(zzij zzij, zzik zzik) {
            throw new NoSuchMethodError();
        }

        public final zziq zza(zziq zziq, zziq zziq2) {
            throw new NoSuchMethodError();
        }

        public final /* synthetic */ int compareTo(Object obj) {
            throw new NoSuchMethodError();
        }
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType> extends zzgy<MessageType, BuilderType> implements zzim {
        protected zzgt<zzc> zzya = zzgt.zzbj();
    }

    /* 'enum' modifier removed */
    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static final class zze {
        public static final int zzyd = 1;
        public static final int zzye = 2;
        public static final int zzyf = 3;
        public static final int zzyg = 4;
        public static final int zzyh = 5;
        public static final int zzyi = 6;
        public static final int zzyj = 7;
        private static final /* synthetic */ int[] zzyk = {zzyd, zzye, zzyf, zzyg, zzyh, zzyi, zzyj};

        public static int[] zzcc() {
            return (int[]) zzyk.clone();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zza(int i, Object obj, Object obj2);

    public String toString() {
        return zzil.zza(this, super.toString());
    }

    public int hashCode() {
        if (this.zztu != 0) {
            return this.zztu;
        }
        this.zztu = zziv.zzcy().zzn(this).hashCode(this);
        return this.zztu;
    }

    /* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
    public static abstract class zzb<MessageType extends zzgy<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzfr<MessageType, BuilderType> {
        private final MessageType zzxx;
        private MessageType zzxy;
        private boolean zzxz = false;

        protected zzb(MessageType messagetype) {
            this.zzxx = messagetype;
            this.zzxy = (zzgy) messagetype.zza(zze.zzyg, (Object) null, (Object) null);
        }

        /* access modifiers changed from: protected */
        public void zzbw() {
            MessageType messagetype = (zzgy) this.zzxy.zza(zze.zzyg, (Object) null, (Object) null);
            zza(messagetype, this.zzxy);
            this.zzxy = messagetype;
        }

        public final boolean isInitialized() {
            return zzgy.zza(this.zzxy, false);
        }

        /* renamed from: zzbx */
        public MessageType zzbz() {
            if (this.zzxz) {
                return this.zzxy;
            }
            MessageType messagetype = this.zzxy;
            zziv.zzcy().zzn(messagetype).zze(messagetype);
            this.zzxz = true;
            return this.zzxy;
        }

        /* renamed from: zzby */
        public final MessageType zzca() {
            MessageType messagetype = (zzgy) zzbz();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzjp(messagetype);
        }

        public final BuilderType zza(MessageType messagetype) {
            if (this.zzxz) {
                zzbw();
                this.zzxz = false;
            }
            zza(this.zzxy, messagetype);
            return this;
        }

        private static void zza(MessageType messagetype, MessageType messagetype2) {
            zziv.zzcy().zzn(messagetype).zzd(messagetype, messagetype2);
        }

        public final /* synthetic */ zzfr zzap() {
            return (zzb) clone();
        }

        public final /* synthetic */ zzik zzbu() {
            return this.zzxx;
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzb zzb = (zzb) ((zzgy) this.zzxx).zza(zze.zzyh, (Object) null, (Object) null);
            zzb.zza((zzgy) zzbz());
            return zzb;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            return zziv.zzcy().zzn(this).equals(this, (zzgy) obj);
        }
        return false;
    }

    public final boolean isInitialized() {
        return zza(this, Boolean.TRUE.booleanValue());
    }

    /* access modifiers changed from: package-private */
    public final int zzan() {
        return this.zzxu;
    }

    /* access modifiers changed from: package-private */
    public final void zzi(int i) {
        this.zzxu = i;
    }

    public final void zzb(zzgk zzgk) throws IOException {
        zziv.zzcy().zzn(this).zza(this, zzgn.zza(zzgk));
    }

    public final int zzbp() {
        if (this.zzxu == -1) {
            this.zzxu = zziv.zzcy().zzn(this).zzm(this);
        }
        return this.zzxu;
    }

    static <T extends zzgy<?, ?>> T zza(Class<T> cls) {
        T t = (zzgy) zzxv.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzgy) zzxv.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t == null) {
            t = (zzgy) ((zzgy) zzjy.zzg(cls)).zza(zze.zzyi, (Object) null, (Object) null);
            if (t != null) {
                zzxv.put(cls, t);
            } else {
                throw new IllegalStateException();
            }
        }
        return t;
    }

    protected static <T extends zzgy<?, ?>> void zza(Class<T> cls, T t) {
        zzxv.put(cls, t);
    }

    protected static Object zza(zzik zzik, String str, Object[] objArr) {
        return new zzix(zzik, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
            }
        }
    }

    protected static final <T extends zzgy<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zze.zzyd, (Object) null, (Object) null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzl = zziv.zzcy().zzn(t).zzl(t);
        if (z) {
            t.zza(zze.zzye, (Object) zzl ? t : null, (Object) null);
        }
        return zzl;
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.fitness.zzhg, com.google.android.gms.internal.fitness.zzha] */
    protected static zzhg zzbq() {
        return zzha.zzcb();
    }

    /* JADX WARNING: type inference failed for: r0v0, types: [com.google.android.gms.internal.fitness.zzhy, com.google.android.gms.internal.fitness.zzhi] */
    protected static zzhi zzbr() {
        return zzhy.zzcm();
    }

    protected static <E> zzhh<E> zzbs() {
        return zziy.zzdb();
    }

    public final /* synthetic */ zzij zzbt() {
        zzb zzb2 = (zzb) zza(zze.zzyh, (Object) null, (Object) null);
        zzb2.zza(this);
        return zzb2;
    }

    public final /* synthetic */ zzik zzbu() {
        return (zzgy) zza(zze.zzyi, (Object) null, (Object) null);
    }
}
