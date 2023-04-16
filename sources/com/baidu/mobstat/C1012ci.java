package com.baidu.mobstat;

import androidx.core.view.PointerIconCompat;
import com.baidubce.BceConfig;
import com.baidubce.http.Headers;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.baidu.mobstat.ci */
public abstract class C1012ci extends C1009cf implements C1006ce, Runnable {

    /* renamed from: c */
    static final /* synthetic */ boolean f1325c = (!C1012ci.class.desiredAssertionStatus());
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C1010cg f1326a = null;

    /* renamed from: b */
    protected URI f1327b = null;

    /* renamed from: d */
    private Socket f1328d = null;

    /* renamed from: e */
    private InputStream f1329e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public OutputStream f1330f;

    /* renamed from: g */
    private Proxy f1331g = Proxy.NO_PROXY;

    /* renamed from: h */
    private Thread f1332h;

    /* renamed from: i */
    private C1015cj f1333i;

    /* renamed from: j */
    private Map<String, String> f1334j;

    /* renamed from: k */
    private CountDownLatch f1335k = new CountDownLatch(1);

    /* renamed from: l */
    private CountDownLatch f1336l = new CountDownLatch(1);

    /* renamed from: m */
    private int f1337m = 0;

    /* renamed from: a */
    public void mo11772a(int i, String str) {
    }

    /* renamed from: a */
    public abstract void mo11456a(int i, String str, boolean z);

    /* renamed from: a */
    public abstract void mo11457a(C1043df dfVar);

    /* renamed from: a */
    public abstract void mo11458a(Exception exc);

    /* renamed from: a */
    public abstract void mo11459a(String str);

    /* renamed from: a */
    public void mo11774a(ByteBuffer byteBuffer) {
    }

    /* renamed from: b */
    public void mo11777b(int i, String str, boolean z) {
    }

    /* renamed from: b */
    public final void mo11769b(C1006ce ceVar) {
    }

    /* renamed from: b */
    public void mo11778b(C1031cw cwVar) {
    }

    public C1012ci(URI uri, C1015cj cjVar, Map<String, String> map, int i) {
        if (uri == null) {
            throw new IllegalArgumentException();
        } else if (cjVar != null) {
            this.f1327b = uri;
            this.f1333i = cjVar;
            this.f1334j = map;
            this.f1337m = i;
            this.f1326a = new C1010cg(this, cjVar);
        } else {
            throw new IllegalArgumentException("null as draft is permitted for `WebSocketServer` only!");
        }
    }

    /* renamed from: b */
    public void mo11776b() {
        if (this.f1332h == null) {
            this.f1332h = new Thread(this);
            this.f1332h.start();
            return;
        }
        throw new IllegalStateException("WebSocketClient objects are not reuseable");
    }

    /* renamed from: c */
    public boolean mo11779c() throws InterruptedException {
        mo11776b();
        this.f1335k.await();
        return this.f1326a.mo11756c();
    }

    /* renamed from: d */
    public void mo11780d() {
        if (this.f1332h != null) {
            this.f1326a.mo11744a(1000);
        }
    }

    /* renamed from: a */
    public void mo11775a(byte[] bArr) throws NotYetConnectedException {
        this.f1326a.mo11751a(bArr);
    }

    public void run() {
        int read;
        try {
            if (this.f1328d == null) {
                this.f1328d = new Socket(this.f1331g);
            } else if (this.f1328d.isClosed()) {
                throw new IOException();
            }
            if (!this.f1328d.isBound()) {
                this.f1328d.connect(new InetSocketAddress(this.f1327b.getHost(), m1558h()), this.f1337m);
            }
            this.f1329e = this.f1328d.getInputStream();
            this.f1330f = this.f1328d.getOutputStream();
            m1559i();
            this.f1332h = new Thread(new C1014a());
            this.f1332h.start();
            byte[] bArr = new byte[C1010cg.f1306b];
            while (!mo11783g() && !mo11782f() && (read = this.f1329e.read(bArr)) != -1) {
                try {
                    this.f1326a.mo11750a(ByteBuffer.wrap(bArr, 0, read));
                } catch (IOException unused) {
                    this.f1326a.mo11752b();
                } catch (RuntimeException e) {
                    mo11458a((Exception) e);
                    this.f1326a.mo11753b(PointerIconCompat.TYPE_CELL, e.getMessage());
                }
            }
            this.f1326a.mo11752b();
            if (!f1325c && !this.f1328d.isClosed()) {
                throw new AssertionError();
            }
        } catch (Exception e2) {
            mo11766a((C1006ce) this.f1326a, e2);
            this.f1326a.mo11753b(-1, e2.getMessage());
        }
    }

