<%--
  Created by IntelliJ IDEA.
  User: FTKJ
  Date: 2020/6/29
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script>
    function sendEmail() {
        $.ajax({
            url: "/email",
            type: "get",
            data: {"email": $("#email").val()},
            dataType: "text",
            success: function (data) {
                if (data == 1) {
                    alert("发送成功！");
                } else {
                    alert("发送失败！");
                }
            }
        });
    }
</script>
<body>

<form action="/login/forget" method="post">
    用户名：<input type="text" value="" name="username"> <br><br>
    新密码：<input type="text" value="" name="newPs"> <br><br>
    邮箱：<input type="text" value="" id="email" name="email">
    <input onclick="sendEmail()" type="button" value="发送验证码" name="" class="btn btn-primary"> <br><br>
    验证码：<input type="text" value="" name="code"> <br><br>
    <input type="submit" value="修改" name=""> <br><br>
</form>

</body>
</html>
