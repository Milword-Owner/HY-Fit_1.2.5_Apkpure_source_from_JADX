package org.litepal.crud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.litepal.annotation.Encrypt;
import org.litepal.crud.model.AssociationsInfo;
import org.litepal.exceptions.LitePalSupportException;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

public class SaveHandler extends DataHandler {
    private ContentValues values = new ContentValues();

    private boolean shouldGiveModelIdValue(String str, Class<?> cls, long j) {
        return (str == null || cls == null || j <= 0) ? false : true;
    }

    public SaveHandler(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    /* access modifiers changed from: package-private */
    public void onSave(LitePalSupport litePalSupport) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String className = litePalSupport.getClassName();
        List<Field> supportedFields = getSupportedFields(className);
        List<Field> supportedGenericFields = getSupportedGenericFields(className);
        Collection<AssociationsInfo> associationInfo = getAssociationInfo(className);
        if (!litePalSupport.isSaved()) {
            analyzeAssociatedModels(litePalSupport, associationInfo);
            doSaveAction(litePalSupport, supportedFields, supportedGenericFields);
            analyzeAssociatedModels(litePalSupport, associationInfo);
            return;
        }
        analyzeAssociatedModels(litePalSupport, associationInfo);
        doUpdateAction(litePalSupport, supportedFields, supportedGenericFields);
    }

