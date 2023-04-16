package p005cn.sharesdk.facebook;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.LinearLayout;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.mob.MobSDK;
import com.mob.tools.FakeActivity;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;
import p005cn.sharesdk.framework.utils.SSDKLog;
import p005cn.sharesdk.framework.utils.ShareSDKFileProvider;
import p015io.reactivex.annotations.SchedulerSupport;

/* renamed from: cn.sharesdk.facebook.j */
/* compiled from: ShareActivity */
public class C0677j extends FakeActivity {

    /* renamed from: a */
    private PlatformActionListener f128a;

    /* renamed from: b */
    private Platform f129b;

    /* renamed from: c */
    private Platform.ShareParams f130c;

    /* renamed from: d */
    private String f131d;

    /* renamed from: a */
    public void mo10232a(PlatformActionListener platformActionListener, Platform platform, Platform.ShareParams shareParams, String str) {
        this.f128a = platformActionListener;
        this.f129b = platform;
        this.f130c = shareParams;
        this.f131d = str;
    }

    public void onCreate() {
        super.onCreate();
        try {
            LinearLayout linearLayout = new LinearLayout(this.activity);
            linearLayout.setOrientation(1);
            this.activity.setContentView(linearLayout);
            if (mo10231a() != null) {
                this.activity.startActivityForResult(mo10231a(), 64206);
            }
        } catch (Throwable th) {
            finish();
            this.f128a.onError(this.f129b, 9, th);
        }
    }

    /* renamed from: a */
    public Intent mo10231a() {
        Intent intent = new Intent("com.facebook.platform.PLATFORM_ACTIVITY");
        intent.setPackage("com.facebook.katana");
        intent.addCategory("android.intent.category.DEFAULT");
        Bundle bundle = new Bundle();
        bundle.putBoolean(ShareConstants.DATA_FAILURES_FATAL, false);
        bundle.putString(ShareConstants.TITLE, this.f130c.getTitle());
        if (!TextUtils.isEmpty(this.f130c.getUrl())) {
            bundle.putString(ShareConstants.CONTENT_URL, this.f129b.getShortLintk(this.f130c.getUrl(), false));
            bundle.putString("type", ShareConstants.CONTENT_URL);
            if (!TextUtils.isEmpty(this.f130c.getQuote())) {
                bundle.putString(ShareConstants.QUOTE, this.f130c.getQuote());
            }
            if (!TextUtils.isEmpty(this.f130c.getHashtag())) {
                bundle.putString(ShareConstants.HASHTAG, this.f130c.getHashtag());
            }
        } else if (!TextUtils.isEmpty(this.f130c.getFilePath())) {
            bundle.putString(ShareConstants.VIDEO_URL, Uri.fromFile(new File(this.f130c.getFilePath())).toString());
            bundle.putString("type", ShareConstants.VIDEO_URL);
            bundle.putString(ShareConstants.DESCRIPTION, this.f130c.getText());
            bundle.putString(ShareConstants.TITLE, this.f130c.getTitle());
            if (!TextUtils.isEmpty(this.f130c.getHashtag())) {
                bundle.putString(ShareConstants.HASHTAG, this.f130c.getHashtag());
            }
        } else if (this.f130c.getImageArray() != null && this.f130c.getImageArray().length > 0) {
            try {
                ArrayList arrayList = new ArrayList();
                List<String> arrayList2 = new ArrayList<>();
                if (this.f130c.getImageArray() != null) {
                    arrayList2 = Arrays.asList(this.f130c.getImageArray());
                }
                for (String file : arrayList2) {
                    File file2 = new File(file);
                    if (!file2.exists()) {
                        SSDKLog.m645b().mo29786w("Facebook share iamge ShareActivity file is not exist");
                    } else if (Build.VERSION.SDK_INT >= 29) {
                        Context context = MobSDK.getContext();
                        Uri a = ShareSDKFileProvider.m647a(context, MobSDK.getContext().getPackageName() + ".cn.sharesdk.ShareSDKFileProvider", file2);
                        MobSDK.getContext().grantUriPermission("com.facebook.katana", a, 3);
                        arrayList.add(String.valueOf(a));
                    } else if (Build.VERSION.SDK_INT >= 24) {
                        arrayList.add(ResHelper.pathToContentUri(MobSDK.getContext(), file2.getAbsolutePath()).toString());
                    } else {
                        arrayList.add(Uri.fromFile(file2).toString());
                    }
                }
                if (!TextUtils.isEmpty(this.f130c.getHashtag())) {
                    bundle.putString(ShareConstants.HASHTAG, this.f130c.getHashtag());
                }
                bundle.putStringArrayList(ShareConstants.PHOTOS, arrayList);
                bundle.putString(ShareConstants.DESCRIPTION, this.f130c.getText());
                bundle.putString("NAME", this.f130c.getTitle());
            } catch (Throwable th) {
                SSDKLog.m645b().mo29769d(th);
            }
        }
        intent.putExtra(NativeProtocol.EXTRA_PROTOCOL_VERSION, NativeProtocol.PROTOCOL_VERSION_20171115).putExtra(NativeProtocol.EXTRA_PROTOCOL_ACTION, NativeProtocol.ACTION_FEED_DIALOG).putExtra(NativeProtocol.EXTRA_APPLICATION_ID, this.f131d);
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
        if (this.f128a != null) {
            Bundle bundleExtra = intent != null ? intent.getBundleExtra(NativeProtocol.EXTRA_PROTOCOL_METHOD_RESULTS) : null;
            if (bundleExtra != null) {
                String string = bundleExtra.getString(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETION_GESTURE_KEY);
                boolean z = bundleExtra.getBoolean(NativeProtocol.RESULT_ARGS_DIALOG_COMPLETE_KEY);
                if (!TextUtils.isEmpty(string)) {
                    if (string.equalsIgnoreCase("cancel")) {
                        this.f128a.onCancel(this.f129b, 9);
                    } else if (string.equalsIgnoreCase("post")) {
                        this.f128a.onComplete(this.f129b, 9, (HashMap<String, Object>) null);
                    }
                } else if (z) {
                    this.f128a.onComplete(this.f129b, 9, (HashMap<String, Object>) null);
                } else {
                    this.f128a.onCancel(this.f129b, 9);
                }
            } else if (i == 64206 && i2 == 0) {
                this.f128a.onCancel(this.f129b, 9);
            } else {
                this.f128a.onError(this.f129b, 9, new Throwable("share error!"));
            }
        }
    }
}
