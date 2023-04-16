package com.baidu.mobstat;

import androidx.core.view.PointerIconCompat;
import com.baidu.mobstat.C1006ce;
import com.baidu.mobstat.C1015cj;
import com.baidu.mobstat.C1031cw;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import kotlin.jvm.internal.ByteCompanionObject;

/* renamed from: com.baidu.mobstat.ck */
public class C1018ck extends C1015cj {

    /* renamed from: f */
    static final /* synthetic */ boolean f1351f = (!C1018ck.class.desiredAssertionStatus());

    /* renamed from: g */
    private ByteBuffer f1352g;

    /* renamed from: h */
    private final Random f1353h = new Random();

    /* renamed from: com.baidu.mobstat.ck$a */
    class C1019a extends Throwable {

        /* renamed from: b */
        private int f1355b;

        public C1019a(int i) {
            this.f1355b = i;
        }

        /* renamed from: a */
        public int mo11800a() {
            return this.f1355b;
        }
    }

    /* renamed from: a */
    public C1015cj.C1017b mo11787a(C1034cy cyVar, C1043df dfVar) throws C1024cp {
        if (!cyVar.mo11825c("Sec-WebSocket-Key") || !dfVar.mo11825c("Sec-WebSocket-Accept")) {
            return C1015cj.C1017b.NOT_MATCHED;
        }
        if (m1606a(cyVar.mo11823b("Sec-WebSocket-Key")).equals(dfVar.mo11823b("Sec-WebSocket-Accept"))) {
            return C1015cj.C1017b.MATCHED;
        }
        return C1015cj.C1017b.NOT_MATCHED;
    }

