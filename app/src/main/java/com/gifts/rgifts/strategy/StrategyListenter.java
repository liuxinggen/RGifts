package com.gifts.rgifts.strategy;

import android.content.Context;

/**
 * 包名： com.gifts.rgifts.strategy
 * 创建人： Liu_xg
 * 时间： 2017/10/27 14:41
 * 描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public interface StrategyListenter {
    /**
     * 策略执行接口
     */

    void execute(Context context, int position,String title, boolean[] isHandle);
}
