package com.flamboyantes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.flamboyantes.R;
import com.flamboyantes.listener.ProductsListener;
import com.flamboyantes.model.products.AllNewPeoductsDataModel;
import com.flamboyantes.util.Singleton;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    public ArrayList <AllNewPeoductsDataModel> allNewProductArray;
    public ProductsListener listener;
    public Context context;

    public GridAdapter(ArrayList<AllNewPeoductsDataModel> allNewProductArray, ProductsListener listener, Context context){
        this.allNewProductArray = allNewProductArray;
        this.listener = listener;
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
        holder.album_price.setText("$"+" "+allNewProductArray.get(position).getPrice());

        for (int i =0 ; i<allNewProductArray.get(position).getImages().size(); i++){

            Glide.with(context)
                    .load(allNewProductArray.get(position).getImages().get(i).getSrc())
                    .into(holder.album_img);
        }

        holder.grid_relative_id.setOnClickListener(view -> {
            Singleton.getInstance().setId(allNewProductArray.get(position).getId());
            for (int i =0 ; i<allNewProductArray.get(position).getImages().size(); i++){

                Singleton.getInstance().setImage(allNewProductArray.get(position).getImages().get(i).getSrc());
            }

            Singleton.getInstance().setPrice(allNewProductArray.get(position).getPrice());
            Singleton.getInstance().setName(allNewProductArray.get(position).getName());
            Singleton.getInstance().setUpdate_on_utc(allNewProductArray.get(position).getUpdatedOnUtc());
            Singleton.getInstance().setShort_description(allNewProductArray.get(position).getShortDescription());
           listener.onPositionNumber(Singleton.getInstance().getId(), position);

           System.out.println(Singleton.getInstance().getId());
           System.out.println(position);
        });

    }

    @Override
    public int getItemCount() {
        return allNewProductArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView album_img;
        AppCompatTextView album_name, album_price;
        RelativeLayout grid_relative_id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            album_img = itemView.findViewById (R.id.album_img);
            album_name = itemView.findViewById (R.id.album_name);
            album_price = itemView.findViewById (R.id.album_price);
            grid_relative_id = itemView.findViewById (R.id.grid_relative_id);
        }
    }
}
