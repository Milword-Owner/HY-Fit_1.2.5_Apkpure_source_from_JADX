package androidx.core.graphics;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import com.baidu.mobstat.Config;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\f\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\f\u001a\r\u0010\u0004\u001a\u00020\u0005*\u00020\u0001H\n\u001a\r\u0010\u0004\u001a\u00020\u0006*\u00020\u0003H\n\u001a\r\u0010\u0007\u001a\u00020\u0005*\u00020\u0001H\n\u001a\r\u0010\u0007\u001a\u00020\u0006*\u00020\u0003H\n\u001a\r\u0010\b\u001a\u00020\u0005*\u00020\u0001H\n\u001a\r\u0010\b\u001a\u00020\u0006*\u00020\u0003H\n\u001a\r\u0010\t\u001a\u00020\u0005*\u00020\u0001H\n\u001a\r\u0010\t\u001a\u00020\u0006*\u00020\u0003H\n\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00012\u0006\u0010\f\u001a\u00020\rH\n\u001a\u0015\u0010\n\u001a\u00020\u000b*\u00020\u00032\u0006\u0010\f\u001a\u00020\u000eH\n\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\rH\n\u001a\u0015\u0010\u000f\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u000f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0005H\n\u001a\u0015\u0010\u000f\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\n\u001a\u0015\u0010\u000f\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\u0015\u0010\u000f\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0006H\n\u001a\u0015\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\f\u001a\u0015\u0010\u0012\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\f\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\rH\n\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0005H\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u000eH\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\u0015\u0010\u0013\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0006H\n\u001a\u0015\u0010\u0014\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u0005H\n\u001a\u0015\u0010\u0014\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0006H\n\u001a\u0015\u0010\u0014\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0005H\n\u001a\r\u0010\u0016\u001a\u00020\u0001*\u00020\u0003H\b\u001a\r\u0010\u0017\u001a\u00020\u0003*\u00020\u0001H\b\u001a\r\u0010\u0018\u001a\u00020\u0011*\u00020\u0001H\b\u001a\r\u0010\u0018\u001a\u00020\u0011*\u00020\u0003H\b\u001a\u0015\u0010\u0019\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH\b\u001a\u0015\u0010\u001c\u001a\u00020\u0011*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\f\u001a\u0015\u0010\u001c\u001a\u00020\u0011*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\f¨\u0006\u001d"}, mo21895d2 = {"and", "Landroid/graphics/Rect;", "r", "Landroid/graphics/RectF;", "component1", "", "", "component2", "component3", "component4", "contains", "", "p", "Landroid/graphics/Point;", "Landroid/graphics/PointF;", "minus", "xy", "Landroid/graphics/Region;", "or", "plus", "times", "factor", "toRect", "toRectF", "toRegion", "transform", "m", "Landroid/graphics/Matrix;", "xor", "core-ktx_release"}, mo21896k = 2, mo21897mv = {1, 1, 16})
/* compiled from: Rect.kt */
public final class RectKt {
    public static final int component1(@NotNull Rect rect) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$component1");
        return rect.left;
    }

    public static final int component2(@NotNull Rect rect) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$component2");
        return rect.top;
    }

    public static final int component3(@NotNull Rect rect) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$component3");
        return rect.right;
    }

    public static final int component4(@NotNull Rect rect) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$component4");
        return rect.bottom;
    }

    public static final float component1(@NotNull RectF rectF) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$component1");
        return rectF.left;
    }

    public static final float component2(@NotNull RectF rectF) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$component2");
        return rectF.top;
    }

    public static final float component3(@NotNull RectF rectF) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$component3");
        return rectF.right;
    }

    public static final float component4(@NotNull RectF rectF) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$component4");
        return rectF.bottom;
    }

    @NotNull
    public static final Rect plus(@NotNull Rect rect, @NotNull Rect rect2) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$plus");
        Intrinsics.checkParameterIsNotNull(rect2, "r");
        Rect rect3 = new Rect(rect);
        rect3.union(rect2);
        return rect3;
    }

    @NotNull
    public static final RectF plus(@NotNull RectF rectF, @NotNull RectF rectF2) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$plus");
        Intrinsics.checkParameterIsNotNull(rectF2, "r");
        RectF rectF3 = new RectF(rectF);
        rectF3.union(rectF2);
        return rectF3;
    }

    @NotNull
    public static final Rect plus(@NotNull Rect rect, int i) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$plus");
        Rect rect2 = new Rect(rect);
        rect2.offset(i, i);
        return rect2;
    }

    @NotNull
    public static final RectF plus(@NotNull RectF rectF, float f) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$plus");
        RectF rectF2 = new RectF(rectF);
        rectF2.offset(f, f);
        return rectF2;
    }

    @NotNull
    public static final Rect plus(@NotNull Rect rect, @NotNull Point point) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$plus");
        Intrinsics.checkParameterIsNotNull(point, "xy");
        Rect rect2 = new Rect(rect);
        rect2.offset(point.x, point.y);
        return rect2;
    }

    @NotNull
    public static final RectF plus(@NotNull RectF rectF, @NotNull PointF pointF) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$plus");
        Intrinsics.checkParameterIsNotNull(pointF, "xy");
        RectF rectF2 = new RectF(rectF);
        rectF2.offset(pointF.x, pointF.y);
        return rectF2;
    }

    @NotNull
    public static final Region minus(@NotNull Rect rect, @NotNull Rect rect2) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$minus");
        Intrinsics.checkParameterIsNotNull(rect2, "r");
        Region region = new Region(rect);
        region.op(rect2, Region.Op.DIFFERENCE);
        return region;
    }

    @NotNull
    public static final Rect minus(@NotNull Rect rect, int i) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$minus");
        Rect rect2 = new Rect(rect);
        int i2 = -i;
        rect2.offset(i2, i2);
        return rect2;
    }

    @NotNull
    public static final RectF minus(@NotNull RectF rectF, float f) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$minus");
        RectF rectF2 = new RectF(rectF);
        float f2 = -f;
        rectF2.offset(f2, f2);
        return rectF2;
    }

    @NotNull
    public static final Rect minus(@NotNull Rect rect, @NotNull Point point) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$minus");
        Intrinsics.checkParameterIsNotNull(point, "xy");
        Rect rect2 = new Rect(rect);
        rect2.offset(-point.x, -point.y);
        return rect2;
    }

    @NotNull
    public static final RectF minus(@NotNull RectF rectF, @NotNull PointF pointF) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$minus");
        Intrinsics.checkParameterIsNotNull(pointF, "xy");
        RectF rectF2 = new RectF(rectF);
        rectF2.offset(-pointF.x, -pointF.y);
        return rectF2;
    }

    @NotNull
    public static final Rect times(@NotNull Rect rect, int i) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$times");
        Rect rect2 = new Rect(rect);
        rect2.top *= i;
        rect2.left *= i;
        rect2.right *= i;
        rect2.bottom *= i;
        return rect2;
    }

    @NotNull
    public static final RectF times(@NotNull RectF rectF, float f) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$times");
        RectF rectF2 = new RectF(rectF);
        rectF2.top *= f;
        rectF2.left *= f;
        rectF2.right *= f;
        rectF2.bottom *= f;
        return rectF2;
    }

    @NotNull
    @SuppressLint({"CheckResult"})
    public static final Rect and(@NotNull Rect rect, @NotNull Rect rect2) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$and");
        Intrinsics.checkParameterIsNotNull(rect2, "r");
        Rect rect3 = new Rect(rect);
        rect3.intersect(rect2);
        return rect3;
    }

    @NotNull
    @SuppressLint({"CheckResult"})
    public static final RectF and(@NotNull RectF rectF, @NotNull RectF rectF2) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$and");
        Intrinsics.checkParameterIsNotNull(rectF2, "r");
        RectF rectF3 = new RectF(rectF);
        rectF3.intersect(rectF2);
        return rectF3;
    }

    @NotNull
    public static final Region xor(@NotNull Rect rect, @NotNull Rect rect2) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$xor");
        Intrinsics.checkParameterIsNotNull(rect2, "r");
        Region region = new Region(rect);
        region.op(rect2, Region.Op.XOR);
        return region;
    }

    public static final boolean contains(@NotNull Rect rect, @NotNull Point point) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$contains");
        Intrinsics.checkParameterIsNotNull(point, "p");
        return rect.contains(point.x, point.y);
    }

    public static final boolean contains(@NotNull RectF rectF, @NotNull PointF pointF) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$contains");
        Intrinsics.checkParameterIsNotNull(pointF, "p");
        return rectF.contains(pointF.x, pointF.y);
    }

    @NotNull
    public static final RectF toRectF(@NotNull Rect rect) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$toRectF");
        return new RectF(rect);
    }

    @NotNull
    public static final Rect toRect(@NotNull RectF rectF) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$toRect");
        Rect rect = new Rect();
        rectF.roundOut(rect);
        return rect;
    }

    @NotNull
    public static final Region toRegion(@NotNull Rect rect) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$toRegion");
        return new Region(rect);
    }

    @NotNull
    public static final RectF transform(@NotNull RectF rectF, @NotNull Matrix matrix) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$transform");
        Intrinsics.checkParameterIsNotNull(matrix, Config.MODEL);
        matrix.mapRect(rectF);
        return rectF;
    }

    @NotNull
    public static final Region minus(@NotNull RectF rectF, @NotNull RectF rectF2) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$minus");
        Intrinsics.checkParameterIsNotNull(rectF2, "r");
        Rect rect = new Rect();
        rectF.roundOut(rect);
        Region region = new Region(rect);
        Rect rect2 = new Rect();
        rectF2.roundOut(rect2);
        region.op(rect2, Region.Op.DIFFERENCE);
        return region;
    }

    @NotNull
    public static final RectF times(@NotNull RectF rectF, int i) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$times");
        float f = (float) i;
        RectF rectF2 = new RectF(rectF);
        rectF2.top *= f;
        rectF2.left *= f;
        rectF2.right *= f;
        rectF2.bottom *= f;
        return rectF2;
    }

    @NotNull
    /* renamed from: or */
    public static final Rect m7or(@NotNull Rect rect, @NotNull Rect rect2) {
        Intrinsics.checkParameterIsNotNull(rect, "$this$or");
        Intrinsics.checkParameterIsNotNull(rect2, "r");
        Rect rect3 = new Rect(rect);
        rect3.union(rect2);
        return rect3;
    }

    @NotNull
    /* renamed from: or */
    public static final RectF m8or(@NotNull RectF rectF, @NotNull RectF rectF2) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$or");
        Intrinsics.checkParameterIsNotNull(rectF2, "r");
        RectF rectF3 = new RectF(rectF);
        rectF3.union(rectF2);
        return rectF3;
    }

    @NotNull
    public static final Region xor(@NotNull RectF rectF, @NotNull RectF rectF2) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$xor");
        Intrinsics.checkParameterIsNotNull(rectF2, "r");
        Rect rect = new Rect();
        rectF.roundOut(rect);
        Region region = new Region(rect);
        Rect rect2 = new Rect();
        rectF2.roundOut(rect2);
        region.op(rect2, Region.Op.XOR);
        return region;
    }

    @NotNull
    public static final Region toRegion(@NotNull RectF rectF) {
        Intrinsics.checkParameterIsNotNull(rectF, "$this$toRegion");
        Rect rect = new Rect();
        rectF.roundOut(rect);
        return new Region(rect);
    }
}
