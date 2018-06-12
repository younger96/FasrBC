package com.example.latter_ec.main.personal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.latte_core.bottom.BottomItemDelegate;
import com.example.latter_ec.R;

public class PersonalDelegate extends BottomItemDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_personal;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
