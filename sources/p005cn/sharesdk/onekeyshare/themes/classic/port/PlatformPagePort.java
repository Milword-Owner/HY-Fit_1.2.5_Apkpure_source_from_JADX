package p005cn.sharesdk.onekeyshare.themes.classic.port;

import java.util.ArrayList;
import p005cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import p005cn.sharesdk.onekeyshare.themes.classic.PlatformPage;
import p005cn.sharesdk.onekeyshare.themes.classic.PlatformPageAdapter;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.port.PlatformPagePort */
public class PlatformPagePort extends PlatformPage {
    public PlatformPagePort(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
    }

    public void onCreate() {
        requestSensorPortraitOrientation();
        super.onCreate();
    }

    /* access modifiers changed from: protected */
    public PlatformPageAdapter newAdapter(ArrayList<Object> arrayList) {
        return new PlatformPageAdapterPort(this, arrayList);
    }
}
