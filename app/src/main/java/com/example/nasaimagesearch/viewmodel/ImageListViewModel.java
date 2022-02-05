package com.example.nasaimagesearch.viewmodel;

import com.example.nasaimagesearch.model.ImageDetailModel;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ImageListViewModel extends ViewModel {

    public MutableLiveData<List<ImageDetailModel.Item>> imageList = new MutableLiveData<>();
    public MutableLiveData<Boolean> imageLoadError=new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading=new MutableLiveData<Boolean>();

    private void fetchImages(){

    }

}
