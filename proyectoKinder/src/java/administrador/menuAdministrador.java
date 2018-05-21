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
            //Recuperamos la sesion
            HttpSession session = request.getSession();
            String usuario = (String)session.getAttribute("usuario");
            String contrasena = (String)session.getAttribute("contrasena");
            String tipoAtt = (String)session.getAttribute("tipo");
            PrintWriter out = response.getWriter();
            
            //info del administrador
//            System.out.println("menuAdministrador");
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
            out.println("<title>Menu Administrador</title>");     
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Menu Administrador</h1>");
            out.println("<br />");
            out.println("<h1>Bienvenido Administrador:"+usuario+"</h1>");
            
            //Alta, baja y cambio de usuarios
            out.println("<form action='administrarUsuario' method='get'>");
            out.println("<input type='submit' value='Administrar Usuario'/><br />");
            out.println("</form>");
            
            //Alta, baja y cambio de grupos
            out.println("<form action='administrarGrupos' method='get'>");
            out.println("<input type='submit' value='Administrar Grupos'/><br />");
            out.println("</form>");
            
            //Checar??
            out.println("<form action='login' method='get'>");
            out.println("<input type='submit' value='Cerrar Sesion'/><br />");
            out.println("</form>");
            
         
            
            out.println("</body>");
            out.println("</html>");
    }

}