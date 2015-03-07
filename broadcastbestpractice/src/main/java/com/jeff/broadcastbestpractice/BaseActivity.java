package com.jeff.broadcastbestpractice;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Jeff on 15/3/6.
 */
public class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
