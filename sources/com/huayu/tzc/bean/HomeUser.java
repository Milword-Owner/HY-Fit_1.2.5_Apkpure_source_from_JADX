package com.huayu.tzc.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b6\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005Be\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\u0007\u0012\u0006\u0010\r\u001a\u00020\u0007\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\t\u0012\u0006\u0010\u0012\u001a\u00020\u0007\u0012\u0006\u0010\u0013\u001a\u00020\u0007¢\u0006\u0002\u0010\u0014J\t\u00101\u001a\u00020\u0007HÆ\u0003J\t\u00102\u001a\u00020\tHÆ\u0003J\t\u00103\u001a\u00020\u0007HÆ\u0003J\t\u00104\u001a\u00020\u0007HÆ\u0003J\t\u00105\u001a\u00020\tHÆ\u0003J\t\u00106\u001a\u00020\tHÆ\u0003J\t\u00107\u001a\u00020\tHÆ\u0003J\t\u00108\u001a\u00020\u0007HÆ\u0003J\t\u00109\u001a\u00020\u0007HÆ\u0003J\t\u0010:\u001a\u00020\tHÆ\u0003J\t\u0010;\u001a\u00020\tHÆ\u0003J\t\u0010<\u001a\u00020\tHÆ\u0003J\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u0007HÆ\u0001J\t\u0010>\u001a\u00020\u0007HÖ\u0001J\u0013\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010BHÖ\u0003J\t\u0010C\u001a\u00020\u0007HÖ\u0001J\t\u0010D\u001a\u00020\tHÖ\u0001J\u0019\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\u0007HÖ\u0001R\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\n\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0016\"\u0004\b\u001a\u0010\u0018R\u001a\u0010\u000b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0016\"\u0004\b\u001c\u0010\u0018R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010\f\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001e\"\u0004\b\"\u0010 R\u001a\u0010\r\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u001e\"\u0004\b$\u0010 R\u001a\u0010\u000e\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0016\"\u0004\b&\u0010\u0018R\u001a\u0010\u0012\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001e\"\u0004\b(\u0010 R\u001a\u0010\u000f\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0016\"\u0004\b*\u0010\u0018R\u001a\u0010\u0010\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0016\"\u0004\b,\u0010\u0018R\u001a\u0010\u0013\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001e\"\u0004\b.\u0010 R\u001a\u0010\u0011\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0016\"\u0004\b0\u0010\u0018¨\u0006J"}, mo21895d2 = {"Lcom/huayu/tzc/bean/HomeUser;", "Landroid/os/Parcelable;", "()V", "user", "Lcom/huayu/tzc/bean/User;", "(Lcom/huayu/tzc/bean/User;)V", "flag", "", "avatar", "", "birthday", "create_date", "gender", "goal", "height", "modify_date", "nickname", "weight", "id", "user_id", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "getAvatar", "()Ljava/lang/String;", "setAvatar", "(Ljava/lang/String;)V", "getBirthday", "setBirthday", "getCreate_date", "setCreate_date", "getFlag", "()I", "setFlag", "(I)V", "getGender", "setGender", "getGoal", "setGoal", "getHeight", "setHeight", "getId", "setId", "getModify_date", "setModify_date", "getNickname", "setNickname", "getUser_id", "setUser_id", "getWeight", "setWeight", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
@Parcelize
/* compiled from: HomeUser.kt */
public final class HomeUser implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    @NotNull
    private String avatar;
    @NotNull
    private String birthday;
    @NotNull
    private String create_date;
    private int flag;
    private int gender;
    private int goal;
    @NotNull
    private String height;

    /* renamed from: id */
    private int f1686id;
    @NotNull
    private String modify_date;
    @NotNull
    private String nickname;
    private int user_id;
    @NotNull
    private String weight;

    @Metadata(mo21893bv = {1, 0, 3}, mo21896k = 3, mo21897mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new HomeUser(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt());
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new HomeUser[i];
        }
    }

    public static /* synthetic */ HomeUser copy$default(HomeUser homeUser, int i, String str, String str2, String str3, int i2, int i3, String str4, String str5, String str6, String str7, int i4, int i5, int i6, Object obj) {
        HomeUser homeUser2 = homeUser;
        int i7 = i6;
        return homeUser.copy((i7 & 1) != 0 ? homeUser2.flag : i, (i7 & 2) != 0 ? homeUser2.avatar : str, (i7 & 4) != 0 ? homeUser2.birthday : str2, (i7 & 8) != 0 ? homeUser2.create_date : str3, (i7 & 16) != 0 ? homeUser2.gender : i2, (i7 & 32) != 0 ? homeUser2.goal : i3, (i7 & 64) != 0 ? homeUser2.height : str4, (i7 & 128) != 0 ? homeUser2.modify_date : str5, (i7 & 256) != 0 ? homeUser2.nickname : str6, (i7 & 512) != 0 ? homeUser2.weight : str7, (i7 & 1024) != 0 ? homeUser2.f1686id : i4, (i7 & 2048) != 0 ? homeUser2.user_id : i5);
    }

    public final int component1() {
        return this.flag;
    }

    @NotNull
    public final String component10() {
        return this.weight;
    }

    public final int component11() {
        return this.f1686id;
    }

    public final int component12() {
        return this.user_id;
    }

    @NotNull
    public final String component2() {
        return this.avatar;
    }

    @NotNull
    public final String component3() {
        return this.birthday;
    }

    @NotNull
    public final String component4() {
        return this.create_date;
    }

    public final int component5() {
        return this.gender;
    }

    public final int component6() {
        return this.goal;
    }

    @NotNull
    public final String component7() {
        return this.height;
    }

    @NotNull
    public final String component8() {
        return this.modify_date;
    }

    @NotNull
    public final String component9() {
        return this.nickname;
    }

    @NotNull
    public final HomeUser copy(int i, @NotNull String str, @NotNull String str2, @NotNull String str3, int i2, int i3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, int i4, int i5) {
        String str8 = str;
        Intrinsics.checkParameterIsNotNull(str8, "avatar");
        String str9 = str2;
        Intrinsics.checkParameterIsNotNull(str9, "birthday");
        String str10 = str3;
        Intrinsics.checkParameterIsNotNull(str10, "create_date");
        String str11 = str4;
        Intrinsics.checkParameterIsNotNull(str11, ViewHierarchyConstants.DIMENSION_HEIGHT_KEY);
        String str12 = str5;
        Intrinsics.checkParameterIsNotNull(str12, "modify_date");
        String str13 = str6;
        Intrinsics.checkParameterIsNotNull(str13, "nickname");
        String str14 = str7;
        Intrinsics.checkParameterIsNotNull(str14, "weight");
        return new HomeUser(i, str8, str9, str10, i2, i3, str11, str12, str13, str14, i4, i5);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HomeUser)) {
            return false;
        }
        HomeUser homeUser = (HomeUser) obj;
        return this.flag == homeUser.flag && Intrinsics.areEqual((Object) this.avatar, (Object) homeUser.avatar) && Intrinsics.areEqual((Object) this.birthday, (Object) homeUser.birthday) && Intrinsics.areEqual((Object) this.create_date, (Object) homeUser.create_date) && this.gender == homeUser.gender && this.goal == homeUser.goal && Intrinsics.areEqual((Object) this.height, (Object) homeUser.height) && Intrinsics.areEqual((Object) this.modify_date, (Object) homeUser.modify_date) && Intrinsics.areEqual((Object) this.nickname, (Object) homeUser.nickname) && Intrinsics.areEqual((Object) this.weight, (Object) homeUser.weight) && this.f1686id == homeUser.f1686id && this.user_id == homeUser.user_id;
    }

    public int hashCode() {
        int i = this.flag * 31;
        String str = this.avatar;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.birthday;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.create_date;
        int hashCode3 = (((((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.gender) * 31) + this.goal) * 31;
        String str4 = this.height;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.modify_date;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.nickname;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.weight;
        if (str7 != null) {
            i2 = str7.hashCode();
        }
        return ((((hashCode6 + i2) * 31) + this.f1686id) * 31) + this.user_id;
    }

    @NotNull
    public String toString() {
        return "HomeUser(flag=" + this.flag + ", avatar=" + this.avatar + ", birthday=" + this.birthday + ", create_date=" + this.create_date + ", gender=" + this.gender + ", goal=" + this.goal + ", height=" + this.height + ", modify_date=" + this.modify_date + ", nickname=" + this.nickname + ", weight=" + this.weight + ", id=" + this.f1686id + ", user_id=" + this.user_id + ")";
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeInt(this.flag);
        parcel.writeString(this.avatar);
        parcel.writeString(this.birthday);
        parcel.writeString(this.create_date);
        parcel.writeInt(this.gender);
        parcel.writeInt(this.goal);
        parcel.writeString(this.height);
        parcel.writeString(this.modify_date);
        parcel.writeString(this.nickname);
        parcel.writeString(this.weight);
        parcel.writeInt(this.f1686id);
        parcel.writeInt(this.user_id);
    }

    public HomeUser(int i, @NotNull String str, @NotNull String str2, @NotNull String str3, int i2, int i3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, int i4, int i5) {
        Intrinsics.checkParameterIsNotNull(str, "avatar");
        Intrinsics.checkParameterIsNotNull(str2, "birthday");
        Intrinsics.checkParameterIsNotNull(str3, "create_date");
        Intrinsics.checkParameterIsNotNull(str4, ViewHierarchyConstants.DIMENSION_HEIGHT_KEY);
        Intrinsics.checkParameterIsNotNull(str5, "modify_date");
        Intrinsics.checkParameterIsNotNull(str6, "nickname");
        Intrinsics.checkParameterIsNotNull(str7, "weight");
        this.flag = i;
        this.avatar = str;
        this.birthday = str2;
        this.create_date = str3;
        this.gender = i2;
        this.goal = i3;
        this.height = str4;
        this.modify_date = str5;
        this.nickname = str6;
        this.weight = str7;
        this.f1686id = i4;
        this.user_id = i5;
    }

    public final int getFlag() {
        return this.flag;
    }

    public final void setFlag(int i) {
        this.flag = i;
    }

    @NotNull
    public final String getAvatar() {
        return this.avatar;
    }

    public final void setAvatar(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.avatar = str;
    }

    @NotNull
    public final String getBirthday() {
        return this.birthday;
    }

    public final void setBirthday(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.birthday = str;
    }

    @NotNull
    public final String getCreate_date() {
        return this.create_date;
    }

    public final void setCreate_date(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.create_date = str;
    }

    public final int getGender() {
        return this.gender;
    }

    public final void setGender(int i) {
        this.gender = i;
    }

    public final int getGoal() {
        return this.goal;
    }

    public final void setGoal(int i) {
        this.goal = i;
    }

    @NotNull
    public final String getHeight() {
        return this.height;
    }

    public final void setHeight(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.height = str;
    }

    @NotNull
    public final String getModify_date() {
        return this.modify_date;
    }

    public final void setModify_date(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.modify_date = str;
    }

    @NotNull
    public final String getNickname() {
        return this.nickname;
    }

    public final void setNickname(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.nickname = str;
    }

    @NotNull
    public final String getWeight() {
        return this.weight;
    }

    public final void setWeight(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.weight = str;
    }

    public final int getId() {
        return this.f1686id;
    }

    public final void setId(int i) {
        this.f1686id = i;
    }

    public final int getUser_id() {
        return this.user_id;
    }

    public final void setUser_id(int i) {
        this.user_id = i;
    }

    public HomeUser() {
        this(0, "", "", "", 0, -1, "", "", "", "", -1, -1);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public HomeUser(@NotNull User user) {
        this();
        Intrinsics.checkParameterIsNotNull(user, "user");
    }
}
