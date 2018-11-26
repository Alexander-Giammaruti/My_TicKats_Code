package com.example.alexandergiammaruti.fingerprintauthentication;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;


/*
class location{
    public float[] lonLat;
}
*/



public class LocationPolling extends Service {


    public static final String LOCATION_POLLING_TASK = "Location_Polling_Task";
    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        LocationPolling getService() {
            return LocationPolling.this;
        }
    }

    Context context;
    final int POLLING_GAP = 1000 * 60; //five minutes in milliseconds

    /*
    public LocationPolling(Context context){
        this.context=context;
    }
    */

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i("EXIT", "Service destroyed! boo");
        Intent broadcastIntent = new Intent(this, LocationPollingRestarterBroadcastReceiver.class);
        sendBroadcast(broadcastIntent);
    }

    @Override
    public IBinder onBind(Intent intent){
        return mBinder;
    }


    @Override
    public int onStartCommand(final Intent intent, int flags, int startID) {

        super.onStartCommand(intent, flags, startID);

        //get a reference to the system Location Manager
        LocationManager locationManager =(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        final int userID = intent.getIntExtra("user_id", 0);

        //define a Location Listener that responds to location updates
        final LocationListener locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {
                //called when a new location is found by the network location provider
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                //send location data to the database
                BackgroundWorker backgroundWorker = new BackgroundWorker(FingerprintAuthenticationActivity.context);
                backgroundWorker.execute("update_location", Double.toString(longitude), Double.toString(latitude));

            }//end onLocationChanged

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }//end onStatusChanged

            @Override
            public void onProviderEnabled(String provider) {
                //Toast.makeText( context, "Gps Enabled", Toast.LENGTH_SHORT).show();
            }//end onProviderEnabled

            @Override
            public void onProviderDisabled(String provider) {
                //Toast.makeText( context, "Gps Disabled", Toast.LENGTH_SHORT ).show();
            }//end onProviderDisabled
        };//end LocationListener

        try {
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 1000 * 60 * 5, 0, locationListener, Looper.getMainLooper());
            String LocationProvider = locationManager.GPS_PROVIDER;
            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationProvider);
        }catch(SecurityException e){Toast.makeText(context, "" + e, Toast.LENGTH_LONG).show();}

        return START_STICKY;
    }//end onStartCommand()

    /*
    protected boolean isBetterLocation(Location location, Location currentBestLocation){
        if(currentBestLocation == null){
            //new location is better than no location at all
            return true;
        }//end if

        //find out if it is older or newer
        long timeDiff = location.getTime() - currentBestLocation.getTime();
        boolean isSignigicantlyNewer = timeDiff > POLLING_GAP;
        boolean isSignificantlyOlder = timeDiff < -POLLING_GAP;
        boolean isNewer = timeDiff > 0;

        if(isSignigicantlyNewer){
            return true;
        }else if(isSignificantlyOlder){
            return false;
        }//end if

        //check for accuracy of the current best location
        int accuracyDiff = (int)(location.getAccuracy() - currentBestLocation.getAccuracy());
        boolean isLessAccurate = accuracyDiff > 0;
        boolean isMoreAccurate = accuracyDiff < 0;
        boolean isSignificantlyLessAccurate = accuracyDiff > 200;

        //check if the old and new location are from the same location provider
        boolean isFromSameProvider = isSameProvider(location.getProvider(), currentBestLocation.getProvider());

        //determine location quality using a combination of time differences and location accuracy
        if(isMoreAccurate){
            return true;
        }else if(isNewer && !isLessAccurate){
            return true;
        }else if(isNewer && !isSignificantlyLessAccurate && isFromSameProvider){
            return true;
        }
        return false;
    }//end isBetterLocation

    private boolean isSameProvider(String provider1, String provider2){
        if(provider1 == null){
            return provider2 == null;
        }
        return provider1.equals(provider2);
    }
    */
}// end LocationPolling
