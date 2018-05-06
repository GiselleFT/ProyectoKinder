package general;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class login extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Mezclas consonanticas y digrafos</title>");     
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Modulo: Mezclas consonanticas y digrafos</h1>");
            
            out.println("<form action='formInicioSesion' method='get'>");
            out.println("<input type='submit' value='Iniciar Sesion'/>");
            out.println("</form>");
            out.println("<br />");
            out.println("<br />");
            
            
            out.println("<form action='formRegistrar' method='get'>");
            out.println("<input type='submit' value='Registrar'/>");
            out.println("</form>");
            
            
            out.println("</body>");
            out.println("</html>");
    }

}
