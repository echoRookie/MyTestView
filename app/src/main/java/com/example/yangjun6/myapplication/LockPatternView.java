package com.example.yangjun6.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.View;

public class LockPatternView extends View {
    private Paint mNormalPaint;
    private Paint mLinePaint;
    private Paint mErrorPaint;
    private Object[][] mPoint;
    public LockPatternView(Context context) {
        this(context,null);
    }

    public LockPatternView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LockPatternView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画九个点

    }
    /*
     初始化
     */
    private void init(){
        // 初始化画笔
        mNormalPaint = new Paint();
        mNormalPaint.setAntiAlias(true);
        mNormalPaint.setColor(Color.BLACK);
        mNormalPaint.setStyle(Paint.Style.STROKE);
        mNormalPaint.setStrokeWidth(10);
        mLinePaint = new Paint();
        mLinePaint.setAntiAlias(true);
        mLinePaint.setColor(Color.BLUE);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(10);
        mErrorPaint = new Paint();
        mErrorPaint.setAntiAlias(true);
        mErrorPaint.setColor(Color.RED);
        mErrorPaint.setStyle(Paint.Style.STROKE);
        mErrorPaint.setStrokeWidth(10);
        // 初始化点
        mPoint[0][0] = new Point(0,0,1);
        mPoint[0][1] = new Point(0,0,2);
        mPoint[0][2] = new Point(0,0,3);
        mPoint[1][0] = new Point(0,0,4);
        mPoint[1][1] = new Point(0,0,5);
        mPoint[1][2] = new Point(0,0,6);
        mPoint[2][0] = new Point(0,0,7);
        mPoint[2][1] = new Point(0,0,8);
        mPoint[3][2] = new Point(0,0,9);

    }
}
class Point{
    private int STATUS_NORMAL = 0;
    private int STATUS_PRESSED = 1;
    private int STATUS_ERROR = 2;
    private int status = STATUS_NORMAL;
    private int centerX;
    private int centerY;
    private int index;
    public Point(int centerX,int centerY,int index){
        this.centerX = centerX;
        this.centerY = centerY;
        this.index = index;
    }
}
