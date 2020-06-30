package com.nwq.filters;

/**
 * @auth nwq
 * @data 2020/6/22
 * @Description
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.nwq.constants.SysConstant;
import com.nwq.entity.User;
import com.nwq.enums.SysEnum;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

@WebFilter("/*")
public class SysFliter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpServletResponse response=(HttpServletResponse) servletResponse;

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();

        //http://localhost:8080/user/list
        String uri = request.getRequestURI();
//        if (uri.endsWith("/") || uri.endsWith("/login")){
//
//        }

        //规定登录地址：http://localhost:8080/index.jsp
//        if (uri.endsWith("/index.jsp")) {
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null) {
//                for (Cookie c : cookies) {
//                    String cookieName = c.getName();
//                    if (SysEnum.COOKIE_LOGIN_NAME.getValue().equals(cookieName)) {
//                        String cookieValue = c.getValue();
//
//                        //把cookie中的登录信息放到session中
//                        cookieValue = URLDecoder.decode(cookieValue, "utf-8");
//                        User user = JSON.parseObject(cookieValue, new TypeReference<User>(){});
//                        session.setAttribute(SysConstant.SESSION_LOGIN, user);
//                        session.setMaxInactiveInterval(30 * 60);
//
//                        //如果有cookie.则直接跳转到成功页面
//                        filterChain.doFilter(request, response);
//                        request.getRequestDispatcher("/jsp/common/main.jsp").forward(request, response);
//                        return;
//                    }
//                }
//            }
//
//        } else if (uri.endsWith("/")
//                || uri.endsWith("/forget.jsp")
//                || uri.endsWith("/email")
//                || uri.endsWith("/menu")
//                || uri.contains("img")
//                || uri.contains("static")
//                || uri.contains("login")) {
//            // /login
//            //直接放行
//        } else {
//            //判断是否有session
//            Object obj = session.getAttribute(SysConstant.SESSION_LOGIN);
//            if (obj == null) {
//                //说明session中没有值
//                //非法登陆，强制跳转到登录页面重新登录
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
//            } else {
//                //session中有登陆信息
//
//            }
//        }


        filterChain.doFilter(request,response);
    }
}
