/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.User;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex
 */
public class RegisterCheckServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try {
            request.setCharacterEncoding("utf-8");
            String name = (String) request.getParameter("name");
            String password = (String) request.getParameter("password");
            String password2 = (String) request.getParameter("password2");
            String remark = (String) request.getParameter("remark");
            UserDao ud = new UserDao();
            List<User> ul = ud.QueryAll();
            for (User u : ul) {
                String uname = u.getName();
                if (name.equals(uname)) {
                    request.setAttribute("message", "用户名已存在");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
            }
            if (name.equals("") || password.equals("")) {
                request.setAttribute("message", "用户名或密码不能为空！");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else if (password.equals(password2)) {
                User user = new User();
                user.setName(name);
                user.setPassword(password);
                user.setRemark(remark);
                ud.add(user);
                request.setAttribute("message", "注册成功！3秒后返回论坛页面");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "两次密码不一致！");
                request.setAttribute("name", name);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("message", "注册失败");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RegisterCheckServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(RegisterCheckServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
