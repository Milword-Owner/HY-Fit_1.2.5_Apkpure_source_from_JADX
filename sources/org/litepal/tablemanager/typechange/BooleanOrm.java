package org.litepal.tablemanager.typechange;

public class BooleanOrm extends OrmChange {
    public String object2Relation(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("boolean") || str.equals("java.lang.Boolean")) {
            return "integer";
        }
        return null;
    }
}
