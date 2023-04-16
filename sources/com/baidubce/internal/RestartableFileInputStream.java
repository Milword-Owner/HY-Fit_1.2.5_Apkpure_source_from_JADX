package com.baidubce.internal;

import com.baidubce.BceClientException;
import com.baidubce.util.CheckUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RestartableFileInputStream extends RestartableInputStream {
    private File file;
    private FileInputStream input;

    public RestartableFileInputStream(File file2) throws FileNotFoundException {
        CheckUtils.isNotNull(file2, "file should not be null.");
        this.file = file2;
        this.input = new FileInputStream(file2);
    }

    public void restart() {
        try {
            this.input.close();
            this.input = new FileInputStream(this.file);
        } catch (IOException e) {
            throw new BceClientException("Fail to restart.", e);
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
