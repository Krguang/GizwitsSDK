package com.yy.k.gizwitssdk.DevicesControlActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.yy.k.gizwitssdk.R;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import utils.ConstantUtil;


public class UnitMonitorActivity extends BaseDevicesControlActivity {


    private static final String TAG = "krguang";
    private static final int CODE_HANDLER_UI = 108;     //进入第一界面收到数据后传递的句柄用来刷新本页面的数据显示

    /*
     * ===========================================================
     * 以下key值对应开发者在云端定义的数据点标识名
     * ===========================================================
     */
    // 数据点"风机已启动A1"对应的标识名
    protected static final String KEY_FENGJIYIQIDONG = "fengJiYiQiDong";
    // 数据点"值班状态A1"对应的标识名
    protected static final String KEY_ZHIBANSTATUS = "zhiBanStatus";
    // 数据点"电加热1A1"对应的标识名
    protected static final String KEY_DIANJIARE1 = "dianJiaRe1";
    // 数据点"电加热2A1"对应的标识名
    protected static final String KEY_DIANJIARE2 = "dianJiaRe2";
    // 数据点"电加热3A1"对应的标识名
    protected static final String KEY_DIANJIARE3 = "dianJiaRe3";
    // 数据点"风机状态A1"对应的标识名
    protected static final String KEY_FENGJISTATUS = "fengJiStatus";
    // 数据点"手自动A1"对应的标识名
    protected static final String KEY_SHOUZIDONG = "shouZiDong";
    // 数据点"冬夏季A1"对应的标识名
    protected static final String KEY_DONGXIAJI = "dongXiaJi";
    // 数据点"中效报警A1"对应的标识名
    protected static final String KEY_ZHONGXIAOBAOJING = "zhongXiaoBaoJing";
    // 数据点"电加热高温A1"对应的标识名
    protected static final String KEY_DIANJIAREGAOWEN = "dianJiaReGaoWen";
    // 数据点"风机缺风A1"对应的标识名
    protected static final String KEY_FENGJIQUEFENG = "fengJiQueFeng";
    // 数据点"排风机已启动A1"对应的标识名
    protected static final String KEY_PAIFENGJIYIQIDONG = "paiFengJiYiQiDong";
    // 数据点"灭菌运行A1"对应的标识名
    protected static final String KEY_MIEJUNYUNXING = "mieJunYunXing";
    // 数据点"负压状态A1"对应的标识名
    protected static final String KEY_FUYAQIDONGSTATUS = "fuYaQiDongStatus";
    // 数据点"风机故障A1"对应的标识名
    protected static final String KEY_FENGJIERROR = "fengJiError";
    // 数据点"高效阻塞A1"对应的标识名
    protected static final String KEY_GAOXIAOZUSE = "gaoXiaoZuSe";
    // 数据点"实时温度A1"对应的标识名
    protected static final String KEY_TEMPREAL = "tempReal";
    // 数据点"实时湿度A1"对应的标识名
    protected static final String KEY_HUMIREAL = "humiReal";
    // 数据点"温度设定值A1"对应的标识名
    protected static final String KEY_TEMPSET = "tempSet";
    // 数据点"湿度设定值A1"对应的标识名
    protected static final String KEY_HUMISET = "humiSet";
    // 数据点"冷水阀开度A1"对应的标识名
    protected static final String KEY_LENGSHUIFAKAIDU = "lengShuiFaKaiDu";
    // 数据点"热水阀开度A1"对应的标识名
    protected static final String KEY_RESHUIFAKAIDU = "reShuiFaKaiDu";
    // 数据点"新风温度A1"对应的标识名
    protected static final String KEY_XINFENGWENDU = "xinFengWenDU";
    // 数据点"加湿器开度A1"对应的标识名
    protected static final String KEY_JIASHIQIKAIDU = "jiaShiQIKaiDu";



