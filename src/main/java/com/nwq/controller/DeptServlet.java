package com.nwq.controller;

import com.alibaba.fastjson.JSON;
import com.nwq.entity.Dept;
import com.nwq.service.DeptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @auth nwq
 * @data 2020/6/22
 * @Description
 */

@WebServlet("/dept/*")
public class DeptServlet extends BaseServlet{

    private DeptService deptService = new DeptService();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        String name=request.getParameter("name");
    }
}
