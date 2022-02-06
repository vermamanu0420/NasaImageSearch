package com.example.nasaimagesearch.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.nasaimagesearch.R;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

public class Util  {

    public static void loadImageCenterCrop (ImageView view, String url, CircularProgressDrawable progressDrawable) {

        RequestOptions options= new RequestOptions()
                .placeholder(progressDrawable)
                .error(R.mipmap.ic_launcher_round);
        Glide.with(view.getContext())
                .setDefaultRequestOptions(options)
                .load(url)
                .centerCrop()
                .into(view);
    }

    public static void loadImageCenterFit (ImageView view, String url, CircularProgressDrawable progressDrawable) {
        RequestOptions options= new RequestOptions()
                .placeholder(progressDrawable)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(view.getContext())
                .setDefaultRequestOptions(options)
                .load(url)
                .fitCenter()
                .into(view);
    }

    public static CircularProgressDrawable getProgressDrawable(Context context){
        CircularProgressDrawable progressDrawable = new CircularProgressDrawable(context);
        progressDrawable.setStrokeWidth(10f);
        progressDrawable.setCenterRadius(50f);
        progressDrawable.start();
        return progressDrawable;

    }

    public static String dateToStringConversion(Date date, String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);

    }
}
