package com.mob.mobapm.bean;

import java.io.Serializable;

public class SocketTransaction implements Serializable {
    private static final long serialVersionUID = 1;
    private long clientTime;
    private long connectBeginTime;
    private long connectDuration;
    private long connectEndTime;
    private String dataNetworkType;
    private String duid;
    private String host;
    private boolean isCreate = false;
    private String networkType;
    private int port;
    private int status;
    private int transStatus;
    private TransactionType transType;

    public long getClientTime() {
        return this.clientTime;
    }

    public long getConnectBeginTime() {
        return this.connectBeginTime;
    }

    public long getConnectDuration() {
        return this.connectDuration;
    }

    public long getConnectEndTime() {
        return this.connectEndTime;
    }

    public String getDataNetworkType() {
        return this.dataNetworkType;
    }

    public String getDuid() {
        return this.duid;
    }

    public String getHost() {
        return this.host;
    }

    public String getNetworkType() {
        return this.networkType;
    }

    public int getPort() {
        return this.port;
    }

    public int getStatus() {
        return this.status;
    }

    public int getTransStatus() {
        return this.transStatus;
    }

    public TransactionType getTransType() {
        return this.transType;
    }

    public boolean isCreate() {
        return this.isCreate;
    }

    public void setClientTime(long j) {
        this.clientTime = j;
    }

    public void setConnectBeginTime(long j) {
        this.connectBeginTime = j;
    }

    public void setConnectDuration(long j) {
        this.connectDuration = j;
    }

    public void setConnectEndTime(long j) {
        this.connectEndTime = j;
    }

    public void setCreate(boolean z) {
        this.isCreate = z;
    }

    public void setDataNetworkType(String str) {
        this.dataNetworkType = str;
    }

    public void setDuid(String str) {
        this.duid = str;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setNetworkType(String str) {
        this.networkType = str;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public void setTransStatus(int i) {
        this.transStatus = i;
    }

    public void setTransType(TransactionType transactionType) {
        this.transType = transactionType;
    }

    public String toString() {
        return "{host:" + this.host + ",port:" + this.port + ",status:" + this.status + ",connectBeginTime:" + this.connectBeginTime + ",connectEndTime:" + this.connectEndTime + ",connectDuration:" + this.connectDuration + ",networkType:" + this.networkType + ",dataNetworkType:" + this.dataNetworkType + ",transStatus:" + this.transStatus + ",transType:" + this.transType + ",isCreate:" + this.isCreate + "}";
    }
}
