package com.yy.k.gizwitssdk.DevicesControlActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.gizwits.gizwifisdk.api.GizWifiDevice;
import com.gizwits.gizwifisdk.enumration.GizWifiErrorCode;
import com.yy.k.gizwitssdk.CustomApplication;
import com.yy.k.gizwitssdk.R;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import utils.ConstantUtil;

public class UnitJingTaiActivity extends BaseDevicesControlActivity implements View.OnClickListener {


    private final String TAG = "krguang";
    private static final int CODE_HANDLER_UI = 105; //用来刷新本界面UI的句柄
    private static final int AHU_HANDLER_UI = 106;  //数据更新时，用来向下一个界面传递消息的句柄
    /*
     * ===========================================================
     * 以下key值对应开发者在云端定义的数据点标识名
     * ===========================================================
     */
    // 数据点"风机已启动A1"对应的标识名
    static final String KEY_JK_1_FENGJIYIQIDONG = "JK_1_fengJiYiQiDong";
    // 数据点"值班状态A1"对应的标识名
    static final String KEY_JK_1_ZHIBANSTATUS = "JK_1_zhiBanStatus";
    // 数据点"电加热1A1"对应的标识名
    static final String KEY_JK_1_DIANJIARE1 = "JK_1_dianJiaRe1";
    // 数据点"电加热2A1"对应的标识名
    static final String KEY_JK_1_DIANJIARE2 = "JK_1_dianJiaRe2";
    // 数据点"电加热3A1"对应的标识名
    static final String KEY_JK_1_DIANJIARE3 = "JK_1_dianJiaRe3";
    // 数据点"风机状态A1"对应的标识名
    static final String KEY_JK_1_FENGJISTATUS = "JK_1_fengJiStatus";
    // 数据点"手自动A1"对应的标识名
    static final String KEY_JK_1_SHOUZIDONG = "JK_1_shouZiDong";
    // 数据点"冬夏季A1"对应的标识名
    static final String KEY_JK_1_DONGXIAJI = "JK_1_dongXiaJi";
    // 数据点"中效报警A1"对应的标识名
    static final String KEY_JK_1_ZHONGXIAOBAOJING = "JK_1_zhongXiaoBaoJing";
    // 数据点"电加热高温A1"对应的标识名
    static final String KEY_JK_1_DIANJIAREGAOWEN = "JK_1_dianJiaReGaoWen";
    // 数据点"风机缺风A1"对应的标识名
    static final String KEY_JK_1_FENGJIQUEFENG = "JK_1_fengJiQueFeng";
    // 数据点"排风机已启动A1"对应的标识名
    static final String KEY_JK_1_PAIFENGJIYIQIDONG = "JK_1_paiFengJiYiQiDong";
    // 数据点"盘管低温A1"对应的标识名
    static final String KEY_JK_1_DIWENPANGUAN = "JK_1_diWenPanGuan";
    // 数据点"灭菌运行A1"对应的标识名
    static final String KEY_JK_1_MIEJUNYUNXING = "JK_1_mieJunYunXing";
    // 数据点"风机已启动A2"对应的标识名
    static final String KEY_JK_2_FENGJIYIQIDONG = "JK_2_fengJiYiQiDong";
    // 数据点"值班状态A2"对应的标识名
    static final String KEY_JK_2_ZHIBANSTATUS = "JK_2_zhiBanStatus";
    // 数据点"电加热1A2"对应的标识名
    static final String KEY_JK_2_DIANJIARE1 = "JK_2_dianJiaRe1";
    // 数据点"电加热2A2"对应的标识名
    static final String KEY_JK_2_DIANJIARE2 = "JK_2_dianJiaRe2";
    // 数据点"电加热3A2"对应的标识名
    static final String KEY_JK_2_DIANJIARE3 = "JK_2_dianJiaRe3";
    // 数据点"风机状态A2"对应的标识名
    static final String KEY_JK_2_FENGJISTATUS = "JK_2_fengJiStatus";
    // 数据点"手自动A2"对应的标识名
    static final String KEY_JK_2_SHOUZIDONG = "JK_2_shouZiDong";
    // 数据点"冬夏季A2"对应的标识名
    static final String KEY_JK_2_DONGXIAJI = "JK_2_dongXiaJi";
    // 数据点"中效报警A2"对应的标识名
    static final String KEY_JK_2_ZHONGXIAOBAOJING = "JK_2_zhongXiaoBaoJing";
    // 数据点"电加热高温A2"对应的标识名
    static final String KEY_JK_2_DIANJIAREGAOWEN = "JK_2_dianJiaReGaoWen";
    // 数据点"风机缺风A2"对应的标识名
    static final String KEY_JK_2_FENGJIQUEFENG = "JK_2_fengJiQueFeng";
    // 数据点"排风机已启动A2"对应的标识名
    static final String KEY_JK_2_PAIFENGJIYIQIDONG = "JK_2_paiFengJiYiQiDong";
    // 数据点"盘管低温A2"对应的标识名
    static final String KEY_JK_2_DIWENPANGUAN = "JK_2_diWenPanGuan";
    // 数据点"灭菌运行A2"对应的标识名
    static final String KEY_JK_2_MIEJUNYUNXING = "JK_2_mieJunYunXing";
    // 数据点"风机已启动A3"对应的标识名
    static final String KEY_JK_3_FENGJIYIQIDONG = "JK_3_fengJiYiQiDong";
    // 数据点"值班状态A3"对应的标识名
    static final String KEY_JK_3_ZHIBANSTATUS = "JK_3_zhiBanStatus";
    // 数据点"电加热1A3"对应的标识名
    static final String KEY_JK_3_DIANJIARE1 = "JK_3_dianJiaRe1";
    // 数据点"电加热2A3"对应的标识名
    static final String KEY_JK_3_DIANJIARE2 = "JK_3_dianJiaRe2";
    // 数据点"电加热3A3"对应的标识名
    static final String KEY_JK_3_DIANJIARE3 = "JK_3_dianJiaRe3";
    // 数据点"风机状态A3"对应的标识名
    static final String KEY_JK_3_FENGJISTATUS = "JK_3_fengJiStatus";
    // 数据点"手自动A3"对应的标识名
    static final String KEY_JK_3_SHOUZIDONG = "JK_3_shouZiDong";
    // 数据点"冬夏季A3"对应的标识名
    static final String KEY_JK_3_DONGXIAJI = "JK_3_dongXiaJi";
    // 数据点"中效报警A3"对应的标识名
    static final String KEY_JK_3_ZHONGXIAOBAOJING = "JK_3_zhongXiaoBaoJing";
    // 数据点"电加热高温A3"对应的标识名
    static final String KEY_JK_3_DIANJIAREGAOWEN = "JK_3_dianJiaReGaoWen";
    // 数据点"风机缺风A3"对应的标识名
    static final String KEY_JK_3_FENGJIQUEFENG = "JK_3_fengJiQueFeng";
    // 数据点"排风机已启动A3"对应的标识名
    static final String KEY_JK_3_PAIFENGJIYIQIDONG = "JK_3_paiFengJiYiQiDong";
    // 数据点"盘管低温A3"对应的标识名
    static final String KEY_JK_3_DIWENPANGUAN = "JK_3_diWenPanGuan";
    // 数据点"灭菌运行A3"对应的标识名
    static final String KEY_JK_3_MIEJUNYUNXING = "JK_3_mieJunYunXing";
    // 数据点"风机已启动A4"对应的标识名
    static final String KEY_JK_XF_FENGJIYIQIDONG = "JK_XF_fengJiYiQiDong";
    // 数据点"值班状态A4"对应的标识名
    static final String KEY_JK_XF_ZHIBANSTATUS = "JK_XF_zhiBanStatus";
    // 数据点"电加热1A4"对应的标识名
    static final String KEY_JK_XF_DIANJIARE1 = "JK_XF_dianJiaRe1";
    // 数据点"电加热2A4"对应的标识名
    static final String KEY_JK_XF_DIANJIARE2 = "JK_XF_dianJiaRe2";
    // 数据点"电加热3A4"对应的标识名
    static final String KEY_JK_XF_DIANJIARE3 = "JK_XF_dianJiaRe3";
    // 数据点"风机状态A4"对应的标识名
    static final String KEY_JK_XF_FENGJISTATUS = "JK_XF_fengJiStatus";
    // 数据点"手自动A4"对应的标识名
    static final String KEY_JK_XF_SHOUZIDONG = "JK_XF_shouZiDong";
    // 数据点"冬夏季A4"对应的标识名
    static final String KEY_JK_XF_DONGXIAJI = "JK_XF_dongXiaJi";
    // 数据点"中效报警A4"对应的标识名
    static final String KEY_JK_XF_ZHONGXIAOBAOJING = "JK_XF_zhongXiaoBaoJing";
    // 数据点"电加热高温A4"对应的标识名
    static final String KEY_JK_XF_DIANJIAREGAOWEN = "JK_XF_dianJiaReGaoWen";
    // 数据点"风机缺风A4"对应的标识名
    static final String KEY_JK_XF_FENGJIQUEFENG = "JK_XF_fengJiQueFeng";
    // 数据点"排风机已启动A4"对应的标识名
    static final String KEY_JK_XF_PAIFENGJIYIQIDONG = "JK_XF_paiFengJiYiQiDong";
    // 数据点"盘管低温A4"对应的标识名
    static final String KEY_JK_XF_DIWENPANGUAN = "JK_XF_diWenPanGuan";
    // 数据点"灭菌运行A4"对应的标识名
    static final String KEY_JK_XF_MIEJUNYUNXING = "JK_XF_mieJunYunXing";
    // 数据点"面板1通讯状态A1"对应的标识名
    static final String KEY_JK_1_MIANBANTONGXUNZHUANGTAI1 = "JK_1_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A1"对应的标识名
    static final String KEY_JK_1_MIANBANTONGXUNZHUANGTAI2 = "JK_1_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A1"对应的标识名
    static final String KEY_JK_1_MIANBANTONGXUNZHUANGTAI3 = "JK_1_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A2"对应的标识名
    static final String KEY_JK_2_MIANBANTONGXUNZHUANGTAI1 = "JK_2_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A2"对应的标识名
    static final String KEY_JK_2_MIANBANTONGXUNZHUANGTAI2 = "JK_2_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A2"对应的标识名
    static final String KEY_JK_2_MIANBANTONGXUNZHUANGTAI3 = "JK_2_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A3"对应的标识名
    static final String KEY_JK_3_MIANBANTONGXUNZHUANGTAI1 = "JK_3_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A3"对应的标识名
    static final String KEY_JK_3_MIANBANTONGXUNZHUANGTAI2 = "JK_3_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A3"对应的标识名
    static final String KEY_JK_3_MIANBANTONGXUNZHUANGTAI3 = "JK_3_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A4"对应的标识名
    static final String KEY_JK_XF_MIANBANTONGXUNZHUANGTAI1 = "JK_XF_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A4"对应的标识名
    static final String KEY_JK_XF_MIANBANTONGXUNZHUANGTAI2 = "JK_XF_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A4"对应的标识名
    static final String KEY_JK_XF_MIANBANTONGXUNZHUANGTAI3 = "JK_XF_mianBanTongXunZhuangTai3";
    // 数据点"实时温度A1"对应的标识名
    static final String KEY_JK_1_TEMPREAL = "JK_1_tempReal";
    // 数据点"实时湿度A1"对应的标识名
    static final String KEY_JK_1_HUMIREAL = "JK_1_humiReal";
    // 数据点"温度设定值A1"对应的标识名
    static final String KEY_JK_1_TEMPSET = "JK_1_tempSet";
    // 数据点"湿度设定值A1"对应的标识名
    static final String KEY_JK_1_HUMISET = "JK_1_humiSet";
    // 数据点"冷水阀开度A1"对应的标识名
    static final String KEY_JK_1_LENGSHUIFAKAIDU = "JK_1_lengShuiFaKaiDu";
    // 数据点"热水阀开度A1"对应的标识名
    static final String KEY_JK_1_RESHUIFAKAIDU = "JK_1_reShuiFaKaiDu";
    // 数据点"新风温度A1"对应的标识名
    static final String KEY_JK_1_XINFENGWENDU = "JK_1_xinFengWenDU";
    // 数据点"加湿器开度A1"对应的标识名
    static final String KEY_JK_1_JIASHIQIKAIDU = "JK_1_jiaShiQIKaiDu";
    // 数据点"备用A1"对应的标识名
    static final String KEY_JK_1_BEIYONG = "JK_1_beiYong";
    // 数据点"实时温度A2"对应的标识名
    static final String KEY_JK_2_TEMPREAL = "JK_2_tempReal";
    // 数据点"实时湿度A2"对应的标识名
    static final String KEY_JK_2_HUMIREAL = "JK_2_humiReal";
    // 数据点"温度设定值A2"对应的标识名
    static final String KEY_JK_2_TEMPSET = "JK_2_tempSet";
    // 数据点"湿度设定值A2"对应的标识名
    static final String KEY_JK_2_HUMISET = "JK_2_humiSet";
    // 数据点"冷水阀开度A2"对应的标识名
    static final String KEY_JK_2_LENGSHUIFAKAIDU = "JK_2_lengShuiFaKaiDu";
    // 数据点"热水阀开度A2"对应的标识名
    static final String KEY_JK_2_RESHUIFAKAIDU = "JK_2_reShuiFaKaiDu";
    // 数据点"新风温度A2"对应的标识名
    static final String KEY_JK_2_XINFENGWENDU = "JK_2_xinFengWenDU";
    // 数据点"加湿器开度A2"对应的标识名
    static final String KEY_JK_2_JIASHIQIKAIDU = "JK_2_jiaShiQIKaiDu";
    // 数据点"备用A2"对应的标识名
    static final String KEY_JK_2_BEIYONG = "JK_2_beiYong";
    // 数据点"实时温度A3"对应的标识名
    static final String KEY_JK_3_TEMPREAL = "JK_3_tempReal";
    // 数据点"实时湿度A3"对应的标识名
    static final String KEY_JK_3_HUMIREAL = "JK_3_humiReal";
    // 数据点"温度设定值A3"对应的标识名
    static final String KEY_JK_3_TEMPSET = "JK_3_tempSet";
    // 数据点"湿度设定值A3"对应的标识名
    static final String KEY_JK_3_HUMISET = "JK_3_humiSet";
    // 数据点"冷水阀开度A3"对应的标识名
    static final String KEY_JK_3_LENGSHUIFAKAIDU = "JK_3_lengShuiFaKaiDu";
    // 数据点"热水阀开度A3"对应的标识名
    static final String KEY_JK_3_RESHUIFAKAIDU = "JK_3_reShuiFaKaiDu";
    // 数据点"新风温度A3"对应的标识名
    static final String KEY_JK_3_XINFENGWENDU = "JK_3_xinFengWenDU";
    // 数据点"加湿器开度A3"对应的标识名
    static final String KEY_JK_3_JIASHIQIKAIDU = "JK_3_jiaShiQIKaiDu";
    // 数据点"备用A3"对应的标识名
    static final String KEY_JK_3_BEIYONG = "JK_3_beiYong";
    // 数据点"实时温度A4"对应的标识名
    static final String KEY_JK_XF_TEMPREAL = "JK_XF_tempReal";
    // 数据点"实时湿度A4"对应的标识名
    static final String KEY_JK_XF_HUMIREAL = "JK_XF_humiReal";
    // 数据点"温度设定值A4"对应的标识名
    static final String KEY_JK_XF_TEMPSET = "JK_XF_tempSet";
    // 数据点"湿度设定值A4"对应的标识名
    static final String KEY_JK_XF_HUMISET = "JK_XF_humiSet";
    // 数据点"冷水阀开度A4"对应的标识名
    static final String KEY_JK_XF_LENGSHUIFAKAIDU = "JK_XF_lengShuiFaKaiDu";
    // 数据点"热水阀开度A4"对应的标识名
    static final String KEY_JK_XF_RESHUIFAKAIDU = "JK_XF_reShuiFaKaiDu";
    // 数据点"新风温度A4"对应的标识名
    static final String KEY_JK_XF_XINFENGWENDU = "JK_XF_xinFengWenDU";
    // 数据点"加湿器开度A4"对应的标识名
    static final String KEY_JK_XF_JIASHIQIKAIDU = "JK_XF_jiaShiQIKaiDu";
    // 数据点"备用A4"对应的标识名
    static final String KEY_JK_XF_BEIYONG = "JK_XF_beiYong";

