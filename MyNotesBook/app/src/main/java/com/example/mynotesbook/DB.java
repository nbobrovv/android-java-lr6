package com.example.mynotesbook;//package com.example.myapplication;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//
//public class DB {
//    private static final String DB_NAME = "mydb";
//    private static final int
//            DB_VERSION = 1;
//    private static final String DB_TABLE = "goods";
//    private final Context mCtx;
//    private DBHelper mDBHelper;
//    private SQLiteDatabase mDB;
//
//    public DB(Context ctx) {
//        mCtx = ctx;
//    }
//
//    // открыть подключение
//    public void open() {
//        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
//        mDB = mDBHelper.getWritableDatabase();
//    }
//
//
//    // закрыть подключение
//    public void close() {
//        if (mDBHelper != null) mDBHelper.close();
//    }
//
//    // заполнить таблицу исходными данными при необходимости
//    public void write() {
//        int i = 0;
//        while (i < 25) {
//            i++;
//            addRec("My good №" + i, i, i);
//        }
//    }
//
//    // получить все данные из таблицы DB_TABLE
//    public Cursor getAllData() {
//        return mDB.query(DB_TABLE, null, null, null, null, null, null);
//    }
//
//    // добавить запись в DB_TABLE
//    public void addRec(String name, int price, int count) {
//        ContentValues cv = new ContentValues();
//        cv.put("name", name);
//        cv.put("price", price);
//        cv.put("count", count);
//        mDB.insert(DB_TABLE, null, cv);
//    }
//
//    // обновить запись в DB_TABLE
//    public void update(int id, String name, int price, int count) {
//        ContentValues cv =
//                new ContentValues();
//        cv.put("name", name);
//        cv.put("price", price);
//        cv.put("count", count);
//        mDB.update(DB_TABLE, cv, "id = ?",
//                new String[]{String.valueOf(id)});
//    }
//
//    // удалить запись из DB_TABLE
//    public void delRec(long id) {
//        mDB.delete(DB_TABLE, "id = " + id, null);
//    }
//
//    // удалить все записи из DB_TABLE
//    public void delAll() {
//        mDB.delete(DB_TABLE, null, null);
//    }
//}