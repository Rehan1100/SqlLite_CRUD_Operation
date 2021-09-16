package com.example.sqllite_project.DBHelperFolder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper  extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {

        super(context, "UserDatabase",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("Create Table UserDetail(name Text primary key, field Text,Age Text,Description Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

   /*     db.execSQL("drop table if exists UserDetail");
   */ }

    public boolean InsertData(String name,String Field,String Age,String Desc)
    {
        SQLiteDatabase database= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name",name);
        contentValues.put("field",Field);
        contentValues.put("Age",Age);
        contentValues.put("Description",Desc);
        long result= database.insert("UserDetail","",contentValues);
        if (result==-1)
        {
            return false;
        }else {
            return true;
        }

    }
    public boolean UpdateData(String name,String Field,String Age,String Desc)
    {
        SQLiteDatabase database= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("name",name);
        contentValues.put("field",Field);
        contentValues.put("Age",Age);
        contentValues.put("Description",Desc);
        Cursor cursor= database.rawQuery("Select * from UserDetail where name = ?",new String[]{name});
        if (cursor.getCount()>0) {
            long result = database.update("UserDetail", contentValues, "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }

    }
    public boolean Delete(String name)
    {
        SQLiteDatabase database= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        Cursor cursor= database.rawQuery("Select * from UserDetail where name=?",new String[]{name});
        if (cursor.getCount()>0) {
            long result = database.delete("UserDetail", "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }

    }

    public Cursor getData() {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from UserDetail",null);
        return cursor;


    }
}
