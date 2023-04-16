package org.junit.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.junit.Assert;

public abstract class ComparisonCriteria {
    /* access modifiers changed from: protected */
    public abstract void assertElementsEqual(Object obj, Object obj2);

    public void arrayEquals(String str, Object obj, Object obj2) throws ArrayComparisonFailure {
        String str2;
        if (obj != obj2) {
            if (!Arrays.deepEquals(new Object[]{obj}, new Object[]{obj2})) {
                if (str == null) {
                    str2 = "";
                } else {
                    str2 = str + ": ";
                }
                int assertArraysAreSameLength = assertArraysAreSameLength(obj, obj2, str2);
                for (int i = 0; i < assertArraysAreSameLength; i++) {
                    Object obj3 = Array.get(obj, i);
                    Object obj4 = Array.get(obj2, i);
                    if (!isArray(obj3) || !isArray(obj4)) {
                        try {
                            assertElementsEqual(obj3, obj4);
                        } catch (AssertionError e) {
                            throw new ArrayComparisonFailure(str2, e, i);
                        }
                    } else {
                        try {
                            arrayEquals(str, obj3, obj4);
                        } catch (ArrayComparisonFailure e2) {
                            e2.addDimension(i);
                            throw e2;
                        }
                    }
                }
            }
        }
    }

    private boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray();
    }

    private int assertArraysAreSameLength(Object obj, Object obj2, String str) {
        if (obj == null) {
            Assert.fail(str + "expected array was null");
        }
        if (obj2 == null) {
            Assert.fail(str + "actual array was null");
        }
        int length = Array.getLength(obj2);
        int length2 = Array.getLength(obj);
        if (length != length2) {
            Assert.fail(str + "array lengths differed, expected.length=" + length2 + " actual.length=" + length);
        }
        return length2;
    }
}
