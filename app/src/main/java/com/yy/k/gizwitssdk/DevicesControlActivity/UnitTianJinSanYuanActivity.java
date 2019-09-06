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

public class UnitTianJinSanYuanActivity extends BaseDevicesControlActivity implements View.OnClickListener{


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
    protected static final String KEY_AHU06_FENGJIYIQIDONG = "AHU06_fengJiYiQiDong";
    // 数据点"值班状态A6"对应的标识名
    protected static final String KEY_AHU06_ZHIBANSTATUS = "AHU06_zhiBanStatus";
    // 数据点"电加热1A6"对应的标识名
    protected static final String KEY_AHU06_DIANJIARE1 = "AHU06_dianJiaRe1";
    // 数据点"电加热2A6"对应的标识名
    protected static final String KEY_AHU06_DIANJIARE2 = "AHU06_dianJiaRe2";
    // 数据点"电加热3A6"对应的标识名
    protected static final String KEY_AHU06_DIANJIARE3 = "AHU06_dianJiaRe3";
    // 数据点"风机状态A6"对应的标识名
    protected static final String KEY_AHU06_FENGJISTATUS = "AHU06_fengJiStatus";
    // 数据点"手自动A6"对应的标识名
    protected static final String KEY_AHU06_SHOUZIDONG = "AHU06_shouZiDong";
    // 数据点"冬夏季A6"对应的标识名
    protected static final String KEY_AHU06_DONGXIAJI = "AHU06_dongXiaJi";
    // 数据点"中效报警A6"对应的标识名
    protected static final String KEY_AHU06_ZHONGXIAOBAOJING = "AHU06_zhongXiaoBaoJing";
    // 数据点"电加热高温A6"对应的标识名
    protected static final String KEY_AHU06_DIANJIAREGAOWEN = "AHU06_dianJiaReGaoWen";
    // 数据点"风机缺风A6"对应的标识名
    protected static final String KEY_AHU06_FENGJIQUEFENG = "AHU06_fengJiQueFeng";
    // 数据点"排风机已启动A6"对应的标识名
    protected static final String KEY_AHU06_PAIFENGJIYIQIDONG = "AHU06_paiFengJiYiQiDong";
    // 数据点"盘管低温A6"对应的标识名
    protected static final String KEY_AHU06_DIWENPANGUAN = "AHU06_diWenPanGuan";
    // 数据点"灭菌运行A6"对应的标识名
    protected static final String KEY_AHU06_MIEJUNYUNXING = "AHU06_mieJunYunXing";
    // 数据点"风机已启动A7"对应的标识名
    protected static final String KEY_AHU07_FENGJIYIQIDONG = "AHU07_fengJiYiQiDong";
    // 数据点"值班状态A7"对应的标识名
    protected static final String KEY_AHU07_ZHIBANSTATUS = "AHU07_zhiBanStatus";
    // 数据点"电加热1A7"对应的标识名
    protected static final String KEY_AHU07_DIANJIARE1 = "AHU07_dianJiaRe1";
    // 数据点"电加热2A7"对应的标识名
    protected static final String KEY_AHU07_DIANJIARE2 = "AHU07_dianJiaRe2";
    // 数据点"电加热3A7"对应的标识名
    protected static final String KEY_AHU07_DIANJIARE3 = "AHU07_dianJiaRe3";
    // 数据点"风机状态A7"对应的标识名
    protected static final String KEY_AHU07_FENGJISTATUS = "AHU07_fengJiStatus";
    // 数据点"手自动A7"对应的标识名
    protected static final String KEY_AHU07_SHOUZIDONG = "AHU07_shouZiDong";
    // 数据点"冬夏季A7"对应的标识名
    protected static final String KEY_AHU07_DONGXIAJI = "AHU07_dongXiaJi";
    // 数据点"中效报警A7"对应的标识名
    protected static final String KEY_AHU07_ZHONGXIAOBAOJING = "AHU07_zhongXiaoBaoJing";
    // 数据点"电加热高温A7"对应的标识名
    protected static final String KEY_AHU07_DIANJIAREGAOWEN = "AHU07_dianJiaReGaoWen";
    // 数据点"风机缺风A7"对应的标识名
    protected static final String KEY_AHU07_FENGJIQUEFENG = "AHU07_fengJiQueFeng";
    // 数据点"排风机已启动A7"对应的标识名
    protected static final String KEY_AHU07_PAIFENGJIYIQIDONG = "AHU07_paiFengJiYiQiDong";
    // 数据点"盘管低温A7"对应的标识名
    protected static final String KEY_AHU07_DIWENPANGUAN = "AHU07_diWenPanGuan";
    // 数据点"灭菌运行A7"对应的标识名
    protected static final String KEY_AHU07_MIEJUNYUNXING = "AHU07_mieJunYunXing";
    // 数据点"风机已启动A8"对应的标识名
    protected static final String KEY_AHU08_FENGJIYIQIDONG = "AHU08_fengJiYiQiDong";
    // 数据点"值班状态A8"对应的标识名
    protected static final String KEY_AHU08_ZHIBANSTATUS = "AHU08_zhiBanStatus";
    // 数据点"电加热1A8"对应的标识名
    protected static final String KEY_AHU08_DIANJIARE1 = "AHU08_dianJiaRe1";
    // 数据点"电加热2A8"对应的标识名
    protected static final String KEY_AHU08_DIANJIARE2 = "AHU08_dianJiaRe2";
    // 数据点"电加热3A8"对应的标识名
    protected static final String KEY_AHU08_DIANJIARE3 = "AHU08_dianJiaRe3";
    // 数据点"风机状态A8"对应的标识名
    protected static final String KEY_AHU08_FENGJISTATUS = "AHU08_fengJiStatus";
    // 数据点"手自动A8"对应的标识名
    protected static final String KEY_AHU08_SHOUZIDONG = "AHU08_shouZiDong";
    // 数据点"冬夏季A8"对应的标识名
    protected static final String KEY_AHU08_DONGXIAJI = "AHU08_dongXiaJi";
    // 数据点"中效报警A8"对应的标识名
    protected static final String KEY_AHU08_ZHONGXIAOBAOJING = "AHU08_zhongXiaoBaoJing";
    // 数据点"电加热高温A8"对应的标识名
    protected static final String KEY_AHU08_DIANJIAREGAOWEN = "AHU08_dianJiaReGaoWen";
    // 数据点"风机缺风A8"对应的标识名
    protected static final String KEY_AHU08_FENGJIQUEFENG = "AHU08_fengJiQueFeng";
    // 数据点"排风机已启动A8"对应的标识名
    protected static final String KEY_AHU08_PAIFENGJIYIQIDONG = "AHU08_paiFengJiYiQiDong";
    // 数据点"盘管低温A8"对应的标识名
    protected static final String KEY_AHU08_DIWENPANGUAN = "AHU08_diWenPanGuan";
    // 数据点"灭菌运行A8"对应的标识名
    protected static final String KEY_AHU08_MIEJUNYUNXING = "AHU08_mieJunYunXing";
    // 数据点"风机已启动A9"对应的标识名
    protected static final String KEY_AHU09_FENGJIYIQIDONG = "AHU09_fengJiYiQiDong";
    // 数据点"值班状态A9"对应的标识名
    protected static final String KEY_AHU09_ZHIBANSTATUS = "AHU09_zhiBanStatus";
    // 数据点"电加热1A9"对应的标识名
    protected static final String KEY_AHU09_DIANJIARE1 = "AHU09_dianJiaRe1";
    // 数据点"电加热2A9"对应的标识名
    protected static final String KEY_AHU09_DIANJIARE2 = "AHU09_dianJiaRe2";
    // 数据点"电加热3A9"对应的标识名
    protected static final String KEY_AHU09_DIANJIARE3 = "AHU09_dianJiaRe3";
    // 数据点"风机状态A9"对应的标识名
    protected static final String KEY_AHU09_FENGJISTATUS = "AHU09_fengJiStatus";
    // 数据点"手自动A9"对应的标识名
    protected static final String KEY_AHU09_SHOUZIDONG = "AHU09_shouZiDong";
    // 数据点"冬夏季A9"对应的标识名
    protected static final String KEY_AHU09_DONGXIAJI = "AHU09_dongXiaJi";
    // 数据点"中效报警A9"对应的标识名
    protected static final String KEY_AHU09_ZHONGXIAOBAOJING = "AHU09_zhongXiaoBaoJing";
    // 数据点"电加热高温A9"对应的标识名
    protected static final String KEY_AHU09_DIANJIAREGAOWEN = "AHU09_dianJiaReGaoWen";
    // 数据点"风机缺风A9"对应的标识名
    protected static final String KEY_AHU09_FENGJIQUEFENG = "AHU09_fengJiQueFeng";
    // 数据点"排风机已启动A9"对应的标识名
    protected static final String KEY_AHU09_PAIFENGJIYIQIDONG = "AHU09_paiFengJiYiQiDong";
    // 数据点"盘管低温A9"对应的标识名
    protected static final String KEY_AHU09_DIWENPANGUAN = "AHU09_diWenPanGuan";
    // 数据点"灭菌运行A9"对应的标识名
    protected static final String KEY_AHU09_MIEJUNYUNXING = "AHU09_mieJunYunXing";
    // 数据点"风机已启动A10"对应的标识名
    protected static final String KEY_AHU10_FENGJIYIQIDONG = "AHU10_fengJiYiQiDong";
    // 数据点"值班状态A10"对应的标识名
    protected static final String KEY_AHU10_ZHIBANSTATUS = "AHU10_zhiBanStatus";
    // 数据点"电加热1A10"对应的标识名
    protected static final String KEY_AHU10_DIANJIARE1 = "AHU10_dianJiaRe1";
    // 数据点"电加热2A10"对应的标识名
    protected static final String KEY_AHU10_DIANJIARE2 = "AHU10_dianJiaRe2";
    // 数据点"电加热3A10"对应的标识名
    protected static final String KEY_AHU10_DIANJIARE3 = "AHU10_dianJiaRe3";
    // 数据点"风机状态A10"对应的标识名
    protected static final String KEY_AHU10_FENGJISTATUS = "AHU10_fengJiStatus";
    // 数据点"手自动A10"对应的标识名
    protected static final String KEY_AHU10_SHOUZIDONG = "AHU10_shouZiDong";
    // 数据点"冬夏季A10"对应的标识名
    protected static final String KEY_AHU10_DONGXIAJI = "AHU10_dongXiaJi";
    // 数据点"中效报警A10"对应的标识名
    protected static final String KEY_AHU10_ZHONGXIAOBAOJING = "AHU10_zhongXiaoBaoJing";
    // 数据点"电加热高温A10"对应的标识名
    protected static final String KEY_AHU10_DIANJIAREGAOWEN = "AHU10_dianJiaReGaoWen";
    // 数据点"风机缺风A10"对应的标识名
    protected static final String KEY_AHU10_FENGJIQUEFENG = "AHU10_fengJiQueFeng";
    // 数据点"排风机已启动A10"对应的标识名
    protected static final String KEY_AHU10_PAIFENGJIYIQIDONG = "AHU10_paiFengJiYiQiDong";
    // 数据点"盘管低温A10"对应的标识名
    protected static final String KEY_AHU10_DIWENPANGUAN = "AHU10_diWenPanGuan";
    // 数据点"灭菌运行A10"对应的标识名
    protected static final String KEY_AHU10_MIEJUNYUNXING = "AHU10_mieJunYunXing";
    // 数据点"风机已启动A11"对应的标识名
    protected static final String KEY_MAU01_FENGJIYIQIDONG = "MAU01_fengJiYiQiDong";
    // 数据点"值班状态A11"对应的标识名
    protected static final String KEY_MAU01_ZHIBANSTATUS = "MAU01_zhiBanStatus";
    // 数据点"电预热1A11"对应的标识名
    protected static final String KEY_MAU01_DIANJIARE1 = "MAU01_dianJiaRe1";
    // 数据点"电预热2A11"对应的标识名
    protected static final String KEY_MAU01_DIANJIARE2 = "MAU01_dianJiaRe2";
    // 数据点"电预热3A11"对应的标识名
    protected static final String KEY_MAU01_DIANJIARE3 = "MAU01_dianJiaRe3";
    // 数据点"风机状态A11"对应的标识名
    protected static final String KEY_MAU01_FENGJISTATUS = "MAU01_fengJiStatus";
    // 数据点"手自动A11"对应的标识名
    protected static final String KEY_MAU01_SHOUZIDONG = "MAU01_shouZiDong";
    // 数据点"冬夏季A11"对应的标识名
    protected static final String KEY_MAU01_DONGXIAJI = "MAU01_dongXiaJi";
    // 数据点"中效报警A11"对应的标识名
    protected static final String KEY_MAU01_ZHONGXIAOBAOJING = "MAU01_zhongXiaoBaoJing";
    // 数据点"电预热高温A11"对应的标识名
    protected static final String KEY_MAU01_DIANJIAREGAOWEN = "MAU01_dianJiaReGaoWen";
    // 数据点"风机缺风A11"对应的标识名
    protected static final String KEY_MAU01_FENGJIQUEFENG = "MAU01_fengJiQueFeng";
    // 数据点"排风机已启动A11"对应的标识名
    protected static final String KEY_MAU01_PAIFENGJIYIQIDONG = "MAU01_paiFengJiYiQiDong";
    // 数据点"盘管低温A11"对应的标识名
    protected static final String KEY_MAU01_DIWENPANGUAN = "MAU01_diWenPanGuan";
    // 数据点"灭菌运行A11"对应的标识名
    protected static final String KEY_MAU01_MIEJUNYUNXING = "MAU01_mieJunYunXing";
    // 数据点"风机已启动A12"对应的标识名
    protected static final String KEY_MAU02_FENGJIYIQIDONG = "MAU02_fengJiYiQiDong";
    // 数据点"值班状态A12"对应的标识名
    protected static final String KEY_MAU02_ZHIBANSTATUS = "MAU02_zhiBanStatus";
    // 数据点"电预热1A12"对应的标识名
    protected static final String KEY_MAU02_DIANJIARE1 = "MAU02_dianJiaRe1";
    // 数据点"电预热2A12"对应的标识名
    protected static final String KEY_MAU02_DIANJIARE2 = "MAU02_dianJiaRe2";
    // 数据点"电预热3A12"对应的标识名
    protected static final String KEY_MAU02_DIANJIARE3 = "MAU02_dianJiaRe3";
    // 数据点"风机状态A12"对应的标识名
    protected static final String KEY_MAU02_FENGJISTATUS = "MAU02_fengJiStatus";
    // 数据点"手自动A12"对应的标识名
    protected static final String KEY_MAU02_SHOUZIDONG = "MAU02_shouZiDong";
    // 数据点"冬夏季A12"对应的标识名
    protected static final String KEY_MAU02_DONGXIAJI = "MAU02_dongXiaJi";
    // 数据点"中效报警A12"对应的标识名
    protected static final String KEY_MAU02_ZHONGXIAOBAOJING = "MAU02_zhongXiaoBaoJing";
    // 数据点"电预热高温A12"对应的标识名
    protected static final String KEY_MAU02_DIANJIAREGAOWEN = "MAU02_dianJiaReGaoWen";
    // 数据点"风机缺风A12"对应的标识名
    protected static final String KEY_MAU02_FENGJIQUEFENG = "MAU02_fengJiQueFeng";
    // 数据点"排风机已启动A12"对应的标识名
    protected static final String KEY_MAU02_PAIFENGJIYIQIDONG = "MAU02_paiFengJiYiQiDong";
    // 数据点"盘管低温A12"对应的标识名
    protected static final String KEY_MAU02_DIWENPANGUAN = "MAU02_diWenPanGuan";
    // 数据点"灭菌运行A12"对应的标识名
    protected static final String KEY_MAU02_MIEJUNYUNXING = "MAU02_mieJunYunXing";
    // 数据点"风机已启动A13"对应的标识名
    protected static final String KEY_MAU03_FENGJIYIQIDONG = "MAU03_fengJiYiQiDong";
    // 数据点"值班状态A13"对应的标识名
    protected static final String KEY_MAU03_ZHIBANSTATUS = "MAU03_zhiBanStatus";
    // 数据点"电预热1A13"对应的标识名
    protected static final String KEY_MAU03_DIANJIARE1 = "MAU03_dianJiaRe1";
    // 数据点"电预热2A13"对应的标识名
    protected static final String KEY_MAU03_DIANJIARE2 = "MAU03_dianJiaRe2";
    // 数据点"电预热3A13"对应的标识名
    protected static final String KEY_MAU03_DIANJIARE3 = "MAU03_dianJiaRe3";
    // 数据点"风机状态A13"对应的标识名
    protected static final String KEY_MAU03_FENGJISTATUS = "MAU03_fengJiStatus";
    // 数据点"手自动A13"对应的标识名
    protected static final String KEY_MAU03_SHOUZIDONG = "MAU03_shouZiDong";
    // 数据点"冬夏季A13"对应的标识名
    protected static final String KEY_MAU03_DONGXIAJI = "MAU03_dongXiaJi";
    // 数据点"中效报警A13"对应的标识名
    protected static final String KEY_MAU03_ZHONGXIAOBAOJING = "MAU03_zhongXiaoBaoJing";
    // 数据点"电预热高温A13"对应的标识名
    protected static final String KEY_MAU03_DIANJIAREGAOWEN = "MAU03_dianJiaReGaoWen";
    // 数据点"风机缺风A13"对应的标识名
    protected static final String KEY_MAU03_FENGJIQUEFENG = "MAU03_fengJiQueFeng";
    // 数据点"排风机已启动A13"对应的标识名
    protected static final String KEY_MAU03_PAIFENGJIYIQIDONG = "MAU03_paiFengJiYiQiDong";
    // 数据点"盘管低温A13"对应的标识名
    protected static final String KEY_MAU03_DIWENPANGUAN = "MAU03_diWenPanGuan";
    // 数据点"灭菌运行A13"对应的标识名
    protected static final String KEY_MAU03_MIEJUNYUNXING = "MAU03_mieJunYunXing";
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
    protected static final String KEY_AHU06_MIANBANTONGXUNZHUANGTAI1 = "AHU06_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A6"对应的标识名
    protected static final String KEY_AHU06_MIANBANTONGXUNZHUANGTAI2 = "AHU06_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A6"对应的标识名
    protected static final String KEY_AHU06_MIANBANTONGXUNZHUANGTAI3 = "AHU06_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A7"对应的标识名
    protected static final String KEY_AHU07_MIANBANTONGXUNZHUANGTAI1 = "AHU07_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A7"对应的标识名
    protected static final String KEY_AHU07_MIANBANTONGXUNZHUANGTAI2 = "AHU07_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A7"对应的标识名
    protected static final String KEY_AHU07_MIANBANTONGXUNZHUANGTAI3 = "AHU07_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A8"对应的标识名
    protected static final String KEY_AHU08_MIANBANTONGXUNZHUANGTAI1 = "AHU08_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A8"对应的标识名
    protected static final String KEY_AHU08_MIANBANTONGXUNZHUANGTAI2 = "AHU08_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A8"对应的标识名
    protected static final String KEY_AHU08_MIANBANTONGXUNZHUANGTAI3 = "AHU08_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A9"对应的标识名
    protected static final String KEY_AHU09_MIANBANTONGXUNZHUANGTAI1 = "AHU09_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A9"对应的标识名
    protected static final String KEY_AHU09_MIANBANTONGXUNZHUANGTAI2 = "AHU09_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A9"对应的标识名
    protected static final String KEY_AHU09_MIANBANTONGXUNZHUANGTAI3 = "AHU09_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A10"对应的标识名
    protected static final String KEY_AHU10_MIANBANTONGXUNZHUANGTAI1 = "AHU10_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A10"对应的标识名
    protected static final String KEY_AHU10_MIANBANTONGXUNZHUANGTAI2 = "AHU10_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A10"对应的标识名
    protected static final String KEY_AHU10_MIANBANTONGXUNZHUANGTAI3 = "AHU10_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A11"对应的标识名
    protected static final String KEY_MAU01_MIANBANTONGXUNZHUANGTAI1 = "MAU01_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A11"对应的标识名
    protected static final String KEY_MAU01_MIANBANTONGXUNZHUANGTAI2 = "MAU01_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A11"对应的标识名
    protected static final String KEY_MAU01_MIANBANTONGXUNZHUANGTAI3 = "MAU01_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A12"对应的标识名
    protected static final String KEY_MAU02_MIANBANTONGXUNZHUANGTAI1 = "MAU02_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A12"对应的标识名
    protected static final String KEY_MAU02_MIANBANTONGXUNZHUANGTAI2 = "MAU02_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A12"对应的标识名
    protected static final String KEY_MAU02_MIANBANTONGXUNZHUANGTAI3 = "MAU02_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A13"对应的标识名
    protected static final String KEY_MAU03_MIANBANTONGXUNZHUANGTAI1 = "MAU03_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A13"对应的标识名
    protected static final String KEY_MAU03_MIANBANTONGXUNZHUANGTAI2 = "MAU03_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A13"对应的标识名
    protected static final String KEY_MAU03_MIANBANTONGXUNZHUANGTAI3 = "MAU03_mianBanTongXunZhuangTai3";
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
    protected static final String KEY_AHU06_TEMPREAL = "AHU06_tempReal";
    // 数据点"实时湿度A6"对应的标识名
    protected static final String KEY_AHU06_HUMIREAL = "AHU06_humiReal";
    // 数据点"温度设定值A6"对应的标识名
    protected static final String KEY_AHU06_TEMPSET = "AHU06_tempSet";
    // 数据点"湿度设定值A6"对应的标识名
    protected static final String KEY_AHU06_HUMISET = "AHU06_humiSet";
    // 数据点"冷水阀开度A6"对应的标识名
    protected static final String KEY_AHU06_LENGSHUIFAKAIDU = "AHU06_lengShuiFaKaiDu";
    // 数据点"热水阀开度A6"对应的标识名
    protected static final String KEY_AHU06_RESHUIFAKAIDU = "AHU06_reShuiFaKaiDu";
    // 数据点"新风温度A6"对应的标识名
    protected static final String KEY_AHU06_XINFENGWENDU = "AHU06_xinFengWenDU";
    // 数据点"加湿器开度A6"对应的标识名
    protected static final String KEY_AHU06_JIASHIQIKAIDU = "AHU06_jiaShiQIKaiDu";
    // 数据点"实时温度A7"对应的标识名
    protected static final String KEY_AHU07_TEMPREAL = "AHU07_tempReal";
    // 数据点"实时湿度A7"对应的标识名
    protected static final String KEY_AHU07_HUMIREAL = "AHU07_humiReal";
    // 数据点"温度设定值A7"对应的标识名
    protected static final String KEY_AHU07_TEMPSET = "AHU07_tempSet";
    // 数据点"湿度设定值A7"对应的标识名
    protected static final String KEY_AHU07_HUMISET = "AHU07_humiSet";
    // 数据点"冷水阀开度A7"对应的标识名
    protected static final String KEY_AHU07_LENGSHUIFAKAIDU = "AHU07_lengShuiFaKaiDu";
    // 数据点"热水阀开度A7"对应的标识名
    protected static final String KEY_AHU07_RESHUIFAKAIDU = "AHU07_reShuiFaKaiDu";
    // 数据点"新风温度A7"对应的标识名
    protected static final String KEY_AHU07_XINFENGWENDU = "AHU07_xinFengWenDU";
    // 数据点"加湿器开度A7"对应的标识名
    protected static final String KEY_AHU07_JIASHIQIKAIDU = "AHU07_jiaShiQIKaiDu";
    // 数据点"实时温度A8"对应的标识名
    protected static final String KEY_AHU08_TEMPREAL = "AHU08_tempReal";
    // 数据点"实时湿度A8"对应的标识名
    protected static final String KEY_AHU08_HUMIREAL = "AHU08_humiReal";
    // 数据点"温度设定值A8"对应的标识名
    protected static final String KEY_AHU08_TEMPSET = "AHU08_tempSet";
    // 数据点"湿度设定值A8"对应的标识名
    protected static final String KEY_AHU08_HUMISET = "AHU08_humiSet";
    // 数据点"冷水阀开度A8"对应的标识名
    protected static final String KEY_AHU08_LENGSHUIFAKAIDU = "AHU08_lengShuiFaKaiDu";
    // 数据点"热水阀开度A8"对应的标识名
    protected static final String KEY_AHU08_RESHUIFAKAIDU = "AHU08_reShuiFaKaiDu";
    // 数据点"新风温度A8"对应的标识名
    protected static final String KEY_AHU08_XINFENGWENDU = "AHU08_xinFengWenDU";
    // 数据点"加湿器开度A8"对应的标识名
    protected static final String KEY_AHU08_JIASHIQIKAIDU = "AHU08_jiaShiQIKaiDu";
    // 数据点"实时温度A9"对应的标识名
    protected static final String KEY_AHU09_TEMPREAL = "AHU09_tempReal";
    // 数据点"实时湿度A9"对应的标识名
    protected static final String KEY_AHU09_HUMIREAL = "AHU09_humiReal";
    // 数据点"温度设定值A9"对应的标识名
    protected static final String KEY_AHU09_TEMPSET = "AHU09_tempSet";
    // 数据点"湿度设定值A9"对应的标识名
    protected static final String KEY_AHU09_HUMISET = "AHU09_humiSet";
    // 数据点"冷水阀开度A9"对应的标识名
    protected static final String KEY_AHU09_LENGSHUIFAKAIDU = "AHU09_lengShuiFaKaiDu";
    // 数据点"热水阀开度A9"对应的标识名
    protected static final String KEY_AHU09_RESHUIFAKAIDU = "AHU09_reShuiFaKaiDu";
    // 数据点"新风温度A9"对应的标识名
    protected static final String KEY_AHU09_XINFENGWENDU = "AHU09_xinFengWenDU";
    // 数据点"加湿器开度A9"对应的标识名
    protected static final String KEY_AHU09_JIASHIQIKAIDU = "AHU09_jiaShiQIKaiDu";
    // 数据点"实时温度A10"对应的标识名
    protected static final String KEY_AHU10_TEMPREAL = "AHU10_tempReal";
    // 数据点"实时湿度A10"对应的标识名
    protected static final String KEY_AHU10_HUMIREAL = "AHU10_humiReal";
    // 数据点"温度设定值A10"对应的标识名
    protected static final String KEY_AHU10_TEMPSET = "AHU10_tempSet";
    // 数据点"湿度设定值A10"对应的标识名
    protected static final String KEY_AHU10_HUMISET = "AHU10_humiSet";
    // 数据点"冷水阀开度A10"对应的标识名
    protected static final String KEY_AHU10_LENGSHUIFAKAIDU = "AHU10_lengShuiFaKaiDu";
    // 数据点"热水阀开度A10"对应的标识名
    protected static final String KEY_AHU10_RESHUIFAKAIDU = "AHU10_reShuiFaKaiDu";
    // 数据点"新风温度A10"对应的标识名
    protected static final String KEY_AHU10_XINFENGWENDU = "AHU10_xinFengWenDU";
    // 数据点"加湿器开度A10"对应的标识名
    protected static final String KEY_AHU10_JIASHIQIKAIDU = "AHU10_jiaShiQIKaiDu";
    // 数据点"实时温度A11"对应的标识名
    protected static final String KEY_MAU01_TEMPREAL = "MAU01_tempReal";
    // 数据点"实时湿度A11"对应的标识名
    protected static final String KEY_MAU01_HUMIREAL = "MAU01_humiReal";
    // 数据点"温度设定值A11"对应的标识名
    protected static final String KEY_MAU01_TEMPSET = "MAU01_tempSet";
    // 数据点"湿度设定值A11"对应的标识名
    protected static final String KEY_MAU01_HUMISET = "MAU01_humiSet";
    // 数据点"冷水阀开度A11"对应的标识名
    protected static final String KEY_MAU01_LENGSHUIFAKAIDU = "MAU01_lengShuiFaKaiDu";
    // 数据点"热水阀开度A11"对应的标识名
    protected static final String KEY_MAU01_RESHUIFAKAIDU = "MAU01_reShuiFaKaiDu";
    // 数据点"新风温度A11"对应的标识名
    protected static final String KEY_MAU01_XINFENGWENDU = "MAU01_xinFengWenDU";
    // 数据点"加湿器开度A11"对应的标识名
    protected static final String KEY_MAU01_JIASHIQIKAIDU = "MAU01_jiaShiQIKaiDu";
    // 数据点"实时温度A12"对应的标识名
    protected static final String KEY_MAU02_TEMPREAL = "MAU02_tempReal";
    // 数据点"实时湿度A12"对应的标识名
    protected static final String KEY_MAU02_HUMIREAL = "MAU02_humiReal";
    // 数据点"温度设定值A12"对应的标识名
    protected static final String KEY_MAU02_TEMPSET = "MAU02_tempSet";
    // 数据点"湿度设定值A12"对应的标识名
    protected static final String KEY_MAU02_HUMISET = "MAU02_humiSet";
    // 数据点"冷水阀开度A12"对应的标识名
    protected static final String KEY_MAU02_LENGSHUIFAKAIDU = "MAU02_lengShuiFaKaiDu";
    // 数据点"热水阀开度A12"对应的标识名
    protected static final String KEY_MAU02_RESHUIFAKAIDU = "MAU02_reShuiFaKaiDu";
    // 数据点"新风温度A12"对应的标识名
    protected static final String KEY_MAU02_XINFENGWENDU = "MAU02_xinFengWenDU";
    // 数据点"加湿器开度A12"对应的标识名
    protected static final String KEY_MAU02_JIASHIQIKAIDU = "MAU02_jiaShiQIKaiDu";
    // 数据点"实时温度A13"对应的标识名
    protected static final String KEY_MAU03_TEMPREAL = "MAU03_tempReal";
    // 数据点"实时湿度A13"对应的标识名
    protected static final String KEY_MAU03_HUMIREAL = "MAU03_humiReal";
    // 数据点"温度设定值A13"对应的标识名
    protected static final String KEY_MAU03_TEMPSET = "MAU03_tempSet";
    // 数据点"湿度设定值A13"对应的标识名
    protected static final String KEY_MAU03_HUMISET = "MAU03_humiSet";
    // 数据点"冷水阀开度A13"对应的标识名
    protected static final String KEY_MAU03_LENGSHUIFAKAIDU = "MAU03_lengShuiFaKaiDu";
    // 数据点"热水阀开度A13"对应的标识名
    protected static final String KEY_MAU03_RESHUIFAKAIDU = "MAU03_reShuiFaKaiDu";
    // 数据点"新风温度A13"对应的标识名
    protected static final String KEY_MAU03_XINFENGWENDU = "MAU03_xinFengWenDU";
    // 数据点"加湿器开度A13"对应的标识名
    protected static final String KEY_MAU03_JIASHIQIKAIDU = "MAU03_jiaShiQIKaiDu";

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
    protected static boolean data_AHU06_fengJiYiQiDong;
    // 数据点"值班状态A6"对应的存储数据
    protected static boolean data_AHU06_zhiBanStatus;
    // 数据点"电加热1A6"对应的存储数据
    protected static boolean data_AHU06_dianJiaRe1;
    // 数据点"电加热2A6"对应的存储数据
    protected static boolean data_AHU06_dianJiaRe2;
    // 数据点"电加热3A6"对应的存储数据
    protected static boolean data_AHU06_dianJiaRe3;
    // 数据点"风机状态A6"对应的存储数据
    protected static boolean data_AHU06_fengJiStatus;
    // 数据点"手自动A6"对应的存储数据
    protected static boolean data_AHU06_shouZiDong;
    // 数据点"冬夏季A6"对应的存储数据
    protected static boolean data_AHU06_dongXiaJi;
    // 数据点"中效报警A6"对应的存储数据
    protected static boolean data_AHU06_zhongXiaoBaoJing;
    // 数据点"电加热高温A6"对应的存储数据
    protected static boolean data_AHU06_dianJiaReGaoWen;
    // 数据点"风机缺风A6"对应的存储数据
    protected static boolean data_AHU06_fengJiQueFeng;
    // 数据点"排风机已启动A6"对应的存储数据
    protected static boolean data_AHU06_paiFengJiYiQiDong;
    // 数据点"盘管低温A6"对应的存储数据
    protected static boolean data_AHU06_diWenPanGuan;
    // 数据点"灭菌运行A6"对应的存储数据
    protected static boolean data_AHU06_mieJunYunXing;
    // 数据点"风机已启动A7"对应的存储数据
    protected static boolean data_AHU07_fengJiYiQiDong;
    // 数据点"值班状态A7"对应的存储数据
    protected static boolean data_AHU07_zhiBanStatus;
    // 数据点"电加热1A7"对应的存储数据
    protected static boolean data_AHU07_dianJiaRe1;
    // 数据点"电加热2A7"对应的存储数据
    protected static boolean data_AHU07_dianJiaRe2;
    // 数据点"电加热3A7"对应的存储数据
    protected static boolean data_AHU07_dianJiaRe3;
    // 数据点"风机状态A7"对应的存储数据
    protected static boolean data_AHU07_fengJiStatus;
    // 数据点"手自动A7"对应的存储数据
    protected static boolean data_AHU07_shouZiDong;
    // 数据点"冬夏季A7"对应的存储数据
    protected static boolean data_AHU07_dongXiaJi;
    // 数据点"中效报警A7"对应的存储数据
    protected static boolean data_AHU07_zhongXiaoBaoJing;
    // 数据点"电加热高温A7"对应的存储数据
    protected static boolean data_AHU07_dianJiaReGaoWen;
    // 数据点"风机缺风A7"对应的存储数据
    protected static boolean data_AHU07_fengJiQueFeng;
    // 数据点"排风机已启动A7"对应的存储数据
    protected static boolean data_AHU07_paiFengJiYiQiDong;
    // 数据点"盘管低温A7"对应的存储数据
    protected static boolean data_AHU07_diWenPanGuan;
    // 数据点"灭菌运行A7"对应的存储数据
    protected static boolean data_AHU07_mieJunYunXing;
    // 数据点"风机已启动A8"对应的存储数据
    protected static boolean data_AHU08_fengJiYiQiDong;
    // 数据点"值班状态A8"对应的存储数据
    protected static boolean data_AHU08_zhiBanStatus;
    // 数据点"电加热1A8"对应的存储数据
    protected static boolean data_AHU08_dianJiaRe1;
    // 数据点"电加热2A8"对应的存储数据
    protected static boolean data_AHU08_dianJiaRe2;
    // 数据点"电加热3A8"对应的存储数据
    protected static boolean data_AHU08_dianJiaRe3;
    // 数据点"风机状态A8"对应的存储数据
    protected static boolean data_AHU08_fengJiStatus;
    // 数据点"手自动A8"对应的存储数据
    protected static boolean data_AHU08_shouZiDong;
    // 数据点"冬夏季A8"对应的存储数据
    protected static boolean data_AHU08_dongXiaJi;
    // 数据点"中效报警A8"对应的存储数据
    protected static boolean data_AHU08_zhongXiaoBaoJing;
    // 数据点"电加热高温A8"对应的存储数据
    protected static boolean data_AHU08_dianJiaReGaoWen;
    // 数据点"风机缺风A8"对应的存储数据
    protected static boolean data_AHU08_fengJiQueFeng;
    // 数据点"排风机已启动A8"对应的存储数据
    protected static boolean data_AHU08_paiFengJiYiQiDong;
    // 数据点"盘管低温A8"对应的存储数据
    protected static boolean data_AHU08_diWenPanGuan;
    // 数据点"灭菌运行A8"对应的存储数据
    protected static boolean data_AHU08_mieJunYunXing;
    // 数据点"风机已启动A9"对应的存储数据
    protected static boolean data_AHU09_fengJiYiQiDong;
    // 数据点"值班状态A9"对应的存储数据
    protected static boolean data_AHU09_zhiBanStatus;
    // 数据点"电加热1A9"对应的存储数据
    protected static boolean data_AHU09_dianJiaRe1;
    // 数据点"电加热2A9"对应的存储数据
    protected static boolean data_AHU09_dianJiaRe2;
    // 数据点"电加热3A9"对应的存储数据
    protected static boolean data_AHU09_dianJiaRe3;
    // 数据点"风机状态A9"对应的存储数据
    protected static boolean data_AHU09_fengJiStatus;
    // 数据点"手自动A9"对应的存储数据
    protected static boolean data_AHU09_shouZiDong;
    // 数据点"冬夏季A9"对应的存储数据
    protected static boolean data_AHU09_dongXiaJi;
    // 数据点"中效报警A9"对应的存储数据
    protected static boolean data_AHU09_zhongXiaoBaoJing;
    // 数据点"电加热高温A9"对应的存储数据
    protected static boolean data_AHU09_dianJiaReGaoWen;
    // 数据点"风机缺风A9"对应的存储数据
    protected static boolean data_AHU09_fengJiQueFeng;
    // 数据点"排风机已启动A9"对应的存储数据
    protected static boolean data_AHU09_paiFengJiYiQiDong;
    // 数据点"盘管低温A9"对应的存储数据
    protected static boolean data_AHU09_diWenPanGuan;
    // 数据点"灭菌运行A9"对应的存储数据
    protected static boolean data_AHU09_mieJunYunXing;
    // 数据点"风机已启动A10"对应的存储数据
    protected static boolean data_AHU10_fengJiYiQiDong;
    // 数据点"值班状态A10"对应的存储数据
    protected static boolean data_AHU10_zhiBanStatus;
    // 数据点"电加热1A10"对应的存储数据
    protected static boolean data_AHU10_dianJiaRe1;
    // 数据点"电加热2A10"对应的存储数据
    protected static boolean data_AHU10_dianJiaRe2;
    // 数据点"电加热3A10"对应的存储数据
    protected static boolean data_AHU10_dianJiaRe3;
    // 数据点"风机状态A10"对应的存储数据
    protected static boolean data_AHU10_fengJiStatus;
    // 数据点"手自动A10"对应的存储数据
    protected static boolean data_AHU10_shouZiDong;
    // 数据点"冬夏季A10"对应的存储数据
    protected static boolean data_AHU10_dongXiaJi;
    // 数据点"中效报警A10"对应的存储数据
    protected static boolean data_AHU10_zhongXiaoBaoJing;
    // 数据点"电加热高温A10"对应的存储数据
    protected static boolean data_AHU10_dianJiaReGaoWen;
    // 数据点"风机缺风A10"对应的存储数据
    protected static boolean data_AHU10_fengJiQueFeng;
    // 数据点"排风机已启动A10"对应的存储数据
    protected static boolean data_AHU10_paiFengJiYiQiDong;
    // 数据点"盘管低温A10"对应的存储数据
    protected static boolean data_AHU10_diWenPanGuan;
    // 数据点"灭菌运行A10"对应的存储数据
    protected static boolean data_AHU10_mieJunYunXing;
    // 数据点"风机已启动A11"对应的存储数据
    protected static boolean data_MAU01_fengJiYiQiDong;
    // 数据点"值班状态A11"对应的存储数据
    protected static boolean data_MAU01_zhiBanStatus;
    // 数据点"电预热1A11"对应的存储数据
    protected static boolean data_MAU01_dianJiaRe1;
    // 数据点"电预热2A11"对应的存储数据
    protected static boolean data_MAU01_dianJiaRe2;
    // 数据点"电预热3A11"对应的存储数据
    protected static boolean data_MAU01_dianJiaRe3;
    // 数据点"风机状态A11"对应的存储数据
    protected static boolean data_MAU01_fengJiStatus;
    // 数据点"手自动A11"对应的存储数据
    protected static boolean data_MAU01_shouZiDong;
    // 数据点"冬夏季A11"对应的存储数据
    protected static boolean data_MAU01_dongXiaJi;
    // 数据点"中效报警A11"对应的存储数据
    protected static boolean data_MAU01_zhongXiaoBaoJing;
    // 数据点"电预热高温A11"对应的存储数据
    protected static boolean data_MAU01_dianJiaReGaoWen;
    // 数据点"风机缺风A11"对应的存储数据
    protected static boolean data_MAU01_fengJiQueFeng;
    // 数据点"排风机已启动A11"对应的存储数据
    protected static boolean data_MAU01_paiFengJiYiQiDong;
    // 数据点"盘管低温A11"对应的存储数据
    protected static boolean data_MAU01_diWenPanGuan;
    // 数据点"灭菌运行A11"对应的存储数据
    protected static boolean data_MAU01_mieJunYunXing;
    // 数据点"风机已启动A12"对应的存储数据
    protected static boolean data_MAU02_fengJiYiQiDong;
    // 数据点"值班状态A12"对应的存储数据
    protected static boolean data_MAU02_zhiBanStatus;
    // 数据点"电预热1A12"对应的存储数据
    protected static boolean data_MAU02_dianJiaRe1;
    // 数据点"电预热2A12"对应的存储数据
    protected static boolean data_MAU02_dianJiaRe2;
    // 数据点"电预热3A12"对应的存储数据
    protected static boolean data_MAU02_dianJiaRe3;
    // 数据点"风机状态A12"对应的存储数据
    protected static boolean data_MAU02_fengJiStatus;
    // 数据点"手自动A12"对应的存储数据
    protected static boolean data_MAU02_shouZiDong;
    // 数据点"冬夏季A12"对应的存储数据
    protected static boolean data_MAU02_dongXiaJi;
    // 数据点"中效报警A12"对应的存储数据
    protected static boolean data_MAU02_zhongXiaoBaoJing;
    // 数据点"电预热高温A12"对应的存储数据
    protected static boolean data_MAU02_dianJiaReGaoWen;
    // 数据点"风机缺风A12"对应的存储数据
    protected static boolean data_MAU02_fengJiQueFeng;
    // 数据点"排风机已启动A12"对应的存储数据
    protected static boolean data_MAU02_paiFengJiYiQiDong;
    // 数据点"盘管低温A12"对应的存储数据
    protected static boolean data_MAU02_diWenPanGuan;
    // 数据点"灭菌运行A12"对应的存储数据
    protected static boolean data_MAU02_mieJunYunXing;
    // 数据点"风机已启动A13"对应的存储数据
    protected static boolean data_MAU03_fengJiYiQiDong;
    // 数据点"值班状态A13"对应的存储数据
    protected static boolean data_MAU03_zhiBanStatus;
    // 数据点"电预热1A13"对应的存储数据
    protected static boolean data_MAU03_dianJiaRe1;
    // 数据点"电预热2A13"对应的存储数据
    protected static boolean data_MAU03_dianJiaRe2;
    // 数据点"电预热3A13"对应的存储数据
    protected static boolean data_MAU03_dianJiaRe3;
    // 数据点"风机状态A13"对应的存储数据
    protected static boolean data_MAU03_fengJiStatus;
    // 数据点"手自动A13"对应的存储数据
    protected static boolean data_MAU03_shouZiDong;
    // 数据点"冬夏季A13"对应的存储数据
    protected static boolean data_MAU03_dongXiaJi;
    // 数据点"中效报警A13"对应的存储数据
    protected static boolean data_MAU03_zhongXiaoBaoJing;
    // 数据点"电预热高温A13"对应的存储数据
    protected static boolean data_MAU03_dianJiaReGaoWen;
    // 数据点"风机缺风A13"对应的存储数据
    protected static boolean data_MAU03_fengJiQueFeng;
    // 数据点"排风机已启动A13"对应的存储数据
    protected static boolean data_MAU03_paiFengJiYiQiDong;
    // 数据点"盘管低温A13"对应的存储数据
    protected static boolean data_MAU03_diWenPanGuan;
    // 数据点"灭菌运行A13"对应的存储数据
    protected static boolean data_MAU03_mieJunYunXing;
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
    protected static int data_AHU06_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A6"对应的存储数据
    protected static int data_AHU06_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A6"对应的存储数据
    protected static int data_AHU06_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A7"对应的存储数据
    protected static int data_AHU07_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A7"对应的存储数据
    protected static int data_AHU07_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A7"对应的存储数据
    protected static int data_AHU07_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A8"对应的存储数据
    protected static int data_AHU08_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A8"对应的存储数据
    protected static int data_AHU08_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A8"对应的存储数据
    protected static int data_AHU08_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A9"对应的存储数据
    protected static int data_AHU09_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A9"对应的存储数据
    protected static int data_AHU09_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A9"对应的存储数据
    protected static int data_AHU09_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A10"对应的存储数据
    protected static int data_AHU10_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A10"对应的存储数据
    protected static int data_AHU10_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A10"对应的存储数据
    protected static int data_AHU10_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A11"对应的存储数据
    protected static int data_MAU01_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A11"对应的存储数据
    protected static int data_MAU01_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A11"对应的存储数据
    protected static int data_MAU01_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A12"对应的存储数据
    protected static int data_MAU02_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A12"对应的存储数据
    protected static int data_MAU02_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A12"对应的存储数据
    protected static int data_MAU02_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A13"对应的存储数据
    protected static int data_MAU03_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A13"对应的存储数据
    protected static int data_MAU03_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A13"对应的存储数据
    protected static int data_MAU03_mianBanTongXunZhuangTai3;
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
    protected static int data_AHU06_tempReal;
    // 数据点"实时湿度A6"对应的存储数据
    protected static int data_AHU06_humiReal;
    // 数据点"温度设定值A6"对应的存储数据
    protected static int data_AHU06_tempSet;
    // 数据点"湿度设定值A6"对应的存储数据
    protected static int data_AHU06_humiSet;
    // 数据点"冷水阀开度A6"对应的存储数据
    protected static int data_AHU06_lengShuiFaKaiDu;
    // 数据点"热水阀开度A6"对应的存储数据
    protected static int data_AHU06_reShuiFaKaiDu;
    // 数据点"新风温度A6"对应的存储数据
    protected static int data_AHU06_xinFengWenDU;
    // 数据点"加湿器开度A6"对应的存储数据
    protected static int data_AHU06_jiaShiQIKaiDu;
    // 数据点"实时温度A7"对应的存储数据
    protected static int data_AHU07_tempReal;
    // 数据点"实时湿度A7"对应的存储数据
    protected static int data_AHU07_humiReal;
    // 数据点"温度设定值A7"对应的存储数据
    protected static int data_AHU07_tempSet;
    // 数据点"湿度设定值A7"对应的存储数据
    protected static int data_AHU07_humiSet;
    // 数据点"冷水阀开度A7"对应的存储数据
    protected static int data_AHU07_lengShuiFaKaiDu;
    // 数据点"热水阀开度A7"对应的存储数据
    protected static int data_AHU07_reShuiFaKaiDu;
    // 数据点"新风温度A7"对应的存储数据
    protected static int data_AHU07_xinFengWenDU;
    // 数据点"加湿器开度A7"对应的存储数据
    protected static int data_AHU07_jiaShiQIKaiDu;
    // 数据点"实时温度A8"对应的存储数据
    protected static int data_AHU08_tempReal;
    // 数据点"实时湿度A8"对应的存储数据
    protected static int data_AHU08_humiReal;
    // 数据点"温度设定值A8"对应的存储数据
    protected static int data_AHU08_tempSet;
    // 数据点"湿度设定值A8"对应的存储数据
    protected static int data_AHU08_humiSet;
    // 数据点"冷水阀开度A8"对应的存储数据
    protected static int data_AHU08_lengShuiFaKaiDu;
    // 数据点"热水阀开度A8"对应的存储数据
    protected static int data_AHU08_reShuiFaKaiDu;
    // 数据点"新风温度A8"对应的存储数据
    protected static int data_AHU08_xinFengWenDU;
    // 数据点"加湿器开度A8"对应的存储数据
    protected static int data_AHU08_jiaShiQIKaiDu;
    // 数据点"实时温度A9"对应的存储数据
    protected static int data_AHU09_tempReal;
    // 数据点"实时湿度A9"对应的存储数据
    protected static int data_AHU09_humiReal;
    // 数据点"温度设定值A9"对应的存储数据
    protected static int data_AHU09_tempSet;
    // 数据点"湿度设定值A9"对应的存储数据
    protected static int data_AHU09_humiSet;
    // 数据点"冷水阀开度A9"对应的存储数据
    protected static int data_AHU09_lengShuiFaKaiDu;
    // 数据点"热水阀开度A9"对应的存储数据
    protected static int data_AHU09_reShuiFaKaiDu;
    // 数据点"新风温度A9"对应的存储数据
    protected static int data_AHU09_xinFengWenDU;
    // 数据点"加湿器开度A9"对应的存储数据
    protected static int data_AHU09_jiaShiQIKaiDu;
    // 数据点"实时温度A10"对应的存储数据
    protected static int data_AHU10_tempReal;
    // 数据点"实时湿度A10"对应的存储数据
    protected static int data_AHU10_humiReal;
    // 数据点"温度设定值A10"对应的存储数据
    protected static int data_AHU10_tempSet;
    // 数据点"湿度设定值A10"对应的存储数据
    protected static int data_AHU10_humiSet;
    // 数据点"冷水阀开度A10"对应的存储数据
    protected static int data_AHU10_lengShuiFaKaiDu;
    // 数据点"热水阀开度A10"对应的存储数据
    protected static int data_AHU10_reShuiFaKaiDu;
    // 数据点"新风温度A10"对应的存储数据
    protected static int data_AHU10_xinFengWenDU;
    // 数据点"加湿器开度A10"对应的存储数据
    protected static int data_AHU10_jiaShiQIKaiDu;
    // 数据点"实时温度A11"对应的存储数据
    protected static int data_MAU01_tempReal;
    // 数据点"实时湿度A11"对应的存储数据
    protected static int data_MAU01_humiReal;
    // 数据点"温度设定值A11"对应的存储数据
    protected static int data_MAU01_tempSet;
    // 数据点"湿度设定值A11"对应的存储数据
    protected static int data_MAU01_humiSet;
    // 数据点"冷水阀开度A11"对应的存储数据
    protected static int data_MAU01_lengShuiFaKaiDu;
    // 数据点"热水阀开度A11"对应的存储数据
    protected static int data_MAU01_reShuiFaKaiDu;
    // 数据点"新风温度A11"对应的存储数据
    protected static int data_MAU01_xinFengWenDU;
    // 数据点"加湿器开度A11"对应的存储数据
    protected static int data_MAU01_jiaShiQIKaiDu;
    // 数据点"实时温度A12"对应的存储数据
    protected static int data_MAU02_tempReal;
    // 数据点"实时湿度A12"对应的存储数据
    protected static int data_MAU02_humiReal;
    // 数据点"温度设定值A12"对应的存储数据
    protected static int data_MAU02_tempSet;
    // 数据点"湿度设定值A12"对应的存储数据
    protected static int data_MAU02_humiSet;
    // 数据点"冷水阀开度A12"对应的存储数据
    protected static int data_MAU02_lengShuiFaKaiDu;
    // 数据点"热水阀开度A12"对应的存储数据
    protected static int data_MAU02_reShuiFaKaiDu;
    // 数据点"新风温度A12"对应的存储数据
    protected static int data_MAU02_xinFengWenDU;
    // 数据点"加湿器开度A12"对应的存储数据
    protected static int data_MAU02_jiaShiQIKaiDu;
    // 数据点"实时温度A13"对应的存储数据
    protected static int data_MAU03_tempReal;
    // 数据点"实时湿度A13"对应的存储数据
    protected static int data_MAU03_humiReal;
    // 数据点"温度设定值A13"对应的存储数据
    protected static int data_MAU03_tempSet;
    // 数据点"湿度设定值A13"对应的存储数据
    protected static int data_MAU03_humiSet;
    // 数据点"冷水阀开度A13"对应的存储数据
    protected static int data_MAU03_lengShuiFaKaiDu;
    // 数据点"热水阀开度A13"对应的存储数据
    protected static int data_MAU03_reShuiFaKaiDu;
    // 数据点"新风温度A13"对应的存储数据
    protected static int data_MAU03_xinFengWenDU;
    // 数据点"加湿器开度A13"对应的存储数据
    protected static int data_MAU03_jiaShiQIKaiDu;


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


