package com.huayu.tzc.p014ui.activity.setting;

import android.app.Activity;
import android.os.Build;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;
import com.huayu.tzc.C2128R;
import com.huayu.tzc.utils.CalendarReminderUtils;
import java.util.Calendar;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(mo21893bv = {1, 0, 3}, mo21894d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016J \u0010\t\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\bH\u0016¨\u0006\u000b"}, mo21895d2 = {"com/huayu/tzc/ui/activity/setting/RemindActivity$requestCalendarPermission$1", "Lcom/hjq/permissions/OnPermissionCallback;", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_release"}, mo21896k = 1, mo21897mv = {1, 1, 16})
/* renamed from: com.huayu.tzc.ui.activity.setting.RemindActivity$requestCalendarPermission$1 */
/* compiled from: RemindActivity.kt */
public final class RemindActivity$requestCalendarPermission$1 implements OnPermissionCallback {
    final /* synthetic */ int $requestCode;
    final /* synthetic */ RemindActivity this$0;

    RemindActivity$requestCalendarPermission$1(RemindActivity remindActivity, int i) {
        this.this$0 = remindActivity;
        this.$requestCode = i;
    }

    public void onGranted(@Nullable List<String> list, boolean z) {
        if (z && Build.VERSION.SDK_INT >= 24) {
            int i = this.$requestCode;
            if (i == 616) {
                String string = this.this$0.getString(C2128R.string.alarm_content);
                Calendar access$getCalendar$p = this.this$0.calendar;
                Intrinsics.checkExpressionValueIsNotNull(access$getCalendar$p, "calendar");
                CalendarReminderUtils.addCalendarEvent(this.this$0, this.this$0.getString(C2128R.string.alarm_title) + this.this$0.getTitle(), string, access$getCalendar$p.getTimeInMillis(), 1);
            } else if (i == 617) {
                CalendarReminderUtils.deleteCalendarEvent(this.this$0, this.this$0.getString(C2128R.string.alarm_title) + this.this$0.getTitle());
            }
            ToastUtils.show((CharSequence) "ok");
        }
    }

    public void onDenied(@Nullable List<String> list, boolean z) {
        ToastUtils.show((CharSequence) this.this$0.getString(C2128R.string.permiss_fail));
        if (z) {
            XXPermissions.startPermissionActivity((Activity) this.this$0, list);
        }
    }
}
