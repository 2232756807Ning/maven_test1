<%@page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<script src="${path}/static/js/jquery.js"></script>
<script>

    function changeImg() {
        $("#img").attr("src", "/img/getCode?nocache=" + new Date().getTime());
    }

</script>
<body>
<form action="/login/login" method="post">
    账号：<input type="text" value="" name="username"><br><br>
    密码：<input type="text" value="" name="password"><br><br>

    验证码：<input type="text" value="" name="code">
    <img src="/img/getCode" id="img" onclick="changeImg()">
    <br><br>

    <input type="checkbox" value="1" name="remember">记住我<br><br>
    <input type="submit" value="登录"><br><br>
    <a href="/jsp/login/forget.jsp">忘记密码</a><br><br>
    <a href="/weChat/login"><img src="pic/2.png"></a>
    <a href="/qq/login">QQ登录</a>

</form>

<%--<a href="/login">登录</a>--%>


</body>
</html>
