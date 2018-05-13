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
            HttpSession session = request.getSession();
            String usuario = (String)session.getAttribute("usuario");
            String contrasena = (String)session.getAttribute("contrasena");
            String tipoAtt = (String)session.getAttribute("tipo");
            PrintWriter out = response.getWriter();
            //*****************************//
            if(!tipoAtt.equals("1")){
               response.sendRedirect("login.html");
            }
            //*****************************//
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Menu Administrador</title>");     
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Menu Administrador</h1>");
            
            
            out.println("<form action='administrarUsuario' method='get'>");
            out.println("<input type='submit' value='Administrar Usuario'/><br />");
            out.println("</form>");
            
            out.println("<form action='login' method='get'>");
            out.println("<input type='submit' value='Cerrar Sesion'/><br />");
            out.println("</form>");
            
         
            
            out.println("</body>");
            out.println("</html>");
    }

}