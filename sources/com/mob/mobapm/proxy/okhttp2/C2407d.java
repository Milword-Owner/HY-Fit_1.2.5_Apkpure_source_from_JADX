package com.mob.mobapm.proxy.okhttp2;

import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import com.squareup.okhttp.ResponseBody;
import java.io.IOException;
import okio.Buffer;
import okio.BufferedSource;

/* renamed from: com.mob.mobapm.proxy.okhttp2.d */
public class C2407d {
    /* renamed from: a */
    public static ResponseBody m2903a(ResponseBody responseBody, long j) throws IOException {
        try {
            BufferedSource source = responseBody.source();
            source.request(j);
            Buffer clone = source.buffer().clone();
            if (clone.size() > j) {
                Buffer buffer = new Buffer();
                buffer.write(clone, j);
                clone.clear();
                clone = buffer;
            }
            return ResponseBody.create(responseBody.contentType(), clone.size(), clone);
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29775i("APM: error:" + th, new Object[0]);
            return null;
        }
    }
}
