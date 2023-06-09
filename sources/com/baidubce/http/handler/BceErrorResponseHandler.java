package com.baidubce.http.handler;

import com.baidubce.BceErrorResponse;
import com.baidubce.BceServiceException;
import com.baidubce.http.BceHttpResponse;
import com.baidubce.http.Headers;
import com.baidubce.model.AbstractBceResponse;
import com.baidubce.util.JsonUtils;
import java.io.InputStream;

public class BceErrorResponseHandler implements HttpResponseHandler {
    public boolean handle(BceHttpResponse bceHttpResponse, AbstractBceResponse abstractBceResponse) throws Exception {
        if (bceHttpResponse.getStatusCode() / 100 == 2) {
            return false;
        }
        InputStream content = bceHttpResponse.getContent();
        BceServiceException bceServiceException = null;
        if (content != null) {
            BceErrorResponse loadError = JsonUtils.loadError(content);
            if (loadError == null) {
                BceServiceException bceServiceException2 = new BceServiceException(bceHttpResponse.getStatusText());
                bceServiceException2.setErrorCode((String) null);
                bceServiceException2.setRequestId(bceHttpResponse.getHeader(Headers.BCE_REQUEST_ID));
                bceServiceException = bceServiceException2;
            } else if (loadError.getMessage() != null) {
                bceServiceException = new BceServiceException(loadError.getMessage());
                bceServiceException.setErrorCode(loadError.getCode());
                bceServiceException.setRequestId(loadError.getRequestId());
            }
            content.close();
        }
        if (bceServiceException == null) {
            bceServiceException = new BceServiceException(bceHttpResponse.getStatusText());
            bceServiceException.setRequestId(abstractBceResponse.getMetadata().getBceRequestId());
        }
        bceServiceException.setStatusCode(bceHttpResponse.getStatusCode());
        if (bceServiceException.getStatusCode() >= 500) {
            bceServiceException.setErrorType(BceServiceException.ErrorType.Service);
        } else {
            bceServiceException.setErrorType(BceServiceException.ErrorType.Client);
        }
        throw bceServiceException;
    }
}
