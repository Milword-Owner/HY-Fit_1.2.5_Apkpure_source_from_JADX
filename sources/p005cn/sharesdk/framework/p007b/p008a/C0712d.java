package p005cn.sharesdk.framework.p007b.p008a;

import android.content.ContentValues;
import android.database.Cursor;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import p005cn.sharesdk.framework.utils.SSDKLog;

/* renamed from: cn.sharesdk.framework.b.a.d */
/* compiled from: MessageUtils */
public class C0712d {
    /* renamed from: a */
    public static synchronized long m192a(String str, long j) {
        synchronized (C0712d.class) {
            if (str != null) {
                if (str.trim() != "") {
                    C0710b a = C0710b.m187a();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("post_time", Long.valueOf(j));
                    contentValues.put("message_data", str.toString());
                    long a2 = a.mo10579a(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, contentValues);
                    return a2;
                }
            }
            return -1;
        }
    }

    /* renamed from: a */
    public static synchronized long m193a(ArrayList<String> arrayList) {
        synchronized (C0712d.class) {
            if (arrayList == null) {
                return 0;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arrayList.size(); i++) {
                sb.append("'");
                sb.append(arrayList.get(i));
                sb.append("'");
                sb.append(",");
            }
            String substring = sb.toString().substring(0, sb.length() - 1);
            C0710b a = C0710b.m187a();
            int a2 = a.mo10578a(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, "_id in ( " + substring + " )", (String[]) null);
            SSDKLog.m645b().mo29775i("delete COUNT == %s", Integer.valueOf(a2));
            long j = (long) a2;
            return j;
        }
    }

    /* renamed from: a */
    private static synchronized ArrayList<C0711c> m195a(String str, String[] strArr) {
        ArrayList<C0711c> arrayList;
        synchronized (C0712d.class) {
            arrayList = new ArrayList<>();
            C0711c cVar = new C0711c();
            StringBuilder sb = new StringBuilder();
            Cursor a = C0710b.m187a().mo10580a(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, new String[]{"_id", "post_time", "message_data"}, str, strArr, (String) null);
            while (a != null && a.moveToNext()) {
                cVar.f219b.add(a.getString(0));
                if (cVar.f219b.size() == 100) {
                    sb.append(a.getString(2));
                    cVar.f218a = sb.toString();
                    arrayList.add(cVar);
                    cVar = new C0711c();
                    sb = new StringBuilder();
                } else {
                    sb.append(a.getString(2) + "\n");
                }
            }
            a.close();
            if (cVar.f219b.size() != 0) {
                cVar.f218a = sb.toString().substring(0, sb.length() - 1);
                arrayList.add(cVar);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static synchronized ArrayList<C0711c> m194a() {
        synchronized (C0712d.class) {
            if (C0710b.m187a().mo10577a(ShareConstants.WEB_DIALOG_PARAM_MESSAGE) > 0) {
                ArrayList<C0711c> a = m195a((String) null, (String[]) null);
                return a;
            }
            ArrayList<C0711c> arrayList = new ArrayList<>();
            return arrayList;
        }
    }
}
