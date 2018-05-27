<%-- 
    Document   : comment
    Created on : 2018-5-25, 14:31:54
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>发表评论</title>
    </head>
    <style>
        #adc{
            background-color: #f0f693;
            border: #eefc10 solid 1px;
            padding: 20px;
            margin: 0 auto;
            width: 1000px;
        }
    </style>
    <body>
        <%
            String articleid = (String) request.getParameter("id");
            if (request.getAttribute("message") != null) {
                out.print(request.getAttribute("message"));
            }
        %>
        <div id="adc">
            <form action="addComment?articleid=<%=articleid%>" method="post">
                评论：<textarea id="input" name="content" rows="4" style="width:100%"></textarea>
                <input type="submit" value="提交" />
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
