package com.soojeongshin.testapp;

import android.graphics.Color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class PageViewModel extends ViewModel {

    private MutableLiveData<String> mAlphabet = new MutableLiveData<>();
    private MutableLiveData<Integer> mColour = new MutableLiveData<>();

    public void setAlphabet() {
        mAlphabet.setValue(generateRandomAlphabet());
    }

    public LiveData<String> getAlphabet() {
        return mAlphabet;
    }

    private String generateRandomAlphabet() {
        Random random = new Random();
        String [] alphabets = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                "L","M", "N","O","P","Q", "R", "S", "T", "U", "V","W", "X","Y","Z"};
        int index = random.nextInt(alphabets.length -1);
        return alphabets[index];
    }

    public void setColour() {
        mColour.setValue(generateRandomColour());
    }

    public LiveData<Integer> getColour() {
        return mColour;
    }

    private Integer generateRandomColour() {
        Random random = new Random();
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);
        return Color.rgb(r, g, b);
    }
}
