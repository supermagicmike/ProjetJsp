/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.DAOException;
import model.PurchaseEntity;

/**
 *
 * @author micka
 */
public class CustomerController extends HttpServlet
{



    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
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
            List<PurchaseEntity> purchase = dao.viewPurshases(1);
            request.setAttribute("purchases", purchase);
            String action = request.getParameter("action");
            String code=request.getParameter("code");
            switch (action) {
				case "DELETE":
            {
                try
                {
                    // Requête de suppression (vient du lien hypertexte)
                    dao.deletePurchase(Integer.valueOf(code));
                    request.setAttribute("message", "Code " + code + " Supprimé");
                    request.setAttribute("purchases", dao.viewPurshases(1));
                }
                catch (SQLException ex)
                {
                    Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
																						


			}
        }
        catch (DAOException ex)
        {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }

      

getServletContext().getRequestDispatcher("WEB-INF/affiche.jsp").forward(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
