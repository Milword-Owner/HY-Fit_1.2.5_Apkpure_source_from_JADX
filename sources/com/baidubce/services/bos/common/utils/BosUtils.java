package com.baidubce.services.bos.common.utils;

import com.baidubce.services.bos.common.BosConstants;

public class BosUtils {
    public static boolean isCnameLikeHost(String str) {
        for (String endsWith : BosConstants.DEFAULT_CNAME_LIKE_LIST) {
            if (str.toLowerCase().endsWith(endsWith)) {
                return true;
            }
        }
        return false;
    }
}
