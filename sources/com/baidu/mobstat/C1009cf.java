package com.baidu.mobstat;

import com.baidu.mobstat.C1031cw;
import java.net.InetSocketAddress;

/* renamed from: com.baidu.mobstat.cf */
public abstract class C1009cf implements C1011ch {
    /* renamed from: a */
    public void mo11739a(C1006ce ceVar, C1031cw cwVar) {
    }

    /* renamed from: a */
    public void mo11740a(C1006ce ceVar, C1034cy cyVar) throws C1022cn {
    }

    /* renamed from: a */
    public void mo11741a(C1006ce ceVar, C1034cy cyVar, C1043df dfVar) throws C1022cn {
    }

    /* renamed from: c */
    public void mo11743c(C1006ce ceVar, C1031cw cwVar) {
    }

    /* renamed from: b */
    public void mo11742b(C1006ce ceVar, C1031cw cwVar) {
        C1033cx cxVar = new C1033cx(cwVar);
        cxVar.mo11808a(C1031cw.C1032a.PONG);
        ceVar.mo11737a(cxVar);
    }

    /* renamed from: a */
    public String mo11738a(C1006ce ceVar) throws C1022cn {
        InetSocketAddress a = ceVar.mo11736a();
        if (a != null) {
            return "<cross-domain-policy><allow-access-from domain=\"*\" to-ports=\"" + a.getPort() + "\" /></cross-domain-policy>\u0000";
        }
        throw new C1024cp("socket not bound");
    }
}
