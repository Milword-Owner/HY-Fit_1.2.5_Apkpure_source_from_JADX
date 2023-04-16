package junit.runner;

import com.facebook.internal.ServerProtocol;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.NumberFormat;
import java.util.Properties;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestListener;
import junit.framework.TestSuite;

public abstract class BaseTestRunner implements TestListener {
    public static final String SUITE_METHODNAME = "suite";
    private static Properties fPreferences = null;
    static boolean fgFilterStack = true;
    static int fgMaxMessageLength = getPreference("maxmessage", fgMaxMessageLength);
    boolean fLoading = true;

    /* access modifiers changed from: protected */
    public void clearStatus() {
    }

    /* access modifiers changed from: protected */
    public abstract void runFailed(String str);

    public abstract void testEnded(String str);

    public abstract void testFailed(int i, Test test, Throwable th);

    public abstract void testStarted(String str);

    public synchronized void startTest(Test test) {
        testStarted(test.toString());
    }

    protected static void setPreferences(Properties properties) {
        fPreferences = properties;
    }

    protected static Properties getPreferences() {
        if (fPreferences == null) {
            fPreferences = new Properties();
            fPreferences.put("loading", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            fPreferences.put("filterstack", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
            readPreferences();
        }
        return fPreferences;
    }

    public static void savePreferences() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(getPreferencesFile());
        try {
            getPreferences().store(fileOutputStream, "");
        } finally {
            fileOutputStream.close();
        }
    }

    public static void setPreference(String str, String str2) {
        getPreferences().put(str, str2);
    }

    public synchronized void endTest(Test test) {
        testEnded(test.toString());
    }

    public synchronized void addError(Test test, Throwable th) {
        testFailed(1, test, th);
    }

    public synchronized void addFailure(Test test, AssertionFailedError assertionFailedError) {
        testFailed(2, test, assertionFailedError);
    }

    public Test getTest(String str) {
        if (str.length() <= 0) {
            clearStatus();
            return null;
        }
        try {
            Class<?> loadSuiteClass = loadSuiteClass(str);
            try {
                Method method = loadSuiteClass.getMethod(SUITE_METHODNAME, new Class[0]);
                if (!Modifier.isStatic(method.getModifiers())) {
                    runFailed("Suite() method must be static");
                    return null;
                }
                try {
                    Test test = (Test) method.invoke((Object) null, new Object[0]);
                    if (test == null) {
                        return test;
                    }
                    clearStatus();
                    return test;
                } catch (InvocationTargetException e) {
                    runFailed("Failed to invoke suite():" + e.getTargetException().toString());
                    return null;
                } catch (IllegalAccessException e2) {
                    runFailed("Failed to invoke suite():" + e2.toString());
                    return null;
                }
            } catch (Exception unused) {
                clearStatus();
                return new TestSuite(loadSuiteClass);
            }
        } catch (ClassNotFoundException e3) {
            String message = e3.getMessage();
            if (message != null) {
                str = message;
            }
            runFailed("Class not found \"" + str + "\"");
            return null;
        } catch (Exception e4) {
            runFailed("Error: " + e4.toString());
            return null;
        }
    }

    public String elapsedTimeAsString(long j) {
        NumberFormat instance = NumberFormat.getInstance();
        double d = (double) j;
        Double.isNaN(d);
        return instance.format(d / 1000.0d);
    }

    /* access modifiers changed from: protected */
    public String processArguments(String[] strArr) {
        String str = null;
        int i = 0;
        while (i < strArr.length) {
            if (strArr[i].equals("-noloading")) {
                setLoading(false);
            } else if (strArr[i].equals("-nofilterstack")) {
                fgFilterStack = false;
            } else if (strArr[i].equals("-c")) {
                i++;
                if (strArr.length > i) {
                    str = extractClassName(strArr[i]);
                } else {
                    System.out.println("Missing Test class name");
                }
            } else {
                str = strArr[i];
            }
            i++;
        }
        return str;
    }

    public void setLoading(boolean z) {
        this.fLoading = z;
    }

    public String extractClassName(String str) {
        return str.startsWith("Default package for") ? str.substring(str.lastIndexOf(".") + 1) : str;
    }

    public static String truncate(String str) {
        if (fgMaxMessageLength == -1 || str.length() <= fgMaxMessageLength) {
            return str;
        }
        return str.substring(0, fgMaxMessageLength) + "...";
    }

    /* access modifiers changed from: protected */
    public Class<?> loadSuiteClass(String str) throws ClassNotFoundException {
        return Class.forName(str);
    }

    /* access modifiers changed from: protected */
    public boolean useReloadingTestSuiteLoader() {
        return getPreference("loading").equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE) && this.fLoading;
    }

    private static File getPreferencesFile() {
        return new File(System.getProperty("user.home"), "junit.properties");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0029 A[SYNTHETIC, Splitter:B:11:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void readPreferences() {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ IOException -> 0x002d, all -> 0x0023 }
            java.io.File r2 = getPreferencesFile()     // Catch:{ IOException -> 0x002d, all -> 0x0023 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x002d, all -> 0x0023 }
            java.util.Properties r0 = new java.util.Properties     // Catch:{ IOException -> 0x002e, all -> 0x0021 }
            java.util.Properties r2 = getPreferences()     // Catch:{ IOException -> 0x002e, all -> 0x0021 }
            r0.<init>(r2)     // Catch:{ IOException -> 0x002e, all -> 0x0021 }
            setPreferences(r0)     // Catch:{ IOException -> 0x002e, all -> 0x0021 }
            java.util.Properties r0 = getPreferences()     // Catch:{ IOException -> 0x002e, all -> 0x0021 }
            r0.load(r1)     // Catch:{ IOException -> 0x002e, all -> 0x0021 }
        L_0x001d:
            r1.close()     // Catch:{ IOException -> 0x0031 }
            goto L_0x0031
        L_0x0021:
            r0 = move-exception
            goto L_0x0027
        L_0x0023:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
        L_0x0027:
            if (r1 == 0) goto L_0x002c
            r1.close()     // Catch:{ IOException -> 0x002c }
        L_0x002c:
            throw r0
        L_0x002d:
            r1 = r0
        L_0x002e:
            if (r1 == 0) goto L_0x0031
            goto L_0x001d
        L_0x0031:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: junit.runner.BaseTestRunner.readPreferences():void");
    }

    public static String getPreference(String str) {
        return getPreferences().getProperty(str);
    }

    public static int getPreference(String str, int i) {
        String preference = getPreference(str);
        if (preference == null) {
            return i;
        }
        try {
            return Integer.parseInt(preference);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static String getFilteredTrace(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return getFilteredTrace(stringWriter.toString());
    }

    public static String getFilteredTrace(String str) {
        if (showStackRaw()) {
            return str;
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
        while (true) {
            try {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return stringWriter.toString();
                }
                if (!filterLine(readLine)) {
                    printWriter.println(readLine);
                }
            } catch (Exception unused) {
                return str;
            }
        }
    }

    protected static boolean showStackRaw() {
        return !getPreference("filterstack").equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE) || !fgFilterStack;
    }

    static boolean filterLine(String str) {
        String[] strArr = {"junit.framework.TestCase", "junit.framework.TestResult", "junit.framework.TestSuite", "junit.framework.Assert.", "junit.swingui.TestRunner", "junit.awtui.TestRunner", "junit.textui.TestRunner", "java.lang.reflect.Method.invoke("};
        for (String indexOf : strArr) {
            if (str.indexOf(indexOf) > 0) {
                return true;
            }
        }
        return false;
    }
}
