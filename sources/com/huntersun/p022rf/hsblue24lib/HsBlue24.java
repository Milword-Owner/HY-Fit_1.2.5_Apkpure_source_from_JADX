package com.huntersun.p022rf.hsblue24lib;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.os.ParcelUuid;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* renamed from: com.huntersun.rf.hsblue24lib.HsBlue24 */
public final class HsBlue24 {
    /* access modifiers changed from: private */
    public static final int[] advertising_channel = {37, 38, 39};
    private static HsBlue24 mHsBlue24;
    /* access modifiers changed from: private */
    public BluetoothLeScanner bluetoothLeScanner;
    private AdvertiseCallback mAdvertiseCallback;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeAdvertiser mBluetoothLeAdvertiser;
    /* access modifiers changed from: private */
    public String mErrorString;
    private Future mFuture;
    /* access modifiers changed from: private */
    public RxCallBack mRxCallBack = null;
    /* access modifiers changed from: private */
    public boolean mRxStopped = false;
    /* access modifiers changed from: private */
    public ScanCallback mScanCallback;
    private ScheduledThreadPoolExecutor mScheduleTaskExecutor = new ScheduledThreadPoolExecutor(5);
    /* access modifiers changed from: private */
    public ArrayList<Integer> mUserIdsOfRxFilters;

