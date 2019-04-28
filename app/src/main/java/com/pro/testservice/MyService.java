package com.pro.testservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/*
* @author Jerry
* create at 2019/4/27 下午10:07
* description:测试用service
*/
public class MyService extends Service {
    private static final String TAG = "MyService";
    private DownloadBinder mBinder = new DownloadBinder();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    @Override
    public void onCreate() {//服务创建时调用
        Log.e(TAG,"myService onCreate");
        super.onCreate();
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0 ,intent ,0);
        Notification notification = new NotificationCompat.Builder(this,"default")
                .setContentTitle("this ic content title")
                .setContentText("this is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1,notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {//服务每次启动时调用
        Log.e(TAG,"myService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {//服务销毁时调用
        Log.e(TAG,"myService onDestroy");
        super.onDestroy();
    }
    class DownloadBinder extends Binder {
        public void startDownload(){
            Log.e(TAG,"startDownload");
        }
        public int getProgress(){
            Log.e(TAG,"getProgess");
            return 0;
        }
    }
}
