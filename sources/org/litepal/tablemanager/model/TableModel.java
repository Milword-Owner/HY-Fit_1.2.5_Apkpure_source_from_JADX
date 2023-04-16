package org.litepal.tablemanager.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.litepal.util.BaseUtility;

public class TableModel {
    private String className;
    private Map<String, ColumnModel> columnModelMap = new HashMap();
    private String tableName;

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String str) {
        this.tableName = str;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String str) {
        this.className = str;
    }

    public void addColumnModel(ColumnModel columnModel) {
        this.columnModelMap.put(BaseUtility.changeCase(columnModel.getColumnName()), columnModel);
    }

    public Collection<ColumnModel> getColumnModels() {
        return this.columnModelMap.values();
    }

    public ColumnModel getColumnModelByName(String str) {
        return this.columnModelMap.get(BaseUtility.changeCase(str));
    }

    public void removeColumnModelByName(String str) {
        this.columnModelMap.remove(BaseUtility.changeCase(str));
    }

    public boolean containsColumn(String str) {
        return this.columnModelMap.containsKey(BaseUtility.changeCase(str));
    }
}
