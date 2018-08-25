package com.example.diffut;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class TextSwitchView extends TextSwitcher implements ViewSwitcher.ViewFactory{

    private Context context;

    public TextSwitchView(Context context) {
        this(context, null);
    }

    public TextSwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        setFactory(this);
    }

    public void startTextChangeAnim(String curCount){
        setInAnimation(AnimationUtils.loadAnimation(context, R.anim.in_animation));
        setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.out_animation));
        setText(curCount);
    }

    @Override
    public View makeView() {
        TextView tv = new TextView(context);
        tv.setTextColor(Color.parseColor("#A9A9A9"));
        tv.setTextSize(18);
        return tv;
    }
}
