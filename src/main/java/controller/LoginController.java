package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.CustomerEntity;
import model.DAO;
import model.DAOException;
import model.PurchaseEntity;

public class LoginController extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
     * @throws model.DAOException
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, DAOException {
		Boolean first = true;
                request.setAttribute("first_time", first);
                // Quelle action a appelé cette servlet ?
		String action = request.getParameter("action");
                DAO customer = new DAO();
		if (null != action) {
			switch (action) {
				case "login":
					checkLogin(request);

                                        
					break;
				case "logout":
					doLogout(request);
					break;
			}
		}

		// Est-ce que l'utilisateur est connecté ?
		// On cherche l'attribut userName dans la session
		String userName = findUserInSession(request);
		String jspView;
		if (null == userName) { // L'utilisateur n'est pas connecté
			// On choisit la page de login
			jspView = "login.jsp";

		} else { // L'utilisateur est connecté
			// On choisit la page d'affichage
                        if(userName.equals("Mr. Super-User")){
                            jspView="WEB-INF/afficheAdmin.jsp";
                        }else{
			jspView = "WEB-INF/affiche.jsp";
                        }
		}
		// On va vers la page choisie
		request.getRequestDispatcher(jspView).forward(request, response);
                first = false;
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
            try
            {
                processRequest(request, response);
            }
            catch (DAOException ex)
            {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            try
            {
                processRequest(request, response);
            }
            catch (DAOException ex)
            {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
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

	private void checkLogin(HttpServletRequest request) throws DAOException {
                
                // Les paramètres transmis dans la requête
		String loginParam = request.getParameter("loginParam");
		String passwordParam = request.getParameter("passwordParam");
		// Le login/password défini dans web.xml
		String admin = getInitParameter("login");
		String adminpassword = getInitParameter("password");
		String userName = getInitParameter("userName");
                DAO customer = new DAO();
                CustomerEntity cust = new CustomerEntity();
                if ((admin.equals(loginParam) && (adminpassword.equals(passwordParam)))) {
			// On a trouvé la combinaison login / password
			// On stocke l'information dans la session
			HttpSession session = request.getSession(true); // démarre la session
			session.setAttribute("userName", userName);

                        
                        
                         
		}
                try{
                cust = customer.findCustomer(Integer.valueOf(passwordParam));
                
                if(cust!=null){
                    if(cust.getEmail().equals(loginParam)){
                    HttpSession session = request.getSession(true); // démarre la session
			session.setAttribute("userName", cust.getName());
                        session.setAttribute("Id", cust.getCustomerId());
                        List<PurchaseEntity> purchase = customer.viewPurshases(cust.getCustomerId());
                        request.setAttribute("purchases", purchase);
                        request.setAttribute("Descritpions", customer.GetProductsDescriptions());
                        request.setAttribute("Companies", customer.GetCompanies());                         
                        try {           
                            request.setAttribute("customer_credit", customer.GetCreditLimit((Integer) request.getSession().getAttribute("Id")));
                        } catch (SQLException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        
                }}
                else { // On positionne un message d'erreur pour l'afficher dans la JSP
			request.setAttribute("errorMessage", "Login/Password incorrect");
		}
                }catch(NumberFormatException e){ 
                    request.setAttribute("errorMessage", "Login/Password incorrect");
                }

		
	}

	private void doLogout(HttpServletRequest request) {
		// On termine la session
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
	}

	private String findUserInSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		return (session == null) ? null : (String) session.getAttribute("userName");
	}

}