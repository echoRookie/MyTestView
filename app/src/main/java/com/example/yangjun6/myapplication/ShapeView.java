package com.example.yangjun6.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ShapeView extends View {
    private Paint mPaint1;
    private PointF mDragPoint;
    private PointF mFixationPoint;
    public ShapeView(Context context) {
        this(context,null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mFixationPoint != null){
            canvas.drawCircle(mFixationPoint.x,mFixationPoint.y,5,mPaint1);
        }
        if(mDragPoint != null){
            canvas.drawCircle(mDragPoint.x,mDragPoint.y,20,mPaint1);
        }


        super.onDraw(canvas);
    }
    // 初始化画笔
    private void init(){
        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setColor(Color.BLUE);
        mPaint1.setAntiAlias(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                initPoint(event.getX(),event.getY());
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                changePoint(event.getX(),event.getY());
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }
    private void initPoint(float x,float y){
        mFixationPoint = new PointF(x,y);
        mDragPoint = new PointF(x,y);
    }
    private void changePoint(float x,float y){
        mDragPoint.x = x;
        mDragPoint.y = y;
    }
}
