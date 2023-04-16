package com.baidu.mobstat;

import com.baidu.mobstat.C1031cw;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* renamed from: com.baidu.mobstat.cx */
public class C1033cx implements C1030cv {

    /* renamed from: b */
    protected static byte[] f1368b = new byte[0];

    /* renamed from: a */
    private ByteBuffer f1369a;

    /* renamed from: c */
    protected boolean f1370c;

    /* renamed from: d */
    protected C1031cw.C1032a f1371d;

    /* renamed from: e */
    protected boolean f1372e;

    public C1033cx() {
    }

    public C1033cx(C1031cw.C1032a aVar) {
        this.f1371d = aVar;
        this.f1369a = ByteBuffer.wrap(f1368b);
    }

    public C1033cx(C1031cw cwVar) {
        this.f1370c = cwVar.mo11812d();
        this.f1371d = cwVar.mo11814f();
        this.f1369a = cwVar.mo11806c();
        this.f1372e = cwVar.mo11813e();
    }

    /* renamed from: d */
    public boolean mo11812d() {
        return this.f1370c;
    }

    /* renamed from: f */
    public C1031cw.C1032a mo11814f() {
        return this.f1371d;
    }

    /* renamed from: e */
    public boolean mo11813e() {
        return this.f1372e;
    }

    /* renamed from: c */
    public ByteBuffer mo11806c() {
        return this.f1369a;
    }

    /* renamed from: a */
    public void mo11809a(boolean z) {
        this.f1370c = z;
    }

    /* renamed from: a */
    public void mo11808a(C1031cw.C1032a aVar) {
        this.f1371d = aVar;
    }

    /* renamed from: a */
    public void mo11805a(ByteBuffer byteBuffer) throws C1022cn {
        this.f1369a = byteBuffer;
    }

    /* renamed from: b */
    public void mo11810b(boolean z) {
        this.f1372e = z;
    }

    /* renamed from: a */
    public void mo11811a(C1031cw cwVar) throws C1023co {
        ByteBuffer c = cwVar.mo11806c();
        if (this.f1369a == null) {
            this.f1369a = ByteBuffer.allocate(c.remaining());
            c.mark();
            this.f1369a.put(c);
            c.reset();
        } else {
            c.mark();
            ByteBuffer byteBuffer = this.f1369a;
            byteBuffer.position(byteBuffer.limit());
            ByteBuffer byteBuffer2 = this.f1369a;
            byteBuffer2.limit(byteBuffer2.capacity());
            if (c.remaining() > this.f1369a.remaining()) {
                ByteBuffer allocate = ByteBuffer.allocate(c.remaining() + this.f1369a.capacity());
                this.f1369a.flip();
                allocate.put(this.f1369a);
                allocate.put(c);
                this.f1369a = allocate;
            } else {
                this.f1369a.put(c);
            }
            this.f1369a.rewind();
            c.reset();
        }
        this.f1370c = cwVar.mo11812d();
    }

    public String toString() {
        return "Framedata{ optcode:" + mo11814f() + ", fin:" + mo11812d() + ", payloadlength:[pos:" + this.f1369a.position() + ", len:" + this.f1369a.remaining() + "], payload:" + Arrays.toString(C1047di.m1690a(new String(this.f1369a.array()))) + "}";
    }
}
