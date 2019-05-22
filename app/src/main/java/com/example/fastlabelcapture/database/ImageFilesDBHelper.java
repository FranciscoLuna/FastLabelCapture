package com.example.fastlabelcapture.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ImageFilesDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ImageFiles.db";

    public ImageFilesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + FilesContract.ImageFileEntry.TABLE_NAME + " ("
                + FilesContract.ImageFileEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FilesContract.ImageFileEntry.USER + " TEXT NOT NULL,"
                + FilesContract.ImageFileEntry.FILENAME + " TEXT NOT NULL,"
                + FilesContract.ImageFileEntry.PATH + " TEXT NOT NULL,"
                + FilesContract.ImageFileEntry.HEIGHT + " INTEGER NOT NULL,"
                + FilesContract.ImageFileEntry.WIDTH + " INTEGER NOT NULL,"
                + FilesContract.ImageFileEntry.HAVE_CROP + " INTEGER,"
                + FilesContract.ImageFileEntry.POINT_X_1 + " INTEGER,"
                + FilesContract.ImageFileEntry.POINT_Y_1 + " INTEGER,"
                + FilesContract.ImageFileEntry.POINT_X_2 + " INTEGER,"
                + FilesContract.ImageFileEntry.POINT_Y_2 + " INTEGER,"
                + "UNIQUE ("
                + FilesContract.ImageFileEntry.USER + ","
                + FilesContract.ImageFileEntry.FILENAME
                + "))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }


    public void insertImageFile(String user, String filename, String path,
                                Integer height, Integer width){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FilesContract.ImageFileEntry.USER, user);
        values.put(FilesContract.ImageFileEntry.FILENAME , filename);
        values.put(FilesContract.ImageFileEntry.PATH, path);
        values.put(FilesContract.ImageFileEntry.HEIGHT, height);
        values.put(FilesContract.ImageFileEntry.WIDTH, width);
        values.put(FilesContract.ImageFileEntry.HAVE_CROP, 0);

        db.insert(FilesContract.ImageFileEntry.TABLE_NAME, null, values);

        db.close();
    }

    public void insertImageFile(String user, String filename, String path,
                                Integer height, Integer width,
                                Integer pointX1, Integer pointY1,
                                Integer pointX2, Integer pointY2){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FilesContract.ImageFileEntry.USER, user);
        values.put(FilesContract.ImageFileEntry.FILENAME , filename);
        values.put(FilesContract.ImageFileEntry.PATH, path);
        values.put(FilesContract.ImageFileEntry.HEIGHT, height);
        values.put(FilesContract.ImageFileEntry.WIDTH, width);
        values.put(FilesContract.ImageFileEntry.HAVE_CROP, 1);
        values.put(FilesContract.ImageFileEntry.POINT_X_1, pointX1);
        values.put(FilesContract.ImageFileEntry.POINT_Y_1, pointY1);
        values.put(FilesContract.ImageFileEntry.POINT_X_2, pointX2);
        values.put(FilesContract.ImageFileEntry.POINT_Y_2, pointY2);

        db.insert(FilesContract.ImageFileEntry.TABLE_NAME, null, values);

        db.close();
    }
}