<%--
  Created by IntelliJ IDEA.
  User: FTKJ
  Date: 2020/6/29
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主界面</title>
</head>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>

<div id="right">
    <a href="/jsp/dept/add.jsp" class="btn btn-success">添加</a>
    <%--<a href="/poi/exportUser?username=${username}" class="btn btn-primary">导出Excel</a>--%>
    <a href="/poi/exportUser" class="btn btn-primary">导出Excel</a>

    <br><br>


    <%--<form action="/dept/dept" method="post">--%>
        <%--用户名： <input type="text" name="username" value="${username}">--%>

        <%--&lt;%&ndash;性别：<select name="sex" id="">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<option value="-1" <c:if test="${sex==-1}"></c:if> 请选择</option>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<option value="1" <c:if test="${sex==1}"></c:if>>男</option>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<option value="0" <c:if test="${sex==0}"></c:if>>女</option>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</select>&ndash;%&gt;--%>

        <%--<input type="submit" value="查询" class="btn btn-info">--%>
    <%--</form>--%>

    <table class="table table-bordered">
        <tr>
            <td>序号</td>
            <td>id</td>
            <td>部门名称</td>
        </tr>

        <c:forEach var="dept" items="${page.data}" varStatus="status">
            <tr>
                <td>${status.index+1}</td>
                <td>${dept.id}</td>
                <td>${dept.name}</td>


                <td>
                    <a href="/dept/toUpdate?id=${user.id}" class="btn btn-primary">修改</a>
                        <%--物理删除，逻辑删除--%>
                    <a href="/dept/delete?id=${user.id}" class="btn btn-danger">删除</a>

                </td>

            </tr>
        </c:forEach>

    </table>

    当前页：${page.pageCurrent}
    总页数：${page.pageCount}
    总记录数：${page.count}

    <a href="/dept/list?page=${page.pageCurrent-1>0?page.pageCurrent-1:1}&name=${username}">上一页</a>
    <a href="/dept/list?page=${page.pageCurrent+1>=page.pageCount?page.pageCount:page.pageCurrent+1}&name=${username}">下一页</a>

</div>
</body>
</html>
