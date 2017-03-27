package com.example.namjai.namjaiapp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by namjai on 2017-03-08.
 */

public class Sample {

    public static void main(String[] args) throws IOException {

        String[] atr ={"a","b","c"};

        List<VO> list = new ArrayList<>();

        VO vo = new VO();

        for ( int i = 0; i<atr.length ; i ++){
            vo.setValue(atr[i]);
            list.add(vo);
        }


        for ( int i = 0; i<list.size() ; i ++){
            System.out.println(i+"번째 Vo value:"+list.get(i).getValue());
        }



        System.out.println("----------------------------------------------");

        ArrayList<String> testList1 = new ArrayList<>();
        testList1.add("test1-1");
        testList1.add("test1-2");
        testList1.add("test1-3");


        ArrayList<String> testList2 = testList1;
        testList2.add("test2-1");
        testList2.add("test2-2");


        for( int i = 0; i<testList1.size() ; i ++){
            System.out.println( i+"번째 testList1:"+testList1.get(i) );
        }
        for( int i = 0; i<testList2.size() ; i ++){
            System.out.println( i+"번째 testList2:"+testList2.get(i) );
        }
        System.out.println("-----------이상하게돔-----------------------------------");

        for( int i = 0; i<testList2.size() ; i ++){
            System.out.println( i+"번째 testList2 :"+testList2.get(i) );
            testList1.remove(i);
        }

        System.out.println("-----------무한루프-----------------------------------");

        for( int i = 0; i<testList2.size() ; i ++){
            System.out.println( i+"번째 testList2 :"+testList2.get(i) );
            //testList1.add("test1- i."+i);
        }



        //개선 방안
        /*
        ArrayList<String> testList2 = testList1; 할때
         testList2 = (ArrayList<String>)testList1.clone();
         testList2.addAll(testList1);
        */
    }

}

