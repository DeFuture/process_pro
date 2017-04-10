package com.defuture.processpro.keep.ui;


import com.defuture.processpro.R;
import com.defuture.processpro.keep.LocalService;
import com.defuture.processpro.keep.RemoteService;
import com.defuture.processpro.keep.entities.FirstEvent;
import com.defuture.processpro.keep.entities.SecondEvent;
import com.defuture.processpro.keep.entities.ThirdEvent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        this.startService(new Intent(this,LocalService.class));
        this.startService(new Intent(this,RemoteService.class));

        findViewById(R.id.btn_tosecond).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mainThread(FirstEvent event) {

        Log.d("harvic", "onEventMainThread收到了消息：" + event.getMsg());
    }

    //SecondEvent接收函数一
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void mainThread(SecondEvent event) {

        Log.d("harvic", "onEventMainThread收到了消息：" + event.getMsg());
    }
    //SecondEvent接收函数二
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void backgroundThread(SecondEvent event){
        Log.d("harvic", "onEventBackground收到了消息：" + event.getMsg());
    }
    //SecondEvent接收函数三
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void async(SecondEvent event){
        Log.d("harvic", "onEventAsync收到了消息：" + event.getMsg());
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onEvent(ThirdEvent event) {
        Log.d("harvic", "OnEvent收到了消息：" + event.getMsg());
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
