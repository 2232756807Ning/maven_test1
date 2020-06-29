package com.nwq.controller;

import com.nwq.entity.Dept;
import com.nwq.entity.User;
import com.nwq.service.DeptService;
import com.nwq.service.UserService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * @auth nwq
 * @data 2020/6/22
 * @Description
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserService();

    private DeptService deptService = new DeptService();

    /*
     * @description  查询
     * @author nwq
     * @date 2020/6/23
     * @params [request, response]
     * @return void
     */
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询条件
        String name = request.getParameter("username");
        name = name == null ? "" : name;
        //当前页
        String pageStr = request.getParameter("page");

        request.setAttribute("username", name);

        request.setAttribute("page", userService.listAll(name, pageStr));
        request.getRequestDispatcher("/jsp/user/list.jsp").forward(request, response);
    }

//    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //查询条件
//        String name = request.getParameter("username");
////        String sex = request.getParameter("sex");
//
//        name = name == null ? "" : name;
////        if (sex==null){
////            sex="-1";
////        }
//        // 选中 shift+F6 （替换/改名）
//        Page page=new Page();
//
//        String pageStr = request.getParameter("page");
////        Integer page=1;
//        if (!StringUtils.isEmpty(pageStr)) {
//            page.setPageCurrent(Integer.valueOf(pageStr));
////            page = Integer.valueOf(pageStr);
////            p.setPageCurrent(Integer.valueOf(page));
//        }
//
//        //总记录数
//        Integer count = userService.getCount();
//        page.setCount(count);
//        //总页数
////        page.setPageCount(count % page.getPageSize() == 0 ? count / page.getPageSize() : count / page.getPageSize() + 1);
////        p.setPageCount(count % 5 == 0 ? count / 5 : count / 5 + 1);
////        Integer pageCount = count % 5 == 0 ? count / 5 : count / 5 + 1;
////        p.setPageCount(pageCount);
//
////        List<User> list=userService.listAll(name,sex);
//        //总数据
//        List<User> list = userService.listAll(name, page);
//        request.setAttribute("list", list);
//        request.setAttribute("username", name);
//        request.setAttribute("page", page);
////        //当前页
////        request.setAttribute("page", page);
////        //总记录数
////        request.setAttribute("count", count);
////        //总页数
////        request.setAttribute("pageCount", pageCount);
////        request.setAttribute("sex", sex);
//        request.getRequestDispatcher("/jsp/user/list.jsp").forward(request, response);
//    }

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

    public void getUserByUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String name = request.getParameter("userName");
        //name==null || "".equals(name)
        if (StringUtils.isEmpty(name)) {
            return;
        }
        boolean b = userService.getUserByUserName(name);
        if (b) {
            out.write("1");
        } else {
            //已存在
            out.write("0");
        }
        out.close();
    }

    public void toUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //数据回显
        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id)) {
            return;
        }
        User user = userService.getUserById(Integer.valueOf(id));
        List<Dept> deptList = deptService.listAll();

        request.setAttribute("user", user);
        request.setAttribute("deptList", deptList);
        request.getRequestDispatcher("/jsp/user/update.jsp").forward(request, response);
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        User user=new User();
        user.setUsername(request.getParameter("username"));
        String username=request.getParameter("username");
        userService.update(user);
        response.sendRedirect("/user/list");
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        if (StringUtils.isEmpty(id)) {
            return;
        }
        userService.delete(Integer.valueOf(id));
        response.sendRedirect("/user/list");
    }


}
