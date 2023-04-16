package kotlin.reflect;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.internal.LowPriorityInOverloadResolution;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0010\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a+\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0007¢\u0006\u0002\u0010\u0005\u001a-\u0010\u0006\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0002H\u0007¢\u0006\u0002\u0010\u0005¨\u0006\u0007"}, mo21895d2 = {"cast", "T", "", "Lkotlin/reflect/KClass;", "value", "(Lkotlin/reflect/KClass;Ljava/lang/Object;)Ljava/lang/Object;", "safeCast", "kotlin-stdlib"}, mo21896k = 2, mo21897mv = {1, 1, 16})
@JvmName(name = "KClasses")
/* compiled from: KClasses.kt */
public final class KClasses {
    @SinceKotlin(version = "1.3")
    @NotNull
    @ExperimentalStdlibApi
    @LowPriorityInOverloadResolution
    public static final <T> T cast(@NotNull KClass<T> kClass, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(kClass, "$this$cast");
        if (!kClass.isInstance(obj)) {
            throw new ClassCastException("Value cannot be cast to " + kClass.getQualifiedName());
        } else if (obj != null) {
            return obj;
        } else {
            throw new TypeCastException("null cannot be cast to non-null type T");
        }
    }

    @SinceKotlin(version = "1.3")
    @Nullable
    @ExperimentalStdlibApi
    @LowPriorityInOverloadResolution
    public static final <T> T safeCast(@NotNull KClass<T> kClass, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(kClass, "$this$safeCast");
        if (!kClass.isInstance(obj)) {
            return null;
        }
        if (obj != null) {
            return obj;
        }
        throw new TypeCastException("null cannot be cast to non-null type T");
    }
}
