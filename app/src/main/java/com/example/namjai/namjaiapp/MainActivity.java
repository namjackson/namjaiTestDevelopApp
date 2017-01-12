package com.example.namjai.namjaiapp;

import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.namjai.namjaiapp.FlashTest.FlashActivity;

import com.example.namjai.namjaiapp.permission.PermissionActivity;
import com.example.namjai.namjaiapp.GraphTest.GraphActivity;
import com.example.namjai.namjaiapp.ServiceTest.ServiceTestActivity;
import com.example.namjai.namjaiapp.TimeSettingTest.TimeSettingCheckActivity;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    Button btn_flash; // 플래시 이동
    Button btn_permissions; // 권한제어
    Button btn_graph; // 그래프

    Button btn_service; // 그래프
    Button btn_timesetting; // 그래프

    Button button_dialog;






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


        //String token = FirebaseInstanceId.getInstance().getToken();
        // 이 token을 서버에 전달 한다.

        //알람
        FirebaseMessaging.getInstance().subscribeToTopic("notice");





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
            Intent intent = new Intent(MainActivity.this, PermissionActivity.class);
            startActivity(intent);
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






}
