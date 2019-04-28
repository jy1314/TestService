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
* create at 2019/4/28 下午7:16
* description:前台service Demo
*/
public class MyFrontService extends Service {
    private static final String TAG = "MyFrontService";
    public MyFrontService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {//服务创建时调用
        Log.e(TAG,"MyFrontService onCreate");
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
        Log.e(TAG,"MyFrontService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {//服务销毁时调用
        Log.e(TAG,"MyFrontService onDestroy");
        super.onDestroy();
    }
    class DownloadBinder extends Binder {
        public void startDownload(){
            Log.e(TAG,"MyFrontService startDownload");
        }
        public int getProgress(){
            Log.e(TAG,"MyFrontService getProgess");
            return 0;
        }
    }
}
