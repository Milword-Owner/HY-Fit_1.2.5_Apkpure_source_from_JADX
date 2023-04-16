package com.huayu.tzc.http;

import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class StringConverter implements Converter<ResponseBody, String> {
    public static final StringConverter INSTANCE = new StringConverter();

    public String convert(ResponseBody responseBody) throws IOException {
        return responseBody.string();
    }
}
