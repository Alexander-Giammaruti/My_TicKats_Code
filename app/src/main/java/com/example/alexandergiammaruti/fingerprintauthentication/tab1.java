package com.example.alexandergiammaruti.fingerprintauthentication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alexandergiammaruti.fingerprintauthentication.Login;
import com.example.alexandergiammaruti.fingerprintauthentication.R;


public class tab1 extends Fragment {
    private static final String TAG="tab1";
    private Context context;
    private TextView emplID,empName,empLastName,hoursworked;
    private ImageView profilepic;
    private Button Logout, ClockIn;
    private EditText username,password;
    public static String fid,fname,lname,hrs;







    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstantState){

        View view= inflater.inflate(R.layout.fragment_tab1,container,false);




        emplID= (TextView) view.findViewById(R.id.etEmpID);
        empName= (TextView) view.findViewById(R.id.etEmpName);
        empLastName= (TextView) view.findViewById(R.id.etEmpLastName);
        hoursworked= (TextView) view.findViewById(R.id.etsumHours);
        profilepic= view.findViewById(R.id.etProfilePic);
        Logout=view.findViewById(R.id.etLogout);
        ClockIn=view.findViewById(R.id.etclockin);

        emplID.setText(fid);
        empName.setText(fname);
        empLastName.setText(lname);
        hoursworked.setText(hrs);

        ClockIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fingerprintIntent=new Intent(getActivity(),FingerprintAuthenticationActivity.class);
                startActivity(fingerprintIntent);

            }
        });


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),Login.class);
                startActivity(intent);
                double clockOutTime = System.currentTimeMillis();
                double HoursWorked =(((((Globals) FingerprintAuthenticationActivity.context.getApplicationContext()).getClockInTime())/clockOutTime)/3600000);
                BackgroundWorker backgroundWorker = new BackgroundWorker(context);
                backgroundWorker.execute("update_hours", Double.toString(HoursWorked),((Globals) FingerprintAuthenticationActivity.context.getApplicationContext()).getFieldWorkerID());
            }
        });



        return view;
    }


}
