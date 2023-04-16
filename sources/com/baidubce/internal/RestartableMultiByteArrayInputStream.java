package com.baidubce.internal;

import com.baidubce.util.CheckUtils;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import kotlin.UByte;

public class RestartableMultiByteArrayInputStream extends RestartableInputStream {
    private int blockSize;
    private List<byte[]> byteArrayList;
    private long length;
    private long pos = 0;

    public RestartableMultiByteArrayInputStream(List<byte[]> list, long j) {
        long j2 = 0;
        CheckUtils.isNotNull(list, "byteArrayList should not be null.");
        CheckUtils.checkArgument(!list.isEmpty(), "byteArrayList should not be empty.");
        Iterator<byte[]> it = list.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            byte[] next = it.next();
            CheckUtils.isNotNull(next, "byteArrayList should not contain null element.");
            if (next.length > 0) {
                z = true;
            }
            CheckUtils.checkArgument(z, "byteArrayList should not contain empty byte array.");
            j2 += (long) next.length;
        }
        CheckUtils.checkArgument(j2 >= j, "The specified length(%s) is greater than the total length(%s) of elements in byteArrayList.", Long.valueOf(j), Long.valueOf(j2));
        this.blockSize = list.get(0).length;
        for (int i = 1; i < list.size() - 1; i++) {
            int length2 = list.get(i).length;
            CheckUtils.checkArgument(length2 == this.blockSize, "All elements in byteArrayList except the last one should have the same length. The first element's length is %s but the %sth element's length is %s.", Integer.valueOf(this.blockSize), Integer.valueOf(i), Integer.valueOf(length2));
        }
        this.byteArrayList = list;
        this.length = j;
    }

    public void restart() {
        this.pos = 0;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        CheckUtils.isNotNull(bArr, "b should not be null.");
        if (i < 0 || i2 < 0 || i2 > bArr.length - i) {
            throw new IndexOutOfBoundsException();
        } else if (this.pos == this.length) {
            return -1;
        } else {
            int i3 = 0;
            while (i2 > 0) {
                long j = this.pos;
                if (j >= this.length) {
                    break;
                }
                int i4 = this.blockSize;
                int i5 = (int) (j % ((long) i4));
                byte[] bArr2 = this.byteArrayList.get((int) (j / ((long) i4)));
                int length2 = bArr2.length - i5;
                if (length2 > i2) {
                    length2 = i2;
                }
                System.arraycopy(bArr2, i5, bArr, i, length2);
                this.pos += (long) length2;
                i += length2;
                i2 -= length2;
                i3 += length2;
            }
            return i3;
        }
    }

    public int read() {
        long j = this.pos;
        if (j == this.length) {
            return -1;
        }
        int i = this.blockSize;
        this.pos = j + 1;
        return this.byteArrayList.get((int) (j / ((long) i)))[(int) (j % ((long) i))] & UByte.MAX_VALUE;
    }
}
