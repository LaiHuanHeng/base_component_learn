package com.example.diffut;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class TestMainActivity extends AppCompatActivity {

    private List<TestBean> mDatas;  //旧的数据集合
    private List<TestBean> mNewDatas; //新的数据集合
    private RecyclerView mRv;
    private DiffAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_main);
        initData();
        mRv = (RecyclerView) findViewById(R.id.rv);
        mRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DiffAdapter(this, mDatas);
        mRv.setAdapter(mAdapter);
    }

    private void initData() {
        mDatas = new ArrayList<>();
        mDatas.add(new TestBean(1,"1", "a", "666"));
        mDatas.add(new TestBean(2,"2", "b", "666"));
        mDatas.add(new TestBean(3, "3", "c", "666"));
        mDatas.add(new TestBean(4,"4", "d", "666"));
        mDatas.add(new TestBean(5,"5", "e", "666"));
        mDatas.add(new TestBean(6,"6", "f", "666"));
        mDatas.add(new TestBean(7,"7", "g", "666"));
        mDatas.add(new TestBean(8,"8", "h", "666"));
        mDatas.add(new TestBean(9,"9", "i", "666"));
        mDatas.add(new TestBean(10,"10", "j", "666"));
        mDatas.add(new TestBean(11,"11", "k", "666"));
        mDatas.add(new TestBean(12,"12", "l", "666"));
        mDatas.add(new TestBean(13,"13", "m", "666"));
        mDatas.add(new TestBean(14,"14", "n", "666"));
        mDatas.add(new TestBean(15,"15", "o", "666"));
    }

    public void onRefresh(View view) {
        mNewDatas = new ArrayList<>();
        for (TestBean bean : mDatas) {
            try {
                mNewDatas.add(bean.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        mNewDatas.get(1).setDesc("哈哈哈哈哈");
        mNewDatas.get(2).setDesc("嘻嘻嘻嘻嘻");
        mNewDatas.get(1).setTextAnim("锄禾日当午");
        mNewDatas.get(2).setTextAnim("汗滴禾下土");

        /**
         * 数据量小可以直接这么写，
         * 计算新旧数据集合的不同，进而根据这个不同进行更新
         */
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(mDatas, mNewDatas), true);
        diffResult.dispatchUpdatesTo(mAdapter);
        mAdapter.setDatas(mNewDatas);

        /**
         * 将计算过程放在子线程中做
         * 计算新旧数据集合的不同，进而根据这个不同进行更新
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                //放在子线程中计算DiffResult
                DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallBack(mDatas, mNewDatas), true);
                Message message = mHandler.obtainMessage(1);
                message.obj = diffResult;//obj存放DiffResult
                message.sendToTarget();
            }
        }).start();

    }

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what) {
                case 1:
                    //取出Result
                    DiffUtil.DiffResult diffResult = (DiffUtil.DiffResult) msg.obj;
                    //利用DiffUtil.DiffResult对象的dispatchUpdatesTo（）方法，传入RecyclerView的Adapter，轻松成为文艺青年
                    diffResult.dispatchUpdatesTo(mAdapter);
                    //别忘了将新数据给Adapter
                    mAdapter.setDatas(mNewDatas);
                    break;
            }
        }
    };

}
