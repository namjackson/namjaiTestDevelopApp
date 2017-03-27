package com.example.namjai.namjaiapp.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.namjai.namjaiapp.R;
import com.example.namjai.namjaiapp.location.adapter.DividerItemDecoration;
import com.example.namjai.namjaiapp.location.adapter.RecycleAdapter;
import com.example.namjai.namjaiapp.location.bean.locationBean;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity { private RecyclerView lecyclerView;
    private RecycleAdapter recycleAdapter;
    LinearLayoutManager linearLayoutManager;
    DividerItemDecoration dividerItemDecoration;

    List<locationBean> locationList = new ArrayList<>();

    TextView text_current;
    ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        check();
        //리싸이클
        lecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        dividerItemDecoration = new DividerItemDecoration(getApplicationContext());

        linearLayoutManager =new LinearLayoutManager(getApplicationContext());

        recycleAdapter = new RecycleAdapter(locationList,R.layout.renderer_locationlist);
        lecyclerView.setAdapter(recycleAdapter);
        lecyclerView.setLayoutManager(linearLayoutManager);
        lecyclerView.setItemAnimator(new DefaultItemAnimator());
        lecyclerView.addItemDecoration(dividerItemDecoration);

        //view
        text_current = (TextView) findViewById(R.id.text_current);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);

        //location
        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        List<String> list = lm.getAllProviders(); // 위치제공자 모두 가져오기

        String str = ""; // 출력할 문자열
        for (int i = 0; i < list.size(); i++) {
            str += "위치제공자 : " + list.get(i) + ", 사용가능여부 -"
                    + lm.isProviderEnabled(list.get(i)) +"\n";
        }
        text_current.setText(str);



        toggleButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
//http://gobawi.tistory.com/45
                try{
                    if(toggleButton.isChecked()){
                        text_current.setText("수신중..");
                        // GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
                        System.out.println("check1");
                        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                                100, // 통지사이의 최소 시간간격 (miliSecond)
                                1, // 통지사이의 최소 변경거리 (m)
                                mLocationListener);
                        System.out.println("check2");
                        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, // 등록할 위치제공자
                                100, // 통지사이의 최소 시간간격 (miliSecond)
                                1, // 통지사이의 최소 변경거리 (m)
                                mLocationListener);

                        System.out.println("check3");

                    }else{
                        text_current.setText("위치정보 미수신중");
                        lm.removeUpdates(mLocationListener);  //  미수신할때는 반드시 자원해체를 해주어야 한다.
                    }
                    System.out.println("check4");
                }catch(SecurityException ex){
                    System.out.println("check5");
                    ex.printStackTrace();
                    System.out.println(ex.getMessage());
                }
                System.out.println("check6");



            }
        });
    }



    public final LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            //여기서 위치값이 갱신되면 이벤트가 발생한다.
            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

            Log.d("test", "onLocationChanged, location:" + location);
            double longitude = location.getLongitude(); //경도
            double latitude = location.getLatitude();   //위도
            double altitude = location.getAltitude();   //고도
            float accuracy = location.getAccuracy();    //정확도
            String provider = location.getProvider();   //위치제공자

            //Gps 위치제공자에 의한 위치변화. 오차범위가 좁다.
            //Network 위치제공자에 의한 위치변화
            //Network 위치는 Gps에 비해 정확도가 많이 떨어진다.
            text_current.setText("위치정보 : " + provider + "\n위도 : " + longitude + "\n경도 : " + latitude
                    + "\n고도 : " + altitude + "\n정확도 : "  + accuracy);
            locationBean bean = new locationBean();
            bean.setLongitude(longitude);
            bean.setLatitude(latitude);
            bean.setAltitude(altitude);
            bean.setAccuracy(accuracy);
            bean.setProvider(provider);

            recycleAdapter.add(bean);

        }
        public void onProviderDisabled(String provider) {
            // Disabled시
            Log.d("test", "onProviderDisabled, provider:" + provider);
        }

        public void onProviderEnabled(String provider) {
            // Enabled시
            Log.d("test", "onProviderEnabled, provider:" + provider);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
            // 변경시
            Log.d("test", "onStatusChanged, provider:" + provider + ", status:" + status + " ,Bundle:" + extras);
        }
    };

    public void check(){
        ///////////////
        // Here, thisActivity is the current activity

        System.out.println("1");
        int coarse_permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int fine_permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("2");
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                System.out.println("3");
                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                System.out.println("4");
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
        System.out.println("4.5");
        ////////

//        boolean result = false;
//
//        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.M) {
//
//
//                    /* 사용자 단말기의 권한 중 "전화걸기" 권한이 허용되어 있는지 체크한다.
//                    *  int를 쓴 이유? 안드로이드는 C기반이기 때문에, Boolean 이 잘 안쓰인다.
//                    */
//            int permissionResult = checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION);
//
//                    /* CALL_PHONE의 권한이 없을 때 */
//            // 패키지는 안드로이드 어플리케이션의 아이디다.( 어플리케이션 구분자 )
//            if (permissionResult == PackageManager.PERMISSION_DENIED) {
//
//
//                        /* 사용자가 CALL_PHONE 권한을 한번이라도 거부한 적이 있는 지 조사한다.
//                        * 거부한 이력이 한번이라도 있다면, true를 리턴한다.
//                        */
//                if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
//
//                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
//                    dialog.setTitle("권한이 필요합니다.")
//                            .setMessage("이 기능을 사용하기 위해서는 단말기의 \"전화걸기\" 권한이 필요합니다. 계속하시겠습니까?")
//                            .setPositiveButton("네", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//
//                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
//                                    }
//
//                                }
//                            })
//                            .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Toast.makeText(MainActivity.this, "기능을 취소했습니다.", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .create()
//                            .show();
//                }
//
//                //최초로 권한을 요청할 때
//                else {
//                    // CALL_PHONE 권한을 Android OS 에 요청한다.
//                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
//                }
//
//            }
//                    /* CALL_PHONE의 권한이 있을 때 */
//            else {
//                result = true;
//            }
//
//        }
//                /* 사용자의 OS 버전이 마시멜로우 이하일 떄 */
//        else {
//            result = true;
//        }
//
//
//        return result;



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000) {

            /* 요청한 권한을 사용자가 "허용"했다면 인텐트를 띄워라
                내가 요청한 게 하나밖에 없기 때문에. 원래 같으면 for문을 돈다.*/
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }else {
                Toast.makeText(LocationActivity.this, "권한 요청을 거부했습니다.", Toast.LENGTH_SHORT).show();
            }


        }



    }
}
