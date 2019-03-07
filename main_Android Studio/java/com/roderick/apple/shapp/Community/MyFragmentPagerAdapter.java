package com.roderick.apple.shapp.Community;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by apple on 18/2/24.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;
    private String[] titles = {"团游", "攻略", "动态"};
    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment>fragmentList) {
        super(fm);
        this.fragmentList=fragmentList;
        fragmentManager=fm;

    }


    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
