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

import de.hdodenhof.circleimageview.CircleImageView;

public class ListRecyclerAdapter extends RecyclerView.Adapter<ListRecyclerAdapter.ViewHolder> {

    public ArrayList<AllNewPeoductsDataModel> allNewProductArray;
    public Context context;

    public ListRecyclerAdapter(ArrayList<AllNewPeoductsDataModel> allNewProductArray, Context context){
        this.allNewProductArray = allNewProductArray;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View listAdapter=inflater.inflate(R.layout.adapter_list_layout,parent,false);
        ListRecyclerAdapter.ViewHolder viewHolder=new ListRecyclerAdapter.ViewHolder(listAdapter);
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
        CircleImageView album_img;
        AppCompatTextView album_name, album_price;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            album_img = itemView.findViewById (R.id.album_img);
            album_name = itemView.findViewById (R.id.album_name);
            album_price = itemView.findViewById (R.id.album_price);
        }
    }
}
