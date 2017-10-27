package com.gifts.rgifts.data;

import cn.bmob.v3.BmobObject;

/**
 * 包名： com.gifts.rgifts.data
 * 创建人： Liu_xg
 * 时间： 2017/10/25 11:44
 * 描述： 用户实体类
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class UserBean  extends BmobObject {
    private String name;
    private String phoneNum;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
