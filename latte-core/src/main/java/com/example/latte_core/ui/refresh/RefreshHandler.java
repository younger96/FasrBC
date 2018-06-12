package com.example.latte_core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;

import com.example.latte_core.app.Latte;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener {
    private final SwipeRefreshLayout REFRESH_LAYOUT;

    public RefreshHandler(SwipeRefreshLayout REFRESH_LAYOUT) {
        this.REFRESH_LAYOUT = REFRESH_LAYOUT;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }

    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行网络操作
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);
    }

    @Override
    public void onRefresh() {
        refresh();
    }
}
