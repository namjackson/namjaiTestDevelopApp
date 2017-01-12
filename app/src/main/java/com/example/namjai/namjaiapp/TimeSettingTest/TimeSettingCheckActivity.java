package com.example.namjai.namjaiapp.TimeSettingTest;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.namjai.namjaiapp.R;

public class TimeSettingCheckActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_setting_check);


        textView = (TextView)findViewById(R.id.edit_text);
        button = (Button)findViewById(R.id.button);

        button.setOnClickListener(mClickLister);
    }


    Button.OnClickListener mClickLister = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                String str="";
                int autoTime=android.provider.Settings.System.getInt(getContentResolver(), Settings.System.AUTO_TIME);
                int autoTime2=android.provider.Settings.System.getInt(getContentResolver(), Settings.System.AUTO_TIME_ZONE);

                //값
                boolean autuTimeCheck = autoTime==1 ? true : false; // 1:true(자동), 0:false(수동)
                boolean autuTimeCheck2 = autoTime2==1 ? true : false; // 1:true(자동), 0:false(수동)
                str="autuTimeCheck : "+ autuTimeCheck +" autuTimeCheck2 " + autuTimeCheck2;

                textView.setText(str);
            }catch (Settings.SettingNotFoundException e){
                e.printStackTrace();
            }


        }
    };
}
