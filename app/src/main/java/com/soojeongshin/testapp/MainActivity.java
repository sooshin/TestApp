package com.soojeongshin.testapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.soojeongshin.testapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyPagerAdapter mAdapter;
    private ActivityMainBinding mBinding;
    private List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        mFragments = new ArrayList<>();
        mFragments.add(SectionFragment.newInstance(0));
        mFragments.add(SectionFragment.newInstance(1));
        mFragments.add(SectionFragment.newInstance(2));

        FragmentManager fm = getSupportFragmentManager();
        mAdapter = new MyPagerAdapter(this, fm, mFragments);
        mBinding.viewpager.setAdapter(mAdapter);

        insert();
    }

    private void insert() {
        mBinding.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentIndex = mBinding.viewpager.getCurrentItem();
                mAdapter.addFragmentAtPosition(currentIndex, SectionFragment.newInstance(currentIndex));
                mBinding.viewpager.setCurrentItem(currentIndex +1);
            }
        });
    }
}
