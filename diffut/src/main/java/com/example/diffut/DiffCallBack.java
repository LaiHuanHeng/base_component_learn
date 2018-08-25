package com.example.diffut;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * 完成数据集更新的核心回调
 * 判断新旧Item是否相等,里面的属性值是否需要更新
 */

public class DiffCallBack extends DiffUtil.Callback {

    private List<TestBean> mOldDatas, mNewDatas;

    public DiffCallBack(List<TestBean> mOldDatas, List<TestBean> mNewDatas) {
        this.mOldDatas = mOldDatas;
        this.mNewDatas = mNewDatas;
    }

    @Override
    public int getOldListSize() {
        return mOldDatas != null ? mOldDatas.size() : 0;
    }

    @Override
    public int getNewListSize() {
        return mNewDatas != null ? mNewDatas.size() : 0;
    }

    //判断是不是同一个item
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldDatas.get(oldItemPosition).getItemID() == mNewDatas.get(newItemPosition).getItemID();
    }

    //判断Item对象里面的属性值是否变化了，变化了返回false
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        TestBean beanOld = mOldDatas.get(oldItemPosition);
        TestBean beanNew = mNewDatas.get(newItemPosition);
        if (!beanOld.getName().equals(beanNew.getName())) {
            return false; //如果有内容不同，就返回false
        }
        if (!beanOld.getDesc().equals(beanNew.getDesc())) {
            return false;
        }
        if (!beanOld.getTextAnim().equals(beanNew.getTextAnim())) {
           return false;
        }
        return true;
    }

    /**
     * 新值存入
     *
     * 高效的局部更新，判断新旧数据集每个Item里面的属性是否相同，不同的话将新数据集中的数据取出来，放到payLoad中
     * 然后从Adapter的onBindViewHolder（DiffVH holder, int position, List<Object> payloads）这个方法中取出更新的值，
     * 设置到相对应的Item的属性上面，完成更新
     *
     * 调用情况是：areItemsTheSame()返回true而areContentsTheSame()返回false，也就是说两个对象代表的数据是一条，但是内容更新了。
     * @param oldItemPosition
     * @param newItemPosition
     * @return
     */
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {

        TestBean oldBean = mOldDatas.get(oldItemPosition);
        TestBean newBean = mNewDatas.get(newItemPosition);

        Bundle payload = new Bundle();
        if (!oldBean.getDesc().equals(newBean.getDesc())) {
            payload.putString("KEY_DESC", newBean.getDesc());
        }
        if (!oldBean.getName().equals(newBean.getName())) {
            payload.putString("KEY_NAME", newBean.getDesc());
        }
        if (!oldBean.getTextAnim().equals(newBean.getTextAnim())) {
            payload.putString("KEY_ANIM_TEXT", newBean.getTextAnim());
        }

        if (payload.size() == 0)//如果没有变化 就传空
            return null;
        return payload;
    }
}
