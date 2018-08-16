package com.example.startactivity_learn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SecondActivity extends Activity {

    EditText text1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        text1 = (EditText) findViewById(R.id.text1);
        Button finish = (Button) findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.putExtra("result", text1.getText().toString());
                setResult(3, i);
                finish();
            }
        });
    }

    /**
     * 不起作用
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent();
        i.putExtra("result", text1.getText().toString());
        setResult(3, i);
        finish();
    }

    /**
     *   需要拦截press的事件
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("result", text1.getText().toString());
            setResult(3, i.putExtras(bundle));

//            Intent i = new Intent();
//            i.putExtra("result", text1.getText().toString());
//            setResult(3, i);
            finish();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
