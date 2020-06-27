package com.nwq.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @auth nwq
 * @data 2020/6/22
 * @Description
 */
@WebServlet("/user/*")
public class UserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //   /user/list  /user/add  /user/delete
        String uri=request.getRequestURI();
        if (uri.endsWith("list")){
            list(request,response);
        }else if (uri.endsWith("add")){
            add(request,response);
        }

    }

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/user/list.jsp").forward(request, response);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    
}
