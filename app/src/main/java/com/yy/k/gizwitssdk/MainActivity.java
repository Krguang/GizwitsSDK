package com.yy.k.gizwitssdk;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.api.GizWifiSDK;
import com.gizwits.gizwifisdk.enumration.GizEventType;
import com.gizwits.gizwifisdk.enumration.GizWifiDeviceNetStatus;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.gizwits.gizwifisdk.listener.GizWifiDeviceListener;
import com.gizwits.gizwifisdk.listener.GizWifiSDKListener;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity;
import com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity;
import com.yy.k.gizwitssdk.adapter.LVDevicesAdapter;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import utils.ConstantUtil;
import utils.SpUtils;

public class MainActivity extends AppCompatActivity {

    String TAG = "krguang";
    private static final int REQUEST_EXTERNAL_STORAGE = 22;
    private static final int REQUEST_CODE_SCAN = 30;
    private static final int REQUEST_DEVICE_INIT = 31;
    private static final int REQUEST_DEVICE_DELETE = 32;
    private static final int REQUEST_DEVICE_BIND = 33;

    private static String[] PERMISSIONS_STORAGE = {"android.permission.CAMERA","android.permission.READ_EXTERNAL_STORAGE"};

    private String uid;
    private String token;

    private ListView listView;
    private LVDevicesAdapter adapter;
    private List<GizWifiDevice> gizWifiDeviceList;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    //刷新的弹窗
    private QMUITipDialog refleshTipDialog;
    private QMUITipDialog mTipDialog;

    //解绑进度弹窗
    private ProgressDialog dialogDelate;
    Message msg;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.d(TAG, "handleMessage: "+msg);

            if (msg.what == REQUEST_DEVICE_INIT){

                Log.d(TAG, "run: 我运行起来了");
                checkDevice();
            }

            if (msg.what == REQUEST_DEVICE_DELETE){
                if (dialogDelate != null){
                    dialogDelate.dismiss();
                }
                reloadListView();
            }

            if (msg.what == REQUEST_DEVICE_BIND){
                reloadListView();
            }
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
        topBar.setTitle("我的设备");
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

        gizWifiDeviceList = new ArrayList<>();
        listView = findViewById(R.id.lv_device);
        adapter = new LVDevicesAdapter(this, gizWifiDeviceList);
        listView.setAdapter(adapter);
        View vEmpty = findViewById(R.id.cl_no_device);

