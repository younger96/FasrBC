package com.example.latter_ec.icon;

import com.joanzapata.iconify.Icon;

public enum  EcIcons implements Icon {
    icon_test('\ue65b');

    private char mChar;

    EcIcons(char aChar) {
        mChar = aChar;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return mChar;
    }
}
