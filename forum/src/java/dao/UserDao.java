package dao;

import java.sql.*;
import beans.User;
import java.util.*;

public class UserDao {
    //向数据库中添加用户记录的方法add()

    public void add(User user) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "insert into user (name, password, remark) values (?,?,?) ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRemark());
            ps.executeUpdate();
        } finally {
            JdbcUtil.free(null, ps, conn);
        }
    }
    //修改数据库用户记录的方法update()

    public void update(User user) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "update user set name=?,password=?,remark=? where id=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRemark());
            ps.setString(4, user.getId());
            ps.executeUpdate();
        } finally {
            JdbcUtil.free(null, ps, conn);
        }
    }
    //删除数据库用户记录的方法delete()

    public void delete(String userId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "delete from user where id=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ps.executeUpdate();
        } finally {
            JdbcUtil.free(null, ps, conn);
        }
    }
    //根据id查询用户的方法findUserById()

    public User findUserById(String userId) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from user where id=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getString(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRemark(rs.getString(4));
            }
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
        return user;
    }
    
    public String checkPassword(String name, String password) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select id,password from user where name=? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                String id = rs.getString(1);
                String p = rs.getString(2);
                if(p.equals(password)){
                    return id;
                }
            }
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
        return "";
    }
    //查询全部用户的方法QueryAll()

    public List<User> QueryAll() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<User>();
        try {
            conn = JdbcUtil.getConnection();
            String sql = "select * from user ";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getString(1));
                user.setName(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setRemark(rs.getString(4));
                userList.add(user);
            }
        } finally {
            JdbcUtil.free(rs, ps, conn);
        }
        return userList;
    }
}
