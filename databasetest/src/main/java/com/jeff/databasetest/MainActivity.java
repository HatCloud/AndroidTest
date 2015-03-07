package com.jeff.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
    }

    public void onClickCreateDatabase(View view) {
        dbHelper.getWritableDatabase();
    }

    public void onClickAddData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", "The Da Vinci Code");
        values.put("author", "Dan Brown");
        values.put("pages", 454);
        values.put("price", 16.96);
        db.insert("Book", null, values);
        values.clear();

        values.put("name", "The Lost Symbol");
        values.put("author", "TDan Brown");
        values.put("pages", 510);
        values.put("price", 19.95);
        db.insert("Book", null, values);
    }

    public void onClickUpdateData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("price", 10.99);
        db.update("Book", values, "name = ?", new String[]{"The Da Vinci Code"});
    }

    public void onClickDeleteData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("book", "pages < ?", new String[]{"500"});
    }

    public void onClickQueryData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String author = cursor.getString(cursor.getColumnIndex("author"));
                int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                double price = cursor.getDouble(cursor.getColumnIndex("price"));

                Log.d("MainActivity", "Book's name is " + name);
                Log.d("MainActivity", "Book's author is " + author);
                Log.d("MainActivity", "Book's pages are " + pages);
                Log.d("MainActivity", "Book's price is " + price);
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void onClickReplaceData(View view) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.execSQL("delete from Book");
            /*if (true) {
                throw new NullPointerException();
            }*/
            db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
                    new String[]{"Game of Thrones", "George Martin", "720", "20.85"});
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
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
}
