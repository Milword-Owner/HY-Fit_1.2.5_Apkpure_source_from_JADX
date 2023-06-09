package com.baidubce.services.sts;

import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.handler.BceJsonResponseHandler;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.sts.model.GetSessionTokenResponse;
import com.baidubce.util.JsonUtils;
import java.io.InputStream;

public class BceStsJsonResponseHandler extends BceJsonResponseHandler {
    public boolean handle(BceHttpResponse bceHttpResponse, AbstractBceResponse abstractBceResponse) throws Exception {
        if (!(abstractBceResponse instanceof GetSessionTokenResponse)) {
            return super.handle(bceHttpResponse, abstractBceResponse);
        }
        GetSessionTokenResponse getSessionTokenResponse = (GetSessionTokenResponse) abstractBceResponse;
        InputStream content = bceHttpResponse.getContent();
        if (content == null) {
            return true;
        }
        if (abstractBceResponse.getMetadata().getContentLength() > 0 || "chunked".equalsIgnoreCase(abstractBceResponse.getMetadata().getTransferEncoding())) {
            JsonUtils.load(content, (AbstractBceResponse) getSessionTokenResponse);
        }
        content.close();
        return true;
    }
}
