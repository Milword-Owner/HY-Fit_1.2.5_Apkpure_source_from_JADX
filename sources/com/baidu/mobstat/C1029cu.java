package com.baidu.mobstat;

import com.baidu.mobstat.C1031cw;
import java.nio.ByteBuffer;

/* renamed from: com.baidu.mobstat.cu */
public class C1029cu extends C1033cx implements C1028ct {

    /* renamed from: a */
    static final ByteBuffer f1358a = ByteBuffer.allocate(0);

    /* renamed from: f */
    private int f1359f;

    /* renamed from: g */
    private String f1360g;

    public C1029cu() {
        super(C1031cw.C1032a.CLOSING);
        mo11809a(true);
    }

    public C1029cu(int i, String str) throws C1022cn {
        super(C1031cw.C1032a.CLOSING);
        mo11809a(true);
        m1624a(i, str);
    }

    /* renamed from: a */
    private void m1624a(int i, String str) throws C1022cn {
        if (str == null) {
            str = "";
        }
        if (i == 1015) {
            str = "";
            i = 1005;
        }
        if (i == 1005) {
            if (str.length() > 0) {
                throw new C1022cn(1002, "A close frame must have a closecode if it has a reason");
            }
        } else if (i <= 1011 || i >= 3000 || i == 1015) {
            byte[] a = C1047di.m1690a(str);
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.putInt(i);
            allocate.position(2);
            ByteBuffer allocate2 = ByteBuffer.allocate(a.length + 2);
            allocate2.put(allocate);
            allocate2.put(a);
            allocate2.rewind();
            mo11805a(allocate2);
        } else {
            throw new C1022cn(1002, "Trying to send an illegal close code!");
        }
    }

    /* renamed from: g */
    private void m1625g() throws C1023co {
        this.f1359f = 1005;
        ByteBuffer c = super.mo11806c();
        c.mark();
        if (c.remaining() >= 2) {
            ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.position(2);
            allocate.putShort(c.getShort());
            allocate.position(0);
            this.f1359f = allocate.getInt();
            int i = this.f1359f;
            if (i == 1006 || i == 1015 || i == 1005 || i > 4999 || i < 1000 || i == 1004) {
                throw new C1023co("closecode must not be sent over the wire: " + this.f1359f);
            }
        }
        c.reset();
    }

    /* renamed from: a */
    public int mo11803a() {
        return this.f1359f;
    }

    /* renamed from: h */
    private void m1626h() throws C1022cn {
        if (this.f1359f == 1005) {
            this.f1360g = C1047di.m1687a(super.mo11806c());
            return;
        }
        ByteBuffer c = super.mo11806c();
        int position = c.position();
        try {
            c.position(c.position() + 2);
            this.f1360g = C1047di.m1687a(c);
            c.position(position);
        } catch (IllegalArgumentException e) {
            throw new C1023co((Throwable) e);
        } catch (Throwable th) {
            c.position(position);
            throw th;
        }
    }

    /* renamed from: b */
    public String mo11804b() {
        return this.f1360g;
    }

    public String toString() {
        return super.toString() + "code: " + this.f1359f;
    }

    /* renamed from: a */
    public void mo11805a(ByteBuffer byteBuffer) throws C1022cn {
        super.mo11805a(byteBuffer);
        m1625g();
        m1626h();
    }

    /* renamed from: c */
    public ByteBuffer mo11806c() {
        if (this.f1359f == 1005) {
            return f1358a;
        }
        return super.mo11806c();
    }
}
