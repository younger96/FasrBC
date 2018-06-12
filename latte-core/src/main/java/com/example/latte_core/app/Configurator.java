package com.example.latte_core.app;


import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;

/**
 * 配置全局变量  单例
 */
public class Configurator {
    private static final HashMap<Object,Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();

    private static final ArrayList<Interceptor> INTERCEPTORS  =  new ArrayList<>();
    private static final Handler HANDLDR  = new Handler();


    private Configurator(){
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(),false);

        //构造方法实例化handler
        LATTE_CONFIGS.put(ConfigKeys.HANDLER.name(),HANDLDR);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final HashMap<Object,Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /**
     * 根据key获取配置项
     *
     * @param key
     * @param <T>
     * @return
     */
    final <T> T getConfiguration(Object key)
    {
        checkConfiguration();
        final Object value = LATTE_CONFIGS.get(key);
        if (value == null)
        {
            throw new NullPointerException(key.toString() + " IS NULL");
        }
        return (T) LATTE_CONFIGS.get(key);
    }


    //静态内部类构建单例
    private static class Holder{
        private static final Configurator INSTANCE  = new Configurator();
    }

    public final void configure(){
        initIcons();
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY.name(),true);
    }

    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigKeys.API_HOST.name(),host);
        return this;
    }

    //初始化图标
    private void initIcons(){
        if (ICONS.size() > 0 ){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 设置loading延时
     *
     * @param delayed
     * @return
     */
    public final Configurator withLoaderDelayed(long delayed)
    {
        LATTE_CONFIGS.put(ConfigKeys.LOADER_DELAYED.name(), delayed);
        return this;
    }


    //添加拦截器
    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }

    //添加拦截器
    public final Configurator withInterceptors(List<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR.name(),INTERCEPTORS);
        return this;
    }

    //检查变量是否已经配置
    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("YoungLatter Configuration is not ready,call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigKeys> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

}
