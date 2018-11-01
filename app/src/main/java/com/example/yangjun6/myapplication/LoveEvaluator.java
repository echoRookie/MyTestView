package com.example.yangjun6.myapplication;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

public class LoveEvaluator implements TypeEvaluator<PointF> {
    private PointF p1,p2;
    public LoveEvaluator(PointF p1,PointF p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    @Override
    public PointF evaluate(float v, PointF p0, PointF p3) {
        PointF pointF1 = new PointF();
        pointF1.x = p0.x * (1-v)*(1-v)*(1-v)
                + 3*p1.x*v*(1-v)*(1-v)
                +3*p2.x*v*v*(1-v)
                +p3.x *v*v*v;
        pointF1.y = p0.y * (1-v)*(1-v)*(1-v)
                + 3*p1.y*v*(1-v)*(1-v)
                +3*p2.y*v*v*(1-v)
                +p3.y *v*v*v;

        return pointF1;
    }
}
