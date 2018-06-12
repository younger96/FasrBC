package com.example.latte_core.net.iterceptor;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by young on 18-3-23.
 * 拦截器
 */

public class BaseInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        return null;
    }

    //获取url参数对
    protected LinkedHashMap<String,String> getUrlParameters(Chain chain){
        final HttpUrl url = chain.request().url();
        int size = url.querySize();
        final LinkedHashMap<String,String> params = new LinkedHashMap<>();
        for (int i = 0; i < size; i++) {
            params.put(url.queryParameterName(i),url.queryParameterValue(i));
        }
        return params;
    }


    //获取url参数
    protected String getUrlParameters(Chain chain,String key){
        final Request request = chain.request();
        return request.url().queryParameter(key);
    }


    //获取参数对
    protected LinkedHashMap<String,String> getBodyParameters(Chain chain){
        final FormBody body = (FormBody) chain.request().body();
        final LinkedHashMap<String,String> params = new LinkedHashMap<>();

        int size = 0;
        if (body != null){
            size = body.size();
        }
        for (int i = 0; i < size; i++) {
            params.put(body.name(i),body.value(i));
        }
        return params;
    }

    //获取参数
    protected String getBodyParameters(Chain chain,String key){
        return getBodyParameters(chain).get(key);
    }

}
