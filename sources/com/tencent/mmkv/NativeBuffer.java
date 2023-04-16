package com.tencent.mmkv;

public final class NativeBuffer {
    public long pointer;
    public int size;

    public NativeBuffer(long j, int i) {
        this.pointer = j;
        this.size = i;
    }
}
