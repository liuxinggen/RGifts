package com.gifts.rgifts.ui;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gifts.rgifts.R;
import com.gifts.rgifts.adapter.MoneyCountAdapter;
import com.gifts.rgifts.base.BaseActivity;
import com.gifts.rgifts.listenter.OnGridItemClikListenter;
import com.gifts.rgifts.views.RecyclerViewDecoration;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 类名： SecondActivity
 * 创建人： Liu_xg
 * 时间： 2017/10/27 14:27
 * 描述： 首页二级界面
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class SecondActivity extends BaseActivity {


    @BindView(R.id.common_left_ll)
    LinearLayout commonLeftLl;
    @BindView(R.id.common_right_ll)
    LinearLayout commonRightLl;
    @BindView(R.id.edit_name)
    MaterialEditText editName;
    @BindView(R.id.common_recyclerview)
    RecyclerView commonRecyclerview;
    @BindView(R.id.edit_describe)
    MaterialEditText editDescribe;
    @BindView(R.id.edit_input_money)
    MaterialEditText editInputMoney;
    private String title;
    private int type;

    private String[] montyCount = {"100", "200", "300", "500", "1000", "2000"};
    private MoneyCountAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_married);
        ButterKnife.bind(this);
        activity = this;
        initView();
        initMoneyLayout();
        initEvent();


    }

    private void initEvent() {
        adapter.setOnGridItemClikListenter(new OnGridItemClikListenter() {
            @Override
            public void onClick(View v, int position) {
                Toast.makeText(activity, "点击了" + montyCount[position],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 选择金额的界面
     */
    private void initMoneyLayout() {
        commonRecyclerview.setLayoutManager(
                new GridLayoutManager(activity, 3,
                        LinearLayoutManager.VERTICAL, false));
        commonRecyclerview.addItemDecoration(
                new RecyclerViewDecoration(2, 2, 2, 2));
        adapter = new MoneyCountAdapter(activity, montyCount);
        commonRecyclerview.setAdapter(adapter);
    }

    private void initView() {
        title = getIntent().getStringExtra("title");
        type = getIntent().getIntExtra("type", -1);
        initCommonTopBar(2, "返回", title,
                null, 0, 0);

    }

    @OnClick({R.id.common_left_ll, R.id.common_right_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.common_left_ll:
                onBackPressed();
                break;
            case R.id.common_right_ll:
                break;
        }
    }
}
