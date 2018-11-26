package com.example.alexandergiammaruti.fingerprintauthentication;

import android.app.Application;
import android.content.Intent;

public class Globals extends Application {

    private double clockInTime;
    private double clockOutTime;
    private String fieldWorkerID;
    private String firstName;
    private String lastName;
    private String hoursWorked;
    private Intent locationService;

    public double getClockInTime(){ return clockInTime;}
    public void setClockInTime(double clockInTime){this.clockInTime = clockInTime;}
    public double getClockOutTime(){ return clockOutTime;}
    public void setClockOutTime(double clockOutTime){this.clockOutTime = clockOutTime;}
    public String getFieldWorkerID(){
        return fieldWorkerID;
    }
    public void setFieldWorkerID(String fieldWorkerID){
        this.fieldWorkerID = fieldWorkerID;
    }
    public String getFirstName(){return firstName;}
    public void setFirstName(String firstName){this.firstName = firstName;}
    public String getLastName(){return lastName;}
    public void setLastName(String lastName){this.lastName = lastName;}
    public String getHoursWorked(){return hoursWorked;}
    public void setHoursWorked(String hoursWorked){this.hoursWorked = hoursWorked;}
    public Intent getLocationService(){
        return locationService;
    }
    public void setLocationService(Intent locationService){
        this.locationService = locationService;
    }
}




