package com.beepscore.android.events;

import android.provider.BaseColumns;

/**
 * Created by stevebaker on 5/31/15.
 */
public interface Constants extends BaseColumns {

    // superclass BaseColumns declares primary key _id

    public static final String TABLE_NAME = "events";

    // Columns in the Events database
    // time stamp
    public static final String TIME = "time";
    public static final String TITLE = "title";

}
