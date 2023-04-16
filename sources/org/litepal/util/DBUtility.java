package org.litepal.util;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Pair;
import com.baidu.mobstat.Config;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.litepal.exceptions.DatabaseGenerateException;
import org.litepal.tablemanager.model.ColumnModel;
import org.litepal.tablemanager.model.TableModel;
import org.litepal.util.Const;

public class DBUtility {
    private static final String KEYWORDS_COLUMN_SUFFIX = "_lpcolumn";
    private static final String REG_COLLECTION = "\\s+(not\\s+)?(in)\\s*\\(";
    private static final String REG_FUZZY = "\\s+(not\\s+)?(like|between)\\s+";
    private static final String REG_OPERATOR = "\\s*(=|!=|<>|<|>)";
    private static final String SQLITE_KEYWORDS = ",abort,add,after,all,alter,and,as,asc,autoincrement,before,begin,between,by,cascade,check,collate,column,commit,conflict,constraint,create,cross,database,deferrable,deferred,delete,desc,distinct,drop,each,end,escape,except,exclusive,exists,foreign,from,glob,group,having,in,index,inner,insert,intersect,into,is,isnull,join,like,limit,match,natural,not,notnull,null,of,offset,on,or,order,outer,plan,pragma,primary,query,raise,references,regexp,reindex,release,rename,replace,restrict,right,rollback,row,savepoint,select,set,table,temp,temporary,then,to,transaction,trigger,union,unique,update,using,vacuum,values,view,virtual,when,where,";
    private static final String TAG = "DBUtility";

    private DBUtility() {
    }

    public static String getTableNameByClassName(String str) {
        if (TextUtils.isEmpty(str) || '.' == str.charAt(str.length() - 1)) {
            return null;
        }
        return str.substring(str.lastIndexOf(".") + 1);
    }

    public static String getIndexName(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        return str + Config.replace + str2 + "_index";
    }

