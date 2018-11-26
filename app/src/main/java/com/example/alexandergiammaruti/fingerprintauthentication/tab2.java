package com.example.alexandergiammaruti.fingerprintauthentication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.annotation.Nullable;

import com.example.alexandergiammaruti.fingerprintauthentication.BackgroundWorker;
import com.example.alexandergiammaruti.fingerprintauthentication.R;


public class tab2 extends Fragment {
    private Context context = getContext();
    private static final String TAG="Tab1Fragment";

    // Arrays that hold the ticket data
    public static ArrayList<String> mTicketID = new ArrayList<>();
    public static ArrayList<String> mWorksite = new ArrayList<>();
    public static ArrayList<String> mPriority = new ArrayList<>();
    private String FWid;
    private TextView TID;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstantState){
        View view= inflater.inflate(R.layout.fragment_tab2,container,false);

            // Initialize Vew Ticket Button Will pass ticket ID to View full ticket module
            Button ViewFullTicket = (Button)view.findViewById(R.id.ViewTicketButton);
            TID = (TextView)view.findViewById(R.id.TicketIdEntry);
            ViewFullTicket.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    String FWid = TID.getText().toString(); // FWid will need to be changed to accept input from login
                    BackgroundWorker backgroundWorker = new BackgroundWorker(getActivity());
                    backgroundWorker.execute("TicketStart", FWid);
                    Intent intent = new Intent(getContext(), TicketDetails.class);
                    startActivity(intent);
                }
            });


            //This is where you will get the ticket ID
            view.findViewById(R.id.TicketIdEntry);

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initRecyclerView(view);
            return view;
        }

    @Override
    public void onPause() {
        super.onPause();
        mTicketID.clear();
        mWorksite.clear();
        mPriority.clear();
    }

        private void initRecyclerView (View view){
            RecyclerView TicketViewer = view.findViewById(R.id.TicketViewer);
            RelativeRecyclerAdapter adapter = new RelativeRecyclerAdapter(mTicketID, mWorksite, mPriority);
            TicketViewer.setAdapter(adapter);
            TicketViewer.setLayoutManager(new LinearLayoutManager(getActivity()));

        }


}



