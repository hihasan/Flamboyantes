package com.flamboyantes.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.flamboyantes.views.auth.ArtistSignupFragment;
import com.flamboyantes.views.auth.SignupActivity;
import com.flamboyantes.views.auth.UserSignupFragment;

public class SignUpAdapter extends FragmentPagerAdapter
{
    private int totalTabs;

    public SignUpAdapter(SignupActivity context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new UserSignupFragment();
            case 1:
                return new ArtistSignupFragment();

            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
