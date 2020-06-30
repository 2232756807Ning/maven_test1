package com.nwq.controller;

import com.alibaba.fastjson.JSONObject;
import com.nwq.constants.SysConstant;
import com.nwq.entity.User;
import com.nwq.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.UUID;

/**
 * @Author: nwq
 * @Description:
 * @Date: 2020/6/30 18:16
 * @Version: 1.0
 */

@WebServlet("/qq/*")
public class QQServlet extends BaseServlet{

    private UserService userService = new UserService();


    /**
     * @Author nwq
     * @Description  点击qq登录，跳转到qq授权页面,获取code
     * @Date  2020/6/30 18:17
     * @Param [request, response]
     * @return void
     **/
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties prop = new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        //qq应用的AppID
        String appid = prop.getProperty("qq.AppID");
        //QQ授权成功后的回调地址
        String redirectUri = prop.getProperty("qq.redirect_uri");

        //固定格式
        //Step1：获取Authorization Code
        String url = "https://graph.qq.com/oauth2.0/authorize?response_type=code" +
                "&client_id=" + appid +
                "&redirect_uri=" + URLEncoder.encode(redirectUri) +
                "&state=1";

        // 重定向到QQ登录指定的地址进行QQ授权,授权成功后返回code
        response.sendRedirect(url);
    }


        protected void callBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Properties prop = new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        //qq应用的AppID
        String appid = prop.getProperty("qq.AppID");
        String appKey = prop.getProperty("qq.AppKey");
        //QQ授权成功后的回调地址
        String redirectUri = prop.getProperty("qq.redirect_uri");

        String code = request.getParameter("code");

        //固定格式
        //Step1：获取Authorization Code
        String url = "https://graph.qq.com/oauth2.0/token?grant_type=authorization_code" + "&client_id=" + appid +
                "&client_secret=" + appKey +
                "&code=" + code +
                "&redirect_uri=" + redirectUri;

        // 发送HttpClient请求获取access_token(临时票据)
        String access_token = userService.getAccessTokenForQQ(url);

        url = "https://graph.qq.com/oauth2.0/me?access_token=" + access_token;
        // 通过access_token获取openid(qq唯一标识符)
        String openid = userService.getQQOpenID(url);

        url = "https://graph.qq.com/user/get_user_info?access_token=" + access_token +
                "&oauth_consumer_key=" + appid +
                "&openid=" + openid;
        // 通过access_token和openid获取qq的用户信息（昵称，性别，头像...）
        JSONObject jsonObject = userService.getUserInfoForQQ(url);

        // 根据qqOpenid查询此用户原来有没有使用过qq登录
        User user = userService.findByQqOpenid(openid);
        // 说明该用户是第一次使用QQ登录
        if (user == null) {
            user = new User();
            // 用户的头像
            user.setPic(jsonObject.getString("figureurl_qq_2"));
            // 用户的昵称
            user.setRealName(jsonObject.getString("nickname"));
            // 随机用户名(11位随机字符串)
            user.setUsername(UUID.randomUUID().toString().substring(36 - 15));
            user.setQqOpenid(openid);
            // 注册一个新的用户
            userService.addUser(user);

            user = userService.findByQqOpenid(openid);
        }

        HttpSession session = request.getSession();
        session.setAttribute(SysConstant.SESSION_LOGIN_USER, user);
        response.sendRedirect("/jsp/common/main.jsp");
    }
}
