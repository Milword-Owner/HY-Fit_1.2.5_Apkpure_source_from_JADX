package org.hamcrest.internal;

import java.lang.reflect.Method;

public class ReflectiveTypeFinder {
    private final int expectedNumberOfParameters;
    private final String methodName;
    private final int typedParameter;

    public ReflectiveTypeFinder(String str, int i, int i2) {
        this.methodName = str;
        this.expectedNumberOfParameters = i;
        this.typedParameter = i2;
    }

    public Class<?> findExpectedType(Class<?> cls) {
        while (true) {
            Class<? super Object> cls2 = cls;
            if (cls2 != Object.class) {
                for (Method method : cls2.getDeclaredMethods()) {
                    if (canObtainExpectedTypeFrom(method)) {
                        return expectedTypeFrom(method);
                    }
                }
                cls2 = cls2.getSuperclass();
            } else {
                throw new Error("Cannot determine correct type for " + this.methodName + "() method.");
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean canObtainExpectedTypeFrom(Method method) {
        return method.getName().equals(this.methodName) && method.getParameterTypes().length == this.expectedNumberOfParameters && !method.isSynthetic();
    }

    /* access modifiers changed from: protected */
    public Class<?> expectedTypeFrom(Method method) {
        return method.getParameterTypes()[this.typedParameter];
    }
}
