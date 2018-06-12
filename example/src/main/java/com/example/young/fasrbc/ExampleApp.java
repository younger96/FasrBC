package com.example.young.fasrbc;


import android.app.Application;

import com.example.latte_core.app.Latte;
import com.example.latte_core.net.iterceptor.DebugInterceptor;
import com.example.latter_ec.database.DatabaseManager;
import com.example.latter_ec.icon.FontECMoudule;
import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.fonts.FontAwesomeModule;


public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontECMoudule())
                .withIcon(new FontAwesomeModule())
                .withApiHost("http://127.0.0.1/")
                .withLoaderDelayed(1000)
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .withInterceptor(new DebugInterceptor("user_profile",R.raw.user_profile))
                .configure();

        //测试数据库的工具
//        initStetho();

        DatabaseManager.getInstance().init(this);

    }


    private void initStetho(){
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(
                                Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

}
