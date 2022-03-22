package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {


    public static final String DBNAME="final.db";

    public DBHelper(@Nullable Context context) {

        super(context, "final.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE USERS(USERNAME TEXT PRIMARY KEY, PASSWORD TEXT, TYPE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    //DROP DACA EXISTA 10:30 https://www.youtube.com/watch?v=yJ02XTKiuAc&t=458s
    }

    public Boolean insertData(String username, String password, String type)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("username",username);
        values.put("password",password);
        values.put("type",type);

        long result = db.insert("users", null, values);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean checkusername(String username)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0)
        { cursor.close();
            return true;}
        else
        { cursor.close();
            return false;}
    }

    public Boolean checkusernamepassword(String username, String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0)
          { cursor.close();
            return true;}
        else
        cursor.close();
        { cursor.close();
            return false;}

    }

}
