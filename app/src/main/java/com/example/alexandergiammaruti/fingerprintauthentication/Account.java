package com.example.alexandergiammaruti.fingerprintauthentication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.alexandergiammaruti.fingerprintauthentication.R;
import com.example.alexandergiammaruti.fingerprintauthentication.SectionPageAdapter;

public class Account extends AppCompatActivity {


    private static final String TAG="Account";
    private SectionPageAdapter mSectionPageAdapter;
    private ViewPager mviewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        Log.d(TAG,"onCreate: Starting.");
        mSectionPageAdapter = new SectionPageAdapter(getSupportFragmentManager());
        mviewPager=findViewById(R.id.container);
        setupViewPager(mviewPager);
        TabLayout tabLayout=findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mviewPager);



    }
    private void setupViewPager(ViewPager viewPager){
        SectionPageAdapter adapter= new SectionPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new tab1(),"Account");
        adapter.addFragment(new tab2(),"Tickets");
        adapter.addFragment(new tab3(),"Contacts");
        viewPager.setAdapter(adapter);
    }
}
