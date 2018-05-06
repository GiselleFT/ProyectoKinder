package profesor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class adminEjercicio6 extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ejercicio 6: Seleccionar Palabra Fin de Mezclas consonanticas</title>");     
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Ejercicio 6: Seleccionar Palabra Fin de Mezclas consonanticas</h1>");
            
            //Altas, bajas y cambios de ejercicios
            out.println("<form action='crearEjercicio6' method='get'>");
            out.println("<input type='submit' value='Crear ejercicio'/><br />");
            out.println("</form>");
            
            
            out.println("<form action='eliminarEjercicio6' method='get'>");
            out.println("<input type='submit' value='Eliminar ejercicio'/><br />");
            out.println("</form>");
            
            
            out.println("<form action='modificarEjercicio6' method='get'>");
            out.println("<input type='submit' value='Modificar ejercicio'/><br />");
            out.println("</form>");
            
            
            out.println("<br /><br />");
            
            //Cerrar Sesion
            out.println("<form action='login' method='get'>");
            out.println("<input type='submit' value='Cerrar Sesion'/><br />");
            out.println("</form>");
            
            out.println("</body>");
            out.println("</html>");
    }

}