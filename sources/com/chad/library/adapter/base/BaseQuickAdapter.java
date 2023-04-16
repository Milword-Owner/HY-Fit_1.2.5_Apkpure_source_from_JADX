package com.chad.library.adapter.base;

import android.animation.Animator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.annotation.IdRes;
import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.chad.library.adapter.base.BaseQuickAdapterModuleImp;
import com.chad.library.adapter.base.animation.AlphaInAnimation;
import com.chad.library.adapter.base.animation.BaseAnimation;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.chad.library.adapter.base.animation.SlideInBottomAnimation;
import com.chad.library.adapter.base.animation.SlideInLeftAnimation;
import com.chad.library.adapter.base.animation.SlideInRightAnimation;
import com.chad.library.adapter.base.diff.BrvahAsyncDiffer;
import com.chad.library.adapter.base.diff.BrvahAsyncDifferConfig;
import com.chad.library.adapter.base.diff.BrvahListUpdateCallback;
import com.chad.library.adapter.base.listener.BaseListenerImp;
import com.chad.library.adapter.base.listener.GridSpanSizeLookup;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemChildLongClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.module.BaseDraggableModule;
import com.chad.library.adapter.base.module.BaseLoadMoreModule;
import com.chad.library.adapter.base.module.BaseUpFetchModule;
import com.chad.library.adapter.base.module.DraggableModule;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.module.UpFetchModule;
import com.chad.library.adapter.base.util.AdapterUtilsKt;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\t\n\u0002\b$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 ú\u0001*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\b\u0012\u0004\u0012\u0002H\u00020\u00042\u00020\u00052\u00020\u0006:\u0004ù\u0001ú\u0001B#\b\u0007\u0012\b\b\u0001\u0010\u0007\u001a\u00020\b\u0012\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010{\u001a\u00020|2\u0006\u0010}\u001a\u00020~H\u0002J\u0016\u0010\u001a\u00020|2\u000e\b\u0001\u0010\u0001\u001a\u00030\u0001\"\u00020\bJ\u0017\u0010\u0001\u001a\u00020|2\u000e\b\u0001\u0010\u0001\u001a\u00030\u0001\"\u00020\bJ\u0019\u0010\u0001\u001a\u00020|2\b\b\u0001\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0003\u0010\u0001J\"\u0010\u0001\u001a\u00020|2\t\b\u0001\u0010\u0001\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0003\u0010\u0001J$\u0010\u0001\u001a\u00020|2\t\b\u0001\u0010\u0001\u001a\u00020\b2\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u0001H\u0016J\u001b\u0010\u0001\u001a\u00020|2\u0010\b\u0001\u0010\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u0001H\u0016J)\u0010\u0001\u001a\u00020\b2\b\u0010\u0001\u001a\u00030\u00012\t\b\u0002\u0010\u0001\u001a\u00020\b2\t\b\u0002\u0010\u0001\u001a\u00020\bH\u0007J)\u0010\u0001\u001a\u00020\b2\b\u0010\u0001\u001a\u00030\u00012\t\b\u0002\u0010\u0001\u001a\u00020\b2\t\b\u0002\u0010\u0001\u001a\u00020\bH\u0007J!\u0010\u0001\u001a\u00020|2\u0007\u0010\u0001\u001a\u00028\u00012\u0007\u0010\u0001\u001a\u00020\bH\u0014¢\u0006\u0003\u0010\u0001J\t\u0010\u0001\u001a\u00020|H\u0002J\u0012\u0010\u0001\u001a\u00020|2\u0007\u0010\u0001\u001a\u00020\bH\u0004J \u0010\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00028\u00012\u0007\u0010\u0001\u001a\u00028\u0000H$¢\u0006\u0003\u0010\u0001J1\u0010\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00028\u00012\u0007\u0010\u0001\u001a\u00028\u00002\u000f\u0010\u0001\u001a\n\u0012\u0005\u0012\u00030\u00010\u0001H\u0014¢\u0006\u0003\u0010\u0001J)\u0010\u0001\u001a\u0004\u0018\u00018\u00012\f\u0010\u0001\u001a\u0007\u0012\u0002\b\u00030\u00012\b\u0010\u0001\u001a\u00030\u0001H\u0002¢\u0006\u0003\u0010 \u0001J\u0019\u0010¡\u0001\u001a\u00028\u00012\b\u0010\u0001\u001a\u00030\u0001H\u0014¢\u0006\u0003\u0010¢\u0001J#\u0010¡\u0001\u001a\u00028\u00012\b\u0010£\u0001\u001a\u00030¤\u00012\b\b\u0001\u0010\u0007\u001a\u00020\bH\u0014¢\u0006\u0003\u0010¥\u0001J\r\u0010¦\u0001\u001a\b\u0012\u0004\u0012\u00020\b0\u001aJ\r\u0010§\u0001\u001a\b\u0012\u0004\u0012\u00020\b0\u001aJ\t\u0010¨\u0001\u001a\u00020\bH\u0014J\u0012\u0010©\u0001\u001a\u00020\b2\u0007\u0010\u0001\u001a\u00020\bH\u0014J\u000f\u0010ª\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000QH\u0007J\r\u0010«\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000QJ\u001e\u0010¬\u0001\u001a\t\u0012\u0002\b\u0003\u0018\u00010\u00012\f\u0010\u0001\u001a\u0007\u0012\u0002\b\u00030\u0001H\u0002J\u001a\u0010­\u0001\u001a\u00028\u00002\t\b\u0001\u0010\u0001\u001a\u00020\bH\u0016¢\u0006\u0003\u0010®\u0001J\t\u0010¯\u0001\u001a\u00020\bH\u0016J\u0013\u0010°\u0001\u001a\u00030±\u00012\u0007\u0010\u0001\u001a\u00020\bH\u0016J\u001c\u0010²\u0001\u001a\u0004\u0018\u00018\u00002\t\b\u0001\u0010\u0001\u001a\u00020\bH\u0016¢\u0006\u0003\u0010®\u0001J\u001a\u0010³\u0001\u001a\u00020\b2\t\u0010\u0001\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0003\u0010´\u0001J\u0012\u0010µ\u0001\u001a\u00020\b2\u0007\u0010\u0001\u001a\u00020\bH\u0016J\t\u0010¶\u0001\u001a\u0004\u0018\u00010\\J\t\u0010·\u0001\u001a\u0004\u0018\u00010^J\t\u0010¸\u0001\u001a\u0004\u0018\u00010`J\t\u0010¹\u0001\u001a\u0004\u0018\u00010bJ\u001e\u0010º\u0001\u001a\u0005\u0018\u00010\u00012\u0007\u0010\u0001\u001a\u00020\b2\t\b\u0001\u0010»\u0001\u001a\u00020\bJ\u0007\u0010¼\u0001\u001a\u00020\u0014J\u0007\u0010½\u0001\u001a\u00020\u0014J\u0007\u0010¾\u0001\u001a\u00020\u0014J\u0012\u0010¿\u0001\u001a\u00020\u00142\u0007\u0010À\u0001\u001a\u00020\bH\u0014J\u0011\u0010Á\u0001\u001a\u00020|2\u0006\u0010m\u001a\u00020dH\u0016J \u0010Â\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00028\u00012\u0007\u0010\u0001\u001a\u00020\bH\u0016¢\u0006\u0003\u0010\u0001J0\u0010Â\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00028\u00012\u0007\u0010\u0001\u001a\u00020\b2\u000e\u0010\u0001\u001a\t\u0012\u0005\u0012\u00030\u00010\nH\u0016¢\u0006\u0003\u0010Ã\u0001J\"\u0010Ä\u0001\u001a\u00028\u00012\b\u0010£\u0001\u001a\u00030¤\u00012\u0007\u0010\u0001\u001a\u00020\bH\u0014¢\u0006\u0003\u0010¥\u0001J\"\u0010Å\u0001\u001a\u00028\u00012\b\u0010£\u0001\u001a\u00030¤\u00012\u0007\u0010\u0001\u001a\u00020\bH\u0016¢\u0006\u0003\u0010¥\u0001J\u0011\u0010Æ\u0001\u001a\u00020|2\u0006\u0010m\u001a\u00020dH\u0016J!\u0010Ç\u0001\u001a\u00020|2\u0007\u0010\u0001\u001a\u00028\u00012\u0007\u0010\u0001\u001a\u00020\bH\u0014¢\u0006\u0003\u0010\u0001J\u0017\u0010È\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010É\u0001J\u0017\u0010Ê\u0001\u001a\u00020|2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0003\u0010\u0001J\u0014\u0010Ê\u0001\u001a\u00020|2\t\b\u0001\u0010\u0001\u001a\u00020\bH\u0017J\u0007\u0010Ë\u0001\u001a\u00020|J\u0007\u0010Ì\u0001\u001a\u00020|J\u0014\u0010Í\u0001\u001a\u00020|2\t\b\u0001\u0010\u0001\u001a\u00020\bH\u0016J\u0007\u0010Î\u0001\u001a\u00020|J\u0011\u0010Ï\u0001\u001a\u00020|2\b\u0010Ð\u0001\u001a\u00030\u0001J\u0011\u0010Ñ\u0001\u001a\u00020|2\b\u0010Ò\u0001\u001a\u00030\u0001J\u0019\u0010Ó\u0001\u001a\u00020|2\u000e\u0010\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000\u0001H\u0017J\u0011\u0010Ô\u0001\u001a\u00020|2\b\u0010Õ\u0001\u001a\u00030Ö\u0001J\"\u0010×\u0001\u001a\u00020|2\t\b\u0001\u0010\u0001\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H\u0016¢\u0006\u0003\u0010\u0001J\u0017\u0010Ø\u0001\u001a\u00020|2\u000e\u0010Ù\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000Ú\u0001J\u0017\u0010Û\u0001\u001a\u00020|2\u000e\u0010Ü\u0001\u001a\t\u0012\u0004\u0012\u00028\u00000Ý\u0001J$\u0010Þ\u0001\u001a\u00020|2\n\b\u0001\u0010ß\u0001\u001a\u00030à\u00012\r\u0010á\u0001\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0016J\u001a\u0010Þ\u0001\u001a\u00020|2\u000f\u0010á\u0001\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\nH\u0016J\u0011\u0010â\u0001\u001a\u00020|2\b\u0010ã\u0001\u001a\u00030\u0001J\u000f\u0010â\u0001\u001a\u00020|2\u0006\u0010\u0007\u001a\u00020\bJ)\u0010ä\u0001\u001a\u00020\b2\b\u0010\u0001\u001a\u00030\u00012\t\b\u0002\u0010\u0001\u001a\u00020\b2\t\b\u0002\u0010\u0001\u001a\u00020\bH\u0007J\u0011\u0010å\u0001\u001a\u00020|2\u0006\u0010}\u001a\u00020~H\u0014J\u0014\u0010æ\u0001\u001a\u00020|2\t\u0010ç\u0001\u001a\u0004\u0018\u00010jH\u0016J)\u0010è\u0001\u001a\u00020\b2\b\u0010\u0001\u001a\u00030\u00012\t\b\u0002\u0010\u0001\u001a\u00020\b2\t\b\u0002\u0010\u0001\u001a\u00020\bH\u0007J\u001b\u0010é\u0001\u001a\u00020|2\u0010\u0010á\u0001\u001a\u000b\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0001H\u0016J\u0019\u0010ê\u0001\u001a\u00020|2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\nH\u0017J\u001a\u0010ë\u0001\u001a\u00020|2\u000f\u0010á\u0001\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\nH\u0016J\u001c\u0010ì\u0001\u001a\u00020|2\b\u0010í\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020\bH\u0014J\u0014\u0010î\u0001\u001a\u00020|2\t\u0010ï\u0001\u001a\u0004\u0018\u00010\\H\u0016J\u001c\u0010ð\u0001\u001a\u00020\u00142\b\u0010í\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020\bH\u0014J\u0014\u0010ñ\u0001\u001a\u00020|2\t\u0010ï\u0001\u001a\u0004\u0018\u00010^H\u0016J\u001c\u0010ò\u0001\u001a\u00020|2\b\u0010í\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020\bH\u0014J\u0014\u0010ó\u0001\u001a\u00020|2\t\u0010ï\u0001\u001a\u0004\u0018\u00010`H\u0016J\u001c\u0010ô\u0001\u001a\u00020\u00142\b\u0010í\u0001\u001a\u00030\u00012\u0007\u0010\u0001\u001a\u00020\bH\u0014J\u0014\u0010õ\u0001\u001a\u00020|2\t\u0010ï\u0001\u001a\u0004\u0018\u00010bH\u0016J\u001c\u0010ö\u0001\u001a\u00020|2\b\u0010÷\u0001\u001a\u00030ø\u00012\u0007\u0010\u0001\u001a\u00020\bH\u0014R(\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\b0\u001aX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u001aX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d@BX.¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R0\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\n2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\n@@X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0013\u0010)\u001a\u0004\u0018\u00010*8F¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0013\u0010-\u001a\u0004\u0018\u00010.8F¢\u0006\u0006\u001a\u0004\b/\u00100R\u0011\u00101\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b2\u00103R\u001a\u00104\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0016\"\u0004\b6\u0010\u0018R\u0011\u00107\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b8\u00103R\u001a\u00109\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u0016\"\u0004\b;\u0010\u0018R\u0013\u0010<\u001a\u0004\u0018\u00010.8F¢\u0006\u0006\u001a\u0004\b=\u00100R\u0011\u0010>\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b?\u00103R\u001a\u0010@\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u0016\"\u0004\bB\u0010\u0018R\u0011\u0010C\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\bD\u00103R\u001a\u0010E\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u0016\"\u0004\bG\u0010\u0018R\u001a\u0010H\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\u0016\"\u0004\bI\u0010\u0018R\u001a\u0010J\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u0016\"\u0004\bK\u0010\u0018R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010L\u001a\u00020M8F¢\u0006\u0006\u001a\u0004\bN\u0010OR\u0016\u0010P\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010QX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010R\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010S\u001a\u00020*X.¢\u0006\u0002\n\u0000R\u000e\u0010T\u001a\u00020.X.¢\u0006\u0002\n\u0000R\u000e\u0010U\u001a\u00020.X.¢\u0006\u0002\n\u0000R\u000e\u0010V\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010W\u001a\u0004\u0018\u00010MX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bX\u0010O\"\u0004\bY\u0010ZR\u0010\u0010[\u001a\u0004\u0018\u00010\\X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010]\u001a\u0004\u0018\u00010^X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010_\u001a\u0004\u0018\u00010`X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010a\u001a\u0004\u0018\u00010bX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010c\u001a\u0004\u0018\u00010dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\be\u0010f\"\u0004\bg\u0010hR\u0010\u0010i\u001a\u0004\u0018\u00010jX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010k\u001a\u0004\u0018\u00010lX\u000e¢\u0006\u0002\n\u0000R$\u0010m\u001a\u00020d2\u0006\u0010\f\u001a\u00020d8F@FX\u000e¢\u0006\f\u001a\u0004\bn\u0010f\"\u0004\bo\u0010hR\u0011\u0010p\u001a\u00020l8F¢\u0006\u0006\u001a\u0004\bq\u0010rR*\u0010s\u001a\b\u0012\u0004\u0012\u00020d0t8\u0006@\u0006X.¢\u0006\u0014\n\u0000\u0012\u0004\bu\u0010v\u001a\u0004\bw\u0010x\"\u0004\by\u0010z¨\u0006û\u0001"}, mo21895d2 = {"Lcom/chad/library/adapter/base/BaseQuickAdapter;", "T", "VH", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapterModuleImp;", "Lcom/chad/library/adapter/base/listener/BaseListenerImp;", "layoutResId", "", "data", "", "(ILjava/util/List;)V", "value", "Lcom/chad/library/adapter/base/animation/BaseAnimation;", "adapterAnimation", "getAdapterAnimation", "()Lcom/chad/library/adapter/base/animation/BaseAnimation;", "setAdapterAnimation", "(Lcom/chad/library/adapter/base/animation/BaseAnimation;)V", "animationEnable", "", "getAnimationEnable", "()Z", "setAnimationEnable", "(Z)V", "childClickViewIds", "Ljava/util/LinkedHashSet;", "childLongClickViewIds", "<set-?>", "Landroid/content/Context;", "context", "getContext", "()Landroid/content/Context;", "getData", "()Ljava/util/List;", "setData$com_github_CymChad_brvah", "(Ljava/util/List;)V", "draggableModule", "Lcom/chad/library/adapter/base/module/BaseDraggableModule;", "getDraggableModule", "()Lcom/chad/library/adapter/base/module/BaseDraggableModule;", "emptyLayout", "Landroid/widget/FrameLayout;", "getEmptyLayout", "()Landroid/widget/FrameLayout;", "footerLayout", "Landroid/widget/LinearLayout;", "getFooterLayout", "()Landroid/widget/LinearLayout;", "footerLayoutCount", "getFooterLayoutCount", "()I", "footerViewAsFlow", "getFooterViewAsFlow", "setFooterViewAsFlow", "footerViewPosition", "getFooterViewPosition", "footerWithEmptyEnable", "getFooterWithEmptyEnable", "setFooterWithEmptyEnable", "headerLayout", "getHeaderLayout", "headerLayoutCount", "getHeaderLayoutCount", "headerViewAsFlow", "getHeaderViewAsFlow", "setHeaderViewAsFlow", "headerViewPosition", "getHeaderViewPosition", "headerWithEmptyEnable", "getHeaderWithEmptyEnable", "setHeaderWithEmptyEnable", "isAnimationFirstOnly", "setAnimationFirstOnly", "isUseEmpty", "setUseEmpty", "loadMoreModule", "Lcom/chad/library/adapter/base/module/BaseLoadMoreModule;", "getLoadMoreModule", "()Lcom/chad/library/adapter/base/module/BaseLoadMoreModule;", "mDiffHelper", "Lcom/chad/library/adapter/base/diff/BrvahAsyncDiffer;", "mDraggableModule", "mEmptyLayout", "mFooterLayout", "mHeaderLayout", "mLastPosition", "mLoadMoreModule", "getMLoadMoreModule$com_github_CymChad_brvah", "setMLoadMoreModule$com_github_CymChad_brvah", "(Lcom/chad/library/adapter/base/module/BaseLoadMoreModule;)V", "mOnItemChildClickListener", "Lcom/chad/library/adapter/base/listener/OnItemChildClickListener;", "mOnItemChildLongClickListener", "Lcom/chad/library/adapter/base/listener/OnItemChildLongClickListener;", "mOnItemClickListener", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "mOnItemLongClickListener", "Lcom/chad/library/adapter/base/listener/OnItemLongClickListener;", "mRecyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getMRecyclerView$com_github_CymChad_brvah", "()Landroidx/recyclerview/widget/RecyclerView;", "setMRecyclerView$com_github_CymChad_brvah", "(Landroidx/recyclerview/widget/RecyclerView;)V", "mSpanSizeLookup", "Lcom/chad/library/adapter/base/listener/GridSpanSizeLookup;", "mUpFetchModule", "Lcom/chad/library/adapter/base/module/BaseUpFetchModule;", "recyclerView", "getRecyclerView", "setRecyclerView", "upFetchModule", "getUpFetchModule", "()Lcom/chad/library/adapter/base/module/BaseUpFetchModule;", "weakRecyclerView", "Ljava/lang/ref/WeakReference;", "weakRecyclerView$annotations", "()V", "getWeakRecyclerView", "()Ljava/lang/ref/WeakReference;", "setWeakRecyclerView", "(Ljava/lang/ref/WeakReference;)V", "addAnimation", "", "holder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "addChildClickViewIds", "viewIds", "", "addChildLongClickViewIds", "addData", "(Ljava/lang/Object;)V", "position", "(ILjava/lang/Object;)V", "newData", "", "addFooterView", "view", "Landroid/view/View;", "index", "orientation", "addHeaderView", "bindViewClickListener", "viewHolder", "viewType", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;I)V", "checkModule", "compatibilityDataSizeChanged", "size", "convert", "item", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;)V", "payloads", "", "", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;Ljava/util/List;)V", "createBaseGenericKInstance", "z", "Ljava/lang/Class;", "(Ljava/lang/Class;Landroid/view/View;)Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "createBaseViewHolder", "(Landroid/view/View;)Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "parent", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;I)Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "getChildClickViewIds", "getChildLongClickViewIds", "getDefItemCount", "getDefItemViewType", "getDiffHelper", "getDiffer", "getInstancedGenericKClass", "getItem", "(I)Ljava/lang/Object;", "getItemCount", "getItemId", "", "getItemOrNull", "getItemPosition", "(Ljava/lang/Object;)I", "getItemViewType", "getOnItemChildClickListener", "getOnItemChildLongClickListener", "getOnItemClickListener", "getOnItemLongClickListener", "getViewByPosition", "viewId", "hasEmptyView", "hasFooterLayout", "hasHeaderLayout", "isFixedViewType", "type", "onAttachedToRecyclerView", "onBindViewHolder", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;ILjava/util/List;)V", "onCreateDefViewHolder", "onCreateViewHolder", "onDetachedFromRecyclerView", "onItemViewHolderCreated", "onViewAttachedToWindow", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;)V", "remove", "removeAllFooterView", "removeAllHeaderView", "removeAt", "removeEmptyView", "removeFooterView", "footer", "removeHeaderView", "header", "replaceData", "setAnimationWithDefault", "animationType", "Lcom/chad/library/adapter/base/BaseQuickAdapter$AnimationType;", "setData", "setDiffCallback", "diffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "setDiffConfig", "config", "Lcom/chad/library/adapter/base/diff/BrvahAsyncDifferConfig;", "setDiffNewData", "diffResult", "Landroidx/recyclerview/widget/DiffUtil$DiffResult;", "list", "setEmptyView", "emptyView", "setFooterView", "setFullSpan", "setGridSpanSizeLookup", "spanSizeLookup", "setHeaderView", "setList", "setNewData", "setNewInstance", "setOnItemChildClick", "v", "setOnItemChildClickListener", "listener", "setOnItemChildLongClick", "setOnItemChildLongClickListener", "setOnItemClick", "setOnItemClickListener", "setOnItemLongClick", "setOnItemLongClickListener", "startAnim", "anim", "Landroid/animation/Animator;", "AnimationType", "Companion", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BaseQuickAdapter.kt */
public abstract class BaseQuickAdapter<T, VH extends BaseViewHolder> extends RecyclerView.Adapter<VH> implements BaseQuickAdapterModuleImp, BaseListenerImp {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int EMPTY_VIEW = 268436821;
    public static final int FOOTER_VIEW = 268436275;
    public static final int HEADER_VIEW = 268435729;
    public static final int LOAD_MORE_VIEW = 268436002;
    @Nullable
    private BaseAnimation adapterAnimation;
    private boolean animationEnable;
    private final LinkedHashSet<Integer> childClickViewIds;
    private final LinkedHashSet<Integer> childLongClickViewIds;
    @NotNull
    private Context context;
    @NotNull
    private List<T> data;
    private boolean footerViewAsFlow;
    private boolean footerWithEmptyEnable;
    private boolean headerViewAsFlow;
    private boolean headerWithEmptyEnable;
    private boolean isAnimationFirstOnly;
    private boolean isUseEmpty;
    private final int layoutResId;
    private BrvahAsyncDiffer<T> mDiffHelper;
    private BaseDraggableModule mDraggableModule;
    /* access modifiers changed from: private */
    public FrameLayout mEmptyLayout;
    /* access modifiers changed from: private */
    public LinearLayout mFooterLayout;
    /* access modifiers changed from: private */
    public LinearLayout mHeaderLayout;
    private int mLastPosition;
    @Nullable
    private BaseLoadMoreModule mLoadMoreModule;
    private OnItemChildClickListener mOnItemChildClickListener;
    private OnItemChildLongClickListener mOnItemChildLongClickListener;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;
    @Nullable
    private RecyclerView mRecyclerView;
    /* access modifiers changed from: private */
    public GridSpanSizeLookup mSpanSizeLookup;
    private BaseUpFetchModule mUpFetchModule;
    @NotNull
    public WeakReference<RecyclerView> weakRecyclerView;

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, mo21895d2 = {"Lcom/chad/library/adapter/base/BaseQuickAdapter$AnimationType;", "", "(Ljava/lang/String;I)V", "AlphaIn", "ScaleIn", "SlideInBottom", "SlideInLeft", "SlideInRight", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: BaseQuickAdapter.kt */
    public enum AnimationType {
        AlphaIn,
        ScaleIn,
        SlideInBottom,
        SlideInLeft,
        SlideInRight
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21896k = 3, mo21897mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[AnimationType.values().length];

        static {
            $EnumSwitchMapping$0[AnimationType.AlphaIn.ordinal()] = 1;
            $EnumSwitchMapping$0[AnimationType.ScaleIn.ordinal()] = 2;
            $EnumSwitchMapping$0[AnimationType.SlideInBottom.ordinal()] = 3;
            $EnumSwitchMapping$0[AnimationType.SlideInLeft.ordinal()] = 4;
            $EnumSwitchMapping$0[AnimationType.SlideInRight.ordinal()] = 5;
        }
    }

    @JvmOverloads
    public BaseQuickAdapter(@LayoutRes int i) {
        this(i, (List) null, 2, (DefaultConstructorMarker) null);
    }

    @Deprecated(message = "Please use recyclerView", replaceWith = @ReplaceWith(expression = "recyclerView", imports = {}))
    public static /* synthetic */ void weakRecyclerView$annotations() {
    }

    @JvmOverloads
    public final int addFooterView(@NotNull View view) {
        return addFooterView$default(this, view, 0, 0, 6, (Object) null);
    }

    @JvmOverloads
    public final int addFooterView(@NotNull View view, int i) {
        return addFooterView$default(this, view, i, 0, 4, (Object) null);
    }

    @JvmOverloads
    public final int addHeaderView(@NotNull View view) {
        return addHeaderView$default(this, view, 0, 0, 6, (Object) null);
    }

    @JvmOverloads
    public final int addHeaderView(@NotNull View view, int i) {
        return addHeaderView$default(this, view, i, 0, 4, (Object) null);
    }

    /* access modifiers changed from: protected */
    public abstract void convert(@NotNull VH vh, T t);

    /* access modifiers changed from: protected */
    public void convert(@NotNull VH vh, T t, @NotNull List<? extends Object> list) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        Intrinsics.checkParameterIsNotNull(list, "payloads");
    }

