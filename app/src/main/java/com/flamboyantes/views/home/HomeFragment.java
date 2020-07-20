package com.flamboyantes.views.home;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.flamboyantes.R;
import com.flamboyantes.adapter.AdapterOne;
import com.flamboyantes.adapter.AdapterTwo;
import com.flamboyantes.adapter.GridAdapter;
import com.flamboyantes.adapter.SlidingImageAdapter;
import com.flamboyantes.api.ProductsApi;
import com.flamboyantes.model.products.AllNewPeoductsDataModel;
import com.flamboyantes.model.products.AllNewProductArray;
import com.flamboyantes.util.APIClient;
import com.flamboyantes.util.BaseFragment;
import com.flamboyantes.util.Constants;
import com.flamboyantes.util.RetrofitService;
import com.flamboyantes.util.Singleton;
import com.flamboyantes.util.Utils;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private HomeFragment context=this;
    public CirclePageIndicator indicator;
    public static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {android.R.color.background_dark,android.R.color.holo_blue_bright,android.R.color.holo_green_light,android.R.color.holo_red_dark};

    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    private RecyclerView recycler_two, recycler_three, recycler_four, recycler_five, recycler_six;
    private LinearLayoutManager linearLayoutManager;
    private AdapterOne adapterOne;
    private AdapterTwo adapterTwo;

    private ProductsApi products;
    private GridAdapter gridAdapter;
    private ShimmerFrameLayout mShimmerViewContainer, shimmer_view_container_two, shimmer_view_container_three, shimmer_view_container_four, shimmer_view_container_five;

    //Btn relative
    RelativeLayout new_album_view_btn, top_selling_view_btn, top_sellling_this_week_view_btn, upcoming_album_view_btn, concert_ticket_view_btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home, container, false);
        Singleton.getInstance().setContext(getActivity());
        initViews();
        initListeners();
        init();

        return view;
    }

    @Override
    public void onResume() {
        Log.e("DEBUG", "onResume of HomeFragment");
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
        shimmer_view_container_two.startShimmerAnimation();
        shimmer_view_container_three.startShimmerAnimation();
        shimmer_view_container_four.startShimmerAnimation();
        shimmer_view_container_five.startShimmerAnimation();
    }

    @Override
    public void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        shimmer_view_container_two.stopShimmerAnimation();
        shimmer_view_container_three.stopShimmerAnimation();
        shimmer_view_container_four.stopShimmerAnimation();
        shimmer_view_container_five.stopShimmerAnimation();
        Log.e("DEBUG", "OnPause of HomeFragment");
        super.onPause();
    }

    public void initViews(){
        Utils.setModelOne();
        Utils.setModelTwo();
        mPager =  view.findViewById(R.id.pager);
        indicator = view.findViewById(R.id.indicator);

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        shimmer_view_container_two = view.findViewById (R.id.shimmer_view_container_two);
        shimmer_view_container_three = view.findViewById (R.id.shimmer_view_container_three);
        shimmer_view_container_four = view.findViewById (R.id.shimmer_view_container_four);
        shimmer_view_container_five = view.findViewById (R.id.shimmer_view_container_five);

        recycler_two = view.findViewById (R.id.recycler_two);
        recycler_three = view.findViewById (R.id.recycler_three);
        recycler_four = view.findViewById (R.id.recycler_four);
        recycler_five = view.findViewById (R.id.recycler_five);
        recycler_six = view.findViewById (R.id.recycler_six);

        new_album_view_btn = view.findViewById (R.id.new_album_view_btn);
        top_selling_view_btn = view.findViewById (R.id.top_selling_view_btn);
        top_sellling_this_week_view_btn = view.findViewById (R.id.top_sellling_this_week_view_btn);
        upcoming_album_view_btn = view.findViewById (R.id.upcoming_album_view_btn);
        concert_ticket_view_btn = view.findViewById (R.id.concert_ticket_view_btn);

    }

    public void initListeners(){
        callNewFourProduct();
        callgettopsellingproduct();
        calltopsellingproductthisweek();
        callupcomingalbums4();
        callconcerttickets();

        new_album_view_btn.setOnClickListener(this);
        top_selling_view_btn.setOnClickListener(this);
        top_sellling_this_week_view_btn.setOnClickListener(this);
        upcoming_album_view_btn.setOnClickListener (this);
        concert_ticket_view_btn.setOnClickListener(this);
    }

    public void init() {

        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);

        mPager.setAdapter(new SlidingImageAdapter(getActivity(),ImagesArray));

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }

    private void callNewFourProduct() {
        products = RetrofitService.createService(ProductsApi.class, APIClient.BASE_URL, true);


        if (isNetworkAvailable()) {
//            dialogUtil.showProgressDialog();
            Call<AllNewProductArray> callAdvisoryApi = products.getFourAllNewProductArray("Bearer "+Constants.AUTHKEY);

            callAdvisoryApi.enqueue(new Callback<AllNewProductArray>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<AllNewProductArray> call, Response<AllNewProductArray> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            newFourProducts(response.body().getAllNewPeoductsDataModel());
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

    private void callgettopsellingproduct() {
        products = RetrofitService.createService(ProductsApi.class, APIClient.BASE_URL, true);


        if (isNetworkAvailable()) {
//            dialogUtil.showProgressDialog();
            Call<AllNewProductArray> callAdvisoryApi = products.gettopsellingproduct("Bearer "+Constants.AUTHKEY);

            callAdvisoryApi.enqueue(new Callback<AllNewProductArray>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<AllNewProductArray> call, Response<AllNewProductArray> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            topsellingproduct(response.body().getAllNewPeoductsDataModel());
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

    private void calltopsellingproductthisweek() {
        products = RetrofitService.createService(ProductsApi.class, APIClient.BASE_URL, true);


        if (isNetworkAvailable()) {
//            dialogUtil.showProgressDialog();
            Call<AllNewProductArray> callAdvisoryApi = products.gettopsellingproductthisweek("Bearer "+Constants.AUTHKEY);

            callAdvisoryApi.enqueue(new Callback<AllNewProductArray>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<AllNewProductArray> call, Response<AllNewProductArray> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            topsellingproductthisweek(response.body().getAllNewPeoductsDataModel());
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
    private void callupcomingalbums4() {
        products = RetrofitService.createService(ProductsApi.class, APIClient.BASE_URL, true);


        if (isNetworkAvailable()) {
//            dialogUtil.showProgressDialog();
            Call<AllNewProductArray> callAdvisoryApi = products.getupcomingalbums4("Bearer "+Constants.AUTHKEY);

            callAdvisoryApi.enqueue(new Callback<AllNewProductArray>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<AllNewProductArray> call, Response<AllNewProductArray> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            upcomingalbums4(response.body().getAllNewPeoductsDataModel());
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

    private void callconcerttickets() {
        products = RetrofitService.createService(ProductsApi.class, APIClient.BASE_URL, true);


        if (isNetworkAvailable()) {
//            dialogUtil.showProgressDialog();
            Call<AllNewProductArray> callAdvisoryApi = products.getconcerttickets("Bearer "+Constants.AUTHKEY);

            callAdvisoryApi.enqueue(new Callback<AllNewProductArray>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onResponse(Call<AllNewProductArray> call, Response<AllNewProductArray> response) {
                    dialogUtil.dismissProgress();
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            concerttickets(response.body().getAllNewPeoductsDataModel());
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

    private void concerttickets(ArrayList<AllNewPeoductsDataModel> allNewPeoductsDataModel) {
        gridAdapter = new GridAdapter(allNewPeoductsDataModel, getContext());
        linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recycler_six.setAdapter(gridAdapter);
        recycler_six.setLayoutManager(linearLayoutManager);
        recycler_six.setNestedScrollingEnabled(false);

        // Stopping Shimmer Effect's animation after data is loaded to ListView
        shimmer_view_container_five.stopShimmerAnimation();
        shimmer_view_container_five.setVisibility(View.GONE);
    }

    private void upcomingalbums4(ArrayList<AllNewPeoductsDataModel> allNewPeoductsDataModel) {
        gridAdapter = new GridAdapter(allNewPeoductsDataModel, getContext());
        linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recycler_five.setAdapter(gridAdapter);
        recycler_five.setLayoutManager(linearLayoutManager);
        recycler_five.setNestedScrollingEnabled(false);

        // Stopping Shimmer Effect's animation after data is loaded to ListView
        shimmer_view_container_four.stopShimmerAnimation();
        shimmer_view_container_four.setVisibility(View.GONE);
    }

    private void topsellingproductthisweek(ArrayList<AllNewPeoductsDataModel> allNewPeoductsDataModel) {
        gridAdapter = new GridAdapter(allNewPeoductsDataModel, getContext());
        linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recycler_four.setAdapter(gridAdapter);
        recycler_four.setLayoutManager(linearLayoutManager);
        recycler_four.setNestedScrollingEnabled(false);

        // Stopping Shimmer Effect's animation after data is loaded to ListView
        shimmer_view_container_three.stopShimmerAnimation();
        shimmer_view_container_three.setVisibility(View.GONE);
    }

    private void topsellingproduct(ArrayList<AllNewPeoductsDataModel> allNewPeoductsDataModel) {
        gridAdapter = new GridAdapter(allNewPeoductsDataModel, getContext());
        linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recycler_three.setAdapter(gridAdapter);
        recycler_three.setLayoutManager(linearLayoutManager);
        recycler_three.setNestedScrollingEnabled(false);

        // Stopping Shimmer Effect's animation after data is loaded to ListView
        shimmer_view_container_two.stopShimmerAnimation();
        shimmer_view_container_two.setVisibility(View.GONE);
    }

    private void newFourProducts(ArrayList<AllNewPeoductsDataModel> allNewPeoductsDataModel) {
        gridAdapter = new GridAdapter(allNewPeoductsDataModel, getContext());
        linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recycler_two.setAdapter(gridAdapter);
        recycler_two.setLayoutManager(linearLayoutManager);
        recycler_two.setNestedScrollingEnabled(false);

        // Stopping Shimmer Effect's animation after data is loaded to ListView
        mShimmerViewContainer.stopShimmerAnimation();
        mShimmerViewContainer.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.new_album_view_btn:
                replaceFragment(new NewAlbumFragment(), Constants.HomeScreen, Constants.HomeFragment, R.id.fragment_container);
                break;

            case R.id.top_selling_view_btn:
                replaceFragment(new TopSellingFragment(), Constants.HomeScreen, Constants.HomeFragment, R.id.fragment_container);
                break;

            case R.id.top_sellling_this_week_view_btn:
                replaceFragment(new TopWeekFragment(), Constants.HomeScreen, Constants.HomeFragment, R.id.fragment_container);
                break;

            case R.id.upcoming_album_view_btn:
                replaceFragment(new UpcomingAlbumFragment(), Constants.HomeScreen, Constants.HomeFragment, R.id.fragment_container);
                break;

            case R.id.concert_ticket_view_btn:
                replaceFragment(new ConcertFragment(), Constants.HomeScreen, Constants.HomeFragment, R.id.fragment_container);
                break;
        }
    }
}
