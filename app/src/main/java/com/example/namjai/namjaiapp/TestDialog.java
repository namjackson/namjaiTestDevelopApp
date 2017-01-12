package com.example.namjai.namjaiapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by namjai on 2016-12-06.
 */
public class TestDialog extends Dialog {

    private Activity activity;
    // 상단 타이틀 내용
    private String title;
    // 버튼 리스너
    private View.OnClickListener checkBtListener;
    // 리스트뷰 어뎁터
    private ListAdapter listAdapter;
    // 닫기 버튼
    private Button btn_close;
    private Button btn_barcode;
    // 상단 타이틀뷰
    private TextView dialogTitle;
    // 리스트뷰
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 메인 layout
        setContentView(R.layout.dialog_testlist);

        btn_close = (Button) findViewById(R.id.btn_close);
        btn_barcode = (Button) findViewById(R.id.btn_barcode);
        //listView = (ListView) findViewById(R.id.ll_list);

        // 제목 설정
        dialogTitle.setText(title);
        // 리스트뷰 설정
        listView.setAdapter(listAdapter);
        // 버튼 리스너 설정
        btn_close.setOnClickListener(checkBtListener);
    }

   public TestDialog(Activity activity, String title, ListAdapter listAdapter, View.OnClickListener checkBtListener) {
       super(activity, android.R.style.Theme_Translucent_NoTitleBar);
       this.activity = activity;
       this.title = title;
       this.listAdapter = listAdapter;
       this.checkBtListener = checkBtListener;
   }


  /*  public void TestlistDialog(Context context){
        final Dialog dialog = new Dialog(context);

        //노타이틀
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_testlist);

        Button btn_barcode = (Button) dialog.findViewById(R.id.btn_barcode);
        Button btn_close = (Button) dialog.findViewById(R.id.btn_close);








        dialog.show();

    }*/
}
