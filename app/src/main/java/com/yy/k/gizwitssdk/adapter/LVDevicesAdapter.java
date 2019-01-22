package com.yy.k.gizwitssdk.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.enumration.GizWifiDeviceNetStatus;
import com.yy.k.gizwitssdk.R;
import java.util.List;

/**
 * 包名：com.xuhoys.xuhong_gizwits_andorid.adapter
 * 文件名：xuhong_gizwits_andorid
 * 创建时间：2018-03-30 20:38
 * 创建者：xuhong
 * CSDN: http://blog.csdn.net/xh870189248
 * 描述：TODO
 */

public class LVDevicesAdapter extends BaseAdapter {

    private Context mContext;

    private List<GizWifiDevice> gizWifiDeviceList;

    private LayoutInflater mLayoutInflater;

    public LVDevicesAdapter(Context mContext, List<GizWifiDevice> gizWifiDeviceList) {
        this.mContext = mContext;
        this.gizWifiDeviceList = gizWifiDeviceList;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private final String TAG = "LVD";

    @Override
    public int getCount() {
        return gizWifiDeviceList.size();
    }

    @Override
    public Object getItem(int i) {
        return gizWifiDeviceList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHodlerListView viewHodlerListView = null;
        View view1;
        GizWifiDevice device = gizWifiDeviceList.get(i);
        if (view == null) {
            view1 = mLayoutInflater.inflate(R.layout.item_list_view_devices, null);
            viewHodlerListView = new ViewHodlerListView();
            //绑定控件
            viewHodlerListView.mTvName = view1.findViewById(R.id.tvDeviceName);
            viewHodlerListView.mTVStatus = view1.findViewById(R.id.tvStatus);
            viewHodlerListView.mIvDeviceIcon = view1.findViewById(R.id.ivDeviceIcon);
            viewHodlerListView.mIvNext = view1.findViewById(R.id.ivNext);
            viewHodlerListView.mTvIMEI = view1.findViewById(R.id.tvDeviceIMEI);

            view1.setTag(viewHodlerListView);
        } else {
            view1 = view;
            viewHodlerListView = (ViewHodlerListView) view1.getTag();
        }


        //设置名字。如果用户已经设置了该设备的别名，那么就优先显示别名
        if (device.getAlias().isEmpty()) {
            viewHodlerListView.mTvName.setText(device.getProductName());
        } else {
            viewHodlerListView.mTvName.setText(device.getAlias());
        }

        viewHodlerListView.mTvIMEI.setText(device.getMacAddress());

        //设置下状态
        if (device.getNetStatus() == GizWifiDeviceNetStatus.GizDeviceOffline) {
            viewHodlerListView.mTVStatus.setText("离线");
            viewHodlerListView.mTVStatus.setTextColor(mContext.getResources().getColor(R.color.text_gray_light));
            viewHodlerListView.mIvNext.setVisibility(View.GONE);//把箭头隐藏,
            viewHodlerListView.mTvName.setTextColor(mContext.getResources().getColor(R.color.text_gray_light));
            viewHodlerListView.mIvDeviceIcon.setBackgroundResource(R.drawable.ic_device_offline);

        }else {
            viewHodlerListView.mTVStatus.setText("在线");
            viewHodlerListView.mTvName.setTextColor(mContext.getResources().getColor(R.color.black));
            viewHodlerListView.mTVStatus.setTextColor(mContext.getResources().getColor(R.color.black));
            viewHodlerListView.mIvNext.setVisibility(View.VISIBLE);//把箭头显示出来
            viewHodlerListView.mIvDeviceIcon.setBackgroundResource(R.drawable.ic_device);
        }

        return view1;
    }


    private class ViewHodlerListView {
        //设备图标 ,箭头
        ImageView mIvDeviceIcon, mIvNext;

        //设备名字和设备状态
        TextView mTvName, mTVStatus,mTvIMEI;

    }


}
