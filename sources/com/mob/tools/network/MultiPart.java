package com.mob.tools.network;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class MultiPart extends HTTPPart {
    private ArrayList<HTTPPart> parts = new ArrayList<>();

    public MultiPart append(HTTPPart hTTPPart) throws Throwable {
        this.parts.add(hTTPPart);
        return this;
    }

    /* access modifiers changed from: protected */
    public InputStream getInputStream() throws Throwable {
        MultiPartInputStream multiPartInputStream = new MultiPartInputStream();
        Iterator<HTTPPart> it = this.parts.iterator();
        while (it.hasNext()) {
            multiPartInputStream.addInputStream(it.next().getInputStream());
        }
        return multiPartInputStream;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<HTTPPart> it = this.parts.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public long length() throws Throwable {
        Iterator<HTTPPart> it = this.parts.iterator();
        long j = 0;
        while (it.hasNext()) {
            j += it.next().length();
        }
        return j;
    }
}
