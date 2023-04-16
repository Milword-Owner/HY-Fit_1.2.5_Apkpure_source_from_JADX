package com.baidubce.http.handler;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.JsonUtils;
import java.io.InputStream;

public class BceJsonResponseHandler implements HttpResponseHandler {
    public boolean handle(BceHttpResponse bceHttpResponse, AbstractBceResponse abstractBceResponse) throws Exception {
        InputStream content = bceHttpResponse.getContent();
        if (content == null) {
            return true;
        }
        if (abstractBceResponse.getMetadata().getContentLength() > 0 || "chunked".equalsIgnoreCase(abstractBceResponse.getMetadata().getTransferEncoding())) {
            JsonUtils.load(bceHttpResponse, abstractBceResponse);
        }
        content.close();
        return true;
    }
}
