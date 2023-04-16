package org.litepal.crud;

import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.baidubce.http.StatusCodes;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.litepal.Operator;
import org.litepal.crud.model.AssociationsInfo;
import org.litepal.exceptions.LitePalSupportException;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

public class DeleteHandler extends DataHandler {
    private List<String> foreignKeyTableToDelete;

    public DeleteHandler(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    /* access modifiers changed from: package-private */
    public int onDelete(LitePalSupport litePalSupport) {
        if (!litePalSupport.isSaved()) {
            return 0;
        }
        List<Field> supportedGenericFields = getSupportedGenericFields(litePalSupport.getClassName());
        deleteGenericData(litePalSupport.getClass(), supportedGenericFields, litePalSupport.getBaseObjId());
        Collection<AssociationsInfo> analyzeAssociations = analyzeAssociations(litePalSupport);
        int deleteCascade = deleteCascade(litePalSupport);
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        String tableName = litePalSupport.getTableName();
        int delete = deleteCascade + sQLiteDatabase.delete(tableName, "id = " + litePalSupport.getBaseObjId(), (String[]) null);
        clearAssociatedModelSaveState(litePalSupport, analyzeAssociations);
        return delete;
    }

    public int onDelete(Class<?> cls, long j) {
        deleteGenericData(cls, getSupportedGenericFields(cls.getName()), j);
        analyzeAssociations(cls);
        int deleteCascade = deleteCascade(cls, j);
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        String tableName = getTableName(cls);
        int delete = deleteCascade + sQLiteDatabase.delete(tableName, "id = " + j, (String[]) null);
        getForeignKeyTableToDelete().clear();
        return delete;
    }

    public int onDeleteAll(String str, String... strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return this.mDatabase.delete(str, getWhereClause(strArr), getWhereArgs(strArr));
    }

    public int onDeleteAll(Class<?> cls, String... strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        List<Field> supportedGenericFields = getSupportedGenericFields(cls.getName());
        if (!supportedGenericFields.isEmpty()) {
            List<?> find = Operator.select("id").where(strArr).find(cls);
            if (find.size() > 0) {
                long[] jArr = new long[find.size()];
                for (int i = 0; i < jArr.length; i++) {
                    jArr[i] = ((LitePalSupport) find.get(i)).getBaseObjId();
                }
                deleteGenericData(cls, supportedGenericFields, jArr);
            }
        }
        analyzeAssociations(cls);
        int deleteAllCascade = deleteAllCascade(cls, strArr) + this.mDatabase.delete(getTableName(cls), getWhereClause(strArr), getWhereArgs(strArr));
        getForeignKeyTableToDelete().clear();
        return deleteAllCascade;
    }

    private void analyzeAssociations(Class<?> cls) {
        for (AssociationsInfo next : getAssociationInfo(cls.getName())) {
            String tableNameByClassName = DBUtility.getTableNameByClassName(next.getAssociatedClassName());
            if (next.getAssociationType() == 2 || next.getAssociationType() == 1) {
                if (!cls.getName().equals(next.getClassHoldsForeignKey())) {
                    getForeignKeyTableToDelete().add(tableNameByClassName);
                }
            } else if (next.getAssociationType() == 3) {
                getForeignKeyTableToDelete().add(BaseUtility.changeCase(DBUtility.getIntermediateTableName(getTableName(cls), tableNameByClassName)));
            }
        }
    }

    private int deleteCascade(Class<?> cls, long j) {
        int i = 0;
        for (String delete : getForeignKeyTableToDelete()) {
            String foreignKeyColumnName = getForeignKeyColumnName(getTableName(cls));
            SQLiteDatabase sQLiteDatabase = this.mDatabase;
            i += sQLiteDatabase.delete(delete, foreignKeyColumnName + " = " + j, (String[]) null);
        }
        return i;
    }

    private int deleteAllCascade(Class<?> cls, String... strArr) {
        int i = 0;
        for (String next : getForeignKeyTableToDelete()) {
            String tableName = getTableName(cls);
            String foreignKeyColumnName = getForeignKeyColumnName(tableName);
            StringBuilder sb = new StringBuilder();
            sb.append(foreignKeyColumnName);
            sb.append(" in (select id from ");
            sb.append(tableName);
            if (strArr != null && strArr.length > 0) {
                sb.append(" where ");
                sb.append(buildConditionString(strArr));
            }
            sb.append(")");
            i += this.mDatabase.delete(next, BaseUtility.changeCase(sb.toString()), (String[]) null);
        }
        return i;
    }

    private String buildConditionString(String... strArr) {
        int length = strArr.length - 1;
        int i = 0;
        String str = strArr[0];
        while (i < length) {
            StringBuilder sb = new StringBuilder();
            sb.append("'");
            i++;
            sb.append(strArr[i]);
            sb.append("'");
            str = str.replaceFirst("\\?", sb.toString());
        }
        return str;
    }

    private Collection<AssociationsInfo> analyzeAssociations(LitePalSupport litePalSupport) {
        try {
            Collection<AssociationsInfo> associationInfo = getAssociationInfo(litePalSupport.getClassName());
            analyzeAssociatedModels(litePalSupport, associationInfo);
            return associationInfo;
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    private void clearAssociatedModelSaveState(LitePalSupport litePalSupport, Collection<AssociationsInfo> collection) {
        LitePalSupport associatedModel;
        try {
            for (AssociationsInfo next : collection) {
                if (next.getAssociationType() == 2 && !litePalSupport.getClassName().equals(next.getClassHoldsForeignKey())) {
                    Collection<LitePalSupport> associatedModels = getAssociatedModels(litePalSupport, next);
                    if (associatedModels != null && !associatedModels.isEmpty()) {
                        for (LitePalSupport next2 : associatedModels) {
                            if (next2 != null) {
                                next2.clearSavedState();
                            }
                        }
                    }
                } else if (next.getAssociationType() == 1 && (associatedModel = getAssociatedModel(litePalSupport, next)) != null) {
                    associatedModel.clearSavedState();
                }
            }
        } catch (Exception e) {
            throw new LitePalSupportException(e.getMessage(), e);
        }
    }

    private int deleteCascade(LitePalSupport litePalSupport) {
        return deleteAssociatedForeignKeyRows(litePalSupport) + deleteAssociatedJoinTableRows(litePalSupport);
    }

    private int deleteAssociatedForeignKeyRows(LitePalSupport litePalSupport) {
        int i = 0;
        for (String delete : litePalSupport.getAssociatedModelsMapWithFK().keySet()) {
            String foreignKeyColumnName = getForeignKeyColumnName(litePalSupport.getTableName());
            SQLiteDatabase sQLiteDatabase = this.mDatabase;
            i += sQLiteDatabase.delete(delete, foreignKeyColumnName + " = " + litePalSupport.getBaseObjId(), (String[]) null);
        }
        return i;
    }

    private int deleteAssociatedJoinTableRows(LitePalSupport litePalSupport) {
        int i = 0;
        for (String intermediateTableName : litePalSupport.getAssociatedModelsMapForJoinTable().keySet()) {
            String intermediateTableName2 = DBUtility.getIntermediateTableName(litePalSupport.getTableName(), intermediateTableName);
            String foreignKeyColumnName = getForeignKeyColumnName(litePalSupport.getTableName());
            SQLiteDatabase sQLiteDatabase = this.mDatabase;
            i += sQLiteDatabase.delete(intermediateTableName2, foreignKeyColumnName + " = " + litePalSupport.getBaseObjId(), (String[]) null);
        }
        return i;
    }

    private List<String> getForeignKeyTableToDelete() {
        if (this.foreignKeyTableToDelete == null) {
            this.foreignKeyTableToDelete = new ArrayList();
        }
        return this.foreignKeyTableToDelete;
    }

    private void deleteGenericData(Class<?> cls, List<Field> list, long... jArr) {
        int i;
        for (Field name : list) {
            String genericTableName = DBUtility.getGenericTableName(cls.getName(), name.getName());
            String genericValueIdColumnName = DBUtility.getGenericValueIdColumnName(cls.getName());
            int length = jArr.length;
            int i2 = (length - 1) / StatusCodes.INTERNAL_ERROR;
            int i3 = 0;
            while (i3 <= i2) {
                StringBuilder sb = new StringBuilder();
                int i4 = StatusCodes.INTERNAL_ERROR * i3;
                boolean z = false;
                while (true) {
                    i = i3 + 1;
                    if (i4 < StatusCodes.INTERNAL_ERROR * i && i4 < length) {
                        long j = jArr[i4];
                        if (z) {
                            sb.append(" or ");
                        }
                        sb.append(genericValueIdColumnName);
                        sb.append(" = ");
                        sb.append(j);
                        i4++;
                        z = true;
                    }
                }
                if (!TextUtils.isEmpty(sb.toString())) {
                    this.mDatabase.delete(genericTableName, sb.toString(), (String[]) null);
                }
                i3 = i;
            }
        }
    }
}
