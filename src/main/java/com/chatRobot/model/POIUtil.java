package com.chatRobot.model;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author: sun xiaoming
 * @Title: POIUtil
 * @ProjectName: WebGLShop
 * @date: 2019/4/5 15:26
 * @desc: excel 读写类，入库
 */
public class POIUtil {
    private static Logger logger = Logger.getLogger(POIUtil.class);
    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    /*
     * 读入excel 文件解析后返回
     */
    public static List<Goods> readExcel(MultipartFile file) throws IOException {
        //检查文件
        checkFile(file);
        //获得workbook工作簿对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组返回，所有行作为一个集合返回
        List<Goods> list = new ArrayList<>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                // 获取当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                // 获取当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                // 获取当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                // 循环除了第一行的所有行
                for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    // 获取当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    // 获取当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    //String[] cells = new String[row.getPhysicalNumberOfCells()];
                    // 循环当前行 模板
                    Goods good = new Goods();
                    good.setGoodsId((int) row.getCell(0).getNumericCellValue());
                    good.setGoodsName(row.getCell(1).getStringCellValue());
                    good.setGoodsPrice(BigDecimal.valueOf(row.getCell(2).getNumericCellValue()));
                    good.setGoodsDiscount((float)row.getCell(3).getNumericCellValue());
                    if( (int)row.getCell(4).getNumericCellValue() == 1){
                        good.setGoodsIsnew(true);
                    }else{
                        good.setGoodsIsnew(false);
                    }
                    if((int)row.getCell(5).getNumericCellValue() == 1){
                        good.setGoodsStatus("1");
                    }else{
                        good.setGoodsStatus("0");
                    }
                    good.setGoodsAmount((int)row.getCell(6).getNumericCellValue());
                    good.setGoodsDesc(row.getCell(7).getStringCellValue());
                    good.setGoodsUpdateTime(new Date());
                    good.setGoodsCreateTime(new Date());
                    //for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                    //    Cell cell = row.getCell(cellNum);
                    //    cells[cellNum] = getCellValue(cell);
                    //}
                    list.add(good);
                }
            }
        }
        return list;
    }

    public static void checkFile(MultipartFile file) throws IOException {
        if (file == null) {
            logger.error("文件不存在");
            throw new FileNotFoundException("文件不存在");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 判定文件是否为excel文件
        if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
            logger.error(fileName + "不是excel文件");
            throw new IOException(fileName + "不是excel文件");
        }
    }

    public static Workbook getWorkBook(MultipartFile file) {
        Workbook workbook = null;
        try {
            InputStream is = file.getInputStream();
            workbook = WorkbookFactory.create(is);
            is.close();
        } catch (IOException e) {
            logger.info("文件流转为workbook对象异常：" + e.getMessage());
            System.out.println("文件流转为workbook对象异常：" + e.getMessage());
        }
        return workbook;
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        // 判断数据类型
        switch (cell.getCellType()) {
            case NUMERIC: // 数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case BOOLEAN:
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK:
                cellValue = "";
                break;
            case ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    /*
     * 输出excel文件输出
     */
    public static HSSFWorkbook export(String sheetName, String[] headers, String[] columns, List<Goods> lists) throws Exception {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setDefaultColumnWidth(11);
        HSSFCellStyle style = wb.createCellStyle();
        HSSFRow row = sheet.createRow(0);
        style.setAlignment(HorizontalAlignment.CENTER); // 文本单元格 水平居中
        style.setVerticalAlignment(VerticalAlignment.CENTER); //  垂直居中
        for (int i = 0; i < headers.length; i++) {
            HSSFCell headcell = row.createCell(i);
            headcell.setCellValue(headers[i]);
            headcell.setCellStyle(style); // 设置单元格格式
        }
        Iterator<Goods> it = lists.iterator();
        int rowIndex = 0;
        while (it.hasNext()) {
            rowIndex++;
            row = sheet.createRow(rowIndex);
            Goods t = it.next();
            Field[] fields = t.getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
                for (int j = 0; j < columns.length; j++) {
                    if (fieldName.equals(columns[j])) {
                        String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1); //  设置反射类名
                        Class<? extends Object> cls = t.getClass();
                        Method getMethod = cls.getMethod(getMethodName, new Class[]{});
                        Object val = getMethod.invoke(t, new Object[]{});
                        String textVal;
                        if (null != val) {
                            textVal = val.toString();
                        } else {
                            textVal = null;
                        }
                        HSSFCell hssfCell = row.createCell(j);
                        hssfCell.setCellValue(textVal);
                        hssfCell.setCellStyle(style);
                    }
                }
            }
        }
        return wb;
    }
}
