package com.huayu.tzc.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b)\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0001\u0012\u0006\u0010\t\u001a\u00020\u0001\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003¢\u0006\u0002\u0010\rJ\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0001HÆ\u0003J\t\u0010)\u001a\u00020\u0007HÆ\u0003J\t\u0010*\u001a\u00020\u0001HÆ\u0003J\t\u0010+\u001a\u00020\u0001HÆ\u0003J\t\u0010,\u001a\u00020\u0007HÆ\u0003J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u0003HÆ\u0003Jc\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00012\b\b\u0002\u0010\t\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003HÆ\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u00020\u0007HÖ\u0001J\t\u00104\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000f\"\u0004\b\u0013\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\b\u001a\u00020\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017R\u001a\u0010\t\u001a\u00020\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0015\"\u0004\b\u001f\u0010\u0017R\u001a\u0010\n\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001bR\u001a\u0010\u000b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u000f\"\u0004\b#\u0010\u0011R\u001a\u0010\f\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u000f\"\u0004\b%\u0010\u0011¨\u00065"}, mo21895d2 = {"Lcom/huayu/tzc/bean/Tweet;", "", "content", "", "createdate", "creater", "giveuptotal", "", "modifier", "modifydate", "pushmessageid", "summary", "title", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;ILjava/lang/Object;Ljava/lang/Object;ILjava/lang/String;Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "setContent", "(Ljava/lang/String;)V", "getCreatedate", "setCreatedate", "getCreater", "()Ljava/lang/Object;", "setCreater", "(Ljava/lang/Object;)V", "getGiveuptotal", "()I", "setGiveuptotal", "(I)V", "getModifier", "setModifier", "getModifydate", "setModifydate", "getPushmessageid", "setPushmessageid", "getSummary", "setSummary", "getTitle", "setTitle", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: Tweet.kt */
public final class Tweet {
    @NotNull
    private String content;
    @NotNull
    private String createdate;
    @NotNull
    private Object creater;
    private int giveuptotal;
    @NotNull
    private Object modifier;
    @NotNull
    private Object modifydate;
    private int pushmessageid;
    @NotNull
    private String summary;
    @NotNull
    private String title;

    public static /* synthetic */ Tweet copy$default(Tweet tweet, String str, String str2, Object obj, int i, Object obj2, Object obj3, int i2, String str3, String str4, int i3, Object obj4) {
        Tweet tweet2 = tweet;
        int i4 = i3;
        return tweet.copy((i4 & 1) != 0 ? tweet2.content : str, (i4 & 2) != 0 ? tweet2.createdate : str2, (i4 & 4) != 0 ? tweet2.creater : obj, (i4 & 8) != 0 ? tweet2.giveuptotal : i, (i4 & 16) != 0 ? tweet2.modifier : obj2, (i4 & 32) != 0 ? tweet2.modifydate : obj3, (i4 & 64) != 0 ? tweet2.pushmessageid : i2, (i4 & 128) != 0 ? tweet2.summary : str3, (i4 & 256) != 0 ? tweet2.title : str4);
    }

    @NotNull
    public final String component1() {
        return this.content;
    }

    @NotNull
    public final String component2() {
        return this.createdate;
    }

    @NotNull
    public final Object component3() {
        return this.creater;
    }

    public final int component4() {
        return this.giveuptotal;
    }

    @NotNull
    public final Object component5() {
        return this.modifier;
    }

    @NotNull
    public final Object component6() {
        return this.modifydate;
    }

    public final int component7() {
        return this.pushmessageid;
    }

    @NotNull
    public final String component8() {
        return this.summary;
    }

    @NotNull
    public final String component9() {
        return this.title;
    }

    @NotNull
    public final Tweet copy(@NotNull String str, @NotNull String str2, @NotNull Object obj, int i, @NotNull Object obj2, @NotNull Object obj3, int i2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "content");
        Intrinsics.checkParameterIsNotNull(str2, "createdate");
        Intrinsics.checkParameterIsNotNull(obj, "creater");
        Object obj4 = obj2;
        Intrinsics.checkParameterIsNotNull(obj4, "modifier");
        Object obj5 = obj3;
        Intrinsics.checkParameterIsNotNull(obj5, "modifydate");
        String str5 = str3;
        Intrinsics.checkParameterIsNotNull(str5, "summary");
        String str6 = str4;
        Intrinsics.checkParameterIsNotNull(str6, "title");
        return new Tweet(str, str2, obj, i, obj4, obj5, i2, str5, str6);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tweet)) {
            return false;
        }
        Tweet tweet = (Tweet) obj;
        return Intrinsics.areEqual((Object) this.content, (Object) tweet.content) && Intrinsics.areEqual((Object) this.createdate, (Object) tweet.createdate) && Intrinsics.areEqual(this.creater, tweet.creater) && this.giveuptotal == tweet.giveuptotal && Intrinsics.areEqual(this.modifier, tweet.modifier) && Intrinsics.areEqual(this.modifydate, tweet.modifydate) && this.pushmessageid == tweet.pushmessageid && Intrinsics.areEqual((Object) this.summary, (Object) tweet.summary) && Intrinsics.areEqual((Object) this.title, (Object) tweet.title);
    }

    public int hashCode() {
        String str = this.content;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.createdate;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Object obj = this.creater;
        int hashCode3 = (((hashCode2 + (obj != null ? obj.hashCode() : 0)) * 31) + this.giveuptotal) * 31;
        Object obj2 = this.modifier;
        int hashCode4 = (hashCode3 + (obj2 != null ? obj2.hashCode() : 0)) * 31;
        Object obj3 = this.modifydate;
        int hashCode5 = (((hashCode4 + (obj3 != null ? obj3.hashCode() : 0)) * 31) + this.pushmessageid) * 31;
        String str3 = this.summary;
        int hashCode6 = (hashCode5 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.title;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode6 + i;
    }

    @NotNull
    public String toString() {
        return "Tweet(content=" + this.content + ", createdate=" + this.createdate + ", creater=" + this.creater + ", giveuptotal=" + this.giveuptotal + ", modifier=" + this.modifier + ", modifydate=" + this.modifydate + ", pushmessageid=" + this.pushmessageid + ", summary=" + this.summary + ", title=" + this.title + ")";
    }

    public Tweet(@NotNull String str, @NotNull String str2, @NotNull Object obj, int i, @NotNull Object obj2, @NotNull Object obj3, int i2, @NotNull String str3, @NotNull String str4) {
        Intrinsics.checkParameterIsNotNull(str, "content");
        Intrinsics.checkParameterIsNotNull(str2, "createdate");
        Intrinsics.checkParameterIsNotNull(obj, "creater");
        Intrinsics.checkParameterIsNotNull(obj2, "modifier");
        Intrinsics.checkParameterIsNotNull(obj3, "modifydate");
        Intrinsics.checkParameterIsNotNull(str3, "summary");
        Intrinsics.checkParameterIsNotNull(str4, "title");
        this.content = str;
        this.createdate = str2;
        this.creater = obj;
        this.giveuptotal = i;
        this.modifier = obj2;
        this.modifydate = obj3;
        this.pushmessageid = i2;
        this.summary = str3;
        this.title = str4;
    }

    @NotNull
    public final String getContent() {
        return this.content;
    }

    public final void setContent(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.content = str;
    }

    @NotNull
    public final String getCreatedate() {
        return this.createdate;
    }

    public final void setCreatedate(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.createdate = str;
    }

    @NotNull
    public final Object getCreater() {
        return this.creater;
    }

    public final void setCreater(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "<set-?>");
        this.creater = obj;
    }

    public final int getGiveuptotal() {
        return this.giveuptotal;
    }

    public final void setGiveuptotal(int i) {
        this.giveuptotal = i;
    }

    @NotNull
    public final Object getModifier() {
        return this.modifier;
    }

    public final void setModifier(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "<set-?>");
        this.modifier = obj;
    }

    @NotNull
    public final Object getModifydate() {
        return this.modifydate;
    }

    public final void setModifydate(@NotNull Object obj) {
        Intrinsics.checkParameterIsNotNull(obj, "<set-?>");
        this.modifydate = obj;
    }

    public final int getPushmessageid() {
        return this.pushmessageid;
    }

    public final void setPushmessageid(int i) {
        this.pushmessageid = i;
    }

    @NotNull
    public final String getSummary() {
        return this.summary;
    }

    public final void setSummary(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.summary = str;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    public final void setTitle(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.title = str;
    }
}
