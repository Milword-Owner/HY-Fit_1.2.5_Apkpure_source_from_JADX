package p040uk.p041co.senab2.photoview2.log;

/* renamed from: uk.co.senab2.photoview2.log.LogManager */
public final class LogManager {
    private static Logger logger = new LoggerDefault();

    public static void setLogger(Logger logger2) {
        logger = logger2;
    }

    public static Logger getLogger() {
        return logger;
    }
}
