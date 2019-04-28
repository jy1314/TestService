package com.pro.testservice;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

/*
* @author Jerry
* create at 2019/4/28 下午7:44
* description:MyIntentService 异步自动停止service Demo
*/
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.e("MyIntentService","thread id is :"+ Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyIntentService","onDestory executed");
    }
}
