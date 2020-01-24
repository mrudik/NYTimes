package com.mrudik.nytimes;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.mrudik.nytimes.dagger.AppComponent;
import com.mrudik.nytimes.dagger.module.AppModule;
import com.mrudik.nytimes.dagger.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

public class NewsApp extends Application {
    private AppComponent appComponent;

    public NewsApp() {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        Stetho.initializeWithDefaults(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}