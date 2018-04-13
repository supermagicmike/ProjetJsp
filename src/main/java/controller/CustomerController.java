/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DAO;
import model.DAOException;

/**
 *
 * @author tzanchi
 */
@WebServlet(name = "CustomerController", urlPatterns = {"/CustomerController"})
public class CustomerController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean editer = false;
        String action = request.getParameter("action");
        action = (action == null) ? "" : action; //pour les switch qui n'aiment pas les null
        String code = request.getParameter("code");
        String code_edit;
        try {
            DAO dao = new DAO();
            request.setAttribute("purchases", dao.viewPurshases((Integer) request.getSession().getAttribute("Id")));
            request.setAttribute("Descritpions", dao.GetProductsDescriptions());
            request.setAttribute("Companies", dao.GetCompanies());
            request.setAttribute("numero_edit", code);
            request.setAttribute("edit", editer);
            code_edit = code;
            
            switch (action) {
                case "DELETE": {
                    try {
                        // Requête de suppression (vient du lien hypertexte)
                        dao.deletePurchase(Integer.valueOf(code));
                        request.setAttribute("purchases", dao.viewPurshases((Integer) request.getSession().getAttribute("Id")));
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }
                case "ADD":
                    try {
                        String Description = request.getParameter("Description");
                        String ShippingCost = request.getParameter("ShippingCost");
                        String Quantity = request.getParameter("Quantity");
                        String freightCompany = request.getParameter("freightCompany");
                        System.out.println("++++++++++++++++++++++++++++++++++++++++" + dao.findProductId(Description));
                        dao.createPurshase((Integer) request.getSession().getAttribute("Id"), dao.findProductId(Description), Integer.parseInt(Quantity), Float.parseFloat(ShippingCost), freightCompany);
                        request.setAttribute("purchases", dao.viewPurshases((Integer) request.getSession().getAttribute("Id")));
                        request.getRequestDispatcher("/WEB-INF/affiche.jsp").forward(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                    
                case "EDIT":
                    editer = true;
                    request.setAttribute("edit", editer);
                    break;
                
                case "VALIDEDIT":
                    try {
                        String num_edit = request.getParameter("num_edit");
                        editer = false;
                        request.setAttribute("edit", editer);                 
                        String editQuantity = request.getParameter("editQuantity");
                        String editFreightCompany = request.getParameter("editFreightCompany");
                        String editShippingCost = request.getParameter("editShippingCost"); 
                        System.out.println("**************************quantity = "+editQuantity+" company = "+editFreightCompany+" cost = "+editShippingCost);
                        dao.EditPurshase(                                
                                Integer.parseInt(num_edit), 
                                Integer.parseInt(editQuantity), 
                                Float.parseFloat(editShippingCost), 
                                editFreightCompany);
                        request.setAttribute("purchases", dao.viewPurshases((Integer) request.getSession().getAttribute("Id")));
                        break; 
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        } catch (DAOException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
        //getServletContext().getRequestDispatcher("/WEB-INF/affiche.jsp").forward(request, response);
        request.getRequestDispatcher("/WEB-INF/affiche.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * <<<<<<< HEAD
     *
     * @param request servlet request
     * @param response servlet response
     *
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
     * <<<<<<< HEAD
     *
     * @param request servlet request
     * @param response servlet response
     *
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
