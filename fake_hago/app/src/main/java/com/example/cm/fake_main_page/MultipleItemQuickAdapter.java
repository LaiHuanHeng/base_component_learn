package com.example.cm.fake_main_page;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.cm.fake_main_page.entity.MultipleItem;
import com.example.cm.fake_main_page.view.TextSwitchView;

import java.util.List;

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    public MultipleItemQuickAdapter(Context context, List data) {
        super(data);
        //必须绑定type和layout的关系
        addItemType(MultipleItem.TEXT, R.layout.item_text_view);
        addItemType(MultipleItem.IMG, R.layout.item_image_view);
        addItemType(MultipleItem.IMG_TEXT, R.layout.item_img_text_view);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TEXT:
                helper.setText(R.id.tv, item.getContent());
                break;
            case MultipleItem.IMG_TEXT:
                switch (helper.getLayoutPosition() % 2) {
                    case 0:
                        helper.setImageResource(R.id.iv, R.mipmap.animation_img1)
                                .addOnClickListener(R.id.iv)
                                .addOnClickListener(R.id.switcher);
                        break;
                    case 1:
                        helper.setImageResource(R.id.iv, R.mipmap.animation_img2)
                                .addOnClickListener(R.id.iv)
                                .addOnClickListener(R.id.switcher);
                        break;
                }
                TextSwitchView textSwitchView = helper.getView(R.id.switcher);
                String[] res = {"窗前明月光","疑是地上霜","举头望明月","低头思故乡"};
                textSwitchView.setResources(res);
                textSwitchView.setTextStillTime(5000);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
                lp.gravity = Gravity.CENTER_VERTICAL;
                lp.topMargin = 13;
                lp.leftMargin = 10;
                textSwitchView.setLayoutParams(lp);
                break;
        }
    }

}
