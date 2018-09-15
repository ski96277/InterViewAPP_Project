package com.example.imransk.interviewapp2.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.imransk.interviewapp2.Activity.All_Email_Activity;
import com.example.imransk.interviewapp2.GeterSeter.User;
import com.example.imransk.interviewapp2.R;

import java.util.ArrayList;
import java.util.List;

public class EmailListAdapter extends ArrayAdapter<User> {

    Context context;
    ArrayList<User> users;
    LayoutInflater layoutInflater = null;

    public EmailListAdapter(@NonNull Context context, ArrayList<User> users) {
        super(context, R.layout.emil_item_list, users);

        this.context = context;
        this.users = users;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView=layoutInflater.inflate(R.layout.emil_item_list,null);

        TextView email_Tv=convertView.findViewById(R.id.email_TV_ID);
        TextView name_TV=convertView.findViewById(R.id.name_TV_ID);

        email_Tv.setText(users.get(position).getEmail());
        name_TV.setText(users.get(position).getName());


        return convertView;
    }
}
