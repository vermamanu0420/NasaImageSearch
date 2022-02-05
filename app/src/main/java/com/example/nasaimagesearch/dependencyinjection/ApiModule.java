package com.example.nasaimagesearch.dependencyinjection;

import com.example.nasaimagesearch.model.NasaImageSearchApi;
import com.example.nasaimagesearch.model.NasaImageSearchService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    public static final String BASE_URL ="https://images-api.nasa.gov/";

    @Provides
    public NasaImageSearchApi provideNasaImageSearchApi(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(NasaImageSearchApi.class);

    }

    @Provides
    public NasaImageSearchService provideNasaImageSearchService(){
        return  NasaImageSearchService.getInstance();
    }
}


