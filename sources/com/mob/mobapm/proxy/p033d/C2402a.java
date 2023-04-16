package com.mob.mobapm.proxy.p033d;

import com.mob.mobapm.bean.SocketTransaction;
import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;

/* renamed from: com.mob.mobapm.proxy.d.a */
public class C2402a extends Socket {

    /* renamed from: a */
    private Socket f2330a;

    /* renamed from: b */
    private SocketTransaction f2331b = new SocketTransaction();

    public C2402a(Socket socket) {
        this.f2330a = socket;
    }

    public void bind(SocketAddress socketAddress) throws IOException {
        this.f2330a.bind(socketAddress);
    }

    public synchronized void close() throws IOException {
        this.f2330a.close();
    }

    public void connect(SocketAddress socketAddress, int i) throws IOException {
        try {
            NLog a = C2373a.m2807a();
            a.mo29768d("APM: socket address:" + socketAddress.toString(), new Object[0]);
            C2403b.m2895a(this.f2331b, (InetSocketAddress) socketAddress);
            this.f2330a.connect(socketAddress, i);
            C2403b.m2893a(this.f2331b);
        } catch (Throwable th) {
            C2403b.m2894a(this.f2331b, th);
            throw th;
        }
    }

    public SocketChannel getChannel() {
        return this.f2330a.getChannel();
    }

    public InetAddress getInetAddress() {
        return this.f2330a.getInetAddress();
    }

    public InputStream getInputStream() throws IOException {
        return this.f2330a.getInputStream();
    }

    public boolean getKeepAlive() throws SocketException {
        return this.f2330a.getKeepAlive();
    }

    public InetAddress getLocalAddress() {
        return this.f2330a.getLocalAddress();
    }

    public int getLocalPort() {
        return this.f2330a.getLocalPort();
    }

    public SocketAddress getLocalSocketAddress() {
        return this.f2330a.getLocalSocketAddress();
    }

    public boolean getOOBInline() throws SocketException {
        return this.f2330a.getOOBInline();
    }

    public OutputStream getOutputStream() throws IOException {
        return this.f2330a.getOutputStream();
    }

    public int getPort() {
        return this.f2330a.getPort();
    }

    public synchronized int getReceiveBufferSize() throws SocketException {
        return this.f2330a.getReceiveBufferSize();
    }

    public SocketAddress getRemoteSocketAddress() {
        return this.f2330a.getRemoteSocketAddress();
    }

    public boolean getReuseAddress() throws SocketException {
        return this.f2330a.getReuseAddress();
    }

    public synchronized int getSendBufferSize() throws SocketException {
        return this.f2330a.getSendBufferSize();
    }

    public int getSoLinger() throws SocketException {
        return this.f2330a.getSoLinger();
    }

    public synchronized int getSoTimeout() throws SocketException {
        return this.f2330a.getSoTimeout();
    }

    public boolean getTcpNoDelay() throws SocketException {
        return this.f2330a.getTcpNoDelay();
    }

    public int getTrafficClass() throws SocketException {
        return this.f2330a.getTrafficClass();
    }

    public boolean isBound() {
        return this.f2330a.isBound();
    }

    public boolean isClosed() {
        return this.f2330a.isClosed();
    }

    public boolean isConnected() {
        return this.f2330a.isConnected();
    }

    public boolean isInputShutdown() {
        return this.f2330a.isInputShutdown();
    }

    public boolean isOutputShutdown() {
        return this.f2330a.isOutputShutdown();
    }

    public void sendUrgentData(int i) throws IOException {
        this.f2330a.sendUrgentData(i);
    }

    public void setKeepAlive(boolean z) throws SocketException {
        this.f2330a.setKeepAlive(z);
    }

    public void setOOBInline(boolean z) throws SocketException {
        this.f2330a.setOOBInline(z);
    }

    public void setPerformancePreferences(int i, int i2, int i3) {
        this.f2330a.setPerformancePreferences(i, i2, i3);
    }

    public synchronized void setReceiveBufferSize(int i) throws SocketException {
        this.f2330a.setReceiveBufferSize(i);
    }

    public void setReuseAddress(boolean z) throws SocketException {
        this.f2330a.setReuseAddress(z);
    }

    public synchronized void setSendBufferSize(int i) throws SocketException {
        this.f2330a.setSendBufferSize(i);
    }

    public void setSoLinger(boolean z, int i) throws SocketException {
        this.f2330a.setSoLinger(z, i);
    }

    public synchronized void setSoTimeout(int i) throws SocketException {
        this.f2330a.setSoTimeout(i);
    }

    public void setTcpNoDelay(boolean z) throws SocketException {
        this.f2330a.setTcpNoDelay(z);
    }

    public void setTrafficClass(int i) throws SocketException {
        this.f2330a.setTrafficClass(i);
    }

    public void shutdownInput() throws IOException {
        this.f2330a.shutdownInput();
    }

    public void shutdownOutput() throws IOException {
        this.f2330a.shutdownOutput();
    }

    public String toString() {
        return this.f2330a.toString();
    }

    public void connect(SocketAddress socketAddress) throws IOException {
        try {
            NLog a = C2373a.m2807a();
            a.mo29768d("APM: socket address:" + socketAddress.toString(), new Object[0]);
            C2403b.m2895a(this.f2331b, (InetSocketAddress) socketAddress);
            this.f2330a.connect(socketAddress);
            C2403b.m2893a(this.f2331b);
        } catch (Throwable th) {
            C2403b.m2894a(this.f2331b, th);
            throw th;
        }
    }
}