    public <T extends LitePalSupport> void onSaveAll(Collection<T> collection) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (collection != null && collection.size() > 0) {
            LitePalSupport[] litePalSupportArr = (LitePalSupport[]) collection.toArray(new LitePalSupport[0]);
            String className = litePalSupportArr[0].getClassName();
            List<Field> supportedFields = getSupportedFields(className);
            List<Field> supportedGenericFields = getSupportedGenericFields(className);
            Collection<AssociationsInfo> associationInfo = getAssociationInfo(className);
            for (LitePalSupport litePalSupport : litePalSupportArr) {
                if (!litePalSupport.isSaved()) {
                    analyzeAssociatedModels(litePalSupport, associationInfo);
                    doSaveAction(litePalSupport, supportedFields, supportedGenericFields);
                    analyzeAssociatedModels(litePalSupport, associationInfo);
                } else {
                    analyzeAssociatedModels(litePalSupport, associationInfo);
                    doUpdateAction(litePalSupport, supportedFields, supportedGenericFields);
                }
                litePalSupport.clearAssociatedData();
            }
        }
    }

    private void doSaveAction(LitePalSupport litePalSupport, List<Field> list, List<Field> list2) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        this.values.clear();
        beforeSave(litePalSupport, list, this.values);
        afterSave(litePalSupport, list, list2, saving(litePalSupport, this.values));
    }

    private void beforeSave(LitePalSupport litePalSupport, List<Field> list, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        putFieldsValue(litePalSupport, list, contentValues);
        putForeignKeyValue(contentValues, litePalSupport);
    }

    private long saving(LitePalSupport litePalSupport, ContentValues contentValues) {
        if (contentValues.size() == 0) {
            contentValues.putNull("id");
        }
        return this.mDatabase.insert(litePalSupport.getTableName(), (String) null, contentValues);
    }

    private void afterSave(LitePalSupport litePalSupport, List<Field> list, List<Field> list2, long j) throws IllegalAccessException, InvocationTargetException {
        throwIfSaveFailed(j);
        assignIdValue(litePalSupport, getIdField(list), j);
        updateGenericTables(litePalSupport, list2, j);
        updateAssociatedTableWithFK(litePalSupport);
        insertIntermediateJoinTableValue(litePalSupport, false);
    }

    private void doUpdateAction(LitePalSupport litePalSupport, List<Field> list, List<Field> list2) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        this.values.clear();
        beforeUpdate(litePalSupport, list, this.values);
        updating(litePalSupport, this.values);
        afterUpdate(litePalSupport, list2);
    }

    private void beforeUpdate(LitePalSupport litePalSupport, List<Field> list, ContentValues contentValues) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        putFieldsValue(litePalSupport, list, contentValues);
        putForeignKeyValue(contentValues, litePalSupport);
        for (String putNull : litePalSupport.getListToClearSelfFK()) {
            contentValues.putNull(putNull);
        }
    }

    private void updating(LitePalSupport litePalSupport, ContentValues contentValues) {
        if (contentValues.size() > 0) {
            this.mDatabase.update(litePalSupport.getTableName(), contentValues, "id = ?", new String[]{String.valueOf(litePalSupport.getBaseObjId())});
        }
    }

    private void afterUpdate(LitePalSupport litePalSupport, List<Field> list) throws InvocationTargetException, IllegalAccessException {
        updateGenericTables(litePalSupport, list, litePalSupport.getBaseObjId());
        updateAssociatedTableWithFK(litePalSupport);
        insertIntermediateJoinTableValue(litePalSupport, true);
        clearFKValueInAssociatedTable(litePalSupport);
    }

    private Field getIdField(List<Field> list) {
        for (Field next : list) {
            if (isIdColumn(next.getName())) {
                return next;
            }
        }
        return null;
    }

    private void throwIfSaveFailed(long j) {
        if (j == -1) {
            throw new LitePalSupportException(LitePalSupportException.SAVE_FAILED);
        }
    }

    private void assignIdValue(LitePalSupport litePalSupport, Field field, long j) {
        try {
            giveBaseObjIdValue(litePalSupport, j);
            if (field != null) {
                giveModelIdValue(litePalSupport, field.getName(), field.getType(), j);
            }
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    private void giveModelIdValue(LitePalSupport litePalSupport, String str, Class<?> cls, long j) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Object obj;
        if (shouldGiveModelIdValue(str, cls, j)) {
            if (cls == Integer.TYPE || cls == Integer.class) {
                obj = Integer.valueOf((int) j);
            } else if (cls == Long.TYPE || cls == Long.class) {
                obj = Long.valueOf(j);
            } else {
                throw new LitePalSupportException(LitePalSupportException.ID_TYPE_INVALID_EXCEPTION);
            }
            DynamicExecutor.setField(litePalSupport, str, obj, litePalSupport.getClass());
        }
    }

    private void putForeignKeyValue(ContentValues contentValues, LitePalSupport litePalSupport) {
        Map<String, Long> associatedModelsMapWithoutFK = litePalSupport.getAssociatedModelsMapWithoutFK();
        for (String next : associatedModelsMapWithoutFK.keySet()) {
            contentValues.put(getForeignKeyColumnName(next), associatedModelsMapWithoutFK.get(next));
        }
    }

    private void updateAssociatedTableWithFK(LitePalSupport litePalSupport) {
        Map<String, Set<Long>> associatedModelsMapWithFK = litePalSupport.getAssociatedModelsMapWithFK();
        ContentValues contentValues = new ContentValues();
        for (String next : associatedModelsMapWithFK.keySet()) {
            contentValues.clear();
            contentValues.put(getForeignKeyColumnName(litePalSupport.getTableName()), Long.valueOf(litePalSupport.getBaseObjId()));
            Set set = associatedModelsMapWithFK.get(next);
            if (set != null && !set.isEmpty()) {
                this.mDatabase.update(next, contentValues, getWhereOfIdsWithOr((Collection<Long>) set), (String[]) null);
            }
        }
    }

    private void clearFKValueInAssociatedTable(LitePalSupport litePalSupport) {
        for (String update : litePalSupport.getListToClearAssociatedFK()) {
            String foreignKeyColumnName = getForeignKeyColumnName(litePalSupport.getTableName());
            ContentValues contentValues = new ContentValues();
            contentValues.putNull(foreignKeyColumnName);
            this.mDatabase.update(update, contentValues, foreignKeyColumnName + " = " + litePalSupport.getBaseObjId(), (String[]) null);
        }
    }

    private void insertIntermediateJoinTableValue(LitePalSupport litePalSupport, boolean z) {
        Map<String, List<Long>> associatedModelsMapForJoinTable = litePalSupport.getAssociatedModelsMapForJoinTable();
        ContentValues contentValues = new ContentValues();
        for (String next : associatedModelsMapForJoinTable.keySet()) {
            String intermediateTableName = getIntermediateTableName(litePalSupport, next);
            if (z) {
                this.mDatabase.delete(intermediateTableName, getWhereForJoinTableToDelete(litePalSupport), new String[]{String.valueOf(litePalSupport.getBaseObjId())});
            }
            for (Long longValue : associatedModelsMapForJoinTable.get(next)) {
                long longValue2 = longValue.longValue();
                contentValues.clear();
                contentValues.put(getForeignKeyColumnName(litePalSupport.getTableName()), Long.valueOf(litePalSupport.getBaseObjId()));
                contentValues.put(getForeignKeyColumnName(next), Long.valueOf(longValue2));
                this.mDatabase.insert(intermediateTableName, (String) null, contentValues);
            }
        }
    }

    private String getWhereForJoinTableToDelete(LitePalSupport litePalSupport) {
        return getForeignKeyColumnName(litePalSupport.getTableName()) + " = ?";
    }

    private void updateGenericTables(LitePalSupport litePalSupport, List<Field> list, long j) throws IllegalAccessException, InvocationTargetException {
        for (Field next : list) {
            Encrypt encrypt = (Encrypt) next.getAnnotation(Encrypt.class);
            String genericTypeName = getGenericTypeName(next);
            String algorithm = (encrypt == null || !"java.lang.String".equals(genericTypeName)) ? null : encrypt.algorithm();
            char c = 1;
            next.setAccessible(true);
            Collection collection = (Collection) next.get(litePalSupport);
            if (collection != null) {
                Log.d(DataHandler.TAG, "updateGenericTables: class name is " + litePalSupport.getClassName() + " , field name is " + next.getName());
                String genericTableName = DBUtility.getGenericTableName(litePalSupport.getClassName(), next.getName());
                String genericValueIdColumnName = DBUtility.getGenericValueIdColumnName(litePalSupport.getClassName());
                SQLiteDatabase sQLiteDatabase = this.mDatabase;
                sQLiteDatabase.delete(genericTableName, genericValueIdColumnName + " = ?", new String[]{String.valueOf(j)});
                for (Object next2 : collection) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(genericValueIdColumnName, Long.valueOf(j));
                    Object encryptValue = encryptValue(algorithm, next2);
                    if (litePalSupport.getClassName().equals(genericTypeName)) {
                        LitePalSupport litePalSupport2 = (LitePalSupport) encryptValue;
                        if (litePalSupport2 != null) {
                            long baseObjId = litePalSupport2.getBaseObjId();
                            if (baseObjId > 0) {
                                contentValues.put(DBUtility.getM2MSelfRefColumnName(next), Long.valueOf(baseObjId));
                            }
                        }
                    } else {
                        Object[] objArr = new Object[2];
                        objArr[0] = BaseUtility.changeCase(DBUtility.convertToValidColumnName(next.getName()));
                        objArr[c] = encryptValue;
                        Class[] clsArr = new Class[2];
                        clsArr[0] = String.class;
                        clsArr[c] = getGenericTypeClass(next);
                        DynamicExecutor.send(contentValues, "put", objArr, contentValues.getClass(), clsArr);
                    }
                    this.mDatabase.insert(genericTableName, (String) null, contentValues);
                    c = 1;
                }
            }
        }
    }
}
