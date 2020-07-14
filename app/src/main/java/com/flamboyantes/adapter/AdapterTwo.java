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
import com.flamboyantes.model.ModelTwo;

public class AdapterTwo extends RecyclerView.Adapter<AdapterTwo.ViewHolder> {

    public ModelTwo[] modelTwo;
    public Context context;

    public AdapterTwo(ModelTwo[] modelTwo, Context context) {
        this.modelTwo = modelTwo;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View menuAdapter=inflater.inflate(R.layout.adapter_two_layout,parent,false);
        AdapterTwo.ViewHolder viewHolder=new AdapterTwo.ViewHolder(menuAdapter);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.album_name.setText(modelTwo[position].getName());
        holder.album_price.setText("$ "+modelTwo[position].getAmmount());
        Glide.with(context)
                .load(modelTwo[position].getImg())
                .into(holder.album_img);
    }

    @Override
    public int getItemCount() {
        return modelTwo.length;
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
