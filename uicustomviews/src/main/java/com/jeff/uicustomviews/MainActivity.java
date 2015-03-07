package com.jeff.uicustomviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by Jeff on 15/1/23.
 */
public class MainActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }
}

