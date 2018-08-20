package com.example.verticalscrolltext;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    private TextSwitcher textSwitcher1;
    private int curStr;
    String[] res = {"窗前明月光","疑是地上霜","举头望明月","低头思故乡"};

    private AutoVerticalScrollTextView mTitleTv;
    private TurnHandler mHander;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *  CMS 的动态View
         */
        mHander = new TurnHandler(getApplicationContext().getMainLooper());
        mTitleTv = (AutoVerticalScrollTextView) findViewById(R.id.vip_header_tv);
        if (mTitleTv != null) {
            startAnim();
        }


        /**
         * 自动更新逻辑
         */
        TextSwitchView  ast = (TextSwitchView ) findViewById(R.id.switcher2);
        String[] res = {"窗前明月光","疑是地上霜","举头望明月","低头思故乡"};
        ast.setResources(res);
        ast.setTextStillTime(3000);


        /**
         * 点击逻辑
         *
         */
        textSwitcher1 = (TextSwitcher) findViewById(R.id.switcher1);
        textSwitcher1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(MainActivity.this);
                tv.setTextColor(Color.parseColor("#30c2b1")); // tv.setTextColor(Color.BLUE);
                tv.setTextSize(22);
                return tv;
            }
        });
        next(null);
    }

    public void next(View source) {
        textSwitcher1.setText(res[curStr++ % res.length]);
    }

    private void startAnim() {
        if (mTitleTv != null && mHander != null){
            mHander.removeCallbacksAndMessages(null);
            int what = 4;
            mHander.sendEmptyMessage(what);
        }
    }

    class TurnHandler extends Handler {
        public TurnHandler(Looper mainLooper) {
            super(mainLooper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg != null){
                switch (msg.what){
                    case 4:
                        if (mTitleTv != null){
                            mTitleTv.next();
                            mTitleTv.setText("6666");
                            mHander.sendEmptyMessageDelayed(4, 3000);
                        }
                }
            }
        }
    }
}
