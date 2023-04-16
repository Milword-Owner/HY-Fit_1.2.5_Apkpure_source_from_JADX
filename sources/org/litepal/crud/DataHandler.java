package org.litepal.crud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.SparseArray;
import com.github.mikephil.charting.utils.Utils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import kotlin.jvm.internal.LongCompanionObject;
import org.litepal.LitePalBase;
import org.litepal.Operator;
import org.litepal.annotation.Column;
import org.litepal.annotation.Encrypt;
import org.litepal.crud.model.AssociationsInfo;
import org.litepal.exceptions.DatabaseGenerateException;
import org.litepal.exceptions.LitePalSupportException;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;
import org.litepal.util.cipher.CipherUtil;

abstract class DataHandler extends LitePalBase {
    public static final String TAG = "DataHandler";
    private List<AssociationsInfo> fkInCurrentModel;
    private List<AssociationsInfo> fkInOtherModel;
    SQLiteDatabase mDatabase;
    private LitePalSupport tempEmptyModel;

    /* access modifiers changed from: protected */
    public boolean shouldGetOrSet(LitePalSupport litePalSupport, Field field) {
        return (litePalSupport == null || field == null) ? false : true;
    }

    DataHandler() {
    }

    /* access modifiers changed from: protected */
    public <T> List<T> query(Class<T> cls, String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5, List<AssociationsInfo> list) {
        List<AssociationsInfo> list2 = list;
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            List<Field> supportedFields = getSupportedFields(cls.getName());
            List<Field> supportedGenericFields = getSupportedGenericFields(cls.getName());
            String[] convertSelectClauseToValidNames = DBUtility.convertSelectClauseToValidNames(getCustomizedColumns(strArr, supportedGenericFields, list2));
            Cursor query = this.mDatabase.query(getTableName(cls), convertSelectClauseToValidNames, str, strArr2, str2, str3, str4, str5);
            if (query.moveToFirst()) {
                SparseArray sparseArray = new SparseArray();
                HashMap hashMap = new HashMap();
                do {
                    Object createInstanceFromClass = createInstanceFromClass(cls);
                    giveBaseObjIdValue((LitePalSupport) createInstanceFromClass, query.getLong(query.getColumnIndexOrThrow("id")));
                    setValueToModel(createInstanceFromClass, supportedFields, list, query, sparseArray);
                    setGenericValueToModel((LitePalSupport) createInstanceFromClass, supportedGenericFields, hashMap);
                    if (list2 != null) {
                        setAssociatedModel((LitePalSupport) createInstanceFromClass);
                    }
                    arrayList.add(createInstanceFromClass);
                } while (query.moveToNext());
                sparseArray.clear();
                hashMap.clear();
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0058  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> T mathQuery(java.lang.String r10, java.lang.String[] r11, java.lang.String[] r12, java.lang.Class<T> r13) {
        /*
            r9 = this;
            org.litepal.util.BaseUtility.checkConditionsCorrect(r12)
            r0 = 0
            android.database.sqlite.SQLiteDatabase r1 = r9.mDatabase     // Catch:{ Exception -> 0x004b }
            java.lang.String r4 = r9.getWhereClause(r12)     // Catch:{ Exception -> 0x004b }
            java.lang.String[] r5 = r9.getWhereArgs(r12)     // Catch:{ Exception -> 0x004b }
            r6 = 0
            r7 = 0
            r8 = 0
            r2 = r10
            r3 = r11
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x004b }
            boolean r11 = r10.moveToFirst()     // Catch:{ Exception -> 0x0045, all -> 0x0043 }
            if (r11 == 0) goto L_0x003d
            java.lang.Class r11 = r10.getClass()     // Catch:{ Exception -> 0x0045, all -> 0x0043 }
            java.lang.String r12 = r9.genGetColumnMethod((java.lang.Class<?>) r13)     // Catch:{ Exception -> 0x0045, all -> 0x0043 }
            r13 = 1
            java.lang.Class[] r0 = new java.lang.Class[r13]     // Catch:{ Exception -> 0x0045, all -> 0x0043 }
            java.lang.Class r1 = java.lang.Integer.TYPE     // Catch:{ Exception -> 0x0045, all -> 0x0043 }
            r2 = 0
            r0[r2] = r1     // Catch:{ Exception -> 0x0045, all -> 0x0043 }
            java.lang.reflect.Method r11 = r11.getMethod(r12, r0)     // Catch:{ Exception -> 0x0045, all -> 0x0043 }
            java.lang.Object[] r12 = new java.lang.Object[r13]     // Catch:{ Exception -> 0x0045, all -> 0x0043 }
            java.lang.Integer r13 = java.lang.Integer.valueOf(r2)     // Catch:{ Exception -> 0x0045, all -> 0x0043 }
            r12[r2] = r13     // Catch:{ Exception -> 0x0045, all -> 0x0043 }
            java.lang.Object r0 = r11.invoke(r10, r12)     // Catch:{ Exception -> 0x0045, all -> 0x0043 }
        L_0x003d:
            if (r10 == 0) goto L_0x0042
            r10.close()
        L_0x0042:
            return r0
        L_0x0043:
            r11 = move-exception
            goto L_0x0056
        L_0x0045:
            r11 = move-exception
            r0 = r10
            goto L_0x004c
        L_0x0048:
            r11 = move-exception
            r10 = r0
            goto L_0x0056
        L_0x004b:
            r11 = move-exception
        L_0x004c:
            org.litepal.exceptions.LitePalSupportException r10 = new org.litepal.exceptions.LitePalSupportException     // Catch:{ all -> 0x0048 }
            java.lang.String r12 = r11.getMessage()     // Catch:{ all -> 0x0048 }
            r10.<init>(r12, r11)     // Catch:{ all -> 0x0048 }
            throw r10     // Catch:{ all -> 0x0048 }
        L_0x0056:
            if (r10 == 0) goto L_0x005b
            r10.close()
        L_0x005b:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.crud.DataHandler.mathQuery(java.lang.String, java.lang.String[], java.lang.String[], java.lang.Class):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public void giveBaseObjIdValue(LitePalSupport litePalSupport, long j) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        if (j > 0) {
            DynamicExecutor.set(litePalSupport, "baseObjId", Long.valueOf(j), LitePalSupport.class);
        }
    }

    /* access modifiers changed from: protected */
    public void putFieldsValue(LitePalSupport litePalSupport, List<Field> list, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (Field next : list) {
            if (!isIdColumn(next.getName())) {
                putFieldsValueDependsOnSaveOrUpdate(litePalSupport, next, contentValues);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void putContentValuesForSave(LitePalSupport litePalSupport, Field field, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object fieldValue = getFieldValue(litePalSupport, field);
        if ("java.util.Date".equals(field.getType().getName())) {
            if (fieldValue != null) {
                fieldValue = Long.valueOf(((Date) fieldValue).getTime());
            } else {
                Column column = (Column) field.getAnnotation(Column.class);
                if (column != null) {
                    String defaultValue = column.defaultValue();
                    if (!defaultValue.isEmpty()) {
                        try {
                            fieldValue = Long.valueOf(Long.parseLong(defaultValue));
                        } catch (NumberFormatException unused) {
                            Log.w(TAG, field + " in " + litePalSupport.getClass() + " with invalid defaultValue. So we use null instead");
                        }
                    }
                }
                if (fieldValue == null) {
                    fieldValue = Long.valueOf(LongCompanionObject.MAX_VALUE);
                }
            }
        }
        if (fieldValue != null) {
            Encrypt encrypt = (Encrypt) field.getAnnotation(Encrypt.class);
            if (encrypt != null && "java.lang.String".equals(field.getType().getName())) {
                fieldValue = encryptValue(encrypt.algorithm(), fieldValue);
            }
            Object[] objArr = {BaseUtility.changeCase(DBUtility.convertToValidColumnName(field.getName())), fieldValue};
            DynamicExecutor.send(contentValues, "put", objArr, contentValues.getClass(), getParameterTypes(field, fieldValue, objArr));
        }
    }

    /* access modifiers changed from: protected */
    public void putContentValuesForUpdate(LitePalSupport litePalSupport, Field field, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Object fieldValue = getFieldValue(litePalSupport, field);
        if ("java.util.Date".equals(field.getType().getName())) {
            if (fieldValue != null) {
                fieldValue = Long.valueOf(((Date) fieldValue).getTime());
            } else {
                fieldValue = Long.valueOf(LongCompanionObject.MAX_VALUE);
            }
        }
        Encrypt encrypt = (Encrypt) field.getAnnotation(Encrypt.class);
        if (encrypt != null && "java.lang.String".equals(field.getType().getName())) {
            fieldValue = encryptValue(encrypt.algorithm(), fieldValue);
        }
        Object[] objArr = {BaseUtility.changeCase(DBUtility.convertToValidColumnName(field.getName())), fieldValue};
        DynamicExecutor.send(contentValues, "put", objArr, contentValues.getClass(), getParameterTypes(field, fieldValue, objArr));
    }

    /* access modifiers changed from: protected */
    public Object encryptValue(String str, Object obj) {
        if (str == null || obj == null) {
            return obj;
        }
        if ("AES".equalsIgnoreCase(str)) {
            return CipherUtil.aesEncrypt((String) obj);
        }
        return "MD5".equalsIgnoreCase(str) ? CipherUtil.md5Encrypt((String) obj) : obj;
    }

    /* access modifiers changed from: protected */
    public Object getFieldValue(LitePalSupport litePalSupport, Field field) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (shouldGetOrSet(litePalSupport, field)) {
            return DynamicExecutor.getField(litePalSupport, field.getName(), litePalSupport.getClass());
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void setFieldValue(LitePalSupport litePalSupport, Field field, Object obj) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (shouldGetOrSet(litePalSupport, field)) {
            DynamicExecutor.setField(litePalSupport, field.getName(), obj, litePalSupport.getClass());
        }
    }

    /* access modifiers changed from: protected */
    public void analyzeAssociatedModels(LitePalSupport litePalSupport, Collection<AssociationsInfo> collection) {
        try {
            for (AssociationsInfo next : collection) {
                if (next.getAssociationType() == 2) {
                    new Many2OneAnalyzer().analyze(litePalSupport, next);
                } else if (next.getAssociationType() == 1) {
                    new One2OneAnalyzer().analyze(litePalSupport, next);
                } else if (next.getAssociationType() == 3) {
                    new Many2ManyAnalyzer().analyze(litePalSupport, next);
                }
            }
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public LitePalSupport getAssociatedModel(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (LitePalSupport) getFieldValue(litePalSupport, associationsInfo.getAssociateOtherModelFromSelf());
    }

    /* access modifiers changed from: protected */
    public Collection<LitePalSupport> getAssociatedModels(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (Collection) getFieldValue(litePalSupport, associationsInfo.getAssociateOtherModelFromSelf());
    }

    /* access modifiers changed from: protected */
    public LitePalSupport getEmptyModel(LitePalSupport litePalSupport) {
        LitePalSupport litePalSupport2 = this.tempEmptyModel;
        if (litePalSupport2 != null) {
            return litePalSupport2;
        }
        String str = null;
        try {
            str = litePalSupport.getClassName();
            this.tempEmptyModel = (LitePalSupport) Class.forName(str).newInstance();
            return this.tempEmptyModel;
        } catch (ClassNotFoundException unused) {
            throw new DatabaseGenerateException(DatabaseGenerateException.CLASS_NOT_FOUND + str);
        } catch (InstantiationException e) {
            throw new LitePalSupportException(str + LitePalSupportException.INSTANTIATION_EXCEPTION, e);
        } catch (Exception e2) {
            throw new LitePalSupportException(e2.getMessage(), e2);
        }
    }

    /* access modifiers changed from: protected */
    public String getWhereClause(String... strArr) {
        if (!isAffectAllLines(strArr) && strArr != null && strArr.length > 0) {
            return strArr[0];
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public String[] getWhereArgs(String... strArr) {
        if (isAffectAllLines(strArr) || strArr == null || strArr.length <= 1) {
            return null;
        }
        String[] strArr2 = new String[(strArr.length - 1)];
        System.arraycopy(strArr, 1, strArr2, 0, strArr.length - 1);
        return strArr2;
    }

    /* access modifiers changed from: protected */
    public boolean isAffectAllLines(Object... objArr) {
        return objArr != null && objArr.length == 0;
    }

    /* access modifiers changed from: protected */
    public String getWhereOfIdsWithOr(Collection<Long> collection) {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (Long longValue : collection) {
            long longValue2 = longValue.longValue();
            if (z) {
                sb.append(" or ");
            }
            z = true;
            sb.append("id = ");
            sb.append(longValue2);
        }
        return BaseUtility.changeCase(sb.toString());
    }

    /* access modifiers changed from: protected */
    public String getWhereOfIdsWithOr(long... jArr) {
        StringBuilder sb = new StringBuilder();
        int length = jArr.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            long j = jArr[i];
            if (z) {
                sb.append(" or ");
            }
            sb.append("id = ");
            sb.append(j);
            i++;
            z = true;
        }
        return BaseUtility.changeCase(sb.toString());
    }

    /* access modifiers changed from: protected */
    public String getIntermediateTableName(LitePalSupport litePalSupport, String str) {
        return BaseUtility.changeCase(DBUtility.getIntermediateTableName(litePalSupport.getTableName(), str));
    }

    /* access modifiers changed from: protected */
    public String getTableName(Class<?> cls) {
        return BaseUtility.changeCase(DBUtility.getTableNameByClassName(cls.getName()));
    }

    /* access modifiers changed from: protected */
    public Object createInstanceFromClass(Class<?> cls) {
        try {
            Constructor<?> findBestSuitConstructor = findBestSuitConstructor(cls);
            return findBestSuitConstructor.newInstance(getConstructorParams(cls, findBestSuitConstructor));
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public Constructor<?> findBestSuitConstructor(Class<?> cls) {
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        if (declaredConstructors.length != 0) {
            int length = declaredConstructors.length;
            Constructor<?> constructor = null;
            int i = 0;
            int i2 = Integer.MAX_VALUE;
            while (true) {
                boolean z = true;
                if (i >= length) {
                    break;
                }
                Constructor<?> constructor2 = declaredConstructors[i];
                Class<?>[] parameterTypes = constructor2.getParameterTypes();
                int length2 = parameterTypes.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        break;
                    }
                    Class<?> cls2 = parameterTypes[i3];
                    if (cls2 == cls || (cls2.getName().startsWith("com.android") && cls2.getName().endsWith("InstantReloadException"))) {
                        z = false;
                    } else {
                        i3++;
                    }
                }
                if (z && parameterTypes.length < i2) {
                    i2 = parameterTypes.length;
                    constructor = constructor2;
                }
                i++;
            }
            if (constructor != null) {
                constructor.setAccessible(true);
                return constructor;
            }
            StringBuilder sb = new StringBuilder(cls.getName());
            sb.append(" has no suited constructor to new instance. Constructors defined in class:");
            for (Constructor<?> constructor3 : declaredConstructors) {
                sb.append("\n");
                sb.append(constructor3.toString());
            }
            throw new LitePalSupportException(sb.toString());
        }
        throw new LitePalSupportException(cls.getName() + " has no constructor. LitePal could not handle it");
    }

    /* access modifiers changed from: protected */
    public Object[] getConstructorParams(Class<?> cls, Constructor<?> constructor) {
        Class[] parameterTypes = constructor.getParameterTypes();
        Object[] objArr = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            objArr[i] = getInitParamValue(cls, parameterTypes[i]);
        }
        return objArr;
    }

    /* access modifiers changed from: protected */
    public void setValueToModel(Object obj, List<Field> list, List<AssociationsInfo> list2, Cursor cursor, SparseArray<QueryInfoCache> sparseArray) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int size = sparseArray.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                int keyAt = sparseArray.keyAt(i);
                QueryInfoCache queryInfoCache = sparseArray.get(keyAt);
                setToModelByReflection(obj, queryInfoCache.field, keyAt, queryInfoCache.getMethodName, cursor);
            }
        } else {
            for (Field next : list) {
                String genGetColumnMethod = genGetColumnMethod(next);
                int columnIndex = cursor.getColumnIndex(BaseUtility.changeCase(isIdColumn(next.getName()) ? "id" : DBUtility.convertToValidColumnName(next.getName())));
                if (columnIndex != -1) {
                    setToModelByReflection(obj, next, columnIndex, genGetColumnMethod, cursor);
                    QueryInfoCache queryInfoCache2 = new QueryInfoCache();
                    queryInfoCache2.getMethodName = genGetColumnMethod;
                    queryInfoCache2.field = next;
                    sparseArray.put(columnIndex, queryInfoCache2);
                }
            }
        }
        if (list2 != null) {
            for (AssociationsInfo next2 : list2) {
                int columnIndex2 = cursor.getColumnIndex(getForeignKeyColumnName(DBUtility.getTableNameByClassName(next2.getAssociatedClassName())));
                if (columnIndex2 != -1) {
                    try {
                        LitePalSupport litePalSupport = (LitePalSupport) Operator.find(Class.forName(next2.getAssociatedClassName()), cursor.getLong(columnIndex2));
                        if (litePalSupport != null) {
                            setFieldValue((LitePalSupport) obj, next2.getAssociateOtherModelFromSelf(), litePalSupport);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00db  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setGenericValueToModel(org.litepal.crud.LitePalSupport r21, java.util.List<java.lang.reflect.Field> r22, java.util.Map<java.lang.reflect.Field, org.litepal.tablemanager.model.GenericModel> r23) throws java.lang.NoSuchMethodException, java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r20 = this;
            r7 = r20
            r0 = r23
            java.util.Iterator r8 = r22.iterator()
        L_0x0008:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x00df
            java.lang.Object r1 = r8.next()
            r9 = r1
            java.lang.reflect.Field r9 = (java.lang.reflect.Field) r9
            r1 = 0
            java.lang.Object r2 = r0.get(r9)
            org.litepal.tablemanager.model.GenericModel r2 = (org.litepal.tablemanager.model.GenericModel) r2
            if (r2 != 0) goto L_0x006c
            java.lang.String r2 = r7.getGenericTypeName(r9)
            java.lang.String r3 = r21.getClassName()
            boolean r2 = r3.equals(r2)
            if (r2 == 0) goto L_0x0033
            java.lang.String r2 = org.litepal.util.DBUtility.getM2MSelfRefColumnName(r9)
            java.lang.String r3 = "getLong"
            goto L_0x003f
        L_0x0033:
            java.lang.String r2 = r9.getName()
            java.lang.String r2 = org.litepal.util.DBUtility.convertToValidColumnName(r2)
            java.lang.String r3 = r7.genGetColumnMethod((java.lang.reflect.Field) r9)
        L_0x003f:
            java.lang.String r4 = r21.getClassName()
            java.lang.String r5 = r9.getName()
            java.lang.String r4 = org.litepal.util.DBUtility.getGenericTableName(r4, r5)
            java.lang.String r5 = r21.getClassName()
            java.lang.String r5 = org.litepal.util.DBUtility.getGenericValueIdColumnName(r5)
            org.litepal.tablemanager.model.GenericModel r6 = new org.litepal.tablemanager.model.GenericModel
            r6.<init>()
            r6.setTableName(r4)
            r6.setValueColumnName(r2)
            r6.setValueIdColumnName(r5)
            r6.setGetMethodName(r3)
            r0.put(r9, r6)
            r18 = r2
            r19 = r3
            goto L_0x0080
        L_0x006c:
            java.lang.String r4 = r2.getTableName()
            java.lang.String r3 = r2.getValueColumnName()
            java.lang.String r5 = r2.getValueIdColumnName()
            java.lang.String r2 = r2.getGetMethodName()
            r19 = r2
            r18 = r3
        L_0x0080:
            r11 = r4
            android.database.sqlite.SQLiteDatabase r10 = r7.mDatabase     // Catch:{ all -> 0x00d8 }
            r12 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d8 }
            r2.<init>()     // Catch:{ all -> 0x00d8 }
            r2.append(r5)     // Catch:{ all -> 0x00d8 }
            java.lang.String r3 = " = ?"
            r2.append(r3)     // Catch:{ all -> 0x00d8 }
            java.lang.String r13 = r2.toString()     // Catch:{ all -> 0x00d8 }
            r2 = 1
            java.lang.String[] r14 = new java.lang.String[r2]     // Catch:{ all -> 0x00d8 }
            r2 = 0
            long r3 = r21.getBaseObjId()     // Catch:{ all -> 0x00d8 }
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x00d8 }
            r14[r2] = r3     // Catch:{ all -> 0x00d8 }
            r15 = 0
            r16 = 0
            r17 = 0
            android.database.Cursor r10 = r10.query(r11, r12, r13, r14, r15, r16, r17)     // Catch:{ all -> 0x00d8 }
            boolean r1 = r10.moveToFirst()     // Catch:{ all -> 0x00d5 }
            if (r1 == 0) goto L_0x00ce
        L_0x00b2:
            java.lang.String r1 = org.litepal.util.BaseUtility.changeCase(r18)     // Catch:{ all -> 0x00d5 }
            int r4 = r10.getColumnIndex(r1)     // Catch:{ all -> 0x00d5 }
            r1 = -1
            if (r4 == r1) goto L_0x00c8
            r1 = r20
            r2 = r21
            r3 = r9
            r5 = r19
            r6 = r10
            r1.setToModelByReflection(r2, r3, r4, r5, r6)     // Catch:{ all -> 0x00d5 }
        L_0x00c8:
            boolean r1 = r10.moveToNext()     // Catch:{ all -> 0x00d5 }
            if (r1 != 0) goto L_0x00b2
        L_0x00ce:
            if (r10 == 0) goto L_0x0008
            r10.close()
            goto L_0x0008
        L_0x00d5:
            r0 = move-exception
            r1 = r10
            goto L_0x00d9
        L_0x00d8:
            r0 = move-exception
        L_0x00d9:
            if (r1 == 0) goto L_0x00de
            r1.close()
        L_0x00de:
            throw r0
        L_0x00df:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.crud.DataHandler.setGenericValueToModel(org.litepal.crud.LitePalSupport, java.util.List, java.util.Map):void");
    }

    /* access modifiers changed from: protected */
    public List<AssociationsInfo> getForeignKeyAssociations(String str, boolean z) {
        if (!z) {
            return null;
        }
        analyzeAssociations(str);
        return this.fkInCurrentModel;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.Class<?>[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v7, resolved type: java.lang.Class<?>[]} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Class<?>[] getParameterTypes(java.lang.reflect.Field r5, java.lang.Object r6, java.lang.Object[] r7) {
        /*
            r4 = this;
            boolean r0 = r4.isCharType(r5)
            r1 = 0
            r2 = 2
            r3 = 1
            if (r0 == 0) goto L_0x001a
            java.lang.String r5 = java.lang.String.valueOf(r6)
            r7[r3] = r5
            java.lang.Class[] r5 = new java.lang.Class[r2]
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r1] = r6
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r3] = r6
            goto L_0x005e
        L_0x001a:
            java.lang.Class r6 = r5.getType()
            boolean r6 = r6.isPrimitive()
            if (r6 == 0) goto L_0x0036
            java.lang.Class[] r6 = new java.lang.Class[r2]
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r1] = r7
            java.lang.Class r5 = r5.getType()
            java.lang.Class r5 = r4.getObjectType(r5)
            r6[r3] = r5
        L_0x0034:
            r5 = r6
            goto L_0x005e
        L_0x0036:
            java.lang.Class r6 = r5.getType()
            java.lang.String r6 = r6.getName()
            java.lang.String r7 = "java.util.Date"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0051
            java.lang.Class[] r5 = new java.lang.Class[r2]
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r5[r1] = r6
            java.lang.Class<java.lang.Long> r6 = java.lang.Long.class
            r5[r3] = r6
            goto L_0x005e
        L_0x0051:
            java.lang.Class[] r6 = new java.lang.Class[r2]
            java.lang.Class<java.lang.String> r7 = java.lang.String.class
            r6[r1] = r7
            java.lang.Class r5 = r5.getType()
            r6[r3] = r5
            goto L_0x0034
        L_0x005e:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.crud.DataHandler.getParameterTypes(java.lang.reflect.Field, java.lang.Object, java.lang.Object[]):java.lang.Class[]");
    }

    private Class<?> getObjectType(Class<?> cls) {
        if (cls == null || !cls.isPrimitive()) {
            return null;
        }
        String name = cls.getName();
        if ("int".equals(name)) {
            return Integer.class;
        }
        if ("short".equals(name)) {
            return Short.class;
        }
        if ("long".equals(name)) {
            return Long.class;
        }
        if ("float".equals(name)) {
            return Float.class;
        }
        if ("double".equals(name)) {
            return Double.class;
        }
        if ("boolean".equals(name)) {
            return Boolean.class;
        }
        if ("char".equals(name)) {
            return Character.class;
        }
        return null;
    }

    private Object getInitParamValue(Class<?> cls, Class<?> cls2) {
        String name = cls2.getName();
        if ("boolean".equals(name) || "java.lang.Boolean".equals(name)) {
            return false;
        }
        if ("float".equals(name) || "java.lang.Float".equals(name)) {
            return Float.valueOf(0.0f);
        }
        if ("double".equals(name) || "java.lang.Double".equals(name)) {
            return Double.valueOf(Utils.DOUBLE_EPSILON);
        }
        if ("int".equals(name) || "java.lang.Integer".equals(name)) {
            return 0;
        }
        if ("long".equals(name) || "java.lang.Long".equals(name)) {
            return 0L;
        }
        if ("short".equals(name) || "java.lang.Short".equals(name)) {
            return 0;
        }
        if ("char".equals(name) || "java.lang.Character".equals(name)) {
            return ' ';
        }
        if ("[B".equals(name) || "[Ljava.lang.Byte;".equals(name)) {
            return new byte[0];
        }
        if ("java.lang.String".equals(name)) {
            return "";
        }
        if (cls == cls2) {
            return null;
        }
        return createInstanceFromClass(cls2);
    }

    private boolean isCharType(Field field) {
        String name = field.getType().getName();
        return name.equals("char") || name.endsWith("Character");
    }

    private boolean isPrimitiveBooleanType(Field field) {
        return "boolean".equals(field.getType().getName());
    }

    private void putFieldsValueDependsOnSaveOrUpdate(LitePalSupport litePalSupport, Field field, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (isUpdating()) {
            if (!isFieldWithDefaultValue(litePalSupport, field)) {
                putContentValuesForUpdate(litePalSupport, field, contentValues);
            }
        } else if (isSaving()) {
            putContentValuesForSave(litePalSupport, field, contentValues);
        }
    }

    private boolean isUpdating() {
        return UpdateHandler.class.getName().equals(getClass().getName());
    }

    private boolean isSaving() {
        return SaveHandler.class.getName().equals(getClass().getName());
    }

    private boolean isFieldWithDefaultValue(LitePalSupport litePalSupport, Field field) throws IllegalAccessException, SecurityException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException {
        LitePalSupport emptyModel = getEmptyModel(litePalSupport);
        Object fieldValue = getFieldValue(litePalSupport, field);
        Object fieldValue2 = getFieldValue(emptyModel, field);
        if (fieldValue == null || fieldValue2 == null) {
            return fieldValue == fieldValue2;
        }
        return fieldValue.toString().equals(fieldValue2.toString());
    }

    private String makeGetterMethodName(Field field) {
        String str;
        String name = field.getName();
        if (isPrimitiveBooleanType(field)) {
            if (name.matches("^is[A-Z]{1}.*$")) {
                name = name.substring(2);
            }
            str = "is";
        } else {
            str = "get";
        }
        if (name.matches("^[a-z]{1}[A-Z]{1}.*")) {
            return str + name;
        }
        return str + BaseUtility.capitalize(name);
    }

    private String makeSetterMethodName(Field field) {
        if (isPrimitiveBooleanType(field) && field.getName().matches("^is[A-Z]{1}.*$")) {
            return "set" + field.getName().substring(2);
        } else if (field.getName().matches("^[a-z]{1}[A-Z]{1}.*")) {
            return "set" + field.getName();
        } else {
            return "set" + BaseUtility.capitalize(field.getName());
        }
    }

    private String genGetColumnMethod(Field field) {
        Class<?> cls;
        if (isCollection(field.getType())) {
            cls = getGenericTypeClass(field);
        } else {
            cls = field.getType();
        }
        return genGetColumnMethod(cls);
    }

    private String genGetColumnMethod(Class<?> cls) {
        String str;
        if (cls.isPrimitive()) {
            str = BaseUtility.capitalize(cls.getName());
        } else {
            str = cls.getSimpleName();
        }
        String str2 = "get" + str;
        if (!"getBoolean".equals(str2)) {
            if ("getChar".equals(str2) || "getCharacter".equals(str2)) {
                return "getString";
            }
            if ("getDate".equals(str2)) {
                return "getLong";
            }
            if (!"getInteger".equals(str2)) {
                return str2;
            }
        }
        return "getInt";
    }

    private String[] getCustomizedColumns(String[] strArr, List<Field> list, List<AssociationsInfo> list2) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(strArr));
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        for (Field name : list) {
            arrayList2.add(name.getName());
        }
        boolean z = false;
        for (int i = 0; i < arrayList.size(); i++) {
            String str = (String) arrayList.get(i);
            if (BaseUtility.containsIgnoreCases(arrayList2, str)) {
                arrayList3.add(Integer.valueOf(i));
            } else if (isIdColumn(str)) {
                if ("_id".equalsIgnoreCase(str)) {
                    arrayList.set(i, BaseUtility.changeCase("id"));
                }
                z = true;
            }
        }
        for (int size = arrayList3.size() - 1; size >= 0; size--) {
            arrayList4.add((String) arrayList.remove(((Integer) arrayList3.get(size)).intValue()));
        }
        for (Field next : list) {
            if (BaseUtility.containsIgnoreCases(arrayList4, next.getName())) {
                arrayList5.add(next);
            }
        }
        list.clear();
        list.addAll(arrayList5);
        if (list2 != null && list2.size() > 0) {
            for (int i2 = 0; i2 < list2.size(); i2++) {
                arrayList.add(getForeignKeyColumnName(DBUtility.getTableNameByClassName(list2.get(i2).getAssociatedClassName())));
            }
        }
        if (!z) {
            arrayList.add(BaseUtility.changeCase("id"));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    private void analyzeAssociations(String str) {
        Collection<AssociationsInfo> associationInfo = getAssociationInfo(str);
        List<AssociationsInfo> list = this.fkInCurrentModel;
        if (list == null) {
            this.fkInCurrentModel = new ArrayList();
        } else {
            list.clear();
        }
        List<AssociationsInfo> list2 = this.fkInOtherModel;
        if (list2 == null) {
            this.fkInOtherModel = new ArrayList();
        } else {
            list2.clear();
        }
        for (AssociationsInfo next : associationInfo) {
            if (next.getAssociationType() == 2 || next.getAssociationType() == 1) {
                if (next.getClassHoldsForeignKey().equals(str)) {
                    this.fkInCurrentModel.add(next);
                } else {
                    this.fkInOtherModel.add(next);
                }
            } else if (next.getAssociationType() == 3) {
                this.fkInOtherModel.add(next);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x0172 A[LOOP:1: B:21:0x00ef->B:42:0x0172, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x018d  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01a7  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x000d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x016b A[EDGE_INSN: B:71:0x016b->B:41:0x016b ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setAssociatedModel(org.litepal.crud.LitePalSupport r27) {
        /*
            r26 = this;
            r7 = r26
            r0 = r27
            java.util.List<org.litepal.crud.model.AssociationsInfo> r1 = r7.fkInOtherModel
            if (r1 != 0) goto L_0x0009
            return
        L_0x0009:
            java.util.Iterator r8 = r1.iterator()
        L_0x000d:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L_0x01ab
            java.lang.Object r1 = r8.next()
            r9 = r1
            org.litepal.crud.model.AssociationsInfo r9 = (org.litepal.crud.model.AssociationsInfo) r9
            java.lang.String r10 = r9.getAssociatedClassName()
            int r2 = r9.getAssociationType()
            r3 = 3
            r4 = 0
            r11 = 1
            if (r2 != r3) goto L_0x0029
            r12 = 1
            goto L_0x002a
        L_0x0029:
            r12 = 0
        L_0x002a:
            java.util.List r13 = r7.getSupportedFields(r10)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.util.List r14 = r7.getSupportedGenericFields(r10)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r15 = 2
            if (r12 == 0) goto L_0x0097
            java.lang.String r2 = r27.getTableName()     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r3 = org.litepal.util.DBUtility.getTableNameByClassName(r10)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r5 = org.litepal.util.DBUtility.getIntermediateTableName(r2, r3)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r6.<init>()     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r1 = "select * from "
            r6.append(r1)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r6.append(r3)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r1 = " a inner join "
            r6.append(r1)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r6.append(r5)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r1 = " b on a.id = b."
            r6.append(r1)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r1.<init>()     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r1.append(r3)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r3 = "_id"
            r1.append(r3)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r6.append(r1)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r1 = " where b."
            r6.append(r1)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r6.append(r2)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r1 = "_id = ?"
            r6.append(r1)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String[] r1 = new java.lang.String[r15]     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r2 = r6.toString()     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r2 = org.litepal.util.BaseUtility.changeCase(r2)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r1[r4] = r2     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            long r2 = r27.getBaseObjId()     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r1[r11] = r2     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            android.database.Cursor r1 = org.litepal.Operator.findBySQL(r1)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            goto L_0x00dc
        L_0x0097:
            java.lang.String r1 = r9.getSelfClassName()     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r1 = org.litepal.util.DBUtility.getTableNameByClassName(r1)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r1 = r7.getForeignKeyColumnName(r1)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r2 = org.litepal.util.DBUtility.getTableNameByClassName(r10)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            android.database.sqlite.SQLiteDatabase r3 = r7.mDatabase     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r18 = org.litepal.util.BaseUtility.changeCase(r2)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r19 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r2.<init>()     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r2.append(r1)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r1 = "=?"
            r2.append(r1)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r20 = r2.toString()     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String[] r1 = new java.lang.String[r11]     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            long r5 = r27.getBaseObjId()     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            java.lang.String r2 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r1[r4] = r2     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
            r22 = 0
            r23 = 0
            r24 = 0
            r25 = 0
            r17 = r3
            r21 = r1
            android.database.Cursor r1 = r17.query(r18, r19, r20, r21, r22, r23, r24, r25)     // Catch:{ Exception -> 0x0196, all -> 0x0192 }
        L_0x00dc:
            r6 = r1
            if (r6 == 0) goto L_0x0189
            boolean r1 = r6.moveToFirst()     // Catch:{ Exception -> 0x0183, all -> 0x017d }
            if (r1 == 0) goto L_0x0189
            android.util.SparseArray r16 = new android.util.SparseArray     // Catch:{ Exception -> 0x0183, all -> 0x017d }
            r16.<init>()     // Catch:{ Exception -> 0x0183, all -> 0x017d }
            java.util.HashMap r5 = new java.util.HashMap     // Catch:{ Exception -> 0x0183, all -> 0x017d }
            r5.<init>()     // Catch:{ Exception -> 0x0183, all -> 0x017d }
        L_0x00ef:
            java.lang.Class r1 = java.lang.Class.forName(r10)     // Catch:{ Exception -> 0x0183, all -> 0x017d }
            java.lang.Object r1 = r7.createInstanceFromClass(r1)     // Catch:{ Exception -> 0x0183, all -> 0x017d }
            r4 = r1
            org.litepal.crud.LitePalSupport r4 = (org.litepal.crud.LitePalSupport) r4     // Catch:{ Exception -> 0x0183, all -> 0x017d }
            java.lang.String r1 = "id"
            int r1 = r6.getColumnIndexOrThrow(r1)     // Catch:{ Exception -> 0x0183, all -> 0x017d }
            long r1 = r6.getLong(r1)     // Catch:{ Exception -> 0x0183, all -> 0x017d }
            r7.giveBaseObjIdValue(r4, r1)     // Catch:{ Exception -> 0x0183, all -> 0x017d }
            r17 = 0
            r1 = r26
            r2 = r4
            r3 = r13
            r11 = r4
            r4 = r17
            r15 = r5
            r5 = r6
            r19 = r6
            r6 = r16
            r1.setValueToModel(r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r7.setGenericValueToModel(r11, r14, r15)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            int r1 = r9.getAssociationType()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r2 = 2
            if (r1 == r2) goto L_0x0135
            if (r12 == 0) goto L_0x0126
            goto L_0x0135
        L_0x0126:
            int r1 = r9.getAssociationType()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r3 = 1
            if (r1 != r3) goto L_0x0165
            java.lang.reflect.Field r1 = r9.getAssociateOtherModelFromSelf()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r7.setFieldValue(r0, r1, r11)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            goto L_0x0165
        L_0x0135:
            r3 = 1
            java.lang.reflect.Field r1 = r9.getAssociateOtherModelFromSelf()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.Object r4 = r7.getFieldValue(r0, r1)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.util.Collection r4 = (java.util.Collection) r4     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            if (r4 != 0) goto L_0x0162
            java.lang.Class r4 = r1.getType()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            boolean r4 = r7.isList(r4)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            if (r4 == 0) goto L_0x0152
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r4.<init>()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            goto L_0x0157
        L_0x0152:
            java.util.HashSet r4 = new java.util.HashSet     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r4.<init>()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
        L_0x0157:
            java.lang.String r1 = r1.getName()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            java.lang.Class r5 = r27.getClass()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            org.litepal.crud.DynamicExecutor.setField(r0, r1, r4, r5)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
        L_0x0162:
            r4.add(r11)     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
        L_0x0165:
            boolean r1 = r19.moveToNext()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            if (r1 != 0) goto L_0x0172
            r16.clear()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            r15.clear()     // Catch:{ Exception -> 0x017b, all -> 0x0179 }
            goto L_0x018b
        L_0x0172:
            r5 = r15
            r6 = r19
            r11 = 1
            r15 = 2
            goto L_0x00ef
        L_0x0179:
            r0 = move-exception
            goto L_0x0180
        L_0x017b:
            r0 = move-exception
            goto L_0x0186
        L_0x017d:
            r0 = move-exception
            r19 = r6
        L_0x0180:
            r16 = r19
            goto L_0x01a5
        L_0x0183:
            r0 = move-exception
            r19 = r6
        L_0x0186:
            r1 = r19
            goto L_0x0198
        L_0x0189:
            r19 = r6
        L_0x018b:
            if (r19 == 0) goto L_0x000d
            r19.close()
            goto L_0x000d
        L_0x0192:
            r0 = move-exception
            r16 = 0
            goto L_0x01a5
        L_0x0196:
            r0 = move-exception
            r1 = 0
        L_0x0198:
            org.litepal.exceptions.LitePalSupportException r2 = new org.litepal.exceptions.LitePalSupportException     // Catch:{ all -> 0x01a2 }
            java.lang.String r3 = r0.getMessage()     // Catch:{ all -> 0x01a2 }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x01a2 }
            throw r2     // Catch:{ all -> 0x01a2 }
        L_0x01a2:
            r0 = move-exception
            r16 = r1
        L_0x01a5:
            if (r16 == 0) goto L_0x01aa
            r16.close()
        L_0x01aa:
            throw r0
        L_0x01ab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.crud.DataHandler.setAssociatedModel(org.litepal.crud.LitePalSupport):void");
    }

    private void setToModelByReflection(Object obj, Field field, int i, String str, Cursor cursor) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        boolean invoke = cursor.getClass().getMethod(str, new Class[]{Integer.TYPE}).invoke(cursor, new Object[]{Integer.valueOf(i)});
        if (field.getType() == Boolean.TYPE || field.getType() == Boolean.class) {
            if ("0".equals(String.valueOf(invoke))) {
                invoke = false;
            } else if ("1".equals(String.valueOf(invoke))) {
                invoke = true;
            }
        } else if (field.getType() == Character.TYPE || field.getType() == Character.class) {
            invoke = Character.valueOf(((String) invoke).charAt(0));
        } else if (field.getType() == Date.class) {
            long longValue = ((Long) invoke).longValue();
            invoke = longValue == LongCompanionObject.MAX_VALUE ? null : new Date(longValue);
        }
        if (isCollection(field.getType())) {
            Collection collection = (Collection) DynamicExecutor.getField(obj, field.getName(), obj.getClass());
            if (collection == null) {
                if (isList(field.getType())) {
                    collection = new ArrayList();
                } else {
                    collection = new HashSet();
                }
                DynamicExecutor.setField(obj, field.getName(), collection, obj.getClass());
            }
            String genericTypeName = getGenericTypeName(field);
            if ("java.lang.String".equals(genericTypeName)) {
                Encrypt encrypt = (Encrypt) field.getAnnotation(Encrypt.class);
                if (encrypt != null) {
                    invoke = decryptValue(encrypt.algorithm(), invoke);
                }
            } else if (obj.getClass().getName().equals(genericTypeName) && ((invoke instanceof Long) || (invoke instanceof Integer))) {
                invoke = Operator.find(obj.getClass(), ((Long) invoke).longValue());
            }
            collection.add(invoke);
            return;
        }
        Encrypt encrypt2 = (Encrypt) field.getAnnotation(Encrypt.class);
        if (encrypt2 != null && "java.lang.String".equals(field.getType().getName())) {
            invoke = decryptValue(encrypt2.algorithm(), invoke);
        }
        DynamicExecutor.setField(obj, field.getName(), invoke, obj.getClass());
    }

    /* access modifiers changed from: protected */
    public Object decryptValue(String str, Object obj) {
        return (str == null || obj == null || !"AES".equalsIgnoreCase(str)) ? obj : CipherUtil.aesDecrypt((String) obj);
    }

    class QueryInfoCache {
        Field field;
        String getMethodName;

        QueryInfoCache() {
        }
    }
}
