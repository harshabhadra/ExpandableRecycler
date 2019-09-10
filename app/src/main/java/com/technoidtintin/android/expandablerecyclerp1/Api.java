package com.technoidtintin.android.expandablerecyclerp1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    String baseUrl = "http://api.themoviedb.org/";

    @GET("/3/movie/popular/")
    Call<String>getMovieList(@Query("api_key")String api);
}
