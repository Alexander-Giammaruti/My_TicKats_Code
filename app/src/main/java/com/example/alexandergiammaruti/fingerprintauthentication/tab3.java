package com.example.alexandergiammaruti.fingerprintauthentication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.alexandergiammaruti.fingerprintauthentication.R;


public class tab3 extends Fragment {
    private static final String TAG="Tab1Fragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstantState){
        View view= inflater.inflate(R.layout.fragment_tab3,container,false);

        return view;
    }

}



