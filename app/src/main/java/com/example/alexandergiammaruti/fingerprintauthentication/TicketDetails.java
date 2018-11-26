package com.example.alexandergiammaruti.fingerprintauthentication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.alexandergiammaruti.fingerprintauthentication.R;

import java.util.ArrayList;

public class TicketDetails extends AppCompatActivity {

    // Arrays to hold data in RecyclerViews (scroll boxes)
    public static ArrayList<String> mArr1 = new ArrayList<>();
    public static ArrayList<String> mArr2 = new ArrayList<>();
    public static ArrayList<String> mArr3 = new ArrayList<>(); //End Heavy Equipment arrays
    public static ArrayList<String> mArr4 = new ArrayList<>();
    public static ArrayList<String> mArr5 = new ArrayList<>();
    public static ArrayList<String> mArr6 = new ArrayList<>(); //End Tools (light equip) arrays
    public static ArrayList<String> mArr7 = new ArrayList<>();
    public static ArrayList<String> mArr8 = new ArrayList<>();
    public static ArrayList<String> mArr9 = new ArrayList<>(); //End Worker arrays
    public static ArrayList<String> mArr10 = new ArrayList<>();
    public static ArrayList<String> mArr11 = new ArrayList<>();
    public static ArrayList<String> mArr12 = new ArrayList<>();
    public static String JobAddress,DateAdded,Prior;
    @Override
    //onCreate:= main
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);

        //Adds data to RecyclerView boxes - TID passed from Brendan's screen should be passed
        AddInfo1(/*TID*/);
        AddInfo2(/*TID*/);
        AddInfo3(/*TID*/);

        //Puts thread to sleep for minimal time to allow data to be added.
        try {
            Thread.sleep(900);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        //positions them in format: RecycleView1 = Heavy Equip view; 2= Tools view; 3=Workers view*/
        RecycleView1();
        RecycleView2();
        RecycleView3();

        //Populate header of Ticket Detail page
        populateHeader();

    }

    private void RecycleView1(){                       //Formats Heavy Equipment info in its scroll box
        RecyclerView HeavyScroll = findViewById(R.id.HeavyScroll);
        RelativeAdapter adapter = new RelativeAdapter(mArr1, mArr2, mArr3);
        HeavyScroll.setAdapter(adapter);
        HeavyScroll.setLayoutManager(new LinearLayoutManager(this ));
        }
    private void AddInfo1 (/*TID*/){                          //Adds info to Heavy Equipment scroll box
        String TID = "25";       //static TID for now, should accept TID from previous screen upon merge
        BackgroundWorker thread = new BackgroundWorker(this);
        thread.execute("HeavyEquip", TID);

    }
    private void RecycleView2(){                      //Formats Tools info in its scroll box
        RecyclerView ToolScroll = findViewById(R.id.ToolScroll);
        RelativeAdapter adapter = new RelativeAdapter(mArr4, mArr5, mArr6);
        ToolScroll.setAdapter(adapter);
        ToolScroll.setLayoutManager(new LinearLayoutManager(this ));
    }
    private void AddInfo2 (/*TID*/){                         //Adds info to Tools scroll box
        String TID = "30";                  //change static TID
        BackgroundWorker thread = new BackgroundWorker(this);
        thread.execute("LightEquip", TID);
        //mArr4.add("567");   ******FOR TESTING PURPOSES
        //mArr5.add("SkillSaw");
        //mArr6.add("Red");
    }
    private void RecycleView3(){                      //Formats Workers info in its scroll box
        RecyclerView WorkerScroll = findViewById(R.id.WorkerScroll);
        RelativeAdapter adapter = new RelativeAdapter(mArr7, mArr8, mArr9);
        WorkerScroll.setAdapter(adapter);
        WorkerScroll.setLayoutManager(new LinearLayoutManager(this ));
    }
    private void AddInfo3 (/*TID*/){                          //Adds info to Workers scroll box
        String TID = "30";                   //Change static TID
        BackgroundWorker thread = new BackgroundWorker(this);
        thread.execute("Workers", TID);
    }

    private void populateHeader(/*TID*/){                     //Populates Jobsite, Start, & Priority fields at top of screen
        String TID = "30";
        BackgroundWorker thread = new BackgroundWorker(this);

        thread.execute("JobsiteHeader", TID);

        try{
            Thread.sleep(900);
        }catch(InterruptedException e){}

        TextView JobAddy = findViewById(R.id.JobsiteBox);
        JobAddy.setText(JobAddress);
        TextView StartDate = findViewById(R.id.StartBox);
        StartDate.setText(DateAdded);
        TextView Priority = findViewById(R.id.PriorityBox);
        Priority.setText(Prior);
    }
}
