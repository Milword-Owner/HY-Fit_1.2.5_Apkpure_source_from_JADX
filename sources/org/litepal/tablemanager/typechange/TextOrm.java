package org.litepal.tablemanager.typechange;

import com.facebook.appevents.internal.ViewHierarchyConstants;

public class TextOrm extends OrmChange {
    public String object2Relation(String str) {
        if (str == null) {
            return null;
        }
        if (str.equals("char") || str.equals("java.lang.Character") || str.equals("java.lang.String")) {
            return ViewHierarchyConstants.TEXT_KEY;
        }
        return null;
    }
}
