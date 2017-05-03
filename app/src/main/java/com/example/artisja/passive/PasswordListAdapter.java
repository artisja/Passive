package com.example.artisja.passive;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by qvk871 on 4/20/17.
 */

public class PasswordListAdapter  extends RecyclerView.Adapter<PasswordListAdapter.ViewHolder>{

    private ArrayList<Login> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView siteName,passwordName;

        public ViewHolder(View itemView) {
            super(itemView);
            siteName = (TextView) itemView.findViewById(R.id.site_text_view);
            passwordName = (TextView) itemView.findViewById(R.id.password_text_view);

        }
    }

    public PasswordListAdapter(ArrayList<Login> loginList){
        this.dataSet = Home.passwordSet;
        this.dataSet.toString();
    }

    @Override
    public PasswordListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.password_row,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PasswordListAdapter.ViewHolder holder, int position) {
            holder.siteName.setText(dataSet.get(position).getSiteName());
            holder.passwordName.setText(dataSet.get(position).getPassword());
        Log.d("holder value",holder.siteName.getText().toString());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