    private TextView tv_data_AHU06_title;
    private TextView tv_data_AHU06_tempReal;
    private TextView tv_data_AHU06_humiReal;
    private TextView tv_data_AHU06_tempSet;
    private TextView tv_data_AHU06_humiSet;


    private TextView tv_data_AHU07_title;
    private TextView tv_data_AHU07_tempReal;
    private TextView tv_data_AHU07_humiReal;
    private TextView tv_data_AHU07_tempSet;
    private TextView tv_data_AHU07_humiSet;

    private TextView tv_data_AHU08_title;
    private TextView tv_data_AHU08_tempReal;
    private TextView tv_data_AHU08_humiReal;
    private TextView tv_data_AHU08_tempSet;
    private TextView tv_data_AHU08_humiSet;

    private TextView tv_data_AHU09_title;
    private TextView tv_data_AHU09_tempReal;
    private TextView tv_data_AHU09_humiReal;
    private TextView tv_data_AHU09_tempSet;
    private TextView tv_data_AHU09_humiSet;

    private TextView tv_data_AHU10_title;
    private TextView tv_data_AHU10_tempReal;
    private TextView tv_data_AHU10_humiReal;
    private TextView tv_data_AHU10_tempSet;
    private TextView tv_data_AHU10_humiSet;

    private TextView tv_data_MAU01_title;
    private TextView tv_data_MAU01_xinFengWenDU;
    private TextView tv_data_MAU01_tempReal;
    private TextView tv_data_MAU01_tempSet;

