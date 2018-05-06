package profesor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class menuProfesor extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Menu Profesor</title>");     
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Menu Profesor</h1>");
            
            //Altas, bajas y cambios de ejercicios
            out.println("<h2>Administrar Ejercicios</h2>");
            
            out.println("<form action='adminEjercicio1' method='get'>");
            out.println("<input type='submit' value='Seleccionar Inicio de Mezclas consonanticas'/><br />");
            out.println("</form>");
            
            out.println("<form action='adminEjercicio2' method='get'>");
            out.println("<input type='submit' value='Completar Inicio de Mezclas consonanticas'/><br />");
            out.println("</form>");
            
            out.println("<form action='adminEjercicio3' method='get'>");
            out.println("<input type='submit' value='Seleccionar Palabra Inicio de Mezclas consonanticas'/><br />");
            out.println("</form>");
            
            out.println("<form action='adminEjercicio4' method='get'>");
            out.println("<input type='submit' value='Seleccionar Fin de Mezclas consonanticas'/><br />");
            out.println("</form>");
            
            out.println("<form action='adminEjercicio5' method='get'>");
            out.println("<input type='submit' value='Completar Fin de Mezclas consonanticas'/><br />");
            out.println("</form>");
            
            out.println("<form action='adminEjercicio6' method='get'>");
            out.println("<input type='submit' value='Seleccionar Palabra Fin de Mezclas consonanticas'/><br />");
            out.println("</form>");
            
            out.println("<form action='adminEjercicio7' method='get'>");
            out.println("<input type='submit' value='Seleccionar Palabra'/><br />");
            out.println("</form>");
            
            out.println("<form action='adminEjercicio8' method='get'>");
            out.println("<input type='submit' value='Seleccionar Digrafo'/><br />");
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