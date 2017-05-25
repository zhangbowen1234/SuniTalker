package com.silver.chat.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 */
public class DateUtils {

    /*获取系统时间 格式为："yyyy/MM/dd "*/
    public static String getCurrentDate() {
        Date d = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
    }

    /*时间戳转换成字符窜*/
    public static String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat  sf = new SimpleDateFormat("yyyy年MM月dd日");
        return sf.format(d);
        }

   /*将字符串转为时间戳*/
    public static long getStringToDate(String time) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        try {
            date = sf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
        }


    /**
     * 将long得到-- 小时:分
     *
     * @param lefttime
     * @return 小时:分
     */
    public static String formatTimeSimple(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    public static String formatTime(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 年-月-日 小时:分钟
     *
     * @param lefttime
     * @return 年-月-日 小时:分钟
     */
    public static String formatDateAndTime_(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 年-月-日 小时:分钟
     *
     * @param lefttime
     * @return 年-月-日 小时:分钟
     */
    public static String formatDateAndTimes_(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 年-月-日 小时:分钟
     *
     * @param lefttime
     * @return 年-月-日 小时:分钟
     */
    public static String formatDateAndTime(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 年-月-日
     *
     * @param lefttime
     * @return 年-月-日
     */
    public static String formatDate_(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 年-月-日
     *
     * @param lefttime
     * @return 年-月-日
     */
    public static String formatDates_(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 年-月-日
     *
     * @param lefttime
     * @return 年-月-日
     */
    public static String formatDate(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        return sDateTime;
    }

    /**
     * 得到: 年-月-日 时
     *
     * @param time
     * @return 年-月-日
     */
    public static String getDateYYMMDDHH0(long time) {
        return new SimpleDateFormat("yyyy年M月dd HH时", Locale.CHINA).format(time);
    }

    /**
     * 得到: 年-月-日 时
     *
     * @param time
     * @return 年-月-日
     */
    public static String getDateYYMMDDHH1(long time) {
        return new SimpleDateFormat("yyyy/M/dd HH:00", Locale.CHINA).format(time);
    }

    /**
     * 得到: 年-月-日 时
     *
     * @param time
     * @return 年-月-日
     */
    public static String getDateHHmm(long time) {
        return new SimpleDateFormat("HH:mm", Locale.CHINA).format(time);
    }


    /**
     * 字符串转为long
     *
     * @param time     字符串时间,注意:格式要与template定义的一样
     * @param template 要格式化的格式:如time为09:21:12那么template为"HH:mm:ss"
     * @return long
     */
    public static long formatToLong(String time, String template) {
        SimpleDateFormat sdf = new SimpleDateFormat(template, Locale.CHINA);
        try {
            Date d = sdf.parse(time);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            long l = c.getTimeInMillis();
            return l;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 得到年份
     *
     * @param lefttime
     * @return 得到年份
     */
    public static int formatYear(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        int i = Integer.parseInt(sDateTime);
        return i;
    }

    /**
     * 得到月份
     *
     * @param lefttime
     * @return 得到月份
     */
    public static int formatMonth(long lefttime) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM", Locale.CHINA);
        String sDateTime = sdf.format(lefttime);
        int i = Integer.parseInt(sDateTime);
        return i;
    }

    /**
     * 得到月份
     *
     * @param lefttime
     * @return 得到月份
     */
    public static String formatMonthAndDay(long lefttime) {
        return new SimpleDateFormat("MM-dd", Locale.CHINA).format(lefttime);
    }


}
