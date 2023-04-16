package com.mob.commons;

import com.mob.guard.MobGuard;
import com.mob.tools.proguard.ClassKeeper;

public class MOBGUARD implements MobProduct, ClassKeeper {
    public String getProductTag() {
        return MobGuard.getSdkTag();
    }

    public int getSdkver() {
        return MobGuard.SDK_VERSION_CODE;
    }
}