        //轻触的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                startControl(gizWifiDeviceList.get(position));
                Log.d(TAG, "onItemClick: 单击");
            }
        });

        //长按三秒的点击事件
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                showLongDialogOnClick(gizWifiDeviceList.get(position));
                Log.d(TAG, "onItemLongClick: 长按");
                return true;
            }
        });
        listView.setEmptyView(vEmpty);

        getBoundDevices();

        msg = new Message();
        msg.what = REQUEST_DEVICE_INIT;
        mHandler.sendMessageDelayed(msg,3000);
        Log.d(TAG, "initView: 延时3秒启动发送");


        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.white);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.app_color_theme_1, R.color.app_color_theme_2
                , R.color.app_color_theme_3, R.color.app_color_theme_4, R.color.app_color_theme_5
                , R.color.app_color_theme_6, R.color.app_color_theme_7);
        //手动调用通知系统测量
        mSwipeRefreshLayout.measure(0, 0);
        //打开页面就是下啦的状态
        mSwipeRefreshLayout.setRefreshing(false);

        //设置手动下啦的监听事件
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refleshTipDialog = new QMUITipDialog.Builder(MainActivity.this)
                        .setTipWord("正在刷新...")
                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                        .create();
                refleshTipDialog.show();


                //这里面的是可以在主线程调用的
                mSwipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        //拿到SDK里面的设备
                        int deviceNum = GizWifiSDK.sharedInstance().getDeviceList().size();
                        if (deviceNum != 0) {
                            Log.d(TAG, "run: getDeviceList().size() = "+deviceNum);
                            for (int i=0;i<deviceNum;i++){
                                GizWifiSDK.sharedInstance().getDeviceList().get(i).setListener(mWifiDeviceListener);
                            }

                            reloadListView();
                        }
                        refleshTipDialog.dismiss();
                        mSwipeRefreshLayout.setRefreshing(false);

                        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo info = connectivityManager.getActiveNetworkInfo();

                        //获取到了手机已经处于断开网络的状态
                        if (info == null || !info.isConnected()) {
                            mTipDialog = new QMUITipDialog.Builder(MainActivity.this)
                                    .setIconType(QMUITipDialog.Builder.ICON_TYPE_FAIL)
                                    .setTipWord("获取失败，请检查网络！")
                                    .create();
                            mTipDialog.show();
                            listView.setVisibility(View.GONE);

                        } else {

                            listView.setVisibility(View.VISIBLE);
                            //显示另外一个弹窗,如果获取到的设备为空
                            if (gizWifiDeviceList.size() == 0) {
                                mTipDialog = new QMUITipDialog.Builder(MainActivity.this)
                                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_NOTHING)
                                        .setTipWord("暂无设备")
                                        .create();
                                mTipDialog.show();
                            } else {
                                mTipDialog = new QMUITipDialog.Builder(MainActivity.this)
                                        .setIconType(QMUITipDialog.Builder.ICON_TYPE_SUCCESS)
                                        .setTipWord("获取成功")
                                        .create();
                                mTipDialog.show();
                                reloadListView();
                            }
                        }

                        mSwipeRefreshLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mTipDialog.dismiss();
                            }
                        }, 1500);
                    }
                }, 3000);
            }
        });
        //3s之后自动收回来
        listView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 3000);
    }


    private void checkDevice(){

        //拿到SDK里面的设备
        int deviceNum = GizWifiSDK.sharedInstance().getDeviceList().size();
        if (deviceNum != 0) {
            Log.d(TAG, "run: getDeviceList().size() = "+deviceNum);
            for (int i=0;i<deviceNum;i++){
                GizWifiSDK.sharedInstance().getDeviceList().get(i).setListener(mWifiDeviceListener);
            }

            reloadListView();
        }
    }

    private void startControl(GizWifiDevice gizWifiDevice) {
        if (gizWifiDevice.getNetStatus() == GizWifiDeviceNetStatus.GizDeviceOffline)
            return;

        switch (gizWifiDevice.getProductKey()){
            case ConstantUtil.JINGTAI_PRODUCT_KEY:
                gizWifiDevice.setSubscribe(ConstantUtil.JINGTAI_PRODUCT_SECRET,true);
                Intent intent1 = new Intent(MainActivity.this, UnitJingTaiActivity.class);
                intent1.putExtra("gizWifiDevice", gizWifiDevice);
                startActivity(intent1);
                break;
            case ConstantUtil.DALIANYOUYI_PRODUCT_KEY:
                gizWifiDevice.setSubscribe(ConstantUtil.DALIANYOUYI_PRODUCT_SECRET,true);
                Intent intent2 = new Intent(MainActivity.this, UnitDaLianActivity.class);
                intent2.putExtra("gizWifiDevice", gizWifiDevice);
                startActivity(intent2);

        }
    }

    private void showLongDialogOnClick(final GizWifiDevice device){
        //显示弹窗
        String[] items = new String[]{"重命名", "解绑设备"};
        new QMUIDialog.MenuDialogBuilder(this).addItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        showReNameDialog(device);
                        break;
                    case 1:
                        showDelateDialog(device);
                        break;
                }
                dialogInterface.dismiss();
            }

        }).show();
    }


    //解绑设备
    private void showDelateDialog(final GizWifiDevice device) {

        new QMUIDialog.MessageDialogBuilder(this)
                .setTitle("确定要解绑设备？")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("解绑", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        GizWifiSDK.sharedInstance().unbindDevice(uid, token, device.getDid());
                        dialog.dismiss();

                        dialogDelate = new ProgressDialog(MainActivity.this);
                        dialogDelate.setMessage("处理中，请稍等...");
                        dialogDelate.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        dialogDelate.setCancelable(false);//屏幕外不可点击
                        dialogDelate.show();

                        msg = new Message();
                        msg.what = REQUEST_DEVICE_DELETE;
                        mHandler.sendMessageDelayed(msg,2000);
                    }
                })
                .show();
    }


    //重命名操作
    private void showReNameDialog(final GizWifiDevice device) {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(this);
        builder.setTitle("重命名操作")
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .setPlaceholder("在此输入新名字")
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        String newName = builder.getEditText().getText().toString().trim();
                        //判断是否输入为空
                        if (newName.isEmpty()) {
                            Toast.makeText(MainActivity.this, "修改失败!输入为空！", Toast.LENGTH_SHORT).show();
                        } else {
                         //   device.setListener(mWifiDeviceListener);
                            device.setCustomInfo(null, newName);
                        }
                        dialog.dismiss();
                    }
                })
                .show();
    }


    protected void reloadListView() {
        gizWifiDeviceList.clear();
        gizWifiDeviceList.addAll(GizWifiSDK.sharedInstance().getDeviceList());
        adapter.notifyDataSetChanged();
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
        //此处初始化sdk
        ConcurrentHashMap<String, String> appInfo = new ConcurrentHashMap<>();
        appInfo.put("appId", ConstantUtil.APP_ID);
        appInfo.put("appSecret", ConstantUtil.APP_SECRET);

        List<ConcurrentHashMap<String, String>> productInfo = new ArrayList<>();

        ConcurrentHashMap<String, String> product1 = new ConcurrentHashMap<>();
        product1.put("productKey", ConstantUtil.JINGTAI_PRODUCT_KEY);
        product1.put("productSecret", ConstantUtil.JINGTAI_PRODUCT_SECRET);
        productInfo.add(product1);

        ConcurrentHashMap<String, String> product2 = new ConcurrentHashMap<>();
        product2.put("productKey", ConstantUtil.DALIANYOUYI_PRODUCT_KEY);
        product2.put("productSecret", ConstantUtil.DALIANYOUYI_PRODUCT_SECRET);
        productInfo.add(product2);

        GizWifiSDK.sharedInstance().startWithAppInfo(this, appInfo, productInfo, null, false);
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

                Log.d(TAG, "didNotifyEvent: GizWifiSDKNotifyDeviceStatusChanged");
                GizWifiDevice mDevice = (GizWifiDevice)eventSource;
                Log.i(TAG, "device mac: " + mDevice.getMacAddress() + " disconnect caused by eventID: " + eventID + ", eventMessage: " + eventMessage);
            } else if (eventType == GizEventType.GizEventM2MService) {
                // M2M服务返回的异常通知
                Log.i(TAG, "M2M domain " + (String)eventSource + " exception happened, eventID: " + eventID + ", eventMessage: " + eventMessage);
            } else if (eventType == GizEventType.GizEventToken) {
                // token失效通知Gizwits 文档 SDK 2.0 参考手册
                Log.i(TAG, "token " + (String)eventSource + " expired: " + eventMessage);
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
            msg = new Message();
            msg.what = REQUEST_DEVICE_BIND;
            mHandler.sendMessageDelayed(msg,2000);
        }
    }

    private GizWifiDeviceListener mWifiDeviceListener = new GizWifiDeviceListener() {


        @Override
        public void didSetSubscribe(GizWifiErrorCode result, GizWifiDevice device, boolean isSubscribed) {
            super.didSetSubscribe(result, device, isSubscribed);
            Log.d(TAG, "订阅结果：" + result);
            Log.d(TAG, "订阅设备：" + device);

            //如果为成功的订阅了回调，则可以跳转
            if (result == GizWifiErrorCode.GIZ_SDK_SUCCESS) {
              //  Intent intent = new Intent(MainActivity.this, DevivePetAvtivity.class);
              //  intent.putExtra("_device", device);
              //  startActivity(intent);
            }
        }

        @Override
        public void didSetCustomInfo(GizWifiErrorCode result, GizWifiDevice device) {

            super.didSetCustomInfo(result, device);
            if (result == GizWifiErrorCode.GIZ_SDK_SUCCESS) {
                // 修改成功
                if (GizWifiSDK.sharedInstance().getDeviceList().size() != 0) {
                    reloadListView();
                    Toast.makeText(MainActivity.this, device.getAlias()+":修改成功", Toast.LENGTH_SHORT).show();
                }
            } else {
                // 修改失败
                Toast.makeText(MainActivity.this, device.getAlias()+":修改失败", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void didUpdateNetStatus(GizWifiDevice device, GizWifiDeviceNetStatus netStatus) {
            super.didUpdateNetStatus(device, netStatus);

            Log.d(TAG, "didUpdateNetStatus: 网络状态变化");
            reloadListView();
        }
    };
}
