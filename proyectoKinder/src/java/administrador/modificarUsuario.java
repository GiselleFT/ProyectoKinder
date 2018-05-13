package administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class modificarUsuario extends HttpServlet {
    
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
            out.println("<title>Modificar Profesor</title>");     
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Modificar Profesor</h1>");
            
            out.println("<form action='administrarProfesores' method='get'>");
            out.println("<h6>Nombre:</h6> <input type='text' name='nombre'/><br />");
            out.println("<h6>Usuario:</h6> <input type='text' name='usuario'/><br />");
            out.println("<h6>Contrasena:</h6> <input type='text' name='contrasena'/><br />");
            out.println("<input type='submit' value='Guardar cambios'/><br />");
            out.println("</form>");
            
            
         
            
            out.println("</body>");
            out.println("</html>");
    }

}