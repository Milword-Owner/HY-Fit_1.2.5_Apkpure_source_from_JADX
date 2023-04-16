package p005cn.sharesdk.onekeyshare;

import com.mob.tools.FakeActivity;
import java.util.ArrayList;
import java.util.HashMap;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.PlatformActionListener;

/* renamed from: cn.sharesdk.onekeyshare.OnekeySharePage */
public class OnekeySharePage extends FakeActivity {
    private OnekeyShareThemeImpl impl;

    public OnekeySharePage(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        this.impl = onekeyShareThemeImpl;
    }

    /* access modifiers changed from: protected */
    public final boolean isDialogMode() {
        return this.impl.dialogMode;
    }

    /* access modifiers changed from: protected */
    public final HashMap<String, Object> getShareParamsMap() {
        return this.impl.shareParamsMap;
    }

    /* access modifiers changed from: protected */
    public final boolean isSilent() {
        return this.impl.silent;
    }

    /* access modifiers changed from: protected */
    public final ArrayList<CustomerLogo> getCustomerLogos() {
        return this.impl.customerLogos;
    }

    /* access modifiers changed from: protected */
    public final HashMap<String, String> getHiddenPlatforms() {
        return this.impl.hiddenPlatforms;
    }

    /* access modifiers changed from: protected */
    public final PlatformActionListener getCallback() {
        return this.impl.callback;
    }

    /* access modifiers changed from: protected */
    public final ShareContentCustomizeCallback getCustomizeCallback() {
        return this.impl.customizeCallback;
    }

    /* access modifiers changed from: protected */
    public final boolean isDisableSSO() {
        return this.impl.disableSSO;
    }

    /* access modifiers changed from: protected */
    public final void shareSilently(Platform platform) {
        this.impl.shareSilently(platform);
    }

    /* access modifiers changed from: protected */
    public final Platform.ShareParams formateShareData(Platform platform) {
        if (this.impl.formateShareData(platform)) {
            return this.impl.shareDataToShareParams(platform);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final boolean isUseClientToShare(Platform platform) {
        return this.impl.isUseClientToShare(platform);
    }
}
