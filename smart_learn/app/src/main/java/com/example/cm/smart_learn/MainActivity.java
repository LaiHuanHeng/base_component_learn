package com.example.cm.smart_learn;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import java.util.ArrayList;

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
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyAdapter(getDatas());
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initView() {
        refreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        /**
         * 设置不同的头部、底部样式
         */
//        refreshLayout.setRefreshFooter(new ClassicsFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
//        refreshLayout.setRefreshHeader(new BezierRadarHeader(this));
//        refreshLayout.setRefreshHeader(new TwoLevelHeader(this));
        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));

        refreshLayout.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));

        //设置样式后面的北京颜色
        refreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);

        //设置监听器，包括顶部下拉刷新、底部上滑刷新
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener(){

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mAdapter.refreshData(MoreDatas()); //下拉刷新，数据从上往下添加到界面上
                refreshLayout.finishRefresh(1000); //这个记得设置，否则一直转圈
            }

            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mAdapter.loadMore(MoreDatas());  //上滑刷新，数据从下往上添加到界面上
                refreshLayout.finishLoadMore(1000); //这个记得设置，否则一直转圈
            }
        });

        /**
         *  上面的那个是多监听器，可以直接将多个监听卸载一个listener中，当然也可以写成下面的形式，是一样的效果
         */
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                mAdapter.refreshData(MoreDatas()); //下拉刷新，数据从上往下添加到界面上
//                refreshLayout.finishRefresh(1000); //这个记得设置，否则一直转圈
//            }
//        });
//
//        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                mAdapter.loadMore(MoreDatas());  //上滑刷新，数据从下往上添加到界面上
//                refreshLayout.finishLoadMore(1000); //这个记得设置，否则一直转圈
//            }
//        });
    }

    //原始的recyclerView数据
    private ArrayList<String> getDatas() {
        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for(int i = 0; i < 15; i++) {
            data.add(i + temp);
        }
        return data;
    }

    //刷新得到的数据
    private ArrayList<String> MoreDatas() {
        ArrayList<String> data = new ArrayList<>();
        String temp = "新加数据 ";
        for(int i = 0; i < 6; i++) {
            data.add(temp + i);
        }
        return data;
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        public ArrayList<String> datas = null;

        public MyAdapter(ArrayList<String> datas) {
            this.datas = datas;
        }

        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,viewGroup,false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.mTextView.setText(datas.get(position));
        }

        //获取数据的数量
        @Override
        public int getItemCount() {
            return datas.size();
        }

        //底部上拉刷新，数据直接在底部显示
        public void loadMore(ArrayList<String> strings) {
            datas.addAll(strings);
            notifyDataSetChanged();
        }

        //底部下拉刷新，数据直接从上往下添加数据，显示在顶部
        public void refreshData(ArrayList<String> strings) {
            datas.addAll(0, strings);
            notifyDataSetChanged();
//            notifyItemInserted(0); 一次只能加一项数据
        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public static class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;
            public ViewHolder(View view){
                super(view);
                mTextView = (TextView) view.findViewById(R.id.text);
            }
        }
    }
}
