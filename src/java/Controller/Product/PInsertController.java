/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Product;

import Controller.BaseAuthController;
import DBContext.AccountDBContext;
import DBContext.CompanyDBContext;
import DBContext.OrderDBContext;
import DBContext.ProductDBContext;
import Entity.Account;
import Entity.Company;
import Entity.Product;
import com.sun.xml.rpc.processor.modeler.j2ee.xml.string;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author BK
 */
public class PInsertController extends HttpServlet {

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
        String[] company = request.getParameterValues("company");
        String[] product = request.getParameterValues("product");
        String[] amount = request.getParameterValues("amount");
        int[] amounts = new int[10];
        for (int i = 0; i < amount.length; i++) {
            amounts[i] = Integer.parseInt(amount[i]);
        }
        String[] price = request.getParameterValues("price");
        float[] prices = new float[10];
        for (int i = 0; i < price.length; i++) {
            prices[i] = Float.parseFloat(price[i]);
        }
        String[] date = request.getParameterValues("importdate");
        OrderDBContext odb = new OrderDBContext();
        int OrderNo = odb.GetNoOfRecord();
        AccountDBContext adb = new AccountDBContext();
        String InvoiceID = null;

        Account account = (Account) request.getSession().getAttribute("account");
        String username = account.getUsername();
        for (int i = 0; i < company.length; i++) {
            if (OrderNo < 10) {
                InvoiceID = "IN00" + String.valueOf(OrderNo);
            } else if (OrderNo < 100) {
                InvoiceID = "IN0" + String.valueOf(OrderNo);
            } else if (OrderNo < 1000) {
                InvoiceID = "IN" + String.valueOf(OrderNo);
            }
            adb.addAccountOrder(username, InvoiceID);
            odb.AddOrder(InvoiceID, company[i], product[i], amounts[i], prices[i], Date.valueOf(date[i]));
        }
        response.getWriter().println("Product added succesful!");
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
        CompanyDBContext cdb = new CompanyDBContext();
        ProductDBContext pdb = new ProductDBContext();
        ArrayList<Company> company = cdb.GetCompany();
        ArrayList<Product> product = pdb.GetProduct();
        request.setAttribute("company", company);
        request.setAttribute("product", product);
        request.getRequestDispatcher("/view/product/insert.jsp").forward(request, response);
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
