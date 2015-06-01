package com.beepscore.android.events;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

import static android.provider.BaseColumns._ID;
import static com.beepscore.android.events.Constants.CONTENT_URI;
import static com.beepscore.android.events.Constants.TIME;
import static com.beepscore.android.events.Constants.TITLE;


public class MainActivity extends ListActivity
        implements LoaderManager.LoaderCallbacks<Cursor> {

    // The loader's unique id (within this activity)
    private static final int LOADER_ID = 1;

    // The adapter that binds our data to the ListView
    private SimpleCursorAdapter mAdapter;

    private EventsData events;
    private static String[] FROM = { _ID, TIME, TITLE, };
    private static String ORDER_BY = TIME + " DESC";
    private static int[] TO = { R.id.rowid, R.id.time, R.id.title, };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the adapter. It starts off empty
        mAdapter = new SimpleCursorAdapter(this, R.layout.item, null, FROM, TO, 0);

        // Associate the adapter with the ListView
        setListAdapter(mAdapter);

        // Initialize the loader
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(LOADER_ID, null, this);

        addEvent("Hello Android!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addEvent(String title) {
        // Insert a new record into the Events data source.
        // You would do something similar for delete and update.
        ContentValues values = new ContentValues();
        values.put(TIME, System.currentTimeMillis());
        values.put(TITLE, title);
        getContentResolver().insert(CONTENT_URI, values);
    }

    //=========================================================================
    // Loader callbacks interface methods
    //=========================================================================

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // Create a new CursorLoader
        return new CursorLoader(this, CONTENT_URI, FROM, null, null, ORDER_BY);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()) {
            case LOADER_ID:
                // The data is now available to use
                mAdapter.swapCursor(cursor);
                break;
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // The loader's data is unavailable
        mAdapter.swapCursor(null);
    }
}
