package com.example.nasaimagesearch.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nasaimagesearch.R;
import com.example.nasaimagesearch.viewmodel.ImageListViewModel;

import java.util.ArrayList;

public class ImageListFragment extends Fragment {

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
    private ImageListAdapter adapter;

    private int currentPage = 1;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);
        ButterKnife.bind(this,view);
        viewModel = ViewModelProviders.of(getActivity()).get(ImageListViewModel.class);

        adapter = new ImageListAdapter(new ArrayList<>(), item -> {
            viewModel.setSelectedImage(item);
            ((MainActivity) getActivity()).replaceFragments(new ImageDetailFragment());
        });

        imagesList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        imagesList.setAdapter(adapter);

        observerViewModel();

        searchButton.setOnClickListener(v -> {
            viewModel.fetchImages(String.valueOf(searchTerm.getText()), "image", currentPage=1);
        });

        searchTerm.setOnEditorActionListener((v, actionId, event) -> {
            if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                searchButton.performClick();
            }
            return false;
        });

        imagesList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1)) {
                    currentPage++;
                    if (currentPage <= 100) {
                        Toast.makeText(getActivity(), "Loading more data", Toast.LENGTH_LONG).show();
                        viewModel.fetchImages(String.valueOf(searchTerm.getText()), "image", currentPage);
                    }
                    else
                        Toast.makeText(getActivity(),"No More Data Available",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    private void observerViewModel() {

        viewModel.imageList.observe(getViewLifecycleOwner(),items -> {
            listError.setVisibility(View.GONE);
            adapter.updateImages(items);
            loadingView.setVisibility(View.GONE);

            if (items == null || items.size() == 0){
                listError.setVisibility(View.VISIBLE);
                listError.setText(R.string.noDataFoundMsg);
            }
        });

        viewModel.imageLoadError.observe(getViewLifecycleOwner(), isError -> {
            if (isError != null) {
                listError.setVisibility(isError ? View.VISIBLE : View.GONE);
            }

        });
        viewModel.loading.observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });

    }
}