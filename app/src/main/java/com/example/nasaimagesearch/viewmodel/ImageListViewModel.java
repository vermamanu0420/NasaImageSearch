package com.example.nasaimagesearch.viewmodel;

import com.example.nasaimagesearch.dependencyinjection.DaggerApiComponent;
import com.example.nasaimagesearch.model.ImageDetailModel;
import com.example.nasaimagesearch.model.NasaImageSearchService;

import java.util.List;

import javax.inject.Inject;

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
    public MutableLiveData<ImageDetailModel.Item> selectedImage = new MutableLiveData<ImageDetailModel.Item>();

    @Inject
    public NasaImageSearchService imageSearchServices;

    private CompositeDisposable disposable = new CompositeDisposable();

    public ImageListViewModel() {
        super();
        DaggerApiComponent.create().inject(this);
    }
    public void fetchImages(String searchTerm, String type){
        loading.setValue(true);
        disposable.add(
                imageSearchServices.getImages(searchTerm, type)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ImageDetailModel>() {
                            @Override
                            public void onSuccess(@NonNull ImageDetailModel imageCollection) {
                                imageLoadError.setValue(false);
                                loading.setValue(false);

                                imageList.setValue(imageCollection.getCollection().items);
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

    public void setSelectedImage(ImageDetailModel.Item item){
        selectedImage.setValue(item);
    }

}
