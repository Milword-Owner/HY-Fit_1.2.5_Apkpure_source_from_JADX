package com.mob.tools.network;

import com.mob.tools.utils.Data;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayPart extends HTTPPart {
    private BufferedByteArrayOutputStream buffer;

    public ByteArrayPart append(byte[] bArr) throws Throwable {
        if (this.buffer == null) {
            this.buffer = new BufferedByteArrayOutputStream(bArr.length);
        }
        this.buffer.write(bArr);
        this.buffer.flush();
        return this;
    }

    /* access modifiers changed from: protected */
    public InputStream getInputStream() throws Throwable {
        BufferedByteArrayOutputStream bufferedByteArrayOutputStream = this.buffer;
        if (bufferedByteArrayOutputStream == null) {
            return new ByteArrayInputStream(new byte[0]);
        }
        byte[] buffer2 = bufferedByteArrayOutputStream.getBuffer();
        if (buffer2 == null || this.buffer.size() <= 0) {
            return new ByteArrayInputStream(new byte[0]);
        }
        return new ByteArrayInputStream(buffer2, 0, this.buffer.size());
    }

    public String toString() {
        byte[] buffer2;
        BufferedByteArrayOutputStream bufferedByteArrayOutputStream = this.buffer;
        if (bufferedByteArrayOutputStream == null || (buffer2 = bufferedByteArrayOutputStream.getBuffer()) == null) {
            return null;
        }
        return Data.byteToHex(buffer2, 0, this.buffer.size());
    }

    /* access modifiers changed from: protected */
    public long length() throws Throwable {
        BufferedByteArrayOutputStream bufferedByteArrayOutputStream = this.buffer;
        if (bufferedByteArrayOutputStream == null) {
            return 0;
        }
        return (long) bufferedByteArrayOutputStream.size();
    }
}
