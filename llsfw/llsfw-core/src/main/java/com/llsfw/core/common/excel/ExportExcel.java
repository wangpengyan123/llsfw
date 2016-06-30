/**
 * ExportExcel.java
 * Created at 2016-03-01
 * Created by Administrator
 * Copyright (C) 2016 LLSFW, All rights reserved.
 */
package com.llsfw.core.common.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.llsfw.core.common.Constants;
import com.llsfw.core.exception.SystemRuntimeException;

/**
 * <p>
 * ClassName: ExportExcel
 * </p>
 * <p>
 * Description: 导出excel(2007+)
 * </p>
 * <p>
 * Author: Administrator
 * </p>
 * <p>
 * Date: 2016年3月23日
 * </p>
 */
public class ExportExcel {

    /**
     * 日志
     */
    private static Logger log = LoggerFactory.getLogger(ExportExcel.class);

    /**
     * 字体
     */
    private static final String FONT_NAME = "Arial";

    /**
     * <p>
     * Field DATA: 样式名称
     * </p>
     */
    private static final String STYLE_DATA = "data";

    /**
     * 工作薄对象
     */
    private SXSSFWorkbook wb;

    /**
     * 工作表对象
     */
    private SXSSFSheet sheet;

    /**
     * 样式列表
     */
    private Map<String, CellStyle> styles;

    /**
     * 当前行号
     */
    private int rownum;

    /**
     * 构造函数
     * 
     * @param title 表格标题，传“空值”，表示无标题
     * @param headerList 表头列表
     * @param rowAccessWindowSize 刷新行数
     * @throws SystemRuntimeException
     */
    public ExportExcel(String title, List<ExportExcelHeaderMateData> headerList, int rowAccessWindowSize) {
        initialize(title, headerList, rowAccessWindowSize);
    }

    /**
     * 初始化函数
     * 
     * @param title 表格标题，传“空值”，表示无标题
     * @param headerList 表头列表
     * @param rowAccessWindowSize 刷新行数
     * @throws SystemRuntimeException
     */
    private void initialize(String title, List<ExportExcelHeaderMateData> headerList, int rowAccessWindowSize) {
        this.wb = new SXSSFWorkbook(rowAccessWindowSize); //有没有被关闭的风险
        this.wb.setCompressTempFiles(true); // 缓冲区文件压缩
        this.sheet = this.wb.createSheet("Export");
        this.styles = createStyles();
        // 创建标题
        if (StringUtils.isNotBlank(title)) {
            Row titleRow = this.sheet.createRow(this.rownum++);
            titleRow.setHeightInPoints(Constants.NUMBER_30);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellStyle(this.styles.get("title"));
            titleCell.setCellValue(title);
            this.sheet.addMergedRegion(new CellRangeAddress(titleRow.getRowNum(), titleRow.getRowNum(),
                    titleRow.getRowNum(), headerList.size() - 1));
        }
        // 创建表头
        if (headerList == null) {
            throw new SystemRuntimeException("headerList not null!");
        }
        Row headerRow = this.sheet.createRow(this.rownum++);
        headerRow.setHeightInPoints(Constants.NUMBER_16);
        for (int i = 0; i < headerList.size(); i++) {
            ExportExcelHeaderMateData headerItem = headerList.get(i);
            Cell cell = headerRow.createCell(i);
            cell.setCellStyle(this.styles.get("header"));
            String[] ss = StringUtils.split(headerItem.getHeaderName(), "**", Constants.NUMBER_2);
            if (ss.length == Constants.NUMBER_2) {
                cell.setCellValue(ss[0]);
                Comment comment = this.sheet.createDrawingPatriarch()
                        .createCellComment(new XSSFClientAnchor(0, 0, 0, 0, (short) Constants.NUMBER_3,
                                Constants.NUMBER_3, (short) Constants.NUMBER_5, Constants.NUMBER_6));
                comment.setString(new XSSFRichTextString(ss[1]));
                cell.setCellComment(comment);
            } else {
                cell.setCellValue(headerItem.getHeaderName());
            }
            this.sheet.setColumnWidth(i, headerItem.getHeaderWidth() * Constants.NUMBER_2 * Constants.NUMBER_256);
        }
        log.debug("Initialize success.");
    }

    /**
     * 创建表格样式
     * 
     * @return 样式列表
     */
    private Map<String, CellStyle> createStyles() {
        Map<String, CellStyle> stylesMap = new HashMap<String, CellStyle>();

        // 标题样式
        CellStyle style = this.wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        Font titleFont = this.wb.createFont();
        titleFont.setFontName(FONT_NAME);
        titleFont.setFontHeightInPoints((short) Constants.NUMBER_16);
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(titleFont);
        stylesMap.put("title", style);

        // 数据样式
        style = this.wb.createCellStyle();
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.GREY_50_PERCENT.getIndex());
        Font dataFont = this.wb.createFont();
        dataFont.setFontName(FONT_NAME);
        dataFont.setFontHeightInPoints((short) Constants.NUMBER_10);
        style.setFont(dataFont);
        stylesMap.put(STYLE_DATA, style);

        // 数据样式1
        style = this.wb.createCellStyle();
        style.cloneStyleFrom(stylesMap.get(STYLE_DATA));
        style.setAlignment(CellStyle.ALIGN_LEFT);
        stylesMap.put("data1", style);

        // 数据样式2
        style = this.wb.createCellStyle();
        style.cloneStyleFrom(stylesMap.get(STYLE_DATA));
        style.setAlignment(CellStyle.ALIGN_CENTER);
        stylesMap.put("data2", style);

