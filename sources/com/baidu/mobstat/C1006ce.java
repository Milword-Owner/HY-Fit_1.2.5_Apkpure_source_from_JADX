package com.baidu.mobstat;

import java.net.InetSocketAddress;

/* renamed from: com.baidu.mobstat.ce */
public interface C1006ce {

    /* renamed from: com.baidu.mobstat.ce$a */
    public enum C1007a {
        NOT_YET_CONNECTED,
        CONNECTING,
        OPEN,
        CLOSING,
        CLOSED
    }

    /* renamed from: com.baidu.mobstat.ce$b */
    public enum C1008b {
        CLIENT
    }

    /* renamed from: a */
    InetSocketAddress mo11736a();

    /* renamed from: a */
    void mo11737a(C1031cw cwVar);
}
