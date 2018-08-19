package com.example.buder_cp.brvah_learn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

public class MultiItemActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_item);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);

        //设置每种数据类型所占用的grid的宽度
        final List<MyMultipleItem> data = getMultipleItemData();
        MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(this, data);

        //创建布局管理
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);

        //获取并设置每种数据类型所占用的grid的宽度
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });

        mRecyclerView.setAdapter(multipleItemAdapter);
    }

    //在这里定义每种数据类型所占用grid格子的宽度
    private List<MyMultipleItem> getMultipleItemData() {
        List<MyMultipleItem> list = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            list.add(new MyMultipleItem(MyMultipleItem.IMG, MyMultipleItem.IMG_SPAN_SIZE)); // 1
            list.add(new MyMultipleItem(MyMultipleItem.TEXT, MyMultipleItem.TEXT_SPAN_SIZE, "CymChad")); // 3
            list.add(new MyMultipleItem(MyMultipleItem.IMG_TEXT, MyMultipleItem.IMG_TEXT_SPAN_SIZE)); // 4
            list.add(new MyMultipleItem(MyMultipleItem.IMG_TEXT, MyMultipleItem.IMG_TEXT_SPAN_SIZE_MIN)); // 2
            list.add(new MyMultipleItem(MyMultipleItem.IMG_TEXT, MyMultipleItem.IMG_TEXT_SPAN_SIZE_MIN)); // 2
        }
        return list;
    }
}
