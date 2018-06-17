package sawi_servlets;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author VictorFerreiraPereir
 */
public class IndexServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet indexServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println(request.getParameter("name"));
//            out.println("<h1>Servlet indexServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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

        // Browser parameter
        String browser = request.getParameter("browser");
        // Environment parameter
        String env = request.getParameter("env");

        // Tests parameters
        // String[] scripts = request.getParameterValues("chkBox");
        String params = "The parameters are: <br>" + browser + "<br>" + env + "<br>";

        for (int i = 0; i < 3; i++) {
            String[] scripts = request.getParameterValues("chkBox" + i);
            if (scripts == null) {
                params = params + "[off]";
            } else {
                params = params + Arrays.toString(scripts);
            }
//            System.out.println("passou aqui " + i); // Bug - O loop acontece roda duas vezes seguidas
        }
           
        request.setAttribute("params", params); // set your String value in the attribute
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
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

//        processRequest(request, response);
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
