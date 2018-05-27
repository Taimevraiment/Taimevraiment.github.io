<%@ page  pageEncoding="UTF-8"%>
<html>
    <head><title>登录页面</title></head>
    <style>
        body{
            background: linear-gradient( #a1f4e5, white, #a1f4e5);
        }
        #login{
            margin: 0 auto;
            margin-top: 150px;
            padding: 20px;
            background-color: #e1e2e9;
            border: #000 solid 2px;
            width: 300px;
        }
        #button{
            text-align: center;
            width:300px;
        }
    </style>
    <body>
        <%
            if (request.getAttribute("message") != null) {
                out.print(request.getAttribute("message"));
            }
        %>
        <div id="login">
            <form action="loginCheck" method="post">
                请输入用户名：<input type="text" name="name"/><br/><br/>
                请输入密&ensp;&ensp;码：<input type="password" name="password"/><br/><br/>
                <input type="checkbox" name="is30" checked />30天内免登录<br/><br/>
                <div id="button">
                    <input type="submit" value="登录"/>&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
                    <input type="reset"/>
                </div>
            </form>
        </div>
    </body>
</html>
