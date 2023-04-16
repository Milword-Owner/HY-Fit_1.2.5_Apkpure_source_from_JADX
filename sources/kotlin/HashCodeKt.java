package kotlin;

import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\f\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0000\n\u0000\u001a\u000f\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u0002H\b¨\u0006\u0003"}, mo21895d2 = {"hashCode", "", "", "kotlin-stdlib"}, mo21896k = 2, mo21897mv = {1, 1, 16})
/* compiled from: HashCode.kt */
public final class HashCodeKt {
    @SinceKotlin(version = "1.3")
    @InlineOnly
    private static final int hashCode(@Nullable Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }
}
