package com.baidubce.services.bos;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import okhttp3.Response;

public class BosObjectInputStream extends FilterInputStream {
    private Response httpResponse;

    public BosObjectInputStream(InputStream inputStream, Response response) {
        super(inputStream);
        this.httpResponse = response;
    }

    public void close() throws IOException {
        try {
            super.close();
        } catch (SocketException unused) {
        }
    }
}
