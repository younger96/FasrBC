package com.example.latte_core.ui.recycler;

import java.util.LinkedHashMap;

/**
 * Created by gaoyy on 2018/1/16 0016.
 */

public class MultipleEntityBuilder
{

    private static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();


    public MultipleEntityBuilder()
    {
        FIELDS.clear();
    }

    static MultipleEntityBuilder builder()
    {
        return new MultipleEntityBuilder();
    }

    public MultipleEntityBuilder setItemType(int itemType)
    {
        FIELDS.put(MultipleFields.ITEM_TYPE, itemType);
        return this;
    }

    public final MultipleEntityBuilder setField(Object key, Object value)
    {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleEntityBuilder setFields(LinkedHashMap<?, ?> map)
    {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build()
    {
        return new MultipleItemEntity(FIELDS);
    }
}