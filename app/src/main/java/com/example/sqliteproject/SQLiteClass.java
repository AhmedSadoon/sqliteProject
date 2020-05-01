package com.example.sqliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.jar.Attributes;


public class SQLiteClass extends SQLiteOpenHelper {

    private static final String Database_Name = "sqlitedb.db"; //Database Name + Extension
    private static final String Table_Name = "registration"; //Table Name

    //Columns Name
    private static final String COL_1 = "Name";
    private static final String COL_2 = "Username";
    private static final String COL_3 = "Password";
    //private static final String COL_4="Image";

    public SQLiteClass(@Nullable Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Table_Name + " (Name TEXT,Username TEXT  PRIMARY KEY,Password TEXT)"); //انشاء جدول


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Table_Name);
        onCreate(db);
    }

    //سويناها boolean حتى نشوف اذا انضافت او لا
    public boolean insertData(String Name, String Username, String Password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); // صندوق نحط بي كلشي
        contentValues.put(COL_1, Name); //هاي القيم العمود
        contentValues.put(COL_2, Username);
        contentValues.put(COL_3, Password);
        //contentValues.put(COL_4,Image);
        long result = db.insert(Table_Name, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean updtateData(String Name, String Username, String Password, String Where) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); // صندوق نحط بي كلشي
        contentValues.put(COL_1, Name); //هاي القيم العمود
        contentValues.put(COL_2, Username);
        contentValues.put(COL_3, Password);
        //contentValues.put(COL_4,Image);
        db.update(Table_Name, contentValues, "Username = ?", new String[]{Where});

        return true;
    }

    public Cursor getData() {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase(); // استدعاء قاعدة البيانات
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + Table_Name, null);
        return cursor;
    }

    public Cursor getSelectedData(String Username){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("Select * From "+ Table_Name +" where Username= '"+Username +"'",null);
        return cursor;
    }

    public boolean deleteData(String Name, String Username) {
        SQLiteDatabase db = getWritableDatabase();
        long result = db.delete(Table_Name, "(Name= '" + Name + "' and Username= '" + Username + ")'", null);

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

}
