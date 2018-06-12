package com.example.latter_ec.sign;

import android.app.Activity;

import com.example.latte_core.delegates.LatteDelegate;

public abstract class SignBaseDelegate extends LatteDelegate {
    protected ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }


}
