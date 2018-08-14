package com.example.bouncetest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout mMainLy;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //第一种添加根布局
        //setContentView(R.layout.activity_main);

        //第二种添加根布局
        mMainLy = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        setContentView(mMainLy);

        //自定义View，金币雨
        RPEarnCashEntranceView myView = new RPEarnCashEntranceView(getApplicationContext());
        mMainLy.addView(myView);

        TextView tv = new TextView(getApplicationContext());
        tv.setText("hello Animator");
        tv.setTextColor(Color.argb(255, 255, 0, 0));
        tv.setTextSize(19);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW, R.id.hehe2);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.topMargin = 40;
        lp.leftMargin = 200;
        tv.setLayoutParams(lp);

        //文字左边动态添加图片
        Drawable drawableLeft = getResources().getDrawable(R.mipmap.cms_home_icon_coin);
        drawableLeft.setBounds(0,0,drawableLeft.getMinimumWidth(),drawableLeft.getMinimumHeight());
        tv.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableLeft, null,null,null);
        tv.setCompoundDrawablePadding(140);

        //第一种添加根布局
        //mMainLy = (RelativeLayout) findViewById(R.id.main_ly);
        //mMainLy.addView(tv);

        //第二种添加根布局
        mMainLy.addView(tv);

        /**
         * 动态添加动画
         */
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tv,"translationY",500,-DimenUtils.dp2px(35));
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(tv,"alpha",1f,0.3f);
        objectAnimator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator,objectAnimator1);
        animatorSet.setDuration(5000);
        animatorSet.start();
    }
}
