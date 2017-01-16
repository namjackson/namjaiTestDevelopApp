package com.example.namjai.namjaiapp.firebase;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import com.example.namjai.namjaiapp.FlashTest.FlashActivity;
import com.example.namjai.namjaiapp.GraphTest.GraphActivity;
import com.example.namjai.namjaiapp.MainActivity;
import com.example.namjai.namjaiapp.R;
import com.google.firebase.messaging.*;

import java.util.Map;

/**
 * Created by namjai on 2017-01-11.
 */

public class MyFcmListenerService extends com.google.firebase.messaging.FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage message) {
        System.out.println("received message : " + message);

        String from = message.getFrom();
        Map<String, String> data = message.getData();

        String title = data.get("title");
        String msg = data.get("message");

        // 전달 받은 정보로 뭔가를 하면 된다.

        //
        // Sets up the Snooze and Dismiss action buttons that will appear in the
        // big view of the notification.
        Intent dismissIntent = new Intent(this, GraphActivity.class);
        dismissIntent.setAction("Graph");
        PendingIntent piDismiss = PendingIntent.getService(this, 0, dismissIntent, 0);

        Intent snoozeIntent = new Intent(this, FlashActivity.class);
        snoozeIntent.setAction("flash");
        PendingIntent piSnooze = PendingIntent.getService(this, 0, snoozeIntent, 0);
        //////

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher) )
                .setContentTitle("Title : "+title)
                .setContentText("내용 : "+msg) //
                .setAutoCancel(true)
                .setSound(defaultSoundUri).setLights(000000255,500,2000)
                .setContentIntent(pendingIntent)
                //확대스타일
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText(msg + "\n" +message.toString()))
                .addAction (R.mipmap.ic_test1,
                        getString(R.string.graph), piDismiss)
                .addAction (R.mipmap.ic_test2,
                        getString(R.string.flash), piSnooze);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakelock.acquire(5000);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
