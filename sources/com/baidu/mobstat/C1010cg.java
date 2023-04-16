package com.baidu.mobstat;

import androidx.core.view.PointerIconCompat;
import com.baidu.mobstat.C1006ce;
import com.baidu.mobstat.C1015cj;
import com.baidu.mobstat.C1031cw;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* renamed from: com.baidu.mobstat.cg */
public class C1010cg implements C1006ce {

    /* renamed from: a */
    public static final List<C1015cj> f1305a = new ArrayList(4);

    /* renamed from: b */
    public static int f1306b = 16384;

    /* renamed from: c */
    public static boolean f1307c = false;

    /* renamed from: h */
    static final /* synthetic */ boolean f1308h = (!C1010cg.class.desiredAssertionStatus());

    /* renamed from: d */
    public final BlockingQueue<ByteBuffer> f1309d;

    /* renamed from: e */
    public final BlockingQueue<ByteBuffer> f1310e;

    /* renamed from: f */
    public SelectionKey f1311f;

    /* renamed from: g */
    public ByteChannel f1312g;

    /* renamed from: i */
    private final C1011ch f1313i;

    /* renamed from: j */
    private volatile boolean f1314j = false;

    /* renamed from: k */
    private C1006ce.C1007a f1315k = C1006ce.C1007a.NOT_YET_CONNECTED;

    /* renamed from: l */
    private C1015cj f1316l = null;

    /* renamed from: m */
    private C1006ce.C1008b f1317m;

    /* renamed from: n */
    private C1031cw f1318n = null;

    /* renamed from: o */
    private ByteBuffer f1319o = ByteBuffer.allocate(0);

    /* renamed from: p */
    private C1034cy f1320p = null;

    /* renamed from: q */
    private String f1321q = null;

    /* renamed from: r */
    private Integer f1322r = null;

    /* renamed from: s */
    private Boolean f1323s = null;

    /* renamed from: t */
    private String f1324t = null;

    static {
        f1305a.add(new C1020cl());
        f1305a.add(new C1018ck());
    }

    public C1010cg(C1011ch chVar, C1015cj cjVar) {
        if (chVar == null || cjVar == null) {
            throw new IllegalArgumentException("parameters must not be null");
        }
        this.f1309d = new LinkedBlockingQueue();
        this.f1310e = new LinkedBlockingQueue();
        this.f1313i = chVar;
        this.f1317m = C1006ce.C1008b.CLIENT;
        if (cjVar != null) {
            this.f1316l = cjVar.mo11796c();
        }
    }

