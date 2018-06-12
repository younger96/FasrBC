package com.example.latte_core.net.iterceptor;

import android.support.annotation.RawRes;
import android.util.Log;

import com.example.latte_core.util.file.FileUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DebugInterceptor extends BaseInterceptor {
    private static final String TAG = "DebugInterceptor";

    private final String DEBUG_URL;
    private final int DEBUG_RAW_iD;

    public DebugInterceptor(String debugUrl, int rawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_iD = rawId;
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .message("OK")
                .addHeader("Content-Type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }


    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json= FileUtil.getRawFile(rawId);
        return getResponse(chain,json);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        Log.i(TAG, "intercept: "+url);
        if (url.contains(DEBUG_URL)){
            return debugResponse(chain,DEBUG_RAW_iD);
        }
        return chain.proceed(chain.request());
    }
}
