package org.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.litepal.parser.LitePalAttr;
import org.litepal.tablemanager.model.AssociationsModel;
import org.litepal.tablemanager.model.ColumnModel;
import org.litepal.tablemanager.model.GenericModel;
import org.litepal.tablemanager.model.TableModel;
import org.litepal.util.BaseUtility;
import org.litepal.util.Const;
import org.litepal.util.DBUtility;
import org.litepal.util.LitePalLog;

public abstract class AssociationUpdater extends Creator {
    public static final String TAG = "AssociationUpdater";
    private Collection<AssociationsModel> mAssociationModels;
    protected SQLiteDatabase mDb;

    /* access modifiers changed from: protected */
    public abstract void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z);

    /* access modifiers changed from: protected */
    public void addOrUpdateAssociation(SQLiteDatabase sQLiteDatabase, boolean z) {
        this.mAssociationModels = getAllAssociations();
        this.mDb = sQLiteDatabase;
        removeAssociations();
    }

    /* access modifiers changed from: protected */
    public List<String> getForeignKeyColumns(TableModel tableModel) {
        ArrayList arrayList = new ArrayList();
        for (ColumnModel next : getTableModelFromDB(tableModel.getTableName()).getColumnModels()) {
            String columnName = next.getColumnName();
            if (isForeignKeyColumnFormat(next.getColumnName()) && !tableModel.containsColumn(columnName)) {
                LitePalLog.m2964d(TAG, "getForeignKeyColumnNames >> foreign key column is " + columnName);
                arrayList.add(columnName);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public boolean isForeignKeyColumn(TableModel tableModel, String str) {
        return BaseUtility.containsIgnoreCases(getForeignKeyColumns(tableModel), str);
    }

    /* access modifiers changed from: protected */
    public TableModel getTableModelFromDB(String str) {
        return DBUtility.findPragmaTableInfo(str, this.mDb);
    }

    /* access modifiers changed from: protected */
    public void dropTables(List<String> list, SQLiteDatabase sQLiteDatabase) {
        if (list != null && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(generateDropTableSQL(list.get(i)));
            }
            execute(arrayList, sQLiteDatabase);
        }
    }

    /* access modifiers changed from: protected */
    public void removeColumns(Collection<String> collection, String str) {
        if (collection != null && !collection.isEmpty()) {
            execute(getRemoveColumnSQLs(collection, str), this.mDb);
        }
    }

    /* access modifiers changed from: protected */
    public void clearCopyInTableSchema(List<String> list) {
        if (list != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder("delete from ");
            sb.append(Const.TableSchema.TABLE_NAME);
            sb.append(" where");
            boolean z = false;
            for (String next : list) {
                if (z) {
                    sb.append(" or ");
                }
                z = true;
                sb.append(" lower(");
                sb.append("name");
                sb.append(") ");
                sb.append("=");
                sb.append(" lower('");
                sb.append(next);
                sb.append("')");
            }
            LitePalLog.m2964d(TAG, "clear table schema value sql is " + sb);
            ArrayList arrayList = new ArrayList();
            arrayList.add(sb.toString());
            execute(arrayList, this.mDb);
        }
    }

    private void removeAssociations() {
        removeForeignKeyColumns();
        removeIntermediateTables();
        removeGenericTables();
    }

    private void removeForeignKeyColumns() {
        for (String tableModel : LitePalAttr.getInstance().getClassNames()) {
            TableModel tableModel2 = getTableModel(tableModel);
            removeColumns(findForeignKeyToRemove(tableModel2), tableModel2.getTableName());
        }
    }

    private void removeIntermediateTables() {
        List<String> findIntermediateTablesToDrop = findIntermediateTablesToDrop();
        dropTables(findIntermediateTablesToDrop, this.mDb);
        clearCopyInTableSchema(findIntermediateTablesToDrop);
    }

    private void removeGenericTables() {
        List<String> findGenericTablesToDrop = findGenericTablesToDrop();
        dropTables(findGenericTablesToDrop, this.mDb);
        clearCopyInTableSchema(findGenericTablesToDrop);
    }

    private List<String> findForeignKeyToRemove(TableModel tableModel) {
        ArrayList arrayList = new ArrayList();
        List<String> foreignKeyColumns = getForeignKeyColumns(tableModel);
        String tableName = tableModel.getTableName();
        for (String next : foreignKeyColumns) {
            if (shouldDropForeignKey(tableName, DBUtility.getTableNameByForeignColumn(next))) {
                arrayList.add(next);
            }
        }
        LitePalLog.m2964d(TAG, "findForeignKeyToRemove >> " + tableModel.getTableName() + " " + arrayList);
        return arrayList;
    }

    private List<String> findIntermediateTablesToDrop() {
        ArrayList arrayList = new ArrayList();
        for (String next : DBUtility.findAllTableNames(this.mDb)) {
            if (DBUtility.isIntermediateTable(next, this.mDb)) {
                boolean z = true;
                for (AssociationsModel next2 : this.mAssociationModels) {
                    if (next2.getAssociationType() == 3 && next.equalsIgnoreCase(DBUtility.getIntermediateTableName(next2.getTableName(), next2.getAssociatedTableName()))) {
                        z = false;
                    }
                }
                if (z) {
                    arrayList.add(next);
                }
            }
        }
        LitePalLog.m2964d(TAG, "findIntermediateTablesToDrop >> " + arrayList);
        return arrayList;
    }

    private List<String> findGenericTablesToDrop() {
        ArrayList arrayList = new ArrayList();
        for (String next : DBUtility.findAllTableNames(this.mDb)) {
            if (DBUtility.isGenericTable(next, this.mDb)) {
                boolean z = true;
                Iterator<GenericModel> it = getGenericModels().iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (next.equalsIgnoreCase(it.next().getTableName())) {
                            z = false;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (z) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public String generateAlterToTempTableSQL(String str) {
        return "alter table " + str + " rename to " + getTempTableName(str);
    }

    private String generateCreateNewTableSQL(Collection<String> collection, TableModel tableModel) {
        for (String removeColumnModelByName : collection) {
            tableModel.removeColumnModelByName(removeColumnModelByName);
        }
        return generateCreateTableSQL(tableModel);
    }

    /* access modifiers changed from: protected */
    public String generateDataMigrationSQL(TableModel tableModel) {
        String tableName = tableModel.getTableName();
        Collection<ColumnModel> columnModels = tableModel.getColumnModels();
        if (columnModels.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ");
        sb.append(tableName);
        sb.append("(");
        boolean z = false;
        boolean z2 = false;
        for (ColumnModel next : columnModels) {
            if (z2) {
                sb.append(", ");
            }
            sb.append(next.getColumnName());
            z2 = true;
        }
        sb.append(") ");
        sb.append("select ");
        for (ColumnModel next2 : columnModels) {
            if (z) {
                sb.append(", ");
            }
            sb.append(next2.getColumnName());
            z = true;
        }
        sb.append(" from ");
        sb.append(getTempTableName(tableName));
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String generateDropTempTableSQL(String str) {
        return generateDropTableSQL(getTempTableName(str));
    }

    /* access modifiers changed from: protected */
    public String getTempTableName(String str) {
        return str + "_temp";
    }

    private List<String> getRemoveColumnSQLs(Collection<String> collection, String str) {
        TableModel tableModelFromDB = getTableModelFromDB(str);
        String generateAlterToTempTableSQL = generateAlterToTempTableSQL(str);
        LitePalLog.m2964d(TAG, "generateRemoveColumnSQL >> " + generateAlterToTempTableSQL);
        String generateCreateNewTableSQL = generateCreateNewTableSQL(collection, tableModelFromDB);
        LitePalLog.m2964d(TAG, "generateRemoveColumnSQL >> " + generateCreateNewTableSQL);
        String generateDataMigrationSQL = generateDataMigrationSQL(tableModelFromDB);
        LitePalLog.m2964d(TAG, "generateRemoveColumnSQL >> " + generateDataMigrationSQL);
        String generateDropTempTableSQL = generateDropTempTableSQL(str);
        LitePalLog.m2964d(TAG, "generateRemoveColumnSQL >> " + generateDropTempTableSQL);
        List<String> generateCreateIndexSQLs = generateCreateIndexSQLs(tableModelFromDB);
        ArrayList arrayList = new ArrayList();
        arrayList.add(generateAlterToTempTableSQL);
        arrayList.add(generateCreateNewTableSQL);
        arrayList.add(generateDataMigrationSQL);
        arrayList.add(generateDropTempTableSQL);
        arrayList.addAll(generateCreateIndexSQLs);
        return arrayList;
    }

    private boolean shouldDropForeignKey(String str, String str2) {
        for (AssociationsModel next : this.mAssociationModels) {
            if (next.getAssociationType() == 1) {
                if (!str.equalsIgnoreCase(next.getTableHoldsForeignKey())) {
                    continue;
                } else if (next.getTableName().equalsIgnoreCase(str)) {
                    if (isRelationCorrect(next, str, str2)) {
                        return false;
                    }
                } else if (next.getAssociatedTableName().equalsIgnoreCase(str) && isRelationCorrect(next, str2, str)) {
                    return false;
                }
            } else if (next.getAssociationType() == 2 && isRelationCorrect(next, str2, str)) {
                return false;
            }
        }
        return true;
    }

    private boolean isRelationCorrect(AssociationsModel associationsModel, String str, String str2) {
        return associationsModel.getTableName().equalsIgnoreCase(str) && associationsModel.getAssociatedTableName().equalsIgnoreCase(str2);
    }
}