    /*
     * ===========================================================
     * 以下变量对应设备上报类型为布尔、数值、扩展数据点的数据存储
     * ===========================================================
     */
    // 数据点"风机已启动A1"对应的存储数据
    protected static boolean data_fengJiYiQiDong;
    // 数据点"值班状态A1"对应的存储数据
    protected static boolean data_zhiBanStatus;
    // 数据点"电加热1A1"对应的存储数据
    protected static boolean data_dianJiaRe1;
    // 数据点"电加热2A1"对应的存储数据
    protected static boolean data_dianJiaRe2;
    // 数据点"电加热3A1"对应的存储数据
    protected static boolean data_dianJiaRe3;
    // 数据点"风机状态A1"对应的存储数据
    protected static boolean data_fengJiStatus;
    // 数据点"手自动A1"对应的存储数据
    protected static boolean data_shouZiDong;
    // 数据点"冬夏季A1"对应的存储数据
    protected static boolean data_dongXiaJi;
    // 数据点"中效报警A1"对应的存储数据
    protected static boolean data_zhongXiaoBaoJing;
    // 数据点"电加热高温A1"对应的存储数据
    protected static boolean data_dianJiaReGaoWen;
    // 数据点"风机缺风A1"对应的存储数据
    protected static boolean data_fengJiQueFeng;
    // 数据点"排风机已启动A1"对应的存储数据
    protected static boolean data_paiFengJiYiQiDong;
    // 数据点"灭菌运行A1"对应的存储数据
    protected static boolean data_mieJunYunXing;
    // 数据点"负压状态A1"对应的存储数据
    protected static boolean data_fuYaQiDongStatus;
    // 数据点"风机故障A1"对应的存储数据
    protected static boolean data_fengJiError;
    // 数据点"高效阻塞A1"对应的存储数据
    protected static boolean data_gaoXiaoZuSe;
    // 数据点"实时温度A1"对应的存储数据
    protected static int data_tempReal;
    // 数据点"实时湿度A1"对应的存储数据
    protected static int data_humiReal;
    // 数据点"温度设定值A1"对应的存储数据
    protected static int data_tempSet;
    // 数据点"湿度设定值A1"对应的存储数据
    protected static int data_humiSet;
    // 数据点"冷水阀开度A1"对应的存储数据
    protected static int data_lengShuiFaKaiDu;
    // 数据点"热水阀开度A1"对应的存储数据
    protected static int data_reShuiFaKaiDu;
    // 数据点"新风温度A1"对应的存储数据
    protected static int data_xinFengWenDU;
    // 数据点"加湿器开度A1"对应的存储数据
    protected static int data_jiaShiQIKaiDu;


    private TextView tv_data_tempReal;
    private TextView tv_data_humiReal;
    private TextView tv_data_tempSet;
    private TextView tv_data_humiSet;
    private TextView tv_data_lengShuiFaKaiDu;
    private TextView tv_data_reShuiFaKaiDu;
    private TextView tv_data_jiaShiQIKaiDu;

    private TextView tv_dongXiaJi;
    private TextView tv_shouZiDong;

