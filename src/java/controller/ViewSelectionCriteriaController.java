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
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import topicDescription.SelectionCriteriaDAO;

@WebServlet(name = "ViewSelectionCriteriaController", urlPatterns = {"/ViewSelectionCriteriaController"})
public class ViewSelectionCriteriaController extends HttpServlet {
    private static final String ERROR = "selectionCriteria.jsp";
    private static final String SUCCESS = "selectionCriteria.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String profession = request.getParameter("profession");
            SelectionCriteriaDAO dao = new SelectionCriteriaDAO();
            String details= dao.getDetails(profession);
            if (!details.isEmpty()) {
                Gson g = new Gson();
                Type listType = new TypeToken<ArrayList<String>>(){}.getType();
                List<String> selectionCriteria= g.fromJson(details, listType);

                request.setAttribute("GRADING", selectionCriteria.get(0));
                request.setAttribute("OBJECTIVES", selectionCriteria.get(1));
                url = SUCCESS;
            }
        } catch (Exception e) {
            log("Error at ViewSelectionCriteriaController:" + e.toString());
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
