package com.example.latte_core.bottom;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.latte_core.R;
import com.example.latte_core.R2;
import com.example.latte_core.delegates.LatteDelegate;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 基类 底部导航栏
 */
public abstract class BaseBottomDelegate extends LatteDelegate implements View.OnClickListener {

    private final List<BottomTabBean> TAB_BEANS = new ArrayList<>();
    private final List<BottomItemDelegate> ITEM_DELEGATES = new ArrayList<>();
    private final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    @BindView(R2.id.bottom_bar)
    LinearLayoutCompat bottomBar;


    //当前的显示的item
    private int mCurrentItem = 0;
    //进入app的时候第一个显示的item
    private int mFirstItem = 0;
    //tab选中的颜色
    private int mClickColor = Color.RED;

    //设置第一个进入的item
    public abstract int setFirstItem();

    //设置选中的颜色
    @ColorInt
    public abstract int setClickColor();

    //设置数据
//    public abstract LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder);

    //设置数据
    public abstract ItemBuilder setItems();

    @Override
    public Object setLayout() {
        return R.layout.delegate_bottom;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirstItem = setFirstItem();
        if (setClickColor()!=0){
            mClickColor = setClickColor();
        }

        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items =  setItems().build();
        ITEMS.putAll(items);

        //添加数据
        for (Map.Entry<BottomTabBean,BottomItemDelegate> entry:ITEMS.entrySet()){
            final BottomTabBean bean = entry.getKey();
            final BottomItemDelegate bottomItemDelegate  = entry.getValue();
            TAB_BEANS.add(bean);
            ITEM_DELEGATES.add(bottomItemDelegate);
        }
    }


    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        final int size = ITEMS.size();
        for (int i=0;i<size;i++){
            LayoutInflater.from(getContext()).inflate(R.layout.bottom_item_icon_text_layout,bottomBar);

            //设置每个item的点击事件
            final RelativeLayout item = (RelativeLayout) bottomBar.getChildAt(i);
            item.setTag(i);
            item.setOnClickListener(this);

            //绑定控件
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView tvTitle = (AppCompatTextView) item.getChildAt(1);

            //初始化数据
            final BottomTabBean bean = TAB_BEANS.get(i);
            itemIcon.setText(bean.getICON());
            tvTitle.setText(bean.getTITLE());

            //设置第一个的点击颜色
            if (i==mFirstItem){
                itemIcon.setTextColor(mClickColor);
                tvTitle.setTextColor(mClickColor);
            }
        }


        // ??? 添加我们的Fragment到根Fragment中
        final SupportFragment[] delegateArray = ITEM_DELEGATES.toArray(new SupportFragment[size]);
        loadMultipleRootFragment(R.id.bottom_bar_delegate_container, mFirstItem, delegateArray);
    }

    //重置默认颜色
    private void resetColor()
    {
        final int count = bottomBar.getChildCount();
        for (int i = 0; i < count; i++)
        {
            final RelativeLayout item = (RelativeLayout) bottomBar.getChildAt(i);
            final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
            final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
            itemIcon.setTextColor(Color.GRAY);
            itemTitle.setTextColor(Color.GRAY);
        }
    }


    @Override
    public void onClick(View view)
    {
        final int tag = (int) view.getTag();
        resetColor();
        final RelativeLayout item = (RelativeLayout) view;
        final IconTextView itemIcon = (IconTextView) item.getChildAt(0);
        final AppCompatTextView itemTitle = (AppCompatTextView) item.getChildAt(1);
        itemIcon.setTextColor(mClickColor);
        itemTitle.setTextColor(mClickColor);

        showHideFragment(ITEM_DELEGATES.get(tag), ITEM_DELEGATES.get(mCurrentItem));
        mCurrentItem = tag;
    }

}
