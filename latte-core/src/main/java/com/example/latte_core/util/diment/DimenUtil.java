package com.example.latte_core.util.diment;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.latte_core.app.Latte;

/**
 * Created by young on 18-3-22.
 */

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }


    public static int getScreenHeight(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
