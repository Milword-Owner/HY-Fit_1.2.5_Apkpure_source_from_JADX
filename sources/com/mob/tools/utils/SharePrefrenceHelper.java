package com.mob.tools.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobstat.Config;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;

public class SharePrefrenceHelper {
    private Context context;
    private MobSharePreference prefrence;

    public interface OnCommitListener {
        void onCommit(Throwable th);
    }

    public SharePrefrenceHelper(Context context2) {
        if (context2 != null) {
            this.context = context2.getApplicationContext();
        }
    }

    public void open(String str) {
        open(str, 0);
    }

    public void open(String str, int i) {
        this.prefrence = new MobSharePreference(this.context, str + Config.replace + i);
    }

    public void putString(String str, String str2) {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            mobSharePreference.putString(str, str2);
        }
    }

    public String getString(String str) {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            return mobSharePreference.getString(str, "");
        }
        return "";
    }

    public String getString(String str, String str2) {
        MobSharePreference mobSharePreference = this.prefrence;
        return mobSharePreference != null ? mobSharePreference.getString(str, str2) : str2;
    }

    public void putBoolean(String str, Boolean bool) {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            mobSharePreference.putBoolean(str, bool.booleanValue());
        }
    }

    public boolean getBoolean(String str) {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            return mobSharePreference.getBoolean(str, false);
        }
        return false;
    }

    public boolean getBoolean(String str, boolean z) {
        MobSharePreference mobSharePreference = this.prefrence;
        return mobSharePreference != null ? mobSharePreference.getBoolean(str, z) : z;
    }

    public void putLong(String str, Long l) {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            mobSharePreference.putLong(str, l.longValue());
        }
    }

    public long getLong(String str) {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            return mobSharePreference.getLong(str, 0);
        }
        return 0;
    }

    public long getLong(String str, long j) {
        MobSharePreference mobSharePreference = this.prefrence;
        return mobSharePreference != null ? mobSharePreference.getLong(str, j) : j;
    }

    public void putInt(String str, Integer num) {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            mobSharePreference.putInt(str, num.intValue());
        }
    }

    public int getInt(String str) {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            return mobSharePreference.getInt(str, 0);
        }
        return 0;
    }

    public int getInt(String str, int i) {
        MobSharePreference mobSharePreference = this.prefrence;
        return mobSharePreference != null ? mobSharePreference.getInt(str, i) : i;
    }

    public void putFloat(String str, Float f) {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            mobSharePreference.putFloat(str, f.floatValue());
        }
    }

    public float getFloat(String str) {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            return mobSharePreference.getFloat(str, 0.0f);
        }
        return 0.0f;
    }

    public float getFloat(String str, float f) {
        MobSharePreference mobSharePreference = this.prefrence;
        return mobSharePreference != null ? mobSharePreference.getFloat(str, f) : f;
    }

    public void put(String str, Object obj) {
        if (obj != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(obj);
                objectOutputStream.flush();
                objectOutputStream.close();
                putString(str, Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2));
            } catch (Throwable th) {
                MobLog.getInstance().mo29787w(th);
            }
        }
    }

    public Object get(String str) {
        try {
            String string = getString(str);
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(string, 2)));
            Object readObject = objectInputStream.readObject();
            objectInputStream.close();
            return readObject;
        } catch (Throwable th) {
            MobLog.getInstance().mo29787w(th);
            return null;
        }
    }

    public HashMap<String, Object> getAll() {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            return mobSharePreference.getAll();
        }
        return new HashMap<>();
    }

    public void putAll(HashMap<String, Object> hashMap) {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            mobSharePreference.putAll(hashMap);
        }
    }

    public void remove(String str) {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            mobSharePreference.remove(str);
        }
    }

    public void clear() {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            mobSharePreference.clear();
        }
    }

    public void setOnCommitListener(OnCommitListener onCommitListener) {
        MobSharePreference mobSharePreference = this.prefrence;
        if (mobSharePreference != null) {
            mobSharePreference.setOnCommitListener(onCommitListener);
        }
    }

    private static final class MobSharePreference {
        private static Handler handler = MobHandlerThread.newHandler("s", (Handler.Callback) new Handler.Callback() {
            public boolean handleMessage(Message message) {
                OnCommitListener onCommitListener;
                try {
                    onCommitListener = (OnCommitListener) message.obj;
                } catch (Throwable unused) {
                    onCommitListener = null;
                }
                try {
                    Bundle data = message.getData();
                    String string = data.getString("json");
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(data.getString("file")), "utf-8");
                    outputStreamWriter.append(string);
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    if (onCommitListener == null) {
                        return false;
                    }
                    onCommitListener.onCommit((Throwable) null);
                    return false;
                } catch (Throwable th) {
                    MobLog.getInstance().mo29787w(th);
                    if (onCommitListener == null) {
                        return false;
                    }
                    onCommitListener.onCommit(th);
                    return false;
                }
            }
        });
        private Hashon hashon;
        private OnCommitListener listener;
        private File spFile;
        private HashMap<String, Object> spMap;

        public MobSharePreference(Context context, String str) {
            if (context != null) {
                try {
                    this.spFile = new File(new File(context.getFilesDir(), "Mob"), str);
                    if (!this.spFile.getParentFile().exists()) {
                        this.spFile.getParentFile().mkdirs();
                    }
                } catch (Throwable th) {
                    MobLog.getInstance().mo29769d(th);
                    return;
                }
            }
            this.spMap = new HashMap<>();
            this.hashon = new Hashon();
            open();
        }

        private void open() {
            synchronized (this.spMap) {
                if (this.spFile.exists()) {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.spFile), "utf-8"));
                        StringBuilder sb = new StringBuilder();
                        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                            if (sb.length() > 0) {
                                sb.append("\n");
                            }
                            sb.append(readLine);
                        }
                        bufferedReader.close();
                        this.spMap = this.hashon.fromJson(sb.toString());
                    } catch (Throwable th) {
                        MobLog.getInstance().mo29787w(th);
                    }
                }
            }
        }

        private Object get(String str) {
            Object obj;
            synchronized (this.spMap) {
                obj = this.spMap.get(str);
            }
            return obj;
        }

        private void put(String str, Object obj) {
            synchronized (this.spMap) {
                this.spMap.put(str, obj);
                if (handler != null) {
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("json", this.hashon.fromHashMap(this.spMap));
                    bundle.putString("file", this.spFile.getAbsolutePath());
                    message.setData(bundle);
                    message.what = 1;
                    message.obj = this.listener;
                    handler.sendMessage(message);
                }
            }
        }

        public byte getByte(String str, byte b) {
            Object obj = get(str);
            return obj != null ? ((Number) obj).byteValue() : b;
        }

        public void putByte(String str, byte b) {
            put(str, Byte.valueOf(b));
        }

        public short getShort(String str, short s) {
            Object obj = get(str);
            return obj != null ? ((Number) obj).shortValue() : s;
        }

        public void putShort(String str, short s) {
            put(str, Short.valueOf(s));
        }

        public int getInt(String str, int i) {
            Object obj = get(str);
            return obj != null ? ((Number) obj).intValue() : i;
        }

        public void putInt(String str, int i) {
            put(str, Integer.valueOf(i));
        }

        public long getLong(String str, long j) {
            Object obj = get(str);
            return obj != null ? ((Number) obj).longValue() : j;
        }

        public void putLong(String str, long j) {
            put(str, Long.valueOf(j));
        }

        public float getFloat(String str, float f) {
            Object obj = get(str);
            return obj != null ? ((Number) obj).floatValue() : f;
        }

        public void putFloat(String str, float f) {
            put(str, Float.valueOf(f));
        }

        public double getDouble(String str, double d) {
            Object obj = get(str);
            return obj != null ? ((Number) obj).doubleValue() : d;
        }

        public void putDouble(String str, double d) {
            put(str, Double.valueOf(d));
        }

        public char getChar(String str, char c) {
            Object obj = get(str);
            return obj != null ? ((String) obj).charAt(0) : c;
        }

        public void putChar(String str, char c) {
            putString(str, String.valueOf(c));
        }

        public boolean getBoolean(String str, boolean z) {
            Object obj = get(str);
            if (obj != null) {
                return ((Number) obj).byteValue() == 1;
            }
            return z;
        }

        public void putBoolean(String str, boolean z) {
            putByte(str, z ? (byte) 1 : 0);
        }

        public String getString(String str, String str2) {
            Object obj = get(str);
            return obj != null ? (String) obj : str2;
        }

        public void putString(String str, String str2) {
            put(str, str2);
        }

        public HashMap<String, Object> getAll() {
            HashMap<String, Object> hashMap;
            synchronized (this.spMap) {
                hashMap = new HashMap<>();
                hashMap.putAll(this.spMap);
            }
            return hashMap;
        }

        public void putAll(HashMap<String, Object> hashMap) {
            synchronized (this.spMap) {
                this.spMap.putAll(hashMap);
            }
            if (handler != null) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("json", this.hashon.fromHashMap(this.spMap));
                bundle.putString("file", this.spFile.getAbsolutePath());
                message.setData(bundle);
                message.what = 1;
                message.obj = this.listener;
                handler.sendMessage(message);
            }
        }

        public void remove(String str) {
            put(str, (Object) null);
        }

        public void clear() {
            synchronized (this.spMap) {
                this.spMap.clear();
            }
            if (handler != null) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("json", this.hashon.fromHashMap(this.spMap));
                bundle.putString("file", this.spFile.getAbsolutePath());
                message.setData(bundle);
                message.what = 1;
                message.obj = this.listener;
                handler.sendMessage(message);
            }
        }

        public void setOnCommitListener(OnCommitListener onCommitListener) {
            this.listener = onCommitListener;
        }
    }
}
