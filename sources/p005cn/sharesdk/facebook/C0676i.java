package p005cn.sharesdk.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.LinearLayout;
import com.baidu.mobstat.Config;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.DeviceHelper;
import java.util.HashMap;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p015io.reactivex.annotations.SchedulerSupport;

/* renamed from: cn.sharesdk.facebook.i */
/* compiled from: InviteActivity */
public class C0676i extends FakeActivity {

    /* renamed from: a */
    private PlatformActionListener f124a;

    /* renamed from: b */
    private Platform f125b;

    /* renamed from: c */
    private String f126c;

    /* renamed from: d */
    private Platform.ShareParams f127d;

    /* renamed from: a */
    public void mo10229a(PlatformActionListener platformActionListener, Platform platform, Platform.ShareParams shareParams) {
        this.f124a = platformActionListener;
        this.f125b = platform;
        this.f127d = shareParams;
    }

    /* renamed from: a */
    public void mo10230a(String str) {
        this.f126c = str;
    }

    public void onCreate() {
        super.onCreate();
        try {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(1);
            this.activity.setContentView(linearLayout);
        } catch (Exception e) {
            SSDKLog.m645b().mo29769d(e);
        }
        try {
            if (mo10228a() != null) {
                this.activity.startActivityForResult(mo10228a(), 64206);
            }
        } catch (Throwable th) {
            finish();
            this.f124a.onError(this.f125b, 9, th);
        }
    }

    /* renamed from: a */
    public Intent mo10228a() {
        Intent intent = new Intent("com.facebook.platform.PLATFORM_ACTIVITY");
        intent.setPackage("com.facebook.katana");
        intent.addCategory("android.intent.category.DEFAULT");
        Bundle bundle = new Bundle();
        bundle.putString(ShareConstants.APPLINK_URL, this.f127d.getUrl());
        bundle.putString(ShareConstants.PREVIEW_IMAGE_URL, this.f127d.getImageUrl());
        intent.putExtra(NativeProtocol.EXTRA_PROTOCOL_VERSION, NativeProtocol.PROTOCOL_VERSION_20171115).putExtra(NativeProtocol.EXTRA_PROTOCOL_ACTION, NativeProtocol.ACTION_APPINVITE_DIALOG).putExtra(NativeProtocol.EXTRA_APPLICATION_ID, this.f126c);
        Bundle bundle2 = new Bundle();
        bundle2.putString("action_id", "cf61947c-a8fe-4fa3-aa7c-fbeb7f291352");
        DeviceHelper instance = DeviceHelper.getInstance(getContext());
        String appName = instance.getAppName();
        if (!TextUtils.isEmpty(appName) && instance.getNetworkTypeForStatic().equals(SchedulerSupport.NONE)) {
            bundle2.putString(NativeProtocol.BRIDGE_ARG_APP_NAME_STRING, appName);
        }
        intent.putExtra(NativeProtocol.EXTRA_PROTOCOL_BRIDGE_ARGS, bundle2);
        intent.putExtra(NativeProtocol.EXTRA_PROTOCOL_METHOD_ARGS, bundle);
        return intent;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        finish();
        if (intent != null) {
            Bundle bundleExtra = intent.getBundleExtra(NativeProtocol.EXTRA_PROTOCOL_BRIDGE_ARGS);
            if (bundleExtra != null) {
                for (String str : bundleExtra.keySet()) {
                    if (str.equals("error")) {
                        Bundle bundle = (Bundle) bundleExtra.get(str);
                        if (bundle != null) {
                            String str2 = "";
                            for (String str3 : bundle.keySet()) {
                                str2 = str2 + str3 + Config.TRACE_TODAY_VISIT_SPLIT + bundle.get(str3) + ", ";
                            }
                            if (str2.indexOf(NativeProtocol.ERROR_USER_CANCELED) > -1) {
                                this.f124a.onCancel(this.f125b, 9);
                            }
                            this.f124a.onError(this.f125b, 9, new Throwable(str2));
                            return;
                        }
                        return;
                    }
                }
            }
            Bundle bundleExtra2 = intent.getBundleExtra(NativeProtocol.EXTRA_PROTOCOL_METHOD_RESULTS);
            if (bundleExtra2 != null) {
                boolean z = true;
                if (bundleExtra2.getInt(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETE_KEY) != 1) {
                    z = false;
                }
                String string = bundleExtra2.getString(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY);
                if (!TextUtils.isEmpty(string)) {
                    if (string.equalsIgnoreCase("cancel")) {
                        this.f124a.onCancel(this.f125b, 9);
                    }
                } else if (z) {
                    this.f124a.onComplete(this.f125b, 9, (HashMap<String, Object>) null);
                }
            }
        } else if (i == 64206 && i2 == 0) {
            this.f124a.onCancel(this.f125b, 9);
        } else {
            this.f124a.onError(this.f125b, 9, new Throwable("share error!"));
        }
    }
}
