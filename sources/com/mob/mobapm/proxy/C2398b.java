package com.mob.mobapm.proxy;

import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p027c.C2352a;
import com.mob.mobapm.p031e.C2378d;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: com.mob.mobapm.proxy.b */
public class C2398b extends HttpsURLConnection {

    /* renamed from: a */
    public HttpsURLConnection f2324a;

    /* renamed from: b */
    private Transaction f2325b;

    public C2398b(HttpsURLConnection httpsURLConnection) {
        super(httpsURLConnection.getURL());
        this.f2324a = httpsURLConnection;
        Transaction transaction = new Transaction();
        this.f2325b = transaction;
        C2352a.m2755b(transaction, httpsURLConnection);
    }

    public void addRequestProperty(String str, String str2) {
        this.f2324a.addRequestProperty(str, str2);
    }

    public void connect() throws IOException {
        try {
            C2352a.m2749a(this.f2325b);
            this.f2324a.connect();
        } catch (IOException e) {
            C2352a.m2754a(this.f2325b, (HttpURLConnection) this.f2324a, e.getMessage());
            throw e;
        }
    }

    public void disconnect() {
        C2352a.m2753a(this.f2325b, (HttpURLConnection) this.f2324a);
        this.f2324a.disconnect();
    }

    public boolean getAllowUserInteraction() {
        return this.f2324a.getAllowUserInteraction();
    }

    public String getCipherSuite() {
        return this.f2324a.getCipherSuite();
    }

    public int getConnectTimeout() {
        return this.f2324a.getConnectTimeout();
    }

    public Object getContent() throws IOException {
        try {
            return this.f2324a.getContent();
        } catch (IOException e) {
            C2352a.m2753a(this.f2325b, (HttpURLConnection) this.f2324a);
            throw e;
        }
    }

    public String getContentEncoding() {
        return this.f2324a.getContentEncoding();
    }

    public int getContentLength() {
        return this.f2324a.getContentLength();
    }

    public String getContentType() {
        return this.f2324a.getContentType();
    }

    public long getDate() {
        return this.f2324a.getDate();
    }

    public boolean getDefaultUseCaches() {
        return this.f2324a.getDefaultUseCaches();
    }

    public boolean getDoInput() {
        return this.f2324a.getDoInput();
    }

    public boolean getDoOutput() {
        return this.f2324a.getDoOutput();
    }

    public InputStream getErrorStream() {
        try {
            ByteArrayOutputStream a = C2378d.m2818a(this.f2324a.getErrorStream());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(a.toByteArray());
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(a.toByteArray());
            C2352a.m2754a(this.f2325b, (HttpURLConnection) this.f2324a, C2378d.m2819b(byteArrayInputStream));
            return byteArrayInputStream2;
        } catch (Throwable unused) {
            return this.f2324a.getErrorStream();
        }
    }

    public long getExpiration() {
        return this.f2324a.getExpiration();
    }

    public String getHeaderField(int i) {
        return this.f2324a.getHeaderField(i);
    }

    public long getHeaderFieldDate(String str, long j) {
        return this.f2324a.getHeaderFieldDate(str, j);
    }

    public int getHeaderFieldInt(String str, int i) {
        return this.f2324a.getHeaderFieldInt(str, i);
    }

    public String getHeaderFieldKey(int i) {
        return this.f2324a.getHeaderFieldKey(i);
    }

    public Map<String, List<String>> getHeaderFields() {
        return this.f2324a.getHeaderFields();
    }

    public HostnameVerifier getHostnameVerifier() {
        return super.getHostnameVerifier();
    }

    public long getIfModifiedSince() {
        return this.f2324a.getIfModifiedSince();
    }

    public InputStream getInputStream() throws IOException {
        try {
            C2352a.m2753a(this.f2325b, (HttpURLConnection) this.f2324a);
            return this.f2324a.getInputStream();
        } catch (IOException e) {
            C2352a.m2754a(this.f2325b, (HttpURLConnection) this.f2324a, e.getMessage());
            throw e;
        }
    }

    public boolean getInstanceFollowRedirects() {
        return this.f2324a.getInstanceFollowRedirects();
    }

    public long getLastModified() {
        return this.f2324a.getLastModified();
    }

    public Certificate[] getLocalCertificates() {
        return this.f2324a.getLocalCertificates();
    }

