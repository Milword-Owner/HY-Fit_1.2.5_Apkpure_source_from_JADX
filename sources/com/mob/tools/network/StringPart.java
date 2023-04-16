package com.mob.tools.network;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class StringPart extends HTTPPart {

    /* renamed from: sb */
    private StringBuilder f2348sb = new StringBuilder();

    public StringPart append(String str) {
        this.f2348sb.append(str);
        return this;
    }

    /* access modifiers changed from: protected */
    public InputStream getInputStream() throws Throwable {
        return new ByteArrayInputStream(this.f2348sb.toString().getBytes("utf-8"));
    }

    public String toString() {
        return this.f2348sb.toString();
    }

    /* access modifiers changed from: protected */
    public long length() throws Throwable {
        return (long) this.f2348sb.toString().getBytes("utf-8").length;
    }
}
