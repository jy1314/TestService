package com.pro.testservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.start_service)
    Button start_service;
    @BindView(R.id.stop_service)
    Button stop_service;
    @BindView(R.id.bind_service)
    Button bind_service;
    @BindView(R.id.unbind_service)
    Button unbind_service;
    @BindView(R.id.start_intentservice)
    Button start_intentservice;

    private MyService.DownloadBinder downloadBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.start_service)
    public void startOnclick(){
        Intent startIntent = new Intent(this,MyService.class);
        startService(startIntent);
    }
    @OnClick(R.id.stop_service)
    public void stopOnclick(){
        Intent stopIntent = new Intent(this,MyService.class);
        stopService(stopIntent);
    }
    @OnClick(R.id.bind_service)
    public void bindOnclick(){
        Intent bindIntent = new Intent(this,MyService.class);
        bindService(bindIntent,connection,BIND_AUTO_CREATE);

    }
    @OnClick(R.id.unbind_service)
    public void unbindOnclick(){
        unbindService(connection);

    }
    @OnClick(R.id.start_intentservice)
    public void startIntentonClick(){
        Log.e("MyIntentService","Main thread id is :" + Thread.currentThread().getId());
        Intent intentService = new Intent(this,MyIntentService.class);
        startService(intentService);
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            downloadBinder = (MyService.DownloadBinder) iBinder;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}
