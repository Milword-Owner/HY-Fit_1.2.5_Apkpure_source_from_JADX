package com.mob.tools.network;

import com.mob.tools.utils.ReflectHelper;
import java.io.InputStream;

public abstract class HTTPPart {
    private OnReadListener listener;
    private long offset;

    /* access modifiers changed from: protected */
    public abstract InputStream getInputStream() throws Throwable;

    /* access modifiers changed from: protected */
    public abstract long length() throws Throwable;

    public void setOffset(long j) {
        this.offset = j;
    }

    public InputStream toInputStream() throws Throwable {
        ByteCounterInputStream byteCounterInputStream = new ByteCounterInputStream(getInputStream());
        byteCounterInputStream.setOnInputStreamReadListener(this.listener);
        long j = this.offset;
        if (j > 0) {
            byteCounterInputStream.skip(j);
        }
        return byteCounterInputStream;
    }

    public Object getInputStreamEntity() throws Throwable {
        InputStream inputStream = toInputStream();
        long length = length() - this.offset;
        ReflectHelper.importClass("org.apache.http.entity.InputStreamEntity");
        return ReflectHelper.newInstance("InputStreamEntity", inputStream, Long.valueOf(length));
    }

    public void setOnReadListener(OnReadListener onReadListener) {
        this.listener = onReadListener;
    }
}
