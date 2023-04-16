package com.google.android.gms.internal.fitness;

import com.google.android.gms.internal.fitness.zzfo;
import com.google.android.gms.internal.fitness.zzfr;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-fitness@@20.0.0 */
public abstract class zzfo<MessageType extends zzfo<MessageType, BuilderType>, BuilderType extends zzfr<MessageType, BuilderType>> implements zzik {
    protected int zztu = 0;

    public final zzfx zzam() {
        try {
            zzgf zzl = zzfx.zzl(zzbp());
            zzb(zzl.zzba());
            return zzl.zzaz();
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + "ByteString".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* access modifiers changed from: package-private */
    public int zzan() {
        throw new UnsupportedOperationException();
    }

    /* access modifiers changed from: package-private */
    public void zzi(int i) {
        throw new UnsupportedOperationException();
    }
}
