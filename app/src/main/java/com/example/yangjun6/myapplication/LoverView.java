package com.example.yangjun6.myapplication;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

public class LoverView extends RelativeLayout {
    private int width,height;
    public LoverView(Context context) {
        this(context,null);
    }

    public LoverView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoverView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    public void addLoveView(){
        ImageView imageView = new ImageView(getContext());
        Random random = new Random();
        int[] drawableRes = {R.drawable.green,R.drawable.yellow,R.drawable.red};
        imageView.setImageResource(drawableRes[random.nextInt(2)]);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(CENTER_HORIZONTAL);
        imageView.setLayoutParams(layoutParams);
        addView(imageView);
        AnimatorSet set = getAnimation(imageView);
        set.start();
    }
    public AnimatorSet getAnimation(ImageView iv){
        AnimatorSet set = new AnimatorSet();
        AnimatorSet innerAnimation = new AnimatorSet();
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(iv,"alpha",0.3f,1.0f);
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(iv,"scaleX",0.3f,1.0f);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(iv,"scaleY",0.3f,1.0f);
        innerAnimation.playTogether(alphaAnimation,scaleXAnimation,scaleYAnimation);
        innerAnimation.setDuration(1000);
        set.playSequentially(innerAnimation,getBezierAnimator(iv));

        return  set;
    }

    private Animator getBezierAnimator(final ImageView iv) {
        PointF p0 =new PointF(width/2,height-10);
        PointF p1 =new PointF(width/4,height/4);
        PointF p2 =new PointF(width*3/4,height*3/4);
        PointF p3 =new PointF(width/2,0);

        LoveEvaluator loveEvaluator = new LoveEvaluator(p1,p2);
        ValueAnimator bezierAnimator = ObjectAnimator.ofObject(loveEvaluator,p0,p3);

        bezierAnimator.setDuration(10000);
        bezierAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                PointF pointF = (PointF) valueAnimator.getAnimatedValue();
                iv.setX(pointF.x);
                iv.setY(pointF.y);
            }
        });
        return bezierAnimator;
    }
}
