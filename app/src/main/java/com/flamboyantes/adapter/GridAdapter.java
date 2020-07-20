package com.flamboyantes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.flamboyantes.R;
import com.flamboyantes.model.products.AllNewPeoductsDataModel;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    public ArrayList <AllNewPeoductsDataModel> allNewProductArray;
    public Context context;

    public GridAdapter(ArrayList<AllNewPeoductsDataModel> allNewProductArray, Context context){
        this.allNewProductArray = allNewProductArray;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View gridAdapter=inflater.inflate(R.layout.adapter_grid_layout,parent,false);
        GridAdapter.ViewHolder viewHolder=new GridAdapter.ViewHolder(gridAdapter);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.album_name.setText(allNewProductArray.get(position).getName());
        holder.album_price.setText("$5");

        for (int i =0 ; i<allNewProductArray.get(position).getImages().size(); i++){

            Glide.with(context)
                    .load(allNewProductArray.get(position).getImages().get(i).getSrc())
                    .into(holder.album_img);
        }



    }

    @Override
    public int getItemCount() {
        return allNewProductArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView album_img;
        AppCompatTextView album_name, album_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            album_img = itemView.findViewById (R.id.album_img);
            album_name = itemView.findViewById (R.id.album_name);
            album_price = itemView.findViewById (R.id.album_price);
        }
    }
}
