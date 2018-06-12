package alumno;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class menuAlumno extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            //Recuperamos la sesion
            HttpSession session = request.getSession();
            String usuario = (String)session.getAttribute("usuario");
            String tipoAtt = (String)session.getAttribute("tipo");
            PrintWriter out = response.getWriter();
            
            //info del alumno
//            System.out.println("menuAlumno");
//            System.out.println("alumno= "+usuario);
//            System.out.println("alumno= "+contrasena);
//            System.out.println("alumno= "+tipoAtt);
            
            //********Valida Tipo Usuario****************//
            if(!tipoAtt.equals("3")){
               response.sendRedirect("login.html");
            }
            //*******************************************//
            System.out.println("ENTRE AL MENU ALUMNO");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Menu Alumno</title>"); 
            out.println("<link rel='stylesheet' href='css/estilos.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Menu Alumno</h1>");
            out.println("<br />");
            out.println("<h1>Bienvenido Alumno: "+usuario+"</h1>");
            
            //Cerrar Sesion
            out.println("<form action='cerrarSesion' method='get'>");
            out.println("<input type='submit' value='Cerrar Sesion'/><br />");
            out.println("</form>");
            
            out.println("</body>");
            out.println("</html>");
    }

}