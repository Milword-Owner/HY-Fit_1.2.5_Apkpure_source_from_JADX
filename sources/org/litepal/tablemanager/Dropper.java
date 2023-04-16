package org.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.litepal.tablemanager.model.TableModel;
import org.litepal.util.BaseUtility;

public class Dropper extends AssociationUpdater {
    private Collection<TableModel> mTableModels;

    /* access modifiers changed from: protected */
    public void createOrUpgradeTable(SQLiteDatabase sQLiteDatabase, boolean z) {
        this.mTableModels = getAllTableModels();
        this.mDb = sQLiteDatabase;
        dropTables();
    }

    private void dropTables() {
        List<String> findTablesToDrop = findTablesToDrop();
        dropTables(findTablesToDrop, this.mDb);
        clearCopyInTableSchema(findTablesToDrop);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005c, code lost:
        if (r1 == null) goto L_0x0061;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005e, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0061, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0053, code lost:
        if (r1 != null) goto L_0x005e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<java.lang.String> findTablesToDrop() {
        /*
            r10 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.sqlite.SQLiteDatabase r2 = r10.mDb     // Catch:{ Exception -> 0x0058 }
            java.lang.String r3 = "table_schema"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0058 }
            boolean r2 = r1.moveToFirst()     // Catch:{ Exception -> 0x0058 }
            if (r2 == 0) goto L_0x0053
        L_0x001a:
            java.lang.String r2 = "name"
            int r2 = r1.getColumnIndexOrThrow(r2)     // Catch:{ Exception -> 0x0058 }
            java.lang.String r2 = r1.getString(r2)     // Catch:{ Exception -> 0x0058 }
            java.lang.String r3 = "type"
            int r3 = r1.getColumnIndexOrThrow(r3)     // Catch:{ Exception -> 0x0058 }
            int r3 = r1.getInt(r3)     // Catch:{ Exception -> 0x0058 }
            boolean r3 = r10.shouldDropThisTable(r2, r3)     // Catch:{ Exception -> 0x0058 }
            if (r3 == 0) goto L_0x004d
            java.lang.String r3 = "AssociationUpdater"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0058 }
            r4.<init>()     // Catch:{ Exception -> 0x0058 }
            java.lang.String r5 = "need to drop "
            r4.append(r5)     // Catch:{ Exception -> 0x0058 }
            r4.append(r2)     // Catch:{ Exception -> 0x0058 }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x0058 }
            org.litepal.util.LitePalLog.m2964d(r3, r4)     // Catch:{ Exception -> 0x0058 }
            r0.add(r2)     // Catch:{ Exception -> 0x0058 }
        L_0x004d:
            boolean r2 = r1.moveToNext()     // Catch:{ Exception -> 0x0058 }
            if (r2 != 0) goto L_0x001a
        L_0x0053:
            if (r1 == 0) goto L_0x0061
            goto L_0x005e
        L_0x0056:
            r0 = move-exception
            goto L_0x0062
        L_0x0058:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ all -> 0x0056 }
            if (r1 == 0) goto L_0x0061
        L_0x005e:
            r1.close()
        L_0x0061:
            return r0
        L_0x0062:
            if (r1 == 0) goto L_0x0067
            r1.close()
        L_0x0067:
            goto L_0x0069
        L_0x0068:
            throw r0
        L_0x0069:
            goto L_0x0068
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.tablemanager.Dropper.findTablesToDrop():java.util.List");
    }

    private List<String> pickTableNamesFromTableModels() {
        ArrayList arrayList = new ArrayList();
        for (TableModel tableName : this.mTableModels) {
            arrayList.add(tableName.getTableName());
        }
        return arrayList;
    }

    private boolean shouldDropThisTable(String str, int i) {
        return !BaseUtility.containsIgnoreCases(pickTableNamesFromTableModels(), str) && i == 0;
    }
}
