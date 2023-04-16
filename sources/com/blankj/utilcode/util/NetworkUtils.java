package com.blankj.utilcode.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import com.blankj.utilcode.util.Utils;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArraySet;

public final class NetworkUtils {
    private static final long SCAN_PERIOD_MILLIS = 3000;
    /* access modifiers changed from: private */
    public static final Set<Utils.Consumer<WifiScanResults>> SCAN_RESULT_CONSUMERS = new CopyOnWriteArraySet();
    /* access modifiers changed from: private */
    public static WifiScanResults sPreWifiScanResults;
    private static Timer sScanWifiTimer;

    public enum NetworkType {
        NETWORK_ETHERNET,
        NETWORK_WIFI,
        NETWORK_5G,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO
    }

    public interface OnNetworkStatusChangedListener {
        void onConnected(NetworkType networkType);

        void onDisconnected();
    }

    private NetworkUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void openWirelessSettings() {
        Utils.getApp().startActivity(new Intent("android.settings.WIRELESS_SETTINGS").setFlags(268435456));
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isConnected() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task<Boolean> isAvailableAsync(@NonNull Utils.Consumer<Boolean> consumer) {
        if (consumer != null) {
            return UtilsBridge.doAsync(new Utils.Task<Boolean>(consumer) {
                @RequiresPermission("android.permission.INTERNET")
                public Boolean doInBackground() {
                    return Boolean.valueOf(NetworkUtils.isAvailable());
                }
            });
        }
        throw new NullPointerException("Argument 'consumer' of type Utils.Consumer<Boolean> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailable() {
        return isAvailableByDns() || isAvailableByPing((String) null);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static void isAvailableByPingAsync(Utils.Consumer<Boolean> consumer) {
        isAvailableByPingAsync("", consumer);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task<Boolean> isAvailableByPingAsync(final String str, @NonNull Utils.Consumer<Boolean> consumer) {
        if (consumer != null) {
            return UtilsBridge.doAsync(new Utils.Task<Boolean>(consumer) {
                @RequiresPermission("android.permission.INTERNET")
                public Boolean doInBackground() {
                    return Boolean.valueOf(NetworkUtils.isAvailableByPing(str));
                }
            });
        }
        throw new NullPointerException("Argument 'consumer' of type Utils.Consumer<Boolean> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByPing() {
        return isAvailableByPing("");
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByPing(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "223.5.5.5";
        }
        if (ShellUtils.execCmd(String.format("ping -c 1 %s", new Object[]{str}), false).result == 0) {
            return true;
        }
        return false;
    }

    @RequiresPermission("android.permission.INTERNET")
    public static void isAvailableByDnsAsync(Utils.Consumer<Boolean> consumer) {
        isAvailableByDnsAsync("", consumer);
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task isAvailableByDnsAsync(final String str, @NonNull Utils.Consumer<Boolean> consumer) {
        if (consumer != null) {
            return UtilsBridge.doAsync(new Utils.Task<Boolean>(consumer) {
                @RequiresPermission("android.permission.INTERNET")
                public Boolean doInBackground() {
                    return Boolean.valueOf(NetworkUtils.isAvailableByDns(str));
                }
            });
        }
        throw new NullPointerException("Argument 'consumer' of type Utils.Consumer<Boolean> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByDns() {
        return isAvailableByDns("");
    }

    @RequiresPermission("android.permission.INTERNET")
    public static boolean isAvailableByDns(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "www.baidu.com";
        }
        try {
            if (InetAddress.getByName(str) != null) {
                return true;
            }
            return false;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean getMobileDataEnabled() {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
            if (telephonyManager == null) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                return telephonyManager.isDataEnabled();
            }
            Method declaredMethod = telephonyManager.getClass().getDeclaredMethod("getDataEnabled", new Class[0]);
            if (declaredMethod != null) {
                return ((Boolean) declaredMethod.invoke(telephonyManager, new Object[0])).booleanValue();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isMobileData() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getType() == 0;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean is4G() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getSubtype() == 13;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean is5G() {
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getSubtype() == 20;
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static boolean getWifiEnabled() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return false;
        }
        return wifiManager.isWifiEnabled();
    }

    @RequiresPermission("android.permission.CHANGE_WIFI_STATE")
    public static void setWifiEnabled(boolean z) {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager != null && z != wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(z);
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static boolean isWifiConnected() {
        NetworkInfo activeNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return true;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static boolean isWifiAvailable() {
        return getWifiEnabled() && isAvailable();
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
    public static Utils.Task<Boolean> isWifiAvailableAsync(@NonNull Utils.Consumer<Boolean> consumer) {
        if (consumer != null) {
            return UtilsBridge.doAsync(new Utils.Task<Boolean>(consumer) {
                @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.INTERNET"})
                public Boolean doInBackground() {
                    return Boolean.valueOf(NetworkUtils.isWifiAvailable());
                }
            });
        }
        throw new NullPointerException("Argument 'consumer' of type Utils.Consumer<Boolean> (#0 out of 1, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    public static String getNetworkOperatorName() {
        TelephonyManager telephonyManager = (TelephonyManager) Utils.getApp().getSystemService("phone");
        if (telephonyManager == null) {
            return "";
        }
        return telephonyManager.getNetworkOperatorName();
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static NetworkType getNetworkType() {
        if (isEthernet()) {
            return NetworkType.NETWORK_ETHERNET;
        }
        NetworkInfo activeNetworkInfo = getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return NetworkType.NETWORK_NO;
        }
        if (activeNetworkInfo.getType() == 1) {
            return NetworkType.NETWORK_WIFI;
        }
        if (activeNetworkInfo.getType() != 0) {
            return NetworkType.NETWORK_UNKNOWN;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
            case 16:
                return NetworkType.NETWORK_2G;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 17:
                return NetworkType.NETWORK_3G;
            case 13:
            case 18:
                return NetworkType.NETWORK_4G;
            case 20:
                return NetworkType.NETWORK_5G;
            default:
                String subtypeName = activeNetworkInfo.getSubtypeName();
                if (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) {
                    return NetworkType.NETWORK_3G;
                }
                return NetworkType.NETWORK_UNKNOWN;
        }
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    private static boolean isEthernet() {
        NetworkInfo networkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(9)) == null || (state = networkInfo.getState()) == null) {
            return false;
        }
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            return true;
        }
        return false;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    private static NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Utils.getApp().getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    public static Utils.Task<String> getIPAddressAsync(final boolean z, @NonNull Utils.Consumer<String> consumer) {
        if (consumer != null) {
            return UtilsBridge.doAsync(new Utils.Task<String>(consumer) {
                @RequiresPermission("android.permission.INTERNET")
                public String doInBackground() {
                    return NetworkUtils.getIPAddress(z);
                }
            });
        }
        throw new NullPointerException("Argument 'consumer' of type Utils.Consumer<String> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @RequiresPermission("android.permission.INTERNET")
    public static String getIPAddress(boolean z) {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            LinkedList linkedList = new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp()) {
                    if (!nextElement.isLoopback()) {
                        Enumeration<InetAddress> inetAddresses = nextElement.getInetAddresses();
                        while (inetAddresses.hasMoreElements()) {
                            linkedList.addFirst(inetAddresses.nextElement());
                        }
                    }
                }
            }
            Iterator it = linkedList.iterator();
            while (it.hasNext()) {
                InetAddress inetAddress = (InetAddress) it.next();
                if (!inetAddress.isLoopbackAddress()) {
                    String hostAddress = inetAddress.getHostAddress();
                    boolean z2 = hostAddress.indexOf(58) < 0;
                    if (z) {
                        if (z2) {
                            return hostAddress;
                        }
                    } else if (!z2) {
                        int indexOf = hostAddress.indexOf(37);
                        if (indexOf < 0) {
                            return hostAddress.toUpperCase();
                        }
                        return hostAddress.substring(0, indexOf).toUpperCase();
                    }
                }
            }
            return "";
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getBroadcastIpAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            new LinkedList();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface nextElement = networkInterfaces.nextElement();
                if (nextElement.isUp()) {
                    if (!nextElement.isLoopback()) {
                        List<InterfaceAddress> interfaceAddresses = nextElement.getInterfaceAddresses();
                        int size = interfaceAddresses.size();
                        for (int i = 0; i < size; i++) {
                            InetAddress broadcast = interfaceAddresses.get(i).getBroadcast();
                            if (broadcast != null) {
                                return broadcast.getHostAddress();
                            }
                        }
                        continue;
                    }
                }
            }
            return "";
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public static Utils.Task<String> getDomainAddressAsync(final String str, @NonNull Utils.Consumer<String> consumer) {
        if (consumer != null) {
            return UtilsBridge.doAsync(new Utils.Task<String>(consumer) {
                @RequiresPermission("android.permission.INTERNET")
                public String doInBackground() {
                    return NetworkUtils.getDomainAddress(str);
                }
            });
        }
        throw new NullPointerException("Argument 'consumer' of type Utils.Consumer<String> (#1 out of 2, zero-based) is marked by @androidx.annotation.NonNull but got null for it");
    }

    @RequiresPermission("android.permission.INTERNET")
    public static String getDomainAddress(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getIpAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        return Formatter.formatIpAddress(wifiManager.getDhcpInfo().ipAddress);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getGatewayByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        return Formatter.formatIpAddress(wifiManager.getDhcpInfo().gateway);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getNetMaskByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        return Formatter.formatIpAddress(wifiManager.getDhcpInfo().netmask);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getServerAddressByWifi() {
        WifiManager wifiManager = (WifiManager) Utils.getApp().getSystemService("wifi");
        if (wifiManager == null) {
            return "";
        }
        return Formatter.formatIpAddress(wifiManager.getDhcpInfo().serverAddress);
    }

    @RequiresPermission("android.permission.ACCESS_WIFI_STATE")
    public static String getSSID() {
        WifiInfo connectionInfo;
        WifiManager wifiManager = (WifiManager) Utils.getApp().getApplicationContext().getSystemService("wifi");
        if (wifiManager == null || (connectionInfo = wifiManager.getConnectionInfo()) == null) {
            return "";
        }
        String ssid = connectionInfo.getSSID();
        if (TextUtils.isEmpty(ssid)) {
            return "";
        }
        return (ssid.length() > 2 && ssid.charAt(0) == '\"' && ssid.charAt(ssid.length() - 1) == '\"') ? ssid.substring(1, ssid.length() - 1) : ssid;
    }

    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
    public static void registerNetworkStatusChangedListener(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
        NetworkChangedReceiver.getInstance().registerListener(onNetworkStatusChangedListener);
    }

    public static boolean isRegisteredNetworkStatusChangedListener(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
        return NetworkChangedReceiver.getInstance().isRegistered(onNetworkStatusChangedListener);
    }

    public static void unregisterNetworkStatusChangedListener(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
        NetworkChangedReceiver.getInstance().unregisterListener(onNetworkStatusChangedListener);
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION"})
    public static WifiScanResults getWifiScanResult() {
        List<ScanResult> scanResults;
        WifiScanResults wifiScanResults = new WifiScanResults();
        if (getWifiEnabled() && (scanResults = ((WifiManager) Utils.getApp().getSystemService("wifi")).getScanResults()) != null) {
            wifiScanResults.setAllResults(scanResults);
        }
        return wifiScanResults;
    }

    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION"})
    public static void addOnWifiChangedConsumer(final Utils.Consumer<WifiScanResults> consumer) {
        if (consumer != null) {
            UtilsBridge.runOnUiThread(new Runnable() {
                public void run() {
                    if (NetworkUtils.SCAN_RESULT_CONSUMERS.isEmpty()) {
                        NetworkUtils.SCAN_RESULT_CONSUMERS.add(consumer);
                        NetworkUtils.startScanWifi();
                        return;
                    }
                    consumer.accept(NetworkUtils.sPreWifiScanResults);
                    NetworkUtils.SCAN_RESULT_CONSUMERS.add(consumer);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void startScanWifi() {
        sPreWifiScanResults = new WifiScanResults();
        sScanWifiTimer = new Timer();
        sScanWifiTimer.schedule(new TimerTask() {
            @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE", "android.permission.ACCESS_COARSE_LOCATION"})
            public void run() {
                NetworkUtils.startScanWifiIfEnabled();
                WifiScanResults wifiScanResult = NetworkUtils.getWifiScanResult();
                if (!NetworkUtils.isSameScanResults(NetworkUtils.sPreWifiScanResults.allResults, wifiScanResult.allResults)) {
                    WifiScanResults unused = NetworkUtils.sPreWifiScanResults = wifiScanResult;
                    UtilsBridge.runOnUiThread(new Runnable() {
                        public void run() {
                            for (Utils.Consumer accept : NetworkUtils.SCAN_RESULT_CONSUMERS) {
                                accept.accept(NetworkUtils.sPreWifiScanResults);
                            }
                        }
                    });
                }
            }
        }, 0, SCAN_PERIOD_MILLIS);
    }

    /* access modifiers changed from: private */
    @RequiresPermission(allOf = {"android.permission.ACCESS_WIFI_STATE", "android.permission.CHANGE_WIFI_STATE"})
    public static void startScanWifiIfEnabled() {
        if (getWifiEnabled()) {
            ((WifiManager) Utils.getApp().getSystemService("wifi")).startScan();
        }
    }

    public static void removeOnWifiChangedConsumer(final Utils.Consumer<WifiScanResults> consumer) {
        if (consumer != null) {
            UtilsBridge.runOnUiThread(new Runnable() {
                public void run() {
                    NetworkUtils.SCAN_RESULT_CONSUMERS.remove(consumer);
                    if (NetworkUtils.SCAN_RESULT_CONSUMERS.isEmpty()) {
                        NetworkUtils.stopScanWifi();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static void stopScanWifi() {
        Timer timer = sScanWifiTimer;
        if (timer != null) {
            timer.cancel();
            sScanWifiTimer = null;
        }
    }

    /* access modifiers changed from: private */
    public static boolean isSameScanResults(List<ScanResult> list, List<ScanResult> list2) {
        if (list == null && list2 == null) {
            return true;
        }
        if (list == null || list2 == null || list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!isSameScanResultContent(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isSameScanResultContent(ScanResult scanResult, ScanResult scanResult2) {
        return scanResult != null && scanResult2 != null && UtilsBridge.equals(scanResult.BSSID, scanResult2.BSSID) && UtilsBridge.equals(scanResult.SSID, scanResult2.SSID) && UtilsBridge.equals(scanResult.capabilities, scanResult2.capabilities) && scanResult.level == scanResult2.level;
    }

    public static final class NetworkChangedReceiver extends BroadcastReceiver {
        /* access modifiers changed from: private */
        public Set<OnNetworkStatusChangedListener> mListeners = new HashSet();
        /* access modifiers changed from: private */
        public NetworkType mType;

        /* access modifiers changed from: private */
        public static NetworkChangedReceiver getInstance() {
            return LazyHolder.INSTANCE;
        }

        /* access modifiers changed from: package-private */
        @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
        public void registerListener(final OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
            if (onNetworkStatusChangedListener != null) {
                UtilsBridge.runOnUiThread(new Runnable() {
                    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
                    public void run() {
                        int size = NetworkChangedReceiver.this.mListeners.size();
                        NetworkChangedReceiver.this.mListeners.add(onNetworkStatusChangedListener);
                        if (size == 0 && NetworkChangedReceiver.this.mListeners.size() == 1) {
                            NetworkType unused = NetworkChangedReceiver.this.mType = NetworkUtils.getNetworkType();
                            Utils.getApp().registerReceiver(NetworkChangedReceiver.getInstance(), new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
                        }
                    }
                });
            }
        }

        /* access modifiers changed from: package-private */
        public boolean isRegistered(OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
            if (onNetworkStatusChangedListener == null) {
                return false;
            }
            return this.mListeners.contains(onNetworkStatusChangedListener);
        }

        /* access modifiers changed from: package-private */
        public void unregisterListener(final OnNetworkStatusChangedListener onNetworkStatusChangedListener) {
            if (onNetworkStatusChangedListener != null) {
                UtilsBridge.runOnUiThread(new Runnable() {
                    public void run() {
                        int size = NetworkChangedReceiver.this.mListeners.size();
                        NetworkChangedReceiver.this.mListeners.remove(onNetworkStatusChangedListener);
                        if (size == 1 && NetworkChangedReceiver.this.mListeners.size() == 0) {
                            Utils.getApp().unregisterReceiver(NetworkChangedReceiver.getInstance());
                        }
                    }
                });
            }
        }

        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                UtilsBridge.runOnUiThreadDelayed(new Runnable() {
                    @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
                    public void run() {
                        NetworkType networkType = NetworkUtils.getNetworkType();
                        if (NetworkChangedReceiver.this.mType != networkType) {
                            NetworkType unused = NetworkChangedReceiver.this.mType = networkType;
                            if (networkType == NetworkType.NETWORK_NO) {
                                for (OnNetworkStatusChangedListener onDisconnected : NetworkChangedReceiver.this.mListeners) {
                                    onDisconnected.onDisconnected();
                                }
                                return;
                            }
                            for (OnNetworkStatusChangedListener onConnected : NetworkChangedReceiver.this.mListeners) {
                                onConnected.onConnected(networkType);
                            }
                        }
                    }
                }, 1000);
            }
        }

        private static class LazyHolder {
            /* access modifiers changed from: private */
            public static final NetworkChangedReceiver INSTANCE = new NetworkChangedReceiver();

            private LazyHolder() {
            }
        }
    }

    public static final class WifiScanResults {
        /* access modifiers changed from: private */
        public List<ScanResult> allResults = new ArrayList();
        private List<ScanResult> filterResults = new ArrayList();

        public List<ScanResult> getAllResults() {
            return this.allResults;
        }

        public List<ScanResult> getFilterResults() {
            return this.filterResults;
        }

        public void setAllResults(List<ScanResult> list) {
            this.allResults = list;
            this.filterResults = filterScanResult(list);
        }

        private static List<ScanResult> filterScanResult(List<ScanResult> list) {
            ScanResult scanResult;
            if (list == null || list.isEmpty()) {
                return new ArrayList();
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
            for (ScanResult next : list) {
                if (!TextUtils.isEmpty(next.SSID) && ((scanResult = (ScanResult) linkedHashMap.get(next.SSID)) == null || scanResult.level < next.level)) {
                    linkedHashMap.put(next.SSID, next);
                }
            }
            return new ArrayList(linkedHashMap.values());
        }
    }
}
