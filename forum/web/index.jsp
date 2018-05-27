<%-- 
    Document   : index
    Created on : 2018-5-16, 11:53:40
    Author     : sun
--%>

<%@page import="beans.Comment"%>
<%@page import="dao.CommentDao"%>
<%@page import="dao.UserDao"%>
<%@page import="beans.Article"%>
<%@page import="java.util.List"%>
<%@page import="dao.ArticleDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>简易论坛</title>
    </head>
    <style>
        body{
            padding:0;
            margin:0;
            background: linear-gradient( #a1f4e5, white, #a1f4e5);
        }
        #head{
            font-size: 40px;
            font-family: "华文新魏";
            text-align: center;
            color: white;
        }
        .container{
            width: 960px;
            margin: 0 auto;
        }
        .header > .container{
            background-color: #718991;
            height: 50px;
        }
        .notice{
            float: left;
            width: 250px;
            padding-left: 10px;
        }
        .header a{           
            display: block;
            text-decoration: none;
            text-align: center;
            color: #000;
            float: right;
            width: 70px;
            height: 30px;
            line-height: 30px;
            margin:1px;
            border: 1px solid #fff;
            background: #c5dbf2;
        }
        #menu{
            position: absolute;
            top: 51px;
            right: 20px;
            margin-top: 1px;
            width: 70px;
            margin: 0;
            padding: 0;

        }
        #menu{
            display: none;
        }
        li {
            list-style-type: none;
            float: right;
            text-align: center;
            position: relative;
        }
        .header{
            background-color: #95cdea;
            position: fixed;
            top: 0;
            width: 100%;
            height: 100px;
            line-height: 50px;
            z-index: 10;
            margin: 0 auto;
        }
        .footer > .container{
            background-color: black;
            height: 100%;
        }
        .footer{
            position: relative;
            bottom: -100px;
            height: 60px;
            width: 100%;
            margin: 0 auto;
            z-index: 10;
            background-color: #abb6b4;
        }
        .main{
            width: 934px;
            position: relative;
            top:103px;
            padding: 10px 10px 10px 10px;
            background-color: #9197d2;
            margin: 0 auto;
            margin-bottom: 10px;
            border: #1d36f0 solid 3px;
        }
        h3{
            float: left;
        }
        h4{
            float:right;
        }
        p{
            clear: both;
        }
    </style>
    <script>
        function displaySubMenu(li) {
            var subMenu = li.getElementsByTagName("ul")[0];
            subMenu.style.display = "block";
        }

        function hideSubMenu(li) {
            var subMenu = li.getElementsByTagName("ul")[0];
            subMenu.style.display = "none";
        }

    </script>
    <body>
        <div class="header" style="text-align: right">
            <div class="container" id="head">简易论坛</div>
            <%
                if (session.getAttribute("name") == null) {
                    Cookie[] cs = request.getCookies();
                    String v = null;
                    String id = null;
                    if (cs != null) {
                        for (int i = 0; i < cs.length; i++) {//获取名称为username的Cookie对象值
                            if (cs[i].getName().equals("name")) {
                                v = cs[i].getValue();
                            } else if (cs[i].getName().equals("userid")) {
                                id = cs[i].getValue();
                            }
                        }
                    }
                    if (v != null) {//Cookie值不空，自动登录成功
                        session.setAttribute("name", v);
                        session.setAttribute("userid", id);

            %>
            <li onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)">
                你好 <%=v%>&ensp;&ensp;&ensp;&ensp;
                <ul id="menu">              
                    <a href="updateuser.jsp">修改信息</a>
                    <a href="exit">退出</a>
                    <a href="delUser">注销</a>
                </ul>
            </li>
            <%
            } else { //自动登录失败，转到登录页面
            %>
            <div class="notice">先登录，才能发表文章或评论</div>
            <a href="login.jsp">登录</a>
            <a href="register.jsp">注册</a><br/>
            <%
                }
            } else {
            %>         
            <li onmouseover="displaySubMenu(this)" onmouseout="hideSubMenu(this)">
                你好 <%=session.getAttribute("name")%>&ensp;&ensp;&ensp;&ensp;
                <ul id="menu">              
                    <li><a href="updateuser.jsp">修改信息</a></li>
                    <li><a href="exit">退出</a></li>
                    <li><a href="delUser">注销</a></li>
                </ul>
            </li>
            <%
                }
            %>
        </div>
        <div>
            <div class="main">
                <%
                    ArticleDao ad = new ArticleDao();
                    CommentDao cd = new CommentDao();
                    UserDao ud = new UserDao();
                    List<Article> al = ad.QueryAll();
                    List<Comment> cl = cd.QueryAll();
                    String userid = (String) session.getAttribute("userid");
                    if (!al.isEmpty()) {
                        for (Article a : al) {
                            String title = a.getTitle();
                            String uid = a.getUserid();
                            String username = ud.findUserById(uid).getName();
                            String createtime = a.getCreatetime();
                            String updatetime = a.getUpdatetime();
                            String content = a.getContent();
                            String articleid = a.getId();
                %>
                <h1><%=title%></h1>
                <h3>作者：<%=username%></h3>
                <h4>创建时间：<%=createtime%>  |  最后修改：<%=updatetime%></h4>
                <p><%=content%></p>
                <%
                    if (ad.checkIsAuth(articleid, userid)) {
                %>
                <div style="text-align: right">
                    <a href="addcomment.jsp?id=<%=a.getId()%>">评论</a> | <a href="updatearticle.jsp?id=<%=a.getId()%>">修改文章</a> | <a href="delArticle?id=<%=a.getId()%>">删除文章</a>
                </div>
                <%
                } else if (session.getAttribute("name") != null) {
                %>
                <div style="text-align: right">
                    <a href="addcomment.jsp?id=<%=a.getId()%>">评论</a>
                </div>
                <%
                    }
                    if (!cl.isEmpty()) {
                        for (Comment b : cl) {
                            String comcontent = b.getContent();
                            String comuid = b.getUserid();
                            String comusername = ud.findUserById(comuid).getName();
                            String comcreatetime = b.getCreatetime();
                            String comupdatetime = b.getUpdatetime();
                            String commentid = b.getId();
                            if (b.getArticleid().equals(articleid)) {
                %>
                <hr style="border:1px black dashed">
                <%=comcontent%>             
                <div style="text-align: right">
                    <h5>评论时间：<%=comcreatetime%>  |  最后修改：<%=comupdatetime%></h5>
                    <h5>评论者：<%=comusername%></h5>
                </div>
                <%
                    if (cd.checkIsAuth(commentid, userid)) {
                %>
                <div style="text-align: right">
                    <a href="updatecomment.jsp?id=<%=b.getId()%>">修改评论</a> | <a href="delComment?id=<%=b.getId()%>">删除评论</a>
                </div>
                <%
                            }
                        }
                    }
                %>
                <hr style="border:1px black solid">
                <%
                            }
                        }
                    }
                    if (session.getAttribute("name") != null) {
                %>
                <div style="text-align: right">
                    <a href="addarticle.jsp">发表文章</a>
                </div>
            </div>
            <%
                }
            %>
        </div>
        <div class="footer">
            <div class="container"></div>
        </div>
    </body>
</html>
