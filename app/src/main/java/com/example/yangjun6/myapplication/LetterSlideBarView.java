package com.example.yangjun6.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

public class LetterSlideBarView extends View {
    private TouchListener touchListener;
    private Paint mDefaultPaint;
    private Paint mChangePaint;
    private int currentItem;
    private int itemHeight;
    private String currentLetter;
    private String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O",
        "P","Q","R","S","T","U","V","W","X","Y","Z"};
    public LetterSlideBarView(Context context) {
        this(context,null);
    }

    public LetterSlideBarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LetterSlideBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = (int)(getPaddingLeft() + mChangePaint.measureText("A") + getPaddingRight());
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        itemHeight = getHeight() / 26;
        int length = letters.length;
        for(int i = 0;i < length;i++){
            Rect bound = new Rect();
            mDefaultPaint.getTextBounds(letters[i],0,letters[i].length(),bound);
            int dx = getPaddingLeft() + getWidth() / 2 - bound.width() / 2;
            Paint.FontMetrics fontMetrics = mDefaultPaint.getFontMetrics();
            int dy = (int)((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
            int baseline = itemHeight * i + itemHeight / 2 + dy;
            if(letters[i].equals(currentLetter)){
                canvas.drawText(letters[i],dx,baseline,mChangePaint);
            }else {
                canvas.drawText(letters[i],dx,baseline,mDefaultPaint);
            }


        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                // 计算当前触摸字母的位置
                float currentMoveY = event.getY();
                currentItem = (int)currentMoveY / itemHeight;
                if(currentItem <= 0)
                    currentItem = 0;
                if(currentItem > letters.length - 1)
                    currentItem = letters.length - 1;
                currentLetter = letters[currentItem];
                if(touchListener != null){
                    touchListener.touch(currentLetter,true);
                }
                invalidate();

                break;
            case MotionEvent.ACTION_UP:
                if(touchListener != null){
                    touchListener.touch(currentLetter,false);
                }
                break;
        }
        return true;
    }

    // 初始化画笔
    public void init(){
        mDefaultPaint = new Paint();
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setColor(Color.BLUE);
        mDefaultPaint.setTextSize(sp2px(12));
        mChangePaint = new Paint();
        mChangePaint.setAntiAlias(true);
        mChangePaint.setColor(Color.RED);
        mChangePaint.setTextSize(20);
    }

    private float sp2px(int i) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,i,getResources().getDisplayMetrics());
    }
    public void setTouchListener(TouchListener touchListener){
        this.touchListener = touchListener;
    }
    interface TouchListener{
        void touch(String letter,boolean isTouch);
    }
}
