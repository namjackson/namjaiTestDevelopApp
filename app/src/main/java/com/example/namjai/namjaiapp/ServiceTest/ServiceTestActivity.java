package com.example.namjai.namjaiapp.ServiceTest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.namjai.namjaiapp.MainActivity;
import com.example.namjai.namjaiapp.R;

public class ServiceTestActivity extends AppCompatActivity {

    Button btn_start;
    Button btn_end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        btn_start =(Button)findViewById(R.id.btn_start);
        btn_end =(Button)findViewById(R.id.btn_end);


        btn_start.setOnClickListener(serviceStart);
        btn_end.setOnClickListener(serviceEnd);
    }





    Button.OnClickListener serviceStart = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ServiceTestActivity.this, MyService.class);
            intent.putExtra("name","NAMJACKSON");
            intent.putExtra("count",100);
            startService(intent);
        }
    };
    Button.OnClickListener serviceEnd = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ServiceTestActivity.this, MyService.class);
            stopService(intent);
        }
    };

}
