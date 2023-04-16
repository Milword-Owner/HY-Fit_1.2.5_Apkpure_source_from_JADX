package com.baidubce;

import android.support.p000v4.media.session.PlaybackStateCompat;
import com.baidu.mobstat.Config;
import com.baidubce.auth.BceCredentials;
import com.baidubce.http.RetryPolicy;
import com.baidubce.util.CheckUtils;
import com.baidubce.util.JoinerUtils;
import java.net.InetAddress;
import okhttp3.Dns;

public class BceClientConfiguration {
    public static String DEFAULT_ACCPET_ENCODING = "identity";
    public static final int DEFAULT_CONNECTION_TIMEOUT_IN_MILLIS = 30000;
    public static long DEFAULT_KEEPALIVE_DURATION = 30;
    public static final int DEFAULT_MAX_CONNECTIONS = 5;
    public static Protocol DEFAULT_PROTOCOL = Protocol.HTTP;
    public static Region DEFAULT_REGION = Region.CN_N1;
    public static final int DEFAULT_SOCKET_TIMEOUT_IN_MILLIS = 30000;
    public static final String DEFAULT_USER_AGENT;
    public static long MAX_PARTS = 10000;
    private String acceptEncoding = DEFAULT_ACCPET_ENCODING;
    private int connectionTimeoutInMillis = 30000;
    private BceCredentials credentials = null;
    private Dns dns = null;
    private String endpoint = null;
    private long keepAliveDuration = DEFAULT_KEEPALIVE_DURATION;
    private InetAddress localAddress = null;
    private int maxConnections = 5;
    private long multipartBlockSize = Config.FULL_TRACE_LOG_LIMIT;
    private Protocol protocol = Protocol.HTTP;
    private String proxyDomain = null;
    private String proxyHost = null;
    private String proxyPassword = null;
    private int proxyPort = -1;
    private boolean proxyPreemptiveAuthenticationEnabled;
    private String proxyUsername = null;
    private String proxyWorkstation = null;
    private Region region = DEFAULT_REGION;
    private RetryPolicy retryPolicy = RetryPolicy.DEFAULT_RETRY_POLICY;
    private int socketBufferSizeInBytes = 0;
    private int socketTimeoutInMillis = 30000;
    private String token = null;
    private int uploadRetry = 3;
    private long uploadSegmentPart = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
    private String userAgent = DEFAULT_USER_AGENT;

    static {
        String property = System.getProperty("user.language");
        String str = "";
        if (property == null) {
            property = str;
        }
        String property2 = System.getProperty("user.region");
        if (property2 != null) {
            str = property2;
        }
        DEFAULT_USER_AGENT = JoinerUtils.m1834on(BceConfig.BOS_DELIMITER, "bce-sdk-android", "1.0.10", System.getProperty("os.name"), System.getProperty("os.version"), System.getProperty("java.vm.name"), System.getProperty("java.vm.version"), System.getProperty("java.version"), property, str).replace(' ', '_');
    }

    public BceClientConfiguration() {
    }

    public BceClientConfiguration(BceClientConfiguration bceClientConfiguration) {
        this.connectionTimeoutInMillis = bceClientConfiguration.connectionTimeoutInMillis;
        this.maxConnections = bceClientConfiguration.maxConnections;
        this.retryPolicy = bceClientConfiguration.retryPolicy;
        this.localAddress = bceClientConfiguration.localAddress;
        this.protocol = bceClientConfiguration.protocol;
        this.proxyDomain = bceClientConfiguration.proxyDomain;
        this.proxyHost = bceClientConfiguration.proxyHost;
        this.proxyPassword = bceClientConfiguration.proxyPassword;
        this.proxyPort = bceClientConfiguration.proxyPort;
        this.proxyUsername = bceClientConfiguration.proxyUsername;
        this.proxyWorkstation = bceClientConfiguration.proxyWorkstation;
        this.proxyPreemptiveAuthenticationEnabled = bceClientConfiguration.proxyPreemptiveAuthenticationEnabled;
        this.socketTimeoutInMillis = bceClientConfiguration.socketTimeoutInMillis;
        this.userAgent = bceClientConfiguration.userAgent;
        this.socketBufferSizeInBytes = bceClientConfiguration.socketBufferSizeInBytes;
        this.endpoint = bceClientConfiguration.endpoint;
        this.region = bceClientConfiguration.region;
        this.credentials = bceClientConfiguration.credentials;
        this.token = bceClientConfiguration.token;
        this.uploadSegmentPart = bceClientConfiguration.uploadSegmentPart;
        this.multipartBlockSize = bceClientConfiguration.multipartBlockSize;
        this.uploadRetry = bceClientConfiguration.uploadRetry;
        this.acceptEncoding = bceClientConfiguration.acceptEncoding;
        this.keepAliveDuration = bceClientConfiguration.keepAliveDuration;
    }

