package com.mob.tools.utils;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BHelper {
    private static final String TAG = "BHelper";
    private static BHelper instance;
    /* access modifiers changed from: private */
    public Map<String, BOperationCallback> bOperationCallbackMap;
    private BroadcastReceiver bOperationReceiver;
    private boolean bOperationRegistered = false;
    private BroadcastReceiver bScanReceiver;
    private boolean bScanRegistered = false;
    private Context context;
    /* access modifiers changed from: private */
    public boolean mScanning = false;

    public static class BOperationCallback {
        /* access modifiers changed from: protected */
        public void onConnectionChanged(boolean z, HashMap<String, Object> hashMap) {
        }

        /* access modifiers changed from: protected */
        public void onDeviceConnected(HashMap<String, Object> hashMap) {
        }

        /* access modifiers changed from: protected */
        public void onDeviceDisconnected(HashMap<String, Object> hashMap) {
        }

        /* access modifiers changed from: protected */
        public void onDisabled() {
        }

        /* access modifiers changed from: protected */
        public void onEnabled() {
        }
    }

    public interface BScanCallback {
        void onScan(ArrayList<HashMap<String, Object>> arrayList);
    }

    private BHelper(Context context2) {
        this.context = context2.getApplicationContext();
    }

    public static BHelper getInstance(Context context2) {
        if (instance == null) {
            synchronized (BHelper.class) {
                if (instance == null) {
                    instance = new BHelper(context2);
                }
            }
        }
        return instance;
    }

    public void unRegisterBOperationReceiver(String str) {
        try {
            if (this.bOperationCallbackMap != null && !this.bOperationCallbackMap.containsKey(str)) {
                this.bOperationCallbackMap.remove(str);
            }
            if (this.bOperationCallbackMap.isEmpty() && this.bOperationReceiver != null && this.bOperationRegistered) {
                ReflectHelper.invokeInstanceMethod(this.context, "unregisterReceiver", new Object[]{this.bOperationReceiver}, new Class[]{BroadcastReceiver.class});
                this.bOperationRegistered = false;
                this.bOperationReceiver = null;
            }
        } catch (Throwable th) {
            NLog instance2 = MobLog.getInstance();
            instance2.mo29770d(th, th.getMessage() + "", new Object[0]);
        }
    }

    public void registerBOperationReceiver(String str, BOperationCallback bOperationCallback) {
        if (bOperationCallback != null) {
            if (this.bOperationCallbackMap == null) {
                this.bOperationCallbackMap = new HashMap();
            }
            this.bOperationCallbackMap.put(str, bOperationCallback);
            if (this.bOperationReceiver == null) {
                try {
                    this.bOperationReceiver = new BroadcastReceiver() {
                        public void onReceive(Context context, Intent intent) {
                            try {
                                String action = intent.getAction();
                                if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {
                                    int intExtra = intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 0);
                                    intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_STATE", 0);
                                    switch (intExtra) {
                                        case 10:
                                            if (BHelper.this.bOperationCallbackMap != null && !BHelper.this.bOperationCallbackMap.isEmpty()) {
                                                for (Map.Entry value : BHelper.this.bOperationCallbackMap.entrySet()) {
                                                    BOperationCallback bOperationCallback = (BOperationCallback) value.getValue();
                                                    if (bOperationCallback != null) {
                                                        bOperationCallback.onDisabled();
                                                    }
                                                }
                                                return;
                                            }
                                            return;
                                        case 11:
                                        case 13:
                                            return;
                                        case 12:
                                            if (BHelper.this.bOperationCallbackMap != null && !BHelper.this.bOperationCallbackMap.isEmpty()) {
                                                for (Map.Entry value2 : BHelper.this.bOperationCallbackMap.entrySet()) {
                                                    BOperationCallback bOperationCallback2 = (BOperationCallback) value2.getValue();
                                                    if (bOperationCallback2 != null) {
                                                        bOperationCallback2.onEnabled();
                                                    }
                                                }
                                                return;
                                            }
                                            return;
                                        default:
                                            return;
                                    }
                                } else if (action.equals("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED")) {
                                    int intExtra2 = intent.getIntExtra("android.bluetooth.adapter.extra.CONNECTION_STATE", 0);
                                    intent.getIntExtra("android.bluetooth.adapter.extra.PREVIOUS_CONNECTION_STATE", 0);
                                    HashMap access$100 = BHelper.this.parseDevice2Map((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
                                    if (intExtra2 != 0) {
                                        if (intExtra2 == 1) {
                                            return;
                                        }
                                        if (intExtra2 == 2) {
                                            if (BHelper.this.bOperationCallbackMap != null && !BHelper.this.bOperationCallbackMap.isEmpty()) {
                                                for (Map.Entry value3 : BHelper.this.bOperationCallbackMap.entrySet()) {
                                                    BOperationCallback bOperationCallback3 = (BOperationCallback) value3.getValue();
                                                    if (bOperationCallback3 != null) {
                                                        bOperationCallback3.onConnectionChanged(true, access$100);
                                                    }
                                                }
                                            }
                                        }
                                    } else if (BHelper.this.bOperationCallbackMap != null && !BHelper.this.bOperationCallbackMap.isEmpty()) {
                                        for (Map.Entry value4 : BHelper.this.bOperationCallbackMap.entrySet()) {
                                            BOperationCallback bOperationCallback4 = (BOperationCallback) value4.getValue();
                                            if (bOperationCallback4 != null) {
                                                bOperationCallback4.onConnectionChanged(false, access$100);
                                            }
                                        }
                                    }
                                } else if (action.equals("android.bluetooth.device.action.ACL_CONNECTED")) {
                                    HashMap access$1002 = BHelper.this.parseDevice2Map((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
                                    if (BHelper.this.bOperationCallbackMap != null && !BHelper.this.bOperationCallbackMap.isEmpty()) {
                                        for (Map.Entry value5 : BHelper.this.bOperationCallbackMap.entrySet()) {
                                            BOperationCallback bOperationCallback5 = (BOperationCallback) value5.getValue();
                                            if (bOperationCallback5 != null) {
                                                bOperationCallback5.onDeviceConnected(access$1002);
                                            }
                                        }
                                    }
                                } else if (action.equals("android.bluetooth.device.action.ACL_DISCONNECTED")) {
                                    HashMap access$1003 = BHelper.this.parseDevice2Map((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
                                    if (BHelper.this.bOperationCallbackMap != null && !BHelper.this.bOperationCallbackMap.isEmpty()) {
                                        for (Map.Entry value6 : BHelper.this.bOperationCallbackMap.entrySet()) {
                                            BOperationCallback bOperationCallback6 = (BOperationCallback) value6.getValue();
                                            if (bOperationCallback6 != null) {
                                                bOperationCallback6.onDeviceDisconnected(access$1003);
                                            }
                                        }
                                    }
                                }
                            } catch (Throwable th) {
                                NLog instance = MobLog.getInstance();
                                instance.mo29770d(th, th.getMessage() + "", new Object[0]);
                            }
                        }
                    };
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
                    intentFilter.addAction("android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED");
                    intentFilter.addAction("android.bluetooth.device.action.ACL_CONNECTED");
                    intentFilter.addAction("android.bluetooth.device.action.ACL_DISCONNECTED");
                    ReflectHelper.invokeInstanceMethod(this.context, "registerReceiver", new Object[]{this.bOperationReceiver, intentFilter}, new Class[]{BroadcastReceiver.class, IntentFilter.class});
                    this.bOperationRegistered = true;
                } catch (Throwable th) {
                    NLog instance2 = MobLog.getInstance();
                    instance2.mo29770d(th, th.getMessage() + "", new Object[0]);
                }
            }
        }
    }

    @SuppressLint({"MissingPermission"})
    public void open() {
        try {
            if (DeviceHelper.getInstance(this.context).checkPermission("android.permission.BLUETOOTH") && DeviceHelper.getInstance(this.context).checkPermission("android.permission.BLUETOOTH_ADMIN")) {
                getBAdapter().enable();
            }
        } catch (Throwable th) {
            NLog instance2 = MobLog.getInstance();
            instance2.mo29770d(th, th.getMessage() + "", new Object[0]);
        }
    }

    @SuppressLint({"MissingPermission"})
    public boolean isEnabled() {
        BluetoothAdapter bAdapter;
        try {
            if (!DeviceHelper.getInstance(this.context).checkPermission("android.permission.BLUETOOTH") || (bAdapter = getBAdapter()) == null || !bAdapter.isEnabled()) {
                return false;
            }
            return true;
        } catch (Throwable th) {
            NLog instance2 = MobLog.getInstance();
            instance2.mo29770d(th, th.getMessage() + "", new Object[0]);
            return false;
        }
    }

    @SuppressLint({"MissingPermission"})
    public ArrayList<HashMap<String, Object>> getBondedDevice() {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        try {
            if (DeviceHelper.getInstance(this.context).checkPermission("android.permission.BLUETOOTH")) {
                Set<BluetoothDevice> bondedDevices = getBAdapter().getBondedDevices();
                if (bondedDevices.size() > 0) {
                    for (BluetoothDevice next : bondedDevices) {
                        HashMap<String, Object> parseDevice2Map = parseDevice2Map(next);
                        parseDevice2Map.put("__currConnected", Integer.valueOf(isConnectedDevice(next) ? 1 : 0));
                        arrayList.add(parseDevice2Map);
                    }
                }
            }
        } catch (Throwable th) {
            NLog instance2 = MobLog.getInstance();
            instance2.mo29770d(th, th.getMessage() + "", new Object[0]);
        }
        return arrayList;
    }

    public boolean isConnectedDevice(BluetoothDevice bluetoothDevice) {
        Boolean bool;
        if (bluetoothDevice == null) {
            return false;
        }
        try {
            if (!DeviceHelper.getInstance(this.context).checkPermission("android.permission.BLUETOOTH") || (bool = (Boolean) ReflectHelper.invokeInstanceMethod(bluetoothDevice, Strings.getString(115), new Object[0])) == null) {
                return false;
            }
            return bool.booleanValue();
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return false;
        }
    }

    @SuppressLint({"MissingPermission"})
    public void findLEAndClassic(int i, final BScanCallback bScanCallback) {
        try {
            if (!DeviceHelper.getInstance(this.context).checkPermission("android.permission.BLUETOOTH") || !DeviceHelper.getInstance(this.context).checkPermission("android.permission.BLUETOOTH_ADMIN")) {
                bScanCallback.onScan(new ArrayList());
                return;
            }
            final BluetoothAdapter bAdapter = getBAdapter();
            if (!bAdapter.isEnabled()) {
                bScanCallback.onScan(new ArrayList());
            } else if (this.mScanning) {
                bScanCallback.onScan(new ArrayList());
            } else {
                if (i <= 0) {
                    i = 6;
                }
                final Handler newHandler = MobHandlerThread.newHandler(new Handler.Callback() {
                    public boolean handleMessage(Message message) {
                        bAdapter.cancelDiscovery();
                        return false;
                    }
                });
                newHandler.sendEmptyMessageDelayed(0, (long) (i * 1000));
                final ArrayList arrayList = new ArrayList();
                this.bScanReceiver = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        try {
                            String action = intent.getAction();
                            if (action.equals("android.bluetooth.device.action.FOUND")) {
                                HashMap access$100 = BHelper.this.parseDevice2Map((BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE"));
                                access$100.put("rssi", Short.valueOf(intent.getShortExtra("android.bluetooth.device.extra.RSSI", 0)));
                                arrayList.add(access$100);
                            } else if (action.equals("android.bluetooth.adapter.action.DISCOVERY_STARTED")) {
                                MobLog.getInstance().mo29768d("started", new Object[0]);
                            } else if (action.equals("android.bluetooth.adapter.action.DISCOVERY_FINISHED")) {
                                MobLog.getInstance().mo29768d("done", new Object[0]);
                                boolean unused = BHelper.this.mScanning = false;
                                bScanCallback.onScan(arrayList);
                                newHandler.removeMessages(0);
                                BHelper.this.unRegisterBScanReceiver();
                            }
                        } catch (Throwable th) {
                            NLog instance = MobLog.getInstance();
                            instance.mo29770d(th, th.getMessage() + "", new Object[0]);
                        }
                    }
                };
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.bluetooth.device.action.FOUND");
                intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_STARTED");
                intentFilter.addAction("android.bluetooth.adapter.action.DISCOVERY_FINISHED");
                ReflectHelper.invokeInstanceMethod(this.context, "registerReceiver", new Object[]{this.bScanReceiver, intentFilter}, new Class[]{BroadcastReceiver.class, IntentFilter.class});
                this.bScanRegistered = true;
                this.mScanning = true;
                bAdapter.startDiscovery();
            }
        } catch (Throwable th) {
            NLog instance2 = MobLog.getInstance();
            instance2.mo29770d(th, th.getMessage() + "", new Object[0]);
            bScanCallback.onScan(new ArrayList());
        }
    }

    public void unRegisterBScanReceiver() {
        try {
            if (this.bScanReceiver != null && this.bScanRegistered) {
                ReflectHelper.invokeInstanceMethod(this.context, "unregisterReceiver", new Object[]{this.bScanReceiver}, new Class[]{BroadcastReceiver.class});
                this.bScanRegistered = false;
                this.bScanReceiver = null;
            }
        } catch (Throwable th) {
            NLog instance2 = MobLog.getInstance();
            instance2.mo29770d(th, th.getMessage() + "", new Object[0]);
        }
    }

    @SuppressLint({"MissingPermission"})
    public void findLE(int i, BluetoothAdapter bluetoothAdapter, BScanCallback bScanCallback) {
        try {
            if (!DeviceHelper.getInstance(this.context).checkPermission("android.permission.BLUETOOTH") || !DeviceHelper.getInstance(this.context).checkPermission("android.permission.BLUETOOTH_ADMIN")) {
                bScanCallback.onScan(new ArrayList());
            } else if (!bluetoothAdapter.isEnabled()) {
                bScanCallback.onScan(new ArrayList());
            } else if (this.mScanning) {
                bScanCallback.onScan(new ArrayList());
            } else {
                if (i <= 0) {
                    i = 6;
                }
                if (Build.VERSION.SDK_INT >= 18) {
                    final ArrayList arrayList = new ArrayList();
                    if (Build.VERSION.SDK_INT < 21) {
                        final C24644 r6 = new BluetoothAdapter.LeScanCallback() {
                            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
                                try {
                                    HashMap access$100 = BHelper.this.parseDevice2Map(bluetoothDevice);
                                    access$100.put("rssi", Integer.valueOf(i));
                                    arrayList.add(access$100);
                                } catch (Throwable th) {
                                    NLog instance = MobLog.getInstance();
                                    instance.mo29770d(th, th.getMessage() + "", new Object[0]);
                                }
                            }
                        };
                        this.mScanning = true;
                        bluetoothAdapter.startLeScan(r6);
                        final BluetoothAdapter bluetoothAdapter2 = bluetoothAdapter;
                        final BScanCallback bScanCallback2 = bScanCallback;
                        MobHandlerThread.newHandler(new Handler.Callback() {
                            public boolean handleMessage(Message message) {
                                boolean unused = BHelper.this.mScanning = false;
                                bluetoothAdapter2.stopLeScan(r6);
                                bScanCallback2.onScan(arrayList);
                                return false;
                            }
                        }).sendEmptyMessageDelayed(0, (long) (i * 1000));
                        return;
                    }
                    final C24666 r62 = new ScanCallback() {
                        public void onScanResult(int i, ScanResult scanResult) {
                            super.onScanResult(i, scanResult);
                            if (scanResult != null) {
                                HashMap hashMap = new HashMap();
                                BluetoothDevice device = scanResult.getDevice();
                                if (device != null) {
                                    hashMap = BHelper.this.parseDevice2Map(device);
                                }
                                hashMap.put("rssi", Integer.valueOf(scanResult.getRssi()));
                                scanResult.getScanRecord();
                                arrayList.add(hashMap);
                            }
                        }

                        public void onBatchScanResults(List<ScanResult> list) {
                            super.onBatchScanResults(list);
                        }

                        public void onScanFailed(int i) {
                            super.onScanFailed(i);
                        }
                    };
                    final BluetoothLeScanner bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
                    this.mScanning = true;
                    bluetoothLeScanner.startScan(r62);
                    final BScanCallback bScanCallback3 = bScanCallback;
                    MobHandlerThread.newHandler(new Handler.Callback() {
                        public boolean handleMessage(Message message) {
                            boolean unused = BHelper.this.mScanning = false;
                            bluetoothLeScanner.stopScan(r62);
                            bScanCallback3.onScan(arrayList);
                            return false;
                        }
                    }).sendEmptyMessageDelayed(0, (long) (i * 1000));
                    return;
                }
                findLEAndClassic(i, bScanCallback);
            }
        } catch (Throwable th) {
            NLog instance2 = MobLog.getInstance();
            instance2.mo29770d(th, th.getMessage() + "", new Object[0]);
            bScanCallback.onScan(new ArrayList());
        }
    }

    private BluetoothAdapter getBAdapter() {
        try {
            if (Build.VERSION.SDK_INT >= 18) {
                return ((BluetoothManager) DeviceHelper.getInstance(this.context).getSystemServiceSafe("bluetooth")).getAdapter();
            }
            return BluetoothAdapter.getDefaultAdapter();
        } catch (Throwable th) {
            NLog instance2 = MobLog.getInstance();
            instance2.mo29770d(th, th.getMessage() + "", new Object[0]);
            return null;
        }
    }

    /* access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public HashMap<String, Object> parseDevice2Map(BluetoothDevice bluetoothDevice) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (bluetoothDevice != null) {
            try {
                hashMap.put("name", bluetoothDevice.getName());
                hashMap.put("address", bluetoothDevice.getAddress());
                hashMap.put("bondState", Integer.valueOf(bluetoothDevice.getBondState()));
                BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
                int majorDeviceClass = bluetoothClass.getMajorDeviceClass();
                int deviceClass = bluetoothClass.getDeviceClass();
                hashMap.put("majorClass", Integer.valueOf(majorDeviceClass));
                hashMap.put("deviceClass", Integer.valueOf(deviceClass));
                if (Build.VERSION.SDK_INT >= 18) {
                    hashMap.put("type", Integer.valueOf(bluetoothDevice.getType()));
                }
                if (Build.VERSION.SDK_INT >= 15 && bluetoothDevice.getBondState() == 12) {
                    ArrayList arrayList = new ArrayList();
                    ParcelUuid[] uuids = bluetoothDevice.getUuids();
                    if (uuids != null && uuids.length > 0) {
                        for (ParcelUuid parcelUuid : uuids) {
                            if (!(parcelUuid == null || parcelUuid.getUuid() == null)) {
                                arrayList.add(parcelUuid.getUuid().toString());
                            }
                        }
                    }
                    hashMap.put("uuids", arrayList);
                }
            } catch (Throwable th) {
                MobLog.getInstance().mo29769d(th);
            }
        }
        return hashMap;
    }
}
