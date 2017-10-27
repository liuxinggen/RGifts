package com.gifts.rgifts.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gifts.rgifts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 包名： com.gifts.rgifts.viewholder
 * 创建人： Liu_xg
 * 时间： 2017/10/27 15:40
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MoneyCountViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_money_show)
    public TextView tvMontyShow;
    @BindView(R.id.ll_money_show)
    public LinearLayout llMontyShow;

    public MoneyCountViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
