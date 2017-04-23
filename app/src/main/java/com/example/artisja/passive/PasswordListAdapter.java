package com.example.artisja.passive;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
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
        this.dataSet = loginList;
    }
    @Override
    public PasswordListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PasswordListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
