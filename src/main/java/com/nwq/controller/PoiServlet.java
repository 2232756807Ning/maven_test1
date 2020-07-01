package com.nwq.controller;

import com.nwq.entity.User;
import com.nwq.service.PoiService;
import com.nwq.service.UserService;
import org.apache.catalina.LifecycleState;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * @Author: nwq
 * @Description:
 * @Date: 2020/7/1 11:24
 * @Version: 1.0
 */

@WebServlet("/poi/*")
public class PoiServlet extends BaseServlet {
    private PoiService poiService=new PoiService();

    protected void exportUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//        String username=request.getParameter("username");
//        username=username==null?"":username;

        poiService.exportUser();
//        Workbook wb = poiService.exportUser();

//        response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户.xlsx".getBytes("utf-8"), "iso-8859-1"));
//        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
//
//        OutputStream out = response.getOutputStream();
//        wb.write(out);
//
//        out.flush();
//        out.close();
    }
}
