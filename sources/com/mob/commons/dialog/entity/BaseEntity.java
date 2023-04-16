package com.mob.commons.dialog.entity;

import com.mob.tools.proguard.EverythingKeeper;
import com.mob.tools.utils.Hashon;
import java.io.Serializable;

@Deprecated
public class BaseEntity implements EverythingKeeper, Serializable {
    public String toJSONString() {
        return new Hashon().fromObject(this);
    }
}
