package com.nwq.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @auth nwq
 * @data 2020/6/22
 * @Description
 */


public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //  /user/list   /user/add   /user/delete
        String uri = request.getRequestURI();
        //  user list
        String[] uriArr = uri.split("/");
        //得到 list或add或delete
        String method = uriArr[uriArr.length - 1];

//        UserServlet userServlet = new UserServlet();
//        DeptServlet deptServlet=new DeptServlet();

        //获取字节码对象有三种方式
//        Class clazz = userServlet.getClass();
//        Class clazz = deptServlet.getClass();

        //这里的this不是BaseServlet，谁调用service()就是谁
        //重点理解这里的this
        Class clazz = this.getClass();
        try {
            // （方法名成，参数1，参数2）
            Method m = clazz.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
            //暴力反射
            m.setAccessible(true);
            //执行方法
            m.invoke(this, request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
