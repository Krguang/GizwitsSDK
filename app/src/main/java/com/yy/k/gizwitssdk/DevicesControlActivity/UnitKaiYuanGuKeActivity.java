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

public class UnitKaiYuanGuKeActivity extends BaseDevicesControlActivity implements View.OnClickListener {

    /*
     * ===========================================================
     * 以下key值对应开发者在云端定义的数据点标识名
     * ===========================================================
     */
    // 数据点"风机已启动A1"对应的标识名
    protected static final String KEY_AHU01_FENGJIYIQIDONG = "AHU01_fengJiYiQiDong";
    // 数据点"值班状态A1"对应的标识名
    protected static final String KEY_AHU01_ZHIBANSTATUS = "AHU01_zhiBanStatus";
    // 数据点"电加热1A1"对应的标识名
    protected static final String KEY_AHU01_DIANJIARE1 = "AHU01_dianJiaRe1";
    // 数据点"电加热2A1"对应的标识名
    protected static final String KEY_AHU01_DIANJIARE2 = "AHU01_dianJiaRe2";
    // 数据点"电加热3A1"对应的标识名
    protected static final String KEY_AHU01_DIANJIARE3 = "AHU01_dianJiaRe3";
    // 数据点"风机状态A1"对应的标识名
    protected static final String KEY_AHU01_FENGJISTATUS = "AHU01_fengJiStatus";
    // 数据点"手自动A1"对应的标识名
    protected static final String KEY_AHU01_SHOUZIDONG = "AHU01_shouZiDong";
    // 数据点"冬夏季A1"对应的标识名
    protected static final String KEY_AHU01_DONGXIAJI = "AHU01_dongXiaJi";
    // 数据点"中效报警A1"对应的标识名
    protected static final String KEY_AHU01_ZHONGXIAOBAOJING = "AHU01_zhongXiaoBaoJing";
    // 数据点"电加热高温A1"对应的标识名
    protected static final String KEY_AHU01_DIANJIAREGAOWEN = "AHU01_dianJiaReGaoWen";
    // 数据点"风机缺风A1"对应的标识名
    protected static final String KEY_AHU01_FENGJIQUEFENG = "AHU01_fengJiQueFeng";
    // 数据点"排风机已启动A1"对应的标识名
    protected static final String KEY_AHU01_PAIFENGJIYIQIDONG = "AHU01_paiFengJiYiQiDong";
    // 数据点"盘管低温A1"对应的标识名
    protected static final String KEY_AHU01_DIWENPANGUAN = "AHU01_diWenPanGuan";
    // 数据点"灭菌运行A1"对应的标识名
    protected static final String KEY_AHU01_MIEJUNYUNXING = "AHU01_mieJunYunXing";
    // 数据点"风机已启动A2"对应的标识名
    protected static final String KEY_AHU02_FENGJIYIQIDONG = "AHU02_fengJiYiQiDong";
    // 数据点"值班状态A2"对应的标识名
    protected static final String KEY_AHU02_ZHIBANSTATUS = "AHU02_zhiBanStatus";
    // 数据点"电加热1A2"对应的标识名
    protected static final String KEY_AHU02_DIANJIARE1 = "AHU02_dianJiaRe1";
    // 数据点"电加热2A2"对应的标识名
    protected static final String KEY_AHU02_DIANJIARE2 = "AHU02_dianJiaRe2";
    // 数据点"电加热3A2"对应的标识名
    protected static final String KEY_AHU02_DIANJIARE3 = "AHU02_dianJiaRe3";
    // 数据点"风机状态A2"对应的标识名
    protected static final String KEY_AHU02_FENGJISTATUS = "AHU02_fengJiStatus";
    // 数据点"手自动A2"对应的标识名
    protected static final String KEY_AHU02_SHOUZIDONG = "AHU02_shouZiDong";
    // 数据点"冬夏季A2"对应的标识名
    protected static final String KEY_AHU02_DONGXIAJI = "AHU02_dongXiaJi";
    // 数据点"中效报警A2"对应的标识名
    protected static final String KEY_AHU02_ZHONGXIAOBAOJING = "AHU02_zhongXiaoBaoJing";
    // 数据点"电加热高温A2"对应的标识名
    protected static final String KEY_AHU02_DIANJIAREGAOWEN = "AHU02_dianJiaReGaoWen";
    // 数据点"风机缺风A2"对应的标识名
    protected static final String KEY_AHU02_FENGJIQUEFENG = "AHU02_fengJiQueFeng";
    // 数据点"排风机已启动A2"对应的标识名
    protected static final String KEY_AHU02_PAIFENGJIYIQIDONG = "AHU02_paiFengJiYiQiDong";
    // 数据点"盘管低温A2"对应的标识名
    protected static final String KEY_AHU02_DIWENPANGUAN = "AHU02_diWenPanGuan";
    // 数据点"灭菌运行A2"对应的标识名
    protected static final String KEY_AHU02_MIEJUNYUNXING = "AHU02_mieJunYunXing";
    // 数据点"风机已启动A3"对应的标识名
    protected static final String KEY_AHU03_FENGJIYIQIDONG = "AHU03_fengJiYiQiDong";
    // 数据点"值班状态A3"对应的标识名
    protected static final String KEY_AHU03_ZHIBANSTATUS = "AHU03_zhiBanStatus";
    // 数据点"电加热1A3"对应的标识名
    protected static final String KEY_AHU03_DIANJIARE1 = "AHU03_dianJiaRe1";
    // 数据点"电加热2A3"对应的标识名
    protected static final String KEY_AHU03_DIANJIARE2 = "AHU03_dianJiaRe2";
    // 数据点"电加热3A3"对应的标识名
    protected static final String KEY_AHU03_DIANJIARE3 = "AHU03_dianJiaRe3";
    // 数据点"风机状态A3"对应的标识名
    protected static final String KEY_AHU03_FENGJISTATUS = "AHU03_fengJiStatus";
    // 数据点"手自动A3"对应的标识名
    protected static final String KEY_AHU03_SHOUZIDONG = "AHU03_shouZiDong";
    // 数据点"冬夏季A3"对应的标识名
    protected static final String KEY_AHU03_DONGXIAJI = "AHU03_dongXiaJi";
    // 数据点"中效报警A3"对应的标识名
    protected static final String KEY_AHU03_ZHONGXIAOBAOJING = "AHU03_zhongXiaoBaoJing";
    // 数据点"电加热高温A3"对应的标识名
    protected static final String KEY_AHU03_DIANJIAREGAOWEN = "AHU03_dianJiaReGaoWen";
    // 数据点"风机缺风A3"对应的标识名
    protected static final String KEY_AHU03_FENGJIQUEFENG = "AHU03_fengJiQueFeng";
    // 数据点"排风机已启动A3"对应的标识名
    protected static final String KEY_AHU03_PAIFENGJIYIQIDONG = "AHU03_paiFengJiYiQiDong";
    // 数据点"盘管低温A3"对应的标识名
    protected static final String KEY_AHU03_DIWENPANGUAN = "AHU03_diWenPanGuan";
    // 数据点"灭菌运行A3"对应的标识名
    protected static final String KEY_AHU03_MIEJUNYUNXING = "AHU03_mieJunYunXing";
    // 数据点"风机已启动A4"对应的标识名
    protected static final String KEY_AHU04_FENGJIYIQIDONG = "AHU04_fengJiYiQiDong";
    // 数据点"值班状态A4"对应的标识名
    protected static final String KEY_AHU04_ZHIBANSTATUS = "AHU04_zhiBanStatus";
    // 数据点"电加热1A4"对应的标识名
    protected static final String KEY_AHU04_DIANJIARE1 = "AHU04_dianJiaRe1";
    // 数据点"电加热2A4"对应的标识名
    protected static final String KEY_AHU04_DIANJIARE2 = "AHU04_dianJiaRe2";
    // 数据点"电加热3A4"对应的标识名
    protected static final String KEY_AHU04_DIANJIARE3 = "AHU04_dianJiaRe3";
    // 数据点"风机状态A4"对应的标识名
    protected static final String KEY_AHU04_FENGJISTATUS = "AHU04_fengJiStatus";
    // 数据点"手自动A4"对应的标识名
    protected static final String KEY_AHU04_SHOUZIDONG = "AHU04_shouZiDong";
    // 数据点"冬夏季A4"对应的标识名
    protected static final String KEY_AHU04_DONGXIAJI = "AHU04_dongXiaJi";
    // 数据点"中效报警A4"对应的标识名
    protected static final String KEY_AHU04_ZHONGXIAOBAOJING = "AHU04_zhongXiaoBaoJing";
    // 数据点"电加热高温A4"对应的标识名
    protected static final String KEY_AHU04_DIANJIAREGAOWEN = "AHU04_dianJiaReGaoWen";
    // 数据点"风机缺风A4"对应的标识名
    protected static final String KEY_AHU04_FENGJIQUEFENG = "AHU04_fengJiQueFeng";
    // 数据点"排风机已启动A4"对应的标识名
    protected static final String KEY_AHU04_PAIFENGJIYIQIDONG = "AHU04_paiFengJiYiQiDong";
    // 数据点"盘管低温A4"对应的标识名
    protected static final String KEY_AHU04_DIWENPANGUAN = "AHU04_diWenPanGuan";
    // 数据点"灭菌运行A4"对应的标识名
    protected static final String KEY_AHU04_MIEJUNYUNXING = "AHU04_mieJunYunXing";
    // 数据点"风机已启动A5"对应的标识名
    protected static final String KEY_AHU05_FENGJIYIQIDONG = "AHU05_fengJiYiQiDong";
    // 数据点"值班状态A5"对应的标识名
    protected static final String KEY_AHU05_ZHIBANSTATUS = "AHU05_zhiBanStatus";
    // 数据点"电加热1A5"对应的标识名
    protected static final String KEY_AHU05_DIANJIARE1 = "AHU05_dianJiaRe1";
    // 数据点"电加热2A5"对应的标识名
    protected static final String KEY_AHU05_DIANJIARE2 = "AHU05_dianJiaRe2";
    // 数据点"电加热3A5"对应的标识名
    protected static final String KEY_AHU05_DIANJIARE3 = "AHU05_dianJiaRe3";
    // 数据点"风机状态A5"对应的标识名
    protected static final String KEY_AHU05_FENGJISTATUS = "AHU05_fengJiStatus";
    // 数据点"手自动A5"对应的标识名
    protected static final String KEY_AHU05_SHOUZIDONG = "AHU05_shouZiDong";
    // 数据点"冬夏季A5"对应的标识名
    protected static final String KEY_AHU05_DONGXIAJI = "AHU05_dongXiaJi";
    // 数据点"中效报警A5"对应的标识名
    protected static final String KEY_AHU05_ZHONGXIAOBAOJING = "AHU05_zhongXiaoBaoJing";
    // 数据点"电加热高温A5"对应的标识名
    protected static final String KEY_AHU05_DIANJIAREGAOWEN = "AHU05_dianJiaReGaoWen";
    // 数据点"风机缺风A5"对应的标识名
    protected static final String KEY_AHU05_FENGJIQUEFENG = "AHU05_fengJiQueFeng";
    // 数据点"排风机已启动A5"对应的标识名
    protected static final String KEY_AHU05_PAIFENGJIYIQIDONG = "AHU05_paiFengJiYiQiDong";
    // 数据点"盘管低温A5"对应的标识名
    protected static final String KEY_AHU05_DIWENPANGUAN = "AHU05_diWenPanGuan";
    // 数据点"灭菌运行A5"对应的标识名
    protected static final String KEY_AHU05_MIEJUNYUNXING = "AHU05_mieJunYunXing";
    // 数据点"风机已启动A6"对应的标识名
    protected static final String KEY_PAU01_FENGJIYIQIDONG = "PAU01_fengJiYiQiDong";
    // 数据点"值班状态A6"对应的标识名
    protected static final String KEY_PAU01_ZHIBANSTATUS = "PAU01_zhiBanStatus";
    // 数据点"电预热1A6"对应的标识名
    protected static final String KEY_PAU01_DIANJIARE1 = "PAU01_dianJiaRe1";
    // 数据点"电预热2A6"对应的标识名
    protected static final String KEY_PAU01_DIANJIARE2 = "PAU01_dianJiaRe2";
    // 数据点"电预热3A6"对应的标识名
    protected static final String KEY_PAU01_DIANJIARE3 = "PAU01_dianJiaRe3";
    // 数据点"风机状态A6"对应的标识名
    protected static final String KEY_PAU01_FENGJISTATUS = "PAU01_fengJiStatus";
    // 数据点"手自动A6"对应的标识名
    protected static final String KEY_PAU01_SHOUZIDONG = "PAU01_shouZiDong";
    // 数据点"冬夏季A6"对应的标识名
    protected static final String KEY_PAU01_DONGXIAJI = "PAU01_dongXiaJi";
    // 数据点"中效报警A6"对应的标识名
    protected static final String KEY_PAU01_ZHONGXIAOBAOJING = "PAU01_zhongXiaoBaoJing";
    // 数据点"电预热高温A6"对应的标识名
    protected static final String KEY_PAU01_DIANJIAREGAOWEN = "PAU01_dianJiaReGaoWen";
    // 数据点"风机缺风A6"对应的标识名
    protected static final String KEY_PAU01_FENGJIQUEFENG = "PAU01_fengJiQueFeng";
    // 数据点"排风机已启动A6"对应的标识名
    protected static final String KEY_PAU01_PAIFENGJIYIQIDONG = "PAU01_paiFengJiYiQiDong";
    // 数据点"盘管低温A6"对应的标识名
    protected static final String KEY_PAU01_DIWENPANGUAN = "PAU01_diWenPanGuan";
    // 数据点"灭菌运行A6"对应的标识名
    protected static final String KEY_PAU01_MIEJUNYUNXING = "PAU01_mieJunYunXing";
    // 数据点"风机已启动A7"对应的标识名
    protected static final String KEY_MAU01_FENGJIYIQIDONG = "MAU01_fengJiYiQiDong";
    // 数据点"值班状态A7"对应的标识名
    protected static final String KEY_MAU01_ZHIBANSTATUS = "MAU01_zhiBanStatus";
    // 数据点"电预热1A7"对应的标识名
    protected static final String KEY_MAU01_DIANJIARE1 = "MAU01_dianJiaRe1";
    // 数据点"电预热2A7"对应的标识名
    protected static final String KEY_MAU01_DIANJIARE2 = "MAU01_dianJiaRe2";
    // 数据点"电预热3A7"对应的标识名
    protected static final String KEY_MAU01_DIANJIARE3 = "MAU01_dianJiaRe3";
    // 数据点"风机状态A7"对应的标识名
    protected static final String KEY_MAU01_FENGJISTATUS = "MAU01_fengJiStatus";
    // 数据点"手自动A7"对应的标识名
    protected static final String KEY_MAU01_SHOUZIDONG = "MAU01_shouZiDong";
    // 数据点"冬夏季A7"对应的标识名
    protected static final String KEY_MAU01_DONGXIAJI = "MAU01_dongXiaJi";
    // 数据点"中效报警A7"对应的标识名
    protected static final String KEY_MAU01_ZHONGXIAOBAOJING = "MAU01_zhongXiaoBaoJing";
    // 数据点"电预热高温A7"对应的标识名
    protected static final String KEY_MAU01_DIANJIAREGAOWEN = "MAU01_dianJiaReGaoWen";
    // 数据点"风机缺风A7"对应的标识名
    protected static final String KEY_MAU01_FENGJIQUEFENG = "MAU01_fengJiQueFeng";
    // 数据点"排风机已启动A7"对应的标识名
    protected static final String KEY_MAU01_PAIFENGJIYIQIDONG = "MAU01_paiFengJiYiQiDong";
    // 数据点"盘管低温A7"对应的标识名
    protected static final String KEY_MAU01_DIWENPANGUAN = "MAU01_diWenPanGuan";
    // 数据点"灭菌运行A7"对应的标识名
    protected static final String KEY_MAU01_MIEJUNYUNXING = "MAU01_mieJunYunXing";
    // 数据点"面板1通讯状态A1"对应的标识名
    protected static final String KEY_AHU01_MIANBANTONGXUNZHUANGTAI1 = "AHU01_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A1"对应的标识名
    protected static final String KEY_AHU01_MIANBANTONGXUNZHUANGTAI2 = "AHU01_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A1"对应的标识名
    protected static final String KEY_AHU01_MIANBANTONGXUNZHUANGTAI3 = "AHU01_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A2"对应的标识名
    protected static final String KEY_AHU02_MIANBANTONGXUNZHUANGTAI1 = "AHU02_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A2"对应的标识名
    protected static final String KEY_AHU02_MIANBANTONGXUNZHUANGTAI2 = "AHU02_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A2"对应的标识名
    protected static final String KEY_AHU02_MIANBANTONGXUNZHUANGTAI3 = "AHU02_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A3"对应的标识名
    protected static final String KEY_AHU03_MIANBANTONGXUNZHUANGTAI1 = "AHU03_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A3"对应的标识名
    protected static final String KEY_AHU03_MIANBANTONGXUNZHUANGTAI2 = "AHU03_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A3"对应的标识名
    protected static final String KEY_AHU03_MIANBANTONGXUNZHUANGTAI3 = "AHU03_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A4"对应的标识名
    protected static final String KEY_AHU04_MIANBANTONGXUNZHUANGTAI1 = "AHU04_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A4"对应的标识名
    protected static final String KEY_AHU04_MIANBANTONGXUNZHUANGTAI2 = "AHU04_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A4"对应的标识名
    protected static final String KEY_AHU04_MIANBANTONGXUNZHUANGTAI3 = "AHU04_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A5"对应的标识名
    protected static final String KEY_AHU05_MIANBANTONGXUNZHUANGTAI1 = "AHU05_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A5"对应的标识名
    protected static final String KEY_AHU05_MIANBANTONGXUNZHUANGTAI2 = "AHU05_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A5"对应的标识名
    protected static final String KEY_AHU05_MIANBANTONGXUNZHUANGTAI3 = "AHU05_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A6"对应的标识名
    protected static final String KEY_PAU01_MIANBANTONGXUNZHUANGTAI1 = "PAU01_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A6"对应的标识名
    protected static final String KEY_PAU01_MIANBANTONGXUNZHUANGTAI2 = "PAU01_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A6"对应的标识名
    protected static final String KEY_PAU01_MIANBANTONGXUNZHUANGTAI3 = "PAU01_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A7"对应的标识名
    protected static final String KEY_MAU01_MIANBANTONGXUNZHUANGTAI1 = "MAU01_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A7"对应的标识名
    protected static final String KEY_MAU01_MIANBANTONGXUNZHUANGTAI2 = "MAU01_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A7"对应的标识名
    protected static final String KEY_MAU01_MIANBANTONGXUNZHUANGTAI3 = "MAU01_mianBanTongXunZhuangTai3";
    // 数据点"实时温度A1"对应的标识名
    protected static final String KEY_AHU01_TEMPREAL = "AHU01_tempReal";
    // 数据点"实时湿度A1"对应的标识名
    protected static final String KEY_AHU01_HUMIREAL = "AHU01_humiReal";
    // 数据点"温度设定值A1"对应的标识名
    protected static final String KEY_AHU01_TEMPSET = "AHU01_tempSet";
    // 数据点"湿度设定值A1"对应的标识名
    protected static final String KEY_AHU01_HUMISET = "AHU01_humiSet";
    // 数据点"冷水阀开度A1"对应的标识名
    protected static final String KEY_AHU01_LENGSHUIFAKAIDU = "AHU01_lengShuiFaKaiDu";
    // 数据点"热水阀开度A1"对应的标识名
    protected static final String KEY_AHU01_RESHUIFAKAIDU = "AHU01_reShuiFaKaiDu";
    // 数据点"新风温度A1"对应的标识名
    protected static final String KEY_AHU01_XINFENGWENDU = "AHU01_xinFengWenDU";
    // 数据点"加湿器开度A1"对应的标识名
    protected static final String KEY_AHU01_JIASHIQIKAIDU = "AHU01_jiaShiQIKaiDu";
    // 数据点"实时温度A2"对应的标识名
    protected static final String KEY_AHU02_TEMPREAL = "AHU02_tempReal";
    // 数据点"实时湿度A2"对应的标识名
    protected static final String KEY_AHU02_HUMIREAL = "AHU02_humiReal";
    // 数据点"温度设定值A2"对应的标识名
    protected static final String KEY_AHU02_TEMPSET = "AHU02_tempSet";
    // 数据点"湿度设定值A2"对应的标识名
    protected static final String KEY_AHU02_HUMISET = "AHU02_humiSet";
    // 数据点"冷水阀开度A2"对应的标识名
    protected static final String KEY_AHU02_LENGSHUIFAKAIDU = "AHU02_lengShuiFaKaiDu";
    // 数据点"热水阀开度A2"对应的标识名
    protected static final String KEY_AHU02_RESHUIFAKAIDU = "AHU02_reShuiFaKaiDu";
    // 数据点"新风温度A2"对应的标识名
    protected static final String KEY_AHU02_XINFENGWENDU = "AHU02_xinFengWenDU";
    // 数据点"加湿器开度A2"对应的标识名
    protected static final String KEY_AHU02_JIASHIQIKAIDU = "AHU02_jiaShiQIKaiDu";
    // 数据点"实时温度A3"对应的标识名
    protected static final String KEY_AHU03_TEMPREAL = "AHU03_tempReal";
    // 数据点"实时湿度A3"对应的标识名
    protected static final String KEY_AHU03_HUMIREAL = "AHU03_humiReal";
    // 数据点"温度设定值A3"对应的标识名
    protected static final String KEY_AHU03_TEMPSET = "AHU03_tempSet";
    // 数据点"湿度设定值A3"对应的标识名
    protected static final String KEY_AHU03_HUMISET = "AHU03_humiSet";
    // 数据点"冷水阀开度A3"对应的标识名
    protected static final String KEY_AHU03_LENGSHUIFAKAIDU = "AHU03_lengShuiFaKaiDu";
    // 数据点"热水阀开度A3"对应的标识名
    protected static final String KEY_AHU03_RESHUIFAKAIDU = "AHU03_reShuiFaKaiDu";
    // 数据点"新风温度A3"对应的标识名
    protected static final String KEY_AHU03_XINFENGWENDU = "AHU03_xinFengWenDU";
    // 数据点"加湿器开度A3"对应的标识名
    protected static final String KEY_AHU03_JIASHIQIKAIDU = "AHU03_jiaShiQIKaiDu";
    // 数据点"实时温度A4"对应的标识名
    protected static final String KEY_AHU04_TEMPREAL = "AHU04_tempReal";
    // 数据点"实时湿度A4"对应的标识名
    protected static final String KEY_AHU04_HUMIREAL = "AHU04_humiReal";
    // 数据点"温度设定值A4"对应的标识名
    protected static final String KEY_AHU04_TEMPSET = "AHU04_tempSet";
    // 数据点"湿度设定值A4"对应的标识名
    protected static final String KEY_AHU04_HUMISET = "AHU04_humiSet";
    // 数据点"冷水阀开度A4"对应的标识名
    protected static final String KEY_AHU04_LENGSHUIFAKAIDU = "AHU04_lengShuiFaKaiDu";
    // 数据点"热水阀开度A4"对应的标识名
    protected static final String KEY_AHU04_RESHUIFAKAIDU = "AHU04_reShuiFaKaiDu";
    // 数据点"新风温度A4"对应的标识名
    protected static final String KEY_AHU04_XINFENGWENDU = "AHU04_xinFengWenDU";
    // 数据点"加湿器开度A4"对应的标识名
    protected static final String KEY_AHU04_JIASHIQIKAIDU = "AHU04_jiaShiQIKaiDu";
    // 数据点"实时温度A5"对应的标识名
    protected static final String KEY_AHU05_TEMPREAL = "AHU05_tempReal";
    // 数据点"实时湿度A5"对应的标识名
    protected static final String KEY_AHU05_HUMIREAL = "AHU05_humiReal";
    // 数据点"温度设定值A5"对应的标识名
    protected static final String KEY_AHU05_TEMPSET = "AHU05_tempSet";
    // 数据点"湿度设定值A5"对应的标识名
    protected static final String KEY_AHU05_HUMISET = "AHU05_humiSet";
    // 数据点"冷水阀开度A5"对应的标识名
    protected static final String KEY_AHU05_LENGSHUIFAKAIDU = "AHU05_lengShuiFaKaiDu";
    // 数据点"热水阀开度A5"对应的标识名
    protected static final String KEY_AHU05_RESHUIFAKAIDU = "AHU05_reShuiFaKaiDu";
    // 数据点"新风温度A5"对应的标识名
    protected static final String KEY_AHU05_XINFENGWENDU = "AHU05_xinFengWenDU";
    // 数据点"加湿器开度A5"对应的标识名
    protected static final String KEY_AHU05_JIASHIQIKAIDU = "AHU05_jiaShiQIKaiDu";
    // 数据点"实时温度A6"对应的标识名
    protected static final String KEY_PAU01_TEMPREAL = "PAU01_tempReal";
    // 数据点"实时湿度A6"对应的标识名
    protected static final String KEY_PAU01_HUMIREAL = "PAU01_humiReal";
    // 数据点"温度设定值A6"对应的标识名
    protected static final String KEY_PAU01_TEMPSET = "PAU01_tempSet";
    // 数据点"湿度设定值A6"对应的标识名
    protected static final String KEY_PAU01_HUMISET = "PAU01_humiSet";
    // 数据点"冷水阀开度A6"对应的标识名
    protected static final String KEY_PAU01_LENGSHUIFAKAIDU = "PAU01_lengShuiFaKaiDu";
    // 数据点"热水阀开度A6"对应的标识名
    protected static final String KEY_PAU01_RESHUIFAKAIDU = "PAU01_reShuiFaKaiDu";
    // 数据点"新风温度A6"对应的标识名
    protected static final String KEY_PAU01_XINFENGWENDU = "PAU01_xinFengWenDU";
    // 数据点"加湿器开度A6"对应的标识名
    protected static final String KEY_PAU01_JIASHIQIKAIDU = "PAU01_jiaShiQIKaiDu";
    // 数据点"实时温度A7"对应的标识名
    protected static final String KEY_MAU01_TEMPREAL = "MAU01_tempReal";
    // 数据点"实时湿度A7"对应的标识名
    protected static final String KEY_MAU01_HUMIREAL = "MAU01_humiReal";
    // 数据点"温度设定值A7"对应的标识名
    protected static final String KEY_MAU01_TEMPSET = "MAU01_tempSet";
    // 数据点"湿度设定值A7"对应的标识名
    protected static final String KEY_MAU01_HUMISET = "MAU01_humiSet";
    // 数据点"冷水阀开度A7"对应的标识名
    protected static final String KEY_MAU01_LENGSHUIFAKAIDU = "MAU01_lengShuiFaKaiDu";
    // 数据点"热水阀开度A7"对应的标识名
    protected static final String KEY_MAU01_RESHUIFAKAIDU = "MAU01_reShuiFaKaiDu";
    // 数据点"新风温度A7"对应的标识名
    protected static final String KEY_MAU01_XINFENGWENDU = "MAU01_xinFengWenDU";
    // 数据点"加湿器开度A7"对应的标识名
    protected static final String KEY_MAU01_JIASHIQIKAIDU = "MAU01_jiaShiQIKaiDu";

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
    protected static boolean data_AHU01_fengJiYiQiDong;
    // 数据点"值班状态A1"对应的存储数据
    protected static boolean data_AHU01_zhiBanStatus;
    // 数据点"电加热1A1"对应的存储数据
    protected static boolean data_AHU01_dianJiaRe1;
    // 数据点"电加热2A1"对应的存储数据
    protected static boolean data_AHU01_dianJiaRe2;
    // 数据点"电加热3A1"对应的存储数据
    protected static boolean data_AHU01_dianJiaRe3;
    // 数据点"风机状态A1"对应的存储数据
    protected static boolean data_AHU01_fengJiStatus;
    // 数据点"手自动A1"对应的存储数据
    protected static boolean data_AHU01_shouZiDong;
    // 数据点"冬夏季A1"对应的存储数据
    protected static boolean data_AHU01_dongXiaJi;
    // 数据点"中效报警A1"对应的存储数据
    protected static boolean data_AHU01_zhongXiaoBaoJing;
    // 数据点"电加热高温A1"对应的存储数据
    protected static boolean data_AHU01_dianJiaReGaoWen;
    // 数据点"风机缺风A1"对应的存储数据
    protected static boolean data_AHU01_fengJiQueFeng;
    // 数据点"排风机已启动A1"对应的存储数据
    protected static boolean data_AHU01_paiFengJiYiQiDong;
    // 数据点"盘管低温A1"对应的存储数据
    protected static boolean data_AHU01_diWenPanGuan;
    // 数据点"灭菌运行A1"对应的存储数据
    protected static boolean data_AHU01_mieJunYunXing;
    // 数据点"风机已启动A2"对应的存储数据
    protected static boolean data_AHU02_fengJiYiQiDong;
    // 数据点"值班状态A2"对应的存储数据
    protected static boolean data_AHU02_zhiBanStatus;
    // 数据点"电加热1A2"对应的存储数据
    protected static boolean data_AHU02_dianJiaRe1;
    // 数据点"电加热2A2"对应的存储数据
    protected static boolean data_AHU02_dianJiaRe2;
    // 数据点"电加热3A2"对应的存储数据
    protected static boolean data_AHU02_dianJiaRe3;
    // 数据点"风机状态A2"对应的存储数据
    protected static boolean data_AHU02_fengJiStatus;
    // 数据点"手自动A2"对应的存储数据
    protected static boolean data_AHU02_shouZiDong;
    // 数据点"冬夏季A2"对应的存储数据
    protected static boolean data_AHU02_dongXiaJi;
    // 数据点"中效报警A2"对应的存储数据
    protected static boolean data_AHU02_zhongXiaoBaoJing;
    // 数据点"电加热高温A2"对应的存储数据
    protected static boolean data_AHU02_dianJiaReGaoWen;
    // 数据点"风机缺风A2"对应的存储数据
    protected static boolean data_AHU02_fengJiQueFeng;
    // 数据点"排风机已启动A2"对应的存储数据
    protected static boolean data_AHU02_paiFengJiYiQiDong;
    // 数据点"盘管低温A2"对应的存储数据
    protected static boolean data_AHU02_diWenPanGuan;
    // 数据点"灭菌运行A2"对应的存储数据
    protected static boolean data_AHU02_mieJunYunXing;
    // 数据点"风机已启动A3"对应的存储数据
    protected static boolean data_AHU03_fengJiYiQiDong;
    // 数据点"值班状态A3"对应的存储数据
    protected static boolean data_AHU03_zhiBanStatus;
    // 数据点"电加热1A3"对应的存储数据
    protected static boolean data_AHU03_dianJiaRe1;
    // 数据点"电加热2A3"对应的存储数据
    protected static boolean data_AHU03_dianJiaRe2;
    // 数据点"电加热3A3"对应的存储数据
    protected static boolean data_AHU03_dianJiaRe3;
    // 数据点"风机状态A3"对应的存储数据
    protected static boolean data_AHU03_fengJiStatus;
    // 数据点"手自动A3"对应的存储数据
    protected static boolean data_AHU03_shouZiDong;
    // 数据点"冬夏季A3"对应的存储数据
    protected static boolean data_AHU03_dongXiaJi;
    // 数据点"中效报警A3"对应的存储数据
    protected static boolean data_AHU03_zhongXiaoBaoJing;
    // 数据点"电加热高温A3"对应的存储数据
    protected static boolean data_AHU03_dianJiaReGaoWen;
    // 数据点"风机缺风A3"对应的存储数据
    protected static boolean data_AHU03_fengJiQueFeng;
    // 数据点"排风机已启动A3"对应的存储数据
    protected static boolean data_AHU03_paiFengJiYiQiDong;
    // 数据点"盘管低温A3"对应的存储数据
    protected static boolean data_AHU03_diWenPanGuan;
    // 数据点"灭菌运行A3"对应的存储数据
    protected static boolean data_AHU03_mieJunYunXing;
    // 数据点"风机已启动A4"对应的存储数据
    protected static boolean data_AHU04_fengJiYiQiDong;
    // 数据点"值班状态A4"对应的存储数据
    protected static boolean data_AHU04_zhiBanStatus;
    // 数据点"电加热1A4"对应的存储数据
    protected static boolean data_AHU04_dianJiaRe1;
    // 数据点"电加热2A4"对应的存储数据
    protected static boolean data_AHU04_dianJiaRe2;
    // 数据点"电加热3A4"对应的存储数据
    protected static boolean data_AHU04_dianJiaRe3;
    // 数据点"风机状态A4"对应的存储数据
    protected static boolean data_AHU04_fengJiStatus;
    // 数据点"手自动A4"对应的存储数据
    protected static boolean data_AHU04_shouZiDong;
    // 数据点"冬夏季A4"对应的存储数据
    protected static boolean data_AHU04_dongXiaJi;
    // 数据点"中效报警A4"对应的存储数据
    protected static boolean data_AHU04_zhongXiaoBaoJing;
    // 数据点"电加热高温A4"对应的存储数据
    protected static boolean data_AHU04_dianJiaReGaoWen;
    // 数据点"风机缺风A4"对应的存储数据
    protected static boolean data_AHU04_fengJiQueFeng;
    // 数据点"排风机已启动A4"对应的存储数据
    protected static boolean data_AHU04_paiFengJiYiQiDong;
    // 数据点"盘管低温A4"对应的存储数据
    protected static boolean data_AHU04_diWenPanGuan;
    // 数据点"灭菌运行A4"对应的存储数据
    protected static boolean data_AHU04_mieJunYunXing;
    // 数据点"风机已启动A5"对应的存储数据
    protected static boolean data_AHU05_fengJiYiQiDong;
    // 数据点"值班状态A5"对应的存储数据
    protected static boolean data_AHU05_zhiBanStatus;
    // 数据点"电加热1A5"对应的存储数据
    protected static boolean data_AHU05_dianJiaRe1;
    // 数据点"电加热2A5"对应的存储数据
    protected static boolean data_AHU05_dianJiaRe2;
    // 数据点"电加热3A5"对应的存储数据
    protected static boolean data_AHU05_dianJiaRe3;
    // 数据点"风机状态A5"对应的存储数据
    protected static boolean data_AHU05_fengJiStatus;
    // 数据点"手自动A5"对应的存储数据
    protected static boolean data_AHU05_shouZiDong;
    // 数据点"冬夏季A5"对应的存储数据
    protected static boolean data_AHU05_dongXiaJi;
    // 数据点"中效报警A5"对应的存储数据
    protected static boolean data_AHU05_zhongXiaoBaoJing;
    // 数据点"电加热高温A5"对应的存储数据
    protected static boolean data_AHU05_dianJiaReGaoWen;
    // 数据点"风机缺风A5"对应的存储数据
    protected static boolean data_AHU05_fengJiQueFeng;
    // 数据点"排风机已启动A5"对应的存储数据
    protected static boolean data_AHU05_paiFengJiYiQiDong;
    // 数据点"盘管低温A5"对应的存储数据
    protected static boolean data_AHU05_diWenPanGuan;
    // 数据点"灭菌运行A5"对应的存储数据
    protected static boolean data_AHU05_mieJunYunXing;
    // 数据点"风机已启动A6"对应的存储数据
    protected static boolean data_PAU01_fengJiYiQiDong;
    // 数据点"值班状态A6"对应的存储数据
    protected static boolean data_PAU01_zhiBanStatus;
    // 数据点"电预热1A6"对应的存储数据
    protected static boolean data_PAU01_dianJiaRe1;
    // 数据点"电预热2A6"对应的存储数据
    protected static boolean data_PAU01_dianJiaRe2;
    // 数据点"电预热3A6"对应的存储数据
    protected static boolean data_PAU01_dianJiaRe3;
    // 数据点"风机状态A6"对应的存储数据
    protected static boolean data_PAU01_fengJiStatus;
    // 数据点"手自动A6"对应的存储数据
    protected static boolean data_PAU01_shouZiDong;
    // 数据点"冬夏季A6"对应的存储数据
    protected static boolean data_PAU01_dongXiaJi;
    // 数据点"中效报警A6"对应的存储数据
    protected static boolean data_PAU01_zhongXiaoBaoJing;
    // 数据点"电预热高温A6"对应的存储数据
    protected static boolean data_PAU01_dianJiaReGaoWen;
    // 数据点"风机缺风A6"对应的存储数据
    protected static boolean data_PAU01_fengJiQueFeng;
    // 数据点"排风机已启动A6"对应的存储数据
    protected static boolean data_PAU01_paiFengJiYiQiDong;
    // 数据点"盘管低温A6"对应的存储数据
    protected static boolean data_PAU01_diWenPanGuan;
    // 数据点"灭菌运行A6"对应的存储数据
    protected static boolean data_PAU01_mieJunYunXing;
    // 数据点"风机已启动A7"对应的存储数据
    protected static boolean data_MAU01_fengJiYiQiDong;
    // 数据点"值班状态A7"对应的存储数据
    protected static boolean data_MAU01_zhiBanStatus;
    // 数据点"电预热1A7"对应的存储数据
    protected static boolean data_MAU01_dianJiaRe1;
    // 数据点"电预热2A7"对应的存储数据
    protected static boolean data_MAU01_dianJiaRe2;
    // 数据点"电预热3A7"对应的存储数据
    protected static boolean data_MAU01_dianJiaRe3;
    // 数据点"风机状态A7"对应的存储数据
    protected static boolean data_MAU01_fengJiStatus;
    // 数据点"手自动A7"对应的存储数据
    protected static boolean data_MAU01_shouZiDong;
    // 数据点"冬夏季A7"对应的存储数据
    protected static boolean data_MAU01_dongXiaJi;
    // 数据点"中效报警A7"对应的存储数据
    protected static boolean data_MAU01_zhongXiaoBaoJing;
    // 数据点"电预热高温A7"对应的存储数据
    protected static boolean data_MAU01_dianJiaReGaoWen;
    // 数据点"风机缺风A7"对应的存储数据
    protected static boolean data_MAU01_fengJiQueFeng;
    // 数据点"排风机已启动A7"对应的存储数据
    protected static boolean data_MAU01_paiFengJiYiQiDong;
    // 数据点"盘管低温A7"对应的存储数据
    protected static boolean data_MAU01_diWenPanGuan;
    // 数据点"灭菌运行A7"对应的存储数据
    protected static boolean data_MAU01_mieJunYunXing;
    // 数据点"面板1通讯状态A1"对应的存储数据
    protected static int data_AHU01_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A1"对应的存储数据
    protected static int data_AHU01_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A1"对应的存储数据
    protected static int data_AHU01_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A2"对应的存储数据
    protected static int data_AHU02_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A2"对应的存储数据
    protected static int data_AHU02_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A2"对应的存储数据
    protected static int data_AHU02_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A3"对应的存储数据
    protected static int data_AHU03_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A3"对应的存储数据
    protected static int data_AHU03_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A3"对应的存储数据
    protected static int data_AHU03_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A4"对应的存储数据
    protected static int data_AHU04_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A4"对应的存储数据
    protected static int data_AHU04_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A4"对应的存储数据
    protected static int data_AHU04_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A5"对应的存储数据
    protected static int data_AHU05_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A5"对应的存储数据
    protected static int data_AHU05_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A5"对应的存储数据
    protected static int data_AHU05_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A6"对应的存储数据
    protected static int data_PAU01_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A6"对应的存储数据
    protected static int data_PAU01_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A6"对应的存储数据
    protected static int data_PAU01_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A7"对应的存储数据
    protected static int data_MAU01_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A7"对应的存储数据
    protected static int data_MAU01_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A7"对应的存储数据
    protected static int data_MAU01_mianBanTongXunZhuangTai3;
    // 数据点"实时温度A1"对应的存储数据
    protected static int data_AHU01_tempReal;
    // 数据点"实时湿度A1"对应的存储数据
    protected static int data_AHU01_humiReal;
    // 数据点"温度设定值A1"对应的存储数据
    protected static int data_AHU01_tempSet;
    // 数据点"湿度设定值A1"对应的存储数据
    protected static int data_AHU01_humiSet;
    // 数据点"冷水阀开度A1"对应的存储数据
    protected static int data_AHU01_lengShuiFaKaiDu;
    // 数据点"热水阀开度A1"对应的存储数据
    protected static int data_AHU01_reShuiFaKaiDu;
    // 数据点"新风温度A1"对应的存储数据
    protected static int data_AHU01_xinFengWenDU;
    // 数据点"加湿器开度A1"对应的存储数据
    protected static int data_AHU01_jiaShiQIKaiDu;
    // 数据点"实时温度A2"对应的存储数据
    protected static int data_AHU02_tempReal;
    // 数据点"实时湿度A2"对应的存储数据
    protected static int data_AHU02_humiReal;
    // 数据点"温度设定值A2"对应的存储数据
    protected static int data_AHU02_tempSet;
    // 数据点"湿度设定值A2"对应的存储数据
    protected static int data_AHU02_humiSet;
    // 数据点"冷水阀开度A2"对应的存储数据
    protected static int data_AHU02_lengShuiFaKaiDu;
    // 数据点"热水阀开度A2"对应的存储数据
    protected static int data_AHU02_reShuiFaKaiDu;
    // 数据点"新风温度A2"对应的存储数据
    protected static int data_AHU02_xinFengWenDU;
    // 数据点"加湿器开度A2"对应的存储数据
    protected static int data_AHU02_jiaShiQIKaiDu;
    // 数据点"实时温度A3"对应的存储数据
    protected static int data_AHU03_tempReal;
    // 数据点"实时湿度A3"对应的存储数据
    protected static int data_AHU03_humiReal;
    // 数据点"温度设定值A3"对应的存储数据
    protected static int data_AHU03_tempSet;
    // 数据点"湿度设定值A3"对应的存储数据
    protected static int data_AHU03_humiSet;
    // 数据点"冷水阀开度A3"对应的存储数据
    protected static int data_AHU03_lengShuiFaKaiDu;
    // 数据点"热水阀开度A3"对应的存储数据
    protected static int data_AHU03_reShuiFaKaiDu;
    // 数据点"新风温度A3"对应的存储数据
    protected static int data_AHU03_xinFengWenDU;
    // 数据点"加湿器开度A3"对应的存储数据
    protected static int data_AHU03_jiaShiQIKaiDu;
    // 数据点"实时温度A4"对应的存储数据
    protected static int data_AHU04_tempReal;
    // 数据点"实时湿度A4"对应的存储数据
    protected static int data_AHU04_humiReal;
    // 数据点"温度设定值A4"对应的存储数据
    protected static int data_AHU04_tempSet;
    // 数据点"湿度设定值A4"对应的存储数据
    protected static int data_AHU04_humiSet;
    // 数据点"冷水阀开度A4"对应的存储数据
    protected static int data_AHU04_lengShuiFaKaiDu;
    // 数据点"热水阀开度A4"对应的存储数据
    protected static int data_AHU04_reShuiFaKaiDu;
    // 数据点"新风温度A4"对应的存储数据
    protected static int data_AHU04_xinFengWenDU;
    // 数据点"加湿器开度A4"对应的存储数据
    protected static int data_AHU04_jiaShiQIKaiDu;
    // 数据点"实时温度A5"对应的存储数据
    protected static int data_AHU05_tempReal;
    // 数据点"实时湿度A5"对应的存储数据
    protected static int data_AHU05_humiReal;
    // 数据点"温度设定值A5"对应的存储数据
    protected static int data_AHU05_tempSet;
    // 数据点"湿度设定值A5"对应的存储数据
    protected static int data_AHU05_humiSet;
    // 数据点"冷水阀开度A5"对应的存储数据
    protected static int data_AHU05_lengShuiFaKaiDu;
    // 数据点"热水阀开度A5"对应的存储数据
    protected static int data_AHU05_reShuiFaKaiDu;
    // 数据点"新风温度A5"对应的存储数据
    protected static int data_AHU05_xinFengWenDU;
    // 数据点"加湿器开度A5"对应的存储数据
    protected static int data_AHU05_jiaShiQIKaiDu;
    // 数据点"实时温度A6"对应的存储数据
    protected static int data_PAU01_tempReal;
    // 数据点"实时湿度A6"对应的存储数据
    protected static int data_PAU01_humiReal;
    // 数据点"温度设定值A6"对应的存储数据
    protected static int data_PAU01_tempSet;
    // 数据点"湿度设定值A6"对应的存储数据
    protected static int data_PAU01_humiSet;
    // 数据点"冷水阀开度A6"对应的存储数据
    protected static int data_PAU01_lengShuiFaKaiDu;
    // 数据点"热水阀开度A6"对应的存储数据
    protected static int data_PAU01_reShuiFaKaiDu;
    // 数据点"新风温度A6"对应的存储数据
    protected static int data_PAU01_xinFengWenDU;
    // 数据点"加湿器开度A6"对应的存储数据
    protected static int data_PAU01_jiaShiQIKaiDu;
    // 数据点"实时温度A7"对应的存储数据
    protected static int data_MAU01_tempReal;
    // 数据点"实时湿度A7"对应的存储数据
    protected static int data_MAU01_humiReal;
    // 数据点"温度设定值A7"对应的存储数据
    protected static int data_MAU01_tempSet;
    // 数据点"湿度设定值A7"对应的存储数据
    protected static int data_MAU01_humiSet;
    // 数据点"冷水阀开度A7"对应的存储数据
    protected static int data_MAU01_lengShuiFaKaiDu;
    // 数据点"热水阀开度A7"对应的存储数据
    protected static int data_MAU01_reShuiFaKaiDu;
    // 数据点"新风温度A7"对应的存储数据
    protected static int data_MAU01_xinFengWenDU;
    // 数据点"加湿器开度A7"对应的存储数据
    protected static int data_MAU01_jiaShiQIKaiDu;

