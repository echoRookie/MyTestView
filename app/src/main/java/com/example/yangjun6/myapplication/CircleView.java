package com.example.yangjun6.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import java.util.logging.Handler;

import static java.lang.Math.PI;

public class CircleView  extends View {
    private double mCenterX;
    private double mCenterY;
    private Paint mPaint;
    private Handler handler;
    private float mCurrentAngle = 0;
    private  int[] colorRes = {Color.RED,Color.BLACK,Color.BLUE,Color.YELLOW,Color.GREEN,Color.GRAY};
    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        animation();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mCenterX = MeasureSpec.getSize(widthMeasureSpec)/2;
        mCenterY = MeasureSpec.getSize(heightMeasureSpec)/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = 0;
        double percentAngle =  Math.PI *2 / colorRes.length;
        for(i=0;i<colorRes.length;i++){
            mPaint.setColor(colorRes[i]);
            float dx = (float) (mCenterX + 100*Math.cos((percentAngle )*i+ mCurrentAngle));
            float dy = (float) (mCenterY + 100*Math.sin((percentAngle )*i+ mCurrentAngle));
            canvas.drawCircle(dx,dy,15,mPaint);
        }
    }
    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }
    public void animation(){
        ValueAnimator animator = ValueAnimator.ofFloat(0,(float) (Math.PI*2));
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mCurrentAngle = (float)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                Toast.makeText(getContext(),"动画执行完毕",Toast.LENGTH_LONG).show();
            }
        });
        animator.start();

    }
}
