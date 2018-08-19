package com.example.buder_cp.brvah_learn;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class MyAdapter extends BaseQuickAdapter<Model, BaseViewHolder> {

    public MyAdapter(@LayoutRes int layoutResId, @Nullable List<Model> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Model item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getContent())
                .addOnClickListener(R.id.iv_img)
                .addOnClickListener(R.id.tv_title) //可以通过给子控件添加点击事件的listener来相应点击事件
                .setImageResource(R.id.iv_img, R.mipmap.ic_launcher);
    }
}
