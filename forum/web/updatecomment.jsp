<%-- 
    Document   : updatearticle
    Created on : 2018-5-25, 16:34:29
    Author     : Alex
--%>

<%@page import="beans.Comment"%>
<%@page import="dao.CommentDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>修改评论</title>
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
            String commentid = (String) request.getParameter("id");
            CommentDao cd = new CommentDao();
            Comment comment = new Comment();
            comment = cd.findCommentById(commentid);
            String content = comment.getContent();
            if (request.getAttribute("message") != null) {
                out.print(request.getAttribute("message"));
            }
        %>
        <div id="adc">
            <form action="updateComment?commentid=<%=commentid%>" method="post">
                评论：<textarea id="input" name="content" rows="4" style="width:100%"><%=content%></textarea>
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
