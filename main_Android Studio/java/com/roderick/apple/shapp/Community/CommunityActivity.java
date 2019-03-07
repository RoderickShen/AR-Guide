package com.roderick.apple.shapp.Community;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;


import com.roderick.apple.shapp.Community.Discuss.Fragment3;
import com.roderick.apple.shapp.Community.TourGroupAndStrategy.Fragment2;
import com.roderick.apple.shapp.Community.TourGroupAndStrategy.Fragment1;
import com.roderick.apple.shapp.R;

import java.util.ArrayList;
import java.util.List;

public class CommunityActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private List<Fragment>fragmentList;
    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private String[] titles = {"团游", "攻略", "动态"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_community);

        viewPager=(ViewPager)findViewById(R.id.pager);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);

        fragment1=new Fragment1();
        fragment2=new Fragment2();
        fragment3=new Fragment3();
        fragmentList=new ArrayList<Fragment>();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);

        MyFragmentPagerAdapter myFragmentPagerAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(myFragmentPagerAdapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);

    }
}
