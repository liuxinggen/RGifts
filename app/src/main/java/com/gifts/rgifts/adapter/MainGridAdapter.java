package com.gifts.rgifts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gifts.rgifts.R;
import com.gifts.rgifts.data.MainDataBean;
import com.gifts.rgifts.listenter.OnGridItemClikListenter;
import com.gifts.rgifts.viewholder.MainGridViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * 包名： com.gifts.rgifts.adapter
 * 创建人： Liu_xg
 * 时间： 2017/10/27 13:39
 * 描述： 首页数据adapter
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MainGridAdapter extends RecyclerView.Adapter<MainGridViewHolder> {


    private Context context;
    private List<MainDataBean> listdata;
    private OnGridItemClikListenter onGridItemClikListenter;

    public MainGridAdapter(Context context, List<MainDataBean> listdata) {
        this.context = context;
        this.listdata = listdata;
    }

    @Override
    public MainGridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MainGridViewHolder(LayoutInflater
                .from(context).inflate(R.layout.main_grid_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MainGridViewHolder holder, final int position) {
        holder.mainItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnGridItemClikListenter().onClick(view, position);
            }
        });
        holder.mainTitle.setText(listdata.get(position).getName());
        holder.mainIcon.setBackgroundResource(listdata.get(position).getIconID());

    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }


    public OnGridItemClikListenter getOnGridItemClikListenter() {
        return onGridItemClikListenter;
    }

    public void setOnGridItemClikListenter(OnGridItemClikListenter onGridItemClikListenter) {
        this.onGridItemClikListenter = onGridItemClikListenter;
    }
}
