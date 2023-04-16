package com.huayu.tzc.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import chipsea.bias.v235.CSBiasAPI;
import com.baidu.mobstat.Config;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import com.huayu.tzc.base.Constant;
import com.huayu.tzc.utils.ByteUtil;
import com.huayu.tzc.utils.RangeUtil;
import com.huayu.tzc.utils.UnitUtils;
import java.util.Arrays;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import kotlin.text.CharsKt;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.litepal.crud.LitePalSupport;

@Parcelize
@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000i\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0003\b\u0001\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u0014\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0007\b\u0016¢\u0006\u0002\u0010\u0003BÝ\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\r\u0012\u0006\u0010\u0014\u001a\u00020\r\u0012\u0006\u0010\u0015\u001a\u00020\r\u0012\u0006\u0010\u0016\u001a\u00020\r\u0012\u0006\u0010\u0017\u001a\u00020\b\u0012\u0006\u0010\u0018\u001a\u00020\b\u0012\u0006\u0010\u0019\u001a\u00020\r\u0012\u0006\u0010\u001a\u001a\u00020\b\u0012\u0006\u0010\u001b\u001a\u00020\b\u0012\u0006\u0010\u001c\u001a\u00020\u0005\u0012\u0006\u0010\u001d\u001a\u00020\b\u0012\u0006\u0010\u001e\u001a\u00020\b\u0012\u0006\u0010\u001f\u001a\u00020\r\u0012\u0006\u0010 \u001a\u00020\u0005\u0012\u0006\u0010!\u001a\u00020\u0005\u0012\u0006\u0010\"\u001a\u00020\r\u0012\u0006\u0010#\u001a\u00020$\u0012\u0006\u0010%\u001a\u00020\r\u0012\u0006\u0010&\u001a\u00020\r\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(\u0012\b\b\u0002\u0010)\u001a\u00020\u0012\u0012\b\b\u0002\u0010*\u001a\u00020\r\u0012\b\b\u0002\u0010+\u001a\u00020\r\u0012\b\b\u0002\u0010,\u001a\u00020\r\u0012\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.\u0012\b\b\u0002\u0010/\u001a\u00020$\u0012\b\b\u0002\u00100\u001a\u00020$\u0012\b\b\u0002\u00101\u001a\u00020$\u0012\b\b\u0002\u00102\u001a\u00020\u0012¢\u0006\u0002\u00103J\n\u0010\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0001\u001a\u00020\rHÆ\u0003J\n\u0010\u0001\u001a\u00020\u0012HÆ\u0003J\n\u0010\u0001\u001a\u00020\rHÆ\u0003J\n\u0010\u0001\u001a\u00020\rHÆ\u0003J\n\u0010\u0001\u001a\u00020\rHÆ\u0003J\n\u0010\u0001\u001a\u00020\rHÆ\u0003J\n\u0010\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0001\u001a\u00020\rHÆ\u0003J\n\u0010\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010\u0001\u001a\u00020\bHÆ\u0003J\n\u0010\u0001\u001a\u00020\bHÆ\u0003J\n\u0010 \u0001\u001a\u00020\rHÆ\u0003J\n\u0010¡\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010¢\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010£\u0001\u001a\u00020\rHÆ\u0003J\n\u0010¤\u0001\u001a\u00020$HÆ\u0003J\n\u0010¥\u0001\u001a\u00020\rHÆ\u0003J\n\u0010¦\u0001\u001a\u00020\bHÆ\u0003J\n\u0010§\u0001\u001a\u00020\rHÆ\u0003J\f\u0010¨\u0001\u001a\u0004\u0018\u00010(HÆ\u0003J\n\u0010©\u0001\u001a\u00020\u0012HÆ\u0003J\n\u0010ª\u0001\u001a\u00020\rHÆ\u0003J\n\u0010«\u0001\u001a\u00020\rHÆ\u0003J\n\u0010¬\u0001\u001a\u00020\rHÆ\u0003J\f\u0010­\u0001\u001a\u0004\u0018\u00010.HÆ\u0003J\n\u0010®\u0001\u001a\u00020$HÆ\u0003J\n\u0010¯\u0001\u001a\u00020$HÆ\u0003J\n\u0010°\u0001\u001a\u00020$HÆ\u0003J\n\u0010±\u0001\u001a\u00020\bHÆ\u0003J\n\u0010²\u0001\u001a\u00020\u0012HÆ\u0003J\n\u0010³\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010´\u0001\u001a\u00020\u0005HÆ\u0003J\n\u0010µ\u0001\u001a\u00020\rHÆ\u0003J\n\u0010¶\u0001\u001a\u00020\rHÆ\u0003J\n\u0010·\u0001\u001a\u00020\rHÆ\u0003J3\u0010¸\u0001\u001a\u00030¹\u00012\u0006\u0010\u0013\u001a\u00020$2\u0007\u0010º\u0001\u001a\u00020\b2\u0007\u0010»\u0001\u001a\u00020\b2\u0006\u0010%\u001a\u00020$2\u0007\u0010¼\u0001\u001a\u00020$J\u0010\u0010½\u0001\u001a\u00020$2\u0007\u0010¾\u0001\u001a\u00020.J\u0003\u0010¿\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\r2\b\b\u0002\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0015\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\r2\b\b\u0002\u0010\u0017\u001a\u00020\b2\b\b\u0002\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\r2\b\b\u0002\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001b\u001a\u00020\b2\b\b\u0002\u0010\u001c\u001a\u00020\u00052\b\b\u0002\u0010\u001d\u001a\u00020\b2\b\b\u0002\u0010\u001e\u001a\u00020\b2\b\b\u0002\u0010\u001f\u001a\u00020\r2\b\b\u0002\u0010 \u001a\u00020\u00052\b\b\u0002\u0010!\u001a\u00020\u00052\b\b\u0002\u0010\"\u001a\u00020\r2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\r2\b\b\u0002\u0010&\u001a\u00020\r2\n\b\u0002\u0010'\u001a\u0004\u0018\u00010(2\b\b\u0002\u0010)\u001a\u00020\u00122\b\b\u0002\u0010*\u001a\u00020\r2\b\b\u0002\u0010+\u001a\u00020\r2\b\b\u0002\u0010,\u001a\u00020\r2\n\b\u0002\u0010-\u001a\u0004\u0018\u00010.2\b\b\u0002\u0010/\u001a\u00020$2\b\b\u0002\u00100\u001a\u00020$2\b\b\u0002\u00101\u001a\u00020$2\b\b\u0002\u00102\u001a\u00020\u0012HÆ\u0001J\n\u0010À\u0001\u001a\u00020\bHÖ\u0001J\u0016\u0010Á\u0001\u001a\u00020\u00122\n\u0010Â\u0001\u001a\u0005\u0018\u00010Ã\u0001HÖ\u0003J\u0011\u0010Ä\u0001\u001a\u00020$2\u0006\u0010+\u001a\u00020\rH\u0002J\u001b\u0010Å\u0001\u001a\u00020\b2\u0007\u0010Æ\u0001\u001a\u00020\b2\u0007\u0010Ç\u0001\u001a\u00020\bH\u0002J\u001c\u0010È\u0001\u001a\u00020\b2\u0007\u0010É\u0001\u001a\u00020$2\b\u0010Ê\u0001\u001a\u00030Ë\u0001H\u0002J\u0007\u0010Ì\u0001\u001a\u00020(J/\u0010Í\u0001\u001a\u00020\b2\u0007\u0010Î\u0001\u001a\u00020$2\u0007\u0010É\u0001\u001a\u00020$2\b\u0010Ï\u0001\u001a\u00030Ë\u00012\b\u0010Ê\u0001\u001a\u00030Ë\u0001H\u0016J\u001c\u0010Ð\u0001\u001a\u00020\b2\u0007\u0010Î\u0001\u001a\u00020$2\b\u0010Ï\u0001\u001a\u00030Ë\u0001H\u0002J\u0007\u0010Ñ\u0001\u001a\u00020.J\n\u0010Ò\u0001\u001a\u00020\bHÖ\u0001J\n\u0010Ó\u0001\u001a\u00020\rHÖ\u0001J\u001e\u0010Ô\u0001\u001a\u00030¹\u00012\b\u0010Õ\u0001\u001a\u00030Ö\u00012\u0007\u0010×\u0001\u001a\u00020\bHÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001a\u0010\u0006\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00105\"\u0004\b9\u00107R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010\t\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010;\"\u0004\b?\u0010=R\u001a\u0010\n\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u00105\"\u0004\bA\u00107R\u001a\u0010\u000b\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u00105\"\u0004\bC\u00107R\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR\u001a\u0010\u000e\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010E\"\u0004\bI\u0010GR\u001c\u0010'\u001a\u0004\u0018\u00010(X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR\u001a\u0010,\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010E\"\u0004\bO\u0010GR\u001a\u0010\u000f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010E\"\u0004\bQ\u0010GR\u001a\u0010\u0010\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u0010E\"\u0004\bS\u0010GR\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u001a\u0010\u0013\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010E\"\u0004\bY\u0010GR\u001a\u0010\u0014\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010E\"\u0004\b[\u0010GR\u001a\u0010\u0015\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u0010E\"\u0004\b]\u0010GR\u001a\u0010)\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010U\"\u0004\b^\u0010WR\u001a\u0010\u0016\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010E\"\u0004\b_\u0010GR\u001a\u0010/\u001a\u00020$X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR\u001a\u00100\u001a\u00020$X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010a\"\u0004\be\u0010cR\u001a\u0010*\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010E\"\u0004\bg\u0010GR\u001a\u0010\u0017\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bh\u0010;\"\u0004\bi\u0010=R\u001a\u0010\u0018\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010;\"\u0004\bk\u0010=R\u001a\u0010+\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010E\"\u0004\bm\u0010GR\u001a\u0010\u0019\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010E\"\u0004\bo\u0010GR\u001a\u0010\u001a\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010;\"\u0004\bq\u0010=R\u001a\u00102\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010U\"\u0004\bs\u0010WR\u001a\u0010\u001b\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bt\u0010;\"\u0004\bu\u0010=R\u001a\u0010\u001c\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bv\u00105\"\u0004\bw\u00107R\u001a\u0010\u001d\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bx\u0010;\"\u0004\by\u0010=R\u001a\u0010\u001e\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bz\u0010;\"\u0004\b{\u0010=R\u001c\u0010-\u001a\u0004\u0018\u00010.X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b|\u0010}\"\u0004\b~\u0010R\u001c\u0010\u001f\u001a\u00020\rX\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u0010E\"\u0005\b\u0001\u0010GR\u001c\u0010 \u001a\u00020\u0005X\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u00105\"\u0005\b\u0001\u00107R\u001c\u00101\u001a\u00020$X\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u0010a\"\u0005\b\u0001\u0010cR\u001c\u0010!\u001a\u00020\u0005X\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u00105\"\u0005\b\u0001\u00107R\u001c\u0010\"\u001a\u00020\rX\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u0010E\"\u0005\b\u0001\u0010GR\u001c\u0010#\u001a\u00020$X\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u0010a\"\u0005\b\u0001\u0010cR\u001c\u0010%\u001a\u00020\rX\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u0010E\"\u0005\b\u0001\u0010GR\u001c\u0010&\u001a\u00020\rX\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0001\u0010E\"\u0005\b\u0001\u0010G¨\u0006Ø\u0001"}, mo21895d2 = {"Lcom/huayu/tzc/bean/Measure;", "Landroid/os/Parcelable;", "Lorg/litepal/crud/LitePalSupport;", "()V", "basalmetabolic_rate", "", "bmi", "body_score", "", "bodyage", "bodyfat_rate", "bodywater_rate", "bone_mass", "", "create_date", "devmac", "ffm", "flag", "", "height", "heightunit", "ibw", "is_mainmember", "measuredate", "member_id", "muscle_mass", "obesity_grade", "physical_age", "protein_rate", "recordguid", "recordid", "shape", "skeletalfat_percentage", "subcutaneousfat_rate", "user_name", "visceral_fat", "", "weight", "weightunit", "date", "Ljava/util/Date;", "isConnect", "macData", "msgType", "devName", "scanRecord", "", "kgWeight", "lbWeight", "stWeight", "onlyWeight", "(DDIIDDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILjava/lang/String;IIDIILjava/lang/String;DDLjava/lang/String;FLjava/lang/String;Ljava/lang/String;Ljava/util/Date;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;[BFFFZ)V", "getBasalmetabolic_rate", "()D", "setBasalmetabolic_rate", "(D)V", "getBmi", "setBmi", "getBody_score", "()I", "setBody_score", "(I)V", "getBodyage", "setBodyage", "getBodyfat_rate", "setBodyfat_rate", "getBodywater_rate", "setBodywater_rate", "getBone_mass", "()Ljava/lang/String;", "setBone_mass", "(Ljava/lang/String;)V", "getCreate_date", "setCreate_date", "getDate", "()Ljava/util/Date;", "setDate", "(Ljava/util/Date;)V", "getDevName", "setDevName", "getDevmac", "setDevmac", "getFfm", "setFfm", "getFlag", "()Z", "setFlag", "(Z)V", "getHeight", "setHeight", "getHeightunit", "setHeightunit", "getIbw", "setIbw", "setConnect", "set_mainmember", "getKgWeight", "()F", "setKgWeight", "(F)V", "getLbWeight", "setLbWeight", "getMacData", "setMacData", "getMeasuredate", "setMeasuredate", "getMember_id", "setMember_id", "getMsgType", "setMsgType", "getMuscle_mass", "setMuscle_mass", "getObesity_grade", "setObesity_grade", "getOnlyWeight", "setOnlyWeight", "getPhysical_age", "setPhysical_age", "getProtein_rate", "setProtein_rate", "getRecordguid", "setRecordguid", "getRecordid", "setRecordid", "getScanRecord", "()[B", "setScanRecord", "([B)V", "getShape", "setShape", "getSkeletalfat_percentage", "setSkeletalfat_percentage", "getStWeight", "setStWeight", "getSubcutaneousfat_rate", "setSubcutaneousfat_rate", "getUser_name", "setUser_name", "getVisceral_fat", "setVisceral_fat", "getWeight", "setWeight", "getWeightunit", "setWeightunit", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component23", "component24", "component25", "component26", "component27", "component28", "component29", "component3", "component30", "component31", "component32", "component33", "component34", "component35", "component36", "component37", "component38", "component39", "component4", "component40", "component5", "component6", "component7", "component8", "component9", "computeTz", "", "sex", "age", "dz", "computeWeight", "bytes", "copy", "describeContents", "equals", "other", "", "getDecimal", "getFalseAge", "measuringAge", "userAge", "getJrLevel", "jr", "jrRang", "", "getMeasureDate", "getTx", "tz", "fatRang", "getTzLevel", "getWeightData", "hashCode", "toString", "writeToParcel", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: Measure.kt */
public final class Measure extends LitePalSupport implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private double basalmetabolic_rate;
    private double bmi;
    private int body_score;
    private int bodyage;
    private double bodyfat_rate;
    private double bodywater_rate;
    @NotNull
    private String bone_mass;
    @NotNull
    private String create_date;
    @Nullable
    private Date date;
    @NotNull
    private String devName;
    @NotNull
    private String devmac;
    @NotNull
    private String ffm;
    private boolean flag;
    @NotNull
    private String height;
    @NotNull
    private String heightunit;
    @NotNull
    private String ibw;
    private boolean isConnect;
    @NotNull
    private String is_mainmember;
    private float kgWeight;
    private float lbWeight;
    @NotNull
    private String macData;
    private int measuredate;
    private int member_id;
    @NotNull
    private String msgType;
    @NotNull
    private String muscle_mass;
    private int obesity_grade;
    private boolean onlyWeight;
    private int physical_age;
    private double protein_rate;
    private int recordguid;
    private int recordid;
    @Nullable
    private byte[] scanRecord;
    @NotNull
    private String shape;
    private double skeletalfat_percentage;
    private float stWeight;
    private double subcutaneousfat_rate;
    @NotNull
    private String user_name;
    private float visceral_fat;
    @NotNull
    private String weight;
    @NotNull
    private String weightunit;

    @Metadata(mo21893bv = {1, 0, 3}, mo21896k = 3, mo21897mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        @NotNull
        public final Object createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new Measure(parcel.readDouble(), parcel.readDouble(), parcel.readInt(), parcel.readInt(), parcel.readDouble(), parcel.readDouble(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readDouble(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readDouble(), parcel.readDouble(), parcel.readString(), parcel.readFloat(), parcel.readString(), parcel.readString(), (Date) parcel.readSerializable(), parcel.readInt() != 0, parcel.readString(), parcel.readString(), parcel.readString(), parcel.createByteArray(), parcel.readFloat(), parcel.readFloat(), parcel.readFloat(), parcel.readInt() != 0);
        }

        @NotNull
        public final Object[] newArray(int i) {
            return new Measure[i];
        }
    }

    public static /* synthetic */ Measure copy$default(Measure measure, double d, double d2, int i, int i2, double d3, double d4, String str, String str2, String str3, String str4, boolean z, String str5, String str6, String str7, String str8, int i3, int i4, String str9, int i5, int i6, double d5, int i7, int i8, String str10, double d6, double d7, String str11, float f, String str12, String str13, Date date2, boolean z2, String str14, String str15, String str16, byte[] bArr, float f2, float f3, float f4, boolean z3, int i9, int i10, Object obj) {
        String str17;
        int i11;
        int i12;
        int i13;
        int i14;
        String str18;
        String str19;
        int i15;
        int i16;
        int i17;
        int i18;
        String str20;
        double d8;
        double d9;
        int i19;
        int i20;
        String str21;
        String str22;
        int i21;
        double d10;
        double d11;
        double d12;
        double d13;
        String str23;
        float f5;
        String str24;
        String str25;
        String str26;
        String str27;
        Date date3;
        boolean z4;
        String str28;
        String str29;
        String str30;
        String str31;
        String str32;
        String str33;
        byte[] bArr2;
        byte[] bArr3;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        Measure measure2 = measure;
        int i22 = i9;
        int i23 = i10;
        double d14 = (i22 & 1) != 0 ? measure2.basalmetabolic_rate : d;
        double d15 = (i22 & 2) != 0 ? measure2.bmi : d2;
        int i24 = (i22 & 4) != 0 ? measure2.body_score : i;
        int i25 = (i22 & 8) != 0 ? measure2.bodyage : i2;
        double d16 = (i22 & 16) != 0 ? measure2.bodyfat_rate : d3;
        double d17 = (i22 & 32) != 0 ? measure2.bodywater_rate : d4;
        String str34 = (i22 & 64) != 0 ? measure2.bone_mass : str;
        String str35 = (i22 & 128) != 0 ? measure2.create_date : str2;
        String str36 = (i22 & 256) != 0 ? measure2.devmac : str3;
        String str37 = (i22 & 512) != 0 ? measure2.ffm : str4;
        boolean z5 = (i22 & 1024) != 0 ? measure2.flag : z;
        String str38 = (i22 & 2048) != 0 ? measure2.height : str5;
        String str39 = (i22 & 4096) != 0 ? measure2.heightunit : str6;
        String str40 = (i22 & 8192) != 0 ? measure2.ibw : str7;
        String str41 = (i22 & 16384) != 0 ? measure2.is_mainmember : str8;
        if ((i22 & 32768) != 0) {
            str17 = str41;
            i11 = measure2.measuredate;
        } else {
            str17 = str41;
            i11 = i3;
        }
        if ((i22 & 65536) != 0) {
            i12 = i11;
            i13 = measure2.member_id;
        } else {
            i12 = i11;
            i13 = i4;
        }
        if ((i22 & 131072) != 0) {
            i14 = i13;
            str18 = measure2.muscle_mass;
        } else {
            i14 = i13;
            str18 = str9;
        }
        if ((i22 & 262144) != 0) {
            str19 = str18;
            i15 = measure2.obesity_grade;
        } else {
            str19 = str18;
            i15 = i5;
        }
        if ((i22 & 524288) != 0) {
            i16 = i15;
            i17 = measure2.physical_age;
        } else {
            i16 = i15;
            i17 = i6;
        }
        if ((i22 & 1048576) != 0) {
            str20 = str35;
            i18 = i17;
            d8 = measure2.protein_rate;
        } else {
            str20 = str35;
            i18 = i17;
            d8 = d5;
        }
        if ((i22 & 2097152) != 0) {
            d9 = d8;
            i19 = measure2.recordguid;
        } else {
            d9 = d8;
            i19 = i7;
        }
        int i26 = (4194304 & i22) != 0 ? measure2.recordid : i8;
        if ((i22 & 8388608) != 0) {
            i20 = i26;
            str21 = measure2.shape;
        } else {
            i20 = i26;
            str21 = str10;
        }
        if ((i22 & 16777216) != 0) {
            i21 = i19;
            str22 = str21;
            d10 = measure2.skeletalfat_percentage;
        } else {
            i21 = i19;
            str22 = str21;
            d10 = d6;
        }
        if ((i22 & 33554432) != 0) {
            d11 = d10;
            d12 = measure2.subcutaneousfat_rate;
        } else {
            d11 = d10;
            d12 = d7;
        }
        if ((i22 & 67108864) != 0) {
            d13 = d12;
            str23 = measure2.user_name;
        } else {
            d13 = d12;
            str23 = str11;
        }
        float f11 = (134217728 & i22) != 0 ? measure2.visceral_fat : f;
        if ((i22 & 268435456) != 0) {
            f5 = f11;
            str24 = measure2.weight;
        } else {
            f5 = f11;
            str24 = str12;
        }
        if ((i22 & 536870912) != 0) {
            str25 = str24;
            str26 = measure2.weightunit;
        } else {
            str25 = str24;
            str26 = str13;
        }
        if ((i22 & 1073741824) != 0) {
            str27 = str26;
            date3 = measure2.date;
        } else {
            str27 = str26;
            date3 = date2;
        }
        boolean z6 = (i22 & Integer.MIN_VALUE) != 0 ? measure2.isConnect : z2;
        if ((i23 & 1) != 0) {
            z4 = z6;
            str28 = measure2.macData;
        } else {
            z4 = z6;
            str28 = str14;
        }
        if ((i23 & 2) != 0) {
            str29 = str28;
            str30 = measure2.msgType;
        } else {
            str29 = str28;
            str30 = str15;
        }
        if ((i23 & 4) != 0) {
            str31 = str30;
            str32 = measure2.devName;
        } else {
            str31 = str30;
            str32 = str16;
        }
        if ((i23 & 8) != 0) {
            str33 = str32;
            bArr2 = measure2.scanRecord;
        } else {
            str33 = str32;
            bArr2 = bArr;
        }
        if ((i23 & 16) != 0) {
            bArr3 = bArr2;
            f6 = measure2.kgWeight;
        } else {
            bArr3 = bArr2;
            f6 = f2;
        }
        if ((i23 & 32) != 0) {
            f7 = f6;
            f8 = measure2.lbWeight;
        } else {
            f7 = f6;
            f8 = f3;
        }
        if ((i23 & 64) != 0) {
            f9 = f8;
            f10 = measure2.stWeight;
        } else {
            f9 = f8;
            f10 = f4;
        }
        return measure.copy(d14, d15, i24, i25, d16, d17, str34, str20, str36, str37, z5, str38, str39, str40, str17, i12, i14, str19, i16, i18, d9, i21, i20, str22, d11, d13, str23, f5, str25, str27, date3, z4, str29, str31, str33, bArr3, f7, f9, f10, (i23 & 128) != 0 ? measure2.onlyWeight : z3);
    }

    private final int getFalseAge(int i, int i2) {
        int i3;
        if (i == i2) {
            return i2;
        }
        if (i > i2) {
            int i4 = i - i2;
            i3 = i4 < 10 ? i2 + 1 : i4 < 20 ? i2 + 2 : i2 + 3;
        } else {
            i3 = i2 - i < -10 ? i2 - 1 : i - i2 < -20 ? i2 - 2 : i2 - 3;
        }
        if (i3 < 1) {
            return 1;
        }
        return i3;
    }

    public final double component1() {
        return this.basalmetabolic_rate;
    }

    @NotNull
    public final String component10() {
        return this.ffm;
    }

    public final boolean component11() {
        return this.flag;
    }

    @NotNull
    public final String component12() {
        return this.height;
    }

    @NotNull
    public final String component13() {
        return this.heightunit;
    }

    @NotNull
    public final String component14() {
        return this.ibw;
    }

    @NotNull
    public final String component15() {
        return this.is_mainmember;
    }

    public final int component16() {
        return this.measuredate;
    }

    public final int component17() {
        return this.member_id;
    }

    @NotNull
    public final String component18() {
        return this.muscle_mass;
    }

    public final int component19() {
        return this.obesity_grade;
    }

    public final double component2() {
        return this.bmi;
    }

    public final int component20() {
        return this.physical_age;
    }

    public final double component21() {
        return this.protein_rate;
    }

    public final int component22() {
        return this.recordguid;
    }

    public final int component23() {
        return this.recordid;
    }

    @NotNull
    public final String component24() {
        return this.shape;
    }

    public final double component25() {
        return this.skeletalfat_percentage;
    }

    public final double component26() {
        return this.subcutaneousfat_rate;
    }

    @NotNull
    public final String component27() {
        return this.user_name;
    }

    public final float component28() {
        return this.visceral_fat;
    }

    @NotNull
    public final String component29() {
        return this.weight;
    }

    public final int component3() {
        return this.body_score;
    }

    @NotNull
    public final String component30() {
        return this.weightunit;
    }

    @Nullable
    public final Date component31() {
        return this.date;
    }

    public final boolean component32() {
        return this.isConnect;
    }

    @NotNull
    public final String component33() {
        return this.macData;
    }

    @NotNull
    public final String component34() {
        return this.msgType;
    }

    @NotNull
    public final String component35() {
        return this.devName;
    }

    @Nullable
    public final byte[] component36() {
        return this.scanRecord;
    }

    public final float component37() {
        return this.kgWeight;
    }

    public final float component38() {
        return this.lbWeight;
    }

    public final float component39() {
        return this.stWeight;
    }

    public final int component4() {
        return this.bodyage;
    }

    public final boolean component40() {
        return this.onlyWeight;
    }

    public final double component5() {
        return this.bodyfat_rate;
    }

    public final double component6() {
        return this.bodywater_rate;
    }

    @NotNull
    public final String component7() {
        return this.bone_mass;
    }

    @NotNull
    public final String component8() {
        return this.create_date;
    }

    @NotNull
    public final String component9() {
        return this.devmac;
    }

    @NotNull
    public final Measure copy(double d, double d2, int i, int i2, double d3, double d4, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, boolean z, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8, int i3, int i4, @NotNull String str9, int i5, int i6, double d5, int i7, int i8, @NotNull String str10, double d6, double d7, @NotNull String str11, float f, @NotNull String str12, @NotNull String str13, @Nullable Date date2, boolean z2, @NotNull String str14, @NotNull String str15, @NotNull String str16, @Nullable byte[] bArr, float f2, float f3, float f4, boolean z3) {
        double d8 = d;
        Intrinsics.checkParameterIsNotNull(str, "bone_mass");
        Intrinsics.checkParameterIsNotNull(str2, "create_date");
        Intrinsics.checkParameterIsNotNull(str3, "devmac");
        Intrinsics.checkParameterIsNotNull(str4, "ffm");
        Intrinsics.checkParameterIsNotNull(str5, ViewHierarchyConstants.DIMENSION_HEIGHT_KEY);
        Intrinsics.checkParameterIsNotNull(str6, "heightunit");
        Intrinsics.checkParameterIsNotNull(str7, "ibw");
        Intrinsics.checkParameterIsNotNull(str8, "is_mainmember");
        Intrinsics.checkParameterIsNotNull(str9, "muscle_mass");
        Intrinsics.checkParameterIsNotNull(str10, "shape");
        Intrinsics.checkParameterIsNotNull(str11, "user_name");
        Intrinsics.checkParameterIsNotNull(str12, "weight");
        Intrinsics.checkParameterIsNotNull(str13, "weightunit");
        Intrinsics.checkParameterIsNotNull(str14, "macData");
        Intrinsics.checkParameterIsNotNull(str15, "msgType");
        Intrinsics.checkParameterIsNotNull(str16, "devName");
        return new Measure(d, d2, i, i2, d3, d4, str, str2, str3, str4, z, str5, str6, str7, str8, i3, i4, str9, i5, i6, d5, i7, i8, str10, d6, d7, str11, f, str12, str13, date2, z2, str14, str15, str16, bArr, f2, f3, f4, z3);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Measure)) {
            return false;
        }
        Measure measure = (Measure) obj;
        return Double.compare(this.basalmetabolic_rate, measure.basalmetabolic_rate) == 0 && Double.compare(this.bmi, measure.bmi) == 0 && this.body_score == measure.body_score && this.bodyage == measure.bodyage && Double.compare(this.bodyfat_rate, measure.bodyfat_rate) == 0 && Double.compare(this.bodywater_rate, measure.bodywater_rate) == 0 && Intrinsics.areEqual((Object) this.bone_mass, (Object) measure.bone_mass) && Intrinsics.areEqual((Object) this.create_date, (Object) measure.create_date) && Intrinsics.areEqual((Object) this.devmac, (Object) measure.devmac) && Intrinsics.areEqual((Object) this.ffm, (Object) measure.ffm) && this.flag == measure.flag && Intrinsics.areEqual((Object) this.height, (Object) measure.height) && Intrinsics.areEqual((Object) this.heightunit, (Object) measure.heightunit) && Intrinsics.areEqual((Object) this.ibw, (Object) measure.ibw) && Intrinsics.areEqual((Object) this.is_mainmember, (Object) measure.is_mainmember) && this.measuredate == measure.measuredate && this.member_id == measure.member_id && Intrinsics.areEqual((Object) this.muscle_mass, (Object) measure.muscle_mass) && this.obesity_grade == measure.obesity_grade && this.physical_age == measure.physical_age && Double.compare(this.protein_rate, measure.protein_rate) == 0 && this.recordguid == measure.recordguid && this.recordid == measure.recordid && Intrinsics.areEqual((Object) this.shape, (Object) measure.shape) && Double.compare(this.skeletalfat_percentage, measure.skeletalfat_percentage) == 0 && Double.compare(this.subcutaneousfat_rate, measure.subcutaneousfat_rate) == 0 && Intrinsics.areEqual((Object) this.user_name, (Object) measure.user_name) && Float.compare(this.visceral_fat, measure.visceral_fat) == 0 && Intrinsics.areEqual((Object) this.weight, (Object) measure.weight) && Intrinsics.areEqual((Object) this.weightunit, (Object) measure.weightunit) && Intrinsics.areEqual((Object) this.date, (Object) measure.date) && this.isConnect == measure.isConnect && Intrinsics.areEqual((Object) this.macData, (Object) measure.macData) && Intrinsics.areEqual((Object) this.msgType, (Object) measure.msgType) && Intrinsics.areEqual((Object) this.devName, (Object) measure.devName) && Intrinsics.areEqual((Object) this.scanRecord, (Object) measure.scanRecord) && Float.compare(this.kgWeight, measure.kgWeight) == 0 && Float.compare(this.lbWeight, measure.lbWeight) == 0 && Float.compare(this.stWeight, measure.stWeight) == 0 && this.onlyWeight == measure.onlyWeight;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.basalmetabolic_rate);
        long doubleToLongBits2 = Double.doubleToLongBits(this.bmi);
        long doubleToLongBits3 = Double.doubleToLongBits(this.bodyfat_rate);
        long doubleToLongBits4 = Double.doubleToLongBits(this.bodywater_rate);
        int i = ((((((((((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + this.body_score) * 31) + this.bodyage) * 31) + ((int) (doubleToLongBits3 ^ (doubleToLongBits3 >>> 32)))) * 31) + ((int) (doubleToLongBits4 ^ (doubleToLongBits4 >>> 32)))) * 31;
        String str = this.bone_mass;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.create_date;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.devmac;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.ffm;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        boolean z = this.flag;
        if (z) {
            z = true;
        }
        int i3 = (hashCode4 + (z ? 1 : 0)) * 31;
        String str5 = this.height;
        int hashCode5 = (i3 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.heightunit;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.ibw;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.is_mainmember;
        int hashCode8 = (((((hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31) + this.measuredate) * 31) + this.member_id) * 31;
        String str9 = this.muscle_mass;
        int hashCode9 = str9 != null ? str9.hashCode() : 0;
        long doubleToLongBits5 = Double.doubleToLongBits(this.protein_rate);
        int i4 = (((((((((((hashCode8 + hashCode9) * 31) + this.obesity_grade) * 31) + this.physical_age) * 31) + ((int) (doubleToLongBits5 ^ (doubleToLongBits5 >>> 32)))) * 31) + this.recordguid) * 31) + this.recordid) * 31;
        String str10 = this.shape;
        int hashCode10 = str10 != null ? str10.hashCode() : 0;
        long doubleToLongBits6 = Double.doubleToLongBits(this.skeletalfat_percentage);
        long doubleToLongBits7 = Double.doubleToLongBits(this.subcutaneousfat_rate);
        int i5 = (((((i4 + hashCode10) * 31) + ((int) (doubleToLongBits6 ^ (doubleToLongBits6 >>> 32)))) * 31) + ((int) (doubleToLongBits7 ^ (doubleToLongBits7 >>> 32)))) * 31;
        String str11 = this.user_name;
        int hashCode11 = (((i5 + (str11 != null ? str11.hashCode() : 0)) * 31) + Float.floatToIntBits(this.visceral_fat)) * 31;
        String str12 = this.weight;
        int hashCode12 = (hashCode11 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.weightunit;
        int hashCode13 = (hashCode12 + (str13 != null ? str13.hashCode() : 0)) * 31;
        Date date2 = this.date;
        int hashCode14 = (hashCode13 + (date2 != null ? date2.hashCode() : 0)) * 31;
        boolean z2 = this.isConnect;
        if (z2) {
            z2 = true;
        }
        int i6 = (hashCode14 + (z2 ? 1 : 0)) * 31;
        String str14 = this.macData;
        int hashCode15 = (i6 + (str14 != null ? str14.hashCode() : 0)) * 31;
        String str15 = this.msgType;
        int hashCode16 = (hashCode15 + (str15 != null ? str15.hashCode() : 0)) * 31;
        String str16 = this.devName;
        int hashCode17 = (hashCode16 + (str16 != null ? str16.hashCode() : 0)) * 31;
        byte[] bArr = this.scanRecord;
        if (bArr != null) {
            i2 = Arrays.hashCode(bArr);
        }
        int floatToIntBits = (((((((hashCode17 + i2) * 31) + Float.floatToIntBits(this.kgWeight)) * 31) + Float.floatToIntBits(this.lbWeight)) * 31) + Float.floatToIntBits(this.stWeight)) * 31;
        boolean z3 = this.onlyWeight;
        if (z3) {
            z3 = true;
        }
        return floatToIntBits + (z3 ? 1 : 0);
    }

    @NotNull
    public String toString() {
        return "Measure(basalmetabolic_rate=" + this.basalmetabolic_rate + ", bmi=" + this.bmi + ", body_score=" + this.body_score + ", bodyage=" + this.bodyage + ", bodyfat_rate=" + this.bodyfat_rate + ", bodywater_rate=" + this.bodywater_rate + ", bone_mass=" + this.bone_mass + ", create_date=" + this.create_date + ", devmac=" + this.devmac + ", ffm=" + this.ffm + ", flag=" + this.flag + ", height=" + this.height + ", heightunit=" + this.heightunit + ", ibw=" + this.ibw + ", is_mainmember=" + this.is_mainmember + ", measuredate=" + this.measuredate + ", member_id=" + this.member_id + ", muscle_mass=" + this.muscle_mass + ", obesity_grade=" + this.obesity_grade + ", physical_age=" + this.physical_age + ", protein_rate=" + this.protein_rate + ", recordguid=" + this.recordguid + ", recordid=" + this.recordid + ", shape=" + this.shape + ", skeletalfat_percentage=" + this.skeletalfat_percentage + ", subcutaneousfat_rate=" + this.subcutaneousfat_rate + ", user_name=" + this.user_name + ", visceral_fat=" + this.visceral_fat + ", weight=" + this.weight + ", weightunit=" + this.weightunit + ", date=" + this.date + ", isConnect=" + this.isConnect + ", macData=" + this.macData + ", msgType=" + this.msgType + ", devName=" + this.devName + ", scanRecord=" + Arrays.toString(this.scanRecord) + ", kgWeight=" + this.kgWeight + ", lbWeight=" + this.lbWeight + ", stWeight=" + this.stWeight + ", onlyWeight=" + this.onlyWeight + ")";
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeDouble(this.basalmetabolic_rate);
        parcel.writeDouble(this.bmi);
        parcel.writeInt(this.body_score);
        parcel.writeInt(this.bodyage);
        parcel.writeDouble(this.bodyfat_rate);
        parcel.writeDouble(this.bodywater_rate);
        parcel.writeString(this.bone_mass);
        parcel.writeString(this.create_date);
        parcel.writeString(this.devmac);
        parcel.writeString(this.ffm);
        parcel.writeInt(this.flag ? 1 : 0);
        parcel.writeString(this.height);
        parcel.writeString(this.heightunit);
        parcel.writeString(this.ibw);
        parcel.writeString(this.is_mainmember);
        parcel.writeInt(this.measuredate);
        parcel.writeInt(this.member_id);
        parcel.writeString(this.muscle_mass);
        parcel.writeInt(this.obesity_grade);
        parcel.writeInt(this.physical_age);
        parcel.writeDouble(this.protein_rate);
        parcel.writeInt(this.recordguid);
        parcel.writeInt(this.recordid);
        parcel.writeString(this.shape);
        parcel.writeDouble(this.skeletalfat_percentage);
        parcel.writeDouble(this.subcutaneousfat_rate);
        parcel.writeString(this.user_name);
        parcel.writeFloat(this.visceral_fat);
        parcel.writeString(this.weight);
        parcel.writeString(this.weightunit);
        parcel.writeSerializable(this.date);
        parcel.writeInt(this.isConnect ? 1 : 0);
        parcel.writeString(this.macData);
        parcel.writeString(this.msgType);
        parcel.writeString(this.devName);
        parcel.writeByteArray(this.scanRecord);
        parcel.writeFloat(this.kgWeight);
        parcel.writeFloat(this.lbWeight);
        parcel.writeFloat(this.stWeight);
        parcel.writeInt(this.onlyWeight ? 1 : 0);
    }

    public final double getBasalmetabolic_rate() {
        return this.basalmetabolic_rate;
    }

    public final void setBasalmetabolic_rate(double d) {
        this.basalmetabolic_rate = d;
    }

    public final double getBmi() {
        return this.bmi;
    }

    public final void setBmi(double d) {
        this.bmi = d;
    }

    public final int getBody_score() {
        return this.body_score;
    }

    public final void setBody_score(int i) {
        this.body_score = i;
    }

    public final int getBodyage() {
        return this.bodyage;
    }

    public final void setBodyage(int i) {
        this.bodyage = i;
    }

    public final double getBodyfat_rate() {
        return this.bodyfat_rate;
    }

    public final void setBodyfat_rate(double d) {
        this.bodyfat_rate = d;
    }

    public final double getBodywater_rate() {
        return this.bodywater_rate;
    }

    public final void setBodywater_rate(double d) {
        this.bodywater_rate = d;
    }

    @NotNull
    public final String getBone_mass() {
        return this.bone_mass;
    }

    public final void setBone_mass(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.bone_mass = str;
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
    public final String getDevmac() {
        return this.devmac;
    }

    public final void setDevmac(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.devmac = str;
    }

    @NotNull
    public final String getFfm() {
        return this.ffm;
    }

    public final void setFfm(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.ffm = str;
    }

    public final boolean getFlag() {
        return this.flag;
    }

    public final void setFlag(boolean z) {
        this.flag = z;
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
    public final String getHeightunit() {
        return this.heightunit;
    }

    public final void setHeightunit(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.heightunit = str;
    }

    @NotNull
    public final String getIbw() {
        return this.ibw;
    }

    public final void setIbw(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.ibw = str;
    }

    @NotNull
    public final String is_mainmember() {
        return this.is_mainmember;
    }

    public final void set_mainmember(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.is_mainmember = str;
    }

    public final int getMeasuredate() {
        return this.measuredate;
    }

    public final void setMeasuredate(int i) {
        this.measuredate = i;
    }

    public final int getMember_id() {
        return this.member_id;
    }

    public final void setMember_id(int i) {
        this.member_id = i;
    }

    @NotNull
    public final String getMuscle_mass() {
        return this.muscle_mass;
    }

    public final void setMuscle_mass(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.muscle_mass = str;
    }

    public final int getObesity_grade() {
        return this.obesity_grade;
    }

    public final void setObesity_grade(int i) {
        this.obesity_grade = i;
    }

    public final int getPhysical_age() {
        return this.physical_age;
    }

    public final void setPhysical_age(int i) {
        this.physical_age = i;
    }

    public final double getProtein_rate() {
        return this.protein_rate;
    }

    public final void setProtein_rate(double d) {
        this.protein_rate = d;
    }

    public final int getRecordguid() {
        return this.recordguid;
    }

    public final void setRecordguid(int i) {
        this.recordguid = i;
    }

    public final int getRecordid() {
        return this.recordid;
    }

    public final void setRecordid(int i) {
        this.recordid = i;
    }

    @NotNull
    public final String getShape() {
        return this.shape;
    }

    public final void setShape(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.shape = str;
    }

    public final double getSkeletalfat_percentage() {
        return this.skeletalfat_percentage;
    }

    public final void setSkeletalfat_percentage(double d) {
        this.skeletalfat_percentage = d;
    }

    public final double getSubcutaneousfat_rate() {
        return this.subcutaneousfat_rate;
    }

    public final void setSubcutaneousfat_rate(double d) {
        this.subcutaneousfat_rate = d;
    }

    @NotNull
    public final String getUser_name() {
        return this.user_name;
    }

    public final void setUser_name(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.user_name = str;
    }

    public final float getVisceral_fat() {
        return this.visceral_fat;
    }

    public final void setVisceral_fat(float f) {
        this.visceral_fat = f;
    }

    @NotNull
    public final String getWeight() {
        return this.weight;
    }

    public final void setWeight(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.weight = str;
    }

    @NotNull
    public final String getWeightunit() {
        return this.weightunit;
    }

    public final void setWeightunit(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.weightunit = str;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Measure(double r52, double r54, int r56, int r57, double r58, double r60, java.lang.String r62, java.lang.String r63, java.lang.String r64, java.lang.String r65, boolean r66, java.lang.String r67, java.lang.String r68, java.lang.String r69, java.lang.String r70, int r71, int r72, java.lang.String r73, int r74, int r75, double r76, int r78, int r79, java.lang.String r80, double r81, double r83, java.lang.String r85, float r86, java.lang.String r87, java.lang.String r88, java.util.Date r89, boolean r90, java.lang.String r91, java.lang.String r92, java.lang.String r93, byte[] r94, float r95, float r96, float r97, boolean r98, int r99, int r100, kotlin.jvm.internal.DefaultConstructorMarker r101) {
        /*
            r51 = this;
            r0 = r100
            r1 = 1073741824(0x40000000, float:2.0)
            r1 = r99 & r1
            r2 = 0
            if (r1 == 0) goto L_0x000f
            r1 = r2
            java.util.Date r1 = (java.util.Date) r1
            r41 = r1
            goto L_0x0011
        L_0x000f:
            r41 = r89
        L_0x0011:
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r99 & r1
            r3 = 0
            if (r1 == 0) goto L_0x001b
            r42 = 0
            goto L_0x001d
        L_0x001b:
            r42 = r90
        L_0x001d:
            r1 = r0 & 1
            java.lang.String r4 = ""
            if (r1 == 0) goto L_0x0026
            r43 = r4
            goto L_0x0028
        L_0x0026:
            r43 = r91
        L_0x0028:
            r1 = r0 & 2
            if (r1 == 0) goto L_0x002f
            r44 = r4
            goto L_0x0031
        L_0x002f:
            r44 = r92
        L_0x0031:
            r1 = r0 & 4
            if (r1 == 0) goto L_0x0038
            r45 = r4
            goto L_0x003a
        L_0x0038:
            r45 = r93
        L_0x003a:
            r1 = r0 & 8
            if (r1 == 0) goto L_0x0044
            r1 = r2
            byte[] r1 = (byte[]) r1
            r46 = r1
            goto L_0x0046
        L_0x0044:
            r46 = r94
        L_0x0046:
            r1 = r0 & 16
            r2 = 0
            if (r1 == 0) goto L_0x004e
            r47 = 0
            goto L_0x0050
        L_0x004e:
            r47 = r95
        L_0x0050:
            r1 = r0 & 32
            if (r1 == 0) goto L_0x0057
            r48 = 0
            goto L_0x0059
        L_0x0057:
            r48 = r96
        L_0x0059:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0060
            r49 = 0
            goto L_0x0062
        L_0x0060:
            r49 = r97
        L_0x0062:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0069
            r50 = 0
            goto L_0x006b
        L_0x0069:
            r50 = r98
        L_0x006b:
            r3 = r51
            r4 = r52
            r6 = r54
            r8 = r56
            r9 = r57
            r10 = r58
            r12 = r60
            r14 = r62
            r15 = r63
            r16 = r64
            r17 = r65
            r18 = r66
            r19 = r67
            r20 = r68
            r21 = r69
            r22 = r70
            r23 = r71
            r24 = r72
            r25 = r73
            r26 = r74
            r27 = r75
            r28 = r76
            r30 = r78
            r31 = r79
            r32 = r80
            r33 = r81
            r35 = r83
            r37 = r85
            r38 = r86
            r39 = r87
            r40 = r88
            r3.<init>(r4, r6, r8, r9, r10, r12, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r30, r31, r32, r33, r35, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huayu.tzc.bean.Measure.<init>(double, double, int, int, double, double, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int, java.lang.String, int, int, double, int, int, java.lang.String, double, double, java.lang.String, float, java.lang.String, java.lang.String, java.util.Date, boolean, java.lang.String, java.lang.String, java.lang.String, byte[], float, float, float, boolean, int, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    @Nullable
    public final Date getDate() {
        return this.date;
    }

    public final void setDate(@Nullable Date date2) {
        this.date = date2;
    }

    public final boolean isConnect() {
        return this.isConnect;
    }

    public final void setConnect(boolean z) {
        this.isConnect = z;
    }

    @NotNull
    public final String getMacData() {
        return this.macData;
    }

    public final void setMacData(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.macData = str;
    }

    @NotNull
    public final String getMsgType() {
        return this.msgType;
    }

    public final void setMsgType(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.msgType = str;
    }

    @NotNull
    public final String getDevName() {
        return this.devName;
    }

    public final void setDevName(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.devName = str;
    }

    @Nullable
    public final byte[] getScanRecord() {
        return this.scanRecord;
    }

    public final void setScanRecord(@Nullable byte[] bArr) {
        this.scanRecord = bArr;
    }

    public final float getKgWeight() {
        return this.kgWeight;
    }

    public final void setKgWeight(float f) {
        this.kgWeight = f;
    }

    public final float getLbWeight() {
        return this.lbWeight;
    }

    public final void setLbWeight(float f) {
        this.lbWeight = f;
    }

    public final float getStWeight() {
        return this.stWeight;
    }

    public final void setStWeight(float f) {
        this.stWeight = f;
    }

    public final boolean getOnlyWeight() {
        return this.onlyWeight;
    }

    public final void setOnlyWeight(boolean z) {
        this.onlyWeight = z;
    }

    public Measure(double d, double d2, int i, int i2, double d3, double d4, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, boolean z, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8, int i3, int i4, @NotNull String str9, int i5, int i6, double d5, int i7, int i8, @NotNull String str10, double d6, double d7, @NotNull String str11, float f, @NotNull String str12, @NotNull String str13, @Nullable Date date2, boolean z2, @NotNull String str14, @NotNull String str15, @NotNull String str16, @Nullable byte[] bArr, float f2, float f3, float f4, boolean z3) {
        String str17 = str;
        String str18 = str2;
        String str19 = str3;
        String str20 = str4;
        String str21 = str5;
        String str22 = str6;
        String str23 = str7;
        String str24 = str8;
        String str25 = str9;
        String str26 = str10;
        String str27 = str11;
        String str28 = str12;
        String str29 = str13;
        String str30 = str16;
        Intrinsics.checkParameterIsNotNull(str17, "bone_mass");
        Intrinsics.checkParameterIsNotNull(str18, "create_date");
        Intrinsics.checkParameterIsNotNull(str19, "devmac");
        Intrinsics.checkParameterIsNotNull(str20, "ffm");
        Intrinsics.checkParameterIsNotNull(str21, ViewHierarchyConstants.DIMENSION_HEIGHT_KEY);
        Intrinsics.checkParameterIsNotNull(str22, "heightunit");
        Intrinsics.checkParameterIsNotNull(str23, "ibw");
        Intrinsics.checkParameterIsNotNull(str24, "is_mainmember");
        Intrinsics.checkParameterIsNotNull(str25, "muscle_mass");
        Intrinsics.checkParameterIsNotNull(str26, "shape");
        Intrinsics.checkParameterIsNotNull(str27, "user_name");
        Intrinsics.checkParameterIsNotNull(str28, "weight");
        Intrinsics.checkParameterIsNotNull(str29, "weightunit");
        Intrinsics.checkParameterIsNotNull(str14, "macData");
        Intrinsics.checkParameterIsNotNull(str15, "msgType");
        Intrinsics.checkParameterIsNotNull(str16, "devName");
        this.basalmetabolic_rate = d;
        this.bmi = d2;
        this.body_score = i;
        this.bodyage = i2;
        this.bodyfat_rate = d3;
        this.bodywater_rate = d4;
        this.bone_mass = str17;
        this.create_date = str18;
        this.devmac = str19;
        this.ffm = str20;
        this.flag = z;
        this.height = str21;
        this.heightunit = str22;
        this.ibw = str23;
        this.is_mainmember = str24;
        this.measuredate = i3;
        this.member_id = i4;
        this.muscle_mass = str25;
        this.obesity_grade = i5;
        this.physical_age = i6;
        this.protein_rate = d5;
        this.recordguid = i7;
        this.recordid = i8;
        this.shape = str26;
        this.skeletalfat_percentage = d6;
        this.subcutaneousfat_rate = d7;
        this.user_name = str27;
        this.visceral_fat = f;
        this.weight = str28;
        this.weightunit = str29;
        this.date = date2;
        this.isConnect = z2;
        this.macData = str14;
        this.msgType = str15;
        this.devName = str16;
        this.scanRecord = bArr;
        this.kgWeight = f2;
        this.lbWeight = f3;
        this.stWeight = f4;
        this.onlyWeight = z3;
    }

    public Measure() {
        this(-1.0d, -1.0d, -1, -1, -1.0d, -1.0d, "", "", "", "", true, "", "", "", "", 0, 0, "", -1, -1, -1.0d, -1, -1, "", -1.0d, -1.0d, "", -1.0f, "", "", (Date) null, false, (String) null, (String) null, (String) null, (byte[]) null, 0.0f, 0.0f, 0.0f, false, -1073741824, 255, (DefaultConstructorMarker) null);
    }

    @NotNull
    public final Date getMeasureDate() {
        return new Date(((long) this.measuredate) * ((long) 1000));
    }

    public final float computeWeight(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "bytes");
        String bytesToHex = ByteUtil.bytesToHex(bArr);
        Intrinsics.checkExpressionValueIsNotNull(bytesToHex, Config.DEVICE_WIDTH);
        int parseInt = Integer.parseInt(bytesToHex, CharsKt.checkRadix(16));
        float decimal = getDecimal(this.msgType);
        StringBuilder sb = new StringBuilder();
        sb.append(this.msgType.charAt(4));
        sb.append(this.msgType.charAt(3));
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().append(m…nd(msgType[3]).toString()");
        int hashCode = sb2.hashCode();
        if (hashCode != 1536) {
            if (hashCode != 1567) {
                if (hashCode == 1568 && sb2.equals("11")) {
                    String byteToHex = ByteUtil.byteToHex(bArr[0]);
                    Intrinsics.checkExpressionValueIsNotNull(byteToHex, "ByteUtil.byteToHex(bytes[0])");
                    int parseInt2 = Integer.parseInt(byteToHex, CharsKt.checkRadix(16));
                    String byteToHex2 = ByteUtil.byteToHex(bArr[1]);
                    Intrinsics.checkExpressionValueIsNotNull(byteToHex2, "ByteUtil.byteToHex(bytes[1])");
                    int parseInt3 = Integer.parseInt(byteToHex2, CharsKt.checkRadix(16));
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    float f = (float) 10;
                    Object[] objArr = {Float.valueOf(((float) parseInt3) / f)};
                    String format = String.format("%.1f", Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                    float parseFloat = Float.parseFloat(format);
                    if (parseFloat >= f) {
                        this.stWeight = UnitUtils.twoF(((float) parseInt2) + UnitUtils.lb2St((float) MathKt.roundToInt(parseFloat)));
                    } else {
                        this.stWeight = UnitUtils.twoF(((float) parseInt2) + UnitUtils.lb2St(parseFloat));
                    }
                    this.kgWeight = UnitUtils.st2Kg(this.stWeight);
                    this.lbWeight = UnitUtils.st2Lb(this.stWeight);
                }
            } else if (sb2.equals("10")) {
                this.lbWeight = UnitUtils.twoF(((float) parseInt) * decimal);
                this.kgWeight = UnitUtils.lb2Kg(this.lbWeight);
                this.stWeight = UnitUtils.lb2St(this.lbWeight);
            }
        } else if (sb2.equals("00")) {
            this.kgWeight = UnitUtils.twoF(((float) parseInt) * decimal);
            Log.e("kg", "computeWeight: " + this.kgWeight);
            this.stWeight = UnitUtils.kg2St(this.kgWeight);
            this.lbWeight = UnitUtils.kg2Lb(this.kgWeight);
        }
        return this.kgWeight;
    }

    private final float getDecimal(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(2));
        sb.append(str.charAt(1));
        String sb2 = sb.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "java.lang.StringBuilder(…nd(msgType[1]).toString()");
        int hashCode = sb2.hashCode();
        if (hashCode != 1536) {
            if (hashCode != 1537) {
                if (hashCode == 1567 && sb2.equals("10")) {
                    return 0.01f;
                }
            } else if (sb2.equals("01")) {
                return 1.0f;
            }
        } else if (sb2.equals("00")) {
            return 0.1f;
        }
        return 0.0f;
    }

    @NotNull
    public final byte[] getWeightData() {
        byte[] bArr = this.scanRecord;
        if (bArr == null) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[2];
        if (bArr == null) {
            Intrinsics.throwNpe();
        }
        bArr2[0] = bArr[4];
        byte[] bArr3 = this.scanRecord;
        if (bArr3 == null) {
            Intrinsics.throwNpe();
        }
        bArr2[1] = bArr3[5];
        return bArr2;
    }

    public final void computeTz(float f, int i, int i2, float f2, float f3) {
        int i3 = 99;
        if (i2 < 18) {
            i3 = 18;
        } else if (i2 <= 99) {
            i3 = i2;
        }
        if (f2 < ((float) 20)) {
            f2 = 20.0f;
        } else if (f2 > ((float) 150)) {
            f2 = 150.0f;
        }
        if (f < ((float) 90)) {
            f = 90.0f;
        } else if (f > ((float) 220)) {
            f = 220.0f;
        }
        if (f3 < ((float) 2000)) {
            f3 = 2000.0f;
        } else if (f3 > ((float) 15000)) {
            f3 = 15000.0f;
        }
        Log.e("TAG", "computeTz: 电阻 " + f3);
        CSBiasAPI.CSBiasV235Resp cs_bias_v235 = CSBiasAPI.cs_bias_v235(0, i, i3, (int) f, (int) (((float) 10) * f2), (int) f3, 2020);
        Intrinsics.checkExpressionValueIsNotNull(cs_bias_v235, "CSBiasAPI.cs_bias_v235(\n…           2020\n        )");
        if (cs_bias_v235.result == 0) {
            this.body_score = cs_bias_v235.data.SBC;
            Double oneD = UnitUtils.oneD(cs_bias_v235.data.f75PP);
            Intrinsics.checkExpressionValueIsNotNull(oneD, "UnitUtils.oneD(cSBiasV235Resp.data.PP)");
            this.protein_rate = oneD.doubleValue();
            this.muscle_mass = String.valueOf(UnitUtils.oneF((float) cs_bias_v235.data.SLM));
            this.bone_mass = String.valueOf(UnitUtils.oneF((float) (cs_bias_v235.data.BMC * 1.5d)));
            this.visceral_fat = UnitUtils.oneF((float) cs_bias_v235.data.VFR);
            Double oneD2 = UnitUtils.oneD(cs_bias_v235.data.BWP);
            Intrinsics.checkExpressionValueIsNotNull(oneD2, "UnitUtils.oneD(cSBiasV235Resp.data.BWP)");
            this.bodywater_rate = oneD2.doubleValue();
            this.basalmetabolic_rate = (double) cs_bias_v235.data.BMR;
            double d = cs_bias_v235.data.SMM;
            double d2 = (double) f2;
            Double.isNaN(d2);
            double d3 = d / d2;
            double d4 = (double) 100;
            Double.isNaN(d4);
            Double oneD3 = UnitUtils.oneD(d3 * d4);
            Intrinsics.checkExpressionValueIsNotNull(oneD3, "UnitUtils.oneD((cSBiasV2…data.SMM / weight * 100))");
            this.skeletalfat_percentage = oneD3.doubleValue();
            this.bodyage = getFalseAge(cs_bias_v235.data.f73MA, i2);
            int i4 = 1;
            if (i == 1) {
                i4 = 0;
            }
            this.obesity_grade = 0;
            Double oneD4 = UnitUtils.oneD(cs_bias_v235.data.BFP);
            Intrinsics.checkExpressionValueIsNotNull(oneD4, "UnitUtils.oneD(cSBiasV235Resp.data.BFP)");
            this.bodyfat_rate = oneD4.doubleValue();
            this.ffm = String.valueOf(UnitUtils.oneF(RangeUtil.getQzRange(f2, (float) cs_bias_v235.data.BFP)));
            double d5 = cs_bias_v235.data.BFP;
            double d6 = cs_bias_v235.data.VFR;
            double d7 = (double) 0.4f;
            Double.isNaN(d7);
            Double oneD5 = UnitUtils.oneD(d5 - (d6 * d7));
            Intrinsics.checkExpressionValueIsNotNull(oneD5, "UnitUtils.oneD((cSBiasV2…235Resp.data.VFR * 0.4f))");
            this.subcutaneousfat_rate = oneD5.doubleValue();
            this.ibw = String.valueOf(cs_bias_v235.data.SBW);
            Double oneD6 = UnitUtils.oneD(cs_bias_v235.data.BMI);
            Intrinsics.checkExpressionValueIsNotNull(oneD6, "UnitUtils.oneD(cSBiasV235Resp.data.BMI)");
            this.bmi = oneD6.doubleValue();
            String[] strArr = Constant.SHAPE_FALSE;
            float[] fatRangFalse = RangeUtil.getFatRangFalse(i4, i3);
            Intrinsics.checkExpressionValueIsNotNull(fatRangFalse, "RangeUtil.getFatRangFalse(sex, age)");
            float[] jRRange = RangeUtil.getJRRange(i4, f);
            Intrinsics.checkExpressionValueIsNotNull(jRRange, "RangeUtil.getJRRange(sex, height)");
            String str = strArr[getTx((float) cs_bias_v235.data.BFP, (float) cs_bias_v235.data.SLM, fatRangFalse, jRRange)];
            Intrinsics.checkExpressionValueIsNotNull(str, "Constant.SHAPE_FALSE[get…x, height)\n            )]");
            this.shape = str;
            return;
        }
        Log.e("TAG", "输入错误，错误码：" + cs_bias_v235.result);
    }

    public int getTx(float f, float f2, @NotNull float[] fArr, @NotNull float[] fArr2) {
        Intrinsics.checkParameterIsNotNull(fArr, "fatRang");
        Intrinsics.checkParameterIsNotNull(fArr2, "jrRang");
        int tzLevel = getTzLevel(f, fArr);
        int jrLevel = getJrLevel(f2, fArr2);
        Log.e("TAG", "getTx: " + tzLevel + "   " + jrLevel);
        if (tzLevel == 0 && jrLevel == 0) {
            return 0;
        }
        if ((tzLevel == 0 && jrLevel == 1) || (tzLevel == 0 && jrLevel == 2)) {
            return 1;
        }
        if (!(tzLevel == 1 && jrLevel == 0)) {
            if ((tzLevel == 1 && jrLevel == 1) || (tzLevel == 1 && jrLevel == 2)) {
                return 2;
            }
            if (!(tzLevel == 2 && jrLevel == 0)) {
                return 4;
            }
        }
        return 3;
    }

    private final int getTzLevel(float f, float[] fArr) {
        if (f <= fArr[0]) {
            return 0;
        }
        return f <= fArr[2] ? 1 : 2;
    }

    private final int getJrLevel(float f, float[] fArr) {
        if (f <= fArr[0]) {
            return 0;
        }
        return f <= fArr[1] ? 1 : 2;
    }
}
