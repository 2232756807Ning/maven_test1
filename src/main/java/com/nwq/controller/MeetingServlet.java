package com.nwq.controller;

import com.nwq.entity.Meeting;
import com.nwq.entity.Page;
import com.nwq.service.MeetingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author: nwq
 * @Description:
 * @Date: 2020/7/1 16:00
 * @Version: 1.0
 */

@WebServlet("/meeting/*")
public class MeetingServlet extends BaseServlet {
    private MeetingService meetingService = new MeetingService();

    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询条件
        String title = request.getParameter("title");
        String statusStr = request.getParameter("status");
        title = title == null ? "" : title;
        Integer status = (statusStr == null || statusStr == "") ? null : Integer.valueOf(statusStr);

        //当前页
        String pageStr = request.getParameter("page");
        Integer pageCurrent = pageStr == null ? 1 : Integer.valueOf(pageStr);

        //构造查询条件对象
        Meeting meeting = new Meeting();
        meeting.setTitle(title);
        meeting.setStatus(status);

        //总记录数
        Integer count = meetingService.count(meeting);

        Page page = new Page();
        page.setPageCurrent(pageCurrent);
        page.setCount(count);

        //数据
        List<Meeting> list = meetingService.listPage(meeting, page);
        request.setAttribute("list", list);
        request.setAttribute("meeting", meeting);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/jsp/meeting/list.jsp").forward(request, response);
    }
}
