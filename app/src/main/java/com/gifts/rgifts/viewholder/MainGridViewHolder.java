package com.gifts.rgifts.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gifts.rgifts.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 包名： com.gifts.rgifts.viewholder
 * 创建人： Liu_xg
 * 时间： 2017/10/27 13:39
 * 描述： 首页的viewholder
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MainGridViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.main_icon)
    public ImageView mainIcon;
    @BindView(R.id.main_title)
    public TextView mainTitle;
    @BindView(R.id.main_items)
    public LinearLayout mainItems;

    public MainGridViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
