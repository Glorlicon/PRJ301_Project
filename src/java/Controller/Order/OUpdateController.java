/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Order;

import Controller.BaseAuthController;
import DBContext.AccountDBContext;
import DBContext.CompanyDBContext;
import DBContext.OrderDBContext;
import DBContext.ProductDBContext;
import Entity.Account;
import Entity.Company;
import Entity.Order;
import Entity.Product;
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
public class OUpdateController extends BaseAuthController {

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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDBContext odb = new OrderDBContext();
        String invoiceid = request.getParameter("ivd");
        String companyid = request.getParameter("cid");
        String productid = request.getParameter("pid");
        int amount = Integer.parseInt(request.getParameter("a"));
        float cost = Float.parseFloat(request.getParameter("c"));
        String date = request.getParameter("idate");
        Date date1 = Date.valueOf(request.getParameter("idate"));
        Order o = odb.getOrder(invoiceid, companyid, productid, amount, cost, date);
        request.setAttribute("order", o);
        
        Order o2 = new Order();
        Company c = new Company();
        Product p = new Product();
        c.setCompanyid(companyid);
        p.setProductid(productid);
        o2.setC(c);
        o2.setP(p);
        o2.setAmount(amount);
        o2.setCost(cost);
        o2.setImportDate(date1);
        o2.setInvoice_id(invoiceid);
        
        CompanyDBContext cdb = new CompanyDBContext();
        ProductDBContext pdb = new ProductDBContext();
        ArrayList<Company> company = cdb.GetCompany();
        ArrayList<Product> product = pdb.GetProduct();
        request.setAttribute("company", company);
        request.setAttribute("product", product);
        request.setAttribute("vid", invoiceid);
        request.setAttribute("cid", companyid);
        request.setAttribute("pid", productid);
        request.setAttribute("a", amount);
        request.setAttribute("c", cost);
        request.setAttribute("idate", date);
        request.getSession().setAttribute("oldorder", o2);
        request.getRequestDispatcher("/view/order/update.jsp").forward(request, response);
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String company = request.getParameter("company");
        String product = request.getParameter("product");
        String amount = request.getParameter("Amount");
        Float price = Float.parseFloat(request.getParameter("Cost"));
        Date date = Date.valueOf(request.getParameter("Date"));
        Company c = new Company();
        Product p = new Product();
        OrderDBContext odb = new OrderDBContext();
        Order o = new Order();
        
        c.setCompanyid(company);
        p.setProductid(product);
        o.setC(c);
        o.setP(p);
        o.setAmount(Integer.parseInt(amount));
        o.setCost(price);
        o.setImportDate(date);
        
        odb.updateOrder(o,(Order) request.getSession().getAttribute("oldorder"));
        response.sendRedirect(request.getContextPath() + "/search");
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
