package p005cn.sharesdk.framework.utils.QRCodeUtil;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.ReaderException */
public abstract class ReaderException extends Exception {
    protected static final StackTraceElement[] NO_TRACE = new StackTraceElement[0];
    protected static final boolean isStackTrace = (System.getProperty("surefire.test.class.path") != null);

    ReaderException() {
    }

    ReaderException(Throwable th) {
        super(th);
    }

    public final synchronized Throwable fillInStackTrace() {
        return null;
    }
}
