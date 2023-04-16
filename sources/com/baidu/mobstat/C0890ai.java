package com.baidu.mobstat;

import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.fitness.FitnessStatusCodes;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;
import java.security.SecureRandom;
import java.util.Map;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.json.JSONObject;

/* renamed from: com.baidu.mobstat.ai */
class C0890ai {

    /* renamed from: c */
    private static final ByteBuffer f954c = ByteBuffer.allocate(0);
    /* access modifiers changed from: private */

    /* renamed from: a */
    public C0891a f955a;

    /* renamed from: b */
    private C0892b f956b;

    /* renamed from: com.baidu.mobstat.ai$a */
    public interface C0891a {
        /* renamed from: a */
        void mo11452a();

        /* renamed from: a */
        void mo11453a(String str);

        /* renamed from: a */
        void mo11454a(boolean z);

        /* renamed from: b */
        void mo11455b();
    }

    /* renamed from: c */
    private Socket m852c() {
        SSLSocketFactory sSLSocketFactory;
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
            sSLSocketFactory = instance.getSocketFactory();
        } catch (Exception unused) {
            sSLSocketFactory = null;
        }
        if (sSLSocketFactory == null) {
            return null;
        }
        try {
            return sSLSocketFactory.createSocket();
        } catch (Exception unused2) {
            return null;
        }
    }

    public C0890ai(URI uri, C0891a aVar) throws C0893c {
        this.f955a = aVar;
        try {
            this.f956b = new C0892b(uri, FitnessStatusCodes.NEEDS_OAUTH_PERMISSIONS, uri.toString().startsWith("wss://") ? m852c() : null);
            this.f956b.mo11779c();
        } catch (InterruptedException e) {
            throw new C0893c(e);
        }
    }

    /* renamed from: a */
    public void mo11449a() {
        C0892b bVar = this.f956b;
        if (bVar != null) {
            bVar.mo11780d();
        }
    }

    /* renamed from: a */
    public void mo11450a(JSONObject jSONObject) throws NotYetConnectedException {
        if (this.f956b != null) {
            this.f956b.mo11775a(jSONObject.toString().getBytes());
        }
    }

    /* renamed from: b */
    public boolean mo11451b() {
        return !this.f956b.mo11782f() && !this.f956b.mo11783g() && !this.f956b.mo11781e();
    }

    /* renamed from: com.baidu.mobstat.ai$b */
    class C0892b extends C1012ci {
        public C0892b(URI uri, int i, Socket socket) throws InterruptedException {
            super(uri, new C1020cl(), (Map<String, String>) null, i);
            mo11773a(socket);
        }

        /* renamed from: a */
        public void mo11457a(C1043df dfVar) {
            if (C0956bc.m1198c().mo11630b()) {
                C0956bc.m1198c().mo11624a("onOpen");
            }
            if (C0890ai.this.f955a != null) {
                C0890ai.this.f955a.mo11452a();
            }
        }

        /* renamed from: a */
        public void mo11459a(String str) {
            JSONObject jSONObject;
            if (C0956bc.m1198c().mo11630b()) {
                C0956bc.m1198c().mo11624a("onMessage: " + str);
            }
            if (!TextUtils.isEmpty(str)) {
                String str2 = null;
                try {
                    jSONObject = new JSONObject(str);
                } catch (Exception unused) {
                    jSONObject = null;
                }
                if (jSONObject != null) {
                    try {
                        str2 = jSONObject.getString("type");
                    } catch (Exception unused2) {
                    }
                    if (!TextUtils.isEmpty(str2)) {
                        if (str2.equals("deploy")) {
                            try {
                                C0890ai.this.f955a.mo11453a(((JSONObject) jSONObject.get(ShareConstants.WEB_DIALOG_PARAM_DATA)).toString());
                            } catch (Exception unused3) {
                            }
                        } else {
                            int i = -1;
                            try {
                                i = ((Integer) ((JSONObject) jSONObject.get(ShareConstants.WEB_DIALOG_PARAM_DATA)).get("status")).intValue();
                            } catch (Exception unused4) {
                            }
                            switch (i) {
                                case 801020:
                                    C0955bb.m1194c().mo11624a("autotrace: connect established");
                                    C0900al.m880a().mo11492a(2);
                                    return;
                                case 801021:
                                    C0955bb.m1194c().mo11624a("autotrace: connect failed, connect has been established");
                                    C0900al.m880a().mo11493a(5, "already connect");
                                    return;
                                case 801024:
                                    C0955bb.m1194c().mo11624a("autotrace: connect confirm");
                                    C0900al.m880a().mo11492a(3);
                                    if (C0890ai.this.f955a != null) {
                                        C0890ai.this.f955a.mo11455b();
                                        return;
                                    }
                                    return;
                                default:
                                    return;
                            }
                        }
                    }
                }
            }
        }

        /* renamed from: a */
        public void mo11456a(int i, String str, boolean z) {
            if (C0956bc.m1198c().mo11630b()) {
                C0956bc c = C0956bc.m1198c();
                c.mo11624a("onClose,  reason:" + str + ", remote:" + z);
            }
            C0955bb c2 = C0955bb.m1194c();
            c2.mo11624a("autotrace: connect closed, server:" + z + " reason:" + str);
            C0900al a = C0900al.m880a();
            a.mo11493a(5, "remote:" + z + "|reason:" + str);
            if (C0890ai.this.f955a != null) {
                C0890ai.this.f955a.mo11454a(z);
            }
        }

        /* renamed from: a */
        public void mo11458a(Exception exc) {
            if (C0956bc.m1198c().mo11630b()) {
                C0956bc.m1198c().mo11624a("onError");
            }
        }
    }

    /* renamed from: com.baidu.mobstat.ai$c */
    public class C0893c extends IOException {
        public C0893c(Throwable th) {
            super(th.getMessage());
        }
    }
}