    /*
     * ===========================================================
     * 以下数值对应开发者在云端定义的可写数值型数据点增量值、数据点定义的分辨率、seekbar滚动条补偿值
     * _ADDITION:数据点增量值
     * _RATIO:数据点定义的分辨率
     * _OFFSET:seekbar滚动条补偿值
     * APP与设备定义的协议公式为：y（APP接收的值）=x（设备上报的值）* RATIO（分辨率）+ADDITION（增量值）
     * 由于安卓的原生seekbar无法设置最小值，因此代码中增加了一个补偿量OFFSET
     * 实际上公式中的：x（设备上报的值）=seekbar的值+补偿值
     * ===========================================================
     */

    /*
     * ===========================================================
     * 以下变量对应设备上报类型为布尔、数值、扩展数据点的数据存储
     * ===========================================================
     */
    // 数据点"风机已启动A1"对应的存储数据
    static boolean data_JK_1_fengJiYiQiDong;
    // 数据点"值班状态A1"对应的存储数据
    static boolean data_JK_1_zhiBanStatus;
    // 数据点"电加热1A1"对应的存储数据
    static boolean data_JK_1_dianJiaRe1;
    // 数据点"电加热2A1"对应的存储数据
    static boolean data_JK_1_dianJiaRe2;
    // 数据点"电加热3A1"对应的存储数据
    static boolean data_JK_1_dianJiaRe3;
    // 数据点"风机状态A1"对应的存储数据
    static boolean data_JK_1_fengJiStatus;
    // 数据点"手自动A1"对应的存储数据
    static boolean data_JK_1_shouZiDong;
    // 数据点"冬夏季A1"对应的存储数据
    static boolean data_JK_1_dongXiaJi;
    // 数据点"中效报警A1"对应的存储数据
    static boolean data_JK_1_zhongXiaoBaoJing;
    // 数据点"电加热高温A1"对应的存储数据
    static boolean data_JK_1_dianJiaReGaoWen;
    // 数据点"风机缺风A1"对应的存储数据
    static boolean data_JK_1_fengJiQueFeng;
    // 数据点"排风机已启动A1"对应的存储数据
    static boolean data_JK_1_paiFengJiYiQiDong;
    // 数据点"盘管低温A1"对应的存储数据
    static boolean data_JK_1_diWenPanGuan;
    // 数据点"灭菌运行A1"对应的存储数据
    static boolean data_JK_1_mieJunYunXing;
    // 数据点"风机已启动A2"对应的存储数据
    static boolean data_JK_2_fengJiYiQiDong;
    // 数据点"值班状态A2"对应的存储数据
    static boolean data_JK_2_zhiBanStatus;
    // 数据点"电加热1A2"对应的存储数据
    static boolean data_JK_2_dianJiaRe1;
    // 数据点"电加热2A2"对应的存储数据
    static boolean data_JK_2_dianJiaRe2;
    // 数据点"电加热3A2"对应的存储数据
    static boolean data_JK_2_dianJiaRe3;
    // 数据点"风机状态A2"对应的存储数据
    static boolean data_JK_2_fengJiStatus;
    // 数据点"手自动A2"对应的存储数据
    static boolean data_JK_2_shouZiDong;
    // 数据点"冬夏季A2"对应的存储数据
    static boolean data_JK_2_dongXiaJi;
    // 数据点"中效报警A2"对应的存储数据
    static boolean data_JK_2_zhongXiaoBaoJing;
    // 数据点"电加热高温A2"对应的存储数据
    static boolean data_JK_2_dianJiaReGaoWen;
    // 数据点"风机缺风A2"对应的存储数据
    static boolean data_JK_2_fengJiQueFeng;
    // 数据点"排风机已启动A2"对应的存储数据
    static boolean data_JK_2_paiFengJiYiQiDong;
    // 数据点"盘管低温A2"对应的存储数据
    static boolean data_JK_2_diWenPanGuan;
    // 数据点"灭菌运行A2"对应的存储数据
    static boolean data_JK_2_mieJunYunXing;
    // 数据点"风机已启动A3"对应的存储数据
    static boolean data_JK_3_fengJiYiQiDong;
    // 数据点"值班状态A3"对应的存储数据
    static boolean data_JK_3_zhiBanStatus;
    // 数据点"电加热1A3"对应的存储数据
    static boolean data_JK_3_dianJiaRe1;
    // 数据点"电加热2A3"对应的存储数据
    static boolean data_JK_3_dianJiaRe2;
    // 数据点"电加热3A3"对应的存储数据
    static boolean data_JK_3_dianJiaRe3;
    // 数据点"风机状态A3"对应的存储数据
    static boolean data_JK_3_fengJiStatus;
    // 数据点"手自动A3"对应的存储数据
    static boolean data_JK_3_shouZiDong;
    // 数据点"冬夏季A3"对应的存储数据
    static boolean data_JK_3_dongXiaJi;
    // 数据点"中效报警A3"对应的存储数据
    static boolean data_JK_3_zhongXiaoBaoJing;
    // 数据点"电加热高温A3"对应的存储数据
    static boolean data_JK_3_dianJiaReGaoWen;
    // 数据点"风机缺风A3"对应的存储数据
    static boolean data_JK_3_fengJiQueFeng;
    // 数据点"排风机已启动A3"对应的存储数据
    static boolean data_JK_3_paiFengJiYiQiDong;
    // 数据点"盘管低温A3"对应的存储数据
    static boolean data_JK_3_diWenPanGuan;
    // 数据点"灭菌运行A3"对应的存储数据
    static boolean data_JK_3_mieJunYunXing;
    // 数据点"风机已启动A4"对应的存储数据
    static boolean data_JK_XF_fengJiYiQiDong;
    // 数据点"值班状态A4"对应的存储数据
    static boolean data_JK_XF_zhiBanStatus;
    // 数据点"电加热1A4"对应的存储数据
    static boolean data_JK_XF_dianJiaRe1;
    // 数据点"电加热2A4"对应的存储数据
    static boolean data_JK_XF_dianJiaRe2;
    // 数据点"电加热3A4"对应的存储数据
    static boolean data_JK_XF_dianJiaRe3;
    // 数据点"风机状态A4"对应的存储数据
    static boolean data_JK_XF_fengJiStatus;
    // 数据点"手自动A4"对应的存储数据
    static boolean data_JK_XF_shouZiDong;
    // 数据点"冬夏季A4"对应的存储数据
    static boolean data_JK_XF_dongXiaJi;
    // 数据点"中效报警A4"对应的存储数据
    static boolean data_JK_XF_zhongXiaoBaoJing;
    // 数据点"电加热高温A4"对应的存储数据
    static boolean data_JK_XF_dianJiaReGaoWen;
    // 数据点"风机缺风A4"对应的存储数据
    static boolean data_JK_XF_fengJiQueFeng;
    // 数据点"排风机已启动A4"对应的存储数据
    static boolean data_JK_XF_paiFengJiYiQiDong;
    // 数据点"盘管低温A4"对应的存储数据
    static boolean data_JK_XF_diWenPanGuan;
    // 数据点"灭菌运行A4"对应的存储数据
    static boolean data_JK_XF_mieJunYunXing;
    // 数据点"面板1通讯状态A1"对应的存储数据
    static int data_JK_1_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A1"对应的存储数据
    static int data_JK_1_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A1"对应的存储数据
    static int data_JK_1_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A2"对应的存储数据
    static int data_JK_2_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A2"对应的存储数据
    static int data_JK_2_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A2"对应的存储数据
    static int data_JK_2_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A3"对应的存储数据
    static int data_JK_3_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A3"对应的存储数据
    static int data_JK_3_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A3"对应的存储数据
    static int data_JK_3_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A4"对应的存储数据
    static int data_JK_XF_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A4"对应的存储数据
    static int data_JK_XF_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A4"对应的存储数据
    static int data_JK_XF_mianBanTongXunZhuangTai3;
    // 数据点"实时温度A1"对应的存储数据
    static int data_JK_1_tempReal;
    // 数据点"实时湿度A1"对应的存储数据
    static int data_JK_1_humiReal;
    // 数据点"温度设定值A1"对应的存储数据
    static int data_JK_1_tempSet;
    // 数据点"湿度设定值A1"对应的存储数据
    static int data_JK_1_humiSet;
    // 数据点"冷水阀开度A1"对应的存储数据
    static int data_JK_1_lengShuiFaKaiDu;
    // 数据点"热水阀开度A1"对应的存储数据
    static int data_JK_1_reShuiFaKaiDu;
    // 数据点"新风温度A1"对应的存储数据
    static int data_JK_1_xinFengWenDU;
    // 数据点"加湿器开度A1"对应的存储数据
    static int data_JK_1_jiaShiQIKaiDu;
    // 数据点"备用A1"对应的存储数据
    static int data_JK_1_beiYong;
    // 数据点"实时温度A2"对应的存储数据
    static int data_JK_2_tempReal;
    // 数据点"实时湿度A2"对应的存储数据
    static int data_JK_2_humiReal;
    // 数据点"温度设定值A2"对应的存储数据
    static int data_JK_2_tempSet;
    // 数据点"湿度设定值A2"对应的存储数据
    static int data_JK_2_humiSet;
    // 数据点"冷水阀开度A2"对应的存储数据
    static int data_JK_2_lengShuiFaKaiDu;
    // 数据点"热水阀开度A2"对应的存储数据
    static int data_JK_2_reShuiFaKaiDu;
    // 数据点"新风温度A2"对应的存储数据
    static int data_JK_2_xinFengWenDU;
    // 数据点"加湿器开度A2"对应的存储数据
    static int data_JK_2_jiaShiQIKaiDu;
    // 数据点"备用A2"对应的存储数据
    static int data_JK_2_beiYong;
    // 数据点"实时温度A3"对应的存储数据
    static int data_JK_3_tempReal;
    // 数据点"实时湿度A3"对应的存储数据
    static int data_JK_3_humiReal;
    // 数据点"温度设定值A3"对应的存储数据
    static int data_JK_3_tempSet;
    // 数据点"湿度设定值A3"对应的存储数据
    static int data_JK_3_humiSet;
    // 数据点"冷水阀开度A3"对应的存储数据
    static int data_JK_3_lengShuiFaKaiDu;
    // 数据点"热水阀开度A3"对应的存储数据
    static int data_JK_3_reShuiFaKaiDu;
    // 数据点"新风温度A3"对应的存储数据
    static int data_JK_3_xinFengWenDU;
    // 数据点"加湿器开度A3"对应的存储数据
    static int data_JK_3_jiaShiQIKaiDu;
    // 数据点"备用A3"对应的存储数据
    static int data_JK_3_beiYong;
    // 数据点"实时温度A4"对应的存储数据
    static int data_JK_XF_tempReal;
    // 数据点"实时湿度A4"对应的存储数据
    static int data_JK_XF_humiReal;
    // 数据点"温度设定值A4"对应的存储数据
    static int data_JK_XF_tempSet;
    // 数据点"湿度设定值A4"对应的存储数据
    static int data_JK_XF_humiSet;
    // 数据点"冷水阀开度A4"对应的存储数据
    static int data_JK_XF_lengShuiFaKaiDu;
    // 数据点"热水阀开度A4"对应的存储数据
    static int data_JK_XF_reShuiFaKaiDu;
    // 数据点"新风温度A4"对应的存储数据
    static int data_JK_XF_xinFengWenDU;
    // 数据点"加湿器开度A4"对应的存储数据
    static int data_JK_XF_jiaShiQIKaiDu;
    // 数据点"备用A4"对应的存储数据
    static int data_JK_XF_beiYong;

