package androidx.core.util;

import android.util.LongSparseArray;
import androidx.annotation.RequiresApi;
import com.facebook.internal.NativeProtocol;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.LongIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000F\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010(\n\u0000\u001a!\u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\n\u001a!\u0010\n\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\b\u001a&\u0010\u000b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\f\u001a\u0002H\u0002H\b¢\u0006\u0002\u0010\r\u001aQ\u0010\u000e\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000326\u0010\u0010\u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\b\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u000f0\u0011H\b\u001a.\u0010\u0014\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0015\u001a\u0002H\u0002H\b¢\u0006\u0002\u0010\u0016\u001a4\u0010\u0017\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0018H\b¢\u0006\u0002\u0010\u0019\u001a\u0019\u0010\u001a\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\b\u001a\u0019\u0010\u001b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\b\u001a\u0018\u0010\u001c\u001a\u00020\u001d\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a-\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0002\u001a&\u0010 \u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\u001a-\u0010!\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u0002H\u0002H\u0007¢\u0006\u0002\u0010\"\u001a.\u0010#\u001a\u00020\u000f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u0002H\u0002H\n¢\u0006\u0002\u0010$\u001a\u001e\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u00020&\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0007\"\"\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006'"}, mo21895d2 = {"size", "", "T", "Landroid/util/LongSparseArray;", "getSize", "(Landroid/util/LongSparseArray;)I", "contains", "", "key", "", "containsKey", "containsValue", "value", "(Landroid/util/LongSparseArray;Ljava/lang/Object;)Z", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)Ljava/lang/Object;", "getOrElse", "Lkotlin/Function0;", "(Landroid/util/LongSparseArray;JLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/LongIterator;", "plus", "other", "putAll", "remove", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)Z", "set", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)V", "valueIterator", "", "core-ktx_release"}, mo21896k = 2, mo21897mv = {1, 1, 16})
/* compiled from: LongSparseArray.kt */
public final class LongSparseArrayKt {
    @RequiresApi(16)
    public static final <T> int getSize(@NotNull LongSparseArray<T> longSparseArray) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$size");
        return longSparseArray.size();
    }

    @RequiresApi(16)
    public static final <T> boolean contains(@NotNull LongSparseArray<T> longSparseArray, long j) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$contains");
        return longSparseArray.indexOfKey(j) >= 0;
    }

    @RequiresApi(16)
    public static final <T> void set(@NotNull LongSparseArray<T> longSparseArray, long j, T t) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$set");
        longSparseArray.put(j, t);
    }

    @RequiresApi(16)
    @NotNull
    public static final <T> LongSparseArray<T> plus(@NotNull LongSparseArray<T> longSparseArray, @NotNull LongSparseArray<T> longSparseArray2) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$plus");
        Intrinsics.checkParameterIsNotNull(longSparseArray2, "other");
        LongSparseArray<T> longSparseArray3 = new LongSparseArray<>(longSparseArray.size() + longSparseArray2.size());
        putAll(longSparseArray3, longSparseArray);
        putAll(longSparseArray3, longSparseArray2);
        return longSparseArray3;
    }

    @RequiresApi(16)
    public static final <T> boolean containsKey(@NotNull LongSparseArray<T> longSparseArray, long j) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$containsKey");
        return longSparseArray.indexOfKey(j) >= 0;
    }

    @RequiresApi(16)
    public static final <T> boolean containsValue(@NotNull LongSparseArray<T> longSparseArray, T t) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$containsValue");
        return longSparseArray.indexOfValue(t) >= 0;
    }

    @RequiresApi(16)
    public static final <T> T getOrDefault(@NotNull LongSparseArray<T> longSparseArray, long j, T t) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$getOrDefault");
        T t2 = longSparseArray.get(j);
        return t2 != null ? t2 : t;
    }

    @RequiresApi(16)
    public static final <T> T getOrElse(@NotNull LongSparseArray<T> longSparseArray, long j, @NotNull Function0<? extends T> function0) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$getOrElse");
        Intrinsics.checkParameterIsNotNull(function0, "defaultValue");
        T t = longSparseArray.get(j);
        return t != null ? t : function0.invoke();
    }

    @RequiresApi(16)
    public static final <T> boolean isEmpty(@NotNull LongSparseArray<T> longSparseArray) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$isEmpty");
        return longSparseArray.size() == 0;
    }

    @RequiresApi(16)
    public static final <T> boolean isNotEmpty(@NotNull LongSparseArray<T> longSparseArray) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$isNotEmpty");
        return longSparseArray.size() != 0;
    }

    @RequiresApi(16)
    public static final <T> boolean remove(@NotNull LongSparseArray<T> longSparseArray, long j, T t) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$remove");
        int indexOfKey = longSparseArray.indexOfKey(j);
        if (indexOfKey < 0 || !Intrinsics.areEqual((Object) t, (Object) longSparseArray.valueAt(indexOfKey))) {
            return false;
        }
        longSparseArray.removeAt(indexOfKey);
        return true;
    }

    @RequiresApi(16)
    public static final <T> void forEach(@NotNull LongSparseArray<T> longSparseArray, @NotNull Function2<? super Long, ? super T, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$forEach");
        Intrinsics.checkParameterIsNotNull(function2, NativeProtocol.WEB_DIALOG_ACTION);
        int size = longSparseArray.size();
        for (int i = 0; i < size; i++) {
            function2.invoke(Long.valueOf(longSparseArray.keyAt(i)), longSparseArray.valueAt(i));
        }
    }

    @RequiresApi(16)
    @NotNull
    public static final <T> LongIterator keyIterator(@NotNull LongSparseArray<T> longSparseArray) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$keyIterator");
        return new LongSparseArrayKt$keyIterator$1(longSparseArray);
    }

    @RequiresApi(16)
    @NotNull
    public static final <T> Iterator<T> valueIterator(@NotNull LongSparseArray<T> longSparseArray) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$valueIterator");
        return new LongSparseArrayKt$valueIterator$1(longSparseArray);
    }

    @RequiresApi(16)
    public static final <T> void putAll(@NotNull LongSparseArray<T> longSparseArray, @NotNull LongSparseArray<T> longSparseArray2) {
        Intrinsics.checkParameterIsNotNull(longSparseArray, "$this$putAll");
        Intrinsics.checkParameterIsNotNull(longSparseArray2, "other");
        int size = longSparseArray2.size();
        for (int i = 0; i < size; i++) {
            longSparseArray.put(longSparseArray2.keyAt(i), longSparseArray2.valueAt(i));
        }
    }
}
