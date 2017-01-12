package com.example.namjai.namjaiapp.GraphTest.baseapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.infragistics.controls.AnchoredRadialSeries;
import com.infragistics.controls.AxisLabelsLocation;
import com.infragistics.controls.BarSeries;
import com.infragistics.controls.BrushSelectionMode;
import com.infragistics.controls.CategoryAngleAxis;
import com.infragistics.controls.CategoryXAxis;
import com.infragistics.controls.CategoryYAxis;
import com.infragistics.controls.ColumnSeries;
import com.infragistics.controls.DataChartView;
import com.infragistics.controls.ItemLegendView;
import com.infragistics.controls.MarkerType;
import com.infragistics.controls.NumericRadiusAxis;
import com.infragistics.controls.NumericXAxis;
import com.infragistics.controls.NumericYAxis;
import com.infragistics.controls.OnSliceClickListener;
import com.infragistics.controls.PieChartView;
import com.infragistics.controls.RadialAreaSeries;
import com.infragistics.controls.RadialColumnSeries;
import com.infragistics.controls.RadialLineSeries;
import com.infragistics.controls.RadialPieSeries;
import com.infragistics.controls.SliceClickEvent;
import com.infragistics.controls.VerticalAlignment;
import com.infragistics.graphics.Brush;
import com.infragistics.graphics.BrushPalette;
import com.infragistics.graphics.BrushType;
import com.infragistics.graphics.GradientStop;
import com.infragistics.graphics.RadialGradientBrush;
import com.infragistics.graphics.SolidColorBrush;

import java.util.ArrayList;
import java.util.List;


/********************************************************************************
 * 프로그램 개요 : 그래프 관련 유틸리티
 *
 * 최초 작성자 : 김기만
 * 최초 작성일 : 2016-10-05
 *
 * 최종 수정자 : 김기만
 * 최종 수정일 : 2016-10-05
 *
 * 메모 : 없음
 *
 * Copyright 2016 by VisionIC Co., Ltd. Confidential and proprietary information
 * This document contains information, which is the property of VisionIC Co., Ltd,
 * and is furnished for the sole purpose of the operation and the maintenance.
 * Copyright © 2016 VisionIC.  All rights reserved.
 *
 ********************************************************************************/

public class GraphUtil {
    private static GraphUtil instance;

    private String loginId;

    private PieChartView pieGraph;
    private DataChartView columnSeriesGraph;
    private DataChartView columnSeriesGraph2;
    private DataChartView testGraph4;
    private PieChartView testGraph5;

    private DataChartView columnSeriesGraph_inven;

    /**
     * GraphUtil 생성자
     * @param context   사용하는 액티비티정보
     * @param dao       Graph DB에 접근할 Dao객체
     * @param loginId   로그인 사용자
     */
    public GraphUtil(Context context, GraphDao dao, String loginId) {

        createPieGraph(context, dao, loginId);
        createColumnSeriesGraph(context, dao, loginId);
        createColumnSeriesGraph2(context, dao, loginId);
        createRadialSeriesGraph(context, dao, loginId);
        createTestGraph5(context, dao, loginId);
    }

    /**
     * 그래프 Instance 생성자
     * @param context   사용하는 액티비티정보
     * @param dao       Graph DB에 접근할 Dao객체
     * @param loginId   로그인 사용자
     * @return
     */
    public static GraphUtil getInstance(Context context,  GraphDao dao, String loginId) {
        if(instance == null) {
            instance = new GraphUtil(context, dao, loginId);
        }
        instance.setLoginId(loginId);
        return instance;
    }

    public String getLoginId() {
        return loginId;
    }

    private void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    /**
     * PieChartView 파이 차트 그래프 생성
     * @param dao   Graph DB에 접근할 Dao객체
     * @return
     */
    public View getPieGraph(GraphDao dao) {
        List<GraphBean> data = dao.getStatusCnt(loginId);
        pieGraph.setDataSource(data);
        return pieGraph;
    }

    /**
     * pieGraph 그래프 생성
     * @param context   사용하는 액티비티정보
     * @param dao       Graph DB에 접근할 Dao객체
     * @param loginId   로그인 사용자
     */
    private void createPieGraph(Context context, GraphDao dao, String loginId) {
        List<GraphBean> data = dao.getStatusCnt(loginId);

        PieChartView pieChart = new PieChartView(context);
        SolidColorBrush brush = new SolidColorBrush();

        brush.setColor(Color.parseColor("#C62D36"));


        RelativeLayout.LayoutParams chartParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        pieChart.setLayoutParams(chartParams);

        pieChart.setDataSource(data);
        pieChart.setLabelMemberPath("Label");
        pieChart.setValueMemberPath("Value");

        pieChart.setAllowSliceExplosion(true);
        pieChart.setExplodedRadius(0.2);
        pieChart.setRadiusFactor(0.7);

        pieChart.setOnSliceClickListener(new OnSliceClickListener() {
            public void onSliceClick(PieChartView view, SliceClickEvent event) {
                event.setIsExploded(!event.getIsExploded());
                event.setIsSelected(!event.getIsSelected());
            }
        });

        //색상
        pieChart.setBrushes(getRadialGradientPalette());
        pieChart.setOutlines(getRadialGradientPalette());

        pieGraph = pieChart;

    }

