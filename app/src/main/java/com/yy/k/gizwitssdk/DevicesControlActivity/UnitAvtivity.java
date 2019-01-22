package com.yy.k.gizwitssdk.DevicesControlActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.yy.k.gizwitssdk.MainActivity;
import com.yy.k.gizwitssdk.R;
import com.yzq.zxinglibrary.android.CaptureActivity;

import java.util.concurrent.ConcurrentHashMap;

public class UnitAvtivity extends BaseDevicesControlActivity {

    private final String TAG = "krguang";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_activity);

        initView();
    }

    private void initView() {

        QMUITopBar topBar = findViewById(R.id.topBar);

        if (!mDevice.getAlias().equals("")){
            topBar.setTitle(mDevice.getAlias());
        }else {
            topBar.setTitle(mDevice.getProductName());
        }

        topBar.addLeftImageButton(R.mipmap.back_botton, R.id.qmui_topbar_item_left_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @Override
    protected void receiveCloudData(GizWifiErrorCode result, ConcurrentHashMap<String, Object> dataMap) {
        super.receiveCloudData(result, dataMap);

        if (result == GizWifiErrorCode.GIZ_SDK_SUCCESS) {
            //如果下发数据不为null
            if (dataMap != null) {
                parseReceiveData(dataMap);
            }

        }
    }

    private void parseReceiveData(ConcurrentHashMap<String, Object> dataMap) {


    }
}
