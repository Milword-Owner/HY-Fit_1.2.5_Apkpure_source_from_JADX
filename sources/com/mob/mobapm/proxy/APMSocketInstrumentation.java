package com.mob.mobapm.proxy;

import com.mob.mobapm.core.Transaction;
import com.mob.mobapm.proxy.p033d.C2402a;
import com.mob.tools.proguard.ClassKeeper;
import java.net.Socket;

public class APMSocketInstrumentation implements ClassKeeper {
    private Socket impl;
    private Transaction transaction;

    public static Socket wrapSocket(Socket socket) {
        return new C2402a(socket);
    }
}
