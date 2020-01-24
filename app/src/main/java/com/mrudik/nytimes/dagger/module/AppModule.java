package com.mrudik.nytimes.dagger.module;

import android.content.Context;

import com.mrudik.nytimes.dagger.AppScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Provides
    @AppScope
    Context provideApplicationContext() {
        return context;
    }
}