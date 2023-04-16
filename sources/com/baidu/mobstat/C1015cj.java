package com.baidu.mobstat;

import com.baidu.mobstat.C1006ce;
import com.baidu.mobstat.C1031cw;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.baidu.mobstat.cj */
public abstract class C1015cj {

    /* renamed from: a */
    public static int f1339a = 1000;

    /* renamed from: b */
    public static int f1340b = 64;

    /* renamed from: c */
    public static final byte[] f1341c = C1047di.m1690a("<policy-file-request/>\u0000");

    /* renamed from: d */
    protected C1006ce.C1008b f1342d = null;

    /* renamed from: e */
    protected C1031cw.C1032a f1343e = null;

    /* renamed from: com.baidu.mobstat.cj$a */
    public enum C1016a {
        NONE,
        ONEWAY,
        TWOWAY
    }

    /* renamed from: com.baidu.mobstat.cj$b */
    public enum C1017b {
        MATCHED,
        NOT_MATCHED
    }

    /* renamed from: a */
    public abstract C1017b mo11787a(C1034cy cyVar, C1043df dfVar) throws C1024cp;

    /* renamed from: a */
    public abstract C1035cz mo11788a(C1035cz czVar) throws C1024cp;

    /* renamed from: a */
    public abstract ByteBuffer mo11789a(C1031cw cwVar);

    /* renamed from: a */
    public abstract List<C1031cw> mo11792a(ByteBuffer byteBuffer, boolean z);

    /* renamed from: a */
    public abstract void mo11793a();

    /* renamed from: b */
    public abstract C1016a mo11795b();

    /* renamed from: c */
    public abstract C1015cj mo11796c();

    /* renamed from: c */
    public abstract List<C1031cw> mo11797c(ByteBuffer byteBuffer) throws C1022cn;

    /* renamed from: a */
    public static ByteBuffer m1589a(ByteBuffer byteBuffer) {
        ByteBuffer allocate = ByteBuffer.allocate(byteBuffer.remaining());
        byte b = 48;
        while (byteBuffer.hasRemaining()) {
            byte b2 = byteBuffer.get();
            allocate.put(b2);
            if (b == 13 && b2 == 10) {
                allocate.limit(allocate.position() - 2);
                allocate.position(0);
                return allocate;
            }
            b = b2;
        }
        byteBuffer.position(byteBuffer.position() - allocate.position());
        return null;
    }

    /* renamed from: b */
    public static String m1590b(ByteBuffer byteBuffer) {
        ByteBuffer a = m1589a(byteBuffer);
        if (a == null) {
            return null;
        }
        return C1047di.m1688a(a.array(), 0, a.limit());
    }

    /* renamed from: a */
    public static C1038da m1588a(ByteBuffer byteBuffer, C1006ce.C1008b bVar) throws C1024cp, C1021cm {
        C1040dc dcVar;
        String b = m1590b(byteBuffer);
        if (b != null) {
            String[] split = b.split(" ", 3);
            if (split.length == 3) {
                if (bVar == C1006ce.C1008b.CLIENT) {
                    C1040dc dcVar2 = new C1040dc();
                    C1044dg dgVar = dcVar2;
                    dgVar.mo11822a(Short.parseShort(split[1]));
                    dgVar.mo11821a(split[2]);
                    dcVar = dcVar2;
                } else {
                    C1039db dbVar = new C1039db();
                    dbVar.mo11816a(split[1]);
                    dcVar = dbVar;
                }
                String b2 = m1590b(byteBuffer);
                while (b2 != null && b2.length() > 0) {
                    String[] split2 = b2.split(Config.TRACE_TODAY_VISIT_SPLIT, 2);
                    if (split2.length == 2) {
                        dcVar.mo11819a(split2[0], split2[1].replaceFirst("^ +", ""));
                        b2 = m1590b(byteBuffer);
                    } else {
                        throw new C1024cp("not an http header");
                    }
                }
                if (b2 != null) {
                    return dcVar;
                }
                throw new C1021cm();
            }
            throw new C1024cp();
        }
        throw new C1021cm(byteBuffer.capacity() + 128);
    }

    /* renamed from: a */
    public List<ByteBuffer> mo11790a(C1041dd ddVar, C1006ce.C1008b bVar) {
        return mo11791a(ddVar, bVar, true);
    }

    /* renamed from: a */
    public List<ByteBuffer> mo11791a(C1041dd ddVar, C1006ce.C1008b bVar, boolean z) {
        int i;
        StringBuilder sb = new StringBuilder(100);
        if (ddVar instanceof C1034cy) {
            sb.append("GET ");
            sb.append(((C1034cy) ddVar).mo11815a());
            sb.append(" HTTP/1.1");
        } else if (ddVar instanceof C1043df) {
            sb.append("HTTP/1.1 101 ");
            sb.append(((C1043df) ddVar).mo11820a());
        } else {
            throw new RuntimeException("unknown role");
        }
        sb.append("\r\n");
        Iterator<String> b = ddVar.mo11824b();
        while (b.hasNext()) {
            String next = b.next();
            String b2 = ddVar.mo11823b(next);
            sb.append(next);
            sb.append(": ");
            sb.append(b2);
            sb.append("\r\n");
        }
        sb.append("\r\n");
        byte[] b3 = C1047di.m1692b(sb.toString());
        byte[] c = z ? ddVar.mo11826c() : null;
        if (c == null) {
            i = 0;
        } else {
            i = c.length;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i + b3.length);
        allocate.put(b3);
        if (c != null) {
            allocate.put(c);
        }
        allocate.flip();
        return Collections.singletonList(allocate);
    }

    /* renamed from: d */
    public C1041dd mo11798d(ByteBuffer byteBuffer) throws C1024cp {
        return m1588a(byteBuffer, this.f1342d);
    }

    /* renamed from: a */
    public int mo11786a(int i) throws C1025cq, C1022cn {
        if (i >= 0) {
            return i;
        }
        throw new C1022cn(1002, "Negative count");
    }

    /* renamed from: a */
    public void mo11794a(C1006ce.C1008b bVar) {
        this.f1342d = bVar;
    }
}
