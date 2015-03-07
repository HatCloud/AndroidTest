package com.jeff.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    EditText edt_01;
    ProgressBar pgb_01;
    ProgressBar pgb_02;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_01 = (EditText) findViewById(R.id.edt_01);
        Button btn_01 = (Button) findViewById(R.id.btn_01);
        Button btn_02 = (Button) findViewById(R.id.btn_02);
        Button btn_03 = (Button) findViewById(R.id.btn_03);
        pgb_01 = (ProgressBar) findViewById(R.id.pgb_01);
        pgb_02 = (ProgressBar) findViewById(R.id.pgb_02);

        btn_01.setOnClickListener(this);
        btn_02.setOnClickListener(this);
        btn_03.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_01:
                String strText = edt_01.getText().toString();
                Toast.makeText(this, strText, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_02:
                if(pgb_01.getVisibility() == View.GONE)
                    pgb_01.setVisibility(View.VISIBLE);
                else
                    pgb_01.setVisibility(View.GONE);
                break;
            case R.id.btn_03:
                int progress = pgb_02.getProgress();
                progress += 10;
                if(pgb_02.getProgress() == 100)
                    progress = 0;
                pgb_02.setProgress(progress);
            break;
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
