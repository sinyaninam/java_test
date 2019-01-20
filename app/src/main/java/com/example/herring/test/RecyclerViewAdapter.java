package com.example.herring.test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> mPhotoDescriptions = new ArrayList<>();
    private ArrayList<String> mPhotos = new ArrayList<>();
    private ArrayList<String> mPhotoIDs = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mPhotoDescriptions, ArrayList<String> mPhotos, ArrayList<String> mPhotoIDs, Context mContext) {
        this.mPhotoDescriptions = mPhotoDescriptions;
        this.mPhotos = mPhotos;
        this.mContext = mContext;
        this.mPhotoIDs = mPhotoIDs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mPhotos.get(position))
                .into(viewHolder.photo);

        viewHolder.photoDescription.setText(mPhotoDescriptions.get(position));
        viewHolder.photoID.setText(mPhotoIDs.get(position));
    }

    @Override
    public int getItemCount() {
        return mPhotoDescriptions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView photo;
        TextView photoID;
        TextView photoDescription;
        ConstraintLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
            photoDescription = itemView.findViewById(R.id.photo_description);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            photoID = itemView.findViewById(R.id.photo_id);
        }
    }
}
