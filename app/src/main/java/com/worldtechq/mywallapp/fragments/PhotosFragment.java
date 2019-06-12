package com.worldtechq.mywallapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.worldtechq.mywallapp.Activity.Models.Adapter.PhotosAdapter;
import com.worldtechq.mywallapp.Activity.Models.Photo;
import com.worldtechq.mywallapp.Activity.webservice.ApiInterface;
import com.worldtechq.mywallapp.Activity.webservice.ServiceGenerator;
import com.worldtechq.mywallapp.R;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosFragment extends Fragment {
    public final  static String TAG=PhotosFragment.class.getSimpleName ();
    @BindView (R.id.fragmentphotospb)
    ProgressBar progressBar;
    @BindView (R.id.fragment_photosrv)
    RecyclerView recyclerView;

      private PhotosAdapter photosAdapter;
    List<Photo> photos=new ArrayList<> ();
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate (R.layout.fragment_photos,container,false);
        unbinder= ButterKnife.bind (this,view);
        //recyclerview
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager (getActivity ());
        recyclerView.setLayoutManager (linearLayoutManager);
        photosAdapter=new PhotosAdapter (getActivity (),photos);
        recyclerView.setAdapter (photosAdapter);
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        getPhotos ();
        return view;
    }

    private void getPhotos(){

        ApiInterface apiInterface= ServiceGenerator.createService (ApiInterface.class);
        Call<List<Photo>> call=apiInterface.getPhotos ();
        call.enqueue (new Callback<List<Photo>> ( ) {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response.isSuccessful ())
                {  Log.d(TAG, "Loading successfully, size: " + response.body().size());
                    photos.addAll (response.body ());
                    photosAdapter.notifyDataSetChanged ();
                }
                else {
                    Log.e (TAG,"fail in" + response.message ());
                }
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.e (TAG,"fail"+ t.getMessage ());
                 progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView ( );
        unbinder.unbind ();
    }
}
