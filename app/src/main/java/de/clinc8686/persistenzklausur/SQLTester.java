package de.clinc8686.persistenzklausur;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DatabaseHelperKlausur extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "example.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "tableExample";
    public static final String TITLE_NAME = "title";
    public static final String DESCR_NAME = "description";
    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TITLE_NAME + " TINYTEXT NOT NULL, " +
            DESCR_NAME + " TINYTEXT NOT NULL)";

    public DatabaseHelperKlausur(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
