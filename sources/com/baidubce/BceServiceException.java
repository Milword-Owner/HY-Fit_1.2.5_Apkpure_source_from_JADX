package com.baidubce;

public class BceServiceException extends BceClientException {
    private static final long serialVersionUID = 1483785729559154396L;
    private String errorCode;
    private String errorMessage;
    private ErrorType errorType = ErrorType.Unknown;
    private String requestId;
    private int statusCode;

    public enum ErrorType {
        Client,
        Service,
        Unknown
    }

    public BceServiceException(String str) {
        super((String) null);
        this.errorMessage = str;
    }

    public BceServiceException(String str, Exception exc) {
        super((String) null, exc);
        this.errorMessage = str;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setErrorCode(String str) {
        this.errorCode = str;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorType(ErrorType errorType2) {
        this.errorType = errorType2;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public void setErrorMessage(String str) {
        this.errorMessage = str;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getMessage() {
        return getErrorMessage() + " (Status Code: " + getStatusCode() + "; Error Code: " + getErrorCode() + "; Request ID: " + getRequestId() + ")";
    }
}
