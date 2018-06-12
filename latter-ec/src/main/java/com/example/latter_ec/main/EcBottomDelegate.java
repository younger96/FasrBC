package com.example.latter_ec.main;

import android.graphics.Color;

import com.example.latte_core.bottom.BaseBottomDelegate;
import com.example.latte_core.bottom.BottomItemDelegate;
import com.example.latte_core.bottom.BottomTabBean;
import com.example.latte_core.bottom.ItemBuilder;
import com.example.latter_ec.main.cart.ShopDelegate;
import com.example.latter_ec.main.discover.DiscoverDelegate;
import com.example.latter_ec.main.index.IndexDelegate;
import com.example.latter_ec.main.personal.PersonalDelegate;
import com.example.latter_ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    public int setFirstItem() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }

    @Override
    public ItemBuilder setItems() {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new ShopDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new PersonalDelegate());
        return new ItemBuilder().addItems(items);
    }
}
