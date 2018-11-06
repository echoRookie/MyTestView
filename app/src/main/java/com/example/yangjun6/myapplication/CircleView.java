package com.example.yangjun6.myapplication;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import static java.lang.Math.PI;

public class CircleView  extends View {
    private double mCenterX;
    private double mCenterY;
    private Paint mPaint;
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
            float dx = (float) (mCenterX + 100*Math.cos(percentAngle *i));
            float dy = (float) (mCenterY + 100*Math.sin(percentAngle*i));
            canvas.drawCircle(dx,dy,15,mPaint);
        }
    }
    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }
    public void animation(){
        ObjectAnimator animator =
    }
}