    public long getItemId(int i) {
        return (long) i;
    }

    /* access modifiers changed from: protected */
    public boolean isFixedViewType(int i) {
        return i == 268436821 || i == 268435729 || i == 268436275 || i == 268436002;
    }

    /* access modifiers changed from: protected */
    public void onItemViewHolderCreated(@NotNull VH vh, int i) {
        Intrinsics.checkParameterIsNotNull(vh, "viewHolder");
    }

    @JvmOverloads
    public final int setFooterView(@NotNull View view) {
        return setFooterView$default(this, view, 0, 0, 6, (Object) null);
    }

    @JvmOverloads
    public final int setFooterView(@NotNull View view, int i) {
        return setFooterView$default(this, view, i, 0, 4, (Object) null);
    }

    @JvmOverloads
    public final int setHeaderView(@NotNull View view) {
        return setHeaderView$default(this, view, 0, 0, 6, (Object) null);
    }

    @JvmOverloads
    public final int setHeaderView(@NotNull View view, int i) {
        return setHeaderView$default(this, view, i, 0, 4, (Object) null);
    }

    public static final /* synthetic */ FrameLayout access$getMEmptyLayout$p(BaseQuickAdapter baseQuickAdapter) {
        FrameLayout frameLayout = baseQuickAdapter.mEmptyLayout;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
        }
        return frameLayout;
    }

    public static final /* synthetic */ LinearLayout access$getMFooterLayout$p(BaseQuickAdapter baseQuickAdapter) {
        LinearLayout linearLayout = baseQuickAdapter.mFooterLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
        }
        return linearLayout;
    }

    public static final /* synthetic */ LinearLayout access$getMHeaderLayout$p(BaseQuickAdapter baseQuickAdapter) {
        LinearLayout linearLayout = baseQuickAdapter.mHeaderLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
        }
        return linearLayout;
    }

    @NotNull
    public BaseDraggableModule addDraggableModule(@NotNull BaseQuickAdapter<?, ?> baseQuickAdapter) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "baseQuickAdapter");
        return BaseQuickAdapterModuleImp.DefaultImpls.addDraggableModule(this, baseQuickAdapter);
    }

    @NotNull
    public BaseLoadMoreModule addLoadMoreModule(@NotNull BaseQuickAdapter<?, ?> baseQuickAdapter) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "baseQuickAdapter");
        return BaseQuickAdapterModuleImp.DefaultImpls.addLoadMoreModule(this, baseQuickAdapter);
    }

    @NotNull
    public BaseUpFetchModule addUpFetchModule(@NotNull BaseQuickAdapter<?, ?> baseQuickAdapter) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "baseQuickAdapter");
        return BaseQuickAdapterModuleImp.DefaultImpls.addUpFetchModule(this, baseQuickAdapter);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ BaseQuickAdapter(int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : list);
    }

    @JvmOverloads
    public BaseQuickAdapter(@LayoutRes int i, @Nullable List<T> list) {
        this.layoutResId = i;
        this.data = list == null ? new ArrayList<>() : list;
        this.isUseEmpty = true;
        this.isAnimationFirstOnly = true;
        this.mLastPosition = -1;
        checkModule();
        this.childClickViewIds = new LinkedHashSet<>();
        this.childLongClickViewIds = new LinkedHashSet<>();
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, mo21895d2 = {"Lcom/chad/library/adapter/base/BaseQuickAdapter$Companion;", "", "()V", "EMPTY_VIEW", "", "FOOTER_VIEW", "HEADER_VIEW", "LOAD_MORE_VIEW", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: BaseQuickAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    public final List<T> getData() {
        return this.data;
    }

    public final void setData$com_github_CymChad_brvah(@NotNull List<T> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.data = list;
    }

    public final boolean getHeaderWithEmptyEnable() {
        return this.headerWithEmptyEnable;
    }

    public final void setHeaderWithEmptyEnable(boolean z) {
        this.headerWithEmptyEnable = z;
    }

    public final boolean getFooterWithEmptyEnable() {
        return this.footerWithEmptyEnable;
    }

    public final void setFooterWithEmptyEnable(boolean z) {
        this.footerWithEmptyEnable = z;
    }

    public final boolean isUseEmpty() {
        return this.isUseEmpty;
    }

    public final void setUseEmpty(boolean z) {
        this.isUseEmpty = z;
    }

    public final boolean getHeaderViewAsFlow() {
        return this.headerViewAsFlow;
    }

    public final void setHeaderViewAsFlow(boolean z) {
        this.headerViewAsFlow = z;
    }

    public final boolean getFooterViewAsFlow() {
        return this.footerViewAsFlow;
    }

    public final void setFooterViewAsFlow(boolean z) {
        this.footerViewAsFlow = z;
    }

    public final boolean getAnimationEnable() {
        return this.animationEnable;
    }

    public final void setAnimationEnable(boolean z) {
        this.animationEnable = z;
    }

    public final boolean isAnimationFirstOnly() {
        return this.isAnimationFirstOnly;
    }

    public final void setAnimationFirstOnly(boolean z) {
        this.isAnimationFirstOnly = z;
    }

    @Nullable
    public final BaseAnimation getAdapterAnimation() {
        return this.adapterAnimation;
    }

    public final void setAdapterAnimation(@Nullable BaseAnimation baseAnimation) {
        this.animationEnable = true;
        this.adapterAnimation = baseAnimation;
    }

    @NotNull
    public final BaseLoadMoreModule getLoadMoreModule() {
        BaseLoadMoreModule baseLoadMoreModule = this.mLoadMoreModule;
        if (baseLoadMoreModule != null) {
            if (baseLoadMoreModule == null) {
                Intrinsics.throwNpe();
            }
            return baseLoadMoreModule;
        }
        throw new IllegalStateException("Please first implements LoadMoreModule".toString());
    }

    @NotNull
    public final BaseUpFetchModule getUpFetchModule() {
        BaseUpFetchModule baseUpFetchModule = this.mUpFetchModule;
        if (baseUpFetchModule != null) {
            if (baseUpFetchModule == null) {
                Intrinsics.throwNpe();
            }
            return baseUpFetchModule;
        }
        throw new IllegalStateException("Please first implements UpFetchModule".toString());
    }

    @NotNull
    public final BaseDraggableModule getDraggableModule() {
        BaseDraggableModule baseDraggableModule = this.mDraggableModule;
        if (baseDraggableModule != null) {
            if (baseDraggableModule == null) {
                Intrinsics.throwNpe();
            }
            return baseDraggableModule;
        }
        throw new IllegalStateException("Please first implements DraggableModule".toString());
    }

    @Nullable
    public final BaseLoadMoreModule getMLoadMoreModule$com_github_CymChad_brvah() {
        return this.mLoadMoreModule;
    }

    public final void setMLoadMoreModule$com_github_CymChad_brvah(@Nullable BaseLoadMoreModule baseLoadMoreModule) {
        this.mLoadMoreModule = baseLoadMoreModule;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final Context getContext() {
        Context context2 = this.context;
        if (context2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context");
        }
        return context2;
    }

    @NotNull
    public final WeakReference<RecyclerView> getWeakRecyclerView() {
        WeakReference<RecyclerView> weakReference = this.weakRecyclerView;
        if (weakReference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("weakRecyclerView");
        }
        return weakReference;
    }

    public final void setWeakRecyclerView(@NotNull WeakReference<RecyclerView> weakReference) {
        Intrinsics.checkParameterIsNotNull(weakReference, "<set-?>");
        this.weakRecyclerView = weakReference;
    }

    @Nullable
    public final RecyclerView getMRecyclerView$com_github_CymChad_brvah() {
        return this.mRecyclerView;
    }

    public final void setMRecyclerView$com_github_CymChad_brvah(@Nullable RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
    }

    public final void setRecyclerView(@NotNull RecyclerView recyclerView) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "value");
        this.mRecyclerView = recyclerView;
    }

    @NotNull
    public final RecyclerView getRecyclerView() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            if (recyclerView == null) {
                Intrinsics.throwNpe();
            }
            return recyclerView;
        }
        throw new IllegalStateException("Please get it after onAttachedToRecyclerView()".toString());
    }

    private final void checkModule() {
        if (this instanceof LoadMoreModule) {
            this.mLoadMoreModule = addLoadMoreModule(this);
        }
        if (this instanceof UpFetchModule) {
            this.mUpFetchModule = addUpFetchModule(this);
        }
        if (this instanceof DraggableModule) {
            this.mDraggableModule = addDraggableModule(this);
        }
    }

    @NotNull
    public VH onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        switch (i) {
            case HEADER_VIEW /*268435729*/:
                LinearLayout linearLayout = this.mHeaderLayout;
                if (linearLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
                }
                ViewParent parent = linearLayout.getParent();
                if (parent instanceof ViewGroup) {
                    ViewGroup viewGroup2 = (ViewGroup) parent;
                    LinearLayout linearLayout2 = this.mHeaderLayout;
                    if (linearLayout2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
                    }
                    viewGroup2.removeView(linearLayout2);
                }
                LinearLayout linearLayout3 = this.mHeaderLayout;
                if (linearLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
                }
                return createBaseViewHolder(linearLayout3);
            case LOAD_MORE_VIEW /*268436002*/:
                BaseLoadMoreModule baseLoadMoreModule = this.mLoadMoreModule;
                if (baseLoadMoreModule == null) {
                    Intrinsics.throwNpe();
                }
                VH createBaseViewHolder = createBaseViewHolder(baseLoadMoreModule.getLoadMoreView().getRootView(viewGroup));
                BaseLoadMoreModule baseLoadMoreModule2 = this.mLoadMoreModule;
                if (baseLoadMoreModule2 == null) {
                    Intrinsics.throwNpe();
                }
                baseLoadMoreModule2.setupViewHolder$com_github_CymChad_brvah(createBaseViewHolder);
                return createBaseViewHolder;
            case FOOTER_VIEW /*268436275*/:
                LinearLayout linearLayout4 = this.mFooterLayout;
                if (linearLayout4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
                }
                ViewParent parent2 = linearLayout4.getParent();
                if (parent2 instanceof ViewGroup) {
                    ViewGroup viewGroup3 = (ViewGroup) parent2;
                    LinearLayout linearLayout5 = this.mFooterLayout;
                    if (linearLayout5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
                    }
                    viewGroup3.removeView(linearLayout5);
                }
                LinearLayout linearLayout6 = this.mFooterLayout;
                if (linearLayout6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
                }
                return createBaseViewHolder(linearLayout6);
            case EMPTY_VIEW /*268436821*/:
                FrameLayout frameLayout = this.mEmptyLayout;
                if (frameLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
                }
                ViewParent parent3 = frameLayout.getParent();
                if (parent3 instanceof ViewGroup) {
                    ViewGroup viewGroup4 = (ViewGroup) parent3;
                    FrameLayout frameLayout2 = this.mEmptyLayout;
                    if (frameLayout2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
                    }
                    viewGroup4.removeView(frameLayout2);
                }
                FrameLayout frameLayout3 = this.mEmptyLayout;
                if (frameLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
                }
                return createBaseViewHolder(frameLayout3);
            default:
                VH onCreateDefViewHolder = onCreateDefViewHolder(viewGroup, i);
                bindViewClickListener(onCreateDefViewHolder, i);
                BaseDraggableModule baseDraggableModule = this.mDraggableModule;
                if (baseDraggableModule != null) {
                    baseDraggableModule.initView$com_github_CymChad_brvah(onCreateDefViewHolder);
                }
                onItemViewHolderCreated(onCreateDefViewHolder, i);
                return onCreateDefViewHolder;
        }
    }

    public int getItemCount() {
        int i = 1;
        if (hasEmptyView()) {
            if (this.headerWithEmptyEnable && hasHeaderLayout()) {
                i = 2;
            }
            return (!this.footerWithEmptyEnable || !hasFooterLayout()) ? i : i + 1;
        }
        BaseLoadMoreModule baseLoadMoreModule = this.mLoadMoreModule;
        if (baseLoadMoreModule == null || !baseLoadMoreModule.hasLoadMoreView()) {
            i = 0;
        }
        return getHeaderLayoutCount() + getDefItemCount() + getFooterLayoutCount() + i;
    }

    public int getItemViewType(int i) {
        if (hasEmptyView()) {
            boolean z = this.headerWithEmptyEnable && hasHeaderLayout();
            if (i == 0) {
                return z ? HEADER_VIEW : EMPTY_VIEW;
            }
            if (i != 1) {
                if (i != 2) {
                    return EMPTY_VIEW;
                }
            } else if (z) {
                return EMPTY_VIEW;
            }
            return FOOTER_VIEW;
        }
        boolean hasHeaderLayout = hasHeaderLayout();
        if (hasHeaderLayout && i == 0) {
            return HEADER_VIEW;
        }
        if (hasHeaderLayout) {
            i--;
        }
        int size = this.data.size();
        if (i < size) {
            return getDefItemViewType(i);
        }
        if (i - size < hasFooterLayout()) {
            return FOOTER_VIEW;
        }
        return LOAD_MORE_VIEW;
    }

    public void onBindViewHolder(@NotNull VH vh, int i) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        BaseUpFetchModule baseUpFetchModule = this.mUpFetchModule;
        if (baseUpFetchModule != null) {
            baseUpFetchModule.autoUpFetch$com_github_CymChad_brvah(i);
        }
        BaseLoadMoreModule baseLoadMoreModule = this.mLoadMoreModule;
        if (baseLoadMoreModule != null) {
            baseLoadMoreModule.autoLoadMore$com_github_CymChad_brvah(i);
        }
        switch (vh.getItemViewType()) {
            case HEADER_VIEW /*268435729*/:
            case FOOTER_VIEW /*268436275*/:
            case EMPTY_VIEW /*268436821*/:
                return;
            case LOAD_MORE_VIEW /*268436002*/:
                BaseLoadMoreModule baseLoadMoreModule2 = this.mLoadMoreModule;
                if (baseLoadMoreModule2 != null) {
                    baseLoadMoreModule2.getLoadMoreView().convert(vh, i, baseLoadMoreModule2.getLoadMoreStatus());
                    return;
                }
                return;
            default:
                convert(vh, getItem(i - getHeaderLayoutCount()));
                return;
        }
    }

    public void onBindViewHolder(@NotNull VH vh, int i, @NotNull List<Object> list) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        Intrinsics.checkParameterIsNotNull(list, "payloads");
        if (list.isEmpty()) {
            onBindViewHolder(vh, i);
            return;
        }
        BaseUpFetchModule baseUpFetchModule = this.mUpFetchModule;
        if (baseUpFetchModule != null) {
            baseUpFetchModule.autoUpFetch$com_github_CymChad_brvah(i);
        }
        BaseLoadMoreModule baseLoadMoreModule = this.mLoadMoreModule;
        if (baseLoadMoreModule != null) {
            baseLoadMoreModule.autoLoadMore$com_github_CymChad_brvah(i);
        }
        switch (vh.getItemViewType()) {
            case HEADER_VIEW /*268435729*/:
            case FOOTER_VIEW /*268436275*/:
            case EMPTY_VIEW /*268436821*/:
                return;
            case LOAD_MORE_VIEW /*268436002*/:
                BaseLoadMoreModule baseLoadMoreModule2 = this.mLoadMoreModule;
                if (baseLoadMoreModule2 != null) {
                    baseLoadMoreModule2.getLoadMoreView().convert(vh, i, baseLoadMoreModule2.getLoadMoreStatus());
                    return;
                }
                return;
            default:
                convert(vh, getItem(i - getHeaderLayoutCount()), list);
                return;
        }
    }

    public void onViewAttachedToWindow(@NotNull VH vh) {
        Intrinsics.checkParameterIsNotNull(vh, "holder");
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) vh;
        super.onViewAttachedToWindow(viewHolder);
        if (isFixedViewType(vh.getItemViewType())) {
            setFullSpan(viewHolder);
        } else {
            addAnimation(viewHolder);
        }
    }

    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        super.onAttachedToRecyclerView(recyclerView);
        this.weakRecyclerView = new WeakReference<>(recyclerView);
        this.mRecyclerView = recyclerView;
        Context context2 = recyclerView.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "recyclerView.context");
        this.context = context2;
        BaseDraggableModule baseDraggableModule = this.mDraggableModule;
        if (baseDraggableModule != null) {
            baseDraggableModule.attachToRecyclerView(recyclerView);
        }
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new BaseQuickAdapter$onAttachedToRecyclerView$1(this, layoutManager, gridLayoutManager.getSpanSizeLookup()));
        }
    }

    public void onDetachedFromRecyclerView(@NotNull RecyclerView recyclerView) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        super.onDetachedFromRecyclerView(recyclerView);
        this.mRecyclerView = null;
    }

    public T getItem(@IntRange(from = 0) int i) {
        return this.data.get(i);
    }

    @Nullable
    public T getItemOrNull(@IntRange(from = 0) int i) {
        return CollectionsKt.getOrNull(this.data, i);
    }

    public int getItemPosition(@Nullable T t) {
        if (t == null || !(!this.data.isEmpty())) {
            return -1;
        }
        return this.data.indexOf(t);
    }

    @NotNull
    public final LinkedHashSet<Integer> getChildClickViewIds() {
        return this.childClickViewIds;
    }

    public final void addChildClickViewIds(@NotNull @IdRes int... iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "viewIds");
        for (int valueOf : iArr) {
            this.childClickViewIds.add(Integer.valueOf(valueOf));
        }
    }

    @NotNull
    public final LinkedHashSet<Integer> getChildLongClickViewIds() {
        return this.childLongClickViewIds;
    }

    public final void addChildLongClickViewIds(@NotNull @IdRes int... iArr) {
        Intrinsics.checkParameterIsNotNull(iArr, "viewIds");
        for (int valueOf : iArr) {
            this.childLongClickViewIds.add(Integer.valueOf(valueOf));
        }
    }

    /* access modifiers changed from: protected */
    public void bindViewClickListener(@NotNull VH vh, int i) {
        Intrinsics.checkParameterIsNotNull(vh, "viewHolder");
        if (this.mOnItemClickListener != null) {
            vh.itemView.setOnClickListener(new BaseQuickAdapter$bindViewClickListener$$inlined$let$lambda$1(this, vh));
        }
        if (this.mOnItemLongClickListener != null) {
            vh.itemView.setOnLongClickListener(new BaseQuickAdapter$bindViewClickListener$$inlined$let$lambda$2(this, vh));
        }
        if (this.mOnItemChildClickListener != null) {
            Iterator it = getChildClickViewIds().iterator();
            while (it.hasNext()) {
                Integer num = (Integer) it.next();
                View view = vh.itemView;
                Intrinsics.checkExpressionValueIsNotNull(num, "id");
                View findViewById = view.findViewById(num.intValue());
                if (findViewById != null) {
                    if (!findViewById.isClickable()) {
                        findViewById.setClickable(true);
                    }
                    findViewById.setOnClickListener(new BaseQuickAdapter$bindViewClickListener$$inlined$let$lambda$3(this, vh));
                }
            }
        }
        if (this.mOnItemChildLongClickListener != null) {
            Iterator it2 = getChildLongClickViewIds().iterator();
            while (it2.hasNext()) {
                Integer num2 = (Integer) it2.next();
                View view2 = vh.itemView;
                Intrinsics.checkExpressionValueIsNotNull(num2, "id");
                View findViewById2 = view2.findViewById(num2.intValue());
                if (findViewById2 != null) {
                    if (!findViewById2.isLongClickable()) {
                        findViewById2.setLongClickable(true);
                    }
                    findViewById2.setOnLongClickListener(new BaseQuickAdapter$bindViewClickListener$$inlined$let$lambda$4(this, vh));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void setOnItemClick(@NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "v");
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(this, view, i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean setOnItemLongClick(@NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "v");
        OnItemLongClickListener onItemLongClickListener = this.mOnItemLongClickListener;
        if (onItemLongClickListener != null) {
            return onItemLongClickListener.onItemLongClick(this, view, i);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void setOnItemChildClick(@NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "v");
        OnItemChildClickListener onItemChildClickListener = this.mOnItemChildClickListener;
        if (onItemChildClickListener != null) {
            onItemChildClickListener.onItemChildClick(this, view, i);
        }
    }

    /* access modifiers changed from: protected */
    public boolean setOnItemChildLongClick(@NotNull View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "v");
        OnItemChildLongClickListener onItemChildLongClickListener = this.mOnItemChildLongClickListener;
        if (onItemChildLongClickListener != null) {
            return onItemChildLongClickListener.onItemChildLongClick(this, view, i);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public int getDefItemCount() {
        return this.data.size();
    }

    /* access modifiers changed from: protected */
    public int getDefItemViewType(int i) {
        return super.getItemViewType(i);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public VH onCreateDefViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return createBaseViewHolder(viewGroup, this.layoutResId);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public VH createBaseViewHolder(@NotNull ViewGroup viewGroup, @LayoutRes int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return createBaseViewHolder(AdapterUtilsKt.getItemView(viewGroup, i));
    }

    /* access modifiers changed from: protected */
    @NotNull
    public VH createBaseViewHolder(@NotNull View view) {
        VH vh;
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
        Class cls = getClass();
        Class<?> cls2 = null;
        while (cls2 == null && cls != null) {
            cls2 = getInstancedGenericKClass(cls);
            cls = cls.getSuperclass();
        }
        if (cls2 == null) {
            vh = new BaseViewHolder(view);
        } else {
            vh = createBaseGenericKInstance(cls2, view);
        }
        return vh != null ? vh : new BaseViewHolder(view);
    }

    private final Class<?> getInstancedGenericKClass(Class<?> cls) {
        try {
            Type genericSuperclass = cls.getGenericSuperclass();
            if (!(genericSuperclass instanceof ParameterizedType)) {
                return null;
            }
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
            Intrinsics.checkExpressionValueIsNotNull(actualTypeArguments, "type.actualTypeArguments");
            for (Type type : actualTypeArguments) {
                if (type instanceof Class) {
                    if (BaseViewHolder.class.isAssignableFrom((Class) type)) {
                        return (Class) type;
                    }
                } else if (type instanceof ParameterizedType) {
                    Type rawType = ((ParameterizedType) type).getRawType();
                    Intrinsics.checkExpressionValueIsNotNull(rawType, "temp.rawType");
                    if ((rawType instanceof Class) && BaseViewHolder.class.isAssignableFrom((Class) rawType)) {
                        return (Class) rawType;
                    }
                } else {
                    continue;
                }
            }
            return null;
        } catch (GenericSignatureFormatError e) {
            e.printStackTrace();
            return null;
        } catch (TypeNotPresentException e2) {
            e2.printStackTrace();
            return null;
        } catch (MalformedParameterizedTypeException e3) {
            e3.printStackTrace();
            return null;
        }
    }

    private final VH createBaseGenericKInstance(Class<?> cls, View view) {
        try {
            if (cls.isMemberClass()) {
                if (!Modifier.isStatic(cls.getModifiers())) {
                    Constructor<?> declaredConstructor = cls.getDeclaredConstructor(new Class[]{getClass(), View.class});
                    Intrinsics.checkExpressionValueIsNotNull(declaredConstructor, "z.getDeclaredConstructor…aClass, View::class.java)");
                    declaredConstructor.setAccessible(true);
                    VH newInstance = declaredConstructor.newInstance(new Object[]{this, view});
                    if (newInstance != null) {
                        return (BaseViewHolder) newInstance;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type VH");
                }
            }
            Constructor<?> declaredConstructor2 = cls.getDeclaredConstructor(new Class[]{View.class});
            Intrinsics.checkExpressionValueIsNotNull(declaredConstructor2, "z.getDeclaredConstructor(View::class.java)");
            declaredConstructor2.setAccessible(true);
            VH newInstance2 = declaredConstructor2.newInstance(new Object[]{view});
            if (newInstance2 != null) {
                return (BaseViewHolder) newInstance2;
            }
            throw new TypeCastException("null cannot be cast to non-null type VH");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            return null;
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void setFullSpan(@NotNull RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "holder");
        View view = viewHolder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        }
    }

    @Nullable
    public final View getViewByPosition(int i, @IdRes int i2) {
        BaseViewHolder baseViewHolder;
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView == null || (baseViewHolder = (BaseViewHolder) recyclerView.findViewHolderForLayoutPosition(i)) == null) {
            return null;
        }
        return baseViewHolder.getViewOrNull(i2);
    }

    public static /* synthetic */ int addHeaderView$default(BaseQuickAdapter baseQuickAdapter, View view, int i, int i2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                i = -1;
            }
            if ((i3 & 4) != 0) {
                i2 = 1;
            }
            return baseQuickAdapter.addHeaderView(view, i, i2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addHeaderView");
    }

    @JvmOverloads
    public final int addHeaderView(@NotNull View view, int i, int i2) {
        int headerViewPosition;
        ViewGroup.LayoutParams layoutParams;
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
        if (this.mHeaderLayout == null) {
            this.mHeaderLayout = new LinearLayout(view.getContext());
            LinearLayout linearLayout = this.mHeaderLayout;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
            }
            linearLayout.setOrientation(i2);
            LinearLayout linearLayout2 = this.mHeaderLayout;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
            }
            if (i2 == 1) {
                layoutParams = new RecyclerView.LayoutParams(-1, -2);
            } else {
                layoutParams = new RecyclerView.LayoutParams(-2, -1);
            }
            linearLayout2.setLayoutParams(layoutParams);
        }
        LinearLayout linearLayout3 = this.mHeaderLayout;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
        }
        int childCount = linearLayout3.getChildCount();
        if (i < 0 || i > childCount) {
            i = childCount;
        }
        LinearLayout linearLayout4 = this.mHeaderLayout;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
        }
        linearLayout4.addView(view, i);
        LinearLayout linearLayout5 = this.mHeaderLayout;
        if (linearLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
        }
        if (linearLayout5.getChildCount() == 1 && (headerViewPosition = getHeaderViewPosition()) != -1) {
            notifyItemInserted(headerViewPosition);
        }
        return i;
    }

    public static /* synthetic */ int setHeaderView$default(BaseQuickAdapter baseQuickAdapter, View view, int i, int i2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = 1;
            }
            return baseQuickAdapter.setHeaderView(view, i, i2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setHeaderView");
    }

    @JvmOverloads
    public final int setHeaderView(@NotNull View view, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
        if (this.mHeaderLayout != null) {
            LinearLayout linearLayout = this.mHeaderLayout;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
            }
            if (linearLayout.getChildCount() > i) {
                LinearLayout linearLayout2 = this.mHeaderLayout;
                if (linearLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
                }
                linearLayout2.removeViewAt(i);
                LinearLayout linearLayout3 = this.mHeaderLayout;
                if (linearLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
                }
                linearLayout3.addView(view, i);
                return i;
            }
        }
        return addHeaderView(view, i, i2);
    }

    public final boolean hasHeaderLayout() {
        if (this.mHeaderLayout == null) {
            return false;
        }
        LinearLayout linearLayout = this.mHeaderLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
        }
        return linearLayout.getChildCount() > 0;
    }

    public final void removeHeaderView(@NotNull View view) {
        int headerViewPosition;
        Intrinsics.checkParameterIsNotNull(view, "header");
        if (hasHeaderLayout()) {
            LinearLayout linearLayout = this.mHeaderLayout;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
            }
            linearLayout.removeView(view);
            LinearLayout linearLayout2 = this.mHeaderLayout;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
            }
            if (linearLayout2.getChildCount() == 0 && (headerViewPosition = getHeaderViewPosition()) != -1) {
                notifyItemRemoved(headerViewPosition);
            }
        }
    }

    public final void removeAllHeaderView() {
        if (hasHeaderLayout()) {
            LinearLayout linearLayout = this.mHeaderLayout;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
            }
            linearLayout.removeAllViews();
            int headerViewPosition = getHeaderViewPosition();
            if (headerViewPosition != -1) {
                notifyItemRemoved(headerViewPosition);
            }
        }
    }

    public final int getHeaderViewPosition() {
        if (!hasEmptyView() || this.headerWithEmptyEnable) {
            return 0;
        }
        return -1;
    }

    public final int getHeaderLayoutCount() {
        return hasHeaderLayout() ? 1 : 0;
    }

    @Nullable
    public final LinearLayout getHeaderLayout() {
        if (this.mHeaderLayout == null) {
            return null;
        }
        LinearLayout linearLayout = this.mHeaderLayout;
        if (linearLayout != null) {
            return linearLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mHeaderLayout");
        return linearLayout;
    }

    public static /* synthetic */ int addFooterView$default(BaseQuickAdapter baseQuickAdapter, View view, int i, int i2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                i = -1;
            }
            if ((i3 & 4) != 0) {
                i2 = 1;
            }
            return baseQuickAdapter.addFooterView(view, i, i2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: addFooterView");
    }

    @JvmOverloads
    public final int addFooterView(@NotNull View view, int i, int i2) {
        int footerViewPosition;
        ViewGroup.LayoutParams layoutParams;
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
        if (this.mFooterLayout == null) {
            this.mFooterLayout = new LinearLayout(view.getContext());
            LinearLayout linearLayout = this.mFooterLayout;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
            }
            linearLayout.setOrientation(i2);
            LinearLayout linearLayout2 = this.mFooterLayout;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
            }
            if (i2 == 1) {
                layoutParams = new RecyclerView.LayoutParams(-1, -2);
            } else {
                layoutParams = new RecyclerView.LayoutParams(-2, -1);
            }
            linearLayout2.setLayoutParams(layoutParams);
        }
        LinearLayout linearLayout3 = this.mFooterLayout;
        if (linearLayout3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
        }
        int childCount = linearLayout3.getChildCount();
        if (i < 0 || i > childCount) {
            i = childCount;
        }
        LinearLayout linearLayout4 = this.mFooterLayout;
        if (linearLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
        }
        linearLayout4.addView(view, i);
        LinearLayout linearLayout5 = this.mFooterLayout;
        if (linearLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
        }
        if (linearLayout5.getChildCount() == 1 && (footerViewPosition = getFooterViewPosition()) != -1) {
            notifyItemInserted(footerViewPosition);
        }
        return i;
    }

    public static /* synthetic */ int setFooterView$default(BaseQuickAdapter baseQuickAdapter, View view, int i, int i2, int i3, Object obj) {
        if (obj == null) {
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = 1;
            }
            return baseQuickAdapter.setFooterView(view, i, i2);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setFooterView");
    }

    @JvmOverloads
    public final int setFooterView(@NotNull View view, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(view, ViewHierarchyConstants.VIEW_KEY);
        if (this.mFooterLayout != null) {
            LinearLayout linearLayout = this.mFooterLayout;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
            }
            if (linearLayout.getChildCount() > i) {
                LinearLayout linearLayout2 = this.mFooterLayout;
                if (linearLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
                }
                linearLayout2.removeViewAt(i);
                LinearLayout linearLayout3 = this.mFooterLayout;
                if (linearLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
                }
                linearLayout3.addView(view, i);
                return i;
            }
        }
        return addFooterView(view, i, i2);
    }

    public final void removeFooterView(@NotNull View view) {
        int footerViewPosition;
        Intrinsics.checkParameterIsNotNull(view, "footer");
        if (hasFooterLayout()) {
            LinearLayout linearLayout = this.mFooterLayout;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
            }
            linearLayout.removeView(view);
            LinearLayout linearLayout2 = this.mFooterLayout;
            if (linearLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
            }
            if (linearLayout2.getChildCount() == 0 && (footerViewPosition = getFooterViewPosition()) != -1) {
                notifyItemRemoved(footerViewPosition);
            }
        }
    }

    public final void removeAllFooterView() {
        if (hasFooterLayout()) {
            LinearLayout linearLayout = this.mFooterLayout;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
            }
            linearLayout.removeAllViews();
            int footerViewPosition = getFooterViewPosition();
            if (footerViewPosition != -1) {
                notifyItemRemoved(footerViewPosition);
            }
        }
    }

    public final boolean hasFooterLayout() {
        if (this.mFooterLayout == null) {
            return false;
        }
        LinearLayout linearLayout = this.mFooterLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
        }
        return linearLayout.getChildCount() > 0;
    }

    public final int getFooterViewPosition() {
        if (!hasEmptyView()) {
            return getHeaderLayoutCount() + this.data.size();
        }
        int i = 1;
        if (this.headerWithEmptyEnable && hasHeaderLayout()) {
            i = 2;
        }
        if (this.footerWithEmptyEnable) {
            return i;
        }
        return -1;
    }

    public final int getFooterLayoutCount() {
        return hasFooterLayout() ? 1 : 0;
    }

    @Nullable
    public final LinearLayout getFooterLayout() {
        if (this.mFooterLayout == null) {
            return null;
        }
        LinearLayout linearLayout = this.mFooterLayout;
        if (linearLayout != null) {
            return linearLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mFooterLayout");
        return linearLayout;
    }

    public final void setEmptyView(@NotNull View view) {
        boolean z;
        ViewGroup.LayoutParams layoutParams;
        Intrinsics.checkParameterIsNotNull(view, "emptyView");
        int itemCount = getItemCount();
        int i = 0;
        if (this.mEmptyLayout == null) {
            this.mEmptyLayout = new FrameLayout(view.getContext());
            FrameLayout frameLayout = this.mEmptyLayout;
            if (frameLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
            }
            ViewGroup.LayoutParams layoutParams2 = view.getLayoutParams();
            if (layoutParams2 != null) {
                layoutParams = new ViewGroup.LayoutParams(layoutParams2.width, layoutParams2.height);
            } else {
                layoutParams = new ViewGroup.LayoutParams(-1, -1);
            }
            frameLayout.setLayoutParams(layoutParams);
            z = true;
        } else {
            ViewGroup.LayoutParams layoutParams3 = view.getLayoutParams();
            if (layoutParams3 != null) {
                FrameLayout frameLayout2 = this.mEmptyLayout;
                if (frameLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
                }
                ViewGroup.LayoutParams layoutParams4 = frameLayout2.getLayoutParams();
                layoutParams4.width = layoutParams3.width;
                layoutParams4.height = layoutParams3.height;
                FrameLayout frameLayout3 = this.mEmptyLayout;
                if (frameLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
                }
                frameLayout3.setLayoutParams(layoutParams4);
            }
            z = false;
        }
        FrameLayout frameLayout4 = this.mEmptyLayout;
        if (frameLayout4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
        }
        frameLayout4.removeAllViews();
        FrameLayout frameLayout5 = this.mEmptyLayout;
        if (frameLayout5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
        }
        frameLayout5.addView(view);
        this.isUseEmpty = true;
        if (z && hasEmptyView()) {
            if (this.headerWithEmptyEnable && hasHeaderLayout()) {
                i = 1;
            }
            if (getItemCount() > itemCount) {
                notifyItemInserted(i);
            } else {
                notifyDataSetChanged();
            }
        }
    }

    public final void setEmptyView(int i) {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            View inflate = LayoutInflater.from(recyclerView.getContext()).inflate(i, recyclerView, false);
            Intrinsics.checkExpressionValueIsNotNull(inflate, ViewHierarchyConstants.VIEW_KEY);
            setEmptyView(inflate);
        }
    }

    public final void removeEmptyView() {
        if (this.mEmptyLayout != null) {
            FrameLayout frameLayout = this.mEmptyLayout;
            if (frameLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
            }
            frameLayout.removeAllViews();
        }
    }

    public final boolean hasEmptyView() {
        if (this.mEmptyLayout != null) {
            FrameLayout frameLayout = this.mEmptyLayout;
            if (frameLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
            }
            if (frameLayout.getChildCount() != 0 && this.isUseEmpty) {
                return this.data.isEmpty();
            }
            return false;
        }
        return false;
    }

    @Nullable
    public final FrameLayout getEmptyLayout() {
        if (this.mEmptyLayout == null) {
            return null;
        }
        FrameLayout frameLayout = this.mEmptyLayout;
        if (frameLayout != null) {
            return frameLayout;
        }
        Intrinsics.throwUninitializedPropertyAccessException("mEmptyLayout");
        return frameLayout;
    }

    private final void addAnimation(RecyclerView.ViewHolder viewHolder) {
        if (!this.animationEnable) {
            return;
        }
        if (!this.isAnimationFirstOnly || viewHolder.getLayoutPosition() > this.mLastPosition) {
            BaseAnimation baseAnimation = this.adapterAnimation;
            if (baseAnimation == null) {
                baseAnimation = new AlphaInAnimation(0.0f, 1, (DefaultConstructorMarker) null);
            }
            View view = viewHolder.itemView;
            Intrinsics.checkExpressionValueIsNotNull(view, "holder.itemView");
            for (Animator startAnim : baseAnimation.animators(view)) {
                startAnim(startAnim, viewHolder.getLayoutPosition());
            }
            this.mLastPosition = viewHolder.getLayoutPosition();
        }
    }

    /* access modifiers changed from: protected */
    public void startAnim(@NotNull Animator animator, int i) {
        Intrinsics.checkParameterIsNotNull(animator, "anim");
        animator.start();
    }

    public final void setAnimationWithDefault(@NotNull AnimationType animationType) {
        BaseAnimation baseAnimation;
        Intrinsics.checkParameterIsNotNull(animationType, "animationType");
        int i = WhenMappings.$EnumSwitchMapping$0[animationType.ordinal()];
        if (i == 1) {
            baseAnimation = new AlphaInAnimation(0.0f, 1, (DefaultConstructorMarker) null);
        } else if (i == 2) {
            baseAnimation = new ScaleInAnimation(0.0f, 1, (DefaultConstructorMarker) null);
        } else if (i == 3) {
            baseAnimation = new SlideInBottomAnimation();
        } else if (i == 4) {
            baseAnimation = new SlideInLeftAnimation();
        } else if (i == 5) {
            baseAnimation = new SlideInRightAnimation();
        } else {
            throw new NoWhenBranchMatchedException();
        }
        setAdapterAnimation(baseAnimation);
    }

    @Deprecated(message = "Please use setNewInstance(), This method will be removed in the next version", replaceWith = @ReplaceWith(expression = "setNewInstance(data)", imports = {}))
    public void setNewData(@Nullable List<T> list) {
        setNewInstance(list);
    }

    public void setNewInstance(@Nullable List<T> list) {
        if (list != this.data) {
            if (list == null) {
                list = new ArrayList<>();
            }
            this.data = list;
            BaseLoadMoreModule baseLoadMoreModule = this.mLoadMoreModule;
            if (baseLoadMoreModule != null) {
                baseLoadMoreModule.reset$com_github_CymChad_brvah();
            }
            this.mLastPosition = -1;
            notifyDataSetChanged();
            BaseLoadMoreModule baseLoadMoreModule2 = this.mLoadMoreModule;
            if (baseLoadMoreModule2 != null) {
                baseLoadMoreModule2.checkDisableLoadMoreIfNotFullPage();
            }
        }
    }

    @Deprecated(message = "Please use setData()", replaceWith = @ReplaceWith(expression = "setData(newData)", imports = {}))
    public void replaceData(@NotNull Collection<? extends T> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "newData");
        setList(collection);
    }

    public void setList(@Nullable Collection<? extends T> collection) {
        List<T> list = this.data;
        boolean z = false;
        if (collection != list) {
            list.clear();
            if (collection == null || collection.isEmpty()) {
                z = true;
            }
            if (!z) {
                this.data.addAll(collection);
            }
        } else {
            if (collection == null || collection.isEmpty()) {
                z = true;
            }
            if (!z) {
                ArrayList arrayList = new ArrayList(collection);
                this.data.clear();
                this.data.addAll(arrayList);
            } else {
                this.data.clear();
            }
        }
        BaseLoadMoreModule baseLoadMoreModule = this.mLoadMoreModule;
        if (baseLoadMoreModule != null) {
            baseLoadMoreModule.reset$com_github_CymChad_brvah();
        }
        this.mLastPosition = -1;
        notifyDataSetChanged();
        BaseLoadMoreModule baseLoadMoreModule2 = this.mLoadMoreModule;
        if (baseLoadMoreModule2 != null) {
            baseLoadMoreModule2.checkDisableLoadMoreIfNotFullPage();
        }
    }

    public void setData(@IntRange(from = 0) int i, T t) {
        if (i < this.data.size()) {
            this.data.set(i, t);
            notifyItemChanged(i + getHeaderLayoutCount());
        }
    }

    public void addData(@IntRange(from = 0) int i, T t) {
        this.data.add(i, t);
        notifyItemInserted(i + getHeaderLayoutCount());
        compatibilityDataSizeChanged(1);
    }

    public void addData(@NonNull T t) {
        this.data.add(t);
        notifyItemInserted(this.data.size() + getHeaderLayoutCount());
        compatibilityDataSizeChanged(1);
    }

    public void addData(@IntRange(from = 0) int i, @NotNull Collection<? extends T> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "newData");
        this.data.addAll(i, collection);
        notifyItemRangeInserted(i + getHeaderLayoutCount(), collection.size());
        compatibilityDataSizeChanged(collection.size());
    }

    public void addData(@NotNull @NonNull Collection<? extends T> collection) {
        Intrinsics.checkParameterIsNotNull(collection, "newData");
        this.data.addAll(collection);
        notifyItemRangeInserted((this.data.size() - collection.size()) + getHeaderLayoutCount(), collection.size());
        compatibilityDataSizeChanged(collection.size());
    }

    @Deprecated(message = "Please use removeAt()", replaceWith = @ReplaceWith(expression = "removeAt(position)", imports = {}))
    public void remove(@IntRange(from = 0) int i) {
        removeAt(i);
    }

    public void removeAt(@IntRange(from = 0) int i) {
        if (i < this.data.size()) {
            this.data.remove(i);
            int headerLayoutCount = i + getHeaderLayoutCount();
            notifyItemRemoved(headerLayoutCount);
            compatibilityDataSizeChanged(0);
            notifyItemRangeChanged(headerLayoutCount, this.data.size() - headerLayoutCount);
        }
    }

    public void remove(T t) {
        int indexOf = this.data.indexOf(t);
        if (indexOf != -1) {
            removeAt(indexOf);
        }
    }

    /* access modifiers changed from: protected */
    public final void compatibilityDataSizeChanged(int i) {
        if (this.data.size() == i) {
            notifyDataSetChanged();
        }
    }

    public final void setDiffCallback(@NotNull DiffUtil.ItemCallback<T> itemCallback) {
        Intrinsics.checkParameterIsNotNull(itemCallback, "diffCallback");
        setDiffConfig(new BrvahAsyncDifferConfig.Builder(itemCallback).build());
    }

    public final void setDiffConfig(@NotNull BrvahAsyncDifferConfig<T> brvahAsyncDifferConfig) {
        Intrinsics.checkParameterIsNotNull(brvahAsyncDifferConfig, "config");
        this.mDiffHelper = new BrvahAsyncDiffer<>(this, brvahAsyncDifferConfig);
    }

    @NotNull
    @Deprecated(message = "User getDiffer()", replaceWith = @ReplaceWith(expression = "getDiffer()", imports = {}))
    public final BrvahAsyncDiffer<T> getDiffHelper() {
        return getDiffer();
    }

    @NotNull
    public final BrvahAsyncDiffer<T> getDiffer() {
        BrvahAsyncDiffer<T> brvahAsyncDiffer = this.mDiffHelper;
        if (brvahAsyncDiffer != null) {
            if (brvahAsyncDiffer == null) {
                Intrinsics.throwNpe();
            }
            return brvahAsyncDiffer;
        }
        throw new IllegalStateException("Please use setDiffCallback() or setDiffConfig() first!".toString());
    }

    public void setDiffNewData(@Nullable List<T> list) {
        if (hasEmptyView()) {
            setNewInstance(list);
            return;
        }
        BrvahAsyncDiffer<T> brvahAsyncDiffer = this.mDiffHelper;
        if (brvahAsyncDiffer != null) {
            BrvahAsyncDiffer.submitList$default(brvahAsyncDiffer, list, (Runnable) null, 2, (Object) null);
        }
    }

    public void setDiffNewData(@NotNull @NonNull DiffUtil.DiffResult diffResult, @NotNull List<T> list) {
        Intrinsics.checkParameterIsNotNull(diffResult, "diffResult");
        Intrinsics.checkParameterIsNotNull(list, "list");
        if (hasEmptyView()) {
            setNewInstance(list);
            return;
        }
        diffResult.dispatchUpdatesTo((ListUpdateCallback) new BrvahListUpdateCallback(this));
        this.data = list;
    }

    public void setGridSpanSizeLookup(@Nullable GridSpanSizeLookup gridSpanSizeLookup) {
        this.mSpanSizeLookup = gridSpanSizeLookup;
    }

    public void setOnItemClickListener(@Nullable OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(@Nullable OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemChildClickListener(@Nullable OnItemChildClickListener onItemChildClickListener) {
        this.mOnItemChildClickListener = onItemChildClickListener;
    }

    public void setOnItemChildLongClickListener(@Nullable OnItemChildLongClickListener onItemChildLongClickListener) {
        this.mOnItemChildLongClickListener = onItemChildLongClickListener;
    }

    @Nullable
    public final OnItemClickListener getOnItemClickListener() {
        return this.mOnItemClickListener;
    }

    @Nullable
    public final OnItemLongClickListener getOnItemLongClickListener() {
        return this.mOnItemLongClickListener;
    }

    @Nullable
    public final OnItemChildClickListener getOnItemChildClickListener() {
        return this.mOnItemChildClickListener;
    }

    @Nullable
    public final OnItemChildLongClickListener getOnItemChildLongClickListener() {
        return this.mOnItemChildLongClickListener;
    }
}
