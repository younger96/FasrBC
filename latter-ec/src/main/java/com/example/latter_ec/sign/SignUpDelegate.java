package com.example.latter_ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.util.Patterns;
import android.view.View;

import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.ISuccess;
import com.example.latte_core.util.log.LatteLogger;
import com.example.latter_ec.R;
import com.example.latter_ec.R2;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 注册
 */
public class SignUpDelegate extends SignBaseDelegate {
    private static final String TAG = "SignUpDelegate";

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText editSignUpName ;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText editSignUpEmail;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText editSignUpPhone;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText editSignUpPassword;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText editSignUpRePassword;
    @BindView(R2.id.btn_sign_up)
    AppCompatButton btnSignUp;
    @BindView(R2.id.tv_link_sign_in)
    AppCompatButton tvLinkSignIn;



    @OnClick(R2.id.btn_sign_up)
    public void onViewClicked() {
        if (checkForm()){
            //注册操作
            //假装成功注册
            RestClient.builder()
                    .url("http://127.0.0.1/user_profile.json")
//                    .params("name", mName.getText().toString())
//                    .params("email", mEmail.getText().toString())
//                    .params("phone", mPhone.getText().toString())
//                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess()
                    {
                        @Override
                        public void onSuccess(String response)
                        {
                            LatteLogger.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response, mISignListener);
                        }
                    })
                    .build()
                    .get();

        }else {
            Log.i(TAG, "onViewClicked: 错误错误");
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }


    private boolean checkForm(){
        final String name = editSignUpName.getText().toString();
        final String email = editSignUpEmail.getText().toString();
        final String phone = editSignUpPhone.getText().toString();
        final String password = editSignUpPassword.getText().toString();
        final String rePassword = editSignUpRePassword.getText().toString();

        boolean isPass = true;


        if (name.isEmpty()){
            editSignUpName.setError("请输入姓名");
            isPass = false;
        } else {
            editSignUpName.setError(null);
        }


        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editSignUpEmail.setError("邮箱格式错误");
            isPass = false;
        } else {
            editSignUpEmail.setError(null);
        }


        if (phone.isEmpty() || phone.length()<11){
            editSignUpPhone.setError("手机号码错误");
            isPass = false;
        } else {
            editSignUpPhone.setError(null);
        }

        if (password.isEmpty() || password.length()<6){
            editSignUpPassword.setError("密码长度不能少于6位");
            isPass = false;
        } else {
            editSignUpPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length()<6 || !rePassword.equals(password)){
            editSignUpPassword.setError("密码验证错误");
            isPass = false;
        } else {
            editSignUpRePassword.setError(null);
        }

        return isPass;




    }


}
