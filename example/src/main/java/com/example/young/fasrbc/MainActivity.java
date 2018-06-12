package com.example.young.fasrbc;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.widget.Toast;

import com.example.latte_core.activities.ProxyActivity;
import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.ui.launcher.ILauncherListener;
import com.example.latte_core.ui.launcher.OnLauncherFinishTag;
import com.example.latter_ec.launcher.LauncherDelegate;
import com.example.latter_ec.main.EcBottomDelegate;
import com.example.latter_ec.sign.ISignListener;
import com.example.latter_ec.sign.SignInDelegate;
import com.example.latter_ec.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity implements ISignListener, ILauncherListener {
    private static final String TAG = "MainActivity";
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
        startWithPop(new ExampleDelegate());
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(MainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Log.i(TAG, "onLauncherFinish: 用户已经登录");
                startWithPop(new EcBottomDelegate());
                break;
            case NOT_SIGNED:
                Log.i(TAG, "onLauncherFinish: 用户未登录");
                startWithPop(new SignInDelegate());
                break;
            default:
                break;
        }
    }
}
