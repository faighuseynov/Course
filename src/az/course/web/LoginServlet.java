package az.course.web;

import az.course.dao.UserDao;
import az.course.dao.UserDaoImpl;
import az.course.model.Student;
import az.course.service.UserService;
import az.course.service.UserServiceImpl;
import az.course.util.Security;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: fuadp
 * Date: 4/2/16
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String address = "";
        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);


        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

        if (username != null && password != null) {
           Student user = userService.login(username, Security.encodeMd5(password));
            HttpSession session = request.getSession(true);
            System.out.println("user = "+user);
           if (user == null ) {
              request.setAttribute("msg","Invalid username or password");
              address = "login.jsp";
           }   else {
               session.setAttribute("user",user);
               address = "index.jsp";
           }
        }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(address);
            requestDispatcher.forward(request,response);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
