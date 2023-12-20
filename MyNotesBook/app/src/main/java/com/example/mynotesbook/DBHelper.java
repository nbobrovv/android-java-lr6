package com.example.mynotesbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.fragment.app.FragmentActivity;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(FragmentActivity context) {
        super(context, "UserStoreData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Userdata(name TEXT primary key, author TEXT, style TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Userdata");
    }

    public Boolean insertuserdata(String name, String author, String style)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("author", author);
        contentValues.put("style", style);
        long result=DB.insert("Userdata", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updateuserdata(String name, String author, String style)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("author", author);
        contentValues.put("style", style);
        Cursor cursor = DB.rawQuery("Select * from Userdata where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.update("Userdata", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean deletedata (String name)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdata where name = ?", new String[]{name});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Userdata", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdata", null);
        return cursor;
    }
}