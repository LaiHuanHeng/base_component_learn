package com.example.bounce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout mMainLy = (RelativeLayout) findViewById(R.id.main_ly);

        RPEarnCashEntranceView mCoinView = new RPEarnCashEntranceView(getApplicationContext());
        mMainLy.addView(mCoinView);
    }
}
