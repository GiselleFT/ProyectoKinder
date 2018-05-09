package administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class adminProfesores extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Administrar Profesores</title>");     
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Administrar Profesores</h1>");
            
            //Alta
            out.println("<form action='agregarProfesor' method='get'>");
            out.println("<input type='submit' value='Agregar Profesor'/><br />");
            out.println("</form>");
            
            //Baja
            out.println("<form action='adminProfesores' method='get'>");
            out.println("<input type='submit' value='Eliminar'/><br />");
            out.println("</form>");
            
            //Cambio
            out.println("<form action='modificarProfesor' method='get'>");
            out.println("<input type='submit' value='Modificar'/><br />");
            out.println("</form>");
            
            
            
         
            
            out.println("</body>");
            out.println("</html>");
    }

}