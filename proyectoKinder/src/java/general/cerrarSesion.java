package general;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class cerrarSesion extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            response.setContentType("text/html;charset=UTF-8");
            //Recuperamos la sesion
            HttpSession session = request.getSession();
//            String usuario = (String) session.getAttribute("usuario");
//            String contrasena = (String) session.getAttribute("contrasena");
//            String tipoAtt = (String) session.getAttribute("tipo");
//            String idUsuario = (String) session.getAttribute("idUsuario");
            
            session.removeAttribute("usuario");
            session.removeAttribute("contrasena");
            session.setAttribute("tipo", "4");
            //session.removeAttribute("tipo");
            session.removeAttribute("idUsuario");
            
            
            String usuario2 = (String) session.getAttribute("usuario");
            String contrasena2 = (String) session.getAttribute("contrasena");
            String tipoAtt2 = (String) session.getAttribute("tipo");
            String idUsuario2 = (String) session.getAttribute("idUsuario");
            System.out.println("VALORES DESPUES DE CERRAR SESION:");
            System.out.println("usuario: " + usuario2);
            System.out.println("contraseña: " + contrasena2);
            System.out.println("tipoAtt: " + tipoAtt2);
            System.out.println("idUsuario: " + idUsuario2);
            System.out.println("FIN DATOS");
            
            
            response.sendRedirect("login.html");
            
            
            
           
    }

}
