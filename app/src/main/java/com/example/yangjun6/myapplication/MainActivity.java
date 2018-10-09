package com.example.yangjun6.myapplication;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LetterSlideBarView.TouchListener {
    private MyTestView mStepViem;
    private ValueAnimator valueAnimator;
    private TextView mLetterTv;
    private LetterSlideBarView mLetterSlideBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLetterTv = findViewById(R.id.letter_tv);
        mLetterSlideBar = findViewById(R.id.letter_view);
        mLetterSlideBar.setTouchListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void touch(String letter,boolean isTouch) {
        if(isTouch){
            mLetterTv.setText(letter);
            mLetterTv.setVisibility(View.VISIBLE);
        }else {
            mLetterTv.setVisibility(View.GONE);
        }

    }
}
