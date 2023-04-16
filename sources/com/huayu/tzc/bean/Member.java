package com.huayu.tzc.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.github.mikephil.charting.utils.Utils;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Parcelize
@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b7\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u000f\b\u0016\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tB\u000f\b\u0016\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fBo\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0004\u0012\u0006\u0010\u000e\u001a\u00020\u0004\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0006\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0004\u0012\u0006\u0010\u0014\u001a\u00020\u0004\u0012\u0006\u0010\u0015\u001a\u00020\u0004\u0012\u0006\u0010\u0016\u001a\u00020\u0004\u0012\u0006\u0010\u0017\u001a\u00020\u0006\u0012\u0006\u0010\u0018\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0006¢\u0006\u0002\u0010\u001aJ\t\u0010:\u001a\u00020\u0006HÆ\u0003J\t\u0010;\u001a\u00020\u0004HÆ\u0003J\t\u0010<\u001a\u00020\u0006HÆ\u0003J\t\u0010=\u001a\u00020\u0006HÆ\u0003J\t\u0010>\u001a\u00020\u0006HÆ\u0003J\t\u0010?\u001a\u00020\u0004HÆ\u0003J\t\u0010@\u001a\u00020\u0004HÆ\u0003J\t\u0010A\u001a\u00020\u0004HÆ\u0003J\t\u0010B\u001a\u00020\u0006HÆ\u0003J\t\u0010C\u001a\u00020\u0012HÆ\u0003J\t\u0010D\u001a\u00020\u0004HÆ\u0003J\t\u0010E\u001a\u00020\u0004HÆ\u0003J\t\u0010F\u001a\u00020\u0004HÆ\u0003J\u0001\u0010G\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u00042\b\b\u0002\u0010\u000e\u001a\u00020\u00042\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00062\b\b\u0002\u0010\u0019\u001a\u00020\u0006HÆ\u0001J\t\u0010H\u001a\u00020\u0006HÖ\u0001J\u0013\u0010I\u001a\u00020J2\b\u0010K\u001a\u0004\u0018\u00010LHÖ\u0003J\u0010\u0010M\u001a\u00020\u00062\u0006\u0010N\u001a\u00020\u0004H\u0002J\u0006\u0010O\u001a\u00020\u0006J\u0006\u0010P\u001a\u00020\u0006J\t\u0010Q\u001a\u00020\u0006HÖ\u0001J\t\u0010R\u001a\u00020\u0004HÖ\u0001J\u0019\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020\u0006HÖ\u0001R\u001a\u0010\u0019\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\tR\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001c\"\u0004\b\u001f\u0010\tR\u001a\u0010\r\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\u000e\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R\u001a\u0010\u0010\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001c\"\u0004\b)\u0010\tR\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010\u0013\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010!\"\u0004\b/\u0010#R\u001a\u0010\u0014\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010!\"\u0004\b1\u0010#R\u001a\u0010\u0015\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010!\"\u0004\b3\u0010#R\u001a\u0010\u0016\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010!\"\u0004\b5\u0010#R\u001a\u0010\u0017\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u001c\"\u0004\b7\u0010\tR\u001a\u0010\u0018\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u001c\"\u0004\b9\u0010\t¨\u0006X"}, mo21895d2 = {"Lcom/huayu/tzc/bean/Member;", "Landroid/os/Parcelable;", "()V", "name", "", "flag", "", "(Ljava/lang/String;I)V", "id", "(I)V", "user", "Lcom/huayu/tzc/bean/User;", "(Lcom/huayu/tzc/bean/User;)V", "m_avatar", "m_birthday", "m_create_date", "m_gender", "m_goal", "", "m_height", "m_modify_date", "m_nickname", "m_weight", "member_id", "user_id", "age", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;III)V", "getAge", "()I", "setAge", "getFlag", "setFlag", "getM_avatar", "()Ljava/lang/String;", "setM_avatar", "(Ljava/lang/String;)V", "getM_birthday", "setM_birthday", "getM_create_date", "setM_create_date", "getM_gender", "setM_gender", "getM_goal", "()D", "setM_goal", "(D)V", "getM_height", "setM_height", "getM_modify_date", "setM_modify_date", "getM_nickname", "setM_nickname", "getM_weight", "setM_weight", "getMember_id", "setMember_id", "getUser_id", "setUser_id", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "", "other", "", "getAgeByBirthday", "strDate", "getHeight", "getUserAge", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: Member.kt */
public final class Member implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private int age;
    private int flag;
    @NotNull
    private String m_avatar;
    @NotNull
    private String m_birthday;
    @NotNull
    private String m_create_date;
    private int m_gender;
    private double m_goal;
    @NotNull
    private String m_height;
    @NotNull
    private String m_modify_date;
    @NotNull
    private String m_nickname;
    @NotNull
    private String m_weight;
    private int member_id;
    private int user_id;

    @Metadata(mo21893bv = {1, 0, 3}, mo21896k = 3, mo21897mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new Member(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readDouble(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new Member[i];
        }
    }

    public static /* synthetic */ Member copy$default(Member member, int i, String str, String str2, String str3, int i2, double d, String str4, String str5, String str6, String str7, int i3, int i4, int i5, int i6, Object obj) {
        Member member2 = member;
        int i7 = i6;
        return member.copy((i7 & 1) != 0 ? member2.flag : i, (i7 & 2) != 0 ? member2.m_avatar : str, (i7 & 4) != 0 ? member2.m_birthday : str2, (i7 & 8) != 0 ? member2.m_create_date : str3, (i7 & 16) != 0 ? member2.m_gender : i2, (i7 & 32) != 0 ? member2.m_goal : d, (i7 & 64) != 0 ? member2.m_height : str4, (i7 & 128) != 0 ? member2.m_modify_date : str5, (i7 & 256) != 0 ? member2.m_nickname : str6, (i7 & 512) != 0 ? member2.m_weight : str7, (i7 & 1024) != 0 ? member2.member_id : i3, (i7 & 2048) != 0 ? member2.user_id : i4, (i7 & 4096) != 0 ? member2.age : i5);
    }

    public final int component1() {
        return this.flag;
    }

    @NotNull
    public final String component10() {
        return this.m_weight;
    }

    public final int component11() {
        return this.member_id;
    }

    public final int component12() {
        return this.user_id;
    }

    public final int component13() {
        return this.age;
    }

    @NotNull
    public final String component2() {
        return this.m_avatar;
    }

    @NotNull
    public final String component3() {
        return this.m_birthday;
    }

    @NotNull
    public final String component4() {
        return this.m_create_date;
    }

    public final int component5() {
        return this.m_gender;
    }

    public final double component6() {
        return this.m_goal;
    }

    @NotNull
    public final String component7() {
        return this.m_height;
    }

    @NotNull
    public final String component8() {
        return this.m_modify_date;
    }

    @NotNull
    public final String component9() {
        return this.m_nickname;
    }

    @NotNull
    public final Member copy(int i, @NotNull String str, @NotNull String str2, @NotNull String str3, int i2, double d, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, int i3, int i4, int i5) {
        String str8 = str;
        Intrinsics.checkParameterIsNotNull(str8, "m_avatar");
        String str9 = str2;
        Intrinsics.checkParameterIsNotNull(str9, "m_birthday");
        String str10 = str3;
        Intrinsics.checkParameterIsNotNull(str10, "m_create_date");
        String str11 = str4;
        Intrinsics.checkParameterIsNotNull(str11, "m_height");
        String str12 = str5;
        Intrinsics.checkParameterIsNotNull(str12, "m_modify_date");
        String str13 = str6;
        Intrinsics.checkParameterIsNotNull(str13, "m_nickname");
        String str14 = str7;
        Intrinsics.checkParameterIsNotNull(str14, "m_weight");
        return new Member(i, str8, str9, str10, i2, d, str11, str12, str13, str14, i3, i4, i5);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Member)) {
            return false;
        }
        Member member = (Member) obj;
        return this.flag == member.flag && Intrinsics.areEqual((Object) this.m_avatar, (Object) member.m_avatar) && Intrinsics.areEqual((Object) this.m_birthday, (Object) member.m_birthday) && Intrinsics.areEqual((Object) this.m_create_date, (Object) member.m_create_date) && this.m_gender == member.m_gender && Double.compare(this.m_goal, member.m_goal) == 0 && Intrinsics.areEqual((Object) this.m_height, (Object) member.m_height) && Intrinsics.areEqual((Object) this.m_modify_date, (Object) member.m_modify_date) && Intrinsics.areEqual((Object) this.m_nickname, (Object) member.m_nickname) && Intrinsics.areEqual((Object) this.m_weight, (Object) member.m_weight) && this.member_id == member.member_id && this.user_id == member.user_id && this.age == member.age;
    }

    public int hashCode() {
        int i = this.flag * 31;
        String str = this.m_avatar;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.m_birthday;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.m_create_date;
        int hashCode3 = str3 != null ? str3.hashCode() : 0;
        long doubleToLongBits = Double.doubleToLongBits(this.m_goal);
        int i3 = (((((hashCode2 + hashCode3) * 31) + this.m_gender) * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        String str4 = this.m_height;
        int hashCode4 = (i3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.m_modify_date;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.m_nickname;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.m_weight;
        if (str7 != null) {
            i2 = str7.hashCode();
        }
        return ((((((hashCode6 + i2) * 31) + this.member_id) * 31) + this.user_id) * 31) + this.age;
    }

    @NotNull
    public String toString() {
        return "Member(flag=" + this.flag + ", m_avatar=" + this.m_avatar + ", m_birthday=" + this.m_birthday + ", m_create_date=" + this.m_create_date + ", m_gender=" + this.m_gender + ", m_goal=" + this.m_goal + ", m_height=" + this.m_height + ", m_modify_date=" + this.m_modify_date + ", m_nickname=" + this.m_nickname + ", m_weight=" + this.m_weight + ", member_id=" + this.member_id + ", user_id=" + this.user_id + ", age=" + this.age + ")";
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeInt(this.flag);
        parcel.writeString(this.m_avatar);
        parcel.writeString(this.m_birthday);
        parcel.writeString(this.m_create_date);
        parcel.writeInt(this.m_gender);
        parcel.writeDouble(this.m_goal);
        parcel.writeString(this.m_height);
        parcel.writeString(this.m_modify_date);
        parcel.writeString(this.m_nickname);
        parcel.writeString(this.m_weight);
        parcel.writeInt(this.member_id);
        parcel.writeInt(this.user_id);
        parcel.writeInt(this.age);
    }

    public Member(int i, @NotNull String str, @NotNull String str2, @NotNull String str3, int i2, double d, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, int i3, int i4, int i5) {
        Intrinsics.checkParameterIsNotNull(str, "m_avatar");
        Intrinsics.checkParameterIsNotNull(str2, "m_birthday");
        Intrinsics.checkParameterIsNotNull(str3, "m_create_date");
        Intrinsics.checkParameterIsNotNull(str4, "m_height");
        Intrinsics.checkParameterIsNotNull(str5, "m_modify_date");
        Intrinsics.checkParameterIsNotNull(str6, "m_nickname");
        Intrinsics.checkParameterIsNotNull(str7, "m_weight");
        this.flag = i;
        this.m_avatar = str;
        this.m_birthday = str2;
        this.m_create_date = str3;
        this.m_gender = i2;
        this.m_goal = d;
        this.m_height = str4;
        this.m_modify_date = str5;
        this.m_nickname = str6;
        this.m_weight = str7;
        this.member_id = i3;
        this.user_id = i4;
        this.age = i5;
    }

    public final int getFlag() {
        return this.flag;
    }

    public final void setFlag(int i) {
        this.flag = i;
    }

    @NotNull
    public final String getM_avatar() {
        return this.m_avatar;
    }

    public final void setM_avatar(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.m_avatar = str;
    }

    @NotNull
    public final String getM_birthday() {
        return this.m_birthday;
    }

    public final void setM_birthday(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.m_birthday = str;
    }

    @NotNull
    public final String getM_create_date() {
        return this.m_create_date;
    }

    public final void setM_create_date(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.m_create_date = str;
    }

    public final int getM_gender() {
        return this.m_gender;
    }

    public final void setM_gender(int i) {
        this.m_gender = i;
    }

    public final double getM_goal() {
        return this.m_goal;
    }

    public final void setM_goal(double d) {
        this.m_goal = d;
    }

    @NotNull
    public final String getM_height() {
        return this.m_height;
    }

    public final void setM_height(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.m_height = str;
    }

    @NotNull
    public final String getM_modify_date() {
        return this.m_modify_date;
    }

    public final void setM_modify_date(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.m_modify_date = str;
    }

    @NotNull
    public final String getM_nickname() {
        return this.m_nickname;
    }

    public final void setM_nickname(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.m_nickname = str;
    }

    @NotNull
    public final String getM_weight() {
        return this.m_weight;
    }

    public final void setM_weight(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.m_weight = str;
    }

    public final int getMember_id() {
        return this.member_id;
    }

    public final void setMember_id(int i) {
        this.member_id = i;
    }

    public final int getUser_id() {
        return this.user_id;
    }

    public final void setUser_id(int i) {
        this.user_id = i;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Member(int i, String str, String str2, String str3, int i2, double d, String str4, String str5, String str6, String str7, int i3, int i4, int i5, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, str, str2, str3, i2, d, str4, str5, str6, str7, i3, i4, (i6 & 4096) != 0 ? 18 : i5);
    }

    public final int getAge() {
        return this.age;
    }

    public final void setAge(int i) {
        this.age = i;
    }

    public Member() {
        this(0, "", "", "", 0, Utils.DOUBLE_EPSILON, "", "", "", "", -1, -1, 0, 4096, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Member(@NotNull String str, int i) {
        this(i, "", "", "", 0, Utils.DOUBLE_EPSILON, "", "", str, "", -1, -1, 0, 4096, (DefaultConstructorMarker) null);
        Intrinsics.checkParameterIsNotNull(str, "name");
    }

    public Member(int i) {
        this(0, "", "", "", 0, Utils.DOUBLE_EPSILON, "", "", "", "", i, -1, 0, 4096, (DefaultConstructorMarker) null);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public Member(@NotNull User user) {
        this();
        Intrinsics.checkParameterIsNotNull(user, "user");
        this.flag = user.getFlag();
        this.m_avatar = user.getU_avatar();
        this.m_create_date = user.getCreate_date();
        this.m_gender = user.getU_gender();
        this.m_goal = user.getU_goal();
        this.m_height = user.getU_height();
        this.m_modify_date = user.getLast_update_date();
        this.m_nickname = user.getU_nickname();
        this.m_weight = user.getU_weight();
        this.m_birthday = user.getU_birthday();
        this.member_id = 0;
        this.user_id = user.getU_id();
    }

    public final int getUserAge() {
        if (!(this.m_birthday.length() > 0)) {
            return this.age;
        }
        this.age = getAgeByBirthday(this.m_birthday);
        return this.age;
    }

    private final int getAgeByBirthday(String str) {
        Date parse = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(str, new ParsePosition(0));
        Calendar instance = Calendar.getInstance();
        if (!instance.before(parse)) {
            int i = instance.get(1);
            int i2 = instance.get(2) + 1;
            int i3 = instance.get(5);
            Intrinsics.checkExpressionValueIsNotNull(instance, "cal");
            instance.setTime(parse);
            int i4 = instance.get(1);
            int i5 = instance.get(2) + 1;
            int i6 = instance.get(5);
            int i7 = i - i4;
            if (i2 <= i5) {
                return (i2 != i5 || i3 < i6) ? i7 - 1 : i7;
            }
            return i7;
        }
        throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!".toString());
    }

    public final int getHeight() {
        if (!StringsKt.contains$default((CharSequence) this.m_height, (CharSequence) "'", false, 2, (Object) null)) {
            return Integer.parseInt(this.m_height);
        }
        Object[] array = new Regex("'").split(this.m_height, 0).toArray(new String[0]);
        if (array != null) {
            String[] strArr = (String[]) array;
            int parseInt = Integer.parseInt(strArr[0]);
            int parseInt2 = Integer.parseInt(StringsKt.replace$default(strArr[1], "\"", "", false, 4, (Object) null));
            double d = (double) parseInt;
            Double.isNaN(d);
            double d2 = (double) ((float) (d * 30.48d));
            double d3 = (double) parseInt2;
            Double.isNaN(d3);
            Double.isNaN(d2);
            return MathKt.roundToInt((float) (d2 + (d3 * 2.45d)));
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
