package com.example.nasaimagesearch.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class ImageDetailModel {

     public Collection collection;

     public class Collection{
          @SerializedName("items")
          public ArrayList<Item> items;
     }

     public class Item{
          @SerializedName("data")
          public ArrayList<ImageData> data;
          @SerializedName("links")
          public ArrayList<Link> links;

     }

     public class ImageData{
          @SerializedName("description")
          public String description;
          @SerializedName("title")
          public String imageTitle;
          @SerializedName("date_created")
          public Date date_created;

     }

     public class Link{
          @SerializedName("href")
          public String href;
     }

     public ImageDetailModel(Collection collection) {
          this.collection = collection;
     }

     public Collection getCollection() {
          return collection;
     }

     public void setCollection(Collection collection) {
          this.collection = collection;
     }
}
