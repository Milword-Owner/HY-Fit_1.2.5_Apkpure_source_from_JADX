package com.mob.tools.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.baidu.mobstat.Config;
import com.hjq.permissions.Permission;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;

@SuppressLint({"MissingPermission"})
public class LHelper {
    private static final int CACHE_LIFE_CYCLE = 5000;
    private static final int CANCEL_GPS_LOCATING = 1;
    private static final int CANCEL_NETWORK_LOCATING = 2;
    private static final int START_LOCATING = 0;
    private static LHelper instance;
    /* access modifiers changed from: private */
    public Location cache;
    private int gpsTimeoutSec;
    private Handler handler = MobHandlerThread.newHandler("T-lct", (Handler.Callback) new Handler.Callback() {
        public boolean handleMessage(Message message) {
            try {
                int i = message.what;
                if (i == 0) {
                    LHelper.this.onRequest();
                    return false;
                } else if (i == 1) {
                    LHelper.this.onGPSTimeout();
                    return false;
                } else if (i != 2) {
                    return false;
                } else {
                    LHelper.this.onNetworkTimeout();
                    return false;
                }
            } catch (Throwable th) {
                MobLog.getInstance().mo29769d(th);
                LHelper.this.finished();
                return false;
            }
        }
    });
    private LocationListener listener = new LocationListener() {
        public void onProviderDisabled(String str) {
        }

        public void onProviderEnabled(String str) {
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        public void onLocationChanged(Location location) {
            try {
                LHelper.this.f2350lm.removeUpdates(this);
                Location unused = LHelper.this.cache = location;
                long unused2 = LHelper.this.requestAt = System.currentTimeMillis();
            } catch (Throwable th) {
                LHelper.this.finished();
                throw th;
            }
            LHelper.this.finished();
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: lm */
    public LocationManager f2350lm;
    private int networkTimeoutSec;
    /* access modifiers changed from: private */
    public long requestAt;

    private LHelper() {
    }

    public static LHelper getInstance() {
        if (instance == null) {
            synchronized (LHelper.class) {
                if (instance == null) {
                    instance = new LHelper();
                }
            }
        }
        return instance;
    }

    public Location getLocation(Context context) {
        return getLocation(context, 0);
    }

    public Location getLocation(Context context, int i) {
        return getLocation(context, i, 0);
    }

    public Location getLocation(Context context, int i, int i2) {
        return getLocation(context, i, i2, true);
    }

    public Location getLocation(Context context, int i, int i2, boolean z) {
        return getLocation(context, i, i2, z, false);
    }

    public Location getLocation(Context context, int i, int i2, boolean z, boolean z2) {
        Location quickResponse = quickResponse(z2);
        if (quickResponse == null) {
            synchronized (LHelper.class) {
                Location quickResponse2 = quickResponse(z2);
                quickResponse = quickResponse2 == null ? requestLocation(context, i, i2, z) : quickResponse2;
            }
        }
        return quickResponse;
    }

    private Location requestLocation(Context context, int i, int i2, boolean z) {
        try {
            DeviceHelper instance2 = DeviceHelper.getInstance(context);
            if (!instance2.checkPermission(Permission.ACCESS_FINE_LOCATION)) {
                return null;
            }
            this.gpsTimeoutSec = i;
            this.networkTimeoutSec = i2;
            if (this.f2350lm == null) {
                this.f2350lm = (LocationManager) instance2.getSystemServiceSafe("location");
            }
            if (this.f2350lm == null) {
                return null;
            }
            synchronized (this) {
                this.handler.sendEmptyMessageDelayed(0, 50);
                wait();
            }
            if (this.cache == null && z) {
                this.cache = onRequestLastKnown();
                this.requestAt = System.currentTimeMillis();
            }
            return this.cache;
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
            return null;
        }
    }

    private Location quickResponse(boolean z) {
        if (z || this.cache == null || System.currentTimeMillis() - this.requestAt > Config.BPLUS_DELAY_TIME) {
            return null;
        }
        return this.cache;
    }

    /* access modifiers changed from: private */
    public void onRequest() {
        boolean z = true;
        boolean z2 = this.gpsTimeoutSec != 0;
        if (this.networkTimeoutSec == 0) {
            z = false;
        }
        LocationManager locationManager = this.f2350lm;
        if (locationManager == null) {
            finished();
        } else if (z2 && locationManager.isProviderEnabled("gps")) {
            onRequestGps();
        } else if (!z || !this.f2350lm.isProviderEnabled("network")) {
            finished();
        } else {
            onRequestNetwork();
        }
    }

    private void onRequestGps() {
        try {
            ReflectHelper.invokeInstanceMethod(this.f2350lm, Strings.getString(124), new Object[]{Strings.getString(122), 1000, 0, this.listener}, new Class[]{String.class, Long.TYPE, Float.TYPE, LocationListener.class});
            if (this.gpsTimeoutSec > 0) {
                this.handler.sendEmptyMessageDelayed(1, (long) (this.gpsTimeoutSec * 1000));
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            this.handler.sendEmptyMessage(1);
        }
    }

    private void onRequestNetwork() {
        try {
            ReflectHelper.invokeInstanceMethod(this.f2350lm, Strings.getString(124), new Object[]{Strings.getString(123), 1000, 0, this.listener}, new Class[]{String.class, Long.TYPE, Float.TYPE, LocationListener.class});
            if (this.networkTimeoutSec > 0) {
                this.handler.sendEmptyMessageDelayed(2, (long) (this.networkTimeoutSec * 1000));
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            this.handler.sendEmptyMessage(2);
        }
    }

    private Location onRequestLastKnown() {
        Location location;
        Throwable th;
        try {
            location = (Location) ReflectHelper.invokeInstanceMethod(this.f2350lm, Strings.getString(121), Strings.getString(122));
            if (location == null) {
                try {
                    return (Location) ReflectHelper.invokeInstanceMethod(this.f2350lm, Strings.getString(121), Strings.getString(123));
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            location = null;
            th = th4;
            MobLog.getInstance().mo29787w(th);
            return location;
        }
        return location;
    }

    /* access modifiers changed from: private */
    public void onGPSTimeout() {
        LocationManager locationManager = this.f2350lm;
        if (locationManager != null) {
            locationManager.removeUpdates(this.listener);
            if (!(this.networkTimeoutSec != 0) || !this.f2350lm.isProviderEnabled("network")) {
                finished();
            } else {
                onRequestNetwork();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onNetworkTimeout() {
        LocationManager locationManager = this.f2350lm;
        if (locationManager != null) {
            locationManager.removeUpdates(this.listener);
        }
        finished();
    }

    /* access modifiers changed from: private */
    public void finished() {
        try {
            synchronized (this) {
                notifyAll();
            }
        } catch (Throwable th) {
            MobLog.getInstance().mo29769d(th);
        }
    }
}
