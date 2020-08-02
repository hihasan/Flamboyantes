package com.flamboyantes.views.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flamboyantes.R;
import com.flamboyantes.adapter.FavoriteAdapter;
import com.flamboyantes.model.FavoriteModel;
import com.flamboyantes.util.BaseFragment;
import com.flamboyantes.util.SqliteDatabaseHelper;

import java.util.ArrayList;

public class FavoriteFragment extends BaseFragment {
    private View view;
    private RecyclerView favorite_recycler;
    private FavoriteAdapter favoriteAdapter;
    private ArrayList<FavoriteModel> favoriteModel;
    private LinearLayoutManager linearLayoutManager;
    private RelativeLayout layer1;

    private SqliteDatabaseHelper db;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_favorite, container, false);

        initViews();
        initListeners();

        return view;
    }

    public void initViews(){
        favorite_recycler = view.findViewById (R.id.favorite_recycler);
        layer1 = view.findViewById (R.id.layer1);
        db = new SqliteDatabaseHelper(getActivity());
    }

    public void initListeners(){
        favoriteModel = new ArrayList<>(db.getFavorite());
        if (favoriteModel.size() == 0){
            layer1.setVisibility(View.VISIBLE);
        }else {
            layer1.setVisibility(View.INVISIBLE);
            favoriteAdapter = new FavoriteAdapter(favoriteModel, getContext());
            linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            favorite_recycler.setLayoutManager(linearLayoutManager);
            favorite_recycler.setAdapter(favoriteAdapter);
            favorite_recycler.setNestedScrollingEnabled(false);
        }

    }
}

