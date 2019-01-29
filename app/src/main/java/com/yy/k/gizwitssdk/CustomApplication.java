package com.yy.k.gizwitssdk;

import android.app.Application;
import android.os.Handler;

public class CustomApplication extends Application {

    private static final String VALUE = "Harvey";
    private String value;
    private Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        setValue(VALUE); // 初始化全局变量
    }
    public void setValue(String value) //该方法用于测试application的功能特性
    {
        this.value = value;
    }
    public String getValue()
    {
        return value;
    }
    //该方法用于保存要传递的handler
    public void setHandler( Handler handler) {
        this.mHandler = handler;
    }
    //该方法用于获取要传递的handler
    public Handler getHandler() {
        return mHandler;
    }
}
