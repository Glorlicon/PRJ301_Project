/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
public class SearchController extends BaseAuthController {

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
        
        int ppage = 1, cpage = 1, opage = 1, apage = 1;

        int recordsPerPage = 10;
        if (request.getParameter("ppage") != null) {
            ppage = Integer.parseInt(request.getParameter("ppage"));
        }
        if (request.getParameter("cpage") != null) {
            cpage = Integer.parseInt(request.getParameter("cpage"));
        }
        if (request.getParameter("opage") != null) {
            opage = Integer.parseInt(request.getParameter("opage"));
        }
        if (request.getParameter("apage") != null) {
            apage = Integer.parseInt(request.getParameter("opage"));
        }

        String raw_ivd = request.getParameter("ivd");
        if (raw_ivd == null || raw_ivd.trim().length() == 0) {
            raw_ivd = "0";
        }
        OrderDBContext odb = new OrderDBContext();
        AccountDBContext adb = new AccountDBContext();
        CompanyDBContext cdb = new CompanyDBContext();
        ProductDBContext pdb = new ProductDBContext();
        ArrayList<Order> orders = odb.OrderPaging(opage, recordsPerPage, raw_ivd);
        request.setAttribute("order", orders);
        ArrayList<Company> coms = cdb.CompanyPaging(cpage, recordsPerPage);
        request.setAttribute("coms", coms);
        ArrayList<Product> prods = pdb.ProductPaging(ppage, recordsPerPage);
        request.setAttribute("prods", prods);
        ArrayList<Account> accs = adb.getaccounts();
        request.setAttribute("accs", accs);
        String rel = request.getParameter("rel");
        if (rel == null || rel.isEmpty()){
            rel = "1";
        }
        int  update = adb.getPermission(username, "/product/update");
        int  insert = adb.getPermission(username, "/product/insert");
        int  delete = adb.getPermission(username, "/product/delete");
        int  aupdate = adb.getPermission(username, "/account/insert");
        ArrayList<Order> invoiceid = odb.GetSerperateInvoice();
        int onoOfRecords = odb.GetNoOfRecord();
        int cnoOfRecords = cdb.GetNoOfRecord();
        int pnoOfRecords = pdb.GetNoOfRecord();
        int anoOfRecords = adb.GetNoOfRecord();
        int onoOfPages = (int) Math.ceil(onoOfRecords * 1.0 / recordsPerPage);
        int pnoOfPages = (int) Math.ceil(pnoOfRecords * 1.0 / recordsPerPage);
        int cnoOfPages = (int) Math.ceil(cnoOfRecords * 1.0 / recordsPerPage);
        int anoOfPages = (int) Math.ceil(anoOfRecords * 1.0 / recordsPerPage);
        
        request.getSession().setAttribute("rel", rel);
        request.setAttribute("onoOfRecords", onoOfRecords);
        request.setAttribute("cnoOfRecords", cnoOfRecords);
        request.setAttribute("pnoOfRecords", pnoOfRecords);
        request.setAttribute("anoOfRecords", anoOfRecords);
        request.setAttribute("ocurrentPage", opage);
        request.setAttribute("pcurrentPage", ppage);
        request.setAttribute("ccurrentPage", cpage);
        request.setAttribute("acurrentPage", apage);
        request.setAttribute("onoOfPages", onoOfPages);
        request.setAttribute("pnoOfPages", pnoOfPages);
        request.setAttribute("cnoOfPages", cnoOfPages);
        request.setAttribute("anoOfPages", anoOfPages);
        request.setAttribute("invoice", invoiceid);
        request.setAttribute("ivd", raw_ivd);
        request.setAttribute("update", update);
        request.setAttribute("insert", insert);
        request.setAttribute("delete", delete);
        request.setAttribute("aupdate", aupdate);
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
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
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
