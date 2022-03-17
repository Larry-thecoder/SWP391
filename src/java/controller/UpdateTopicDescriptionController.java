package controller;

import approval.ApprovalDAO;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import topicDescription.StudentDAO;
import topicDescription.StudentDTO;
import topicDescription.SupervisorDAO;
import topicDescription.SupervisorDTO;
import topicDescription.TopicDescriptionDAO;
import topicDescription.TopicDescriptionDTO;
import topicDescription.TopicDescriptionDetails;

@WebServlet(name = "UpdateTopicDescriptionController", urlPatterns = {"/UpdateTopicDescriptionController"})
public class UpdateTopicDescriptionController extends HttpServlet {

    private static final String ERROR = "ViewReturnedDetailsController";
    private static final String SUCCESS = "ViewDocumentsReturnedController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String topicDesID = request.getParameter("topicDesID");
            String className = request.getParameter("className");
            String profession = request.getParameter("profession");
            String[] special= request.getParameterValues("special[]");
            List<String> listSpecial= new ArrayList<>();
            listSpecial.addAll(Arrays.asList(special));
            
            String durationTimeFrom = request.getParameter("durationTimeFrom");
            String durationTimeTo = request.getParameter("durationTimeTo");
            List<String> durationTime= new ArrayList<>();
            durationTime.add(durationTimeFrom);
            durationTime.add(durationTimeTo);
            
            String kindOfPersonMakeRegisters = request.getParameter("kindOfPersonMakeRegisters");
            String[] supervisors= request.getParameterValues("supervisors[]");
            SupervisorDAO dao= new SupervisorDAO();
            List<SupervisorDTO> listSupervisors= new ArrayList<>();
            for (String supervisorEmail : supervisors) {
                SupervisorDTO supervisorDTO= dao.getSupervisorByEmail(supervisorEmail);
                if (supervisorDTO!= null) {
                    listSupervisors.add(supervisorDTO);
                }
            }
            String[] students= request.getParameterValues("students[]");
            StudentDAO dao2= new StudentDAO();
            List<StudentDTO> listStudents= new ArrayList<>();
            for (String studentsEmail : students) {
                StudentDTO studentDTO= dao2.getStudentByEmail(studentsEmail);
                if (studentDTO!= null) {
                    listStudents.add(studentDTO);
                }
            }
            
            String thesisNameEnglish = request.getParameter("thesisNameEnglish");
            String thesisNameVN = request.getParameter("thesisNameVN");
            String thesisNameAbbr = request.getParameter("thesisNameAbbr");
            
            String mainContent_Theory = request.getParameter("mainContent_Theory");
            String mainContent_Practice = request.getParameter("mainContent_Practice");
            String otherComment = request.getParameter("otherComment");
            
            String signingPlace = request.getParameter("signingPlace");
            String signedDate = request.getParameter("signedDate");
            String descriptionStatus = "Pending";

            Gson g = new Gson();
            TopicDescriptionDetails t = new TopicDescriptionDetails(className, durationTime, profession, listSpecial, kindOfPersonMakeRegisters, listSupervisors, listStudents, thesisNameEnglish, thesisNameVN, thesisNameAbbr, mainContent_Theory, mainContent_Practice, otherComment, signingPlace, signedDate);
            String details= g.toJson(t, TopicDescriptionDetails.class);
            TopicDescriptionDTO topicDescr = new TopicDescriptionDTO(topicDesID, details, descriptionStatus);
            
            TopicDescriptionDAO dao3 = new TopicDescriptionDAO();
            ApprovalDAO dao4= new ApprovalDAO();
            
            boolean check = dao3.update(topicDescr);
            boolean check2 = dao4.updateSeenCmt(topicDesID);
            if (check && check2) {
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at UpdateTopicDescriptionController: " + e.toString());
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateTopicDescriptionController.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateTopicDescriptionController.class.getName()).log(Level.SEVERE, null, ex);
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

}
