package com.mob.mobapm.core;

import com.mob.mobapm.bean.TransactionType;
import java.io.Serializable;

public class Transaction implements Serializable {
    private static final long serialVersionUID = 1;
    private long clientTime;
    private String dataNetworkType;
    private String duid;
    private String errMsg;
    private int hijacked;
    private String host;
    private String imei;

    /* renamed from: ip */
    private String f2238ip;
    private boolean isCreate = false;
    private String mac;
    private String method;
    private String networkType;
    private String path;
    private long requestDuration;
    private long requestTime;
    private long responseTime;
    private int status;
    private int transStatus;
    private TransactionType transType;

    public long getClientTime() {
        return this.clientTime;
    }

    public String getDataNetworkType() {
        return this.dataNetworkType;
    }

    public String getDuid() {
        return this.duid;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public int getHijacked() {
        return this.hijacked;
    }

    public String getHost() {
        return this.host;
    }

    public String getImei() {
        return this.imei;
    }

    public String getIp() {
        return this.f2238ip;
    }

    public String getMac() {
        return this.mac;
    }

    public String getMethod() {
        return this.method;
    }

    public String getNetworkType() {
        return this.networkType;
    }

    public String getPath() {
        return this.path;
    }

    public long getRequestDuration() {
        return this.requestDuration;
    }

    public long getRequestTime() {
        return this.requestTime;
    }

    public long getResponseTime() {
        return this.responseTime;
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

    public void setCreate(boolean z) {
        this.isCreate = z;
    }

    public void setDataNetworkType(String str) {
        this.dataNetworkType = str;
    }

    public void setDuid(String str) {
        this.duid = str;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public void setHijacked(int i) {
        this.hijacked = i;
    }

    public void setHost(String str) {
        this.host = str;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public void setIp(String str) {
        this.f2238ip = str;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public void setMethod(String str) {
        this.method = str;
    }

    public void setNetworkType(String str) {
        this.networkType = str;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public void setRequestDuration(long j) {
        this.requestDuration = j;
    }

    public void setRequestTime(long j) {
        this.requestTime = j;
    }

    public void setResponseTime(long j) {
        this.responseTime = j;
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
        return "{host:" + this.host + ",ip:" + this.f2238ip + ",hijacked:" + this.hijacked + ",path:" + this.path + ",method:" + this.method + ",status:" + this.status + ",requestTime:" + this.requestTime + ",clientTime:" + this.clientTime + ",responseTime:" + this.responseTime + ",requestDuration:" + this.requestDuration + ",networkType:" + this.networkType + ",dataNetworkType:" + this.dataNetworkType + ",mac:" + this.mac + ",transStatus:" + this.transStatus + ",transType:" + this.transType + ",errMsg:" + this.errMsg + ",isCreate:" + this.isCreate + "}";
    }
}