    public Protocol getProtocol() {
        return this.protocol;
    }

    public void setProtocol(Protocol protocol2) {
        if (protocol2 == null) {
            protocol2 = DEFAULT_PROTOCOL;
        }
        this.protocol = protocol2;
    }

    public BceClientConfiguration withProtocol(Protocol protocol2) {
        setProtocol(protocol2);
        return this;
    }

    public int getMaxConnections() {
        return this.maxConnections;
    }

    public void setMaxConnections(int i) {
        CheckUtils.checkArgument(i >= 0, "maxConnections should not be negative.");
        this.maxConnections = i;
    }

    public BceClientConfiguration withMaxConnections(int i) {
        setMaxConnections(i);
        return this;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String str) {
        if (str == null) {
            this.userAgent = DEFAULT_USER_AGENT;
        } else if (str.endsWith(DEFAULT_USER_AGENT)) {
            this.userAgent = str;
        } else {
            this.userAgent = str + ", " + DEFAULT_USER_AGENT;
        }
    }

    public BceClientConfiguration withUserAgent(String str) {
        setUserAgent(str);
        return this;
    }

    public InetAddress getLocalAddress() {
        return this.localAddress;
    }

    public void setLocalAddress(InetAddress inetAddress) {
        this.localAddress = inetAddress;
    }

    public BceClientConfiguration withLocalAddress(InetAddress inetAddress) {
        setLocalAddress(inetAddress);
        return this;
    }

    public String getProxyHost() {
        return this.proxyHost;
    }

    public void setProxyHost(String str) {
        this.proxyHost = str;
    }

    public BceClientConfiguration withProxyHost(String str) {
        setProxyHost(str);
        return this;
    }

    public int getProxyPort() {
        return this.proxyPort;
    }

    public void setProxyPort(int i) {
        this.proxyPort = i;
    }

    public BceClientConfiguration withProxyPort(int i) {
        setProxyPort(i);
        return this;
    }

    public String getProxyUsername() {
        return this.proxyUsername;
    }

    public void setProxyUsername(String str) {
        this.proxyUsername = str;
    }

    public BceClientConfiguration withProxyUsername(String str) {
        setProxyUsername(str);
        return this;
    }

    public String getProxyPassword() {
        return this.proxyPassword;
    }

    public void setProxyPassword(String str) {
        this.proxyPassword = str;
    }

    public BceClientConfiguration withProxyPassword(String str) {
        setProxyPassword(str);
        return this;
    }

    public String getProxyDomain() {
        return this.proxyDomain;
    }

    public void setProxyDomain(String str) {
        this.proxyDomain = str;
    }

    public BceClientConfiguration withProxyDomain(String str) {
        setProxyDomain(str);
        return this;
    }

    public String getProxyWorkstation() {
        return this.proxyWorkstation;
    }

    public void setProxyWorkstation(String str) {
        this.proxyWorkstation = str;
    }

    public BceClientConfiguration withProxyWorkstation(String str) {
        setProxyWorkstation(str);
        return this;
    }

    public boolean isProxyPreemptiveAuthenticationEnabled() {
        return this.proxyPreemptiveAuthenticationEnabled;
    }

    public void setProxyPreemptiveAuthenticationEnabled(boolean z) {
        this.proxyPreemptiveAuthenticationEnabled = z;
    }

    public BceClientConfiguration withProxyPreemptiveAuthenticationEnabled(boolean z) {
        setProxyPreemptiveAuthenticationEnabled(z);
        return this;
    }

    public RetryPolicy getRetryPolicy() {
        return this.retryPolicy;
    }

    public void setRetryPolicy(RetryPolicy retryPolicy2) {
        if (retryPolicy2 == null) {
            retryPolicy2 = RetryPolicy.DEFAULT_RETRY_POLICY;
        }
        this.retryPolicy = retryPolicy2;
    }

    public BceClientConfiguration withRetryPolicy(RetryPolicy retryPolicy2) {
        setRetryPolicy(retryPolicy2);
        return this;
    }

    public int getSocketTimeoutInMillis() {
        return this.socketTimeoutInMillis;
    }

    public void setSocketTimeoutInMillis(int i) {
        CheckUtils.checkArgument(i >= 0, "socketTimeoutInMillis should not be negative.");
        this.socketTimeoutInMillis = i;
    }

    public BceClientConfiguration withSocketTimeoutInMillis(int i) {
        setSocketTimeoutInMillis(i);
        return this;
    }

    public int getConnectionTimeoutInMillis() {
        return this.connectionTimeoutInMillis;
    }

