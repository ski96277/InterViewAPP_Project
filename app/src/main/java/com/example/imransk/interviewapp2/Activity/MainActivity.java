package com.example.imransk.interviewapp2.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.imransk.interviewapp2.Database.UserDataBaseSource;
import com.example.imransk.interviewapp2.R;
import com.example.imransk.interviewapp2.GeterSeter.User;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText name_ET;
    EditText email_ET;
    EditText phone_ET;
    UserDataBaseSource userDataBaseSource;

    ArrayList<User> users;
    String email_exis;

    String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalize();
    }

    private void initalize() {

        name_ET = findViewById(R.id.name_ET_ID);
        email_ET = findViewById(R.id.email_ET_ID);
        phone_ET = findViewById(R.id.phone_ET_ID);

        userDataBaseSource = new UserDataBaseSource(getApplicationContext());

    }

    public void sameInformation(View view) {
        String name = name_ET.getText().toString();
        String email = email_ET.getText().toString();
        String phone = phone_ET.getText().toString();

        if (TextUtils.isEmpty(name)) {
            name_ET.requestFocus();
            name_ET.setError("Enter Name..");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            email_ET.requestFocus();
            email_ET.setError("Enter Email..");
            return;
        }
        if (!validEmail(email)) {
            email_ET.requestFocus();
            email_ET.setError("Enter valid Email..");
            return;
        }


//get all value From table
        users=userDataBaseSource.getAllEmail();
//Check Email exist or not
        for (User user:users){
            email_exis=user.getEmail();
            Log.e(TAG, "initalize: email  "+email_exis );
            if (email.equals(email_exis)){
                email_ET.requestFocus();
                email_ET.setError("Email already exist");
                return;
            }
        }

        if (TextUtils.isEmpty(phone)) {
            phone_ET.requestFocus();
            phone_ET.setError("Enter Phone..");
            return;
        }

        User user = new User(name, email, phone);
        boolean status = userDataBaseSource.intestUser(user);
        if (status){
            Toast.makeText(this, "Yes saved", Toast.LENGTH_SHORT).show();
            name_ET.setText("");
            email_ET.setText("");
            phone_ET.setText("");
        }else {
            Toast.makeText(this, "Sorry", Toast.LENGTH_SHORT).show();
        }


    }

    public void showALlEmail(View view) {
        startActivity(new Intent(MainActivity.this,All_Email_Activity.class));
    }

    //E-mail validation
    private boolean validEmail(String email) {
        String email_pattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        Pattern pattern = Pattern.compile(email_pattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
