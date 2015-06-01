package com.beepscore.android.events;

// Use import static to get constants
import static android.provider.BaseColumns._ID;
import static com.beepscore.android.events.Constants.TABLE_NAME;
import static com.beepscore.android.events.Constants.TIME;
import static com.beepscore.android.events.Constants.TITLE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by stevebaker on 5/31/15.
 */
public class EventsData extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 1;

    /** Constructor. Creates a helper object for the Events database */
    public EventsData(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // if database doesn't exist, SQLiteOpenHelper calls onCreate
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TIME + " INTEGER, "
                + TITLE + " TEXT NOT NULL);");
    }

    /**
     * Upgrade to new version deletes any existing data, doesn't attempt to migrate it.
     * An alternative implementation could call ALTER TABLE to add a column.
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
