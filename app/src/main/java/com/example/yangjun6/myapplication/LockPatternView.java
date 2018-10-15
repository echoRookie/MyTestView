package com.example.yangjun6.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class LockPatternView extends View {
    private Paint mNormalPaint;
    private Paint mLinePaint;
    private Paint mErrorPaint;
    private Object[][] mPoint ;
    private float moveX = 0f;
    private float moveY = 0f;
    private List<Point> selectPoint = new ArrayList<>();
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
        for(i = 0;i<selectPoint.size();i++){
            canvas.drawCircle(selectPoint.get(i).centerX,selectPoint.get(i).centerY,80,mLinePaint);
            canvas.drawCircle(selectPoint.get(i).centerX,selectPoint.get(i).centerY,15,mLinePaint);
            if(i>0){
                canvas.drawLine(selectPoint.get(i-1).centerX,selectPoint.get(i-1).centerY,selectPoint.get(i).centerX,selectPoint.get(i).centerY,mLinePaint);
            }

        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        moveX = event.getX();
        moveY = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                int i,j;
                for(i = 0;i<mPoint.length;i++) {
                    for (j = 0; j < mPoint[i].length; j++) {
                        Point temp = (Point) mPoint[i][j];
                        if (Math.sqrt((temp.centerX - moveX) * (temp.centerX - moveX) + (temp.centerY - moveY) * (temp.centerY - moveY)) < 80) {
                            selectPoint.add(temp);
                            invalidate();
                            break;
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE :
                for(i = 0;i<mPoint.length;i++) {
                    for (j = 0; j < mPoint[i].length; j++) {
                        Point temp = (Point) mPoint[i][j];
                        if (Math.sqrt((temp.centerX - moveX) * (temp.centerX - moveX) + (temp.centerY - moveY) * (temp.centerY - moveY)) < 80) {
                            selectPoint.add(temp);
                            invalidate();
                            break;
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                selectPoint.clear();
                break;
        }
        return true;
    }

    /*
         初始化:
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

    // 判断触摸的点是否在圆内
//    public Point checkInPoints(float x,float y,float radius){
//        int i,j;
//        Point temp = null;
//        for(i = 0;i<mPoint.length;i++) {
//            for (j = 0; j < mPoint[i].length; j++) {
//                temp = (Point) mPoint[i][j];
//                if (Math.sqrt((temp.centerX - x) * (temp.centerY - x) + (temp.centerY - y) * (temp.centerY - y)) < radius) {
//                    return  temp;
//                    break;
//                }else {
//                    temp = null;
//                    return  temp;
//                }
//            }
//        }
//
//    }
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
