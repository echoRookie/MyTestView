package com.example.yangjun6.myapplication;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private MyTestView mStepViem;
    private ValueAnimator valueAnimator;
    private TextView mLetterTv;
    private LetterSlideBarView mLetterSlideBar;
    private LoverView loverView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loverView = findViewById(R.id.loveView);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void addView(View v){
        loverView.addLoveView();
    }


}
