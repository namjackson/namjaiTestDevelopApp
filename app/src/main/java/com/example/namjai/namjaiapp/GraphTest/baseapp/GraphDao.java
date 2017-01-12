package com.example.namjai.namjaiapp.GraphTest.baseapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by KKM on 2016-10-05.
 */

public class GraphDao {

    public GraphDao(Context context) {

    }

    /**
     * 상태별 작업오더 개수 조회
     * @return          작업오더 개수
     */
    public List<GraphBean> getStatusCnt(String loginId) {
        List<GraphBean> list = new ArrayList<GraphBean>();


        for(int i = 0 ; i < 5 ; i ++) {

        }
        GraphBean bean = new GraphBean();
        bean.setLabel("COMP");
        bean.setValue(10);
        list.add(bean);

        GraphBean bean2 = new GraphBean();
        bean2.setLabel("APPR");
        bean2.setValue(5);
        list.add(bean2);

        GraphBean bean3 = new GraphBean();
        bean3.setLabel("INPRG");
        bean3.setValue(2);
        list.add(bean3);

        return list;
    }

    /**
     * 작업현황별 태스크 개수 조회
     * @return          태스크 개수
     */
    public List<GraphBean> getStatusTaskCnt(String loginId) {
        List<GraphBean> list = new ArrayList<GraphBean>();

        GraphBean bean = new GraphBean();
        bean.setLabel("전체" + " TASK(" + 20 + ")");
        bean.setValue(Double.parseDouble(20 + ""));
        list.add(bean);

        GraphBean bean2 = new GraphBean();
        bean2.setLabel("진행" + " TASK(" + 9 + ")");
        bean2.setValue(Double.parseDouble(9 + ""));
        list.add(bean2);

        GraphBean bean3 = new GraphBean();
        bean3.setLabel("완료" + " TASK(" + 7 + ")");
        bean3.setValue(Double.parseDouble( 7 + ""));
        list.add(bean3);

        GraphBean bean4 = new GraphBean();
        bean4.setLabel("ADD" + " TASK(" + 4 + ")");
        bean4.setValue(Double.parseDouble( 4 + ""));
        list.add(bean4);

        return list;
    }

    public List<GraphBean> getWorkorderTaskCnt(String loginId) {


        List<GraphBean> list = new ArrayList<GraphBean>();

        GraphBean bean = new GraphBean();
        bean.setLabel("1001");
        bean.setValue(11);
        bean.setValue2(5);
        list.add(bean);

        GraphBean bean2 = new GraphBean();
        bean2.setLabel("1002");
        bean2.setValue(15);
        bean2.setValue2(11);
        list.add(bean2);

        GraphBean bean3 = new GraphBean();
        bean3.setLabel("1003");
        bean3.setValue(5);
        bean3.setValue2(3);
        list.add(bean3);



        return list;
    }


    /**
     * 전체수
     * @param loginId
     * @return
     */
    public int getInvenAllCnt(String loginId) {
       int result =50;

        return result;
    }

    /**
     * 진행수
     * @param loginId
     * @return
     */
    public int getInvenIngCnt(String loginId) {
        int result =30;

        return result;
    }
    /**
     * 진행수
     * @param loginId
     * @return
     */
    public int getInvenIngCnt_Ok(String loginId) {
        int result =29;

        return result;
    }
    /**
     * 진행수
     * @param loginId
     * @return
     */
    public int getInvenIngCnt_No(String loginId) {
        int result =20;


        return result;
    }

}
