package com.huayu.tzc.utils;

import android.content.Context;
import android.util.Log;
import com.baidubce.BceClientException;
import com.baidubce.BceConfig;
import com.baidubce.BceServiceException;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.callback.BosProgressCallback;
import com.baidubce.services.bos.model.ObjectMetadata;
import com.baidubce.services.bos.model.PutObjectRequest;
import com.huayu.tzc.base.Constant;
import java.io.File;
import java.util.UUID;

public class UploadImg {
    public static String getKey(Context context, String str, int i) {
        String str2 = "/fit/" + i + BceConfig.BOS_DELIMITER + UUID.randomUUID() + str.substring(str.indexOf("."), str.length()) + "";
        Log.e("21", "UploadImg: " + str2);
        return str2;
    }

    public static PutObjectRequest uploadImg(Context context, String str, File file, BosProgressCallback bosProgressCallback) {
        try {
            BosClientConfiguration bosClientConfiguration = new BosClientConfiguration();
            bosClientConfiguration.setCredentials(new DefaultBceCredentials(Constant.ACCESS_KEY_ID, Constant.SECRET_ACCESS_KEY));
            bosClientConfiguration.setEndpoint(Constant.ENDPOINT);
            BosClient bosClient = new BosClient(bosClientConfiguration);
            PutObjectRequest putObjectRequest = new PutObjectRequest(Constant.BUCKETNAME, str, file);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType("text/plain");
            putObjectRequest.setObjectMetadata(objectMetadata);
            putObjectRequest.setProgressCallback(bosProgressCallback);
            String eTag = bosClient.putObject(putObjectRequest).getETag();
            Log.e("s", "UploadImg: " + eTag);
            return putObjectRequest;
        } catch (BceServiceException e) {
            Log.e("s", "UploadImg: Error Message:" + e.getMessage());
            return null;
        } catch (BceClientException e2) {
            Log.e("s", "UploadImg: Error Message:" + e2.getMessage());
            return null;
        }
    }
}
