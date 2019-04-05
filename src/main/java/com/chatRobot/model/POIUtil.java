package com.chatRobot.model;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
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
    public static List<String[]> readExcel(MultipartFile file) throws IOException {
        //检查文件
        checkFile(file);
        //获得workbook工作簿对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组返回，所有行作为一个集合返回
        List<String[]> list = new ArrayList<>();
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
                    String[] cells = new String[row.getPhysicalNumberOfCells()];
                    // 循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
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
        //File file1 = new File("E://test//" + file.getOriginalFilename());
        //try{
        //    file.transferTo(file1);
        //}catch (IOException e){
        //    e.printStackTrace();
        //}
        Workbook workbook = null;
        try {
            InputStream is = file.getInputStream();
            //InputStream is = new FileInputStream(file1);
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
}