    public static List<String> getTableNameListByClassNameList(List<String> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null && !list.isEmpty()) {
            for (String tableNameByClassName : list) {
                arrayList.add(getTableNameByClassName(tableNameByClassName));
            }
        }
        return arrayList;
    }

    public static String getTableNameByForeignColumn(String str) {
        if (TextUtils.isEmpty(str) || !str.toLowerCase(Locale.US).endsWith("_id")) {
            return null;
        }
        return str.substring(0, str.length() - 3);
    }

    public static String getIntermediateTableName(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        if (str.toLowerCase(Locale.US).compareTo(str2.toLowerCase(Locale.US)) <= 0) {
            return str + Config.replace + str2;
        }
        return str2 + Config.replace + str;
    }

    public static String getGenericTableName(String str, String str2) {
        String tableNameByClassName = getTableNameByClassName(str);
        return BaseUtility.changeCase(tableNameByClassName + Config.replace + str2);
    }

    public static String getGenericValueIdColumnName(String str) {
        return BaseUtility.changeCase(getTableNameByClassName(str) + "_id");
    }

    public static String getM2MSelfRefColumnName(Field field) {
        return BaseUtility.changeCase(field.getName() + "_id");
    }

    public static boolean isIntermediateTable(String str, SQLiteDatabase sQLiteDatabase) {
        if (TextUtils.isEmpty(str) || !str.matches("[0-9a-zA-Z]+_[0-9a-zA-Z]+")) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query(Const.TableSchema.TABLE_NAME, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            if (cursor.moveToFirst()) {
                while (true) {
                    if (!str.equalsIgnoreCase(cursor.getString(cursor.getColumnIndexOrThrow("name")))) {
                        if (!cursor.moveToNext()) {
                            break;
                        }
                    } else if (cursor.getInt(cursor.getColumnIndexOrThrow("type")) == 1) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        return true;
                    }
                }
            }
            if (cursor == null) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (cursor == null) {
                return false;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        cursor.close();
        return false;
    }

    public static boolean isGenericTable(String str, SQLiteDatabase sQLiteDatabase) {
        if (TextUtils.isEmpty(str) || !str.matches("[0-9a-zA-Z]+_[0-9a-zA-Z]+")) {
            return false;
        }
        Cursor cursor = null;
        try {
            cursor = sQLiteDatabase.query(Const.TableSchema.TABLE_NAME, (String[]) null, (String) null, (String[]) null, (String) null, (String) null, (String) null);
            if (cursor.moveToFirst()) {
                while (true) {
                    if (!str.equalsIgnoreCase(cursor.getString(cursor.getColumnIndexOrThrow("name")))) {
                        if (!cursor.moveToNext()) {
                            break;
                        }
                    } else if (cursor.getInt(cursor.getColumnIndexOrThrow("type")) == 2) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        return true;
                    }
                }
            }
            if (cursor == null) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (cursor == null) {
                return false;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        cursor.close();
        return false;
    }

    public static boolean isTableExists(String str, SQLiteDatabase sQLiteDatabase) {
        try {
            return BaseUtility.containsIgnoreCases(findAllTableNames(sQLiteDatabase), str);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: type inference failed for: r0v2, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v4, types: [android.database.Cursor] */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0047, code lost:
        if (r0 != 0) goto L_0x0049;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0049, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0053, code lost:
        if (r0 == 0) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0056, code lost:
        return r1;
     */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isColumnExists(java.lang.String r4, java.lang.String r5, android.database.sqlite.SQLiteDatabase r6) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            r1 = 0
            if (r0 != 0) goto L_0x005d
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 == 0) goto L_0x000e
            goto L_0x005d
        L_0x000e:
            r0 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x004f }
            r2.<init>()     // Catch:{ Exception -> 0x004f }
            java.lang.String r3 = "pragma table_info("
            r2.append(r3)     // Catch:{ Exception -> 0x004f }
            r2.append(r5)     // Catch:{ Exception -> 0x004f }
            java.lang.String r5 = ")"
            r2.append(r5)     // Catch:{ Exception -> 0x004f }
            java.lang.String r5 = r2.toString()     // Catch:{ Exception -> 0x004f }
            android.database.Cursor r0 = r6.rawQuery(r5, r0)     // Catch:{ Exception -> 0x004f }
            boolean r5 = r0.moveToFirst()     // Catch:{ Exception -> 0x004f }
            if (r5 == 0) goto L_0x0047
        L_0x002f:
            java.lang.String r5 = "name"
            int r5 = r0.getColumnIndexOrThrow(r5)     // Catch:{ Exception -> 0x004f }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x004f }
            boolean r5 = r4.equalsIgnoreCase(r5)     // Catch:{ Exception -> 0x004f }
            if (r5 == 0) goto L_0x0041
            r1 = 1
            goto L_0x0047
        L_0x0041:
            boolean r5 = r0.moveToNext()     // Catch:{ Exception -> 0x004f }
            if (r5 != 0) goto L_0x002f
        L_0x0047:
            if (r0 == 0) goto L_0x0056
        L_0x0049:
            r0.close()
            goto L_0x0056
        L_0x004d:
            r4 = move-exception
            goto L_0x0057
        L_0x004f:
            r4 = move-exception
            r4.printStackTrace()     // Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x0056
            goto L_0x0049
        L_0x0056:
            return r1
        L_0x0057:
            if (r0 == 0) goto L_0x005c
            r0.close()
        L_0x005c:
            throw r4
        L_0x005d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.util.DBUtility.isColumnExists(java.lang.String, java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
    }

    public static List<String> findAllTableNames(SQLiteDatabase sQLiteDatabase) {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor rawQuery = sQLiteDatabase.rawQuery("select * from sqlite_master where type = ?", new String[]{"table"});
            if (rawQuery.moveToFirst()) {
                do {
                    String string = rawQuery.getString(rawQuery.getColumnIndexOrThrow("tbl_name"));
                    if (!arrayList.contains(string)) {
                        arrayList.add(string);
                    }
                } while (rawQuery.moveToNext());
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseGenerateException(e.getMessage());
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static TableModel findPragmaTableInfo(String str, SQLiteDatabase sQLiteDatabase) {
        if (isTableExists(str, sQLiteDatabase)) {
            Pair<Set<String>, Set<String>> findIndexedColumns = findIndexedColumns(str, sQLiteDatabase);
            Set set = (Set) findIndexedColumns.first;
            Set set2 = (Set) findIndexedColumns.second;
            TableModel tableModel = new TableModel();
            tableModel.setTableName(str);
            Cursor cursor = null;
            try {
                cursor = sQLiteDatabase.rawQuery("pragma table_info(" + str + ")", (String[]) null);
                if (cursor.moveToFirst()) {
                    do {
                        ColumnModel columnModel = new ColumnModel();
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                        String string2 = cursor.getString(cursor.getColumnIndexOrThrow("type"));
                        boolean z = true;
                        if (cursor.getInt(cursor.getColumnIndexOrThrow("notnull")) == 1) {
                            z = false;
                        }
                        boolean contains = set2.contains(string);
                        boolean contains2 = set.contains(string);
                        String string3 = cursor.getString(cursor.getColumnIndexOrThrow("dflt_value"));
                        columnModel.setColumnName(string);
                        columnModel.setColumnType(string2);
                        columnModel.setNullable(z);
                        columnModel.setUnique(contains);
                        columnModel.setHasIndex(contains2);
                        String str2 = "";
                        if (string3 != null) {
                            str2 = string3.replace("'", str2);
                        }
                        columnModel.setDefaultValue(str2);
                        tableModel.addColumnModel(columnModel);
                    } while (cursor.moveToNext());
                }
                if (cursor != null) {
                    cursor.close();
                }
                return tableModel;
            } catch (Exception e) {
                e.printStackTrace();
                throw new DatabaseGenerateException(e.getMessage());
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        } else {
            throw new DatabaseGenerateException(DatabaseGenerateException.TABLE_DOES_NOT_EXIST_WHEN_EXECUTING + str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00b3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.util.Pair<java.util.Set<java.lang.String>, java.util.Set<java.lang.String>> findIndexedColumns(java.lang.String r10, android.database.sqlite.SQLiteDatabase r11) {
        /*
            java.lang.String r0 = "name"
            java.lang.String r1 = ")"
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.HashSet r3 = new java.util.HashSet
            r3.<init>()
            r4 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009b, all -> 0x0097 }
            r5.<init>()     // Catch:{ Exception -> 0x009b, all -> 0x0097 }
            java.lang.String r6 = "pragma index_list("
            r5.append(r6)     // Catch:{ Exception -> 0x009b, all -> 0x0097 }
            r5.append(r10)     // Catch:{ Exception -> 0x009b, all -> 0x0097 }
            r5.append(r1)     // Catch:{ Exception -> 0x009b, all -> 0x0097 }
            java.lang.String r10 = r5.toString()     // Catch:{ Exception -> 0x009b, all -> 0x0097 }
            android.database.Cursor r10 = r11.rawQuery(r10, r4)     // Catch:{ Exception -> 0x009b, all -> 0x0097 }
            boolean r5 = r10.moveToFirst()     // Catch:{ Exception -> 0x0093, all -> 0x0090 }
            if (r5 == 0) goto L_0x0080
            r5 = r4
        L_0x002e:
            java.lang.String r6 = "unique"
            int r6 = r10.getColumnIndexOrThrow(r6)     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            int r6 = r10.getInt(r6)     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            r7 = 1
            if (r6 != r7) goto L_0x003c
            goto L_0x003d
        L_0x003c:
            r7 = 0
        L_0x003d:
            int r6 = r10.getColumnIndexOrThrow(r0)     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            java.lang.String r6 = r10.getString(r6)     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            r8.<init>()     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            java.lang.String r9 = "pragma index_info("
            r8.append(r9)     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            r8.append(r6)     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            r8.append(r1)     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            java.lang.String r6 = r8.toString()     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            android.database.Cursor r5 = r11.rawQuery(r6, r4)     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            boolean r6 = r5.moveToFirst()     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            if (r6 == 0) goto L_0x0074
            int r6 = r5.getColumnIndexOrThrow(r0)     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            java.lang.String r6 = r5.getString(r6)     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            if (r7 == 0) goto L_0x0071
            r3.add(r6)     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            goto L_0x0074
        L_0x0071:
            r2.add(r6)     // Catch:{ Exception -> 0x007e, all -> 0x007c }
        L_0x0074:
            boolean r6 = r10.moveToNext()     // Catch:{ Exception -> 0x007e, all -> 0x007c }
            if (r6 != 0) goto L_0x002e
            r4 = r5
            goto L_0x0080
        L_0x007c:
            r11 = move-exception
            goto L_0x00ac
        L_0x007e:
            r11 = move-exception
            goto L_0x0095
        L_0x0080:
            if (r10 == 0) goto L_0x0085
            r10.close()
        L_0x0085:
            if (r4 == 0) goto L_0x008a
            r4.close()
        L_0x008a:
            android.util.Pair r10 = new android.util.Pair
            r10.<init>(r2, r3)
            return r10
        L_0x0090:
            r11 = move-exception
            r5 = r4
            goto L_0x00ac
        L_0x0093:
            r11 = move-exception
            r5 = r4
        L_0x0095:
            r4 = r10
            goto L_0x009d
        L_0x0097:
            r11 = move-exception
            r10 = r4
            r5 = r10
            goto L_0x00ac
        L_0x009b:
            r11 = move-exception
            r5 = r4
        L_0x009d:
            r11.printStackTrace()     // Catch:{ all -> 0x00aa }
            org.litepal.exceptions.DatabaseGenerateException r10 = new org.litepal.exceptions.DatabaseGenerateException     // Catch:{ all -> 0x00aa }
            java.lang.String r11 = r11.getMessage()     // Catch:{ all -> 0x00aa }
            r10.<init>(r11)     // Catch:{ all -> 0x00aa }
            throw r10     // Catch:{ all -> 0x00aa }
        L_0x00aa:
            r11 = move-exception
            r10 = r4
        L_0x00ac:
            if (r10 == 0) goto L_0x00b1
            r10.close()
        L_0x00b1:
            if (r5 == 0) goto L_0x00b6
            r5.close()
        L_0x00b6:
            goto L_0x00b8
        L_0x00b7:
            throw r11
        L_0x00b8:
            goto L_0x00b7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.litepal.util.DBUtility.findIndexedColumns(java.lang.String, android.database.sqlite.SQLiteDatabase):android.util.Pair");
    }

    public static boolean isFieldNameConflictWithSQLiteKeywords(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return SQLITE_KEYWORDS.contains("," + str.toLowerCase(Locale.US) + ",");
    }

    public static String convertToValidColumnName(String str) {
        if (!isFieldNameConflictWithSQLiteKeywords(str)) {
            return str;
        }
        return str + KEYWORDS_COLUMN_SUFFIX;
    }

    public static String convertWhereClauseToColumnName(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                StringBuffer stringBuffer = new StringBuffer();
                Matcher matcher = Pattern.compile("(\\w+\\s*(=|!=|<>|<|>)|\\w+\\s+(not\\s+)?(like|between)\\s+|\\w+\\s+(not\\s+)?(in)\\s*\\()").matcher(str);
                while (matcher.find()) {
                    String group = matcher.group();
                    String replaceAll = group.replaceAll("(\\s*(=|!=|<>|<|>)|\\s+(not\\s+)?(like|between)\\s+|\\s+(not\\s+)?(in)\\s*\\()", "");
                    String replace = group.replace(replaceAll, "");
                    String convertToValidColumnName = convertToValidColumnName(replaceAll);
                    matcher.appendReplacement(stringBuffer, convertToValidColumnName + replace);
                }
                matcher.appendTail(stringBuffer);
                return stringBuffer.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String[] convertSelectClauseToValidNames(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return null;
        }
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i] = convertToValidColumnName(strArr[i]);
        }
        return strArr2;
    }

    public static String convertOrderByClauseToValidName(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String lowerCase = str.trim().toLowerCase(Locale.US);
        if (!lowerCase.contains(",")) {
            return convertOrderByItem(lowerCase);
        }
        String[] split = lowerCase.split(",");
        StringBuilder sb = new StringBuilder();
        int length = split.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            String str2 = split[i];
            if (z) {
                sb.append(",");
            }
            sb.append(convertOrderByItem(str2));
            i++;
            z = true;
        }
        return sb.toString();
    }

    private static String convertOrderByItem(String str) {
        String str2 = "";
        if (str.endsWith("asc")) {
            str = str.replace("asc", str2).trim();
            str2 = " asc";
        } else if (str.endsWith("desc")) {
            str = str.replace("desc", str2).trim();
            str2 = " desc";
        }
        return convertToValidColumnName(str) + str2;
    }
}