    private Button bt_fengJiYiQiDong;
    private Button bt_zhiBanStatus;
    private Button bt_fuYaStatus;
    private Button bt_jiZuError;
    private Button bt_gaoXiaoBaoJing;
    private Button bt_dianJiaRe1;
    private Button bt_dianJiaRe2;
    private Button bt_dianJiaRe3;
    private Button bt_fengJiStatus;
    private Button bt_zhongXiaoBaoJing;
    private Button bt_dianJiaReGaoWen;
    private Button bt_fengJiQueFeng;
    private Button bt_paiFengJiYiQiDong;
    private Button bt_mieJunYunXing;

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CODE_HANDLER_UI:
                    updateUI();
                    break;
            }
        }
    };

    private void updateUI() {

        tv_data_tempReal.setText(formatValue(data_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_jiaShiQIKaiDu/10.0,0.1)+"%");


        if (data_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_shouZiDong){
            tv_shouZiDong.setText("自动");
        }else {
            tv_shouZiDong.setText("手动");
        }

        if (data_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_fuYaQiDongStatus){
            bt_fuYaStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fuYaStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_fengJiError){
            bt_jiZuError.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_jiZuError.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_gaoXiaoZuSe){
            bt_gaoXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_gaoXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }

        if (data_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);
        initView();
        setTopBar();
    }

    private void setTopBar() {
        qmuiTopBar = findViewById(R.id.topBar);
        qmuiTopBar.setTitle(Objects.equals(mDevice.getAlias(), "") || mDevice.getAlias() == null ? mDevice.getProductName() : mDevice.getAlias());
        qmuiTopBar.addLeftImageButton(R.mipmap.back_botton, R.id.qmui_topbar_item_left_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void didReceiveData(GizWifiErrorCode result, GizWifiDevice device, ConcurrentHashMap<String, Object> dataMap, int sn) {
        super.didReceiveData(result, device, dataMap, sn);
        Log.d(TAG, "UnitJingTaiActivity: 获取设备"+device);
        Log.d(TAG, "UnitJingTaiActivity: result = "+result);
        if (result == GizWifiErrorCode.GIZ_SDK_SUCCESS) {
            if (dataMap != null) {
                if (!dataMap.isEmpty()) {
                    parseReceiveData(dataMap);
                }
            }
        }
    }

    private void parseReceiveData(ConcurrentHashMap<String, Object> dataMap) {

        if (dataMap.get("data") != null) {
            ConcurrentHashMap<String, Object> map = (ConcurrentHashMap<String, Object>) dataMap.get("data");
            for (String dataKey : map.keySet()) {
                if (dataKey.equals(KEY_FENGJIYIQIDONG)) {
                    data_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_ZHIBANSTATUS)) {
                    data_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_DIANJIARE1)) {
                    data_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_DIANJIARE2)) {
                    data_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_DIANJIARE3)) {
                    data_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_FENGJISTATUS)) {
                    data_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_SHOUZIDONG)) {
                    data_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_DONGXIAJI)) {
                    data_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_ZHONGXIAOBAOJING)) {
                    data_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_DIANJIAREGAOWEN)) {
                    data_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_FENGJIQUEFENG)) {
                    data_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAIFENGJIYIQIDONG)) {
                    data_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MIEJUNYUNXING)) {
                    data_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_FUYAQIDONGSTATUS)) {
                    data_fuYaQiDongStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_FENGJIERROR)) {
                    data_fengJiError = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_GAOXIAOZUSE)) {
                    data_gaoXiaoZuSe = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_TEMPREAL)) {

                    data_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_HUMIREAL)) {

                    data_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_TEMPSET)) {

                    data_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_HUMISET)) {

                    data_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_LENGSHUIFAKAIDU)) {

                    data_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_RESHUIFAKAIDU)) {

                    data_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_XINFENGWENDU)) {

                    data_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JIASHIQIKAIDU)) {

                    data_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
            }
        }
        mHandler.sendEmptyMessage(CODE_HANDLER_UI);
    }

    private void initView() {

        tv_data_tempReal = (TextView) findViewById(R.id.tv_data_tempReal);
        tv_data_humiReal = (TextView) findViewById(R.id.tv_data_humiReal);
        tv_data_tempSet = (TextView) findViewById(R.id.tv_data_tempSet);
        tv_data_humiSet = (TextView) findViewById(R.id.tv_data_humiSet);;
        tv_data_lengShuiFaKaiDu = (TextView) findViewById(R.id.tv_data_lengShuiFaKaiDu);
        tv_data_reShuiFaKaiDu = (TextView) findViewById(R.id.tv_data_reShuiFaKaiDu);
        tv_data_jiaShiQIKaiDu = (TextView) findViewById(R.id.tv_data_jiaShiQIKaiDu);

        tv_dongXiaJi = (TextView) findViewById(R.id.tv_dongXiaJi);
        tv_shouZiDong = (TextView) findViewById(R.id.tv_shouZiDong);

        bt_fengJiYiQiDong = (Button) findViewById(R.id.bt_fengJiYiQiDong);
        bt_zhiBanStatus = (Button) findViewById(R.id.bt_zhiBanStatus);
        bt_fuYaStatus = findViewById(R.id.bt_fuYaStatus);
        bt_jiZuError = findViewById(R.id.bt_jiZuError);
        bt_gaoXiaoBaoJing = findViewById(R.id.bt_gaoXiaoZuSe);
        bt_dianJiaRe1 = (Button) findViewById(R.id.bt_dianJiaRe1);
        bt_dianJiaRe2 = (Button) findViewById(R.id.bt_dianJiaRe2);
        bt_dianJiaRe3 = (Button) findViewById(R.id.bt_dianJiaRe3);
        bt_fengJiStatus = (Button) findViewById(R.id.bt_fengJiStatus);
        bt_zhongXiaoBaoJing = (Button) findViewById(R.id.bt_zhongXiaoBaoJing);
        bt_dianJiaReGaoWen = (Button) findViewById(R.id.bt_dianJiaReGaoWen);
        bt_fengJiQueFeng = (Button) findViewById(R.id.bt_fengJiQueFeng);
        bt_paiFengJiYiQiDong = (Button) findViewById(R.id.bt_paiFengJiYiQiDong);
        bt_mieJunYunXing = (Button) findViewById(R.id.bt_mieJunYunXing);

        tv_data_tempReal.setText("00.0℃");
        tv_data_humiReal.setText("00.0RH");
        tv_data_tempSet.setText("00.0℃");
        tv_data_humiSet.setText("00.0RH");
        tv_data_lengShuiFaKaiDu.setText("00.0%");
        tv_data_reShuiFaKaiDu.setText("00.0%");
        tv_data_jiaShiQIKaiDu.setText("00.0%");
        tv_dongXiaJi.setText("");
        tv_shouZiDong.setText("");
        bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        bt_fuYaStatus.setBackgroundResource(R.drawable.led_gray);
        bt_jiZuError.setBackgroundResource(R.drawable.led_gray);
        bt_gaoXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅云端消息
        mDevice.setListener(null);
        mDevice.setSubscribe(ConstantUtil.MONITOR_PRODUCT_SECRET, false);
    }
}