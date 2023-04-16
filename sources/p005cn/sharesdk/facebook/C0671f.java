package p005cn.sharesdk.facebook;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import p005cn.sharesdk.framework.authorize.C0699d;
import p005cn.sharesdk.framework.authorize.C0700e;

/* renamed from: cn.sharesdk.facebook.f */
/* compiled from: FacebookSSOProcessor */
public class C0671f extends C0700e {

    /* renamed from: d */
    private String f110d;

    /* renamed from: e */
    private String[] f111e;

    public C0671f(C0699d dVar) {
        super(dVar);
    }

    /* renamed from: a */
    public void mo10205a(String str, String[] strArr) {
        this.f110d = str;
        this.f111e = strArr;
    }

    /* renamed from: a */
    public void mo10203a() {
        if (!m57b()) {
            this.f187a.finish();
            if (this.f189c != null) {
                this.f189c.onFailed(new Throwable());
            }
        }
    }

    /* renamed from: b */
    private boolean m57b() {
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.katana.ProxyAuth");
        intent.putExtra("client_id", this.f110d);
        String[] strArr = this.f111e;
        if (strArr != null && strArr.length > 0) {
            intent.putExtra("scope", TextUtils.join(",", strArr));
        }
        if (!m58b(intent)) {
            return false;
        }
        try {
            this.f187a.startActivityForResult(intent, this.f188b);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    private boolean m58b(Intent intent) {
        ResolveInfo resolveActivity = this.f187a.getContext().getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return false;
        }
        try {
            for (Signature charsString : this.f187a.getContext().getPackageManager().getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures) {
                if ("30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2".equals(charsString.toCharsString())) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    /* renamed from: a */
    public void mo10204a(int i, int i2, Intent intent) {
        this.f187a.finish();
        if (i != this.f188b) {
            return;
        }
        if (i2 == -1) {
            m59c(intent);
        } else if (i2 == 0) {
            m60d(intent);
        }
    }

    /* renamed from: c */
    private void m59c(Intent intent) {
        if (this.f189c != null) {
            String stringExtra = intent.getStringExtra(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE);
            if (stringExtra == null) {
                stringExtra = intent.getStringExtra(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
            }
            if (stringExtra == null) {
                this.f189c.onComplete(intent.getExtras());
            } else if (stringExtra.equals("access_denied") || stringExtra.equals("OAuthAccessDeniedException")) {
                this.f189c.onCancel();
            } else {
                String stringExtra2 = intent.getStringExtra(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE);
                if (stringExtra2 != null) {
                    stringExtra = intent.getStringExtra(NativeProtocol.BRIDGE_ARG_ERROR_CODE) + ": " + stringExtra2;
                }
                this.f189c.onFailed(new Throwable(stringExtra));
            }
        }
    }

    /* renamed from: d */
    private void m60d(Intent intent) {
        if (this.f189c != null) {
            if (intent != null) {
                String stringExtra = intent.getStringExtra("error");
                String stringExtra2 = intent.getStringExtra(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
                if (!stringExtra.equals("access_denied") || !stringExtra2.equals("200")) {
                    this.f189c.onFailed(new Throwable(stringExtra + " (" + stringExtra2 + ")"));
                    return;
                }
                this.f189c.onCancel();
                return;
            }
            this.f189c.onCancel();
        }
    }
}
