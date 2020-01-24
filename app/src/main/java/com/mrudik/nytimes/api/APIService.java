package com.mrudik.nytimes.api;

import com.mrudik.nytimes.BuildConfig;
import com.mrudik.nytimes.api.model.MostViewedArticles;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIService {
    @GET("/svc/mostpopular/v2/viewed/{period}/?api-key=" + BuildConfig.API_KEY)
    Observable<MostViewedArticles> getMostViewedArticles(@Path("period") int period);
}