package com.example.namjai.namjaiapp.FlashTest;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;

import java.util.List;

/**
 * Created by namjai on 2016-12-28.
 */

public class FlashUtil {
    Boolean isFlash;                //카메라 객체가 있는지 여부

    static private Boolean isTorchOn= false;        //현재 켜져잇는지 꺼져있는지

    //카메라   신 ver api23 이상
    CameraManager cameraManager = null;
    String cameraId;

    //카메라   구 ver api23 이하
    Camera camera = null;
    Camera.Parameters cameraParameter;

    /**
     * 카메라 객체 생성
     * @param context
     */
    FlashUtil(Context context){
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
                isFlash =  true;
            }   else {
                camera = Camera.open();
                isFlash =  true;
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 카메라 객체 제거
     */
    public void cameraOff(){
        if (camera != null) {
            camera.release();
            camera=null;
        }
        if (cameraManager != null) {
            if(isTorchOn){
                flashOnOff(false);
            }
            cameraManager=null;
        }
        isFlash=false;
    }

    public Boolean getIsFlash(){
        return isFlash;
    }

    /**
     * 플래시 지원의 유무
     * api 23이상인지 이후인지 체크후 로직처리
     * @param context
     * @return boolean 플래시사용가능여부
     */
    public boolean hasFlash(Context context) {
        boolean hasFlash = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
             try{
                cameraId = cameraManager.getCameraIdList()[0];
                hasFlash = cameraManager.getCameraCharacteristics(cameraId).get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
            }catch ( Exception e){
                 e.getMessage();
                 e.printStackTrace();
            }
        }else{
            if (camera == null) {
                return false;
            }
            Camera.Parameters parameters = camera.getParameters();
            if (parameters.getFlashMode() == null) {
                return false;
            }
            List<String> supportedFlashModes = parameters.getSupportedFlashModes();
            if (supportedFlashModes == null || supportedFlashModes.isEmpty() || supportedFlashModes.size() == 1 && supportedFlashModes.get(0).equals(Camera.Parameters.FLASH_MODE_OFF)) {
                return false;
            }
            hasFlash=true;
        }
        return hasFlash;
    }


    /**
     * 플래시 기능 On - Off
     * api 23이상인지 이후인지 체크후 로직처리
     * @param onOff 플래시기능 On Off 여부
     */
    public void flashOnOff(boolean onOff){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try{
                cameraId = cameraManager.getCameraIdList()[0];
                cameraManager.setTorchMode(cameraId, onOff);
            }catch (Exception e){e.printStackTrace(); }
        } else {
            Camera.Parameters mCameraParameter = camera.getParameters();
            if(onOff){
                mCameraParameter.setFlashMode(mCameraParameter.FLASH_MODE_TORCH);
                camera.setParameters(mCameraParameter);
            }else {
                mCameraParameter.setFlashMode(mCameraParameter.FLASH_MODE_OFF);
                camera.setParameters(mCameraParameter);
            }
        }
        this.isTorchOn = onOff;
    }


}
