package controller;

import account.AccountDTO;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import topicDescription.ApproverTableRow;
import topicDescription.SupervisorDTO;
import topicDescription.TopicDescriptionDAO;
import topicDescription.TopicDescriptionDTO;
import topicDescription.TopicDescriptionDetails;

@WebServlet(name = "ViewTopicDescriptionController", urlPatterns = {"/ViewTopicDescriptionController"})
public class ViewTopicDescriptionController extends HttpServlet {
    private static final String ERROR = "lecture.jsp";
    private static final String SUCCESS = "lecture.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session= request.getSession();
            AccountDTO loginAcc = (AccountDTO) session.getAttribute("LOGIN_AC");
            String lecturerID= loginAcc.getAccountID();
            TopicDescriptionDAO dao = new TopicDescriptionDAO();
            List<TopicDescriptionDTO> listTD = dao.getListTopicDescription(lecturerID);
            HashMap<String, String> files = new HashMap<>();
            if (!listTD.isEmpty()) {
                for (TopicDescriptionDTO topicDescriptionDTO : listTD) {
                    Gson g = new Gson();
                    TopicDescriptionDetails t = g.fromJson(topicDescriptionDTO.getDetails(), TopicDescriptionDetails.class);
                    String fileName = "";
                    fileName += t.getThesisNameEnglish();
                    List<SupervisorDTO> supervisors = t.getSupervisors();
                    if (supervisors != null && !supervisors.isEmpty()) {
                        for (SupervisorDTO supervisor : supervisors) {
                            String[] parts = supervisor.getEmail().split("@");
                            fileName += "_" + parts[0];
                        }
                    }
                    files.put(topicDescriptionDTO.getTopicDescrID(), fileName);
                }
                request.setAttribute("PENDING_FILES", files);
                
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ViewTopicDescriptionController:" + e.toString());
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
