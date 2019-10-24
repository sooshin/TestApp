package com.soojeongshin.testapp;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private final Context mContext;
    private List<Fragment> mFragments;

    public MyPagerAdapter(Context context, FragmentManager fm, List<Fragment> fragments) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        mFragments = fragments;
    }

    void addFragmentAtPosition(int position, Fragment f) {
        if (position == mFragments.size()) {
            mFragments.add(f);
        } else {
            mFragments.add(position + 1, f);
        }
        notifyDataSetChanged();
    }

    /**
     * Called when the fragments are reordered to get the changes.
     */
    @Override
    public int getItemPosition(@NonNull Object object) {
        int idx = mFragments.indexOf(object);
        return idx < 0 ? POSITION_NONE : idx;
    }

    /**
     * Map to a position independent ID, because the pager adapter reorders fragments.
     */
    @Override
    public long getItemId(int position) {
        return System.identityHashCode(mFragments.get(position));
    }

    /**
     * Returns the {@link Fragment} that should be displayed for the given page number.
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return SectionFragment.newInstance(position);
    }

    /**
     * Returns the number of views available.
     */
    @Override
    public int getCount() {
        return mFragments.size();
    }
}
