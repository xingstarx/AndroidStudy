package com.ztiany.android.bottomsheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ztiany.android.R;

public class BottomSheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.act_bottom_sheet_container_fl, ContentFragment.newInstance(), ContentFragment.class.getName())
                    .commit();
        }
    }

    public void showBottomDialog(View view) {
        BottomSheetFragmentDialog.newInstance()
                .show(getSupportFragmentManager(), "BottomSheetDialogFragment");

    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
    }

}
