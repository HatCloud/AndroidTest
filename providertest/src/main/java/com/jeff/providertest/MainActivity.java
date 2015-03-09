package com.jeff.providertest;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private String newId;

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickAddToBook(View view) {
        Uri uri = Uri.parse("content://com.jeff.databasetest.provider/book");
        ContentValues values = new ContentValues();
        values.put("name", "A Clash of Kings");
        values.put("author", "George Martin");
        values.put("pages", 1040);
        values.put("price", 22.85);
        Uri newUri = getContentResolver().insert(uri, values);
        Log.d(TAG, "newUri: " + newUri.toString());
        newId = newUri.getPathSegments().get(1);
        Log.d(TAG, "newId: " + newId);
    }

    public void onClickQueryFromBook(View view) {
        Uri uri = Uri.parse("content://com.jeff.databasetest.provider/book");
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));
                Log.d(TAG, "ID: " + newId + "\n Book name: 《" + name + "》" + "\n Author: " + author + "\nPrice: " + price + "\nPages: " + pages);
            }
        }

    }

    public void onClickUpdateBook(View view) {
        Uri uri = Uri.parse("content://com.jeff.databasetest.provider/book/" + newId);
        ContentValues values = new ContentValues();
        values.put("name", "A Storm of Swords");
        values.put("pages", 1216);
        values.put("price", 24.06);
        getContentResolver().update(uri, values, null, null);
        Toast.makeText(this, "Updated, ID: " + newId, Toast.LENGTH_SHORT).show();
    }

    public void onClickDeleteFromBook(View view) {
        Uri uri = Uri.parse("content://com.jeff.databasetest.provider/book/");
        getContentResolver().delete(uri,null,null);
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
}
