package com.zc58s.springbootinfdemo.jna.utils;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/4/16 14:40
 */
public class DateUtil {
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String dateToString(Date d, String format) {
        if (d == null){
            return "";
        }
        Hashtable h = new Hashtable();
        String javaFormat = new String();
        String s = format.toLowerCase();
        h = checkStr1(s,h);

        int intStart = 0;
        while (s.indexOf(Constants.SYMBOL_STROKE, intStart) != -1) {
            intStart = s.indexOf(Constants.SYMBOL_STROKE, intStart);
            h.put(new Integer(intStart), Constants.SYMBOL_STROKE);
            intStart++;
        }

        intStart = 0;
        while (s.indexOf(Constants.SYMBOL_LEFT_SLASH, intStart) != -1) {
            intStart = s.indexOf(Constants.SYMBOL_LEFT_SLASH, intStart);
            h.put(new Integer(intStart), Constants.SYMBOL_LEFT_SLASH);
            intStart++;
        }

        intStart = 0;
        while (s.indexOf(Constants.SYMBOL_SPACE, intStart) != -1) {
            intStart = s.indexOf(Constants.SYMBOL_SPACE, intStart);
            h.put(new Integer(intStart), Constants.SYMBOL_SPACE);
            intStart++;
        }

        intStart = 0;
        while (s.indexOf(Constants.SYMBOL_COLON, intStart) != -1) {
            intStart = s.indexOf(Constants.SYMBOL_COLON, intStart);
            h.put(new Integer(intStart), Constants.SYMBOL_COLON);
            intStart++;
        }
        h = checkStr2(s,h);
        int i = 0;
        while (h.size() != 0) {
            Enumeration e = h.keys();
            int n = 0;
            while (e.hasMoreElements()) {
                i = ((Integer) e.nextElement()).intValue();
                if (i >= n){
                    n = i;}
            }
            String temp = (String) h.get(new Integer(n));
            h.remove(new Integer(n));

            javaFormat = temp + javaFormat;
        }
        SimpleDateFormat df = new SimpleDateFormat(javaFormat, new DateFormatSymbols());

        return df.format(d);
    }

    public static final String DATE_FORMAT_MILLISECOND = "yyyyMMddHHmmssSSS";

    /**
     * 按指定的格式，把Date转换成String 如date为null,返回null
     *
     * @param date   Date参数
     * @param format 日期格式
     * @return String
     */
    public static String format(Date date, String format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }


    public static String formatByMillisecond() {
        Date date = new Date();
        return new SimpleDateFormat(DATE_FORMAT_MILLISECOND).format(date);
    }


    /**
     * 将指定格式的字符串转换为日期型
     *
     * @param strDate      - 日期
     * @param oracleFormat --oracle型日期格式
     * @return 转换得到的日期
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Date stringToDate(String strDate, String oracleFormat) {
        if (strDate == null) {
            return null;
        }
        Hashtable h = new Hashtable();
        String javaFormat = new String();
        String s = oracleFormat.toLowerCase();
        h = checkStr1(s,h);

        int intStart = 0;
        while (s.indexOf(Constants.SYMBOL_STROKE, intStart) != -1) {
            intStart = s.indexOf(Constants.SYMBOL_STROKE, intStart);
            h.put(new Integer(intStart), Constants.SYMBOL_STROKE);
            intStart++;
        }

        intStart = 0;
        while (s.indexOf(Constants.SYMBOL_LEFT_SLASH, intStart) != -1) {
            intStart = s.indexOf(Constants.SYMBOL_LEFT_SLASH, intStart);
            h.put(new Integer(intStart), Constants.SYMBOL_LEFT_SLASH);
            intStart++;
        }

        intStart = 0;
        while (s.indexOf(Constants.SYMBOL_SPACE, intStart) != -1) {
            intStart = s.indexOf(Constants.SYMBOL_SPACE, intStart);
            h.put(new Integer(intStart), Constants.SYMBOL_SPACE);
            intStart++;
        }

        intStart = 0;
        while (s.indexOf(Constants.SYMBOL_COLON, intStart) != -1) {
            intStart = s.indexOf(Constants.SYMBOL_COLON, intStart);
            h.put(new Integer(intStart), Constants.SYMBOL_COLON);
            intStart++;
        }

        h = checkStr2(s,h);

        int i = 0;
        while (h.size() != 0) {
            Enumeration e = h.keys();
            int n = 0;
            while (e.hasMoreElements()) {
                i = ((Integer) e.nextElement()).intValue();
                if (i >= n){
                    n = i;
                }
            }
            String temp = (String) h.get(new Integer(n));
            h.remove(new Integer(n));

            javaFormat = temp + javaFormat;
        }
        SimpleDateFormat df = new SimpleDateFormat(javaFormat);

        Date myDate = new Date();
        try {
            myDate = df.parse(strDate);
        } catch (Exception e) {
            return null;
        }

        return myDate;
    }
    private static Hashtable checkStr1(String s,Hashtable h){
        if (s.indexOf(Constants.DATE_YYYY_SM) != -1){
            h.put(new Integer(s.indexOf(Constants.DATE_YYYY_SM)), Constants.DATE_YYYY_SM);
        } else if (s.indexOf(Constants.DATE_YY_SM) != -1){
            h.put(new Integer(s.indexOf(Constants.DATE_YY_SM)), Constants.DATE_YY_SM);
        }
        if (s.indexOf(Constants.DATE_MM_SM) != -1){
            h.put(new Integer(s.indexOf(Constants.DATE_MM_SM)), Constants.DATE_MM);
        }

        if (s.indexOf(Constants.DATE_DD_SM) != -1){
            h.put(new Integer(s.indexOf(Constants.DATE_DD_SM)), Constants.DATE_DD_SM);
        }
        if (s.indexOf(Constants.DATE_HH24_SM) != -1){
            h.put(new Integer(s.indexOf(Constants.DATE_HH24_SM)), Constants.DATE_HH);
        }
        if (s.indexOf(Constants.DATE_MI_SM) != -1){
            h.put(new Integer(s.indexOf(Constants.DATE_MI_SM)),  Constants.DATE_MM_SM);
        }
        if (s.indexOf(Constants.DATE_SS_SM) != -1){
            h.put(new Integer(s.indexOf(Constants.DATE_SS_SM)), Constants.DATE_SS_SM);
        }
        return h;
    }
    private static Hashtable checkStr2(String s,Hashtable h){
        if (s.indexOf(Constants.DATE_YEAR) != -1){
            h.put(new Integer(s.indexOf(Constants.DATE_YEAR)), Constants.DATE_YEAR);
        }
        if (s.indexOf(Constants.DATE_MONTH) != -1){
            h.put(new Integer(s.indexOf(Constants.DATE_MONTH)), Constants.DATE_MONTH);
        }
        if (s.indexOf(Constants.DATE_DAY) != -1){
            h.put(new Integer(s.indexOf(Constants.DATE_DAY)), Constants.DATE_DAY);
        }
        if (s.indexOf(Constants.DATE_HOUR) != -1){
            h.put(new Integer(s.indexOf(Constants.DATE_HOUR)), Constants.DATE_HOUR);
        }
        if (s.indexOf(Constants.DATE_MINUTE) != -1){
            h.put(new Integer(s.indexOf(Constants.DATE_MINUTE)), Constants.DATE_MINUTE);
        }
        if (s.indexOf(Constants.DATE_SECOND) != -1){
            h.put(new Integer(s.indexOf(Constants.DATE_SECOND)), Constants.DATE_SECOND);
        }
        return h;
    }



}
