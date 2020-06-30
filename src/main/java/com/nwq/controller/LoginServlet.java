package com.nwq.controller;

import com.alibaba.fastjson.JSON;
import com.nwq.constants.SysConstant;
import com.nwq.entity.User;
import com.nwq.enums.SysEnum;
import com.nwq.service.UserService;
import com.nwq.utils.MdUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @auth nwq
 * @data 2020/6/21
 * @Description
 */

@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {

    private UserService userService = new UserService();

    protected void Login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String name = request.getParameter("username");
        String ps = request.getParameter("password");
        String remember = request.getParameter("remember");
        String code = request.getParameter("code");

        Object obj = session.getAttribute(SysEnum.SESSION_LOGIN_CODE.getValue());

        User user = userService.checkLogin(name, ps);
        System.out.println(user);
//        if (user == null){
        if (user == null || obj == null) {
//        if (user == null || obj== null || !(code.equals(obj.toString()))) {
            //账号或密码错误,没有验证码，验证码不正确
            response.sendRedirect("/index.jsp");
        } else {
            //把登陆信息放到session中
            session.setAttribute(SysConstant.SESSION_LOGIN, user);
            session.setMaxInactiveInterval(30 * 60);

            //选中记住我
            if ("1".equals(remember)) {
                Cookie c = new Cookie(SysEnum.COOKIE_LOGIN_NAME.getValue(), URLEncoder.encode(JSON.toJSONString(user), "utf-8"));
                c.setMaxAge(7 * 24 * 60 * 60);
                c.setPath("/");
                response.addCookie(c);
            }
            request.getRequestDispatcher("/jsp/common/main.jsp").forward(request, response);
        }
    }


    protected void forget(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String name = request.getParameter("username");
        String newPs = request.getParameter("newPs");
        String code = request.getParameter("code");

        //判断账号
        boolean b = userService.getUserByUserName(name);

        //判断验证码是否正确
        Object obj = session.getAttribute(SysConstant.SESSION_CODE);
        if (obj != null) {
            //把session中的验证码和前端传递过来的验证做比较
            if (code.equals(obj.toString()) && !b) {
                //说明验证码正确 & 账号存在
                userService.updatePs(name, MdUtil.md5(newPs));
                System.out.println("修改成功！");
                response.sendRedirect("/index.jsp");
            } else {
                System.out.println("账号不存在或者验证码不正确");
                response.sendRedirect("/jsp/login/forget.jsp");
            }
        }

    }


}
