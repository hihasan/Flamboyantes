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
import com.flamboyantes.model.cartfavoriteresponse.ShoppingCart;
import com.flamboyantes.util.Singleton;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private List <ShoppingCart> cartmodel;

    public CartAdapter(List<ShoppingCart> cartmodel, Context context){
        this.cartmodel = cartmodel;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View listAdapter=inflater.inflate(R.layout.adapter_list_close_layout,parent,false);
        CartAdapter.ViewHolder viewHolder=new CartAdapter.ViewHolder(listAdapter);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        double total =0;
        for (int i =0 ;i<cartmodel.get(position).getProduct().getImages().size(); i++){
            Glide.with(context)
                    .load(cartmodel.get(position).getProduct().getImages().get(i).getSrc())
                    .into(holder.album_img);

        }

        holder.album_name.setText(cartmodel.get(position).getProduct().getName());
        holder.album_price.setText("$"+" "+ cartmodel.get(position).getProduct().getPrice());






    }

    @Override
    public int getItemCount() {
        return cartmodel.size();
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
