package com.huayu.tzc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.baidu.mobstat.Config;
import com.blankj.utilcode.util.AppUtils;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleScanState;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.adapter.ViewPagerFragmentAdapter;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.Constant;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.bean.TabEntity;
import com.huayu.tzc.bean.User;
import com.huayu.tzc.bean.Version;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.BaseAlterDialog;
import com.huayu.tzc.p014ui.activity.setting.UnitActivity;
import com.huayu.tzc.p014ui.fragment.HomeFragment;
import com.huayu.tzc.p014ui.fragment.MineFragment;
import com.huayu.tzc.p014ui.fragment.MsgFragment;
import com.huayu.tzc.p014ui.fragment.TweetFragment;
import com.huayu.tzc.presenter.PrimaryPresenter;
import com.huayu.tzc.utils.AppManager;
import com.huayu.tzc.utils.ByteUtil;
import com.huayu.tzc.utils.EventBusUtils;
import com.huayu.tzc.utils.TestImageLoader;
import com.huayu.tzc.utils.UnitUtils;
import com.huayu.tzc.utils.Utils;
import com.previewlibrary.ZoomMediaLoader;
import com.tencent.mmkv.MMKV;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okhttp3.FormBody;
import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.litepal.LitePal;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00042\u00020\u00022\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0016\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"H\u0016J\b\u0010$\u001a\u00020%H\u0014J\u001c\u0010&\u001a\u00020 2\u0012\u0010'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0(0\"H\u0016J\u0016\u0010*\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020%0\"H\u0016J\b\u0010+\u001a\u00020\u0003H\u0016J\u0016\u0010,\u001a\u00020 2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020-0\"H\u0016J\u0006\u0010.\u001a\u00020 J\b\u0010/\u001a\u00020 H\u0002J\b\u00100\u001a\u00020 H\u0002J\b\u00101\u001a\u00020 H\u0002J\b\u00102\u001a\u00020 H\u0014J\"\u00103\u001a\u00020 2\u0006\u00104\u001a\u00020%2\u0006\u00105\u001a\u00020%2\b\u00106\u001a\u0004\u0018\u000107H\u0014J\b\u00108\u001a\u00020 H\u0014J\u0018\u00109\u001a\u00020\u00142\u0006\u0010:\u001a\u00020%2\u0006\u0010;\u001a\u00020<H\u0016J\u0010\u0010=\u001a\u00020 2\u0006\u0010>\u001a\u00020%H\u0016J \u0010?\u001a\u00020 2\u0006\u0010@\u001a\u00020%2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020%H\u0016J\u0010\u0010D\u001a\u00020 2\u0006\u0010@\u001a\u00020%H\u0016J\b\u0010E\u001a\u00020 H\u0014J\u0010\u0010F\u001a\u00020 2\u0006\u0010@\u001a\u00020%H\u0016J\u0010\u0010G\u001a\u00020 2\u0006\u0010@\u001a\u00020%H\u0016J\b\u0010H\u001a\u00020 H\u0002J\u001e\u0010I\u001a\u00020 2\u0006\u0010J\u001a\u00020K2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001e0\"H\u0016J\u0010\u0010L\u001a\u00020 2\u0006\u0010M\u001a\u00020KH\u0002J\u0010\u0010N\u001a\u00020 2\u0006\u0010M\u001a\u00020KH\u0002J\u0012\u0010O\u001a\u00020 2\b\u0010P\u001a\u0004\u0018\u00010QH\u0016J\u0010\u0010R\u001a\u00020 2\u0006\u0010S\u001a\u00020\u001eH\u0002J\u0016\u0010T\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001e0\"H\u0016J\b\u0010U\u001a\u00020 H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000ej\b\u0012\u0004\u0012\u00020\f`\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u000ej\b\u0012\u0004\u0012\u00020\u0019`\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000¨\u0006V"}, mo21895d2 = {"Lcom/huayu/tzc/MainActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$PrimaryView;", "Lcom/huayu/tzc/presenter/PrimaryPresenter;", "Lcom/flyco/tablayout/listener/OnTabSelectListener;", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "()V", "exitTime", "", "fragmentAdapter", "Lcom/huayu/tzc/adapter/ViewPagerFragmentAdapter;", "fragmentHome", "Landroidx/fragment/app/Fragment;", "fragmentList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "fragmentMine", "fragmentMsg", "fragmentTweet", "isShowVersion", "", "mIconSelectIds", "", "mIconUnSelectIds", "mTabEntities", "Lcom/flyco/tablayout/listener/CustomTabEntity;", "scanCallback", "Lcom/clj/fastble/callback/BleScanCallback;", "checkVersion", "versionName", "", "getAppVersion", "", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "Lcom/huayu/tzc/bean/Version;", "getLayoutId", "", "getMineList", "loginBean", "", "Lcom/huayu/tzc/bean/Member;", "getNotReadCount", "getPresenter", "getUserInfo", "Lcom/huayu/tzc/bean/User;", "initBle", "initData", "initFragment", "initTime", "initView", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onPageScrollStateChanged", "state", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "onRestart", "onTabReselect", "onTabSelect", "openGooglePlay", "reporting", "item", "Lcom/huayu/tzc/bean/Measure;", "reportingData", "measure", "reportingOnlyWeightData", "showError", "e", "", "showVersionDialog", "content", "updateUserInfo", "uploadData", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: MainActivity.kt */
public final class MainActivity extends BaseActivity<MainContract.PrimaryView, PrimaryPresenter> implements OnTabSelectListener, MainContract.PrimaryView, ViewPager.OnPageChangeListener {
    private HashMap _$_findViewCache;
    private long exitTime;
    private ViewPagerFragmentAdapter fragmentAdapter;
    private Fragment fragmentHome = new HomeFragment();
    private ArrayList<Fragment> fragmentList = new ArrayList<>();
    private Fragment fragmentMine = new MineFragment();
    private Fragment fragmentMsg = new MsgFragment();
    private Fragment fragmentTweet = new TweetFragment();
    private boolean isShowVersion;
    private final int[] mIconSelectIds = {C2128R.C2130drawable.ic_home, C2128R.C2130drawable.ic_community, C2128R.C2130drawable.ic_support, C2128R.C2130drawable.ic_mine};
    private final int[] mIconUnSelectIds = {C2128R.C2130drawable.ic_unhome, C2128R.C2130drawable.ic_community_f, C2128R.C2130drawable.ic_support_f, C2128R.C2130drawable.ic_unmine};
    private final ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private final BleScanCallback scanCallback = new MainActivity$scanCallback$1(this);

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C2128R.C2133layout.activity_main;
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onTabReselect(int i) {
    }

    @NotNull
    public PrimaryPresenter getPresenter() {
        return new PrimaryPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        ZoomMediaLoader.getInstance().init(new TestImageLoader());
        initData();
        initFragment();
        initTime();
    }

    private final void initTime() {
        new Timer().schedule(new MainActivity$initTime$1(this), 0, 30000);
    }

    public final void initBle() {
        String tag = getTAG();
        StringBuilder sb = new StringBuilder();
        sb.append("initBle: ");
        BleManager instance = BleManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "BleManager.getInstance()");
        BleScanState scanSate = instance.getScanSate();
        Intrinsics.checkExpressionValueIsNotNull(scanSate, "BleManager.getInstance().scanSate");
        sb.append(scanSate.getCode());
        Log.e(tag, sb.toString());
        BleManager instance2 = BleManager.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance2, "BleManager.getInstance()");
        BleScanState scanSate2 = instance2.getScanSate();
        Intrinsics.checkExpressionValueIsNotNull(scanSate2, "BleManager.getInstance().scanSate");
        if (scanSate2.getCode() == -1) {
            BleManager.getInstance().scan(this.scanCallback);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            BleManager.getInstance().cancelScan();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
        try {
            HomeFragment homeFragment = (HomeFragment) this.fragmentHome;
            if (homeFragment != null) {
                homeFragment.checkBleEnabled();
            }
            PrimaryPresenter primaryPresenter = (PrimaryPresenter) getMPresenter();
            if (primaryPresenter != null) {
                primaryPresenter.getNotReadCount();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void initData() {
        progressShow2();
        PrimaryPresenter primaryPresenter = (PrimaryPresenter) getMPresenter();
        if (primaryPresenter != null) {
            primaryPresenter.getMemList();
        }
        PrimaryPresenter primaryPresenter2 = (PrimaryPresenter) getMPresenter();
        if (primaryPresenter2 != null) {
            primaryPresenter2.getUserInfo();
        }
        PrimaryPresenter primaryPresenter3 = (PrimaryPresenter) getMPresenter();
        if (primaryPresenter3 != null) {
            primaryPresenter3.getNotReadCount();
        }
    }

    private final void initFragment() {
        this.fragmentList.add(this.fragmentHome);
        this.fragmentList.add(this.fragmentTweet);
        this.fragmentList.add(this.fragmentMsg);
        this.fragmentList.add(this.fragmentMine);
        String[] strArr = {getString(C2128R.string.main_tab_home), getString(C2128R.string.community), getString(C2128R.string.support), getString(C2128R.string.mine_title)};
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            this.mTabEntities.add(new TabEntity(strArr[i], this.mIconSelectIds[i], this.mIconUnSelectIds[i]));
        }
        ((CommonTabLayout) _$_findCachedViewById(C2128R.C2131id.mainTab)).setTabData(this.mTabEntities);
        ((CommonTabLayout) _$_findCachedViewById(C2128R.C2131id.mainTab)).setOnTabSelectListener(this);
        this.fragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), this.fragmentList);
        ViewPager viewPager = (ViewPager) _$_findCachedViewById(C2128R.C2131id.mainViewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager, "mainViewPager");
        ViewPagerFragmentAdapter viewPagerFragmentAdapter = this.fragmentAdapter;
        if (viewPagerFragmentAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragmentAdapter");
        }
        viewPager.setAdapter(viewPagerFragmentAdapter);
        ViewPager viewPager2 = (ViewPager) _$_findCachedViewById(C2128R.C2131id.mainViewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager2, "mainViewPager");
        viewPager2.setOffscreenPageLimit(4);
        ((ViewPager) _$_findCachedViewById(C2128R.C2131id.mainViewPager)).addOnPageChangeListener(this);
    }

    public void onTabSelect(int i) {
        ViewPager viewPager = (ViewPager) _$_findCachedViewById(C2128R.C2131id.mainViewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager, "mainViewPager");
        viewPager.setCurrentItem(i);
    }

    public boolean onKeyDown(int i, @NotNull KeyEvent keyEvent) {
        Intrinsics.checkParameterIsNotNull(keyEvent, NotificationCompat.CATEGORY_EVENT);
        if (i != 4 || keyEvent.getAction() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        if (System.currentTimeMillis() - this.exitTime > ((long) 2000)) {
            ToastUtils.show((CharSequence) getString(C2128R.string.common_exit));
            this.exitTime = System.currentTimeMillis();
            return true;
        }
        AppManager.finishAllActivity();
        System.exit(0);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }

    public void getAppVersion(@NotNull BaseBean<Version> baseBean) {
        Version data;
        String dupdatecontent;
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        this.isShowVersion = true;
        if (baseBean.getMeta().getSuccess()) {
            Version data2 = baseBean.getData();
            if (data2 == null) {
                Intrinsics.throwNpe();
            }
            if (!checkVersion(data2.getVersion()) && (data = baseBean.getData()) != null && (dupdatecontent = data.getDupdatecontent()) != null) {
                showVersionDialog(dupdatecontent);
            }
        }
    }

    private final boolean checkVersion(String str) {
        List split$default = StringsKt.split$default((CharSequence) str, new String[]{"\\."}, false, 0, 6, (Object) null);
        String appVersionName = AppUtils.getAppVersionName();
        Intrinsics.checkExpressionValueIsNotNull(appVersionName, "AppUtils.getAppVersionName()");
        List split$default2 = StringsKt.split$default((CharSequence) appVersionName, new String[]{"\\."}, false, 0, 6, (Object) null);
        int size = split$default2.size();
        for (int i = 0; i < size; i++) {
            if (((String) split$default2.get(i)).compareTo((String) split$default.get(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    public void updateUserInfo(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
    }

    public void getUserInfo(@NotNull BaseBean<User> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "loginBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            MMKV.defaultMMKV().encode("user", (Parcelable) baseBean.getData());
            User data = baseBean.getData();
            if (data == null || data.getU_status() != 0) {
                if (!this.isShowVersion) {
                    PrimaryPresenter primaryPresenter = (PrimaryPresenter) getMPresenter();
                    if (primaryPresenter != null) {
                        primaryPresenter.getAppVersion();
                    }
                    progressShow();
                }
                new Thread(new MainActivity$getUserInfo$1(this)).start();
                EventBus.getDefault().postSticky(baseBean.getData());
                return;
            }
            startActivity(new Intent(this, UnitActivity.class).putExtra("user", baseBean.getData()).putExtra("fromLogin", true).setFlags(268468224));
        }
    }

    public void getMineList(@NotNull BaseBean<List<Member>> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "loginBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            EventBusUtils.postSticky(new EventBusUtils.EventMessage(5, baseBean.getData(), 5));
        }
    }

    public void getNotReadCount(@NotNull BaseBean<Integer> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            Integer data = baseBean.getData();
            if (data == null) {
                Intrinsics.throwNpe();
            }
            if (data.intValue() > 0) {
                CommonTabLayout commonTabLayout = (CommonTabLayout) _$_findCachedViewById(C2128R.C2131id.mainTab);
                Integer data2 = baseBean.getData();
                if (data2 == null) {
                    Intrinsics.throwNpe();
                }
                commonTabLayout.showMsg(2, data2.intValue());
                ((CommonTabLayout) _$_findCachedViewById(C2128R.C2131id.mainTab)).setMsgMargin(2, -5.0f, 5.0f);
                return;
            }
            ((CommonTabLayout) _$_findCachedViewById(C2128R.C2131id.mainTab)).hideMsg(2);
        }
    }

    public void reporting(@NotNull Measure measure, @NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(measure, "item");
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            measure.delete();
        }
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }

    /* access modifiers changed from: private */
    public final void uploadData() {
        if (Utils.checkNetState(this) != 3) {
            List<Measure> findAll = LitePal.findAll(Measure.class, new long[0]);
            Intrinsics.checkExpressionValueIsNotNull(findAll, "findAll(Measure::class.java)");
            if (!findAll.isEmpty()) {
                for (Measure next : findAll) {
                    if (next.getOnlyWeight()) {
                        reportingOnlyWeightData(next);
                    } else {
                        reportingData(next);
                    }
                }
            }
        }
    }

    private final void showVersionDialog(String str) {
        BaseAlterDialog baseAlterDialog = new BaseAlterDialog((Context) this, str);
        baseAlterDialog.setTitle(getString(C2128R.string.update));
        baseAlterDialog.setmOkBtn(getString(C2128R.string.update), new MainActivity$showVersionDialog$1(this, baseAlterDialog));
        baseAlterDialog.setmCancelBtn(getString(C2128R.string.common_cancel), new MainActivity$showVersionDialog$2(baseAlterDialog));
        baseAlterDialog.show();
    }

    /* access modifiers changed from: private */
    public final void openGooglePlay() {
        if (!AppUtils.isAppInstalled("com.google.market")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(Constant.googleplay));
            startActivity(Intent.createChooser(intent, getString(C2128R.string.select_browser)));
            return;
        }
        Intent intent2 = new Intent("android.intent.action.VIEW");
        intent2.setData(Uri.parse("market://details?id=com.huayu.tzc&target=market&from=met"));
        startActivity(intent2);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        String stringExtra;
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i != 812) {
            switch (i) {
                case 800:
                    PrimaryPresenter primaryPresenter = (PrimaryPresenter) getMPresenter();
                    if (primaryPresenter != null) {
                        primaryPresenter.getMemList();
                    }
                    progressShow();
                    return;
                case 801:
                    PrimaryPresenter primaryPresenter2 = (PrimaryPresenter) getMPresenter();
                    if (primaryPresenter2 != null) {
                        primaryPresenter2.getUserInfo();
                    }
                    progressShow();
                    return;
                case 802:
                    if (intent == null) {
                        Intrinsics.throwNpe();
                    }
                    if (intent.getBooleanExtra("isMainUser", false)) {
                        PrimaryPresenter primaryPresenter3 = (PrimaryPresenter) getMPresenter();
                        if (primaryPresenter3 != null) {
                            primaryPresenter3.getUserInfo();
                        }
                        progressShow();
                        return;
                    }
                    PrimaryPresenter primaryPresenter4 = (PrimaryPresenter) getMPresenter();
                    if (primaryPresenter4 != null) {
                        primaryPresenter4.getMemList();
                    }
                    progressShow();
                    return;
                case 803:
                    PrimaryPresenter primaryPresenter5 = (PrimaryPresenter) getMPresenter();
                    if (primaryPresenter5 != null) {
                        primaryPresenter5.getMemList();
                    }
                    PrimaryPresenter primaryPresenter6 = (PrimaryPresenter) getMPresenter();
                    if (primaryPresenter6 != null) {
                        primaryPresenter6.getUserInfo();
                    }
                    progressShow();
                    return;
                default:
                    return;
            }
        } else {
            ViewPager viewPager = (ViewPager) _$_findCachedViewById(C2128R.C2131id.mainViewPager);
            Intrinsics.checkExpressionValueIsNotNull(viewPager, "mainViewPager");
            viewPager.setCurrentItem(2);
            Fragment fragment = this.fragmentMsg;
            if (fragment != null) {
                MsgFragment msgFragment = (MsgFragment) fragment;
                if (intent != null && (stringExtra = intent.getStringExtra("title")) != null) {
                    msgFragment.sendMsg(stringExtra);
                    return;
                }
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.huayu.tzc.ui.fragment.MsgFragment");
        }
    }

    public void onPageSelected(int i) {
        if (i == 2) {
            ((CommonTabLayout) _$_findCachedViewById(C2128R.C2131id.mainTab)).hideMsg(2);
        }
        CommonTabLayout commonTabLayout = (CommonTabLayout) _$_findCachedViewById(C2128R.C2131id.mainTab);
        Intrinsics.checkExpressionValueIsNotNull(commonTabLayout, "mainTab");
        commonTabLayout.setCurrentTab(i);
    }

    private final void reportingData(Measure measure) {
        Integer num;
        float weight = UnitUtils.getWeight(UnitUtils.getIndex(measure.getWeightunit()), measure.getKgWeight());
        if (Intrinsics.areEqual((Object) measure.getWeightunit(), (Object) Constant.UNIT_WEIGHT[2])) {
            byte[] scanRecord = measure.getScanRecord();
            Integer num2 = null;
            if (scanRecord != null) {
                String byteToHex = ByteUtil.byteToHex(scanRecord[4]);
                Intrinsics.checkExpressionValueIsNotNull(byteToHex, "ByteUtil.byteToHex(it)");
                num = Integer.valueOf(Integer.parseInt(byteToHex, CharsKt.checkRadix(16)));
            } else {
                num = null;
            }
            byte[] scanRecord2 = measure.getScanRecord();
            if (scanRecord2 != null) {
                String byteToHex2 = ByteUtil.byteToHex(scanRecord2[5]);
                Intrinsics.checkExpressionValueIsNotNull(byteToHex2, "ByteUtil.byteToHex(it)");
                num2 = Integer.valueOf(Integer.parseInt(byteToHex2, CharsKt.checkRadix(16)));
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = new Object[1];
            if (num2 == null) {
                Intrinsics.throwNpe();
            }
            float f = (float) 10;
            objArr[0] = Float.valueOf(((float) num2.intValue()) / f);
            String format = String.format("%.1f", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            float parseFloat = Float.parseFloat(format);
            if (parseFloat > f) {
                measure.setWeight(String.valueOf(num) + Config.TRACE_TODAY_VISIT_SPLIT + ((int) ((float) MathKt.roundToInt(parseFloat))));
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(num);
                sb.append(':');
                sb.append(parseFloat);
                measure.setWeight(sb.toString());
            }
            String st2StLb = UnitUtils.st2StLb(Float.parseFloat(measure.getBone_mass()));
            Intrinsics.checkExpressionValueIsNotNull(st2StLb, "UnitUtils.st2StLb(measure.bone_mass.toFloat())");
            measure.setBone_mass(st2StLb);
            String st2StLb2 = UnitUtils.st2StLb(Float.parseFloat(measure.getMuscle_mass()));
            Intrinsics.checkExpressionValueIsNotNull(st2StLb2, "UnitUtils.st2StLb(measure.muscle_mass.toFloat())");
            measure.setMuscle_mass(st2StLb2);
            String st2StLb3 = UnitUtils.st2StLb(Float.parseFloat(measure.getFfm()));
            Intrinsics.checkExpressionValueIsNotNull(st2StLb3, "UnitUtils.st2StLb(measure.ffm.toFloat())");
            measure.setFfm(st2StLb3);
            String st2StLb4 = UnitUtils.st2StLb(Float.parseFloat(measure.getIbw()));
            Intrinsics.checkExpressionValueIsNotNull(st2StLb4, "UnitUtils.st2StLb(measure.ibw.toFloat())");
            measure.setIbw(st2StLb4);
        } else {
            measure.setWeight(String.valueOf(weight));
        }
        FormBody.Builder add = new FormBody.Builder().add("basalmetabolic_rate", String.valueOf(measure.getBasalmetabolic_rate())).add("bmi", String.valueOf(measure.getBmi())).add("body_score", String.valueOf(measure.getBody_score())).add("bodyfat_rate", String.valueOf(measure.getBodyfat_rate())).add("bodywater_rate", String.valueOf(measure.getBodywater_rate())).add("bone_mass", measure.getBone_mass());
        Date date = measure.getDate();
        if (date == null) {
            Intrinsics.throwNpe();
        }
        FormBody build = add.add("measuredate", String.valueOf(date.getTime() / ((long) 1000))).add("ffm", measure.getFfm()).add("member_id", String.valueOf(measure.getMember_id())).add("visceral_fat", String.valueOf(measure.getVisceral_fat())).add("muscle_mass", measure.getMuscle_mass()).add("subcutaneousfat_rate", String.valueOf(measure.getSubcutaneousfat_rate())).add("skeletalfat_percentage", String.valueOf(measure.getSkeletalfat_percentage())).add("protein_rate", String.valueOf(measure.getProtein_rate())).add("obesity_grade", String.valueOf(measure.getObesity_grade())).add("ibw", measure.getIbw()).add("weight", measure.getWeight()).add(ViewHierarchyConstants.DIMENSION_HEIGHT_KEY, measure.getHeight()).add("heightunit", measure.getHeightunit()).add("weightunit", measure.getWeightunit()).add("shape", measure.getShape()).add("devmac", measure.getDevmac()).add("bodyage", String.valueOf(measure.getBodyage())).build();
        Utils.logFormBody(build);
        PrimaryPresenter primaryPresenter = (PrimaryPresenter) getMPresenter();
        if (primaryPresenter != null) {
            primaryPresenter.reportingData(measure, build);
        }
        progressShow();
    }

    private final void reportingOnlyWeightData(Measure measure) {
        Integer num;
        float weight = UnitUtils.getWeight(UnitUtils.getIndex(measure.getWeightunit()), measure.getKgWeight());
        if (Intrinsics.areEqual((Object) measure.getWeightunit(), (Object) Constant.UNIT_WEIGHT[2])) {
            byte[] scanRecord = measure.getScanRecord();
            Integer num2 = null;
            if (scanRecord != null) {
                String byteToHex = ByteUtil.byteToHex(scanRecord[4]);
                Intrinsics.checkExpressionValueIsNotNull(byteToHex, "ByteUtil.byteToHex(it)");
                num = Integer.valueOf(Integer.parseInt(byteToHex, CharsKt.checkRadix(16)));
            } else {
                num = null;
            }
            byte[] scanRecord2 = measure.getScanRecord();
            if (scanRecord2 != null) {
                String byteToHex2 = ByteUtil.byteToHex(scanRecord2[5]);
                Intrinsics.checkExpressionValueIsNotNull(byteToHex2, "ByteUtil.byteToHex(it)");
                num2 = Integer.valueOf(Integer.parseInt(byteToHex2, CharsKt.checkRadix(16)));
            }
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            Object[] objArr = new Object[1];
            if (num2 == null) {
                Intrinsics.throwNpe();
            }
            float f = (float) 10;
            objArr[0] = Float.valueOf(((float) num2.intValue()) / f);
            String format = String.format("%.1f", Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            float parseFloat = Float.parseFloat(format);
            if (parseFloat > f) {
                measure.setWeight(String.valueOf(num) + Config.TRACE_TODAY_VISIT_SPLIT + ((int) ((float) MathKt.roundToInt(parseFloat))));
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(num);
                sb.append(':');
                sb.append(parseFloat);
                measure.setWeight(sb.toString());
            }
        } else {
            measure.setWeight(String.valueOf(weight));
        }
        FormBody.Builder add = new FormBody.Builder().add("bmi", String.valueOf(measure.getBmi()));
        Date date = measure.getDate();
        if (date == null) {
            Intrinsics.throwNpe();
        }
        FormBody build = add.add("measuredate", String.valueOf(date.getTime() / ((long) 1000))).add("member_id", String.valueOf(measure.getMember_id())).add("weight", measure.getWeight()).add(ViewHierarchyConstants.DIMENSION_HEIGHT_KEY, measure.getHeight()).add("heightunit", measure.getHeightunit()).add("weightunit", measure.getWeightunit()).add("devmac", measure.getDevmac()).build();
        Utils.logFormBody(build);
        PrimaryPresenter primaryPresenter = (PrimaryPresenter) getMPresenter();
        if (primaryPresenter != null) {
            primaryPresenter.reportingData(measure, build);
        }
        progressShow();
    }
}
