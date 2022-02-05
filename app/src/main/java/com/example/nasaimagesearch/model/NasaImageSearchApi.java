package com.example.nasaimagesearch.model;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NasaImageSearchApi {

    @GET("search")
    Single<ImageDetailModel> getImages (
            @Query("q") String searchTerm,
            @Query("media_type") String dataType);
}
