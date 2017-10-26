package no.westerdals.odeand.TicTacToe;

// Created by Andreas Ødegaard on 24.03.2017.

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String TABLE_HIGHSCORES = "highscores";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SCORE = "score";
    public static final String COLUMN_SINGLEPLAYER = "singleplayer";

    public static final String DATABASE_NAME = "highscore1.db";
    public static final int DATABASE_VERSION = 1;
    private static final java.lang.String DATABASE_CREATE =
            "create table " +
            TABLE_HIGHSCORES + "(" + COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_NAME + " text not null, " + COLUMN_SCORE + " integer, " +
            COLUMN_SINGLEPLAYER + " INTEGER default 0);";


    public SQLiteHelper (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
