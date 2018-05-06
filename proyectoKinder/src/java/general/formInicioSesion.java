package general;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class formInicioSesion extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            String valorFilas = request.getParameter("claveFilas");
            PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Inicio de sesion</title>");     
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Inicio de sesion</h1>");
            
            out.println("<form action='inicioProfesor' method='get'>");
            out.println("<h6>Usuario:</h6> <input type='text' name='usuario'/><br />");
            out.println("<h6>Contrasena:</h6> <input type='text' name='contrasena'/><br />");
            out.println("<input type='submit' />");
            out.println("</form>");
            
            
            out.println("</body>");
            out.println("</html>");
    }

}