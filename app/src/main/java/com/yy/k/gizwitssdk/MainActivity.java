package com.yy.k.gizwitssdk;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.api.GizWifiSDK;
import com.gizwits.gizwifisdk.enumration.GizEventType;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.gizwits.gizwifisdk.listener.GizWifiSDKListener;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import utils.SpUtils;

public class MainActivity extends AppCompatActivity {

    String TAG = "krguang";
    private static final int REQUEST_EXTERNAL_STORAGE = 22;
    private static final int REQUEST_CODE_SCAN = 30;
    private static String[] PERMISSIONS_STORAGE = {"android.permission.CAMERA","android.permission.READ_EXTERNAL_STORAGE"};

    private String uid;
    private String token;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage: "+msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSDK();
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //保证了每次打开页面能正常的回调SDK监听
        GizWifiSDK.sharedInstance().setListener(mListener);
    }

    private void initView() {
        QMUITopBar topBar = findViewById(R.id.topBar);
        topBar.setTitle("空调监控");
        topBar.addRightImageButton(R.mipmap.scan_botton, R.id.topbar_right_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int permission = ActivityCompat.checkSelfPermission(MainActivity.this,
                        "android.permission.CAMERA");
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    try {
                        // 没有写的权限，去申请写的权限，会弹出对话框
                        ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                        Log.d(TAG, "onCreate: 申请权限");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "onCreate: 有权限");
                    Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                    startActivityForResult(intent, REQUEST_CODE_SCAN);
                }
            }
        });

        getBoundDevices();

    }

    private void getBoundDevices() {
        uid = SpUtils.getString(this, "_uid", null);
        token = SpUtils.getString(this, "_token", null);
        Log.d(TAG, "_uid = " + uid);
        Log.d(TAG, "_token = " + token);

        if (uid != null && token != null)
            GizWifiSDK.sharedInstance().getBoundDevices(uid, token);
    }

    private void initSDK() {

        // 设置 SDK 监听
        GizWifiSDK.sharedInstance().setListener(mListener);
        // 设置 AppInfo
        ConcurrentHashMap<String, String> appInfo = new ConcurrentHashMap<>();
        appInfo.put("appId", "a4a97959861746bfb18a9cc4f73606c9");
        appInfo.put("appSecret", "431f6a51521c439fab805758b3be7b00");
        // 设置要过滤的设备 productKey 列表。 不过滤则直接传 null
        List<ConcurrentHashMap<String, String>> productInfo = new ArrayList<>();
        ConcurrentHashMap<String, String> product = new ConcurrentHashMap<>();
        product.put("productKey", "36ea0d1c0e38492fa6d1f30cff732daa");
        product.put("productSecret", "a79d12e2430b4f6491a7e40ecfd32586");
        productInfo.add(product);

        // 调用 SDK 的启动接口
        GizWifiSDK.sharedInstance().startWithAppInfo(this, appInfo, productInfo, null, false);
        // 实现系统事件通知回调
    }

    private GizWifiSDKListener mListener = new GizWifiSDKListener() {



        @Override
        public void didNotifyEvent(GizEventType eventType, Object eventSource,
                                   GizWifiErrorCode eventID, String eventMessage) {
            if (eventType == GizEventType.GizEventSDK) {
                GizWifiSDK.sharedInstance().userLoginAnonymous();//匿名登陆
                Log.i(TAG, "SDK event happened: " + eventID + ", " + eventMessage);
            } else if (eventType == GizEventType.GizEventDevice) {
                // 设备连接断开时可能产生的通知
                GizWifiDevice mDevice = (GizWifiDevice)eventSource;
                Log.i("krguang", "device mac: " + mDevice.getMacAddress() + " disconnect caused by eventID: " + eventID + ", eventMessage: " + eventMessage);
            } else if (eventType == GizEventType.GizEventM2MService) {
                // M2M服务返回的异常通知
                Log.i("krguang", "M2M domain " + (String)eventSource + " exception happened, eventID: " + eventID + ", eventMessage: " + eventMessage);
            } else if (eventType == GizEventType.GizEventToken) {
                // token失效通知Gizwits 文档 SDK 2.0 参考手册
                Log.i("krguang", "token " + (String)eventSource + " expired: " + eventMessage);
            }
        }

        @Override
        public void didUserLogin(GizWifiErrorCode result, String uid, String token) {
            super.didUserLogin(result, uid, token);
            if (result == GizWifiErrorCode.GIZ_SDK_SUCCESS) {
                // 登录成功
                Log.e(TAG, "登录成功");
                SpUtils.putString(MainActivity.this, "_uid", uid);
                SpUtils.putString(MainActivity.this, "_token", token);

                Log.d(TAG, "didUserLogin: _uid = "+uid);
                Log.d(TAG, "didUserLogin: _token = "+token);

            } else {
                // 登录失败
                Log.e(TAG, "登录失败");
            }
        }

        @Override
        public void didBindDevice(GizWifiErrorCode result, String did) {
            super.didBindDevice(result, did);
            if (result == GizWifiErrorCode.GIZ_SDK_SUCCESS) {
            // 绑定成功
                Log.d(TAG, "didBindDevice: 绑定成功");
            } else {
                // 绑定失败
                Log.d(TAG, "didBindDevice: 绑定失败");
            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String QRCode = data.getStringExtra(Constant.CODED_CONTENT);
                Log.d(TAG, "onActivityResult: 扫描结果为："+QRCode);
                bindDeviceByQRCode(QRCode);
            }
        }
    }

    private void bindDeviceByQRCode(String QRCode) {

        uid = SpUtils.getString(this, "_uid", null);
        token = SpUtils.getString(this, "_token", null);

        if ((uid!=null)&&(token!=null)&&(QRCode!=null)){
            GizWifiSDK.sharedInstance().bindDeviceByQRCode (uid, token, QRCode,false);
        }

    }
}
