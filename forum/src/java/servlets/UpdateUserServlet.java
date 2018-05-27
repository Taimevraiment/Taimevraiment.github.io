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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex
 */
public class UpdateUserServlet extends HttpServlet {

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
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            String name = (String) request.getParameter("name");
            String password = (String) request.getParameter("password");
            String password2 = (String) request.getParameter("password2");
            String remark = (String) request.getParameter("remark");
            String id = (String) request.getSession().getAttribute("userid");
            UserDao ud = new UserDao();
            List<User> ul = ud.QueryAll();
            for (User u : ul) {
                String uname = u.getName();
                if (name.equals(uname)) {
                    request.setAttribute("message", "用户名已存在");
                    request.getRequestDispatcher("updateuser.jsp").forward(request, response);
                }
            }
            if (name.equals("") || password.equals("")) {
                request.setAttribute("message", "用户名或密码不能为空！");
                request.getRequestDispatcher("updateuser.jsp").forward(request, response);
            } else if (password.equals(password2)) {
                User user = new User();
                user.setName(name);
                user.setPassword(password);
                user.setRemark(remark);
                user.setId(id);
                ud.update(user);
                request.getSession().setAttribute("name", name);
                request.setAttribute("message", "信息修改成功！3秒后返回论坛页面");
                request.getRequestDispatcher("updateuser.jsp").forward(request, response);
            } else {
                request.setAttribute("message", "两次密码不一致！");
                request.setAttribute("name", name);
                request.getRequestDispatcher("updateuser.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("message", "修改失败");
            request.getRequestDispatcher("updateuser.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
