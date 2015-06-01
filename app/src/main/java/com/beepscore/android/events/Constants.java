package com.beepscore.android.events;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by stevebaker on 5/31/15.
 * If other classes use import static,
 * I think Constants does have to be an interface
 * See Barnette Ch 13 p 188
 */
public interface Constants extends BaseColumns {

    // superclass BaseColumns declares primary key _id

    public static final String AUTHORITY = "com.beepscore.events";
    public static final String TABLE_NAME = "events";
    public static final Uri CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + TABLE_NAME);

    // Columns in the Events database
    // time stamp
    public static final String TIME = "time";
    public static final String TITLE = "title";

}
