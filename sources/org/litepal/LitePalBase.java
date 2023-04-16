package org.litepal;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;
import org.litepal.crud.model.AssociationsInfo;
import org.litepal.exceptions.DatabaseGenerateException;
import org.litepal.parser.LitePalAttr;
import org.litepal.tablemanager.model.AssociationsModel;
import org.litepal.tablemanager.model.ColumnModel;
import org.litepal.tablemanager.model.GenericModel;
import org.litepal.tablemanager.model.TableModel;
import org.litepal.tablemanager.typechange.BlobOrm;
import org.litepal.tablemanager.typechange.BooleanOrm;
import org.litepal.tablemanager.typechange.DateOrm;
import org.litepal.tablemanager.typechange.DecimalOrm;
import org.litepal.tablemanager.typechange.NumericOrm;
import org.litepal.tablemanager.typechange.OrmChange;
import org.litepal.tablemanager.typechange.TextOrm;
import org.litepal.util.BaseUtility;
import org.litepal.util.DBUtility;

public abstract class LitePalBase {
    private static final int GET_ASSOCIATIONS_ACTION = 1;
    private static final int GET_ASSOCIATION_INFO_ACTION = 2;
    public static final String TAG = "LitePalBase";
    private Map<String, List<Field>> classFieldsMap = new HashMap();
    private Map<String, List<Field>> classGenericFieldsMap = new HashMap();
    private Collection<AssociationsInfo> mAssociationInfos;
    private Collection<AssociationsModel> mAssociationModels;
    private Collection<GenericModel> mGenericModels;
    private OrmChange[] typeChangeRules = {new NumericOrm(), new TextOrm(), new BooleanOrm(), new DecimalOrm(), new DateOrm(), new BlobOrm()};

    /* access modifiers changed from: protected */
    public TableModel getTableModel(String str) {
        String tableNameByClassName = DBUtility.getTableNameByClassName(str);
        TableModel tableModel = new TableModel();
        tableModel.setTableName(tableNameByClassName);
        tableModel.setClassName(str);
        for (Field convertFieldToColumnModel : getSupportedFields(str)) {
            tableModel.addColumnModel(convertFieldToColumnModel(convertFieldToColumnModel));
        }
        return tableModel;
    }

    /* access modifiers changed from: protected */
    public Collection<AssociationsModel> getAssociations(List<String> list) {
        if (this.mAssociationModels == null) {
            this.mAssociationModels = new HashSet();
        }
        if (this.mGenericModels == null) {
            this.mGenericModels = new HashSet();
        }
        this.mAssociationModels.clear();
        this.mGenericModels.clear();
        for (String analyzeClassFields : list) {
            analyzeClassFields(analyzeClassFields, 1);
        }
        return this.mAssociationModels;
    }

    /* access modifiers changed from: protected */
    public Collection<GenericModel> getGenericModels() {
        return this.mGenericModels;
    }

    /* access modifiers changed from: protected */
    public Collection<AssociationsInfo> getAssociationInfo(String str) {
        if (this.mAssociationInfos == null) {
            this.mAssociationInfos = new HashSet();
        }
        this.mAssociationInfos.clear();
        analyzeClassFields(str, 2);
        return this.mAssociationInfos;
    }

    /* access modifiers changed from: protected */
    public List<Field> getSupportedFields(String str) {
        List<Field> list = this.classFieldsMap.get(str);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        try {
            recursiveSupportedFields(Class.forName(str), arrayList);
            this.classFieldsMap.put(str, arrayList);
            return arrayList;
        } catch (ClassNotFoundException unused) {
            throw new DatabaseGenerateException(DatabaseGenerateException.CLASS_NOT_FOUND + str);
        }
    }

    /* access modifiers changed from: protected */
    public List<Field> getSupportedGenericFields(String str) {
        List<Field> list = this.classGenericFieldsMap.get(str);
        if (list != null) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        try {
            recursiveSupportedGenericFields(Class.forName(str), arrayList);
            this.classGenericFieldsMap.put(str, arrayList);
            return arrayList;
        } catch (ClassNotFoundException unused) {
            throw new DatabaseGenerateException(DatabaseGenerateException.CLASS_NOT_FOUND + str);
        }
    }

    /* access modifiers changed from: protected */
    public boolean isCollection(Class<?> cls) {
        return isList(cls) || isSet(cls);
    }

    /* access modifiers changed from: protected */
    public boolean isList(Class<?> cls) {
        return List.class.isAssignableFrom(cls);
    }

    /* access modifiers changed from: protected */
    public boolean isSet(Class<?> cls) {
        return Set.class.isAssignableFrom(cls);
    }

