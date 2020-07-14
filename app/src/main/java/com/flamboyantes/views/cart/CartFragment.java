package com.flamboyantes.views.cart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flamboyantes.R;
import com.flamboyantes.util.BaseFragment;

public class CartFragment extends BaseFragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_cart, container, false);

        initViews();
        initListeners();

        return view;
    }

    public void initViews(){

    }

    public void initListeners(){

    }
}
