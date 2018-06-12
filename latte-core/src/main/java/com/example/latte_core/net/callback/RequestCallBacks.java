package com.example.latte_core.net.callback;

import android.os.Handler;
import android.util.Log;

import com.example.latte_core.ui.loader.LatteLoader;
import com.example.latte_core.ui.loader.LoaderStyle;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by young on 18-3-21.
 */

public class RequestCallBacks implements Callback<String>{
    private static final String TAG = "RequestCallBacks";

    private final IRequest REQUESR;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    private final LoaderStyle LOADERSTYLE;

    private static final Handler HANDLER = new Handler();

    public RequestCallBacks(IRequest request, ISuccess success,
                            IFailure failure, IError error,LoaderStyle loaderStyle) {
        this.REQUESR = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        LOADERSTYLE = loaderStyle;
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()){
            if (call.isExecuted()){
                if(SUCCESS != null){
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if(ERROR != null){
                ERROR.onError(response.body(),response.message());
            }
        }

        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

        Log.i(TAG, "onFailure: "+t);

        if(FAILURE!=null){
            FAILURE.onFailure(call.toString());
        }

        if (REQUESR != null){
            REQUESR.onRequestEnd();
        }

        stopLoading();
    }



    //停止加载
    private void stopLoading(){
//        if(LOADERSTYLE != null){
//            LatteLoader.stopLoading();
//        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(LOADERSTYLE != null){
                    LatteLoader.stopLoading();
                }
            }
        },2000);
    }

}
