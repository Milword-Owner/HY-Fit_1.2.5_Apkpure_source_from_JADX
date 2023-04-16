package com.huayu.tzc.p014ui.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.baidu.mobstat.Config;
import com.clj.fastble.data.BleDevice;
import com.facebook.share.internal.ShareConstants;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.adapter.DeviceAdapter;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.base.BaseBean;
import com.huayu.tzc.base.BaseListBean;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.BaseAlterDialog;
import com.huayu.tzc.customview.MyHeaderView;
import com.huayu.tzc.customview.StepDialog;
import com.huayu.tzc.presenter.HomePresenter;
import com.huayu.tzc.utils.ByteUtil;
import com.huayu.tzc.utils.SnackBarHelper;
import com.tencent.mmkv.MMKV;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0002:\u0002'(B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\u0016\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0003H\u0016J\b\u0010\u001b\u001a\u00020\u0017H\u0002J\b\u0010\u001c\u001a\u00020\u0017H\u0002J\b\u0010\u001d\u001a\u00020\u0017H\u0014J\b\u0010\u001e\u001a\u00020\u0017H\u0014J\u0016\u0010\u001f\u001a\u00020\u00172\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000f0!H\u0016J\u0012\u0010\"\u001a\u00020\u00172\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020\u0017H\u0002J\b\u0010&\u001a\u00020\u0017H\u0002R\u0012\u0010\u0005\u001a\u00060\u0006R\u00020\u0000X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000¨\u0006)"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/DeviceActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$HomeView;", "Lcom/huayu/tzc/presenter/HomePresenter;", "()V", "dataReceiver", "Lcom/huayu/tzc/ui/activity/DeviceActivity$BleDataReceiver;", "deviceAdapter", "Lcom/huayu/tzc/adapter/DeviceAdapter;", "deviceList", "", "Lcom/huayu/tzc/bean/Measure;", "isFirstOpen", "", "mac", "", "scanStatus", "Lcom/huayu/tzc/ui/activity/DeviceActivity$ScanStatus;", "getFileAddSpace", "replace", "getLayoutId", "", "getMeasures", "", "measureBean", "Lcom/huayu/tzc/base/BaseListBean;", "getPresenter", "initAdapter", "initData", "initView", "onDestroy", "reporting", "baseBean", "Lcom/huayu/tzc/base/BaseBean;", "showError", "e", "", "showStepDialog", "showTipDialog", "BleDataReceiver", "ScanStatus", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.DeviceActivity */
/* compiled from: DeviceActivity.kt */
public final class DeviceActivity extends BaseActivity<MainContract.HomeView, HomePresenter> implements MainContract.HomeView {
    private HashMap _$_findViewCache;
    private BleDataReceiver dataReceiver;
    /* access modifiers changed from: private */
    public DeviceAdapter deviceAdapter;
    /* access modifiers changed from: private */
    public List<Measure> deviceList = new ArrayList();
    private boolean isFirstOpen = true;
    /* access modifiers changed from: private */
    public String mac = "";
    /* access modifiers changed from: private */
    public ScanStatus scanStatus = ScanStatus.SCANNING;

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/DeviceActivity$ScanStatus;", "", "(Ljava/lang/String;I)V", "STOP", "SCANNING", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* renamed from: com.huayu.tzc.ui.activity.DeviceActivity$ScanStatus */
    /* compiled from: DeviceActivity.kt */
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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C2128R.C2133layout.activity_device;
    }

    public static final /* synthetic */ DeviceAdapter access$getDeviceAdapter$p(DeviceActivity deviceActivity) {
        DeviceAdapter deviceAdapter2 = deviceActivity.deviceAdapter;
        if (deviceAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
        }
        return deviceAdapter2;
    }

    public static final /* synthetic */ HomePresenter access$getMPresenter$p(DeviceActivity deviceActivity) {
        return (HomePresenter) deviceActivity.getMPresenter();
    }

    @NotNull
    public HomePresenter getPresenter() {
        return new HomePresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        this.isFirstOpen = MMKV.defaultMMKV().decodeBool(Config.TRACE_VISIT_FIRST, true);
        if (this.isFirstOpen) {
            showTipDialog();
        }
        initData();
        initAdapter();
        this.dataReceiver = new BleDataReceiver();
        BleDataReceiver bleDataReceiver = this.dataReceiver;
        if (bleDataReceiver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataReceiver");
        }
        registerReceiver(bleDataReceiver, new IntentFilter("com.huayu.tzc.ble"));
        ((MyHeaderView) _$_findCachedViewById(C2128R.C2131id.deviceTip)).setTextClickListener(new DeviceActivity$initView$1(this));
    }

    private final void showTipDialog() {
        BaseAlterDialog baseAlterDialog = new BaseAlterDialog((Context) this, (int) C2128R.C2133layout.dialog_tip);
        baseAlterDialog.setmCancelBtn(new DeviceActivity$showTipDialog$1(baseAlterDialog));
        baseAlterDialog.setmOkBtn(new DeviceActivity$showTipDialog$2(this, baseAlterDialog));
        baseAlterDialog.show();
    }

    /* access modifiers changed from: private */
    public final void showStepDialog() {
        new StepDialog(this).show();
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/DeviceActivity$BleDataReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/huayu/tzc/ui/activity/DeviceActivity;)V", "onReceive", "", "p0", "Landroid/content/Context;", "p1", "Landroid/content/Intent;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* renamed from: com.huayu.tzc.ui.activity.DeviceActivity$BleDataReceiver */
    /* compiled from: DeviceActivity.kt */
    public final class BleDataReceiver extends BroadcastReceiver {
        public BleDataReceiver() {
        }

        public void onReceive(@Nullable Context context, @Nullable Intent intent) {
            if (Intrinsics.areEqual((Object) intent != null ? intent.getAction() : null, (Object) "com.huayu.tzc.ble") && DeviceActivity.this.scanStatus == ScanStatus.SCANNING) {
                Parcelable parcelableExtra = intent.getParcelableExtra(ShareConstants.WEB_DIALOG_PARAM_DATA);
                Intrinsics.checkExpressionValueIsNotNull(parcelableExtra, "p1.getParcelableExtra(\"data\")");
                BleDevice bleDevice = (BleDevice) parcelableExtra;
                if (Intrinsics.areEqual((Object) "10", (Object) ByteUtil.byteToHex(bleDevice.getScanRecord()[0])) && Intrinsics.areEqual((Object) "ff", (Object) ByteUtil.byteToHex(bleDevice.getScanRecord()[1]))) {
                    boolean z = false;
                    for (Measure devmac : DeviceActivity.this.deviceList) {
                        if (Intrinsics.areEqual((Object) devmac.getDevmac(), (Object) bleDevice.getMac())) {
                            z = true;
                        }
                    }
                    if (!z) {
                        String access$getTAG$p = DeviceActivity.this.getTAG();
                        Log.e(access$getTAG$p, "onLeScan: " + ByteUtil.bytesToHex(bleDevice.getScanRecord()));
                        byte[] bArr = {bleDevice.getScanRecord()[11], bleDevice.getScanRecord()[12], bleDevice.getScanRecord()[13], bleDevice.getScanRecord()[14], bleDevice.getScanRecord()[15], bleDevice.getScanRecord()[16]};
                        Measure measure = new Measure();
                        String bytesToHex = ByteUtil.bytesToHex(bArr);
                        Intrinsics.checkExpressionValueIsNotNull(bytesToHex, "ByteUtil.bytesToHex(bytes)");
                        measure.setMacData(bytesToHex);
                        String mac = bleDevice.getMac();
                        Intrinsics.checkExpressionValueIsNotNull(mac, "bleDevice.mac");
                        measure.setDevmac(mac);
                        measure.setConnect(StringsKt.equals(bleDevice.getMac(), DeviceActivity.this.mac, true));
                        DeviceActivity.this.deviceList.add(measure);
                        DeviceActivity.access$getDeviceAdapter$p(DeviceActivity.this).notifyDataSetChanged();
                        if (DeviceActivity.this.deviceList.size() == 1) {
                            DeviceActivity deviceActivity = DeviceActivity.this;
                            SnackBarHelper.showLong((Activity) deviceActivity, deviceActivity.getString(C2128R.string.device_tip));
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.scanStatus = ScanStatus.STOP;
        BleDataReceiver bleDataReceiver = this.dataReceiver;
        if (bleDataReceiver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataReceiver");
        }
        unregisterReceiver(bleDataReceiver);
    }

    private final void initAdapter() {
        this.deviceAdapter = new DeviceAdapter(this.deviceList);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.device_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "device_recyclerview");
        Context context = this;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.device_recyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "device_recyclerview");
        DeviceAdapter deviceAdapter2 = this.deviceAdapter;
        if (deviceAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
        }
        recyclerView2.setAdapter(deviceAdapter2);
        View inflate = LayoutInflater.from(context).inflate(C2128R.C2133layout.ble_scan, (ViewGroup) null);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(this…(R.layout.ble_scan, null)");
        DeviceAdapter deviceAdapter3 = this.deviceAdapter;
        if (deviceAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
        }
        deviceAdapter3.setEmptyView(inflate);
        DeviceAdapter deviceAdapter4 = this.deviceAdapter;
        if (deviceAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
        }
        deviceAdapter4.setOnItemClickListener(new DeviceActivity$initAdapter$1(this));
    }

    private final void initData() {
        String stringExtra = getIntent().getStringExtra("mac");
        if (stringExtra != null) {
            this.mac = stringExtra;
            if (this.mac.length() == 0) {
                TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.device_mac);
                Intrinsics.checkExpressionValueIsNotNull(textView, "device_mac");
                textView.setText(getString(C2128R.string.mac));
                ImageView imageView = (ImageView) _$_findCachedViewById(C2128R.C2131id.device_ok);
                Intrinsics.checkExpressionValueIsNotNull(imageView, "device_ok");
                imageView.setVisibility(4);
                return;
            }
            this.mac = getFileAddSpace(this.mac);
            TextView textView2 = (TextView) _$_findCachedViewById(C2128R.C2131id.device_mac);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "device_mac");
            StringBuilder sb = new StringBuilder();
            sb.append(getString(C2128R.string.mac));
            String str = this.mac;
            if (str != null) {
                String upperCase = str.toUpperCase();
                Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
                sb.append(upperCase);
                textView2.setText(sb.toString());
                ImageView imageView2 = (ImageView) _$_findCachedViewById(C2128R.C2131id.device_ok);
                Intrinsics.checkExpressionValueIsNotNull(imageView2, "device_ok");
                imageView2.setVisibility(0);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
    }

    private final String getFileAddSpace(String str) {
        String replace = new Regex("(.{2})").replace((CharSequence) str, "$1:");
        int length = replace.length() - 1;
        if (replace != null) {
            String substring = replace.substring(0, length);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            return substring;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    public void reporting(@NotNull BaseBean<String> baseBean) {
        Intrinsics.checkParameterIsNotNull(baseBean, "baseBean");
        progressDissmiss();
        if (baseBean.getMeta().getSuccess()) {
            setResult(-1);
        }
        ToastUtils.show((CharSequence) baseBean.getMeta().getMessage());
    }

    public void getMeasures(@NotNull BaseListBean<Measure> baseListBean) {
        Intrinsics.checkParameterIsNotNull(baseListBean, "measureBean");
        progressDissmiss();
    }

    public void showError(@Nullable Throwable th) {
        progressDissmiss();
    }
}
