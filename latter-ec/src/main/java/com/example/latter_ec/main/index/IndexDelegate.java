package com.example.latter_ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.latte_core.bottom.BottomItemDelegate;
import com.example.latte_core.ui.refresh.RefreshHandler;
import com.example.latter_ec.R;
import com.example.latter_ec.R2;
import com.joanzapata.iconify.widget.IconTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class IndexDelegate extends BottomItemDelegate {
    @BindView(R2.id.rv_index)
    RecyclerView rvIndex;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R2.id.icon_index_scan)
    IconTextView iconIndexScan;
    @BindView(R2.id.et_search_view)
    AppCompatEditText etSearchView;
    @BindView(R2.id.icon_index_message)
    IconTextView iconIndexMessage;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar;


    private RefreshHandler mRefreshHandler = null;


    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefresh();
    }

    private void initRefresh() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        mSwipeRefreshLayout.setProgressViewOffset(true,120,300);
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = new RefreshHandler(mSwipeRefreshLayout);
    }


}
