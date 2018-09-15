package com.example.imransk.interviewapp2.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.imransk.interviewapp2.Adapter.EmailListAdapter;
import com.example.imransk.interviewapp2.Database.UserDataBaseSource;
import com.example.imransk.interviewapp2.GeterSeter.User;
import com.example.imransk.interviewapp2.R;

import java.util.ArrayList;
import java.util.List;

public class All_Email_Activity extends AppCompatActivity {

    ListView listView;
    private EmailListAdapter emailListAdapter;
    UserDataBaseSource userDataBaseSource;
    ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__emil);
        listView = findViewById(R.id.user_Email_list);
        users = new ArrayList<>();
        userDataBaseSource = new UserDataBaseSource(getApplicationContext());
        users = userDataBaseSource.getAllEmail();

        emailListAdapter = new EmailListAdapter(getApplicationContext(), users);
        listView.setAdapter(emailListAdapter);


    }
}
