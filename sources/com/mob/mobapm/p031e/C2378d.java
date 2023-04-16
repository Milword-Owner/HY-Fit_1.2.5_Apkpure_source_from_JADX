package com.mob.mobapm.p031e;

import com.mob.mobapm.p030d.C2373a;
import com.mob.tools.log.NLog;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* renamed from: com.mob.mobapm.e.d */
public class C2378d {
    /* renamed from: a */
    public static ByteArrayOutputStream m2818a(InputStream inputStream) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read > -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    byteArrayOutputStream.flush();
                    return byteArrayOutputStream;
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }

    /* renamed from: b */
    public static String m2819b(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return stringBuffer.toString();
                }
                stringBuffer.append(readLine);
            }
        } catch (Throwable th) {
            NLog a = C2373a.m2807a();
            a.mo29775i("APM: input stream content error:" + th, new Object[0]);
            return null;
        }
    }
}