    private TextView tv_data_JK_1_title;
    private TextView tv_data_JK_1_tempReal;
    private TextView tv_data_JK_1_humiReal;
    private TextView tv_data_JK_1_tempSet;
    private TextView tv_data_JK_1_humiSet;

    private TextView tv_data_JK_2_title;
    private TextView tv_data_JK_2_tempReal;
    private TextView tv_data_JK_2_humiReal;
    private TextView tv_data_JK_2_tempSet;
    private TextView tv_data_JK_2_humiSet;

    private TextView tv_data_JK_3_title;
    private TextView tv_data_JK_3_tempReal;
    private TextView tv_data_JK_3_humiReal;
    private TextView tv_data_JK_3_tempSet;
    private TextView tv_data_JK_3_humiSet;

    private TextView tv_data_JK_XF_title;
    private TextView tv_data_JK_XF_tempReal;
    private TextView tv_data_JK_XF_humiReal;
    private TextView tv_data_JK_XF_tempSet;
    private TextView tv_data_JK_XF_humiSet;

    private View ll_overview_JK_1;
    private View ll_overview_JK_2;
    private View ll_overview_JK_3;
    private View ll_overview_JK_XF;

    private CustomApplication app;
    private Handler handler;


    /** The handler. */
    @SuppressLint("HandlerLeak")
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_jingtai_activity);

        initView();
        initEvent();
        setTopBar();

        app = (CustomApplication) getApplication();
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

    private void initView() {

        tv_data_JK_1_title = (TextView) findViewById(R.id.tv_main_state_title_JK_1);
        tv_data_JK_1_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_JK_1);
        tv_data_JK_1_humiReal = (TextView) findViewById(R.id.tv_main_state_huminow_JK_1);
        tv_data_JK_1_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_JK_1);
        tv_data_JK_1_humiSet = (TextView) findViewById(R.id.tv_main_state_humiset_JK_1);

        tv_data_JK_2_title = (TextView) findViewById(R.id.tv_main_state_title_JK_2);
        tv_data_JK_2_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_JK_2);
        tv_data_JK_2_humiReal = (TextView) findViewById(R.id.tv_main_state_huminow_JK_2);
        tv_data_JK_2_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_JK_2);
        tv_data_JK_2_humiSet = (TextView) findViewById(R.id.tv_main_state_humiset_JK_2);

        tv_data_JK_3_title = (TextView) findViewById(R.id.tv_main_state_title_JK_3);
        tv_data_JK_3_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_JK_3);
        tv_data_JK_3_humiReal = (TextView) findViewById(R.id.tv_main_state_huminow_JK_3);
        tv_data_JK_3_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_JK_3);
        tv_data_JK_3_humiSet = (TextView) findViewById(R.id.tv_main_state_humiset_JK_3);

        tv_data_JK_XF_title = (TextView) findViewById(R.id.tv_main_state_title_JK_XF);
        tv_data_JK_XF_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_JK_XF);
        tv_data_JK_XF_humiReal = (TextView) findViewById(R.id.tv_main_state_huminow_JK_XF);
        tv_data_JK_XF_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_JK_XF);
        tv_data_JK_XF_humiSet = (TextView) findViewById(R.id.tv_main_state_humiset_JK_XF);

        ll_overview_JK_1 = findViewById(R.id.ll_JK_1);
        ll_overview_JK_2 = findViewById(R.id.ll_JK_2);
        ll_overview_JK_3 = findViewById(R.id.ll_JK_3);
        ll_overview_JK_XF = findViewById(R.id.ll_JK_XF);
    }

    private void initEvent() {

        ll_overview_JK_1.setOnClickListener(this);
        ll_overview_JK_2.setOnClickListener(this);
        ll_overview_JK_3.setOnClickListener(this);
        ll_overview_JK_XF.setOnClickListener(this);
    }

    /**
     * Description:根据保存的的数据点的值来更新UI
     */
    protected void updateUI() {

        if (data_JK_1_fengJiQueFeng){
            tv_data_JK_1_title.setBackgroundColor(Color.RED);
        }else if (data_JK_1_fengJiStatus){
            tv_data_JK_1_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_JK_1_title.setBackgroundColor(Color.GRAY);
        }

        if (data_JK_2_fengJiQueFeng){
            tv_data_JK_2_title.setBackgroundColor(Color.RED);
        }else if (data_JK_2_fengJiStatus){
            tv_data_JK_2_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_JK_2_title.setBackgroundColor(Color.GRAY);
        }

        if (data_JK_3_fengJiQueFeng){
            tv_data_JK_3_title.setBackgroundColor(Color.RED);
        }else if (data_JK_3_fengJiStatus){
            tv_data_JK_3_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_JK_3_title.setBackgroundColor(Color.GRAY);
        }

        if (data_JK_XF_fengJiQueFeng){
            tv_data_JK_XF_title.setBackgroundColor(Color.RED);
        }else if (data_JK_XF_fengJiStatus){
            tv_data_JK_XF_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_JK_XF_title.setBackgroundColor(Color.GRAY);
        }

        tv_data_JK_1_tempReal.setText(formatValue(data_JK_1_tempReal/10.0,0.1)+" ℃");
        tv_data_JK_1_humiReal.setText(formatValue(data_JK_1_humiReal/10.0,0.1)+" RH");
        tv_data_JK_1_tempSet.setText(formatValue(data_JK_1_tempSet/10.0,0.1)+" ℃");
        tv_data_JK_1_humiSet.setText(formatValue(data_JK_1_humiSet/10.0,0.1)+" RH");

        tv_data_JK_2_tempReal.setText(formatValue(data_JK_2_tempReal/10.0,0.1)+" ℃");
        tv_data_JK_2_humiReal.setText(formatValue(data_JK_2_humiReal/10.0,0.1)+" RH");
        tv_data_JK_2_tempSet.setText(formatValue(data_JK_2_tempSet/10.0,0.1)+" ℃");
        tv_data_JK_2_humiSet.setText(formatValue(data_JK_2_humiSet/10.0,0.1)+" RH");

        tv_data_JK_3_tempReal.setText(formatValue(data_JK_3_tempReal/10.0,0.1)+" ℃");
        tv_data_JK_3_humiReal.setText(formatValue(data_JK_3_humiReal/10.0,0.1)+" RH");
        tv_data_JK_3_tempSet.setText(formatValue(data_JK_3_tempSet/10.0,0.1)+" ℃");
        tv_data_JK_3_humiSet.setText(formatValue(data_JK_3_humiSet/10.0,0.1)+" RH");

        tv_data_JK_XF_tempReal.setText(formatValue(data_JK_XF_tempReal/10.0,0.1)+" ℃");
        tv_data_JK_XF_humiReal.setText(formatValue(data_JK_XF_humiReal/10.0,0.1)+" RH");
        tv_data_JK_XF_tempSet.setText(formatValue(data_JK_XF_tempSet/10.0,0.1)+" ℃");
        tv_data_JK_XF_humiSet.setText(formatValue(data_JK_XF_humiSet/10.0,0.1)+" RH");
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

                if (dataKey.equals(KEY_JK_1_FENGJIYIQIDONG)) {
                    data_JK_1_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_ZHIBANSTATUS)) {
                    data_JK_1_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_DIANJIARE1)) {
                    data_JK_1_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_DIANJIARE2)) {
                    data_JK_1_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_DIANJIARE3)) {
                    data_JK_1_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_FENGJISTATUS)) {
                    data_JK_1_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_SHOUZIDONG)) {
                    data_JK_1_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_DONGXIAJI)) {
                    data_JK_1_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_ZHONGXIAOBAOJING)) {
                    data_JK_1_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_DIANJIAREGAOWEN)) {
                    data_JK_1_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_FENGJIQUEFENG)) {
                    data_JK_1_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_PAIFENGJIYIQIDONG)) {
                    data_JK_1_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_DIWENPANGUAN)) {
                    data_JK_1_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_MIEJUNYUNXING)) {
                    data_JK_1_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_FENGJIYIQIDONG)) {
                    data_JK_2_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_ZHIBANSTATUS)) {
                    data_JK_2_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_DIANJIARE1)) {
                    data_JK_2_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_DIANJIARE2)) {
                    data_JK_2_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_DIANJIARE3)) {
                    data_JK_2_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_FENGJISTATUS)) {
                    data_JK_2_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_SHOUZIDONG)) {
                    data_JK_2_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_DONGXIAJI)) {
                    data_JK_2_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_ZHONGXIAOBAOJING)) {
                    data_JK_2_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_DIANJIAREGAOWEN)) {
                    data_JK_2_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_FENGJIQUEFENG)) {
                    data_JK_2_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_PAIFENGJIYIQIDONG)) {
                    data_JK_2_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_DIWENPANGUAN)) {
                    data_JK_2_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_MIEJUNYUNXING)) {
                    data_JK_2_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_FENGJIYIQIDONG)) {
                    data_JK_3_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_ZHIBANSTATUS)) {
                    data_JK_3_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_DIANJIARE1)) {
                    data_JK_3_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_DIANJIARE2)) {
                    data_JK_3_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_DIANJIARE3)) {
                    data_JK_3_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_FENGJISTATUS)) {
                    data_JK_3_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_SHOUZIDONG)) {
                    data_JK_3_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_DONGXIAJI)) {
                    data_JK_3_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_ZHONGXIAOBAOJING)) {
                    data_JK_3_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_DIANJIAREGAOWEN)) {
                    data_JK_3_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_FENGJIQUEFENG)) {
                    data_JK_3_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_PAIFENGJIYIQIDONG)) {
                    data_JK_3_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_DIWENPANGUAN)) {
                    data_JK_3_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_MIEJUNYUNXING)) {
                    data_JK_3_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_FENGJIYIQIDONG)) {
                    data_JK_XF_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_ZHIBANSTATUS)) {
                    data_JK_XF_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_DIANJIARE1)) {
                    data_JK_XF_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_DIANJIARE2)) {
                    data_JK_XF_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_DIANJIARE3)) {
                    data_JK_XF_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_FENGJISTATUS)) {
                    data_JK_XF_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_SHOUZIDONG)) {
                    data_JK_XF_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_DONGXIAJI)) {
                    data_JK_XF_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_ZHONGXIAOBAOJING)) {
                    data_JK_XF_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_DIANJIAREGAOWEN)) {
                    data_JK_XF_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_FENGJIQUEFENG)) {
                    data_JK_XF_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_PAIFENGJIYIQIDONG)) {
                    data_JK_XF_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_DIWENPANGUAN)) {
                    data_JK_XF_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_MIEJUNYUNXING)) {
                    data_JK_XF_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_MIANBANTONGXUNZHUANGTAI1)) {

                    data_JK_1_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_MIANBANTONGXUNZHUANGTAI2)) {

                    data_JK_1_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_MIANBANTONGXUNZHUANGTAI3)) {

                    data_JK_1_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_MIANBANTONGXUNZHUANGTAI1)) {

                    data_JK_2_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_MIANBANTONGXUNZHUANGTAI2)) {

                    data_JK_2_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_MIANBANTONGXUNZHUANGTAI3)) {

                    data_JK_2_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_MIANBANTONGXUNZHUANGTAI1)) {

                    data_JK_3_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_MIANBANTONGXUNZHUANGTAI2)) {

                    data_JK_3_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_MIANBANTONGXUNZHUANGTAI3)) {

                    data_JK_3_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_MIANBANTONGXUNZHUANGTAI1)) {

                    data_JK_XF_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_MIANBANTONGXUNZHUANGTAI2)) {

                    data_JK_XF_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_MIANBANTONGXUNZHUANGTAI3)) {

                    data_JK_XF_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_TEMPREAL)) {

                    data_JK_1_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_HUMIREAL)) {

                    data_JK_1_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_TEMPSET)) {

                    data_JK_1_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_HUMISET)) {

                    data_JK_1_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_LENGSHUIFAKAIDU)) {

                    data_JK_1_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_RESHUIFAKAIDU)) {

                    data_JK_1_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_XINFENGWENDU)) {

                    data_JK_1_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_JIASHIQIKAIDU)) {

                    data_JK_1_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_1_BEIYONG)) {

                    data_JK_1_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_TEMPREAL)) {

                    data_JK_2_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_HUMIREAL)) {

                    data_JK_2_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_TEMPSET)) {

                    data_JK_2_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_HUMISET)) {

                    data_JK_2_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_LENGSHUIFAKAIDU)) {

                    data_JK_2_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_RESHUIFAKAIDU)) {

                    data_JK_2_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_XINFENGWENDU)) {

                    data_JK_2_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_JIASHIQIKAIDU)) {

                    data_JK_2_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_2_BEIYONG)) {

                    data_JK_2_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_TEMPREAL)) {

                    data_JK_3_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_HUMIREAL)) {

                    data_JK_3_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_TEMPSET)) {

                    data_JK_3_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_HUMISET)) {

                    data_JK_3_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_LENGSHUIFAKAIDU)) {

                    data_JK_3_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_RESHUIFAKAIDU)) {

                    data_JK_3_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_XINFENGWENDU)) {

                    data_JK_3_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_JIASHIQIKAIDU)) {

                    data_JK_3_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_3_BEIYONG)) {

                    data_JK_3_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_TEMPREAL)) {

                    data_JK_XF_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_HUMIREAL)) {

                    data_JK_XF_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_TEMPSET)) {

                    data_JK_XF_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_HUMISET)) {

                    data_JK_XF_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_LENGSHUIFAKAIDU)) {

                    data_JK_XF_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_RESHUIFAKAIDU)) {

                    data_JK_XF_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_XINFENGWENDU)) {

                    data_JK_XF_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_JIASHIQIKAIDU)) {

                    data_JK_XF_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_JK_XF_BEIYONG)) {

                    data_JK_XF_beiYong = (Integer) map.get(dataKey);
                }
            }
        }
        mHandler.sendEmptyMessage(CODE_HANDLER_UI);
        handler = app.getHandler();
        String GET_CAHNGE = "get_change";
        if (handler != null){
            handler.obtainMessage(AHU_HANDLER_UI,GET_CAHNGE).sendToTarget();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅云端消息
        mDevice.setListener(null);
        mDevice.setSubscribe(ConstantUtil.JINGTAI_PRODUCT_SECRET, false);
    }

    private void startActivityWithStringAHU(String data){

        Bundle bundle = new Bundle();
        bundle.putParcelable("mDevice",mDevice);

        Intent intent = new Intent(UnitJingTaiActivity.this, AhuDeviceDataActivity.class);
        intent.putExtra("extra_data",data);
        Log.d(TAG, "startActivityWithStringAHU: 要传出去的device = "+mDevice);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_JK_1:
                startActivityWithStringAHU("JK_1");
                break;

            case R.id.ll_JK_2:
                startActivityWithStringAHU("JK_2");
                break;

            case R.id.ll_JK_3:
                startActivityWithStringAHU("JK_3");
                break;

            case R.id.ll_JK_XF:
                startActivityWithStringAHU("JK_XF");
                break;

            default:
                break;
        }
    }
}
