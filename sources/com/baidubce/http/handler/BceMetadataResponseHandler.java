package com.baidubce.http.handler;

import com.baidubce.BceResponseMetadata;
import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.Headers;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.JoinerUtils;

public class BceMetadataResponseHandler implements HttpResponseHandler {
    public boolean handle(BceHttpResponse bceHttpResponse, AbstractBceResponse abstractBceResponse) throws Exception {
        abstractBceResponse.setHttpResponse(bceHttpResponse);
        BceResponseMetadata metadata = abstractBceResponse.getMetadata();
        metadata.setBceRequestId(bceHttpResponse.getHeader(Headers.BCE_REQUEST_ID));
        metadata.setBceContentSha256(bceHttpResponse.getHeader(Headers.BCE_CONTENT_SHA256));
        metadata.setContentDisposition(bceHttpResponse.getHeader(Headers.CONTENT_DISPOSITION));
        metadata.setContentEncoding(bceHttpResponse.getHeader(Headers.CONTENT_ENCODING));
        metadata.setContentLength(bceHttpResponse.getHeaderAsLong(Headers.CONTENT_LENGTH));
        metadata.setContentMd5(bceHttpResponse.getHeader(Headers.CONTENT_MD5));
        metadata.setContentRange(bceHttpResponse.getHeader(Headers.CONTENT_RANGE));
        metadata.setContentType(bceHttpResponse.getHeader(Headers.CONTENT_TYPE));
        metadata.setDate(bceHttpResponse.getHeaderAsRfc822Date(Headers.DATE));
        metadata.setTransferEncoding(bceHttpResponse.getHeader(Headers.TRANSFER_ENCODING));
        metadata.setLocation(bceHttpResponse.getHeader(Headers.LOCATION));
        String header = bceHttpResponse.getHeader(Headers.ETAG);
        if (header != null) {
            metadata.setETag(JoinerUtils.cut("\"", header));
        }
        String header2 = bceHttpResponse.getHeader(Headers.BCE_CRC32);
        if (header2 != null) {
            try {
                metadata.setCrc32(Long.valueOf(Long.parseLong(header2)));
            } catch (NumberFormatException unused) {
            }
        }
        metadata.setExpires(bceHttpResponse.getHeaderAsRfc822Date(Headers.EXPIRES));
        metadata.setLastModified(bceHttpResponse.getHeaderAsRfc822Date(Headers.LAST_MODIFIED));
        metadata.setServer(bceHttpResponse.getHeader(Headers.SERVER));
        return false;
    }
}
