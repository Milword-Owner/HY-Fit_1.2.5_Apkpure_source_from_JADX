package com.huayu.tzc;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.util.Log;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.data.BleDevice;
import com.facebook.share.internal.ShareConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0016\u0010\u0006\u001a\u00020\u00032\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u0016J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\r"}, mo21895d2 = {"com/huayu/tzc/MainActivity$scanCallback$1", "Lcom/clj/fastble/callback/BleScanCallback;", "onLeScan", "", "bleDevice", "Lcom/clj/fastble/data/BleDevice;", "onScanFinished", "list", "", "onScanStarted", "b", "", "onScanning", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: MainActivity.kt */
public final class MainActivity$scanCallback$1 extends BleScanCallback {
    final /* synthetic */ MainActivity this$0;

    public void onScanning(@NotNull BleDevice bleDevice) {
        Intrinsics.checkParameterIsNotNull(bleDevice, "bleDevice");
    }

    MainActivity$scanCallback$1(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    public void onScanFinished(@NotNull List<? extends BleDevice> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        Log.e(this.this$0.getTAG(), "onScanFinished: ");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.isEnabled()) {
            this.this$0.initBle();
        }
    }

    public void onScanStarted(boolean z) {
        Log.e(this.this$0.getTAG(), "onScanStarted: ");
    }

    public void onLeScan(@NotNull BleDevice bleDevice) {
        Intrinsics.checkParameterIsNotNull(bleDevice, "bleDevice");
        super.onLeScan(bleDevice);
        this.this$0.sendBroadcast(new Intent("com.huayu.tzc.ble").putExtra(ShareConstants.WEB_DIALOG_PARAM_DATA, bleDevice));
    }
}
