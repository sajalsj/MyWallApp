package com.worldtechq.mywallapp.Activity.webservice;

import com.worldtechq.mywallapp.Activity.Models.Collection;
import com.worldtechq.mywallapp.Activity.Models.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
      @GET("photos")
      Call<List<Photo>> getPhotos();

      @GET("collections/featured")
      Call<List<Collection>> getCollections();

      @GET("collections/{id}")
      Call<Collection> getInformationOfCollection(@Path("id") int id);

      @GET("collections/{id}/photos")
      Call<List<Photo>> getPhotosOfCollection(@Path("id") int id);

      @GET("photos/{id}")
      Call<Photo> getPhoto(@Path("id") String id);


}
