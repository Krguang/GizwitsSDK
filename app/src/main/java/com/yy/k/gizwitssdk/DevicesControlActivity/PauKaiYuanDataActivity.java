package com.yy.k.gizwitssdk.DevicesControlActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.yy.k.gizwitssdk.CustomApplication;
import com.yy.k.gizwitssdk.R;

import java.text.DecimalFormat;

import static com.yy.k.gizwitssdk.DevicesControlActivity.UnitKaiYuanGuKeActivity.*;

public class PauKaiYuanDataActivity extends AppCompatActivity {


    private static final String TAG = "PauKaiYuanDataActivity";
    private static final int AHU_KAIYUAN_HANDLER_UI = 301;
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
                case AHU_KAIYUAN_HANDLER_UI:
                    Log.d(TAG, "handleMessage: 收到了第一界面的消息:"+msg.arg1);
                    updateUI();
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

            case "PAU01":
                updatePau01();
                break;

            case "MAU01":
                updateMau01();
                break;
            default:break;
        }
    }

    private void updateMau01() {
        tv_data_tempReal.setText(formatValue(data_MAU01_tempReal/10.0,0.1)+"℃");
        tv_data_xinFengWenDu.setText(formatValue(data_MAU01_xinFengWenDU/10.0,0.1)+"℃");
        tv_data_tempSet.setText(formatValue(data_MAU01_tempSet/10.0,0.1)+"℃");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_MAU01_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_MAU01_reShuiFaKaiDu/10.0,0.1)+"%");

        if (data_MAU01_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_MAU01_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_MAU01_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_MAU01_dianJiaRe1){
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_MAU01_dianJiaRe2){
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_MAU01_dianJiaRe3){
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_MAU01_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_MAU01_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_MAU01_dianJiaReGaoWen){
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }else {
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_red);
        }

        if (data_MAU01_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_MAU01_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_MAU01_mieJunYunXing){
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_mieJunYunXing.setBackgroundResource(R.drawable.led_gray);
        }
    }

    private void updatePau01() {

        tv_data_tempReal.setText(formatValue(data_PAU01_tempReal/10.0,0.1)+"℃");
        tv_data_xinFengWenDu.setText(formatValue(data_PAU01_xinFengWenDU/10.0,0.1)+"℃");
        tv_data_tempSet.setText(formatValue(data_PAU01_tempSet/10.0,0.1)+"℃");
        tv_data_lengShuiFaKaiDu.setText(formatValue(data_PAU01_lengShuiFaKaiDu/10.0,0.1)+"%");
        tv_data_reShuiFaKaiDu.setText(formatValue(data_PAU01_reShuiFaKaiDu/10.0,0.1)+"%");

        if (data_PAU01_dongXiaJi){
            tv_dongXiaJi.setText("夏季");
        }else{
            tv_dongXiaJi.setText("冬季");
        }

        if (data_PAU01_shouZiDong){
            tv_shouZiDong.setText("手动");
        }else {
            tv_shouZiDong.setText("自动");
        }

        if (data_PAU01_fengJiYiQiDong){
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiYiQiDong.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU01_dianJiaRe1){
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe1.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU01_dianJiaRe2){
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe2.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU01_dianJiaRe3){
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_dianYuRe3.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU01_fengJiStatus){
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_green);
        }else {
            bt_fengJiStatus.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU01_zhongXiaoBaoJing){
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_zhongXiaoBaoJing.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU01_dianJiaReGaoWen){
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_gray);
        }else {
            bt_dianYuReGaoWen.setBackgroundResource(R.drawable.led_red);
        }

        if (data_PAU01_fengJiQueFeng){
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_fengJiQueFeng.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU01_diWenPanGuan){
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_red);
        }else {
            bt_diWenPanGuan.setBackgroundResource(R.drawable.led_gray);
        }

        if (data_PAU01_mieJunYunXing){
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
