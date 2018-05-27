<%-- 
    Document   : updateuser
    Created on : 2018-5-27, 0:10:31
    Author     : Alex
--%>

<%@page import="beans.User"%>
<%@page import="dao.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>修改用户信息</title>
    </head>
    <body>
        <%
            if (request.getAttribute("message") != null) {
                if (request.getAttribute("message") == "信息修改成功！3秒后返回论坛页面") {
                    response.setHeader("refresh", "3;index.jsp");
                }
                out.print(request.getAttribute("message"));
            }
            String id = (String) session.getAttribute("userid");
            UserDao ud = new UserDao();
            User user = ud.findUserById(id);
            String name = user.getName();
            String password = user.getPassword();
            String remark = user.getRemark();
            if (request.getAttribute("message") != "信息修改成功！3秒后返回论坛页面") {
        %>
        <form action="updateUser" method="post">
            请修改用户名：<input type="text" name="name" value="<%=name%>"/><br/>
            请修改密&ensp;&ensp;码：<input type="password" name="password" value="password"/><br/>
            请确认密&ensp;&ensp;码：<input type="password" name="password2"/><br/>
            请修改简介：<br/>
            <textarea id="input" name="remark" rows="4" style="width:500px"><%=remark%></textarea><br/>
            <input type="submit" value="修改"/>
            <input type="reset"/>
        </form>
        <%
            }
        %>
    </body>
</html>
