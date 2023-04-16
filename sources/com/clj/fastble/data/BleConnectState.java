package com.clj.fastble.data;

public enum BleConnectState {
    CONNECT_IDLE(0),
    CONNECT_CONNECTING(1),
    CONNECT_CONNECTED(2),
    CONNECT_FAILURE(3),
    CONNECT_TIMEOUT(4),
    CONNECT_DISCONNECT(5);
    
    private int code;

    private BleConnectState(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }
}
