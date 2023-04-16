package p005cn.sharesdk.framework.loopshare;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.loopshare.MobLink;
import cn.sharesdk.loopshare.Scene;
import cn.sharesdk.loopshare.SceneRestorable;
import com.baidu.mobstat.Config;
import com.mob.MobSDK;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Hashon;
import java.util.HashMap;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.loopshare.RestoreTempActivity */
public class RestoreTempActivity extends Activity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        SSDKLog.m645b().mo29786w("[LoopShare] RestoreTempActivity onCreate");
        super.onCreate(bundle);
        try {
            MobLink.setActivityDelegate(this, new SceneRestorable() {
                public void onReturnSceneData(Scene scene) {
                    if (scene != null) {
                        String path = scene.getPath();
                        HashMap params = scene.getParams();
                        String str = (String) params.get("targetAction_And");
                        params.remove("targetAction_And");
                        params.remove("targetAction_iOS");
                        try {
                            params.put(Config.FEED_LIST_ITEM_PATH, path);
                            String fromHashMap = new Hashon().fromHashMap(params);
                            if (!TextUtils.isEmpty(fromHashMap)) {
                                new C0761a(MobSDK.getContext(), "sharesdk_moblink_sp").mo10767a("share_restore_extra", fromHashMap);
                                NLog b = SSDKLog.m645b();
                                b.mo29768d("LoopShare RestoreTempActivity save json is okd " + fromHashMap, new Object[0]);
                            }
                            if (MobLinkAPI.m458b() != null) {
                                MobLinkAPI.m458b().onResult(params);
                                SSDKLog.m645b().mo29768d("LoopShare RestoreTempActivity onResult is OK", new Object[0]);
                            }
                            RestoreTempActivity.this.finish();
                        } catch (Throwable th) {
                            NLog b2 = SSDKLog.m645b();
                            b2.mo29768d("LoopShare RestoreTempActivity onReturnSceneData catch " + th, new Object[0]);
                            if (MobLinkAPI.m458b() != null) {
                                MobLinkAPI.m458b().onError(th);
                            }
                            RestoreTempActivity.this.finish();
                        }
                    }
                }
            });
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29768d("LoopShare RestoreTempActivity onCreate catch " + th, new Object[0]);
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        SSDKLog.m645b().mo29786w("[LoopShare] RestoreTempActivity onResume");
        super.onResume();
        finish();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        SSDKLog.m645b().mo29786w("[LoopShare] RestoreTempActivity onNewIntent");
        super.onNewIntent(intent);
        try {
            MobLink.updateNewIntent(intent, this);
            SSDKLog.m645b().mo29768d("LoopShare RestoreTempActivity onNewIntent ", new Object[0]);
        } catch (Throwable th) {
            NLog b = SSDKLog.m645b();
            b.mo29768d("LoopShare RestoreTempActivity onNewIntent catch " + th, new Object[0]);
        }
    }
}
