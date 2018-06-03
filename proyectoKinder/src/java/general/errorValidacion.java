package general;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class errorValidacion extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            //Recuperamos la sesion
            HttpSession session = request.getSession();
            //Recupera mensajes de error
            String errorConforme = request.getParameter("errorConforme");
            String errorValido = request.getParameter("errorValido");
            //Recupera tipo de usuario loggeado para redirigir a menú
            String tipoAtt = (String)session.getAttribute("tipo");
            session.setAttribute("tipo", tipoAtt);//conservar sesion
            
            PrintWriter out = response.getWriter();
            
            
//            //Poner boton para regresar al menu, dependiendo del tipo de usuario
//            //********Valida Tipo Usuario****************//
//            if(!tipoAtt.equals("1")){
//               response.sendRedirect("login.html");
//            }
//            //*******************************************//
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error Validacion</title>");
            out.println("<link rel='stylesheet' href='css/estilos.css'>");
            out.println("</head>");
            out.println("<body>");

            //out.println("<div class='contenido'>");
            out.println("<h1>Error validacion</h1>");
            out.println("<br />");
            out.println("<br />");

            out.println("<h3>"+errorConforme+"</h3>");
            out.println("<h3>"+errorValido+"</h3>");
            //Regresar al menu, dependiendo del tipo de usuario
            if(tipoAtt.equals("1")){
                out.println("<form action='menuAdministrador' method='get'>");
                out.println("<input type='submit' value='Regresar al menu'>");
                out.println("</form>");
            }
            else if(tipoAtt.equals("2")){
                
                out.println("<form action='menuProfesor' method='get'>");
                out.println("<input type='submit' value='Regresar al menu'>");
                out.println("</form>");
            }
            else if(tipoAtt.equals("3")){
                out.println("<form action='menuAlumno' method='get'>");
                out.println("<input type='submit' value='Regresar al menu'>");
                out.println("</form>");
            }
            

            out.println("</body>");
            out.println("</html>");
        
    }

}