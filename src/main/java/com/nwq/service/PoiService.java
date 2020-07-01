package com.nwq.service;

import com.nwq.dao.UserDao;
import com.nwq.entity.User;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author: nwq
 * @Description:
 * @Date: 2020/7/1 11:01
 * @Version: 1.0
 */
public class PoiService {
    private UserDao userDao = new UserDao();

    public Workbook exportUser(){
        List<User> list=userDao.listForExport();

        //创建Excel 2007
        Workbook wb=new XSSFWorkbook();
        //创建sheet（工作簿）
        Sheet sheet=wb.createSheet("工作簿");

        //创建头（第一行）
        Row r1=sheet.createRow(0);
        r1.createCell(0).setCellValue("部门");
        r1.createCell(1).setCellValue("用户名");
        r1.createCell(2).setCellValue("真实姓名");
        r1.createCell(3).setCellValue("年龄");
        r1.createCell(4).setCellValue("性别");


//        String[] heads={"部门","用户名","真实姓名","年龄","性别"};
//
//        Row r1=sheet.createRow(0);
//        for (int i = 0; i <heads.length ; i++) {
//            r1.createCell(i).setCellValue(heads[i]);
//        }

        for (int i = 1; i <=list.size() ; i++) {
            Row r=sheet.createRow(i);
            User user=list.get(i-1);
            r.createCell(0).setCellValue(user.getDeptName()==null?"":user.getDeptName());
            r.createCell(1).setCellValue(user.getUsername()==null?"":user.getUsername());
            r.createCell(2).setCellValue(user.getRealName()==null?"":user.getRealName());
            r.createCell(3).setCellValue(user.getAge()==null?"":user.getAge().toString());
            r.createCell(4).setCellValue(user.getSexName()==null?"":user.getSexName());
        }

        try {
            FileOutputStream fos=new FileOutputStream("D:\\用户.xlsx");
            wb.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }


}
