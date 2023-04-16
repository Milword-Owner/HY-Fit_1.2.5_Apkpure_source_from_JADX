package p005cn.sharesdk.onekeyshare.themes.classic;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mob.tools.gui.MobViewPager;
import com.mob.tools.utils.ResHelper;
import java.util.ArrayList;
import java.util.HashMap;
import p005cn.sharesdk.framework.CustomPlatform;
import p005cn.sharesdk.framework.Platform;
import p005cn.sharesdk.framework.ShareSDK;
import p005cn.sharesdk.onekeyshare.CustomerLogo;
import p005cn.sharesdk.onekeyshare.OnekeySharePage;
import p005cn.sharesdk.onekeyshare.OnekeyShareThemeImpl;

/* renamed from: cn.sharesdk.onekeyshare.themes.classic.PlatformPage */
public abstract class PlatformPage extends OnekeySharePage {
    private Animation animHide;
    private Animation animShow;
    /* access modifiers changed from: private */
    public Runnable beforeFinish;
    /* access modifiers changed from: private */
    public boolean finished;
    /* access modifiers changed from: private */
    public ClassicTheme impl;
    private LinearLayout llPanel;

    /* access modifiers changed from: protected */
    public abstract PlatformPageAdapter newAdapter(ArrayList<Object> arrayList);

    public PlatformPage(OnekeyShareThemeImpl onekeyShareThemeImpl) {
        super(onekeyShareThemeImpl);
        this.impl = (ClassicTheme) ResHelper.forceCast(onekeyShareThemeImpl);
    }

    public void onCreate() {
        this.activity.getWindow().setBackgroundDrawable(new ColorDrawable(1275068416));
        initAnims();
        LinearLayout linearLayout = new LinearLayout(this.activity);
        linearLayout.setOrientation(1);
        this.activity.setContentView(linearLayout);
        TextView textView = new TextView(this.activity);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.weight = 1.0f;
        textView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PlatformPage.this.finish();
            }
        });
        linearLayout.addView(textView, layoutParams);
        this.llPanel = new LinearLayout(this.activity);
        this.llPanel.setOrientation(1);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        this.llPanel.setAnimation(this.animShow);
        linearLayout.addView(this.llPanel, layoutParams2);
        MobViewPager mobViewPager = new MobViewPager(this.activity);
        PlatformPageAdapter newAdapter = newAdapter(collectCells());
        this.llPanel.addView(mobViewPager, new LinearLayout.LayoutParams(-1, newAdapter.getPanelHeight()));
        IndicatorView indicatorView = new IndicatorView(this.activity);
        this.llPanel.addView(indicatorView, new LinearLayout.LayoutParams(-1, newAdapter.getBottomHeight()));
        indicatorView.setScreenCount(newAdapter.getCount());
        indicatorView.onScreenChange(0, 0);
        newAdapter.setIndicator(indicatorView);
        mobViewPager.setAdapter(newAdapter);
    }

    /* access modifiers changed from: protected */
    public ArrayList<Object> collectCells() {
        ArrayList<Object> arrayList = new ArrayList<>();
        Platform[] platformList = ShareSDK.getPlatformList();
        if (platformList == null) {
            platformList = new Platform[0];
        }
        HashMap<String, String> hiddenPlatforms = getHiddenPlatforms();
        if (hiddenPlatforms == null) {
            hiddenPlatforms = new HashMap<>();
        }
        for (Platform platform : platformList) {
            if (!hiddenPlatforms.containsKey(platform.getName()) && isCanShare(platform)) {
                arrayList.add(platform);
            }
        }
        ArrayList<CustomerLogo> customerLogos = getCustomerLogos();
        if (customerLogos != null && customerLogos.size() > 0) {
            arrayList.addAll(customerLogos);
        }
        return arrayList;
    }

    public final void showEditPage(final Platform platform) {
        this.beforeFinish = new Runnable() {
            public void run() {
                boolean access$000 = PlatformPage.this.isSilent();
                Platform platform = platform;
                boolean z = platform instanceof CustomPlatform;
                boolean access$100 = PlatformPage.this.isUseClientToShare(platform);
                if (access$000 || z || access$100) {
                    PlatformPage.this.shareSilently(platform);
                    return;
                }
                Platform.ShareParams access$300 = PlatformPage.this.formateShareData(platform);
                if (access$300 != null) {
                    ShareSDK.logDemoEvent(3, platform);
                    access$300.setOpenCustomEven(true);
                    if (PlatformPage.this.getCustomizeCallback() != null) {
                        PlatformPage.this.getCustomizeCallback().onShare(platform, access$300);
                    }
                    PlatformPage.this.impl.showEditPage(PlatformPage.this.activity, platform, access$300);
                }
            }
        };
        finish();
    }

    public final void performCustomLogoClick(final View view, final CustomerLogo customerLogo) {
        this.beforeFinish = new Runnable() {
            public void run() {
                customerLogo.listener.onClick(view);
            }
        };
        finish();
    }

    private boolean isCanShare(Platform platform) {
        String name = platform.getName();
        return !"Cmcc".equals(name) && !"Accountkit".equals(name) && !"Telecom".equals(name) && !"GooglePlus".equals(name) && !"HWAccount".equals(name);
    }

    private void initAnims() {
        this.animShow = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        this.animShow.setDuration(300);
        this.animHide = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
        this.animHide.setDuration(300);
    }

    public boolean onFinish() {
        if (this.finished) {
            this.finished = false;
            return false;
        }
        this.animHide.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                if (PlatformPage.this.beforeFinish == null) {
                    ShareSDK.logDemoEvent(2, (Platform) null);
                } else {
                    PlatformPage.this.beforeFinish.run();
                    Runnable unused = PlatformPage.this.beforeFinish = null;
                }
                boolean unused2 = PlatformPage.this.finished = true;
                PlatformPage.this.finish();
            }
        });
        this.llPanel.clearAnimation();
        this.llPanel.setAnimation(this.animHide);
        this.llPanel.setVisibility(8);
        return true;
    }
}
