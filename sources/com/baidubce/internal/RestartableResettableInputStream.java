package com.baidubce.internal;

import com.baidubce.BceClientException;
import com.baidubce.util.CheckUtils;
import java.io.IOException;
import java.io.InputStream;

public class RestartableResettableInputStream extends RestartableInputStream {
    private InputStream input;

    public RestartableResettableInputStream(InputStream inputStream) {
        CheckUtils.isNotNull(inputStream, "input should not be null.");
        CheckUtils.checkArgument(inputStream.markSupported(), "input does not support mark.");
        this.input = inputStream;
    }

    public void restart() {
        try {
            this.input.reset();
        } catch (IOException e) {
            throw new BceClientException("Fail to reset the underlying input stream.", e);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        return this.input.read(bArr, i, i2);
    }

    public int read() throws IOException {
        return this.input.read();
    }

    public void close() throws IOException {
        this.input.close();
    }
}
