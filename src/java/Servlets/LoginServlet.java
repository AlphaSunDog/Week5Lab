package Servlets;

import Objects.User;
import Services.AccountService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("logout") != null){
            request.setAttribute("message", "Session has been logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response); 
        }
        else if (request.getSession().getAttribute("username") != null){
           response.sendRedirect("home");
        }
        else {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
 
        if ("".equals(username) || "".equals(password)){
            request.setAttribute("message", "Enter username and password to login");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
        else {
            AccountService accServ = new AccountService();
            User user = accServ.login(username, password);
            
            if(user != null){
                request.getSession().setAttribute("username",username);
                response.sendRedirect("home");
                }
            
            else {
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("message", "Incorrect username or password");
                 
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        }
    }
}
