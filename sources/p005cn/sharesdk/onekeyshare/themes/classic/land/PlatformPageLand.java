package p005cn.sharesdk.onekeyshare.themes.classic.land;

import java.util.ArrayList;
import p005cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import p005cn.sharesdk.onekeyshare.themes.classic.PlatformPage;
import p005cn.sharesdk.onekeyshare.themes.classic.PlatformPageAdapter;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.land.PlatformPageLand */
public class PlatformPageLand extends PlatformPage {
    public PlatformPageLand(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
    }

    public void onCreate() {
        requestSensorLandscapeOrientation();
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    public PlatformPageAdapter newAdapter(ArrayList<Object> arrayList) {
        return new PlatformPageAdapterLand(this, arrayList);
    }
}
