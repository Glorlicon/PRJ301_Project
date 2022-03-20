/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DBContext.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entity.Account;

/**
 *
 * @author Bk
 */
public class LoginController extends HttpServlet {

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
        String uri = (request.getQueryString() != null ? "?" + request.getQueryString() : "");
        if (uri.equalsIgnoreCase("?")) {
            String method = request.getParameter("method");
            if (method.isEmpty() || method == null) {
                response.sendRedirect("login.jsp");
            } else {
                if (method.equalsIgnoreCase("logout")) {
                    request.getSession().setAttribute("account", null);
                    response.sendRedirect("search");
                    return;
                }
            }
        }
        
        response.sendRedirect("login.jsp");
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

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        AccountDBContext db = new AccountDBContext();
        Account account = db.getAccount(username, password);
        if (account == null) {
            request.getSession().setAttribute("account", null);
            request.getRequestDispatcher("/loginprocess.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("account", account);
            request.setAttribute("url", request.getContextPath());
            request.getRequestDispatcher("/loginprocess.jsp").forward(request, response);
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
