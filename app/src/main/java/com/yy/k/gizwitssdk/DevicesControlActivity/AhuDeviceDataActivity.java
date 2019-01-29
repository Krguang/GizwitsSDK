package com.yy.k.gizwitssdk.DevicesControlActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.yy.k.gizwitssdk.CustomApplication;
import com.yy.k.gizwitssdk.R;


import java.text.DecimalFormat;

import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_diWenPanGuan;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_dianJiaRe1;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_dianJiaRe2;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_dianJiaRe3;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_dianJiaReGaoWen;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_dongXiaJi;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_fengJiQueFeng;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_fengJiStatus;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_fengJiYiQiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_humiReal;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_humiSet;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_jiaShiQIKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_lengShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_mieJunYunXing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_paiFengJiYiQiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_reShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_shouZiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_tempReal;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_tempSet;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_zhiBanStatus;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_1_zhongXiaoBaoJing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_diWenPanGuan;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_dianJiaRe1;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_dianJiaRe2;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_dianJiaRe3;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_dianJiaReGaoWen;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_dongXiaJi;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_fengJiQueFeng;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_fengJiStatus;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_fengJiYiQiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_humiReal;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_humiSet;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_jiaShiQIKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_lengShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_mieJunYunXing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_paiFengJiYiQiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_reShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_shouZiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_tempReal;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_tempSet;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_zhiBanStatus;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_2_zhongXiaoBaoJing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_diWenPanGuan;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_dianJiaRe1;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_dianJiaRe2;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_dianJiaRe3;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_dianJiaReGaoWen;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_dongXiaJi;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_fengJiQueFeng;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_fengJiStatus;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_fengJiYiQiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_humiReal;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_humiSet;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_jiaShiQIKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_lengShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_mieJunYunXing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_paiFengJiYiQiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_reShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_shouZiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_tempReal;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_tempSet;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_zhiBanStatus;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_3_zhongXiaoBaoJing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_diWenPanGuan;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_dianJiaRe1;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_dianJiaRe2;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_dianJiaRe3;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_dianJiaReGaoWen;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_dongXiaJi;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_fengJiQueFeng;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_fengJiStatus;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_fengJiYiQiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_humiReal;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_humiSet;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_jiaShiQIKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_lengShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_mieJunYunXing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_paiFengJiYiQiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_reShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_shouZiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_tempReal;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_tempSet;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_zhiBanStatus;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.data_JK_XF_zhongXiaoBaoJing;

public class AhuDeviceDataActivity extends AppCompatActivity {


    private static final String TAG = "krguang";
    private static final int AHU_HANDLER_UI = 106;

    protected QMUITopBar qmuiTopBar;

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
    private Button bt_dianJiaRe1;
    private Button bt_dianJiaRe2;
    private Button bt_dianJiaRe3;
    private Button bt_fengJiStatus;
    private Button bt_zhongXiaoBaoJing;
    private Button bt_dianJiaReGaoWen;
    private Button bt_fengJiQueFeng;
    private Button bt_paiFengJiYiQiDong;
    private Button bt_diWenPanGuan;
    private Button bt_mieJunYunXing;

    private String stringFromPrevious;
    private CustomApplication app;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case AHU_HANDLER_UI:
                    Log.d(TAG, "handleMessage: 收到了第一界面的消息:"+msg.arg1);
                    updateUI();
            }
        }
    };
    
    private void setTopBar() {
        qmuiTopBar = findViewById(R.id.topBar);
        qmuiTopBar.setTitle(stringFromPrevious);
        qmuiTopBar.addLeftImageButton(R.mipmap.back_botton, R.id.qmui_topbar_item_left_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_k_ahu);

        initDevice();
        initView();
        setTopBar();
        updateUI();
        app = (CustomApplication) getApplication();
        app.setHandler(mHandler);
    }

    protected void initDevice() {
        Intent intent = getIntent();
        stringFromPrevious = intent.getStringExtra("extra_data");
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
        bt_dianJiaRe1 = (Button) findViewById(R.id.bt_dianJiaRe1);
        bt_dianJiaRe2 = (Button) findViewById(R.id.bt_dianJiaRe2);
        bt_dianJiaRe3 = (Button) findViewById(R.id.bt_dianJiaRe3);
        bt_fengJiStatus = (Button) findViewById(R.id.bt_fengJiStatus);
        bt_zhongXiaoBaoJing = (Button) findViewById(R.id.bt_zhongXiaoBaoJing);
        bt_dianJiaReGaoWen = (Button) findViewById(R.id.bt_dianJiaReGaoWen);
        bt_fengJiQueFeng = (Button) findViewById(R.id.bt_fengJiQueFeng);
        bt_paiFengJiYiQiDong = (Button) findViewById(R.id.bt_paiFengJiYiQiDong);
        bt_diWenPanGuan = (Button) findViewById(R.id.bt_diWenPanGuan);
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
        bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
    }
    

    /**
     * Description:根据保存的的数据点的值来更新UI
     */
    private void updateUI() {

        switch (stringFromPrevious){

            case "JK_1":
                updateAhu301();
                break;

            case "JK_2":
                updateAhu302();
                break;

            case "JK_3":
                updateAhu303();
                break;

            case "JK_XF":
                updateAhu304();
                break;
            default:
                break;
        }
    }

    private void updateAhu301() {

        Log.d(TAG, "updateAhu301: data_JK_1_tempReal = "+data_JK_1_tempReal);
        tv_data_tempReal.setText(formatValue(data_JK_1_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_JK_1_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_JK_1_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_JK_1_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_JK_1_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_JK_1_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_JK_1_jiaShiQIKaiDu/10.0,0.1)+"%");


        if (data_JK_1_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_JK_1_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_JK_1_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_1_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_1_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_1_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_1_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_1_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_1_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_1_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_1_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_1_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_1_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_1_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }
    }

    private void updateAhu302() {

        tv_data_tempReal.setText(formatValue(data_JK_2_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_JK_2_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_JK_2_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_JK_2_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_JK_2_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_JK_2_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_JK_2_jiaShiQIKaiDu/10.0,0.1)+"%");

        if (data_JK_2_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_JK_2_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_JK_2_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_2_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_2_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_2_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_2_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_2_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_2_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_2_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_2_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_2_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_2_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_2_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    private void updateAhu303() {

        tv_data_tempReal.setText(formatValue(data_JK_3_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_JK_3_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_JK_3_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_JK_3_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_JK_3_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_JK_3_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_JK_3_jiaShiQIKaiDu/10.0,0.1)+"%");

        if (data_JK_3_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_JK_3_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_JK_3_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_3_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_3_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_3_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_3_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_3_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_3_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_3_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_3_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_3_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_3_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_3_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    private void updateAhu304() {

        tv_data_tempReal.setText(formatValue(data_JK_XF_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_JK_XF_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_JK_XF_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_JK_XF_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_JK_XF_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_JK_XF_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_JK_XF_jiaShiQIKaiDu/10.0,0.1)+"%");

        if (data_JK_XF_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_JK_XF_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_JK_XF_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_XF_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_XF_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_XF_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_XF_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_XF_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_XF_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_XF_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_XF_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_XF_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_XF_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_JK_XF_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
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
