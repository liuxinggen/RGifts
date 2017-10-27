package com.gifts.rgifts.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gifts.rgifts.R;
import com.gifts.rgifts.listenter.OnGridItemClikListenter;
import com.gifts.rgifts.viewholder.MoneyCountViewHolder;

/**
 * 包名： com.gifts.rgifts.adapter
 * 创建人： Liu_xg
 * 时间： 2017/10/27 15:40
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class MoneyCountAdapter extends RecyclerView.Adapter<MoneyCountViewHolder> {

    private Context context;
    private String[] montyCount;
    private OnGridItemClikListenter onGridItemClikListenter;

    public MoneyCountAdapter(Context context, String[] montyCount) {
        this.context = context;
        this.montyCount = montyCount;
    }

    @Override
    public MoneyCountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MoneyCountViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.monty_items_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(MoneyCountViewHolder holder, final int position) {
        holder.tvMontyShow.setText(montyCount[position]);
        holder.llMontyShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getOnGridItemClikListenter().onClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return montyCount.length;
    }


    public OnGridItemClikListenter getOnGridItemClikListenter() {
        return onGridItemClikListenter;
    }

    public void setOnGridItemClikListenter(OnGridItemClikListenter onGridItemClikListenter) {
        this.onGridItemClikListenter = onGridItemClikListenter;
    }
}
