package com.example.fake_hago_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<NormalMultipleEntity> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);

        mData = DataServer.getNormalMultipleEntities();
        DemoMultipleItemRvAdapter multipleItemAdapter = new DemoMultipleItemRvAdapter(mData);

        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                int type = mData.get(position).type;
                if (type == NormalMultipleEntity.SINGLE_TEXT) { // 1
                    return MultipleItem.TEXT_SPAN_SIZE;  //3个
                } else if (type == NormalMultipleEntity.SINGLE_IMG) { //2
                    return MultipleItem.IMG_SPAN_SIZE;  //1个
                } else if (type == NormalMultipleEntity.TEXT_IMG_BIG){ //4
                    return MultipleItem.IMG;  //2个
                } else {
                    return MultipleItem.IMG_TEXT_SPAN_SIZE; //4个
                }
            }
        });

        multipleItemAdapter.isFirstOnly(false);
        multipleItemAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);

/**
 *      The click event is distributed to the BaseItemProvider and can be overridden.
 *      if you need register itemchild click longClick
 *      you need to use https://github.com/CymChad/BaseRecyclerViewAdapterHelper/wiki/Add-OnItemClickLister#use-it-item-child-long-click
 */
  /*      @Override
        protected void convert(BaseViewHolder helper, Status item) {
            helper.setText(R.id.tweetName, item.getUserName())
                    .setText(R.id.tweetText, item.getText())
                    .setText(R.id.tweetDate, item.getCreatedAt())
                    .setVisible(R.id.tweetRT, item.isRetweet())
                    .addOnLongClickListener(R.id.tweetText)
                    .linkify(R.id.tweetText);

        } */

        //item 和 childItem的点击事件

//        multipleItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(MainActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
//            }
//        });

        /**
         * 子item的点击事件
         */
        multipleItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_title) {
                    Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目的title", Toast.LENGTH_SHORT).show();
                } else if(view.getId() == R.id.tv_sub){
                    Toast.makeText(MainActivity.this, "点击了第" + (position + 1) + "条条目的sub", Toast.LENGTH_SHORT).show();
                }
            }
        });
        multipleItemAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_title) {
                    Toast.makeText(MainActivity.this, "长按了第" + (position + 1) + "条条目的title", Toast.LENGTH_SHORT).show();
                } else if(view.getId() == R.id.tv_sub){
                    Toast.makeText(MainActivity.this, "长按了第" + (position + 1) + "条条目的sub", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        mRecyclerView.setAdapter(multipleItemAdapter);
    }
}
