package com.clj.fastble.scan;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.Looper;
import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleGattCallback;
import com.clj.fastble.callback.BleScanAndConnectCallback;
import com.clj.fastble.callback.BleScanCallback;
import com.clj.fastble.callback.BleScanPresenterImp;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.data.BleScanState;
import java.util.List;
import java.util.UUID;

@TargetApi(18)
public class BleScanner {
    /* access modifiers changed from: private */
    public BleScanPresenter mBleScanPresenter = new BleScanPresenter() {
        public void onScanStarted(boolean z) {
            BleScanPresenterImp bleScanPresenterImp = BleScanner.this.mBleScanPresenter.getBleScanPresenterImp();
            if (bleScanPresenterImp != null) {
                bleScanPresenterImp.onScanStarted(z);
            }
        }

        public void onLeScan(BleDevice bleDevice) {
            if (BleScanner.this.mBleScanPresenter.ismNeedConnect()) {
                BleScanAndConnectCallback bleScanAndConnectCallback = (BleScanAndConnectCallback) BleScanner.this.mBleScanPresenter.getBleScanPresenterImp();
                if (bleScanAndConnectCallback != null) {
                    bleScanAndConnectCallback.onLeScan(bleDevice);
                    return;
                }
                return;
            }
            BleScanCallback bleScanCallback = (BleScanCallback) BleScanner.this.mBleScanPresenter.getBleScanPresenterImp();
            if (bleScanCallback != null) {
                bleScanCallback.onLeScan(bleDevice);
            }
        }

        public void onScanning(BleDevice bleDevice) {
            BleScanPresenterImp bleScanPresenterImp = BleScanner.this.mBleScanPresenter.getBleScanPresenterImp();
            if (bleScanPresenterImp != null) {
                bleScanPresenterImp.onScanning(bleDevice);
            }
        }

        public void onScanFinished(final List<BleDevice> list) {
            if (BleScanner.this.mBleScanPresenter.ismNeedConnect()) {
                final BleScanAndConnectCallback bleScanAndConnectCallback = (BleScanAndConnectCallback) BleScanner.this.mBleScanPresenter.getBleScanPresenterImp();
                if (list != null && list.size() >= 1) {
                    if (bleScanAndConnectCallback != null) {
                        bleScanAndConnectCallback.onScanFinished(list.get(0));
                    }
                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        public void run() {
                            BleManager.getInstance().connect((BleDevice) list.get(0), (BleGattCallback) bleScanAndConnectCallback);
                        }
                    }, 100);
                } else if (bleScanAndConnectCallback != null) {
                    bleScanAndConnectCallback.onScanFinished((BleDevice) null);
                }
            } else {
                BleScanCallback bleScanCallback = (BleScanCallback) BleScanner.this.mBleScanPresenter.getBleScanPresenterImp();
                if (bleScanCallback != null) {
                    bleScanCallback.onScanFinished(list);
                }
            }
        }
    };
    private BleScanState mBleScanState = BleScanState.STATE_IDLE;

    public static BleScanner getInstance() {
        return BleScannerHolder.sBleScanner;
    }

    private static class BleScannerHolder {
        /* access modifiers changed from: private */
        public static final BleScanner sBleScanner = new BleScanner();

        private BleScannerHolder() {
        }
    }

    public void scan(UUID[] uuidArr, String[] strArr, String str, boolean z, long j, BleScanCallback bleScanCallback) {
        startLeScan(uuidArr, strArr, str, z, false, j, bleScanCallback);
    }

    public void scanAndConnect(UUID[] uuidArr, String[] strArr, String str, boolean z, long j, BleScanAndConnectCallback bleScanAndConnectCallback) {
        startLeScan(uuidArr, strArr, str, z, true, j, bleScanAndConnectCallback);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0016, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void startLeScan(java.util.UUID[] r11, java.lang.String[] r12, java.lang.String r13, boolean r14, boolean r15, long r16, com.clj.fastble.callback.BleScanPresenterImp r18) {
        /*
            r10 = this;
            r1 = r10
            r0 = r18
            monitor-enter(r10)
            com.clj.fastble.data.BleScanState r2 = r1.mBleScanState     // Catch:{ all -> 0x0043 }
            com.clj.fastble.data.BleScanState r3 = com.clj.fastble.data.BleScanState.STATE_IDLE     // Catch:{ all -> 0x0043 }
            if (r2 == r3) goto L_0x0017
            java.lang.String r2 = "scan action already exists, complete the previous scan action first"
            com.clj.fastble.utils.BleLog.m1845w(r2)     // Catch:{ all -> 0x0043 }
            if (r0 == 0) goto L_0x0015
            r2 = 0
            r0.onScanStarted(r2)     // Catch:{ all -> 0x0043 }
        L_0x0015:
            monitor-exit(r10)
            return
        L_0x0017:
            com.clj.fastble.scan.BleScanPresenter r2 = r1.mBleScanPresenter     // Catch:{ all -> 0x0043 }
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            r7 = r16
            r9 = r18
            r2.prepare(r3, r4, r5, r6, r7, r9)     // Catch:{ all -> 0x0043 }
            com.clj.fastble.BleManager r0 = com.clj.fastble.BleManager.getInstance()     // Catch:{ all -> 0x0043 }
            android.bluetooth.BluetoothAdapter r0 = r0.getBluetoothAdapter()     // Catch:{ all -> 0x0043 }
            com.clj.fastble.scan.BleScanPresenter r2 = r1.mBleScanPresenter     // Catch:{ all -> 0x0043 }
            r3 = r11
            boolean r0 = r0.startLeScan(r11, r2)     // Catch:{ all -> 0x0043 }
            if (r0 == 0) goto L_0x0038
            com.clj.fastble.data.BleScanState r2 = com.clj.fastble.data.BleScanState.STATE_SCANNING     // Catch:{ all -> 0x0043 }
            goto L_0x003a
        L_0x0038:
            com.clj.fastble.data.BleScanState r2 = com.clj.fastble.data.BleScanState.STATE_IDLE     // Catch:{ all -> 0x0043 }
        L_0x003a:
            r1.mBleScanState = r2     // Catch:{ all -> 0x0043 }
            com.clj.fastble.scan.BleScanPresenter r2 = r1.mBleScanPresenter     // Catch:{ all -> 0x0043 }
            r2.notifyScanStarted(r0)     // Catch:{ all -> 0x0043 }
            monitor-exit(r10)
            return
        L_0x0043:
            r0 = move-exception
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.clj.fastble.scan.BleScanner.startLeScan(java.util.UUID[], java.lang.String[], java.lang.String, boolean, boolean, long, com.clj.fastble.callback.BleScanPresenterImp):void");
    }

    public synchronized void stopLeScan() {
        BleManager.getInstance().getBluetoothAdapter().stopLeScan(this.mBleScanPresenter);
        this.mBleScanState = BleScanState.STATE_IDLE;
        this.mBleScanPresenter.notifyScanStopped();
    }

    public BleScanState getScanState() {
        return this.mBleScanState;
    }
}
