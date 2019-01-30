package com.yy.k.gizwitssdk;

import android.app.Application;
import android.os.Handler;

public class CustomApplication extends Application {

    private Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();

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
