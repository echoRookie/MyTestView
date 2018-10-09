package com.example.yangjun6.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

public class ColorTrackTextView extends AppCompatTextView {
    private int mOriginColor;
    private int mChangeColor;
    private Paint originPaint;
    private Paint changePaint;
    private float mProcess = 0.5F;
    public ColorTrackTextView(Context context) {
        this(context,null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        originPaint = init(Color.BLUE);
        changePaint = init(Color.RED);

    }
    public Paint init(int color){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setTextSize(getTextSize());
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        int middle = (int)(mProcess * getWidth());
        Rect rect = new Rect(0,0,middle,getHeight());
        canvas.clipRect(rect);
        String text = getText().toString();
        Rect bound = new Rect();
        originPaint.getTextBounds(text,0,text.length(),bound);
        int dx = getWidth() / 2 - bound.width() / 2;
        Paint.FontMetrics fontMetrics = originPaint.getFontMetrics();
        int dy = (int)((fontMetrics.bottom - fontMetrics.top)/ 2 - fontMetrics.bottom);
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(text,dx,baseLine,originPaint);
        canvas.restore();

        canvas.save();
        rect = new Rect(middle,0,getWidth(),getHeight());
        canvas.clipRect(rect);
        originPaint.getTextBounds(text,0,text.length(),bound);
        canvas.drawText(text,dx,baseLine,changePaint);
        canvas.restore();
    }
}
