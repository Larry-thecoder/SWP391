package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainController extends HttpServlet {
    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String LOGOUT = "LogoutController";
    
    private static final String LC_SUB_VIEW = "ViewSubByLectureController";
    private static final String LC_SUB_DELETE = "DeleteSubByLectureController";
    private static final String LC_SUB_UPDATE = "UpdateSubByLectureController";
    private static final String LC_SUB_CREATE = "CreateSubByLectureController";
    
    private static final String ST_SUB_VIEW = "ViewSubByStaffController";
    private static final String ST_SUB_UPDATE = "UpdateSubByStaffController";
    
    private static final String ST_TOPIC_VIEW = "ViewTopicController";
    private static final String ST_TOPIC_DELETE = "DeleteTopicController";
    private static final String ST_TOPIC_UPDATE = "UpdateTopicController";
    private static final String ST_TOPIC_CREATE = "CreateTopicController";
    
    private static final String ST_TOPICDES_VIEW = "ViewTopicDescriptionController";
    private static final String ST_TOPICDES_DELETE = "DeleteTopicDescriptionController";
    private static final String ST_TOPICDES_UPDATE = "UpdateTopicDescriptionController";
    private static final String ST_TOPICDES_CREATE = "CreateTopicDescriptionController";
    
    private static final String ST_VIEW_TOPICDES_APPROVED = "ViewTopicDescriptionsApprovedController";
    private static final String DOWNLOAD_FILE_CONTROLLER= "DownloadFileController";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
                
            }else if ("ViewSubByLectureController".equals(action)) {
                url = LC_SUB_VIEW;
            }else if ("DeleteSubByLectureController".equals(action)) {
                url = LC_SUB_DELETE;
            }else if ("UpdateSubByLectureController".equals(action)) {
                url = LC_SUB_UPDATE;
            }else if ("CreateSubByLectureController".equals(action)) {
                url = LC_SUB_CREATE;
                
            }else if ("ViewSubByStaffController".equals(action)) {
                url = ST_SUB_VIEW;
            }else if ("UpdateSubByStaffController".equals(action)) {
                url = ST_SUB_UPDATE;
                
            }else if ("ViewTopicController".equals(action)) {
                url = ST_TOPIC_VIEW;
            }else if ("DeleteTopicController".equals(action)) {
                url = ST_TOPIC_DELETE;
            }else if ("UpdateTopicController".equals(action)) {
                url = ST_TOPIC_UPDATE;
            }else if ("CreateTopicController".equals(action)) {
                url = ST_TOPIC_CREATE;
                
            }else if ("ViewTopicDescriptionController".equals(action)) {
                url = ST_TOPICDES_VIEW;
            }else if ("DeleteTopicDescriptionController".equals(action)) {
                url = ST_TOPICDES_DELETE;
            }else if ("UpdateTopicDescriptionController".equals(action)) {
                url = ST_TOPICDES_UPDATE;
            }else if ("CreateTopicDescriptionController".equals(action)) {
                url = ST_TOPICDES_CREATE;    
                
            } else if (ST_VIEW_TOPICDES_APPROVED.equals(action)) {
                url = ST_VIEW_TOPICDES_APPROVED;
            } else if (DOWNLOAD_FILE_CONTROLLER.equals(action)) {
                url= DOWNLOAD_FILE_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController:" + e.toString());
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
