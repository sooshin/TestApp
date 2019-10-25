package com.soojeongshin.testapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

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

        setupBottomAppBar();

        mFragments = new ArrayList<>();
        mFragments.add(SectionFragment.newInstance(0));
        mFragments.add(SectionFragment.newInstance(1));
        mFragments.add(SectionFragment.newInstance(2));

        FragmentManager fm = getSupportFragmentManager();
        mAdapter = new MyPagerAdapter(this, fm, mFragments,
                mBinding.previous, mBinding.next);
        mBinding.viewpager.setAdapter(mAdapter);

        insert();
        delete();

        mBinding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    // Disable the previous button at the beginning of the page
                    disablePreviousButton();
                } else {
                    enablePreviousButton();
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mFragments.size()-1) {
                    // Disable the next button at the end of the page
                    disableNextButton();
                    enablePreviousButton();
                } else if (position == 0) {
                    // Disable the previous button at the beginning of the page
                    disablePreviousButton();
                    enableNextButton();
                } else {
                    enablePreviousButton();
                    enableNextButton();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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

    private void delete() {
        mBinding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentIndex = mBinding.viewpager.getCurrentItem();
                mAdapter.removeFragmentAtPosition(currentIndex);
                mBinding.viewpager.setCurrentItem(currentIndex -1);
            }
        });
    }

    private void setupBottomAppBar() {
        mBinding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentIndex = mBinding.viewpager.getCurrentItem();
                mBinding.viewpager.setCurrentItem(currentIndex +1);
            }
        });

        mBinding.previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentIndex = mBinding.viewpager.getCurrentItem();
                mBinding.viewpager.setCurrentItem(currentIndex -1);
            }
        });
    }

    private void disablePreviousButton() {
        mBinding.previous.setAlpha(0.5f);
        mBinding.previous.setEnabled(false);
    }

    private void disableNextButton() {
        mBinding.next.setEnabled(false);
        mBinding.next.setAlpha(0.5f);
    }

    private void enablePreviousButton() {
        mBinding.previous.setEnabled(true);
        mBinding.previous.setAlpha(1f);
    }

    private void enableNextButton() {
        mBinding.next.setEnabled(true);
        mBinding.next.setAlpha(1f);
    }

}
