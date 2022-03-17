package controller;

import approval.ApprovalDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import topicDescription.TopicDescriptionDAO;
import utils.IDGenerator;

@WebServlet(name = "RejectTopicDesController", urlPatterns = {"/RejectTopicDesController"})
public class RejectTopicDesController extends HttpServlet {
    private static final String ERROR = "ViewTopicDescriptionsReviewController";
    private static final String SUCCESS = "ViewTopicDescriptionsReviewController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String approvalID= IDGenerator.generateID(16);
            String userID = request.getParameter("userID");
            String topicDesID = request.getParameter("topicDesID");
            String reason = request.getParameter("reason");
            TopicDescriptionDAO dao = new TopicDescriptionDAO();
            boolean check = dao.approveOrReject(topicDesID, "Draft");
            ApprovalDAO dao2= new ApprovalDAO();
            boolean check2 = dao2.reject(approvalID, userID, topicDesID, reason);
            if (check && check2) {
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at RejectTopicDesController" + e.toString());
        } finally {
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
