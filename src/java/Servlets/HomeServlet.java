package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if (request.getSession().getAttribute("username") == null){
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
        else if (request.getParameter("logout") != null){
          response.sendRedirect("login");
        }
        
        else {
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        }
    }
}
