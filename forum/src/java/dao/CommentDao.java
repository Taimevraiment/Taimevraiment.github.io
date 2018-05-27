/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.Comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class CommentDao {

    public void add(String userid, String articleid, String content) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "insert into comment (userid, articleid, content, createtime, updatetime) values (?,?,?,?,?) ";
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date t = new java.util.Date();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userid);
            ps.setString(2, articleid);
            ps.setString(3, content);
            ps.setString(4, df.format(t));
            ps.setString(5, df.format(t));
            ps.executeUpdate();
        } finally {
            JdbcUtil.free(null, ps, conn);
        }
    }
    //修改数据库用户记录的方法update()

    public void update(Comment comment) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "update comment set content=?,updatetime=? where id=? ";
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date t = new java.util.Date();
            ps = conn.prepareStatement(sql);
            ps.setString(1, comment.getContent());
            ps.setString(2, df.format(t));
            ps.setString(3, comment.getId());
            ps.executeUpdate();
        } finally {
            JdbcUtil.free(null, ps, conn);
        }
    }
    //删除数据库用户记录的方法delete()

    public void delete(String commentId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "delete from comment where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, commentId);
            ps.executeUpdate();
        } finally {
            JdbcUtil.free(null, ps, conn);
        }
    }
    //根据id查询用户的方法findArticleById()

    public Comment findCommentById(String commentId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Comment comment = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from comment where id=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, commentId);
            rs = ps.executeQuery();
            if (rs.next()) {
                comment = new Comment();
                comment.setId(rs.getString(1));
                comment.setUserid(rs.getString(2));
                comment.setArticleid(rs.getString(3));
                comment.setContent(rs.getString(4));
                comment.setCreatetime(rs.getString(5));
                comment.setUpdatetime(rs.getString(6));
            }
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
        return comment;
    }

    public boolean checkIsAuth(String commentId, String userid) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Comment comment = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select userid from comment where id=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, commentId);
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

    public List<Comment> QueryAll() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Comment> commentList = new ArrayList<Comment>();
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from comment order by createtime";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getString(1));
                comment.setUserid(rs.getString(2));
                comment.setArticleid(rs.getString(3));
                comment.setContent(rs.getString(4));
                comment.setCreatetime(rs.getString(5));
                comment.setUpdatetime(rs.getString(6));

                commentList.add(comment);
            }
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
        return commentList;
    }
}
