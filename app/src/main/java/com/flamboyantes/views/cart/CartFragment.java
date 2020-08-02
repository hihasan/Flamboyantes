package com.flamboyantes.views.cart;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.flamboyantes.R;
import com.flamboyantes.adapter.CartAdapter;
import com.flamboyantes.model.CartModel;
import com.flamboyantes.util.BaseFragment;
import com.flamboyantes.util.SqliteDatabaseHelper;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class CartFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private RecyclerView cart_item;
    private TextInputEditText discount_tf, gift_tf;
    private AppCompatTextView sub_total_value, tax_value, total_value;

    SqliteDatabaseHelper db;
    private CartAdapter customAdapter;
    private ArrayList<CartModel> cartModel;
    private LinearLayoutManager linearLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_cart, container, false);

        initViews();
        initListeners();

        return view;
    }

    public void initViews(){
        cart_item = view.findViewById (R.id.cart_item);
        discount_tf = view.findViewById (R.id.discount_tf);
        gift_tf = view.findViewById (R.id.gift_tf);
        sub_total_value = view.findViewById (R.id.sub_total_value);
        tax_value = view.findViewById (R.id.tax_value);
        total_value = view.findViewById (R.id.total_value);

        db = new SqliteDatabaseHelper(getActivity());


    }

    public void initListeners(){
        cartModel = new ArrayList<>(db.getCartItem());
        customAdapter = new CartAdapter(cartModel, getContext());
        linearLayoutManager = new LinearLayoutManager (getContext(), RecyclerView.VERTICAL, false);
        cart_item.setAdapter(customAdapter);
        cart_item.setLayoutManager(linearLayoutManager);
        cart_item.setNestedScrollingEnabled(false);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
