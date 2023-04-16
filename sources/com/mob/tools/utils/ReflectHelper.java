package com.mob.tools.utils;

import androidx.exifinterface.media.ExifInterface;
import com.mob.tools.gui.CachePool;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReflectHelper {
    private static CachePool<String, Constructor<?>> cachedConstr = new CachePool<>(5);
    private static CachePool<String, Method> cachedMethod = new CachePool<>(25);
    private static HashMap<String, Class<?>> classMap = new HashMap<>();
    private static HashMap<Class<?>, String> nameMap = new HashMap<>();
    private static HashSet<String> packageSet = new HashSet<>();

    public interface ReflectRunnable<ArgType, RetType> {
        RetType run(ArgType argtype);
    }

    static {
        packageSet.add("java.lang");
        packageSet.add("java.io");
        packageSet.add("java.nio");
        packageSet.add("java.net");
        packageSet.add("java.util");
        packageSet.add("com.mob.tools");
        packageSet.add("com.mob.tools.gui");
        packageSet.add("com.mob.tools.log");
        packageSet.add("com.mob.tools.network");
        packageSet.add("com.mob.tools.utils");
        classMap.put("double", Double.TYPE);
        classMap.put("float", Float.TYPE);
        classMap.put("long", Long.TYPE);
        classMap.put("int", Integer.TYPE);
        classMap.put("short", Short.TYPE);
        classMap.put("byte", Byte.TYPE);
        classMap.put("char", Character.TYPE);
        classMap.put("boolean", Boolean.TYPE);
        classMap.put("Object", Object.class);
        classMap.put("String", String.class);
        classMap.put("Thread", Thread.class);
        classMap.put("Runnable", Runnable.class);
        classMap.put("System", System.class);
        classMap.put("double", Double.class);
        classMap.put("Float", Float.class);
        classMap.put("Long", Long.class);
        classMap.put("Integer", Integer.class);
        classMap.put("Short", Short.class);
        classMap.put("Byte", Byte.class);
        classMap.put("Character", Character.class);
        classMap.put("Boolean", Boolean.class);
        for (Map.Entry next : classMap.entrySet()) {
            nameMap.put(next.getValue(), next.getKey());
        }
    }

    public static String importClass(String str) throws Throwable {
        return importClass((String) null, str);
    }

    public static synchronized String importClass(String str, String str2) throws Throwable {
        synchronized (ReflectHelper.class) {
            if (str2.endsWith(".*")) {
                packageSet.add(str2.substring(0, str2.length() - 2));
                return "*";
            }
            Class<?> cls = Class.forName(str2);
            if (str == null) {
                str = cls.getSimpleName();
            }
            if (classMap.containsKey(str)) {
                nameMap.remove(classMap.get(str));
            }
            classMap.put(str, cls);
            nameMap.put(cls, str);
            return str;
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(7:8|9|10|11|12|(1:20)|6) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0040 A[EDGE_INSN: B:20:0x0040->B:14:0x0040 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized java.lang.Class<?> getImportedClass(java.lang.String r4) {
        /*
            java.lang.Class<com.mob.tools.utils.ReflectHelper> r0 = com.mob.tools.utils.ReflectHelper.class
            monitor-enter(r0)
            java.util.HashMap<java.lang.String, java.lang.Class<?>> r1 = classMap     // Catch:{ all -> 0x0042 }
            java.lang.Object r1 = r1.get(r4)     // Catch:{ all -> 0x0042 }
            java.lang.Class r1 = (java.lang.Class) r1     // Catch:{ all -> 0x0042 }
            if (r1 != 0) goto L_0x0040
            java.util.HashSet<java.lang.String> r2 = packageSet     // Catch:{ all -> 0x0042 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x0042 }
        L_0x0013:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x0042 }
            if (r3 == 0) goto L_0x0040
            java.lang.Object r1 = r2.next()     // Catch:{ all -> 0x0042 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x0042 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Throwable -> 0x0036 }
            r3.<init>()     // Catch:{ Throwable -> 0x0036 }
            r3.append(r1)     // Catch:{ Throwable -> 0x0036 }
            java.lang.String r1 = "."
            r3.append(r1)     // Catch:{ Throwable -> 0x0036 }
            r3.append(r4)     // Catch:{ Throwable -> 0x0036 }
            java.lang.String r1 = r3.toString()     // Catch:{ Throwable -> 0x0036 }
            importClass(r1)     // Catch:{ Throwable -> 0x0036 }
        L_0x0036:
            java.util.HashMap<java.lang.String, java.lang.Class<?>> r1 = classMap     // Catch:{ all -> 0x0042 }
            java.lang.Object r1 = r1.get(r4)     // Catch:{ all -> 0x0042 }
            java.lang.Class r1 = (java.lang.Class) r1     // Catch:{ all -> 0x0042 }
            if (r1 == 0) goto L_0x0013
        L_0x0040:
            monitor-exit(r0)
            return r1
        L_0x0042:
            r4 = move-exception
            monitor-exit(r0)
            goto L_0x0046
        L_0x0045:
            throw r4
        L_0x0046:
            goto L_0x0045
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ReflectHelper.getImportedClass(java.lang.String):java.lang.Class");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Class<?>[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Class<?>[] getTypes(java.lang.Object[] r3) {
        /*
            int r0 = r3.length
            java.lang.Class[] r0 = new java.lang.Class[r0]
            r1 = 0
        L_0x0004:
            int r2 = r3.length
            if (r1 >= r2) goto L_0x0023
            r2 = r3[r1]
            boolean r2 = r2 instanceof android.content.BroadcastReceiver
            if (r2 == 0) goto L_0x0012
            java.lang.Class<android.content.BroadcastReceiver> r2 = android.content.BroadcastReceiver.class
            r0[r1] = r2
            goto L_0x0020
        L_0x0012:
            r2 = r3[r1]
            if (r2 != 0) goto L_0x0018
            r2 = 0
            goto L_0x001e
        L_0x0018:
            r2 = r3[r1]
            java.lang.Class r2 = r2.getClass()
        L_0x001e:
            r0[r1] = r2
        L_0x0020:
            int r1 = r1 + 1
            goto L_0x0004
        L_0x0023:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ReflectHelper.getTypes(java.lang.Object[]):java.lang.Class[]");
    }

    private static boolean primitiveEquals(Class<?> cls, Class<?> cls2) {
        return (cls == Byte.TYPE && cls2 == Byte.class) || (cls == Short.TYPE && (cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Character.TYPE && (cls2 == Character.class || cls2 == Short.class || cls2 == Byte.class)) || ((cls == Integer.TYPE && (cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Long.TYPE && (cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Float.TYPE && (cls2 == Float.class || cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Double.TYPE && (cls2 == Double.class || cls2 == Float.class || cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || (cls == Boolean.TYPE && cls2 == Boolean.class))))));
    }

    private static boolean matchParams(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr2.length; i++) {
            if (clsArr2[i] != null && !primitiveEquals(clsArr[i], clsArr2[i]) && !clsArr[i].isAssignableFrom(clsArr2[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean tryMatchParams(Class<?>[] clsArr, Class<?>[] clsArr2) {
        boolean z;
        if (clsArr.length - clsArr2.length != 1) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i < clsArr2.length) {
                if (clsArr2[i] != null && !primitiveEquals(clsArr[i], clsArr2[i]) && !clsArr[i].isAssignableFrom(clsArr2[i])) {
                    z = false;
                    break;
                }
                i++;
            } else {
                z = true;
                break;
            }
        }
        if (!z || !clsArr[clsArr.length - 1].isArray()) {
            return false;
        }
        return true;
    }

    public static Object newInstance(String str, Object... objArr) throws Throwable {
        try {
            return onNewInstance(str, objArr);
        } catch (Throwable th) {
            if (th instanceof NoSuchMethodException) {
                throw th;
            }
            throw new Throwable("className: " + str + ", methodName: <init>", th);
        }
    }

    private static Object onNewInstance(String str, Object... objArr) throws Throwable {
        boolean z;
        if (str.startsWith("[")) {
            return newArray(str, objArr);
        }
        Class<?> importedClass = getImportedClass(str);
        String str2 = importedClass.getName() + "#" + objArr.length;
        Constructor constructor = cachedConstr.get(str2);
        Class[] types = getTypes(objArr);
        if (constructor == null || !matchParams(constructor.getParameterTypes(), types)) {
            Constructor[] declaredConstructors = importedClass.getDeclaredConstructors();
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Constructor constructor2 : declaredConstructors) {
                Class[] parameterTypes = constructor2.getParameterTypes();
                if (matchParams(parameterTypes, types)) {
                    cachedConstr.put(str2, constructor2);
                    constructor2.setAccessible(true);
                    return constructor2.newInstance(objArr);
                }
                if (parameterTypes.length > 0 && parameterTypes[parameterTypes.length - 1].isArray() && types.length >= parameterTypes.length - 1) {
                    arrayList.add(constructor2);
                    arrayList2.add(parameterTypes);
                }
            }
            for (int i = 0; i < arrayList2.size(); i++) {
                Class[] clsArr = (Class[]) arrayList2.get(i);
                Class<?> componentType = clsArr[clsArr.length - 1].getComponentType();
                if (tryMatchParams(clsArr, types)) {
                    Object[] objArr2 = new Object[(objArr.length + 1)];
                    System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
                    objArr2[objArr.length] = Array.newInstance(componentType, 0);
                    Constructor constructor3 = (Constructor) arrayList.get(i);
                    constructor3.setAccessible(true);
                    return constructor3.newInstance(objArr);
                }
                int length = clsArr.length - 1;
                while (true) {
                    if (length >= types.length) {
                        z = true;
                        break;
                    } else if (!types[length].equals(componentType)) {
                        z = false;
                        break;
                    } else {
                        length++;
                    }
                }
                if (z) {
                    int length2 = (types.length - clsArr.length) + 1;
                    Object newInstance = Array.newInstance(componentType, length2);
                    for (int i2 = 0; i2 < length2; i2++) {
                        Array.set(newInstance, i2, objArr[(clsArr.length - 1) + i2]);
                    }
                    Object[] objArr3 = new Object[(objArr.length + 1)];
                    System.arraycopy(objArr, 0, objArr3, 0, objArr.length);
                    objArr3[objArr.length] = newInstance;
                    Constructor constructor4 = (Constructor) arrayList.get(i);
                    constructor4.setAccessible(true);
                    return constructor4.newInstance(objArr);
                }
            }
            throw new NoSuchMethodException("className: " + str + ", methodName: <init>");
        }
        constructor.setAccessible(true);
        return constructor.newInstance(objArr);
    }

    private static Object newArray(String str, Object... objArr) throws Throwable {
        Class<?> cls;
        int i = 0;
        int i2 = 0;
        String str2 = str;
        while (str2.startsWith("[")) {
            i2++;
            str2 = str2.substring(1);
        }
        int[] iArr = null;
        if (i2 == objArr.length) {
            int[] iArr2 = new int[i2];
            while (i < i2) {
                try {
                    iArr2[i] = Integer.parseInt(String.valueOf(objArr[i]));
                    i++;
                } catch (Throwable unused) {
                }
            }
            iArr = iArr2;
        }
        if (iArr != null) {
            if ("B".equals(str2)) {
                cls = Byte.TYPE;
            } else if (ExifInterface.LATITUDE_SOUTH.equals(str2)) {
                cls = Short.TYPE;
            } else if ("I".equals(str2)) {
                cls = Integer.TYPE;
            } else if ("J".equals(str2)) {
                cls = Long.TYPE;
            } else if ("F".equals(str2)) {
                cls = Float.TYPE;
            } else if ("D".equals(str2)) {
                cls = Double.TYPE;
            } else if ("Z".equals(str2)) {
                cls = Boolean.TYPE;
            } else if ("C".equals(str2)) {
                cls = Character.TYPE;
            } else {
                cls = getImportedClass(str2);
            }
            if (cls != null) {
                return Array.newInstance(cls, iArr);
            }
        }
        throw new NoSuchMethodException("className: [" + str + ", methodName: <init>");
    }

    public static <T> T invokeStaticMethod(String str, String str2, Object[] objArr, Class<?>[] clsArr) throws Throwable {
        return invokeMethod(str, (Object) null, str2, objArr, clsArr);
    }

    public static <T> T invokeStaticMethod(String str, String str2, Object... objArr) throws Throwable {
        try {
            return invokeMethod(str, (Object) null, str2, objArr);
        } catch (Throwable th) {
            if (th instanceof NoSuchMethodException) {
                throw th;
            }
            throw new Throwable("className: " + str + ", methodName: " + str2, th);
        }
    }

    private static <T> T invokeMethod(String str, Object obj, String str2, Object[] objArr, Class<?>[] clsArr) throws Throwable {
        Class<?> cls;
        if (objArr == null) {
            objArr = new Object[0];
        }
        if (clsArr == null) {
            clsArr = new Class[0];
        }
        if (obj == null) {
            cls = getImportedClass(str);
        } else {
            cls = obj.getClass();
        }
        String str3 = cls.getName() + "#" + str2 + "#" + objArr.length;
        Method method = cachedMethod.get(str3);
        if (method != null) {
            method.setAccessible(true);
            if (method.getReturnType() != Void.TYPE) {
                return method.invoke(obj, objArr);
            }
            method.invoke(obj, objArr);
            return null;
        }
        while (cls != null) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str2, clsArr);
                cachedMethod.put(str3, declaredMethod);
                declaredMethod.setAccessible(true);
                if (declaredMethod.getReturnType() != Void.TYPE) {
                    return declaredMethod.invoke(obj, objArr);
                }
                declaredMethod.invoke(obj, objArr);
                return null;
            } catch (Throwable unused) {
                cls = cls.getSuperclass();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("className: ");
        Object obj2 = str;
        if (obj != null) {
            obj2 = obj.getClass();
        }
        sb.append(obj2);
        sb.append(", methodName: ");
        sb.append(str2);
        throw new NoSuchMethodException(sb.toString());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v0, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v1, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v10, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.Class<?>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Class<?>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> T invokeMethod(java.lang.String r8, java.lang.Object r9, java.lang.String r10, java.lang.Object... r11) throws java.lang.Throwable {
        /*
            if (r9 != 0) goto L_0x0007
            java.lang.Class r0 = getImportedClass(r8)
            goto L_0x000b
        L_0x0007:
            java.lang.Class r0 = r9.getClass()
        L_0x000b:
            java.lang.String r1 = "getMethod"
            boolean r1 = r10.equals(r1)
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x0034
            if (r11 == 0) goto L_0x0034
            int r1 = r11.length
            if (r1 != r2) goto L_0x0034
            java.lang.Class[] r1 = new java.lang.Class[r2]
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            r1[r3] = r2
            java.lang.Class<java.lang.Class[]> r2 = java.lang.Class[].class
            r1[r4] = r2
            r2 = r11[r4]
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            if (r2 != r5) goto L_0x0078
            java.lang.Class[] r2 = new java.lang.Class[r4]
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r2[r3] = r5
            r11[r4] = r2
            goto L_0x0078
        L_0x0034:
            java.lang.String r1 = "getDeviceId"
            boolean r1 = r10.equals(r1)
            if (r1 == 0) goto L_0x0048
            if (r11 == 0) goto L_0x0048
            int r1 = r11.length
            if (r1 != r4) goto L_0x0048
            java.lang.Class[] r1 = new java.lang.Class[r4]
            java.lang.Class r2 = java.lang.Integer.TYPE
            r1[r3] = r2
            goto L_0x0078
        L_0x0048:
            java.lang.String r1 = "invoke"
            boolean r1 = r10.equals(r1)
            if (r1 == 0) goto L_0x0060
            if (r11 == 0) goto L_0x0060
            int r1 = r11.length
            if (r1 != r2) goto L_0x0060
            java.lang.Class[] r1 = new java.lang.Class[r2]
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r1[r3] = r2
            java.lang.Class<java.lang.Object[]> r2 = java.lang.Object[].class
            r1[r4] = r2
            goto L_0x0078
        L_0x0060:
            java.lang.String r1 = "setAccessible"
            boolean r1 = r10.equals(r1)
            if (r1 == 0) goto L_0x0074
            if (r11 == 0) goto L_0x0074
            int r1 = r11.length
            if (r1 != r4) goto L_0x0074
            java.lang.Class[] r1 = new java.lang.Class[r4]
            java.lang.Class r2 = java.lang.Boolean.TYPE
            r1[r3] = r2
            goto L_0x0078
        L_0x0074:
            java.lang.Class[] r1 = getTypes(r11)
        L_0x0078:
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            int r5 = r1.length
            r6 = 0
        L_0x007f:
            if (r6 >= r5) goto L_0x0092
            r7 = r1[r6]
            if (r7 != 0) goto L_0x0088
            java.lang.String r7 = ""
            goto L_0x008c
        L_0x0088:
            java.lang.String r7 = r7.getName()
        L_0x008c:
            r2.append(r7)
            int r6 = r6 + 1
            goto L_0x007f
        L_0x0092:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = r0.getName()
            r5.append(r6)
            java.lang.String r6 = "#"
            r5.append(r6)
            r5.append(r10)
            r5.append(r6)
            int r6 = r11.length
            r5.append(r6)
            java.lang.String r2 = r2.toString()
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            com.mob.tools.gui.CachePool<java.lang.String, java.lang.reflect.Method> r5 = cachedMethod
            java.lang.Object r5 = r5.get(r2)
            java.lang.reflect.Method r5 = (java.lang.reflect.Method) r5
            r6 = 0
            if (r5 == 0) goto L_0x00f2
            int r7 = r5.getModifiers()
            boolean r7 = java.lang.reflect.Modifier.isStatic(r7)
            if (r9 != 0) goto L_0x00cf
            r3 = r7
            goto L_0x00d2
        L_0x00cf:
            if (r7 != 0) goto L_0x00d2
            r3 = 1
        L_0x00d2:
            if (r3 == 0) goto L_0x00f2
            java.lang.Class[] r3 = r5.getParameterTypes()
            boolean r3 = matchParams(r3, r1)
            if (r3 == 0) goto L_0x00f2
            r5.setAccessible(r4)
            java.lang.Class r8 = r5.getReturnType()
            java.lang.Class r10 = java.lang.Void.TYPE
            if (r8 != r10) goto L_0x00ed
            r5.invoke(r9, r11)
            return r6
        L_0x00ed:
            java.lang.Object r8 = r5.invoke(r9, r11)
            return r8
        L_0x00f2:
            if (r0 == 0) goto L_0x0116
            java.lang.reflect.Method r3 = r0.getDeclaredMethod(r10, r1)     // Catch:{ Throwable -> 0x0111 }
            com.mob.tools.gui.CachePool<java.lang.String, java.lang.reflect.Method> r5 = cachedMethod     // Catch:{ Throwable -> 0x0111 }
            r5.put(r2, r3)     // Catch:{ Throwable -> 0x0111 }
            r3.setAccessible(r4)     // Catch:{ Throwable -> 0x0111 }
            java.lang.Class r5 = r3.getReturnType()     // Catch:{ Throwable -> 0x0111 }
            java.lang.Class r7 = java.lang.Void.TYPE     // Catch:{ Throwable -> 0x0111 }
            if (r5 != r7) goto L_0x010c
            r3.invoke(r9, r11)     // Catch:{ Throwable -> 0x0111 }
            return r6
        L_0x010c:
            java.lang.Object r8 = r3.invoke(r9, r11)     // Catch:{ Throwable -> 0x0111 }
            return r8
        L_0x0111:
            java.lang.Class r0 = r0.getSuperclass()
            goto L_0x00f2
        L_0x0116:
            java.lang.NoSuchMethodException r11 = new java.lang.NoSuchMethodException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "className: "
            r0.append(r1)
            if (r9 != 0) goto L_0x0125
            goto L_0x0129
        L_0x0125:
            java.lang.Class r8 = r9.getClass()
        L_0x0129:
            r0.append(r8)
            java.lang.String r8 = ", methodName: "
            r0.append(r8)
            r0.append(r10)
            java.lang.String r8 = r0.toString()
            r11.<init>(r8)
            goto L_0x013d
        L_0x013c:
            throw r11
        L_0x013d:
            goto L_0x013c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.ReflectHelper.invokeMethod(java.lang.String, java.lang.Object, java.lang.String, java.lang.Object[]):java.lang.Object");
    }

    public static <T> T invokeInstanceMethod(Object obj, String str, Object[] objArr, Class<?>[] clsArr) throws Throwable {
        return invokeMethod((String) null, obj, str, objArr, clsArr);
    }

    public static <T> T invokeInstanceMethod(Object obj, String str, Object... objArr) throws Throwable {
        Class<?> cls = null;
        try {
            return invokeMethod((String) null, obj, str, objArr);
        } catch (Throwable th) {
            if (!(th instanceof NoSuchMethodException)) {
                StringBuilder sb = new StringBuilder();
                sb.append("className: ");
                if (obj != null) {
                    cls = obj.getClass();
                }
                sb.append(cls);
                sb.append(", methodName: ");
                sb.append(str);
                throw new Throwable(sb.toString(), th);
            }
            throw th;
        }
    }

    public static <T> T getStaticField(String str, String str2) throws Throwable {
        try {
            return onGetStaticField(str, str2);
        } catch (Throwable th) {
            if (th instanceof NoSuchFieldException) {
                throw th;
            }
            throw new Throwable("className: " + str + ", fieldName: " + str2, th);
        }
    }

    private static <T> T onGetStaticField(String str, String str2) throws Throwable {
        Field field;
        ArrayList arrayList = new ArrayList();
        for (Class importedClass = getImportedClass(str); importedClass != null; importedClass = importedClass.getSuperclass()) {
            arrayList.add(importedClass);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                field = ((Class) it.next()).getDeclaredField(str2);
            } catch (Throwable unused) {
                field = null;
            }
            if (field != null && Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                return field.get((Object) null);
            }
        }
        throw new NoSuchFieldException("className: " + str + ", fieldName: " + str2);
    }

    public static void setStaticField(String str, String str2, Object obj) throws Throwable {
        try {
            onSetStaticField(str, str2, obj);
        } catch (Throwable th) {
            if (th instanceof NoSuchFieldException) {
                throw th;
            }
            throw new Throwable("className: " + str + ", fieldName: " + str2 + ", value: " + String.valueOf(obj), th);
        }
    }

    private static void onSetStaticField(String str, String str2, Object obj) throws Throwable {
        Field field;
        ArrayList arrayList = new ArrayList();
        for (Class importedClass = getImportedClass(str); importedClass != null; importedClass = importedClass.getSuperclass()) {
            arrayList.add(importedClass);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                field = ((Class) it.next()).getDeclaredField(str2);
            } catch (Throwable unused) {
                field = null;
            }
            if (field != null && Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                field.set((Object) null, obj);
                return;
            }
        }
        throw new NoSuchFieldException("className: " + str + ", fieldName: " + str2 + ", value: " + String.valueOf(obj));
    }

    public static <T> T getInstanceField(Object obj, String str) throws Throwable {
        try {
            return onGetInstanceField(obj, str);
        } catch (Throwable th) {
            if (th instanceof NoSuchFieldException) {
                throw th;
            }
            throw new Throwable("className: " + obj.getClass() + ", fieldName: " + str, th);
        }
    }

    private static <T> T onGetInstanceField(Object obj, String str) throws Throwable {
        if ((obj instanceof List) || obj.getClass().isArray()) {
            return onGetElement(obj, str);
        }
        if (obj instanceof Map) {
            return ((Map) obj).get(str);
        }
        ArrayList arrayList = new ArrayList();
        for (Class cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            arrayList.add(cls);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Field field = null;
            try {
                field = ((Class) it.next()).getDeclaredField(str);
            } catch (Throwable unused) {
            }
            if (field != null && !Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                return field.get(obj);
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str);
    }

    private static Object onGetElement(Object obj, String str) throws Throwable {
        int i;
        int i2;
        if (obj instanceof List) {
            if (str.startsWith("[") && str.endsWith("]")) {
                try {
                    i2 = Integer.parseInt(str.substring(1, str.length() - 1));
                } catch (Throwable unused) {
                    i2 = -1;
                }
                if (i2 != -1) {
                    return ((List) obj).get(i2);
                }
            }
        } else if ("length".equals(str)) {
            return Integer.valueOf(Array.getLength(obj));
        } else {
            if (str.startsWith("[") && str.endsWith("]")) {
                try {
                    i = Integer.parseInt(str.substring(1, str.length() - 1));
                } catch (Throwable unused2) {
                    i = -1;
                }
                if (i != -1) {
                    return Array.get(obj, i);
                }
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str);
    }

    public static void setInstanceField(Object obj, String str, Object obj2) throws Throwable {
        try {
            onSetInstanceField(obj, str, obj2);
        } catch (Throwable th) {
            if (th instanceof NoSuchFieldException) {
                throw th;
            }
            throw new Throwable("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2), th);
        }
    }

    private static void onSetInstanceField(Object obj, String str, Object obj2) throws Throwable {
        if ((obj instanceof List) || obj.getClass().isArray()) {
            onSetElement(obj, str, obj2);
        } else if (obj instanceof Map) {
            ((Map) obj).put(str, obj2);
        } else {
            ArrayList arrayList = new ArrayList();
            for (Class cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
                arrayList.add(cls);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Field field = null;
                try {
                    field = ((Class) it.next()).getDeclaredField(str);
                } catch (Throwable unused) {
                }
                if (field != null && !Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    field.set(obj, obj2);
                    return;
                }
            }
            throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2));
        }
    }

    private static void onSetElement(Object obj, String str, Object obj2) throws Throwable {
        int i;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        int i2;
        if (obj instanceof List) {
            if (str.startsWith("[") && str.endsWith("]")) {
                try {
                    i2 = Integer.parseInt(str.substring(1, str.length() - 1));
                } catch (Throwable unused) {
                    i2 = -1;
                }
                if (i2 != -1) {
                    ((List) obj).set(i2, obj2);
                    return;
                }
            }
        } else if (str.startsWith("[") && str.endsWith("]")) {
            try {
                i = Integer.parseInt(str.substring(1, str.length() - 1));
            } catch (Throwable unused2) {
                i = -1;
            }
            if (i != -1) {
                String name = obj.getClass().getName();
                while (name.startsWith("[")) {
                    name = name.substring(1);
                }
                Class<?> cls = obj2.getClass();
                if ("B".equals(name)) {
                    if (cls == Byte.class) {
                        Array.set(obj, i, obj2);
                        return;
                    }
                } else if (ExifInterface.LATITUDE_SOUTH.equals(name)) {
                    if (cls == Short.class) {
                        obj7 = obj2;
                    } else {
                        obj7 = cls == Byte.class ? Short.valueOf((short) ((Byte) obj2).byteValue()) : null;
                    }
                    if (obj7 != null) {
                        Array.set(obj, i, obj7);
                        return;
                    }
                } else if ("I".equals(name)) {
                    if (cls == Integer.class) {
                        obj6 = obj2;
                    } else if (cls == Short.class) {
                        obj6 = Integer.valueOf(((Short) obj2).shortValue());
                    } else {
                        obj6 = cls == Byte.class ? Integer.valueOf(((Byte) obj2).byteValue()) : null;
                    }
                    if (obj6 != null) {
                        Array.set(obj, i, obj6);
                        return;
                    }
                } else if ("J".equals(name)) {
                    if (cls == Long.class) {
                        obj5 = obj2;
                    } else if (cls == Integer.class) {
                        obj5 = Long.valueOf((long) ((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        obj5 = Long.valueOf((long) ((Short) obj2).shortValue());
                    } else {
                        obj5 = cls == Byte.class ? Long.valueOf((long) ((Byte) obj2).byteValue()) : null;
                    }
                    if (obj5 != null) {
                        Array.set(obj, i, obj5);
                        return;
                    }
                } else if ("F".equals(name)) {
                    if (cls == Float.class) {
                        obj4 = obj2;
                    } else if (cls == Long.class) {
                        obj4 = Float.valueOf((float) ((Long) obj2).longValue());
                    } else if (cls == Integer.class) {
                        obj4 = Float.valueOf((float) ((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        obj4 = Float.valueOf((float) ((Short) obj2).shortValue());
                    } else {
                        obj4 = cls == Byte.class ? Float.valueOf((float) ((Byte) obj2).byteValue()) : null;
                    }
                    if (obj4 != null) {
                        Array.set(obj, i, obj4);
                        return;
                    }
                } else if ("D".equals(name)) {
                    if (cls == Double.class) {
                        obj3 = obj2;
                    } else if (cls == Float.class) {
                        obj3 = Double.valueOf((double) ((Float) obj2).floatValue());
                    } else if (cls == Long.class) {
                        obj3 = Double.valueOf((double) ((Long) obj2).longValue());
                    } else if (cls == Integer.class) {
                        obj3 = Double.valueOf((double) ((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        obj3 = Double.valueOf((double) ((Short) obj2).shortValue());
                    } else {
                        obj3 = cls == Byte.class ? Double.valueOf((double) ((Byte) obj2).byteValue()) : null;
                    }
                    if (obj3 != null) {
                        Array.set(obj, i, obj3);
                        return;
                    }
                } else if ("Z".equals(name)) {
                    if (cls == Boolean.class) {
                        Array.set(obj, i, obj2);
                        return;
                    }
                } else if ("C".equals(name)) {
                    if (cls == Character.class) {
                        Array.set(obj, i, obj2);
                        return;
                    }
                } else if (name.equals(cls.getName())) {
                    Array.set(obj, i, obj2);
                    return;
                }
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2));
    }

    public static Class<?> getClass(String str) throws Throwable {
        Class<?> importedClass = getImportedClass(str);
        if (importedClass == null) {
            try {
                importedClass = Class.forName(str);
                if (importedClass != null) {
                    classMap.put(str, importedClass);
                }
            } catch (Throwable unused) {
            }
        }
        return importedClass;
    }

    public static String getName(Class<?> cls) throws Throwable {
        String str = nameMap.get(cls);
        if (str == null) {
            str = cls.getSimpleName();
            if (classMap.containsKey(str)) {
                nameMap.remove(classMap.get(str));
            }
            classMap.put(str, cls);
            nameMap.put(cls, str);
        }
        return str;
    }

    public static Object createProxy(HashMap<String, ReflectRunnable<Object, Object[]>> hashMap, Class<?>... clsArr) throws Throwable {
        HashMap hashMap2 = new HashMap();
        for (final Map.Entry next : hashMap.entrySet()) {
            hashMap2.put(next.getKey(), new ReflectRunnable<Object[], Object>() {
                public Object run(Object[] objArr) {
                    return ((Object[]) ((ReflectRunnable) next.getValue()).run(objArr))[0];
                }
            });
        }
        return createProxy((Map<String, ReflectRunnable<Object[], Object>>) hashMap2, clsArr);
    }

    public static Object createProxy(final Map<String, ReflectRunnable<Object[], Object>> map, Class<?>... clsArr) throws Throwable {
        if (clsArr.length == 0) {
            return null;
        }
        return Proxy.newProxyInstance(clsArr[0].getClassLoader(), clsArr, new InvocationHandler() {
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                ReflectRunnable reflectRunnable = (ReflectRunnable) map.get(method.getName());
                if (reflectRunnable != null) {
                    return reflectRunnable.run(objArr);
                }
                throw new NoSuchMethodException();
            }
        });
    }
}
