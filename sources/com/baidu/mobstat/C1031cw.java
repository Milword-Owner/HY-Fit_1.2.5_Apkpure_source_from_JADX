package com.baidu.mobstat;

import java.nio.ByteBuffer;

/* renamed from: com.baidu.mobstat.cw */
public interface C1031cw {

    /* renamed from: com.baidu.mobstat.cw$a */
    public enum C1032a {
        CONTINUOUS,
        TEXT,
        BINARY,
        PING,
        PONG,
        CLOSING
    }

    /* renamed from: a */
    void mo11811a(C1031cw cwVar) throws C1023co;

    /* renamed from: c */
    ByteBuffer mo11806c();

    /* renamed from: d */
    boolean mo11812d();

    /* renamed from: e */
    boolean mo11813e();

    /* renamed from: f */
    C1032a mo11814f();
}
