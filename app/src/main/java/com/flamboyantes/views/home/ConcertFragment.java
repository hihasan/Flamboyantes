package com.flamboyantes.views.home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.flamboyantes.R;
import com.flamboyantes.adapter.ListRecyclerAdapter;
import com.flamboyantes.api.ProductsApi;
import com.flamboyantes.model.products.AllNewPeoductsDataModel;
import com.flamboyantes.model.products.AllNewProductArray;
import com.flamboyantes.util.APIClient;
import com.flamboyantes.util.BaseFragment;
import com.flamboyantes.util.Constants;
import com.flamboyantes.util.RetrofitService;
import com.flamboyantes.util.Singleton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConcertFragment extends BaseFragment implements View.OnClickListener{

    private View view;
    private ShimmerFrameLayout mShimmerViewContainer;
    private AppCompatTextView header;
    private AppCompatImageView back;
    private RecyclerView list_recycler;
    private LinearLayoutManager linearLayoutManager;
    private ListRecyclerAdapter listRecyclerAdapter;
    private ProductsApi products;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_list, container, false);
        Singleton.getInstance().setContext(getActivity());

        initViews();
        initListeners();

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

    public void initViews() {
        header = view.findViewById (R.id.header);
        back = view.findViewById (R.id.back);
        list_recycler = view.findViewById (R.id.list_recycler);
        mShimmerViewContainer = view.findViewById (R.id.shimmer_view_container);
    }

    public void initListeners() {
        header.setText(getResources().getString(R.string.concert_ticket));
        back.setOnClickListener(this);
        callNewProduct();
    }



    private void callNewProduct() {
        products = RetrofitService.createService(ProductsApi.class, APIClient.BASE_URL, true);


        if (isNetworkAvailable()) {
//            dialogUtil.showProgressDialog();
            Call<AllNewProductArray> callAdvisoryApi = products.getconcerttickets("Bearer "+ Constants.AUTHKEY);

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
        listRecyclerAdapter = new ListRecyclerAdapter(allNewPeoductsDataModel, getContext());
        linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        list_recycler.setAdapter(listRecyclerAdapter);
        list_recycler.setLayoutManager(linearLayoutManager);
        list_recycler.setNestedScrollingEnabled(false);

        // Stopping Shimmer Effect's animation after data is loaded to ListView
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                replaceFragment(new HomeFragment(), Constants.HomeScreen, Constants.HomeFragment, R.id.fragment_container);
                break;
        }
    }
}
