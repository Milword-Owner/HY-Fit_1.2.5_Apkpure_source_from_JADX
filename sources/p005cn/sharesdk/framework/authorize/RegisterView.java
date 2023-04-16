package p005cn.sharesdk.framework.authorize;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mob.tools.utils.ResHelper;
import java.lang.reflect.Method;
import p005cn.sharesdk.framework.TitleLayout;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.authorize.RegisterView */
public class RegisterView extends ResizeLayout {

    /* renamed from: a */
    private TitleLayout f173a;

    /* renamed from: b */
    private RelativeLayout f174b;

    /* renamed from: c */
    private WebView f175c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public TextView f176d;

    public RegisterView(Context context) {
        super(context);
        m131a(context);
    }

    public RegisterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m131a(context);
    }

    /* renamed from: a */
    private void m131a(Context context) {
        setBackgroundColor(-1);
        setOrientation(1);
        final int b = m132b(context);
        this.f173a = new TitleLayout(context);
        try {
            int bitmapRes = ResHelper.getBitmapRes(context, "ssdk_auth_title_back");
            if (bitmapRes > 0) {
                this.f173a.setBackgroundResource(bitmapRes);
            }
        } catch (Throwable th) {
            SSDKLog.m645b().mo29769d(th);
        }
        this.f173a.getBtnRight().setVisibility(8);
        int stringRes = ResHelper.getStringRes(getContext(), "ssdk_weibo_oauth_regiseter");
        if (stringRes > 0) {
            this.f173a.getTvTitle().setText(stringRes);
        }
        addView(this.f173a);
        ImageView imageView = new ImageView(context);
        int bitmapRes2 = ResHelper.getBitmapRes(context, "ssdk_logo");
        if (bitmapRes2 > 0) {
            imageView.setImageResource(bitmapRes2);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setPadding(0, 0, ResHelper.dipToPx(context, 10), 0);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    int stringRes = ResHelper.getStringRes(view.getContext(), "ssdk_website");
                    String str = null;
                    if (stringRes > 0) {
                        str = view.getResources().getString(stringRes);
                    }
                    if (!TextUtils.isEmpty(str)) {
                        view.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    }
                } catch (Throwable th) {
                    SSDKLog.m645b().mo29769d(th);
                }
            }
        });
        this.f173a.addView(imageView);
        this.f174b = new RelativeLayout(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 0);
        layoutParams.weight = 1.0f;
        this.f174b.setLayoutParams(layoutParams);
        addView(this.f174b);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.f174b.addView(linearLayout);
        this.f176d = new TextView(context);
        this.f176d.setLayoutParams(new LinearLayout.LayoutParams(-1, 5));
        this.f176d.setBackgroundColor(-12929302);
        linearLayout.addView(this.f176d);
        this.f176d.setVisibility(8);
        this.f175c = new WebView(context);
        this.f175c.getSettings().setAllowFileAccess(false);
        if (Build.VERSION.SDK_INT >= 16) {
            this.f175c.getSettings().setAllowFileAccessFromFileURLs(false);
            this.f175c.getSettings().setAllowUniversalAccessFromFileURLs(false);
        }
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
        layoutParams2.weight = 1.0f;
        this.f175c.setLayoutParams(layoutParams2);
        C06952 r12 = new WebChromeClient() {
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) RegisterView.this.f176d.getLayoutParams();
                layoutParams.width = (b * i) / 100;
                RegisterView.this.f176d.setLayoutParams(layoutParams);
                if (i <= 0 || i >= 100) {
                    RegisterView.this.f176d.setVisibility(8);
                } else {
                    RegisterView.this.f176d.setVisibility(0);
                }
            }
        };
        if (Build.VERSION.SDK_INT > 10 && Build.VERSION.SDK_INT < 17) {
            try {
                Method method = this.f175c.getClass().getMethod("removeJavascriptInterface", new Class[]{String.class});
                method.setAccessible(true);
                method.invoke(this.f175c, new Object[]{"searchBoxJavaBridge_"});
            } catch (Throwable th2) {
                SSDKLog.m645b().mo29769d(th2);
            }
        }
        this.f175c.setWebChromeClient(r12);
        linearLayout.addView(this.f175c);
        this.f175c.requestFocus();
    }

    /* renamed from: b */
    private int m132b(Context context) {
        WindowManager windowManager;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (!(context instanceof Activity) || (windowManager = ((Activity) context).getWindowManager()) == null) {
            return 0;
        }
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /* renamed from: a */
    public View mo10526a() {
        return this.f173a.getBtnBack();
    }

    /* renamed from: a */
    public void mo10527a(boolean z) {
        this.f173a.setVisibility(z ? 8 : 0);
    }

    /* renamed from: b */
    public WebView mo10528b() {
        return this.f175c;
    }

    /* renamed from: c */
    public TitleLayout mo10529c() {
        return this.f173a;
    }

    /* renamed from: d */
    public RelativeLayout mo10530d() {
        return this.f174b;
    }
}
