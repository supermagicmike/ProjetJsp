/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
<<<<<<< HEAD
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
=======
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
>>>>>>> Théo
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
<<<<<<< HEAD
import model.DAOException;
import model.PurchaseEntity;

/**
 *
 * @author micka
 */
public class CustomerController extends HttpServlet
{
=======
import model.DataSourceFactory;

/**
 *
 * @author tzanchi
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerController"})
public class CustomerController extends HttpServlet {
>>>>>>> Théo

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
<<<<<<< HEAD
     * @param request  servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            DAO dao = new DAO();
            request.setAttribute("purchases", dao.viewPurshases((Integer)request.getSession().getAttribute("Id")));
            String action = request.getParameter("action");
            String code = request.getParameter("code");
            switch (action)
            {
                case "DELETE":
                {
                    try
                    {
                        // Requête de suppression (vient du lien hypertexte)
                        dao.deletePurchase(Integer.valueOf(code));
                       }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }

            }
        }
        catch (DAOException ex)
        {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/affiche.jsp").forward(request, response);
=======
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DAO dao = new DAO();
            request.setAttribute("purshaseList", dao.viewPurshases(1));
        } catch (Exception ex) {
        }

        request.getRequestDispatcher("affiche.jsp").forward(request, response);

>>>>>>> Théo
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
<<<<<<< HEAD
     * @param request  servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
=======
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
>>>>>>> Théo
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
<<<<<<< HEAD
     * @param request  servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
=======
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
>>>>>>> Théo
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
<<<<<<< HEAD
    public String getServletInfo()
    {
=======
    public String getServletInfo() {
>>>>>>> Théo
        return "Short description";
    }// </editor-fold>

}
