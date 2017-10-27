package com.gifts.rgifts.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.gifts.rgifts.R;
import com.gifts.rgifts.base.BaseActivity;
import com.gifts.rgifts.data.UserBean;
import com.gifts.rgifts.utils.Cache;
import com.mob.MobSDK;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

/**
 * 类名： RegisterActivity
 * 创建人： Liu_xg
 * 时间： 2017/10/25 13:38
 * 描述：注册
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class RegisterActivity extends BaseActivity {

    private static final String TAG = "RegisterActivity";
    @BindView(R.id.edit_phone_register)
    MaterialEditText editPhoneRegister;
    @BindView(R.id.edit_code_register)
    MaterialEditText editCodeRegister;
    @BindView(R.id.edit_password_register)
    MaterialEditText editPasswordRegister;
    @BindView(R.id.tv_register_time)
    TextView tvRegisterTime;
    @BindView(R.id.cb_register_select)
    CheckBox cbRegisterSelect;
    @BindView(R.id.tv_register_items)
    TextView tvRegisterItems;
    @BindView(R.id.btn_register_sure)
    Button btnRegisterSure;


    private String phoneStr, codeStr, countryCode = "86";
    private Handler mHandlerTime = new Handler();
    private int seconds = 10;
    private final int TIME = 1000;
    private boolean isRight = false;

    private EventHandler eh;

    /**
     * 向服务端添加数据
     */
    private String userName, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initCommonTopBar(2, "返回", "注册",
                null, 0, 0);
        initView();
        initEvent();
        initSMSS();
        initPermissions();
    }

    /**
     * 动态获取权限
     */
    private void initPermissions() {
        //动态获取权限
        if (Build.VERSION.SDK_INT >= 23) {
            int readPhone = checkSelfPermission(Manifest.permission.READ_PHONE_STATE);
            int receiveSms = checkSelfPermission(Manifest.permission.RECEIVE_SMS);
            int readSms = checkSelfPermission(Manifest.permission.READ_SMS);
            int readContacts = checkSelfPermission(Manifest.permission.READ_CONTACTS);
            int readSdcard = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

            int requestCode = 0;
            ArrayList<String> permissions = new ArrayList<String>();
            if (readPhone != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 0;
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (receiveSms != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 1;
                permissions.add(Manifest.permission.RECEIVE_SMS);
            }
            if (readSms != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 2;
                permissions.add(Manifest.permission.READ_SMS);
            }
            if (readContacts != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 3;
                permissions.add(Manifest.permission.READ_CONTACTS);
            }
            if (readSdcard != PackageManager.PERMISSION_GRANTED) {
                requestCode |= 1 << 4;
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (requestCode > 0) {
                String[] permission = new String[permissions.size()];
                this.requestPermissions(permissions.toArray(permission), requestCode);
                return;
            }
        }
    }

    /**
     * 初始化短信验证
     */
    private void initSMSS() {
        // 通过代码注册你的AppKey和AppSecret
        MobSDK.init(activity, "21e4706524678",
                "282059df418147f1cfa946b6380543d6");
        //3.0版本之后的初始化看这里（包括3.0）
        eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                mHandlerCode.sendMessage(msg);
            }
        };
        //注册短信回调
        SMSSDK.registerEventHandler(eh);

        /**
         * static void	getSupportedCountries()
         获取短信目前支持的国家列表，在监听中返回
         static void	getVerificationCode(String country, String phone)
         getVerificationCode(String country, String phone, OnSendMessageHandler listener)
         请求获取短信验证码，在监听中返回
         static void	submitVerificationCode(String country, String phone, String code)
         提交短信验证码，在监听中返回
         */

    }

    private void initEvent() {
        //电话号码输入后的检验
        editPhoneRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
//                //当输入的数字大于等于11位的时候获取验证码的按钮才可以点击
//                if (count >= 10) {
//                    tvRegisterTime.setClickable(true);
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //验证码的输入后检验
        editCodeRegister.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                //当输入的数字大于等于4位的时候验证
                if (start >= 3) {
                    codeStr = editCodeRegister.getText().toString().trim();
                    phoneStr = editPhoneRegister.getText().toString().trim();
                    //提交短信验证码，在监听中返回
                    SMSSDK.submitVerificationCode(countryCode, phoneStr, codeStr);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void initView() {
        activity = this;

    }

    /**
     * 把电话号码存到服务器上
     */
    private void phoneToService(String password) {
        userName = "r_" + System.currentTimeMillis();
        phoneStr = editPhoneRegister.getText().toString().trim();
        UserBean userBean = new UserBean();
        userBean.setName(userName);
        userBean.setPassword(password);
        userBean.setPhoneNum(phoneStr);
        userBean.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    Toast.makeText(activity,
                            "注册成功", Toast.LENGTH_SHORT).show();
                    Cache.OBJECTID = objectId;
                    Cache.PHONENUM = phoneStr;
                } else {
                    Toast.makeText(activity,
                            "注册失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @OnClick({R.id.tv_register_items,
            R.id.btn_register_sure, R.id.tv_register_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_register_items:

                break;
            case R.id.tv_register_time:
                countDown();

                break;
            case R.id.btn_register_sure:
                saveUserData();
                break;
            default:
                break;
        }
    }

    /**
     * 倒计时
     */
    private void countDown() {
        //当倒计时界面时不能点击
        if (tvRegisterTime.getText().toString().indexOf("秒后重新获得") > -1) {
            return;
        }
        phoneStr = editPhoneRegister.getText().toString().trim();
        //请求获取短信验证码，在监听中返回
        SMSSDK.getVerificationCode(countryCode, phoneStr);
        //当输入电话号码位数小于11或者没有输入电话号码，则不能点击
        if (phoneStr != null && phoneStr.length() >= 11) {
            mHandlerTime.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (seconds > 0) {
                        tvRegisterTime.setText(seconds + "秒后重新获得");
                        //每过一秒给自己发
                        mHandlerTime.postDelayed(this, TIME);
                        seconds--;
                    } else {
                        tvRegisterTime.setText("获取验证码");
                        seconds = 10;
                    }
                }
            }, 0);
        }
    }

    /**
     * 验证数据和保存数据
     */
    private void saveUserData() {
        //验证码正确
        if (isRight) {
            Toast.makeText(activity, "请输入正确的验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        password = editPasswordRegister.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(activity, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        } else if (password.length() < 5) {
            Toast.makeText(activity, "密码必须大于五位", Toast.LENGTH_SHORT).show();
            return;
        } else if (!cbRegisterSelect.isChecked()) {
            Toast.makeText(activity, "请先阅读《服务条款》", Toast.LENGTH_SHORT).show();
            return;
        }
        phoneToService(password);
    }

    Handler mHandlerCode = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
//            ArrayList<String> data = (ArrayList<String>) msg.obj;
            if (result == SMSSDK.RESULT_COMPLETE) {
                //回调完成
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    //提交验证码成功
                    Toast.makeText(activity, "验证码输入正确", Toast.LENGTH_SHORT).show();
                    isRight = true;
                    btnRegisterSure.setEnabled(true);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //获取验证码成功
                    Toast.makeText(activity, "验证码已发送", Toast.LENGTH_SHORT).show();
                }
            } else {
                /**
                 * {"status":477,"detail":"当前手机号发送短信的数量超过限额"}
                 {"status":468,"detail":"需要校验的验证码错误"}
                 {"status":468,"detail":"需要校验的验证码错误"}
                 {"status":468,"detail":"需要校验的验证码错误"}
                 */
                Throwable throwable = (Throwable) data;
                JSONObject object = null;
                try {
                    object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");
                    int status = object.optInt("status");
                    Log.i(TAG, "handleMessage: " + des);
                    Log.i(TAG, "handleMessage: " + status);
                    if (!TextUtils.isEmpty(des)) {
                        Toast.makeText(activity, des, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销回调接口
        SMSSDK.unregisterEventHandler(eh);
    }
}
