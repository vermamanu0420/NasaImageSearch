package com.example.nasaimagesearch.apiservice;

import com.example.nasaimagesearch.dependencyinjection.DaggerApiComponent;
import com.example.nasaimagesearch.model.ImageDetailModel;

import javax.inject.Inject;

import io.reactivex.Single;

public class NasaImageSearchService {

    private static NasaImageSearchService instance;

    @Inject
    public NasaImageSearchApi api;

    private NasaImageSearchService() {
        DaggerApiComponent.create().inject(this);
    }

    public static NasaImageSearchService getInstance() {
        if (instance == null) {
            instance = new NasaImageSearchService();
        }
        return instance;
    }

    public Single<ImageDetailModel> getImages(String searchString, String type, int page) {
        return api.getImages(searchString, type, page);
    }
}
