package com.example.bouncetest;


        import android.animation.Animator;
        import android.animation.AnimatorListenerAdapter;
        import android.animation.AnimatorSet;
        import android.animation.ObjectAnimator;
        import android.animation.ValueAnimator;
        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.content.Context;
        import android.os.Handler;
        import android.os.Message;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.util.AttributeSet;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewStub;
        import android.view.animation.AccelerateInterpolator;
        import android.view.animation.CycleInterpolator;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import java.util.Random;

/**
 * Annotation:结果页激励体系入口
 * Created by FanXudong on 18-7-24
 */
public class RPEarnCashEntranceView extends RelativeLayout {

    private static final int COUNT_COINS_RAIN = 9;

    private ImageView vEarnCash;
    private int mRPTaskEarnCoins = -1;
    private boolean mIsReportEarnShow;
    private LinearLayout vCoinsRain;//金币雨ViewGroup
    private byte mRPSource = -1;
    private int mScreenWidth;
    private int mCoinRainWidth;
    private long mIntervalTime = 0;//动画间隔时间
    private ViewStub vNewStyleStub;
//    private View vEarnStar1;
//    private View vEarnStar2;
//    private boolean mIsNewStyle;

    public RPEarnCashEntranceView(@NonNull Context context) {
        this(context, null);
    }

    public RPEarnCashEntranceView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RPEarnCashEntranceView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onDetachedFromWindow() {
        vEarnCash.clearAnimation();
        super.onDetachedFromWindow();
    }

    private void init(Context context) {
//        mIsNewStyle = false;
        mScreenWidth = DimenUtils.getScreenWidth();
        mCoinRainWidth = DimenUtils.dp2px(40);
        LayoutInflater.from(context).inflate(R.layout.inflate_result_page_earn_cash_entrance, this, true);
        vEarnCash = (ImageView) findViewById(R.id.earn_cash_entrance);
        vCoinsRain = (LinearLayout) findViewById(R.id.earn_cash_coins_rain);
//        vEarnStar1 = findViewById(R.id.rp_earn_cash_star1);
//        vEarnStar2 = findViewById(R.id.rp_earn_cash_star2);
        adjustCoinsRainHeight();
//        if (mIsNewStyle) {
//            vEarnCash.setImageResource(R.drawable.ic_launcher_app_antitheftalarm);
//            vEarnStar1.setVisibility(VISIBLE);
//            vEarnStar2.setVisibility(VISIBLE);
//        } else {
//            vEarnCash.setImageResource(R.drawable.ic_launcher_app_antitheftalarm);
//            vEarnStar1.setVisibility(INVISIBLE);
//            vEarnStar2.setVisibility(INVISIBLE);
//        }
        vEarnCash.setImageResource(R.drawable.ic_launcher_app_antitheftalarm);
        addCoinRainView();
    }

    /**
     * 按屏幕比例适配金币雨高度
     */
    private void adjustCoinsRainHeight() {
        LayoutParams params = (LayoutParams) vCoinsRain.getLayoutParams();
        //TODO
        params.height = DimenUtils.dp2px(112 + 650);
        vCoinsRain.setLayoutParams(params);
    }

    /**
     * 设置结果页来源
     */
//    public void setRPSource(byte source) {
//        mRPSource = source;
//        adjustRPMarginRight(source);
//    }

    /**
     * 在不同结果页设置不同的右边距
     */
//    private void adjustRPMarginRight(byte source) {
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) vEarnCash.getLayoutParams();
//        if (source == EarnHelp.INTENT_EARN_MAIN_SOURCE_JUNK_RESULT) {
//            params.rightMargin = 0;
//        } else if (source == EarnHelp.INTENT_EARN_MAIN_SOURCE_BOOST_RESULT) {
//            params.rightMargin = DimenUtils.dp2px(35);
//        }
//        vEarnCash.setLayoutParams(params);
//    }

    /**
     * 激励系统入口展示埋点上报
     */
