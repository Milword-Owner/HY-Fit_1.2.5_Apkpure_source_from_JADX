package com.huayu.tzc.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.github.mikephil.charting.utils.Utils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\bR\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B­\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0004\u0012\u0006\u0010\n\u001a\u00020\u0004\u0012\u0006\u0010\u000b\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0006\u0012\u0006\u0010\u000e\u001a\u00020\u0006\u0012\u0006\u0010\u000f\u001a\u00020\u0004\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0006\u0012\u0006\u0010\u0013\u001a\u00020\u0004\u0012\u0006\u0010\u0014\u001a\u00020\u0004\u0012\u0006\u0010\u0015\u001a\u00020\u0004\u0012\u0006\u0010\u0016\u001a\u00020\u0006\u0012\u0006\u0010\u0017\u001a\u00020\u0006\u0012\u0006\u0010\u0018\u001a\u00020\u0004\u0012\u0006\u0010\u0019\u001a\u00020\u0006\u0012\u0006\u0010\u001a\u001a\u00020\u0006¢\u0006\u0002\u0010\u001bJ\t\u0010L\u001a\u00020\u0004HÆ\u0003J\t\u0010M\u001a\u00020\u0006HÆ\u0003J\t\u0010N\u001a\u00020\u0004HÆ\u0003J\t\u0010O\u001a\u00020\u0011HÆ\u0003J\t\u0010P\u001a\u00020\u0006HÆ\u0003J\t\u0010Q\u001a\u00020\u0004HÆ\u0003J\t\u0010R\u001a\u00020\u0004HÆ\u0003J\t\u0010S\u001a\u00020\u0004HÆ\u0003J\t\u0010T\u001a\u00020\u0006HÆ\u0003J\t\u0010U\u001a\u00020\u0006HÆ\u0003J\t\u0010V\u001a\u00020\u0004HÆ\u0003J\t\u0010W\u001a\u00020\u0006HÆ\u0003J\t\u0010X\u001a\u00020\u0006HÆ\u0003J\t\u0010Y\u001a\u00020\u0006HÆ\u0003J\t\u0010Z\u001a\u00020\u0006HÆ\u0003J\t\u0010[\u001a\u00020\u0006HÆ\u0003J\t\u0010\\\u001a\u00020\u0004HÆ\u0003J\t\u0010]\u001a\u00020\u0004HÆ\u0003J\t\u0010^\u001a\u00020\u0006HÆ\u0003J\t\u0010_\u001a\u00020\u0006HÆ\u0003J\t\u0010`\u001a\u00020\u0006HÆ\u0003JÛ\u0001\u0010a\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00042\b\b\u0002\u0010\n\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u00062\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0013\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\u00042\b\b\u0002\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0017\u001a\u00020\u00062\b\b\u0002\u0010\u0018\u001a\u00020\u00042\b\b\u0002\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u001a\u001a\u00020\u0006HÆ\u0001J\t\u0010b\u001a\u00020\u0004HÖ\u0001J\u0013\u0010c\u001a\u00020d2\b\u0010e\u001a\u0004\u0018\u00010fHÖ\u0003J\t\u0010g\u001a\u00020\u0004HÖ\u0001J\t\u0010h\u001a\u00020\u0006HÖ\u0001J\u0019\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020l2\u0006\u0010m\u001a\u00020\u0004HÖ\u0001R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\u0007\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R\u001a\u0010\b\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u001d\"\u0004\b)\u0010\u001fR\u001a\u0010\n\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001d\"\u0004\b+\u0010\u001fR\u001a\u0010\u000b\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010!\"\u0004\b-\u0010#R\u001a\u0010\f\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010!\"\u0004\b/\u0010#R\u001a\u0010\r\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010!\"\u0004\b1\u0010#R\u001a\u0010\u000e\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010!\"\u0004\b3\u0010#R\u001a\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u001d\"\u0004\b5\u0010\u001fR\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001a\u0010\u0012\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010!\"\u0004\b;\u0010#R\u001a\u0010\u0013\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u001d\"\u0004\b=\u0010\u001fR\u001a\u0010\u0014\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u001d\"\u0004\b?\u0010\u001fR\u001a\u0010\u0015\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u001d\"\u0004\bA\u0010\u001fR\u001a\u0010\u0016\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010!\"\u0004\bC\u0010#R\u001a\u0010\u0017\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010!\"\u0004\bE\u0010#R\u001a\u0010\u0018\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u001d\"\u0004\bG\u0010\u001fR\u001a\u0010\u0019\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010!\"\u0004\bI\u0010#R\u001a\u0010\u001a\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010!\"\u0004\bK\u0010#¨\u0006n"}, mo21895d2 = {"Lcom/huayu/tzc/bean/User;", "Landroid/os/Parcelable;", "()V", "appleid", "", "create_date", "", "cuurmac", "email", "facebookid", "flag", "heightunit", "last_update_date", "u_avatar", "u_birthday", "u_gender", "u_goal", "", "u_height", "u_id", "u_language", "u_level", "u_nickname", "u_pwd", "u_status", "u_weight", "weightunit", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IDLjava/lang/String;IIILjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getAppleid", "()I", "setAppleid", "(I)V", "getCreate_date", "()Ljava/lang/String;", "setCreate_date", "(Ljava/lang/String;)V", "getCuurmac", "setCuurmac", "getEmail", "setEmail", "getFacebookid", "setFacebookid", "getFlag", "setFlag", "getHeightunit", "setHeightunit", "getLast_update_date", "setLast_update_date", "getU_avatar", "setU_avatar", "getU_birthday", "setU_birthday", "getU_gender", "setU_gender", "getU_goal", "()D", "setU_goal", "(D)V", "getU_height", "setU_height", "getU_id", "setU_id", "getU_language", "setU_language", "getU_level", "setU_level", "getU_nickname", "setU_nickname", "getU_pwd", "setU_pwd", "getU_status", "setU_status", "getU_weight", "setU_weight", "getWeightunit", "setWeightunit", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
@Parcelize
/* compiled from: User.kt */
public final class User implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private int appleid;
    @NotNull
    private String create_date;
    @NotNull
    private String cuurmac;
    @NotNull
    private String email;
    private int facebookid;
    private int flag;
    @NotNull
    private String heightunit;
    @NotNull
    private String last_update_date;
    @NotNull
    private String u_avatar;
    @NotNull
    private String u_birthday;
    private int u_gender;
    private double u_goal;
    @NotNull
    private String u_height;
    private int u_id;
    private int u_language;
    private int u_level;
    @NotNull
    private String u_nickname;
    @NotNull
    private String u_pwd;
    private int u_status;
    @NotNull
    private String u_weight;
    @NotNull
    private String weightunit;

    @Metadata(mo21893bv = {1, 0, 3}, mo21896k = 3, mo21897mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new User(parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readDouble(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readString(), parcel.readString());
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new User[i];
        }
    }

    public static /* synthetic */ User copy$default(User user, int i, String str, String str2, String str3, int i2, int i3, String str4, String str5, String str6, String str7, int i4, double d, String str8, int i5, int i6, int i7, String str9, String str10, int i8, String str11, String str12, int i9, Object obj) {
        int i10;
        int i11;
        int i12;
        String str13;
        String str14;
        String str15;
        String str16;
        int i13;
        int i14;
        String str17;
        User user2 = user;
        int i15 = i9;
        int i16 = (i15 & 1) != 0 ? user2.appleid : i;
        String str18 = (i15 & 2) != 0 ? user2.create_date : str;
        String str19 = (i15 & 4) != 0 ? user2.cuurmac : str2;
        String str20 = (i15 & 8) != 0 ? user2.email : str3;
        int i17 = (i15 & 16) != 0 ? user2.facebookid : i2;
        int i18 = (i15 & 32) != 0 ? user2.flag : i3;
        String str21 = (i15 & 64) != 0 ? user2.heightunit : str4;
        String str22 = (i15 & 128) != 0 ? user2.last_update_date : str5;
        String str23 = (i15 & 256) != 0 ? user2.u_avatar : str6;
        String str24 = (i15 & 512) != 0 ? user2.u_birthday : str7;
        int i19 = (i15 & 1024) != 0 ? user2.u_gender : i4;
        double d2 = (i15 & 2048) != 0 ? user2.u_goal : d;
        String str25 = (i15 & 4096) != 0 ? user2.u_height : str8;
        int i20 = (i15 & 8192) != 0 ? user2.u_id : i5;
        int i21 = (i15 & 16384) != 0 ? user2.u_language : i6;
        if ((i15 & 32768) != 0) {
            i10 = i21;
            i11 = user2.u_level;
        } else {
            i10 = i21;
            i11 = i7;
        }
        if ((i15 & 65536) != 0) {
            i12 = i11;
            str13 = user2.u_nickname;
        } else {
            i12 = i11;
            str13 = str9;
        }
        if ((i15 & 131072) != 0) {
            str14 = str13;
            str15 = user2.u_pwd;
        } else {
            str14 = str13;
            str15 = str10;
        }
        if ((i15 & 262144) != 0) {
            str16 = str15;
            i13 = user2.u_status;
        } else {
            str16 = str15;
            i13 = i8;
        }
        if ((i15 & 524288) != 0) {
            i14 = i13;
            str17 = user2.u_weight;
        } else {
            i14 = i13;
            str17 = str11;
        }
        return user.copy(i16, str18, str19, str20, i17, i18, str21, str22, str23, str24, i19, d2, str25, i20, i10, i12, str14, str16, i14, str17, (i15 & 1048576) != 0 ? user2.weightunit : str12);
    }

    public final int component1() {
        return this.appleid;
    }

    @NotNull
    public final String component10() {
        return this.u_birthday;
    }

    public final int component11() {
        return this.u_gender;
    }

    public final double component12() {
        return this.u_goal;
    }

    @NotNull
    public final String component13() {
        return this.u_height;
    }

    public final int component14() {
        return this.u_id;
    }

    public final int component15() {
        return this.u_language;
    }

    public final int component16() {
        return this.u_level;
    }

    @NotNull
    public final String component17() {
        return this.u_nickname;
    }

    @NotNull
    public final String component18() {
        return this.u_pwd;
    }

    public final int component19() {
        return this.u_status;
    }

    @NotNull
    public final String component2() {
        return this.create_date;
    }

    @NotNull
    public final String component20() {
        return this.u_weight;
    }

    @NotNull
    public final String component21() {
        return this.weightunit;
    }

    @NotNull
    public final String component3() {
        return this.cuurmac;
    }

    @NotNull
    public final String component4() {
        return this.email;
    }

    public final int component5() {
        return this.facebookid;
    }

    public final int component6() {
        return this.flag;
    }

    @NotNull
    public final String component7() {
        return this.heightunit;
    }

    @NotNull
    public final String component8() {
        return this.last_update_date;
    }

    @NotNull
    public final String component9() {
        return this.u_avatar;
    }

    @NotNull
    public final User copy(int i, @NotNull String str, @NotNull String str2, @NotNull String str3, int i2, int i3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, int i4, double d, @NotNull String str8, int i5, int i6, int i7, @NotNull String str9, @NotNull String str10, int i8, @NotNull String str11, @NotNull String str12) {
        int i9 = i;
        Intrinsics.checkParameterIsNotNull(str, "create_date");
        Intrinsics.checkParameterIsNotNull(str2, "cuurmac");
        Intrinsics.checkParameterIsNotNull(str3, "email");
        Intrinsics.checkParameterIsNotNull(str4, "heightunit");
        Intrinsics.checkParameterIsNotNull(str5, "last_update_date");
        Intrinsics.checkParameterIsNotNull(str6, "u_avatar");
        Intrinsics.checkParameterIsNotNull(str7, "u_birthday");
        Intrinsics.checkParameterIsNotNull(str8, "u_height");
        Intrinsics.checkParameterIsNotNull(str9, "u_nickname");
        Intrinsics.checkParameterIsNotNull(str10, "u_pwd");
        Intrinsics.checkParameterIsNotNull(str11, "u_weight");
        Intrinsics.checkParameterIsNotNull(str12, "weightunit");
        return new User(i, str, str2, str3, i2, i3, str4, str5, str6, str7, i4, d, str8, i5, i6, i7, str9, str10, i8, str11, str12);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return this.appleid == user.appleid && Intrinsics.areEqual((Object) this.create_date, (Object) user.create_date) && Intrinsics.areEqual((Object) this.cuurmac, (Object) user.cuurmac) && Intrinsics.areEqual((Object) this.email, (Object) user.email) && this.facebookid == user.facebookid && this.flag == user.flag && Intrinsics.areEqual((Object) this.heightunit, (Object) user.heightunit) && Intrinsics.areEqual((Object) this.last_update_date, (Object) user.last_update_date) && Intrinsics.areEqual((Object) this.u_avatar, (Object) user.u_avatar) && Intrinsics.areEqual((Object) this.u_birthday, (Object) user.u_birthday) && this.u_gender == user.u_gender && Double.compare(this.u_goal, user.u_goal) == 0 && Intrinsics.areEqual((Object) this.u_height, (Object) user.u_height) && this.u_id == user.u_id && this.u_language == user.u_language && this.u_level == user.u_level && Intrinsics.areEqual((Object) this.u_nickname, (Object) user.u_nickname) && Intrinsics.areEqual((Object) this.u_pwd, (Object) user.u_pwd) && this.u_status == user.u_status && Intrinsics.areEqual((Object) this.u_weight, (Object) user.u_weight) && Intrinsics.areEqual((Object) this.weightunit, (Object) user.weightunit);
    }

    public int hashCode() {
        int i = this.appleid * 31;
        String str = this.create_date;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.cuurmac;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.email;
        int hashCode3 = (((((hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.facebookid) * 31) + this.flag) * 31;
        String str4 = this.heightunit;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.last_update_date;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.u_avatar;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.u_birthday;
        int hashCode7 = str7 != null ? str7.hashCode() : 0;
        long doubleToLongBits = Double.doubleToLongBits(this.u_goal);
        int i3 = (((((hashCode6 + hashCode7) * 31) + this.u_gender) * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        String str8 = this.u_height;
        int hashCode8 = (((((((i3 + (str8 != null ? str8.hashCode() : 0)) * 31) + this.u_id) * 31) + this.u_language) * 31) + this.u_level) * 31;
        String str9 = this.u_nickname;
        int hashCode9 = (hashCode8 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.u_pwd;
        int hashCode10 = (((hashCode9 + (str10 != null ? str10.hashCode() : 0)) * 31) + this.u_status) * 31;
        String str11 = this.u_weight;
        int hashCode11 = (hashCode10 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.weightunit;
        if (str12 != null) {
            i2 = str12.hashCode();
        }
        return hashCode11 + i2;
    }

    @NotNull
    public String toString() {
        return "User(appleid=" + this.appleid + ", create_date=" + this.create_date + ", cuurmac=" + this.cuurmac + ", email=" + this.email + ", facebookid=" + this.facebookid + ", flag=" + this.flag + ", heightunit=" + this.heightunit + ", last_update_date=" + this.last_update_date + ", u_avatar=" + this.u_avatar + ", u_birthday=" + this.u_birthday + ", u_gender=" + this.u_gender + ", u_goal=" + this.u_goal + ", u_height=" + this.u_height + ", u_id=" + this.u_id + ", u_language=" + this.u_language + ", u_level=" + this.u_level + ", u_nickname=" + this.u_nickname + ", u_pwd=" + this.u_pwd + ", u_status=" + this.u_status + ", u_weight=" + this.u_weight + ", weightunit=" + this.weightunit + ")";
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeInt(this.appleid);
        parcel.writeString(this.create_date);
        parcel.writeString(this.cuurmac);
        parcel.writeString(this.email);
        parcel.writeInt(this.facebookid);
        parcel.writeInt(this.flag);
        parcel.writeString(this.heightunit);
        parcel.writeString(this.last_update_date);
        parcel.writeString(this.u_avatar);
        parcel.writeString(this.u_birthday);
        parcel.writeInt(this.u_gender);
        parcel.writeDouble(this.u_goal);
        parcel.writeString(this.u_height);
        parcel.writeInt(this.u_id);
        parcel.writeInt(this.u_language);
        parcel.writeInt(this.u_level);
        parcel.writeString(this.u_nickname);
        parcel.writeString(this.u_pwd);
        parcel.writeInt(this.u_status);
        parcel.writeString(this.u_weight);
        parcel.writeString(this.weightunit);
    }

    public User(int i, @NotNull String str, @NotNull String str2, @NotNull String str3, int i2, int i3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, int i4, double d, @NotNull String str8, int i5, int i6, int i7, @NotNull String str9, @NotNull String str10, int i8, @NotNull String str11, @NotNull String str12) {
        String str13 = str;
        String str14 = str2;
        String str15 = str3;
        String str16 = str4;
        String str17 = str5;
        String str18 = str6;
        String str19 = str7;
        String str20 = str8;
        String str21 = str9;
        String str22 = str10;
        String str23 = str11;
        String str24 = str12;
        Intrinsics.checkParameterIsNotNull(str13, "create_date");
        Intrinsics.checkParameterIsNotNull(str14, "cuurmac");
        Intrinsics.checkParameterIsNotNull(str15, "email");
        Intrinsics.checkParameterIsNotNull(str16, "heightunit");
        Intrinsics.checkParameterIsNotNull(str17, "last_update_date");
        Intrinsics.checkParameterIsNotNull(str18, "u_avatar");
        Intrinsics.checkParameterIsNotNull(str19, "u_birthday");
        Intrinsics.checkParameterIsNotNull(str20, "u_height");
        Intrinsics.checkParameterIsNotNull(str21, "u_nickname");
        Intrinsics.checkParameterIsNotNull(str22, "u_pwd");
        Intrinsics.checkParameterIsNotNull(str23, "u_weight");
        Intrinsics.checkParameterIsNotNull(str24, "weightunit");
        this.appleid = i;
        this.create_date = str13;
        this.cuurmac = str14;
        this.email = str15;
        this.facebookid = i2;
        this.flag = i3;
        this.heightunit = str16;
        this.last_update_date = str17;
        this.u_avatar = str18;
        this.u_birthday = str19;
        this.u_gender = i4;
        this.u_goal = d;
        this.u_height = str20;
        this.u_id = i5;
        this.u_language = i6;
        this.u_level = i7;
        this.u_nickname = str21;
        this.u_pwd = str22;
        this.u_status = i8;
        this.u_weight = str23;
        this.weightunit = str24;
    }

    public final int getAppleid() {
        return this.appleid;
    }

    public final void setAppleid(int i) {
        this.appleid = i;
    }

    @NotNull
    public final String getCreate_date() {
        return this.create_date;
    }

    public final void setCreate_date(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.create_date = str;
    }

    @NotNull
    public final String getCuurmac() {
        return this.cuurmac;
    }

    public final void setCuurmac(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.cuurmac = str;
    }

    @NotNull
    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.email = str;
    }

    public final int getFacebookid() {
        return this.facebookid;
    }

    public final void setFacebookid(int i) {
        this.facebookid = i;
    }

    public final int getFlag() {
        return this.flag;
    }

    public final void setFlag(int i) {
        this.flag = i;
    }

    @NotNull
    public final String getHeightunit() {
        return this.heightunit;
    }

    public final void setHeightunit(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.heightunit = str;
    }

    @NotNull
    public final String getLast_update_date() {
        return this.last_update_date;
    }

    public final void setLast_update_date(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.last_update_date = str;
    }

    @NotNull
    public final String getU_avatar() {
        return this.u_avatar;
    }

    public final void setU_avatar(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.u_avatar = str;
    }

    @NotNull
    public final String getU_birthday() {
        return this.u_birthday;
    }

    public final void setU_birthday(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.u_birthday = str;
    }

    public final int getU_gender() {
        return this.u_gender;
    }

    public final void setU_gender(int i) {
        this.u_gender = i;
    }

    public final double getU_goal() {
        return this.u_goal;
    }

    public final void setU_goal(double d) {
        this.u_goal = d;
    }

    @NotNull
    public final String getU_height() {
        return this.u_height;
    }

    public final void setU_height(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.u_height = str;
    }

    public final int getU_id() {
        return this.u_id;
    }

    public final void setU_id(int i) {
        this.u_id = i;
    }

    public final int getU_language() {
        return this.u_language;
    }

    public final void setU_language(int i) {
        this.u_language = i;
    }

    public final int getU_level() {
        return this.u_level;
    }

    public final void setU_level(int i) {
        this.u_level = i;
    }

    @NotNull
    public final String getU_nickname() {
        return this.u_nickname;
    }

    public final void setU_nickname(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.u_nickname = str;
    }

    @NotNull
    public final String getU_pwd() {
        return this.u_pwd;
    }

    public final void setU_pwd(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.u_pwd = str;
    }

    public final int getU_status() {
        return this.u_status;
    }

    public final void setU_status(int i) {
        this.u_status = i;
    }

    @NotNull
    public final String getU_weight() {
        return this.u_weight;
    }

    public final void setU_weight(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.u_weight = str;
    }

    @NotNull
    public final String getWeightunit() {
        return this.weightunit;
    }

    public final void setWeightunit(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.weightunit = str;
    }

    public User() {
        this(0, "", "", "", 0, 0, "cm", "", "", "", 1, Utils.DOUBLE_EPSILON, "", 0, 0, 0, "", "", 0, "", "kg");
    }
}
