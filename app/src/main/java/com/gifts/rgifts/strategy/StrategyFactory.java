package com.gifts.rgifts.strategy;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * 包名： com.gifts.rgifts.strategy
 * 创建人： Liu_xg
 * 时间： 2017/10/27 14:37
 * 描述： 策略工厂
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class StrategyFactory {

    /**
     * 单列模式
     **/
    private static StrategyFactory mStrategyFactory = null;

    /**
     * 公有的静态函数，对外暴露获取单例对象的接口
     */
    public static StrategyFactory getInstance() {
        if (mStrategyFactory == null) {
            synchronized (StrategyFactory.class) {
                if (mStrategyFactory == null) {
                    mStrategyFactory = new StrategyFactory();
                }
            }
        }
        return mStrategyFactory;
    }

    public List<StrategyListenter> listenters = new ArrayList<>();

    public StrategyFactory add(StrategyListenter strategyListenter) {
        if (strategyListenter != null) {
            listenters.add(strategyListenter);
        }
        return this;
    }

    public void Strategy(Context context, int position, String title) {
        boolean[] isHandle = {false};
        for (StrategyListenter strategyListenter : this.listenters) {
            if (isHandle[0]) {
                break;
            }
            strategyListenter.execute(context, position, title, isHandle);
        }

    }
}
