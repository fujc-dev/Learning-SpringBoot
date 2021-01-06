<%--
  Created by IntelliJ IDEA.
  User: fjc.dane@gmail.com
  Date: 2021/1/4
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>演示JSON作为参数传递到控制器</title>
</head>
<body>
<form id="insertForm">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" id="username" name="username" value="123456"/></td>
        </tr>
        <tr>
            <td>备注</td>
            <td><input type="text" id="note" name="note" value="123456"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input id="submit" type="button" value="提交"/></td>
        </tr>
    </table>

</form>
</body>
</html>
<script src="./assets/js/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#submit").click(function () {
            let username = $("#username").val();
            let note = $("#note").val();
            let param = {username: username, note: note}
            $.ajax({
                type: "POST",
                url: "containers/requestJson",
                contentType: "application/json",
                data: JSON.stringify(param),
                success: function (data) {
                    console.log(data);
                },
                error: function (error) {
                    console.log(error);
                }
            });
        });
    });
</script>
