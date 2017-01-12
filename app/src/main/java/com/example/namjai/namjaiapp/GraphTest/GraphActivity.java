package com.example.namjai.namjaiapp.GraphTest;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.namjai.namjaiapp.GraphTest.baseapp.GraphBean;
import com.example.namjai.namjaiapp.GraphTest.baseapp.GraphDao;
import com.example.namjai.namjaiapp.GraphTest.baseapp.GraphUtil;
import com.example.namjai.namjaiapp.R;

import java.util.ArrayList;
import java.util.List;

public class GraphActivity extends AppCompatActivity {
    protected Context context = this;
    protected String loginId;
    protected GraphDao graphDao;

    protected LinearLayout llGraphWo1;              // 그래프1
    protected LinearLayout llGraphWo2;              // 그래프2
    protected LinearLayout llGraphWo3;              // 그래프3
    protected TextView tvGraphWo1All;
    protected TextView tvGraphWo1Appr;
    protected TextView tvGraphWo1Inprg;
    protected TextView tvGraphWo1Comp;

    protected LinearLayout llGraphTest4;              // 테스트1
    protected LinearLayout llGraphTest5;              // 테스트2
    protected LinearLayout llGraphTest6;              // 테스트3


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginId= "Hi";
        graphDao = new GraphDao(context);

        setContentView(R.layout.activity_graph);

        llGraphWo1 = (LinearLayout) findViewById(R.id.ll_graph1);              // 그래프1
        llGraphWo2 = (LinearLayout) findViewById(R.id.ll_graph2);              // 그래프2
        llGraphWo3 = (LinearLayout) findViewById(R.id.ll_graph3);              // 그래프3
        tvGraphWo1All = (TextView) findViewById(R.id.tv_graph1_all);
        tvGraphWo1Appr = (TextView) findViewById(R.id.tv_graph1_appr);
        tvGraphWo1Inprg = (TextView) findViewById(R.id.tv_graph1_inprg);
        tvGraphWo1Comp = (TextView) findViewById(R.id.tv_graph1_comp);


        llGraphTest4 = (LinearLayout) findViewById(R.id.ll_graph4);              // 그래프1
        llGraphTest5 = (LinearLayout) findViewById(R.id.ll_graph5);              // 그래프2
        llGraphTest6 = (LinearLayout) findViewById(R.id.ll_graph6);


    }

    @Override
    protected void onResume() {
        super.onResume();
        initWoGraph();
    }

    @Override
    protected void onPause() {
        llGraphWo1.removeAllViews();
        llGraphWo2.removeAllViews();
        llGraphWo3.removeAllViews();
        llGraphTest4.removeAllViews();
        llGraphTest5.removeAllViews();
        llGraphTest6.removeAllViews();


        super.onPause();
    }


    /**
     * WO 그래프 초기화
     */
    public void initWoGraph() {

        List<GraphBean> graph1Data = graphDao.getStatusCnt(loginId);
        int woCnt = 0;
        for(int i = 0; i < graph1Data.size(); i++) {
            String label = graph1Data.get(i).getLabel();
            int value = (int) graph1Data.get(i).getValue();
            if("APPR".equals(label.toUpperCase())) {
                tvGraphWo1Appr.setText(value+"");
            } else if("INPRG".equals(label.toUpperCase())) {
                tvGraphWo1Inprg.setText(value+"");
            } else if("COMP".equals(label.toUpperCase())) {
                tvGraphWo1Comp.setText(value+"");
            }
            woCnt += value;

        }
        tvGraphWo1All.setText(woCnt+"");

        GraphUtil util = GraphUtil.getInstance(context, graphDao, loginId);

        llGraphWo1.addView(util.getPieGraph(graphDao));
        llGraphWo2.addView(util.getColumnSeriesGraph(graphDao));
        llGraphWo3.addView(util.getColumnSeriesGraph2(graphDao));
        llGraphTest4.addView(util.geteRadialSeriesGraph(graphDao));
        llGraphTest5.addView(util.getTestGraph5(graphDao));
        llGraphTest5.addView(util.getTestGraph5_legend(context));

        List<View> viewlist = new ArrayList<>();

        viewlist= util.TestTestGraph5_test(context, graphDao, loginId);
        llGraphTest6.addView(viewlist.get(0));
        llGraphTest6.addView(viewlist.get(1));

    }


}
