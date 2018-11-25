package com.example.alexandergiammaruti.fingerprintauthentication;

import android.app.Application;
import android.content.Intent;

public class Globals extends Application {
    private String fieldWorkerID;
    private Intent locationService;

    public String getFieldWorkerID(){
        return fieldWorkerID;
    }
    public void setFieldWorkerID(String fieldWorkerID){
        this.fieldWorkerID = fieldWorkerID;
    }
    public Intent getLocationService(){
        return locationService;
    }
    public void setLocationService(Intent locationService){
        this.locationService = locationService;
    }
}




