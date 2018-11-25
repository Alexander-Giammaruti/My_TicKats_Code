package com.example.alexandergiammaruti.fingerprintauthentication;

import android.app.Application;

public class Globals extends Application {
    private String fieldWorkerID;
    private LocationPolling locationPolling;

    public String getFieldWorkerID(){
        return fieldWorkerID;
    }
    public void setFieldWorkerID(String fieldWorkerID){
        this.fieldWorkerID = fieldWorkerID;
    }
    public LocationPolling getLocationPolling(){
        return locationPolling;
    }
    public void setLocationPolling(LocationPolling locationPolling){
        this.locationPolling = locationPolling;
    }
}




