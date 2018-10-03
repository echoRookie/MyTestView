package com.example.yangjun6.myapplication;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.content.ContentValues.TAG;

public class MyTestView extends View {
    private int mOuterColor = Color.RED;
    private int mInnerColor = Color.BLUE;
    private int mBorderWidth = 40;
    private int mStepTextSize = 30;
    private int mStepTextColor;
    private Paint mPaint;
    private Paint mPaint1;
    private Paint mPaint2;
    private int mStepMax = 3000;
    private int mStepCurrent = 500;

    public MyTestView(Context context) {
        this(context, null);
    }

    public MyTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    //
    public MyTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.myView);
        mOuterColor = array.getColor(R.styleable.myView_outerColor, mOuterColor);
        mInnerColor = array.getColor(R.styleable.myView_innerColor, mInnerColor);
        mBorderWidth = (int) array.getDimension(R.styleable.myView_borderWidth, mBorderWidth);
        mStepTextSize = array.getDimensionPixelSize(R.styleable.myView_stepTextSize, mStepTextSize);
        mStepTextColor = array.getColor(R.styleable.myView_stepTextColor, mStepTextColor);
        array.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(mBorderWidth);
        mPaint.setColor(mOuterColor);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);

        mPaint1 = new Paint();
        mPaint1.setAntiAlias(true);
        mPaint1.setStrokeWidth(mBorderWidth);
        mPaint1.setColor(mInnerColor);
        mPaint1.setStrokeCap(Paint.Cap.ROUND);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint2 = new Paint();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width > height ? height : width, width > height ? height : width);
    }

    public int getmOuterColor() {
        return mOuterColor;
    }

    public void setmOuterColor(int mOuterColor) {
        this.mOuterColor = mOuterColor;
    }

    public int getmStepMax() {
        return mStepMax;
    }

    public void setmStepMax(int mStepMax) {
        this.mStepMax = mStepMax;
    }

    public int getmStepCurrent() {
        return mStepCurrent;
    }

    public void setmStepCurrent(int mStepCurrent) {
        this.mStepCurrent = mStepCurrent;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        RectF rectF = new RectF(0 + mBorderWidth, 0 + mBorderWidth, getWidth() - mBorderWidth, getHeight() - mBorderWidth);
        canvas.drawArc(rectF, 135, 270, false, mPaint);
        float precent = (float) mStepCurrent / mStepMax;
        Log.d("aaa", "onDraw: " + precent);
        mPaint1.setColor(mInnerColor);
        canvas.drawArc(rectF, 135, precent * 270, false, mPaint1);
        mPaint2.setAntiAlias(true);
        mPaint2.setTextSize(59);
        mPaint2.setColor(mInnerColor);
        String str = String.valueOf(mStepCurrent);

        Rect textBound = new Rect();
        mPaint2.getTextBounds(str, 0, str.length(), textBound);
        int dx = (getWidth() - textBound.width()) / 2;
        Paint.FontMetrics fontMetrics = mPaint2.getFontMetrics();
        int dy = (int) (getHeight() / 2 - (fontMetrics.bottom - fontMetrics.top) / 2);
        canvas.drawText(str, dx, dy, mPaint2);

    }

}
