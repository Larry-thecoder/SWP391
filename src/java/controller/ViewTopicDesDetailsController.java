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
import topicDescription.StudentDTO;
import topicDescription.SupervisorDTO;
import topicDescription.TopicDescriptionDAO;
import topicDescription.TopicDescriptionDTO;
import topicDescription.TopicDescriptionDetails;

@WebServlet(name = "ViewTopicDesDetailsController", urlPatterns = {"/ViewTopicDesDetailsController"})
public class ViewTopicDesDetailsController extends HttpServlet {
    private static final String ERROR = "tDDetails.jsp";
    private static final String SUCCESS = "tDDetails.jsp";

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
                List<String> topicDescriptionDetailsLines = new ArrayList<>();
                List<String> topicDescriptionDetailsLines2 = new ArrayList<>();
                
                String durationTimeTxt = "";
                List<String> durationTime= t.getDurationTime();
                if (!durationTime.isEmpty()) {
                    durationTimeTxt += durationTime.get(0);
                    durationTimeTxt += "  To " + durationTime.get(1);
                }
                topicDescriptionDetailsLines.add("Class: " + t.getClassName() + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                                               + "Duration time: From "+ durationTimeTxt);
         
                String specialTxt = "";
                List<String> special= t.getSpecial();
                if (!special.isEmpty()) {
                    for (int i = 0; i < special.size(); i++) {
                        specialTxt += "< " + special.get(i) + " > ";
                    }
                }
                topicDescriptionDetailsLines.add("(*) Profession: < " + t.getProfession() + " >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                                               + "Special: "+ specialTxt);
                
                topicDescriptionDetailsLines.add("(*) Kinds of person make registers: " + t.getKindOfPersonMakeRegisters());
                List<SupervisorDTO> supervisors= t.getSupervisors();
                List<StudentDTO> students= t.getStudents();
                topicDescriptionDetailsLines2.add("3. Register content of Gradution Thesis:");
                topicDescriptionDetailsLines2.add("(*) 3.1. Gradution Thesis name:");
                topicDescriptionDetailsLines2.add("English: " + t.getThesisNameEnglish());
                topicDescriptionDetailsLines2.add("Vietnamese: " + t.getThesisNameVN());
                topicDescriptionDetailsLines2.add("Abbreviation: " + t.getThesisNameAbbr());
                topicDescriptionDetailsLines2.add("(*) 3.2. Main proposal content (including result and product)");
                topicDescriptionDetailsLines2.add("a) Theory: ");
                topicDescriptionDetailsLines2.add(t.getMainContent_Theory());
                topicDescriptionDetailsLines2.add("b) Practice: ");
                topicDescriptionDetailsLines2.add(t.getMainContent_Practice());
                topicDescriptionDetailsLines2.add("4. Other comment (propose all relative thing if any)");
                topicDescriptionDetailsLines2.add(t.getOtherComment());
                topicDescriptionDetailsLines2.add("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                                               + t.getSigningPlace() + ", " + t.getSignedDate());
                topicDescriptionDetailsLines2.add("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                                               + "Supervisor (If any)"
                                               + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                                               + "On behalf of Registers");
                topicDescriptionDetailsLines2.add("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                                               + "(Sign and full name)"
                                               + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                                               + "(Sign and full name)");

                request.setAttribute("LINES", topicDescriptionDetailsLines);
                request.setAttribute("LINES2", topicDescriptionDetailsLines2);
                request.setAttribute("SUPERVISORS", supervisors);
                request.setAttribute("STUDENTS", students);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ViewTopicDesDetailsController:" + e.toString());
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
