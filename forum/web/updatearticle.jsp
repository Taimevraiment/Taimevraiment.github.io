<%-- 
    Document   : updatearticle
    Created on : 2018-5-25, 16:34:29
    Author     : Alex
--%>

<%@page import="beans.Article"%>
<%@page import="dao.ArticleDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>修改文章</title>
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
            String articleid = (String) request.getParameter("id");
            ArticleDao ad = new ArticleDao();
            Article article = new Article();
            article = ad.findArticleById(articleid);
            String content = article.getContent();
            String title = article.getTitle();
            if (request.getAttribute("message") != null) {
                out.print(request.getAttribute("message"));
            }
        %>
        <div id="ada">
            <form action="updateArticle?articleid=<%=articleid%>" method="post">
                标题：<br/><input type="text" name="title" value="<%=title%>"/><br/>
                内容：<br/>
                <textarea id="input" name="content" rows="4" style="width:100%"><%=content%></textarea>
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