    public static HsBlue24 getInstance(Context context) {
        if (mHsBlue24 == null) {
            BluetoothAdapter adapter = ((BluetoothManager) context.getSystemService("bluetooth")).getAdapter();
            if (adapter == null) {
                Toast.makeText(context, "There's no Bluetooth on this device.", 0).show();
                return null;
            }
            if (!adapter.isEnabled()) {
                Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                intent.addFlags(268435456);
                context.startActivity(intent);
            }
            while (!adapter.isEnabled()) {
                for (int i = 0; i < 4; i++) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (adapter.isEnabled()) {
                mHsBlue24 = new HsBlue24(adapter);
            }
        }
        return mHsBlue24;
    }

    private HsBlue24(BluetoothAdapter bluetoothAdapter) {
        this.mScheduleTaskExecutor.setRemoveOnCancelPolicy(true);
        this.mBluetoothAdapter = bluetoothAdapter;
        this.mBluetoothLeAdvertiser = this.mBluetoothAdapter.getBluetoothLeAdvertiser();
        this.mAdvertiseCallback = new AdvertiseCallback() {
            public void onStartSuccess(AdvertiseSettings advertiseSettings) {
                super.onStartSuccess(advertiseSettings);
            }

            public void onStartFailure(int i) {
                super.onStartFailure(i);
                if (i == 1) {
                    String unused = HsBlue24.this.mErrorString = "Failed to start transmitting as the transmitting data to be sent is larger than 14 bytes.";
                } else if (i == 2) {
                    String unused2 = HsBlue24.this.mErrorString = "Failed to start transmitting because no transmitter instance is available.";
                } else if (i == 3) {
                    String unused3 = HsBlue24.this.mErrorString = "Failed to start transmitting as the transmitting is already started.";
                } else if (i == 4) {
                    String unused4 = HsBlue24.this.mErrorString = "Operation failed due to an internal error.";
                } else if (i == 5) {
                    String unused5 = HsBlue24.this.mErrorString = "The required feature is not supported on this platform.";
                }
            }
        };
        this.bluetoothLeScanner = this.mBluetoothAdapter.getBluetoothLeScanner();
        this.mScanCallback = new ScanCallback() {
            public void onScanFailed(int i) {
                super.onScanFailed(i);
                if (i == 1) {
                    String unused = HsBlue24.this.mErrorString = "Fails to start receiving as HsBlue24 receiving with the same settings is already started by the app.";
                } else if (i == 2) {
                    String unused2 = HsBlue24.this.mErrorString = "Fails to start receiving as app cannot be registered.";
                } else if (i == 3) {
                    String unused3 = HsBlue24.this.mErrorString = "Fails to start receiving due an internal error.";
                } else if (i == 4) {
                    String unused4 = HsBlue24.this.mErrorString = "Fails to start power optimized receiving as this feature is not supported.";
                }
            }

            public void onBatchScanResults(List<ScanResult> list) {
                super.onBatchScanResults(list);
            }

            public void onScanResult(int i, ScanResult scanResult) {
                super.onScanResult(i, scanResult);
                if (!HsBlue24.this.mRxStopped && HsBlue24.this.mRxCallBack != null) {
                    RxResult rxResult = new RxResult();
                    rxResult.setDeviceName(scanResult.getScanRecord().getDeviceName());
                    rxResult.setDeviceAddress(scanResult.getDevice().getAddress());
                    boolean z = true;
                    if (scanResult.getScanRecord().getManufacturerSpecificData().size() == 1) {
                        int keyAt = scanResult.getScanRecord().getManufacturerSpecificData().keyAt(0);
                        rxResult.setUserId(keyAt);
                        rxResult.setUserData(scanResult.getScanRecord().getManufacturerSpecificData(keyAt));
                        if (HsBlue24.this.mUserIdsOfRxFilters.size() > 0) {
                            Iterator it = HsBlue24.this.mUserIdsOfRxFilters.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    if (((Integer) it.next()).intValue() == keyAt) {
                                        break;
                                    }
                                } else {
                                    z = false;
                                    break;
                                }
                            }
                            if (!z) {
                                return;
                            }
                        }
                        rxResult.setRssi(scanResult.getRssi());
                        rxResult.setTimestampNanos(scanResult.getTimestampNanos());
                        byte[] bytes = scanResult.getScanRecord().getBytes();
                        rxResult.setRecord(bytes);
                        byte[] ble_whitening_for_ble_advRecord = BleAdvertisingPacket.ble_whitening_for_ble_advRecord(bytes, HsBlue24.advertising_channel[0]);
                        rxResult.setRecordInTheAir(HsBlue24.bit_reverse_in_byte(ble_whitening_for_ble_advRecord, 0, ble_whitening_for_ble_advRecord.length));
                        HsBlue24.this.mRxCallBack.onRxResult(rxResult);
                    }
                }
            }
        };
        this.mUserIdsOfRxFilters = new ArrayList<>();
    }

    public boolean isRequiredFeatureSupported() {
        return this.mBluetoothLeAdvertiser != null;
    }

    public void startTx(byte[] bArr, byte[] bArr2, int i) {
        stopTx();
        AdvertiseSettings build = new AdvertiseSettings.Builder().setAdvertiseMode(2).setConnectable(true).setTimeout(i).setTxPowerLevel(3).build();
        byte[] data = get_HsRfPacket_iOSCompatible(bArr, bArr2).getData();
        byte[] ble_whitening_for_rf_packet = BleAdvertisingPacket.ble_whitening_for_rf_packet(bit_reverse_in_byte(data, 0, data.length), advertising_channel[0]);
        int i2 = ((ble_whitening_for_rf_packet[1] & UByte.MAX_VALUE) << 8) + (ble_whitening_for_rf_packet[0] & UByte.MAX_VALUE);
        new ParcelUuid(new UUID(((long) i2) << 32, 0));
        byte[] bArr3 = new byte[(ble_whitening_for_rf_packet.length - 2)];
        System.arraycopy(ble_whitening_for_rf_packet, 2, bArr3, 0, bArr3.length);
        this.mBluetoothLeAdvertiser.startAdvertising(build, new AdvertiseData.Builder().setIncludeDeviceName(false).setIncludeTxPowerLevel(false).addManufacturerData(i2, bArr3).build(), this.mAdvertiseCallback);
    }

    public void stopTx() {
        this.mBluetoothLeAdvertiser.stopAdvertising(this.mAdvertiseCallback);
    }

    public String getErrorString() {
        return this.mErrorString;
    }

    private void clearScanResults() {
        this.bluetoothLeScanner.flushPendingScanResults(this.mScanCallback);
    }

    public void startRx(List<RxFilter> list, RxCallBack rxCallBack) {
        stopRx();
        Future future = this.mFuture;
        if (future != null) {
            future.cancel(true);
        }
        this.mRxCallBack = rxCallBack;
        clearScanResults();
        ArrayList arrayList = new ArrayList();
        this.mUserIdsOfRxFilters.clear();
        for (RxFilter next : list) {
            ScanFilter.Builder builder = new ScanFilter.Builder();
            if (next.getDeviceName() != null) {
                builder.setDeviceName(next.getDeviceName());
            }
            if (next.getDeviceAddress() != null) {
                builder.setDeviceAddress(next.getDeviceAddress());
            }
            if (next.getUserId() != -1) {
                this.mUserIdsOfRxFilters.add(Integer.valueOf(next.getUserId()));
            }
            arrayList.add(builder.build());
        }
        ScanSettings build = new ScanSettings.Builder().setScanMode(2).setReportDelay(0).build();
        this.mRxStopped = false;
        this.bluetoothLeScanner.startScan(arrayList, build, this.mScanCallback);
    }

    public void stopRx() {
        this.mRxStopped = true;
        clearScanResults();
        Future future = this.mFuture;
        if (future != null) {
            future.cancel(true);
        }
        this.mFuture = this.mScheduleTaskExecutor.schedule(new Runnable() {
            public void run() {
                HsBlue24.this.bluetoothLeScanner.stopScan(HsBlue24.this.mScanCallback);
            }
        }, 5100, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: private */
    public static byte[] bit_reverse_in_byte(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i3 = 0; i3 < bArr.length; i3++) {
            int i4 = 0;
            for (int i5 = 0; i5 < 8; i5++) {
                i4 += (((bArr[i3] & UByte.MAX_VALUE) >>> (7 - i5)) & 1) << i5;
            }
            bArr2[i3] = (byte) i4;
        }
        return bArr2;
    }

    /* renamed from: com.huntersun.rf.hsblue24lib.HsBlue24$BleAdvertisingPacket */
    private static class BleAdvertisingPacket {
        static final int ble_advertising_pdu_header_length = 2;
        static final int ble_advertising_pdu_payload_ADV_IND_AdvA_length = 6;

        /* renamed from: ble_advertising_pdu_payload_ADV_IND_AdvData_Ad_Structure1_of_Flags_length */
        static final int f1691x8e3c8fa5 = 3;

        /* renamed from: ble_advertising_pdu_payload_ADV_IND_AdvData_Ad_Structure2_of_ServiceDataOrManufactureData_leading_length */
        static final int f1692x1eafe0cd = 2;

        private BleAdvertisingPacket() {
        }

        public static byte[] ble_whitening_for_ble_advRecord(byte[] bArr, int i) {
            return ble_whitening_ex(bArr, i, 8);
        }

        public static byte[] ble_whitening_for_rf_packet(byte[] bArr, int i) {
            return ble_whitening_ex(bArr, i, 13);
        }

        private static byte[] ble_whitening_ex(byte[] bArr, int i, int i2) {
            byte[] bArr2 = new byte[(bArr.length + i2)];
            System.arraycopy(bArr, 0, bArr2, i2, bArr.length);
            byte[] ble_whitening = ble_whitening(bArr2, i);
            byte[] bArr3 = new byte[bArr.length];
            System.arraycopy(ble_whitening, i2, bArr3, 0, bArr.length);
            return bArr3;
        }

        private static byte[] ble_whitening(byte[] bArr, int i) {
            byte[] bArr2 = new byte[bArr.length];
            int i2 = (((i & 1) << 6) | ((i & 32) >>> 4) | 1 | ((i & 16) >>> 2) | ((i & 8) << 0) | ((i & 4) << 2) | ((i & 2) << 4)) & 255;
            int i3 = 0;
            while (i3 < bArr.length) {
                int i4 = i2;
                byte b = 0;
                for (int i5 = 0; i5 < 8; i5++) {
                    int i6 = i4 & 255;
                    b |= ((((i6 & 64) >>> 6) << i5) ^ (bArr[i3] & UByte.MAX_VALUE)) & (1 << i5);
                    int i7 = i6 << 1;
                    int i8 = (i7 >>> 7) & 1;
                    int i9 = (i7 & -2) | i8;
                    i4 = ((i9 ^ (i8 << 4)) & 16) | (i9 & -17);
                }
                bArr2[i3] = (byte) b;
                i3++;
                i2 = i4;
            }
            return bArr2;
        }
    }

    /* renamed from: com.huntersun.rf.hsblue24lib.HsBlue24$HsRfPacket */
    private static class HsRfPacket {
        static final int address_length = 5;
        static final int address_start = 1;
        static final int crc_length = 2;
        static final int guard_length = 2;
        static final int guard_start = 6;
        static int payload_length = 16;
        static final int payload_start = 8;
        static final int preamble_length = 1;
        static final int preamble_start = 0;
        private byte[] mBytes = new byte[packet_length()];

        static int packet_length() {
            return payload_length + 8 + 2;
        }

        static int crc_start() {
            return payload_length + 8;
        }

        HsRfPacket() {
            fill_zero();
        }

        HsRfPacket(byte[] bArr, byte[] bArr2, int i) {
            payload_length = i;
            fill_zero();
            this.mBytes[0] = (bArr[0] & ByteCompanionObject.MIN_VALUE) == 128 ? (byte) -86 : 85;
            System.arraycopy(bArr, 0, this.mBytes, 1, Math.min(bArr.length, 5));
            for (int i2 = 0; i2 < 2; i2++) {
                byte[] bArr3 = this.mBytes;
                bArr3[i2 + 6] = bArr3[5];
            }
            System.arraycopy(bArr2, 0, this.mBytes, 8, Math.min(bArr2.length, payload_length));
            int i3 = CRC16.get_ccitt_crc16(this.mBytes, 8, payload_length, CRC16.get_ccitt_crc16(this.mBytes, 1, 5, 65535));
            this.mBytes[crc_start()] = (byte) ((i3 >>> 8) & 255);
            this.mBytes[crc_start() + 1] = (byte) (i3 & 255);
        }

        public byte[] getData() {
            return this.mBytes;
        }

        private void fill_zero() {
            int i = 0;
            while (true) {
                byte[] bArr = this.mBytes;
                if (i < bArr.length) {
                    bArr[i] = 0;
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    static HsRfPacket get_HsRfPacket_iOSCompatible(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr2, 0, bArr3, 0, Math.min(bArr2.length, 8));
        if (bArr2.length >= 9) {
            bArr3[11] = bArr2[8];
            if (bArr2.length >= 10) {
                bArr3[13] = bArr2[9];
                if (bArr2.length >= 11) {
                    bArr3[15] = bArr2[10];
                }
            }
        }
        bArr3[8] = 76;
        bArr3[9] = -1;
        bArr3[10] = 0;
        bArr3[12] = 1;
        bArr3[14] = 2;
        return new HsRfPacket(bArr, bArr3, bArr3.length);
    }

    private static void main(String[] strArr) {
        byte[] data = get_HsRfPacket_iOSCompatible(new byte[]{-104, 67, -81, 11, 70}, new byte[]{1, 2, 3, 4, 5, -81}).getData();
        bit_reverse_in_byte(data, 0, data.length);
        System.out.println(data);
        byte[] bArr = {1, 2};
        byte[] bArr2 = {3, 4};
        System.out.println(CRC16.get_ccitt_crc16(bArr2, 0, bArr2.length, CRC16.get_ccitt_crc16(bArr, 0, bArr.length, 65535)));
    }
}
