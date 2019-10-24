package com.soojeongshin.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class SectionFragment extends Fragment {

    private PageViewModel mViewModel;

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

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final WebView webView = root.findViewById(R.id.web_view);

        mViewModel.getAlphabet().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                webView.loadData(s, "text/html", "utf-8");
            }
        });

        mViewModel.getColour().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                webView.getSettings();
                webView.setBackgroundColor(integer);
            }
        });
        return root;
    }
}