    /* access modifiers changed from: protected */
    public boolean isIdColumn(String str) {
        return "_id".equalsIgnoreCase(str) || "id".equalsIgnoreCase(str);
    }

    /* access modifiers changed from: protected */
    public String getForeignKeyColumnName(String str) {
        return BaseUtility.changeCase(str + "_id");
    }

    /* access modifiers changed from: protected */
    public String getColumnType(String str) {
        for (OrmChange object2Relation : this.typeChangeRules) {
            String object2Relation2 = object2Relation.object2Relation(str);
            if (object2Relation2 != null) {
                return object2Relation2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Class<?> getGenericTypeClass(Field field) {
        Type genericType = field.getGenericType();
        if (genericType == null || !(genericType instanceof ParameterizedType)) {
            return null;
        }
        return (Class) ((ParameterizedType) genericType).getActualTypeArguments()[0];
    }

    private void recursiveSupportedFields(Class<?> cls, List<Field> list) {
        if (cls != LitePalSupport.class && cls != Object.class) {
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    Column column = (Column) field.getAnnotation(Column.class);
                    if ((column == null || !column.ignore()) && !Modifier.isStatic(field.getModifiers()) && BaseUtility.isFieldTypeSupported(field.getType().getName())) {
                        list.add(field);
                    }
                }
            }
            recursiveSupportedFields(cls.getSuperclass(), list);
        }
    }

    private void recursiveSupportedGenericFields(Class<?> cls, List<Field> list) {
        if (cls != LitePalSupport.class && cls != Object.class) {
            Field[] declaredFields = cls.getDeclaredFields();
            if (declaredFields != null && declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    Column column = (Column) field.getAnnotation(Column.class);
                    if ((column == null || !column.ignore()) && !Modifier.isStatic(field.getModifiers()) && isCollection(field.getType())) {
                        String genericTypeName = getGenericTypeName(field);
                        if (BaseUtility.isGenericTypeSupported(genericTypeName) || cls.getName().equalsIgnoreCase(genericTypeName)) {
                            list.add(field);
                        }
                    }
                }
            }
            recursiveSupportedGenericFields(cls.getSuperclass(), list);
        }
    }

