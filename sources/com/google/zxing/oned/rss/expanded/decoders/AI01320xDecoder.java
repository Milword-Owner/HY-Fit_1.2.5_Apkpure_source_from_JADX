package com.google.zxing.oned.rss.expanded.decoders;

import com.clj.fastble.BleManager;
import com.google.zxing.common.BitArray;

final class AI01320xDecoder extends AI013x0xDecoder {
    /* access modifiers changed from: protected */
    public int checkWeight(int i) {
        return i < 10000 ? i : i - BleManager.DEFAULT_SCAN_TIME;
    }

    AI01320xDecoder(BitArray bitArray) {
        super(bitArray);
    }

    /* access modifiers changed from: protected */
    public void addWeightCode(StringBuilder sb, int i) {
        if (i < 10000) {
            sb.append("(3202)");
        } else {
            sb.append("(3203)");
        }
    }
}
