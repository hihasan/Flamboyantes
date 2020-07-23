package com.flamboyantes.views.search;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.flamboyantes.R;
import com.flamboyantes.adapter.ListRecyclerAdapter;
import com.flamboyantes.api.ProductsApi;
import com.flamboyantes.listener.ProductsListener;
import com.flamboyantes.model.products.AllNewPeoductsDataModel;
import com.flamboyantes.model.products.AllNewProductArray;
import com.flamboyantes.util.APIClient;
import com.flamboyantes.util.BaseFragment;
import com.flamboyantes.util.Constants;
import com.flamboyantes.util.RetrofitService;
import com.flamboyantes.util.Singleton;
import com.flamboyantes.views.home.ProductPageDetails;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends BaseFragment implements View.OnClickListener, ProductsListener {
    private View view;
    private ShimmerFrameLayout mShimmerViewContainer;
    private AppCompatTextView header, no_return;
    private AppCompatImageView back, no_data_found;
    private RecyclerView products_recycler;
    private LinearLayoutManager linearLayoutManager;
    private ListRecyclerAdapter listRecyclerAdapter;
    private ProductsApi products;
    private TextInputEditText search_tf;

    private SearchFragment listener;

    private ArrayList<AllNewPeoductsDataModel> productsList;//=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_search, container, false);

        listener = this;

        initViews();
        initListeners();

        search_tf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                search(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        Log.e("DEBUG", "onResume of HomeFragment");
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        Log.e("DEBUG", "OnPause of HomeFragment");
        super.onPause();
    }

    public void initViews(){
        products_recycler = view.findViewById (R.id.products_recycler);
        mShimmerViewContainer = view.findViewById (R.id.shimmer_view_container);
        search_tf = view.findViewById (R.id.search_tf);
        no_return = view.findViewById (R.id.no_return);
        no_data_found = view.findViewById (R.id.no_data_found);
        callNewProduct();


    }

    //method for search supplier name and set data to adapter
    private void search(CharSequence s) {

        final ArrayList<AllNewPeoductsDataModel> filteredList = new ArrayList<>();

        if (productsList != null) {

            for (int i = 0; i < productsList.size(); i++) {

                if (productsList.get(i).getName() != null) {
                    final String supplierName = productsList.get(i).getName();

                    if (supplierName.toLowerCase().contains(s)) {

                        filteredList.add(productsList.get(i));
                    }

                }

            }
        }



        listRecyclerAdapter = new ListRecyclerAdapter(filteredList, listener, getContext());
        linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        products_recycler.setLayoutManager(linearLayoutManager);
        products_recycler.setAdapter(listRecyclerAdapter);

        listRecyclerAdapter.notifyDataSetChanged();
        products_recycler.setNestedScrollingEnabled(false);
    }

    public void initListeners(){

    }


    private void callNewProduct() {
        products = RetrofitService.createService(ProductsApi.class, APIClient.BASE_URL, true);


        if (isNetworkAvailable()) {
//            dialogUtil.showProgressDialog();
            Call<AllNewProductArray> callAdvisoryApi = products.getProducts("Bearer "+ Constants.AUTHKEY);

            callAdvisoryApi.enqueue(new Callback<AllNewProductArray>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<AllNewProductArray> call, Response<AllNewProductArray> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            newProducts(response.body().getAllNewPeoductsDataModel());
                        } else {
                            Toast.makeText(getActivity(), "Failed to Load Data", Toast.LENGTH_SHORT).show();
                        }
                    } else {
//                        showErrorMessage(response.code(), response);
                        Log.e("Error", String.valueOf(response.code()));

                    }
                }


                @Override
                public void onFailure(Call<AllNewProductArray> call, Throwable t) {
                    dialogUtil.dismissProgress();
                    Toast.makeText(getActivity(), "failed To Load Data", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else{
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void newProducts(ArrayList<AllNewPeoductsDataModel> allNewPeoductsDataModel) {

        this.productsList = allNewPeoductsDataModel;

        if (allNewPeoductsDataModel.size() == 0){
            no_data_found.setVisibility(View.VISIBLE);
            no_return.setVisibility(View.VISIBLE);
        }else {
            no_return.setVisibility(View.INVISIBLE);
            listRecyclerAdapter = new ListRecyclerAdapter(allNewPeoductsDataModel, listener, getContext());
            linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
            products_recycler.setAdapter(listRecyclerAdapter);
            products_recycler.setLayoutManager(linearLayoutManager);
            products_recycler.setNestedScrollingEnabled(false);
        }



        // Stopping Shimmer Effect's animation after data is loaded to ListView
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onPositionNumber(int id, int position) {
        replaceFragment(new ProductPageDetails(), Constants.HomeScreen, Constants.HomeFragment, R.id.fragment_container);
    }
}