    private void analyzeClassFields(String str, int i) {
        try {
            for (Field field : Class.forName(str).getDeclaredFields()) {
                if (isNonPrimitive(field)) {
                    Column column = (Column) field.getAnnotation(Column.class);
                    if (column == null || !column.ignore()) {
                        oneToAnyConditions(str, field, i);
                        manyToAnyConditions(str, field, i);
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new DatabaseGenerateException(DatabaseGenerateException.CLASS_NOT_FOUND + str);
        }
    }

    private boolean isNonPrimitive(Field field) {
        return !field.getType().isPrimitive();
    }

    private boolean isPrivate(Field field) {
        return Modifier.isPrivate(field.getModifiers());
    }

    private void oneToAnyConditions(String str, Field field, int i) throws ClassNotFoundException {
        String str2 = str;
        int i2 = i;
        Class<?> type = field.getType();
        if (LitePalAttr.getInstance().getClassNames().contains(type.getName())) {
            boolean z = false;
            for (Field field2 : Class.forName(type.getName()).getDeclaredFields()) {
                if (!Modifier.isStatic(field2.getModifiers())) {
                    Class<?> type2 = field2.getType();
                    if (str2.equals(type2.getName())) {
                        if (i2 == 1) {
                            addIntoAssociationModelCollection(str2, type.getName(), type.getName(), 1);
                        } else if (i2 == 2) {
                            addIntoAssociationInfoCollection(str, type.getName(), type.getName(), field, field2, 1);
                        }
                    } else if (isCollection(type2) && str2.equals(getGenericTypeName(field2))) {
                        if (i2 == 1) {
                            addIntoAssociationModelCollection(str2, type.getName(), str2, 2);
                        } else if (i2 == 2) {
                            addIntoAssociationInfoCollection(str, type.getName(), str, field, field2, 2);
                        }
                    }
                    z = true;
                }
            }
            if (z) {
                return;
            }
            if (i2 == 1) {
                addIntoAssociationModelCollection(str2, type.getName(), type.getName(), 1);
            } else if (i2 == 2) {
                addIntoAssociationInfoCollection(str, type.getName(), type.getName(), field, (Field) null, 1);
            }
        }
    }

    private void manyToAnyConditions(String str, Field field, int i) throws ClassNotFoundException {
        if (isCollection(field.getType())) {
            String genericTypeName = getGenericTypeName(field);
            if (LitePalAttr.getInstance().getClassNames().contains(genericTypeName)) {
                boolean z = false;
                for (Field field2 : Class.forName(genericTypeName).getDeclaredFields()) {
                    if (!Modifier.isStatic(field2.getModifiers())) {
                        Class<?> type = field2.getType();
                        if (str.equals(type.getName())) {
                            if (i == 1) {
                                addIntoAssociationModelCollection(str, genericTypeName, genericTypeName, 2);
                            } else if (i == 2) {
                                addIntoAssociationInfoCollection(str, genericTypeName, genericTypeName, field, field2, 2);
                            }
                        } else if (isCollection(type) && str.equals(getGenericTypeName(field2))) {
                            if (i == 1) {
                                if (str.equalsIgnoreCase(genericTypeName)) {
                                    GenericModel genericModel = new GenericModel();
                                    genericModel.setTableName(DBUtility.getGenericTableName(str, field.getName()));
                                    genericModel.setValueColumnName(DBUtility.getM2MSelfRefColumnName(field));
                                    genericModel.setValueColumnType("integer");
                                    genericModel.setValueIdColumnName(DBUtility.getGenericValueIdColumnName(str));
                                    this.mGenericModels.add(genericModel);
                                } else {
                                    addIntoAssociationModelCollection(str, genericTypeName, (String) null, 3);
                                }
                            } else if (i == 2 && !str.equalsIgnoreCase(genericTypeName)) {
                                addIntoAssociationInfoCollection(str, genericTypeName, (String) null, field, field2, 3);
                            }
                        }
                        z = true;
                    }
                }
                if (z) {
                    return;
                }
                if (i == 1) {
                    addIntoAssociationModelCollection(str, genericTypeName, genericTypeName, 2);
                } else if (i == 2) {
                    addIntoAssociationInfoCollection(str, genericTypeName, genericTypeName, field, (Field) null, 2);
                }
            } else if (BaseUtility.isGenericTypeSupported(genericTypeName) && i == 1) {
                GenericModel genericModel2 = new GenericModel();
                genericModel2.setTableName(DBUtility.getGenericTableName(str, field.getName()));
                genericModel2.setValueColumnName(DBUtility.convertToValidColumnName(field.getName()));
                genericModel2.setValueColumnType(getColumnType(genericTypeName));
                genericModel2.setValueIdColumnName(DBUtility.getGenericValueIdColumnName(str));
                this.mGenericModels.add(genericModel2);
            }
        }
    }

    private void addIntoAssociationModelCollection(String str, String str2, String str3, int i) {
        AssociationsModel associationsModel = new AssociationsModel();
        associationsModel.setTableName(DBUtility.getTableNameByClassName(str));
        associationsModel.setAssociatedTableName(DBUtility.getTableNameByClassName(str2));
        associationsModel.setTableHoldsForeignKey(DBUtility.getTableNameByClassName(str3));
        associationsModel.setAssociationType(i);
        this.mAssociationModels.add(associationsModel);
    }

    private void addIntoAssociationInfoCollection(String str, String str2, String str3, Field field, Field field2, int i) {
        AssociationsInfo associationsInfo = new AssociationsInfo();
        associationsInfo.setSelfClassName(str);
        associationsInfo.setAssociatedClassName(str2);
        associationsInfo.setClassHoldsForeignKey(str3);
        associationsInfo.setAssociateOtherModelFromSelf(field);
        associationsInfo.setAssociateSelfFromOtherModel(field2);
        associationsInfo.setAssociationType(i);
        this.mAssociationInfos.add(associationsInfo);
    }

    /* access modifiers changed from: protected */
    public String getGenericTypeName(Field field) {
        Class<?> genericTypeClass = getGenericTypeClass(field);
        if (genericTypeClass != null) {
            return genericTypeClass.getName();
        }
        return null;
    }

    private ColumnModel convertFieldToColumnModel(Field field) {
        String str;
        boolean z;
        boolean z2;
        String columnType = getColumnType(field.getType().getName());
        Column column = (Column) field.getAnnotation(Column.class);
        boolean z3 = false;
        if (column != null) {
            boolean nullable = column.nullable();
            boolean unique = column.unique();
            str = column.defaultValue();
            boolean z4 = unique;
            z = column.index();
            z2 = nullable;
            z3 = z4;
        } else {
            z2 = true;
            str = "";
            z = false;
        }
        ColumnModel columnModel = new ColumnModel();
        columnModel.setColumnName(DBUtility.convertToValidColumnName(field.getName()));
        columnModel.setColumnType(columnType);
        columnModel.setNullable(z2);
        columnModel.setUnique(z3);
        columnModel.setDefaultValue(str);
        columnModel.setHasIndex(z);
        return columnModel;
    }
}
