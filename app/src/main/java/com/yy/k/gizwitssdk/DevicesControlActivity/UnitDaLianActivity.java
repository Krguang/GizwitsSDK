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

public class UnitDaLianActivity extends BaseDevicesControlActivity implements View.OnClickListener {

    /*
     * ===========================================================
     * 以下key值对应开发者在云端定义的数据点标识名
     * ===========================================================
     */
    // 数据点"风机已启动A1"对应的标识名
    protected static final String KEY_AHU301_FENGJIYIQIDONG = "AHU301_fengJiYiQiDong";
    // 数据点"值班状态A1"对应的标识名
    protected static final String KEY_AHU301_ZHIBANSTATUS = "AHU301_zhiBanStatus";
    // 数据点"电加热1A1"对应的标识名
    protected static final String KEY_AHU301_DIANJIARE1 = "AHU301_dianJiaRe1";
    // 数据点"电加热2A1"对应的标识名
    protected static final String KEY_AHU301_DIANJIARE2 = "AHU301_dianJiaRe2";
    // 数据点"电加热3A1"对应的标识名
    protected static final String KEY_AHU301_DIANJIARE3 = "AHU301_dianJiaRe3";
    // 数据点"风机状态A1"对应的标识名
    protected static final String KEY_AHU301_FENGJISTATUS = "AHU301_fengJiStatus";
    // 数据点"手自动A1"对应的标识名
    protected static final String KEY_AHU301_SHOUZIDONG = "AHU301_shouZiDong";
    // 数据点"冬夏季A1"对应的标识名
    protected static final String KEY_AHU301_DONGXIAJI = "AHU301_dongXiaJi";
    // 数据点"中效报警A1"对应的标识名
    protected static final String KEY_AHU301_ZHONGXIAOBAOJING = "AHU301_zhongXiaoBaoJing";
    // 数据点"电加热高温A1"对应的标识名
    protected static final String KEY_AHU301_DIANJIAREGAOWEN = "AHU301_dianJiaReGaoWen";
    // 数据点"风机缺风A1"对应的标识名
    protected static final String KEY_AHU301_FENGJIQUEFENG = "AHU301_fengJiQueFeng";
    // 数据点"排风机已启动A1"对应的标识名
    protected static final String KEY_AHU301_PAIFENGJIYIQIDONG = "AHU301_paiFengJiYiQiDong";
    // 数据点"盘管低温A1"对应的标识名
    protected static final String KEY_AHU301_DIWENPANGUAN = "AHU301_diWenPanGuan";
    // 数据点"灭菌运行A1"对应的标识名
    protected static final String KEY_AHU301_MIEJUNYUNXING = "AHU301_mieJunYunXing";
    // 数据点"风机已启动A2"对应的标识名
    protected static final String KEY_AHU302_FENGJIYIQIDONG = "AHU302_fengJiYiQiDong";
    // 数据点"值班状态A2"对应的标识名
    protected static final String KEY_AHU302_ZHIBANSTATUS = "AHU302_zhiBanStatus";
    // 数据点"电加热1A2"对应的标识名
    protected static final String KEY_AHU302_DIANJIARE1 = "AHU302_dianJiaRe1";
    // 数据点"电加热2A2"对应的标识名
    protected static final String KEY_AHU302_DIANJIARE2 = "AHU302_dianJiaRe2";
    // 数据点"电加热3A2"对应的标识名
    protected static final String KEY_AHU302_DIANJIARE3 = "AHU302_dianJiaRe3";
    // 数据点"风机状态A2"对应的标识名
    protected static final String KEY_AHU302_FENGJISTATUS = "AHU302_fengJiStatus";
    // 数据点"手自动A2"对应的标识名
    protected static final String KEY_AHU302_SHOUZIDONG = "AHU302_shouZiDong";
    // 数据点"冬夏季A2"对应的标识名
    protected static final String KEY_AHU302_DONGXIAJI = "AHU302_dongXiaJi";
    // 数据点"中效报警A2"对应的标识名
    protected static final String KEY_AHU302_ZHONGXIAOBAOJING = "AHU302_zhongXiaoBaoJing";
    // 数据点"电加热高温A2"对应的标识名
    protected static final String KEY_AHU302_DIANJIAREGAOWEN = "AHU302_dianJiaReGaoWen";
    // 数据点"风机缺风A2"对应的标识名
    protected static final String KEY_AHU302_FENGJIQUEFENG = "AHU302_fengJiQueFeng";
    // 数据点"排风机已启动A2"对应的标识名
    protected static final String KEY_AHU302_PAIFENGJIYIQIDONG = "AHU302_paiFengJiYiQiDong";
    // 数据点"盘管低温A2"对应的标识名
    protected static final String KEY_AHU302_DIWENPANGUAN = "AHU302_diWenPanGuan";
    // 数据点"灭菌运行A2"对应的标识名
    protected static final String KEY_AHU302_MIEJUNYUNXING = "AHU302_mieJunYunXing";
    // 数据点"风机已启动A3"对应的标识名
    protected static final String KEY_AHU303_FENGJIYIQIDONG = "AHU303_fengJiYiQiDong";
    // 数据点"值班状态A3"对应的标识名
    protected static final String KEY_AHU303_ZHIBANSTATUS = "AHU303_zhiBanStatus";
    // 数据点"电加热1A3"对应的标识名
    protected static final String KEY_AHU303_DIANJIARE1 = "AHU303_dianJiaRe1";
    // 数据点"电加热2A3"对应的标识名
    protected static final String KEY_AHU303_DIANJIARE2 = "AHU303_dianJiaRe2";
    // 数据点"电加热3A3"对应的标识名
    protected static final String KEY_AHU303_DIANJIARE3 = "AHU303_dianJiaRe3";
    // 数据点"风机状态A3"对应的标识名
    protected static final String KEY_AHU303_FENGJISTATUS = "AHU303_fengJiStatus";
    // 数据点"手自动A3"对应的标识名
    protected static final String KEY_AHU303_SHOUZIDONG = "AHU303_shouZiDong";
    // 数据点"冬夏季A3"对应的标识名
    protected static final String KEY_AHU303_DONGXIAJI = "AHU303_dongXiaJi";
    // 数据点"中效报警A3"对应的标识名
    protected static final String KEY_AHU303_ZHONGXIAOBAOJING = "AHU303_zhongXiaoBaoJing";
    // 数据点"电加热高温A3"对应的标识名
    protected static final String KEY_AHU303_DIANJIAREGAOWEN = "AHU303_dianJiaReGaoWen";
    // 数据点"风机缺风A3"对应的标识名
    protected static final String KEY_AHU303_FENGJIQUEFENG = "AHU303_fengJiQueFeng";
    // 数据点"排风机已启动A3"对应的标识名
    protected static final String KEY_AHU303_PAIFENGJIYIQIDONG = "AHU303_paiFengJiYiQiDong";
    // 数据点"盘管低温A3"对应的标识名
    protected static final String KEY_AHU303_DIWENPANGUAN = "AHU303_diWenPanGuan";
    // 数据点"灭菌运行A3"对应的标识名
    protected static final String KEY_AHU303_MIEJUNYUNXING = "AHU303_mieJunYunXing";
    // 数据点"风机已启动A4"对应的标识名
    protected static final String KEY_AHU304_FENGJIYIQIDONG = "AHU304_fengJiYiQiDong";
    // 数据点"值班状态A4"对应的标识名
    protected static final String KEY_AHU304_ZHIBANSTATUS = "AHU304_zhiBanStatus";
    // 数据点"电加热1A4"对应的标识名
    protected static final String KEY_AHU304_DIANJIARE1 = "AHU304_dianJiaRe1";
    // 数据点"电加热2A4"对应的标识名
    protected static final String KEY_AHU304_DIANJIARE2 = "AHU304_dianJiaRe2";
    // 数据点"电加热3A4"对应的标识名
    protected static final String KEY_AHU304_DIANJIARE3 = "AHU304_dianJiaRe3";
    // 数据点"风机状态A4"对应的标识名
    protected static final String KEY_AHU304_FENGJISTATUS = "AHU304_fengJiStatus";
    // 数据点"手自动A4"对应的标识名
    protected static final String KEY_AHU304_SHOUZIDONG = "AHU304_shouZiDong";
    // 数据点"冬夏季A4"对应的标识名
    protected static final String KEY_AHU304_DONGXIAJI = "AHU304_dongXiaJi";
    // 数据点"中效报警A4"对应的标识名
    protected static final String KEY_AHU304_ZHONGXIAOBAOJING = "AHU304_zhongXiaoBaoJing";
    // 数据点"电加热高温A4"对应的标识名
    protected static final String KEY_AHU304_DIANJIAREGAOWEN = "AHU304_dianJiaReGaoWen";
    // 数据点"风机缺风A4"对应的标识名
    protected static final String KEY_AHU304_FENGJIQUEFENG = "AHU304_fengJiQueFeng";
    // 数据点"排风机已启动A4"对应的标识名
    protected static final String KEY_AHU304_PAIFENGJIYIQIDONG = "AHU304_paiFengJiYiQiDong";
    // 数据点"盘管低温A4"对应的标识名
    protected static final String KEY_AHU304_DIWENPANGUAN = "AHU304_diWenPanGuan";
    // 数据点"灭菌运行A4"对应的标识名
    protected static final String KEY_AHU304_MIEJUNYUNXING = "AHU304_mieJunYunXing";
    // 数据点"风机已启动A5"对应的标识名
    protected static final String KEY_AHU305_FENGJIYIQIDONG = "AHU305_fengJiYiQiDong";
    // 数据点"值班状态A5"对应的标识名
    protected static final String KEY_AHU305_ZHIBANSTATUS = "AHU305_zhiBanStatus";
    // 数据点"电加热1A5"对应的标识名
    protected static final String KEY_AHU305_DIANJIARE1 = "AHU305_dianJiaRe1";
    // 数据点"电加热2A5"对应的标识名
    protected static final String KEY_AHU305_DIANJIARE2 = "AHU305_dianJiaRe2";
    // 数据点"电加热3A5"对应的标识名
    protected static final String KEY_AHU305_DIANJIARE3 = "AHU305_dianJiaRe3";
    // 数据点"风机状态A5"对应的标识名
    protected static final String KEY_AHU305_FENGJISTATUS = "AHU305_fengJiStatus";
    // 数据点"手自动A5"对应的标识名
    protected static final String KEY_AHU305_SHOUZIDONG = "AHU305_shouZiDong";
    // 数据点"冬夏季A5"对应的标识名
    protected static final String KEY_AHU305_DONGXIAJI = "AHU305_dongXiaJi";
    // 数据点"中效报警A5"对应的标识名
    protected static final String KEY_AHU305_ZHONGXIAOBAOJING = "AHU305_zhongXiaoBaoJing";
    // 数据点"电加热高温A5"对应的标识名
    protected static final String KEY_AHU305_DIANJIAREGAOWEN = "AHU305_dianJiaReGaoWen";
    // 数据点"风机缺风A5"对应的标识名
    protected static final String KEY_AHU305_FENGJIQUEFENG = "AHU305_fengJiQueFeng";
    // 数据点"排风机已启动A5"对应的标识名
    protected static final String KEY_AHU305_PAIFENGJIYIQIDONG = "AHU305_paiFengJiYiQiDong";
    // 数据点"盘管低温A5"对应的标识名
    protected static final String KEY_AHU305_DIWENPANGUAN = "AHU305_diWenPanGuan";
    // 数据点"灭菌运行A5"对应的标识名
    protected static final String KEY_AHU305_MIEJUNYUNXING = "AHU305_mieJunYunXing";
    // 数据点"风机已启动A6"对应的标识名
    protected static final String KEY_AHU306_FENGJIYIQIDONG = "AHU306_fengJiYiQiDong";
    // 数据点"值班状态A6"对应的标识名
    protected static final String KEY_AHU306_ZHIBANSTATUS = "AHU306_zhiBanStatus";
    // 数据点"电加热1A6"对应的标识名
    protected static final String KEY_AHU306_DIANJIARE1 = "AHU306_dianJiaRe1";
    // 数据点"电加热2A6"对应的标识名
    protected static final String KEY_AHU306_DIANJIARE2 = "AHU306_dianJiaRe2";
    // 数据点"电加热3A6"对应的标识名
    protected static final String KEY_AHU306_DIANJIARE3 = "AHU306_dianJiaRe3";
    // 数据点"风机状态A6"对应的标识名
    protected static final String KEY_AHU306_FENGJISTATUS = "AHU306_fengJiStatus";
    // 数据点"手自动A6"对应的标识名
    protected static final String KEY_AHU306_SHOUZIDONG = "AHU306_shouZiDong";
    // 数据点"冬夏季A6"对应的标识名
    protected static final String KEY_AHU306_DONGXIAJI = "AHU306_dongXiaJi";
    // 数据点"中效报警A6"对应的标识名
    protected static final String KEY_AHU306_ZHONGXIAOBAOJING = "AHU306_zhongXiaoBaoJing";
    // 数据点"电加热高温A6"对应的标识名
    protected static final String KEY_AHU306_DIANJIAREGAOWEN = "AHU306_dianJiaReGaoWen";
    // 数据点"风机缺风A6"对应的标识名
    protected static final String KEY_AHU306_FENGJIQUEFENG = "AHU306_fengJiQueFeng";
    // 数据点"排风机已启动A6"对应的标识名
    protected static final String KEY_AHU306_PAIFENGJIYIQIDONG = "AHU306_paiFengJiYiQiDong";
    // 数据点"盘管低温A6"对应的标识名
    protected static final String KEY_AHU306_DIWENPANGUAN = "AHU306_diWenPanGuan";
    // 数据点"灭菌运行A6"对应的标识名
    protected static final String KEY_AHU306_MIEJUNYUNXING = "AHU306_mieJunYunXing";
    // 数据点"风机已启动A7"对应的标识名
    protected static final String KEY_AHU307_FENGJIYIQIDONG = "AHU307_fengJiYiQiDong";
    // 数据点"值班状态A7"对应的标识名
    protected static final String KEY_AHU307_ZHIBANSTATUS = "AHU307_zhiBanStatus";
    // 数据点"电加热1A7"对应的标识名
    protected static final String KEY_AHU307_DIANJIARE1 = "AHU307_dianJiaRe1";
    // 数据点"电加热2A7"对应的标识名
    protected static final String KEY_AHU307_DIANJIARE2 = "AHU307_dianJiaRe2";
    // 数据点"电加热3A7"对应的标识名
    protected static final String KEY_AHU307_DIANJIARE3 = "AHU307_dianJiaRe3";
    // 数据点"风机状态A7"对应的标识名
    protected static final String KEY_AHU307_FENGJISTATUS = "AHU307_fengJiStatus";
    // 数据点"手自动A7"对应的标识名
    protected static final String KEY_AHU307_SHOUZIDONG = "AHU307_shouZiDong";
    // 数据点"冬夏季A7"对应的标识名
    protected static final String KEY_AHU307_DONGXIAJI = "AHU307_dongXiaJi";
    // 数据点"中效报警A7"对应的标识名
    protected static final String KEY_AHU307_ZHONGXIAOBAOJING = "AHU307_zhongXiaoBaoJing";
    // 数据点"电加热高温A7"对应的标识名
    protected static final String KEY_AHU307_DIANJIAREGAOWEN = "AHU307_dianJiaReGaoWen";
    // 数据点"风机缺风A7"对应的标识名
    protected static final String KEY_AHU307_FENGJIQUEFENG = "AHU307_fengJiQueFeng";
    // 数据点"排风机已启动A7"对应的标识名
    protected static final String KEY_AHU307_PAIFENGJIYIQIDONG = "AHU307_paiFengJiYiQiDong";
    // 数据点"盘管低温A7"对应的标识名
    protected static final String KEY_AHU307_DIWENPANGUAN = "AHU307_diWenPanGuan";
    // 数据点"灭菌运行A7"对应的标识名
    protected static final String KEY_AHU307_MIEJUNYUNXING = "AHU307_mieJunYunXing";
    // 数据点"风机已启动A8"对应的标识名
    protected static final String KEY_AHU308_FENGJIYIQIDONG = "AHU308_fengJiYiQiDong";
    // 数据点"值班状态A8"对应的标识名
    protected static final String KEY_AHU308_ZHIBANSTATUS = "AHU308_zhiBanStatus";
    // 数据点"电加热1A8"对应的标识名
    protected static final String KEY_AHU308_DIANJIARE1 = "AHU308_dianJiaRe1";
    // 数据点"电加热2A8"对应的标识名
    protected static final String KEY_AHU308_DIANJIARE2 = "AHU308_dianJiaRe2";
    // 数据点"电加热3A8"对应的标识名
    protected static final String KEY_AHU308_DIANJIARE3 = "AHU308_dianJiaRe3";
    // 数据点"风机状态A8"对应的标识名
    protected static final String KEY_AHU308_FENGJISTATUS = "AHU308_fengJiStatus";
    // 数据点"手自动A8"对应的标识名
    protected static final String KEY_AHU308_SHOUZIDONG = "AHU308_shouZiDong";
    // 数据点"冬夏季A8"对应的标识名
    protected static final String KEY_AHU308_DONGXIAJI = "AHU308_dongXiaJi";
    // 数据点"中效报警A8"对应的标识名
    protected static final String KEY_AHU308_ZHONGXIAOBAOJING = "AHU308_zhongXiaoBaoJing";
    // 数据点"电加热高温A8"对应的标识名
    protected static final String KEY_AHU308_DIANJIAREGAOWEN = "AHU308_dianJiaReGaoWen";
    // 数据点"风机缺风A8"对应的标识名
    protected static final String KEY_AHU308_FENGJIQUEFENG = "AHU308_fengJiQueFeng";
    // 数据点"排风机已启动A8"对应的标识名
    protected static final String KEY_AHU308_PAIFENGJIYIQIDONG = "AHU308_paiFengJiYiQiDong";
    // 数据点"盘管低温A8"对应的标识名
    protected static final String KEY_AHU308_DIWENPANGUAN = "AHU308_diWenPanGuan";
    // 数据点"灭菌运行A8"对应的标识名
    protected static final String KEY_AHU308_MIEJUNYUNXING = "AHU308_mieJunYunXing";
    // 数据点"风机已启动P1"对应的标识名
    protected static final String KEY_PAU301_FENGJIYIQIDONG = "PAU301_fengJiYiQiDong";
    // 数据点"值班状态P1"对应的标识名
    protected static final String KEY_PAU301_ZHIBANSTATUS = "PAU301_zhiBanStatus";
    // 数据点"电加热1P1"对应的标识名
    protected static final String KEY_PAU301_DIANJIARE1 = "PAU301_dianJiaRe1";
    // 数据点"电加热2P1"对应的标识名
    protected static final String KEY_PAU301_DIANJIARE2 = "PAU301_dianJiaRe2";
    // 数据点"电加热3P1"对应的标识名
    protected static final String KEY_PAU301_DIANJIARE3 = "PAU301_dianJiaRe3";
    // 数据点"风机状态P1"对应的标识名
    protected static final String KEY_PAU301_FENGJISTATUS = "PAU301_fengJiStatus";
    // 数据点"手自动P1"对应的标识名
    protected static final String KEY_PAU301_SHOUZIDONG = "PAU301_shouZiDong";
    // 数据点"冬夏季P1"对应的标识名
    protected static final String KEY_PAU301_DONGXIAJI = "PAU301_dongXiaJi";
    // 数据点"中效报警P1"对应的标识名
    protected static final String KEY_PAU301_ZHONGXIAOBAOJING = "PAU301_zhongXiaoBaoJing";
    // 数据点"电加热高温P1"对应的标识名
    protected static final String KEY_PAU301_DIANJIAREGAOWEN = "PAU301_dianJiaReGaoWen";
    // 数据点"风机缺风P1"对应的标识名
    protected static final String KEY_PAU301_FENGJIQUEFENG = "PAU301_fengJiQueFeng";
    // 数据点"排风机已启动P1"对应的标识名
    protected static final String KEY_PAU301_PAIFENGJIYIQIDONG = "PAU301_paiFengJiYiQiDong";
    // 数据点"电预热1P1"对应的标识名
    protected static final String KEY_PAU301_DIANYURE1 = "PAU301_dianYuRe1";
    // 数据点"电预热2P1"对应的标识名
    protected static final String KEY_PAU301_DIANYURE2 = "PAU301_dianYuRe2";
    // 数据点"电预热3P1"对应的标识名
    protected static final String KEY_PAU301_DIANYURE3 = "PAU301_dianYuRe3";
    // 数据点"电预热高温P1"对应的标识名
    protected static final String KEY_PAU301_DIANYUREGAOWEN = "PAU301_dianYuReGaoWen";
    // 数据点"盘管低温P1"对应的标识名
    protected static final String KEY_PAU301_DIWENPANGUAN = "PAU301_diWenPanGuan";
    // 数据点"灭菌运行P1"对应的标识名
    protected static final String KEY_PAU301_MIEJUNYUNXING = "PAU301_mieJunYunXing";
    // 数据点"风机已启动P2"对应的标识名
    protected static final String KEY_PAU302_FENGJIYIQIDONG = "PAU302_fengJiYiQiDong";
    // 数据点"值班状态P2"对应的标识名
    protected static final String KEY_PAU302_ZHIBANSTATUS = "PAU302_zhiBanStatus";
    // 数据点"风机状态P2"对应的标识名
    protected static final String KEY_PAU302_FENGJISTATUS = "PAU302_fengJiStatus";
    // 数据点"手自动P2"对应的标识名
    protected static final String KEY_PAU302_SHOUZIDONG = "PAU302_shouZiDong";
    // 数据点"冬夏季P2"对应的标识名
    protected static final String KEY_PAU302_DONGXIAJI = "PAU302_dongXiaJi";
    // 数据点"中效报警P2"对应的标识名
    protected static final String KEY_PAU302_ZHONGXIAOBAOJING = "PAU302_zhongXiaoBaoJing";
    // 数据点"风机缺风P2"对应的标识名
    protected static final String KEY_PAU302_FENGJIQUEFENG = "PAU302_fengJiQueFeng";
    // 数据点"排风机已启动P2"对应的标识名
    protected static final String KEY_PAU302_PAIFENGJIYIQIDONG = "PAU302_paiFengJiYiQiDong";
    // 数据点"电预热1P2"对应的标识名
    protected static final String KEY_PAU302_DIANYURE1 = "PAU302_dianYuRe1";
    // 数据点"电预热2P2"对应的标识名
    protected static final String KEY_PAU302_DIANYURE2 = "PAU302_dianYuRe2";
    // 数据点"电预热3P2"对应的标识名
    protected static final String KEY_PAU302_DIANYURE3 = "PAU302_dianYuRe3";
    // 数据点"电预热高温P2"对应的标识名
    protected static final String KEY_PAU302_DIANYUREGAOWEN = "PAU302_dianYuReGaoWen";
    // 数据点"盘管低温P2"对应的标识名
    protected static final String KEY_PAU302_DIWENPANGUAN = "PAU302_diWenPanGuan";
    // 数据点"灭菌运行P2"对应的标识名
    protected static final String KEY_PAU302_MIEJUNYUNXING = "PAU302_mieJunYunXing";
    // 数据点"风机已启动P3"对应的标识名
    protected static final String KEY_PAU303_FENGJIYIQIDONG = "PAU303_fengJiYiQiDong";
    // 数据点"值班状态P3"对应的标识名
    protected static final String KEY_PAU303_ZHIBANSTATUS = "PAU303_zhiBanStatus";
    // 数据点"风机状态P3"对应的标识名
    protected static final String KEY_PAU303_FENGJISTATUS = "PAU303_fengJiStatus";
    // 数据点"手自动P3"对应的标识名
    protected static final String KEY_PAU303_SHOUZIDONG = "PAU303_shouZiDong";
    // 数据点"冬夏季P3"对应的标识名
    protected static final String KEY_PAU303_DONGXIAJI = "PAU303_dongXiaJi";
    // 数据点"中效报警P3"对应的标识名
    protected static final String KEY_PAU303_ZHONGXIAOBAOJING = "PAU303_zhongXiaoBaoJing";
    // 数据点"风机缺风P3"对应的标识名
    protected static final String KEY_PAU303_FENGJIQUEFENG = "PAU303_fengJiQueFeng";
    // 数据点"排风机已启动P3"对应的标识名
    protected static final String KEY_PAU303_PAIFENGJIYIQIDONG = "PAU303_paiFengJiYiQiDong";
    // 数据点"电预热1P3"对应的标识名
    protected static final String KEY_PAU303_DIANYURE1 = "PAU303_dianYuRe1";
    // 数据点"电预热2P3"对应的标识名
    protected static final String KEY_PAU303_DIANYURE2 = "PAU303_dianYuRe2";
    // 数据点"电预热3P3"对应的标识名
    protected static final String KEY_PAU303_DIANYURE3 = "PAU303_dianYuRe3";
    // 数据点"电预热高温P3"对应的标识名
    protected static final String KEY_PAU303_DIANYUREGAOWEN = "PAU303_dianYuReGaoWen";
    // 数据点"盘管低温P3"对应的标识名
    protected static final String KEY_PAU303_DIWENPANGUAN = "PAU303_diWenPanGuan";
    // 数据点"灭菌运行P3"对应的标识名
    protected static final String KEY_PAU303_MIEJUNYUNXING = "PAU303_mieJunYunXing";
    // 数据点"风机已启动P4"对应的标识名
    protected static final String KEY_PAU304_FENGJIYIQIDONG = "PAU304_fengJiYiQiDong";
    // 数据点"值班状态P4"对应的标识名
    protected static final String KEY_PAU304_ZHIBANSTATUS = "PAU304_zhiBanStatus";
    // 数据点"风机状态P4"对应的标识名
    protected static final String KEY_PAU304_FENGJISTATUS = "PAU304_fengJiStatus";
    // 数据点"手自动P4"对应的标识名
    protected static final String KEY_PAU304_SHOUZIDONG = "PAU304_shouZiDong";
    // 数据点"冬夏季P4"对应的标识名
    protected static final String KEY_PAU304_DONGXIAJI = "PAU304_dongXiaJi";
    // 数据点"中效报警P4"对应的标识名
    protected static final String KEY_PAU304_ZHONGXIAOBAOJING = "PAU304_zhongXiaoBaoJing";
    // 数据点"风机缺风P4"对应的标识名
    protected static final String KEY_PAU304_FENGJIQUEFENG = "PAU304_fengJiQueFeng";
    // 数据点"排风机已启动P4"对应的标识名
    protected static final String KEY_PAU304_PAIFENGJIYIQIDONG = "PAU304_paiFengJiYiQiDong";
    // 数据点"电预热1P4"对应的标识名
    protected static final String KEY_PAU304_DIANYURE1 = "PAU304_dianYuRe1";
    // 数据点"电预热2P4"对应的标识名
    protected static final String KEY_PAU304_DIANYURE2 = "PAU304_dianYuRe2";
    // 数据点"电预热3P4"对应的标识名
    protected static final String KEY_PAU304_DIANYURE3 = "PAU304_dianYuRe3";
    // 数据点"电预热高温P4"对应的标识名
    protected static final String KEY_PAU304_DIANYUREGAOWEN = "PAU304_dianYuReGaoWen";
    // 数据点"盘管低温P4"对应的标识名
    protected static final String KEY_PAU304_DIWENPANGUAN = "PAU304_diWenPanGuan";
    // 数据点"灭菌运行P4"对应的标识名
    protected static final String KEY_PAU304_MIEJUNYUNXING = "PAU304_mieJunYunXing";
    // 数据点"风机已启动P5"对应的标识名
    protected static final String KEY_PAU305_FENGJIYIQIDONG = "PAU305_fengJiYiQiDong";
    // 数据点"值班状态P5"对应的标识名
    protected static final String KEY_PAU305_ZHIBANSTATUS = "PAU305_zhiBanStatus";
    // 数据点"风机状态P5"对应的标识名
    protected static final String KEY_PAU305_FENGJISTATUS = "PAU305_fengJiStatus";
    // 数据点"手自动P5"对应的标识名
    protected static final String KEY_PAU305_SHOUZIDONG = "PAU305_shouZiDong";
    // 数据点"冬夏季P5"对应的标识名
    protected static final String KEY_PAU305_DONGXIAJI = "PAU305_dongXiaJi";
    // 数据点"中效报警P5"对应的标识名
    protected static final String KEY_PAU305_ZHONGXIAOBAOJING = "PAU305_zhongXiaoBaoJing";
    // 数据点"风机缺风P5"对应的标识名
    protected static final String KEY_PAU305_FENGJIQUEFENG = "PAU305_fengJiQueFeng";
    // 数据点"排风机已启动P5"对应的标识名
    protected static final String KEY_PAU305_PAIFENGJIYIQIDONG = "PAU305_paiFengJiYiQiDong";
    // 数据点"电预热1P5"对应的标识名
    protected static final String KEY_PAU305_DIANYURE1 = "PAU305_dianYuRe1";
    // 数据点"电预热2P5"对应的标识名
    protected static final String KEY_PAU305_DIANYURE2 = "PAU305_dianYuRe2";
    // 数据点"电预热3P5"对应的标识名
    protected static final String KEY_PAU305_DIANYURE3 = "PAU305_dianYuRe3";
    // 数据点"电预热高温P5"对应的标识名
    protected static final String KEY_PAU305_DIANYUREGAOWEN = "PAU305_dianYuReGaoWen";
    // 数据点"盘管低温P5"对应的标识名
    protected static final String KEY_PAU305_DIWENPANGUAN = "PAU305_diWenPanGuan";
    // 数据点"灭菌运行P5"对应的标识名
    protected static final String KEY_PAU305_MIEJUNYUNXING = "PAU305_mieJunYunXing";
    // 数据点"online_flag"对应的标识名
    protected static final String KEY_ONLINE_FLAG = "online_flag";
    // 数据点"面板1通讯状态A1"对应的标识名
    protected static final String KEY_AHU301_MIANBANTONGXUNZHUANGTAI1 = "AHU301_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A1"对应的标识名
    protected static final String KEY_AHU301_MIANBANTONGXUNZHUANGTAI2 = "AHU301_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A1"对应的标识名
    protected static final String KEY_AHU301_MIANBANTONGXUNZHUANGTAI3 = "AHU301_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A2"对应的标识名
    protected static final String KEY_AHU302_MIANBANTONGXUNZHUANGTAI1 = "AHU302_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A2"对应的标识名
    protected static final String KEY_AHU302_MIANBANTONGXUNZHUANGTAI2 = "AHU302_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A2"对应的标识名
    protected static final String KEY_AHU302_MIANBANTONGXUNZHUANGTAI3 = "AHU302_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A3"对应的标识名
    protected static final String KEY_AHU303_MIANBANTONGXUNZHUANGTAI1 = "AHU303_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A3"对应的标识名
    protected static final String KEY_AHU303_MIANBANTONGXUNZHUANGTAI2 = "AHU303_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A3"对应的标识名
    protected static final String KEY_AHU303_MIANBANTONGXUNZHUANGTAI3 = "AHU303_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A4"对应的标识名
    protected static final String KEY_AHU304_MIANBANTONGXUNZHUANGTAI1 = "AHU304_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A4"对应的标识名
    protected static final String KEY_AHU304_MIANBANTONGXUNZHUANGTAI2 = "AHU304_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A4"对应的标识名
    protected static final String KEY_AHU304_MIANBANTONGXUNZHUANGTAI3 = "AHU304_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A5"对应的标识名
    protected static final String KEY_AHU305_MIANBANTONGXUNZHUANGTAI1 = "AHU305_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A5"对应的标识名
    protected static final String KEY_AHU305_MIANBANTONGXUNZHUANGTAI2 = "AHU305_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A5"对应的标识名
    protected static final String KEY_AHU305_MIANBANTONGXUNZHUANGTAI3 = "AHU305_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A6"对应的标识名
    protected static final String KEY_AHU306_MIANBANTONGXUNZHUANGTAI1 = "AHU306_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A6"对应的标识名
    protected static final String KEY_AHU306_MIANBANTONGXUNZHUANGTAI2 = "AHU306_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A6"对应的标识名
    protected static final String KEY_AHU306_MIANBANTONGXUNZHUANGTAI3 = "AHU306_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A7"对应的标识名
    protected static final String KEY_AHU307_MIANBANTONGXUNZHUANGTAI1 = "AHU307_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A7"对应的标识名
    protected static final String KEY_AHU307_MIANBANTONGXUNZHUANGTAI2 = "AHU307_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A7"对应的标识名
    protected static final String KEY_AHU307_MIANBANTONGXUNZHUANGTAI3 = "AHU307_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态A8"对应的标识名
    protected static final String KEY_AHU308_MIANBANTONGXUNZHUANGTAI1 = "AHU308_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态A8"对应的标识名
    protected static final String KEY_AHU308_MIANBANTONGXUNZHUANGTAI2 = "AHU308_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态A8"对应的标识名
    protected static final String KEY_AHU308_MIANBANTONGXUNZHUANGTAI3 = "AHU308_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态P1"对应的标识名
    protected static final String KEY_PAU301_MIANBANTONGXUNZHUANGTAI1 = "PAU301_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态P1"对应的标识名
    protected static final String KEY_PAU301_MIANBANTONGXUNZHUANGTAI2 = "PAU301_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态P1"对应的标识名
    protected static final String KEY_PAU301_MIANBANTONGXUNZHUANGTAI3 = "PAU301_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态P2"对应的标识名
    protected static final String KEY_PAU302_MIANBANTONGXUNZHUANGTAI1 = "PAU302_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态P2"对应的标识名
    protected static final String KEY_PAU302_MIANBANTONGXUNZHUANGTAI2 = "PAU302_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态P2"对应的标识名
    protected static final String KEY_PAU302_MIANBANTONGXUNZHUANGTAI3 = "PAU302_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态P3"对应的标识名
    protected static final String KEY_PAU303_MIANBANTONGXUNZHUANGTAI1 = "PAU303_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态P3"对应的标识名
    protected static final String KEY_PAU303_MIANBANTONGXUNZHUANGTAI2 = "PAU303_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态P3"对应的标识名
    protected static final String KEY_PAU303_MIANBANTONGXUNZHUANGTAI3 = "PAU303_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态P4"对应的标识名
    protected static final String KEY_PAU304_MIANBANTONGXUNZHUANGTAI1 = "PAU304_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态P4"对应的标识名
    protected static final String KEY_PAU304_MIANBANTONGXUNZHUANGTAI2 = "PAU304_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态P4"对应的标识名
    protected static final String KEY_PAU304_MIANBANTONGXUNZHUANGTAI3 = "PAU304_mianBanTongXunZhuangTai3";
    // 数据点"面板1通讯状态P5"对应的标识名
    protected static final String KEY_PAU305_MIANBANTONGXUNZHUANGTAI1 = "PAU305_mianBanTongXunZhuangTai1";
    // 数据点"面板2通讯状态P5"对应的标识名
    protected static final String KEY_PAU305_MIANBANTONGXUNZHUANGTAI2 = "PAU305_mianBanTongXunZhuangTai2";
    // 数据点"面板3通讯状态P5"对应的标识名
    protected static final String KEY_PAU305_MIANBANTONGXUNZHUANGTAI3 = "PAU305_mianBanTongXunZhuangTai3";
    // 数据点"实时温度A1"对应的标识名
    protected static final String KEY_AHU301_TEMPREAL = "AHU301_tempReal";
    // 数据点"实时湿度A1"对应的标识名
    protected static final String KEY_AHU301_HUMIREAL = "AHU301_humiReal";
    // 数据点"温度设定值A1"对应的标识名
    protected static final String KEY_AHU301_TEMPSET = "AHU301_tempSet";
    // 数据点"湿度设定值A1"对应的标识名
    protected static final String KEY_AHU301_HUMISET = "AHU301_humiSet";
    // 数据点"冷水阀开度A1"对应的标识名
    protected static final String KEY_AHU301_LENGSHUIFAKAIDU = "AHU301_lengShuiFaKaiDu";
    // 数据点"热水阀开度A1"对应的标识名
    protected static final String KEY_AHU301_RESHUIFAKAIDU = "AHU301_reShuiFaKaiDu";
    // 数据点"新风温度A1"对应的标识名
    protected static final String KEY_AHU301_XINFENGWENDU = "AHU301_xinFengWenDU";
    // 数据点"加湿器开度A1"对应的标识名
    protected static final String KEY_AHU301_JIASHIQIKAIDU = "AHU301_jiaShiQIKaiDu";
    // 数据点"备用A1"对应的标识名
    protected static final String KEY_AHU301_BEIYONG = "AHU301_beiYong";
    // 数据点"实时温度A2"对应的标识名
    protected static final String KEY_AHU302_TEMPREAL = "AHU302_tempReal";
    // 数据点"实时湿度A2"对应的标识名
    protected static final String KEY_AHU302_HUMIREAL = "AHU302_humiReal";
    // 数据点"温度设定值A2"对应的标识名
    protected static final String KEY_AHU302_TEMPSET = "AHU302_tempSet";
    // 数据点"湿度设定值A2"对应的标识名
    protected static final String KEY_AHU302_HUMISET = "AHU302_humiSet";
    // 数据点"冷水阀开度A2"对应的标识名
    protected static final String KEY_AHU302_LENGSHUIFAKAIDU = "AHU302_lengShuiFaKaiDu";
    // 数据点"热水阀开度A2"对应的标识名
    protected static final String KEY_AHU302_RESHUIFAKAIDU = "AHU302_reShuiFaKaiDu";
    // 数据点"新风温度A2"对应的标识名
    protected static final String KEY_AHU302_XINFENGWENDU = "AHU302_xinFengWenDU";
    // 数据点"加湿器开度A2"对应的标识名
    protected static final String KEY_AHU302_JIASHIQIKAIDU = "AHU302_jiaShiQIKaiDu";
    // 数据点"备用A2"对应的标识名
    protected static final String KEY_AHU302_BEIYONG = "AHU302_beiYong";
    // 数据点"实时温度A3"对应的标识名
    protected static final String KEY_AHU303_TEMPREAL = "AHU303_tempReal";
    // 数据点"实时湿度A3"对应的标识名
    protected static final String KEY_AHU303_HUMIREAL = "AHU303_humiReal";
    // 数据点"温度设定值A3"对应的标识名
    protected static final String KEY_AHU303_TEMPSET = "AHU303_tempSet";
    // 数据点"湿度设定值A3"对应的标识名
    protected static final String KEY_AHU303_HUMISET = "AHU303_humiSet";
    // 数据点"冷水阀开度A3"对应的标识名
    protected static final String KEY_AHU303_LENGSHUIFAKAIDU = "AHU303_lengShuiFaKaiDu";
    // 数据点"热水阀开度A3"对应的标识名
    protected static final String KEY_AHU303_RESHUIFAKAIDU = "AHU303_reShuiFaKaiDu";
    // 数据点"新风温度A3"对应的标识名
    protected static final String KEY_AHU303_XINFENGWENDU = "AHU303_xinFengWenDU";
    // 数据点"加湿器开度A3"对应的标识名
    protected static final String KEY_AHU303_JIASHIQIKAIDU = "AHU303_jiaShiQIKaiDu";
    // 数据点"备用A3"对应的标识名
    protected static final String KEY_AHU303_BEIYONG = "AHU303_beiYong";
    // 数据点"实时温度A4"对应的标识名
    protected static final String KEY_AHU304_TEMPREAL = "AHU304_tempReal";
    // 数据点"实时湿度A4"对应的标识名
    protected static final String KEY_AHU304_HUMIREAL = "AHU304_humiReal";
    // 数据点"温度设定值A4"对应的标识名
    protected static final String KEY_AHU304_TEMPSET = "AHU304_tempSet";
    // 数据点"湿度设定值A4"对应的标识名
    protected static final String KEY_AHU304_HUMISET = "AHU304_humiSet";
    // 数据点"冷水阀开度A4"对应的标识名
    protected static final String KEY_AHU304_LENGSHUIFAKAIDU = "AHU304_lengShuiFaKaiDu";
    // 数据点"热水阀开度A4"对应的标识名
    protected static final String KEY_AHU304_RESHUIFAKAIDU = "AHU304_reShuiFaKaiDu";
    // 数据点"新风温度A4"对应的标识名
    protected static final String KEY_AHU304_XINFENGWENDU = "AHU304_xinFengWenDU";
    // 数据点"加湿器开度A4"对应的标识名
    protected static final String KEY_AHU304_JIASHIQIKAIDU = "AHU304_jiaShiQIKaiDu";
    // 数据点"备用A4"对应的标识名
    protected static final String KEY_AHU304_BEIYONG = "AHU304_beiYong";
    // 数据点"实时温度A5"对应的标识名
    protected static final String KEY_AHU305_TEMPREAL = "AHU305_tempReal";
    // 数据点"实时湿度A5"对应的标识名
    protected static final String KEY_AHU305_HUMIREAL = "AHU305_humiReal";
    // 数据点"温度设定值A5"对应的标识名
    protected static final String KEY_AHU305_TEMPSET = "AHU305_tempSet";
    // 数据点"湿度设定值A5"对应的标识名
    protected static final String KEY_AHU305_HUMISET = "AHU305_humiSet";
    // 数据点"冷水阀开度A5"对应的标识名
    protected static final String KEY_AHU305_LENGSHUIFAKAIDU = "AHU305_lengShuiFaKaiDu";
    // 数据点"热水阀开度A5"对应的标识名
    protected static final String KEY_AHU305_RESHUIFAKAIDU = "AHU305_reShuiFaKaiDu";
    // 数据点"新风温度A5"对应的标识名
    protected static final String KEY_AHU305_XINFENGWENDU = "AHU305_xinFengWenDU";
    // 数据点"加湿器开度A5"对应的标识名
    protected static final String KEY_AHU305_JIASHIQIKAIDU = "AHU305_jiaShiQIKaiDu";
    // 数据点"备用A5"对应的标识名
    protected static final String KEY_AHU305_BEIYONG = "AHU305_beiYong";
    // 数据点"实时温度A6"对应的标识名
    protected static final String KEY_AHU306_TEMPREAL = "AHU306_tempReal";
    // 数据点"实时湿度A6"对应的标识名
    protected static final String KEY_AHU306_HUMIREAL = "AHU306_humiReal";
    // 数据点"温度设定值A6"对应的标识名
    protected static final String KEY_AHU306_TEMPSET = "AHU306_tempSet";
    // 数据点"湿度设定值A6"对应的标识名
    protected static final String KEY_AHU306_HUMISET = "AHU306_humiSet";
    // 数据点"冷水阀开度A6"对应的标识名
    protected static final String KEY_AHU306_LENGSHUIFAKAIDU = "AHU306_lengShuiFaKaiDu";
    // 数据点"热水阀开度A6"对应的标识名
    protected static final String KEY_AHU306_RESHUIFAKAIDU = "AHU306_reShuiFaKaiDu";
    // 数据点"新风温度A6"对应的标识名
    protected static final String KEY_AHU306_XINFENGWENDU = "AHU306_xinFengWenDU";
    // 数据点"加湿器开度A6"对应的标识名
    protected static final String KEY_AHU306_JIASHIQIKAIDU = "AHU306_jiaShiQIKaiDu";
    // 数据点"备用A6"对应的标识名
    protected static final String KEY_AHU306_BEIYONG = "AHU306_beiYong";
    // 数据点"实时温度A7"对应的标识名
    protected static final String KEY_AHU307_TEMPREAL = "AHU307_tempReal";
    // 数据点"实时湿度A7"对应的标识名
    protected static final String KEY_AHU307_HUMIREAL = "AHU307_humiReal";
    // 数据点"温度设定值A7"对应的标识名
    protected static final String KEY_AHU307_TEMPSET = "AHU307_tempSet";
    // 数据点"湿度设定值A7"对应的标识名
    protected static final String KEY_AHU307_HUMISET = "AHU307_humiSet";
    // 数据点"冷水阀开度A7"对应的标识名
    protected static final String KEY_AHU307_LENGSHUIFAKAIDU = "AHU307_lengShuiFaKaiDu";
    // 数据点"热水阀开度A7"对应的标识名
    protected static final String KEY_AHU307_RESHUIFAKAIDU = "AHU307_reShuiFaKaiDu";
    // 数据点"新风温度A7"对应的标识名
    protected static final String KEY_AHU307_XINFENGWENDU = "AHU307_xinFengWenDU";
    // 数据点"加湿器开度A7"对应的标识名
    protected static final String KEY_AHU307_JIASHIQIKAIDU = "AHU307_jiaShiQIKaiDu";
    // 数据点"备用A7"对应的标识名
    protected static final String KEY_AHU307_BEIYONG = "AHU307_beiYong";
    // 数据点"实时温度A8"对应的标识名
    protected static final String KEY_AHU308_TEMPREAL = "AHU308_tempReal";
    // 数据点"实时湿度A8"对应的标识名
    protected static final String KEY_AHU308_HUMIREAL = "AHU308_humiReal";
    // 数据点"温度设定值A8"对应的标识名
    protected static final String KEY_AHU308_TEMPSET = "AHU308_tempSet";
    // 数据点"湿度设定值A8"对应的标识名
    protected static final String KEY_AHU308_HUMISET = "AHU308_humiSet";
    // 数据点"冷水阀开度A8"对应的标识名
    protected static final String KEY_AHU308_LENGSHUIFAKAIDU = "AHU308_lengShuiFaKaiDu";
    // 数据点"热水阀开度A8"对应的标识名
    protected static final String KEY_AHU308_RESHUIFAKAIDU = "AHU308_reShuiFaKaiDu";
    // 数据点"新风温度A8"对应的标识名
    protected static final String KEY_AHU308_XINFENGWENDU = "AHU308_xinFengWenDU";
    // 数据点"加湿器开度A8"对应的标识名
    protected static final String KEY_AHU308_JIASHIQIKAIDU = "AHU308_jiaShiQIKaiDu";
    // 数据点"备用A8"对应的标识名
    protected static final String KEY_AHU308_BEIYONG = "AHU308_beiYong";
    // 数据点"实时温度P1"对应的标识名
    protected static final String KEY_PAU301_TEMPREAL = "PAU301_tempReal";
    // 数据点"实时湿度P1"对应的标识名
    protected static final String KEY_PAU301_HUMIREAL = "PAU301_humiReal";
    // 数据点"温度设定值P1"对应的标识名
    protected static final String KEY_PAU301_TEMPSET = "PAU301_tempSet";
    // 数据点"湿度设定值P1"对应的标识名
    protected static final String KEY_PAU301_HUMISET = "PAU301_humiSet";
    // 数据点"冷水阀开度P1"对应的标识名
    protected static final String KEY_PAU301_LENGSHUIFAKAIDU = "PAU301_lengShuiFaKaiDu";
    // 数据点"热水阀开度P1"对应的标识名
    protected static final String KEY_PAU301_RESHUIFAKAIDU = "PAU301_reShuiFaKaiDu";
    // 数据点"新风温度P1"对应的标识名
    protected static final String KEY_PAU301_XINFENGWENDU = "PAU301_xinFengWenDU";
    // 数据点"加湿器开度P1"对应的标识名
    protected static final String KEY_PAU301_JIASHIQIKAIDU = "PAU301_jiaShiQIKaiDu";
    // 数据点"备用P1"对应的标识名
    protected static final String KEY_PAU301_BEIYONG = "PAU301_beiYong";
    // 数据点"实时温度P2"对应的标识名
    protected static final String KEY_PAU302_TEMPREAL = "PAU302_tempReal";
    // 数据点"实时湿度P2"对应的标识名
    protected static final String KEY_PAU302_HUMIREAL = "PAU302_humiReal";
    // 数据点"温度设定值P2"对应的标识名
    protected static final String KEY_PAU302_TEMPSET = "PAU302_tempSet";
    // 数据点"湿度设定值P2"对应的标识名
    protected static final String KEY_PAU302_HUMISET = "PAU302_humiSet";
    // 数据点"冷水阀开度P2"对应的标识名
    protected static final String KEY_PAU302_LENGSHUIFAKAIDU = "PAU302_lengShuiFaKaiDu";
    // 数据点"热水阀开度P2"对应的标识名
    protected static final String KEY_PAU302_RESHUIFAKAIDU = "PAU302_reShuiFaKaiDu";
    // 数据点"新风温度P2"对应的标识名
    protected static final String KEY_PAU302_XINFENGWENDU = "PAU302_xinFengWenDU";
    // 数据点"加湿器开度P2"对应的标识名
    protected static final String KEY_PAU302_JIASHIQIKAIDU = "PAU302_jiaShiQIKaiDu";
    // 数据点"备用P2"对应的标识名
    protected static final String KEY_PAU302_BEIYONG = "PAU302_beiYong";
    // 数据点"实时温度P3"对应的标识名
    protected static final String KEY_PAU303_TEMPREAL = "PAU303_tempReal";
    // 数据点"实时湿度P3"对应的标识名
    protected static final String KEY_PAU303_HUMIREAL = "PAU303_humiReal";
    // 数据点"温度设定值P3"对应的标识名
    protected static final String KEY_PAU303_TEMPSET = "PAU303_tempSet";
    // 数据点"湿度设定值P3"对应的标识名
    protected static final String KEY_PAU303_HUMISET = "PAU303_humiSet";
    // 数据点"冷水阀开度P3"对应的标识名
    protected static final String KEY_PAU303_LENGSHUIFAKAIDU = "PAU303_lengShuiFaKaiDu";
    // 数据点"热水阀开度P3"对应的标识名
    protected static final String KEY_PAU303_RESHUIFAKAIDU = "PAU303_reShuiFaKaiDu";
    // 数据点"新风温度P3"对应的标识名
    protected static final String KEY_PAU303_XINFENGWENDU = "PAU303_xinFengWenDU";
    // 数据点"加湿器开度P3"对应的标识名
    protected static final String KEY_PAU303_JIASHIQIKAIDU = "PAU303_jiaShiQIKaiDu";
    // 数据点"备用P3"对应的标识名
    protected static final String KEY_PAU303_BEIYONG = "PAU303_beiYong";
    // 数据点"实时温度P4"对应的标识名
    protected static final String KEY_PAU304_TEMPREAL = "PAU304_tempReal";
    // 数据点"实时湿度P4"对应的标识名
    protected static final String KEY_PAU304_HUMIREAL = "PAU304_humiReal";
    // 数据点"温度设定值P4"对应的标识名
    protected static final String KEY_PAU304_TEMPSET = "PAU304_tempSet";
    // 数据点"湿度设定值P4"对应的标识名
    protected static final String KEY_PAU304_HUMISET = "PAU304_humiSet";
    // 数据点"冷水阀开度P4"对应的标识名
    protected static final String KEY_PAU304_LENGSHUIFAKAIDU = "PAU304_lengShuiFaKaiDu";
    // 数据点"热水阀开度P4"对应的标识名
    protected static final String KEY_PAU304_RESHUIFAKAIDU = "PAU304_reShuiFaKaiDu";
    // 数据点"新风温度P4"对应的标识名
    protected static final String KEY_PAU304_XINFENGWENDU = "PAU304_xinFengWenDU";
    // 数据点"加湿器开度P4"对应的标识名
    protected static final String KEY_PAU304_JIASHIQIKAIDU = "PAU304_jiaShiQIKaiDu";
    // 数据点"备用P4"对应的标识名
    protected static final String KEY_PAU304_BEIYONG = "PAU304_beiYong";
    // 数据点"实时温度P5"对应的标识名
    protected static final String KEY_PAU305_TEMPREAL = "PAU305_tempReal";
    // 数据点"实时湿度P5"对应的标识名
    protected static final String KEY_PAU305_HUMIREAL = "PAU305_humiReal";
    // 数据点"温度设定值P5"对应的标识名
    protected static final String KEY_PAU305_TEMPSET = "PAU305_tempSet";
    // 数据点"湿度设定值P5"对应的标识名
    protected static final String KEY_PAU305_HUMISET = "PAU305_humiSet";
    // 数据点"冷水阀开度P5"对应的标识名
    protected static final String KEY_PAU305_LENGSHUIFAKAIDU = "PAU305_lengShuiFaKaiDu";
    // 数据点"热水阀开度P5"对应的标识名
    protected static final String KEY_PAU305_RESHUIFAKAIDU = "PAU305_reShuiFaKaiDu";
    // 数据点"新风温度P5"对应的标识名
    protected static final String KEY_PAU305_XINFENGWENDU = "PAU305_xinFengWenDU";
    // 数据点"加湿器开度P5"对应的标识名
    protected static final String KEY_PAU305_JIASHIQIKAIDU = "PAU305_jiaShiQIKaiDu";
    // 数据点"备用P5"对应的标识名
    protected static final String KEY_PAU305_BEIYONG = "PAU305_beiYong";

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
    protected static boolean data_AHU301_fengJiYiQiDong;
    // 数据点"值班状态A1"对应的存储数据
    protected static boolean data_AHU301_zhiBanStatus;
    // 数据点"电加热1A1"对应的存储数据
    protected static boolean data_AHU301_dianJiaRe1;
    // 数据点"电加热2A1"对应的存储数据
    protected static boolean data_AHU301_dianJiaRe2;
    // 数据点"电加热3A1"对应的存储数据
    protected static boolean data_AHU301_dianJiaRe3;
    // 数据点"风机状态A1"对应的存储数据
    protected static boolean data_AHU301_fengJiStatus;
    // 数据点"手自动A1"对应的存储数据
    protected static boolean data_AHU301_shouZiDong;
    // 数据点"冬夏季A1"对应的存储数据
    protected static boolean data_AHU301_dongXiaJi;
    // 数据点"中效报警A1"对应的存储数据
    protected static boolean data_AHU301_zhongXiaoBaoJing;
    // 数据点"电加热高温A1"对应的存储数据
    protected static boolean data_AHU301_dianJiaReGaoWen;
    // 数据点"风机缺风A1"对应的存储数据
    protected static boolean data_AHU301_fengJiQueFeng;
    // 数据点"排风机已启动A1"对应的存储数据
    protected static boolean data_AHU301_paiFengJiYiQiDong;
    // 数据点"盘管低温A1"对应的存储数据
    protected static boolean data_AHU301_diWenPanGuan;
    // 数据点"灭菌运行A1"对应的存储数据
    protected static boolean data_AHU301_mieJunYunXing;
    // 数据点"风机已启动A2"对应的存储数据
    protected static boolean data_AHU302_fengJiYiQiDong;
    // 数据点"值班状态A2"对应的存储数据
    protected static boolean data_AHU302_zhiBanStatus;
    // 数据点"电加热1A2"对应的存储数据
    protected static boolean data_AHU302_dianJiaRe1;
    // 数据点"电加热2A2"对应的存储数据
    protected static boolean data_AHU302_dianJiaRe2;
    // 数据点"电加热3A2"对应的存储数据
    protected static boolean data_AHU302_dianJiaRe3;
    // 数据点"风机状态A2"对应的存储数据
    protected static boolean data_AHU302_fengJiStatus;
    // 数据点"手自动A2"对应的存储数据
    protected static boolean data_AHU302_shouZiDong;
    // 数据点"冬夏季A2"对应的存储数据
    protected static boolean data_AHU302_dongXiaJi;
    // 数据点"中效报警A2"对应的存储数据
    protected static boolean data_AHU302_zhongXiaoBaoJing;
    // 数据点"电加热高温A2"对应的存储数据
    protected static boolean data_AHU302_dianJiaReGaoWen;
    // 数据点"风机缺风A2"对应的存储数据
    protected static boolean data_AHU302_fengJiQueFeng;
    // 数据点"排风机已启动A2"对应的存储数据
    protected static boolean data_AHU302_paiFengJiYiQiDong;
    // 数据点"盘管低温A2"对应的存储数据
    protected static boolean data_AHU302_diWenPanGuan;
    // 数据点"灭菌运行A2"对应的存储数据
    protected static boolean data_AHU302_mieJunYunXing;
    // 数据点"风机已启动A3"对应的存储数据
    protected static boolean data_AHU303_fengJiYiQiDong;
    // 数据点"值班状态A3"对应的存储数据
    protected static boolean data_AHU303_zhiBanStatus;
    // 数据点"电加热1A3"对应的存储数据
    protected static boolean data_AHU303_dianJiaRe1;
    // 数据点"电加热2A3"对应的存储数据
    protected static boolean data_AHU303_dianJiaRe2;
    // 数据点"电加热3A3"对应的存储数据
    protected static boolean data_AHU303_dianJiaRe3;
    // 数据点"风机状态A3"对应的存储数据
    protected static boolean data_AHU303_fengJiStatus;
    // 数据点"手自动A3"对应的存储数据
    protected static boolean data_AHU303_shouZiDong;
    // 数据点"冬夏季A3"对应的存储数据
    protected static boolean data_AHU303_dongXiaJi;
    // 数据点"中效报警A3"对应的存储数据
    protected static boolean data_AHU303_zhongXiaoBaoJing;
    // 数据点"电加热高温A3"对应的存储数据
    protected static boolean data_AHU303_dianJiaReGaoWen;
    // 数据点"风机缺风A3"对应的存储数据
    protected static boolean data_AHU303_fengJiQueFeng;
    // 数据点"排风机已启动A3"对应的存储数据
    protected static boolean data_AHU303_paiFengJiYiQiDong;
    // 数据点"盘管低温A3"对应的存储数据
    protected static boolean data_AHU303_diWenPanGuan;
    // 数据点"灭菌运行A3"对应的存储数据
    protected static boolean data_AHU303_mieJunYunXing;
    // 数据点"风机已启动A4"对应的存储数据
    protected static boolean data_AHU304_fengJiYiQiDong;
    // 数据点"值班状态A4"对应的存储数据
    protected static boolean data_AHU304_zhiBanStatus;
    // 数据点"电加热1A4"对应的存储数据
    protected static boolean data_AHU304_dianJiaRe1;
    // 数据点"电加热2A4"对应的存储数据
    protected static boolean data_AHU304_dianJiaRe2;
    // 数据点"电加热3A4"对应的存储数据
    protected static boolean data_AHU304_dianJiaRe3;
    // 数据点"风机状态A4"对应的存储数据
    protected static boolean data_AHU304_fengJiStatus;
    // 数据点"手自动A4"对应的存储数据
    protected static boolean data_AHU304_shouZiDong;
    // 数据点"冬夏季A4"对应的存储数据
    protected static boolean data_AHU304_dongXiaJi;
    // 数据点"中效报警A4"对应的存储数据
    protected static boolean data_AHU304_zhongXiaoBaoJing;
    // 数据点"电加热高温A4"对应的存储数据
    protected static boolean data_AHU304_dianJiaReGaoWen;
    // 数据点"风机缺风A4"对应的存储数据
    protected static boolean data_AHU304_fengJiQueFeng;
    // 数据点"排风机已启动A4"对应的存储数据
    protected static boolean data_AHU304_paiFengJiYiQiDong;
    // 数据点"盘管低温A4"对应的存储数据
    protected static boolean data_AHU304_diWenPanGuan;
    // 数据点"灭菌运行A4"对应的存储数据
    protected static boolean data_AHU304_mieJunYunXing;
    // 数据点"风机已启动A5"对应的存储数据
    protected static boolean data_AHU305_fengJiYiQiDong;
    // 数据点"值班状态A5"对应的存储数据
    protected static boolean data_AHU305_zhiBanStatus;
    // 数据点"电加热1A5"对应的存储数据
    protected static boolean data_AHU305_dianJiaRe1;
    // 数据点"电加热2A5"对应的存储数据
    protected static boolean data_AHU305_dianJiaRe2;
    // 数据点"电加热3A5"对应的存储数据
    protected static boolean data_AHU305_dianJiaRe3;
    // 数据点"风机状态A5"对应的存储数据
    protected static boolean data_AHU305_fengJiStatus;
    // 数据点"手自动A5"对应的存储数据
    protected static boolean data_AHU305_shouZiDong;
    // 数据点"冬夏季A5"对应的存储数据
    protected static boolean data_AHU305_dongXiaJi;
    // 数据点"中效报警A5"对应的存储数据
    protected static boolean data_AHU305_zhongXiaoBaoJing;
    // 数据点"电加热高温A5"对应的存储数据
    protected static boolean data_AHU305_dianJiaReGaoWen;
    // 数据点"风机缺风A5"对应的存储数据
    protected static boolean data_AHU305_fengJiQueFeng;
    // 数据点"排风机已启动A5"对应的存储数据
    protected static boolean data_AHU305_paiFengJiYiQiDong;
    // 数据点"盘管低温A5"对应的存储数据
    protected static boolean data_AHU305_diWenPanGuan;
    // 数据点"灭菌运行A5"对应的存储数据
    protected static boolean data_AHU305_mieJunYunXing;
    // 数据点"风机已启动A6"对应的存储数据
    protected static boolean data_AHU306_fengJiYiQiDong;
    // 数据点"值班状态A6"对应的存储数据
    protected static boolean data_AHU306_zhiBanStatus;
    // 数据点"电加热1A6"对应的存储数据
    protected static boolean data_AHU306_dianJiaRe1;
    // 数据点"电加热2A6"对应的存储数据
    protected static boolean data_AHU306_dianJiaRe2;
    // 数据点"电加热3A6"对应的存储数据
    protected static boolean data_AHU306_dianJiaRe3;
    // 数据点"风机状态A6"对应的存储数据
    protected static boolean data_AHU306_fengJiStatus;
    // 数据点"手自动A6"对应的存储数据
    protected static boolean data_AHU306_shouZiDong;
    // 数据点"冬夏季A6"对应的存储数据
    protected static boolean data_AHU306_dongXiaJi;
    // 数据点"中效报警A6"对应的存储数据
    protected static boolean data_AHU306_zhongXiaoBaoJing;
    // 数据点"电加热高温A6"对应的存储数据
    protected static boolean data_AHU306_dianJiaReGaoWen;
    // 数据点"风机缺风A6"对应的存储数据
    protected static boolean data_AHU306_fengJiQueFeng;
    // 数据点"排风机已启动A6"对应的存储数据
    protected static boolean data_AHU306_paiFengJiYiQiDong;
    // 数据点"盘管低温A6"对应的存储数据
    protected static boolean data_AHU306_diWenPanGuan;
    // 数据点"灭菌运行A6"对应的存储数据
    protected static boolean data_AHU306_mieJunYunXing;
    // 数据点"风机已启动A7"对应的存储数据
    protected static boolean data_AHU307_fengJiYiQiDong;
    // 数据点"值班状态A7"对应的存储数据
    protected static boolean data_AHU307_zhiBanStatus;
    // 数据点"电加热1A7"对应的存储数据
    protected static boolean data_AHU307_dianJiaRe1;
    // 数据点"电加热2A7"对应的存储数据
    protected static boolean data_AHU307_dianJiaRe2;
    // 数据点"电加热3A7"对应的存储数据
    protected static boolean data_AHU307_dianJiaRe3;
    // 数据点"风机状态A7"对应的存储数据
    protected static boolean data_AHU307_fengJiStatus;
    // 数据点"手自动A7"对应的存储数据
    protected static boolean data_AHU307_shouZiDong;
    // 数据点"冬夏季A7"对应的存储数据
    protected static boolean data_AHU307_dongXiaJi;
    // 数据点"中效报警A7"对应的存储数据
    protected static boolean data_AHU307_zhongXiaoBaoJing;
    // 数据点"电加热高温A7"对应的存储数据
    protected static boolean data_AHU307_dianJiaReGaoWen;
    // 数据点"风机缺风A7"对应的存储数据
    protected static boolean data_AHU307_fengJiQueFeng;
    // 数据点"排风机已启动A7"对应的存储数据
    protected static boolean data_AHU307_paiFengJiYiQiDong;
    // 数据点"盘管低温A7"对应的存储数据
    protected static boolean data_AHU307_diWenPanGuan;
    // 数据点"灭菌运行A7"对应的存储数据
    protected static boolean data_AHU307_mieJunYunXing;
    // 数据点"风机已启动A8"对应的存储数据
    protected static boolean data_AHU308_fengJiYiQiDong;
    // 数据点"值班状态A8"对应的存储数据
    protected static boolean data_AHU308_zhiBanStatus;
    // 数据点"电加热1A8"对应的存储数据
    protected static boolean data_AHU308_dianJiaRe1;
    // 数据点"电加热2A8"对应的存储数据
    protected static boolean data_AHU308_dianJiaRe2;
    // 数据点"电加热3A8"对应的存储数据
    protected static boolean data_AHU308_dianJiaRe3;
    // 数据点"风机状态A8"对应的存储数据
    protected static boolean data_AHU308_fengJiStatus;
    // 数据点"手自动A8"对应的存储数据
    protected static boolean data_AHU308_shouZiDong;
    // 数据点"冬夏季A8"对应的存储数据
    protected static boolean data_AHU308_dongXiaJi;
    // 数据点"中效报警A8"对应的存储数据
    protected static boolean data_AHU308_zhongXiaoBaoJing;
    // 数据点"电加热高温A8"对应的存储数据
    protected static boolean data_AHU308_dianJiaReGaoWen;
    // 数据点"风机缺风A8"对应的存储数据
    protected static boolean data_AHU308_fengJiQueFeng;
    // 数据点"排风机已启动A8"对应的存储数据
    protected static boolean data_AHU308_paiFengJiYiQiDong;
    // 数据点"盘管低温A8"对应的存储数据
    protected static boolean data_AHU308_diWenPanGuan;
    // 数据点"灭菌运行A8"对应的存储数据
    protected static boolean data_AHU308_mieJunYunXing;
    // 数据点"风机已启动P1"对应的存储数据
    protected static boolean data_PAU301_fengJiYiQiDong;
    // 数据点"值班状态P1"对应的存储数据
    protected static boolean data_PAU301_zhiBanStatus;
    // 数据点"电加热1P1"对应的存储数据
    protected static boolean data_PAU301_dianJiaRe1;
    // 数据点"电加热2P1"对应的存储数据
    protected static boolean data_PAU301_dianJiaRe2;
    // 数据点"电加热3P1"对应的存储数据
    protected static boolean data_PAU301_dianJiaRe3;
    // 数据点"风机状态P1"对应的存储数据
    protected static boolean data_PAU301_fengJiStatus;
    // 数据点"手自动P1"对应的存储数据
    protected static boolean data_PAU301_shouZiDong;
    // 数据点"冬夏季P1"对应的存储数据
    protected static boolean data_PAU301_dongXiaJi;
    // 数据点"中效报警P1"对应的存储数据
    protected static boolean data_PAU301_zhongXiaoBaoJing;
    // 数据点"电加热高温P1"对应的存储数据
    protected static boolean data_PAU301_dianJiaReGaoWen;
    // 数据点"风机缺风P1"对应的存储数据
    protected static boolean data_PAU301_fengJiQueFeng;
    // 数据点"排风机已启动P1"对应的存储数据
    protected static boolean data_PAU301_paiFengJiYiQiDong;
    // 数据点"电预热1P1"对应的存储数据
    protected static boolean data_PAU301_dianYuRe1;
    // 数据点"电预热2P1"对应的存储数据
    protected static boolean data_PAU301_dianYuRe2;
    // 数据点"电预热3P1"对应的存储数据
    protected static boolean data_PAU301_dianYuRe3;
    // 数据点"电预热高温P1"对应的存储数据
    protected static boolean data_PAU301_dianYuReGaoWen;
    // 数据点"盘管低温P1"对应的存储数据
    protected static boolean data_PAU301_diWenPanGuan;
    // 数据点"灭菌运行P1"对应的存储数据
    protected static boolean data_PAU301_mieJunYunXing;
    // 数据点"风机已启动P2"对应的存储数据
    protected static boolean data_PAU302_fengJiYiQiDong;
    // 数据点"值班状态P2"对应的存储数据
    protected static boolean data_PAU302_zhiBanStatus;
    // 数据点"风机状态P2"对应的存储数据
    protected static boolean data_PAU302_fengJiStatus;
    // 数据点"手自动P2"对应的存储数据
    protected static boolean data_PAU302_shouZiDong;
    // 数据点"冬夏季P2"对应的存储数据
    protected static boolean data_PAU302_dongXiaJi;
    // 数据点"中效报警P2"对应的存储数据
    protected static boolean data_PAU302_zhongXiaoBaoJing;
    // 数据点"风机缺风P2"对应的存储数据
    protected static boolean data_PAU302_fengJiQueFeng;
    // 数据点"排风机已启动P2"对应的存储数据
    protected static boolean data_PAU302_paiFengJiYiQiDong;
    // 数据点"电预热1P2"对应的存储数据
    protected static boolean data_PAU302_dianYuRe1;
    // 数据点"电预热2P2"对应的存储数据
    protected static boolean data_PAU302_dianYuRe2;
    // 数据点"电预热3P2"对应的存储数据
    protected static boolean data_PAU302_dianYuRe3;
    // 数据点"电预热高温P2"对应的存储数据
    protected static boolean data_PAU302_dianYuReGaoWen;
    // 数据点"盘管低温P2"对应的存储数据
    protected static boolean data_PAU302_diWenPanGuan;
    // 数据点"灭菌运行P2"对应的存储数据
    protected static boolean data_PAU302_mieJunYunXing;
    // 数据点"风机已启动P3"对应的存储数据
    protected static boolean data_PAU303_fengJiYiQiDong;
    // 数据点"值班状态P3"对应的存储数据
    protected static boolean data_PAU303_zhiBanStatus;
    // 数据点"风机状态P3"对应的存储数据
    protected static boolean data_PAU303_fengJiStatus;
    // 数据点"手自动P3"对应的存储数据
    protected static boolean data_PAU303_shouZiDong;
    // 数据点"冬夏季P3"对应的存储数据
    protected static boolean data_PAU303_dongXiaJi;
    // 数据点"中效报警P3"对应的存储数据
    protected static boolean data_PAU303_zhongXiaoBaoJing;
    // 数据点"风机缺风P3"对应的存储数据
    protected static boolean data_PAU303_fengJiQueFeng;
    // 数据点"排风机已启动P3"对应的存储数据
    protected static boolean data_PAU303_paiFengJiYiQiDong;
    // 数据点"电预热1P3"对应的存储数据
    protected static boolean data_PAU303_dianYuRe1;
    // 数据点"电预热2P3"对应的存储数据
    protected static boolean data_PAU303_dianYuRe2;
    // 数据点"电预热3P3"对应的存储数据
    protected static boolean data_PAU303_dianYuRe3;
    // 数据点"电预热高温P3"对应的存储数据
    protected static boolean data_PAU303_dianYuReGaoWen;
    // 数据点"盘管低温P3"对应的存储数据
    protected static boolean data_PAU303_diWenPanGuan;
    // 数据点"灭菌运行P3"对应的存储数据
    protected static boolean data_PAU303_mieJunYunXing;
    // 数据点"风机已启动P4"对应的存储数据
    protected static boolean data_PAU304_fengJiYiQiDong;
    // 数据点"值班状态P4"对应的存储数据
    protected static boolean data_PAU304_zhiBanStatus;
    // 数据点"风机状态P4"对应的存储数据
    protected static boolean data_PAU304_fengJiStatus;
    // 数据点"手自动P4"对应的存储数据
    protected static boolean data_PAU304_shouZiDong;
    // 数据点"冬夏季P4"对应的存储数据
    protected static boolean data_PAU304_dongXiaJi;
    // 数据点"中效报警P4"对应的存储数据
    protected static boolean data_PAU304_zhongXiaoBaoJing;
    // 数据点"风机缺风P4"对应的存储数据
    protected static boolean data_PAU304_fengJiQueFeng;
    // 数据点"排风机已启动P4"对应的存储数据
    protected static boolean data_PAU304_paiFengJiYiQiDong;
    // 数据点"电预热1P4"对应的存储数据
    protected static boolean data_PAU304_dianYuRe1;
    // 数据点"电预热2P4"对应的存储数据
    protected static boolean data_PAU304_dianYuRe2;
    // 数据点"电预热3P4"对应的存储数据
    protected static boolean data_PAU304_dianYuRe3;
    // 数据点"电预热高温P4"对应的存储数据
    protected static boolean data_PAU304_dianYuReGaoWen;
    // 数据点"盘管低温P4"对应的存储数据
    protected static boolean data_PAU304_diWenPanGuan;
    // 数据点"灭菌运行P4"对应的存储数据
    protected static boolean data_PAU304_mieJunYunXing;
    // 数据点"风机已启动P5"对应的存储数据
    protected static boolean data_PAU305_fengJiYiQiDong;
    // 数据点"值班状态P5"对应的存储数据
    protected static boolean data_PAU305_zhiBanStatus;
    // 数据点"风机状态P5"对应的存储数据
    protected static boolean data_PAU305_fengJiStatus;
    // 数据点"手自动P5"对应的存储数据
    protected static boolean data_PAU305_shouZiDong;
    // 数据点"冬夏季P5"对应的存储数据
    protected static boolean data_PAU305_dongXiaJi;
    // 数据点"中效报警P5"对应的存储数据
    protected static boolean data_PAU305_zhongXiaoBaoJing;
    // 数据点"风机缺风P5"对应的存储数据
    protected static boolean data_PAU305_fengJiQueFeng;
    // 数据点"排风机已启动P5"对应的存储数据
    protected static boolean data_PAU305_paiFengJiYiQiDong;
    // 数据点"电预热1P5"对应的存储数据
    protected static boolean data_PAU305_dianYuRe1;
    // 数据点"电预热2P5"对应的存储数据
    protected static boolean data_PAU305_dianYuRe2;
    // 数据点"电预热3P5"对应的存储数据
    protected static boolean data_PAU305_dianYuRe3;
    // 数据点"电预热高温P5"对应的存储数据
    protected static boolean data_PAU305_dianYuReGaoWen;
    // 数据点"盘管低温P5"对应的存储数据
    protected static boolean data_PAU305_diWenPanGuan;
    // 数据点"灭菌运行P5"对应的存储数据
    protected static boolean data_PAU305_mieJunYunXing;
    // 数据点"online_flag"对应的存储数据
    protected static boolean data_online_flag;
    // 数据点"面板1通讯状态A1"对应的存储数据
    protected static int data_AHU301_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A1"对应的存储数据
    protected static int data_AHU301_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A1"对应的存储数据
    protected static int data_AHU301_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A2"对应的存储数据
    protected static int data_AHU302_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A2"对应的存储数据
    protected static int data_AHU302_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A2"对应的存储数据
    protected static int data_AHU302_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A3"对应的存储数据
    protected static int data_AHU303_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A3"对应的存储数据
    protected static int data_AHU303_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A3"对应的存储数据
    protected static int data_AHU303_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A4"对应的存储数据
    protected static int data_AHU304_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A4"对应的存储数据
    protected static int data_AHU304_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A4"对应的存储数据
    protected static int data_AHU304_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A5"对应的存储数据
    protected static int data_AHU305_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A5"对应的存储数据
    protected static int data_AHU305_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A5"对应的存储数据
    protected static int data_AHU305_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A6"对应的存储数据
    protected static int data_AHU306_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A6"对应的存储数据
    protected static int data_AHU306_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A6"对应的存储数据
    protected static int data_AHU306_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A7"对应的存储数据
    protected static int data_AHU307_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A7"对应的存储数据
    protected static int data_AHU307_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A7"对应的存储数据
    protected static int data_AHU307_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态A8"对应的存储数据
    protected static int data_AHU308_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态A8"对应的存储数据
    protected static int data_AHU308_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态A8"对应的存储数据
    protected static int data_AHU308_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态P1"对应的存储数据
    protected static int data_PAU301_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态P1"对应的存储数据
    protected static int data_PAU301_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态P1"对应的存储数据
    protected static int data_PAU301_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态P2"对应的存储数据
    protected static int data_PAU302_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态P2"对应的存储数据
    protected static int data_PAU302_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态P2"对应的存储数据
    protected static int data_PAU302_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态P3"对应的存储数据
    protected static int data_PAU303_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态P3"对应的存储数据
    protected static int data_PAU303_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态P3"对应的存储数据
    protected static int data_PAU303_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态P4"对应的存储数据
    protected static int data_PAU304_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态P4"对应的存储数据
    protected static int data_PAU304_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态P4"对应的存储数据
    protected static int data_PAU304_mianBanTongXunZhuangTai3;
    // 数据点"面板1通讯状态P5"对应的存储数据
    protected static int data_PAU305_mianBanTongXunZhuangTai1;
    // 数据点"面板2通讯状态P5"对应的存储数据
    protected static int data_PAU305_mianBanTongXunZhuangTai2;
    // 数据点"面板3通讯状态P5"对应的存储数据
    protected static int data_PAU305_mianBanTongXunZhuangTai3;
    // 数据点"实时温度A1"对应的存储数据
    protected static int data_AHU301_tempReal;
    // 数据点"实时湿度A1"对应的存储数据
    protected static int data_AHU301_humiReal;
    // 数据点"温度设定值A1"对应的存储数据
    protected static int data_AHU301_tempSet;
    // 数据点"湿度设定值A1"对应的存储数据
    protected static int data_AHU301_humiSet;
    // 数据点"冷水阀开度A1"对应的存储数据
    protected static int data_AHU301_lengShuiFaKaiDu;
    // 数据点"热水阀开度A1"对应的存储数据
    protected static int data_AHU301_reShuiFaKaiDu;
    // 数据点"新风温度A1"对应的存储数据
    protected static int data_AHU301_xinFengWenDU;
    // 数据点"加湿器开度A1"对应的存储数据
    protected static int data_AHU301_jiaShiQIKaiDu;
    // 数据点"备用A1"对应的存储数据
    protected static int data_AHU301_beiYong;
    // 数据点"实时温度A2"对应的存储数据
    protected static int data_AHU302_tempReal;
    // 数据点"实时湿度A2"对应的存储数据
    protected static int data_AHU302_humiReal;
    // 数据点"温度设定值A2"对应的存储数据
    protected static int data_AHU302_tempSet;
    // 数据点"湿度设定值A2"对应的存储数据
    protected static int data_AHU302_humiSet;
    // 数据点"冷水阀开度A2"对应的存储数据
    protected static int data_AHU302_lengShuiFaKaiDu;
    // 数据点"热水阀开度A2"对应的存储数据
    protected static int data_AHU302_reShuiFaKaiDu;
    // 数据点"新风温度A2"对应的存储数据
    protected static int data_AHU302_xinFengWenDU;
    // 数据点"加湿器开度A2"对应的存储数据
    protected static int data_AHU302_jiaShiQIKaiDu;
    // 数据点"备用A2"对应的存储数据
    protected static int data_AHU302_beiYong;
    // 数据点"实时温度A3"对应的存储数据
    protected static int data_AHU303_tempReal;
    // 数据点"实时湿度A3"对应的存储数据
    protected static int data_AHU303_humiReal;
    // 数据点"温度设定值A3"对应的存储数据
    protected static int data_AHU303_tempSet;
    // 数据点"湿度设定值A3"对应的存储数据
    protected static int data_AHU303_humiSet;
    // 数据点"冷水阀开度A3"对应的存储数据
    protected static int data_AHU303_lengShuiFaKaiDu;
    // 数据点"热水阀开度A3"对应的存储数据
    protected static int data_AHU303_reShuiFaKaiDu;
    // 数据点"新风温度A3"对应的存储数据
    protected static int data_AHU303_xinFengWenDU;
    // 数据点"加湿器开度A3"对应的存储数据
    protected static int data_AHU303_jiaShiQIKaiDu;
    // 数据点"备用A3"对应的存储数据
    protected static int data_AHU303_beiYong;
    // 数据点"实时温度A4"对应的存储数据
    protected static int data_AHU304_tempReal;
    // 数据点"实时湿度A4"对应的存储数据
    protected static int data_AHU304_humiReal;
    // 数据点"温度设定值A4"对应的存储数据
    protected static int data_AHU304_tempSet;
    // 数据点"湿度设定值A4"对应的存储数据
    protected static int data_AHU304_humiSet;
    // 数据点"冷水阀开度A4"对应的存储数据
    protected static int data_AHU304_lengShuiFaKaiDu;
    // 数据点"热水阀开度A4"对应的存储数据
    protected static int data_AHU304_reShuiFaKaiDu;
    // 数据点"新风温度A4"对应的存储数据
    protected static int data_AHU304_xinFengWenDU;
    // 数据点"加湿器开度A4"对应的存储数据
    protected static int data_AHU304_jiaShiQIKaiDu;
    // 数据点"备用A4"对应的存储数据
    protected static int data_AHU304_beiYong;
    // 数据点"实时温度A5"对应的存储数据
    protected static int data_AHU305_tempReal;
    // 数据点"实时湿度A5"对应的存储数据
    protected static int data_AHU305_humiReal;
    // 数据点"温度设定值A5"对应的存储数据
    protected static int data_AHU305_tempSet;
    // 数据点"湿度设定值A5"对应的存储数据
    protected static int data_AHU305_humiSet;
    // 数据点"冷水阀开度A5"对应的存储数据
    protected static int data_AHU305_lengShuiFaKaiDu;
    // 数据点"热水阀开度A5"对应的存储数据
    protected static int data_AHU305_reShuiFaKaiDu;
    // 数据点"新风温度A5"对应的存储数据
    protected static int data_AHU305_xinFengWenDU;
    // 数据点"加湿器开度A5"对应的存储数据
    protected static int data_AHU305_jiaShiQIKaiDu;
    // 数据点"备用A5"对应的存储数据
    protected static int data_AHU305_beiYong;
    // 数据点"实时温度A6"对应的存储数据
    protected static int data_AHU306_tempReal;
    // 数据点"实时湿度A6"对应的存储数据
    protected static int data_AHU306_humiReal;
    // 数据点"温度设定值A6"对应的存储数据
    protected static int data_AHU306_tempSet;
    // 数据点"湿度设定值A6"对应的存储数据
    protected static int data_AHU306_humiSet;
    // 数据点"冷水阀开度A6"对应的存储数据
    protected static int data_AHU306_lengShuiFaKaiDu;
    // 数据点"热水阀开度A6"对应的存储数据
    protected static int data_AHU306_reShuiFaKaiDu;
    // 数据点"新风温度A6"对应的存储数据
    protected static int data_AHU306_xinFengWenDU;
    // 数据点"加湿器开度A6"对应的存储数据
    protected static int data_AHU306_jiaShiQIKaiDu;
    // 数据点"备用A6"对应的存储数据
    protected static int data_AHU306_beiYong;
    // 数据点"实时温度A7"对应的存储数据
    protected static int data_AHU307_tempReal;
    // 数据点"实时湿度A7"对应的存储数据
    protected static int data_AHU307_humiReal;
    // 数据点"温度设定值A7"对应的存储数据
    protected static int data_AHU307_tempSet;
    // 数据点"湿度设定值A7"对应的存储数据
    protected static int data_AHU307_humiSet;
    // 数据点"冷水阀开度A7"对应的存储数据
    protected static int data_AHU307_lengShuiFaKaiDu;
    // 数据点"热水阀开度A7"对应的存储数据
    protected static int data_AHU307_reShuiFaKaiDu;
    // 数据点"新风温度A7"对应的存储数据
    protected static int data_AHU307_xinFengWenDU;
    // 数据点"加湿器开度A7"对应的存储数据
    protected static int data_AHU307_jiaShiQIKaiDu;
    // 数据点"备用A7"对应的存储数据
    protected static int data_AHU307_beiYong;
    // 数据点"实时温度A8"对应的存储数据
    protected static int data_AHU308_tempReal;
    // 数据点"实时湿度A8"对应的存储数据
    protected static int data_AHU308_humiReal;
    // 数据点"温度设定值A8"对应的存储数据
    protected static int data_AHU308_tempSet;
    // 数据点"湿度设定值A8"对应的存储数据
    protected static int data_AHU308_humiSet;
    // 数据点"冷水阀开度A8"对应的存储数据
    protected static int data_AHU308_lengShuiFaKaiDu;
    // 数据点"热水阀开度A8"对应的存储数据
    protected static int data_AHU308_reShuiFaKaiDu;
    // 数据点"新风温度A8"对应的存储数据
    protected static int data_AHU308_xinFengWenDU;
    // 数据点"加湿器开度A8"对应的存储数据
    protected static int data_AHU308_jiaShiQIKaiDu;
    // 数据点"备用A8"对应的存储数据
    protected static int data_AHU308_beiYong;
    // 数据点"实时温度P1"对应的存储数据
    protected static int data_PAU301_tempReal;
    // 数据点"实时湿度P1"对应的存储数据
    protected static int data_PAU301_humiReal;
    // 数据点"温度设定值P1"对应的存储数据
    protected static int data_PAU301_tempSet;
    // 数据点"湿度设定值P1"对应的存储数据
    protected static int data_PAU301_humiSet;
    // 数据点"冷水阀开度P1"对应的存储数据
    protected static int data_PAU301_lengShuiFaKaiDu;
    // 数据点"热水阀开度P1"对应的存储数据
    protected static int data_PAU301_reShuiFaKaiDu;
    // 数据点"新风温度P1"对应的存储数据
    protected static int data_PAU301_xinFengWenDU;
    // 数据点"加湿器开度P1"对应的存储数据
    protected static int data_PAU301_jiaShiQIKaiDu;
    // 数据点"备用P1"对应的存储数据
    protected static int data_PAU301_beiYong;
    // 数据点"实时温度P2"对应的存储数据
    protected static int data_PAU302_tempReal;
    // 数据点"实时湿度P2"对应的存储数据
    protected static int data_PAU302_humiReal;
    // 数据点"温度设定值P2"对应的存储数据
    protected static int data_PAU302_tempSet;
    // 数据点"湿度设定值P2"对应的存储数据
    protected static int data_PAU302_humiSet;
    // 数据点"冷水阀开度P2"对应的存储数据
    protected static int data_PAU302_lengShuiFaKaiDu;
    // 数据点"热水阀开度P2"对应的存储数据
    protected static int data_PAU302_reShuiFaKaiDu;
    // 数据点"新风温度P2"对应的存储数据
    protected static int data_PAU302_xinFengWenDU;
    // 数据点"加湿器开度P2"对应的存储数据
    protected static int data_PAU302_jiaShiQIKaiDu;
    // 数据点"备用P2"对应的存储数据
    protected static int data_PAU302_beiYong;
    // 数据点"实时温度P3"对应的存储数据
    protected static int data_PAU303_tempReal;
    // 数据点"实时湿度P3"对应的存储数据
    protected static int data_PAU303_humiReal;
    // 数据点"温度设定值P3"对应的存储数据
    protected static int data_PAU303_tempSet;
    // 数据点"湿度设定值P3"对应的存储数据
    protected static int data_PAU303_humiSet;
    // 数据点"冷水阀开度P3"对应的存储数据
    protected static int data_PAU303_lengShuiFaKaiDu;
    // 数据点"热水阀开度P3"对应的存储数据
    protected static int data_PAU303_reShuiFaKaiDu;
    // 数据点"新风温度P3"对应的存储数据
    protected static int data_PAU303_xinFengWenDU;
    // 数据点"加湿器开度P3"对应的存储数据
    protected static int data_PAU303_jiaShiQIKaiDu;
    // 数据点"备用P3"对应的存储数据
    protected static int data_PAU303_beiYong;
    // 数据点"实时温度P4"对应的存储数据
    protected static int data_PAU304_tempReal;
    // 数据点"实时湿度P4"对应的存储数据
    protected static int data_PAU304_humiReal;
    // 数据点"温度设定值P4"对应的存储数据
    protected static int data_PAU304_tempSet;
    // 数据点"湿度设定值P4"对应的存储数据
    protected static int data_PAU304_humiSet;
    // 数据点"冷水阀开度P4"对应的存储数据
    protected static int data_PAU304_lengShuiFaKaiDu;
    // 数据点"热水阀开度P4"对应的存储数据
    protected static int data_PAU304_reShuiFaKaiDu;
    // 数据点"新风温度P4"对应的存储数据
    protected static int data_PAU304_xinFengWenDU;
    // 数据点"加湿器开度P4"对应的存储数据
    protected static int data_PAU304_jiaShiQIKaiDu;
    // 数据点"备用P4"对应的存储数据
    protected static int data_PAU304_beiYong;
    // 数据点"实时温度P5"对应的存储数据
    protected static int data_PAU305_tempReal;
    // 数据点"实时湿度P5"对应的存储数据
    protected static int data_PAU305_humiReal;
    // 数据点"温度设定值P5"对应的存储数据
    protected static int data_PAU305_tempSet;
    // 数据点"湿度设定值P5"对应的存储数据
    protected static int data_PAU305_humiSet;
    // 数据点"冷水阀开度P5"对应的存储数据
    protected static int data_PAU305_lengShuiFaKaiDu;
    // 数据点"热水阀开度P5"对应的存储数据
    protected static int data_PAU305_reShuiFaKaiDu;
    // 数据点"新风温度P5"对应的存储数据
    protected static int data_PAU305_xinFengWenDU;
    // 数据点"加湿器开度P5"对应的存储数据
    protected static int data_PAU305_jiaShiQIKaiDu;

