package com.google.android.gms.internal.fitness;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
final class zzka {
    private static final zzjz zzacu;

    /* access modifiers changed from: private */
    public static int zzan(int i) {
        if (i > -12) {
            return -1;
        }
        return i;
    }

    public static boolean zzc(byte[] bArr) {
        return zzacu.zzc(bArr, 0, bArr.length);
    }

    /* access modifiers changed from: private */
    public static int zzd(int i, int i2, int i3) {
        if (i > -12 || i2 > -65 || i3 > -65) {
            return -1;
        }
        return (i ^ (i2 << 8)) ^ (i3 << 16);
    }

    /* access modifiers changed from: private */
    public static int zzq(int i, int i2) {
        if (i > -12 || i2 > -65) {
            return -1;
        }
        return i ^ (i2 << 8);
    }

    public static boolean zzc(byte[] bArr, int i, int i2) {
        return zzacu.zzc(bArr, i, i2);
    }

    /* access modifiers changed from: private */
    public static int zzd(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        int i3 = i2 - i;
        if (i3 == 0) {
            return zzan(b);
        }
        if (i3 == 1) {
            return zzq(b, bArr[i]);
        }
        if (i3 == 2) {
            return zzd((int) b, (int) bArr[i], (int) bArr[i + 1]);
        }
        throw new AssertionError();
    }

    static int zza(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) >= 65536) {
                                i2++;
                            } else {
                                throw new zzkb(i2, length2);
                            }
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        StringBuilder sb = new StringBuilder(54);
        sb.append("UTF-8 length does not fit in int: ");
        sb.append(((long) i3) + 4294967296L);
        throw new IllegalArgumentException(sb.toString());
    }

    static int zzb(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return zzacu.zza(charSequence, bArr, i, i2);
    }

    static {
        zzjz zzjz;
        if (!(zzjy.zzdr() && zzjy.zzds()) || zzfv.zzat()) {
            zzjz = new zzkc();
        } else {
            zzjz = new zzke();
        }
        zzacu = zzjz;
    }
}
