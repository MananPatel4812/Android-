package com.rd.crudapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    public database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE USER(Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Username TEXT NOT NULL, Contact TEXT NOT NULL, Email TEXT NOT NULL, Password TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS USER");
        onCreate(sqLiteDatabase);
    }

    public void UserInsert(String Username, String Contact, String Email, String Password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Username", Username);
        contentValues.put("Contact", Contact);
        contentValues.put("Email", Email);
        contentValues.put("Password", Password);

        sqLiteDatabase.insert("USER", null, contentValues);
        sqLiteDatabase.close();
    }
    public void UserUpdate(int Id, String Username, String Contact, String Email, String Password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Username", Username);
        contentValues.put("Contact", Contact);
        contentValues.put("Email", Email);
        contentValues.put("Password", Password);

        sqLiteDatabase.update("USER", contentValues, "Id=?", new String[]{String.valueOf(Id)});
        sqLiteDatabase.close();
    }
    public void UserDelete(int Id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        sqLiteDatabase.delete("USER", "Id=?", new String[]{String.valueOf(Id)});
        sqLiteDatabase.close();
    }

    public Cursor UserSelect() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Id, Username, Contact, Email FROM USER", null);
        return cursor;
    }

    public Cursor UserSelectById(Integer Id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Id, Username, Contact, Email ,Password FROM USER WHERE Id =?",new String[]{Id.toString()});
        return cursor;
    }

}

