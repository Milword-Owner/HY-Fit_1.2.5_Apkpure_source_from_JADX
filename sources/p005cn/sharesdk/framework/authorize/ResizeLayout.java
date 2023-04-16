package p005cn.sharesdk.framework.authorize;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/* renamed from: cn.sharesdk.framework.authorize.ResizeLayout */
public class ResizeLayout extends LinearLayout {

    /* renamed from: a */
    private OnResizeListener f180a;

    /* renamed from: cn.sharesdk.framework.authorize.ResizeLayout$OnResizeListener */
    public interface OnResizeListener {
        void OnResize(int i, int i2, int i3, int i4);
    }

    /* renamed from: a */
    public void mo10533a(OnResizeListener onResizeListener) {
        this.f180a = onResizeListener;
    }

    public ResizeLayout(Context context) {
        super(context);
    }

    public ResizeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        OnResizeListener onResizeListener = this.f180a;
        if (onResizeListener != null) {
            onResizeListener.OnResize(i, i2, i3, i4);
        }
    }
}