    private TextView tv_data_AHU301_title;
    private TextView tv_data_AHU301_tempReal;
    private TextView tv_data_AHU301_humiReal;
    private TextView tv_data_AHU301_tempSet;
    private TextView tv_data_AHU301_humiSet;

    private TextView tv_data_AHU302_title;
    private TextView tv_data_AHU302_tempReal;
    private TextView tv_data_AHU302_humiReal;
    private TextView tv_data_AHU302_tempSet;
    private TextView tv_data_AHU302_humiSet;

    private TextView tv_data_AHU303_title;
    private TextView tv_data_AHU303_tempReal;
    private TextView tv_data_AHU303_humiReal;
    private TextView tv_data_AHU303_tempSet;
    private TextView tv_data_AHU303_humiSet;

    private TextView tv_data_AHU304_title;
    private TextView tv_data_AHU304_tempReal;
    private TextView tv_data_AHU304_humiReal;
    private TextView tv_data_AHU304_tempSet;
    private TextView tv_data_AHU304_humiSet;

    private TextView tv_data_AHU305_title;
    private TextView tv_data_AHU305_tempReal;
    private TextView tv_data_AHU305_humiReal;
    private TextView tv_data_AHU305_tempSet;
    private TextView tv_data_AHU305_humiSet;


    private TextView tv_data_AHU306_title;
    private TextView tv_data_AHU306_tempReal;
    private TextView tv_data_AHU306_humiReal;
    private TextView tv_data_AHU306_tempSet;
    private TextView tv_data_AHU306_humiSet;


