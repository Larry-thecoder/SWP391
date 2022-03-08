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
import topic.TopicDAO;
import topic.TopicDTO;
import topic.TopicError;

/**
 *
 * @author Mr.Khuong
 */
@WebServlet(name = "CreateTopicController", urlPatterns = {"/CreateTopicController"})
public class CreateTopicController extends HttpServlet {

    private static final String ERROR = "createTopic.jsp";
    private static final String SUCCESS = "staffTopic.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String subjectID = request.getParameter("subjectID");
            String subjectName = request.getParameter("subjectName");
            String descriptionID = request.getParameter("descriptionID");
            String lectureID = request.getParameter("lectureID");
            boolean check = true;
            TopicError TopicError = new TopicError();
            if (subjectID.length() < 0 || subjectID.length() > 50) {
                TopicError.setTopicID("Topic ID must be in [1,50]");
                check = false;
            }
            if (subjectName.length() < 0 || subjectName.length() > 50) {
                TopicError.setTopicName("Topic Name must be in [1,50]");
                check = false;
            }
            if (descriptionID.length() < 0 || descriptionID.length() > 50) {
                TopicError.setDescriptionID("Description ID must be in [1,50]");
                check = false;
            }
            if (lectureID.length() < 0 || lectureID.length() > 50) {
                TopicError.setLecturerID("Lecture ID must be in [1,50]");
                check = false;
            }
            TopicDAO dao = new TopicDAO();
            TopicDTO checkTD = dao.getTopicInfo(descriptionID);
            if (checkTD != null) {
                TopicError.setTopicID("Duplicate Topic ID!");
                check = false;
            }
            if (check) {
                boolean checkInsert = dao.insert(new TopicDTO(subjectID, subjectName, descriptionID, lectureID));
                if (checkInsert) {
                    url = SUCCESS;
                }
            } else {
                request.setAttribute("ERROR_T", TopicError);
            }
        } catch (Exception e) {
            log("Error at CreateTopicController: " + e.toString());
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
