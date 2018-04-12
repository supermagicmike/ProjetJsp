/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CustomerEntity;
import model.DAO;
import model.DAOException;

/**
 *
 * @author micka
 */
public class AdminController extends HttpServlet
{

    private final static Logger log = Logger.getLogger(LoginController.class.getName());

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
            String action = request.getParameter("action");
            log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ACTION: " + action);

            switch (action)
            {

                case "PRODUCT":
                {
                    String productdeb = request.getParameter("produitDeb");
                    String productfin = request.getParameter("produitFin");
                    log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<deb:" + productdeb + " ///////////////////////////// fin" + productfin);
                    Map<String, Float> hm = new HashMap<>();
                    try
                    {
                        List<String> productCodes = dao.productCodes();
                        for (String c : productCodes)
                        {
                            hm.put(c, dao.chiffreAffaireProduit(c, productdeb, productfin));
                        }
                        request.setAttribute("CAProduit", hm);
                    }
                    catch (SQLException ex)
                    {
                        Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                case "ETAT":
                {
                    String Datedeb = request.getParameter("etatDeb");
                    String Datefin = request.getParameter("etatFin");
                    log.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<deb:" + Datedeb + " ///////////////////////////// fin" + Datefin);
                    Map<String, Float> hm = new HashMap<>();
                    List<String> etat = dao.allEtats();
                    for (String s : etat)
                    {
                        hm.put(s, dao.chiffreAffaireEtat(s, Datedeb, Datefin));
                    }
                    request.setAttribute("CAEtat", hm);
                }
                case "CUSTO":
                {
                    String deb = request.getParameter("custoDeb");
                    String fin = request.getParameter("custoFin");
                    Map<String, Float> hm = new HashMap<>();
                    List<CustomerEntity> customers = dao.AllCustomers();
                    for (CustomerEntity c : customers)
                    {
                        hm.put(c.getName(), dao.chiffreAffaireClientDate(c.getCustomerId(), deb, fin));
                    }
                    request.setAttribute("CAClient", hm);
                }
                case "TEST":{
                    getServletContext().getRequestDispatcher("/WEB-INF/admin_product.jsp").forward(request, response);
                    
                }

            }

        }
        catch (DAOException ex)
        {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/afficheAdmin.jsp").forward(request, response);
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
