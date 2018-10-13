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
    private Object[][] mPoint ;
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
        // 初始化点
        int width = this.getWidth();
        int pointWidth = width / 3;
        int offsetX = pointWidth / 2;
        int offsetY = this.getHeight() / 2;
        mPoint = new Object[3][3];
        mPoint[0][0] = new Point(offsetX,offsetY - pointWidth,1);
        mPoint[0][1] = new Point(offsetX*3,offsetY - pointWidth,2);
        mPoint[0][2] = new Point(offsetX*5,offsetY - pointWidth,3);
        mPoint[1][0] = new Point(offsetX,offsetY,4);
        mPoint[1][1] = new Point(offsetX *3,offsetY,5);
        mPoint[1][2] = new Point(offsetX*5,offsetY,6);
        mPoint[2][0] = new Point(offsetX,offsetY + pointWidth,7);
        mPoint[2][1] = new Point(offsetX*3,offsetY + pointWidth,8);
        mPoint[2][2] = new Point(offsetX*5,offsetY + pointWidth,9);
        int i;
        int j;
        for (i = 0;i < mPoint.length; i++){
            for(j = 0;j < mPoint[i].length;j++){
                Point point = (Point) mPoint[i][j];
                canvas.drawCircle(point.centerX,point.centerY,15,mNormalPaint);
                canvas.drawCircle(point.centerX,point.centerY,80,mNormalPaint);
            }
        }
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


    }
}
class Point{
    private int STATUS_NORMAL = 0;
    private int STATUS_PRESSED = 1;
    private int STATUS_ERROR = 2;
    private int status = STATUS_NORMAL;
    public int centerX;
    public int centerY;
    public int index;
    public Point(int centerX,int centerY,int index){
        this.centerX = centerX;
        this.centerY = centerY;
        this.index = index;
    }
}
