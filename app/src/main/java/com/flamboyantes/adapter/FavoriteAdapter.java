package com.flamboyantes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.flamboyantes.R;
import com.flamboyantes.model.CartModel;
import com.flamboyantes.model.FavoriteModel;
import com.flamboyantes.model.cartfavoriteresponse.ShopingCartModel;
import com.flamboyantes.model.cartfavoriteresponse.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private Context context;
    private List<ShoppingCart> favoriteModel;

    public FavoriteAdapter(List<ShoppingCart> favoriteModel, Context context){
        this.favoriteModel = favoriteModel;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View listAdapter=inflater.inflate(R.layout.adapter_list_close_layout,parent,false);
        FavoriteAdapter.ViewHolder viewHolder=new FavoriteAdapter.ViewHolder(listAdapter);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        for (int i =0 ;i<favoriteModel.get(position).getProduct().getImages().size(); i++){
            Glide.with(context)
                    .load(favoriteModel.get(position).getProduct().getImages().get(i).getSrc())
                    .into(holder.album_img);
        }

        holder.album_name.setText(favoriteModel.get(position).getProduct().getName());
        holder.album_price.setText("$"+" "+ favoriteModel.get(position).getProduct().getPrice());

    }

    @Override
    public int getItemCount() {
        return favoriteModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView album_img;
        AppCompatTextView album_name, album_price;
        RelativeLayout list_relative_id;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            album_img = itemView.findViewById (R.id.album_img);
            album_name = itemView.findViewById (R.id.album_name);
            album_price = itemView.findViewById (R.id.album_price);
            list_relative_id = itemView.findViewById (R.id.list_relative_id);
        }
    }
}
