/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DBContext.DoctorDBContext;
import DBContext.PatientDBContext;
import Entity.Doctor;
import Entity.Patient;
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
@WebServlet(name = "SearchController", urlPatterns = {"/patient/search"})
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
        
        DoctorDBContext db = new DoctorDBContext();
        ArrayList<Doctor> docs = db.GetDocs(); //Get list of doctors
        request.setAttribute("docs", docs); // Embed current doctor list to docs value

        String raw_did = request.getParameter("did");
        //If raw_did received in null (first time running)
        if (raw_did == null || raw_did.trim().length() == 0) {
            raw_did = "-1";
        }
        int did = Integer.parseInt(raw_did);

        PatientDBContext patients = new PatientDBContext();
        ArrayList<Patient> pts = patients.GetPatients(did); //Get list of patients
        request.setAttribute("pts", pts); //Embed current patients list to pts value
        request.setAttribute("did", did); //Embed current did to did value

        request.getRequestDispatcher("/view/patient/Search.jsp").forward(request, response); // Forward to Search.jsp
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
