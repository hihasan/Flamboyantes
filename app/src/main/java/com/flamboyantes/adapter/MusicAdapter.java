package com.flamboyantes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.flamboyantes.R;
import com.flamboyantes.model.products.Downloaditem;
import com.flamboyantes.util.Constants;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private ArrayList<Downloaditem> downloaditem;
    private Context context;

    public MusicAdapter(ArrayList<Downloaditem> downloaditem, Context context){
        this.downloaditem = downloaditem;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View listAdapter=inflater.inflate(R.layout.adapter_music_list_layout,parent,false);
        MusicAdapter.ViewHolder viewHolder=new MusicAdapter.ViewHolder(listAdapter);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.album_name.setText(downloaditem.get(position).getName());
        holder.album_iv.setOnClickListener(view -> {
            if (Constants.love ==true){
                holder.album_iv.setBackgroundResource(R.drawable.ic_play_circle_filled_black_24dp);
            }
            else {
                holder.album_iv.setBackgroundResource(R.drawable.ic_pause_circle_filled_black_24dp);
            }
            Constants.love = !Constants.love;
        });
    }

    @Override
    public int getItemCount() {
        return downloaditem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AppCompatImageView album_iv;
        AppCompatTextView album_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            album_iv = itemView.findViewById (R.id.album_iv);
            album_name = itemView.findViewById (R.id.album_name);
        }
    }
}
