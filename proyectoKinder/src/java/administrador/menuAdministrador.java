package administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class menuAdministrador extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Menu Administrador</title>");     
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Menu Administrador</h1>");
            
            
            out.println("<form action='administrarProfesores' method='get'>");
            out.println("<input type='submit' value='Administrar Profesores'/><br />");
            out.println("</form>");
            
            out.println("<form action='login' method='get'>");
            out.println("<input type='submit' value='Cerrar Sesion'/><br />");
            out.println("</form>");
            
         
            
            out.println("</body>");
            out.println("</html>");
    }

}