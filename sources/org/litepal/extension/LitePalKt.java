package org.litepal.extension;

import android.content.ContentValues;
import androidx.exifinterface.media.ExifInterface;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.crud.async.AverageExecutor;
import org.litepal.crud.async.CountExecutor;
import org.litepal.crud.async.FindExecutor;
import org.litepal.crud.async.FindMultiExecutor;
import org.litepal.crud.async.UpdateOrDeleteExecutor;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0001\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u001d\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\b\u001a%\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\b\u001a\u0015\u0010\t\u001a\u00020\n\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b\u001a\u001d\u0010\u000b\u001a\n \b*\u0004\u0018\u00010\f0\f\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b\u001a\u001d\u0010\r\u001a\u00020\n\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\b\u001a2\u0010\u0010\u001a\u00020\n\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0012\"\u0004\u0018\u00010\u0005H\b¢\u0006\u0002\u0010\u0013\u001a:\u0010\u0014\u001a\n \b*\u0004\u0018\u00010\u00150\u0015\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0012\"\u0004\u0018\u00010\u0005H\b¢\u0006\u0002\u0010\u0016\u001a%\u0010\u0017\u001a\n \b*\u0004\u0018\u00010\u00150\u0015\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\b\u001a$\u0010\u0018\u001a\u0004\u0018\u0001H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\b¢\u0006\u0002\u0010\u0019\u001a2\u0010\u0018\u001a\n \b*\u0004\u0018\u0001H\u0002H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\b¢\u0006\u0002\u0010\u001c\u001aM\u0010\u001d\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010\u001f0\u001e\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001b2\n\u0010 \u001a\u00020!\"\u00020\u000fH\b\u001aE\u0010\u001d\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010\u001f0\u001e\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\n\u0010 \u001a\u00020!\"\u00020\u000fH\b\u001aM\u0010\"\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010#0#\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001b2\n\u0010 \u001a\u00020!\"\u00020\u000fH\b\u001aE\u0010\"\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010#0#\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\n\u0010 \u001a\u00020!\"\u00020\u000fH\b\u001aA\u0010$\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010%0%\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\b\u001a2\u0010$\u001a\n \b*\u0004\u0018\u0001H\u0002H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u001bH\b¢\u0006\u0002\u0010\u001c\u001a\"\u0010&\u001a\n \b*\u0004\u0018\u0001H\u0002H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b¢\u0006\u0002\u0010'\u001a*\u0010&\u001a\n \b*\u0004\u0018\u0001H\u0002H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\b¢\u0006\u0002\u0010(\u001a9\u0010)\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010%0%\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b\u001aA\u0010)\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010%0%\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\b\u001a\"\u0010*\u001a\n \b*\u0004\u0018\u0001H\u0002H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b¢\u0006\u0002\u0010'\u001a*\u0010*\u001a\n \b*\u0004\u0018\u0001H\u0002H\u0002\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\b¢\u0006\u0002\u0010(\u001a9\u0010+\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010%0%\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\b\u001aA\u0010+\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002 \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H\u0002H\u0002\u0018\u00010%0%\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\b\u001a2\u0010,\u001a\u00020\u001b\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0012\"\u0004\u0018\u00010\u0005H\b¢\u0006\u0002\u0010-\u001a2\u0010.\u001a\n \b*\u0004\u0018\u0001H/H/\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010/\u0018\u0001*\u00020\u00032\u0006\u00100\u001a\u00020\u0005H\b¢\u0006\u0002\u00101\u001a2\u0010.\u001a\n \b*\u0004\u0018\u0001H/H/\"\u0006\b\u0000\u0010/\u0018\u0001*\u00020\u00032\u0006\u00102\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005H\b¢\u0006\u0002\u00103\u001aI\u00104\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H/H/ \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H/H/\u0018\u00010%0%\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010/\u0018\u0001*\u00020\u00032\u0006\u00100\u001a\u00020\u0005H\b\u001aI\u00104\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H/H/ \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H/H/\u0018\u00010%0%\"\u0006\b\u0000\u0010/\u0018\u0001*\u00020\u00032\u0006\u00102\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005H\b\u001a2\u00105\u001a\n \b*\u0004\u0018\u0001H/H/\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010/\u0018\u0001*\u00020\u00032\u0006\u00100\u001a\u00020\u0005H\b¢\u0006\u0002\u00101\u001a2\u00105\u001a\n \b*\u0004\u0018\u0001H/H/\"\u0006\b\u0000\u0010/\u0018\u0001*\u00020\u00032\u0006\u00102\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005H\b¢\u0006\u0002\u00103\u001aI\u00106\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H/H/ \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H/H/\u0018\u00010%0%\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010/\u0018\u0001*\u00020\u00032\u0006\u00100\u001a\u00020\u0005H\b\u001aI\u00106\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H/H/ \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H/H/\u0018\u00010%0%\"\u0006\b\u0000\u0010/\u0018\u0001*\u00020\u00032\u0006\u00102\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005H\b\u001a\u0018\u00107\u001a\u00020\u001b*\u00020\u00032\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u001b09\u001a\u001a\u0010:\u001a\u00020\u001b\"\b\b\u0000\u0010\u0002*\u00020;*\b\u0012\u0004\u0012\u0002H\u00020<\u001a2\u0010=\u001a\n \b*\u0004\u0018\u0001H/H/\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010/\u0018\u0001*\u00020\u00032\u0006\u00100\u001a\u00020\u0005H\b¢\u0006\u0002\u00101\u001a2\u0010=\u001a\n \b*\u0004\u0018\u0001H/H/\"\u0006\b\u0000\u0010/\u0018\u0001*\u00020\u00032\u0006\u00102\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005H\b¢\u0006\u0002\u00103\u001aI\u0010>\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H/H/ \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H/H/\u0018\u00010%0%\"\u0006\b\u0000\u0010\u0002\u0018\u0001\"\u0006\b\u0001\u0010/\u0018\u0001*\u00020\u00032\u0006\u00100\u001a\u00020\u0005H\b\u001aI\u0010>\u001a&\u0012\f\u0012\n \b*\u0004\u0018\u0001H/H/ \b*\u0012\u0012\f\u0012\n \b*\u0004\u0018\u0001H/H/\u0018\u00010%0%\"\u0006\b\u0000\u0010/\u0018\u0001*\u00020\u00032\u0006\u00102\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005H\b\u001a%\u0010?\u001a\u00020\n\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010@\u001a\u00020A2\u0006\u0010\u000e\u001a\u00020\u000fH\b\u001a:\u0010B\u001a\u00020\n\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010@\u001a\u00020A2\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0012\"\u0004\u0018\u00010\u0005H\b¢\u0006\u0002\u0010C\u001aB\u0010D\u001a\n \b*\u0004\u0018\u00010\u00150\u0015\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010@\u001a\u00020A2\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0012\"\u0004\u0018\u00010\u0005H\b¢\u0006\u0002\u0010E\u001a-\u0010F\u001a\n \b*\u0004\u0018\u00010\u00150\u0015\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u00032\u0006\u0010@\u001a\u00020A2\u0006\u0010\u000e\u001a\u00020\u000fH\b¨\u0006G"}, mo21895d2 = {"average", "", "T", "Lorg/litepal/LitePal;", "column", "", "averageAsync", "Lorg/litepal/crud/async/AverageExecutor;", "kotlin.jvm.PlatformType", "count", "", "countAsync", "Lorg/litepal/crud/async/CountExecutor;", "delete", "id", "", "deleteAll", "conditions", "", "(Lorg/litepal/LitePal;[Ljava/lang/String;)I", "deleteAllAsync", "Lorg/litepal/crud/async/UpdateOrDeleteExecutor;", "(Lorg/litepal/LitePal;[Ljava/lang/String;)Lorg/litepal/crud/async/UpdateOrDeleteExecutor;", "deleteAsync", "find", "(Lorg/litepal/LitePal;J)Ljava/lang/Object;", "isEager", "", "(Lorg/litepal/LitePal;JZ)Ljava/lang/Object;", "findAll", "", "", "ids", "", "findAllAsync", "Lorg/litepal/crud/async/FindMultiExecutor;", "findAsync", "Lorg/litepal/crud/async/FindExecutor;", "findFirst", "(Lorg/litepal/LitePal;)Ljava/lang/Object;", "(Lorg/litepal/LitePal;Z)Ljava/lang/Object;", "findFirstAsync", "findLast", "findLastAsync", "isExist", "(Lorg/litepal/LitePal;[Ljava/lang/String;)Z", "max", "R", "columnName", "(Lorg/litepal/LitePal;Ljava/lang/String;)Ljava/lang/Object;", "tableName", "(Lorg/litepal/LitePal;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;", "maxAsync", "min", "minAsync", "runInTransaction", "block", "Lkotlin/Function0;", "saveAll", "Lorg/litepal/crud/LitePalSupport;", "", "sum", "sumAsync", "update", "values", "Landroid/content/ContentValues;", "updateAll", "(Lorg/litepal/LitePal;Landroid/content/ContentValues;[Ljava/lang/String;)I", "updateAllAsync", "(Lorg/litepal/LitePal;Landroid/content/ContentValues;[Ljava/lang/String;)Lorg/litepal/crud/async/UpdateOrDeleteExecutor;", "updateAsync", "core_release"}, mo21896k = 2, mo21897mv = {1, 1, 16})
/* compiled from: LitePal.kt */
public final class LitePalKt {
    public static final /* synthetic */ <T> int count(@NotNull LitePal litePal) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$count");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.count((Class<?>) Object.class);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> CountExecutor countAsync(@NotNull LitePal litePal) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$countAsync");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.countAsync((Class<?>) Object.class);
    }

    public static final /* synthetic */ <T> double average(@NotNull LitePal litePal, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$average");
        Intrinsics.checkParameterIsNotNull(str, "column");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.average((Class<?>) Object.class, str);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> AverageExecutor averageAsync(@NotNull LitePal litePal, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$averageAsync");
        Intrinsics.checkParameterIsNotNull(str, "column");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.averageAsync((Class<?>) Object.class, str);
    }

    public static final /* synthetic */ <T, R> R max(@NotNull LitePal litePal, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$max");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intrinsics.reifiedOperationMarker(4, "R");
        return LitePal.max((Class<?>) Object.class, str, Object.class);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T, R> FindExecutor<R> maxAsync(@NotNull LitePal litePal, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$maxAsync");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intrinsics.reifiedOperationMarker(4, "R");
        return LitePal.maxAsync((Class<?>) Object.class, str, Object.class);
    }

    public static final /* synthetic */ <R> R max(@NotNull LitePal litePal, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$max");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.reifiedOperationMarker(4, "R");
        return LitePal.max(str, str2, Object.class);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <R> FindExecutor<R> maxAsync(@NotNull LitePal litePal, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$maxAsync");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.reifiedOperationMarker(4, "R");
        return LitePal.maxAsync(str, str2, Object.class);
    }

    public static final /* synthetic */ <T, R> R min(@NotNull LitePal litePal, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$min");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intrinsics.reifiedOperationMarker(4, "R");
        return LitePal.min((Class<?>) Object.class, str, Object.class);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T, R> FindExecutor<R> minAsync(@NotNull LitePal litePal, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$minAsync");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intrinsics.reifiedOperationMarker(4, "R");
        return LitePal.minAsync((Class<?>) Object.class, str, Object.class);
    }

    public static final /* synthetic */ <R> R min(@NotNull LitePal litePal, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$min");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.reifiedOperationMarker(4, "R");
        return LitePal.min(str, str2, Object.class);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <R> FindExecutor<R> minAsync(@NotNull LitePal litePal, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$minAsync");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.reifiedOperationMarker(4, "R");
        return LitePal.minAsync(str, str2, Object.class);
    }

    public static final /* synthetic */ <T, R> R sum(@NotNull LitePal litePal, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$sum");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intrinsics.reifiedOperationMarker(4, "R");
        return LitePal.sum((Class<?>) Object.class, str, Object.class);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T, R> FindExecutor<R> sumAsync(@NotNull LitePal litePal, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$sumAsync");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Intrinsics.reifiedOperationMarker(4, "R");
        return LitePal.sumAsync((Class<?>) Object.class, str, Object.class);
    }

    public static final /* synthetic */ <R> R sum(@NotNull LitePal litePal, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$sum");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.reifiedOperationMarker(4, "R");
        return LitePal.sum(str, str2, Object.class);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <R> FindExecutor<R> sumAsync(@NotNull LitePal litePal, @NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$sumAsync");
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.reifiedOperationMarker(4, "R");
        return LitePal.sumAsync(str, str2, Object.class);
    }

    @Nullable
    public static final /* synthetic */ <T> T find(@NotNull LitePal litePal, long j) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$find");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.find(Object.class, j);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> FindExecutor<T> findAsync(@NotNull LitePal litePal, long j) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findAsync");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.findAsync(Object.class, j);
    }

    public static final /* synthetic */ <T> T find(@NotNull LitePal litePal, long j, boolean z) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$find");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.find(Object.class, j, z);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> T findAsync(@NotNull LitePal litePal, long j, boolean z) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findAsync");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.find(Object.class, j, z);
    }

    public static final /* synthetic */ <T> T findFirst(@NotNull LitePal litePal) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findFirst");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.findFirst(Object.class);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> FindExecutor<T> findFirstAsync(@NotNull LitePal litePal) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findFirstAsync");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.findFirstAsync(Object.class);
    }

    public static final /* synthetic */ <T> T findFirst(@NotNull LitePal litePal, boolean z) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findFirst");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.findFirst(Object.class, z);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> FindExecutor<T> findFirstAsync(@NotNull LitePal litePal, boolean z) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findFirstAsync");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.findFirstAsync(Object.class, z);
    }

    public static final /* synthetic */ <T> T findLast(@NotNull LitePal litePal) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findLast");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.findLast(Object.class);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> FindExecutor<T> findLastAsync(@NotNull LitePal litePal) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findLastAsync");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.findLastAsync(Object.class);
    }

    public static final /* synthetic */ <T> T findLast(@NotNull LitePal litePal, boolean z) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findLast");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.findLast(Object.class, z);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> FindExecutor<T> findLastAsync(@NotNull LitePal litePal, boolean z) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findLastAsync");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.findLastAsync(Object.class, z);
    }

    public static final /* synthetic */ <T> List<T> findAll(@NotNull LitePal litePal, @NotNull long... jArr) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findAll");
        Intrinsics.checkParameterIsNotNull(jArr, "ids");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.findAll(Object.class, Arrays.copyOf(jArr, jArr.length));
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> FindMultiExecutor<T> findAllAsync(@NotNull LitePal litePal, @NotNull long... jArr) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findAllAsync");
        Intrinsics.checkParameterIsNotNull(jArr, "ids");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.findAllAsync(Object.class, Arrays.copyOf(jArr, jArr.length));
    }

    public static final /* synthetic */ <T> List<T> findAll(@NotNull LitePal litePal, boolean z, @NotNull long... jArr) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findAll");
        Intrinsics.checkParameterIsNotNull(jArr, "ids");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.findAll(Object.class, z, Arrays.copyOf(jArr, jArr.length));
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> FindMultiExecutor<T> findAllAsync(@NotNull LitePal litePal, boolean z, @NotNull long... jArr) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$findAllAsync");
        Intrinsics.checkParameterIsNotNull(jArr, "ids");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.findAllAsync(Object.class, z, Arrays.copyOf(jArr, jArr.length));
    }

    public static final /* synthetic */ <T> int delete(@NotNull LitePal litePal, long j) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$delete");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.delete(Object.class, j);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> UpdateOrDeleteExecutor deleteAsync(@NotNull LitePal litePal, long j) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$deleteAsync");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.deleteAsync(Object.class, j);
    }

    public static final /* synthetic */ <T> int deleteAll(@NotNull LitePal litePal, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$deleteAll");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.deleteAll((Class<?>) Object.class, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> UpdateOrDeleteExecutor deleteAllAsync(@NotNull LitePal litePal, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$deleteAllAsync");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.deleteAllAsync((Class<?>) Object.class, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static final /* synthetic */ <T> int update(@NotNull LitePal litePal, @NotNull ContentValues contentValues, long j) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$update");
        Intrinsics.checkParameterIsNotNull(contentValues, "values");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.update(Object.class, contentValues, j);
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> UpdateOrDeleteExecutor updateAsync(@NotNull LitePal litePal, @NotNull ContentValues contentValues, long j) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$updateAsync");
        Intrinsics.checkParameterIsNotNull(contentValues, "values");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.updateAsync(Object.class, contentValues, j);
    }

    public static final /* synthetic */ <T> int updateAll(@NotNull LitePal litePal, @NotNull ContentValues contentValues, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$updateAll");
        Intrinsics.checkParameterIsNotNull(contentValues, "values");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.updateAll((Class<?>) Object.class, contentValues, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final /* synthetic */ <T> UpdateOrDeleteExecutor updateAllAsync(@NotNull LitePal litePal, @NotNull ContentValues contentValues, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$updateAllAsync");
        Intrinsics.checkParameterIsNotNull(contentValues, "values");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.updateAllAsync((Class<?>) Object.class, contentValues, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static final /* synthetic */ <T> boolean isExist(@NotNull LitePal litePal, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(litePal, "$this$isExist");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return LitePal.isExist(Object.class, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    public static final <T extends LitePalSupport> boolean saveAll(@NotNull Collection<? extends T> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "$this$saveAll");
        return LitePal.saveAll(collection);
    }

    public static final synchronized boolean runInTransaction(@NotNull LitePal litePal, @NotNull Function0<Boolean> function0) {
        boolean z;
        synchronized (LitePalKt.class) {
            Intrinsics.checkParameterIsNotNull(litePal, "$this$runInTransaction");
            Intrinsics.checkParameterIsNotNull(function0, "block");
            LitePal.beginTransaction();
            try {
                z = function0.invoke().booleanValue();
            } catch (Exception unused) {
                z = false;
            }
            if (z) {
                LitePal.setTransactionSuccessful();
            }
            LitePal.endTransaction();
        }
        return z;
    }
}
