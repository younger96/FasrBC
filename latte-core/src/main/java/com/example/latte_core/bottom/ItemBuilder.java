package com.example.latte_core.bottom;

import java.util.LinkedHashMap;

/**
 * Builder模式，建造bean和frag对应的集合
 */
public class ItemBuilder {
    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean tabBean,BottomItemDelegate itemDelegate){
        ITEMS.put(tabBean,itemDelegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean,BottomItemDelegate> build(){
        return ITEMS;
    }

}
