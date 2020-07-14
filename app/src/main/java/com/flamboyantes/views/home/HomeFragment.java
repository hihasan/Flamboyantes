package com.flamboyantes.views.home;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.flamboyantes.R;
import com.flamboyantes.adapter.AdapterOne;
import com.flamboyantes.adapter.AdapterTwo;
import com.flamboyantes.adapter.SlidingImageAdapter;
import com.flamboyantes.util.BaseFragment;
import com.flamboyantes.util.Utils;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends BaseFragment {
    private View view;
    private HomeFragment context=this;
    public CirclePageIndicator indicator;
    public static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {android.R.color.background_dark,android.R.color.holo_blue_bright,android.R.color.holo_green_light,android.R.color.holo_red_dark};

    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();

    private RecyclerView recycler_two, recycler_three, recycler_four;
    private LinearLayoutManager linearLayoutManager, linearLayoutManager2;
    private AdapterOne adapterOne;
    private AdapterTwo adapterTwo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_home, container, false);

        initViews();
        initListeners();
        init();

        return view;
    }

    public void initViews(){
        Utils.setModelOne();
        Utils.setModelTwo();
        mPager =  view.findViewById(R.id.pager);
        indicator = view.findViewById(R.id.indicator);

        recycler_two = view.findViewById (R.id.recycler_two);
        recycler_three = view.findViewById (R.id.recycler_three);
        recycler_four = view.findViewById (R.id.recycler_four);
    }

    public void initListeners(){
        adapterOne = new AdapterOne(Utils.modelOne, getContext());
        linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recycler_two.setAdapter(adapterOne);
        recycler_two.setLayoutManager(linearLayoutManager);
        recycler_two.setNestedScrollingEnabled(false);

        linearLayoutManager2 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        recycler_four.setAdapter(adapterOne);
        recycler_four.setLayoutManager(linearLayoutManager2);
        recycler_four.setNestedScrollingEnabled(false);

        adapterTwo = new AdapterTwo(Utils.modelTwo, getContext());
        linearLayoutManager = new LinearLayoutManager (getActivity(), RecyclerView.HORIZONTAL, false);
        recycler_three.setAdapter(adapterTwo);
        recycler_three.setLayoutManager(linearLayoutManager);
        recycler_three.setNestedScrollingEnabled(false);

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
}
