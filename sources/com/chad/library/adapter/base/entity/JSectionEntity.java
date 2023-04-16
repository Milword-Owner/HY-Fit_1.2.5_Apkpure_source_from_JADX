package com.chad.library.adapter.base.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

public abstract class JSectionEntity implements SectionEntity {
    public int getItemType() {
        if (isHeader()) {
            SectionEntity.Companion companion = SectionEntity.Companion;
            return -99;
        }
        SectionEntity.Companion companion2 = SectionEntity.Companion;
        return -100;
    }
}
