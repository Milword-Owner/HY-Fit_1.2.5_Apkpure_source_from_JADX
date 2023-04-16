package com.blankj.utilcode.util;

import android.util.Log;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public final class BusUtils {
    private static final Object NULL = "nULl";
    private static final String TAG = "BusUtils";
    private final Map<String, Set<Object>> mClassName_BusesMap;
    private final Map<String, Map<String, Object>> mClassName_Tag_Arg4StickyMap;
    private final Map<String, List<String>> mClassName_TagsMap;
    private final Map<String, List<BusInfo>> mTag_BusInfoListMap;

    @Target({ElementType.METHOD})
    @Retention(RetentionPolicy.CLASS)
    public @interface Bus {
        int priority() default 0;

        boolean sticky() default false;

        String tag();

        ThreadMode threadMode() default ThreadMode.POSTING;
    }

    public enum ThreadMode {
        MAIN,
        IO,
        CPU,
        CACHED,
        SINGLE,
        POSTING
    }

    private void init() {
    }

    private BusUtils() {
        this.mTag_BusInfoListMap = new ConcurrentHashMap();
        this.mClassName_BusesMap = new ConcurrentHashMap();
        this.mClassName_TagsMap = new ConcurrentHashMap();
        this.mClassName_Tag_Arg4StickyMap = new ConcurrentHashMap();
        init();
    }

    private void registerBus(String str, String str2, String str3, String str4, String str5, boolean z, String str6) {
        registerBus(str, str2, str3, str4, str5, z, str6, 0);
    }

    private void registerBus(String str, String str2, String str3, String str4, String str5, boolean z, String str6, int i) {
        String str7 = str;
        List list = this.mTag_BusInfoListMap.get(str);
        if (list == null) {
            list = new CopyOnWriteArrayList();
            this.mTag_BusInfoListMap.put(str, list);
        }
        list.add(new BusInfo(str, str2, str3, str4, str5, z, str6, i));
    }

    public static void register(Object obj) {
        getInstance().registerInner(obj);
    }

    public static void unregister(Object obj) {
        getInstance().unregisterInner(obj);
    }

    public static void post(String str) {
        post(str, NULL);
    }

    public static void post(String str, Object obj) {
        getInstance().postInner(str, obj);
    }

    public static void postSticky(String str) {
        postSticky(str, NULL);
    }

    public static void postSticky(String str, Object obj) {
        getInstance().postStickyInner(str, obj);
    }

    public static void removeSticky(String str) {
        getInstance().removeStickyInner(str);
    }

    public static String toString_() {
        return getInstance().toString();
    }

    public String toString() {
        return "BusUtils: " + this.mTag_BusInfoListMap;
    }

    private static BusUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004b, code lost:
        if (r2 == false) goto L_0x0050;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004d, code lost:
        recordTags(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0050, code lost:
        consumeStickyIfExist(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0053, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void registerInner(java.lang.Object r7) {
        /*
            r6 = this;
            if (r7 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.Class r0 = r7.getClass()
            java.lang.String r1 = r0.getName()
            r2 = 0
            java.util.Map<java.lang.String, java.util.Set<java.lang.Object>> r3 = r6.mClassName_BusesMap
            monitor-enter(r3)
            java.util.Map<java.lang.String, java.util.Set<java.lang.Object>> r4 = r6.mClassName_BusesMap     // Catch:{ all -> 0x0054 }
            java.lang.Object r4 = r4.get(r1)     // Catch:{ all -> 0x0054 }
            java.util.Set r4 = (java.util.Set) r4     // Catch:{ all -> 0x0054 }
            if (r4 != 0) goto L_0x0024
            java.util.concurrent.CopyOnWriteArraySet r4 = new java.util.concurrent.CopyOnWriteArraySet     // Catch:{ all -> 0x0054 }
            r4.<init>()     // Catch:{ all -> 0x0054 }
            java.util.Map<java.lang.String, java.util.Set<java.lang.Object>> r2 = r6.mClassName_BusesMap     // Catch:{ all -> 0x0054 }
            r2.put(r1, r4)     // Catch:{ all -> 0x0054 }
            r2 = 1
        L_0x0024:
            boolean r5 = r4.contains(r7)     // Catch:{ all -> 0x0054 }
            if (r5 == 0) goto L_0x0047
            java.lang.String r0 = "BusUtils"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0054 }
            r1.<init>()     // Catch:{ all -> 0x0054 }
            java.lang.String r2 = "The bus of <"
            r1.append(r2)     // Catch:{ all -> 0x0054 }
            r1.append(r7)     // Catch:{ all -> 0x0054 }
            java.lang.String r7 = "> already registered."
            r1.append(r7)     // Catch:{ all -> 0x0054 }
            java.lang.String r7 = r1.toString()     // Catch:{ all -> 0x0054 }
            android.util.Log.w(r0, r7)     // Catch:{ all -> 0x0054 }
            monitor-exit(r3)     // Catch:{ all -> 0x0054 }
            return
        L_0x0047:
            r4.add(r7)     // Catch:{ all -> 0x0054 }
            monitor-exit(r3)     // Catch:{ all -> 0x0054 }
            if (r2 == 0) goto L_0x0050
            r6.recordTags(r0, r1)
        L_0x0050:
            r6.consumeStickyIfExist(r7)
            return
        L_0x0054:
            r7 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0054 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.BusUtils.registerInner(java.lang.Object):void");
    }

    private void recordTags(Class<?> cls, String str) {
        if (this.mClassName_TagsMap.get(str) == null) {
            synchronized (this.mClassName_TagsMap) {
                if (this.mClassName_TagsMap.get(str) == null) {
                    CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
                    for (Map.Entry next : this.mTag_BusInfoListMap.entrySet()) {
                        for (BusInfo busInfo : (List) next.getValue()) {
                            try {
                                if (Class.forName(busInfo.className).isAssignableFrom(cls)) {
                                    copyOnWriteArrayList.add(next.getKey());
                                    busInfo.subClassNames.add(str);
                                }
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    this.mClassName_TagsMap.put(str, copyOnWriteArrayList);
                }
            }
        }
    }

    private void consumeStickyIfExist(Object obj) {
        Map map = this.mClassName_Tag_Arg4StickyMap.get(obj.getClass().getName());
        if (map != null) {
            synchronized (this.mClassName_Tag_Arg4StickyMap) {
                for (Map.Entry entry : map.entrySet()) {
                    consumeSticky(obj, (String) entry.getKey(), entry.getValue());
                }
            }
        }
    }

    private void consumeSticky(Object obj, String str, Object obj2) {
        List<BusInfo> list = this.mTag_BusInfoListMap.get(str);
        if (list == null) {
            Log.e(TAG, "The bus of tag <" + str + "> is not exists.");
            return;
        }
        for (BusInfo busInfo : list) {
            if (busInfo.subClassNames.contains(obj.getClass().getName()) && busInfo.sticky) {
                synchronized (this.mClassName_Tag_Arg4StickyMap) {
                    Map map = this.mClassName_Tag_Arg4StickyMap.get(busInfo.className);
                    if (map != null) {
                        if (map.containsKey(str)) {
                            invokeBus(obj, obj2, busInfo, true);
                        }
                    }
                }
            }
        }
    }

    private void unregisterInner(Object obj) {
        if (obj != null) {
            String name = obj.getClass().getName();
            synchronized (this.mClassName_BusesMap) {
                Set set = this.mClassName_BusesMap.get(name);
                if (set != null) {
                    if (set.contains(obj)) {
                        set.remove(obj);
                        return;
                    }
                }
                Log.e(TAG, "The bus of <" + obj + "> was not registered before.");
            }
        }
    }

    private void postInner(String str, Object obj) {
        postInner(str, obj, false);
    }

    private void postInner(String str, Object obj, boolean z) {
        List<BusInfo> list = this.mTag_BusInfoListMap.get(str);
        if (list == null) {
            Log.e(TAG, "The bus of tag <" + str + "> is not exists.");
            if (this.mTag_BusInfoListMap.isEmpty()) {
                Log.e(TAG, "Please check whether the bus plugin is applied.");
                return;
            }
            return;
        }
        for (BusInfo invokeBus : list) {
            invokeBus(obj, invokeBus, z);
        }
    }

    private void invokeBus(Object obj, BusInfo busInfo, boolean z) {
        invokeBus((Object) null, obj, busInfo, z);
    }

    private void invokeBus(Object obj, Object obj2, BusInfo busInfo, boolean z) {
        if (busInfo.method == null) {
            Method methodByBusInfo = getMethodByBusInfo(busInfo);
            if (methodByBusInfo != null) {
                busInfo.method = methodByBusInfo;
            } else {
                return;
            }
        }
        invokeMethod(obj, obj2, busInfo, z);
    }

    private Method getMethodByBusInfo(BusInfo busInfo) {
        try {
            if ("".equals(busInfo.paramType)) {
                return Class.forName(busInfo.className).getDeclaredMethod(busInfo.funName, new Class[0]);
            }
            return Class.forName(busInfo.className).getDeclaredMethod(busInfo.funName, new Class[]{getClassName(busInfo.paramType)});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Class getClassName(java.lang.String r2) throws java.lang.ClassNotFoundException {
        /*
            r1 = this;
            int r0 = r2.hashCode()
            switch(r0) {
                case -1325958191: goto L_0x004e;
                case 104431: goto L_0x0044;
                case 3039496: goto L_0x003a;
                case 3052374: goto L_0x0030;
                case 3327612: goto L_0x0026;
                case 64711720: goto L_0x001c;
                case 97526364: goto L_0x0012;
                case 109413500: goto L_0x0008;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x0058
        L_0x0008:
            java.lang.String r0 = "short"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0058
            r0 = 3
            goto L_0x0059
        L_0x0012:
            java.lang.String r0 = "float"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0058
            r0 = 6
            goto L_0x0059
        L_0x001c:
            java.lang.String r0 = "boolean"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0058
            r0 = 0
            goto L_0x0059
        L_0x0026:
            java.lang.String r0 = "long"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0058
            r0 = 2
            goto L_0x0059
        L_0x0030:
            java.lang.String r0 = "char"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0058
            r0 = 7
            goto L_0x0059
        L_0x003a:
            java.lang.String r0 = "byte"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0058
            r0 = 4
            goto L_0x0059
        L_0x0044:
            java.lang.String r0 = "int"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0058
            r0 = 1
            goto L_0x0059
        L_0x004e:
            java.lang.String r0 = "double"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0058
            r0 = 5
            goto L_0x0059
        L_0x0058:
            r0 = -1
        L_0x0059:
            switch(r0) {
                case 0: goto L_0x0076;
                case 1: goto L_0x0073;
                case 2: goto L_0x0070;
                case 3: goto L_0x006d;
                case 4: goto L_0x006a;
                case 5: goto L_0x0067;
                case 6: goto L_0x0064;
                case 7: goto L_0x0061;
                default: goto L_0x005c;
            }
        L_0x005c:
            java.lang.Class r2 = java.lang.Class.forName(r2)
            return r2
        L_0x0061:
            java.lang.Class r2 = java.lang.Character.TYPE
            return r2
        L_0x0064:
            java.lang.Class r2 = java.lang.Float.TYPE
            return r2
        L_0x0067:
            java.lang.Class r2 = java.lang.Double.TYPE
            return r2
        L_0x006a:
            java.lang.Class r2 = java.lang.Byte.TYPE
            return r2
        L_0x006d:
            java.lang.Class r2 = java.lang.Short.TYPE
            return r2
        L_0x0070:
            java.lang.Class r2 = java.lang.Long.TYPE
            return r2
        L_0x0073:
            java.lang.Class r2 = java.lang.Integer.TYPE
            return r2
        L_0x0076:
            java.lang.Class r2 = java.lang.Boolean.TYPE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.BusUtils.getClassName(java.lang.String):java.lang.Class");
    }

    private void invokeMethod(Object obj, BusInfo busInfo, boolean z) {
        invokeMethod((Object) null, obj, busInfo, z);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void invokeMethod(java.lang.Object r8, java.lang.Object r9, com.blankj.utilcode.util.BusUtils.BusInfo r10, boolean r11) {
        /*
            r7 = this;
            com.blankj.utilcode.util.BusUtils$1 r6 = new com.blankj.utilcode.util.BusUtils$1
            r0 = r6
            r1 = r7
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            r0.<init>(r2, r3, r4, r5)
            java.lang.String r8 = r10.threadMode
            int r9 = r8.hashCode()
            r10 = 4
            r11 = 3
            r0 = 2
            r1 = 1
            switch(r9) {
                case -1848936376: goto L_0x0041;
                case 2342: goto L_0x0037;
                case 66952: goto L_0x002d;
                case 2358713: goto L_0x0023;
                case 1980249378: goto L_0x0019;
                default: goto L_0x0018;
            }
        L_0x0018:
            goto L_0x004b
        L_0x0019:
            java.lang.String r9 = "CACHED"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x004b
            r8 = 3
            goto L_0x004c
        L_0x0023:
            java.lang.String r9 = "MAIN"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x004b
            r8 = 0
            goto L_0x004c
        L_0x002d:
            java.lang.String r9 = "CPU"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x004b
            r8 = 2
            goto L_0x004c
        L_0x0037:
            java.lang.String r9 = "IO"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x004b
            r8 = 1
            goto L_0x004c
        L_0x0041:
            java.lang.String r9 = "SINGLE"
            boolean r8 = r8.equals(r9)
            if (r8 == 0) goto L_0x004b
            r8 = 4
            goto L_0x004c
        L_0x004b:
            r8 = -1
        L_0x004c:
            if (r8 == 0) goto L_0x007a
            if (r8 == r1) goto L_0x0072
            if (r8 == r0) goto L_0x006a
            if (r8 == r11) goto L_0x0062
            if (r8 == r10) goto L_0x005a
            r6.run()
            return
        L_0x005a:
            java.util.concurrent.ExecutorService r8 = com.blankj.utilcode.util.ThreadUtils.getSinglePool()
            r8.execute(r6)
            return
        L_0x0062:
            java.util.concurrent.ExecutorService r8 = com.blankj.utilcode.util.ThreadUtils.getCachedPool()
            r8.execute(r6)
            return
        L_0x006a:
            java.util.concurrent.ExecutorService r8 = com.blankj.utilcode.util.ThreadUtils.getCpuPool()
            r8.execute(r6)
            return
        L_0x0072:
            java.util.concurrent.ExecutorService r8 = com.blankj.utilcode.util.ThreadUtils.getIoPool()
            r8.execute(r6)
            return
        L_0x007a:
            com.blankj.utilcode.util.ThreadUtils.runOnUiThread(r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.BusUtils.invokeMethod(java.lang.Object, java.lang.Object, com.blankj.utilcode.util.BusUtils$BusInfo, boolean):void");
    }

    /* access modifiers changed from: private */
    public void realInvokeMethod(Object obj, Object obj2, BusInfo busInfo, boolean z) {
        HashSet hashSet = new HashSet();
        if (obj == null) {
            for (String str : busInfo.subClassNames) {
                Set set = this.mClassName_BusesMap.get(str);
                if (set != null && !set.isEmpty()) {
                    hashSet.addAll(set);
                }
            }
            if (hashSet.size() == 0) {
                if (!z) {
                    Log.e(TAG, "The " + busInfo + " was not registered before.");
                    return;
                }
                return;
            }
        } else {
            hashSet.add(obj);
        }
        invokeBuses(obj2, busInfo, hashSet);
    }

    private void invokeBuses(Object obj, BusInfo busInfo, Set<Object> set) {
        try {
            if (obj == NULL) {
                for (Object invoke : set) {
                    busInfo.method.invoke(invoke, new Object[0]);
                }
                return;
            }
            for (Object next : set) {
                busInfo.method.invoke(next, new Object[]{obj});
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e2) {
            e2.printStackTrace();
        }
    }

    private void postStickyInner(String str, Object obj) {
        List<BusInfo> list = this.mTag_BusInfoListMap.get(str);
        if (list == null) {
            Log.e(TAG, "The bus of tag <" + str + "> is not exists.");
            return;
        }
        for (BusInfo busInfo : list) {
            if (!busInfo.sticky) {
                invokeBus(obj, busInfo, false);
            } else {
                synchronized (this.mClassName_Tag_Arg4StickyMap) {
                    Map map = this.mClassName_Tag_Arg4StickyMap.get(busInfo.className);
                    if (map == null) {
                        map = new ConcurrentHashMap();
                        this.mClassName_Tag_Arg4StickyMap.put(busInfo.className, map);
                    }
                    map.put(str, obj);
                }
                invokeBus(obj, busInfo, true);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void removeStickyInner(java.lang.String r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.String, java.util.List<com.blankj.utilcode.util.BusUtils$BusInfo>> r0 = r4.mTag_BusInfoListMap
            java.lang.Object r0 = r0.get(r5)
            java.util.List r0 = (java.util.List) r0
            if (r0 != 0) goto L_0x0026
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "The bus of tag <"
            r0.append(r1)
            r0.append(r5)
            java.lang.String r5 = "> is not exists."
            r0.append(r5)
            java.lang.String r5 = r0.toString()
            java.lang.String r0 = "BusUtils"
            android.util.Log.e(r0, r5)
            return
        L_0x0026:
            java.util.Iterator r0 = r0.iterator()
        L_0x002a:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x005b
            java.lang.Object r1 = r0.next()
            com.blankj.utilcode.util.BusUtils$BusInfo r1 = (com.blankj.utilcode.util.BusUtils.BusInfo) r1
            boolean r2 = r1.sticky
            if (r2 != 0) goto L_0x003b
            goto L_0x002a
        L_0x003b:
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Object>> r2 = r4.mClassName_Tag_Arg4StickyMap
            monitor-enter(r2)
            java.util.Map<java.lang.String, java.util.Map<java.lang.String, java.lang.Object>> r3 = r4.mClassName_Tag_Arg4StickyMap     // Catch:{ all -> 0x0058 }
            java.lang.String r1 = r1.className     // Catch:{ all -> 0x0058 }
            java.lang.Object r1 = r3.get(r1)     // Catch:{ all -> 0x0058 }
            java.util.Map r1 = (java.util.Map) r1     // Catch:{ all -> 0x0058 }
            if (r1 == 0) goto L_0x0056
            boolean r3 = r1.containsKey(r5)     // Catch:{ all -> 0x0058 }
            if (r3 != 0) goto L_0x0051
            goto L_0x0056
        L_0x0051:
            r1.remove(r5)     // Catch:{ all -> 0x0058 }
            monitor-exit(r2)     // Catch:{ all -> 0x0058 }
            goto L_0x002a
        L_0x0056:
            monitor-exit(r2)     // Catch:{ all -> 0x0058 }
            return
        L_0x0058:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0058 }
            throw r5
        L_0x005b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.blankj.utilcode.util.BusUtils.removeStickyInner(java.lang.String):void");
    }

    static void registerBus4Test(String str, String str2, String str3, String str4, String str5, boolean z, String str6, int i) {
        getInstance().registerBus(str, str2, str3, str4, str5, z, str6, i);
    }

    private static final class BusInfo {
        String className;
        String funName;
        Method method;
        String paramName;
        String paramType;
        int priority;
        boolean sticky;
        List<String> subClassNames = new CopyOnWriteArrayList();
        String tag;
        String threadMode;

        BusInfo(String str, String str2, String str3, String str4, String str5, boolean z, String str6, int i) {
            this.tag = str;
            this.className = str2;
            this.funName = str3;
            this.paramType = str4;
            this.paramName = str5;
            this.sticky = z;
            this.threadMode = str6;
            this.priority = i;
        }

        public String toString() {
            return "BusInfo { tag : " + this.tag + ", desc: " + getDesc() + ", sticky: " + this.sticky + ", threadMode: " + this.threadMode + ", method: " + this.method + ", priority: " + this.priority + " }";
        }

        private String getDesc() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append(this.className);
            sb.append("#");
            sb.append(this.funName);
            if ("".equals(this.paramType)) {
                str = "()";
            } else {
                str = "(" + this.paramType + " " + this.paramName + ")";
            }
            sb.append(str);
            return sb.toString();
        }
    }

    private static class LazyHolder {
        /* access modifiers changed from: private */
        public static final BusUtils INSTANCE = new BusUtils();

        private LazyHolder() {
        }
    }
}
