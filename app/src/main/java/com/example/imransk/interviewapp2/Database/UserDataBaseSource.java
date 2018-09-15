package com.example.imransk.interviewapp2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.imransk.interviewapp2.GeterSeter.User;

import java.util.ArrayList;

public class UserDataBaseSource {
    DataBaseHelper dataBaseHelper;
    SQLiteDatabase db;

    public UserDataBaseSource(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
    }

    public void open() {
        db = dataBaseHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public boolean intestUser(User user) {
        this.open();
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.COL_NAME, user.getName());
        values.put(DataBaseHelper.COL_EMAIL, user.getEmail());
        values.put(DataBaseHelper.COL_PHONE, user.getPhone());
        long insertedRow = db.insert(DataBaseHelper.USER_TABLE, null, values);
        this.close();
        if (insertedRow > 0) {
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<User> getAllEmail() {

        ArrayList<User> users = new ArrayList<>();

        this.open();
        Cursor cursor = db.query(DataBaseHelper.USER_TABLE, null, null, null,
                null, null, null);

            if (cursor.getCount() > 0 && cursor != null) {
                cursor.moveToFirst();
                do {

//                int id = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.COL_USER_ID));
                    String name = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_NAME));
                    String email = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_EMAIL));
                    String phone = cursor.getString(cursor.getColumnIndex(DataBaseHelper.COL_PHONE));
//                users.add(new User(id,name, email, phone));
                    users.add(new User(name, email, phone));

                } while (cursor.moveToNext());
            }


        this.close();
        cursor.close();
        return users;

    }
}