    /* renamed from: a */
    public ByteBuffer mo11789a(C1031cw cwVar) {
        ByteBuffer c = cwVar.mo11806c();
        int i = 0;
        boolean z = this.f1342d == C1006ce.C1008b.CLIENT;
        int i2 = c.remaining() <= 125 ? 1 : c.remaining() <= 65535 ? 2 : 8;
        ByteBuffer allocate = ByteBuffer.allocate((i2 > 1 ? i2 + 1 : i2) + 1 + (z ? 4 : 0) + c.remaining());
        byte a = m1604a(cwVar.mo11814f());
        boolean d = cwVar.mo11812d();
        byte b = ByteCompanionObject.MIN_VALUE;
        allocate.put((byte) (((byte) (d ? -128 : 0)) | a));
        byte[] a2 = m1607a((long) c.remaining(), i2);
        if (f1351f || a2.length == i2) {
            if (i2 == 1) {
                byte b2 = a2[0];
                if (!z) {
                    b = 0;
                }
                allocate.put((byte) (b2 | b));
            } else if (i2 == 2) {
                if (!z) {
                    b = 0;
                }
                allocate.put((byte) (b | 126));
                allocate.put(a2);
            } else if (i2 == 8) {
                if (!z) {
                    b = 0;
                }
                allocate.put((byte) (b | ByteCompanionObject.MAX_VALUE));
                allocate.put(a2);
            } else {
                throw new RuntimeException("Size representation not supported/specified");
            }
            if (z) {
                ByteBuffer allocate2 = ByteBuffer.allocate(4);
                allocate2.putInt(this.f1353h.nextInt());
                allocate.put(allocate2.array());
                while (c.hasRemaining()) {
                    allocate.put((byte) (c.get() ^ allocate2.get(i % 4)));
                    i++;
                }
            } else {
                allocate.put(c);
            }
            if (f1351f || allocate.remaining() == 0) {
                allocate.flip();
                return allocate;
            }
            throw new AssertionError(allocate.remaining());
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    public List<C1031cw> mo11792a(ByteBuffer byteBuffer, boolean z) {
        C1033cx cxVar = new C1033cx();
        try {
            cxVar.mo11805a(byteBuffer);
            cxVar.mo11809a(true);
            cxVar.mo11808a(C1031cw.C1032a.BINARY);
            cxVar.mo11810b(z);
            return Collections.singletonList(cxVar);
        } catch (C1022cn e) {
            throw new C1026cr(e);
        }
    }

    /* renamed from: a */
    private byte m1604a(C1031cw.C1032a aVar) {
        if (aVar == C1031cw.C1032a.CONTINUOUS) {
            return 0;
        }
        if (aVar == C1031cw.C1032a.TEXT) {
            return 1;
        }
        if (aVar == C1031cw.C1032a.BINARY) {
            return 2;
        }
        if (aVar == C1031cw.C1032a.CLOSING) {
            return 8;
        }
        if (aVar == C1031cw.C1032a.PING) {
            return 9;
        }
        if (aVar == C1031cw.C1032a.PONG) {
            return 10;
        }
        throw new RuntimeException("Don't know how to handle " + aVar.toString());
    }

    /* renamed from: a */
    private String m1606a(String str) {
        try {
            return C1045dh.m1676a(MessageDigest.getInstance("SHA1").digest((str.trim() + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /* renamed from: a */
    public C1035cz mo11788a(C1035cz czVar) {
        czVar.mo11819a("Upgrade", "websocket");
        czVar.mo11819a("Connection", "Upgrade");
        czVar.mo11819a("Sec-WebSocket-Version", "8");
        byte[] bArr = new byte[16];
        this.f1353h.nextBytes(bArr);
        czVar.mo11819a("Sec-WebSocket-Key", C1045dh.m1676a(bArr));
        return czVar;
    }

    /* renamed from: a */
    private byte[] m1607a(long j, int i) {
        byte[] bArr = new byte[i];
        int i2 = (i * 8) - 8;
        for (int i3 = 0; i3 < i; i3++) {
            bArr[i3] = (byte) ((int) (j >>> (i2 - (i3 * 8))));
        }
        return bArr;
    }

    /* renamed from: a */
    private C1031cw.C1032a m1605a(byte b) throws C1023co {
        if (b == 0) {
            return C1031cw.C1032a.CONTINUOUS;
        }
        if (b == 1) {
            return C1031cw.C1032a.TEXT;
        }
        if (b == 2) {
            return C1031cw.C1032a.BINARY;
        }
        switch (b) {
            case 8:
                return C1031cw.C1032a.CLOSING;
            case 9:
                return C1031cw.C1032a.PING;
            case 10:
                return C1031cw.C1032a.PONG;
            default:
                throw new C1023co("unknow optcode " + ((short) b));
        }
    }

    /* renamed from: c */
    public List<C1031cw> mo11797c(ByteBuffer byteBuffer) throws C1025cq, C1022cn {
        LinkedList linkedList;
        while (true) {
            linkedList = new LinkedList();
            if (this.f1352g == null) {
                break;
            }
            try {
                byteBuffer.mark();
                int remaining = byteBuffer.remaining();
                int remaining2 = this.f1352g.remaining();
                if (remaining2 > remaining) {
                    this.f1352g.put(byteBuffer.array(), byteBuffer.position(), remaining);
                    byteBuffer.position(byteBuffer.position() + remaining);
                    return Collections.emptyList();
                }
                this.f1352g.put(byteBuffer.array(), byteBuffer.position(), remaining2);
                byteBuffer.position(byteBuffer.position() + remaining2);
                linkedList.add(mo11799e((ByteBuffer) this.f1352g.duplicate().position(0)));
                this.f1352g = null;
            } catch (C1019a e) {
                this.f1352g.limit();
                ByteBuffer allocate = ByteBuffer.allocate(mo11786a(e.mo11800a()));
                if (f1351f || allocate.limit() > this.f1352g.limit()) {
                    this.f1352g.rewind();
                    allocate.put(this.f1352g);
                    this.f1352g = allocate;
                } else {
                    throw new AssertionError();
                }
            }
        }
        while (byteBuffer.hasRemaining()) {
            byteBuffer.mark();
            try {
                linkedList.add(mo11799e(byteBuffer));
            } catch (C1019a e2) {
                byteBuffer.reset();
                this.f1352g = ByteBuffer.allocate(mo11786a(e2.mo11800a()));
                this.f1352g.put(byteBuffer);
            }
        }
        return linkedList;
    }

    /* renamed from: e */
    public C1031cw mo11799e(ByteBuffer byteBuffer) throws C1019a, C1022cn {
        C1030cv cvVar;
        int remaining = byteBuffer.remaining();
        int i = 2;
        if (remaining >= 2) {
            byte b = byteBuffer.get();
            boolean z = (b >> 8) != 0;
            byte b2 = (byte) ((b & ByteCompanionObject.MAX_VALUE) >> 4);
            if (b2 == 0) {
                byte b3 = byteBuffer.get();
                boolean z2 = (b3 & ByteCompanionObject.MIN_VALUE) != 0;
                int i2 = (byte) (b3 & ByteCompanionObject.MAX_VALUE);
                C1031cw.C1032a a = m1605a((byte) (b & 15));
                if (z || !(a == C1031cw.C1032a.PING || a == C1031cw.C1032a.PONG || a == C1031cw.C1032a.CLOSING)) {
                    if (i2 < 0 || i2 > 125) {
                        if (a == C1031cw.C1032a.PING || a == C1031cw.C1032a.PONG || a == C1031cw.C1032a.CLOSING) {
                            throw new C1023co("more than 125 octets");
                        } else if (i2 != 126) {
                            i = 10;
                            if (remaining >= 10) {
                                byte[] bArr = new byte[8];
                                for (int i3 = 0; i3 < 8; i3++) {
                                    bArr[i3] = byteBuffer.get();
                                }
                                long longValue = new BigInteger(bArr).longValue();
                                if (longValue <= 2147483647L) {
                                    i2 = (int) longValue;
                                } else {
                                    throw new C1025cq("Payloadsize is to big...");
                                }
                            } else {
                                throw new C1019a(10);
                            }
                        } else if (remaining >= 4) {
                            byte[] bArr2 = new byte[3];
                            bArr2[1] = byteBuffer.get();
                            bArr2[2] = byteBuffer.get();
                            i2 = new BigInteger(bArr2).intValue();
                            i = 4;
                        } else {
                            throw new C1019a(4);
                        }
                    }
                    int i4 = i + (z2 ? 4 : 0) + i2;
                    if (remaining >= i4) {
                        ByteBuffer allocate = ByteBuffer.allocate(mo11786a(i2));
                        if (z2) {
                            byte[] bArr3 = new byte[4];
                            byteBuffer.get(bArr3);
                            for (int i5 = 0; i5 < i2; i5++) {
                                allocate.put((byte) (byteBuffer.get() ^ bArr3[i5 % 4]));
                            }
                        } else {
                            allocate.put(byteBuffer.array(), byteBuffer.position(), allocate.limit());
                            byteBuffer.position(byteBuffer.position() + allocate.limit());
                        }
                        if (a == C1031cw.C1032a.CLOSING) {
                            cvVar = new C1029cu();
                        } else {
                            cvVar = new C1033cx();
                            cvVar.mo11809a(z);
                            cvVar.mo11808a(a);
                        }
                        allocate.flip();
                        cvVar.mo11805a(allocate);
                        if (a != C1031cw.C1032a.TEXT || C1047di.m1691b(cvVar.mo11806c())) {
                            return cvVar;
                        }
                        throw new C1022cn(PointerIconCompat.TYPE_CROSSHAIR);
                    }
                    throw new C1019a(i4);
                }
                throw new C1023co("control frames may no be fragmented");
            }
            throw new C1023co("bad rsv " + b2);
        }
        throw new C1019a(2);
    }

    /* renamed from: a */
    public void mo11793a() {
        this.f1352g = null;
    }

    /* renamed from: c */
    public C1015cj mo11796c() {
        return new C1018ck();
    }

    /* renamed from: b */
    public C1015cj.C1016a mo11795b() {
        return C1015cj.C1016a.TWOWAY;
    }
}
