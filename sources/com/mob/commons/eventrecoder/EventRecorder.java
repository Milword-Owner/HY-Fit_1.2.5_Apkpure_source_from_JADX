package com.mob.commons.eventrecoder;

import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.C2300e;
import com.mob.commons.LockAction;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.FileLocker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

public final class EventRecorder implements PublicMemberKeeper {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static File f2125a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static FileOutputStream f2126b;

    public static final synchronized void prepare() {
        synchronized (EventRecorder.class) {
            m2473a((LockAction) new LockAction() {
                public boolean run(FileLocker fileLocker) {
                    try {
                        File unused = EventRecorder.f2125a = new File(MobSDK.getContext().getFilesDir(), ".mrecord");
                        if (!EventRecorder.f2125a.exists()) {
                            EventRecorder.f2125a.createNewFile();
                        }
                        FileOutputStream unused2 = EventRecorder.f2126b = new FileOutputStream(EventRecorder.f2125a, true);
                        return false;
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29787w(th);
                        return false;
                    }
                }
            });
        }
    }

    public static final synchronized void addBegin(String str, String str2) {
        synchronized (EventRecorder.class) {
            m2474a(str + " " + str2 + " 0\n");
        }
    }

    /* renamed from: a */
    private static final void m2473a(LockAction lockAction) {
        C2300e.m2467a(new File(MobSDK.getContext().getFilesDir(), "comm/locks/.mrlock"), lockAction);
    }

    public static final synchronized void addEnd(String str, String str2) {
        synchronized (EventRecorder.class) {
            m2474a(str + " " + str2 + " 1\n");
        }
    }

    /* renamed from: a */
    private static final void m2474a(final String str) {
        m2473a((LockAction) new LockAction() {
            public boolean run(FileLocker fileLocker) {
                try {
                    EventRecorder.f2126b.write(str.getBytes("utf-8"));
                    EventRecorder.f2126b.flush();
                    return false;
                } catch (Throwable th) {
                    MobLog.getInstance().mo29787w(th);
                    return false;
                }
            }
        });
    }

    public static final synchronized String checkRecord(final String str) {
        synchronized (EventRecorder.class) {
            final LinkedList linkedList = new LinkedList();
            m2473a((LockAction) new LockAction() {
                public boolean run(FileLocker fileLocker) {
                    int indexOf;
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(EventRecorder.f2125a), "utf-8"));
                        for (String readLine = bufferedReader.readLine(); !TextUtils.isEmpty(readLine); readLine = bufferedReader.readLine()) {
                            String[] split = readLine.split(" ");
                            if (str.equals(split[0])) {
                                if ("0".equals(split[2])) {
                                    linkedList.add(split[1]);
                                } else if ("1".equals(split[2]) && (indexOf = linkedList.indexOf(split[1])) != -1) {
                                    linkedList.remove(indexOf);
                                }
                            }
                        }
                        bufferedReader.close();
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29769d(th);
                    }
                    return false;
                }
            });
            if (linkedList.size() <= 0) {
                return null;
            }
            String str2 = (String) linkedList.get(0);
            return str2;
        }
    }

    public static final synchronized void clear() {
        synchronized (EventRecorder.class) {
            m2473a((LockAction) new LockAction() {
                public boolean run(FileLocker fileLocker) {
                    try {
                        EventRecorder.f2126b.close();
                        EventRecorder.f2125a.delete();
                        File unused = EventRecorder.f2125a = new File(MobSDK.getContext().getFilesDir(), ".mrecord");
                        EventRecorder.f2125a.createNewFile();
                        FileOutputStream unused2 = EventRecorder.f2126b = new FileOutputStream(EventRecorder.f2125a, true);
                        return false;
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29787w(th);
                        return false;
                    }
                }
            });
        }
    }
}
