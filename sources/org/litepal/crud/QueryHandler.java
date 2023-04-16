package org.litepal.crud;

import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

public class QueryHandler extends DataHandler {
    public QueryHandler(SQLiteDatabase sQLiteDatabase) {
        this.mDatabase = sQLiteDatabase;
    }

    public <T> T onFind(Class<T> cls, long j, boolean z) {
        Class<T> cls2 = cls;
        List<T> query = query(cls2, (String[]) null, "id = ?", new String[]{String.valueOf(j)}, (String) null, (String) null, (String) null, (String) null, getForeignKeyAssociations(cls.getName(), z));
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }

    public <T> T onFindFirst(Class<T> cls, boolean z) {
        List<T> query = query(cls, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, "id", "1", getForeignKeyAssociations(cls.getName(), z));
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }

    public <T> T onFindLast(Class<T> cls, boolean z) {
        List<T> query = query(cls, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, "id desc", "1", getForeignKeyAssociations(cls.getName(), z));
        if (query.size() > 0) {
            return query.get(0);
        }
        return null;
    }

    public <T> List<T> onFindAll(Class<T> cls, boolean z, long... jArr) {
        if (isAffectAllLines(jArr)) {
            return query(cls, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, "id", (String) null, getForeignKeyAssociations(cls.getName(), z));
        }
        return query(cls, (String[]) null, getWhereOfIdsWithOr(jArr), (String[]) null, (String) null, (String) null, "id", (String) null, getForeignKeyAssociations(cls.getName(), z));
    }

    public <T> List<T> onFind(Class<T> cls, String[] strArr, String[] strArr2, String str, String str2, boolean z) {
        String[] strArr3 = strArr2;
        BaseUtility.checkConditionsCorrect(strArr2);
        if (strArr3 != null && strArr3.length > 0) {
            strArr3[0] = DBUtility.convertWhereClauseToColumnName(strArr3[0]);
        }
        String convertOrderByClauseToValidName = DBUtility.convertOrderByClauseToValidName(str);
        return query(cls, strArr, getWhereClause(strArr2), getWhereArgs(strArr2), (String) null, (String) null, convertOrderByClauseToValidName, str2, getForeignKeyAssociations(cls.getName(), z));
    }

    public int onCount(String str, String[] strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return ((Integer) mathQuery(str, new String[]{"count(1)"}, strArr, Integer.TYPE)).intValue();
    }

    public double onAverage(String str, String str2, String[] strArr) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return ((Double) mathQuery(str, new String[]{"avg(" + str2 + ")"}, strArr, Double.TYPE)).doubleValue();
    }

    public <T> T onMax(String str, String str2, String[] strArr, Class<T> cls) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return mathQuery(str, new String[]{"max(" + str2 + ")"}, strArr, cls);
    }

    public <T> T onMin(String str, String str2, String[] strArr, Class<T> cls) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return mathQuery(str, new String[]{"min(" + str2 + ")"}, strArr, cls);
    }

    public <T> T onSum(String str, String str2, String[] strArr, Class<T> cls) {
        BaseUtility.checkConditionsCorrect(strArr);
        if (strArr != null && strArr.length > 0) {
            strArr[0] = DBUtility.convertWhereClauseToColumnName(strArr[0]);
        }
        return mathQuery(str, new String[]{"sum(" + str2 + ")"}, strArr, cls);
    }
}
