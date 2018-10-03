package com.example.yangjun6.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
//
//
/**
// * @param <T>
 * @param <T>
 * @param <V>
 */
public class Test1 <T,V> {
//  添加泛型类的测试
    public T t;
    public V v;
    List<? extends Do> list = new ArrayList<>();
    public class Test2 extends  Test1{
    }

    /**
     *
     */
    interface  Do{

    }
}

