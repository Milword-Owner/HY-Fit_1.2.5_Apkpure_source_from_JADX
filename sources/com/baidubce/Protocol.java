package com.baidubce;

public enum Protocol {
    HTTP("http", 80),
    HTTPS("https", 443);
    
    private int defaultPort;
    private String protocol;

    private Protocol(String str, int i) {
        this.protocol = str;
        this.defaultPort = i;
    }

    public int getDefaultPort() {
        return this.defaultPort;
    }

    public String toString() {
        return this.protocol;
    }
}
