package com.mrudik.nytimes.dagger;

import com.mrudik.nytimes.dagger.module.AppModule;
import com.mrudik.nytimes.dagger.module.DatabaseModule;
import com.mrudik.nytimes.dagger.module.NetworkModule;
import com.mrudik.nytimes.news.dagger.NewsSubComponent;

import dagger.Component;

@AppScope
@Component(modules = {AppModule.class, NetworkModule.class, DatabaseModule.class})
public interface AppComponent {
    NewsSubComponent createNewsSubComponent();
}