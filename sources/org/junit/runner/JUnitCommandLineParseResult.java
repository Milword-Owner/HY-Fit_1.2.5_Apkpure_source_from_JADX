package org.junit.runner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.internal.Classes;
import org.junit.runner.FilterFactory;
import org.junit.runners.model.InitializationError;

class JUnitCommandLineParseResult {
    private final List<Class<?>> classes = new ArrayList();
    private final List<String> filterSpecs = new ArrayList();
    private final List<Throwable> parserErrors = new ArrayList();

    JUnitCommandLineParseResult() {
    }

    public List<String> getFilterSpecs() {
        return Collections.unmodifiableList(this.filterSpecs);
    }

    public List<Class<?>> getClasses() {
        return Collections.unmodifiableList(this.classes);
    }

    public static JUnitCommandLineParseResult parse(String[] strArr) {
        JUnitCommandLineParseResult jUnitCommandLineParseResult = new JUnitCommandLineParseResult();
        jUnitCommandLineParseResult.parseArgs(strArr);
        return jUnitCommandLineParseResult;
    }

    private void parseArgs(String[] strArr) {
        parseParameters(parseOptions(strArr));
    }

    /* access modifiers changed from: package-private */
    public String[] parseOptions(String... strArr) {
        String str;
        int i = 0;
        while (true) {
            if (i == strArr.length) {
                break;
            }
            String str2 = strArr[i];
            if (str2.equals("--")) {
                return copyArray(strArr, i + 1, strArr.length);
            }
            if (!str2.startsWith("--")) {
                return copyArray(strArr, i, strArr.length);
            }
            if (str2.startsWith("--filter=") || str2.equals("--filter")) {
                if (str2.equals("--filter")) {
                    i++;
                    if (i >= strArr.length) {
                        List<Throwable> list = this.parserErrors;
                        list.add(new CommandLineParserError(str2 + " value not specified"));
                        break;
                    }
                    str = strArr[i];
                } else {
                    str = str2.substring(str2.indexOf(61) + 1);
                }
                this.filterSpecs.add(str);
            } else {
                List<Throwable> list2 = this.parserErrors;
                list2.add(new CommandLineParserError("JUnit knows nothing about the " + str2 + " option"));
            }
            i++;
        }
        return new String[0];
    }

    private String[] copyArray(String[] strArr, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        while (i != i2) {
            arrayList.add(strArr[i]);
            i++;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* access modifiers changed from: package-private */
    public void parseParameters(String[] strArr) {
        for (String str : strArr) {
            try {
                this.classes.add(Classes.getClass(str));
            } catch (ClassNotFoundException e) {
                this.parserErrors.add(new IllegalArgumentException("Could not find class [" + str + "]", e));
            }
        }
    }

    private Request errorReport(Throwable th) {
        return Request.errorReport(JUnitCommandLineParseResult.class, th);
    }

    public Request createRequest(Computer computer) {
        if (!this.parserErrors.isEmpty()) {
            return errorReport(new InitializationError(this.parserErrors));
        }
        List<Class<?>> list = this.classes;
        return applyFilterSpecs(Request.classes(computer, (Class[]) list.toArray(new Class[list.size()])));
    }

    private Request applyFilterSpecs(Request request) {
        try {
            for (String createFilterFromFilterSpec : this.filterSpecs) {
                request = request.filterWith(FilterFactories.createFilterFromFilterSpec(request, createFilterFromFilterSpec));
            }
            return request;
        } catch (FilterFactory.FilterNotCreatedException e) {
            return errorReport(e);
        }
    }

    public static class CommandLineParserError extends Exception {
        private static final long serialVersionUID = 1;

        public CommandLineParserError(String str) {
            super(str);
        }
    }
}