    public void setConnectionTimeoutInMillis(int i) {
        CheckUtils.checkArgument(i >= 0, "connectionTimeoutInMillis should not be negative.");
        this.connectionTimeoutInMillis = i;
    }

    public BceClientConfiguration withConnectionTimeoutInMillis(int i) {
        setConnectionTimeoutInMillis(i);
        return this;
    }

    public int getSocketBufferSizeInBytes() {
        return this.socketBufferSizeInBytes;
    }

    public void setSocketBufferSizeInBytes(int i) {
        this.socketBufferSizeInBytes = i;
    }

    public BceClientConfiguration withSocketBufferSizeInBytes(int i) {
        setSocketBufferSizeInBytes(i);
        return this;
    }

    public String getEndpoint() {
        String str = this.endpoint;
        if (str == null || str.length() <= 0 || this.endpoint.indexOf("://") >= 0) {
            return str;
        }
        return this.protocol.toString().toLowerCase() + "://" + this.endpoint;
    }

    public void setEndpoint(String str) {
        CheckUtils.isNotNull(str, "endpoint should not be null.");
        this.endpoint = str;
    }

    public BceClientConfiguration withEndpoint(String str) {
        setEndpoint(str);
        return this;
    }

    public Region getRegion() {
        return this.region;
    }

    public void setRegion(Region region2) {
        if (region2 == null) {
            region2 = DEFAULT_REGION;
        }
        this.region = region2;
    }

    public BceClientConfiguration withRegion(Region region2) {
        setRegion(region2);
        return this;
    }

    public BceCredentials getCredentials() {
        return this.credentials;
    }

    public void setCredentials(BceCredentials bceCredentials) {
        CheckUtils.isNotNull(bceCredentials, "credentials should not be null.");
        this.credentials = bceCredentials;
    }

    public BceClientConfiguration withCredentials(BceCredentials bceCredentials) {
        setCredentials(bceCredentials);
        return this;
    }

    public long getUploadSegmentPart() {
        return this.uploadSegmentPart;
    }

    public void setUploadSegmentPart(long j) {
        if (j < 1 || j > PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
            j = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        this.uploadSegmentPart = j;
    }

    public BceClientConfiguration withUploadSegmentPart(Long l) {
        setUploadSegmentPart(l.longValue());
        return this;
    }

    public long getMultipartBlockSize() {
        return this.multipartBlockSize;
    }

    public void setMultipartBlockSize(long j) {
        this.multipartBlockSize = j;
    }

    public BceClientConfiguration withMultipartBlockSize(long j) {
        setMultipartBlockSize(j);
        return this;
    }

    public int getUploadRetry() {
        return this.uploadRetry;
    }

    public void setUploadRetry(int i) {
        this.uploadRetry = i;
    }

    public BceClientConfiguration withUploadRetry(int i) {
        setUploadRetry(i);
        return this;
    }

    public String getAcceptEncoding() {
        return this.acceptEncoding;
    }

    public void setAcceptEncoding(String str) {
        this.acceptEncoding = str;
    }

    public long getKeepAliveDuration() {
        return this.keepAliveDuration;
    }

    public void setKeepAliveDuration(long j) {
        this.keepAliveDuration = j;
    }

    public Dns getDns() {
        return this.dns;
    }

    public void setDns(Dns dns2) {
        this.dns = dns2;
    }

    public String toString() {
        return "BceClientConfiguration [ \n  userAgent=" + this.userAgent + ", \n  retryPolicy=" + this.retryPolicy + ", \n  localAddress=" + this.localAddress + ", \n  protocol=" + this.protocol + ", \n  proxyHost=" + this.proxyHost + ", \n  proxyPort=" + this.proxyPort + ", \n  proxyUsername=" + this.proxyUsername + ", \n  proxyPassword=" + this.proxyPassword + ", \n  proxyDomain=" + this.proxyDomain + ", \n  proxyWorkstation=" + this.proxyWorkstation + ", \n  proxyPreemptiveAuthenticationEnabled=" + this.proxyPreemptiveAuthenticationEnabled + ", \n  maxConnections=" + this.maxConnections + ", \n  socketTimeoutInMillis=" + this.socketTimeoutInMillis + ", \n  connectionTimeoutInMillis=" + this.connectionTimeoutInMillis + ", \n  socketBufferSizeInBytes=" + this.socketBufferSizeInBytes + ", \n  endpoint=" + this.endpoint + ", \n  region=" + this.region + ", \n  credentials=" + this.credentials + ", \n  uploadSegmentPart=" + this.uploadSegmentPart + ", \n  acceptEncoding=" + this.acceptEncoding + ", \n  keepAliveDuration=" + this.keepAliveDuration + "]\n";
    }
}
