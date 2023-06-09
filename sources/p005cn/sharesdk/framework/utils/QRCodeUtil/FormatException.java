package p005cn.sharesdk.framework.utils.QRCodeUtil;

/* renamed from: cn.sharesdk.framework.utils.QRCodeUtil.FormatException */
public final class FormatException extends ReaderException {
    private static final FormatException INSTANCE = new FormatException();

    static {
        INSTANCE.setStackTrace(NO_TRACE);
    }

    private FormatException() {
    }

    private FormatException(Throwable th) {
        super(th);
    }

    public static FormatException getFormatInstance() {
        return isStackTrace ? new FormatException() : INSTANCE;
    }

    public static FormatException getFormatInstance(Throwable th) {
        return isStackTrace ? new FormatException(th) : INSTANCE;
    }
}
