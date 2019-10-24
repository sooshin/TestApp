package com.soojeongshin.testapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import static com.soojeongshin.testapp.Constants.KEY_ALPHABET;

public class SectionFragment extends Fragment {

    private PageViewModel mViewModel;
    private String mAlphabet;

    public static SectionFragment newInstance(int index) {
        SectionFragment fragment = new SectionFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        if (savedInstanceState != null) {
            mAlphabet = savedInstanceState.getString(KEY_ALPHABET);
        } else {
            mViewModel.setAlphabet();
        }
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.tv_alphabet);
        mAlphabet = textView.getText().toString();
        mViewModel.getAlphabet().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_ALPHABET, mAlphabet);
    }
}
