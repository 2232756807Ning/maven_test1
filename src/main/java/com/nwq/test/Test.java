package com.nwq.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

/**
 * @Author: nwq
 * @Description:
 * @Date: 2020/7/1 11:02
 * @Version: 1.0
 */
public class Test {

    public static void main(String[] args) throws Exception{
        //创建excel表（2003）
//        Workbook wb=new HSSFWorkbook();
        // 2007
        Workbook wb=new XSSFWorkbook();

        //创建sheet（工作簿）
        Sheet sheet=wb.createSheet("工作簿");
        for (int i = 0; i <10 ; i++) {
            //创建行（row,record)
            Row r=sheet.createRow(i);
            r.setHeightInPoints(50);
            for (int j = 0; j <3 ; j++) {
                //创建列，并放入数据
                Cell c=r.createCell(j);
                c.setCellValue("张三"+j);
            }
        }


        FileOutputStream fos=new FileOutputStream("D:\\用户.xlsx");
        wb.write(fos);





    }


}
