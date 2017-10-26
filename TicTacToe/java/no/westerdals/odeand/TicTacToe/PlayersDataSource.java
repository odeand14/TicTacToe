package no.westerdals.odeand.TicTacToe;

// Created by Andreas Ødegaard on 24.03.2017.


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlayersDataSource {

    private SQLiteHelper dbHelper;
    private SQLiteDatabase database;
    private String[] allColumns = {SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_NAME,
            SQLiteHelper.COLUMN_SCORE, SQLiteHelper.COLUMN_SINGLEPLAYER};

    public PlayersDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }


    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Player createPlayer(Player player) {
        ContentValues values = new ContentValues();

        values.put(SQLiteHelper.COLUMN_NAME, player.getName());
        values.put(SQLiteHelper.COLUMN_SCORE, player.getScore());
        values.put(SQLiteHelper.COLUMN_SINGLEPLAYER, player.isSinglePlayer());

        long insertID = database.insert(SQLiteHelper.TABLE_HIGHSCORES, null, values);

        Cursor cursor = database.query(SQLiteHelper.TABLE_HIGHSCORES, allColumns,
                SQLiteHelper.COLUMN_ID + " = " + insertID, null, null, null, null);

        cursor.moveToFirst();
        Player playerHighscore = cursorToComment(cursor);
        cursor.close();

        return playerHighscore;
    }

    private Player cursorToComment(Cursor cursor) {
        boolean bool = (cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_SINGLEPLAYER)) == 1);
        return new Player(
                cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_NAME)),
                Integer.parseInt(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_SCORE))),
                        bool,
                Long.parseLong(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_ID))));
    }

    public List<Player> getTopTwentyPlayers() {
        List<Player> topTenPlayers = new ArrayList<>();
        try (Cursor cursor = database.query(SQLiteHelper.TABLE_HIGHSCORES, allColumns,
                null, null, null, null, SQLiteHelper.COLUMN_SCORE + " DESC ", "20")) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                topTenPlayers.add(cursorToComment(cursor));
                while (cursor.moveToNext()) {
                    topTenPlayers.add(cursorToComment(cursor));
                }
                cursor.close();
            }
            return topTenPlayers;
        }
    }
}
