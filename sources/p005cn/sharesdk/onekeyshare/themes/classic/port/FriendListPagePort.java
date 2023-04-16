package p005cn.sharesdk.onekeyshare.themes.classic.port;

import com.mob.tools.utils.ResHelper;
import p005cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import p005cn.sharesdk.onekeyshare.themes.classic.FriendListPage;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.port.FriendListPagePort */
public class FriendListPagePort extends FriendListPage {
    private static final int DESIGN_SCREEN_WIDTH = 720;
    private static final int DESIGN_TITLE_HEIGHT = 96;

    /* access modifiers changed from: protected */
    public int getDesignTitleHeight() {
        return 96;
    }

    public FriendListPagePort(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
    }

    /* access modifiers changed from: protected */
    public float getRatio() {
        return ((float) ResHelper.getScreenWidth(this.activity)) / 720.0f;
    }
}
