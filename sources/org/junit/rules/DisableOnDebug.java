package org.junit.rules;

import java.lang.management.ManagementFactory;
import java.util.List;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class DisableOnDebug implements TestRule {
    private final boolean debugging;
    private final TestRule rule;

    public DisableOnDebug(TestRule testRule) {
        this(testRule, ManagementFactory.getRuntimeMXBean().getInputArguments());
    }

    DisableOnDebug(TestRule testRule, List<String> list) {
        this.rule = testRule;
        this.debugging = isDebugging(list);
    }

    public Statement apply(Statement statement, Description description) {
        if (this.debugging) {
            return statement;
        }
        return this.rule.apply(statement, description);
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isDebugging(java.util.List<java.lang.String> r3) {
        /*
            java.util.Iterator r3 = r3.iterator()
        L_0x0004:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L_0x0023
            java.lang.Object r0 = r3.next()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.String r1 = "-Xdebug"
            boolean r1 = r1.equals(r0)
            r2 = 1
            if (r1 == 0) goto L_0x001a
            return r2
        L_0x001a:
            java.lang.String r1 = "-agentlib:jdwp"
            boolean r0 = r0.startsWith(r1)
            if (r0 == 0) goto L_0x0004
            return r2
        L_0x0023:
            r3 = 0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.junit.rules.DisableOnDebug.isDebugging(java.util.List):boolean");
    }

    public boolean isDebugging() {
        return this.debugging;
    }
}
