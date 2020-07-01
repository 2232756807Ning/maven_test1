<%--
  Created by IntelliJ IDEA.
  User: FTKJ
  Date: 2020/7/1
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询会议</title>
</head>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>
<div id="right">
    <form method="post" action="/meeting/list">
        标题：<input type="text" name="title" value="${meeting.title}">
        状态：
        <select name="status" id="status">
            <option value="">请选择状态</option>
            <option value="0" <c:if test="${meeting.status==0}">selected</c:if> >未开始</option>
            <option value="1" <c:if test="${meeting.status==1}">selected</c:if> >进行中</option>
            <option value="2" <c:if test="${meeting.status==2}">selected</c:if> >已结束</option>
        </select>
        <input type="submit" value="查询" class="btn btn-primary">
    </form>

    <a href="/jsp/meeting/add.jsp" class="btn btn-primary">发布会议</a>

    <table class="table table-bordered">
        <tr>
            <td>主题</td>
            <td>部门</td>
            <td>状态</td>
            <td>开始时间</td>
            <td>会议内容</td>
        </tr>
        <c:forEach items="${list}" varStatus="status" var="meeting">
            <tr>
                <td><a href="/meeting/getMeetingById?id=${meeting.id}">${meeting.title}</a></td>
                <td>${meeting.deptName}</td>
                <td>
                    <c:choose>
                    <c:when test="${meeting.status==0}">未开始</c:when>
                    <c:when test="${meeting.status==1}">进行中</c:when>
                    <c:when test="${meeting.status==2}">已结束</c:when>
                        <c:otherwise></c:otherwise>
                    </c:choose>
                <td>
                    <fmt:parseDate var="startTime" value="${meeting.startTime}"
                                   pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                    <fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </td>
                <td>${meeting.content}</td>
            </tr>
        </c:forEach>

    </table>

    <a href="/meeting/list?page=1&title=${meeting.title}">首页</a>
    <a href="/meeting/list?page=${page.pageCurrent-1<=0?1:page.pageCurrent-1}&title=${meeting.title}">上一页</a>
    <a href="/meeting/list?page=${page.pageCurrent+1>=page.pageCount?page.pageCount:page.pageCurrent+1}&title=${meeting.title}">下一页</a>
    <a href="/meeting/list?page=${page.pageCount}&title=${meeting.title}">尾页</a>
    当前页:${page.pageCurrent},共 ${page.pageCount}页,共${page.count}条

</div>
</body>
</html>
