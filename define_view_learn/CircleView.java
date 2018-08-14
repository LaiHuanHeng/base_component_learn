package com.example.bouncetest;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * @author shidefeng
 * @since 2016/3/30
 */
public class CircleView extends View {

    private Paint mDotPaint;
    private Paint mPaint;
    private RectF mRectF;

    private Animator mAnimator;

    private Path mPath;
    private PathMeasure mMeasure;

    private float mRadius;
    private float mBallRadius;
    private float mOver;
    private float mFraction;
    private float[] mCurrentPositions = new float[2];
    private float mLength;
    private float mAlpha;
    private boolean mIsAlphaAnimEnd;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final float arcWidth = DimenUtils.dp2px(2f);
        mBallRadius = DimenUtils.dp2px(4f);
        mOver = DimenUtils.dp2px(1f);

        mRectF = new RectF();
        mPath = new Path();
        mMeasure = new PathMeasure();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(arcWidth);

        mDotPaint = new Paint(mPaint);
        mDotPaint.setStyle(Paint.Style.FILL);
        mDotPaint.setStrokeWidth(0);

        setRotation(-90);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRadius == 0) {
            final float offset = mBallRadius + mOver;
            final float cx = getWidth() / 2f;
            final float cy = getHeight() / 2f;
            mRadius = Math.min(cx, cy) - mBallRadius;
            mRectF.set(0f + offset, 0f + offset, cx * 2f - offset, cy * 2f - offset);
            mPath.addArc(mRectF, 0, 360);
            mMeasure.setPath(mPath, true);
            mLength = mMeasure.getLength();
            mMeasure.getPosTan(0, mCurrentPositions, null);
            mPath.reset();
        }

        if (mIsAlphaAnimEnd) {
            mMeasure.getPosTan(mLength * mFraction, mCurrentPositions, null);
            mPath.addArc(mRectF, 0, mFraction * 360);
            canvas.drawPath(mPath, mPaint);
            mPath.reset();
        }

        drawDot(canvas);
    }

    private void drawDot(Canvas canvas) {
        mDotPaint.setAlpha((int) mAlpha);
        mDotPaint.setShader(null);
        canvas.drawCircle(mCurrentPositions[0], mCurrentPositions[1], mBallRadius, mDotPaint);

        final Shader shader = new RadialGradient(mCurrentPositions[0], mCurrentPositions[1], mBallRadius + mOver, 0xffffffff, 0x00ffffff, Shader.TileMode.CLAMP);
        mDotPaint.setShader(shader);
        canvas.drawCircle(mCurrentPositions[0], mCurrentPositions[1], mBallRadius + mOver, mDotPaint);
    }

    public Animator generateCircleAnim(boolean needRotation) {

        ValueAnimator anim1 = ValueAnimator.ofFloat(0, 255);
        anim1.setDuration(500);
        anim1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAlpha = (float) animation.getAnimatedValue();
                ViewCompat.postInvalidateOnAnimation(CircleView.this);
            }
        });
        anim1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mAlpha = 255;
                mIsAlphaAnimEnd = true;
                ViewCompat.postInvalidateOnAnimation(CircleView.this);
            }
        });

        ValueAnimator anim2 = ValueAnimator.ofFloat(0f, 1f);
        anim2.setDuration(1000);
        anim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mFraction = animation.getAnimatedFraction();
                ViewCompat.postInvalidateOnAnimation(CircleView.this);
            }
        });
        anim2.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
        AnimatorSet animator = new AnimatorSet();
        if (needRotation) {
            animator.playSequentially(anim1, anim2, getRotationAnimator());
        } else {
            animator.playSequentially(anim1, anim2);
        }
        return animator;
    }

    public Animator getRotationAnimator() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, View.ROTATION, getRotation(), getRotation() + 36000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setDuration(150000);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }

    public void startCircle(boolean needRotation) {
        mAnimator = generateCircleAnim(needRotation);
        mAnimator.start();
    }

    public void stopCircle() {
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.end();
        }
    }

    public void reset() {
        mFraction = 0;
        postInvalidate();
    }
}
