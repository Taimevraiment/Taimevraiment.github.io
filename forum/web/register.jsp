<%-- 
    Document   : register
    Created on : 2018-5-26, 22:28:42
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>注册界面</title>
    </head>
    <style>
        body{
            background: linear-gradient( #a1f4e5, white, #a1f4e5);
        }
        #register{
            margin: 0 auto;
            margin-top: 150px;
            padding: 20px;
            background-color: #e1e2e9;
            border: #000 solid 2px;
            width: 302px;
        }
        #button{
            text-align: center;
            width:300px;
        }
    </style>
    <body>
        <%
            if (request.getAttribute("message") != null) {
                if (request.getAttribute("message") == "注册成功！3秒后返回论坛页面") {
                    response.setHeader("refresh", "3;index.jsp");
                }
                out.print(request.getAttribute("message"));
            }
            String rname = "";
            if (request.getAttribute("name") != null) {
                rname = (String) request.getAttribute("name");
            }
            if (request.getAttribute("message") != "注册成功！3秒后返回论坛页面") {
        %>
        <div id="register">
            <form action="registerCheck" method="post">
                请设置用户名：<input type="text" name="name" value="<%=rname%>"/><br/><br/>
                请设置密&ensp;&ensp;码：<input type="password" name="password"/><br/><br/>
                请确认密&ensp;&ensp;码：<input type="password" name="password2"/><br/><br/>
                简介：<br/><br/>
                <textarea id="input" name="remark" rows="4" style="width:300px"></textarea><br/><br/>
                <div id="button">
                    <input type="submit" value="注册"/>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
                    <input type="reset"/>
                </div>
            </form>
        </div>
        <%
            }
        %>
    </body>
</html>
