package com.flamboyantes.views.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.flamboyantes.R;
import com.flamboyantes.util.BaseFragment;
import com.flamboyantes.util.Constants;
import com.flamboyantes.util.Singleton;
import com.flamboyantes.views.search.SearchFragment;

public class FavoriteMain extends BaseFragment {

    private View view;
    private FrameLayout fragmentContainer;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_main, container, false);
        Singleton.getInstance().setContext(getActivity());
        initViews();
        initListeners();
        return view;
    }

    public void initViews(){
        fragmentContainer = view.findViewById (R.id.fragment_container);
    }

    public void initListeners(){
        //call home fragment
        initFragment(new FavoriteFragment(), Constants.HomeFragment, fragmentContainer.getId());
    }
}


