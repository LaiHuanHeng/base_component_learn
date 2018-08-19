package com.example.buder_cp.brvah_learn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private RecyclerView recyclerView;
    private List<Model> datas;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //模拟的数据（实际开发中一般是从网络获取的）
        datas = new ArrayList<>();
        Model model;
        for (int i = 0; i < 30; i++) {
            model = new Model();
            model.setTitle("我是第" + i + "条标题");
            model.setContent("第" + i + "条内容");
            datas.add(model);
        }

        //创建布局管理
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //创建适配器
        adapter = new MyAdapter(R.layout.item_rv, datas);

        //点击事件相关
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MainActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();

            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //此处通过不同ID来区分子控件的点击响应
                if (view.getId() == R.id.iv_img) {
                    Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目的图片", Toast.LENGTH_SHORT).show();
                } else if (view.getId() == R.id.tv_title) {
                    Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目的标题", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, MultiItemActivity.class);
                    startActivity(intent);
                }
            }
        });


        //创建加载动画的种类样式
        adapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        //true:只第一次进来加载动画；false:每次都加载动画
        adapter.isFirstOnly(false);

        //添加HeaderView相关
        View headerView = getHeaderView(0, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addHeaderView(getHeaderView(1, getRemoveHeaderListener()), 0);
            }
        });
        adapter.addHeaderView(headerView);

        //给RecyclerView设置适配器
        recyclerView.setAdapter(adapter);

    }

    //添加HeaderView相关--删除方法
    private View.OnClickListener getRemoveHeaderListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.removeHeaderView(v);
            }
        };
    }

    //添加HeaderView相关--添加方法
    private View getHeaderView(int type, View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.header_view, (ViewGroup) recyclerView.getParent(), false);
        if (type == 1) {
            ImageView imageView = (ImageView) view.findViewById(R.id.iv);
            imageView.setImageResource(R.mipmap.rm_icon);
        }
        view.setOnClickListener(listener);
        return view;
    }
}
