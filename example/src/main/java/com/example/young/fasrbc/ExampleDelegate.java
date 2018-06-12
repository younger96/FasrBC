package com.example.young.fasrbc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.IError;
import com.example.latte_core.net.callback.IFailure;
import com.example.latte_core.net.callback.ISuccess;

public class ExampleDelegate extends LatteDelegate {

    private static final String TAG = "ExampleDelegate";


    @Override
    public Object setLayout() {
        return R.layout.delegate_test;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        testRestClient();
    }

    private void testRestClient()
    {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess()
                {
                    @Override
                    public void onSuccess(String response)
                    {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                        Log.i(TAG, "onSuccess: "+response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure(String failReason) {
                        Log.i(TAG, "onFailure: "+failReason);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(Object body, String message) {
                        Log.i(TAG, "onError: ");
                    }
                })
                .build()
                .get();
    }
}
