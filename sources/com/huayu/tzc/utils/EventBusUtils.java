package com.huayu.tzc.utils;

import com.huayu.tzc.bean.Member;
import com.huayu.tzc.bean.User;
import java.util.List;
import org.greenrobot.eventbus.EventBus;

public class EventBusUtils {
    public static void register(Object obj) {
        EventBus eventBus = EventBus.getDefault();
        if (!eventBus.isRegistered(obj)) {
            eventBus.register(obj);
        }
    }

    public static void unregister(Object obj) {
        EventBus eventBus = EventBus.getDefault();
        if (eventBus.isRegistered(obj)) {
            eventBus.unregister(obj);
        }
    }

    public static void post(EventMessage eventMessage) {
        EventBus.getDefault().post(eventMessage);
    }

    public static void postSticky(EventMessage eventMessage) {
        EventBus.getDefault().postSticky(eventMessage);
    }

    public static class EventMessage<T> {
        private int code;
        private T data;
        private String endTime;
        private String headImgs;

        /* renamed from: id */
        private String f1690id;
        private String startTime;
        private User userBean;
        private List<Member> userBeanList;

        public User getUserBean() {
            return this.userBean;
        }

        public List<Member> getUserBeanList() {
            return this.userBeanList;
        }

        public EventMessage(int i, String str, String str2) {
            this.code = i;
            this.startTime = str;
            this.endTime = str2;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public void setStartTime(String str) {
            this.startTime = str;
        }

        public String getEndTime() {
            return this.endTime;
        }

        public void setEndTime(String str) {
            this.endTime = str;
        }

        public void setUserBean(User user) {
            this.userBean = user;
        }

        public void setUserBeanList(List<Member> list) {
            this.userBeanList = list;
        }

        public EventMessage(int i, List<Member> list, int i2) {
            this.code = i;
            this.userBeanList = list;
        }

        public EventMessage(int i, User user) {
            this.code = i;
            this.userBean = user;
        }

        public EventMessage(int i) {
            this.code = i;
        }

        public EventMessage(int i, T t) {
            this.code = i;
            this.data = t;
        }

        public EventMessage(int i, String str, T t) {
            this.code = i;
            this.f1690id = str;
            this.data = t;
        }

        public EventMessage(int i, String str, String str2, T t) {
            this.code = i;
            this.f1690id = str;
            this.headImgs = str2;
            this.data = t;
        }

        public int getCode() {
            return this.code;
        }

        public void setCode(int i) {
            this.code = i;
        }

        public T getData() {
            return this.data;
        }

        public void setData(T t) {
            this.data = t;
        }

        public String getId() {
            return this.f1690id;
        }

        public void setId(String str) {
            this.f1690id = str;
        }

        public String getHeadImgs() {
            return this.headImgs;
        }

        public void setHeadImgs(String str) {
            this.headImgs = str;
        }

        public String toString() {
            return "EventMessage{code=" + this.code + ", data=" + this.data + '}';
        }
    }

    public class EventCode {
        public static final int EVENT_DETAIL_QUIT_ROOM = 104;
        public static final int EVENT_DETAIL_SEND_REFRESH = 102;
        public static final int EVENT_MESSAGE_GIFT_SEND = 103;
        public static final int EVENT_MESSAGE_roomspeak = 106;
        public static final int EVENT_OCR = 101;
        public static final int EVENT_RELOGIN = 105;
        public static final int EVENT_SEARCH = 100;

        public EventCode() {
        }
    }
}