    private BrushPalette getRadialGradientPalette() {
        BrushPalette b = new BrushPalette();
        b.getBrushes().add(new RadialGradientBrush(
                new GradientStop(Color.parseColor("#FF50a8be"), 0),
                new GradientStop(Color.parseColor("#FF357686"), 1)
        ));

        b.getBrushes().add(new RadialGradientBrush(
                new GradientStop(Color.parseColor("#FFfc6754"), 0),
                new GradientStop(Color.parseColor("#FFe45340"), 1)
        ));

        b.getBrushes().add(new RadialGradientBrush(
                new GradientStop(Color.parseColor("#FFedb22c"), 0),
                new GradientStop(Color.parseColor("#FFfec33c"), 1)
        ));

        b.getBrushes().add(new RadialGradientBrush(
                new GradientStop(Color.parseColor("#FF3c6399"), 0),
                new GradientStop(Color.parseColor("#FF264f88"), 1)
        ));

        b.getBrushes().add(new RadialGradientBrush(
                new GradientStop(Color.parseColor("#FF91af49"), 0),
                new GradientStop(Color.parseColor("#FF809d38"), 1)
        ));


        return b;
    }
    /**
     *
     * @param dao Graph DB에 접근할 Dao객체
     * @return
     */
    public View getColumnSeriesGraph(GraphDao dao) {
        List<GraphBean> data = dao.getStatusTaskCnt(loginId);

        columnSeriesGraph.getSeriesAt(0).setDataSource(data);
        ((CategoryYAxis)columnSeriesGraph.getAxisAt(1)).setDataSource(data);

        return columnSeriesGraph;
    }
    /**
     *
     * @param context
     * @param dao
     * @param loginId
     */
    private void createColumnSeriesGraph(Context context, GraphDao dao, String loginId) {
        List<GraphBean> data = dao.getStatusTaskCnt(loginId);

        DataChartView chart = new DataChartView(context);
        NumericXAxis xAxis = new NumericXAxis();
        CategoryYAxis yAxis = new CategoryYAxis();
        BarSeries series = new BarSeries();

        yAxis.setDataSource(data);
        yAxis.setLabel("Label");
        xAxis.setLabel("Value");
        xAxis.setTitle("작업량");

/*

        SolidColorBrush brush = new SolidColorBrush();
        brush.setColor(Color.parseColor("#C62D36"));
        yAxis.setStrip(brush);

        SolidColorBrush brush2 = new SolidColorBrush();
        brush2.setColor(Color.parseColor("#a630e5"));
        yAxis.setStroke(brush2);

        SolidColorBrush brush3 = new SolidColorBrush();
        brush3.setColor(Color.parseColor("#32f207"));
        xAxis.setStroke(brush3);

        SolidColorBrush brush4 = new SolidColorBrush();
        brush4.setColor(Color.parseColor("#c4c11f"));
        xAxis.setStrip(brush4);

*/
        chart.addAxis(xAxis);
        chart.addAxis(yAxis);

        series.setIsTransitionInEnabled(true);
        series.setXAxis(xAxis);
        series.setYAxis(yAxis);

        SolidColorBrush brush = new SolidColorBrush();
        brush.setColor(Color.parseColor("#c4c11f"));
        series.setBrush(brush);


        series.setValueMemberPath("Value");
        series.setDataSource(data);
        chart.addSeries(series);
        //chart.setBrushes(getRadialGradientPalette());

        columnSeriesGraph = chart;
    }

    /**
     *
     * @param dao
     * @return
     */
    public View getColumnSeriesGraph2(GraphDao dao) {
        List<GraphBean> data = dao.getWorkorderTaskCnt(loginId);

        columnSeriesGraph2.getSeriesAt(0).setDataSource(data);
        columnSeriesGraph2.getSeriesAt(1).setDataSource(data);
        ((CategoryXAxis)columnSeriesGraph2.getAxisAt(0)).setDataSource(data);

        return columnSeriesGraph2;
    }

