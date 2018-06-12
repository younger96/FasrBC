package com.example.latte_core.app;

import com.example.latte_core.util.storage.LattePreference;

/**
 * 判断用户的登录信息是否在本机保存
 */
public class AccountManager {

    private enum SignTag{
        SIGN_TAG
    }

    //设置用户登录状态
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    //获取用户登录状态
    private static boolean getSignState(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    //判断用户账号状态
    public static void checkAccount(IUserChecker iUserChecker){
        if(getSignState()){
            iUserChecker.onSignIn();
        }else {
            iUserChecker.onNotSignIn();
        }
    }
}
