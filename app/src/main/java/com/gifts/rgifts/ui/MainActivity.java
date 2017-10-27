package com.gifts.rgifts.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.gifts.rgifts.R;
import com.gifts.rgifts.adapter.MainGridAdapter;
import com.gifts.rgifts.base.BaseActivity;
import com.gifts.rgifts.data.MainDataBean;
import com.gifts.rgifts.listenter.OnGridItemClikListenter;
import com.gifts.rgifts.strategy.StrategyFactory;
import com.gifts.rgifts.views.RecyclerViewDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.common_recyclerview)
    RecyclerView commonRecyclerview;

    private MainGridAdapter adapter;
    private List<MainDataBean> listbean;
    private String name[] = {"结婚", "满月", "升学", "乔迁"};
    private int icon[] = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activity = this;
        initCommonTopBar(1, null, "首页",
                null, 0, 0);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        adapter.setOnGridItemClikListenter(new OnGridItemClikListenter() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(activity, "点击了" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, SecondActivity.class);
                intent.putExtra("type", position);
                intent.putExtra("title", name[position]);
                activity.startActivity(intent);
            }
        });
    }

    private void initData() {
        listbean = new ArrayList<>();
        MainDataBean dataBean = null;
        for (int i = 0; i < name.length; i++) {
            dataBean = new MainDataBean();
            dataBean.setName(name[i]);
            dataBean.setIconID(icon[i]);
            listbean.add(dataBean);
        }
        adapter = new MainGridAdapter(activity, listbean);
        commonRecyclerview.setAdapter(adapter);

    }

    private void initView() {
        commonRecyclerview.setLayoutManager(
                new GridLayoutManager(activity, 2,
                        LinearLayoutManager.VERTICAL, false));
        commonRecyclerview.addItemDecoration(
                new RecyclerViewDecoration(2, 2, 2, 2));
    }
}