    private TextView tv_data_AHU01_title;
    private TextView tv_data_AHU01_tempReal;
    private TextView tv_data_AHU01_humiReal;
    private TextView tv_data_AHU01_tempSet;
    private TextView tv_data_AHU01_humiSet;

    private TextView tv_data_AHU02_title;
    private TextView tv_data_AHU02_tempReal;
    private TextView tv_data_AHU02_humiReal;
    private TextView tv_data_AHU02_tempSet;
    private TextView tv_data_AHU02_humiSet;

    private TextView tv_data_AHU03_title;
    private TextView tv_data_AHU03_tempReal;
    private TextView tv_data_AHU03_humiReal;
    private TextView tv_data_AHU03_tempSet;
    private TextView tv_data_AHU03_humiSet;

    private TextView tv_data_AHU04_title;
    private TextView tv_data_AHU04_tempReal;
    private TextView tv_data_AHU04_humiReal;
    private TextView tv_data_AHU04_tempSet;
    private TextView tv_data_AHU04_humiSet;

    private TextView tv_data_AHU05_title;
    private TextView tv_data_AHU05_tempReal;
    private TextView tv_data_AHU05_humiReal;
    private TextView tv_data_AHU05_tempSet;
    private TextView tv_data_AHU05_humiSet;


    private TextView tv_data_PAU01_title;
    private TextView tv_data_PAU01_xinFengWenDU;
    private TextView tv_data_PAU01_tempReal;
    private TextView tv_data_PAU01_tempSet;

