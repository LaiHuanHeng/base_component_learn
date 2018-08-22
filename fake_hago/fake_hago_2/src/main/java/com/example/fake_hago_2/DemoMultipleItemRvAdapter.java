package com.example.fake_hago_2;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;
import com.example.fake_hago_2.provider.ImgItemBigProvider;
import com.example.fake_hago_2.provider.ImgItemProvider;
import com.example.fake_hago_2.provider.TextImgItemProvider;
import com.example.fake_hago_2.provider.TextItemProvider;

import java.util.List;

/**
 * @author ChayChan
 * modify by AllenCoder 2018/04/11
 * @description: MultipleItemRvAdapter demo
 * @date 2018/3/30  11:28
 */

public class DemoMultipleItemRvAdapter extends MultipleItemRvAdapter<NormalMultipleEntity, BaseViewHolder> {

    public static final int TYPE_TEXT = 100;
    public static final int TYPE_IMG = 200;
    public static final int TYPE_TEXT_IMG = 300;
    public static final int TYPE_IMG_BIG = 400;

    public DemoMultipleItemRvAdapter(@Nullable List<NormalMultipleEntity> data) {
        super(data);

        //构造函数若有传其他参数可以在调用finishInitialize()之前进行赋值，赋值给全局变量
        //这样getViewType()和registerItemProvider()方法中可以获取到传过来的值
        finishInitialize();
    }

    @Override
    protected int getViewType(NormalMultipleEntity entity) {
        //根据实体类判断并返回对应的viewType，具体判断逻辑因业务不同，这里这是简单通过判断type属性
        if (entity.type == NormalMultipleEntity.SINGLE_TEXT) {
            return TYPE_TEXT;
        } else if (entity.type == NormalMultipleEntity.SINGLE_IMG) {
            return TYPE_IMG;
        } else if (entity.type == NormalMultipleEntity.TEXT_IMG) {
            return TYPE_TEXT_IMG;
        } else if (entity.type == NormalMultipleEntity.TEXT_IMG_BIG) {
            return TYPE_IMG_BIG;
        }
        return 0;
    }

    @Override
    public void registerItemProvider() {
        //注册相关的条目provider
        mProviderDelegate.registerProvider(new TextItemProvider());
        mProviderDelegate.registerProvider(new ImgItemProvider());
        mProviderDelegate.registerProvider(new ImgItemBigProvider());
        mProviderDelegate.registerProvider(new TextImgItemProvider());
    }
}
