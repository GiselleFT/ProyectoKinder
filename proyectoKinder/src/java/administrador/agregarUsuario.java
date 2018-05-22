package administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class agregarUsuario extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {  
            response.setContentType("text/html;charset=UTF-8");
            HttpSession session = request.getSession();
            String usuario = (String)session.getAttribute("usuario");
            String contrasena = (String)session.getAttribute("contrasena");
            String tipoAtt = (String)session.getAttribute("tipo");
            PrintWriter out = response.getWriter();
            
            //info del administrador
//            System.out.println("agregarUsuario");
//            System.out.println("admin= "+usuario);
//            System.out.println("admin= "+contrasena);
//            System.out.println("admin= "+tipoAtt);
            
            //********Valida Tipo Usuario****************//
            if(!tipoAtt.equals("1")){
               response.sendRedirect("login.html");
            }
            //*******************************************//
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Agregar Usuario</title>");
            out.println("<link rel='stylesheet' href='css/estilos.css'>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Agregar Usuario</h1>");
            
            out.println("<form action='addUser' method='get'>");
            out.println("<h6>Nombre:</h6> <input id='nombre' type='text' name='nombreNuevo' required/><br />");
            out.println("<h6>Usuario:</h6> <input id='usuario' type='text' name='usuarioNuevo' required/><br />");
            out.println("<h6>Contrasena:</h6> <input id='contrasena' type='password' name='contrasenaNuevo' required/><br />");
            out.println("<h6>Tipo:</h6> <select id='tipo' name='tipoNuevo'>"//Combobox
                    + "<option>Administrador</option>"
                    + "<option>Profesor</option>"
                    + "<option>Alumno</option></select>"
                    + "<br />");
            out.println("<input type='hidden' name='tipo' value="+tipoAtt+">");//Del administrador
            out.println("<br />");
            out.println("<br />");
            out.println("<input type='submit' value='Agregar usuario'>");
            out.println("</form>");
            
            
            out.println("<br />");
            out.println("<br />");
            //Agregar Usuario
            out.println("<form action='menuAdministrador' method='get'>");
            out.println("<input type='submit' value='Menu Administrador'>");
            out.println("</form>");
            
            out.println("</body>");
            out.println("</html>");
    }

}