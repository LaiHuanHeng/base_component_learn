package com.example.cm.fake_main_page;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.cm.fake_main_page.data.DataServer;
import com.example.cm.fake_main_page.entity.MultipleItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);

        final List<MultipleItem> data = DataServer.getMultipleItemData();
        MultipleItemQuickAdapter multipleItemQuickAdapter = new MultipleItemQuickAdapter(this, data);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);
        multipleItemQuickAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });

        //创建加载动画的种类样式
        multipleItemQuickAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
        multipleItemQuickAdapter.isFirstOnly(false);

        multipleItemQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MainActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });

        multipleItemQuickAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv) {
                    Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目的标题", Toast.LENGTH_SHORT).show();
                } else if (view.getId() == R.id.iv) {
                    Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目的图片", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mRecyclerView.setAdapter(multipleItemQuickAdapter);
    }

}
