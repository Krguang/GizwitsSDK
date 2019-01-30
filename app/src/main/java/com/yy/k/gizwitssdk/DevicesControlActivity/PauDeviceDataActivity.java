package com.yy.k.gizwitssdk.DevicesControlActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.enumration.GizWifiDeviceNetStatus;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.qmuiteam.qmui.widget.QMUITopBar;
import com.yy.k.gizwitssdk.CustomApplication;
import com.yy.k.gizwitssdk.R;

import java.text.DecimalFormat;
import java.util.concurrent.ConcurrentHashMap;

import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_diWenPanGuan;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_dianYuRe1;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_dianYuRe2;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_dianYuRe3;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_dianYuReGaoWen;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_dongXiaJi;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_fengJiQueFeng;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_fengJiStatus;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_fengJiYiQiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_lengShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_mieJunYunXing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_reShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_shouZiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_tempReal;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_tempSet;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_xinFengWenDU;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU301_zhongXiaoBaoJing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_diWenPanGuan;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_dianYuRe1;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_dianYuRe2;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_dianYuRe3;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_dianYuReGaoWen;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_dongXiaJi;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_fengJiQueFeng;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_fengJiStatus;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_fengJiYiQiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_lengShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_mieJunYunXing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_reShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_shouZiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_tempReal;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_tempSet;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_xinFengWenDU;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU302_zhongXiaoBaoJing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_diWenPanGuan;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_dianYuRe1;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_dianYuRe2;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_dianYuRe3;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_dianYuReGaoWen;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_dongXiaJi;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_fengJiQueFeng;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_fengJiStatus;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_fengJiYiQiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_lengShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_mieJunYunXing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_reShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_shouZiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_tempReal;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_tempSet;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_xinFengWenDU;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU303_zhongXiaoBaoJing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_diWenPanGuan;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_dianYuRe1;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_dianYuRe2;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_dianYuRe3;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_dianYuReGaoWen;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_dongXiaJi;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_fengJiQueFeng;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_fengJiStatus;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_fengJiYiQiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_lengShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_mieJunYunXing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_reShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_shouZiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_tempReal;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_tempSet;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_xinFengWenDU;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU304_zhongXiaoBaoJing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_diWenPanGuan;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_dianYuRe1;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_dianYuRe2;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_dianYuRe3;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_dianYuReGaoWen;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_dongXiaJi;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_fengJiQueFeng;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_fengJiStatus;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_fengJiYiQiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_lengShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_mieJunYunXing;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_reShuiFaKaiDu;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_shouZiDong;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_tempReal;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_tempSet;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_xinFengWenDU;
import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitDaLianActivity.data_PAU305_zhongXiaoBaoJing;

public class PauDeviceDataActivity extends AppCompatActivity {


    private static final String TAG = "krguang";
    private static final int AHU_DALIAN_HANDLER_UI = 108;
    protected QMUITopBar qmuiTopBar;

    private TextView tv_data_tempReal;
    private TextView tv_data_xinFengWenDu;
    private TextView tv_data_tempSet;
    private TextView tv_data_lengShuiFaKaiDu;
    private TextView tv_data_reShuiFaKaiDu;

    private TextView tv_dongXiaJi;
    private TextView tv_shouZiDong;

    private Button bt_fengJiYiQiDong;
    private Button bt_dianYuRe1;
    private Button bt_dianYuRe2;
    private Button bt_dianYuRe3;
    private Button bt_fengJiStatus;
    private Button bt_zhongXiaoBaoJing;
    private Button bt_dianYuReGaoWen;
    private Button bt_fengJiQueFeng;
    private Button bt_diWenPanGuan;
    private Button bt_mieJunYunXing;

