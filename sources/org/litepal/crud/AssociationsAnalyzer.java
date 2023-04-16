package org.litepal.crud;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import org.litepal.crud.model.AssociationsInfo;
import org.litepal.exceptions.LitePalSupportException;
import org.litepal.util.DBUtility;

abstract class AssociationsAnalyzer extends DataHandler {
    AssociationsAnalyzer() {
    }

    /* access modifiers changed from: protected */
    public Collection<LitePalSupport> getReverseAssociatedModels(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return (Collection) getFieldValue(litePalSupport, associationsInfo.getAssociateSelfFromOtherModel());
    }

    /* access modifiers changed from: protected */
    public void setReverseAssociatedModels(LitePalSupport litePalSupport, AssociationsInfo associationsInfo, Collection<LitePalSupport> collection) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        setFieldValue(litePalSupport, associationsInfo.getAssociateSelfFromOtherModel(), collection);
    }

    /* access modifiers changed from: protected */
    public Collection<LitePalSupport> checkAssociatedModelCollection(Collection<LitePalSupport> collection, Field field) {
        Collection<LitePalSupport> collection2;
        if (isList(field.getType())) {
            collection2 = new ArrayList<>();
        } else if (isSet(field.getType())) {
            collection2 = new HashSet<>();
        } else {
            throw new LitePalSupportException(LitePalSupportException.WRONG_FIELD_TYPE_FOR_ASSOCIATIONS);
        }
        if (collection != null) {
            collection2.addAll(collection);
        }
        return collection2;
    }

    /* access modifiers changed from: protected */
    public void buildBidirectionalAssociations(LitePalSupport litePalSupport, LitePalSupport litePalSupport2, AssociationsInfo associationsInfo) throws SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        setFieldValue(litePalSupport2, associationsInfo.getAssociateSelfFromOtherModel(), litePalSupport);
    }

    /* access modifiers changed from: protected */
    public void dealsAssociationsOnTheSideWithoutFK(LitePalSupport litePalSupport, LitePalSupport litePalSupport2) {
        if (litePalSupport2 == null) {
            return;
        }
        if (litePalSupport2.isSaved()) {
            litePalSupport.addAssociatedModelWithFK(litePalSupport2.getTableName(), litePalSupport2.getBaseObjId());
        } else if (litePalSupport.isSaved()) {
            litePalSupport2.addAssociatedModelWithoutFK(litePalSupport.getTableName(), litePalSupport.getBaseObjId());
        }
    }

    /* access modifiers changed from: protected */
    public void mightClearFKValue(LitePalSupport litePalSupport, AssociationsInfo associationsInfo) {
        litePalSupport.addFKNameToClearSelf(getForeignKeyName(associationsInfo));
    }

    private String getForeignKeyName(AssociationsInfo associationsInfo) {
        return getForeignKeyColumnName(DBUtility.getTableNameByClassName(associationsInfo.getAssociatedClassName()));
    }
}
