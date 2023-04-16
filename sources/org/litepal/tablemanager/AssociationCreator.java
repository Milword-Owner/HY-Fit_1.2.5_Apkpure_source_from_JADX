package org.litepal.tablemanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import org.litepal.exceptions.DatabaseGenerateException;
import org.litepal.tablemanager.model.AssociationsModel;
import org.litepal.tablemanager.model.ColumnModel;
import org.litepal.tablemanager.model.GenericModel;
import org.litepal.util.Const;
import org.litepal.util.DBUtility;
import org.litepal.util.LitePalLog;

public abstract class AssociationCreator extends Generator {
    /* access modifiers changed from: protected */
    public abstract void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z);

    /* access modifiers changed from: protected */
    public void addOrUpdateAssociation(SQLiteDatabase sQLiteDatabase, boolean z) {
        addAssociations(getAllAssociations(), sQLiteDatabase, z);
    }

    /* access modifiers changed from: protected */
    public String generateCreateTableSQL(String str, Collection<ColumnModel> collection, boolean z) {
        StringBuilder sb = new StringBuilder("create table ");
        sb.append(str);
        sb.append(" (");
        if (z) {
            sb.append("id integer primary key autoincrement,");
        }
        if (isContainsOnlyIdField(collection)) {
            sb.deleteCharAt(sb.length() - 1);
        }
        boolean z2 = false;
        for (ColumnModel next : collection) {
            if (!next.isIdColumn()) {
                if (z2) {
                    sb.append(", ");
                }
                sb.append(next.getColumnName());
                sb.append(" ");
                sb.append(next.getColumnType());
                if (!next.isNullable()) {
                    sb.append(" not null");
                }
                if (next.isUnique()) {
                    sb.append(" unique");
                }
                String defaultValue = next.getDefaultValue();
                if (!TextUtils.isEmpty(defaultValue)) {
                    sb.append(" default ");
                    sb.append(defaultValue);
                }
                z2 = true;
            }
        }
        sb.append(")");
        LitePalLog.m2964d(Generator.TAG, "create table sql is >> " + sb);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public List<String> generateCreateIndexSQLs(String str, Collection<ColumnModel> collection) {
        ArrayList arrayList = new ArrayList();
        for (ColumnModel next : collection) {
            if (next.hasIndex()) {
                arrayList.add(generateCreateIndexSQL(str, next));
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public String generateDropTableSQL(String str) {
        return "drop table if exists " + str;
    }

    /* access modifiers changed from: protected */
    public String generateAddColumnSQL(String str, ColumnModel columnModel) {
        StringBuilder sb = new StringBuilder();
        sb.append("alter table ");
        sb.append(str);
        sb.append(" add column ");
        sb.append(columnModel.getColumnName());
        sb.append(" ");
        sb.append(columnModel.getColumnType());
        if (!columnModel.isNullable()) {
            sb.append(" not null");
        }
        if (columnModel.isUnique()) {
            sb.append(" unique");
        }
        String defaultValue = columnModel.getDefaultValue();
        if (!TextUtils.isEmpty(defaultValue)) {
            sb.append(" default ");
            sb.append(defaultValue);
        } else if (!columnModel.isNullable()) {
            if ("integer".equalsIgnoreCase(columnModel.getColumnType())) {
                defaultValue = "0";
            } else if (ViewHierarchyConstants.TEXT_KEY.equalsIgnoreCase(columnModel.getColumnType())) {
                defaultValue = "''";
            } else if ("real".equalsIgnoreCase(columnModel.getColumnType())) {
                defaultValue = "0.0";
            }
            sb.append(" default ");
            sb.append(defaultValue);
        }
        LitePalLog.m2964d(Generator.TAG, "add column sql is >> " + sb);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String generateCreateIndexSQL(String str, ColumnModel columnModel) {
        StringBuilder sb = new StringBuilder();
        if (columnModel.hasIndex()) {
            sb.append("create index ");
            sb.append(DBUtility.getIndexName(str, columnModel.getColumnName()));
            sb.append(" on ");
            sb.append(str);
            sb.append(" (");
            sb.append(columnModel.getColumnName());
            sb.append(")");
            LitePalLog.m2964d(Generator.TAG, "create table index sql is >> " + sb);
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public boolean isForeignKeyColumnFormat(String str) {
        if (TextUtils.isEmpty(str) || !str.toLowerCase(Locale.US).endsWith("_id") || str.equalsIgnoreCase("_id")) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void giveTableSchemaACopy(java.lang.String r6, int r7, android.database.sqlite.SQLiteDatabase r8) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "select * from "
            r0.<init>(r1)
            java.lang.String r1 = "table_schema"
            r0.append(r1)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "giveTableSchemaACopy SQL is >> "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r2 = r2.toString()
            java.lang.String r3 = "Generator"
            org.litepal.util.LitePalLog.m2964d(r3, r2)
            r2 = 0
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0059 }
            android.database.Cursor r0 = r8.rawQuery(r0, r2)     // Catch:{ Exception -> 0x0059 }
            boolean r3 = r5.isNeedtoGiveACopy(r0, r6)     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            if (r3 == 0) goto L_0x004b
            android.content.ContentValues r3 = new android.content.ContentValues     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            r3.<init>()     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            java.lang.String r4 = "name"
            java.lang.String r6 = org.litepal.util.BaseUtility.changeCase(r6)     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            r3.put(r4, r6)     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            java.lang.String r6 = "type"
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            r3.put(r6, r7)     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            r8.insert(r1, r2, r3)     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
        L_0x004b:
            if (r0 == 0) goto L_0x0062
            r0.close()
            goto L_0x0062
        L_0x0051:
            r6 = move-exception
            goto L_0x0063
        L_0x0053:
            r6 = move-exception
            r2 = r0
            goto L_0x005a
        L_0x0056:
            r6 = move-exception
            r0 = r2
            goto L_0x0063
        L_0x0059:
            r6 = move-exception
        L_0x005a:
            r6.printStackTrace()     // Catch:{ all -> 0x0056 }
            if (r2 == 0) goto L_0x0062
            r2.close()
        L_0x0062:
            return
        L_0x0063:
            if (r0 == 0) goto L_0x0068
            r0.close()
        L_0x0068:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.tablemanager.AssociationCreator.giveTableSchemaACopy(java.lang.String, int, android.database.sqlite.SQLiteDatabase):void");
    }

    private boolean isNeedtoGiveACopy(Cursor cursor, String str) {
        return !isValueExists(cursor, str) && !isSpecialTable(str);
    }

    private boolean isValueExists(Cursor cursor, String str) {
        if (cursor.moveToFirst()) {
            while (!cursor.getString(cursor.getColumnIndexOrThrow("name")).equalsIgnoreCase(str)) {
                if (!cursor.moveToNext()) {
                }
            }
            return true;
        }
        return false;
    }

    private boolean isSpecialTable(String str) {
        return Const.TableSchema.TABLE_NAME.equalsIgnoreCase(str);
    }

    private void addAssociations(Collection<AssociationsModel> collection, SQLiteDatabase sQLiteDatabase, boolean z) {
        for (AssociationsModel next : collection) {
            if (2 == next.getAssociationType() || 1 == next.getAssociationType()) {
                addForeignKeyColumn(next.getTableName(), next.getAssociatedTableName(), next.getTableHoldsForeignKey(), sQLiteDatabase);
            } else if (3 == next.getAssociationType()) {
                createIntermediateTable(next.getTableName(), next.getAssociatedTableName(), sQLiteDatabase, z);
            }
        }
        for (GenericModel createGenericTable : getGenericModels()) {
            createGenericTable(createGenericTable, sQLiteDatabase, z);
        }
    }

    private void createIntermediateTable(String str, String str2, SQLiteDatabase sQLiteDatabase, boolean z) {
        ArrayList arrayList = new ArrayList();
        ColumnModel columnModel = new ColumnModel();
        columnModel.setColumnName(str + "_id");
        columnModel.setColumnType("integer");
        ColumnModel columnModel2 = new ColumnModel();
        columnModel2.setColumnName(str2 + "_id");
        columnModel2.setColumnType("integer");
        arrayList.add(columnModel);
        arrayList.add(columnModel2);
        String intermediateTableName = DBUtility.getIntermediateTableName(str, str2);
        ArrayList arrayList2 = new ArrayList();
        if (!DBUtility.isTableExists(intermediateTableName, sQLiteDatabase)) {
            arrayList2.add(generateCreateTableSQL(intermediateTableName, arrayList, false));
        } else if (z) {
            arrayList2.add(generateDropTableSQL(intermediateTableName));
            arrayList2.add(generateCreateTableSQL(intermediateTableName, arrayList, false));
        }
        execute(arrayList2, sQLiteDatabase);
        giveTableSchemaACopy(intermediateTableName, 1, sQLiteDatabase);
    }

    private void createGenericTable(GenericModel genericModel, SQLiteDatabase sQLiteDatabase, boolean z) {
        String tableName = genericModel.getTableName();
        String valueColumnName = genericModel.getValueColumnName();
        String valueColumnType = genericModel.getValueColumnType();
        String valueIdColumnName = genericModel.getValueIdColumnName();
        ArrayList arrayList = new ArrayList();
        ColumnModel columnModel = new ColumnModel();
        columnModel.setColumnName(valueColumnName);
        columnModel.setColumnType(valueColumnType);
        ColumnModel columnModel2 = new ColumnModel();
        columnModel2.setColumnName(valueIdColumnName);
        columnModel2.setColumnType("integer");
        arrayList.add(columnModel);
        arrayList.add(columnModel2);
        ArrayList arrayList2 = new ArrayList();
        if (!DBUtility.isTableExists(tableName, sQLiteDatabase)) {
            arrayList2.add(generateCreateTableSQL(tableName, arrayList, false));
        } else if (z) {
            arrayList2.add(generateDropTableSQL(tableName));
            arrayList2.add(generateCreateTableSQL(tableName, arrayList, false));
        }
        execute(arrayList2, sQLiteDatabase);
        giveTableSchemaACopy(tableName, 2, sQLiteDatabase);
    }

    /* access modifiers changed from: protected */
    public void addForeignKeyColumn(String str, String str2, String str3, SQLiteDatabase sQLiteDatabase) {
        if (!DBUtility.isTableExists(str, sQLiteDatabase)) {
            throw new DatabaseGenerateException(DatabaseGenerateException.TABLE_DOES_NOT_EXIST + str);
        } else if (DBUtility.isTableExists(str2, sQLiteDatabase)) {
            String str4 = null;
            if (str.equals(str3)) {
                str4 = getForeignKeyColumnName(str2);
            } else if (str2.equals(str3)) {
                str4 = getForeignKeyColumnName(str);
            }
            if (!DBUtility.isColumnExists(str4, str3, sQLiteDatabase)) {
                ColumnModel columnModel = new ColumnModel();
                columnModel.setColumnName(str4);
                columnModel.setColumnType("integer");
                ArrayList arrayList = new ArrayList();
                arrayList.add(generateAddColumnSQL(str3, columnModel));
                execute(arrayList, sQLiteDatabase);
                return;
            }
            LitePalLog.m2964d(Generator.TAG, "column " + str4 + " is already exist, no need to add one");
        } else {
            throw new DatabaseGenerateException(DatabaseGenerateException.TABLE_DOES_NOT_EXIST + str2);
        }
    }

    private boolean isContainsOnlyIdField(Collection<ColumnModel> collection) {
        for (ColumnModel isIdColumn : collection) {
            if (!isIdColumn.isIdColumn()) {
                return false;
            }
        }
        return true;
    }
}
