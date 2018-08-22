package com.example.fake_hago_2.provider;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.provider.BaseItemProvider;
import com.example.fake_hago_2.DemoMultipleItemRvAdapter;
import com.example.fake_hago_2.NormalMultipleEntity;
import com.example.fake_hago_2.R;
import com.example.fake_hago_2.view.TextSwitchView;

/**
 * https://github.com/chaychan
 *
 * @author ChayChan
 * @description: Img ItemProvider
 * @date 2018/3/30  11:39
 */

public class ImgItemBigProvider extends BaseItemProvider<NormalMultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return DemoMultipleItemRvAdapter.TYPE_IMG_BIG;
    }

    @Override
    public int layout() {
        return R.layout.item_big_image_view;
    }

    @Override
    public void convert(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        if (position % 2 == 0) {
            helper.setImageResource(R.id.iv, R.mipmap.animation_img1);
        }else{
            helper.setImageResource(R.id.iv, R.mipmap.animation_img2);
        }

        TextSwitchView textSwitchView = helper.getView(R.id.switcher);
        String[] res = {"窗前明月光","疑是地上霜","举头望明月","低头思故乡"};
        textSwitchView.setResources(res);
        textSwitchView.setTextStillTime(5000);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.BELOW, R.id.iv);
        lp.addRule(RelativeLayout.ALIGN_BOTTOM);
        lp.addRule(Gravity.CENTER);
        textSwitchView.setLayoutParams(lp);

    }

    @Override
    public void onClick(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, NormalMultipleEntity data, int position) {
        Toast.makeText(mContext, "longClick", Toast.LENGTH_SHORT).show();
        return true;
    }
}