    String stringFromPrevious;


    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case AHU_DALIAN_HANDLER_UI:
                    Log.d(TAG, "handleMessage: 收到了第一界面的消息:"+msg.arg1);
                    updateUI();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_k_pau);

        initDevice();
        initView();
        initEvent();
        setTopBar();
        updateUI();
        CustomApplication app = (CustomApplication) getApplication();
        app.setHandler(mHandler);
    }

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

    private void initView() {

        tv_data_tempReal = (TextView) findViewById(R.id.tv_data_tempReal);
        tv_data_xinFengWenDu = (TextView) findViewById(R.id.tv_data_xinFengWenDU);
        tv_data_tempSet = (TextView) findViewById(R.id.tv_data_tempSet);
        tv_data_lengShuiFaKaiDu = (TextView) findViewById(R.id.tv_data_lengShuiFaKaiDu);
        tv_data_reShuiFaKaiDu = (TextView) findViewById(R.id.tv_data_reShuiFaKaiDu);

        tv_dongXiaJi = (TextView) findViewById(R.id.tv_dongXiaJi);
        tv_shouZiDong = (TextView) findViewById(R.id.tv_shouZiDong);

        bt_fengJiYiQiDong = (Button) findViewById(R.id.bt_fengJiYiQiDong);
        bt_dianYuRe1 = (Button) findViewById(R.id.bt_dianYuRe1);
        bt_dianYuRe2 = (Button) findViewById(R.id.bt_dianYuRe2);
        bt_dianYuRe3 = (Button) findViewById(R.id.bt_dianYuRe3);
        bt_fengJiStatus = (Button) findViewById(R.id.bt_fengJiStatus);
        bt_zhongXiaoBaoJing = (Button) findViewById(R.id.bt_zhongXiaoBaoJing);
        bt_dianYuReGaoWen = (Button) findViewById(R.id.bt_dianYuReGaoWen);
        bt_fengJiQueFeng = (Button) findViewById(R.id.bt_fengJiQueFeng);
        bt_diWenPanGuan = (Button) findViewById(R.id.bt_diWenPanGuan);
        bt_mieJunYunXing = (Button) findViewById(R.id.bt_mieJunYunXing);

    }

    private void initEvent() {

        tv_data_tempReal.setText("00.0℃");
        tv_data_xinFengWenDu.setText("00.0℃");
        tv_data_tempSet.setText("00.0℃");
        tv_data_lengShuiFaKaiDu.setText("00.0%");
        tv_data_reShuiFaKaiDu.setText("00.0%");
        tv_dongXiaJi.setText("");
        tv_shouZiDong.setText("");
        bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        bt_dianYuRe1.setBackgroundResource(R.drawable.led_gray);
        bt_dianYuRe2.setBackgroundResource(R.drawable.led_gray);
        bt_dianYuRe3.setBackgroundResource(R.drawable.led_gray);
        bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_gray);
        bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
    }

    private void initDevice() {
        Intent intent = getIntent();
        stringFromPrevious = intent.getStringExtra("extra_data");
    }

    /**
     * Description:根据保存的的数据点的值来更新UI
     */
    protected void updateUI() {

        switch (stringFromPrevious){

            case "PAU301":
                updatePau301();
                break;

            case "PAU302":
                updatePau302();
                break;

            case "PAU303":
                updatePau303();
                break;

            case "PAU304":
                updatePau304();
                break;

            case "PAU305":
                updatePau305();
                break;

            default:break;
        }
    }

    private void updatePau301() {

        tv_data_tempReal.setText(formatValue(data_PAU301_tempReal/10.0,0.1)+"℃");
        tv_data_xinFengWenDu.setText(formatValue(data_PAU301_xinFengWenDU/10.0,0.1)+"℃");
        tv_data_tempSet.setText(formatValue(data_PAU301_tempSet/10.0,0.1)+"℃");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_PAU301_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_PAU301_reShuiFaKaiDu/10.0,0.1)+"%");

        if (data_PAU301_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_PAU301_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_PAU301_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU301_dianYuRe1){
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU301_dianYuRe2){
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU301_dianYuRe3){
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU301_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU301_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU301_dianYuReGaoWen){
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU301_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU301_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU301_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }
    }

    private void updatePau302() {

        tv_data_tempReal.setText(data_PAU302_tempReal+"℃");
        tv_data_xinFengWenDu.setText(data_PAU302_xinFengWenDU+"℃");
        tv_data_tempSet.setText(data_PAU302_tempSet+"℃");
        tv_data_lengShuiFaKaiDu.setText(data_PAU302_lengShuiFaKaiDu+"%");
        tv_data_reShuiFaKaiDu.setText(data_PAU302_reShuiFaKaiDu+"%");

        if (data_PAU302_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_PAU302_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_PAU302_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }


        if (data_PAU302_dianYuRe1){
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU302_dianYuRe2){
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU302_dianYuRe3){
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU302_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU302_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU302_dianYuReGaoWen){
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU302_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU302_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU302_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    private void updatePau303() {

        tv_data_tempReal.setText(data_PAU303_tempReal+"℃");
        tv_data_xinFengWenDu.setText(data_PAU303_xinFengWenDU+"℃");
        tv_data_tempSet.setText(data_PAU303_tempSet+"℃");
        tv_data_lengShuiFaKaiDu.setText(data_PAU303_lengShuiFaKaiDu+"%");
        tv_data_reShuiFaKaiDu.setText(data_PAU303_reShuiFaKaiDu+"%");

        if (data_PAU303_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_PAU303_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_PAU303_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU303_dianYuRe1){
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU303_dianYuRe2){
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU303_dianYuRe3){
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU303_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU303_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU303_dianYuReGaoWen){
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU303_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU303_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU303_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    private void updatePau304() {

        tv_data_tempReal.setText(data_PAU304_tempReal+"℃");
        tv_data_xinFengWenDu.setText(data_PAU304_xinFengWenDU+"℃");
        tv_data_tempSet.setText(data_PAU304_tempSet+"℃");
        tv_data_lengShuiFaKaiDu.setText(data_PAU304_lengShuiFaKaiDu+"%");
        tv_data_reShuiFaKaiDu.setText(data_PAU304_reShuiFaKaiDu+"%");

        if (data_PAU304_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_PAU304_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_PAU304_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }


        if (data_PAU304_dianYuRe1){
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU304_dianYuRe2){
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU304_dianYuRe3){
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU304_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU304_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU304_dianYuReGaoWen){
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU304_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU304_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU304_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }

    }

    private void updatePau305() {

        tv_data_tempReal.setText(data_PAU305_tempReal+"℃");
        tv_data_xinFengWenDu.setText(data_PAU305_xinFengWenDU+"℃");
        tv_data_tempSet.setText(data_PAU305_tempSet+"℃");
        tv_data_lengShuiFaKaiDu.setText(data_PAU305_lengShuiFaKaiDu+"%");
        tv_data_reShuiFaKaiDu.setText(data_PAU305_reShuiFaKaiDu+"%");

        if (data_PAU305_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_PAU305_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_PAU305_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }


        if (data_PAU305_dianYuRe1){
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU305_dianYuRe2){
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU305_dianYuRe3){
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU305_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU305_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU305_dianYuReGaoWen){
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU305_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU305_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU305_mieJunYunXing){
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
