package com.example.verticalscrolltext;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    private TextSwitcher textSwitcher1;
    private int curStr;
    String[] mRes = {"点击我翻转", "窗前明月光","疑是地上霜","举头望明月","低头思故乡"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 自动更新的TextSwitchView
         */
        TextSwitchView  textSwitchView = (TextSwitchView) findViewById(R.id.switcher2);
        String[] autoRes = {"窗前明月光","疑是地上霜","举头望明月","低头思故乡"};
        textSwitchView.setResources(autoRes);
        textSwitchView.setTextStillTime(3000);


        /**
         * 点击更新的TextSwitchView
         */
        textSwitcher1 = (TextSwitcher) findViewById(R.id.switcher1);
        textSwitcher1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(MainActivity.this);
                tv.setTextColor(Color.parseColor("#30c2b1"));
                tv.setText("点击我翻转");
                tv.setTextSize(22);
                return tv;
            }
        });
        next(null);
    }

    public void next(View source) {
        textSwitcher1.setText(mRes[curStr++ % mRes.length]);
    }

}