    /* renamed from: h */
    private int m1558h() {
        int port = this.f1327b.getPort();
        if (port != -1) {
            return port;
        }
        String scheme = this.f1327b.getScheme();
        if (scheme.equals("wss")) {
            return 443;
        }
        if (scheme.equals("ws")) {
            return 80;
        }
        throw new RuntimeException("unknown scheme: " + scheme);
    }

    /* renamed from: i */
    private void m1559i() throws C1024cp {
        String str;
        String rawPath = this.f1327b.getRawPath();
        String rawQuery = this.f1327b.getRawQuery();
        if (rawPath == null || rawPath.length() == 0) {
            rawPath = BceConfig.BOS_DELIMITER;
        }
        if (rawQuery != null) {
            rawPath = rawPath + "?" + rawQuery;
        }
        int h = m1558h();
        StringBuilder sb = new StringBuilder();
        sb.append(this.f1327b.getHost());
        if (h != 80) {
            str = Config.TRACE_TODAY_VISIT_SPLIT + h;
        } else {
            str = "";
        }
        sb.append(str);
        String sb2 = sb.toString();
        C1039db dbVar = new C1039db();
        dbVar.mo11816a(rawPath);
        dbVar.mo11819a(Headers.HOST, sb2);
        Map<String, String> map = this.f1334j;
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                dbVar.mo11819a((String) next.getKey(), (String) next.getValue());
            }
        }
        this.f1326a.mo11749a((C1035cz) dbVar);
    }

    /* renamed from: a */
    public final void mo11767a(C1006ce ceVar, String str) {
        mo11459a(str);
    }

    /* renamed from: a */
    public final void mo11768a(C1006ce ceVar, ByteBuffer byteBuffer) {
        mo11774a(byteBuffer);
    }

    /* renamed from: a */
    public void mo11739a(C1006ce ceVar, C1031cw cwVar) {
        mo11778b(cwVar);
    }

    /* renamed from: a */
    public final void mo11765a(C1006ce ceVar, C1041dd ddVar) {
        mo11457a((C1043df) ddVar);
        this.f1335k.countDown();
    }

    /* renamed from: a */
    public final void mo11764a(C1006ce ceVar, int i, String str, boolean z) {
        Thread thread = this.f1332h;
        if (thread != null) {
            thread.interrupt();
        }
        try {
            if (this.f1328d != null) {
                this.f1328d.close();
            }
        } catch (IOException e) {
            mo11766a((C1006ce) this, (Exception) e);
        }
        mo11456a(i, str, z);
        this.f1335k.countDown();
        this.f1336l.countDown();
    }

    /* renamed from: a */
    public final void mo11766a(C1006ce ceVar, Exception exc) {
        mo11458a(exc);
    }

    /* renamed from: a */
    public void mo11763a(C1006ce ceVar, int i, String str) {
        mo11772a(i, str);
    }

    /* renamed from: b */
    public void mo11770b(C1006ce ceVar, int i, String str, boolean z) {
        mo11777b(i, str, z);
    }

    /* renamed from: c */
    public InetSocketAddress mo11771c(C1006ce ceVar) {
        Socket socket = this.f1328d;
        if (socket != null) {
            return (InetSocketAddress) socket.getLocalSocketAddress();
        }
        return null;
    }

    /* renamed from: com.baidu.mobstat.ci$a */
    class C1014a implements Runnable {
        private C1014a() {
        }

        public void run() {
            Thread.currentThread().setName("WebsocketWriteThread");
            while (!Thread.interrupted()) {
                try {
                    ByteBuffer take = C1012ci.this.f1326a.f1309d.take();
                    C1012ci.this.f1330f.write(take.array(), 0, take.limit());
                    C1012ci.this.f1330f.flush();
                } catch (IOException unused) {
                    C1012ci.this.f1326a.mo11752b();
                    return;
                } catch (InterruptedException unused2) {
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public void mo11773a(Socket socket) {
        if (this.f1328d == null) {
            this.f1328d = socket;
            return;
        }
        throw new IllegalStateException("socket has already been set");
    }

    /* renamed from: e */
    public boolean mo11781e() {
        return this.f1326a.mo11758e();
    }

    /* renamed from: f */
    public boolean mo11782f() {
        return this.f1326a.mo11759f();
    }

    /* renamed from: g */
    public boolean mo11783g() {
        return this.f1326a.mo11757d();
    }

    /* renamed from: a */
    public void mo11737a(C1031cw cwVar) {
        this.f1326a.mo11737a(cwVar);
    }

    /* renamed from: a */
    public InetSocketAddress mo11736a() {
        return this.f1326a.mo11736a();
    }
}
