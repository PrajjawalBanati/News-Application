package com.example.basicnewsapplication.api;

import com.example.basicnewsapplication.models.News;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("everything")
    Call<News> getNews(@Query("sources") String sources,@Query("apiKey") String apiKey);
}
