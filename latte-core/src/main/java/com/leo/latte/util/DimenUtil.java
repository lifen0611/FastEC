package com.leo.latte.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.leo.latte.app.Latte;

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight() {
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.heightPixels;
    }
}
