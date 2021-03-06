package controller;

import account.AccountDAO;
import account.AccountDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String LECTURE = "lecture.jsp";
    private static final String STAFF = "staff.jsp";
    private static final String APPROVER_PAGE = "approver.jsp";
    private static final String LC = "Lecturer";
    private static final String ST = "Staff";
    private static final String APPROVER = "Approver";
      
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try{
            String accountID = request.getParameter("accountID");
            String password = request.getParameter("password");
            AccountDAO dao = new AccountDAO();
            AccountDTO loginAc = dao.checkLogin(accountID, password);
            if(loginAc == null){
                request.setAttribute("ERROR", "Incorrect User ID or Password! ");
            }else{
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_AC", loginAc);
                String role = loginAc.getRole();
                if(LC.equals(role)){
                    url = LECTURE;
                } else if(ST.equals(role)){
                    url = STAFF;
                } else if(APPROVER.equals(role)){
                    url = APPROVER_PAGE;
                } else{
                    request.setAttribute("ERROR", "Your role is not supportted!");
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
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
