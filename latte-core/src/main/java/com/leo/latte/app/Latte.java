package com.leo.latte.app;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;

import java.util.WeakHashMap;

/**
 * Created by leo
 */
public final class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static WeakHashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Application getApplication() {
        return (Application) Configurator.getInstance().getLatteConfigs().get(ConfigType.APPLICATION_CONTEXT.name());
    }

}
