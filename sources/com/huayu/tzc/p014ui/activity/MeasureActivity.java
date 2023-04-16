package com.huayu.tzc.p014ui.activity;

import android.app.ActivityManager;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.utils.HexUtil;
import com.facebook.share.internal.ShareConstants;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.bean.Measure;
import com.huayu.tzc.bean.Member;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.customview.BaseAlterDialog;
import com.huayu.tzc.presenter.NotPresenter;
import com.huayu.tzc.statusbar.StatusBarUtil;
import com.huayu.tzc.utils.ByteUtil;
import com.huayu.tzc.utils.UnitUtils;
import com.huntersun.p022rf.hsblue24lib.HsBlue24;
import com.huntersun.p022rf.hsblue24lib.HsString;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u000256B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010 \u001a\u00020\u001eH\u0014J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\u000e\u0010%\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u0019J\b\u0010'\u001a\u00020\u0003H\u0016J\b\u0010(\u001a\u00020\"H\u0014J\u0010\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020\"H\u0014J\b\u0010-\u001a\u00020\"H\u0014J \u0010.\u001a\u00020\"2\u0006\u0010/\u001a\u00020\u00142\u0006\u00100\u001a\u00020\u00192\u0006\u0010#\u001a\u00020$H\u0002J\b\u00101\u001a\u00020\"H\u0002J\b\u00102\u001a\u00020\"H\u0002J\u0018\u00103\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u00100\u001a\u00020\u0019H\u0002J\b\u00104\u001a\u00020\"H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000¨\u00067"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/MeasureActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$View;", "Lcom/huayu/tzc/presenter/NotPresenter;", "()V", "bluetoothAdapter", "Landroid/bluetooth/BluetoothAdapter;", "dataReceiver", "Lcom/huayu/tzc/ui/activity/MeasureActivity$BleDataReceiver;", "handler", "Landroid/os/Handler;", "isShowDialog", "", "lastTimeStamp", "", "mHsBlue24", "Lcom/huntersun/rf/hsblue24lib/HsBlue24;", "mPlayer", "Landroid/media/MediaPlayer;", "mScanRecord", "", "mac", "member", "Lcom/huayu/tzc/bean/Member;", "rfData", "", "scanStatus", "Lcom/huayu/tzc/ui/activity/MeasureActivity$ScanStatus;", "string", "timeout", "", "weightUnit", "getLayoutId", "getMeasureData", "", "bleDevice", "Lcom/clj/fastble/data/BleDevice;", "getMsgType", "data", "getPresenter", "initView", "isAPPBroughtToBackground", "context", "Landroid/content/Context;", "onDestroy", "onRestart", "onlyWeight", "s", "weight", "permissionSuccess", "requestLocationPermission", "showOnlyWeight", "startPlay", "BleDataReceiver", "ScanStatus", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.MeasureActivity */
/* compiled from: MeasureActivity.kt */
public final class MeasureActivity extends BaseActivity<MainContract.View, NotPresenter> {
    private HashMap _$_findViewCache;
    private BluetoothAdapter bluetoothAdapter;
    private BleDataReceiver dataReceiver;
    private Handler handler;
    private boolean isShowDialog;
    /* access modifiers changed from: private */
    public long lastTimeStamp;
    private HsBlue24 mHsBlue24;
    private MediaPlayer mPlayer;
    /* access modifiers changed from: private */
    public String mScanRecord = "";
    /* access modifiers changed from: private */
    public String mac = "";
    /* access modifiers changed from: private */
    public Member member;
    private byte[] rfData;
    /* access modifiers changed from: private */
    public ScanStatus scanStatus = ScanStatus.SCANNING;
    private String string = "";
    private int timeout;
    private String weightUnit = "";

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/MeasureActivity$ScanStatus;", "", "(Ljava/lang/String;I)V", "STOP", "SCANNING", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* renamed from: com.huayu.tzc.ui.activity.MeasureActivity$ScanStatus */
    /* compiled from: MeasureActivity.kt */
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
        return C2128R.C2133layout.activity_measure;
    }

    public static final /* synthetic */ Member access$getMember$p(MeasureActivity measureActivity) {
        Member member2 = measureActivity.member;
        if (member2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("member");
        }
        return member2;
    }

    /* access modifiers changed from: protected */
    public void initView() {
        StatusBarUtil.setStatusBarColor(this, Color.parseColor("#3C82FF"));
        String stringExtra = getIntent().getStringExtra("mac");
        if (stringExtra != null) {
            this.mac = stringExtra;
            String stringExtra2 = getIntent().getStringExtra("weightUnit");
            if (stringExtra2 != null) {
                this.weightUnit = stringExtra2;
                String stringExtra3 = getIntent().getStringExtra("mScanRecord");
                if (stringExtra3 != null) {
                    this.mScanRecord = stringExtra3;
                    Parcelable parcelableExtra = getIntent().getParcelableExtra("member");
                    if (parcelableExtra != null) {
                        this.member = (Member) parcelableExtra;
                        Glide.with((FragmentActivity) this).load(Integer.valueOf(C2128R.C2130drawable.gif)).into((ImageView) _$_findCachedViewById(C2128R.C2131id.measure_gif));
                        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                        Intrinsics.checkExpressionValueIsNotNull(defaultAdapter, "BluetoothAdapter.getDefaultAdapter()");
                        this.bluetoothAdapter = defaultAdapter;
                        BluetoothAdapter bluetoothAdapter2 = this.bluetoothAdapter;
                        if (bluetoothAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("bluetoothAdapter");
                        }
                        if (!bluetoothAdapter2.isEnabled()) {
                            startActivityForResult(new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE"), 303);
                            return;
                        }
                        requestLocationPermission();
                        ((ImageView) _$_findCachedViewById(C2128R.C2131id.measure_back)).setOnClickListener(new MeasureActivity$initView$1(this));
                        this.dataReceiver = new BleDataReceiver();
                        BleDataReceiver bleDataReceiver = this.dataReceiver;
                        if (bleDataReceiver == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("dataReceiver");
                        }
                        registerReceiver(bleDataReceiver, new IntentFilter("com.huayu.tzc.ble"));
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type com.huayu.tzc.bean.Member");
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
    }

    private final void requestLocationPermission() {
        XXPermissions.with((FragmentActivity) this).permission(Permission.ACCESS_FINE_LOCATION).permission(Permission.ACCESS_COARSE_LOCATION).request(new MeasureActivity$requestLocationPermission$1(this));
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/MeasureActivity$BleDataReceiver;", "Landroid/content/BroadcastReceiver;", "(Lcom/huayu/tzc/ui/activity/MeasureActivity;)V", "onReceive", "", "p0", "Landroid/content/Context;", "p1", "Landroid/content/Intent;", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* renamed from: com.huayu.tzc.ui.activity.MeasureActivity$BleDataReceiver */
    /* compiled from: MeasureActivity.kt */
    public final class BleDataReceiver extends BroadcastReceiver {
        public BleDataReceiver() {
        }

        public void onReceive(@Nullable Context context, @Nullable Intent intent) {
            if (Intrinsics.areEqual((Object) intent != null ? intent.getAction() : null, (Object) "com.huayu.tzc.ble") && MeasureActivity.this.scanStatus == ScanStatus.SCANNING) {
                Parcelable parcelableExtra = intent.getParcelableExtra(ShareConstants.WEB_DIALOG_PARAM_DATA);
                Intrinsics.checkExpressionValueIsNotNull(parcelableExtra, "p1.getParcelableExtra(\"data\")");
                BleDevice bleDevice = (BleDevice) parcelableExtra;
                if (!Intrinsics.areEqual((Object) ByteUtil.bytesToHex(bleDevice.getScanRecord()), (Object) MeasureActivity.this.mScanRecord) && StringsKt.equals(bleDevice.getMac(), MeasureActivity.this.mac, true) && Intrinsics.areEqual((Object) "10", (Object) ByteUtil.byteToHex(bleDevice.getScanRecord()[0])) && Intrinsics.areEqual((Object) "ff", (Object) ByteUtil.byteToHex(bleDevice.getScanRecord()[1]))) {
                    try {
                        MeasureActivity.this.getMeasureData(bleDevice);
                    } catch (Exception e) {
                        String access$getTAG$p = MeasureActivity.this.getTAG();
                        Log.e(access$getTAG$p, "Exception: " + e);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final void permissionSuccess() {
        HsBlue24 instance = HsBlue24.getInstance(getApplicationContext());
        Intrinsics.checkExpressionValueIsNotNull(instance, "HsBlue24.getInstance(applicationContext)");
        this.mHsBlue24 = instance;
        byte switchUnit = UnitUtils.switchUnit(this.weightUnit);
        byte[] ParseHexString = HsString.ParseHexString(this.mac);
        Intrinsics.checkExpressionValueIsNotNull(ParseHexString, "HsString.ParseHexString(mac)");
        byte[] bArr = new byte[(ParseHexString.length - 1)];
        int length = ParseHexString.length - 1;
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            bArr[i] = ParseHexString[i2];
            i = i2;
        }
        this.rfData = new byte[]{122, 0, 0, ParseHexString[3], ParseHexString[4], switchUnit};
        HsBlue24 hsBlue24 = this.mHsBlue24;
        if (hsBlue24 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHsBlue24");
        }
        byte[] bArr2 = this.rfData;
        if (bArr2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rfData");
        }
        hsBlue24.startTx(bArr, bArr2, this.timeout);
    }

    /* access modifiers changed from: private */
    public final void getMeasureData(BleDevice bleDevice) {
        byte[] bArr = {bleDevice.getScanRecord()[4], bleDevice.getScanRecord()[5]};
        String computeWeight = UnitUtils.computeWeight(bArr, this.weightUnit, bleDevice.getScanRecord());
        TextView textView = (TextView) _$_findCachedViewById(C2128R.C2131id.measure_num);
        Intrinsics.checkExpressionValueIsNotNull(textView, "measure_num");
        textView.setText(computeWeight);
        if (!Intrinsics.areEqual((Object) this.string, (Object) ByteUtil.bytesToHex(bArr))) {
            this.lastTimeStamp = System.currentTimeMillis();
            String bytesToHex = ByteUtil.bytesToHex(bArr);
            Intrinsics.checkExpressionValueIsNotNull(bytesToHex, "ByteUtil.bytesToHex(weight)");
            this.string = bytesToHex;
            String tag = getTAG();
            Log.e(tag, "getMeasureData:  " + this.string);
            onlyWeight(this.string, bArr, bleDevice);
        }
        if ((!Intrinsics.areEqual((Object) "00", (Object) ByteUtil.byteToHex(bleDevice.getScanRecord()[6]))) || (!Intrinsics.areEqual((Object) "00", (Object) ByteUtil.byteToHex(bleDevice.getScanRecord()[7])))) {
            Handler handler2 = this.handler;
            if (handler2 != null) {
                if (handler2 == null) {
                    Intrinsics.throwNpe();
                }
                handler2.removeCallbacksAndMessages((Object) null);
            }
            startPlay();
            String bigInteger = new BigInteger(1, new byte[]{bleDevice.getScanRecord()[10]}).toString(2);
            StringBuffer stringBuffer = new StringBuffer();
            int length = 8 - bigInteger.length();
            for (int i = 0; i < length; i++) {
                stringBuffer.append(0);
            }
            stringBuffer.append(bigInteger);
            String tag2 = getTAG();
            Log.e(tag2, "getMeasureData: 消息体 " + bleDevice.getScanRecord()[6] + "  " + bleDevice.getScanRecord()[7]);
            String tag3 = getTAG();
            Log.e(tag3, "onLeScan: 电阻为 " + ByteUtil.byteToHex(bleDevice.getScanRecord()[6]) + " " + ByteUtil.byteToHex(bleDevice.getScanRecord()[7]));
            this.scanStatus = ScanStatus.STOP;
            Measure measure = new Measure();
            Member member2 = this.member;
            if (member2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("member");
            }
            measure.setHeight(member2.getM_height());
            byte[] scanRecord = bleDevice.getScanRecord();
            Intrinsics.checkExpressionValueIsNotNull(scanRecord, "bleDevice.scanRecord");
            measure.setMsgType(getMsgType(scanRecord));
            String mac2 = bleDevice.getMac();
            Intrinsics.checkExpressionValueIsNotNull(mac2, "bleDevice.mac");
            measure.setDevmac(mac2);
            measure.setScanRecord(bleDevice.getScanRecord());
            Member member3 = this.member;
            if (member3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("member");
            }
            int i2 = member3.getM_gender() == 1 ? 0 : 1;
            measure.computeWeight(bArr);
            String tag4 = getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("onLeScan: 身高 ");
            Member member4 = this.member;
            if (member4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("member");
            }
            sb.append(member4.getHeight());
            Log.e(tag4, sb.toString());
            String tag5 = getTAG();
            Log.e(tag5, "onLeScan: 性别 " + i2);
            String tag6 = getTAG();
            StringBuilder sb2 = new StringBuilder();
            sb2.append("onLeScan: 年龄  ");
            Member member5 = this.member;
            if (member5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("member");
            }
            sb2.append(member5.getUserAge());
            Log.e(tag6, sb2.toString());
            String tag7 = getTAG();
            Log.e(tag7, "onLeScan: 体重 " + measure.getKgWeight());
            String byteToHex = ByteUtil.byteToHex(bleDevice.getScanRecord()[6]);
            Intrinsics.checkExpressionValueIsNotNull(byteToHex, "ByteUtil.byteToHex(\n    …[6]\n                    )");
            String byteToHex2 = ByteUtil.byteToHex(bleDevice.getScanRecord()[7]);
            Intrinsics.checkExpressionValueIsNotNull(byteToHex2, "ByteUtil.byteToHex(bleDevice.scanRecord[7])");
            String formatHexString = HexUtil.formatHexString(new byte[]{Byte.parseByte(byteToHex), Byte.parseByte(byteToHex2)}, false);
            Intrinsics.checkExpressionValueIsNotNull(formatHexString, "HexUtil.formatHexString(…   ), false\n            )");
            float parseInt = (float) Integer.parseInt(formatHexString, CharsKt.checkRadix(16));
            String tag8 = getTAG();
            Log.e(tag8, "onLeScan: 电阻 " + parseInt);
            Member member6 = this.member;
            if (member6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("member");
            }
            float height = (float) member6.getHeight();
            Member member7 = this.member;
            if (member7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("member");
            }
            measure.computeTz(height, i2, member7.getUserAge(), measure.getKgWeight(), parseInt);
            String tag9 = getTAG();
            Log.e(tag9, "onLeScan: " + measure);
            setResult(-1, new Intent().putExtra(ShareConstants.WEB_DIALOG_PARAM_DATA, measure));
            finish();
        }
    }

    private final void onlyWeight(String str, byte[] bArr, BleDevice bleDevice) {
        Handler handler2 = this.handler;
        if (handler2 != null) {
            if (handler2 == null) {
                Intrinsics.throwNpe();
            }
            handler2.removeCallbacksAndMessages((Object) null);
        }
        this.handler = new Handler();
        Handler handler3 = this.handler;
        if (handler3 == null) {
            Intrinsics.throwNpe();
        }
        handler3.postDelayed(new MeasureActivity$onlyWeight$1(this, bleDevice, bArr), 8000);
    }

    /* access modifiers changed from: protected */
    public void onRestart() {
        super.onRestart();
    }

    private final boolean isAPPBroughtToBackground(Context context) {
        Object systemService = context.getSystemService("activity");
        if (systemService != null) {
            List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) systemService).getRunningTasks(1);
            Intrinsics.checkExpressionValueIsNotNull(runningTasks, "am.getRunningTasks(1)");
            if (!runningTasks.isEmpty()) {
                ComponentName componentName = runningTasks.get(0).topActivity;
                if (StringsKt.equals$default(componentName != null ? componentName.getClassName() : null, context.getClass().getName(), false, 2, (Object) null)) {
                    Log.e(getTAG(), "isAPPBroughtToBackground: 前台");
                    return true;
                }
                Log.e(getTAG(), "isAPPBroughtToBackground: 后台");
            }
            return false;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
    }

    /* access modifiers changed from: private */
    public final void showOnlyWeight(BleDevice bleDevice, byte[] bArr) {
        this.isShowDialog = false;
        this.scanStatus = ScanStatus.STOP;
        Context context = this;
        BaseAlterDialog baseAlterDialog = new BaseAlterDialog(context, getString(C2128R.string.measure_method), C2128R.C2133layout.dialog_measure);
        baseAlterDialog.setCancelable(false);
        baseAlterDialog.setmCancelBtn(getString(C2128R.string.save_data), new MeasureActivity$showOnlyWeight$1(this, bleDevice, bArr, baseAlterDialog));
        baseAlterDialog.setmOkBtn(getString(C2128R.string.savedata), new MeasureActivity$showOnlyWeight$2(this, bleDevice, bArr, baseAlterDialog));
        if (isAPPBroughtToBackground(context)) {
            baseAlterDialog.show();
        }
    }

    @NotNull
    public final String getMsgType(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, ShareConstants.WEB_DIALOG_PARAM_DATA);
        String bigInteger = new BigInteger(1, new byte[]{bArr[10]}).toString(2);
        StringBuffer stringBuffer = new StringBuffer();
        int length = 8 - bigInteger.length();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(0);
        }
        stringBuffer.append(bigInteger);
        String stringBuffer2 = stringBuffer.reverse().toString();
        Intrinsics.checkExpressionValueIsNotNull(stringBuffer2, "stringBuffer.reverse().toString()");
        return stringBuffer2;
    }

    private final void startPlay() {
        this.mPlayer = MediaPlayer.create(this, C2128R.raw.raw);
        MediaPlayer mediaPlayer = this.mPlayer;
        if (mediaPlayer == null) {
            Intrinsics.throwNpe();
        }
        mediaPlayer.start();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            this.scanStatus = ScanStatus.STOP;
            BleDataReceiver bleDataReceiver = this.dataReceiver;
            if (bleDataReceiver == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dataReceiver");
            }
            unregisterReceiver(bleDataReceiver);
            if (this.handler != null) {
                Handler handler2 = this.handler;
                if (handler2 == null) {
                    Intrinsics.throwNpe();
                }
                handler2.removeCallbacksAndMessages((Object) null);
            }
            HsBlue24 hsBlue24 = this.mHsBlue24;
            if (hsBlue24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mHsBlue24");
            }
            hsBlue24.stopTx();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @NotNull
    public NotPresenter getPresenter() {
        return new NotPresenter();
    }
}
