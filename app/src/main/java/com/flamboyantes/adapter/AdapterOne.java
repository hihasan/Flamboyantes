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
import com.flamboyantes.model.ModelOne;

import java.util.concurrent.CopyOnWriteArrayList;

public class AdapterOne extends RecyclerView.Adapter<AdapterOne.ViewHolder> {
    public ModelOne[] modelOne;
    public Context context;

    public AdapterOne(ModelOne[] modelOne, Context context){
        this.modelOne = modelOne;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View menuAdapter=inflater.inflate(R.layout.adapter_one_layout,parent,false);
        AdapterOne.ViewHolder viewHolder=new AdapterOne.ViewHolder(menuAdapter);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.album_name.setText(modelOne[position].getName());
        holder.album_price.setText("$ "+modelOne[position].getAmmount());
        Glide.with(context)
                .load(modelOne[position].getImg())
                .into(holder.album_img);
    }

    @Override
    public int getItemCount() {
        return modelOne.length;
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