    /* renamed from: a */
    public void mo11750a(ByteBuffer byteBuffer) {
        if (f1308h || byteBuffer.hasRemaining()) {
            if (f1307c) {
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder();
                sb.append("process(");
                sb.append(byteBuffer.remaining());
                sb.append("): {");
                sb.append(byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.remaining()));
                sb.append("}");
                printStream.println(sb.toString());
            }
            if (this.f1315k != C1006ce.C1007a.NOT_YET_CONNECTED) {
                m1519d(byteBuffer);
            } else if (m1518c(byteBuffer)) {
                if (!f1308h && this.f1319o.hasRemaining() == byteBuffer.hasRemaining() && byteBuffer.hasRemaining()) {
                    throw new AssertionError();
                } else if (byteBuffer.hasRemaining()) {
                    m1519d(byteBuffer);
                } else if (this.f1319o.hasRemaining()) {
                    m1519d(this.f1319o);
                }
            }
            if (!f1308h && !mo11757d() && !mo11758e() && byteBuffer.hasRemaining()) {
                throw new AssertionError();
            }
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: c */
    private boolean m1518c(ByteBuffer byteBuffer) {
        ByteBuffer byteBuffer2;
        if (this.f1319o.capacity() == 0) {
            byteBuffer2 = byteBuffer;
        } else {
            if (this.f1319o.remaining() < byteBuffer.remaining()) {
                ByteBuffer allocate = ByteBuffer.allocate(this.f1319o.capacity() + byteBuffer.remaining());
                this.f1319o.flip();
                allocate.put(this.f1319o);
                this.f1319o = allocate;
            }
            this.f1319o.put(byteBuffer);
            this.f1319o.flip();
            byteBuffer2 = this.f1319o;
        }
        byteBuffer2.mark();
        try {
            if (this.f1316l == null && m1520e(byteBuffer2) == C1015cj.C1017b.MATCHED) {
                try {
                    m1521f(ByteBuffer.wrap(C1047di.m1690a(this.f1313i.mo11738a(this))));
                    mo11745a(-3, "");
                } catch (C1022cn unused) {
                    m1517c(PointerIconCompat.TYPE_CELL, "remote peer closed connection before flashpolicy could be transmitted", true);
                }
                return false;
            }
            try {
                if (this.f1317m == C1006ce.C1008b.CLIENT) {
                    this.f1316l.mo11794a(this.f1317m);
                    C1041dd d = this.f1316l.mo11798d(byteBuffer2);
                    if (!(d instanceof C1043df)) {
                        mo11754b(1002, "wrong http function", false);
                        return false;
                    }
                    C1043df dfVar = (C1043df) d;
                    if (this.f1316l.mo11787a(this.f1320p, dfVar) == C1015cj.C1017b.MATCHED) {
                        try {
                            this.f1313i.mo11741a((C1006ce) this, this.f1320p, dfVar);
                            m1514a((C1041dd) dfVar);
                            return true;
                        } catch (C1022cn e) {
                            mo11754b(e.mo11802a(), e.getMessage(), false);
                            return false;
                        } catch (RuntimeException e2) {
                            this.f1313i.mo11766a((C1006ce) this, (Exception) e2);
                            mo11754b(-1, e2.getMessage(), false);
                            return false;
                        }
                    } else {
                        mo11745a(1002, "draft " + this.f1316l + " refuses handshake");
                    }
                }
            } catch (C1024cp e3) {
                mo11748a((C1022cn) e3);
            }
            return false;
        } catch (C1021cm e4) {
            if (this.f1319o.capacity() == 0) {
                byteBuffer2.reset();
                int a = e4.mo11801a();
                if (a == 0) {
                    a = byteBuffer2.capacity() + 16;
                } else if (!f1308h && e4.mo11801a() < byteBuffer2.remaining()) {
                    throw new AssertionError();
                }
                this.f1319o = ByteBuffer.allocate(a);
                this.f1319o.put(byteBuffer);
            } else {
                ByteBuffer byteBuffer3 = this.f1319o;
                byteBuffer3.position(byteBuffer3.limit());
                ByteBuffer byteBuffer4 = this.f1319o;
                byteBuffer4.limit(byteBuffer4.capacity());
            }
        }
    }

    /* renamed from: d */
    private void m1519d(ByteBuffer byteBuffer) {
        try {
            for (C1031cw next : this.f1316l.mo11797c(byteBuffer)) {
                if (f1307c) {
                    PrintStream printStream = System.out;
                    printStream.println("matched frame: " + next);
                }
                C1031cw.C1032a f = next.mo11814f();
                boolean d = next.mo11812d();
                if (this.f1315k != C1006ce.C1007a.CLOSING) {
                    if (f == C1031cw.C1032a.CLOSING) {
                        int i = 1005;
                        String str = "";
                        if (next instanceof C1028ct) {
                            C1028ct ctVar = (C1028ct) next;
                            i = ctVar.mo11803a();
                            str = ctVar.mo11804b();
                        }
                        if (this.f1315k == C1006ce.C1007a.CLOSING) {
                            mo11746a(i, str, true);
                        } else if (this.f1316l.mo11795b() == C1015cj.C1016a.TWOWAY) {
                            m1517c(i, str, true);
                        } else {
                            mo11754b(i, str, false);
                        }
                    } else if (f == C1031cw.C1032a.PING) {
                        this.f1313i.mo11742b(this, next);
                    } else if (f == C1031cw.C1032a.PONG) {
                        this.f1313i.mo11743c(this, next);
                    } else {
                        if (d) {
                            if (f != C1031cw.C1032a.CONTINUOUS) {
                                if (this.f1318n != null) {
                                    throw new C1022cn(1002, "Continuous frame sequence not completed.");
                                } else if (f == C1031cw.C1032a.TEXT) {
                                    try {
                                        this.f1313i.mo11767a((C1006ce) this, C1047di.m1687a(next.mo11806c()));
                                    } catch (RuntimeException e) {
                                        this.f1313i.mo11766a((C1006ce) this, (Exception) e);
                                    }
                                } else if (f == C1031cw.C1032a.BINARY) {
                                    try {
                                        this.f1313i.mo11768a((C1006ce) this, next.mo11806c());
                                    } catch (RuntimeException e2) {
                                        this.f1313i.mo11766a((C1006ce) this, (Exception) e2);
                                    }
                                } else {
                                    throw new C1022cn(1002, "non control or continious frame expected");
                                }
                            }
                        }
                        if (f != C1031cw.C1032a.CONTINUOUS) {
                            if (this.f1318n == null) {
                                this.f1318n = next;
                            } else {
                                throw new C1022cn(1002, "Previous continuous frame sequence not completed.");
                            }
                        } else if (d) {
                            if (this.f1318n != null) {
                                if (this.f1318n.mo11814f() == C1031cw.C1032a.TEXT) {
                                    int max = Math.max(this.f1318n.mo11806c().limit() - 64, 0);
                                    this.f1318n.mo11811a(next);
                                    if (!C1047di.m1689a(this.f1318n.mo11806c(), max)) {
                                        throw new C1022cn(PointerIconCompat.TYPE_CROSSHAIR);
                                    }
                                }
                                this.f1318n = null;
                            } else {
                                throw new C1022cn(1002, "Continuous frame sequence was not started.");
                            }
                        } else if (this.f1318n == null) {
                            throw new C1022cn(1002, "Continuous frame sequence was not started.");
                        }
                        if (f == C1031cw.C1032a.TEXT) {
                            if (!C1047di.m1691b(next.mo11806c())) {
                                throw new C1022cn(PointerIconCompat.TYPE_CROSSHAIR);
                            }
                        }
                        if (f == C1031cw.C1032a.CONTINUOUS && this.f1318n != null && this.f1318n.mo11814f() == C1031cw.C1032a.TEXT) {
                            int max2 = Math.max(this.f1318n.mo11806c().limit() - 64, 0);
                            this.f1318n.mo11811a(next);
                            if (!C1047di.m1689a(this.f1318n.mo11806c(), max2)) {
                                throw new C1022cn(PointerIconCompat.TYPE_CROSSHAIR);
                            }
                        }
                        try {
                            this.f1313i.mo11739a((C1006ce) this, next);
                        } catch (RuntimeException e3) {
                            this.f1313i.mo11766a((C1006ce) this, (Exception) e3);
                        }
                    }
                } else {
                    return;
                }
            }
        } catch (C1022cn e4) {
            this.f1313i.mo11766a((C1006ce) this, (Exception) e4);
            mo11748a(e4);
        }
    }

    /* renamed from: c */
    private void m1517c(int i, String str, boolean z) {
        if (this.f1315k != C1006ce.C1007a.CLOSING && this.f1315k != C1006ce.C1007a.CLOSED) {
            if (this.f1315k == C1006ce.C1007a.OPEN) {
                if (i != 1006) {
                    if (this.f1316l.mo11795b() != C1015cj.C1016a.NONE) {
                        if (!z) {
                            try {
                                this.f1313i.mo11763a((C1006ce) this, i, str);
                            } catch (RuntimeException e) {
                                try {
                                    this.f1313i.mo11766a((C1006ce) this, (Exception) e);
                                } catch (C1022cn e2) {
                                    this.f1313i.mo11766a((C1006ce) this, (Exception) e2);
                                    mo11754b(PointerIconCompat.TYPE_CELL, "generated frame is invalid", false);
                                }
                            }
                        }
                        mo11737a((C1031cw) new C1029cu(i, str));
                    }
                    mo11754b(i, str, z);
                } else if (f1308h || !z) {
                    this.f1315k = C1006ce.C1007a.CLOSING;
                    mo11754b(i, str, false);
                    return;
                } else {
                    throw new AssertionError();
                }
            } else if (i != -3) {
                mo11754b(-1, str, false);
            } else if (f1308h || z) {
                mo11754b(-3, str, true);
            } else {
                throw new AssertionError();
            }
            if (i == 1002) {
                mo11754b(i, str, z);
            }
            this.f1315k = C1006ce.C1007a.CLOSING;
            this.f1319o = null;
        }
    }

    /* renamed from: a */
    public void mo11745a(int i, String str) {
        m1517c(i, str, false);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void mo11746a(int i, String str, boolean z) {
        if (this.f1315k != C1006ce.C1007a.CLOSED) {
            if (this.f1311f != null) {
                this.f1311f.cancel();
            }
            if (this.f1312g != null) {
                try {
                    this.f1312g.close();
                } catch (IOException e) {
                    this.f1313i.mo11766a((C1006ce) this, (Exception) e);
                }
            }
            try {
                this.f1313i.mo11764a(this, i, str, z);
            } catch (RuntimeException e2) {
                this.f1313i.mo11766a((C1006ce) this, (Exception) e2);
            }
            if (this.f1316l != null) {
                this.f1316l.mo11793a();
            }
            this.f1320p = null;
            this.f1315k = C1006ce.C1007a.CLOSED;
            this.f1309d.clear();
            return;
        }
        return;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo11747a(int i, boolean z) {
        mo11746a(i, "", z);
    }

    /* renamed from: b */
    public void mo11753b(int i, String str) {
        mo11746a(i, str, false);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public synchronized void mo11754b(int i, String str, boolean z) {
        if (!this.f1314j) {
            this.f1322r = Integer.valueOf(i);
            this.f1321q = str;
            this.f1323s = Boolean.valueOf(z);
            this.f1314j = true;
            this.f1313i.mo11769b(this);
            try {
                this.f1313i.mo11770b(this, i, str, z);
            } catch (RuntimeException e) {
                this.f1313i.mo11766a((C1006ce) this, (Exception) e);
            }
            if (this.f1316l != null) {
                this.f1316l.mo11793a();
            }
            this.f1320p = null;
        }
    }

    /* renamed from: b */
    public void mo11752b() {
        if (mo11760g() == C1006ce.C1007a.NOT_YET_CONNECTED) {
            mo11747a(-1, true);
        } else if (this.f1314j) {
            mo11746a(this.f1322r.intValue(), this.f1321q, this.f1323s.booleanValue());
        } else if (this.f1316l.mo11795b() == C1015cj.C1016a.NONE) {
            mo11747a(1000, true);
        } else if (this.f1316l.mo11795b() == C1015cj.C1016a.ONEWAY) {
            mo11747a(1000, true);
        } else {
            mo11747a((int) PointerIconCompat.TYPE_CELL, true);
        }
    }

    /* renamed from: a */
    public void mo11744a(int i) {
        m1517c(i, "", false);
    }

    /* renamed from: a */
    public void mo11748a(C1022cn cnVar) {
        m1517c(cnVar.mo11802a(), cnVar.getMessage(), false);
    }

    /* renamed from: b */
    public void mo11755b(ByteBuffer byteBuffer) throws IllegalArgumentException, C1027cs {
        if (byteBuffer != null) {
            m1515a((Collection<C1031cw>) this.f1316l.mo11792a(byteBuffer, this.f1317m == C1006ce.C1008b.CLIENT));
            return;
        }
        throw new IllegalArgumentException("Cannot send 'null' data to a WebSocketImpl.");
    }

    /* renamed from: a */
    public void mo11751a(byte[] bArr) throws IllegalArgumentException, C1027cs {
        mo11755b(ByteBuffer.wrap(bArr));
    }

    /* renamed from: a */
    private void m1515a(Collection<C1031cw> collection) {
        if (mo11756c()) {
            for (C1031cw a : collection) {
                mo11737a(a);
            }
            return;
        }
        throw new C1027cs();
    }

    /* renamed from: a */
    public void mo11737a(C1031cw cwVar) {
        if (f1307c) {
            PrintStream printStream = System.out;
            printStream.println("send frame: " + cwVar);
        }
        m1521f(this.f1316l.mo11789a(cwVar));
    }

    /* renamed from: e */
    private C1015cj.C1017b m1520e(ByteBuffer byteBuffer) throws C1021cm {
        byteBuffer.mark();
        if (byteBuffer.limit() > C1015cj.f1341c.length) {
            return C1015cj.C1017b.NOT_MATCHED;
        }
        if (byteBuffer.limit() >= C1015cj.f1341c.length) {
            int i = 0;
            while (byteBuffer.hasRemaining()) {
                if (C1015cj.f1341c[i] != byteBuffer.get()) {
                    byteBuffer.reset();
                    return C1015cj.C1017b.NOT_MATCHED;
                }
                i++;
            }
            return C1015cj.C1017b.MATCHED;
        }
        throw new C1021cm(C1015cj.f1341c.length);
    }

    /* renamed from: a */
    public void mo11749a(C1035cz czVar) throws C1024cp {
        if (f1308h || this.f1315k != C1006ce.C1007a.CONNECTING) {
            this.f1320p = this.f1316l.mo11788a(czVar);
            this.f1324t = czVar.mo11815a();
            if (f1308h || this.f1324t != null) {
                try {
                    this.f1313i.mo11740a((C1006ce) this, this.f1320p);
                    m1516a(this.f1316l.mo11790a((C1041dd) this.f1320p, this.f1317m));
                } catch (C1022cn unused) {
                    throw new C1024cp("Handshake data rejected by client.");
                } catch (RuntimeException e) {
                    this.f1313i.mo11766a((C1006ce) this, (Exception) e);
                    throw new C1024cp("rejected because of" + e);
                }
            } else {
                throw new AssertionError();
            }
        } else {
            throw new AssertionError("shall only be called once");
        }
    }

    /* renamed from: f */
    private void m1521f(ByteBuffer byteBuffer) {
        if (f1307c) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("write(");
            sb.append(byteBuffer.remaining());
            sb.append("): {");
            sb.append(byteBuffer.remaining() > 1000 ? "too big to display" : new String(byteBuffer.array()));
            sb.append("}");
            printStream.println(sb.toString());
        }
        this.f1309d.add(byteBuffer);
        this.f1313i.mo11769b(this);
    }

    /* renamed from: a */
    private void m1516a(List<ByteBuffer> list) {
        for (ByteBuffer f : list) {
            m1521f(f);
        }
    }

    /* renamed from: a */
    private void m1514a(C1041dd ddVar) {
        if (f1307c) {
            PrintStream printStream = System.out;
            printStream.println("open using draft: " + this.f1316l.getClass().getSimpleName());
        }
        this.f1315k = C1006ce.C1007a.OPEN;
        try {
            this.f1313i.mo11765a((C1006ce) this, ddVar);
        } catch (RuntimeException e) {
            this.f1313i.mo11766a((C1006ce) this, (Exception) e);
        }
    }

    /* renamed from: c */
    public boolean mo11756c() {
        if (f1308h || this.f1315k != C1006ce.C1007a.OPEN || !this.f1314j) {
            return this.f1315k == C1006ce.C1007a.OPEN;
        }
        throw new AssertionError();
    }

    /* renamed from: d */
    public boolean mo11757d() {
        return this.f1315k == C1006ce.C1007a.CLOSING;
    }

    /* renamed from: e */
    public boolean mo11758e() {
        return this.f1314j;
    }

    /* renamed from: f */
    public boolean mo11759f() {
        return this.f1315k == C1006ce.C1007a.CLOSED;
    }

    /* renamed from: g */
    public C1006ce.C1007a mo11760g() {
        return this.f1315k;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        return super.toString();
    }

    /* renamed from: a */
    public InetSocketAddress mo11736a() {
        return this.f1313i.mo11771c(this);
    }
}
