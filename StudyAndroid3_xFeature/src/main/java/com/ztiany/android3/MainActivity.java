package com.ztiany.android3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ztiany.android3.loader.LoaderContactActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loaderContact(View view) {
        start(LoaderContactActivity.class);
    }




    private void start(Class<? extends Activity> clazz) {
        startActivity(new Intent(this, clazz));
    }
}
