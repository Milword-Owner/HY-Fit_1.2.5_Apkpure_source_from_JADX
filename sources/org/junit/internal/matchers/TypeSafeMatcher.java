package org.junit.internal.matchers;

import java.lang.reflect.Method;
import org.hamcrest.BaseMatcher;
import org.junit.internal.MethodSorter;

@Deprecated
public abstract class TypeSafeMatcher<T> extends BaseMatcher<T> {
    private Class<?> expectedType;

    public abstract boolean matchesSafely(T t);

    protected TypeSafeMatcher() {
        this.expectedType = findExpectedType(getClass());
    }

    private static Class<?> findExpectedType(Class<?> cls) {
        while (true) {
            Class<? super Object> cls2 = cls;
            if (cls2 != Object.class) {
                for (Method method : MethodSorter.getDeclaredMethods(cls2)) {
                    if (isMatchesSafelyMethod(method)) {
                        return method.getParameterTypes()[0];
                    }
                }
                cls2 = cls2.getSuperclass();
            } else {
                throw new Error("Cannot determine correct type for matchesSafely() method.");
            }
        }
    }

    private static boolean isMatchesSafelyMethod(Method method) {
        return method.getName().equals("matchesSafely") && method.getParameterTypes().length == 1 && !method.isSynthetic();
    }

    protected TypeSafeMatcher(Class<T> cls) {
        this.expectedType = cls;
    }

    public final boolean matches(Object obj) {
        return obj != null && this.expectedType.isInstance(obj) && matchesSafely(obj);
    }
}
