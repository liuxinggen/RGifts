package com.gifts.rgifts.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gifts.rgifts.R;
import com.mob.MobSDK;

public class BaseActivity extends Activity {

    /**
     * 公用的当前Activity的对象
     */
    protected Activity activity;
    /**
     * 公用的Intent对象
     */
    protected Intent intent;
    /**
     * 公用的Bundle对象
     */
    protected Bundle bundle;
    /**
     * 公用的等待对话框对象
     */
    protected Dialog progressDialog;
    /**
     * 页面顶部标题TextView和布局LinearLayout
     */
    protected TextView commonTopMidTV;
    protected LinearLayout commonTopMidLL;
    /**
     * 页面顶部左边页面标题TextView,布局LinearLayout和图标ImageView
     */
    protected TextView commonTopLeftTV;
    protected LinearLayout commonTopLeftLL;
    protected ImageView commonTopLeftIV;
    /**
     * 页面顶部右边页面标题TextView,布局LinearLayout和图标ImageView
     */
    protected TextView commonTopRightTV;
    protected LinearLayout commonTopRightLL;
    protected ImageView commonTopRightIV;
    /**
     * SharedPreferences
     */
    protected SharedPreferences commonSP;
    protected SharedPreferences.Editor commonEditor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 初始化公共标题
     *
     * @param rightTitle 右边标题
     * @param midTitle   中间标题
     * @param leftTitle  左边标题
     * @param type       1:首页  2：二级界面
     * @param rightIcon  右边图标
     * @param leftIcon   坐标图标
     */
    protected void initCommonTopBar(int type, String leftTitle,
                                    String midTitle,
                                    String rightTitle,
                                    int rightIcon, int leftIcon) {
        commonTopRightTV = this.findViewById(R.id.common_right_tv);
        commonTopRightLL = this.findViewById(R.id.common_right_ll);
        commonTopRightIV = this.findViewById(R.id.common_right_iv);
        commonTopLeftIV = this.findViewById(R.id.common_left_iv);
        commonTopLeftLL = this.findViewById(R.id.common_left_ll);
        commonTopLeftTV = this.findViewById(R.id.common_left_tv);
        commonTopMidTV = this.findViewById(R.id.common_mid_tv);
        commonTopMidLL = this.findViewById(R.id.common_mid_ll);
        if (type == 1) {
            commonTopLeftLL.setVisibility(View.GONE);
            commonTopRightLL.setVisibility(View.GONE);
        }
        if (rightTitle != null) {
            commonTopRightTV.setText(rightTitle);
        }
        if (midTitle != null) {
            commonTopMidTV.setText(midTitle);
        }
        if (leftTitle != null) {
            commonTopLeftTV.setText(leftTitle);
        }
        if (rightIcon != 0) {
            commonTopRightIV.setImageResource(rightIcon);
        }
        if (leftIcon != 0) {
            commonTopLeftIV.setImageResource(leftIcon);
        }
        commonTopRightLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


}
