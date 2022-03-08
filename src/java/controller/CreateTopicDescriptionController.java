/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import topicDescription.TopicDescriptionDAO;
import topicDescription.TopicDescriptionDTO;
import topicDescription.TopicDescriptionError;

/**
 *
 * @author Mr.Khuong
 */
@WebServlet(name = "CreateTopicDescriptionController", urlPatterns = {"/CreateTopicDescriptionController"})
public class CreateTopicDescriptionController extends HttpServlet {

    private static final String ERROR = "createTP.jsp";
    private static final String SUCCESS = "staffTP.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String descriptionID = request.getParameter("descriptionID");
            String approverID = request.getParameter("approverID");
            String details = request.getParameter("details");
            String descriptionStatus = request.getParameter("descriptionStatus");
            boolean check = true;
            TopicDescriptionError TDError = new TopicDescriptionError();
            if (descriptionID.length() < 0 || descriptionID.length() > 50) {
                TDError.setTopicDescrID("DescriptionID must be in [1,50]");
                check = false;
            }
            if (approverID.length() < 0 || approverID.length() > 50) {
                TDError.setApproverID("ApproverID must be in [1,50]");
                check = false;
            }
            if (details.length() < 0 || details.length() > 50) {
                TDError.setDetails("Details must be in [1,50]");
                check = false;
            }
            if (descriptionStatus.length() < 0 || descriptionStatus.length() > 50) {
                TDError.setTopicDescrStatus("Description Status must be in [1,50]");
                check = false;
            }
            TopicDescriptionDAO dao = new TopicDescriptionDAO();
            TopicDescriptionDTO checkTD = dao.getTPInfo(descriptionID);
            if (checkTD != null) {
                TDError.setTopicDescrID("Duplicate DescriptionID!");
                check = false;
            }
            if (check) {
                boolean checkInsert = dao.insert(new TopicDescriptionDTO(approverID, approverID, details, descriptionStatus));
                if (checkInsert) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("ERROR_TD", TDError);
            }
        } catch (Exception e) {
            log("Error at CreateTopicDescriptionController: " + e.toString());
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
