package com.example.cm.smart_learn;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.scwang.smartrefresh.layout.internal.InternalClassics.ID_IMAGE_PROGRESS;
import static com.scwang.smartrefresh.layout.internal.InternalClassics.ID_TEXT_TITLE;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private RefreshLayout refreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyAdapter(getDatas());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initView() {
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        /**
         * 设置不同的头部、底部样式
         */
//        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));

        refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);

        final TextView tv = refreshLayout.getLayout().findViewById(ID_TEXT_TITLE);
        final ImageView iv2 = refreshLayout.getLayout().findViewById(ID_IMAGE_PROGRESS);
        final AtomicBoolean net = new AtomicBoolean(true);
        final AtomicInteger mostTimes = new AtomicInteger(0);//假设只有三屏数据

        //设置多监听器，包括顶部下拉刷新、底部上滑刷新
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener(){

            /**
             * 根据上拉的状态，设置文字，并且判断条件
             * @param refreshLayout
             * @param oldState
             * @param newState
             */
            @Override
            public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
                switch (newState) {
                    case None:
                    case PullUpToLoad:
                        break;
                    case Loading:
                    case LoadReleased:
                        tv.setText("正在加载游戏..."); //在这里修改文字
                        if (!isNetworkConnected(getApplicationContext())) {
                            net.set(false);
                        } else {
                            net.set(true);
                        }
                        break;
                    case ReleaseToLoad:
                        tv.setText("release");
                        break;
                    case Refreshing:
                        tv.setText("refreshing");
                        break;
                }
            }

            /**
             * 添加是否可以加载更多数据的条件
             * @param refreshLayout
             */
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (mostTimes.get() < 3 && isNetworkConnected(getApplicationContext())) {
                    mAdapter.loadMore(MoreDatas());  //上滑刷新，数据从下往上添加到界面上
                    mostTimes.getAndIncrement();
                }
                refreshLayout.finishLoadMore(1000); //这个记得设置，否则一直转圈
            }

            /**
             *  在这里根据不同的情况来修改加载完成后的提示语
             * @param footer
             * @param success
             */
            @Override
            public void onFooterFinish(RefreshFooter footer, boolean success) {
                super.onFooterFinish(footer, success);
                if (net.get() == false) {
                    tv.setText("请检查网络设置");
                } else if (mostTimes.get() >= 3) {
                    tv.setText("没有更多游戏");
                } else {
                    tv.setText("加载完成");
                    if (mostTimes.get() == 2) {
                        mostTimes.getAndIncrement();
                    }
                }
            }
        });

    }

    //原始的recyclerView数据
    private ArrayList<String> getDatas() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for(int i = 0; i < 6; i++) {
            data.add(i + temp);
        }
        return data;
    }

    //刷新得到的数据
    private ArrayList<String> MoreDatas() {
        ArrayList<String> data = new ArrayList<>();
        String temp = "新加数据 ";
        for(int i = 0; i < 5; i++) {
            data.add(temp + i);
        }
        return data;
    }

    /**
     * 检查网络是否可用
     *
     * @param context
     * @return
     */
    public boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }


    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        public ArrayList<String> datas = null;

        public MyAdapter(ArrayList<String> datas) {
            this.datas = datas;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.mTextView.setText(datas.get(position));
        }

        @Override
        public int getItemCount() {
            return datas.size();
        }

        public void loadMore(ArrayList<String> strings) {
            datas.addAll(strings);
            notifyDataSetChanged();
        }

        public void refreshData(ArrayList<String> strings) {
            datas.addAll(0, strings);
            notifyDataSetChanged();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ViewHolder(View view){
                super(view);
                mTextView = (TextView) view.findViewById(R.id.text);
            }
        }
    }
}
