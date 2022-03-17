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
    
    // Lecturer
    private static final String ST_TOPICDES_VIEW = "ViewTopicDescriptionController";
    private static final String ST_TOPICDES_DELETE = "DeleteTopicDescriptionController";
    private static final String TOPICDES_UPDATE = "Update Topic Description";
    private static final String TOPICDES_UPDATE_CONTROLLER = "UpdateTopicDescriptionController";
    private static final String TOPICDES_CREATE = "Add new Topic Description";
    private static final String TOPICDES_CREATE_CONTROLLER = "CreateTopicDescriptionController";
    private static final String TOPICDES_LOAD_FORM = "LoadFormCreateTopicDesController";
    private static final String VIEW_DOC_RETURNED_CONTROLLER = "ViewDocumentsReturnedController";
    private static final String VIEW_COMMENTS_CONTROLLER = "ViewCommentsController";
    private static final String VIEW_RETURNED_CONTROLLER = "ViewReturnedDetailsController";
    
    // Approver
    private static final String ST_VIEW_TOPICDES_REVIEW = "ViewTopicDescriptionsReviewController";
    private static final String ST_VIEW_TOPICDES_DETAILS = "ViewTopicDesDetailsController";
    private static final String APPROVE = "Approve";
    private static final String REJECT = "Reject";
    private static final String APPROVE_TOPIC_DES_CONTROLLER = "ApproveTopicDesController";
    private static final String REJECT_TOPIC_DES_CONTROLLER = "RejectTopicDesController";
    private static final String VIEW_SELECTION_CRITERIA_CONTROLLER = "ViewSelectionCriteriaController";
    
    // StaffDT
    private static final String ST_VIEW_TOPICDES_APPROVED = "ViewTopicDescriptionsApprovedController";
    private static final String DOWNLOAD_FILE_CONTROLLER= "DownloadFileController";
    private static final String ADD_SELECTION_CRITERIA = "Add new Selection Criteria";
    private static final String ADD_SELECTION_CRITERIA_CONTROLLER = "AddSelectionCriteriaController";
    private static final String LOAD_FORM_SELECTION_CRITERIA_CONTROLLER = "LoadFormCreateSelCritController";
    
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
            }else if (TOPICDES_UPDATE.equals(action)) {
                url = TOPICDES_UPDATE_CONTROLLER;
            }else if (TOPICDES_CREATE.equals(action)) {
                url = TOPICDES_CREATE_CONTROLLER;    
            }else if (TOPICDES_LOAD_FORM.equals(action)) {
                url = TOPICDES_LOAD_FORM;    
            }else if (VIEW_DOC_RETURNED_CONTROLLER.equals(action)) {
                url = VIEW_DOC_RETURNED_CONTROLLER;
            }else if (VIEW_COMMENTS_CONTROLLER.equals(action)) {
                url = VIEW_COMMENTS_CONTROLLER;
            }else if (VIEW_RETURNED_CONTROLLER.equals(action)) {
                url = VIEW_RETURNED_CONTROLLER;
                
            } else if (ST_VIEW_TOPICDES_REVIEW.equals(action)) {
                url = ST_VIEW_TOPICDES_REVIEW;
            } else if (ST_VIEW_TOPICDES_DETAILS.equals(action)) {
                url = ST_VIEW_TOPICDES_DETAILS;
            } else if (APPROVE.equals(action)) {
                url = APPROVE_TOPIC_DES_CONTROLLER;
            } else if (REJECT.equals(action)) {
                url = REJECT_TOPIC_DES_CONTROLLER;
            } else if (VIEW_SELECTION_CRITERIA_CONTROLLER.equals(action)) {
                url = VIEW_SELECTION_CRITERIA_CONTROLLER;
            
            } else if (ST_VIEW_TOPICDES_APPROVED.equals(action)) {
                url = ST_VIEW_TOPICDES_APPROVED;
            } else if (DOWNLOAD_FILE_CONTROLLER.equals(action)) {
                url= DOWNLOAD_FILE_CONTROLLER;
            } else if (ADD_SELECTION_CRITERIA.equals(action)) {
                url= ADD_SELECTION_CRITERIA_CONTROLLER;
            } else if (LOAD_FORM_SELECTION_CRITERIA_CONTROLLER.equals(action)) {
                url= LOAD_FORM_SELECTION_CRITERIA_CONTROLLER;
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