    private TextView tv_data_MAU02_title;
    private TextView tv_data_MAU02_xinFengWenDU;
    private TextView tv_data_MAU02_tempReal;
    private TextView tv_data_MAU02_tempSet;

    private TextView tv_data_MAU03_title;
    private TextView tv_data_MAU03_xinFengWenDU;
    private TextView tv_data_MAU03_tempReal;
    private TextView tv_data_MAU03_tempSet;



    private View ll_overview_ahu01;
    private View ll_overview_ahu02;
    private View ll_overview_ahu03;
    private View ll_overview_ahu04;
    private View ll_overview_ahu05;
    private View ll_overview_ahu06;
    private View ll_overview_ahu07;
    private View ll_overview_ahu08;
    private View ll_overview_ahu09;
    private View ll_overview_ahu10;
    private View ll_overview_mau01;
    private View ll_overview_mau02;
    private View ll_overview_mau03;

    private final String TAG = "krguang";
    private static final int CODE_TIANJINSANYUAN_HANDLER_UI = 109;
    private static final int AHU_HANDLER_UI = 106;

    private CustomApplication app;
    private Handler handler;


    /** The handler. */
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CODE_TIANJINSANYUAN_HANDLER_UI:
                    updateUI();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_tianjinsanyuan_activity);

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

        tv_data_AHU06_title = findViewById(R.id.tv_main_state_title_ahu06);
        tv_data_AHU06_tempReal = findViewById(R.id.tv_main_state_tempnow_ahu06);
        tv_data_AHU06_humiReal = findViewById(R.id.tv_main_state_huminow_ahu06);
        tv_data_AHU06_tempSet = findViewById(R.id.tv_main_state_tempset_ahu06);
        tv_data_AHU06_humiSet = findViewById(R.id.tv_main_state_humiset_ahu06);

        tv_data_AHU07_title = findViewById(R.id.tv_main_state_title_ahu07);
        tv_data_AHU07_tempReal = findViewById(R.id.tv_main_state_tempnow_ahu07);
        tv_data_AHU07_humiReal = findViewById(R.id.tv_main_state_huminow_ahu07);
        tv_data_AHU07_tempSet = findViewById(R.id.tv_main_state_tempset_ahu07);
        tv_data_AHU07_humiSet = findViewById(R.id.tv_main_state_humiset_ahu07);

        tv_data_AHU08_title = findViewById(R.id.tv_main_state_title_ahu08);
        tv_data_AHU08_tempReal = findViewById(R.id.tv_main_state_tempnow_ahu08);
        tv_data_AHU08_humiReal = findViewById(R.id.tv_main_state_huminow_ahu08);
        tv_data_AHU08_tempSet = findViewById(R.id.tv_main_state_tempset_ahu08);
        tv_data_AHU08_humiSet = findViewById(R.id.tv_main_state_humiset_ahu08);

        tv_data_AHU09_title = findViewById(R.id.tv_main_state_title_ahu09);
        tv_data_AHU09_tempReal = findViewById(R.id.tv_main_state_tempnow_ahu09);
        tv_data_AHU09_humiReal = findViewById(R.id.tv_main_state_huminow_ahu09);
        tv_data_AHU09_tempSet = findViewById(R.id.tv_main_state_tempset_ahu09);
        tv_data_AHU09_humiSet = findViewById(R.id.tv_main_state_humiset_ahu09);

        tv_data_AHU10_title = findViewById(R.id.tv_main_state_title_ahu10);
        tv_data_AHU10_tempReal = findViewById(R.id.tv_main_state_tempnow_ahu10);
        tv_data_AHU10_humiReal = findViewById(R.id.tv_main_state_huminow_ahu10);
        tv_data_AHU10_tempSet = findViewById(R.id.tv_main_state_tempset_ahu10);
        tv_data_AHU10_humiSet = findViewById(R.id.tv_main_state_humiset_ahu10);

        tv_data_MAU01_title = findViewById(R.id.tv_main_state_title_mau01);
        tv_data_MAU01_xinFengWenDU = findViewById(R.id.tv_main_state_temp_new_wind_mau01);
        tv_data_MAU01_tempReal = findViewById(R.id.tv_main_state_tempnow_mau01);
        tv_data_MAU01_tempSet = findViewById(R.id.tv_main_state_tempset_mau01);

        tv_data_MAU02_title = findViewById(R.id.tv_main_state_title_mau02);
        tv_data_MAU02_xinFengWenDU = findViewById(R.id.tv_main_state_temp_new_wind_mau02);
        tv_data_MAU02_tempReal = findViewById(R.id.tv_main_state_tempnow_mau02);
        tv_data_MAU02_tempSet = findViewById(R.id.tv_main_state_tempset_mau02);

        tv_data_MAU03_title = findViewById(R.id.tv_main_state_title_mau03);
        tv_data_MAU03_xinFengWenDU = findViewById(R.id.tv_main_state_temp_new_wind_mau03);
        tv_data_MAU03_tempReal = findViewById(R.id.tv_main_state_tempnow_mau03);
        tv_data_MAU03_tempSet = findViewById(R.id.tv_main_state_tempset_mau03);

        ll_overview_ahu01 = findViewById(R.id.ll_ahu01);
        ll_overview_ahu02 = findViewById(R.id.ll_ahu02);
        ll_overview_ahu03 = findViewById(R.id.ll_ahu03);
        ll_overview_ahu04 = findViewById(R.id.ll_ahu04);
        ll_overview_ahu05 = findViewById(R.id.ll_ahu05);
        ll_overview_ahu06 = findViewById(R.id.ll_ahu06);
        ll_overview_ahu07 = findViewById(R.id.ll_ahu07);
        ll_overview_ahu08 = findViewById(R.id.ll_ahu08);
        ll_overview_ahu09 = findViewById(R.id.ll_ahu09);
        ll_overview_ahu10 = findViewById(R.id.ll_ahu10);

        ll_overview_mau01 = findViewById(R.id.ll_mau01);
        ll_overview_mau02 = findViewById(R.id.ll_mau02);
        ll_overview_mau03 = findViewById(R.id.ll_mau03);
    }

    private void initEvent() {

        ll_overview_ahu01.setOnClickListener(this);
        ll_overview_ahu02.setOnClickListener(this);
        ll_overview_ahu03.setOnClickListener(this);
        ll_overview_ahu04.setOnClickListener(this);
        ll_overview_ahu05.setOnClickListener(this);
        ll_overview_ahu06.setOnClickListener(this);
        ll_overview_ahu07.setOnClickListener(this);
        ll_overview_ahu08.setOnClickListener(this);
        ll_overview_ahu09.setOnClickListener(this);
        ll_overview_ahu10.setOnClickListener(this);

        ll_overview_mau01.setOnClickListener(this);
        ll_overview_mau02.setOnClickListener(this);
        ll_overview_mau03.setOnClickListener(this);

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

        if (data_AHU06_fengJiQueFeng){
            tv_data_AHU06_title.setBackgroundColor(Color.RED);
        }else if (data_AHU06_fengJiStatus){
            tv_data_AHU06_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU06_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU07_fengJiQueFeng){
            tv_data_AHU07_title.setBackgroundColor(Color.RED);
        }else if (data_AHU07_fengJiStatus){
            tv_data_AHU07_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU07_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU08_fengJiQueFeng){
            tv_data_AHU08_title.setBackgroundColor(Color.RED);
        }else if (data_AHU08_fengJiStatus){
            tv_data_AHU08_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU08_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU09_fengJiQueFeng){
            tv_data_AHU09_title.setBackgroundColor(Color.RED);
        }else if (data_AHU09_fengJiStatus){
            tv_data_AHU09_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU09_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU10_fengJiQueFeng){
            tv_data_AHU10_title.setBackgroundColor(Color.RED);
        }else if (data_AHU10_fengJiStatus){
            tv_data_AHU10_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU10_title.setBackgroundColor(Color.GRAY);
        }


        if (data_MAU01_fengJiQueFeng){
            tv_data_MAU01_title.setBackgroundColor(Color.RED);
        }else if (data_MAU01_fengJiStatus){
            tv_data_MAU01_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_MAU01_title.setBackgroundColor(Color.GRAY);
        }

        if (data_MAU02_fengJiQueFeng){
            tv_data_MAU02_title.setBackgroundColor(Color.RED);
        }else if (data_MAU02_fengJiStatus){
            tv_data_MAU02_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_MAU02_title.setBackgroundColor(Color.GRAY);
        }

        if (data_MAU03_fengJiQueFeng){
            tv_data_MAU03_title.setBackgroundColor(Color.RED);
        }else if (data_MAU03_fengJiStatus){
            tv_data_MAU03_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_MAU03_title.setBackgroundColor(Color.GRAY);
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

        tv_data_AHU06_tempReal.setText(formatValue(data_AHU06_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU06_humiReal.setText(formatValue(data_AHU06_humiReal/10.0,0.1)+" RH");
        tv_data_AHU06_tempSet.setText(formatValue(data_AHU06_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU06_humiSet.setText(formatValue(data_AHU06_humiSet/10.0,0.1)+" RH");

        tv_data_AHU07_tempReal.setText(formatValue(data_AHU07_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU07_humiReal.setText(formatValue(data_AHU07_humiReal/10.0,0.1)+" RH");
        tv_data_AHU07_tempSet.setText(formatValue(data_AHU07_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU07_humiSet.setText(formatValue(data_AHU07_humiSet/10.0,0.1)+" RH");

        tv_data_AHU08_tempReal.setText(formatValue(data_AHU08_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU08_humiReal.setText(formatValue(data_AHU08_humiReal/10.0,0.1)+" RH");
        tv_data_AHU08_tempSet.setText(formatValue(data_AHU08_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU08_humiSet.setText(formatValue(data_AHU08_humiSet/10.0,0.1)+" RH");

        tv_data_AHU09_tempReal.setText(formatValue(data_AHU09_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU09_humiReal.setText(formatValue(data_AHU09_humiReal/10.0,0.1)+" RH");
        tv_data_AHU09_tempSet.setText(formatValue(data_AHU09_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU09_humiSet.setText(formatValue(data_AHU09_humiSet/10.0,0.1)+" RH");

        tv_data_AHU10_tempReal.setText(formatValue(data_AHU10_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU10_humiReal.setText(formatValue(data_AHU10_humiReal/10.0,0.1)+" RH");
        tv_data_AHU10_tempSet.setText(formatValue(data_AHU10_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU10_humiSet.setText(formatValue(data_AHU10_humiSet/10.0,0.1)+" RH");

        tv_data_MAU01_tempReal.setText(formatValue(data_MAU01_tempReal/10.0,0.1)+" ℃");
        tv_data_MAU01_tempSet.setText(formatValue(data_MAU01_tempSet/10.0,0.1)+" ℃");
        tv_data_MAU01_xinFengWenDU.setText(formatValue(data_MAU01_xinFengWenDU/10.0,0.1)+" ℃");

        tv_data_MAU02_tempReal.setText(formatValue(data_MAU02_tempReal/10.0,0.1)+" ℃");
        tv_data_MAU02_tempSet.setText(formatValue(data_MAU02_tempSet/10.0,0.1)+" ℃");
        tv_data_MAU02_xinFengWenDU.setText(formatValue(data_MAU02_xinFengWenDU/10.0,0.1)+" ℃");

        tv_data_MAU03_tempReal.setText(formatValue(data_MAU03_tempReal/10.0,0.1)+" ℃");
        tv_data_MAU03_tempSet.setText(formatValue(data_MAU03_tempSet/10.0,0.1)+" ℃");
        tv_data_MAU03_xinFengWenDU.setText(formatValue(data_MAU03_xinFengWenDU/10.0,0.1)+" ℃");

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
                if (dataKey.equals(KEY_AHU06_FENGJIYIQIDONG)) {
                    data_AHU06_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_ZHIBANSTATUS)) {
                    data_AHU06_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_DIANJIARE1)) {
                    data_AHU06_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_DIANJIARE2)) {
                    data_AHU06_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_DIANJIARE3)) {
                    data_AHU06_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_FENGJISTATUS)) {
                    data_AHU06_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_SHOUZIDONG)) {
                    data_AHU06_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_DONGXIAJI)) {
                    data_AHU06_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_ZHONGXIAOBAOJING)) {
                    data_AHU06_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_DIANJIAREGAOWEN)) {
                    data_AHU06_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_FENGJIQUEFENG)) {
                    data_AHU06_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_PAIFENGJIYIQIDONG)) {
                    data_AHU06_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_DIWENPANGUAN)) {
                    data_AHU06_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_MIEJUNYUNXING)) {
                    data_AHU06_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_FENGJIYIQIDONG)) {
                    data_AHU07_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_ZHIBANSTATUS)) {
                    data_AHU07_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_DIANJIARE1)) {
                    data_AHU07_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_DIANJIARE2)) {
                    data_AHU07_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_DIANJIARE3)) {
                    data_AHU07_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_FENGJISTATUS)) {
                    data_AHU07_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_SHOUZIDONG)) {
                    data_AHU07_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_DONGXIAJI)) {
                    data_AHU07_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_ZHONGXIAOBAOJING)) {
                    data_AHU07_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_DIANJIAREGAOWEN)) {
                    data_AHU07_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_FENGJIQUEFENG)) {
                    data_AHU07_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_PAIFENGJIYIQIDONG)) {
                    data_AHU07_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_DIWENPANGUAN)) {
                    data_AHU07_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_MIEJUNYUNXING)) {
                    data_AHU07_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_FENGJIYIQIDONG)) {
                    data_AHU08_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_ZHIBANSTATUS)) {
                    data_AHU08_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_DIANJIARE1)) {
                    data_AHU08_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_DIANJIARE2)) {
                    data_AHU08_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_DIANJIARE3)) {
                    data_AHU08_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_FENGJISTATUS)) {
                    data_AHU08_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_SHOUZIDONG)) {
                    data_AHU08_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_DONGXIAJI)) {
                    data_AHU08_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_ZHONGXIAOBAOJING)) {
                    data_AHU08_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_DIANJIAREGAOWEN)) {
                    data_AHU08_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_FENGJIQUEFENG)) {
                    data_AHU08_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_PAIFENGJIYIQIDONG)) {
                    data_AHU08_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_DIWENPANGUAN)) {
                    data_AHU08_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_MIEJUNYUNXING)) {
                    data_AHU08_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_FENGJIYIQIDONG)) {
                    data_AHU09_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_ZHIBANSTATUS)) {
                    data_AHU09_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_DIANJIARE1)) {
                    data_AHU09_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_DIANJIARE2)) {
                    data_AHU09_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_DIANJIARE3)) {
                    data_AHU09_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_FENGJISTATUS)) {
                    data_AHU09_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_SHOUZIDONG)) {
                    data_AHU09_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_DONGXIAJI)) {
                    data_AHU09_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_ZHONGXIAOBAOJING)) {
                    data_AHU09_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_DIANJIAREGAOWEN)) {
                    data_AHU09_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_FENGJIQUEFENG)) {
                    data_AHU09_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_PAIFENGJIYIQIDONG)) {
                    data_AHU09_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_DIWENPANGUAN)) {
                    data_AHU09_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_MIEJUNYUNXING)) {
                    data_AHU09_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_FENGJIYIQIDONG)) {
                    data_AHU10_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_ZHIBANSTATUS)) {
                    data_AHU10_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_DIANJIARE1)) {
                    data_AHU10_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_DIANJIARE2)) {
                    data_AHU10_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_DIANJIARE3)) {
                    data_AHU10_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_FENGJISTATUS)) {
                    data_AHU10_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_SHOUZIDONG)) {
                    data_AHU10_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_DONGXIAJI)) {
                    data_AHU10_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_ZHONGXIAOBAOJING)) {
                    data_AHU10_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_DIANJIAREGAOWEN)) {
                    data_AHU10_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_FENGJIQUEFENG)) {
                    data_AHU10_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_PAIFENGJIYIQIDONG)) {
                    data_AHU10_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_DIWENPANGUAN)) {
                    data_AHU10_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_MIEJUNYUNXING)) {
                    data_AHU10_mieJunYunXing = (Boolean) map.get(dataKey);
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
                if (dataKey.equals(KEY_MAU02_FENGJIYIQIDONG)) {
                    data_MAU02_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_ZHIBANSTATUS)) {
                    data_MAU02_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_DIANJIARE1)) {
                    data_MAU02_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_DIANJIARE2)) {
                    data_MAU02_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_DIANJIARE3)) {
                    data_MAU02_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_FENGJISTATUS)) {
                    data_MAU02_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_SHOUZIDONG)) {
                    data_MAU02_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_DONGXIAJI)) {
                    data_MAU02_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_ZHONGXIAOBAOJING)) {
                    data_MAU02_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_DIANJIAREGAOWEN)) {
                    data_MAU02_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_FENGJIQUEFENG)) {
                    data_MAU02_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_PAIFENGJIYIQIDONG)) {
                    data_MAU02_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_DIWENPANGUAN)) {
                    data_MAU02_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_MIEJUNYUNXING)) {
                    data_MAU02_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_FENGJIYIQIDONG)) {
                    data_MAU03_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_ZHIBANSTATUS)) {
                    data_MAU03_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_DIANJIARE1)) {
                    data_MAU03_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_DIANJIARE2)) {
                    data_MAU03_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_DIANJIARE3)) {
                    data_MAU03_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_FENGJISTATUS)) {
                    data_MAU03_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_SHOUZIDONG)) {
                    data_MAU03_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_DONGXIAJI)) {
                    data_MAU03_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_ZHONGXIAOBAOJING)) {
                    data_MAU03_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_DIANJIAREGAOWEN)) {
                    data_MAU03_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_FENGJIQUEFENG)) {
                    data_MAU03_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_PAIFENGJIYIQIDONG)) {
                    data_MAU03_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_DIWENPANGUAN)) {
                    data_MAU03_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_MIEJUNYUNXING)) {
                    data_MAU03_mieJunYunXing = (Boolean) map.get(dataKey);
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
                if (dataKey.equals(KEY_AHU06_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU06_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU06_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU06_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU07_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU07_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU07_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU08_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU08_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU08_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU09_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU09_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU09_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU10_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU10_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU10_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
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
                if (dataKey.equals(KEY_MAU02_MIANBANTONGXUNZHUANGTAI1)) {

                    data_MAU02_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_MIANBANTONGXUNZHUANGTAI2)) {

                    data_MAU02_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_MIANBANTONGXUNZHUANGTAI3)) {

                    data_MAU02_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_MIANBANTONGXUNZHUANGTAI1)) {

                    data_MAU03_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_MIANBANTONGXUNZHUANGTAI2)) {

                    data_MAU03_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_MIANBANTONGXUNZHUANGTAI3)) {

                    data_MAU03_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
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
                if (dataKey.equals(KEY_AHU06_TEMPREAL)) {

                    data_AHU06_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_HUMIREAL)) {

                    data_AHU06_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_TEMPSET)) {

                    data_AHU06_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_HUMISET)) {

                    data_AHU06_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_LENGSHUIFAKAIDU)) {

                    data_AHU06_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_RESHUIFAKAIDU)) {

                    data_AHU06_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_XINFENGWENDU)) {

                    data_AHU06_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU06_JIASHIQIKAIDU)) {

                    data_AHU06_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_TEMPREAL)) {

                    data_AHU07_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_HUMIREAL)) {

                    data_AHU07_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_TEMPSET)) {

                    data_AHU07_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_HUMISET)) {

                    data_AHU07_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_LENGSHUIFAKAIDU)) {

                    data_AHU07_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_RESHUIFAKAIDU)) {

                    data_AHU07_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_XINFENGWENDU)) {

                    data_AHU07_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU07_JIASHIQIKAIDU)) {

                    data_AHU07_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_TEMPREAL)) {

                    data_AHU08_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_HUMIREAL)) {

                    data_AHU08_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_TEMPSET)) {

                    data_AHU08_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_HUMISET)) {

                    data_AHU08_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_LENGSHUIFAKAIDU)) {

                    data_AHU08_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_RESHUIFAKAIDU)) {

                    data_AHU08_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_XINFENGWENDU)) {

                    data_AHU08_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU08_JIASHIQIKAIDU)) {

                    data_AHU08_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_TEMPREAL)) {

                    data_AHU09_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_HUMIREAL)) {

                    data_AHU09_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_TEMPSET)) {

                    data_AHU09_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_HUMISET)) {

                    data_AHU09_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_LENGSHUIFAKAIDU)) {

                    data_AHU09_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_RESHUIFAKAIDU)) {

                    data_AHU09_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_XINFENGWENDU)) {

                    data_AHU09_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU09_JIASHIQIKAIDU)) {

                    data_AHU09_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_TEMPREAL)) {

                    data_AHU10_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_HUMIREAL)) {

                    data_AHU10_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_TEMPSET)) {

                    data_AHU10_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_HUMISET)) {

                    data_AHU10_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_LENGSHUIFAKAIDU)) {

                    data_AHU10_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_RESHUIFAKAIDU)) {

                    data_AHU10_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_XINFENGWENDU)) {

                    data_AHU10_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU10_JIASHIQIKAIDU)) {

                    data_AHU10_jiaShiQIKaiDu = (Integer) map.get(dataKey);
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
                if (dataKey.equals(KEY_MAU02_TEMPREAL)) {

                    data_MAU02_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_HUMIREAL)) {

                    data_MAU02_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_TEMPSET)) {

                    data_MAU02_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_HUMISET)) {

                    data_MAU02_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_LENGSHUIFAKAIDU)) {

                    data_MAU02_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_RESHUIFAKAIDU)) {

                    data_MAU02_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_XINFENGWENDU)) {

                    data_MAU02_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU02_JIASHIQIKAIDU)) {

                    data_MAU02_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_TEMPREAL)) {

                    data_MAU03_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_HUMIREAL)) {

                    data_MAU03_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_TEMPSET)) {

                    data_MAU03_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_HUMISET)) {

                    data_MAU03_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_LENGSHUIFAKAIDU)) {

                    data_MAU03_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_RESHUIFAKAIDU)) {

                    data_MAU03_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_XINFENGWENDU)) {

                    data_MAU03_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_MAU03_JIASHIQIKAIDU)) {

                    data_MAU03_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }

            }
        }

        mHandler.sendEmptyMessage(CODE_TIANJINSANYUAN_HANDLER_UI);
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
        mDevice.setSubscribe(ConstantUtil.TIANJINSANYUAN_PRODUCT_SECRET, false);
    }

    private void startActivityWithStringAHU(String data){

        Bundle bundle = new Bundle();
        bundle.putParcelable("mDevice",mDevice);

        Intent intent = new Intent(UnitTianJinSanYuanActivity.this, AhuDeviceDataActivity.class);
        intent.putExtra("extra_data",data);
        Log.d(TAG, "startActivityWithStringAHU: 要传出去的device = "+mDevice);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void startActivityWithStringMAU(String data){

        Bundle bundle = new Bundle();
        bundle.putParcelable("GizWifiDevice",mDevice);

        Intent intent = new Intent(UnitTianJinSanYuanActivity.this,PauDeviceDataActivity.class);
        intent.putExtra("extra_data",data);
        intent.putExtras(bundle);
        startActivity(intent);
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

            case R.id.ll_ahu06:
                startActivityWithStringAHU("AHU06");
                break;

            case R.id.ll_ahu07:
                startActivityWithStringAHU("AHU07");
                break;

            case R.id.ll_ahu08:
                startActivityWithStringAHU("AHU08");
                break;

            case R.id.ll_ahu09:
                startActivityWithStringAHU("AHU09");
                break;

            case R.id.ll_ahu10:
                startActivityWithStringAHU("AHU10");
                break;

            case R.id.ll_mau01:
                startActivityWithStringMAU("MAU01");
                break;

            case R.id.ll_mau02:
                startActivityWithStringMAU("MAU02");
                break;

            case R.id.ll_mau03:
                startActivityWithStringMAU("MAU03");
                break;

            default:
                break;
        }

    }
}
