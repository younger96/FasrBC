package com.example.latte_core.net;

import android.content.Context;
import android.support.annotation.Nullable;

import com.example.latte_core.net.callback.IError;
import com.example.latte_core.net.callback.IFailure;
import com.example.latte_core.net.callback.IRequest;
import com.example.latte_core.net.callback.ISuccess;
import com.example.latte_core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by young on 18-3-21.
 */

public class RestClientBuilder {
    //网络请求操作参数
    private String mUrl;
    private Map<String, Object> mParams;
    private IRequest mRequest;
    private ISuccess mSuccess;
    private IFailure mFailure;
    private IError mError;
    private RequestBody mBody;

    //文件下载
    private  String mDownloadDir; //存放目录
    private  String mExtension;  //后缀名

    //文件上传
    private File mFile;
    //添加加载框操作
    private Context mContext;
    private LoaderStyle mLoaderStyle;


    public RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        mUrl = url;
        return this;
    }

    public final RestClientBuilder params(Map<String, Object> params) {
        mParams = params;
        return this;
    }

    public final RestClientBuilder params(String key,Object value) {
        if(mParams == null){
            mParams = new WeakHashMap<>();
        }
        mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder file(String fileName) {
        mFile = new File(fileName);
        return this;
    }

    public final RestClientBuilder file(File file) {
         mFile =file;
         return this;
    }


    //******文件下载
    //文件存放目录
    public final RestClientBuilder dir(String dir){
        mDownloadDir = dir;
        return this;
    }

    //文件后缀名
    public final RestClientBuilder extension(String extension){
        mExtension = extension;
        return this;
    }

    //******文件下载

    public final RestClientBuilder raw(String raw) {
        mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }


    public final RestClientBuilder request(IRequest request) {
        mRequest = request;
        return this;
    }

    public final RestClientBuilder success(ISuccess success) {
        mSuccess = success;
        return this;
    }

    public final RestClientBuilder failure(IFailure failure) {
        mFailure = failure;
        return this;
    }

    public final RestClientBuilder error(IError error) {
        mError = error;
        return this;
    }

    //加载框的添加
    public final RestClientBuilder showLoadingDialog(Context context,@Nullable LoaderStyle loaderStyle){
        mContext =context;
        if(loaderStyle == null){
            mLoaderStyle = LoaderStyle.BallClipRotateIndicator;
        }else {
            mLoaderStyle = loaderStyle;
        }
        return this;
    }


    public Map<String, Object> checkParams() {
        if (mParams == null) {
            return new WeakHashMap<>();
        }
        return mParams;
    }


    public final RestClientBuilder loader(Context context, LoaderStyle style)
    {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    //loader默认样式
    public final RestClientBuilder loader(Context context)
    {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }



    public final RestClient build() {
        return new RestClient(mUrl, mParams, mRequest, mSuccess, mFailure, mError,
                mBody,mFile,mDownloadDir,mExtension, mContext,mLoaderStyle);
    }



}
