package controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import topicDescription.SelectionCriteriaDAO;

@WebServlet(name = "AddSelectionCriteriaController", urlPatterns = {"/AddSelectionCriteriaController"})
public class AddSelectionCriteriaController extends HttpServlet {
    private static final String ERROR = "createSelCrit.jsp";
    private static final String SUCCESS = "ViewTopicDescriptionsApprovedController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String profession= request.getParameter("profession");
            String grading = request.getParameter("grading");
            String objectives = request.getParameter("objectives");
            List<String> details= new ArrayList<>();
            details.add(grading);
            details.add(objectives);
                    
            SelectionCriteriaDAO dao = new SelectionCriteriaDAO();
            
            Gson g = new Gson();
            Type listType = new TypeToken<ArrayList<String>>(){}.getType();
            String detailsJson= g.toJson(details, listType);
            
            boolean check= dao.insert(profession, detailsJson);
            if (check) {
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at AddSelectionCriteriaController: " + e.toString());
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
