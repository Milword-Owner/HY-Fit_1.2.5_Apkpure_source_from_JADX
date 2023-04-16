package com.baidu.mobstat;

public class MtjConfig {
    public static final String BAIDU_MTJ_PUSH_CALL = "Baidu_mtj_push_call";
    public static final String BAIDU_MTJ_PUSH_MSG = "Baidu_mtj_push_msg";

    public enum FeedTrackStrategy {
        TRACK_ALL,
        TRACK_SINGLE,
        TRACK_NONE
    }

    public enum PushPlatform {
        BAIDUYUN("baiduyun", 0),
        JIGUANG("jiguang", 1),
        GETUI("getui", 2),
        HUAWEI("huawei", 3),
        XIAOMI("xiaomi", 4),
        UMENG("umeng", 5),
        XINGE("xinge", 6),
        ALIYUN("aliyun", 7),
        OPPO("oppo", 8),
        MEIZU("meizu", 9);
        

        /* renamed from: a */
        private String f888a;

        /* renamed from: b */
        private int f889b;

        private PushPlatform(String str, int i) {
            this.f888a = str;
            this.f889b = i;
        }

        public String value() {
            return "p" + this.f889b;
        }

        public String showName() {
            return this.f888a;
        }
    }
}