    private TextView tv_data_MAU01_title;
    private TextView tv_data_MAU01_xinFengWenDU;
    private TextView tv_data_MAU01_tempReal;
    private TextView tv_data_MAU01_tempSet;

    private View ll_overview_ahu01;
    private View ll_overview_ahu02;
    private View ll_overview_ahu03;
    private View ll_overview_ahu04;
    private View ll_overview_ahu05;
    private View ll_overview_pau01;
    private View ll_overview_mau01;

    private final String TAG = "krguang";
    private static final int CODE_KAIYUAN_HANDLER_UI = 200;
    private static final int AHU_KAIYUAN_HANDLER_UI = 301;

    private CustomApplication app;
    private Handler handler;

    /** The handler. */
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CODE_KAIYUAN_HANDLER_UI:
                    updateUI();
                    break;
            }
        }
    };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_kaiyuan_activity);

        initView();
        initEvent();
        setTopBar();

        app = (CustomApplication) getApplication();
    }

    private void initView() {

        tv_data_AHU01_title = findViewById(R.id.tv_main_state_title_ahu01);
        tv_data_AHU01_tempReal = findViewById(R.id.tv_main_state_tempnow_ahu01);
        tv_data_AHU01_humiReal = findViewById(R.id.tv_main_state_huminow_ahu01);
        tv_data_AHU01_tempSet = findViewById(R.id.tv_main_state_tempset_ahu01);
        tv_data_AHU01_humiSet = findViewById(R.id.tv_main_state_humiset_ahu01);

        tv_data_AHU02_title = findViewById(R.id.tv_main_state_title_ahu02);
        tv_data_AHU02_tempReal = findViewById(R.id.tv_main_state_tempnow_ahu02);
        tv_data_AHU02_humiReal = findViewById(R.id.tv_main_state_huminow_ahu02);
        tv_data_AHU02_tempSet = findViewById(R.id.tv_main_state_tempset_ahu02);
        tv_data_AHU02_humiSet = findViewById(R.id.tv_main_state_humiset_ahu02);

        tv_data_AHU03_title = findViewById(R.id.tv_main_state_title_ahu03);
        tv_data_AHU03_tempReal = findViewById(R.id.tv_main_state_tempnow_ahu03);
        tv_data_AHU03_humiReal = findViewById(R.id.tv_main_state_huminow_ahu03);
        tv_data_AHU03_tempSet = findViewById(R.id.tv_main_state_tempset_ahu03);
        tv_data_AHU03_humiSet = findViewById(R.id.tv_main_state_humiset_ahu03);

        tv_data_AHU04_title = findViewById(R.id.tv_main_state_title_ahu04);
        tv_data_AHU04_tempReal = findViewById(R.id.tv_main_state_tempnow_ahu04);
        tv_data_AHU04_humiReal = findViewById(R.id.tv_main_state_huminow_ahu04);
        tv_data_AHU04_tempSet = findViewById(R.id.tv_main_state_tempset_ahu04);
        tv_data_AHU04_humiSet = findViewById(R.id.tv_main_state_humiset_ahu04);

        tv_data_AHU05_title = findViewById(R.id.tv_main_state_title_ahu05);
        tv_data_AHU05_tempReal = findViewById(R.id.tv_main_state_tempnow_ahu05);
        tv_data_AHU05_humiReal = findViewById(R.id.tv_main_state_huminow_ahu05);
        tv_data_AHU05_tempSet = findViewById(R.id.tv_main_state_tempset_ahu05);
        tv_data_AHU05_humiSet = findViewById(R.id.tv_main_state_humiset_ahu05);

        tv_data_PAU01_title = findViewById(R.id.tv_main_state_title_pau01);
        tv_data_PAU01_xinFengWenDU = findViewById(R.id.tv_main_state_temp_new_wind_pau01);
        tv_data_PAU01_tempReal = findViewById(R.id.tv_main_state_tempnow_pau01);
        tv_data_PAU01_tempSet = findViewById(R.id.tv_main_state_tempset_pau01);


        tv_data_MAU01_title = findViewById(R.id.tv_main_state_title_mau01);
        tv_data_MAU01_xinFengWenDU = findViewById(R.id.tv_main_state_temp_new_wind_mau01);
        tv_data_MAU01_tempReal = findViewById(R.id.tv_main_state_tempnow_mau01);
        tv_data_MAU01_tempSet = findViewById(R.id.tv_main_state_tempset_mau01);


        ll_overview_ahu01 = findViewById(R.id.ll_ahu01);
        ll_overview_ahu02 = findViewById(R.id.ll_ahu02);
        ll_overview_ahu03 = findViewById(R.id.ll_ahu03);
        ll_overview_ahu04 = findViewById(R.id.ll_ahu04);
        ll_overview_ahu05 = findViewById(R.id.ll_ahu05);

        ll_overview_pau01 = findViewById(R.id.ll_pau01);
        ll_overview_mau01 = findViewById(R.id.ll_mau01);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消订阅云端消息
        mDevice.setListener(null);
        mDevice.setSubscribe(ConstantUtil.TIANJINSANYUAN_PRODUCT_SECRET, false);
    }

    private void startActivityWithStringAHU(String data){

        Bundle bundle = new Bundle();
        bundle.putParcelable("mDevice",mDevice);

        Intent intent = new Intent(UnitKaiYuanGuKeActivity.this, AhukaiYuanDataActivity.class);
        intent.putExtra("extra_data",data);
        Log.d(TAG, "startActivityWithStringAHU: 要传出去的device = "+mDevice);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void startActivityWithStringMAU(String data){

        Bundle bundle = new Bundle();
        bundle.putParcelable("GizWifiDevice",mDevice);

        Intent intent = new Intent(UnitKaiYuanGuKeActivity.this,PauKaiYuanDataActivity.class);
        intent.putExtra("extra_data",data);
        intent.putExtras(bundle);
        startActivity(intent);
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

                if (dataKey.equals(KEY_AHU01_FENGJIYIQIDONG)) {
                    data_AHU01_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_ZHIBANSTATUS)) {
                    data_AHU01_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_DIANJIARE1)) {
                    data_AHU01_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_DIANJIARE2)) {
                    data_AHU01_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_DIANJIARE3)) {
                    data_AHU01_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_FENGJISTATUS)) {
                    data_AHU01_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_SHOUZIDONG)) {
                    data_AHU01_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_DONGXIAJI)) {
                    data_AHU01_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_ZHONGXIAOBAOJING)) {
                    data_AHU01_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_DIANJIAREGAOWEN)) {
                    data_AHU01_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_FENGJIQUEFENG)) {
                    data_AHU01_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_PAIFENGJIYIQIDONG)) {
                    data_AHU01_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_DIWENPANGUAN)) {
                    data_AHU01_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_MIEJUNYUNXING)) {
                    data_AHU01_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_FENGJIYIQIDONG)) {
                    data_AHU02_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_ZHIBANSTATUS)) {
                    data_AHU02_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_DIANJIARE1)) {
                    data_AHU02_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_DIANJIARE2)) {
                    data_AHU02_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_DIANJIARE3)) {
                    data_AHU02_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_FENGJISTATUS)) {
                    data_AHU02_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_SHOUZIDONG)) {
                    data_AHU02_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_DONGXIAJI)) {
                    data_AHU02_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_ZHONGXIAOBAOJING)) {
                    data_AHU02_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_DIANJIAREGAOWEN)) {
                    data_AHU02_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_FENGJIQUEFENG)) {
                    data_AHU02_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_PAIFENGJIYIQIDONG)) {
                    data_AHU02_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_DIWENPANGUAN)) {
                    data_AHU02_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_MIEJUNYUNXING)) {
                    data_AHU02_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_FENGJIYIQIDONG)) {
                    data_AHU03_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_ZHIBANSTATUS)) {
                    data_AHU03_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_DIANJIARE1)) {
                    data_AHU03_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_DIANJIARE2)) {
                    data_AHU03_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_DIANJIARE3)) {
                    data_AHU03_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_FENGJISTATUS)) {
                    data_AHU03_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_SHOUZIDONG)) {
                    data_AHU03_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_DONGXIAJI)) {
                    data_AHU03_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_ZHONGXIAOBAOJING)) {
                    data_AHU03_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_DIANJIAREGAOWEN)) {
                    data_AHU03_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_FENGJIQUEFENG)) {
                    data_AHU03_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_PAIFENGJIYIQIDONG)) {
                    data_AHU03_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_DIWENPANGUAN)) {
                    data_AHU03_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_MIEJUNYUNXING)) {
                    data_AHU03_mieJunYunXing = (Boolean) map.get(dataKey);
                }


                if (dataKey.equals(KEY_AHU04_FENGJIYIQIDONG)) {
                    data_AHU04_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_ZHIBANSTATUS)) {
                    data_AHU04_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_DIANJIARE1)) {
                    data_AHU04_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_DIANJIARE2)) {
                    data_AHU04_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_DIANJIARE3)) {
                    data_AHU04_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_FENGJISTATUS)) {
                    data_AHU04_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_SHOUZIDONG)) {
                    data_AHU04_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_DONGXIAJI)) {
                    data_AHU04_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_ZHONGXIAOBAOJING)) {
                    data_AHU04_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_DIANJIAREGAOWEN)) {
                    data_AHU04_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_FENGJIQUEFENG)) {
                    data_AHU04_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_PAIFENGJIYIQIDONG)) {
                    data_AHU04_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_DIWENPANGUAN)) {
                    data_AHU04_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_MIEJUNYUNXING)) {
                    data_AHU04_mieJunYunXing = (Boolean) map.get(dataKey);
                }

                if (dataKey.equals(KEY_AHU05_FENGJIYIQIDONG)) {
                    data_AHU05_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_ZHIBANSTATUS)) {
                    data_AHU05_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_DIANJIARE1)) {
                    data_AHU05_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_DIANJIARE2)) {
                    data_AHU05_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_DIANJIARE3)) {
                    data_AHU05_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_FENGJISTATUS)) {
                    data_AHU05_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_SHOUZIDONG)) {
                    data_AHU05_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_DONGXIAJI)) {
                    data_AHU05_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_ZHONGXIAOBAOJING)) {
                    data_AHU05_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_DIANJIAREGAOWEN)) {
                    data_AHU05_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_FENGJIQUEFENG)) {
                    data_AHU05_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_PAIFENGJIYIQIDONG)) {
                    data_AHU05_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_DIWENPANGUAN)) {
                    data_AHU05_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_MIEJUNYUNXING)) {
                    data_AHU05_mieJunYunXing = (Boolean) map.get(dataKey);
                }

                if (dataKey.equals(KEY_PAU01_FENGJIYIQIDONG)) {
                    data_PAU01_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_ZHIBANSTATUS)) {
                    data_PAU01_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_DIANJIARE1)) {
                    data_PAU01_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_DIANJIARE2)) {
                    data_PAU01_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_DIANJIARE3)) {
                    data_PAU01_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_FENGJISTATUS)) {
                    data_PAU01_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_SHOUZIDONG)) {
                    data_PAU01_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_DONGXIAJI)) {
                    data_PAU01_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_ZHONGXIAOBAOJING)) {
                    data_PAU01_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_DIANJIAREGAOWEN)) {
                    data_PAU01_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_FENGJIQUEFENG)) {
                    data_PAU01_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_PAIFENGJIYIQIDONG)) {
                    data_PAU01_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_DIWENPANGUAN)) {
                    data_PAU01_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_MIEJUNYUNXING)) {
                    data_PAU01_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_FENGJIYIQIDONG)) {
                    data_MAU01_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_ZHIBANSTATUS)) {
                    data_MAU01_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_DIANJIARE1)) {
                    data_MAU01_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_DIANJIARE2)) {
                    data_MAU01_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_DIANJIARE3)) {
                    data_MAU01_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_FENGJISTATUS)) {
                    data_MAU01_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_SHOUZIDONG)) {
                    data_MAU01_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_DONGXIAJI)) {
                    data_MAU01_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_ZHONGXIAOBAOJING)) {
                    data_MAU01_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_DIANJIAREGAOWEN)) {
                    data_MAU01_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_FENGJIQUEFENG)) {
                    data_MAU01_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_PAIFENGJIYIQIDONG)) {
                    data_MAU01_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_DIWENPANGUAN)) {
                    data_MAU01_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_MIEJUNYUNXING)) {
                    data_MAU01_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU01_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU01_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU01_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU02_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU02_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU02_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU03_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU03_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU03_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU04_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU04_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU04_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU05_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU05_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU05_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_MIANBANTONGXUNZHUANGTAI1)) {

                    data_PAU01_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_MIANBANTONGXUNZHUANGTAI2)) {

                    data_PAU01_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_MIANBANTONGXUNZHUANGTAI3)) {

                    data_PAU01_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_MIANBANTONGXUNZHUANGTAI1)) {

                    data_MAU01_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_MIANBANTONGXUNZHUANGTAI2)) {

                    data_MAU01_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_MIANBANTONGXUNZHUANGTAI3)) {

                    data_MAU01_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_TEMPREAL)) {

                    data_AHU01_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_HUMIREAL)) {

                    data_AHU01_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_TEMPSET)) {

                    data_AHU01_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_HUMISET)) {

                    data_AHU01_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_LENGSHUIFAKAIDU)) {

                    data_AHU01_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_RESHUIFAKAIDU)) {

                    data_AHU01_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_XINFENGWENDU)) {

                    data_AHU01_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU01_JIASHIQIKAIDU)) {

                    data_AHU01_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_TEMPREAL)) {

                    data_AHU02_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_HUMIREAL)) {

                    data_AHU02_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_TEMPSET)) {

                    data_AHU02_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_HUMISET)) {

                    data_AHU02_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_LENGSHUIFAKAIDU)) {

                    data_AHU02_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_RESHUIFAKAIDU)) {

                    data_AHU02_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_XINFENGWENDU)) {

                    data_AHU02_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU02_JIASHIQIKAIDU)) {

                    data_AHU02_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_TEMPREAL)) {

                    data_AHU03_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_HUMIREAL)) {

                    data_AHU03_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_TEMPSET)) {

                    data_AHU03_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_HUMISET)) {

                    data_AHU03_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_LENGSHUIFAKAIDU)) {

                    data_AHU03_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_RESHUIFAKAIDU)) {

                    data_AHU03_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_XINFENGWENDU)) {

                    data_AHU03_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU03_JIASHIQIKAIDU)) {

                    data_AHU03_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_TEMPREAL)) {

                    data_AHU04_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_HUMIREAL)) {

                    data_AHU04_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_TEMPSET)) {

                    data_AHU04_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_HUMISET)) {

                    data_AHU04_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_LENGSHUIFAKAIDU)) {

                    data_AHU04_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_RESHUIFAKAIDU)) {

                    data_AHU04_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_XINFENGWENDU)) {

                    data_AHU04_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU04_JIASHIQIKAIDU)) {

                    data_AHU04_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_TEMPREAL)) {

                    data_AHU05_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_HUMIREAL)) {

                    data_AHU05_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_TEMPSET)) {

                    data_AHU05_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_HUMISET)) {

                    data_AHU05_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_LENGSHUIFAKAIDU)) {

                    data_AHU05_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_RESHUIFAKAIDU)) {

                    data_AHU05_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_XINFENGWENDU)) {

                    data_AHU05_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU05_JIASHIQIKAIDU)) {

                    data_AHU05_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }

                if (dataKey.equals(KEY_PAU01_TEMPREAL)) {

                    data_PAU01_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_HUMIREAL)) {

                    data_PAU01_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_TEMPSET)) {

                    data_PAU01_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_HUMISET)) {

                    data_PAU01_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_LENGSHUIFAKAIDU)) {

                    data_PAU01_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_RESHUIFAKAIDU)) {

                    data_PAU01_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_XINFENGWENDU)) {

                    data_PAU01_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU01_JIASHIQIKAIDU)) {

                    data_PAU01_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }

                if (dataKey.equals(KEY_MAU01_TEMPREAL)) {

                    data_MAU01_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_HUMIREAL)) {

                    data_MAU01_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_TEMPSET)) {

                    data_MAU01_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_HUMISET)) {

                    data_MAU01_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_LENGSHUIFAKAIDU)) {

                    data_MAU01_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_RESHUIFAKAIDU)) {

                    data_MAU01_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_XINFENGWENDU)) {

                    data_MAU01_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU01_JIASHIQIKAIDU)) {

                    data_MAU01_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
            }
        }

        mHandler.sendEmptyMessage(CODE_KAIYUAN_HANDLER_UI);
        handler = app.getHandler();
        String GET_CAHNGE = "get_change";
        if (handler != null){
            handler.obtainMessage(AHU_KAIYUAN_HANDLER_UI,GET_CAHNGE).sendToTarget();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_ahu01:
                startActivityWithStringAHU("AHU01");
                break;

            case R.id.ll_ahu02:
                startActivityWithStringAHU("AHU02");
                break;

            case R.id.ll_ahu03:
                startActivityWithStringAHU("AHU03");
                break;

            case R.id.ll_ahu04:
                startActivityWithStringAHU("AHU04");
                break;

            case R.id.ll_ahu05:
                startActivityWithStringAHU("AHU05");
                break;

            case R.id.ll_pau01:
                startActivityWithStringMAU("PAU01");
                break;

            case R.id.ll_mau01:
                startActivityWithStringMAU("MAU01");
                break;
            default:
                break;
        }
    }

    private void initEvent() {

        ll_overview_ahu01.setOnClickListener(this);
        ll_overview_ahu02.setOnClickListener(this);
        ll_overview_ahu03.setOnClickListener(this);
        ll_overview_ahu04.setOnClickListener(this);
        ll_overview_ahu05.setOnClickListener(this);
        ll_overview_pau01.setOnClickListener(this);
        ll_overview_mau01.setOnClickListener(this);
    }//给view设置监听事件

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

    private void updateUI() {
        if (data_AHU01_fengJiQueFeng){
            tv_data_AHU01_title.setBackgroundColor(Color.RED);
        }else if (data_AHU01_fengJiStatus){
            tv_data_AHU01_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU01_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU02_fengJiQueFeng){
            tv_data_AHU02_title.setBackgroundColor(Color.RED);
        }else if (data_AHU02_fengJiStatus){
            tv_data_AHU02_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU02_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU03_fengJiQueFeng){
            tv_data_AHU03_title.setBackgroundColor(Color.RED);
        }else if (data_AHU03_fengJiStatus){
            tv_data_AHU03_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU03_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU04_fengJiQueFeng){
            tv_data_AHU04_title.setBackgroundColor(Color.RED);
        }else if (data_AHU04_fengJiStatus){
            tv_data_AHU04_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU04_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU05_fengJiQueFeng){
            tv_data_AHU05_title.setBackgroundColor(Color.RED);
        }else if (data_AHU05_fengJiStatus){
            tv_data_AHU05_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU05_title.setBackgroundColor(Color.GRAY);
        }


        if (data_PAU01_fengJiQueFeng){
            tv_data_PAU01_title.setBackgroundColor(Color.RED);
        }else if (data_PAU01_fengJiStatus){
            tv_data_PAU01_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_PAU01_title.setBackgroundColor(Color.GRAY);
        }

        if (data_MAU01_fengJiQueFeng){
            tv_data_MAU01_title.setBackgroundColor(Color.RED);
        }else if (data_MAU01_fengJiStatus){
            tv_data_MAU01_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_MAU01_title.setBackgroundColor(Color.GRAY);
        }


        tv_data_AHU01_tempReal.setText(formatValue(data_AHU01_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU01_humiReal.setText(formatValue(data_AHU01_humiReal/10.0,0.1)+" RH");
        tv_data_AHU01_tempSet.setText(formatValue(data_AHU01_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU01_humiSet.setText(formatValue(data_AHU01_humiSet/10.0,0.1)+" RH");

        tv_data_AHU02_tempReal.setText(formatValue(data_AHU02_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU02_humiReal.setText(formatValue(data_AHU02_humiReal/10.0,0.1)+" RH");
        tv_data_AHU02_tempSet.setText(formatValue(data_AHU02_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU02_humiSet.setText(formatValue(data_AHU02_humiSet/10.0,0.1)+" RH");

        tv_data_AHU03_tempReal.setText(formatValue(data_AHU03_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU03_humiReal.setText(formatValue(data_AHU03_humiReal/10.0,0.1)+" RH");
        tv_data_AHU03_tempSet.setText(formatValue(data_AHU03_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU03_humiSet.setText(formatValue(data_AHU03_humiSet/10.0,0.1)+" RH");

        tv_data_AHU04_tempReal.setText(formatValue(data_AHU04_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU04_humiReal.setText(formatValue(data_AHU04_humiReal/10.0,0.1)+" RH");
        tv_data_AHU04_tempSet.setText(formatValue(data_AHU04_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU04_humiSet.setText(formatValue(data_AHU04_humiSet/10.0,0.1)+" RH");

        tv_data_AHU05_tempReal.setText(formatValue(data_AHU05_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU05_humiReal.setText(formatValue(data_AHU05_humiReal/10.0,0.1)+" RH");
        tv_data_AHU05_tempSet.setText(formatValue(data_AHU05_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU05_humiSet.setText(formatValue(data_AHU05_humiSet/10.0,0.1)+" RH");

        tv_data_PAU01_tempReal.setText(formatValue(data_PAU01_tempReal/10.0,0.1)+" ℃");
        tv_data_PAU01_tempSet.setText(formatValue(data_PAU01_tempSet/10.0,0.1)+" ℃");
        tv_data_PAU01_xinFengWenDU.setText(formatValue(data_PAU01_xinFengWenDU/10.0,0.1)+" ℃");

        tv_data_MAU01_tempReal.setText(formatValue(data_MAU01_tempReal/10.0,0.1)+" ℃");
        tv_data_MAU01_tempSet.setText(formatValue(data_MAU01_tempSet/10.0,0.1)+" ℃");
        tv_data_MAU01_xinFengWenDU.setText(formatValue(data_MAU01_xinFengWenDU/10.0,0.1)+" ℃");

    }
}
