package com.huayu.tzc.p014ui.fragment;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.baidu.mobstat.Config;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.clj.fastble.BleManager;
import com.clj.fastble.data.BleDevice;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.adapter.HomeTzAdapter;
import com.huayu.tzc.adapter.PopupAdapter;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.BaseFragment;
import com.huayu.tzc.base.BaseListBean;
import com.huayu.tzc.base.Constant;
import com.huayu.tzc.bean.FitBit;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.bean.TzContent;
import com.huayu.tzc.bean.User;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.BodyStateView;
import com.huayu.tzc.customview.CircularView;
import com.huayu.tzc.customview.MyImageView;
import com.huayu.tzc.p014ui.activity.DeviceActivity;
import com.huayu.tzc.p014ui.activity.MeasureActivity;
import com.huayu.tzc.p014ui.activity.ShareActivity;
import com.huayu.tzc.p014ui.activity.TargetActivity;
import com.huayu.tzc.p014ui.activity.TrendActivity;
import com.huayu.tzc.p014ui.activity.history.HistoryActivity;
import com.huayu.tzc.presenter.HomePresenter;
import com.huayu.tzc.utils.BodyLevelUtil;
import com.huayu.tzc.utils.BodyUtil;
import com.huayu.tzc.utils.ByteUtil;
import com.huayu.tzc.utils.EventBusUtils;
import com.huayu.tzc.utils.RangeUtil;
import com.huayu.tzc.utils.SingleClickKt;
import com.huayu.tzc.utils.SnackBarHelper;
import com.huayu.tzc.utils.UnitUtils;
import com.huayu.tzc.utils.Utils;
import com.tencent.mmkv.MMKV;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import kotlin.text.CharsKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p036de.hdodenhof.circleimageview.CircleImageView;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0014\n\u0002\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00042\u00020\u00022\u00020\u0005:\u0006\u0001\u0001\u0001B\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\b\u0010&\u001a\u00020#H\u0016J\b\u0010'\u001a\u00020#H\u0002J0\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020%2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u000200H\u0002J\b\u00102\u001a\u00020#H\u0002J\u0018\u00103\u001a\u00020#2\u0006\u00104\u001a\u00020%2\u0006\u00105\u001a\u00020%H\u0002J\u0010\u00106\u001a\u00020\f2\u0006\u00107\u001a\u00020\fH\u0002J\b\u00108\u001a\u000209H\u0014J\u0016\u0010:\u001a\u00020#2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u001b0<H\u0016J\b\u0010=\u001a\u00020\u0003H\u0014J\u0010\u0010>\u001a\u00020#2\u0006\u00104\u001a\u00020%H\u0002J\b\u0010?\u001a\u00020#H\u0002J\u0010\u0010@\u001a\u00020#2\u0006\u0010A\u001a\u00020BH\u0002J\u0010\u0010C\u001a\u00020#2\u0006\u0010D\u001a\u000209H\u0002J\b\u0010E\u001a\u00020#H\u0002J\u0010\u0010F\u001a\u00020#2\u0006\u0010G\u001a\u00020\u001bH\u0002J\b\u0010H\u001a\u00020#H\u0002J\b\u0010I\u001a\u00020#H\u0014J\u0010\u0010J\u001a\u00020#2\u0006\u0010G\u001a\u00020\u001bH\u0002J\"\u0010K\u001a\u00020#2\u0006\u0010L\u001a\u0002092\u0006\u0010M\u001a\u0002092\b\u0010N\u001a\u0004\u0018\u00010OH\u0016J\u0012\u0010P\u001a\u00020#2\b\u0010Q\u001a\u0004\u0018\u00010BH\u0016J\b\u0010R\u001a\u00020#H\u0016J(\u0010S\u001a\u00020#2\u000e\u0010T\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030U2\u0006\u0010V\u001a\u00020B2\u0006\u0010W\u001a\u000209H\u0016J\u0010\u0010X\u001a\u00020#2\u0006\u0010Y\u001a\u00020 H\u0007J\u0016\u0010X\u001a\u00020#2\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020[0ZH\u0007J\b\u0010\\\u001a\u00020#H\u0016J\b\u0010]\u001a\u00020#H\u0016J\u0016\u0010^\u001a\u00020#2\f\u0010_\u001a\b\u0012\u0004\u0012\u00020\f0`H\u0016J\u0010\u0010a\u001a\u00020#2\u0006\u0010G\u001a\u00020\u001bH\u0002J\u0010\u0010b\u001a\u00020#2\u0006\u0010G\u001a\u00020\u001bH\u0002J\b\u0010c\u001a\u00020#H\u0002J\u0010\u0010d\u001a\u00020%2\u0006\u0010G\u001a\u00020\u001bH\u0002J\u0010\u0010e\u001a\u00020#2\u0006\u0010f\u001a\u00020\u0010H\u0016J\u0010\u0010g\u001a\u00020#2\u0006\u0010G\u001a\u00020\u001bH\u0002J0\u0010h\u001a\u00020\u00102\u0006\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020l2\u0006\u0010m\u001a\u0002092\u0006\u0010n\u001a\u00020%2\u0006\u0010W\u001a\u000209H\u0002J\u0012\u0010o\u001a\u00020#2\b\u0010p\u001a\u0004\u0018\u00010qH\u0016J\b\u0010r\u001a\u00020#H\u0002J(\u0010s\u001a\u00020#2\u000e\u0010T\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030U2\u0006\u0010W\u001a\u0002092\u0006\u0010t\u001a\u000209H\u0002JI\u0010u\u001a\u00020#2\u0006\u0010i\u001a\u00020j2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020\f0w2\u0006\u0010x\u001a\u00020y2\u0006\u0010m\u001a\u0002092\u0006\u0010n\u001a\u00020%2\f\u0010z\u001a\b\u0012\u0004\u0012\u00020\f0wH\u0002¢\u0006\u0002\u0010{J\u0010\u0010|\u001a\u00020#2\u0006\u0010}\u001a\u00020\u0015H\u0002J\u0010\u0010~\u001a\u00020#2\u0006\u0010}\u001a\u00020\u0015H\u0002J\u0010\u0010\u001a\u00020#2\u0006\u0010}\u001a\u00020\u0015H\u0002R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X.¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00060\nR\u00020\u0000X.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0001"}, mo21895d2 = {"Lcom/huayu/tzc/ui/fragment/HomeFragment;", "Lcom/huayu/tzc/base/BaseFragment;", "Lcom/huayu/tzc/contract/MainContract$HomeView;", "Lcom/huayu/tzc/presenter/HomePresenter;", "Landroid/view/View$OnClickListener;", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "()V", "bleReceiver", "Lcom/huayu/tzc/ui/fragment/HomeFragment$BleReceiver;", "dataReceiver", "Lcom/huayu/tzc/ui/fragment/HomeFragment$BleDataReceiver;", "heightUnit", "", "homeTzAdapter", "Lcom/huayu/tzc/adapter/HomeTzAdapter;", "isLook", "", "mScanRecord", "mac", "memberList", "", "Lcom/huayu/tzc/bean/Member;", "pop", "Landroid/widget/PopupWindow;", "scanStatus", "Lcom/huayu/tzc/ui/fragment/HomeFragment$ScanStatus;", "selectMeasure", "Lcom/huayu/tzc/bean/Measure;", "selectUser", "tzContentList", "Lcom/huayu/tzc/bean/TzContent;", "user", "Lcom/huayu/tzc/bean/User;", "weightUnit", "backgroundAlpha", "", "f", "", "checkBleEnabled", "checkMacEmpty", "createDataForRequest", "Lcom/google/android/gms/fitness/data/DataSet;", "dataType", "Lcom/google/android/gms/fitness/data/DataType;", "field", "Lcom/google/android/gms/fitness/data/Field;", "values", "startTime", "", "endTime", "doRegisterReceiver", "fitBit", "weight", "tz", "getFileAddSpace", "replace", "getLayoutId", "", "getMeasures", "measureBean", "Lcom/huayu/tzc/base/BaseListBean;", "getPresenter", "googleFit", "initClick", "initPopupAdapter", "popupWindowMenu", "Landroid/view/View;", "initTz", "id", "initTzAdapter", "initTzData", "measure", "initUser", "initView", "notSaveData", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onClick", "p0", "onDestroy", "onItemClick", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "view", "position", "onMessageEventMain", "event", "Lcom/huayu/tzc/utils/EventBusUtils$EventMessage;", "", "onResume", "onStop", "reporting", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "reportingData", "reportingOnlyWeightData", "requestLocationPermission", "setCirView", "setUserVisibleHint", "isVisibleToUser", "setWeightData", "showContent", "stateView", "Lcom/huayu/tzc/customview/BodyStateView;", "textView", "Landroid/widget/TextView;", "index", "num", "showError", "e", "", "showPopupWidow", "showStateView", "viewPosition", "showView", "contents", "", "nums", "", "colors", "(Lcom/huayu/tzc/customview/BodyStateView;[Ljava/lang/String;[FIF[Ljava/lang/String;)V", "updateData", "member", "updateUser", "updateView", "BleDataReceiver", "BleReceiver", "ScanStatus", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.fragment.HomeFragment */
/* compiled from: HomeFragment.kt */
public final class HomeFragment extends BaseFragment<MainContract.HomeView, HomePresenter> implements View.OnClickListener, MainContract.HomeView, OnItemClickListener {
    private HashMap _$_findViewCache;
    private BleReceiver bleReceiver;
    private BleDataReceiver dataReceiver;
    private String heightUnit = "";
    /* access modifiers changed from: private */
    public HomeTzAdapter homeTzAdapter;
    private boolean isLook = true;
    /* access modifiers changed from: private */
    public String mScanRecord = "";
    /* access modifiers changed from: private */
    public String mac = "";
    private List<Member> memberList = new ArrayList();
    /* access modifiers changed from: private */
    public PopupWindow pop;
    /* access modifiers changed from: private */
    public ScanStatus scanStatus = ScanStatus.STOP;
    private Measure selectMeasure = new Measure();
    /* access modifiers changed from: private */
    public Member selectUser = new Member(0);
    private List<TzContent> tzContentList = new ArrayList();
    private User user = new User();
    /* access modifiers changed from: private */
    public String weightUnit = "";

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, mo21895d2 = {"Lcom/huayu/tzc/ui/fragment/HomeFragment$ScanStatus;", "", "(Ljava/lang/String;I)V", "STOP", "SCANNING", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* renamed from: com.huayu.tzc.ui.fragment.HomeFragment$ScanStatus */
    /* compiled from: HomeFragment.kt */
    private enum ScanStatus {
        STOP,
        SCANNING
    }

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
        View view2 = getView();
        if (view2 == null) {
            return null;
        }
        View findViewById = view2.findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C2128R.C2133layout.fragment_home;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public static final /* synthetic */ HomeTzAdapter access$getHomeTzAdapter$p(HomeFragment homeFragment) {
        HomeTzAdapter homeTzAdapter2 = homeFragment.homeTzAdapter;
        if (homeTzAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("homeTzAdapter");
        }
        return homeTzAdapter2;
    }

    public static final /* synthetic */ PopupWindow access$getPop$p(HomeFragment homeFragment) {
        PopupWindow popupWindow = homeFragment.pop;
        if (popupWindow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pop");
        }
        return popupWindow;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        Parcelable decodeParcelable = MMKV.defaultMMKV().decodeParcelable("user", User.class, new User());
        Intrinsics.checkExpressionValueIsNotNull(decodeParcelable, "MMKV.defaultMMKV().decod…,User::class.java,User())");
        this.user = (User) decodeParcelable;
        initUser();
        doRegisterReceiver();
        initClick();
        initTzAdapter();
        EventBusUtils.register(this);
        checkBleEnabled();
    }

    private final void initUser() {
        this.selectUser = new Member(this.user);
        updateView(this.selectUser);
        this.mac = this.user.getCuurmac();
        this.heightUnit = this.user.getHeightunit();
        this.weightUnit = this.user.getWeightunit();
    }

    public void checkBleEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || !defaultAdapter.isEnabled()) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeTipLayout);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "homeTipLayout");
            linearLayout.setVisibility(0);
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeMacLayout);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "homeMacLayout");
            linearLayout2.setVisibility(8);
            LinearLayout linearLayout3 = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeAuthorityLayout);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "homeAuthorityLayout");
            linearLayout3.setVisibility(8);
            return;
        }
        LinearLayout linearLayout4 = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeTipLayout);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout4, "homeTipLayout");
        linearLayout4.setVisibility(8);
        LinearLayout linearLayout5 = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeMacLayout);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout5, "homeMacLayout");
        linearLayout5.setVisibility(8);
        requestLocationPermission();
    }

    /* access modifiers changed from: private */
    public final void checkMacEmpty() {
        if (this.mac.length() == 0) {
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeTipLayout);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "homeTipLayout");
            linearLayout.setVisibility(8);
            LinearLayout linearLayout2 = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeAuthorityLayout);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "homeAuthorityLayout");
            linearLayout2.setVisibility(8);
            LinearLayout linearLayout3 = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeMacLayout);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "homeMacLayout");
            linearLayout3.setVisibility(0);
            return;
        }
        LinearLayout linearLayout4 = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeTipLayout);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout4, "homeTipLayout");
        linearLayout4.setVisibility(8);
        LinearLayout linearLayout5 = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeAuthorityLayout);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout5, "homeAuthorityLayout");
        linearLayout5.setVisibility(8);
        LinearLayout linearLayout6 = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeMacLayout);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout6, "homeMacLayout");
        linearLayout6.setVisibility(8);
    }

    private final void doRegisterReceiver() {
        this.bleReceiver = new BleReceiver();
        IntentFilter intentFilter = new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED");
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        BleReceiver bleReceiver2 = this.bleReceiver;
        if (bleReceiver2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleReceiver");
        }
        context.registerReceiver(bleReceiver2, intentFilter);
        this.dataReceiver = new BleDataReceiver();
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        BleDataReceiver bleDataReceiver = this.dataReceiver;
        if (bleDataReceiver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataReceiver");
        }
        context2.registerReceiver(bleDataReceiver, new IntentFilter("com.huayu.tzc.ble"));
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, mo21895d2 = {"Lcom/huayu/tzc/ui/fragment/HomeFragment$BleReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/huayu/tzc/ui/fragment/HomeFragment;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* renamed from: com.huayu.tzc.ui.fragment.HomeFragment$BleReceiver */
    /* compiled from: HomeFragment.kt */
    public final class BleReceiver extends BroadcastReceiver {
        public BleReceiver() {
        }

        public void onReceive(@NotNull Context context, @NotNull Intent intent) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(intent, "intent");
            if (Intrinsics.areEqual((Object) "android.bluetooth.adapter.action.STATE_CHANGED", (Object) intent.getAction())) {
                int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", Integer.MIN_VALUE);
                if (intExtra == 10) {
                    LinearLayout linearLayout = (LinearLayout) HomeFragment.this._$_findCachedViewById(C2128R.C2131id.homeTipLayout);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout, "homeTipLayout");
                    linearLayout.setVisibility(0);
                    LinearLayout linearLayout2 = (LinearLayout) HomeFragment.this._$_findCachedViewById(C2128R.C2131id.homeMacLayout);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout2, "homeMacLayout");
                    linearLayout2.setVisibility(8);
                    LinearLayout linearLayout3 = (LinearLayout) HomeFragment.this._$_findCachedViewById(C2128R.C2131id.homeAuthorityLayout);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout3, "homeAuthorityLayout");
                    linearLayout3.setVisibility(8);
                    try {
                        BleManager.getInstance().cancelScan();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (intExtra == 12) {
                    LinearLayout linearLayout4 = (LinearLayout) HomeFragment.this._$_findCachedViewById(C2128R.C2131id.homeTipLayout);
                    Intrinsics.checkExpressionValueIsNotNull(linearLayout4, "homeTipLayout");
                    linearLayout4.setVisibility(8);
                    HomeFragment.this.checkBleEnabled();
                }
            }
        }
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, mo21895d2 = {"Lcom/huayu/tzc/ui/fragment/HomeFragment$BleDataReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/huayu/tzc/ui/fragment/HomeFragment;)V", "onReceive", "", "p0", "Landroid/content/Context;", "p1", "Landroid/content/Intent;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* renamed from: com.huayu.tzc.ui.fragment.HomeFragment$BleDataReceiver */
    /* compiled from: HomeFragment.kt */
    public final class BleDataReceiver extends BroadcastReceiver {
        public BleDataReceiver() {
        }

        public void onReceive(@Nullable Context context, @Nullable Intent intent) {
            if (Intrinsics.areEqual((Object) intent != null ? intent.getAction() : null, (Object) "com.huayu.tzc.ble") && HomeFragment.this.scanStatus == ScanStatus.SCANNING) {
                Parcelable parcelableExtra = intent.getParcelableExtra(ShareConstants.WEB_DIALOG_PARAM_DATA);
                Intrinsics.checkExpressionValueIsNotNull(parcelableExtra, "p1.getParcelableExtra(\"data\")");
                BleDevice bleDevice = (BleDevice) parcelableExtra;
                if ((HomeFragment.this.mac.length() > 0) && !Intrinsics.areEqual((Object) ByteUtil.bytesToHex(bleDevice.getScanRecord()), (Object) HomeFragment.this.mScanRecord)) {
                    String mac = bleDevice.getMac();
                    HomeFragment homeFragment = HomeFragment.this;
                    if (StringsKt.equals(mac, homeFragment.getFileAddSpace(homeFragment.mac), true) && Intrinsics.areEqual((Object) "10", (Object) ByteUtil.byteToHex(bleDevice.getScanRecord()[0])) && Intrinsics.areEqual((Object) "ff", (Object) ByteUtil.byteToHex(bleDevice.getScanRecord()[1]))) {
                        HomeFragment.this.scanStatus = ScanStatus.STOP;
                        HomeFragment homeFragment2 = HomeFragment.this;
                        Intent putExtra = new Intent(homeFragment2.getContext(), MeasureActivity.class).putExtra("member", HomeFragment.this.selectUser);
                        HomeFragment homeFragment3 = HomeFragment.this;
                        homeFragment2.startActivityForResult(putExtra.putExtra("mac", homeFragment3.getFileAddSpace(homeFragment3.mac)).putExtra("weightUnit", HomeFragment.this.weightUnit).putExtra("mScanRecord", HomeFragment.this.mScanRecord), 810);
                    }
                }
            }
        }
    }

    private final void initClick() {
        View.OnClickListener onClickListener = this;
        SingleClickKt.singleClick$default((View) (CircleImageView) _$_findCachedViewById(C2128R.C2131id.homeHeader), onClickListener, 0, 2, (Object) null);
        View.OnClickListener onClickListener2 = onClickListener;
        SingleClickKt.singleClick$default((View) (ImageView) _$_findCachedViewById(C2128R.C2131id.homeBle), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (ImageView) _$_findCachedViewById(C2128R.C2131id.homeShare), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeTargetLayout), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeTzlLayout), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeQs), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (ImageView) _$_findCachedViewById(C2128R.C2131id.homeHistory), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (TextView) _$_findCachedViewById(C2128R.C2131id.homeTip), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (TextView) _$_findCachedViewById(C2128R.C2131id.homeMac), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (TextView) _$_findCachedViewById(C2128R.C2131id.homeAuthority), onClickListener2, 0, 2, (Object) null);
        SingleClickKt.singleClick$default((View) (CircularView) _$_findCachedViewById(C2128R.C2131id.homeCirView), onClickListener2, 0, 2, (Object) null);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public HomePresenter getPresenter() {
        return new HomePresenter();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void onMessageEventMain(@NotNull User user2) {
        Intrinsics.checkParameterIsNotNull(user2, NotificationCompat.CATEGORY_EVENT);
        this.mac = user2.getCuurmac();
        this.user = user2;
        this.selectUser = new Member(user2);
        this.heightUnit = user2.getHeightunit();
        this.weightUnit = user2.getWeightunit();
        updateUser(this.selectUser);
        checkBleEnabled();
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public final void onMessageEventMain(@NotNull EventBusUtils.EventMessage<Object> eventMessage) {
        Intrinsics.checkParameterIsNotNull(eventMessage, NotificationCompat.CATEGORY_EVENT);
        if (eventMessage.getCode() == 5) {
            this.memberList.clear();
            List<Member> list = this.memberList;
            List<Member> userBeanList = eventMessage.getUserBeanList();
            Intrinsics.checkExpressionValueIsNotNull(userBeanList, "event.userBeanList");
            list.addAll(userBeanList);
            int size = this.memberList.size();
            for (int i = 0; i < size; i++) {
                if (this.memberList.get(i).getMember_id() == this.selectUser.getMember_id()) {
                    updateData(this.memberList.get(i));
                    return;
                }
            }
        }
    }

    private final void initTzAdapter() {
        this.homeTzAdapter = new HomeTzAdapter(this.tzContentList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
        gridLayoutManager.setSpanSizeLookup(new HomeFragment$initTzAdapter$1(this));
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.homeRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "homeRecyclerview");
        recyclerView.setNestedScrollingEnabled(true);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.homeRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "homeRecyclerview");
        HomeTzAdapter homeTzAdapter2 = this.homeTzAdapter;
        if (homeTzAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("homeTzAdapter");
        }
        recyclerView2.setAdapter(homeTzAdapter2);
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.homeRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView3, "homeRecyclerview");
        recyclerView3.setLayoutManager(gridLayoutManager);
        HomeTzAdapter homeTzAdapter3 = this.homeTzAdapter;
        if (homeTzAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("homeTzAdapter");
        }
        homeTzAdapter3.setOnItemClickListener(this);
        ((CircularView) _$_findCachedViewById(C2128R.C2131id.homeCirView)).setWeight(0.0f, "", "");
        initTzData(new Measure());
    }

    private final void updateUser(Member member) {
        updateView(member);
        initTz(member.getMember_id());
    }

    private final void initTz(int i) {
        HomePresenter homePresenter = (HomePresenter) getMPresenter();
        if (homePresenter != null) {
            homePresenter.getMeasures(1, 1, i);
        }
        progressShow();
    }

    private final void updateView(Member member) {
        if (!TextUtils.isEmpty(member.getM_avatar())) {
            Intrinsics.checkExpressionValueIsNotNull(Glide.with((Fragment) this).load(member.getM_avatar()).into((ImageView) (CircleImageView) _$_findCachedViewById(C2128R.C2131id.homeHeader)), "Glide.with(this).load(me…_avatar).into(homeHeader)");
        } else {
            ((CircleImageView) _$_findCachedViewById(C2128R.C2131id.homeHeader)).setImageResource(Utils.getImgRes(member.getM_gender()));
        }
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.homeName);
        Intrinsics.checkExpressionValueIsNotNull(textView, "homeName");
        textView.setText(member.getM_nickname());
        TextView textView2 = (TextView) _$_findCachedViewById(C2128R.C2131id.homeTarget);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "homeTarget");
        textView2.setText(member.getM_weight() + this.weightUnit);
        TextView textView3 = (TextView) _$_findCachedViewById(C2128R.C2131id.homeTzl);
        Intrinsics.checkExpressionValueIsNotNull(textView3, "homeTzl");
        StringBuilder sb = new StringBuilder();
        sb.append(member.getM_goal());
        sb.append('%');
        textView3.setText(sb.toString());
    }

    public void onDestroy() {
        super.onDestroy();
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        BleReceiver bleReceiver2 = this.bleReceiver;
        if (bleReceiver2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bleReceiver");
        }
        context.unregisterReceiver(bleReceiver2);
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        BleDataReceiver bleDataReceiver = this.dataReceiver;
        if (bleDataReceiver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataReceiver");
        }
        context2.unregisterReceiver(bleDataReceiver);
        EventBus.getDefault().unregister(this);
    }

    public void onClick(@Nullable View view) {
        Integer valueOf = view != null ? Integer.valueOf(view.getId()) : null;
        if (valueOf != null && valueOf.intValue() == C2128R.C2131id.homeHeader) {
            showPopupWidow();
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.homeBle) {
            if (!Utils.isLocServiceEnable(getContext())) {
                ToastUtils.show((CharSequence) getString(C2128R.string.mobile_location));
                startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 304);
                return;
            }
            FragmentActivity activity = getActivity();
            if (activity == null) {
                Intrinsics.throwNpe();
            }
            activity.startActivityForResult(new Intent(getContext(), DeviceActivity.class).putExtra("mac", this.mac), 801);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.homeShare) {
            if (!Utils.isPackageExisted(getContext(), "com.facebook.katana")) {
                ToastUtils.show((CharSequence) getString(C2128R.string.noinstall_facebook));
            } else {
                startActivity(new Intent(getContext(), ShareActivity.class).putExtra(ShareConstants.WEB_DIALOG_PARAM_DATA, this.selectMeasure).putExtra("member", this.selectUser));
            }
        } else if ((valueOf != null && valueOf.intValue() == C2128R.C2131id.homeTargetLayout) || (valueOf != null && valueOf.intValue() == C2128R.C2131id.homeTzlLayout)) {
            FragmentActivity activity2 = getActivity();
            if (activity2 == null) {
                Intrinsics.throwNpe();
            }
            activity2.startActivityForResult(new Intent(getContext(), TargetActivity.class).putExtra("member", this.selectUser).putExtra("unit", this.weightUnit), 802);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.homeQs) {
            startActivity(new Intent(getContext(), TrendActivity.class).putExtra("unit", this.weightUnit).putExtra("member", this.selectUser));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.homeHistory) {
            startActivity(new Intent(getContext(), HistoryActivity.class).putExtra("member", this.selectUser));
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.homeTip) {
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            if (defaultAdapter != null && !defaultAdapter.isEnabled()) {
                startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 303);
            }
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.homeMac) {
            FragmentActivity activity3 = getActivity();
            if (activity3 == null) {
                Intrinsics.throwNpe();
            }
            activity3.startActivityForResult(new Intent(getContext(), DeviceActivity.class).putExtra("mac", this.mac), 801);
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.homeAuthority) {
            requestLocationPermission();
        } else if (valueOf != null && valueOf.intValue() == C2128R.C2131id.homeCirView) {
            SnackBarHelper.showLong((Activity) getActivity(), getString(C2128R.string.stand));
        }
    }

    private final void initTzData(Measure measure) {
        Measure measure2 = measure;
        this.selectMeasure = measure2;
        this.tzContentList.clear();
        int m_gender = this.selectUser.getM_gender();
        int userAge = this.selectUser.getUserAge();
        float height = (float) UnitUtils.getHeight(measure.getHeight());
        Context context = getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context, "context!!");
        String[] stringArray = context.getResources().getStringArray(C2128R.array.details_body);
        Intrinsics.checkExpressionValueIsNotNull(stringArray, "context!!.resources.getS…ray(R.array.details_body)");
        Context context2 = getContext();
        if (context2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(context2, "context!!");
        String[] stringArray2 = context2.getResources().getStringArray(C2128R.array.shape);
        Intrinsics.checkExpressionValueIsNotNull(stringArray2, "context!!.resources.getStringArray(R.array.shape)");
        String tag = getTAG();
        Log.e(tag, "initTzData: " + height);
        int weightLevel = BodyLevelUtil.getWeightLevel(UnitUtils.getKgWeight(UnitUtils.getFloatWeight(measure.getWeight()), measure.getWeightunit()), m_gender, height);
        String tag2 = getTAG();
        Log.e(tag2, "initTzData: " + measure.getHeight() + "  " + height + "   ");
        int i = -1;
        if (measure.getMuscle_mass().length() > 0) {
            float floatWeight = UnitUtils.getFloatWeight(measure.getMuscle_mass());
            float[] fatRangFalse = RangeUtil.getFatRangFalse(m_gender, userAge);
            Intrinsics.checkExpressionValueIsNotNull(fatRangFalse, "RangeUtil.getFatRangFalse(sex, age)");
            float[] units = BodyUtil.getUnits(RangeUtil.getJRRange(m_gender, height), this.weightUnit);
            Intrinsics.checkExpressionValueIsNotNull(units, "getUnits(RangeUtil.getJR…(sex, height),weightUnit)");
            i = measure2.getTx((float) measure.getBodyfat_rate(), floatWeight, fatRangFalse, units);
        }
        List<TzContent> list = this.tzContentList;
        String weight = measure.getWeight();
        String str = this.weightUnit;
        float floatWeight2 = UnitUtils.getFloatWeight(measure.getWeight());
        String weightColor = BodyUtil.getWeightColor(weightLevel);
        Intrinsics.checkExpressionValueIsNotNull(weightColor, "getWeightColor(level)");
        String str2 = stringArray[0];
        Intrinsics.checkExpressionValueIsNotNull(str2, "titles[0]");
        TzContent tzContent = r12;
        TzContent tzContent2 = new TzContent(1, weight, str, floatWeight2, weightColor, str2);
        list.add(tzContent);
        List<TzContent> list2 = this.tzContentList;
        String valueOf = String.valueOf(measure.getBmi());
        float bmi = (float) measure.getBmi();
        String str3 = valueOf;
        String colorBmi = BodyUtil.getColorBmi(Constant.BMI_FALSE, (float) measure.getBmi());
        Intrinsics.checkExpressionValueIsNotNull(colorBmi, "getColorBmi(BMI_FALSE, measure.bmi.toFloat())");
        String str4 = stringArray[1];
        Intrinsics.checkExpressionValueIsNotNull(str4, "titles[1]");
        TzContent tzContent3 = r9;
        TzContent tzContent4 = new TzContent(1, str3, "", bmi, colorBmi, str4);
        list2.add(tzContent3);
        List<TzContent> list3 = this.tzContentList;
        String color4 = BodyUtil.getColor4(RangeUtil.getFatRangFalse(m_gender, userAge), (float) measure.getBodyfat_rate());
        Intrinsics.checkExpressionValueIsNotNull(color4, "getColor4(RangeUtil.getF…e.bodyfat_rate.toFloat())");
        String str5 = stringArray[2];
        Intrinsics.checkExpressionValueIsNotNull(str5, "titles[2]");
        list3.add(new TzContent(1, String.valueOf(measure.getBodyfat_rate()), "%", (float) measure.getBodyfat_rate(), color4, str5));
        this.tzContentList.add(new TzContent(2));
        if (i >= 0) {
            List<TzContent> list4 = this.tzContentList;
            String str6 = stringArray2[i];
            Intrinsics.checkExpressionValueIsNotNull(str6, "txString[tx]");
            String str7 = stringArray[3];
            Intrinsics.checkExpressionValueIsNotNull(str7, "titles[3]");
            TzContent tzContent5 = r10;
            TzContent tzContent6 = new TzContent(1, str6, "", (float) i, "#ff58d99e", str7);
            list4.add(tzContent5);
        } else {
            List<TzContent> list5 = this.tzContentList;
            String str8 = stringArray[3];
            Intrinsics.checkExpressionValueIsNotNull(str8, "titles[3]");
            TzContent tzContent7 = r9;
            TzContent tzContent8 = new TzContent(1, String.valueOf(i), "", (float) i, "#ff58d99e", str8);
            list5.add(tzContent7);
        }
        List<TzContent> list6 = this.tzContentList;
        String ffm = measure.getFfm();
        String weightunit = measure.getWeightunit();
        float floatWeight3 = UnitUtils.getFloatWeight(measure.getFfm());
        String str9 = stringArray[4];
        Intrinsics.checkExpressionValueIsNotNull(str9, "titles[4]");
        list6.add(new TzContent(1, ffm, weightunit, floatWeight3, "#ff58d99e", str9));
        List<TzContent> list7 = this.tzContentList;
        String valueOf2 = String.valueOf(measure.getSubcutaneousfat_rate());
        float subcutaneousfat_rate = (float) measure.getSubcutaneousfat_rate();
        String color3 = BodyUtil.getColor3(RangeUtil.getPxRang(m_gender), (float) measure.getSubcutaneousfat_rate());
        Intrinsics.checkExpressionValueIsNotNull(color3, "getColor3(RangeUtil.getP…aneousfat_rate.toFloat())");
        String str10 = stringArray[5];
        Intrinsics.checkExpressionValueIsNotNull(str10, "titles[5]");
        list7.add(new TzContent(1, valueOf2, "%", subcutaneousfat_rate, color3, str10));
        this.tzContentList.add(new TzContent(2));
        List<TzContent> list8 = this.tzContentList;
        String valueOf3 = String.valueOf(measure.getVisceral_fat());
        float visceral_fat = measure.getVisceral_fat();
        String color42 = BodyUtil.getColor4(Constant.NZZZ, measure.getVisceral_fat());
        Intrinsics.checkExpressionValueIsNotNull(color42, "getColor4(NZZZ, measure.visceral_fat)");
        String str11 = stringArray[6];
        Intrinsics.checkExpressionValueIsNotNull(str11, "titles[6]");
        list8.add(new TzContent(1, valueOf3, "", visceral_fat, color42, str11));
        List<TzContent> list9 = this.tzContentList;
        String valueOf4 = String.valueOf(measure.getBodywater_rate());
        float bodywater_rate = (float) measure.getBodywater_rate();
        String colorTs = BodyUtil.getColorTs(RangeUtil.getTsRang(m_gender), (float) measure.getBodywater_rate());
        Intrinsics.checkExpressionValueIsNotNull(colorTs, "getColorTs(RangeUtil.get…bodywater_rate.toFloat())");
        String str12 = stringArray[7];
        Intrinsics.checkExpressionValueIsNotNull(str12, "titles[7]");
        list9.add(new TzContent(1, valueOf4, "%", bodywater_rate, colorTs, str12));
        List<TzContent> list10 = this.tzContentList;
        String valueOf5 = String.valueOf(measure.getSkeletalfat_percentage());
        float skeletalfat_percentage = (float) measure.getSkeletalfat_percentage();
        String colorTs2 = BodyUtil.getColorTs(Constant.GGJ, (float) measure.getSkeletalfat_percentage());
        Intrinsics.checkExpressionValueIsNotNull(colorTs2, "getColorTs(GGJ, measure.…fat_percentage.toFloat())");
        String str13 = stringArray[8];
        Intrinsics.checkExpressionValueIsNotNull(str13, "titles[8]");
        list10.add(new TzContent(1, valueOf5, "%", skeletalfat_percentage, colorTs2, str13));
        this.tzContentList.add(new TzContent(2));
        List<TzContent> list11 = this.tzContentList;
        String muscle_mass = measure.getMuscle_mass();
        String weightunit2 = measure.getWeightunit();
        float floatWeight4 = UnitUtils.getFloatWeight(measure.getMuscle_mass());
        String colorTs3 = BodyUtil.getColorTs(BodyUtil.getUnits(RangeUtil.getJRRange(m_gender, height), measure.getWeightunit()), UnitUtils.getFloatWeight(measure.getMuscle_mass()));
        Intrinsics.checkExpressionValueIsNotNull(colorTs3, "getColorTs(getUnits(Rang…ght(measure.muscle_mass))");
        String str14 = stringArray[9];
        Intrinsics.checkExpressionValueIsNotNull(str14, "titles[9]");
        list11.add(new TzContent(1, muscle_mass, weightunit2, floatWeight4, colorTs3, str14));
        List<TzContent> list12 = this.tzContentList;
        String bone_mass = measure.getBone_mass();
        String weightunit3 = measure.getWeightunit();
        float floatWeight5 = UnitUtils.getFloatWeight(measure.getBone_mass());
        String colorTs4 = BodyUtil.getColorTs(BodyUtil.getUnits(Constant.f1685GL, measure.getWeightunit()), UnitUtils.getFloatWeight(measure.getBone_mass()));
        Intrinsics.checkExpressionValueIsNotNull(colorTs4, "getColorTs(getUnits(GL, …eight(measure.bone_mass))");
        String str15 = stringArray[10];
        Intrinsics.checkExpressionValueIsNotNull(str15, "titles[10]");
        list12.add(new TzContent(1, bone_mass, weightunit3, floatWeight5, colorTs4, str15));
        List<TzContent> list13 = this.tzContentList;
        String colorTs5 = BodyUtil.getColorTs(Constant.DBZ, (float) measure.getProtein_rate());
        Intrinsics.checkExpressionValueIsNotNull(colorTs5, "getColorTs(DBZ, measure.protein_rate.toFloat())");
        String str16 = stringArray[11];
        Intrinsics.checkExpressionValueIsNotNull(str16, "titles[11]");
        list13.add(new TzContent(1, String.valueOf(measure.getProtein_rate()), "%", (float) measure.getProtein_rate(), colorTs5, str16));
        this.tzContentList.add(new TzContent(2));
        List<TzContent> list14 = this.tzContentList;
        String color2 = BodyUtil.getColor2(Constant.JCDX, (float) measure.getBasalmetabolic_rate());
        Intrinsics.checkExpressionValueIsNotNull(color2, "getColor2(JCDX,measure.b…metabolic_rate.toFloat())");
        String str17 = stringArray[12];
        Intrinsics.checkExpressionValueIsNotNull(str17, "titles[12]");
        list14.add(new TzContent(1, String.valueOf(measure.getBasalmetabolic_rate()), "kcal", (float) measure.getBasalmetabolic_rate(), color2, str17));
        List<TzContent> list15 = this.tzContentList;
        String str18 = stringArray[13];
        Intrinsics.checkExpressionValueIsNotNull(str18, "titles[13]");
        list15.add(new TzContent(1, String.valueOf(measure.getBodyage()), "", (float) measure.getBodyage(), "#ff58d99e", str18));
        List<TzContent> list16 = this.tzContentList;
        String valueOf6 = String.valueOf(measure.getBody_score());
        float body_score = (float) measure.getBody_score();
        String color1 = BodyUtil.getColor1((float) measure.getBody_score());
        Intrinsics.checkExpressionValueIsNotNull(color1, "getColor1(measure.body_score.toFloat())");
        String str19 = stringArray[14];
        Intrinsics.checkExpressionValueIsNotNull(str19, "titles[14]");
        list16.add(new TzContent(1, valueOf6, "", body_score, color1, str19));
        this.tzContentList.add(new TzContent(2));
        List<TzContent> list17 = this.tzContentList;
        String str20 = stringArray[13];
        Intrinsics.checkExpressionValueIsNotNull(str20, "titles[13]");
        list17.add(new TzContent(3, String.valueOf(measure.getBodyage()), "", (float) measure.getBodyage(), "#ff58d99e", str20));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);
        if (measure.getMeasuredate() == 0) {
            TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.homeTime);
            Intrinsics.checkExpressionValueIsNotNull(textView, "homeTime");
            textView.setText(simpleDateFormat.format(new Date(System.currentTimeMillis())));
        } else {
            TextView textView2 = (TextView) _$_findCachedViewById(C2128R.C2131id.homeTime);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "homeTime");
            textView2.setText(simpleDateFormat.format(measure.getMeasureDate()));
        }
        HomeTzAdapter homeTzAdapter2 = this.homeTzAdapter;
        if (homeTzAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("homeTzAdapter");
        }
        homeTzAdapter2.notifyDataSetChanged();
    }

    public void reporting(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
    }

    public void getMeasures(@NotNull BaseListBean<Measure> baseListBean) {
        Intrinsics.checkParameterIsNotNull(baseListBean, "measureBean");
        progressDissmiss();
        Measure measure = new Measure();
        if (!baseListBean.getRows().isEmpty()) {
            measure = baseListBean.getRows().get(0);
        }
        initTzData(measure);
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }

    private final void showPopupWidow() {
        View inflate = getLayoutInflater().inflate(C2128R.C2133layout.dialog_option, (ViewGroup) null, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "layoutInflater.inflate(R…alog_option, null, false)");
        initPopupAdapter(inflate);
        this.pop = new PopupWindow(inflate, -1, -1, true);
        PopupWindow popupWindow = this.pop;
        if (popupWindow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pop");
        }
        popupWindow.showAsDropDown((CircleImageView) _$_findCachedViewById(C2128R.C2131id.homeHeader), 0, 0);
        PopupWindow popupWindow2 = this.pop;
        if (popupWindow2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pop");
        }
        popupWindow2.setTouchable(true);
        backgroundAlpha(0.4f);
        PopupWindow popupWindow3 = this.pop;
        if (popupWindow3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("pop");
        }
        popupWindow3.setOnDismissListener(new HomeFragment$showPopupWidow$1(this));
        inflate.setOnTouchListener(new HomeFragment$showPopupWidow$2(this));
    }

    private final void initPopupAdapter(View view) {
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = (List) new ArrayList();
        ((List) objectRef.element).add(new Member(this.user));
        ((List) objectRef.element).addAll(this.memberList);
        String string = getString(C2128R.string.mine_add);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.mine_add)");
        ((List) objectRef.element).add(new Member(string, -1));
        View findViewById = view.findViewById(C2128R.C2131id.popupRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "popupWindowMenu.findView…d(R.id.popupRecyclerview)");
        RecyclerView recyclerView = (RecyclerView) findViewById;
        PopupAdapter popupAdapter = new PopupAdapter((List) objectRef.element);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(popupAdapter);
        popupAdapter.setOnItemClickListener(new HomeFragment$initPopupAdapter$1(this, objectRef));
    }

    /* access modifiers changed from: private */
    public final void updateData(Member member) {
        updateView(member);
        ((CircularView) _$_findCachedViewById(C2128R.C2131id.homeCirView)).setWeight(0.0f, "", "");
        HomePresenter homePresenter = (HomePresenter) getMPresenter();
        if (homePresenter != null) {
            homePresenter.getMeasures(1, 1, member.getMember_id());
        }
        progressShow();
    }

    /* access modifiers changed from: private */
    public final void backgroundAlpha(float f) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity!!");
        Window window = activity.getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "activity!!.window");
        WindowManager.LayoutParams attributes = window.getAttributes();
        Intrinsics.checkExpressionValueIsNotNull(attributes, "activity!!.window.attributes");
        attributes.alpha = f;
        FragmentActivity activity2 = getActivity();
        if (activity2 == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(activity2, "activity!!");
        Window window2 = activity2.getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window2, "activity!!.window");
        window2.setAttributes(attributes);
    }

    public void onItemClick(@NotNull BaseQuickAdapter<?, ?> baseQuickAdapter, @NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "adapter");
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
        if (baseQuickAdapter.getItemViewType(i) == 1) {
            int size = this.tzContentList.size();
            for (int i2 = 0; i2 < size; i2++) {
                if (i2 == 3 || i2 == 7 || i2 == 11 || i2 == 15 || i2 == 19) {
                    BodyStateView bodyStateView = (BodyStateView) baseQuickAdapter.getViewByPosition(i2, C2128R.C2131id.home_view);
                    LinearLayout linearLayout = (LinearLayout) baseQuickAdapter.getViewByPosition(i2, C2128R.C2131id.home_linear);
                    TextView textView = (TextView) baseQuickAdapter.getViewByPosition(i2, C2128R.C2131id.home_text);
                    if (bodyStateView != null) {
                        bodyStateView.setVisibility(8);
                    }
                    if (linearLayout != null) {
                        linearLayout.setVisibility(8);
                    }
                    if (textView != null) {
                        textView.setVisibility(8);
                    }
                } else {
                    MyImageView myImageView = (MyImageView) baseQuickAdapter.getViewByPosition(i2, C2128R.C2131id.home_img);
                    if (myImageView != null) {
                        myImageView.setVisibility(4);
                    }
                }
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                    showStateView(baseQuickAdapter, i, 3);
                    return;
                case 4:
                case 5:
                case 6:
                    showStateView(baseQuickAdapter, i, 7);
                    return;
                case 8:
                case 9:
                case 10:
                    showStateView(baseQuickAdapter, i, 11);
                    return;
                case 12:
                case 13:
                case 14:
                    showStateView(baseQuickAdapter, i, 15);
                    return;
                case 16:
                case 17:
                case 18:
                    showStateView(baseQuickAdapter, i, 19);
                    return;
                default:
                    return;
            }
        }
    }

    private final void showStateView(BaseQuickAdapter<?, ?> baseQuickAdapter, int i, int i2) {
        BodyStateView bodyStateView = (BodyStateView) baseQuickAdapter.getViewByPosition(i2, C2128R.C2131id.home_view);
        LinearLayout linearLayout = (LinearLayout) baseQuickAdapter.getViewByPosition(i2, C2128R.C2131id.home_linear);
        TextView textView = (TextView) baseQuickAdapter.getViewByPosition(i2, C2128R.C2131id.home_text);
        if (this.tzContentList.get(i).isShow()) {
            MyImageView myImageView = (MyImageView) baseQuickAdapter.getViewByPosition(i, C2128R.C2131id.home_img);
            if (myImageView != null) {
                myImageView.setVisibility(4);
            }
            if (bodyStateView != null) {
                bodyStateView.setVisibility(8);
            }
            if (textView != null) {
                textView.setVisibility(8);
            }
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
            for (TzContent show : this.tzContentList) {
                show.setShow(false);
            }
            return;
        }
        if (!(bodyStateView == null || textView == null)) {
            showContent(bodyStateView, textView, 0, this.tzContentList.get(i).getValue(), i);
        }
        for (TzContent show2 : this.tzContentList) {
            show2.setShow(false);
        }
        MyImageView myImageView2 = (MyImageView) baseQuickAdapter.getViewByPosition(i, C2128R.C2131id.home_img);
        if (myImageView2 != null) {
            myImageView2.setVisibility(0);
        }
        this.tzContentList.get(i).setShow(true);
        if (textView != null) {
            textView.setVisibility(0);
        }
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
        if (linearLayout != null) {
            ((NestedScrollView) _$_findCachedViewById(C2128R.C2131id.homeScrollview)).scrollTo(0, linearLayout.getBottom() + 200);
        }
    }

    private final boolean showContent(BodyStateView bodyStateView, TextView textView, int i, float f, int i2) {
        int i3 = i2;
        if (4 <= i3 && 6 >= i3) {
            i3--;
        } else if (8 <= i3 && 10 >= i3) {
            i3 -= 2;
        } else if (12 <= i3 && 14 >= i3) {
            i3 -= 3;
        } else if (16 <= i3 && 18 >= i3) {
            i3 -= 4;
        }
        int i4 = i3;
        int m_gender = this.selectUser.getM_gender();
        int userAge = this.selectUser.getUserAge();
        float height = (float) UnitUtils.getHeight(this.user.getU_height());
        String weightunit = this.user.getWeightunit();
        switch (i4) {
            case 0:
                String[] stringArray = getResources().getStringArray(C2128R.array.weight_status);
                Intrinsics.checkExpressionValueIsNotNull(stringArray, "resources.getStringArray(R.array.weight_status)");
                float[] units = BodyUtil.getUnits(RangeUtil.getWeightArray(m_gender, height), weightunit);
                Intrinsics.checkExpressionValueIsNotNull(units, "getUnits(RangeUtil.getWe…tArray(sex, height),unit)");
                String[] strArr = Constant.COLORS4;
                Intrinsics.checkExpressionValueIsNotNull(strArr, "COLORS4");
                showView(bodyStateView, stringArray, units, i, f, strArr);
                break;
            case 1:
                String[] stringArray2 = getResources().getStringArray(C2128R.array.have_red_status);
                Intrinsics.checkExpressionValueIsNotNull(stringArray2, "resources.getStringArray(R.array.have_red_status)");
                float[] fArr = Constant.BMI_FALSE;
                Intrinsics.checkExpressionValueIsNotNull(fArr, "BMI_FALSE");
                String[] strArr2 = Constant.COLORS3;
                Intrinsics.checkExpressionValueIsNotNull(strArr2, "COLORS3");
                showView(bodyStateView, stringArray2, fArr, i, f, strArr2);
                break;
            case 2:
                String[] stringArray3 = getResources().getStringArray(C2128R.array.fat_status);
                Intrinsics.checkExpressionValueIsNotNull(stringArray3, "resources.getStringArray(R.array.fat_status)");
                float[] fatRangFalse = RangeUtil.getFatRangFalse(m_gender, userAge);
                Intrinsics.checkExpressionValueIsNotNull(fatRangFalse, "RangeUtil.getFatRangFalse(sex, age)");
                String[] strArr3 = Constant.COLORSFat;
                Intrinsics.checkExpressionValueIsNotNull(strArr3, "COLORSFat");
                showView(bodyStateView, stringArray3, fatRangFalse, i, f, strArr3);
                break;
            case 5:
                String[] stringArray4 = getResources().getStringArray(C2128R.array.have_red_status);
                Intrinsics.checkExpressionValueIsNotNull(stringArray4, "resources.getStringArray(R.array.have_red_status)");
                float[] pxRang = RangeUtil.getPxRang(m_gender);
                Intrinsics.checkExpressionValueIsNotNull(pxRang, "RangeUtil.getPxRang(sex)");
                String[] strArr4 = Constant.COLORS3;
                Intrinsics.checkExpressionValueIsNotNull(strArr4, "COLORS3");
                showView(bodyStateView, stringArray4, pxRang, i, f, strArr4);
                break;
            case 6:
                String[] stringArray5 = getResources().getStringArray(C2128R.array.visceral_fat);
                Intrinsics.checkExpressionValueIsNotNull(stringArray5, "resources.getStringArray(R.array.visceral_fat)");
                float[] fArr2 = Constant.NZZZ;
                Intrinsics.checkExpressionValueIsNotNull(fArr2, "NZZZ");
                String[] strArr5 = Constant.COLORS;
                Intrinsics.checkExpressionValueIsNotNull(strArr5, "COLORS");
                showView(bodyStateView, stringArray5, fArr2, i, f, strArr5);
                break;
            case 7:
                String[] stringArray6 = getResources().getStringArray(C2128R.array.no_red_status);
                Intrinsics.checkExpressionValueIsNotNull(stringArray6, "resources.getStringArray(R.array.no_red_status)");
                float[] tsRang = RangeUtil.getTsRang(m_gender);
                Intrinsics.checkExpressionValueIsNotNull(tsRang, "RangeUtil.getTsRang(sex)");
                String[] strArr6 = Constant.COLORS2;
                Intrinsics.checkExpressionValueIsNotNull(strArr6, "COLORS2");
                showView(bodyStateView, stringArray6, tsRang, i, f, strArr6);
                break;
            case 8:
                String[] stringArray7 = getResources().getStringArray(C2128R.array.no_red_status);
                Intrinsics.checkExpressionValueIsNotNull(stringArray7, "resources.getStringArray(R.array.no_red_status)");
                float[] fArr3 = Constant.GGJ;
                Intrinsics.checkExpressionValueIsNotNull(fArr3, "GGJ");
                String[] strArr7 = Constant.COLORS2;
                Intrinsics.checkExpressionValueIsNotNull(strArr7, "COLORS2");
                showView(bodyStateView, stringArray7, fArr3, i, f, strArr7);
                break;
            case 9:
                String[] stringArray8 = getResources().getStringArray(C2128R.array.no_red_status);
                Intrinsics.checkExpressionValueIsNotNull(stringArray8, "resources.getStringArray(R.array.no_red_status)");
                float[] units2 = BodyUtil.getUnits(RangeUtil.getJRRange(m_gender, height), weightunit);
                Intrinsics.checkExpressionValueIsNotNull(units2, "getUnits(RangeUtil.getJRRange(sex, height), unit)");
                String[] strArr8 = Constant.COLORS2;
                Intrinsics.checkExpressionValueIsNotNull(strArr8, "COLORS2");
                showView(bodyStateView, stringArray8, units2, i, f, strArr8);
                break;
            case 10:
                String[] stringArray9 = getResources().getStringArray(C2128R.array.no_red_status);
                Intrinsics.checkExpressionValueIsNotNull(stringArray9, "resources.getStringArray(R.array.no_red_status)");
                float[] units3 = BodyUtil.getUnits(Constant.f1685GL, weightunit);
                Intrinsics.checkExpressionValueIsNotNull(units3, "getUnits(GL, unit)");
                String[] strArr9 = Constant.COLORS2;
                Intrinsics.checkExpressionValueIsNotNull(strArr9, "COLORS2");
                showView(bodyStateView, stringArray9, units3, i, f, strArr9);
                break;
            case 11:
                String[] stringArray10 = getResources().getStringArray(C2128R.array.no_red_status);
                Intrinsics.checkExpressionValueIsNotNull(stringArray10, "resources.getStringArray(R.array.no_red_status)");
                float[] fArr4 = Constant.DBZ;
                Intrinsics.checkExpressionValueIsNotNull(fArr4, "DBZ");
                String[] strArr10 = Constant.COLORS2;
                Intrinsics.checkExpressionValueIsNotNull(strArr10, "COLORS2");
                showView(bodyStateView, stringArray10, fArr4, i, f, strArr10);
                break;
            case 12:
                String[] stringArray11 = getResources().getStringArray(C2128R.array.base_metabolic);
                Intrinsics.checkExpressionValueIsNotNull(stringArray11, "resources.getStringArray(R.array.base_metabolic)");
                float[] fArr5 = Constant.JCDX;
                Intrinsics.checkExpressionValueIsNotNull(fArr5, "JCDX");
                String[] strArr11 = Constant.COLORS;
                Intrinsics.checkExpressionValueIsNotNull(strArr11, "COLORS");
                showView(bodyStateView, stringArray11, fArr5, i, f, strArr11);
                break;
        }
        TextView textView2 = textView;
        textView.setText(getResources().getStringArray(C2128R.array.details_body_description)[i4]);
        return false;
    }

    private final void showView(BodyStateView bodyStateView, String[] strArr, float[] fArr, int i, float f, String[] strArr2) {
        bodyStateView.setVisibility(0);
        bodyStateView.canvasContent(strArr, fArr, f, strArr2, i);
    }

    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 810) {
            if (intent == null) {
                try {
                    Intrinsics.throwNpe();
                } catch (Exception e) {
                    String tag = getTAG();
                    Log.e(tag, "onActivityResult: " + e);
                    return;
                }
            }
            Parcelable parcelableExtra = intent.getParcelableExtra(ShareConstants.WEB_DIALOG_PARAM_DATA);
            if (parcelableExtra != null) {
                Measure measure = (Measure) parcelableExtra;
                String bytesToHex = ByteUtil.bytesToHex(measure.getScanRecord());
                Intrinsics.checkExpressionValueIsNotNull(bytesToHex, "ByteUtil.bytesToHex(bleBean.scanRecord)");
                this.mScanRecord = bytesToHex;
                measure.setHeightunit(this.user.getHeightunit());
                measure.setWeightunit(this.weightUnit);
                measure.setDate(new Date(System.currentTimeMillis()));
                measure.setMember_id(this.selectUser.getMember_id());
                if (i2 == -1) {
                    measure.setOnlyWeight(false);
                    measure.setMuscle_mass(String.valueOf(UnitUtils.getWeight(UnitUtils.getIndex(this.weightUnit), UnitUtils.getFloatWeight(measure.getMuscle_mass()))));
                    measure.setBone_mass(String.valueOf(UnitUtils.getWeight(UnitUtils.getIndex(this.weightUnit), UnitUtils.getFloatWeight(measure.getBone_mass()))));
                    measure.setFfm(String.valueOf(UnitUtils.getWeight(UnitUtils.getIndex(this.weightUnit), UnitUtils.getFloatWeight(measure.getFfm()))));
                    measure.setIbw(String.valueOf(UnitUtils.getWeight(UnitUtils.getIndex(this.weightUnit), UnitUtils.getFloatWeight(measure.getIbw()))));
                    setWeightData(measure);
                } else if (i2 == 2) {
                    measure.setOnlyWeight(true);
                    setCirView(measure);
                    if (measure.getKgWeight() > ((float) 0)) {
                        reportingOnlyWeightData(measure);
                        googleFit(measure.getKgWeight());
                        fitBit(measure.getKgWeight(), (float) measure.getBodyfat_rate());
                    }
                } else if (i2 == 3) {
                    notSaveData(measure);
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.huayu.tzc.bean.Measure");
            }
        }
        if (i == 304 && i2 == -1) {
            requestLocationPermission();
        }
        String tag2 = getTAG();
        Log.e(tag2, "onActivityResult: " + i + "   " + i2);
    }

    private final void notSaveData(Measure measure) {
        Integer num;
        setCirView(measure);
        float weight = UnitUtils.getWeight(UnitUtils.getIndex(this.weightUnit), measure.getKgWeight());
        if (Intrinsics.areEqual((Object) this.weightUnit, (Object) Constant.UNIT_WEIGHT[2])) {
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
        initTzData(measure);
    }

    private final void setWeightData(Measure measure) {
        setCirView(measure);
        reportingData(measure);
        googleFit(measure.getKgWeight());
        fitBit(measure.getKgWeight(), (float) measure.getBodyfat_rate());
    }

    private final void reportingData(Measure measure) {
        Integer num;
        float weight = UnitUtils.getWeight(UnitUtils.getIndex(this.weightUnit), measure.getKgWeight());
        if (Intrinsics.areEqual((Object) this.weightUnit, (Object) Constant.UNIT_WEIGHT[2])) {
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
        initTzData(measure);
        if (Utils.checkNetState(getContext()) == 3) {
            measure.save();
        }
        FormBody.Builder add = new FormBody.Builder().add("basalmetabolic_rate", String.valueOf(measure.getBasalmetabolic_rate())).add("bmi", String.valueOf(measure.getBmi())).add("body_score", String.valueOf(measure.getBody_score())).add("bodyfat_rate", String.valueOf(measure.getBodyfat_rate())).add("bodywater_rate", String.valueOf(measure.getBodywater_rate())).add("bone_mass", measure.getBone_mass());
        Date date = measure.getDate();
        if (date == null) {
            Intrinsics.throwNpe();
        }
        FormBody build = add.add("measuredate", String.valueOf(date.getTime() / ((long) 1000))).add("ffm", measure.getFfm()).add("member_id", String.valueOf(this.selectUser.getMember_id())).add("visceral_fat", String.valueOf(measure.getVisceral_fat())).add("muscle_mass", measure.getMuscle_mass()).add("subcutaneousfat_rate", String.valueOf(measure.getSubcutaneousfat_rate())).add("skeletalfat_percentage", String.valueOf(measure.getSkeletalfat_percentage())).add("protein_rate", String.valueOf(measure.getProtein_rate())).add("obesity_grade", String.valueOf(measure.getObesity_grade())).add("ibw", measure.getIbw()).add("weight", measure.getWeight()).add(ViewHierarchyConstants.DIMENSION_HEIGHT_KEY, this.selectUser.getM_height()).add("heightunit", this.user.getHeightunit()).add("weightunit", this.weightUnit).add("shape", measure.getShape()).add("devmac", this.mac).add("bodyage", String.valueOf(measure.getBodyage())).build();
        Utils.logFormBody(build);
        HomePresenter homePresenter = (HomePresenter) getMPresenter();
        if (homePresenter != null) {
            homePresenter.reportingData(build);
        }
        progressShow();
    }

    private final void reportingOnlyWeightData(Measure measure) {
        Integer num;
        float weight = UnitUtils.getWeight(UnitUtils.getIndex(this.weightUnit), measure.getKgWeight());
        if (Intrinsics.areEqual((Object) this.weightUnit, (Object) Constant.UNIT_WEIGHT[2])) {
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
        if (Utils.checkNetState(getContext()) == 3) {
            measure.save();
        }
        initTzData(measure);
        FormBody.Builder add = new FormBody.Builder().add("bmi", String.valueOf(measure.getBmi()));
        Date date = measure.getDate();
        if (date == null) {
            Intrinsics.throwNpe();
        }
        FormBody build = add.add("measuredate", String.valueOf(date.getTime() / ((long) 1000))).add("member_id", String.valueOf(this.selectUser.getMember_id())).add("weight", measure.getWeight()).add(ViewHierarchyConstants.DIMENSION_HEIGHT_KEY, this.selectUser.getM_height()).add("heightunit", this.user.getHeightunit()).add("weightunit", this.weightUnit).add("devmac", this.mac).build();
        Utils.logFormBody(build);
        HomePresenter homePresenter = (HomePresenter) getMPresenter();
        if (homePresenter != null) {
            homePresenter.reportingData(build);
        }
        progressShow();
    }

    private final void fitBit(float f, float f2) {
        HomePresenter homePresenter;
        FitBit fitBit = (FitBit) MMKV.defaultMMKV().decodeParcelable("fitbit", FitBit.class, new FitBit());
        if (fitBit.getConnectFitBit()) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date date = new Date(System.currentTimeMillis());
            String fitToken = fitBit.getFitToken();
            String fitId = fitBit.getFitId();
            boolean z = true;
            if (fitToken.length() > 0) {
                if (fitId.length() <= 0) {
                    z = false;
                }
                if (z) {
                    FormBody build = new FormBody.Builder().add("weight", String.valueOf(f)).add("date", simpleDateFormat.format(date)).build();
                    Intrinsics.checkExpressionValueIsNotNull(build, "FormBody.Builder()\n     …\n                .build()");
                    RequestBody requestBody = build;
                    FormBody build2 = new FormBody.Builder().add("fat", String.valueOf(f2)).add("date", simpleDateFormat.format(date)).build();
                    Intrinsics.checkExpressionValueIsNotNull(build2, "FormBody.Builder()\n     …\n                .build()");
                    RequestBody requestBody2 = build2;
                    HomePresenter homePresenter2 = (HomePresenter) getMPresenter();
                    if (homePresenter2 != null) {
                        homePresenter2.fitWeight(fitId, requestBody);
                    }
                    if (f2 > ((float) 0) && (homePresenter = (HomePresenter) getMPresenter()) != null) {
                        homePresenter.fitFat(fitId, requestBody2);
                    }
                }
            }
        }
    }

    private final void googleFit(float f) {
        if (MMKV.defaultMMKV().decodeBool("google", false)) {
            if (GoogleSignIn.getLastSignedInAccount(getContext()) == null) {
                ToastUtils.show((CharSequence) getString(C2128R.string.google_sq));
                MMKV.defaultMMKV().encode("google", false);
                return;
            }
            FitnessOptions build = FitnessOptions.builder().addDataType(DataType.TYPE_WEIGHT, 1).build();
            Context context = getContext();
            if (context == null) {
                Intrinsics.throwNpe();
            }
            GoogleSignInAccount accountForExtension = GoogleSignIn.getAccountForExtension(context, build);
            Intrinsics.checkExpressionValueIsNotNull(accountForExtension, "GoogleSignIn\n           …ontext!!, fitnessOptions)");
            Calendar instance = Calendar.getInstance();
            Intrinsics.checkExpressionValueIsNotNull(instance, "cal");
            instance.setTime(new Date());
            long timeInMillis = instance.getTimeInMillis();
            instance.add(1, -1);
            long timeInMillis2 = instance.getTimeInMillis();
            DataType dataType = DataType.TYPE_WEIGHT;
            Intrinsics.checkExpressionValueIsNotNull(dataType, "DataType.TYPE_WEIGHT");
            Field field = Field.FIELD_WEIGHT;
            Intrinsics.checkExpressionValueIsNotNull(field, "Field.FIELD_WEIGHT");
            Fitness.getHistoryClient(getContext(), accountForExtension).insertData(createDataForRequest(dataType, field, f, timeInMillis2, timeInMillis)).addOnSuccessListener(new HomeFragment$googleFit$1(this)).addOnFailureListener(new HomeFragment$googleFit$2(this));
        }
    }

    private final DataSet createDataForRequest(DataType dataType, Field field, float f, long j, long j2) {
        DataSet create = DataSet.create(new DataSource.Builder().setAppPackageName(getContext()).setDataType(dataType).setStreamName("streamName").setType(0).build());
        DataPoint timeInterval = create.createDataPoint().setTimeInterval(j, j2, TimeUnit.MILLISECONDS);
        if (dataType == DataType.TYPE_CALORIES_EXPENDED || dataType == DataType.TYPE_HEART_RATE_BPM) {
            timeInterval.getValue(field).setFloat(f);
        } else {
            timeInterval = timeInterval.setFloatValues(f);
        }
        create.add(timeInterval);
        Intrinsics.checkExpressionValueIsNotNull(create, "dataSet");
        return create;
    }

    private final float setCirView(Measure measure) {
        String weightunit = this.user.getWeightunit();
        int hashCode = weightunit.hashCode();
        if (hashCode != 3420) {
            if (hashCode != 3446) {
                if (hashCode == 109719855 && weightunit.equals("st:lb")) {
                    float stWeight = measure.getStWeight();
                    String byteToHex = ByteUtil.byteToHex(measure.getWeightData()[0]);
                    Intrinsics.checkExpressionValueIsNotNull(byteToHex, "ByteUtil.byteToHex(measure.getWeightData()[0])");
                    int parseInt = Integer.parseInt(byteToHex, CharsKt.checkRadix(16));
                    String byteToHex2 = ByteUtil.byteToHex(measure.getWeightData()[1]);
                    Intrinsics.checkExpressionValueIsNotNull(byteToHex2, "ByteUtil.byteToHex(measure.getWeightData()[1])");
                    int parseInt2 = Integer.parseInt(byteToHex2, CharsKt.checkRadix(16));
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    float f = (float) 10;
                    Object[] objArr = {Float.valueOf(((float) parseInt2) / f)};
                    String format = String.format("%.1f", Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                    float parseFloat = Float.parseFloat(format);
                    if (parseFloat > f) {
                        float kgWeight = measure.getKgWeight();
                        ((CircularView) _$_findCachedViewById(C2128R.C2131id.homeCirView)).setWeight(kgWeight, "st:lb", String.valueOf(parseInt) + Config.TRACE_TODAY_VISIT_SPLIT + ((int) ((float) MathKt.roundToInt(parseFloat))));
                        return stWeight;
                    }
                    float kgWeight2 = measure.getKgWeight();
                    StringBuilder sb = new StringBuilder();
                    sb.append(parseInt);
                    sb.append(':');
                    sb.append(parseFloat);
                    ((CircularView) _$_findCachedViewById(C2128R.C2131id.homeCirView)).setWeight(kgWeight2, "st:lb", sb.toString());
                    return stWeight;
                }
            } else if (weightunit.equals("lb")) {
                float lbWeight = measure.getLbWeight();
                ((CircularView) _$_findCachedViewById(C2128R.C2131id.homeCirView)).setWeight(measure.getKgWeight(), this.user.getWeightunit(), String.valueOf(lbWeight));
                return lbWeight;
            }
        } else if (weightunit.equals("kg")) {
            float kgWeight3 = measure.getKgWeight();
            ((CircularView) _$_findCachedViewById(C2128R.C2131id.homeCirView)).setWeight(kgWeight3, this.user.getWeightunit(), String.valueOf(kgWeight3));
            return kgWeight3;
        }
        return 0.0f;
    }

    /* access modifiers changed from: private */
    public final String getFileAddSpace(String str) {
        String replace = new Regex("(.{2})").replace((CharSequence) str, "$1:");
        int length = replace.length() - 1;
        if (replace != null) {
            String substring = replace.substring(0, length);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public void onResume() {
        super.onResume();
        this.scanStatus = ScanStatus.SCANNING;
    }

    public void onStop() {
        super.onStop();
        this.scanStatus = ScanStatus.STOP;
    }

    private final void requestLocationPermission() {
        if (!Utils.isLocServiceEnable(getContext())) {
            ToastUtils.show((CharSequence) getString(C2128R.string.mobile_location));
            startActivityForResult(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"), 304);
            LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(C2128R.C2131id.homeAuthorityLayout);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "homeAuthorityLayout");
            linearLayout.setVisibility(0);
            return;
        }
        Log.e(getTAG(), "onGranted: sss");
        XXPermissions.with((Fragment) this).permission(Permission.ACCESS_FINE_LOCATION).permission(Permission.ACCESS_COARSE_LOCATION).request(new HomeFragment$requestLocationPermission$1(this));
    }

    public void setUserVisibleHint(boolean z) {
        ScanStatus scanStatus2;
        super.setUserVisibleHint(z);
        if (z) {
            scanStatus2 = ScanStatus.SCANNING;
        } else {
            scanStatus2 = ScanStatus.STOP;
        }
        this.scanStatus = scanStatus2;
    }
}
