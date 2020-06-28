package com.nwq.controller;

import com.nwq.entity.User;
import com.nwq.service.UserService;
import org.apache.commons.beanutils.BeanUtils;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @auth nwq
 * @data 2020/6/22
 * @Description
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService=new UserService();

    /*
     * @description  查询
     * @author nwq
     * @date 2020/6/23
     * @params [request, response]
     * @return void
    */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> list=userService.listAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("/jsp/user/list.jsp").forward(request, response);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        userService.addUser(user);

        response.sendRedirect("/user/list");
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    
}
