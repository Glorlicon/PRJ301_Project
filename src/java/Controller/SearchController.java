/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DBContext.AccountDBContext;
import DBContext.OrderDBContext;
import Entity.Account;
import Entity.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author BK
 */
@WebServlet(name = "SearchController", urlPatterns = {"/product/search"})
public class SearchController extends HttpServlet {

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
        Account account = (Account) request.getSession().getAttribute("account");
        String username = "";
        if (account == null){
            username = "";
        } else username = account.getUsername();
        
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        String raw_ivd = request.getParameter("ivd");
        if (raw_ivd == null || raw_ivd.trim().length() == 0) {
            raw_ivd = "0";
        }
        OrderDBContext odb = new OrderDBContext();
        AccountDBContext adb = new AccountDBContext();
        ArrayList<Order> orders = odb.OrderPaging(page, recordsPerPage, raw_ivd);
        int  update = adb.getPermission(username, "/product/update");
        int  insert = adb.getPermission(username, "/product/insert");
        int  delete = adb.getPermission(username, "/product/delete");
        ArrayList<Order> invoiceid = odb.GetSerperateInvoice();
        int noOfRecords = odb.GetNoOfRecord();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        request.setAttribute("order", orders);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("invoice", invoiceid);
        request.setAttribute("ivd", raw_ivd);
        request.setAttribute("update", update);
        request.setAttribute("insert", insert);
        request.setAttribute("delete", delete);
        request.setAttribute("account", account);
         //response.getWriter().println(update);
        request.getRequestDispatcher("/home.jsp").forward(request, response); // Forward to Search.jsp
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
