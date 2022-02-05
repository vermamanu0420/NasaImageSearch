package com.example.nasaimagesearch.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nasaimagesearch.R;
import com.example.nasaimagesearch.model.ImageDetailModel;
import com.example.nasaimagesearch.viewmodel.ImageListViewModel;

import java.io.Console;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imagesList)
    RecyclerView imagesList;

    @BindView(R.id.list_error)
    TextView listError;

    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    @BindView(R.id.searchButton)
    Button searchButton;

    @BindView(R.id.searchTextview)
    EditText searchTerm;


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

        searchButton.setOnClickListener(v -> {
           viewModel.fetchImages(String.valueOf(searchTerm.getText()), "image");
        });

        searchTerm.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                searchButton.performClick();
            }
            return false;
        });

    }

    private void observerViewModel() {

        viewModel.imageList.observe(this,items -> {
            listError.setVisibility(View.GONE);
            adapter.updateImages(items);
            loadingView.setVisibility(View.GONE);

            if (items == null || items.size() == 0){
                listError.setVisibility(View.VISIBLE);
                listError.setText(R.string.noDataFoundMsg);
            }
        });

        viewModel.imageLoadError.observe(this, isError -> {
            if (isError != null) {
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }

        });
        viewModel.loading.observe(this, isLoading -> {
            if (isLoading != null) {
                 loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });

    }
}
