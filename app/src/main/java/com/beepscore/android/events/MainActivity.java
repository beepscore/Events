package com.beepscore.android.events;

import android.app.ListActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;

import static android.provider.BaseColumns._ID;
import static com.beepscore.android.events.Constants.CONTENT_URI;
import static com.beepscore.android.events.Constants.TIME;
import static com.beepscore.android.events.Constants.TITLE;


public class MainActivity extends ListActivity {

    private EventsData events;
    private static String[] FROM = { _ID, TIME, TITLE, };
    private static String ORDER_BY = TIME + " DESC";
    private static int[] TO = { R.id.rowid, R.id.time, R.id.title, };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addEvent("Hello Android!");
        Cursor cursor = getEvents();
        showEvents(cursor);
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

    private Cursor getEvents() {
        // Perform a managed query. The Activity will handle closing
        // and re-querying the cursor when needed.
        // TODO: replace deprecated managedQuery
        return managedQuery(CONTENT_URI, FROM, null, null, ORDER_BY);
    }

    private void showEvents(Cursor cursor) {
        // Set up data binding
        // TODO: replace deprecated SimpleCursorAdapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.item, cursor, FROM, TO);
        setListAdapter(adapter);
    }
}
