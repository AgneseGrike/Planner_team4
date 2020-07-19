package com.example.Planner_team4.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?appid=8861ad406215ddaa471df74095bca17c&units=metric")
    Call<Example> getWeatherData(@Query("q") String name);

}
