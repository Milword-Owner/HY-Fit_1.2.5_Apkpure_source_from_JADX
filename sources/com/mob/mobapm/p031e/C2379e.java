package com.mob.mobapm.p031e;

import com.mob.mobapm.p030d.C2373a;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* renamed from: com.mob.mobapm.e.e */
public class C2379e {
    /* renamed from: a */
    public static String m2820a(String str) {
        try {
            return InetAddress.getByName(str).getHostAddress();
        } catch (UnknownHostException e) {
            C2373a.m2807a().mo29776i(e);
            return null;
        }
    }
}
