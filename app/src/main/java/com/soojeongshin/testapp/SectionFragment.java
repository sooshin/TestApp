package com.soojeongshin.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.soojeongshin.testapp.databinding.FragmentMainBinding;

public class SectionFragment extends Fragment {

    private PageViewModel mViewModel;
    private FragmentMainBinding mBinding;

    public static SectionFragment newInstance(int index) {
        SectionFragment fragment = new SectionFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        if (savedInstanceState == null) {
            mViewModel.setAlphabet();
            mViewModel.setColour();
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View rootView = mBinding.getRoot();
        mViewModel.getAlphabet().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                float fontSize = getResources().getDimension(R.dimen.text_size);
                mBinding.webView.getSettings().setDefaultFontSize((int) fontSize);
                mBinding.webView.loadData("<html><body align='center'>" + s +"</body></html>",
                        "text/html", "utf-8");
            }
        });

        mViewModel.getColour().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mBinding.webView.getSettings();
                mBinding.webView.setBackgroundColor(integer);
            }
        });
        return rootView;
    }
}
