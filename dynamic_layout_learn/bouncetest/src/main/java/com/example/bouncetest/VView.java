package com.example.bouncetest;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

/**
 * Created by viseator on 3/8/17.
 * Wu Di
 * Email: viseator@gmail.com
 */

public class VView extends LinearLayout implements ValueAnimator.AnimatorUpdateListener,
        View.OnClickListener, Animator.AnimatorListener {
    private static final String TAG = "@vir VView";
    public static final int radius = 50;
    private int xPos = radius;
    private int yPos = radius;
    private Paint paint = new Paint();
    private ValueAnimator animator;
    private boolean isDown = true;
    private int animationHeight;
    private int canvasHeight;

    public VView(Context context) {
        super(context);
    }

    public VView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setWillNotDraw(false);
        setOnClickListener(this);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (animator == null) {
            canvasHeight = canvas.getHeight() - radius;
            paint.setColor(getResources().getColor(R.color.colorAccent));
            paint.setAntiAlias(true);
        }
        drawCircle(canvas);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        yPos = (int) animation.getAnimatedValue();
        invalidate();
    }

    void drawCircle(Canvas canvas) {
        canvas.drawCircle(xPos, yPos, radius, paint);
    }

    void init(int start, int end) {
        animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(1000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new AccelerateInterpolator());
        animator.addUpdateListener(this);
        animator.addListener(this);
    }


    @Override
    public void onClick(View v) {
        if (animator != null) {
            animator.end();
        }
        init(radius, canvasHeight);
        animationHeight = canvasHeight + radius;
        isDown = true;
        animator.start();
    }


    @Override
    public void onAnimationRepeat(Animator animation) {
        ValueAnimator vAnimation = (ValueAnimator) animation;
        if (isDown) {
            animationHeight = (int) (animationHeight * 0.5);
        }
        isDown = !isDown;
        if (isDown) {
            vAnimation.setIntValues(canvasHeight - animationHeight, canvasHeight);
            vAnimation.setInterpolator(new AccelerateInterpolator());
        } else {
            vAnimation.setIntValues(canvasHeight, canvasHeight - animationHeight);
            vAnimation.setInterpolator(new DecelerateInterpolator());
        }
    }


    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }
}
