package com.gifts.rgifts.views;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 包名： com.gifts.rgifts.views
 * 创建人： Liu_xg
 * 时间： 2017/10/27 14:14
 * 描述： recyclerview的间隔
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RecyclerViewDecoration extends RecyclerView.ItemDecoration {

    private int left, top, right, bottom;

    public RecyclerViewDecoration(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(left, top, right, bottom);
    }
}