    public Principal getLocalPrincipal() {
        return this.f2324a.getLocalPrincipal();
    }

    public OutputStream getOutputStream() throws IOException {
        try {
            C2352a.m2749a(this.f2325b);
            return this.f2324a.getOutputStream();
        } catch (IOException e) {
            C2352a.m2754a(this.f2325b, (HttpURLConnection) this.f2324a, e.getMessage());
            throw e;
        }
    }

    public Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        return this.f2324a.getPeerPrincipal();
    }

    public Permission getPermission() throws IOException {
        return this.f2324a.getPermission();
    }

    public int getReadTimeout() {
        return this.f2324a.getReadTimeout();
    }

    public String getRequestMethod() {
        return this.f2324a.getRequestMethod();
    }

    public Map<String, List<String>> getRequestProperties() {
        return this.f2324a.getRequestProperties();
    }

    public String getRequestProperty(String str) {
        return this.f2324a.getRequestProperty(str);
    }

    public int getResponseCode() throws IOException {
        try {
            return this.f2324a.getResponseCode();
        } catch (IOException e) {
            C2352a.m2754a(this.f2325b, (HttpURLConnection) this.f2324a, e.getMessage());
            throw e;
        }
    }

    public String getResponseMessage() throws IOException {
        try {
            return this.f2324a.getResponseMessage();
        } catch (IOException e) {
            C2352a.m2753a(this.f2325b, (HttpURLConnection) this.f2324a);
            throw e;
        }
    }

    public SSLSocketFactory getSSLSocketFactory() {
        return this.f2324a.getSSLSocketFactory();
    }

    public Certificate[] getServerCertificates() throws SSLPeerUnverifiedException {
        return this.f2324a.getServerCertificates();
    }

    public URL getURL() {
        return this.f2324a.getURL();
    }

    public boolean getUseCaches() {
        return this.f2324a.getUseCaches();
    }

    public void setAllowUserInteraction(boolean z) {
        this.f2324a.setAllowUserInteraction(z);
    }

    public void setChunkedStreamingMode(int i) {
        this.f2324a.setChunkedStreamingMode(i);
    }

    public void setConnectTimeout(int i) {
        this.f2324a.setConnectTimeout(i);
    }

    public void setDefaultUseCaches(boolean z) {
        this.f2324a.setDefaultUseCaches(z);
    }

    public void setDoInput(boolean z) {
        this.f2324a.setDoInput(z);
    }

    public void setDoOutput(boolean z) {
        this.f2324a.setDoOutput(z);
    }

    public void setFixedLengthStreamingMode(int i) {
        this.f2324a.setFixedLengthStreamingMode(i);
    }

    public void setHostnameVerifier(HostnameVerifier hostnameVerifier) {
        this.f2324a.setHostnameVerifier(hostnameVerifier);
    }

    public void setIfModifiedSince(long j) {
        this.f2324a.setIfModifiedSince(j);
    }

    public void setInstanceFollowRedirects(boolean z) {
        this.f2324a.setInstanceFollowRedirects(z);
    }

    public void setReadTimeout(int i) {
        this.f2324a.setReadTimeout(i);
    }

    public void setRequestMethod(String str) throws ProtocolException {
        try {
            this.f2324a.setRequestMethod(str);
        } catch (ProtocolException e) {
            C2352a.m2754a(this.f2325b, (HttpURLConnection) this.f2324a, e.getMessage());
            throw e;
        }
    }

    public void setRequestProperty(String str, String str2) {
        this.f2324a.setRequestProperty(str, str2);
    }

    public void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.f2324a.setSSLSocketFactory(sSLSocketFactory);
    }

    public void setUseCaches(boolean z) {
        this.f2324a.setUseCaches(z);
    }

    public String toString() {
        return this.f2324a.toString();
    }

    public boolean usingProxy() {
        return this.f2324a.usingProxy();
    }

    public String getHeaderField(String str) {
        return this.f2324a.getHeaderField(str);
    }

    public Object getContent(Class[] clsArr) throws IOException {
        try {
            return this.f2324a.getContent(clsArr);
        } catch (IOException e) {
            C2352a.m2753a(this.f2325b, (HttpURLConnection) this.f2324a);
            throw e;
        }
    }
}
