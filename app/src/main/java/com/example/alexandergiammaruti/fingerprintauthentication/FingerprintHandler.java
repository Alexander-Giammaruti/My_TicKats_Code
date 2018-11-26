package com.example.alexandergiammaruti.fingerprintauthentication;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;
import android.os.CancellationSignal;


import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    // You should use the CancellationSignal method whenever your app can no longer process user input, for example when your app goes
    // into the background. If you don’t use this method, then other apps will be unable to access the touch sensor, including the lockscreen!//


    private CancellationSignal cancellationSignal;
    private Context context;
    private MyCallback myCallback;


    public long clockInTime, clockOutTime;

    public FingerprintHandler(Context mContext, MyCallback myCallback) {
        context = mContext;
        this.myCallback = myCallback;
    }

    //Implement the startAuth method, which is responsible for starting the fingerprint authentication process//

    public void startAuth(FingerprintManager manager, FingerprintManager.CryptoObject cryptoObject) {

        cancellationSignal = new CancellationSignal();
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        manager.authenticate(cryptoObject, cancellationSignal, 0, this, null);
    }

    @Override
    //onAuthenticationError is called when a fatal error has occurred. It provides the error code and error message as its parameters//

    public void onAuthenticationError(int errMsgId, CharSequence errString) {

        //I’m going to display the results of fingerprint authentication as a series of toasts.
        //Here, I’m creating the message that’ll be displayed if an error occurs//

        Toast.makeText(context, "Authentication error\n" + errString, Toast.LENGTH_LONG).show();
    }

    @Override

    //onAuthenticationFailed is called when the fingerprint doesn’t match with any of the fingerprints registered on the device//

    public void onAuthenticationFailed() {
        Toast.makeText(context, "Authentication failed", Toast.LENGTH_SHORT).show();
    }

    @Override

    //onAuthenticationHelp is called when a non-fatal error has occurred. This method provides additional information about the error,
    //so to provide the user with as much feedback as possible I’m incorporating this information into my toast//
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Toast.makeText(context, "Authentication help\n" + helpString, Toast.LENGTH_LONG).show();
    }//@Override

    //onAuthenticationSucceeded is called when a fingerprint has been successfully matched to one of the fingerprints stored on the user’s device//
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        clockInTime = System.currentTimeMillis();
        java.util.Date d = new java.util.Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        String currentDateTImeString = DateFormat.getDateInstance().format(new java.util.Date());
        currentDateTImeString = simpleDateFormat.format(d);
        ((Globals) context.getApplicationContext()).setClockInTime(clockInTime);
        Toast.makeText(context, "Success! You clocked in at: " + currentDateTImeString, Toast.LENGTH_LONG).show();









        myCallback.onSuccess(1);

    }


}


