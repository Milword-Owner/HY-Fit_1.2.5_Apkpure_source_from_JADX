package com.baidubce.http.handler;

import com.baidubce.BceResponseMetadata;
import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.Headers;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.services.bos.model.BosResponseMetadata;

public class BosMetadataResponseHandler implements HttpResponseHandler {
    public boolean handle(BceHttpResponse bceHttpResponse, AbstractBceResponse abstractBceResponse) throws Exception {
        BceResponseMetadata metadata = abstractBceResponse.getMetadata();
        if (!(metadata instanceof BosResponseMetadata)) {
            return false;
        }
        BosResponseMetadata bosResponseMetadata = (BosResponseMetadata) metadata;
        bosResponseMetadata.setBosDebugId(bceHttpResponse.getHeader(Headers.BCE_DEBUG_ID));
        if (bceHttpResponse.getHeader(Headers.BCE_NEXT_APPEND_OFFSET) != null) {
            bosResponseMetadata.setNextAppendOffset(Long.valueOf(Long.parseLong(bceHttpResponse.getHeader(Headers.BCE_NEXT_APPEND_OFFSET))));
        }
        if (bceHttpResponse.getHeader(Headers.LOCATION) == null) {
            return false;
        }
        metadata.setLocation(bceHttpResponse.getHeader(Headers.LOCATION));
        return false;
    }
}
