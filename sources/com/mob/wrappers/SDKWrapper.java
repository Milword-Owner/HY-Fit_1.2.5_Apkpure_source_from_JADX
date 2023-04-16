package com.mob.wrappers;

import com.mob.commons.MobProduct;
import com.mob.commons.MobProductCollector;
import java.util.Iterator;

public class SDKWrapper {
    protected static int isAvailable(String str) {
        try {
            Iterator<MobProduct> it = MobProductCollector.getProducts().iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().getProductTag())) {
                    return 1;
                }
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }
}
