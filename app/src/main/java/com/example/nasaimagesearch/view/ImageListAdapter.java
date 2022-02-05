package com.example.nasaimagesearch.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nasaimagesearch.R;
import com.example.nasaimagesearch.model.ImageDetailModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {
    private List<ImageDetailModel.Item> imagesList;

    public ImageListAdapter(List<ImageDetailModel.Item> imagesList) {
        this.imagesList = imagesList;
    }

    public void updateImages(List<ImageDetailModel.Item> newImages) {
        imagesList.clear();
        imagesList.addAll(newImages);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.bind(imagesList.get(position));

    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;

        @BindView(R.id.imageTitle)
        TextView imageTitle;

        @BindView(R.id.imageCreatedDate)
        TextView imageCreatedDate;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(ImageDetailModel.Item imageItem) {
            imageTitle.setText(imageItem.data.get(0).imageTitle);
            imageCreatedDate.setText(imageItem.data.get(0).description);
           // Util.loadImage(countryImage, country.getFlag(), Util.getProgressDrawable(countryImage.getContext()));
        }
        
    }
    
    
}