    /**
     *
     * @param context
     * @param dao
     * @param loginId
     */
    private void createColumnSeriesGraph2(Context context, GraphDao dao, String loginId) {
        List<GraphBean> data = dao.getWorkorderTaskCnt(loginId);

        DataChartView chart = new DataChartView(context);
        CategoryXAxis xAxis = new CategoryXAxis();
        NumericYAxis yAxis = new NumericYAxis();


        chart.setHorizontalZoomable(true);

        xAxis.setDataSource(data);
        xAxis.setLabel("Label");
        yAxis.setTitle("작업량");
        yAxis.setMinimumValue(0);

        chart.addAxis(xAxis);
        chart.addAxis(yAxis);

        ColumnSeries columnSeries = new ColumnSeries();
        columnSeries.setThickness(TypedValue.COMPLEX_UNIT_DIP, 2);
        columnSeries.setXAxis(xAxis);
        columnSeries.setYAxis(yAxis);
        columnSeries.setDataSource(data);
        columnSeries.setValueMemberPath("Value");
        chart.addSeries(columnSeries);


        ColumnSeries columnSeries2 = new ColumnSeries();
        columnSeries2.setXAxis(xAxis);
        columnSeries2.setYAxis(yAxis);
        columnSeries2.setThickness(TypedValue.COMPLEX_UNIT_DIP, 2);
        columnSeries2.setDataSource(data);
        columnSeries2.setValueMemberPath("Value2");
        chart.addSeries(columnSeries2);


        chart.setBrushes(getRadialGradientPalette());

        columnSeriesGraph2 = chart;
    }

/////////////////////////////////////////////////////////////////////테스트그래프 4
    /**
     *
     * @param dao
     * @return
     */
    public View geteRadialSeriesGraph(GraphDao dao) {
        return testGraph4;
    }

    private void createRadialSeriesGraph(Context context, GraphDao dao, String loginId) {
        List<GraphBean> data = getData();
        DataChartView chart = new DataChartView(context);

        CategoryAngleAxis angleAxis = new CategoryAngleAxis();
        NumericRadiusAxis radiusAxis = new NumericRadiusAxis();

        //angleAxis
        angleAxis.setDataSource(data);

        SolidColorBrush brush = new SolidColorBrush();
        brush.setColor(Color.parseColor("#c4c11f"));

        angleAxis.setMajorStroke(brush);
        angleAxis.setLabel("Label");

        //radiusAxis
        radiusAxis.setCrossingValue(0.0);
        radiusAxis.setInnerRadiusExtentScale(0.1);
        radiusAxis.setRadiusExtentScale(0.8);
        radiusAxis.setMinimumValue(0);
        radiusAxis.setMaximumValue(100);
        radiusAxis.setInterval(25);
        radiusAxis.setLabelExtent(TypedValue.COMPLEX_UNIT_DIP, 30);
        radiusAxis.setLabelVerticalAlignment(VerticalAlignment.CENTER);
        radiusAxis.setLabelLocation(AxisLabelsLocation.INSIDE_BOTTOM);

        SolidColorBrush brush2 = new SolidColorBrush();
        brush2.setColor(Color.parseColor("#7f7f7f"));
        radiusAxis.setLabelTextColor(brush2);

        chart.addAxis(angleAxis);
        chart.addAxis(radiusAxis);


        //클래스만 바꿔도 그래프가 달라짐
        Class<?> seriesClass = RadialAreaSeries.class;
        //Class<?> seriesClass = RadialColumnSeries.class
        //Class<?> seriesClass = RadialLineSeries.class
        //Class<?> seriesClass = RadialPieSeries.class
        AnchoredRadialSeries series1 = GetRadialSeries(angleAxis, radiusAxis, seriesClass , data);
        AnchoredRadialSeries series2 = GetRadialSeries(angleAxis, radiusAxis, seriesClass , data);


        series1.setValueMemberPath("Value");
        series2.setValueMemberPath("Value2");
        series1.setTitle("Spending");
        series2.setTitle("Budget");

        chart.addSeries(series1);
        chart.addSeries(series2);

        chart.setTitle("Test");
        chart.setRightMargin(TypedValue.COMPLEX_UNIT_DIP, 10);
        chart.setLeftMargin(TypedValue.COMPLEX_UNIT_DIP, 25);


        testGraph4 = chart;

    }

    //임시 데이터 셋
    public static List<GraphBean> getData(){

        String[] labels = new String[] {"100,45", "90,55", "80,65", "70,75", "60,85", "50,95" };
        double[] spendingValue = new double[] { 100 , 90 , 80 , 70 , 60 , 50 };
        double[] budgetValue = new double[]  { 45 , 55 , 65 , 75 , 85 , 95 };;

        List<GraphBean> data = new ArrayList<>();

        for (int i = 0; i < labels.length; i++){
            GraphBean  bean = new GraphBean();
            bean.setLabel(labels[i]);
            bean.setValue(spendingValue[i]);
            bean.setValue2(budgetValue[i]);


            data.add(bean);

        }
        return data;
    }