//    public void reportEarnCashEntrance() {
//        if (mIsReportEarnShow && EarnController.checkIfShowEarnCashEntrance()) {
//            if (vEarnCash != null) {
//                if (mIsNewStyle) {
//                    startTreasureChestAnim();
//                } else {
//                    startEarnCoins3DAnim();
//                }
//            }
//            new cm_luckybox_entrance_clean().setAction(cm_luckybox_entrance_clean.ACTION_SHOW).setResultType(mRPSource).report();
//        }
//    }

//    public void handleRPEarnCash(final Activity activity) {
//        if (!EarnController.checkIfShowEarnCashEntrance()) {
//            return;
//        }
//        setVisibility(VISIBLE);
//        new cm_luckybox_entrance_clean().setAction(cm_luckybox_entrance_clean.ACTION_SHOW).setResultType(mRPSource).report();
//        mIsReportEarnShow = true;
//        vEarnCash.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (activity != null && !activity.isFinishing()) {
//                    new cm_luckybox_entrance_clean().setAction(cm_luckybox_entrance_clean.ACTION_CLICK).setResultType(mRPSource).report();
//                    EarnRoute.routeToEarnMain(activity, mRPSource, mRPTaskEarnCoins);
//                    mRPTaskEarnCoins = -1;
//                }
//            }
//        });
//        if (mRPTaskEarnCoins < 0) {
//            if (mIsNewStyle) {
//                startTreasureChestAnim();
//            } else {
//                startEarnCoins3DAnim();
//            }
//            return;
//        }
//        if (EarnController.checkIfShowRPNewEntranceStyle()) {
//            EarnHelp.setShowNewEarnRpStyleTime();
//            showAddCoinsDialog();
//        } else {
//            addCoinRainView();
//        }
//    }

    /**
     * 显示加金币的Dialog
     */
//    private void showAddCoinsDialog() {
//        final RPEarnCashDialog rpEarnCashDialog = (RPEarnCashDialog) vNewStyleStub.inflate();
//        rpEarnCashDialog.setRPSource(mRPSource);
//        rpEarnCashDialog.setCoins(mRPTaskEarnCoins);
//        rpEarnCashDialog.playAnimation();
//        rpEarnCashDialog.setDismissListener(new RPEarnCashDialog.DismissListener() {
//            @Override
//            public void onDismissListener() {
//                startTreasureChestAnim();
//                showAddCoinsDetail();
//            }
//        });
//        rpEarnCashDialog.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (rpEarnCashDialog != null) {
//                    rpEarnCashDialog.delayDismiss();
//                }
//            }
//        }, 3 * 1000);
//    }

    /**
     * 开始金币翻转动画
     */
//    private void startEarnCoins3DAnim() {
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(vEarnCash, "rotationY", 0, 360).setDuration(1000);
//        animator1.start();
//    }

    /**
     * 开始宝箱晃动动画
     */
//    private void startTreasureChestAnim() {
//        ObjectAnimator objectAnimator = UIUtil.shakeAnimator(vEarnCash, 3f);
//        objectAnimator.start();
//        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                vEarnStar1.setAlpha(1 - animation.getAnimatedFraction());
//                vEarnStar2.setAlpha(animation.getAnimatedFraction());
//            }
//        });
//        valueAnimator.setRepeatCount(1);
//        valueAnimator.setDuration(500);
//        valueAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                vEarnStar1.setAlpha(1);
//                vEarnStar2.setAlpha(1);
//            }
//        });
//        objectAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                valueAnimator.start();
//            }
//        });
//    }

    /**
     * 使用Toast的形式提示加了多少积分
     */
//    private void showAddCoinsDetail() {
//        Toast result = new Toast(getContext());
//        LayoutInflater inflate = (LayoutInflater)
//                getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflate.inflate(R.layout.inflate_rp_earn_cash_toast, null);
//        TextView vContent = (TextView) v.findViewById(R.id.rp_earn_cash_toast);
//        vContent.setText("+" + mRPTaskEarnCoins + " " + EarnHelp.getRPAddCoinsDesc(getContext(), mRPSource));
//        result.setView(v);
//        result.setGravity(Gravity.TOP | Gravity.RIGHT, DimenUtils.dp2px(getContext(), getToastXOffset()), DimenUtils.dp2px(getContext(), 11));
//        result.setDuration(Toast.LENGTH_SHORT);
//        result.show();
//    }

    /**
     * 获取Toast右边的间距
     */
    /**
     * 在不同结果页设置不同的右边距
     */
