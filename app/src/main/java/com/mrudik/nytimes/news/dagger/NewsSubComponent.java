package com.mrudik.nytimes.news.dagger;

import com.mrudik.nytimes.news.NewsActivity;

import dagger.Subcomponent;

@NewsScope
@Subcomponent(modules = NewsModule.class)
public interface NewsSubComponent {
    void inject(NewsActivity activity);
}