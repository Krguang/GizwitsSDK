package com.yy.k.gizwitssdk.DevicesControlActivity;

import android.annotation.SuppressLint;
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

import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.*;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitJingTaiActivity.*;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitTianJinSanYuanActivity.*;

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

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == AHU_HANDLER_UI) {
                Log.d(TAG, "AhuDeviceDataActivity：handleMessage: 收到了第一界面的消息:" + msg.arg1);
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
        CustomApplication app = (CustomApplication) getApplication();
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
                updateJK_1();
                break;

            case "JK_2":
                updateJK_2();
                break;

            case "JK_3":
                updateJK_3();
                break;

            case "JK_XF":
                updateJK_XF();
                break;

            case "AHU301":
                updateAhu301();
                break;

            case "AHU302":
                updateAhu302();
                break;

            case "AHU303":
                updateAhu303();
                break;

            case "AHU304":
                updateAhu304();
                break;

            case "AHU305":
                updateAhu305();
                break;

            case "AHU306":
                updateAhu306();
                break;

            case "AHU307":
                updateAhu307();
                break;

            case "AHU308":
                updateAhu308();
                break;

            case "AHU01":
                updateAhu01();
                break;

            case "AHU02":
                updateAhu02();
                break;

            case "AHU03":
                updateAhu03();
                break;

            case "AHU04":
                updateAhu04();
                break;

            case "AHU05":
                updateAhu05();
                break;

            case "AHU06":
                updateAhu06();
                break;

            case "AHU07":
                updateAhu07();
                break;

            case "AHU08":
                updateAhu08();
                break;

            case "AHU09":
                updateAhu09();
                break;

            case "AHU10":
                updateAhu10();
                break;

            default:
                break;
        }
    }

    private void updateAhu01() {

        tv_data_tempReal.setText(formatValue(data_AHU01_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU01_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU01_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU01_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU01_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU01_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU01_jiaShiQIKaiDu/10.0,0.1)+"%");


        if (data_AHU01_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU01_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU01_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU01_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU01_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU01_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU01_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU01_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU01_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU01_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU01_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU01_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU01_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU01_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }
    }

    private void updateAhu02() {
        tv_data_tempReal.setText(formatValue(data_AHU02_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU02_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU02_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU02_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU02_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU02_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU02_jiaShiQIKaiDu/10.0,0.1)+"%");


        if (data_AHU02_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU02_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU02_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU02_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU02_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU02_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU02_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU02_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU02_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU02_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU02_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU02_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU02_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU02_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    private void updateAhu03() {

        tv_data_tempReal.setText(formatValue(data_AHU03_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU03_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU03_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU03_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU03_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU03_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU03_jiaShiQIKaiDu/10.0,0.1)+"%");


        if (data_AHU03_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU03_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU03_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU03_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU03_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU03_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU03_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU03_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU03_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU03_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU03_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU03_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU03_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU03_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }
    }

    private void updateAhu04() {
        tv_data_tempReal.setText(formatValue(data_AHU04_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU04_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU04_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU04_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU04_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU04_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU04_jiaShiQIKaiDu/10.0,0.1)+"%");


        if (data_AHU04_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU04_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU04_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU04_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU04_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU04_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU04_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU04_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU04_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU04_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU04_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU04_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU04_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU04_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    private void updateAhu05() {

        tv_data_tempReal.setText(formatValue(data_AHU05_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU05_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU05_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU05_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU05_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU05_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU05_jiaShiQIKaiDu/10.0,0.1)+"%");


        if (data_AHU05_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU05_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU05_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU05_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU05_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU05_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU05_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU05_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU05_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU05_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU05_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU05_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU05_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU05_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }
    }

    private void updateAhu06() {

        tv_data_tempReal.setText(formatValue(data_AHU06_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU06_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU06_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU06_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU06_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU06_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU06_jiaShiQIKaiDu/10.0,0.1)+"%");


        if (data_AHU06_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU06_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU06_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU06_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU06_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU06_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU06_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU06_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU06_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU06_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU06_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU06_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU06_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU06_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }
    }

    private void updateAhu07() {

        tv_data_tempReal.setText(formatValue(data_AHU07_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU07_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU07_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU07_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU07_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU07_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU07_jiaShiQIKaiDu/10.0,0.1)+"%");


        if (data_AHU07_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU07_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU07_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU07_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU07_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU07_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU07_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU07_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU07_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU07_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU07_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU07_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU07_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU07_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }
    }

    private void updateAhu08() {

        tv_data_tempReal.setText(formatValue(data_AHU08_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU08_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU08_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU08_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU08_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU08_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU08_jiaShiQIKaiDu/10.0,0.1)+"%");


        if (data_AHU08_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU08_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU08_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU08_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU08_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU08_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU08_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU08_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU08_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU08_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU08_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU08_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU08_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU08_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }
    }

    private void updateAhu09() {

        tv_data_tempReal.setText(formatValue(data_AHU09_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU09_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU09_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU09_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU09_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU09_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU09_jiaShiQIKaiDu/10.0,0.1)+"%");


        if (data_AHU09_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU09_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU09_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU09_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU09_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU09_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU09_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU09_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU09_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU09_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU09_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU09_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU09_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU09_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }
    }

    private void updateAhu10() {

        tv_data_tempReal.setText(formatValue(data_AHU10_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU10_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU10_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU10_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU10_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU10_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU10_jiaShiQIKaiDu/10.0,0.1)+"%");


        if (data_AHU10_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU10_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU10_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU10_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU10_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU10_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU10_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU10_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU10_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU10_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU10_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU10_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU10_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU10_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }
    }


    private void updateJK_1() {

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

    private void updateJK_2() {

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

    private void updateJK_3() {

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

    private void updateJK_XF() {

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


    private void updateAhu301() {

        tv_data_tempReal.setText(formatValue(data_AHU301_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU301_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU301_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU301_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU301_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU301_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU301_jiaShiQIKaiDu/10.0,0.1)+"%");


        if (data_AHU301_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU301_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU301_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU301_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU301_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU301_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU301_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU301_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU301_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU301_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU301_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU301_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU301_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU301_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }
    }

    private void updateAhu302() {

        tv_data_tempReal.setText(formatValue(data_AHU302_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU302_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU302_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU302_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU302_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU302_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU302_jiaShiQIKaiDu/10.0,0.1)+"%");

        if (data_AHU302_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU302_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU302_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU302_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU302_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU302_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU302_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU302_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU302_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU302_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU302_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU302_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU302_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU302_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    private void updateAhu303() {

        tv_data_tempReal.setText(formatValue(data_AHU303_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU303_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU303_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU303_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU303_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU303_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU303_jiaShiQIKaiDu/10.0,0.1)+"%");

        if (data_AHU303_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU303_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU303_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU303_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU303_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU303_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU303_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU303_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU303_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU303_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU303_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU303_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU303_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU303_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    private void updateAhu304() {

        tv_data_tempReal.setText(formatValue(data_AHU304_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU304_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU304_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU304_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU304_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU304_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU304_jiaShiQIKaiDu/10.0,0.1)+"%");

        if (data_AHU304_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU304_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU304_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU304_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU304_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU304_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU304_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU304_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU304_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU304_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU304_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU304_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU304_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU304_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    private void updateAhu305() {

        tv_data_tempReal.setText(formatValue(data_AHU305_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU305_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU305_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU305_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU305_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU305_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU305_jiaShiQIKaiDu/10.0,0.1)+"%");

        if (data_AHU305_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU305_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU305_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU305_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU305_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU305_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU305_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU305_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU305_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU305_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU305_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU305_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU305_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU305_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    private void updateAhu306() {

        tv_data_tempReal.setText(formatValue(data_AHU306_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU306_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU306_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU306_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU306_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU306_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU306_jiaShiQIKaiDu/10.0,0.1)+"%");

        if (data_AHU306_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU306_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU306_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU306_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU306_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU306_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU306_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU306_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU306_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU306_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU306_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU306_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU306_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU306_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    private void updateAhu307() {

        tv_data_tempReal.setText(formatValue(data_AHU307_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU307_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU307_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU307_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU307_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU307_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU307_jiaShiQIKaiDu/10.0,0.1)+"%");

        if (data_AHU307_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU307_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU307_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU307_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU307_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU307_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU307_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU307_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU307_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU307_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU307_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU307_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU307_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU307_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    private void updateAhu308() {

        tv_data_tempReal.setText(formatValue(data_AHU308_tempReal/10.0,0.1)+"℃");
        tv_data_humiReal.setText(formatValue(data_AHU308_humiReal/10.0,0.1)+"RH");
        tv_data_tempSet.setText(formatValue(data_AHU308_tempSet/10.0,0.1)+"℃");
        tv_data_humiSet.setText(formatValue(data_AHU308_humiSet/10.0,0.1)+"RH");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_AHU308_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_AHU308_reShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_jiaShiQIKaiDu.setText(formatValue(data_AHU308_jiaShiQIKaiDu/10.0,0.1)+"%");

        if (data_AHU308_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_AHU308_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_AHU308_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU308_zhiBanStatus){
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_zhiBanStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU308_dianJiaRe1){
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU308_dianJiaRe2){
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU308_dianJiaRe3){
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianJiaRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU308_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU308_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU308_dianJiaReGaoWen){
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianJiaReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU308_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU308_paiFengJiYiQiDong){
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_paiFengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU308_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_AHU308_mieJunYunXing){
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
