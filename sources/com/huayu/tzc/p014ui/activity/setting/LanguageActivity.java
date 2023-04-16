package com.huayu.tzc.p014ui.activity.setting;

import android.content.Intent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.hjq.language.MultiLanguages;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.MainActivity;
import com.huayu.tzc.adapter.LanguageAdapter;
import com.huayu.tzc.base.BaseActivity;
import com.huayu.tzc.bean.LanguageBean;
import com.huayu.tzc.contract.MainContract;
import com.huayu.tzc.presenter.NotPresenter;
import com.tencent.mmkv.MMKV;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0014J\b\u0010\f\u001a\u00020\u0003H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0014J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0012"}, mo21895d2 = {"Lcom/huayu/tzc/ui/activity/setting/LanguageActivity;", "Lcom/huayu/tzc/base/BaseActivity;", "Lcom/huayu/tzc/contract/MainContract$View;", "Lcom/huayu/tzc/presenter/NotPresenter;", "()V", "languageAdapter", "Lcom/huayu/tzc/adapter/LanguageAdapter;", "languageList", "", "Lcom/huayu/tzc/bean/LanguageBean;", "getLayoutId", "", "getPresenter", "initAdapter", "", "initView", "switchLanguage", "position", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.LanguageActivity */
/* compiled from: LanguageActivity.kt */
public final class LanguageActivity extends BaseActivity<MainContract.View, NotPresenter> {
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public LanguageAdapter languageAdapter;
    /* access modifiers changed from: private */
    public List<LanguageBean> languageList = new ArrayList();

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C2128R.C2133layout.activity_language;
    }

    public static final /* synthetic */ LanguageAdapter access$getLanguageAdapter$p(LanguageActivity languageActivity) {
        LanguageAdapter languageAdapter2 = languageActivity.languageAdapter;
        if (languageAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        }
        return languageAdapter2;
    }

    @NotNull
    public NotPresenter getPresenter() {
        return new NotPresenter();
    }

    /* access modifiers changed from: protected */
    public void initView() {
        int decodeInt = MMKV.defaultMMKV().decodeInt("language", 7);
        String[] stringArray = getResources().getStringArray(C2128R.array.language);
        Intrinsics.checkExpressionValueIsNotNull(stringArray, "resources.getStringArray(R.array.language)");
        for (String str : stringArray) {
            List<LanguageBean> list = this.languageList;
            Intrinsics.checkExpressionValueIsNotNull(str, "item");
            list.add(new LanguageBean(str, false));
        }
        this.languageList.get(decodeInt).setChecked(true);
        initAdapter();
    }

    private final void initAdapter() {
        this.languageAdapter = new LanguageAdapter(this.languageList);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.languageRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "languageRecyclerview");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(C2128R.C2131id.languageRecyclerview);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "languageRecyclerview");
        LanguageAdapter languageAdapter2 = this.languageAdapter;
        if (languageAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        }
        recyclerView2.setAdapter(languageAdapter2);
        LanguageAdapter languageAdapter3 = this.languageAdapter;
        if (languageAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("languageAdapter");
        }
        languageAdapter3.setOnItemClickListener(new LanguageActivity$initAdapter$1(this));
    }

    /* access modifiers changed from: private */
    public final void switchLanguage(int i) {
        boolean z;
        MMKV.defaultMMKV().encode("language", i);
        switch (i) {
            case 0:
                z = MultiLanguages.setAppLanguage(this, Locale.CHINA);
                break;
            case 1:
                z = MultiLanguages.setAppLanguage(this, Locale.ENGLISH);
                break;
            case 2:
                z = MultiLanguages.setAppLanguage(this, Locale.ENGLISH);
                break;
            case 3:
                z = MultiLanguages.setAppLanguage(this, Locale.ENGLISH);
                break;
            case 4:
                z = MultiLanguages.setAppLanguage(this, Locale.ENGLISH);
                break;
            case 5:
                z = MultiLanguages.setAppLanguage(this, Locale.ENGLISH);
                break;
            case 6:
                z = MultiLanguages.setAppLanguage(this, Locale.JAPANESE);
                break;
            case 7:
                z = MultiLanguages.setSystemLanguage(this);
                break;
            default:
                z = false;
                break;
        }
        if (z) {
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(C2128R.anim.activity_alpha_in, C2128R.anim.activity_alpha_out);
            finish();
        }
    }
}
