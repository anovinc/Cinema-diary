package com.example.cinema_diary;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    int numOftabs=2;
    public PagerAdapter(FragmentManager fm){
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment= null;
        switch (position){
            case 0:
                fragment= new FragmentWatched();
                break;
            case 1:
                fragment= new FragmentWishlist();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return numOftabs;
    }
}
