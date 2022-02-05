package com.example.nasaimagesearch.viewmodel;

import com.example.nasaimagesearch.model.ImageDetailModel;
import com.example.nasaimagesearch.model.NasaImageSearchServices;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ImageListViewModel extends ViewModel {

    public MutableLiveData<List<ImageDetailModel.Item>> imageList = new MutableLiveData<List<ImageDetailModel.Item>>();
    public MutableLiveData<Boolean> imageLoadError=new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading=new MutableLiveData<Boolean>();

    private NasaImageSearchServices imageSearchServices = NasaImageSearchServices.getInstance();

    private CompositeDisposable disposable = new CompositeDisposable();

    public void fetchImages(String searchTerm, String type){
        loading.setValue(true);
        disposable.add(
                imageSearchServices.getImages(searchTerm, type)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ImageDetailModel>() {
                            @Override
                            public void onSuccess(@NonNull ImageDetailModel imageCollection) {
                                imageList.setValue(imageCollection.getCollection().items);

                                imageLoadError.setValue(false);
                                loading.setValue(false);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                imageLoadError.setValue(true);
                                loading.setValue(false);
                                e.printStackTrace();
                            }
                        })

        );
    }

}
