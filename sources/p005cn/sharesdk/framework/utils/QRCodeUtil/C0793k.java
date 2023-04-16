package p005cn.sharesdk.framework.utils.QRCodeUtil;

import com.baidubce.BceConfig;
import com.mob.MobSDK;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import p005cn.sharesdk.framework.p007b.p008a.C0713e;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.k */
/* compiled from: QRCodeProtocols */
public class C0793k {

    /* renamed from: a */
    private String f572a = ((this.f575d.getPackageName() + BceConfig.BOS_DELIMITER + this.f575d.getAppVersionName()) + " " + "ShareSDK/3.8.5" + " " + ("Android/" + this.f575d.getOSVersionInt()));

    /* renamed from: b */
    private C0713e f573b = C0713e.m196a();

    /* renamed from: c */
    private Hashon f574c = new Hashon();

    /* renamed from: d */
    private DeviceHelper f575d = DeviceHelper.getInstance(MobSDK.getContext());

    /* renamed from: e */
    private NetworkHelper f576e = new NetworkHelper();
}
