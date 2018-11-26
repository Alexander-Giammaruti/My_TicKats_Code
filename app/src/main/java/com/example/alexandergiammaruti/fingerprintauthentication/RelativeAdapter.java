package com.example.alexandergiammaruti.fingerprintauthentication;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alexandergiammaruti.fingerprintauthentication.R;

import java.util.ArrayList;

public class RelativeAdapter extends RecyclerView.Adapter<RelativeAdapter.ViewHolder>  {
    private ArrayList<String> mArr1 = new ArrayList<>();
    private ArrayList<String> mArr2 = new ArrayList<>();
    private ArrayList<String> mArr3 = new ArrayList<>();

    public RelativeAdapter(ArrayList<String> col1, ArrayList<String> col2, ArrayList<String> col3){
        mArr1 = col1;
        mArr2 = col2;
        mArr3 = col3;
    }
    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.relativelayout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.c1.setText(mArr1.get(i));
        holder.c2.setText(mArr2.get(i));
        holder.c3.setText(mArr3.get(i));
        }

    @Override
    public int getItemCount() {
        return mArr1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView c1;
        TextView c2;
        TextView c3;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            c1 = itemView.findViewById(R.id.ColOne);
            c2 = itemView.findViewById(R.id.ColTwo);
            c3 = itemView.findViewById(R.id.ColThree);
        }
    }
}
