package com.soojeongshin.testapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        MyPagerAdapter adapter = new MyPagerAdapter(MainActivity.this, getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }
}
