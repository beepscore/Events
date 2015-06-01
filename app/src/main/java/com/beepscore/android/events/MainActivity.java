package com.beepscore.android.events;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import static android.provider.BaseColumns._ID;
import static com.beepscore.android.events.Constants.TABLE_NAME;
import static com.beepscore.android.events.Constants.TIME;
import static com.beepscore.android.events.Constants.TITLE;


public class MainActivity extends Activity {

    private EventsData events;
    private static String[] FROM = { _ID, TIME, TITLE, };
    private static String ORDER_BY = TIME + " DESC";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        events = new EventsData(this);
        try {
            addEvent("Hello Android!");
            Cursor cursor = getEvents();
            showEvents(cursor);
        } finally {
            events.close();
        }
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
        SQLiteDatabase db = events.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIME, System.currentTimeMillis());
        values.put(TITLE, title);
        db.insertOrThrow(TABLE_NAME, null, values);
    }

    private Cursor getEvents() {
        // Perform a managed query. The Activity will handle closing
        // and re-querying the cursor when needed.
        SQLiteDatabase db = events.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, FROM,
                null, null, null, null, ORDER_BY);
        // TODO: replace deprecated method startManagingCursor
        startManagingCursor(cursor);
        return cursor;
    }

    private void showEvents(Cursor cursor) {
        // Stuff them all into a big string
        StringBuilder builder = new StringBuilder("Saved events:\n");

        while (cursor.moveToNext()) {
            // Could use getColumnIndexOrThrow() to get indexes
            long id = cursor.getLong(0);
            long time = cursor.getLong(1);
            String title = cursor.getString(2);

            builder.append(id).append(": ");
            builder.append(time).append(": ");
            builder.append(title).append("\n");
        }
        // Display on the screen
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(builder);
    }
}
