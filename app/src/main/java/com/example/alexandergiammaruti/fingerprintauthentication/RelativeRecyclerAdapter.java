package com.example.alexandergiammaruti.fingerprintauthentication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.alexandergiammaruti.fingerprintauthentication.R;

import java.util.ArrayList;

public class RelativeRecyclerAdapter extends RecyclerView.Adapter<RelativeRecyclerAdapter.ViewHolder> {

    private ArrayList<String> mTicketID = new ArrayList<>();
    private ArrayList<String> mWorksite = new ArrayList<>();
    private ArrayList<String> mPriority = new ArrayList<>();

    public RelativeRecyclerAdapter(ArrayList<String> TicketIDs, ArrayList<String> Worksites, ArrayList<String> Prioritys) {
        mTicketID = TicketIDs;
        mWorksite = Worksites;
        mPriority = Prioritys;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.relative_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.TicketID.setText(mTicketID.get(i));
        holder.Worksite.setText(mWorksite.get(i));
        holder.Priority.setText(mPriority.get(i));


    }

    @Override
    public int getItemCount()
    {
        return mTicketID.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView TicketID;
        TextView Worksite;
        TextView Priority;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            TicketID = itemView.findViewById(R.id.rTicketID);
            Worksite = itemView.findViewById(R.id.rWorksite);
            Priority = itemView.findViewById(R.id.rPriority);

        }
    }

}
