/**
 * CsvFileUtil.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * ClassName: CSVFileUtil
 * </p>
 * <p>
 * Description: CSV文件工具
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class CsvFileUtil {

    /**
     * <p>
     * Field fis: 输入流
     * </p>
     */
    private FileInputStream fis = null;
    /**
     * <p>
     * Field isw: 输入流
     * </p>
     */
    private InputStreamReader isw = null;
    /**
     * <p>
     * Field br: 输入流
     * </p>
     */
    private BufferedReader br = null;

    /**
     * <p>
     * Description: 构造方法
     * </p>
     * 
     * @param filename 文件名
     * @param encode 编码
     * @throws FileNotFoundException 异常
     * @throws UnsupportedEncodingException 异常
     */
    public CsvFileUtil(String filename, String encode) throws FileNotFoundException, UnsupportedEncodingException {
        this.fis = new FileInputStream(filename);
        this.isw = new InputStreamReader(this.fis, encode);
        this.br = new BufferedReader(this.isw);
    }

    /**
     * <p>
     * Description: 从CSV文件流中读取一个CSV行。
     * </p>
     * 
     * @return 行数据
     * @throws IOException 异常
     */
    public String readLine() throws IOException {
        StringBuilder readLine = new StringBuilder();
        boolean bReadNext = true;
        while (bReadNext) {
            if (readLine.length() > 0) {
                readLine.append("\r\n");
            }
            String strReadLine = this.br.readLine();
            if (strReadLine == null) {
                return null;
            }
            readLine.append(strReadLine);
            if (countChar(readLine.toString(), '"', 0) % Constants.NUMBER_2 != 0) {
                bReadNext = true;
            } else {
                bReadNext = false;
            }
        }
        return readLine.toString();
    }

    /**
     * <p>
     * Description: 把CSV文件的一行转换成字符串数组。指定数组长度，不够长度的部分设置为null。
     * </p>
     * 
     * @param source 源
     * @param size 长度
     * @return 数组
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String[] fromCsvLine(String source, int size) {
        List tmpArray = fromCsvLinetoArray(source);
        String[] rtnArray;
        if (size < tmpArray.size()) {
            rtnArray = new String[tmpArray.size()];
        } else {
            rtnArray = new String[size];
        }
        tmpArray.toArray(rtnArray);
        return rtnArray;
    }

    /**
     * <p>
     * Description: 把CSV文件的一行转换成字符串数组。不指定数组长度。
     * </p>
     * 
     * @param source 源
     * @return 列表
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List fromCsvLinetoArray(String source) {
        if (source == null || source.length() == 0) {
            return new ArrayList();
        }
        int currentPosition = 0;
        int maxPosition = source.length();
        int nextComma;
        ArrayList rtnArray = new ArrayList();
        while (currentPosition < maxPosition) {
            nextComma = nextComma(source, currentPosition);
            rtnArray.add(nextToken(source, currentPosition, nextComma));
            currentPosition = nextComma + 1;
            if (currentPosition == maxPosition) {
                rtnArray.add("");
            }
        }
        return rtnArray;
    }

    /**
     * <p>
     * Description: 把字符串类型的数组转换成一个CSV行。（输出CSV文件的时候用）
     * </p>
     * 
     * @param strArray 数组
     * @return 字符串
     */
    public String toCsvLine(String[] strArray) {
        if (strArray == null) {
            return "";
        }
        StringBuilder cvsLine = new StringBuilder();
        for (int idx = 0; idx < strArray.length; idx++) {
            String item = addQuote(strArray[idx]);
            cvsLine.append(item);
            if (strArray.length - 1 != idx) {
                cvsLine.append(',');
            }
        }
        return cvsLine.toString();
    }

    /**
     * <p>
     * Description: 字符串类型的List转换成一个CSV行。（输出CSV文件的时候用）
     * </p>
     * 
     * @param strArrList 列表
     * @return 字符串
     */
    @SuppressWarnings("rawtypes")
    public String toCsvLine(List strArrList) {
        if (strArrList == null) {
            return "";
        }
        String[] strArray = new String[strArrList.size()];
        for (int idx = 0; idx < strArrList.size(); idx++) {
            strArray[idx] = (String) strArrList.get(idx);
        }
        return toCsvLine(strArray);
    }

    /**
     * 计算指定文字的个数。
     * 
     * @param str 文字列
     * @param c 文字
     * @param start 开始位置
     * @return 个数
     */
    private static int countChar(String str, char c, int start) {
        int i = 0;
        int index = str.indexOf(c, start);
        return index == -1 ? i : countChar(str, c, index + 1) + 1;
    }

    /**
     * 查询下一个逗号的位置。
     * 
     * @param source 文字列
     * @param st 检索开始位置
     * @return 下一个逗号的位置。
     */
    private static int nextComma(String source, int st) {
        int s = st;
        int maxPosition = source.length();
        boolean inquote = false;
        while (s < maxPosition) {
            char ch = source.charAt(s);
            if (!inquote && ch == ',') {
                break;
            } else if ('"' == ch) {
                inquote = !inquote;
            }
            s++;
        }
        return s;
    }

    /**
     * <p>
     * Description: 取得下一个字符串
     * </p>
     * 
     * @param source 源
     * @param st 开始
     * @param nextComma 下一个
     * @return 字符串
     */
    private static String nextToken(String source, int st, int nextComma) {
        StringBuilder strb = new StringBuilder();
        int next = st;
        while (next < nextComma) {
            char ch = source.charAt(next++);
            if (ch == '"') {
                if ((st + 1 < next && next < nextComma) && (source.charAt(next) == '"')) {
                    strb.append(ch);
                    next++;
                }
            } else {
                strb.append(ch);
            }
        }
        return strb.toString();
    }

    /**
     * 在字符串的外侧加双引号。如果该字符串的内部有双引号的话，把"转换成""。
     * 
     * @param item 字符串
     * @return 处理过的字符串
     */
    private static String addQuote(String item) {
        if (item == null || item.length() == 0) {
            return "\"\"";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('"');
        for (int idx = 0; idx < item.length(); idx++) {
            char ch = item.charAt(idx);
            if ('"' == ch) {
                sb.append("\"\"");
            } else {
                sb.append(ch);
            }
        }
        sb.append('"');
        return sb.toString();
    }
}
