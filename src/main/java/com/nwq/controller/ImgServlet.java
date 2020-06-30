package com.nwq.controller;

import com.nwq.constants.SysConstant;
import com.nwq.entity.User;
import com.nwq.enums.SysEnum;
import com.nwq.service.UserService;
import com.nwq.utils.ImgCodeUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * @Author: nwq
 * @Description:
 * @Date: 2020/6/29 11:17
 * @Version: 1.0
 */

@WebServlet("/img/*")
public class ImgServlet extends BaseServlet{

    private UserService userService = new UserService();

    public void getCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ImgCodeUtil imgCodeUtil=new ImgCodeUtil();
        HttpSession session=request.getSession();
        OutputStream os=response.getOutputStream();


        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/jpeg");

        BufferedImage img=imgCodeUtil.getImage();

        session.setAttribute(SysEnum.SESSION_LOGIN_CODE.getValue(),imgCodeUtil.getText());


        ImageIO.write(img,"jpeg",os);

        os.flush();
        os.close();

    }

    public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        //为解析类提供配置信息 创建文件上传工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //创建解析类的实例 传入工厂类获取文件上传对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //设置文件最大解析大小(单位：字节)
        sfu.setFileSizeMax(1024 * 1024 * 2);

        String suffix = "";
        try {
            List<FileItem> items = sfu.parseRequest(request);

            for (int i = 0; i < items.size(); i++) {
                FileItem item = items.get(i);
                //isFormField为true，表示这不是文件上传表单域
                if (!item.isFormField()) {
                    String name = item.getName();
                    String[] names = name.split("\\.");
                    name = names[names.length - 1];

                    //构造文件路径(保存到数据库的路劲)
                    //1时间戳
                    suffix = String.valueOf(System.currentTimeMillis() + "." + name);
                    String path = SysConstant.FILE_PREFIX + suffix;

                    //2uuid
                    String path2 = SysConstant.FILE_PREFIX + UUID.randomUUID().toString().replace("-", "") + "." + name;

                    //把文件上传到服务器上
                    File file = new File(path);
                    if (!file.exists()) {
                        //将文件写出到指定磁盘（即保存图片的服务器）
                        item.write(file);
                    }

                }

            }

            //获取session中的登陆信息
            User loginUser = (User) request.getSession().getAttribute(SysConstant.SESSION_LOGIN);

            //保存路径到数据库
            userService.updatePic(loginUser.getId(), suffix);

            out.write("1");
        } catch (Exception e) {
            e.printStackTrace();
            out.write("0");
        }

    }

    public void getHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = userService.getUserById(Integer.valueOf(id));
        String path = SysConstant.FILE_PREFIX + user.getPic();
        FileInputStream fis = new FileInputStream(path);
        OutputStream os = response.getOutputStream();
        byte[] b = new byte[1024];
        int len;
        while ((len = fis.read(b)) != -1) {
            os.write(b, 0, len);
        }
        os.flush();
        os.close();
        fis.close();

    }


}
