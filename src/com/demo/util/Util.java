package com.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;


public class Util {
    public static String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static String DBURL = "jdbc:mysql://localhost:3306/database?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    public static String DBUSER = "root";
    public static String PASSWORD = "root";
    
    public static Connection getConnection() throws Exception {
        Class.forName(DBDRIVER);
        return DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
    }
    
    public static void main(String[] args) {
        try {
            Connection conn = Util.getConnection();
            System.out.println("数据库连接成功！！！");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据库连接失败！！！");
        }
    }
    
    public static String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
    }
    
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS);
    }

    
    public static boolean isMessyCode(String strName) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("\\s*|\t*|\r*|\n*");
        java.util.regex.Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = 0;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
                chLength++;
            }
        }
        return count / chLength > 0.4;
    }

    
    public static String decode(javax.servlet.http.HttpServletRequest request, String parameterName) {
        String str;
        if ((str = request.getParameter(parameterName)) == null) {
            return null;
        }
        try {
            if (isMessyCode(str)) {
                str = new String(str.getBytes("ISO-8859-1"), "UTF-8");
            }
            if (isMessyCode(str)) {
                str = new String(str.getBytes("GB2312"), "UTF-8");
            }
            if (isMessyCode(str)) {
                str = new String(str.getBytes("GBK"), "UTF-8");
            }
            if (isMessyCode(str)) {
                str = new String(str.getBytes("UTF-8"), "ISO-8859-1");
            }
            if (isMessyCode(str)) {
                str = new String(str.getBytes("GB2312"), "ISO-8859-1");
            }
            if (isMessyCode(str)) {
                str = new String(str.getBytes("GBK"), "ISO-8859-1");
            }
            if (isMessyCode(str)) {
                str = new String(str.getBytes("UTF-8"), "GB2312");
            }
            if (isMessyCode(str)) {
                str = new String(str.getBytes("ISO-8859-1"), "GB2312");
            }
            if (isMessyCode(str)) {
                str = new String(str.getBytes("GBK"), "GB2312");
            }
            if (isMessyCode(str)) {
                str = new String(str.getBytes("UTF-8"), "GBK");
            }
            if (isMessyCode(str)) {
                str = new String(str.getBytes("ISO-8859-1"), "GBK");
            }
            if (isMessyCode(str)) {
                str = new String(str.getBytes("GB2312"), "GBK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(parameterName + "==" + str.trim());
        return str.trim();
    }
}
