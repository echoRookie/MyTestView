package com.example.yangjun6.myapplication;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyTestView mStepViem;
    private ValueAnimator valueAnimator;
    private TextView mLetterTv;
    private LetterSlideBarView mLetterSlideBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



}
