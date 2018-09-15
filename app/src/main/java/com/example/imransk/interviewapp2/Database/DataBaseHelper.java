package com.example.imransk.interviewapp2.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "userDB";
    public static final int DB_VERSION = 1;

    public static final String USER_TABLE = "appRC";
    public static final String COL_USER_ID = "User_id";
    public static final String COL_NAME = "Name";
    public static final String COL_EMAIL = "Email";
    public static final String COL_PHONE = "Phone";

    public static final String CREATE_TABLE_USER = "create table " + USER_TABLE + "("
            + COL_USER_ID + "integer primary key, " + COL_NAME + " text, " + COL_EMAIL + " text, " + COL_PHONE + " text);";


    public DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
