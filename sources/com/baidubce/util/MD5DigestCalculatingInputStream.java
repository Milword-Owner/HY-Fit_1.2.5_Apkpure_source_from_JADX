package com.baidubce.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5DigestCalculatingInputStream extends FilterInputStream {
    private MessageDigest digest = MessageDigest.getInstance("MD5");

    public MD5DigestCalculatingInputStream(InputStream inputStream) throws NoSuchAlgorithmException {
        super(inputStream);
    }

    public byte[] getMd5Digest() {
        return this.digest.digest();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void reset() throws java.io.IOException {
        /*
            r1 = this;
            monitor-enter(r1)
            java.lang.String r0 = "MD5"
            java.security.MessageDigest r0 = java.security.MessageDigest.getInstance(r0)     // Catch:{ NoSuchAlgorithmException -> 0x000c }
            r1.digest = r0     // Catch:{ NoSuchAlgorithmException -> 0x000c }
            goto L_0x000c
        L_0x000a:
            r0 = move-exception
            goto L_0x0013
        L_0x000c:
            java.io.InputStream r0 = r1.in     // Catch:{ all -> 0x000a }
            r0.reset()     // Catch:{ all -> 0x000a }
            monitor-exit(r1)
            return
        L_0x0013:
            monitor-exit(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidubce.util.MD5DigestCalculatingInputStream.reset():void");
    }

    public int read() throws IOException {
        int read = this.in.read();
        if (read != -1) {
            this.digest.update((byte) read);
        }
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int read = this.in.read(bArr, i, i2);
        if (read != -1) {
            this.digest.update(bArr, i, read);
        }
        return read;
    }
}
