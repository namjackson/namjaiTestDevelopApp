package com.example.namjai.namjaiapp.ServiceTest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG= "MyService";
    boolean isRunning;
    int mCount;

    @Override
    public void onCreate() {
         // OnCreate는 이미 존재하는 서비스는 재생성되지않음 대신 onStartCommand실행
        super.onCreate();
        isRunning = true;
        mCount = 0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRunning){
                    Log.i(TAG,"###Service1 : "+ mCount);
                    mCount++;
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public MyService() {
    }

    //onbind
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //onstart
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
        //onStartCommand는 Oncreate대신 실행됨.
        if(intent != null){
            String name = intent.getStringExtra("name");
            int count = intent.getIntExtra("count",0);
            Log.i(TAG,startId+" : "+ name +count );
        }
        return Service.START_NOT_STICKY;
        //죽으면 다시 띄워줄필요없음..
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
}
