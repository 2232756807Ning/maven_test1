package com.nwq.controller;

import com.nwq.constants.SysConstant;
import com.nwq.utils.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: nwq
 * @Description:
 * @Date: 2020/6/30 10:54
 * @Version: 1.0
 */

@WebServlet("/email")
public class EmailServlet extends BaseServlet{

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();
        if (!(email == null || "".equals(email))) {
            //随机验证码
            String randomCode = Math.random() + "";
            randomCode = randomCode.substring(randomCode.length() - 5, randomCode.length() - 1);
            boolean b = EmailUtil.send(email, randomCode);
            if (b) {
                //把验证码放到session中
                session.setAttribute(SysConstant.SESSION_CODE, randomCode);
                session.setMaxInactiveInterval(60);
                out.write("1");
                return;
            }
        }
        out.write("0");
    }
}
