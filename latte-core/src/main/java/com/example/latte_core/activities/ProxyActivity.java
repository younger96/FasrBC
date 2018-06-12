package com.example.latte_core.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.example.latte_core.R;
import com.example.latte_core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class ProxyActivity extends SupportActivity{
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }


    private void initContainer(@Nullable Bundle savedInstanceState){
        @SuppressLint("RestrictedApi") final ContentFrameLayout contentFrameLayout = new ContentFrameLayout(this);
        contentFrameLayout.setId(R.id.delegate_container);
        setContentView(contentFrameLayout);
        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
