package com.example.buder_cp.slide_viewpager;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.zhpan.viewpager.holder.HolderCreator;
import com.zhpan.viewpager.holder.ViewHolder;
import com.zhpan.viewpager.view.CircleViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CircleViewPager mViewpager;
    private CircleViewPager mViewPager2;
    private List<DataBean> mList = new ArrayList<>();
    private List<Integer> mListInt = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setViewPager();
    }

    private void setViewPager() {

        //  设置指示器位置
        // mViewpager.setIndicatorGravity(CircleViewPager.END);
        //  是否显示指示器
        mViewpager.isShowIndicator(true);
        //  设置图片切换时间间隔
        mViewpager.setInterval(3000);
        //  设置指示器圆点半径
        // mViewpager.setIndicatorRadius(6);

        //  设置页面点击事件
        mViewpager.setOnPageClickListener(new CircleViewPager.OnPageClickListener() {
            @Override
            public void onPageClick(int position) {
                List<DataBean> list = mViewpager.getList();
                Toast.makeText(MainActivity.this, "点击了" + list.get(position).getDescribe(), Toast.LENGTH_SHORT).show();
            }
        });
        //  设置数据
        mViewpager.setPages(mList, new HolderCreator<ViewHolder>() {
            @Override
            public ViewHolder createViewHolder() {
                return new MyViewHolder();
            }
        });


        mViewPager2.setAutoPlay(false);
        mViewPager2.setCanLoop(false);
        mViewPager2.setPages(mListInt, new HolderCreator<ViewHolder>() {
            @Override
            public ViewHolder createViewHolder() {
                return new MyViewHolder();
            }
        });
        //  设置指示器资源图片
        mViewPager2.setIndicatorColor(Color.parseColor("#6C6D72"),
                Color.parseColor("#18171C"));
        mViewPager2.setOnPageClickListener(new CircleViewPager.OnPageClickListener() {
            @Override
            public void onPageClick(int position) {
                Toast.makeText(MainActivity.this, "图片" + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {
        DataBean dataBean = new DataBean("http://imgsrc.baidu.com/forum/w=580/sign=980a82b2efcd7b89e96c3a8b3f244291/38226eec2e738bd4ff113049a88b87d6277ff92a.jpg", "图片一");
        DataBean dataBean2 = new DataBean("http://img.zcool.cn/community/0105455608951732f875a132b93e14.jpg@1280w_1l_2o_100sh.jpg", "图片二");
        DataBean dataBean3 = new DataBean("http://img.19196.com/uploads/151209/9-151209112042D8.jpg", "图片三");
        DataBean dataBean4 = new DataBean("http://ztd00.photos.bdimg.com/ztd/w=700;q=50/sign=e7ebd10418dfa9ecfd2e541752eb863e/0823dd54564e9258c43135f69582d158ccbf4ea4.jpg", "图片四");
        mList.add(dataBean);
        mList.add(dataBean2);
        mList.add(dataBean3);
        mList.add(dataBean4);

        for (int i = 0; i <= 3; i++) {
            int drawable = getResources().getIdentifier("b" + i, "drawable", getPackageName());
            mListInt.add(drawable);
        }
    }

    private void initView() {
        mViewpager = (CircleViewPager) findViewById(R.id.viewpager);
        mViewPager2 = (CircleViewPager) findViewById(R.id.viewpager2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mViewpager.stopLoop();
        mViewPager2.stopLoop();
    }
}
