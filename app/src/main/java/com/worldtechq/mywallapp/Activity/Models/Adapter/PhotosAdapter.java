package com.worldtechq.mywallapp.Activity.Models.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.worldtechq.mywallapp.Activity.FullscreenPhotoActivity;
import com.worldtechq.mywallapp.Activity.Models.Photo;
import com.worldtechq.mywallapp.R;
import com.worldtechq.mywallapp.utils.GlideApp;
import com.worldtechq.mywallapp.utils.SqaureImage;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {
    private final String TAG = PhotosAdapter.class.getSimpleName();
    private Context context;
    private List<Photo> photos;

    public PhotosAdapter(Context context, List<Photo> photos) {
        this.context = context;
        this.photos = photos;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.circleimg)
        CircleImageView userAvatar;
        @BindView(R.id.item_text)
        TextView username;
        @BindView(R.id.itemphotosquare)
        SqaureImage sqaureImage;
        @BindView (R.id.item_photo_layout)
        FrameLayout frameLayout;

        public ViewHolder(View itemView) {
            super (itemView);
            ButterKnife.bind (this,itemView);
        }
        @OnClick(R.id.item_photo_layout)
        public void handleOnClick(){
            Log.d(TAG, "dmmmmmmmmm");
            int position = getAdapterPosition();
            String photoId = photos.get(position).getId();
            Intent intent = new Intent(context, FullscreenPhotoActivity.class);
            intent.putExtra("photoId", photoId);
            context.startActivity (intent);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from (viewGroup.getContext ( )).inflate (R.layout.item_photos, viewGroup, false);
        return new ViewHolder (view);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Photo photo = photos.get (i);

            viewHolder.username.setText (photo.getUser ( ).getUsername ( ));


        GlideApp.with (context)
                .load (photo.getUrl ( ).getRegular ( ))
                .placeholder (R.drawable.ic_menu_camera)
                .override (600, 600)
                .into (viewHolder.sqaureImage);
        GlideApp.with (context)
                .load (photo.getUser ( ).getProfileImage ( ).getSmall ( ))
                .into (viewHolder.userAvatar);


    }

    @Override
    public int getItemCount() {
        return photos.size ( );
    }


}
