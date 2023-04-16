package com.mob.mobapm.proxy;

import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.p027c.C2352a;
import com.mob.mobapm.p030d.C2373a;
import com.mob.mobapm.p031e.C2378d;
import com.mob.tools.log.NLog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.Permission;
import java.util.List;
import java.util.Map;

/* renamed from: com.mob.mobapm.proxy.a */
public class C2397a extends HttpURLConnection {

    /* renamed from: a */
    public HttpURLConnection f2322a;

    /* renamed from: b */
    public Transaction f2323b;

    public C2397a(HttpURLConnection httpURLConnection) {
        super(httpURLConnection.getURL());
        this.f2322a = httpURLConnection;
        Transaction transaction = new Transaction();
        this.f2323b = transaction;
        C2352a.m2755b(transaction, httpURLConnection);
    }

    public void connect() throws IOException {
        try {
            C2352a.m2749a(this.f2323b);
            this.f2322a.connect();
        } catch (IOException e) {
            C2352a.m2754a(this.f2323b, this.f2322a, e.getMessage());
            throw e;
        }
    }

    public void disconnect() {
        C2352a.m2753a(this.f2323b, this.f2322a);
        this.f2322a.disconnect();
    }

    public boolean getAllowUserInteraction() {
        return this.f2322a.getAllowUserInteraction();
    }

    public int getConnectTimeout() {
        return this.f2322a.getConnectTimeout();
    }

    public Object getContent() throws IOException {
        try {
            return this.f2322a.getContent();
        } catch (IOException e) {
            C2352a.m2753a(this.f2323b, this.f2322a);
            throw e;
        }
    }

    public String getContentEncoding() {
        return this.f2322a.getContentEncoding();
    }

    public int getContentLength() {
        return this.f2322a.getContentLength();
    }

    public String getContentType() {
        return this.f2322a.getContentType();
    }

    public long getDate() {
        return this.f2322a.getDate();
    }

    public boolean getDefaultUseCaches() {
        return this.f2322a.getDefaultUseCaches();
    }

    public boolean getDoInput() {
        return this.f2322a.getDoInput();
    }

    public boolean getDoOutput() {
        return this.f2322a.getDoOutput();
    }

    public InputStream getErrorStream() {
        try {
            ByteArrayOutputStream a = C2378d.m2818a(this.f2322a.getErrorStream());
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(a.toByteArray());
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(a.toByteArray());
            C2352a.m2754a(this.f2323b, this.f2322a, C2378d.m2819b(byteArrayInputStream));
            return byteArrayInputStream2;
        } catch (Throwable th) {
            NLog a2 = C2373a.m2807a();
            a2.mo29775i("APM: httpURLConn get errorStream error: " + th, new Object[0]);
            return this.f2322a.getErrorStream();
        }
    }

    public long getExpiration() {
        return this.f2322a.getExpiration();
    }

    public String getHeaderField(int i) {
        return this.f2322a.getHeaderField(i);
    }

    public long getHeaderFieldDate(String str, long j) {
        return this.f2322a.getHeaderFieldDate(str, j);
    }

    public int getHeaderFieldInt(String str, int i) {
        return this.f2322a.getHeaderFieldInt(str, i);
    }

    public String getHeaderFieldKey(int i) {
        return this.f2322a.getHeaderFieldKey(i);
    }

    public Map<String, List<String>> getHeaderFields() {
        return this.f2322a.getHeaderFields();
    }

    public long getIfModifiedSince() {
        return this.f2322a.getIfModifiedSince();
    }

    public InputStream getInputStream() throws IOException {
        try {
            C2352a.m2753a(this.f2323b, this.f2322a);
            return this.f2322a.getInputStream();
        } catch (IOException e) {
            C2352a.m2754a(this.f2323b, this.f2322a, e.getMessage());
            throw e;
        }
    }

    public boolean getInstanceFollowRedirects() {
        return this.f2322a.getInstanceFollowRedirects();
    }

    public long getLastModified() {
        return this.f2322a.getLastModified();
    }

    public OutputStream getOutputStream() throws IOException {
        try {
            C2352a.m2749a(this.f2323b);
            return this.f2322a.getOutputStream();
        } catch (IOException e) {
            C2352a.m2754a(this.f2323b, this.f2322a, e.getMessage());
            throw e;
        }
    }

    public Permission getPermission() throws IOException {
        return this.f2322a.getPermission();
    }

    public int getReadTimeout() {
        return this.f2322a.getReadTimeout();
    }

    public String getRequestMethod() {
        return this.f2322a.getRequestMethod();
    }

    public Map<String, List<String>> getRequestProperties() {
        return this.f2322a.getRequestProperties();
    }

    public String getRequestProperty(String str) {
        return this.f2322a.getRequestProperty(str);
    }

    public int getResponseCode() throws IOException {
        try {
            return this.f2322a.getResponseCode();
        } catch (IOException e) {
            C2352a.m2754a(this.f2323b, this.f2322a, e.getMessage());
            throw e;
        }
    }

    public String getResponseMessage() throws IOException {
        try {
            return this.f2322a.getResponseMessage();
        } catch (IOException e) {
            C2352a.m2753a(this.f2323b, this.f2322a);
            throw e;
        }
    }

    public URL getURL() {
        return this.f2322a.getURL();
    }

    public boolean getUseCaches() {
        return this.f2322a.getUseCaches();
    }

    public void setAllowUserInteraction(boolean z) {
        this.f2322a.setAllowUserInteraction(z);
    }

    public void setChunkedStreamingMode(int i) {
        this.f2322a.setChunkedStreamingMode(i);
    }

    public void setConnectTimeout(int i) {
        this.f2322a.setConnectTimeout(i);
    }

    public void setDefaultUseCaches(boolean z) {
        this.f2322a.setDefaultUseCaches(z);
    }

    public void setDoInput(boolean z) {
        this.f2322a.setDoInput(z);
    }

    public void setDoOutput(boolean z) {
        this.f2322a.setDoOutput(z);
    }

    public void setFixedLengthStreamingMode(int i) {
        this.f2322a.setFixedLengthStreamingMode(i);
    }

    public void setIfModifiedSince(long j) {
        this.f2322a.setIfModifiedSince(j);
    }

    public void setInstanceFollowRedirects(boolean z) {
        this.f2322a.setInstanceFollowRedirects(z);
    }

    public void setReadTimeout(int i) {
        this.f2322a.setReadTimeout(i);
    }

    public void setRequestMethod(String str) throws ProtocolException {
        try {
            this.f2322a.setRequestMethod(str);
        } catch (ProtocolException e) {
            C2352a.m2754a(this.f2323b, this.f2322a, e.getMessage());
            throw e;
        }
    }

    public void setRequestProperty(String str, String str2) {
        this.f2322a.setRequestProperty(str, str2);
    }

    public void setUseCaches(boolean z) {
        this.f2322a.setUseCaches(z);
    }

    public String toString() {
        return this.f2322a.toString();
    }

    public boolean usingProxy() {
        return this.f2322a.usingProxy();
    }

    public Object getContent(Class[] clsArr) throws IOException {
        try {
            return this.f2322a.getContent(clsArr);
        } catch (IOException e) {
            C2352a.m2753a(this.f2323b, this.f2322a);
            throw e;
        }
    }
}
