package com.chad.library.adapter.base.viewholder;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.Keep;
import androidx.annotation.StringRes;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\b\b\u0017\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0007\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\tH\u0017¢\u0006\u0002\u0010\nJ!\u0010\u000b\u001a\u0002H\f\"\b\b\u0000\u0010\f*\u00020\u00032\b\b\u0001\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u000fJ#\u0010\u0010\u001a\u0004\u0018\u0001H\f\"\b\b\u0000\u0010\f*\u00020\u00032\b\b\u0001\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0002\u0010\u000fJ\u001c\u0010\u0011\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u0012\u001a\u00020\u000eH\u0016J\u001c\u0010\u0013\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u0014\u001a\u00020\u000eH\u0016J\u001a\u0010\u0015\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u001a\u0010\u0018\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J\u001c\u0010\u001a\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u001c\u0010\u001d\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u001c\u0010 \u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010!\u001a\u00020\u000eH\u0016J\u001c\u0010\"\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u001e\u0010\"\u001a\u0004\u0018\u00010\u00002\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010%\u001a\u00020\u000eH\u0016J\u001c\u0010&\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010\u0012\u001a\u00020\u000eH\u0016J\u001c\u0010'\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\u000e2\b\b\u0001\u0010(\u001a\u00020\u000eH\u0016J\u001a\u0010)\u001a\u00020\u00002\b\b\u0001\u0010\r\u001a\u00020\u000e2\u0006\u0010*\u001a\u00020\u0017H\u0016J\u001d\u0010+\u001a\u0004\u0018\u0001H\f\"\b\b\u0000\u0010\f*\u00020\u0003*\u00020\u000eH\u0016¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, mo21895d2 = {"Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "views", "Landroid/util/SparseArray;", "getBinding", "B", "Landroidx/databinding/ViewDataBinding;", "()Landroidx/databinding/ViewDataBinding;", "getView", "T", "viewId", "", "(I)Landroid/view/View;", "getViewOrNull", "setBackgroundColor", "color", "setBackgroundResource", "backgroundRes", "setEnabled", "isEnabled", "", "setGone", "isGone", "setImageBitmap", "bitmap", "Landroid/graphics/Bitmap;", "setImageDrawable", "drawable", "Landroid/graphics/drawable/Drawable;", "setImageResource", "imageResId", "setText", "value", "", "strId", "setTextColor", "setTextColorRes", "colorRes", "setVisible", "isVisible", "findView", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
@Keep
/* compiled from: BaseViewHolder.kt */
public class BaseViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> views = new SparseArray<>();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BaseViewHolder(@NotNull View view) {
        super(view);
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
    }

    @Deprecated(message = "Please use BaseDataBindingHolder class", replaceWith = @ReplaceWith(expression = "DataBindingUtil.getBinding(itemView)", imports = {"androidx.databinding.DataBindingUtil"}))
    @Nullable
    public <B extends ViewDataBinding> B getBinding() {
        return DataBindingUtil.getBinding(this.itemView);
    }

    @NotNull
    public <T extends View> T getView(@IdRes int i) {
        T viewOrNull = getViewOrNull(i);
        if (viewOrNull != null) {
            return viewOrNull;
        }
        throw new IllegalStateException(("No view found with id " + i).toString());
    }

    @Nullable
    public <T extends View> T getViewOrNull(@IdRes int i) {
        T findViewById;
        T t = (View) this.views.get(i);
        if (t == null && (findViewById = this.itemView.findViewById(i)) != null) {
            this.views.put(i, findViewById);
            return findViewById;
        } else if (!(t instanceof View)) {
            return null;
        } else {
            return t;
        }
    }

    @Nullable
    public <T extends View> T findView(int i) {
        return this.itemView.findViewById(i);
    }

    @NotNull
    public BaseViewHolder setText(@IdRes int i, @Nullable CharSequence charSequence) {
        ((TextView) getView(i)).setText(charSequence);
        return this;
    }

    @Nullable
    public BaseViewHolder setText(@IdRes int i, @StringRes int i2) {
        ((TextView) getView(i)).setText(i2);
        return this;
    }

    @NotNull
    public BaseViewHolder setTextColor(@IdRes int i, @ColorInt int i2) {
        ((TextView) getView(i)).setTextColor(i2);
        return this;
    }

    @NotNull
    public BaseViewHolder setTextColorRes(@IdRes int i, @ColorRes int i2) {
        View view = this.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "itemView");
        ((TextView) getView(i)).setTextColor(view.getResources().getColor(i2));
        return this;
    }

    @NotNull
    public BaseViewHolder setImageResource(@IdRes int i, @DrawableRes int i2) {
        ((ImageView) getView(i)).setImageResource(i2);
        return this;
    }

    @NotNull
    public BaseViewHolder setImageDrawable(@IdRes int i, @Nullable Drawable drawable) {
        ((ImageView) getView(i)).setImageDrawable(drawable);
        return this;
    }

    @NotNull
    public BaseViewHolder setImageBitmap(@IdRes int i, @Nullable Bitmap bitmap) {
        ((ImageView) getView(i)).setImageBitmap(bitmap);
        return this;
    }

    @NotNull
    public BaseViewHolder setBackgroundColor(@IdRes int i, @ColorInt int i2) {
        getView(i).setBackgroundColor(i2);
        return this;
    }

    @NotNull
    public BaseViewHolder setBackgroundResource(@IdRes int i, @DrawableRes int i2) {
        getView(i).setBackgroundResource(i2);
        return this;
    }

    @NotNull
    public BaseViewHolder setVisible(@IdRes int i, boolean z) {
        getView(i).setVisibility(z ? 0 : 4);
        return this;
    }

    @NotNull
    public BaseViewHolder setGone(@IdRes int i, boolean z) {
        getView(i).setVisibility(z ? 8 : 0);
        return this;
    }

    @NotNull
    public BaseViewHolder setEnabled(@IdRes int i, boolean z) {
        getView(i).setEnabled(z);
        return this;
    }
}
