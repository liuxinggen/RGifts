package com.gifts.rgifts.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gifts.rgifts.R;
import com.gifts.rgifts.base.BaseActivity;
import com.gifts.rgifts.data.UserBean;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;
//        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                UserBean userBean = new UserBean();
//                userBean.setName("张三");
//                userBean.setLoginname("zs");
//                userBean.setPassword("123");
//                userBean.setAdress("兰州市");
//                userBean.setPhone("12345678910");
//                userBean.save(new SaveListener<String>() {
//                    @Override
//                    public void done(String objectId, BmobException e) {
//                        if (e == null) {
//                            Toast.makeText(MainActivity.this,
//                                    "添加数据成功，返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(MainActivity.this,
//                                    "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//            }
//        });
        findViewById(R.id.btn_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
//        findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final UserBean userBean = new UserBean();
//                userBean.setObjectId("f1577df832");
//                userBean.delete(new UpdateListener() {
//
//                    @Override
//                    public void done(BmobException e) {
//                        if (e == null) {
//                            Toast.makeText(activity, "删除成功:" + userBean,
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(activity, "删除失败：" + e.getMessage(),
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                });
//
//
//            }
//        });
//        findViewById(R.id.btn_select).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //查找Person表里面id为6b6c11c537的数据
//                BmobQuery<UserBean> bmobQuery = new BmobQuery<UserBean>();
//                bmobQuery.getObject("f1577df832", new QueryListener<UserBean>() {
//                    @Override
//                    public void done(UserBean object, BmobException e) {
//                        if (e == null) {
//                            Toast.makeText(activity, "查询成功:" + object.getName(),
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(activity, "查询失败：" + e.getMessage(),
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//
//
//            }
//        });
//        findViewById(R.id.btn_update).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //更新Person表里面id为6b6c11c537的数据，address内容更新为“北京朝阳”
//                final UserBean userBean = new UserBean();
//                userBean.setAdress("城关");
//                userBean.update("f1577df832", new UpdateListener() {
//
//                    @Override
//                    public void done(BmobException e) {
//                        if (e == null) {
//                            Toast.makeText(activity, "更新成功:" + userBean.getUpdatedAt(),
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(activity, "更新失败：" + e.getMessage(),
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                });
//
//
//            }
//        });

    }
}
