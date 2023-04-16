package com.mob.tools.network;

import java.io.IOException;
import java.io.InputStream;

public class ByteCounterInputStream extends InputStream {

    /* renamed from: is */
    private InputStream f2347is;
    private OnReadListener listener;
    private long readBytes;

    public ByteCounterInputStream(InputStream inputStream) {
        this.f2347is = inputStream;
    }

    public int read() throws IOException {
        int read = this.f2347is.read();
        if (read >= 0) {
            this.readBytes++;
            OnReadListener onReadListener = this.listener;
            if (onReadListener != null) {
                onReadListener.onRead(this.readBytes);
            }
        }
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.f2347is.read(bArr, i, i2);
        if (read > 0) {
            this.readBytes += (long) read;
            OnReadListener onReadListener = this.listener;
            if (onReadListener != null) {
                onReadListener.onRead(this.readBytes);
            }
        }
        return read;
    }

    public void mark(int i) {
        this.f2347is.mark(i);
    }

    public boolean markSupported() {
        return this.f2347is.markSupported();
    }

    public synchronized void reset() throws IOException {
        this.f2347is.reset();
        this.readBytes = 0;
    }

    public long skip(long j) throws IOException {
        return this.f2347is.skip(j);
    }

    public int available() throws IOException {
        return this.f2347is.available();
    }

    public void close() throws IOException {
        this.f2347is.close();
    }

    public void setOnInputStreamReadListener(OnReadListener onReadListener) {
        this.listener = onReadListener;
    }
}
