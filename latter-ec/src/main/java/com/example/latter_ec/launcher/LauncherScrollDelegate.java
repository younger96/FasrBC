package com.example.latter_ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.latte_core.app.AccountManager;
import com.example.latte_core.app.IUserChecker;
import com.example.latte_core.delegates.LatteDelegate;
import com.example.latte_core.ui.launcher.ILauncherListener;
import com.example.latte_core.ui.launcher.LauncherHolderCreator;
import com.example.latte_core.ui.launcher.OnLauncherFinishTag;
import com.example.latte_core.ui.launcher.ScrollLauncherTag;
import com.example.latte_core.util.storage.LattePreference;
import com.example.latter_ec.R;

import java.util.ArrayList;

public class LauncherScrollDelegate extends LauncherBaseDelegate implements OnItemClickListener {
    private static final String TAG = "LauncherScrollDelegate";

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTERGER  = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;
    


    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }


    /**
     * 初始化引导的滚动图控件
     */
    private void initBanner() {
        INTERGER.add(R.mipmap.launcher_01);
        INTERGER.add(R.mipmap.launcher_02);
        INTERGER.add(R.mipmap.launcher_03);
        INTERGER.add(R.mipmap.launcher_04);
        INTERGER.add(R.mipmap.launcher_05);
        mConvenientBanner.setPages(new LauncherHolderCreator(),INTERGER)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_forcus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);
    }

    @Override
    public void onItemClick(int position) {
        if (position == INTERGER.size()-1){
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否登录
            Log.i(TAG, "onItemClick: 登录登录");
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
}