//    private int getToastXOffset() {
//        if (mRPSource == EarnHelp.INTENT_EARN_MAIN_SOURCE_JUNK_RESULT) {
//            return 45;
//        } else if (mRPSource == EarnHelp.INTENT_EARN_MAIN_SOURCE_BOOST_RESULT) {
//            return 80;
//        } else if (mRPSource == EarnHelp.INTENT_EARN_MAIN_SOURCE_CPU_RESULT) {
//            return 85;
//        } else {
//            return 80;
//        }
//
//    }

    /**
     * 请求垃圾清理任务积分
     */
//    public void requestJunkClearTaskCoins() {
//        if (!EarnController.checkIfShowEarnCashEntrance()) {
//            return;
//        }
//        //这里暂时使用读新闻的积分体系
//        API.requestDanceAward(EarnTask.CATEGORY_NEWS, new NewRequestListener<LotteryInfo>() {
//            @Override
//            public void onSuccess(LotteryInfo lotteryInfo) {
//                if (lotteryInfo != null) {
//                    mRPTaskEarnCoins = lotteryInfo.coins;
//                }
//            }
//
//            @Override
//            public void onError(int errorCode) {
//            }
//        });
//    }

    /**
     * 添加金币雨
     */
    private void addCoinRainView() {
        //金币之间的间距
        int unitSpace = (mScreenWidth - DimenUtils.dp2px(2 * 60) - 9 * mCoinRainWidth) / 10;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mCoinRainWidth, mCoinRainWidth);
        params.leftMargin = unitSpace;
        for (int i = 0; i < COUNT_COINS_RAIN; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.cms_home_icon_coin);
            imageView.setLayoutParams(params);
            imageView.setVisibility(View.INVISIBLE);
            vCoinsRain.addView(imageView);
            CoinRainModel coinRainModel = new CoinRainModel(imageView, (i + 1) * unitSpace + i * mCoinRainWidth);
            startCoinRainAnim(coinRainModel, i);

        }
    }

    private void startCoinRainAnim(CoinRainModel coinRainModel, int what) {
        Message message = Message.obtain();
        message.obj = coinRainModel;
        message.what = what;
        mIntervalTime += 20;
        animHandler.sendMessageDelayed(message, mIntervalTime);
    }

    private void startCoinsAnim(final CoinRainModel coinRainModel) {
        startFallDownAnim(coinRainModel.coinView, 1000).addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                startCollapseCoinsAnim(coinRainModel.coinView);
            }
        });
    }

    /**
     * 回弹一圈的View
     */
    private void startReboundOnceAnim(final CoinRainModel coinRainModel, final int direction) {
        final Random random = new Random();
        startFallDownAnim(coinRainModel.coinView, 600).addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                int radius = DimenUtils.dp2px(20);
                AnimatorSet animatorSet = startCycleCurveAnim(coinRainModel.coinView, direction == 0 ? 300 : 400, direction == 0 ? coinRainModel.positionX - radius : coinRainModel.positionX + radius, 150 + random.nextInt(100));
                animatorSet.start();

                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startCollapseCoinsAnim(coinRainModel.coinView);
                    }
                });
            }
        });
    }

    /**
     * 回弹两圈的动
     */
    private void startReboundTwiceAnim(final CoinRainModel coinRainModel, final int direction) {
        startFallDownAnim(coinRainModel.coinView, 400).addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                int radius = DimenUtils.dp2px(50);
                AnimatorSet animatorSet = startCycleCurveAnim(coinRainModel.coinView, 600, direction == 0 ? coinRainModel.positionX - radius : coinRainModel.positionX + radius, 200);
                animatorSet.start();

                final AnimatorSet animatorSet1 = startCycleCurveAnim(coinRainModel.coinView, 600, direction == 0 ? coinRainModel.positionX - radius * 2 : coinRainModel.positionX + radius * 2, 300);
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animatorSet1.start();
                    }
                });
                animatorSet1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        startCollapseCoinsAnim(coinRainModel.coinView);
                    }
                });
            }
        });
    }

    /**
     * 漂浮出去的动画
     */
    private void floatOutAnim(final CoinRainModel coinRainModel, final int direction) {
        startFallDownAnim(coinRainModel.coinView, 400).addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {

                AnimatorSet animatorSet = startCycleCurveAnim(coinRainModel.coinView, 600, direction == 0 ? coinRainModel.positionX / 2 : coinRainModel.positionX + (mScreenWidth - coinRainModel.positionX) / 2, 300);
                animatorSet.start();

                final AnimatorSet animatorSet1 = startCycleCurveAnim(coinRainModel.coinView, 600, direction == 0 ? -coinRainModel.positionX / 2 : coinRainModel.positionX + (mScreenWidth - coinRainModel.positionX) / 2 * 3, 400);

                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        animatorSet1.start();
                    }
                });
                animatorSet1.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        coinRainModel.coinView.setAlpha(0);
                        vCoinsRain.removeView(coinRainModel.coinView);
                    }
                });
            }
        });
    }

    /**
     * 收起金币的动画
     */
    private void startCollapseCoinsAnim(final View view) {
        int marginRight = 100;
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "x", mScreenWidth - DimenUtils.dp2px(marginRight));
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "y", DimenUtils.dp2px(12));
        animator1.setInterpolator(new AccelerateInterpolator(0.5f));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.playTogether(animator1, animator2);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setAlpha(0);
                vCoinsRain.removeView(view);
                if (vCoinsRain != null && vCoinsRain.getChildCount() == 1) {
//                    startEarnCoins3DAnim();
//                    if (mIsNewStyle) {
//                        startTreasureChestAnim();
//                    } else {
//                        startEarnCoins3DAnim();
//                    }
//                    showAddCoinsDetail();
                }
            }
        });
    }

    /**
     * 下落的动画
     */
    private ObjectAnimator startFallDownAnim(View view, int duration) {
        view.setVisibility(View.VISIBLE);
        //TODO
        ObjectAnimator coinsAnim = ObjectAnimator.ofFloat(view, "translationY", 0f, 600f);
        coinsAnim.setDuration(duration);
        coinsAnim.start();
        return coinsAnim;
    }

    /**
     * 获取半个周期正弦弧形的动画
     */
    private AnimatorSet startCycleCurveAnim(View view, int duration, int x, int y) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "x", x);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(view, "y", y);
        animator2.setInterpolator(new CycleInterpolator(0.5f));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(duration);
        animatorSet.playTogether(animator1, animator2);
        return animatorSet;
    }


    private static class CoinRainModel {

        View coinView;
        int positionX;


        public CoinRainModel(View coinView, int positionX) {
            this.coinView = coinView;
            this.positionX = positionX;
        }
    }

    private static class AnimHandler extends Handler {

    }

    @SuppressLint("HandlerLeak")
    private AnimHandler animHandler = new AnimHandler() {

        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            CoinRainModel coinRainModel = (CoinRainModel) message.obj;
            if (message.what == COUNT_COINS_RAIN / 2 + 1) {
                //右边飘出去
                floatOutAnim(coinRainModel, 0);
            } else if (message.what == COUNT_COINS_RAIN / 2 - 1) {
                //左边飘出去
                floatOutAnim(coinRainModel, 1);
            } else if (message.what == COUNT_COINS_RAIN / 2) {
                //直下
                startCoinsAnim(coinRainModel);
            } else if (message.what == COUNT_COINS_RAIN / 2 + 2) {
                //右边两圈
                startReboundTwiceAnim(coinRainModel, 0);
            } else if (message.what == COUNT_COINS_RAIN / 2 - 2) {
                startReboundTwiceAnim(coinRainModel, 1);
            } else if (message.what < COUNT_COINS_RAIN / 2 - 2) {
                //向右边转一圈
                startReboundOnceAnim(coinRainModel, 0);
            } else {
                //向左边转一圈
                startReboundOnceAnim(coinRainModel, 1);
            }
        }
    };
}

