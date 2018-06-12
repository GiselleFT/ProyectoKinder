package profesor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class adminGrupos extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String usuario = (String) session.getAttribute("usuario");
            String tipoAtt = (String) session.getAttribute("tipo");
            String idUsuario = (String) session.getAttribute("idUsuario");
            System.out.println("ID DE USUARIO! " + idUsuario);
            PrintWriter out = response.getWriter();
            
            //********Valida Tipo Usuario****************//
            if (!tipoAtt.equals("2") || usuario.isEmpty() || idUsuario.isEmpty()) {
                response.sendRedirect("login.html");
            }
            //*******************************************//
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet adminGrupos</title>");  
            out.println("<link rel='stylesheet' href='css/estilos.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div align='center'>");
            out.println("<h1>En construccion...</h1>");
            out.println("<br/>");
            out.println("<h1>Profe, ya pónganos 10 :D jeje</h1>");
            out.println("<image src='archivos/404.jpg' width='800' height='500'/>");
            out.println("<center><h1><a href='menuProfesor'>!Regresar al menu</a></h1></center>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
    }

}
