package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import topicDescription.SpecialDAO;
import topicDescription.StudentDAO;
import topicDescription.StudentDTO;
import topicDescription.SupervisorDAO;
import topicDescription.SupervisorDTO;
import topicDescription.TopicDescriptionDAO;
import topicDescription.TopicDescriptionDTO;
import topicDescription.TopicDescriptionDetails;

@WebServlet(name = "ViewReturnedDetailsController", urlPatterns = {"/ViewReturnedDetailsController"})
public class ViewReturnedDetailsController extends HttpServlet {
    private static final String ERROR = "updateTD.jsp";
    private static final String SUCCESS = "updateTD.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String topicDesID = request.getParameter("id");
            TopicDescriptionDAO dao = new TopicDescriptionDAO();
            TopicDescriptionDTO topicDes = dao.getTopicDesByID(topicDesID);
            if (topicDes != null) {
                Gson g = new Gson();
                TopicDescriptionDetails t = g.fromJson(topicDes.getDetails(), TopicDescriptionDetails.class);
                
                SupervisorDAO dao1 = new SupervisorDAO();
                List<SupervisorDTO> supervisors = dao1.getListSupervisor();
                    
                List<SupervisorDTO> supervisorsByID= t.getSupervisors();
                List<String> supervisorsEmail = new ArrayList<>();
                for (SupervisorDTO supervisorDTO : supervisorsByID) {
                    supervisorsEmail.add(supervisorDTO.getEmail());
                }
                String supervisorsJSON= g.toJson(supervisorsEmail);
                    
                StudentDAO dao2 = new StudentDAO();
                List<StudentDTO> students = dao2.getListStudent();
                
                List<StudentDTO> studentsByID= t.getStudents();
                List<String> studentsEmail = new ArrayList<>();
                for (StudentDTO studentDTO : studentsByID) {
                    studentsEmail.add(studentDTO.getEmail());
                }
                String studentsJSON= g.toJson(studentsEmail);
                    
                SpecialDAO dao3 = new SpecialDAO();
                List<String> professions = dao3.getListProfession();


                List<String> specialsByID= t.getSpecial();
                String[] specialArr= new String[specialsByID.size()];
                specialsByID.toArray(specialArr);
                String specialsJSON= g.toJson(specialArr);
                
                List<String> specials = dao3.getListSpecial();
                

                request.setAttribute("TOPIC_DES_ID", topicDesID);
                request.setAttribute("CLASS", t.getClassName());
                request.setAttribute("LIST_PROFESSION", professions);
                request.setAttribute("PROFESSION", t.getProfession());
                request.setAttribute("LIST_SPECIAL", specials);
                request.setAttribute("SPECIALS", specialsJSON);
                request.setAttribute("DURATION_FROM", t.getDurationTime().get(0));
                request.setAttribute("DURATION_TO", t.getDurationTime().get(1));
                request.setAttribute("PERSON_MAKE_REGISTERS", t.getKindOfPersonMakeRegisters());
                request.setAttribute("LIST_SUPERVISOR", supervisors);
                request.setAttribute("SUPERVISORS", supervisorsJSON);
                request.setAttribute("LIST_STUDENT", students);
                request.setAttribute("STUDENTS", studentsJSON);
                request.setAttribute("THESIS_NAME_ENGLISH", t.getThesisNameEnglish());
                request.setAttribute("THESIS_NAME_VN", t.getThesisNameVN());
                request.setAttribute("THESIS_NAME_ABBR", t.getThesisNameAbbr());
                request.setAttribute("MAIN_CONTENT_THEORY", t.getMainContent_Theory());
                request.setAttribute("MAIN_CONTENT_PRACTICE", t.getMainContent_Practice());
                request.setAttribute("OTHER_COMMENT", t.getOtherComment());
                request.setAttribute("SIGNING_PLACE", t.getSigningPlace());
                request.setAttribute("SIGNED_DATE", t.getSignedDate());
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ViewReturnedDetailsController:" + e.toString());
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
