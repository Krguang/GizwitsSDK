package com.yy.k.gizwitssdk.DevicesControlActivity;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.enumration.GizWifiDeviceNetStatus;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.gizwits.gizwifisdk.listener.GizWifiDeviceListener;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.yy.k.gizwitssdk.R;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


public abstract class BaseDevicesControlActivity extends AppCompatActivity {


    protected QMUITipDialog mTipDialog;
    protected GizWifiDevice mDevice;
    protected QMUITopBar qmuiTopBar;
    private final String TAG = "krguang";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: 看看什么时候执行父类oncreate");
        initDevice();
    }

    protected void initDevice() {
        mDevice = (GizWifiDevice)this.getIntent().getParcelableExtra("gizWifiDevice");

        if (mDevice != null){
            mDevice.setListener(mListener);
        }
        //我们拿到上个界面传来的一个设备对象
        mTipDialog = new QMUITipDialog.Builder(this)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord("同步状态中...")
                .create();
        mTipDialog.show();

        //设置设备的云端回调结果监听
        //主动获取最新状态
        getStatus();
    }

   private void getStatus() {
        //如果当前设备可控制，那么我们就获取最新状态
        if (mDevice.getNetStatus() == GizWifiDeviceNetStatus.GizDeviceControlled) {
           // mDevice.getNetStatus();
            mTipDialog.dismiss();
        }
    }

    private void updateNetStatus(GizWifiDevice device, GizWifiDeviceNetStatus netStatus) {

        if (netStatus == GizWifiDeviceNetStatus.GizDeviceOffline) {
            if (mTipDialog.isShowing()) {
                Toast.makeText(this, "设备无法同步！", Toast.LENGTH_SHORT).show();
                mTipDialog.dismiss();
                finish();//退出界面
            }
        }
    }


    protected GizWifiDeviceListener mListener = new GizWifiDeviceListener() {

        /** 用于设备订阅 */
        public void didSetSubscribe(GizWifiErrorCode result, GizWifiDevice device, boolean isSubscribed) {
            BaseDevicesControlActivity.this.didSetSubscribe(result, device, isSubscribed);
        }

        //设备状态回调
        @Override
        public void didReceiveData(GizWifiErrorCode result, GizWifiDevice device, ConcurrentHashMap<String, Object> dataMap, int sn) {
            super.didReceiveData(result, device, dataMap, sn);
            BaseDevicesControlActivity.this.didReceiveData(result, device, dataMap, sn);
          //  receiveCloudData(result, dataMap);
          //  Log.e(TAG, "控制界面的下发数据:" + dataMap);
        }

        //设备的状态回调：包括离线、在线回调
        //该回调主动上报设备的网络状态变化，当设备重上电、断电或可控时会触发该回调
        @Override
        public void didUpdateNetStatus(GizWifiDevice device, GizWifiDeviceNetStatus netStatus) {
            super.didUpdateNetStatus(device, netStatus);
            if (netStatus == GizWifiDeviceNetStatus.GizDeviceControlled) {
                mTipDialog.dismiss();
            }
            BaseDevicesControlActivity.this.didUpdateNetStatus(device, netStatus);
           // updateNetStatus(device, netStatus);
         //   Log.e(TAG, "控制界面的设备状态回调:" + netStatus);
        }

        /** 用于修改设备信息 */
        public void didSetCustomInfo(GizWifiErrorCode result, GizWifiDevice device) {
            BaseDevicesControlActivity.this.didSetCustomInfo(result, device);
        }

    };


    /**
     * 设备订阅回调
     *
     * @param result       错误码
     * @param device       被订阅设备
     * @param isSubscribed 订阅状态
     */
    protected void didSetSubscribe(GizWifiErrorCode result, GizWifiDevice device, boolean isSubscribed) {

//        if (result == GizWifiErrorCode.GIZ_SDK_SUCCESS) {
//            Log.d(TAG, "didSetSubscribe: 订阅成功");
//            qmuiTopBar.setTitle(Objects.equals(device.getAlias(), "") || device.getAlias() == null ? device.getProductName() : device.getAlias());
//            qmuiTopBar.addLeftImageButton(R.mipmap.back_botton, R.id.qmui_topbar_item_left_back).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    finish();
//                }
//            });
//            // 订阅或解除订阅成功
//        } else {
//            // 失败
//        }

    }

    /**
     * 设备状态回调
     *
     * @param result  错误码
     * @param device  当前设备
     * @param dataMap 当前设备状态
     * @param sn      命令序号
     */
    protected void didReceiveData(GizWifiErrorCode result, GizWifiDevice device,
                                  ConcurrentHashMap<String, Object> dataMap, int sn) {

        if (result == GizWifiErrorCode.GIZ_SDK_SUCCESS) {
            mTipDialog.dismiss();
        } else {
            if (!mTipDialog.isShowing()) {
                mTipDialog.show();
            }
        }
    }

    /**
     * 设备硬件信息回调
     *
     * @param result       错误码
     * @param device       当前设备
     * @param hardwareInfo 当前设备硬件信息
     */
    protected void didGetHardwareInfo(GizWifiErrorCode result, GizWifiDevice device,
                                      ConcurrentHashMap<String, String> hardwareInfo) {
    }

    /**
     * 修改设备信息回调
     *
     * @param result 错误码
     * @param device 当前设备
     */
    protected void didSetCustomInfo(GizWifiErrorCode result, GizWifiDevice device) {
        if (result == GizWifiErrorCode.GIZ_SDK_SUCCESS) {

            qmuiTopBar.setTitle(Objects.equals(mDevice.getAlias(), "") || mDevice.getAlias() == null ? mDevice.getProductName() : mDevice.getAlias());
        }
    }

    /**
     * 设备状态变化回调
     */
    protected void didUpdateNetStatus(GizWifiDevice device, GizWifiDeviceNetStatus netStatus) {
        if (netStatus == GizWifiDeviceNetStatus.GizDeviceOffline) {
            Toast.makeText(this, "设备已断开！", Toast.LENGTH_SHORT).show();
            if (mTipDialog.isShowing()) {
                mTipDialog.dismiss();
            }
            finish();
        }
    }


    //内部类，获取手机网络 状态发生改变 的广播截取
    private class NetWorkChangedReciver extends BroadcastReceiver {

        //广接受的内容
        @Override
        public void onReceive(Context context, Intent intent) {

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo info = connectivityManager.getActiveNetworkInfo();

            //获取到了手机已经处于断开网络的状态
            if (info == null || !info.isConnected()) {
                Log.e(TAG, "断网状态触发");
                finish();
            }

            if (info == null)
                return;

            //切换到我们的移动网络
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                Log.e(TAG, "切换到移动网络");
            }

            //切换到我们的wi-fi网络
            if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                Log.e(TAG, "切换到wi-fi网络");
            }
        }
    }

    /**
     *Description:显示格式化数值，保留对应分辨率的小数个数，比如传入参数（20.3656，0.01），将返回20.37
     *@param date 传入的数值
     *@param scale 保留多少位小数
     *@return
     */
    protected String formatValue(double date, Object scale) {
        if (scale instanceof Double) {
            DecimalFormat df = new DecimalFormat(scale.toString());
            return df.format(date);
        }
        return Math.round(date) + "";
    }
}
