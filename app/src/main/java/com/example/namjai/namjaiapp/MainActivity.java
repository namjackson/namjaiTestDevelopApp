package com.example.namjai.namjaiapp;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.namjai.namjaiapp.FlashTest.FlashActivity;

import com.example.namjai.namjaiapp.firebase.NewMessageNotificationPic;
import com.example.namjai.namjaiapp.firebase.NewMessageNotification_More;
//import com.example.namjai.namjaiapp.permission.PermissionActivity;
import com.example.namjai.namjaiapp.GraphTest.GraphActivity;
import com.example.namjai.namjaiapp.ServiceTest.ServiceTestActivity;
import com.example.namjai.namjaiapp.TimeSettingTest.TimeSettingCheckActivity;

import com.example.namjai.namjaiapp.location.LocationActivity;
import com.example.namjai.namjaiapp.sampleimage.ItemListActivity;
import com.example.namjai.namjaiapp.firebase.NewMessageNotification;
import com.example.namjai.namjaiapp.sampleimage.ScrollingActivity;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    Button btn_flash; // 플래시 이동
    Button btn_permissions; // 권한제어
    Button btn_graph; // 그래프

    Button btn_service; // 그래프
    Button btn_timesetting; // 그래프

    Button btn_location;  // 버튼

    Button button_dialog;

    Button btn_etc;

    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_flash = (Button)findViewById(R.id.btn_flash);
        btn_flash.setOnClickListener(flashClickLister);

        btn_permissions = (Button) findViewById(R.id.btn_permissions);
        btn_permissions.setOnClickListener(permisionClickLister);

        btn_graph = (Button) findViewById(R.id.btn_graph);
        btn_graph.setOnClickListener(graphClickLister);

        btn_service = (Button) findViewById(R.id.btn_service);
        btn_service.setOnClickListener(serviceClickLister);

        btn_timesetting = (Button) findViewById(R.id.btn_timesetting);
        btn_timesetting.setOnClickListener(timesettingClickLister);

        btn_etc = (Button) findViewById(R.id.btn_etc);
        btn_etc.setOnClickListener(subsribeClickLister);

        btn_location = (Button)findViewById(R.id.btn_location);
        btn_location.setOnClickListener(locationClickLister);

        System.out.println("token!! ##token :dasdasd");
        String token = FirebaseInstanceId.getInstance().getToken();
        // 이 token을 서버에 전달 한다.
        System.out.println("token!! ##token : "+ token);

        //알람
        FirebaseMessaging.getInstance().subscribeToTopic("notice");
        FirebaseMessaging.getInstance().subscribeToTopic("dd");
        //  FirebaseMessaging.getInstance().send();
        //FirebaseMessaging.getInstance().unsubscribeFromTopic("");




        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(btn_1ClickLister);

        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(btn_2ClickLister);

        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(btn_3ClickLister);

        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(btn_4ClickLister);

        btn_5 = (Button) findViewById(R.id.btn_5);
        btn_5.setOnClickListener(btn_5ClickLister);








    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 플래시이동
     */
    Button.OnClickListener flashClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, FlashActivity.class);
            startActivity(intent);
        }
    };
    Button.OnClickListener permisionClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(MainActivity.this, PermissionActivity.class);
//            startActivity(intent);
        }
    };
    Button.OnClickListener graphClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, GraphActivity.class);
            startActivity(intent);
        }
    };
    Button.OnClickListener serviceClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ServiceTestActivity.class);
            startActivity(intent);
        }
    };
    Button.OnClickListener timesettingClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, TimeSettingCheckActivity.class);
            startActivity(intent);
        }
    };
    Button.OnClickListener subsribeClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // [START subscribe_topics]
            FirebaseMessaging.getInstance().subscribeToTopic("news");
            // [END subscribe_topics]

            // Log and toast
            String msg = "News";
            System.out.println("msg : " + msg);
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    };

    Button.OnClickListener locationClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, LocationActivity.class);
            startActivity(intent);
        }
    };




    Button.OnClickListener btn_1ClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ScrollingActivity.class);
            startActivity(intent);
        }
    };
    Button.OnClickListener btn_2ClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, ItemListActivity.class);
            startActivity(intent);
        }
    };
    Button.OnClickListener btn_3ClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ///Intent intent = new Intent(MainActivity.this, ItemDetailActivity.class);
            //startActivity(intent);
            NewMessageNotification.notify(MainActivity.this,"알람발생",999);
        }
    };
    Button.OnClickListener btn_4ClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(MainActivity.this, FlashActivity.class);
            //startActivity(intent);
            NewMessageNotification_More.notify(MainActivity.this,"알람발생",999);
        }
    };
    Button.OnClickListener btn_5ClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(MainActivity.this, FlashActivity.class);
            //startActivity(intent);
           //
            NewMessageNotificationPic.notify(MainActivity.this,"알람발생",999);
        }
    };










}