        // 数据样式3
        style = this.wb.createCellStyle();
        style.cloneStyleFrom(stylesMap.get(STYLE_DATA));
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        stylesMap.put("data3", style);

        // 表头样式
        style = this.wb.createCellStyle();
        style.cloneStyleFrom(stylesMap.get(STYLE_DATA));
        style.setWrapText(true);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        Font headerFont = this.wb.createFont();
        headerFont.setFontName(FONT_NAME);
        headerFont.setFontHeightInPoints((short) Constants.NUMBER_10);
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(headerFont);
        stylesMap.put("header", style);

        // 返回
        return stylesMap;
    }

    /**
     * 添加一行
     * 
     * @return 行对象
     */
    public Row addRow() {
        return this.sheet.createRow(this.rownum++);
    }

    /**
     * 添加一个单元格
     * 
     * @param row 添加的行
     * @param column 添加列号
     * @param val 添加值
     * @return 单元格对象
     */
    public Cell addCell(Row row, int column, Object val) {
        return this.addCell(row, column, val, 0);
    }

    /**
     * <p>
     * Description: 添加一个单元格
     * </p>
     * 
     * @param row 添加的行
     * @param column 添加列号
     * @param val 添加值
     * @param align 对齐方式（1：靠左；2：居中；3：靠右）
     * @return 单元格
     */
    public Cell addCell(Row row, int column, Object val, int align) {
        Cell cell = row.createCell(column);
        CellStyle style = this.styles.get(STYLE_DATA + (align >= 1 && align <= Constants.NUMBER_3 ? align : ""));
        if (val == null) {
            cell.setCellValue("");
        } else if (val instanceof String) {
            cell.setCellValue((String) val);
        } else if (val instanceof Integer) {
            cell.setCellValue((Integer) val);
        } else if (val instanceof Long) {
            cell.setCellValue((Long) val);
        } else if (val instanceof Double) {
            cell.setCellValue((Double) val);
        } else if (val instanceof Float) {
            cell.setCellValue((Float) val);
        } else if (val instanceof Date) {
            DataFormat format = this.wb.createDataFormat();
            style.setDataFormat(format.getFormat("yyyy-MM-dd"));
            cell.setCellValue((Date) val);
        } else {
            cell.setCellValue(val.toString());
        }
        cell.setCellStyle(style);
        return cell;
    }

    /**
     * <p>
     * Description: 合并单元格，行列位置都是从0开始
     * </p>
     *
     * @param firstRow 起始行
     * @param lastRow 结束行
     * @param firstCol 其实列
     * @param lastCol 结束列
     */
    public void mergeCell(int firstRow, int lastRow, int firstCol, int lastCol) {
        this.sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }

    /**
     * 输出到客户端
     * 
     * @param response 响应对象
     * @param fileName 文件名
     * @throws IOException 异常
     */
    public void write(HttpServletResponse response, String fileName) throws IOException {
        OutputStream os = null;
        try {
            response.reset();
            response.setContentType("application/octet-stream; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + urlEncode(fileName));
            os = response.getOutputStream();
            this.wb.write(os);
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }
    }

    /**
     * 输出到文件
     * 
     * @param name 输出文件名
     * @throws IOException 异常
     */
    public void writeFile(String name) throws IOException {

        FileOutputStream os = null;
        try {
            os = new FileOutputStream(name);
            this.wb.write(os);
            os.flush();
        } finally {
            if (os != null) {
                os.close();
            }
        }

    }

    /**
     * 清理临时文件
     * 
     * @throws IOException 异常
     */
    public void dispose() throws IOException {
        this.wb.dispose();
        this.wb.close();
    }

    /**
     * URL 编码, Encode默认为UTF-8.
     * 
     * @param part 附件名称
     * @return 编码后的附件名称
     * @throws UnsupportedEncodingException 异常
     */
    private String urlEncode(String part) throws UnsupportedEncodingException {
        return URLEncoder.encode(part, Constants.DEF_CHARACTER_SET_ENCODING);
    }

    /**
     * <p>
     * Description: 测试方法
     * </p>
     * 
     * @param args 参数
     * @throws IOException 异常
     */
    //    public static void main(String[] args) throws IOException {
    //        ExportExcel ee = null;
    //        try { // 表头 
    //            List<ExportExcelHeaderMateData> headerList = Lists.newArrayList();
    //            for (int i = 1; i <= 10; i++) {
    //                headerList.add(new ExportExcelHeaderMateData("表头" + i, 10));
    //            } // 数据 
    //            List<String> dataRowList = Lists.newArrayList();
    //            for (int i = 1; i <= headerList.size(); i++) {
    //                dataRowList.add("数据" + i);
    //            } // 行数
    //            List<List<String>> dataList = Lists.newArrayList();
    //            for (int i = 1; i <= 100; i++) {
    //                dataList.add(dataRowList);
    //            } // 构造导出对象
    //            ee = new ExportExcel("表格标题", headerList, 100); // 创建行 
    //            for (int i = 0; i < dataList.size(); i++) {
    //                Row row = ee.addRow();
    //                for (int j = 0; j < dataList.get(i).size(); j++) {
    //                    ee.addCell(row, j, dataList.get(i).get(j));
    //                }
    //            } //输出 
    //            ee.writeFile("C:/AA/export.xlsx");
    //        } finally {
    //            if (ee != null) {
    //                ee.dispose();
    //            }
    //        }
    //    }

}
