package p005cn.sharesdk.onekeyshare.themes.classic;

import android.content.Context;
import android.content.Intent;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import p005cn.sharesdk.onekeyshare.themes.classic.land.EditPageLand;
import p005cn.sharesdk.onekeyshare.themes.classic.land.PlatformPageLand;
import p005cn.sharesdk.onekeyshare.themes.classic.port.EditPagePort;
import p005cn.sharesdk.onekeyshare.themes.classic.port.PlatformPagePort;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.ClassicTheme */
public class ClassicTheme extends OnekeyShareThemeImpl {
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastTime;

    /* access modifiers changed from: protected */
    public void showPlatformPage(Context context) {
        PlatformPage platformPage;
        if (context.getResources().getConfiguration().orientation == 1) {
            platformPage = new PlatformPagePort(this);
        } else {
            platformPage = new PlatformPageLand(this);
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - lastTime >= 1000) {
            platformPage.show(context, (Intent) null);
        }
        lastTime = currentTimeMillis;
    }

    /* access modifiers changed from: protected */
    public void showEditPage(Context context, Platform platform, Platform.ShareParams shareParams) {
        EditPage editPage;
        if (context.getResources().getConfiguration().orientation == 1) {
            editPage = new EditPagePort(this);
        } else {
            editPage = new EditPageLand(this);
        }
        editPage.setPlatform(platform);
        editPage.setShareParams(shareParams);
        editPage.show(context, (Intent) null);
    }
}