    private TextView tv_data_AHU307_title;
    private TextView tv_data_AHU307_tempReal;
    private TextView tv_data_AHU307_humiReal;
    private TextView tv_data_AHU307_tempSet;
    private TextView tv_data_AHU307_humiSet;

    private TextView tv_data_AHU308_title;
    private TextView tv_data_AHU308_tempReal;
    private TextView tv_data_AHU308_humiReal;
    private TextView tv_data_AHU308_tempSet;
    private TextView tv_data_AHU308_humiSet;

    private TextView tv_data_PAU301_title;
    private TextView tv_data_PAU301_xinFengWenDU;
    private TextView tv_data_PAU301_tempReal;
    private TextView tv_data_PAU301_tempSet;

    private TextView tv_data_PAU302_title;
    private TextView tv_data_PAU302_xinFengWenDU;
    private TextView tv_data_PAU302_tempReal;
    private TextView tv_data_PAU302_tempSet;

    private TextView tv_data_PAU303_title;
    private TextView tv_data_PAU303_xinFengWenDU;
    private TextView tv_data_PAU303_tempReal;
    private TextView tv_data_PAU303_tempSet;

    private TextView tv_data_PAU304_title;
    private TextView tv_data_PAU304_xinFengWenDU;
    private TextView tv_data_PAU304_tempReal;
    private TextView tv_data_PAU304_tempSet;

