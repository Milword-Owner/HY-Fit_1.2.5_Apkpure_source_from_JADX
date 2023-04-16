package com.mob.tools.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.os.Build;
import android.text.TextUtils;
import com.hjq.permissions.Permission;
import com.mob.tools.MobLog;
import p015io.reactivex.annotations.SchedulerSupport;

public class NtFetcher {
    private static NtFetcher instance;
    private Context context;
    private DeviceHelper device;
    private Integer dtNtType;
    private String ntType;
    private BroadcastReceiver receiver;

    private NtFetcher(Context context2) {
        this.context = context2;
        this.device = DeviceHelper.getInstance(context2);
        if (this.device.isSensitiveDevice()) {
            prepare();
        }
    }

    public static NtFetcher getInstance(Context context2) {
        if (instance == null) {
            synchronized (NtFetcher.class) {
                if (instance == null) {
                    instance = new NtFetcher(context2);
                }
            }
        }
        return instance;
    }

    @SuppressLint({"MissingPermission"})
    private void prepare() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) this.device.getSystemServiceSafe("connectivity");
            if (Build.VERSION.SDK_INT >= 26) {
                if (this.device.checkPermission("android.permission.ACCESS_NETWORK_STATE")) {
                    connectivityManager.registerDefaultNetworkCallback(initNetworkCallback());
                    return;
                }
            }
            if (Build.VERSION.SDK_INT < 21 || !this.device.checkPermission("android.permission.ACCESS_NETWORK_STATE")) {
                registerRcv();
            } else {
                connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().build(), initNetworkCallback());
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }

    public void recycle() {
        unregisterRcv();
    }

    public synchronized String getNtType() {
        if (!this.device.isSensitiveDevice() || TextUtils.isEmpty(this.ntType)) {
            this.ntType = getNetworkType();
        }
        return this.ntType;
    }

    public synchronized int getDtNtType() {
        if (!this.device.isSensitiveDevice() || this.dtNtType == null) {
            this.dtNtType = Integer.valueOf(getDataNtType());
        }
        return this.dtNtType.intValue();
    }

    @TargetApi(21)
    private ConnectivityManager.NetworkCallback initNetworkCallback() {
        return new ConnectivityManager.NetworkCallback() {
            public void onAvailable(Network network) {
                super.onAvailable(network);
                NtFetcher.this.refreshNet();
            }

            public void onLost(Network network) {
                super.onLost(network);
                NtFetcher.this.refreshNet();
            }

            public void onLosing(Network network, int i) {
                super.onLosing(network, i);
            }

            public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities);
            }

            public void onLinkPropertiesChanged(Network network, LinkProperties linkProperties) {
                super.onLinkPropertiesChanged(network, linkProperties);
            }

            public void onUnavailable() {
                super.onUnavailable();
            }
        };
    }

    /* access modifiers changed from: private */
    public void refreshNet() {
        this.ntType = getNetworkType();
        this.dtNtType = Integer.valueOf(getDataNtType());
    }

    private int getDataNtType() {
        int i;
        Object systemServiceSafe = this.device.getSystemServiceSafe("phone");
        if (systemServiceSafe == null) {
            return -1;
        }
        try {
            if (Build.VERSION.SDK_INT < 24 || !this.device.checkPermission(Permission.READ_PHONE_STATE)) {
                i = ((Integer) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(19), new Object[0])).intValue();
            } else {
                i = ((Integer) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(101), new Object[0])).intValue();
            }
            return i;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return -1;
        }
    }

    private void registerRcv() {
        this.receiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                try {
                    if (intent.getAction().equalsIgnoreCase("android.net.conn.CONNECTIVITY_CHANGE")) {
                        NtFetcher.this.refreshNet();
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().mo29769d(th);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        try {
            ReflectHelper.invokeInstanceMethod(this.context, "registerReceiver", new Object[]{this.receiver, intentFilter}, new Class[]{BroadcastReceiver.class, IntentFilter.class});
        } catch (Throwable unused) {
        }
    }

    private void unregisterRcv() {
        BroadcastReceiver broadcastReceiver = this.receiver;
        if (broadcastReceiver != null) {
            try {
                ReflectHelper.invokeInstanceMethod(this.context, "unregisterReceiver", new Object[]{broadcastReceiver}, new Class[]{BroadcastReceiver.class});
            } catch (Throwable unused) {
            }
            this.receiver = null;
        }
    }

    private String getNetworkType() {
        Object systemServiceSafe;
        NetworkInfo activeNetworkInfo;
        try {
            if (!this.device.checkPermission("android.permission.ACCESS_NETWORK_STATE") || (systemServiceSafe = this.device.getSystemServiceSafe("connectivity")) == null || (activeNetworkInfo = ((ConnectivityManager) systemServiceSafe).getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
                return SchedulerSupport.NONE;
            }
            int type = activeNetworkInfo.getType();
            if (type != 0) {
                if (type == 1) {
                    return "wifi";
                }
                switch (type) {
                    case 6:
                        return "wimax";
                    case 7:
                        return "bluetooth";
                    case 8:
                        return "dummy";
                    case 9:
                        return "ethernet";
                    default:
                        return String.valueOf(type);
                }
            } else if (is5GMobileNetwork()) {
                return "5G";
            } else {
                if (is4GMobileNetwork()) {
                    return "4G";
                }
                return isFastMobileNetwork() ? "3G" : "2G";
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return SchedulerSupport.NONE;
        }
    }

    private boolean is5GMobileNetwork() {
        if (!is5GHw() && !maybe5G()) {
            return is5GCommon();
        }
        return true;
    }

    private boolean is5GHw() {
        Object systemServiceSafe;
        try {
            if (this.device.checkPermission(Permission.READ_PHONE_STATE)) {
                String manufacturer = this.device.getManufacturer();
                if (TextUtils.isEmpty(manufacturer) || ((!manufacturer.contains("huawei") && !manufacturer.contains("Huawei") && !manufacturer.contains("HUAWEI")) || (systemServiceSafe = this.device.getSystemServiceSafe("phone")) == null || Build.VERSION.SDK_INT < 29 || ((Integer) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(129), new Object[0]), Strings.getString(131), new Object[0])).intValue() != 20)) {
                    return false;
                }
                return true;
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        return false;
    }

    private boolean maybe5G() {
        Object systemServiceSafe;
        try {
            if (!this.device.checkPermission(Permission.READ_PHONE_STATE) || Build.VERSION.SDK_INT < 26 || (systemServiceSafe = this.device.getSystemServiceSafe("phone")) == null || ((Integer) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(129), new Object[0]), Strings.getString(130), new Object[0])).intValue() != 3) {
                return false;
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private boolean is5GCommon() {
        Object systemServiceSafe = this.device.getSystemServiceSafe("phone");
        if (systemServiceSafe == null) {
            return false;
        }
        try {
            if (((Integer) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(19), new Object[0])).intValue() == 20) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return false;
        }
    }

    private boolean is4GMobileNetwork() {
        Object systemServiceSafe = this.device.getSystemServiceSafe("phone");
        if (systemServiceSafe == null) {
            return false;
        }
        try {
            if (((Integer) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(19), new Object[0])).intValue() == 13) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return false;
        }
    }

    private boolean isFastMobileNetwork() {
        Object systemServiceSafe = this.device.getSystemServiceSafe("phone");
        if (systemServiceSafe == null) {
            return false;
        }
        try {
            switch (((Integer) ReflectHelper.invokeInstanceMethod(systemServiceSafe, Strings.getString(19), new Object[0])).intValue()) {
                case 0:
                case 1:
                case 2:
                    return false;
                case 3:
                    return true;
                case 4:
                    return false;
                case 5:
                case 6:
                    return true;
                case 7:
                    return false;
                case 8:
                case 9:
                case 10:
                    return true;
                case 11:
                    return false;
                case 12:
                case 13:
                case 14:
                case 15:
                    return true;
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
        }
        return false;
    }
}
