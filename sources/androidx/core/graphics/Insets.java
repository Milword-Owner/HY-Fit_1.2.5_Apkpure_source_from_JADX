package androidx.core.graphics;

import android.graphics.Rect;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;

public final class Insets {
    @NonNull
    public static final Insets NONE = new Insets(0, 0, 0, 0);
    public final int bottom;
    public final int left;
    public final int right;

    /* renamed from: top  reason: collision with root package name */
    public final int f2821top;

    private Insets(int i, int i2, int i3, int i4) {
        this.left = i;
        this.f2821top = i2;
        this.right = i3;
        this.bottom = i4;
    }

    @NonNull
    /* renamed from: of */
    public static Insets m4of(int i, int i2, int i3, int i4) {
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return NONE;
        }
        return new Insets(i, i2, i3, i4);
    }

    @NonNull
    /* renamed from: of */
    public static Insets m5of(@NonNull Rect rect) {
        return m4of(rect.left, rect.top, rect.right, rect.bottom);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Insets insets = (Insets) obj;
        return this.bottom == insets.bottom && this.left == insets.left && this.right == insets.right && this.f2821top == insets.f2821top;
    }

    public int hashCode() {
        return (((((this.left * 31) + this.f2821top) * 31) + this.right) * 31) + this.bottom;
    }

    public String toString() {
        return "Insets{left=" + this.left + ", top=" + this.f2821top + ", right=" + this.right + ", bottom=" + this.bottom + '}';
    }

    @RequiresApi(api = 29)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    @NonNull
    @Deprecated
    public static Insets wrap(@NonNull android.graphics.Insets insets) {
        return toCompatInsets(insets);
    }

    @RequiresApi(api = 29)
    @NonNull
    public static Insets toCompatInsets(@NonNull android.graphics.Insets insets) {
        return m4of(insets.left, insets.top, insets.right, insets.bottom);
    }

    @RequiresApi(api = 29)
    @NonNull
    public android.graphics.Insets toPlatformInsets() {
        return android.graphics.Insets.of(this.left, this.f2821top, this.right, this.bottom);
    }
}
