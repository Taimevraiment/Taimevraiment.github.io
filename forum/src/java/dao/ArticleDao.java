package dao;

import java.sql.*;
import beans.Article;
import java.text.*;
import java.util.*;

public class ArticleDao {
    //向数据库中添加用户记录的方法add()

    public void add(String content, String title, String userid) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "insert into article (content, userid, createtime, updatetime, title) values (?,?,?,?,?) ";
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date t = new java.util.Date();
            ps = conn.prepareStatement(sql);
            ps.setString(1, content);
            ps.setString(2, userid);
            ps.setString(3, df.format(t));
            ps.setString(4, df.format(t));
            ps.setString(5, title);
            ps.executeUpdate();
        } finally {
            JdbcUtil.free(null, ps, conn);
        }
    }
    //修改数据库用户记录的方法update()

    public void update(Article article) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "update article set content=?,updatetime=?,title=? where id=? ";
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date t = new java.util.Date();
            ps = conn.prepareStatement(sql);
            ps.setString(1, article.getContent());
            ps.setString(2, df.format(t));
            ps.setString(3, article.getTitle());
            ps.setString(4, article.getId());
            ps.executeUpdate();
        } finally {
            JdbcUtil.free(null, ps, conn);
        }
    }

    //删除数据库用户记录的方法delete()
    public void delete(String articleId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "delete from article where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, articleId);
            ps.executeUpdate();
        } finally {
            JdbcUtil.free(null, ps, conn);
        }
    }
    //根据id查询用户的方法findArticleById()

    public Article findArticleById(String articleId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Article article = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from article where id=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, articleId);
            rs = ps.executeQuery();
            if (rs.next()) {
                article = new Article();
                article.setId(rs.getString(1));
                article.setUserid(rs.getString(2));
                article.setContent(rs.getString(3));
                article.setCreatetime(rs.getString(4));
                article.setUpdatetime(rs.getString(5));
                article.setTitle(rs.getString(6));
            }
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
        return article;
    }

    public boolean checkIsAuth(String articleId, String userid) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Article article = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select userid from article where id=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, articleId);
            rs = ps.executeQuery();
            if (rs.next()) {
                String i = rs.getString(1);
                if (i.equals(userid)) {
                    return true;
                }
            }
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
        return false;
    }
    //查询全部用户的方法QueryAll()

    public List<Article> QueryAll() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Article> articleList = new ArrayList<Article>();
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from article order by createtime";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Article article = new Article();
                article.setId(rs.getString(1));
                article.setUserid(rs.getString(2));
                article.setContent(rs.getString(3));
                article.setCreatetime(rs.getString(4));
                article.setUpdatetime(rs.getString(5));
                article.setTitle(rs.getString(6));
                articleList.add(article);
            }
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
        return articleList;
    }
}
