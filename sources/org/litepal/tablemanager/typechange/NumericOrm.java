package org.litepal.tablemanager.typechange;

public class NumericOrm extends OrmChange {
    public String object2Relation(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("int") || str.equals("java.lang.Integer") || str.equals("long") || str.equals("java.lang.Long") || str.equals("short") || str.equals("java.lang.Short")) {
            return "integer";
        }
        return null;
    }
}
