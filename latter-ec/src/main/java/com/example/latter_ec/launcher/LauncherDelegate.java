package com.example.latter_ec.launcher;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.latte_core.app.AccountManager;
import com.example.latte_core.app.IUserChecker;
import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.ui.launcher.OnLauncherFinishTag;
import com.example.latte_core.ui.launcher.ScrollLauncherTag;
import com.example.latte_core.util.storage.LattePreference;
import com.example.latte_core.util.timer.BaseTimerTask;
import com.example.latte_core.util.timer.ITimerListener;
import com.example.latter_ec.R;
import com.example.latter_ec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

public class LauncherDelegate extends LauncherBaseDelegate implements ITimerListener{
    private static final String TAG = "LauncherDelegate";

    private Timer mTimer = null;
    private int mCount= 5;

    @BindView(R2.id.tv_launcher_delegate)
    AppCompatTextView mTVTimer ;

    @OnClick(R2.id.tv_launcher_delegate)
    void onClickTimerView(){
        if (mTimer != null){
            mTimer.cancel();
            mTimer = null;
            checkShowScroll();
        }
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }

    /**
     * 判断滑动导航启动页面是否显示
     */
    private void checkShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            start(new LauncherScrollDelegate());
        }else {
            //检查用户是否登录了App
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });

        }

    }


    @Override
    public void onTimer() {
        getProxyActivty().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTVTimer != null){
                    mTVTimer.setText(MessageFormat.format("跳过\n{0}",mCount));
                    mCount--;
                    if (mCount < 0){
                        if (mTimer != null){
                            mTimer.cancel();
                            mTimer = null;
                            checkShowScroll();
                        }
                    }
                }
            }
        });
    }
}
