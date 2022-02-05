package com.example.nasaimagesearch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nasaimagesearch.R;
import com.example.nasaimagesearch.model.ImageDetailModel;
import com.example.nasaimagesearch.viewmodel.ImageListViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imagesList)
    RecyclerView imagesList;

    @BindView(R.id.list_error)
    TextView listError;

    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    private ImageListViewModel viewModel;
    private ImageListAdapter adapter = new ImageListAdapter(new ArrayList<>());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(ImageListViewModel.class);


        imagesList.setLayoutManager(new LinearLayoutManager(this));
        imagesList.setAdapter(adapter);


        observerViewModel();
        viewModel.fetchImages("moon","image");

    }

    private void observerViewModel() {
        viewModel.imageList.observe(this,items -> {
            if (items != null){
                adapter.updateImages(items);
                loadingView.setVisibility(View.GONE);
            }
        });

        viewModel.imageLoadError.observe(this, isError -> {
            if (isError != null) {
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }

        });
        viewModel.loading.observe(this, isLoading -> {
            if (isLoading != null) ;
            listError.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            //countriesList.setVisibility(View.GONE);
        });

    }
}
