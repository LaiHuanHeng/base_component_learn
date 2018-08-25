package com.example.diffut;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class DiffAdapter extends RecyclerView.Adapter<DiffAdapter.DiffVH>{

    private List<TestBean> mDatas;
    private Context mContext;
    private LayoutInflater mInflater;

    private ScaleInAnimation mSelectAnimation = new ScaleInAnimation();

    public DiffAdapter(Context mContext, List<TestBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setDatas(List<TestBean> mDatas) {
        this.mDatas = mDatas;
    }

    @Override
    public DiffVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiffVH(mInflater.inflate(R.layout.item_diff, parent, false));
    }

    @Override
    public void onBindViewHolder(DiffVH holder, int position) {
        TestBean bean = mDatas.get(position);
        holder.tv1.setText(bean.getName());
        holder.tv2.setText(bean.getDesc());
        holder.switchView.startTextChangeAnim(bean.getTextAnim());
    }

    /**
     * 重写这个方法，从DiffCallBack的getChangePayload（）方法中取出存入的新值
     * @param holder
     * @param position
     * @param payloads
     */
    @Override
    public void onBindViewHolder(DiffVH holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position);
        } else {
            Bundle payload = (Bundle) payloads.get(0);//取出我们在getChangePayload（）方法返回的bundle
            TestBean bean = mDatas.get(position);//取出新数据源，（可以不用，data也是新的 也可以用）
            for (String key : payload.keySet()) {
                switch (key) {
                    case "KEY_NAME":
                        //这里可以用payload里的数据，不过data也是新的 也可以用
                        holder.tv1.setText(bean.getName());
                        break;
                    case "KEY_DESC":
                        holder.tv2.setText(bean.getDesc());
                        break;
                    case "KEY_ANIM_TEXT":
                        holder.switchView.startTextChangeAnim(bean.getTextAnim());
                        break;
                    default:
                        break;
                }
            }
        }
    }

    @Override
    public void onViewAttachedToWindow(DiffVH holder) {
        super.onViewAttachedToWindow(holder);
        addAnimation(holder);
    }

    private void addAnimation(DiffVH holder) {
        for (Animator anim : mSelectAnimation.getAnimators(holder.itemView)) {
            anim.setDuration(300).start();
            anim.setInterpolator(new LinearInterpolator());
        }
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    class DiffVH extends RecyclerView.ViewHolder {
        TextView tv1, tv2;
        TextSwitchView switchView;

        public DiffVH(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
            tv2 = (TextView) itemView.findViewById(R.id.tv2);
            switchView = (TextSwitchView) itemView.findViewById(R.id.switchView);
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"1111111111",Toast.LENGTH_SHORT).show();
                }
            });
            tv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"22222222222",Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
}
