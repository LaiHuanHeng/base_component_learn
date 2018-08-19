package com.example.buder_cp.brvah_learn;

import android.content.Context;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 * modify by AllenCoder
 */
public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MyMultipleItem, BaseViewHolder> {

    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        //必须绑定type和layout的关系
        addItemType(MyMultipleItem.TEXT, R.layout.item_text_view);
        addItemType(MyMultipleItem.IMG, R.layout.item_image_view);
        addItemType(MyMultipleItem.IMG_TEXT, R.layout.item_img_text_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyMultipleItem item) {
        switch (helper.getItemViewType()) {
            case MyMultipleItem.TEXT:
                helper.setText(R.id.tv, item.getContent());
                break;
            case MyMultipleItem.IMG_TEXT:
                switch (helper.getLayoutPosition() % 2) {
                    case 0:
                        helper.setImageResource(R.id.iv, R.mipmap.animation_img1);
                        break;
                    case 1:
                        helper.setImageResource(R.id.iv, R.mipmap.animation_img2);
                        break;

                }
                break;
        }
    }

}
