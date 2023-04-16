package p005cn.sharesdk.onekeyshare.themes.classic.land;

import com.mob.tools.utils.ResHelper;
import p005cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;
import p005cn.sharesdk.onekeyshare.themes.classic.FriendListPage;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.land.FriendListPageLand */
public class FriendListPageLand extends FriendListPage {
    private static final int DESIGN_SCREEN_WIDTH = 1280;
    private static final int DESIGN_TITLE_HEIGHT = 70;

    /* access modifiers changed from: protected */
    public int getDesignTitleHeight() {
        return 70;
    }

    public FriendListPageLand(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
    }

    /* access modifiers changed from: protected */
    public float getRatio() {
        return ((float) ResHelper.getScreenWidth(this.activity)) / 1280.0f;
    }
}
