/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alumno;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sam-y
 */
public class resolverEjercicios extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("    <meta charset=\"utf-8\">");
        out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("    <title>Menu Administrador</title>");
        out.println("    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("    <link href=\"font-awesome/css/font-awesome.css\" rel=\"stylesheet\">");
        out.println("    <link href=\"css/animate.css\" rel=\"stylesheet\">");
        out.println("    <link href=\"css/style.css\" rel=\"stylesheet\">");
        out.println("</head>");
        out.println("<body class=\"\">");
        out.println("    <div id=\"wrapper\">");
        out.println("    <nav class=\"navbar-default navbar-static-side\" role=\"navigation\">");
        out.println("        <div class=\"sidebar-collapse\">");
        out.println("            <ul class=\"nav metismenu\" id=\"side-menu\">");
        out.println("<li class=\"nav-header\">");
        out.println("<div class=\"dropdown profile-element\">");
        out.println("	<span>");
        out.println("    	<img alt=\"image\" class=\"img-circle\" src=\"img/munequito.png\" width=\"100\" height=\"100\"/>");
        out.println("    </span>");
        out.println("</div>");
        out.println("</li>");
        out.println("                <li>");
        out.println("                    <a href=\"#\"><i class=\"fa fa-user-circle\"></i> <span class=\"nav-label\">Menu profesor</span><span class=\"fa arrow\"></span></a>");
        out.println("                    <ul class=\"nav nav-second-level collapse\">");
        out.println("                        <li><a href=\"resolverEjercicios\">Resolver Ejercicios</a></li>");
        out.println("                        <li><a href=\"verCalificaciones\">Ver Calificaciones</a></li>");
        out.println("                    </ul>");
        out.println("                </li>");
        out.println("                <li class=\"special_link\">");
        out.println("                    <a href=\"cerrarSesion\"><i class=\"fa fa-times-rectangle\"></i> <span class=\"nav-label\">Cerrar sesion</span></a>");
        out.println("                </li>");
        out.println("            </ul>");
        out.println("");
        out.println("        </div>");
        out.println("    </nav>");
        out.println("        <div id=\"page-wrapper\" class=\"gray-bg\">");
        out.println("        <div class=\"row border-bottom\">");
        out.println("        <nav class=\"navbar navbar-static-top  \" role=\"navigation\" style=\"margin-bottom: 0\">");
        out.println("        <div class=\"navbar-header\">");
        out.println("            <a class=\"navbar-minimalize minimalize-styl-2 btn btn-primary \" href=\"#\"><i class=\"fa fa-bars\"></i> </a>");
        out.println("        </div>");
        out.println("");
        out.println("        </nav>");
        out.println("        </div>");
        out.println("            <div class=\"row wrapper border-bottom white-bg page-heading\">");
        out.println("                <center>");
        out.println("                    <h2><font color=\"blue\"><b>Instrucciones: </b></font></h2>");
        out.println("                    <br/>");
        out.println("                    <p>");
        out.println("                    <h3>Jugarás una ronda de 3 ejercicios y deberás conseguir la mayor puntuación posible.<br/>");
        out.println("                    Una vez iniciada la partida no podrás cancelarla hasta concluir con los 3 ejercicios.<br/></h3>");
        out.println("                    <h3><b>Suerte!!</b></h3>");
        out.println("                    </p>");
        out.println("                    <br/>");
        out.println("                    <h2><font color=\"blue\"><b>");
        out.println("                    Puntajes si respondes al:");
        out.println("                    </b></font></h2>");
        out.println("                    <h3><p>");
        out.println("                    1er intento - 3 puntos");
        out.println("                    </p>");
        out.println("                    <p>");
        out.println("                    2do intento - 1 punto");
        out.println("                    </p>");
        out.println("                    <p>");
        out.println("                    3er intento - 0 puntos");
        out.println("                    </p></h3>");
        out.println("                    <br/>");
        out.println("<form action='ejercicio1' method='get'>");
        out.println("<h6><input type='submit' value='Comenzar' class=\"btn btn-sm btn-success\"></h6>");
        out.println("</form>");
        out.println("                    <br/>");
        out.println("                    <br/>");
        out.println("<form action='menuAlumno' method='get'>");
        out.println("<input type='submit' value='Regresar' class=\"btn btn-sm btn-warning\">");
        out.println("</form>");
        out.println("                </center>");
        out.println("            </div>");
        out.println("        </div>");
        out.println("        </div>");
        out.println("    <script src=\"js/jquery-3.1.1.min.js\"></script>");
        out.println("    <script src=\"js/bootstrap.min.js\"></script>");
        out.println("    <script src=\"js/plugins/metisMenu/jquery.metisMenu.js\"></script>");
        out.println("    <script src=\"js/plugins/slimscroll/jquery.slimscroll.min.js\"></script>");
        out.println("    <script src=\"js/inspinia.js\"></script>");
        out.println("    <script src=\"js/plugins/pace/pace.min.js\"></script>");
        out.println("</body>");
        out.println("</html>");
        out.println("");
    }
}
