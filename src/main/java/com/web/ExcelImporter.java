//package com.web;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//
//public class ExcelImporter {
//    public static void main(String[] args) {
//        try {
//            // 1. 加载Excel文件
//            InputStream inputStream = new FileInputStream("./src/main/resources/导入数据测试.xlsx");
//
//            // 2. 根据文件扩展名判断应该使用哪种Workbook对象
//            Workbook workbook;
//            if (args[0].endsWith(".xlsx")) {
//                workbook = new XSSFWorkbook(inputStream);
//            } else if (args[0].endsWith(".xls")) {
//                workbook = new HSSFWorkbook(inputStream);
//            } else {
//                throw new IllegalArgumentException("Unsupported file format");
//            }
//
//            // 3. 获取第一个工作表
//            Sheet sheet = workbook.getSheetAt(0);
//
//            // 4. 遍历行
//            for (Row row : sheet) {
//                // 5. 遍历单元格
//                for (Cell cell : row) {
//                    // 6. 读取单元格数据
//                    switch (cell.getCellType()) {
//                        case STRING:
//                            System.out.print(cell.getStringCellValue() + "\t");
//                            break;
//                        case NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + "\t");
//                            break;
//                        case BOOLEAN:
//                            System.out.print(cell.getBooleanCellValue() + "\t");
//                            break;
//                        default:
//                            System.out.print("\t");
//                    }
//                }
//                System.out.println(); // 换行
//            }
//
//            // 7. 关闭工作簿
//            workbook.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
