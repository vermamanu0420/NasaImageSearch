package com.example.nasaimagesearch.dependencyinjection;

import com.example.nasaimagesearch.model.NasaImageSearchService;
import com.example.nasaimagesearch.viewmodel.ImageListViewModel;

import dagger.Component;

@Component(modules={ApiModule.class})
public interface ApiComponent {

    void inject(NasaImageSearchService service);

    void inject(ImageListViewModel viewModel);
}
