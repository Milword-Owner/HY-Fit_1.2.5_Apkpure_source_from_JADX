package org.litepal.exceptions;

public class DataSupportException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public DataSupportException(String str) {
        super(str);
    }

    public DataSupportException(String str, Throwable th) {
        super(str, th);
    }
}
