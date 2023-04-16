package org.litepal.extension;

import androidx.exifinterface.media.ExifInterface;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.litepal.FluentQuery;
import org.litepal.crud.async.FindExecutor;
import org.litepal.crud.async.FindMultiExecutor;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u00006\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u001d\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\b\u001a\u0015\u0010\u0006\u001a\u00020\u0007\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b\u001a\u001b\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b\u001a#\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00020\t\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\b\u001a9\u0010\f\u001a&\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\u0002H\u0002 \u000e*\u0012\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010\r0\r\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b\u001aA\u0010\f\u001a&\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\u0002H\u0002 \u000e*\u0012\u0012\f\u0012\n \u000e*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010\r0\r\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\b\u001a\u001c\u0010\u000f\u001a\u0004\u0018\u0001H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b¢\u0006\u0002\u0010\u0010\u001a$\u0010\u000f\u001a\u0004\u0018\u0001H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\b¢\u0006\u0002\u0010\u0011\u001a\u001b\u0010\u0012\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0013\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b\u001a\u001c\u0010\u0014\u001a\u0004\u0018\u0001H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b¢\u0006\u0002\u0010\u0010\u001a$\u0010\u0014\u001a\u0004\u0018\u0001H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\b¢\u0006\u0002\u0010\u0011\u001a*\u0010\u0015\u001a\u0002H\u0016\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001*\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0005H\b¢\u0006\u0002\u0010\u0018\u001a*\u0010\u0015\u001a\u0002H\u0016\"\u0006\b\u0000\u0010\u0016\u0018\u0001*\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\b¢\u0006\u0002\u0010\u001a\u001a*\u0010\u001b\u001a\u0002H\u0016\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001*\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0005H\b¢\u0006\u0002\u0010\u0018\u001a*\u0010\u001b\u001a\u0002H\u0016\"\u0006\b\u0000\u0010\u0016\u0018\u0001*\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\b¢\u0006\u0002\u0010\u001a\u001a*\u0010\u001c\u001a\u0002H\u0016\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010\u0016\u0018\u0001*\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0005H\b¢\u0006\u0002\u0010\u0018\u001a*\u0010\u001c\u001a\u0002H\u0016\"\u0006\b\u0000\u0010\u0016\u0018\u0001*\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\b¢\u0006\u0002\u0010\u001a¨\u0006\u001d"}, mo21895d2 = {"average", "", "T", "Lorg/litepal/FluentQuery;", "column", "", "count", "", "find", "", "isEager", "", "findAsync", "Lorg/litepal/crud/async/FindMultiExecutor;", "kotlin.jvm.PlatformType", "findFirst", "(Lorg/litepal/FluentQuery;)Ljava/lang/Object;", "(Lorg/litepal/FluentQuery;Z)Ljava/lang/Object;", "findFirstAsync", "Lorg/litepal/crud/async/FindExecutor;", "findLast", "max", "R", "columnName", "(Lorg/litepal/FluentQuery;Ljava/lang/String;)Ljava/lang/Object;", "tableName", "(Lorg/litepal/FluentQuery;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", "min", "sum", "core_release"}, mo21896k = 2, mo21897mv = {1, 1, 16})
/* compiled from: FluentQuery.kt */
public final class FluentQueryKt {
    @NotNull
    public static final /* synthetic */ <T> List<T> find(@NotNull FluentQuery fluentQuery) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$find");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        List<T> find = fluentQuery.find(Object.class);
        Intrinsics.checkExpressionValueIsNotNull(find, "find(T::class.java)");
        return find;
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> FindMultiExecutor<T> findAsync(@NotNull FluentQuery fluentQuery) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$findAsync");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return fluentQuery.findAsync(Object.class);
    }

    @NotNull
    public static final /* synthetic */ <T> List<T> find(@NotNull FluentQuery fluentQuery, boolean z) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$find");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        List<T> find = fluentQuery.find(Object.class, z);
        Intrinsics.checkExpressionValueIsNotNull(find, "find(T::class.java, isEager)");
        return find;
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> FindMultiExecutor<T> findAsync(@NotNull FluentQuery fluentQuery, boolean z) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$findAsync");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return fluentQuery.findAsync(Object.class, z);
    }

    @Nullable
    public static final /* synthetic */ <T> T findFirst(@NotNull FluentQuery fluentQuery) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$findFirst");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return fluentQuery.findFirst(Object.class);
    }

    @NotNull
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> FindExecutor<T> findFirstAsync(@NotNull FluentQuery fluentQuery) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$findFirstAsync");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        FindExecutor<T> findFirstAsync = fluentQuery.findFirstAsync(Object.class);
        Intrinsics.checkExpressionValueIsNotNull(findFirstAsync, "findFirstAsync(T::class.java)");
        return findFirstAsync;
    }

    @Nullable
    public static final /* synthetic */ <T> T findFirst(@NotNull FluentQuery fluentQuery, boolean z) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$findFirst");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return fluentQuery.findFirst(Object.class, z);
    }

    @Nullable
    public static final /* synthetic */ <T> T findLast(@NotNull FluentQuery fluentQuery) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$findLast");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return fluentQuery.findLast(Object.class);
    }

    @Nullable
    public static final /* synthetic */ <T> T findLast(@NotNull FluentQuery fluentQuery, boolean z) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$findLast");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return fluentQuery.findLast(Object.class, z);
    }

    public static final /* synthetic */ <T> int count(@NotNull FluentQuery fluentQuery) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$count");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return fluentQuery.count((Class<?>) Object.class);
    }

    public static final /* synthetic */ <T> double average(@NotNull FluentQuery fluentQuery, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$average");
        Intrinsics.checkParameterIsNotNull(str, "column");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return fluentQuery.average((Class<?>) Object.class, str);
    }

    public static final /* synthetic */ <T, R> R max(@NotNull FluentQuery fluentQuery, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$max");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intrinsics.reifiedOperationMarker(4, "R");
        return fluentQuery.max((Class<?>) Object.class, str, Object.class);
    }

    public static final /* synthetic */ <R> R max(@NotNull FluentQuery fluentQuery, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$max");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.reifiedOperationMarker(4, "R");
        return fluentQuery.max(str, str2, Object.class);
    }

    public static final /* synthetic */ <T, R> R min(@NotNull FluentQuery fluentQuery, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$min");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intrinsics.reifiedOperationMarker(4, "R");
        return fluentQuery.min((Class<?>) Object.class, str, Object.class);
    }

    public static final /* synthetic */ <R> R min(@NotNull FluentQuery fluentQuery, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$min");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.reifiedOperationMarker(4, "R");
        return fluentQuery.min(str, str2, Object.class);
    }

    public static final /* synthetic */ <T, R> R sum(@NotNull FluentQuery fluentQuery, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$sum");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intrinsics.reifiedOperationMarker(4, "R");
        return fluentQuery.sum((Class<?>) Object.class, str, Object.class);
    }

    public static final /* synthetic */ <R> R sum(@NotNull FluentQuery fluentQuery, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(fluentQuery, "$this$sum");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.reifiedOperationMarker(4, "R");
        return fluentQuery.sum(str, str2, Object.class);
    }
}
