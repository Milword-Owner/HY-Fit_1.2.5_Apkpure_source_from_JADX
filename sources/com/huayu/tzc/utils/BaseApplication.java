package com.huayu.tzc.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.multidex.MultiDex;
import com.baidu.mobstat.StatService;
import com.chad.library.adapter.base.module.LoadMoreModuleConfig;
import com.clj.fastble.BleManager;
import com.clj.fastble.scan.BleScanRuleConfig;
import com.hjq.language.MultiLanguages;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.customview.CustomLoadMoreView;
import com.mob.MobSDK;
import com.tencent.mmkv.MMKV;
import org.litepal.LitePal;

public class BaseApplication extends Application {
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    public void onCreate() {
        StatService.setDebugOn(false);
        StatService.setAppKey("484af5ac10");
        StatService.setStartType(true);
        StatService.autoTrace(this);
        super.onCreate();
        instance = this;
        MMKV.initialize((Context) this);
        MultiDex.install(this);
        MobSDK.init(this);
        LitePal.initialize(this);
        ToastUtils.init(this);
        BleManager.getInstance().init(this);
        BleManager.getInstance().enableLog(true).setMaxConnectCount(1000).setOperateTimeout(150000);
        BleManager.getInstance().initScanRule(new BleScanRuleConfig.Builder().setScanTimeOut(0).build());
        StatService.setAuthorizedState(this, true);
        Resources resources = super.getResources();
        Configuration configuration = new Configuration();
        configuration.setToDefaults();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        MultiLanguages.init(this);
        LoadMoreModuleConfig.setDefLoadMoreView(new CustomLoadMoreView());
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(MultiLanguages.attach(context));
    }
}
