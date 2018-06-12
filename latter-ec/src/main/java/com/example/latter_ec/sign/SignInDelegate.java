package com.example.latter_ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.IFailure;
import com.example.latte_core.net.callback.ISuccess;
import com.example.latte_core.util.log.LatteLogger;
import com.example.latter_ec.R;
import com.example.latter_ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 登录
 */
public class SignInDelegate extends SignBaseDelegate {
    private static final String TAG = "SignInDelegate";
    
    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText editSignInEmail;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText editSignInPassword;
    @BindView(R2.id.btn_sign_in)
    AppCompatButton btnSignIn;
    @BindView(R2.id.tv_link_sign_up)
    AppCompatTextView tvLinkSignUp;
    @BindView(R2.id.icon_sign_in_wechat)
    IconTextView iconSignInWechat;


    /**
     * 登录前的检测
     */
    @OnClick(R2.id.btn_sign_in)
    public void onClickSignIn() {
        if (checkForm()){
            Log.i(TAG, "onClickSignIn: ");

            //网络操作
            //假装登录成功
            RestClient.builder()
                    .url("http://127.0.0.1/user_profile.json")
//                    .params("phone", mPhone.getText().toString())
//                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess()
                    {
                        @Override
                        public void onSuccess(String response)
                        {
                            Log.i(TAG, "onSuccess: "+response);
                            
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onLogin(response, mISignListener);
                        }
                    })
                    .failure(new IFailure() {
                        @Override
                        public void onFailure(String failReason) {
                            Log.i(TAG, "onFailure: ");
                        }
                    })
                    .build()
                    .get();
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    private boolean checkForm() {
        final String email = editSignInEmail.getText().toString();
        final String password = editSignInPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editSignInEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            editSignInEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            editSignInPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            editSignInPassword.setError(null);
        }

        return isPass;

    }


    @OnClick(R2.id.icon_sign_in_wechat)
    public void onClickWechat() {

    }

    @OnClick(R2.id.tv_link_sign_up)
    public void onClickLink() {
        startWithPop(new SignUpDelegate());
    }
}
