package com.mob.mobapm.proxy.p032c;

import com.mob.mobapm.p031e.C2378d;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

/* renamed from: com.mob.mobapm.proxy.c.a */
public class C2399a implements HttpEntity {

    /* renamed from: a */
    private HttpEntity f2326a;

    /* renamed from: b */
    private ByteArrayOutputStream f2327b;

    public C2399a(HttpEntity httpEntity) {
        this.f2326a = httpEntity;
    }

    public void consumeContent() throws IOException {
        this.f2326a.consumeContent();
    }

    public InputStream getContent() throws IOException, IllegalStateException {
        ByteArrayOutputStream byteArrayOutputStream = this.f2327b;
        if (byteArrayOutputStream != null && byteArrayOutputStream.size() <= 0) {
            return this.f2326a.getContent();
        }
        if (this.f2327b == null) {
            this.f2327b = C2378d.m2818a(this.f2326a.getContent());
        }
        if (this.f2327b.size() > 0) {
            return new ByteArrayInputStream(this.f2327b.toByteArray());
        }
        return this.f2326a.getContent();
    }

    public Header getContentEncoding() {
        return this.f2326a.getContentEncoding();
    }

    public long getContentLength() {
        return this.f2326a.getContentLength();
    }

    public Header getContentType() {
        return this.f2326a.getContentType();
    }

    public boolean isChunked() {
        return this.f2326a.isChunked();
    }

    public boolean isRepeatable() {
        return this.f2326a.isRepeatable();
    }

    public boolean isStreaming() {
        return this.f2326a.isStreaming();
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        this.f2326a.writeTo(outputStream);
    }
}