    private TextView tv_data_PAU305_title;
    private TextView tv_data_PAU305_xinFengWenDU;
    private TextView tv_data_PAU305_tempReal;
    private TextView tv_data_PAU305_tempSet;

    private View ll_overview_ahu301;
    private View ll_overview_ahu302;
    private View ll_overview_ahu303;
    private View ll_overview_ahu304;
    private View ll_overview_ahu305;
    private View ll_overview_ahu306;
    private View ll_overview_ahu307;
    private View ll_overview_ahu308;
    private View ll_overview_pau301;
    private View ll_overview_pau302;
    private View ll_overview_pau303;
    private View ll_overview_pau304;
    private View ll_overview_pau305;

    private final String TAG = "krguang";
    private static final int CODE_DALIAN_HANDLER_UI = 107;
    private static final int AHU_HANDLER_UI = 106;

    private CustomApplication app;
    private Handler handler;

    /** The handler. */
    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CODE_DALIAN_HANDLER_UI:
                    updateUI();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_dalian_activity);

        initView();
        initEvent();
        setTopBar();

        app = (CustomApplication) getApplication();
    }

    private void initView() {

        tv_data_AHU301_title = (TextView) findViewById(R.id.tv_main_state_title_ahu301);
        tv_data_AHU301_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_ahu301);
        tv_data_AHU301_humiReal = (TextView) findViewById(R.id.tv_main_state_huminow_ahu301);
        tv_data_AHU301_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_ahu301);
        tv_data_AHU301_humiSet = (TextView) findViewById(R.id.tv_main_state_humiset_ahu301);

        tv_data_AHU302_title = (TextView) findViewById(R.id.tv_main_state_title_ahu302);
        tv_data_AHU302_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_ahu302);
        tv_data_AHU302_humiReal = (TextView) findViewById(R.id.tv_main_state_huminow_ahu302);
        tv_data_AHU302_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_ahu302);
        tv_data_AHU302_humiSet = (TextView) findViewById(R.id.tv_main_state_humiset_ahu302);

        tv_data_AHU303_title = (TextView) findViewById(R.id.tv_main_state_title_ahu303);
        tv_data_AHU303_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_ahu303);
        tv_data_AHU303_humiReal = (TextView) findViewById(R.id.tv_main_state_huminow_ahu303);
        tv_data_AHU303_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_ahu303);
        tv_data_AHU303_humiSet = (TextView) findViewById(R.id.tv_main_state_humiset_ahu303);

        tv_data_AHU304_title = (TextView) findViewById(R.id.tv_main_state_title_ahu304);
        tv_data_AHU304_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_ahu304);
        tv_data_AHU304_humiReal = (TextView) findViewById(R.id.tv_main_state_huminow_ahu304);
        tv_data_AHU304_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_ahu304);
        tv_data_AHU304_humiSet = (TextView) findViewById(R.id.tv_main_state_humiset_ahu304);

        tv_data_AHU305_title = (TextView) findViewById(R.id.tv_main_state_title_ahu305);
        tv_data_AHU305_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_ahu305);
        tv_data_AHU305_humiReal = (TextView) findViewById(R.id.tv_main_state_huminow_ahu305);
        tv_data_AHU305_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_ahu305);
        tv_data_AHU305_humiSet = (TextView) findViewById(R.id.tv_main_state_humiset_ahu305);

        tv_data_AHU306_title = (TextView) findViewById(R.id.tv_main_state_title_ahu306);
        tv_data_AHU306_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_ahu306);
        tv_data_AHU306_humiReal = (TextView) findViewById(R.id.tv_main_state_huminow_ahu306);
        tv_data_AHU306_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_ahu306);
        tv_data_AHU306_humiSet = (TextView) findViewById(R.id.tv_main_state_humiset_ahu306);

        tv_data_AHU307_title = (TextView) findViewById(R.id.tv_main_state_title_ahu307);
        tv_data_AHU307_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_ahu307);
        tv_data_AHU307_humiReal = (TextView) findViewById(R.id.tv_main_state_huminow_ahu307);
        tv_data_AHU307_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_ahu307);
        tv_data_AHU307_humiSet = (TextView) findViewById(R.id.tv_main_state_humiset_ahu307);

        tv_data_AHU308_title = (TextView) findViewById(R.id.tv_main_state_title_ahu308);
        tv_data_AHU308_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_ahu308);
        tv_data_AHU308_humiReal = (TextView) findViewById(R.id.tv_main_state_huminow_ahu308);
        tv_data_AHU308_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_ahu308);
        tv_data_AHU308_humiSet = (TextView) findViewById(R.id.tv_main_state_humiset_ahu308);

        tv_data_PAU301_title = (TextView) findViewById(R.id.tv_main_state_title_pau301);
        tv_data_PAU301_xinFengWenDU = (TextView) findViewById(R.id.tv_main_state_temp_new_wind_pau301);
        tv_data_PAU301_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_pau301);
        tv_data_PAU301_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_pau301);

        tv_data_PAU302_title = (TextView) findViewById(R.id.tv_main_state_title_pau302);
        tv_data_PAU302_xinFengWenDU = (TextView) findViewById(R.id.tv_main_state_temp_new_wind_pau302);
        tv_data_PAU302_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_pau302);
        tv_data_PAU302_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_pau302);

        tv_data_PAU303_title = (TextView) findViewById(R.id.tv_main_state_title_pau303);
        tv_data_PAU303_xinFengWenDU = (TextView) findViewById(R.id.tv_main_state_temp_new_wind_pau303);
        tv_data_PAU303_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_pau303);
        tv_data_PAU303_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_pau303);

        tv_data_PAU304_title = (TextView) findViewById(R.id.tv_main_state_title_pau304);
        tv_data_PAU304_xinFengWenDU = (TextView) findViewById(R.id.tv_main_state_temp_new_wind_pau304);
        tv_data_PAU304_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_pau304);
        tv_data_PAU304_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_pau304);

        tv_data_PAU305_title = (TextView) findViewById(R.id.tv_main_state_title_pau305);
        tv_data_PAU305_xinFengWenDU = (TextView) findViewById(R.id.tv_main_state_temp_new_wind_pau305);
        tv_data_PAU305_tempReal = (TextView) findViewById(R.id.tv_main_state_tempnow_pau305);
        tv_data_PAU305_tempSet = (TextView) findViewById(R.id.tv_main_state_tempset_pau305);

        ll_overview_ahu301 = findViewById(R.id.ll_ahu301);
        ll_overview_ahu302 = findViewById(R.id.ll_ahu302);
        ll_overview_ahu303 = findViewById(R.id.ll_ahu303);
        ll_overview_ahu304 = findViewById(R.id.ll_ahu304);
        ll_overview_ahu305 = findViewById(R.id.ll_ahu305);
        ll_overview_ahu306 = findViewById(R.id.ll_ahu306);
        ll_overview_ahu307 = findViewById(R.id.ll_ahu307);
        ll_overview_ahu308 = findViewById(R.id.ll_ahu308);

        ll_overview_pau301 = findViewById(R.id.ll_pau301);
        ll_overview_pau302 = findViewById(R.id.ll_pau302);
        ll_overview_pau303 = findViewById(R.id.ll_pau303);
        ll_overview_pau304 = findViewById(R.id.ll_pau304);
        ll_overview_pau305 = findViewById(R.id.ll_pau305);

    }//控件初始化

    private void initEvent() {

        ll_overview_ahu301.setOnClickListener(this);
        ll_overview_ahu302.setOnClickListener(this);
        ll_overview_ahu303.setOnClickListener(this);
        ll_overview_ahu304.setOnClickListener(this);
        ll_overview_ahu305.setOnClickListener(this);
        ll_overview_ahu306.setOnClickListener(this);
        ll_overview_ahu307.setOnClickListener(this);
        ll_overview_ahu308.setOnClickListener(this);

        ll_overview_pau301.setOnClickListener(this);
        ll_overview_pau302.setOnClickListener(this);
        ll_overview_pau303.setOnClickListener(this);
        ll_overview_pau304.setOnClickListener(this);
        ll_overview_pau305.setOnClickListener(this);
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
        if (data_AHU301_fengJiQueFeng){
            tv_data_AHU301_title.setBackgroundColor(Color.RED);
        }else if (data_AHU301_fengJiStatus){
            tv_data_AHU301_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU301_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU302_fengJiQueFeng){
            tv_data_AHU302_title.setBackgroundColor(Color.RED);
        }else if (data_AHU302_fengJiStatus){
            tv_data_AHU302_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU302_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU303_fengJiQueFeng){
            tv_data_AHU303_title.setBackgroundColor(Color.RED);
        }else if (data_AHU303_fengJiStatus){
            tv_data_AHU303_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU303_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU304_fengJiQueFeng){
            tv_data_AHU304_title.setBackgroundColor(Color.RED);
        }else if (data_AHU304_fengJiStatus){
            tv_data_AHU304_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU304_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU305_fengJiQueFeng){
            tv_data_AHU305_title.setBackgroundColor(Color.RED);
        }else if (data_AHU305_fengJiStatus){
            tv_data_AHU305_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU305_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU306_fengJiQueFeng){
            tv_data_AHU306_title.setBackgroundColor(Color.RED);
        }else if (data_AHU306_fengJiStatus){
            tv_data_AHU306_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU306_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU307_fengJiQueFeng){
            tv_data_AHU307_title.setBackgroundColor(Color.RED);
        }else if (data_AHU307_fengJiStatus){
            tv_data_AHU307_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU307_title.setBackgroundColor(Color.GRAY);
        }

        if (data_AHU308_fengJiQueFeng){
            tv_data_AHU308_title.setBackgroundColor(Color.RED);
        }else if (data_AHU308_fengJiStatus){
            tv_data_AHU308_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_AHU308_title.setBackgroundColor(Color.GRAY);
        }

        if (data_PAU301_fengJiQueFeng){
            tv_data_PAU301_title.setBackgroundColor(Color.RED);
        }else if (data_PAU301_fengJiStatus){
            tv_data_PAU301_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_PAU301_title.setBackgroundColor(Color.GRAY);
        }

        if (data_PAU302_fengJiQueFeng){
            tv_data_PAU302_title.setBackgroundColor(Color.RED);
        }else if (data_PAU302_fengJiStatus){
            tv_data_PAU302_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_PAU302_title.setBackgroundColor(Color.GRAY);
        }

        if (data_PAU303_fengJiQueFeng){
            tv_data_PAU303_title.setBackgroundColor(Color.RED);
        }else if (data_PAU303_fengJiStatus){
            tv_data_PAU303_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_PAU303_title.setBackgroundColor(Color.GRAY);
        }

        if (data_PAU304_fengJiQueFeng){
            tv_data_PAU304_title.setBackgroundColor(Color.RED);
        }else if (data_PAU304_fengJiStatus){
            tv_data_PAU304_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_PAU304_title.setBackgroundColor(Color.GRAY);
        }

        if (data_PAU305_fengJiQueFeng){
            tv_data_PAU305_title.setBackgroundColor(Color.RED);
        }else if (data_PAU305_fengJiStatus){
            tv_data_PAU305_title.setBackgroundColor(Color.GREEN);
        }else {
            tv_data_PAU305_title.setBackgroundColor(Color.GRAY);
        }


        tv_data_AHU301_tempReal.setText(formatValue(data_AHU301_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU301_humiReal.setText(formatValue(data_AHU301_humiReal/10.0,0.1)+" RH");
        tv_data_AHU301_tempSet.setText(formatValue(data_AHU301_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU301_humiSet.setText(formatValue(data_AHU301_humiSet/10.0,0.1)+" RH");

        tv_data_AHU302_tempReal.setText(formatValue(data_AHU302_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU302_humiReal.setText(formatValue(data_AHU302_humiReal/10.0,0.1)+" RH");
        tv_data_AHU302_tempSet.setText(formatValue(data_AHU302_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU302_humiSet.setText(formatValue(data_AHU302_humiSet/10.0,0.1)+" RH");

        tv_data_AHU303_tempReal.setText(formatValue(data_AHU303_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU303_humiReal.setText(formatValue(data_AHU303_humiReal/10.0,0.1)+" RH");
        tv_data_AHU303_tempSet.setText(formatValue(data_AHU303_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU303_humiSet.setText(formatValue(data_AHU303_humiSet/10.0,0.1)+" RH");

        tv_data_AHU304_tempReal.setText(formatValue(data_AHU304_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU304_humiReal.setText(formatValue(data_AHU304_humiReal/10.0,0.1)+" RH");
        tv_data_AHU304_tempSet.setText(formatValue(data_AHU304_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU304_humiSet.setText(formatValue(data_AHU304_humiSet/10.0,0.1)+" RH");

        tv_data_AHU305_tempReal.setText(formatValue(data_AHU305_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU305_humiReal.setText(formatValue(data_AHU305_humiReal/10.0,0.1)+" RH");
        tv_data_AHU305_tempSet.setText(formatValue(data_AHU305_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU305_humiSet.setText(formatValue(data_AHU305_humiSet/10.0,0.1)+" RH");

        tv_data_AHU306_tempReal.setText(formatValue(data_AHU306_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU306_humiReal.setText(formatValue(data_AHU306_humiReal/10.0,0.1)+" RH");
        tv_data_AHU306_tempSet.setText(formatValue(data_AHU306_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU306_humiSet.setText(formatValue(data_AHU306_humiSet/10.0,0.1)+" RH");

        tv_data_AHU307_tempReal.setText(formatValue(data_AHU307_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU307_humiReal.setText(formatValue(data_AHU307_humiReal/10.0,0.1)+" RH");
        tv_data_AHU307_tempSet.setText(formatValue(data_AHU307_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU307_humiSet.setText(formatValue(data_AHU307_humiSet/10.0,0.1)+" RH");

        tv_data_AHU308_tempReal.setText(formatValue(data_AHU308_tempReal/10.0,0.1)+" ℃");
        tv_data_AHU308_humiReal.setText(formatValue(data_AHU308_humiReal/10.0,0.1)+" RH");
        tv_data_AHU308_tempSet.setText(formatValue(data_AHU308_tempSet/10.0,0.1)+" ℃");
        tv_data_AHU308_humiSet.setText(formatValue(data_AHU308_humiSet/10.0,0.1)+" RH");

        tv_data_PAU301_tempReal.setText(formatValue(data_PAU301_tempReal/10.0,0.1)+" ℃");
        tv_data_PAU301_tempSet.setText(formatValue(data_PAU301_tempSet/10.0,0.1)+" ℃");
        tv_data_PAU301_xinFengWenDU.setText(formatValue(data_PAU301_xinFengWenDU/10.0,0.1)+" ℃");

        tv_data_PAU302_tempReal.setText(formatValue(data_PAU302_tempReal/10.0,0.1)+" ℃");
        tv_data_PAU302_tempSet.setText(formatValue(data_PAU302_tempSet/10.0,0.1)+" ℃");
        tv_data_PAU302_xinFengWenDU.setText(formatValue(data_PAU302_xinFengWenDU/10.0,0.1)+" ℃");

        tv_data_PAU303_tempReal.setText(formatValue(data_PAU303_tempReal/10.0,0.1)+" ℃");
        tv_data_PAU303_tempSet.setText(formatValue(data_PAU303_tempSet/10.0,0.1)+" ℃");
        tv_data_PAU303_xinFengWenDU.setText(formatValue(data_PAU303_xinFengWenDU/10.0,0.1)+" ℃");

        tv_data_PAU304_tempReal.setText(formatValue(data_PAU304_tempReal/10.0,0.1)+" ℃");
        tv_data_PAU304_tempSet.setText(formatValue(data_PAU304_tempSet/10.0,0.1)+" ℃");
        tv_data_PAU304_xinFengWenDU.setText(formatValue(data_PAU304_xinFengWenDU/10.0,0.1)+" ℃");

        tv_data_PAU305_tempReal.setText(formatValue(data_PAU305_tempReal/10.0,0.1)+" ℃");
        tv_data_PAU305_tempSet.setText(formatValue(data_PAU305_tempSet/10.0,0.1)+" ℃");
        tv_data_PAU305_xinFengWenDU.setText(formatValue(data_PAU305_xinFengWenDU/10.0,0.1)+" ℃");
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
                if (dataKey.equals(KEY_AHU301_FENGJIYIQIDONG)) {
                    data_AHU301_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_ZHIBANSTATUS)) {
                    data_AHU301_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_DIANJIARE1)) {
                    data_AHU301_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_DIANJIARE2)) {
                    data_AHU301_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_DIANJIARE3)) {
                    data_AHU301_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_FENGJISTATUS)) {
                    data_AHU301_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_SHOUZIDONG)) {
                    data_AHU301_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_DONGXIAJI)) {
                    data_AHU301_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_ZHONGXIAOBAOJING)) {
                    data_AHU301_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_DIANJIAREGAOWEN)) {
                    data_AHU301_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_FENGJIQUEFENG)) {
                    data_AHU301_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_PAIFENGJIYIQIDONG)) {
                    data_AHU301_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_DIWENPANGUAN)) {
                    data_AHU301_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_MIEJUNYUNXING)) {
                    data_AHU301_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_FENGJIYIQIDONG)) {
                    data_AHU302_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_ZHIBANSTATUS)) {
                    data_AHU302_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_DIANJIARE1)) {
                    data_AHU302_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_DIANJIARE2)) {
                    data_AHU302_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_DIANJIARE3)) {
                    data_AHU302_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_FENGJISTATUS)) {
                    data_AHU302_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_SHOUZIDONG)) {
                    data_AHU302_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_DONGXIAJI)) {
                    data_AHU302_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_ZHONGXIAOBAOJING)) {
                    data_AHU302_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_DIANJIAREGAOWEN)) {
                    data_AHU302_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_FENGJIQUEFENG)) {
                    data_AHU302_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_PAIFENGJIYIQIDONG)) {
                    data_AHU302_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_DIWENPANGUAN)) {
                    data_AHU302_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_MIEJUNYUNXING)) {
                    data_AHU302_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_FENGJIYIQIDONG)) {
                    data_AHU303_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_ZHIBANSTATUS)) {
                    data_AHU303_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_DIANJIARE1)) {
                    data_AHU303_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_DIANJIARE2)) {
                    data_AHU303_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_DIANJIARE3)) {
                    data_AHU303_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_FENGJISTATUS)) {
                    data_AHU303_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_SHOUZIDONG)) {
                    data_AHU303_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_DONGXIAJI)) {
                    data_AHU303_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_ZHONGXIAOBAOJING)) {
                    data_AHU303_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_DIANJIAREGAOWEN)) {
                    data_AHU303_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_FENGJIQUEFENG)) {
                    data_AHU303_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_PAIFENGJIYIQIDONG)) {
                    data_AHU303_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_DIWENPANGUAN)) {
                    data_AHU303_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_MIEJUNYUNXING)) {
                    data_AHU303_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_FENGJIYIQIDONG)) {
                    data_AHU304_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_ZHIBANSTATUS)) {
                    data_AHU304_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_DIANJIARE1)) {
                    data_AHU304_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_DIANJIARE2)) {
                    data_AHU304_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_DIANJIARE3)) {
                    data_AHU304_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_FENGJISTATUS)) {
                    data_AHU304_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_SHOUZIDONG)) {
                    data_AHU304_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_DONGXIAJI)) {
                    data_AHU304_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_ZHONGXIAOBAOJING)) {
                    data_AHU304_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_DIANJIAREGAOWEN)) {
                    data_AHU304_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_FENGJIQUEFENG)) {
                    data_AHU304_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_PAIFENGJIYIQIDONG)) {
                    data_AHU304_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_DIWENPANGUAN)) {
                    data_AHU304_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_MIEJUNYUNXING)) {
                    data_AHU304_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_FENGJIYIQIDONG)) {
                    data_AHU305_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_ZHIBANSTATUS)) {
                    data_AHU305_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_DIANJIARE1)) {
                    data_AHU305_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_DIANJIARE2)) {
                    data_AHU305_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_DIANJIARE3)) {
                    data_AHU305_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_FENGJISTATUS)) {
                    data_AHU305_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_SHOUZIDONG)) {
                    data_AHU305_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_DONGXIAJI)) {
                    data_AHU305_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_ZHONGXIAOBAOJING)) {
                    data_AHU305_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_DIANJIAREGAOWEN)) {
                    data_AHU305_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_FENGJIQUEFENG)) {
                    data_AHU305_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_PAIFENGJIYIQIDONG)) {
                    data_AHU305_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_DIWENPANGUAN)) {
                    data_AHU305_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_MIEJUNYUNXING)) {
                    data_AHU305_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_FENGJIYIQIDONG)) {
                    data_AHU306_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_ZHIBANSTATUS)) {
                    data_AHU306_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_DIANJIARE1)) {
                    data_AHU306_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_DIANJIARE2)) {
                    data_AHU306_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_DIANJIARE3)) {
                    data_AHU306_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_FENGJISTATUS)) {
                    data_AHU306_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_SHOUZIDONG)) {
                    data_AHU306_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_DONGXIAJI)) {
                    data_AHU306_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_ZHONGXIAOBAOJING)) {
                    data_AHU306_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_DIANJIAREGAOWEN)) {
                    data_AHU306_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_FENGJIQUEFENG)) {
                    data_AHU306_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_PAIFENGJIYIQIDONG)) {
                    data_AHU306_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_DIWENPANGUAN)) {
                    data_AHU306_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_MIEJUNYUNXING)) {
                    data_AHU306_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_FENGJIYIQIDONG)) {
                    data_AHU307_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_ZHIBANSTATUS)) {
                    data_AHU307_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_DIANJIARE1)) {
                    data_AHU307_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_DIANJIARE2)) {
                    data_AHU307_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_DIANJIARE3)) {
                    data_AHU307_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_FENGJISTATUS)) {
                    data_AHU307_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_SHOUZIDONG)) {
                    data_AHU307_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_DONGXIAJI)) {
                    data_AHU307_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_ZHONGXIAOBAOJING)) {
                    data_AHU307_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_DIANJIAREGAOWEN)) {
                    data_AHU307_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_FENGJIQUEFENG)) {
                    data_AHU307_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_PAIFENGJIYIQIDONG)) {
                    data_AHU307_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_DIWENPANGUAN)) {
                    data_AHU307_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_MIEJUNYUNXING)) {
                    data_AHU307_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_FENGJIYIQIDONG)) {
                    data_AHU308_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_ZHIBANSTATUS)) {
                    data_AHU308_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_DIANJIARE1)) {
                    data_AHU308_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_DIANJIARE2)) {
                    data_AHU308_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_DIANJIARE3)) {
                    data_AHU308_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_FENGJISTATUS)) {
                    data_AHU308_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_SHOUZIDONG)) {
                    data_AHU308_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_DONGXIAJI)) {
                    data_AHU308_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_ZHONGXIAOBAOJING)) {
                    data_AHU308_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_DIANJIAREGAOWEN)) {
                    data_AHU308_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_FENGJIQUEFENG)) {
                    data_AHU308_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_PAIFENGJIYIQIDONG)) {
                    data_AHU308_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_DIWENPANGUAN)) {
                    data_AHU308_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_MIEJUNYUNXING)) {
                    data_AHU308_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_FENGJIYIQIDONG)) {
                    data_PAU301_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_ZHIBANSTATUS)) {
                    data_PAU301_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_DIANJIARE1)) {
                    data_PAU301_dianJiaRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_DIANJIARE2)) {
                    data_PAU301_dianJiaRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_DIANJIARE3)) {
                    data_PAU301_dianJiaRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_FENGJISTATUS)) {
                    data_PAU301_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_SHOUZIDONG)) {
                    data_PAU301_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_DONGXIAJI)) {
                    data_PAU301_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_ZHONGXIAOBAOJING)) {
                    data_PAU301_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_DIANJIAREGAOWEN)) {
                    data_PAU301_dianJiaReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_FENGJIQUEFENG)) {
                    data_PAU301_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_PAIFENGJIYIQIDONG)) {
                    data_PAU301_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_DIANYURE1)) {
                    data_PAU301_dianYuRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_DIANYURE2)) {
                    data_PAU301_dianYuRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_DIANYURE3)) {
                    data_PAU301_dianYuRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_DIANYUREGAOWEN)) {
                    data_PAU301_dianYuReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_DIWENPANGUAN)) {
                    data_PAU301_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_MIEJUNYUNXING)) {
                    data_PAU301_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_FENGJIYIQIDONG)) {
                    data_PAU302_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_ZHIBANSTATUS)) {
                    data_PAU302_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_FENGJISTATUS)) {
                    data_PAU302_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_SHOUZIDONG)) {
                    data_PAU302_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_DONGXIAJI)) {
                    data_PAU302_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_ZHONGXIAOBAOJING)) {
                    data_PAU302_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_FENGJIQUEFENG)) {
                    data_PAU302_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_PAIFENGJIYIQIDONG)) {
                    data_PAU302_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_DIANYURE1)) {
                    data_PAU302_dianYuRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_DIANYURE2)) {
                    data_PAU302_dianYuRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_DIANYURE3)) {
                    data_PAU302_dianYuRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_DIANYUREGAOWEN)) {
                    data_PAU302_dianYuReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_DIWENPANGUAN)) {
                    data_PAU302_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_MIEJUNYUNXING)) {
                    data_PAU302_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_FENGJIYIQIDONG)) {
                    data_PAU303_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_ZHIBANSTATUS)) {
                    data_PAU303_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_FENGJISTATUS)) {
                    data_PAU303_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_SHOUZIDONG)) {
                    data_PAU303_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_DONGXIAJI)) {
                    data_PAU303_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_ZHONGXIAOBAOJING)) {
                    data_PAU303_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_FENGJIQUEFENG)) {
                    data_PAU303_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_PAIFENGJIYIQIDONG)) {
                    data_PAU303_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_DIANYURE1)) {
                    data_PAU303_dianYuRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_DIANYURE2)) {
                    data_PAU303_dianYuRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_DIANYURE3)) {
                    data_PAU303_dianYuRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_DIANYUREGAOWEN)) {
                    data_PAU303_dianYuReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_DIWENPANGUAN)) {
                    data_PAU303_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_MIEJUNYUNXING)) {
                    data_PAU303_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_FENGJIYIQIDONG)) {
                    data_PAU304_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_ZHIBANSTATUS)) {
                    data_PAU304_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_FENGJISTATUS)) {
                    data_PAU304_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_SHOUZIDONG)) {
                    data_PAU304_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_DONGXIAJI)) {
                    data_PAU304_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_ZHONGXIAOBAOJING)) {
                    data_PAU304_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_FENGJIQUEFENG)) {
                    data_PAU304_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_PAIFENGJIYIQIDONG)) {
                    data_PAU304_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_DIANYURE1)) {
                    data_PAU304_dianYuRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_DIANYURE2)) {
                    data_PAU304_dianYuRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_DIANYURE3)) {
                    data_PAU304_dianYuRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_DIANYUREGAOWEN)) {
                    data_PAU304_dianYuReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_DIWENPANGUAN)) {
                    data_PAU304_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_MIEJUNYUNXING)) {
                    data_PAU304_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_FENGJIYIQIDONG)) {
                    data_PAU305_fengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_ZHIBANSTATUS)) {
                    data_PAU305_zhiBanStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_FENGJISTATUS)) {
                    data_PAU305_fengJiStatus = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_SHOUZIDONG)) {
                    data_PAU305_shouZiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_DONGXIAJI)) {
                    data_PAU305_dongXiaJi = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_ZHONGXIAOBAOJING)) {
                    data_PAU305_zhongXiaoBaoJing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_FENGJIQUEFENG)) {
                    data_PAU305_fengJiQueFeng = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_PAIFENGJIYIQIDONG)) {
                    data_PAU305_paiFengJiYiQiDong = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_DIANYURE1)) {
                    data_PAU305_dianYuRe1 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_DIANYURE2)) {
                    data_PAU305_dianYuRe2 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_DIANYURE3)) {
                    data_PAU305_dianYuRe3 = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_DIANYUREGAOWEN)) {
                    data_PAU305_dianYuReGaoWen = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_DIWENPANGUAN)) {
                    data_PAU305_diWenPanGuan = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_MIEJUNYUNXING)) {
                    data_PAU305_mieJunYunXing = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_ONLINE_FLAG)) {
                    data_online_flag = (Boolean) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU301_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU301_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU301_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU302_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU302_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU302_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU303_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU303_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU303_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU304_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU304_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU304_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU305_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU305_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU305_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU306_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU306_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU306_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU307_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU307_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU307_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_MIANBANTONGXUNZHUANGTAI1)) {

                    data_AHU308_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_MIANBANTONGXUNZHUANGTAI2)) {

                    data_AHU308_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_MIANBANTONGXUNZHUANGTAI3)) {

                    data_AHU308_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_MIANBANTONGXUNZHUANGTAI1)) {

                    data_PAU301_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_MIANBANTONGXUNZHUANGTAI2)) {

                    data_PAU301_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_MIANBANTONGXUNZHUANGTAI3)) {

                    data_PAU301_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_MIANBANTONGXUNZHUANGTAI1)) {

                    data_PAU302_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_MIANBANTONGXUNZHUANGTAI2)) {

                    data_PAU302_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_MIANBANTONGXUNZHUANGTAI3)) {

                    data_PAU302_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_MIANBANTONGXUNZHUANGTAI1)) {

                    data_PAU303_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_MIANBANTONGXUNZHUANGTAI2)) {

                    data_PAU303_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_MIANBANTONGXUNZHUANGTAI3)) {

                    data_PAU303_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_MIANBANTONGXUNZHUANGTAI1)) {

                    data_PAU304_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_MIANBANTONGXUNZHUANGTAI2)) {

                    data_PAU304_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_MIANBANTONGXUNZHUANGTAI3)) {

                    data_PAU304_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_MIANBANTONGXUNZHUANGTAI1)) {

                    data_PAU305_mianBanTongXunZhuangTai1 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_MIANBANTONGXUNZHUANGTAI2)) {

                    data_PAU305_mianBanTongXunZhuangTai2 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_MIANBANTONGXUNZHUANGTAI3)) {

                    data_PAU305_mianBanTongXunZhuangTai3 = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_TEMPREAL)) {

                    data_AHU301_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_HUMIREAL)) {

                    data_AHU301_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_TEMPSET)) {

                    data_AHU301_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_HUMISET)) {

                    data_AHU301_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_LENGSHUIFAKAIDU)) {

                    data_AHU301_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_RESHUIFAKAIDU)) {

                    data_AHU301_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_XINFENGWENDU)) {

                    data_AHU301_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_JIASHIQIKAIDU)) {

                    data_AHU301_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU301_BEIYONG)) {

                    data_AHU301_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_TEMPREAL)) {

                    data_AHU302_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_HUMIREAL)) {

                    data_AHU302_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_TEMPSET)) {

                    data_AHU302_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_HUMISET)) {

                    data_AHU302_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_LENGSHUIFAKAIDU)) {

                    data_AHU302_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_RESHUIFAKAIDU)) {

                    data_AHU302_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_XINFENGWENDU)) {

                    data_AHU302_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_JIASHIQIKAIDU)) {

                    data_AHU302_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU302_BEIYONG)) {

                    data_AHU302_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_TEMPREAL)) {

                    data_AHU303_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_HUMIREAL)) {

                    data_AHU303_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_TEMPSET)) {

                    data_AHU303_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_HUMISET)) {

                    data_AHU303_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_LENGSHUIFAKAIDU)) {

                    data_AHU303_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_RESHUIFAKAIDU)) {

                    data_AHU303_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_XINFENGWENDU)) {

                    data_AHU303_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_JIASHIQIKAIDU)) {

                    data_AHU303_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU303_BEIYONG)) {

                    data_AHU303_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_TEMPREAL)) {

                    data_AHU304_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_HUMIREAL)) {

                    data_AHU304_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_TEMPSET)) {

                    data_AHU304_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_HUMISET)) {

                    data_AHU304_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_LENGSHUIFAKAIDU)) {

                    data_AHU304_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_RESHUIFAKAIDU)) {

                    data_AHU304_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_XINFENGWENDU)) {

                    data_AHU304_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_JIASHIQIKAIDU)) {

                    data_AHU304_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU304_BEIYONG)) {

                    data_AHU304_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_TEMPREAL)) {

                    data_AHU305_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_HUMIREAL)) {

                    data_AHU305_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_TEMPSET)) {

                    data_AHU305_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_HUMISET)) {

                    data_AHU305_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_LENGSHUIFAKAIDU)) {

                    data_AHU305_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_RESHUIFAKAIDU)) {

                    data_AHU305_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_XINFENGWENDU)) {

                    data_AHU305_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_JIASHIQIKAIDU)) {

                    data_AHU305_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU305_BEIYONG)) {

                    data_AHU305_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_TEMPREAL)) {

                    data_AHU306_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_HUMIREAL)) {

                    data_AHU306_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_TEMPSET)) {

                    data_AHU306_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_HUMISET)) {

                    data_AHU306_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_LENGSHUIFAKAIDU)) {

                    data_AHU306_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_RESHUIFAKAIDU)) {

                    data_AHU306_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_XINFENGWENDU)) {

                    data_AHU306_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_JIASHIQIKAIDU)) {

                    data_AHU306_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU306_BEIYONG)) {

                    data_AHU306_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_TEMPREAL)) {

                    data_AHU307_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_HUMIREAL)) {

                    data_AHU307_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_TEMPSET)) {

                    data_AHU307_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_HUMISET)) {

                    data_AHU307_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_LENGSHUIFAKAIDU)) {

                    data_AHU307_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_RESHUIFAKAIDU)) {

                    data_AHU307_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_XINFENGWENDU)) {

                    data_AHU307_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_JIASHIQIKAIDU)) {

                    data_AHU307_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU307_BEIYONG)) {

                    data_AHU307_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_TEMPREAL)) {

                    data_AHU308_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_HUMIREAL)) {

                    data_AHU308_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_TEMPSET)) {

                    data_AHU308_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_HUMISET)) {

                    data_AHU308_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_LENGSHUIFAKAIDU)) {

                    data_AHU308_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_RESHUIFAKAIDU)) {

                    data_AHU308_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_XINFENGWENDU)) {

                    data_AHU308_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_JIASHIQIKAIDU)) {

                    data_AHU308_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_AHU308_BEIYONG)) {

                    data_AHU308_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_TEMPREAL)) {

                    data_PAU301_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_HUMIREAL)) {

                    data_PAU301_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_TEMPSET)) {

                    data_PAU301_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_HUMISET)) {

                    data_PAU301_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_LENGSHUIFAKAIDU)) {

                    data_PAU301_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_RESHUIFAKAIDU)) {

                    data_PAU301_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_XINFENGWENDU)) {

                    data_PAU301_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_JIASHIQIKAIDU)) {

                    data_PAU301_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU301_BEIYONG)) {

                    data_PAU301_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_TEMPREAL)) {

                    data_PAU302_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_HUMIREAL)) {

                    data_PAU302_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_TEMPSET)) {

                    data_PAU302_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_HUMISET)) {

                    data_PAU302_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_LENGSHUIFAKAIDU)) {

                    data_PAU302_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_RESHUIFAKAIDU)) {

                    data_PAU302_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_XINFENGWENDU)) {

                    data_PAU302_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_JIASHIQIKAIDU)) {

                    data_PAU302_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU302_BEIYONG)) {

                    data_PAU302_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_TEMPREAL)) {

                    data_PAU303_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_HUMIREAL)) {

                    data_PAU303_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_TEMPSET)) {

                    data_PAU303_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_HUMISET)) {

                    data_PAU303_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_LENGSHUIFAKAIDU)) {

                    data_PAU303_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_RESHUIFAKAIDU)) {

                    data_PAU303_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_XINFENGWENDU)) {

                    data_PAU303_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_JIASHIQIKAIDU)) {

                    data_PAU303_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU303_BEIYONG)) {

                    data_PAU303_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_TEMPREAL)) {

                    data_PAU304_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_HUMIREAL)) {

                    data_PAU304_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_TEMPSET)) {

                    data_PAU304_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_HUMISET)) {

                    data_PAU304_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_LENGSHUIFAKAIDU)) {

                    data_PAU304_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_RESHUIFAKAIDU)) {

                    data_PAU304_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_XINFENGWENDU)) {

                    data_PAU304_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_JIASHIQIKAIDU)) {

                    data_PAU304_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU304_BEIYONG)) {

                    data_PAU304_beiYong = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_TEMPREAL)) {

                    data_PAU305_tempReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_HUMIREAL)) {

                    data_PAU305_humiReal = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_TEMPSET)) {

                    data_PAU305_tempSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_HUMISET)) {

                    data_PAU305_humiSet = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_LENGSHUIFAKAIDU)) {

                    data_PAU305_lengShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_RESHUIFAKAIDU)) {

                    data_PAU305_reShuiFaKaiDu = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_XINFENGWENDU)) {

                    data_PAU305_xinFengWenDU = (Integer) map.get(dataKey);
                }
                if (dataKey.equals(KEY_PAU305_JIASHIQIKAIDU)) {

                    data_PAU305_jiaShiQIKaiDu = (Integer) map.get(dataKey);
                }
            }
        }

        mHandler.sendEmptyMessage(CODE_DALIAN_HANDLER_UI);
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
        mDevice.setSubscribe(ConstantUtil.DALIANYOUYI_PRODUCT_SECRET, false);
    }

    private void startActivityWithStringAHU(String data){

        Bundle bundle = new Bundle();
        bundle.putParcelable("mDevice",mDevice);

        Intent intent = new Intent(UnitDaLianActivity.this, AhuDeviceDataActivity.class);
        intent.putExtra("extra_data",data);
        Log.d(TAG, "startActivityWithStringAHU: 要传出去的device = "+mDevice);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void startActivityWithStringPAU(String data){

        Bundle bundle = new Bundle();
        bundle.putParcelable("GizWifiDevice",mDevice);

        Intent intent = new Intent(UnitDaLianActivity.this,PauDeviceDataActivity.class);
        intent.putExtra("extra_data",data);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ll_ahu301:
                startActivityWithStringAHU("AHU301");
                break;

            case R.id.ll_ahu302:
                startActivityWithStringAHU("AHU302");
                break;

            case R.id.ll_ahu303:
                startActivityWithStringAHU("AHU303");
                break;

            case R.id.ll_ahu304:
                startActivityWithStringAHU("AHU304");
                break;

            case R.id.ll_ahu305:
                startActivityWithStringAHU("AHU305");
                break;

            case R.id.ll_ahu306:
                startActivityWithStringAHU("AHU306");
                break;

            case R.id.ll_ahu307:
                startActivityWithStringAHU("AHU307");
                break;

            case R.id.ll_ahu308:
                startActivityWithStringAHU("AHU308");
                break;

            case R.id.ll_pau301:
                startActivityWithStringPAU("PAU301");
                break;

            case R.id.ll_pau302:
                startActivityWithStringPAU("PAU302");
                break;

            case R.id.ll_pau303:
                startActivityWithStringPAU("PAU303");
                break;

            case R.id.ll_pau304:
                startActivityWithStringPAU("PAU304");
                break;

            case R.id.ll_pau305:
                startActivityWithStringPAU("PAU305");
                break;

            default:
                break;
        }
    }
}
