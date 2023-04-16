package org.litepal.crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.litepal.Operator;
import org.litepal.annotation.Encrypt;
import org.litepal.exceptions.LitePalSupportException;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

public class UpdateHandler extends DataHandler {
    public UpdateHandler(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    /* access modifiers changed from: package-private */
    public int onUpdate(LitePalSupport litePalSupport, long j) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<Field> supportedFields = getSupportedFields(litePalSupport.getClassName());
        updateGenericTables(litePalSupport, getSupportedGenericFields(litePalSupport.getClassName()), j);
        ContentValues contentValues = new ContentValues();
        putFieldsValue(litePalSupport, supportedFields, contentValues);
        putFieldsToDefaultValue(litePalSupport, contentValues, j);
        if (contentValues.size() <= 0) {
            return 0;
        }
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        String tableName = litePalSupport.getTableName();
        return sQLiteDatabase.update(tableName, contentValues, "id = " + j, (String[]) null);
    }

    public int onUpdate(Class<?> cls, long j, ContentValues contentValues) {
        if (contentValues.size() <= 0) {
            return 0;
        }
        convertContentValues(contentValues);
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        String tableName = getTableName(cls);
        return sQLiteDatabase.update(tableName, contentValues, "id = " + j, (String[]) null);
    }

    /* access modifiers changed from: package-private */
    public int onUpdateAll(LitePalSupport litePalSupport, String... strArr) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        List<Field> supportedFields = getSupportedFields(litePalSupport.getClassName());
        List<Field> supportedGenericFields = getSupportedGenericFields(litePalSupport.getClassName());
        long[] jArr = null;
        if (!supportedGenericFields.isEmpty()) {
            List<?> find = Operator.select("id").where(strArr).find(litePalSupport.getClass());
            if (find.size() > 0) {
                jArr = new long[find.size()];
                for (int i = 0; i < jArr.length; i++) {
                    jArr[i] = ((LitePalSupport) find.get(i)).getBaseObjId();
                }
                updateGenericTables(litePalSupport, supportedGenericFields, jArr);
            }
        }
        ContentValues contentValues = new ContentValues();
        putFieldsValue(litePalSupport, supportedFields, contentValues);
        putFieldsToDefaultValue(litePalSupport, contentValues, jArr);
        return doUpdateAllAction(litePalSupport.getTableName(), contentValues, strArr);
    }

    public int onUpdateAll(String str, ContentValues contentValues, String... strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        convertContentValues(contentValues);
        return doUpdateAllAction(str, contentValues, strArr);
    }

    private int doUpdateAllAction(String str, ContentValues contentValues, String... strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (contentValues.size() > 0) {
            return this.mDatabase.update(str, contentValues, getWhereClause(strArr), getWhereArgs(strArr));
        }
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x008d, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0093, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0094, code lost:
        r7 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0096, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a0, code lost:
        throw new org.litepal.exceptions.LitePalSupportException(r0.getMessage(), r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0096 A[ExcHandler: Exception (r0v3 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:1:0x0004] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void putFieldsToDefaultValue(org.litepal.crud.LitePalSupport r16, android.content.ContentValues r17, long... r18) {
        /*
            r15 = this;
            r1 = r15
            r0 = r18
            r2 = 0
            org.litepal.crud.LitePalSupport r3 = r15.getEmptyModel(r16)     // Catch:{ NoSuchFieldException -> 0x00a1, Exception -> 0x0096 }
            java.lang.Class r4 = r3.getClass()     // Catch:{ NoSuchFieldException -> 0x00a1, Exception -> 0x0096 }
            java.util.List r5 = r16.getFieldsToSetToDefault()     // Catch:{ NoSuchFieldException -> 0x00a1, Exception -> 0x0096 }
            java.util.Iterator r5 = r5.iterator()     // Catch:{ NoSuchFieldException -> 0x00a1, Exception -> 0x0096 }
            r6 = r2
        L_0x0015:
            boolean r7 = r5.hasNext()     // Catch:{ NoSuchFieldException -> 0x0093, Exception -> 0x0096 }
            if (r7 == 0) goto L_0x0092
            java.lang.Object r7 = r5.next()     // Catch:{ NoSuchFieldException -> 0x0093, Exception -> 0x0096 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ NoSuchFieldException -> 0x0093, Exception -> 0x0096 }
            boolean r8 = r15.isIdColumn(r7)     // Catch:{ NoSuchFieldException -> 0x0093, Exception -> 0x0096 }
            if (r8 != 0) goto L_0x008f
            java.lang.reflect.Field r6 = r4.getDeclaredField(r7)     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            java.lang.Class r8 = r6.getType()     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            boolean r8 = r15.isCollection(r8)     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            if (r8 == 0) goto L_0x0086
            if (r0 == 0) goto L_0x0083
            int r8 = r0.length     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            if (r8 <= 0) goto L_0x0083
            java.lang.String r8 = r15.getGenericTypeName(r6)     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            boolean r8 = org.litepal.util.BaseUtility.isGenericTypeSupported(r8)     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            if (r8 == 0) goto L_0x0083
            java.lang.String r8 = r16.getClassName()     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            java.lang.String r6 = r6.getName()     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            java.lang.String r6 = org.litepal.util.DBUtility.getGenericTableName(r8, r6)     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            java.lang.String r8 = r16.getClassName()     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            java.lang.String r8 = org.litepal.util.DBUtility.getGenericValueIdColumnName(r8)     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            r9.<init>()     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            int r10 = r0.length     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            r11 = 0
            r12 = 0
        L_0x0060:
            if (r11 >= r10) goto L_0x007a
            r13 = r0[r11]     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            if (r12 == 0) goto L_0x006b
            java.lang.String r12 = " or "
            r9.append(r12)     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        L_0x006b:
            r9.append(r8)     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            java.lang.String r12 = " = "
            r9.append(r12)     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            r9.append(r13)     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            int r11 = r11 + 1
            r12 = 1
            goto L_0x0060
        L_0x007a:
            android.database.sqlite.SQLiteDatabase r8 = r1.mDatabase     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            java.lang.String r9 = r9.toString()     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
            r8.delete(r6, r9, r2)     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        L_0x0083:
            r8 = r17
            goto L_0x008b
        L_0x0086:
            r8 = r17
            r15.putContentValuesForUpdate(r3, r6, r8)     // Catch:{ NoSuchFieldException -> 0x008d, Exception -> 0x0096 }
        L_0x008b:
            r6 = r7
            goto L_0x0015
        L_0x008d:
            r0 = move-exception
            goto L_0x00a3
        L_0x008f:
            r8 = r17
            goto L_0x0015
        L_0x0092:
            return
        L_0x0093:
            r0 = move-exception
            r7 = r6
            goto L_0x00a3
        L_0x0096:
            r0 = move-exception
            org.litepal.exceptions.LitePalSupportException r2 = new org.litepal.exceptions.LitePalSupportException
            java.lang.String r3 = r0.getMessage()
            r2.<init>(r3, r0)
            throw r2
        L_0x00a1:
            r0 = move-exception
            r7 = r2
        L_0x00a3:
            org.litepal.exceptions.LitePalSupportException r2 = new org.litepal.exceptions.LitePalSupportException
            java.lang.String r3 = r16.getClassName()
            java.lang.String r3 = org.litepal.exceptions.LitePalSupportException.noSuchFieldExceptioin(r3, r7)
            r2.<init>(r3, r0)
            goto L_0x00b2
        L_0x00b1:
            throw r2
        L_0x00b2:
            goto L_0x00b1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.crud.UpdateHandler.putFieldsToDefaultValue(org.litepal.crud.LitePalSupport, android.content.ContentValues, long[]):void");
    }

    private int doUpdateAssociations(LitePalSupport litePalSupport, long j, ContentValues contentValues) {
        analyzeAssociations(litePalSupport);
        updateSelfTableForeignKey(litePalSupport, contentValues);
        return updateAssociatedTableForeignKey(litePalSupport, j) + 0;
    }

    private void analyzeAssociations(LitePalSupport litePalSupport) {
        try {
            analyzeAssociatedModels(litePalSupport, getAssociationInfo(litePalSupport.getClassName()));
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    private void updateSelfTableForeignKey(LitePalSupport litePalSupport, ContentValues contentValues) {
        Map<String, Long> associatedModelsMapWithoutFK = litePalSupport.getAssociatedModelsMapWithoutFK();
        for (String next : associatedModelsMapWithoutFK.keySet()) {
            contentValues.put(getForeignKeyColumnName(next), associatedModelsMapWithoutFK.get(next));
        }
    }

    private int updateAssociatedTableForeignKey(LitePalSupport litePalSupport, long j) {
        Map<String, Set<Long>> associatedModelsMapWithFK = litePalSupport.getAssociatedModelsMapWithFK();
        ContentValues contentValues = new ContentValues();
        for (String next : associatedModelsMapWithFK.keySet()) {
            contentValues.clear();
            contentValues.put(getForeignKeyColumnName(litePalSupport.getTableName()), Long.valueOf(j));
            Set set = associatedModelsMapWithFK.get(next);
            if (set != null && !set.isEmpty()) {
                return this.mDatabase.update(next, contentValues, getWhereOfIdsWithOr((Collection<Long>) set), (String[]) null);
            }
        }
        return 0;
    }

    private void updateGenericTables(LitePalSupport litePalSupport, List<Field> list, long... jArr) throws IllegalAccessException, InvocationTargetException {
        Iterator<Field> it;
        Iterator it2;
        long[] jArr2 = jArr;
        if (jArr2 != null && jArr2.length > 0) {
            Iterator<Field> it3 = list.iterator();
            while (it3.hasNext()) {
                Field next = it3.next();
                Encrypt encrypt = (Encrypt) next.getAnnotation(Encrypt.class);
                String genericTypeName = getGenericTypeName(next);
                String algorithm = (encrypt == null || !"java.lang.String".equals(genericTypeName)) ? null : encrypt.algorithm();
                next.setAccessible(true);
                Collection collection = (Collection) next.get(litePalSupport);
                if (collection != null && !collection.isEmpty()) {
                    String genericTableName = DBUtility.getGenericTableName(litePalSupport.getClassName(), next.getName());
                    String genericValueIdColumnName = DBUtility.getGenericValueIdColumnName(litePalSupport.getClassName());
                    int length = jArr2.length;
                    int i = 0;
                    while (i < length) {
                        long j = jArr2[i];
                        SQLiteDatabase sQLiteDatabase = this.mDatabase;
                        sQLiteDatabase.delete(genericTableName, genericValueIdColumnName + " = ?", new String[]{String.valueOf(j)});
                        Iterator it4 = collection.iterator();
                        while (it4.hasNext()) {
                            Object next2 = it4.next();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put(genericValueIdColumnName, Long.valueOf(j));
                            Object encryptValue = encryptValue(algorithm, next2);
                            if (litePalSupport.getClassName().equals(genericTypeName)) {
                                LitePalSupport litePalSupport2 = (LitePalSupport) encryptValue;
                                if (litePalSupport2 != null) {
                                    long baseObjId = litePalSupport2.getBaseObjId();
                                    if (baseObjId > 0) {
                                        contentValues.put(DBUtility.getM2MSelfRefColumnName(next), Long.valueOf(baseObjId));
                                        it2 = it4;
                                        it = it3;
                                    }
                                }
                            } else {
                                it2 = it4;
                                it = it3;
                                DynamicExecutor.send(contentValues, "put", new Object[]{DBUtility.convertToValidColumnName(BaseUtility.changeCase(next.getName())), encryptValue}, contentValues.getClass(), new Class[]{String.class, getGenericTypeClass(next)});
                            }
                            this.mDatabase.insert(genericTableName, (String) null, contentValues);
                            it4 = it2;
                            it3 = it;
                        }
                        i++;
                        jArr2 = jArr;
                        it3 = it3;
                    }
                }
                jArr2 = jArr;
                it3 = it3;
            }
        }
    }

    private void convertContentValues(ContentValues contentValues) {
        if (Build.VERSION.SDK_INT >= 11) {
            HashMap hashMap = new HashMap();
            for (String next : contentValues.keySet()) {
                if (DBUtility.isFieldNameConflictWithSQLiteKeywords(next)) {
                    hashMap.put(next, contentValues.get(next));
                }
            }
            for (String str : hashMap.keySet()) {
                String convertToValidColumnName = DBUtility.convertToValidColumnName(str);
                Object obj = contentValues.get(str);
                contentValues.remove(str);
                if (obj == null) {
                    contentValues.putNull(convertToValidColumnName);
                } else {
                    String name = obj.getClass().getName();
                    if ("java.lang.Byte".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Byte) obj);
                    } else if ("[B".equals(name)) {
                        contentValues.put(convertToValidColumnName, (byte[]) obj);
                    } else if ("java.lang.Boolean".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Boolean) obj);
                    } else if ("java.lang.String".equals(name)) {
                        contentValues.put(convertToValidColumnName, (String) obj);
                    } else if ("java.lang.Float".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Float) obj);
                    } else if ("java.lang.Long".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Long) obj);
                    } else if ("java.lang.Integer".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Integer) obj);
                    } else if ("java.lang.Short".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Short) obj);
                    } else if ("java.lang.Double".equals(name)) {
                        contentValues.put(convertToValidColumnName, (Double) obj);
                    }
                }
            }
        }
    }
}
