package com.huayu.tzc.bean;

import android.text.TextUtils;
import com.huayu.tzc.base.Constant;

public class TweetTitle {
    private String imgname;
    private String imgurl;
    private String title;

    public String replaceImg(String str) {
        if (!TextUtils.isEmpty(str)) {
            return str.replace(Constant.BASE64_TITLE, "");
        }
        return "";
    }

    public TweetTitle(String str, String str2, String str3) {
        this.title = str;
        this.imgurl = str2;
        this.imgname = str3;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getImgurl() {
        return this.imgurl;
    }

    public void setImgurl(String str) {
        this.imgurl = str;
    }

    public String getImgname() {
        return this.imgname;
    }

    public void setImgname(String str) {
        this.imgname = str;
    }
}
