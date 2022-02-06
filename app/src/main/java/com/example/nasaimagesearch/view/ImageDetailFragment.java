package com.example.nasaimagesearch.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nasaimagesearch.R;
import com.example.nasaimagesearch.utils.Util;
import com.example.nasaimagesearch.viewmodel.ImageListViewModel;

public class ImageDetailFragment extends Fragment {

    @BindView(R.id.fullImage)
    ImageView fullImage;

    @BindView(R.id.imageDetailTitle)
    TextView imageDetailTitle;

    @BindView(R.id.imageDetailDescription)
    TextView imageDetailDescription;

    @BindView(R.id.imageDetailDate)
    TextView imageDetailDate;


    private ImageListViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_detail, container, false);
        ButterKnife.bind(this,view);
        viewModel = ViewModelProviders.of(getActivity()).get(ImageListViewModel.class);
        observerViewModel();
        return view;
    }

    private void observerViewModel() {

        viewModel.selectedImage.observe(getViewLifecycleOwner(),item -> {
            imageDetailTitle.setText(item.data.get(0).imageTitle);
            imageDetailDescription.setText(item.data.get(0).description);
            imageDetailDate.setText(Util.dateToStringConversion(item.data.get(0).date_created,"dd MMM yyyy"));
            Util.loadImageCenterFit(fullImage, item.links.get(0).href, Util.getProgressDrawable(fullImage.getContext()));
        });



    }
}