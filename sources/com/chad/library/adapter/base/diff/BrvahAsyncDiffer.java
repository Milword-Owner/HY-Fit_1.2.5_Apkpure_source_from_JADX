package com.chad.library.adapter.base.diff;

import android.os.Handler;
import android.os.Looper;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001/B%\u0012\u0010\u0010\u0003\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0000¢\u0006\u0002\u0010\u0015J\u001b\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00028\u0000¢\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u00132\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u001aJ\u0016\u0010\u001b\u001a\u00020\u00132\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\nH\u0016J%\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00028\u00002\b\u0010\u001f\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010 J\u0006\u0010!\u001a\u00020\u0013J(\u0010\"\u001a\u00020\u00132\f\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J \u0010(\u001a\u00020\u00132\f\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\u001a2\b\u0010&\u001a\u0004\u0018\u00010'H\u0002J\u0013\u0010*\u001a\u00020\u00132\u0006\u0010+\u001a\u00028\u0000¢\u0006\u0002\u0010\u0015J\u000e\u0010,\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u000eJ\u0014\u0010-\u001a\u00020\u00132\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\nJ$\u0010.\u001a\u00020\u00132\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\t2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010'H\u0007R\u0018\u0010\u0003\u001a\f\u0012\u0004\u0012\u00028\u0000\u0012\u0002\b\u00030\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\n0\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u00060"}, mo21895d2 = {"Lcom/chad/library/adapter/base/diff/BrvahAsyncDiffer;", "T", "Lcom/chad/library/adapter/base/diff/DifferImp;", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "config", "Lcom/chad/library/adapter/base/diff/BrvahAsyncDifferConfig;", "(Lcom/chad/library/adapter/base/BaseQuickAdapter;Lcom/chad/library/adapter/base/diff/BrvahAsyncDifferConfig;)V", "mListeners", "", "Lcom/chad/library/adapter/base/diff/ListChangeListener;", "mMainThreadExecutor", "Ljava/util/concurrent/Executor;", "mMaxScheduledGeneration", "", "mUpdateCallback", "Landroidx/recyclerview/widget/ListUpdateCallback;", "sMainThreadExecutor", "addData", "", "data", "(Ljava/lang/Object;)V", "index", "(ILjava/lang/Object;)V", "addList", "list", "", "addListListener", "listener", "changeData", "newData", "payload", "(ILjava/lang/Object;Ljava/lang/Object;)V", "clearAllListListener", "latchList", "newList", "diffResult", "Landroidx/recyclerview/widget/DiffUtil$DiffResult;", "commitCallback", "Ljava/lang/Runnable;", "onCurrentListChanged", "previousList", "remove", "t", "removeAt", "removeListListener", "submitList", "MainThreadExecutor", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* compiled from: BrvahAsyncDiffer.kt */
public final class BrvahAsyncDiffer<T> implements DifferImp<T> {
    private final BaseQuickAdapter<T, ?> adapter;
    /* access modifiers changed from: private */
    public final BrvahAsyncDifferConfig<T> config;
    private final List<ListChangeListener<T>> mListeners;
    /* access modifiers changed from: private */
    public Executor mMainThreadExecutor;
    /* access modifiers changed from: private */
    public int mMaxScheduledGeneration;
    private final ListUpdateCallback mUpdateCallback = new BrvahListUpdateCallback(this.adapter);
    private final Executor sMainThreadExecutor = new MainThreadExecutor();

    @JvmOverloads
    public final void submitList(@Nullable List<T> list) {
        submitList$default(this, list, (Runnable) null, 2, (Object) null);
    }

    public BrvahAsyncDiffer(@NotNull BaseQuickAdapter<T, ?> baseQuickAdapter, @NotNull BrvahAsyncDifferConfig<T> brvahAsyncDifferConfig) {
        Intrinsics.checkParameterIsNotNull(baseQuickAdapter, "adapter");
        Intrinsics.checkParameterIsNotNull(brvahAsyncDifferConfig, "config");
        this.adapter = baseQuickAdapter;
        this.config = brvahAsyncDifferConfig;
        Executor mainThreadExecutor = this.config.getMainThreadExecutor();
        this.mMainThreadExecutor = mainThreadExecutor == null ? this.sMainThreadExecutor : mainThreadExecutor;
        this.mListeners = new CopyOnWriteArrayList();
    }

    @Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, mo21895d2 = {"Lcom/chad/library/adapter/base/diff/BrvahAsyncDiffer$MainThreadExecutor;", "Ljava/util/concurrent/Executor;", "()V", "mHandler", "Landroid/os/Handler;", "getMHandler", "()Landroid/os/Handler;", "execute", "", "command", "Ljava/lang/Runnable;", "com.github.CymChad.brvah"}, mo21896k = 1, mo21897mv = {1, 1, 16})
    /* compiled from: BrvahAsyncDiffer.kt */
    private static final class MainThreadExecutor implements Executor {
        @NotNull
        private final Handler mHandler = new Handler(Looper.getMainLooper());

        @NotNull
        public final Handler getMHandler() {
            return this.mHandler;
        }

        public void execute(@NotNull Runnable runnable) {
            Intrinsics.checkParameterIsNotNull(runnable, "command");
            this.mHandler.post(runnable);
        }
    }

    public final void addData(int i, T t) {
        List<T> data = this.adapter.getData();
        this.adapter.getData().add(i, t);
        this.mUpdateCallback.onInserted(i, 1);
        onCurrentListChanged(data, (Runnable) null);
    }

    public final void addData(T t) {
        List<T> data = this.adapter.getData();
        this.adapter.getData().add(t);
        this.mUpdateCallback.onInserted(data.size(), 1);
        onCurrentListChanged(data, (Runnable) null);
    }

    public final void addList(@Nullable List<? extends T> list) {
        if (list != null) {
            List<T> data = this.adapter.getData();
            this.adapter.getData().addAll(list);
            this.mUpdateCallback.onInserted(data.size(), list.size());
            onCurrentListChanged(data, (Runnable) null);
        }
    }

    public final void changeData(int i, T t, @Nullable T t2) {
        List<T> data = this.adapter.getData();
        this.adapter.getData().set(i, t);
        this.mUpdateCallback.onChanged(i, 1, t2);
        onCurrentListChanged(data, (Runnable) null);
    }

    public final void removeAt(int i) {
        List<T> data = this.adapter.getData();
        this.adapter.getData().remove(i);
        this.mUpdateCallback.onRemoved(i, 1);
        onCurrentListChanged(data, (Runnable) null);
    }

    public final void remove(T t) {
        List<T> data = this.adapter.getData();
        int indexOf = this.adapter.getData().indexOf(t);
        if (indexOf != -1) {
            this.adapter.getData().remove(indexOf);
            this.mUpdateCallback.onRemoved(indexOf, 1);
            onCurrentListChanged(data, (Runnable) null);
        }
    }

    public static /* synthetic */ void submitList$default(BrvahAsyncDiffer brvahAsyncDiffer, List list, Runnable runnable, int i, Object obj) {
        if ((i & 2) != 0) {
            runnable = null;
        }
        brvahAsyncDiffer.submitList(list, runnable);
    }

    @JvmOverloads
    public final void submitList(@Nullable List<T> list, @Nullable Runnable runnable) {
        this.mMaxScheduledGeneration++;
        int i = this.mMaxScheduledGeneration;
        if (list != this.adapter.getData()) {
            List<T> data = this.adapter.getData();
            if (list == null) {
                int size = this.adapter.getData().size();
                this.adapter.setData$com_github_CymChad_brvah(new ArrayList());
                this.mUpdateCallback.onRemoved(0, size);
                onCurrentListChanged(data, runnable);
            } else if (this.adapter.getData().isEmpty()) {
                this.adapter.setData$com_github_CymChad_brvah(list);
                this.mUpdateCallback.onInserted(0, list.size());
                onCurrentListChanged(data, runnable);
            } else {
                this.config.getBackgroundThreadExecutor().execute(new BrvahAsyncDiffer$submitList$1(this, data, list, i, runnable));
            }
        } else if (runnable != null) {
            runnable.run();
        }
    }

    /* access modifiers changed from: private */
    public final void latchList(List<T> list, DiffUtil.DiffResult diffResult, Runnable runnable) {
        List<T> data = this.adapter.getData();
        this.adapter.setData$com_github_CymChad_brvah(list);
        diffResult.dispatchUpdatesTo(this.mUpdateCallback);
        onCurrentListChanged(data, runnable);
    }

    private final void onCurrentListChanged(List<? extends T> list, Runnable runnable) {
        for (ListChangeListener<T> onCurrentListChanged : this.mListeners) {
            onCurrentListChanged.onCurrentListChanged(list, this.adapter.getData());
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    public void addListListener(@NotNull ListChangeListener<T> listChangeListener) {
        Intrinsics.checkParameterIsNotNull(listChangeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.mListeners.add(listChangeListener);
    }

    public final void removeListListener(@NotNull ListChangeListener<T> listChangeListener) {
        Intrinsics.checkParameterIsNotNull(listChangeListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.mListeners.remove(listChangeListener);
    }

    public final void clearAllListListener() {
        this.mListeners.clear();
    }
}
