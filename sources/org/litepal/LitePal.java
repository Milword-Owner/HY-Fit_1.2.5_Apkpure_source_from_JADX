package org.litepal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.litepal.crud.LitePalSupport;
import org.litepal.crud.async.AverageExecutor;
import org.litepal.crud.async.CountExecutor;
import org.litepal.crud.async.FindExecutor;
import org.litepal.crud.async.FindMultiExecutor;
import org.litepal.crud.async.SaveExecutor;
import org.litepal.crud.async.UpdateOrDeleteExecutor;
import org.litepal.tablemanager.callback.DatabaseListener;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000Æ\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u001c\u0010\u0007\u001a\u00020\b2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0007J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0007J$\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000e2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0007J \u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000e2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0007J\b\u0010\u0010\u001a\u00020\u0004H\u0007J\u0014\u0010\u0011\u001a\u00020\u00122\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0007J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u0006H\u0007J\u001c\u0010\u0013\u001a\n \u000f*\u0004\u0018\u00010\u00140\u00142\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\nH\u0007J\u0018\u0010\u0013\u001a\n \u000f*\u0004\u0018\u00010\u00140\u00142\u0006\u0010\f\u001a\u00020\u0006H\u0007J\u001c\u0010\u0015\u001a\u00020\u00122\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J1\u0010\u0018\u001a\u00020\u00122\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0016\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u001a\"\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u001bJ-\u0010\u0018\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u00062\u0016\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u001a\"\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u001cJ9\u0010\u001d\u001a\n \u000f*\u0004\u0018\u00010\u001e0\u001e2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0016\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u001a\"\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010\u001fJ5\u0010\u001d\u001a\n \u000f*\u0004\u0018\u00010\u001e0\u001e2\u0006\u0010\f\u001a\u00020\u00062\u0016\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u001a\"\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010 J$\u0010!\u001a\n \u000f*\u0004\u0018\u00010\u001e0\u001e2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0006H\u0007J\b\u0010%\u001a\u00020\u0004H\u0007J1\u0010&\u001a\n \u000f*\u0004\u0018\u0001H'H'\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0007¢\u0006\u0002\u0010(J9\u0010&\u001a\n \u000f*\u0004\u0018\u0001H'H'\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\n2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010)\u001a\u00020#H\u0007¢\u0006\u0002\u0010*JT\u0010+\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010-0,\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\n2\u0006\u0010)\u001a\u00020#2\n\u0010.\u001a\u00020/\"\u00020\u0017H\u0007JL\u0010+\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010-0,\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\n2\n\u0010.\u001a\u00020/\"\u00020\u0017H\u0007JT\u00100\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010101\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\n2\u0006\u0010)\u001a\u00020#2\n\u0010.\u001a\u00020/\"\u00020\u0017H\u0007JL\u00100\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010101\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\n2\n\u0010.\u001a\u00020/\"\u00020\u0017H\u0007JH\u00102\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010303\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0007JP\u00102\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010303\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\n2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010)\u001a\u00020#H\u0007J)\u00104\u001a\n \u000f*\u0004\u0018\u000105052\u0012\u00106\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u001a\"\u00020\u0006H\u0007¢\u0006\u0002\u00107J)\u00108\u001a\n \u000f*\u0004\u0018\u0001H'H'\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007¢\u0006\u0002\u00109J1\u00108\u001a\n \u000f*\u0004\u0018\u0001H'H'\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\n2\u0006\u0010)\u001a\u00020#H\u0007¢\u0006\u0002\u0010:J@\u0010;\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010303\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007JH\u0010;\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010303\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\n2\u0006\u0010)\u001a\u00020#H\u0007J)\u0010<\u001a\n \u000f*\u0004\u0018\u0001H'H'\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007¢\u0006\u0002\u00109J1\u0010<\u001a\n \u000f*\u0004\u0018\u0001H'H'\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\n2\u0006\u0010)\u001a\u00020#H\u0007¢\u0006\u0002\u0010:J@\u0010=\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010303\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007JH\u0010=\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010303\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\n2\u0006\u0010)\u001a\u00020#H\u0007J\b\u0010>\u001a\u00020?H\u0007J\u0010\u0010@\u001a\u00020\u00042\u0006\u0010A\u001a\u00020BH\u0007J9\u0010C\u001a\u00020#\"\u0004\b\u0000\u0010'2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H'0\n2\u0016\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u001a\"\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010DJ\u0018\u0010E\u001a\n \u000f*\u0004\u0018\u00010F0F2\u0006\u0010G\u001a\u00020\u0012H\u0007J \u0010H\u001a\u00020\u0004\"\b\b\u0000\u0010'*\u00020I2\f\u0010J\u001a\b\u0012\u0004\u0012\u0002H'0KH\u0007J=\u0010L\u001a\n \u000f*\u0004\u0018\u0001H'H'\"\u0004\b\u0000\u0010'2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010M\u001a\u00020\u00062\f\u0010N\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007¢\u0006\u0002\u0010OJ9\u0010L\u001a\n \u000f*\u0004\u0018\u0001H'H'\"\u0004\b\u0000\u0010'2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u00062\f\u0010N\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007¢\u0006\u0002\u0010PJT\u0010Q\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010303\"\u0004\b\u0000\u0010'2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010M\u001a\u00020\u00062\f\u0010N\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007JP\u0010Q\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010303\"\u0004\b\u0000\u0010'2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u00062\f\u0010N\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007J=\u0010R\u001a\n \u000f*\u0004\u0018\u0001H'H'\"\u0004\b\u0000\u0010'2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010M\u001a\u00020\u00062\f\u0010N\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007¢\u0006\u0002\u0010OJ9\u0010R\u001a\n \u000f*\u0004\u0018\u0001H'H'\"\u0004\b\u0000\u0010'2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u00062\f\u0010N\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007¢\u0006\u0002\u0010PJT\u0010S\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010303\"\u0004\b\u0000\u0010'2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010M\u001a\u00020\u00062\f\u0010N\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007JP\u0010S\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010303\"\u0004\b\u0000\u0010'2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u00062\f\u0010N\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007J\u0018\u0010T\u001a\n \u000f*\u0004\u0018\u00010F0F2\u0006\u0010G\u001a\u00020\u0012H\u0007J\u001a\u0010U\u001a\n \u000f*\u0004\u0018\u00010F0F2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006H\u0007J\u0010\u0010V\u001a\u00020\u00042\u0006\u0010W\u001a\u00020XH\u0007J \u0010Y\u001a\u00020#\"\b\b\u0000\u0010'*\u00020I2\f\u0010J\u001a\b\u0012\u0004\u0012\u0002H'0KH\u0007J(\u0010Z\u001a\n \u000f*\u0004\u0018\u00010[0[\"\b\b\u0000\u0010'*\u00020I2\f\u0010J\u001a\b\u0012\u0004\u0012\u0002H'0KH\u0007J-\u0010\\\u001a\n \u000f*\u0004\u0018\u00010F0F2\u0016\u0010]\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u001a\"\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010^J\b\u0010_\u001a\u00020\u0004H\u0007J=\u0010`\u001a\n \u000f*\u0004\u0018\u0001H'H'\"\u0004\b\u0000\u0010'2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010M\u001a\u00020\u00062\f\u0010N\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007¢\u0006\u0002\u0010OJ9\u0010`\u001a\n \u000f*\u0004\u0018\u0001H'H'\"\u0004\b\u0000\u0010'2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u00062\f\u0010N\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007¢\u0006\u0002\u0010PJT\u0010a\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010303\"\u0004\b\u0000\u0010'2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010M\u001a\u00020\u00062\f\u0010N\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007JP\u0010a\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H' \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H'H'\u0018\u00010303\"\u0004\b\u0000\u0010'2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010M\u001a\u00020\u00062\f\u0010N\u001a\b\u0012\u0004\u0012\u0002H'0\nH\u0007J$\u0010b\u001a\u00020\u00122\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010c\u001a\u00020d2\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J9\u0010e\u001a\u00020\u00122\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010c\u001a\u00020d2\u0016\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u001a\"\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010fJ5\u0010e\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010c\u001a\u00020d2\u0016\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u001a\"\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010gJA\u0010h\u001a\n \u000f*\u0004\u0018\u00010\u001e0\u001e2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010c\u001a\u00020d2\u0016\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u001a\"\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010iJ=\u0010h\u001a\n \u000f*\u0004\u0018\u00010\u001e0\u001e2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010c\u001a\u00020d2\u0016\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u001a\"\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010jJ,\u0010k\u001a\n \u000f*\u0004\u0018\u00010\u001e0\u001e2\n\u0010\t\u001a\u0006\u0012\u0002\b\u00030\n2\u0006\u0010c\u001a\u00020d2\u0006\u0010\u0016\u001a\u00020\u0017H\u0007J\u0010\u0010l\u001a\u00020\u00042\u0006\u0010m\u001a\u00020nH\u0007J\b\u0010o\u001a\u00020\u0004H\u0007J-\u0010p\u001a\n \u000f*\u0004\u0018\u00010F0F2\u0016\u0010\u0019\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00060\u001a\"\u0004\u0018\u00010\u0006H\u0007¢\u0006\u0002\u0010^¨\u0006q"}, mo21895d2 = {"Lorg/litepal/LitePal;", "", "()V", "aesKey", "", "key", "", "average", "", "modelClass", "Ljava/lang/Class;", "column", "tableName", "averageAsync", "Lorg/litepal/crud/async/AverageExecutor;", "kotlin.jvm.PlatformType", "beginTransaction", "count", "", "countAsync", "Lorg/litepal/crud/async/CountExecutor;", "delete", "id", "", "deleteAll", "conditions", "", "(Ljava/lang/Class;[Ljava/lang/String;)I", "(Ljava/lang/String;[Ljava/lang/String;)I", "deleteAllAsync", "Lorg/litepal/crud/async/UpdateOrDeleteExecutor;", "(Ljava/lang/Class;[Ljava/lang/String;)Lorg/litepal/crud/async/UpdateOrDeleteExecutor;", "(Ljava/lang/String;[Ljava/lang/String;)Lorg/litepal/crud/async/UpdateOrDeleteExecutor;", "deleteAsync", "deleteDatabase", "", "dbName", "endTransaction", "find", "T", "(Ljava/lang/Class;J)Ljava/lang/Object;", "isEager", "(Ljava/lang/Class;JZ)Ljava/lang/Object;", "findAll", "", "", "ids", "", "findAllAsync", "Lorg/litepal/crud/async/FindMultiExecutor;", "findAsync", "Lorg/litepal/crud/async/FindExecutor;", "findBySQL", "Landroid/database/Cursor;", "sql", "([Ljava/lang/String;)Landroid/database/Cursor;", "findFirst", "(Ljava/lang/Class;)Ljava/lang/Object;", "(Ljava/lang/Class;Z)Ljava/lang/Object;", "findFirstAsync", "findLast", "findLastAsync", "getDatabase", "Landroid/database/sqlite/SQLiteDatabase;", "initialize", "context", "Landroid/content/Context;", "isExist", "(Ljava/lang/Class;[Ljava/lang/String;)Z", "limit", "Lorg/litepal/FluentQuery;", "value", "markAsDeleted", "Lorg/litepal/crud/LitePalSupport;", "collection", "", "max", "columnName", "columnType", "(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;", "maxAsync", "min", "minAsync", "offset", "order", "registerDatabaseListener", "listener", "Lorg/litepal/tablemanager/callback/DatabaseListener;", "saveAll", "saveAllAsync", "Lorg/litepal/crud/async/SaveExecutor;", "select", "columns", "([Ljava/lang/String;)Lorg/litepal/FluentQuery;", "setTransactionSuccessful", "sum", "sumAsync", "update", "values", "Landroid/content/ContentValues;", "updateAll", "(Ljava/lang/Class;Landroid/content/ContentValues;[Ljava/lang/String;)I", "(Ljava/lang/String;Landroid/content/ContentValues;[Ljava/lang/String;)I", "updateAllAsync", "(Ljava/lang/Class;Landroid/content/ContentValues;[Ljava/lang/String;)Lorg/litepal/crud/async/UpdateOrDeleteExecutor;", "(Ljava/lang/String;Landroid/content/ContentValues;[Ljava/lang/String;)Lorg/litepal/crud/async/UpdateOrDeleteExecutor;", "updateAsync", "use", "litePalDB", "Lorg/litepal/LitePalDB;", "useDefault", "where", "core_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: LitePal.kt */
public final class LitePal {
    public static final LitePal INSTANCE = new LitePal();

    private LitePal() {
    }

    @JvmStatic
    public static final void initialize(@NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Operator.initialize(context);
    }

    @JvmStatic
    @NotNull
    public static final SQLiteDatabase getDatabase() {
        SQLiteDatabase database = Operator.getDatabase();
        Intrinsics.checkExpressionValueIsNotNull(database, "Operator.getDatabase()");
        return database;
    }

    @JvmStatic
    public static final void beginTransaction() {
        Operator.beginTransaction();
    }

    @JvmStatic
    public static final void endTransaction() {
        Operator.endTransaction();
    }

    @JvmStatic
    public static final void setTransactionSuccessful() {
        Operator.setTransactionSuccessful();
    }

    @JvmStatic
    public static final void use(@NotNull LitePalDB litePalDB) {
        Intrinsics.checkParameterIsNotNull(litePalDB, "litePalDB");
        Operator.use(litePalDB);
    }

    @JvmStatic
    public static final void useDefault() {
        Operator.useDefault();
    }

    @JvmStatic
    public static final boolean deleteDatabase(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "dbName");
        return Operator.deleteDatabase(str);
    }

    @JvmStatic
    public static final void aesKey(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        Operator.aesKey(str);
    }

    @JvmStatic
    public static final FluentQuery select(@NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "columns");
        return Operator.select((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @JvmStatic
    public static final FluentQuery where(@NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        return Operator.where((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @JvmStatic
    public static final FluentQuery order(@Nullable String str) {
        return Operator.order(str);
    }

    @JvmStatic
    public static final FluentQuery limit(int i) {
        return Operator.limit(i);
    }

    @JvmStatic
    public static final FluentQuery offset(int i) {
        return Operator.offset(i);
    }

    @JvmStatic
    public static final int count(@NotNull Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.count(cls);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final CountExecutor countAsync(@NotNull Class<?> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.countAsync(cls);
    }

    @JvmStatic
    public static final int count(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        return Operator.count(str);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final CountExecutor countAsync(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        return Operator.countAsync(str);
    }

    @JvmStatic
    public static final double average(@NotNull Class<?> cls, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(str, "column");
        return Operator.average(cls, str);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final AverageExecutor averageAsync(@NotNull Class<?> cls, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(str, "column");
        return Operator.averageAsync(cls, str);
    }

    @JvmStatic
    public static final double average(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "column");
        return Operator.average(str, str2);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final AverageExecutor averageAsync(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "column");
        return Operator.averageAsync(str, str2);
    }

    @JvmStatic
    public static final <T> T max(@NotNull Class<?> cls, @NotNull String str, @NotNull Class<T> cls2) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.checkParameterIsNotNull(cls2, "columnType");
        return Operator.max(cls, str, cls2);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindExecutor<T> maxAsync(@NotNull Class<?> cls, @NotNull String str, @NotNull Class<T> cls2) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.checkParameterIsNotNull(cls2, "columnType");
        return Operator.maxAsync(cls, str, cls2);
    }

    @JvmStatic
    public static final <T> T max(@NotNull String str, @NotNull String str2, @NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.checkParameterIsNotNull(cls, "columnType");
        return Operator.max(str, str2, cls);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindExecutor<T> maxAsync(@NotNull String str, @NotNull String str2, @NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.checkParameterIsNotNull(cls, "columnType");
        return Operator.maxAsync(str, str2, cls);
    }

    @JvmStatic
    public static final <T> T min(@NotNull Class<?> cls, @NotNull String str, @NotNull Class<T> cls2) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.checkParameterIsNotNull(cls2, "columnType");
        return Operator.min(cls, str, cls2);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindExecutor<T> minAsync(@NotNull Class<?> cls, @NotNull String str, @NotNull Class<T> cls2) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.checkParameterIsNotNull(cls2, "columnType");
        return Operator.minAsync(cls, str, cls2);
    }

    @JvmStatic
    public static final <T> T min(@NotNull String str, @NotNull String str2, @NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.checkParameterIsNotNull(cls, "columnType");
        return Operator.min(str, str2, cls);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindExecutor<T> minAsync(@NotNull String str, @NotNull String str2, @NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.checkParameterIsNotNull(cls, "columnType");
        return Operator.minAsync(str, str2, cls);
    }

    @JvmStatic
    public static final <T> T sum(@NotNull Class<?> cls, @NotNull String str, @NotNull Class<T> cls2) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.checkParameterIsNotNull(cls2, "columnType");
        return Operator.sum(cls, str, cls2);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindExecutor<T> sumAsync(@NotNull Class<?> cls, @NotNull String str, @NotNull Class<T> cls2) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(str, "columnName");
        Intrinsics.checkParameterIsNotNull(cls2, "columnType");
        return Operator.sumAsync(cls, str, cls2);
    }

    @JvmStatic
    public static final <T> T sum(@NotNull String str, @NotNull String str2, @NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.checkParameterIsNotNull(cls, "columnType");
        return Operator.sum(str, str2, cls);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindExecutor<T> sumAsync(@NotNull String str, @NotNull String str2, @NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(str2, "columnName");
        Intrinsics.checkParameterIsNotNull(cls, "columnType");
        return Operator.sumAsync(str, str2, cls);
    }

    @JvmStatic
    public static final <T> T find(@NotNull Class<T> cls, long j) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.find(cls, j);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindExecutor<T> findAsync(@NotNull Class<T> cls, long j) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.findAsync(cls, j);
    }

    @JvmStatic
    public static final <T> T find(@NotNull Class<T> cls, long j, boolean z) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.find(cls, j, z);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindExecutor<T> findAsync(@NotNull Class<T> cls, long j, boolean z) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.findAsync(cls, j, z);
    }

    @JvmStatic
    public static final <T> T findFirst(@NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.findFirst(cls);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindExecutor<T> findFirstAsync(@NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.findFirstAsync(cls);
    }

    @JvmStatic
    public static final <T> T findFirst(@NotNull Class<T> cls, boolean z) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.findFirst(cls, z);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindExecutor<T> findFirstAsync(@NotNull Class<T> cls, boolean z) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.findFirstAsync(cls, z);
    }

    @JvmStatic
    public static final <T> T findLast(@NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.findLast(cls);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindExecutor<T> findLastAsync(@NotNull Class<T> cls) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.findLastAsync(cls);
    }

    @JvmStatic
    public static final <T> T findLast(@NotNull Class<T> cls, boolean z) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.findLast(cls, z);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindExecutor<T> findLastAsync(@NotNull Class<T> cls, boolean z) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.findLastAsync(cls, z);
    }

    @JvmStatic
    public static final <T> List<T> findAll(@NotNull Class<T> cls, @NotNull long... jArr) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(jArr, "ids");
        return Operator.findAll(cls, Arrays.copyOf(jArr, jArr.length));
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindMultiExecutor<T> findAllAsync(@NotNull Class<T> cls, @NotNull long... jArr) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(jArr, "ids");
        return Operator.findAllAsync(cls, Arrays.copyOf(jArr, jArr.length));
    }

    @JvmStatic
    public static final <T> List<T> findAll(@NotNull Class<T> cls, boolean z, @NotNull long... jArr) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(jArr, "ids");
        return Operator.findAll(cls, z, Arrays.copyOf(jArr, jArr.length));
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T> FindMultiExecutor<T> findAllAsync(@NotNull Class<T> cls, boolean z, @NotNull long... jArr) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(jArr, "ids");
        return Operator.findAllAsync(cls, z, Arrays.copyOf(jArr, jArr.length));
    }

    @JvmStatic
    public static final Cursor findBySQL(@NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(strArr, "sql");
        return Operator.findBySQL((String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @JvmStatic
    public static final int delete(@NotNull Class<?> cls, long j) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.delete(cls, j);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final UpdateOrDeleteExecutor deleteAsync(@NotNull Class<?> cls, long j) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        return Operator.deleteAsync(cls, j);
    }

    @JvmStatic
    public static final int deleteAll(@NotNull Class<?> cls, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        return Operator.deleteAll(cls, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final UpdateOrDeleteExecutor deleteAllAsync(@NotNull Class<?> cls, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        return Operator.deleteAllAsync(cls, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @JvmStatic
    public static final int deleteAll(@NotNull String str, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        return Operator.deleteAll(str, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final UpdateOrDeleteExecutor deleteAllAsync(@NotNull String str, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        return Operator.deleteAllAsync(str, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @JvmStatic
    public static final int update(@NotNull Class<?> cls, @NotNull ContentValues contentValues, long j) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(contentValues, "values");
        return Operator.update(cls, contentValues, j);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final UpdateOrDeleteExecutor updateAsync(@NotNull Class<?> cls, @NotNull ContentValues contentValues, long j) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(contentValues, "values");
        return Operator.updateAsync(cls, contentValues, j);
    }

    @JvmStatic
    public static final int updateAll(@NotNull Class<?> cls, @NotNull ContentValues contentValues, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(contentValues, "values");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        return Operator.updateAll(cls, contentValues, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final UpdateOrDeleteExecutor updateAllAsync(@NotNull Class<?> cls, @NotNull ContentValues contentValues, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(contentValues, "values");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        return Operator.updateAllAsync(cls, contentValues, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @JvmStatic
    public static final int updateAll(@NotNull String str, @NotNull ContentValues contentValues, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(contentValues, "values");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        return Operator.updateAll(str, contentValues, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final UpdateOrDeleteExecutor updateAllAsync(@NotNull String str, @NotNull ContentValues contentValues, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(str, "tableName");
        Intrinsics.checkParameterIsNotNull(contentValues, "values");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        return Operator.updateAllAsync(str, contentValues, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @JvmStatic
    public static final <T extends LitePalSupport> boolean saveAll(@NotNull Collection<? extends T> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "collection");
        return Operator.saveAll(collection);
    }

    @JvmStatic
    @Deprecated(message = "This method is deprecated and will be removed in the future releases.", replaceWith = @ReplaceWith(expression = "Handle async db operation in your own logic instead.", imports = {}))
    public static final <T extends LitePalSupport> SaveExecutor saveAllAsync(@NotNull Collection<? extends T> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "collection");
        return Operator.saveAllAsync(collection);
    }

    @JvmStatic
    public static final <T extends LitePalSupport> void markAsDeleted(@NotNull Collection<? extends T> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "collection");
        Operator.markAsDeleted(collection);
    }

    @JvmStatic
    public static final <T> boolean isExist(@NotNull Class<T> cls, @NotNull String... strArr) {
        Intrinsics.checkParameterIsNotNull(cls, "modelClass");
        Intrinsics.checkParameterIsNotNull(strArr, "conditions");
        return Operator.isExist(cls, (String[]) Arrays.copyOf(strArr, strArr.length));
    }

    @JvmStatic
    public static final void registerDatabaseListener(@NotNull DatabaseListener databaseListener) {
        Intrinsics.checkParameterIsNotNull(databaseListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Operator.registerDatabaseListener(databaseListener);
    }
}
