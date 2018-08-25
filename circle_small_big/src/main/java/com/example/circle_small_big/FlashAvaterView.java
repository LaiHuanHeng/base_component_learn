package com.example.circle_small_big;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class FlashAvaterView extends RelativeLayout{

    private Button btn_one;
    private ImageView top_2;
    private ImageView top_3;
    private ImageView top_4;
    private ImageView mTopImg;

    private Timer mTimer;
    private long mIntervalTime = 0;

    private static class AnimHandler extends Handler {}

    private AnimHandler mHandler = new AnimHandler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                scaleAnimator(top_2);
            } else if (msg.what == 1) {
                scaleAnimator(mTopImg);
            } else if (msg.what == 2) {
                scaleAnimator(top_3);
            } else if (msg.what == 3) {
                scaleAnimator(top_4);
            }
            super.handleMessage(msg);
        }

    };

    public FlashAvaterView(Context context) {
        this(context, null);
    }

    public FlashAvaterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlashAvaterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        bindViews(context);
    }

    private void bindViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.flash_avater_view, this, true);
        if (mTimer == null) {
            mTimer = new Timer();
        }
        btn_one = (Button) findViewById(R.id.btn_one);

        top_2 = (CircleImageView) findViewById(R.id.top_2);
        mTopImg = (CircleImageView) findViewById(R.id.top_img);
        top_3 = (CircleImageView) findViewById(R.id.top_3);
        top_4 = (CircleImageView) findViewById(R.id.top_4);

        btn_one.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimer == null) {
                    mTimer = new Timer();
                } else {
                    mTimer.scheduleAtFixedRate(new MyTask(), 1, 2000);
                }
            }
        });
    }

    private class MyTask extends TimerTask {
        @Override
        public void run() {
            for (int i = 0 ; i < 4; i++) {
                mIntervalTime += 100;
                mHandler.sendEmptyMessageDelayed(i, mIntervalTime);
            }
        }
    }

    //缩小、放大效果
    private void scaleAnimator(final ImageView img){
        final float scale = 0f;
        AnimatorSet scaleSet = new AnimatorSet();
        ValueAnimator valueAnimatorSmall = ValueAnimator.ofFloat(1.0f, scale);
        valueAnimatorSmall.setDuration(200);

        ValueAnimator valueAnimatorLarge = ValueAnimator.ofFloat(scale, 1.0f);
        valueAnimatorLarge.setDuration(200);

        valueAnimatorSmall.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (Float) animation.getAnimatedValue();
                img.setScaleX(scale);
                img.setScaleY(scale);
            }
        });
        valueAnimatorLarge.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (Float) animation.getAnimatedValue();
                img.setScaleX(scale);
                img.setScaleY(scale);
            }
        });
        scaleSet.play(valueAnimatorSmall).before(valueAnimatorLarge);
        scaleSet.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mTimer.cancel();
        mHandler.removeCallbacksAndMessages(null);
    }
}
