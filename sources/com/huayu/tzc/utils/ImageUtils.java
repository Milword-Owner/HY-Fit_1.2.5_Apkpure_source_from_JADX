package com.huayu.tzc.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import com.facebook.appevents.internal.ViewHierarchyConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {
    public static boolean saveBitmap(Context context, Bitmap bitmap, String str) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            try {
                File file = new File(str);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                intent.setData(Uri.fromFile(file));
                context.sendBroadcast(intent);
                Log.i(ViewHierarchyConstants.TAG_KEY, "saveBitmap success: " + file.getAbsolutePath());
                return true;
            } catch (IOException e) {
                Log.e(ViewHierarchyConstants.TAG_KEY, "saveBitmap: " + e.getMessage());
                return false;
            }
        } else {
            Log.e(ViewHierarchyConstants.TAG_KEY, "saveBitmap failure : sdcard not mounted");
            return false;
        }
    }
}
