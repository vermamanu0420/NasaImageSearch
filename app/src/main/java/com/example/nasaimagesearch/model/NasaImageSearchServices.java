package com.example.nasaimagesearch.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NasaImageSearchServices {

    private static final String BASE_URL ="https://images-api.nasa.gov/";

    private static NasaImageSearchServices instance;

    private NasaImageSearchApi api =  new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(NasaImageSearchApi.class);

    private NasaImageSearchServices() {

    }

    public static NasaImageSearchServices getInstance(){
        if (instance == null) {
            instance = new NasaImageSearchServices();
        }
        return instance;
    }

    public Single<ImageDetailModel> getImages(String searchString, String type){
        return api.getImages(searchString, type);
    }
}