    private AnchoredRadialSeries GetRadialSeries(CategoryAngleAxis angleAxis, NumericRadiusAxis radiusAxis, Class<?> SampleType, List<?> testData){

        AnchoredRadialSeries series;
        try {
            series = (AnchoredRadialSeries)SampleType.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }

        series.setAngleAxis(angleAxis);
        series.setValueAxis(radiusAxis);
        series.setValueMemberPath("spendingValue");
        series.setDataSource(testData);
        series.setTitle("Spending");
        series.setIsHighlightingEnabled(true);
        series.setThickness(TypedValue.COMPLEX_UNIT_DIP, 2);
        series.setAreaFillOpacity(.7);
        series.setAreaFillOpacity(0.5);

        String SampleTypeName = SampleType.toString().toLowerCase();

        if (SampleTypeName.contains("radiallineseries") ||
                SampleTypeName.contains("radialareaseries"))
        {
            series.setMarkerType(MarkerType.CIRCLE);
        }
        else
        {
            series.setMarkerType(MarkerType.NONE);
        }

        return series;
    }
/////////////////////////////////////////////////////////테스트 그래프 5

    public View getTestGraph5(GraphDao dao) {
        List<GraphBean> data = dao.getStatusCnt(loginId);
        testGraph5.setDataSource(data);
        return testGraph5;
    }



    private void createTestGraph5(Context context, GraphDao dao, String loginId) {
        List<GraphBean> data = dao.getStatusCnt(loginId);

        PieChartView pieChart = new PieChartView(context);

        RelativeLayout.LayoutParams chartParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        pieChart.setLayoutParams(chartParams);

        pieChart.setDataSource(data);
        pieChart.setLabelMemberPath("Label");
        pieChart.setValueMemberPath("Value");

        pieChart.setAllowSliceExplosion(true);
        pieChart.setExplodedRadius(0.2);
        pieChart.setRadiusFactor(0.7);

    pieChart.setOnSliceClickListener(new OnSliceClickListener() {
        public void onSliceClick(PieChartView view, SliceClickEvent event) {
            event.setIsExploded(!event.getIsExploded());
            event.setIsSelected(!event.getIsSelected());
        }
    });



        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        pieChart.setLayoutParams(params);

        //색상
        pieChart.setBrushes(getRadialGradientPalette());
        pieChart.setOutlines(getRadialGradientPalette());



        testGraph5 = pieChart;

}
    public ItemLegendView getTestGraph5_legend(Context context) {

        ItemLegendView legend = new ItemLegendView(context);

        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params2.gravity = Gravity.TOP | Gravity.RIGHT;
        legend.setLayoutParams(params2);

        testGraph5.setLegend(legend);

        return legend;
    }

    //////////////////////////test5


    public List<View> TestTestGraph5_test(Context context, GraphDao dao, String loginId) {
        List<GraphBean> data = dao.getStatusCnt(loginId);

        PieChartView pieChart = new PieChartView(context);

        RelativeLayout.LayoutParams chartParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        pieChart.setLayoutParams(chartParams);

        pieChart.setDataSource(data);
        pieChart.setLabelMemberPath("Label");
        pieChart.setValueMemberPath("Value");

        pieChart.setAllowSliceExplosion(true);
        pieChart.setExplodedRadius(0.2);
        pieChart.setRadiusFactor(0.7);

        pieChart.setOnSliceClickListener(new OnSliceClickListener() {
            public void onSliceClick(PieChartView view, SliceClickEvent event) {
                event.setIsExploded(!event.getIsExploded());
                event.setIsSelected(!event.getIsSelected());
            }
        });


        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        pieChart.setLayoutParams(params);

        //색상
        pieChart.setBrushes(getRadialGradientPalette());
        pieChart.setOutlines(getRadialGradientPalette());


        ItemLegendView legend = new ItemLegendView(context);

        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        params2.gravity = Gravity.TOP | Gravity.RIGHT;
        legend.setLayoutParams(params2);

        pieChart.setLegend(legend);


        List<View> viewlist = new ArrayList<>();
        viewlist.add(pieChart);
        viewlist.add(legend);
        return  viewlist;
    }

    ////////////////////////////////test6

    public View getTestGraph6_test(Context context, GraphDao dao, String loginId) {

        View d = testGraph5;
        return d;
    }
}
