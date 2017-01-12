package com.example.namjai.namjaiapp.FlashTest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.namjai.namjaiapp.R;

public class Flash2Activity extends AppCompatActivity {

    //온오프버튼
    FlashUtil flashUtil;

    ToggleButton toggleButton;
    Switch switch1;

    TextView textView;
    Button btn_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash2);

        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        switch1 = (Switch) findViewById(R.id.switch1);

        switch1.setOnCheckedChangeListener(serviceSwitch);
        toggleButton.setOnCheckedChangeListener(serviceToggle);

        btn_intent = (Button) findViewById(R.id.btn_intent);
        btn_intent.setOnClickListener(intentButton);

        textView = (TextView)findViewById(R.id.textView);

    }

    @Override
    protected void onPause() {
        flashUtil.cameraOff();
        super.onPause();

    }
    @Override
    protected void onResume() {
        super.onResume();
        flashUtil = new FlashUtil(this);
        textView.setText("Flash 2 : "+flashUtil.hasFlash(this));

    }

    Switch.OnCheckedChangeListener serviceSwitch = new CompoundButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            flashUtil.flashOnOff(isChecked);

            if (isChecked == true){

            } else {
            }

        }
    };

    ToggleButton.OnCheckedChangeListener serviceToggle = new CompoundButton.OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            flashUtil.flashOnOff(isChecked);

            if (isChecked == true){

            } else {
            }

        }
    };

    Button.OnClickListener intentButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Flash2Activity.this, FlashActivity.class);
            startActivity(intent);
            finish();
        }
    };

}
