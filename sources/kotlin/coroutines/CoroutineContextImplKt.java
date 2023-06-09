package kotlin.coroutines;

import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a+\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0007¢\u0006\u0002\u0010\u0005\u001a\u0018\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0007¨\u0006\b"}, mo21895d2 = {"getPolymorphicElement", "E", "Lkotlin/coroutines/CoroutineContext$Element;", "key", "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Element;Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "minusPolymorphicKey", "Lkotlin/coroutines/CoroutineContext;", "kotlin-stdlib"}, mo21896k = 2, mo21897mv = {1, 1, 16})
/* compiled from: CoroutineContextImpl.kt */
public final class CoroutineContextImplKt {
    @SinceKotlin(version = "1.3")
    @Nullable
    @ExperimentalStdlibApi
    public static final <E extends CoroutineContext.Element> E getPolymorphicElement(@NotNull CoroutineContext.Element element, @NotNull CoroutineContext.Key<E> key) {
        Intrinsics.checkParameterIsNotNull(element, "$this$getPolymorphicElement");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (key instanceof AbstractCoroutineContextKey) {
            AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
            if (!abstractCoroutineContextKey.isSubKey$kotlin_stdlib(element.getKey())) {
                return null;
            }
            E tryCast$kotlin_stdlib = abstractCoroutineContextKey.tryCast$kotlin_stdlib(element);
            if (!(tryCast$kotlin_stdlib instanceof CoroutineContext.Element)) {
                return null;
            }
            return tryCast$kotlin_stdlib;
        } else if (element.getKey() == key) {
            return element;
        } else {
            return null;
        }
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    @ExperimentalStdlibApi
    public static final CoroutineContext minusPolymorphicKey(@NotNull CoroutineContext.Element element, @NotNull CoroutineContext.Key<?> key) {
        Intrinsics.checkParameterIsNotNull(element, "$this$minusPolymorphicKey");
        Intrinsics.checkParameterIsNotNull(key, "key");
        if (key instanceof AbstractCoroutineContextKey) {
            AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
            boolean isSubKey$kotlin_stdlib = abstractCoroutineContextKey.isSubKey$kotlin_stdlib(element.getKey());
            Object obj = element;
            if (isSubKey$kotlin_stdlib) {
                CoroutineContext.Element tryCast$kotlin_stdlib = abstractCoroutineContextKey.tryCast$kotlin_stdlib(element);
                obj = element;
                if (tryCast$kotlin_stdlib != null) {
                    obj = EmptyCoroutineContext.INSTANCE;
                }
            }
            return (CoroutineContext) obj;
        }
        CoroutineContext.Key<?> key2 = element.getKey();
        Object obj2 = element;
        if (key2 == key) {
            obj2 = EmptyCoroutineContext.INSTANCE;
        }
        return (CoroutineContext) obj2;
    }
}
