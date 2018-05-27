<%-- 
    Document   : add
    Created on : 2018-5-23, 11:53:29
    Author     : sun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>发表文章</title>
    </head>
    <style>
        #ada{
            background-color: #dcc0c7;
            border: #ca12f5 solid 1px;
            padding: 20px;
            margin: 0 auto;
            width: 1000px;
        }
    </style>
    <body>
        <%
            if (request.getAttribute("message") != null) {
                out.print(request.getAttribute("message"));
            }
        %>
        <div id="ada">
            <form action="addArticle" method="post">
                标题：<br/><input type="text" name="title"/><br/>
                内容：<br/>
                <textarea id="input" name="content" rows="4" style="width:960px"></textarea><br/>
                <input type="submit" value="提交"/>
            </form>
        </div>
        <hr>
        <div id="out"></div>
        <script>
            var input = document.getElementById("input");
            var out = document.getElementById("out");
            input.onkeyup = function () {
                var c = input.value;
                out.innerHTML = c;
            }
            var c = input.value;
            out.innerHTML = c;
        </script>
    </body>

</html>
