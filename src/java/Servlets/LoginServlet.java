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
        //if the user logs out send them to login page with logged out message
        if(request.getParameter("logout") != null){
            //used to clear the session
            request.getSession().invalidate();
            request.setAttribute("message", "Session has been logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response); 
        }
        //else if the username has something, redirect to home servlet
        else if (request.getSession().getAttribute("username") != null){
           response.sendRedirect("home");
        }
        //else send user to login page with no message
        else {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //variables for username and password
        String username = request.getParameter("username");
        String password = request.getParameter("password");
 
        //if nothing is typed in both fields
        if ("".equals(username) || "".equals(password)){
            request.setAttribute("message", "Enter username and password to login");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        //else create a new user object
        else {
            AccountService accServ = new AccountService();
            User user = accServ.login(username, password);
            //if user != null then display the home page with username
            if(user != null){
                request.getSession().setAttribute("username",username);
                response.sendRedirect("home");
                }
            //else the username or password is wrong, display username or password is wrong message and keep the data saved
            else {
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                request.setAttribute("message", "Incorrect username or password");
                 
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        }
    }
}
