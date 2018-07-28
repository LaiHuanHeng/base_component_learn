package com.example.buder_cp.viewpager_1;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by buder_cp on 2018/7/12.
 */
public class FourActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener{

    private ViewPager vpager_four;
    private ImageView img_cursor;
    private TextView tv_one;
    private TextView tv_two;
    private TextView tv_three;

    private ArrayList<View> listView;
    private int offset = 0;
    private int currIndex = 0;
    private int bmpWidth;
    private int one = 0;
    private int two = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        initViews();
    }

    private void initViews() {
        vpager_four = (ViewPager) findViewById(R.id.vpager_four);
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        tv_three = (TextView) findViewById(R.id.tv_three);
        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
        tv_three.setOnClickListener(this);
        img_cursor = (ImageView) findViewById(R.id.img_cursor);

        bmpWidth = BitmapFactory.decodeResource(getResources(),R.mipmap.line).getWidth();
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;
        offset = (screenW/3 - bmpWidth)/2;
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset,0);
        img_cursor.setImageMatrix(matrix);

        one = offset * 2 + bmpWidth;
        two = one * 2;

        LayoutInflater mInflater = getLayoutInflater();
        listView = new ArrayList<View>();
        listView.add(mInflater.inflate(R.layout.view_one,null,false));
        listView.add(mInflater.inflate(R.layout.view_two,null,false));
        listView.add(mInflater.inflate(R.layout.view_three,null,false));

        vpager_four.setAdapter(new MyPageAdapter(listView));
        vpager_four.setCurrentItem(0);

        vpager_four.addOnPageChangeListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_one:
                vpager_four.setCurrentItem(0);
                break;
            case R.id.tv_two:
                vpager_four.setCurrentItem(1);
                break;
            case R.id.tv_three:
                vpager_four.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int index) {
        Animation animation = null;
        switch (index) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                break;
        }
        currIndex = index;
        animation.setFillAfter(true);
        animation.setDuration(300);
        img_cursor.startAnimation(animation);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
