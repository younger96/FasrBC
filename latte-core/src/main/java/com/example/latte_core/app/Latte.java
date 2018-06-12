package com.example.latte_core.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 *
 */
public class Latte {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    /**
     * 获取Configurator单例
     *
     * @return
     */
    public static Configurator getConfigurator()
    {
        return Configurator.getInstance();
    }

    /**
     * 获取配置项
     *
     * @param key not null
     * @param <T>
     * @return
     */
    public static <T> T getConfiguration(Object key)
    {
        return getConfigurator().getConfiguration(key);
    }

    public static HashMap<Object,Object> getConfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }


    public static Context getApplicationContext(){
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT.name());
    }


    /**
     * 获取全局handler
     *
     * @return
     */
    public static Handler getHandler()
    {
        return getConfiguration(ConfigKeys.HANDLER.name());
    }
}
