package org.litepal;

import java.util.ArrayList;
import java.util.List;
import org.litepal.parser.LitePalConfig;
import org.litepal.parser.LitePalParser;

public class LitePalDB {
    private List<String> classNames;
    private String dbName;
    private boolean isExternalStorage = false;
    private String storage;
    private int version;

    public static LitePalDB fromDefault(String str) {
        LitePalConfig parseLitePalConfiguration = LitePalParser.parseLitePalConfiguration();
        LitePalDB litePalDB = new LitePalDB(str, parseLitePalConfiguration.getVersion());
        litePalDB.setStorage(parseLitePalConfiguration.getStorage());
        litePalDB.setClassNames(parseLitePalConfiguration.getClassNames());
        return litePalDB;
    }

    public LitePalDB(String str, int i) {
        this.dbName = str;
        this.version = i;
    }

    public int getVersion() {
        return this.version;
    }

    public String getDbName() {
        return this.dbName;
    }

    public String getStorage() {
        return this.storage;
    }

    public void setStorage(String str) {
        this.storage = str;
    }

    public boolean isExternalStorage() {
        return this.isExternalStorage;
    }

    public void setExternalStorage(boolean z) {
        this.isExternalStorage = z;
    }

    public List<String> getClassNames() {
        List<String> list = this.classNames;
        if (list == null) {
            this.classNames = new ArrayList();
            this.classNames.add("org.litepal.model.Table_Schema");
        } else if (list.isEmpty()) {
            this.classNames.add("org.litepal.model.Table_Schema");
        }
        return this.classNames;
    }

    public void addClassName(String str) {
        getClassNames().add(str);
    }

    /* access modifiers changed from: package-private */
    public void setClassNames(List<String> list) {
        this.classNames = list;
    }
}
