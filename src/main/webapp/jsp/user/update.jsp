<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/6/23
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<script>
    function changeHead() {
        // 点击图片的时候让picFile产生点击的效果
        $("#picFile").click();
    }

    $(function () {
        $("#picFile").change(function () {
            // 构造文件上传form
            var formData = new FormData();
            formData.append("iconFile", document.getElementById("picFile").files[0]);

            $.ajax({
                url: "/img/upload",
                processData: false,      //默认为true,对请求传递的参数(formData)不做编码处理
                contentType: false,       //不对请求头做处理
                type: "post",
                data: formData,
                dataType: "",
                success: function (data) {
                    if (data == 1) {
                        $("#img-head").attr("src", "/img/getHead?id=" + $("#id").val() + "&nocache=" + new Date().getTime());
                    } else {
                        alert("上传失败");
                    }
                }
            });

        });
    });

</script>
<body>
<%@include file="../common/top.jsp" %>
<%@include file="../common/left.jsp" %>
<div id="right">

    <form action="/user/update" method="post">

        <%--隐藏域--%>
        <input type="text" name="id" value="${user.id}" style="display: none;">

            <%--后台以io流的方式返回图片数据--%>
            <img id="img-head" src="/img/getHead?id=${user.id}" onclick="changeHead()">
            <br><br>

            <!-- 真正的头像图片上传表单 -->
            <input type="file" id="picFile" style="display: none;"><br><br>

        <%--修改的时候用户名不能重复    --%>
        用户名：<input type="text" name="username" value="${user.username}" id="name"/><br><br>

        邮箱：<input type="text" name="email" value="${user.email}"/><br><br>
        真实姓名：<input type="text" name="realName" value="${user.realName}"/><br><br>
        年龄：<input type="text" name="age" value="${user.age}"/><br><br>

        性别：<input type="radio" name="sex" value="1"
                  <c:if test="${user.sex==1}">checked</c:if> />男
        <input type="radio" name="sex" value="0"
               <c:if test="${user.sex==0}">checked</c:if> />女<br><br>

        简介：<textarea name="description">${user.description}</textarea><br><br>

        部门：<select id="deptId" name="deptId">
        <c:forEach var="dept" items="${deptList}">
            <option value="${dept.id}"
                    <c:if test="${user.deptId==dept.id}">selected</c:if> >${dept.name}</option>
        </c:forEach>
    </select>
        <br><br>

        <input type="submit" value="修改"/>
        <input type="reset" value="重置"/>

    </form>


</div>
</body>
</html>
