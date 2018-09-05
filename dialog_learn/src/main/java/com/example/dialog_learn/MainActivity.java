package com.example.dialog_learn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    private SelfDialog selfDialog;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.txt);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selfDialog = new SelfDialog(MainActivity.this);
                selfDialog.setTitle("提示");
                selfDialog.setMessage("确定退出应用?");
                selfDialog.setYesOnclickListener("确定", new SelfDialog.onYesOnclickListener() {
                    @Override
                    public void onYesClick() {
                        Toast.makeText(MainActivity.this,"点击了--确定--按钮",Toast.LENGTH_LONG).show();
                        selfDialog.dismiss();
                    }
                });
                selfDialog.setNoOnclickListener("取消", new SelfDialog.onNoOnclickListener() {
                    @Override
                    public void onNoClick() {
                        Toast.makeText(MainActivity.this,"点击了--取消--按钮",Toast.LENGTH_LONG).show();
                        selfDialog.dismiss();
                    }
                });
                selfDialog.show();
            }
        });
    }
}
