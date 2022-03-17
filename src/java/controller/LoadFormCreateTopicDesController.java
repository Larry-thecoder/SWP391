package controller;

import java.io.IOException;
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

@WebServlet(name = "LoadFormCreateTopicDesController", urlPatterns = {"/LoadFormCreateTopicDesController"})
public class LoadFormCreateTopicDesController extends HttpServlet {
    private static final String ERROR = "createTP.jsp";
    private static final String SUCCESS = "createTP.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            SupervisorDAO dao = new SupervisorDAO();
            List<SupervisorDTO> supervisors = dao.getListSupervisor();
            if (!supervisors.isEmpty()) {
                request.setAttribute("LIST_SUPERVISOR", supervisors);
                url = SUCCESS;
            }
            StudentDAO dao2 = new StudentDAO();
            List<StudentDTO> students = dao2.getListStudent();
            if (!students.isEmpty()) {
                request.setAttribute("LIST_STUDENT", students);
                url = SUCCESS;
            }
            SpecialDAO dao3 = new SpecialDAO();
            List<String> professions = dao3.getListProfession();
            if (!professions.isEmpty()) {
                request.setAttribute("LIST_PROFESSION", professions);
                url = SUCCESS;
            }
            List<String> specials = dao3.getListSpecial();
            if (!specials.isEmpty()) {
                request.setAttribute("LIST_SPECIAL", specials);
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at LoadFormCreateTopicDesController:" + e.toString());
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
